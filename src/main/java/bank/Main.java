package bank;

import bank.entity.*;
import java.util.Date;
import bank.entity.helpClass.FullName;
import bank.entity.helpClass.StatusATM;
import bank.service.impl.*;
import bank.entity.helpClass.StatusOffice;

public class Main {
    public static void main(String[] args) {
        //Bank
        Bank bank = new Bank(1, "bank_name");
        BankServiceImpl bankService = new BankServiceImpl();
        System.out.println("Банк:");
        System.out.println("Старая сумма = " + bank.getMoneyBank());
        bankService.addMoney(bank,90000.0);
        System.out.println("Сумма после прибавки = " + bank.getMoneyBank());
        bankService.subtractMoney(bank,519.0);
        System.out.println("Сумма после вычета = " + bank.getMoneyBank());

        //Bank Office
        System.out.println("\nБаковский офис:");
        BankOffice office = new BankOffice(bank, 1, "office_name", "address",
                StatusOffice.Work, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,0.0,5.0);
        BankOfficeServiceImpl bankOfficeService = new BankOfficeServiceImpl();
        System.out.println("Текущая сумма у офиса= " + office.getMoneyOffice());
        System.out.println("Текущее кол-во банкоматов у офиса = " + office.getCountATMOffice());
        bankOfficeService.addMoney(office, 1000.0);
        bankOfficeService.addATM(office);
        System.out.println("Новая сумма у офиса = " + office.getMoneyOffice());
        System.out.println("Новая сумма у банка = " + office.getMoneyBank());
        System.out.println("Новое кол-во банкоматов у офиса = " + office.getCountATMOffice());
        System.out.println("Новое кол-во банкоматов у банка = " + office.getCountATMBank());

        //Employee
        System.out.println("\nСотрудник:");
        Employee employee = new Employee(office, 1, new FullName("Ivan", "Sevor"),
                new Date(19081917), "employer_1", Boolean.TRUE, Boolean.TRUE, 100.0);
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        if (employee.getDistantWork())
            System.out.println("Работаeт в офисе");
        else
            System.out.println("Работаeт на дому");
        employeeService.toDistantWork(employee);
        if (employee.getDistantWork())
            System.out.println("Работаeт в офисе");
        else
            System.out.println("Работаeт на дому");

        //BankAtm
        System.out.println("\nБанкомат:");
        BankATM atm = new BankATM(employee, 1, "ATM_1", StatusATM.Work, Boolean.TRUE, Boolean.TRUE,
                500.0, 50.0);
        AtmServiceImpl atmService = new AtmServiceImpl();
        System.out.println("Текущая сумма у Банкомата = " + atm.getMoneyATM());
        atmService.addMoney(atm, 1000.0);
        System.out.println("Новая сумма у Банкомата = " + atm.getMoneyATM());
        System.out.println("Новая сумма у офиса = " + atm.getMoneyOffice());
        System.out.println("Новая сумма у банка = " + atm.getMoneyBank());

        //User
        System.out.println("\nПользователь:");
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User(bank, 1, new FullName("Ivan", "Sevor"), new Date(10112000),
                "work_1");
        System.out.println("Работа пользователя = " + user.getWorkUser());
        System.out.println("Зарплата = " + user.getMonthSalary());
        System.out.println("Рейтинг = " + user.getCreditRating());
        userService.changeWork(user, "work_2", 7000.0);
        System.out.println("Новая работа = " + user.getWorkUser());
        System.out.println("Новая зарплата = " + user.getMonthSalary());
        System.out.println("Новая рейтинг = " + user.getCreditRating());

        //Payment Account
        System.out.println("\nПлатёжный счёт:");
        PaymentAccountServiceImpl paymentAccountService = new PaymentAccountServiceImpl();
        PaymentAccount payAcc = new PaymentAccount(user, 1);
        System.out.println("Сумма на счету = " + payAcc.getAmountPayAcc());
        paymentAccountService.addMoney(payAcc, 5000.0);
        System.out.println("Новая сумма на счету = " + payAcc.getAmountPayAcc());
        System.out.println("Новая сумма в банке = " + payAcc.getMoneyBank());

        //Credit Account
        System.out.println("\nКредитный счёт:");
        CreditAccount creditAcc = new CreditAccount(payAcc, 1, new Date(10112020), new Date(10112021),
                12, 150.0, 1);
        CreditAccountServiceImpl creditAccountService = new CreditAccountServiceImpl();
        if (creditAccountService.applyLoan(creditAcc, employee))
            System.out.println("Разрешили получить кредит");
        else System.out.println("Не удалось получить кредит");
    }
}