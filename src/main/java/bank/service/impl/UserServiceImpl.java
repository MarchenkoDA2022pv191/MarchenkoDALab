package bank.service.impl;

import bank.entity.*;
import bank.exceptions.*;
import bank.service.BankService;
import bank.service.CreditAccountService;
import bank.service.PaymentAccountService;
import bank.service.UserService;
import bank.utils.StatusATM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    @Override
    public void changeWork(User user, String newWork, Double newMonthSalary) {
        user.setWork(newWork);
        user.setMonthSalary(newMonthSalary);
        int creditRating = 0;
        int startRat = 0;
        int endRat = 1000;
        while ((startRat != 10000) && (creditRating == 0)) {
            if ((newMonthSalary <= endRat) && (newMonthSalary >= startRat))
                creditRating = endRat / 10;
            else {
                startRat += 1000;
                endRat += 1000;
            }
        }
        user.setCreditRating(creditRating);
    }

    @Override
    public String getInfo(User user){
        String res = "";

        res += user.toString();

        res += "\n\nИнформация о платёжных счётах:\n";
        for (PaymentAccount paymentAccount: user.getPaymentAccounts()){
            res += paymentAccount.toString();
            res += "\n\n";
        }

        res += "\n\nИнформация о кредитных счётах:\n";
        for (CreditAccount creditAccount: user.getCreditAccounts()){
            res += creditAccount.toString();
            res += "\n\n";
        }


        return res;
    }



    @Override
    public Boolean applyForLoan(ArrayList<Bank> banks, User user, Double loanSum, LocalDate startDate,
                                Integer countMonth, Integer newId) {

        try {
            PaymentAccountService payAcc = new PaymentAccountServiceImpl();
            CreditAccountService creditAcc = new CreditAccountServiceImpl();
            ArrayList<Bank> banksWithMoney = new ArrayList<>();
            ArrayList<Double> criteria = new ArrayList<>();
            for (Bank bank : banks) {
                if (bank.getMoney() >= loanSum) {
                    banksWithMoney.add(bank);
                    criteria.add(bank.getBankOffices().size() + bank.getBankATMS().size() +
                            bank.getEmployees().size() + (20 - bank.getInterestRate()));
                }
            }
            if (banksWithMoney.isEmpty()) {
                throw new NoBankMoneyException();
            }
            Double maxRating = 0.0;
            Bank bank = null;
            for (int i = 0; i < criteria.size(); i++) {
                if (criteria.get(i) > maxRating) {
                    maxRating = criteria.get(i);
                    bank = banksWithMoney.get(i);
                }
            }
            try {
                ArrayList<BankOffice> availableOffices = new ArrayList<>();
                for (BankOffice office : bank.getBankOffices()) {
                    if (office.getMayApplyCredit() && (office.getStatus()) &&
                            (office.getMoney() >= loanSum)) {
                        availableOffices.add(office);
                    }
                }
                if (availableOffices.isEmpty()) {
                    throw new NoEligibleOffice();
                }
                int currentOffice = -1;
                boolean inOfficeNotOk = true;
                do {
                    try {
                        currentOffice++;
                        if ((currentOffice + 1) > availableOffices.size()) {
                            inOfficeNotOk = false;
                            throw new NoEligibleEmployee();
                        }
                        BankOffice office = availableOffices.get(currentOffice);
                        ArrayList<Employee> availableEmployees = new ArrayList<>();
                        for (Employee employee : office.getEmployees()) {
                            if (employee.getCanLend()) {
                                availableEmployees.add(employee);
                            }
                        }
                        if (availableEmployees.isEmpty()) {
                            throw new NoEligibleEmployee();
                        }
                        try {
                            ArrayList<BankATM> availableATMs = new ArrayList<>();
                            for (BankATM atm : office.getBankATMS()) {
                                if ((atm.getStatus() == StatusATM.Work) && atm.getCanPayMoney()) {
                                    availableATMs.add(atm);
                                }
                            }
                            if (availableATMs.isEmpty()) {
                                throw new NoEligibleAtm();
                            }
                            int currentATM = -1;
                            boolean isAtmNotOk = true;
                            do {
                                try {
                                    currentATM++;
                                    if ((currentATM + 1) > availableATMs.size()) {
                                        throw new NoEligibleAtm();
                                    }
                                    if (availableATMs.get(currentATM).getMoney() < loanSum) {
                                        throw new BankAtmException();
                                    }
                                    else {
                                        try {
                                            if (user.getCreditRating()/10 < bank.getRating()) {
                                                throw new BadUserRatingException(bank.getRating(),
                                                        user.getCreditRating()/10);
                                            }
                                            else {
                                                isAtmNotOk = false;
                                                inOfficeNotOk = false;
                                                if (!bank.getClients().contains(user)) {
                                                    payAcc.addPayment(newId, user, bank);
                                                }
                                                PaymentAccount paymentAcc =null;
                                                for (PaymentAccount item : user.getPaymentAccounts()){
                                                    if (Objects.equals(item.getBank(), bank)){
                                                        paymentAcc = item;
                                                        break;
                                                    }
                                                }
                                                creditAcc.openCredit(newId, user, office,
                                                        availableEmployees.get(0),paymentAcc,
                                                        startDate, countMonth, loanSum);
                                            }
                                        } catch (BadUserRatingException e) {
                                            e.printStackTrace();
                                            return false;
                                        }
                                    }
                                } catch (BankAtmException e) {
                                    System.out.println("\n" + e.toString() + "\nПереходим к следующему банкомату");
                                }
                            } while (isAtmNotOk);
                        } catch (NoEligibleAtm e) {
                            System.out.println("\n" + e.toString() + "\nПереходим к следующему офису");
                        }
                    } catch (NoEligibleEmployee e) {
                        System.out.println("\n" + e.toString() + "\nПереходим к следующему офису");
                    }
                } while (inOfficeNotOk);
                if ((currentOffice + 1) > availableOffices.size()) {
                    throw new NoEligibleOffice();
                }
            } catch (NoEligibleOffice e) {
                e.printStackTrace();
                return false;
            }
        } catch (NoBankMoneyException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}