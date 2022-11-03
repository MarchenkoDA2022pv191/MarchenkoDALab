package bank.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import bank.entity.common.Person;

public class User extends Person {
    private String work;
    private Double monthSalary;

    private Bank bank;
    private ArrayList<CreditAccount> creditAccounts;
    private ArrayList<PaymentAccount> paymentAccounts;
    private Integer creditRating;

    public User(Integer id, String name, String surname, Date birthday, String work) {
        super(id,name,surname,birthday);
        this.setWork(work);
        Random random = new Random();
        this.setMonthSalary(random.nextDouble(1, 10000));
        int creditRating = 0;
        Integer startRat = 0;
        Integer endRat = 1000;
        while ((startRat != 10000) && (creditRating == 0)) {
            if ((getMonthSalary() <= endRat) && (getMonthSalary() >= startRat))
                creditRating = endRat / 10;
            else {
                startRat += 1000;
                endRat += 1000;
            }
        }
        this.creditRating = creditRating;
        this.bank = null;
        this.paymentAccounts = null;
        this.creditAccounts = null;
    }


    @Override
    public String toString() {
        String str =  "Имя: " + getFullName() +
                "\nДата рождения: " + birthday +
                "\nРабота: " + work +
                "\nБанк: " + bank +
                "\nЗарплата: " + monthSalary;
        if (bank == null){
            str += "\nБанк: пусто";
        }
        else {
            str += "\nБанк: " + bank.getName();
        }

        if (getCreditAccounts() == null){
            str += "\nКредитный аккаунт: пусто";
        }
        else {
            str += "\nКоличество кредитных аккаунтов: " + getCreditAccounts().size();
        }

        if (getPaymentAccounts() == null){
            str += "\nПлатёжный аккаунт: пусто";
        }
        else {
            str += "\nоличество платёжных аккаунтов: " + getPaymentAccounts().size();
        }
        str += "\nКредитный рейтинг: " + creditRating;
        return str;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(Double monthSalary) {
        this.monthSalary = monthSalary;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }



    public Integer getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(Integer creditRating) {
        this.creditRating = creditRating;
    }

    public ArrayList<CreditAccount> getCreditAccounts() {
        return creditAccounts;
    }

    public void setCreditAccounts(ArrayList<CreditAccount> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

    public ArrayList<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public void setPaymentAccounts(ArrayList<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }
}
