package bank;

import bank.entity.*;
import bank.service.impl.*;
import bank.utils.FullName;
import bank.utils.StatusATM;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //Bank
        Bank bank = new Bank(1, "bank_name");
        BankServiceImpl bankService = new BankServiceImpl();
        bankService.addMoney(bank,90000.0);
        bankService.subtractMoney(bank,519.0);


        //Bank Office
        BankOffice office = new BankOffice( 1, "office_name", "adress",true, 500.0);
        BankOfficeServiceImpl bankOfficeService = new BankOfficeServiceImpl();
        office.setBank(bank);
        bank.setCountOffice(bank.getCountOffice() + 1);
        bankOfficeService.addMoney(office, 1000.0);

        //BankAtm
        BankATM atm = new BankATM(1, "ATM_1", StatusATM.Work, Boolean.TRUE, Boolean.TRUE,
                500.0);
        AtmServiceImpl atmService = new AtmServiceImpl();

        bankOfficeService.addATM(office,atm);

        atmService.addMoney(atm, 1000.0);
        //atm.setBank(bank);
        bankOfficeService.addATM(office, atm);


        //Employee
        Employee employee = new Employee(1, new FullName("Ivan", "Sevor"),
                new Date(19081917), bank, "job1", 10000.0, office );
        bank.setCountEmployees(bank.getCountEmployees() + 1);
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        employeeService.toDistantWork(employee);
        atm.setEmployee(employee);

        //User
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User( 1, new FullName("Seryy", "Ivanov"), new Date(10112000),
                "work_1");
        user.setBank(bank);
        bank.setCountClients(bank.getCountClients() + 1);
        userService.changeWork(user, "work_2", 7000.0);

        //Payment Account
        PaymentAccountServiceImpl paymentAccountService = new PaymentAccountServiceImpl();
        PaymentAccount payAcc = new PaymentAccount(1, user, bank);
        paymentAccountService.addMoney(payAcc, 5000.0);

        //Credit Account
        CreditAccount creditAcc = new CreditAccount(1,user, bank, employee, payAcc, LocalDate.of(2020,11,12),
                12, 150.0);
        CreditAccountServiceImpl creditAccountService = new CreditAccountServiceImpl();
        if (creditAccountService.applyLoan(creditAcc, employee))
            System.out.println("?????????????????? ???????????????? ????????????");
        else System.out.println("???? ?????????????? ???????????????? ????????????");

        user.setCreditAccount(creditAcc);
        user.setPaymentAccount(payAcc);


        System.out.println("\n????????:");
        System.out.println(bank.toString());

        System.out.println("\n???????????????????? ????????:");
        System.out.println(office.toString());

        System.out.println("\n????????????????:");
        System.out.println(atm.toString());

        System.out.println("\n??????????????????:");
        System.out.println(employee.toString());

        System.out.println("\n?????????????????? ????????:");
        System.out.println(payAcc.toString());

        System.out.println("\n?????????????????? ????????:");
        System.out.println(creditAcc.toString());

        System.out.println("\n????????????????????????:");
        System.out.println(user.toString());
    }

}