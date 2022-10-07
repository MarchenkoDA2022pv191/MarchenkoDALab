package bank.entity;

import java.util.Date;

import bank.entity.common.Person;
import bank.utils.FullName;

public class Employee extends Person {
    private String job;
    private Bank bank;
    private Boolean distantWork;
    private BankOffice bankOffice;
    private Boolean canLend;
    private Double salary;

    public Employee(Integer id, FullName name, Date birthday, Bank bank, String job, Double salary, BankOffice bankOffice ) {

        super(id,name,birthday);
        this.bank = bank;
        this.job = job;
        this.salary = salary;
        this.distantWork = true;
        this.canLend = true;
        this.bankOffice= bankOffice;
    }

    @Override
    public String toString() {
        String str =  "Имя: " + name.toString() +
                "\nДата рождения: " + birthday +
                "\nДолжность: " + job +
                "\nБанк: " + bank.getName();
        if (distantWork){
            str += "\nРаботает в офисе";
        }
        else{
            str += "\nРаботает удалённо";
        }
        if (canLend){
            str += "\nМожет выдавать кредиты";
        }
        else{
            str += "\nНе Может выдавать кредиты";
        }
        str += "\nОфис: " + bankOffice.getName() +
                "\nЗарплата: " + salary;

        return str;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Boolean getDistantWork() {
        return distantWork;
    }

    public void setDistantWork(Boolean distantWork) {
        this.distantWork = distantWork;
    }

    public BankOffice getBankOffice() {
        return bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public Boolean getCanLend() {
        return canLend;
    }

    public void setCanLend(Boolean canLend) {
        this.canLend = canLend;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
