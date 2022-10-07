package bank.service;

import bank.entity.Bank;

public interface BankService {
    /*Добавление суммы денег в банк*/
    void addMoney(Bank bank, Double sumMoney);
    /*Вычитание суммы денег из банка*/
    Boolean subtractMoney(Bank bank, Double sumMoney);
}