package bank.service;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;

public interface BankService {
    /*Добавление суммы денег в банк*/
    void addMoney(Bank bank, Double sumMoney);
    /*Вычитание суммы денег из банка*/
    Boolean subtractMoney(Bank bank, Double sumMoney);

    //Добавить банкомат банку
    Boolean addBankATM(Bank bank, BankATM bankATM);

    //Убрать банкомат у банка. Для этого надо убрать банкомат у соответсвующего
    //банковского офиса
    Boolean deleteBankATM(Bank bank, BankATM bankATM);

    //Добавить банковский офис.
    Boolean addOffice(Bank bank, BankOffice bankOffice);

    //Убрать банковский офис. Все банкоматы этого офиса убираются банку
    Boolean deleteOffice(Bank bank, BankOffice bankOffice);
}