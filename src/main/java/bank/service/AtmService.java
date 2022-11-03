package bank.service;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.entity.Employee;

public interface AtmService {
    /*Добавление суммы денег в банкомат, а, соответственно, добавление суммы денег в офис банка
     * и в банк, которому принадлежит данный банкомат, с учётом того, работает ли банкомат. Если он
     * работает, то деньги вносятся и возвращается true, иначе false*/
    Boolean addMoney(BankATM bankATM, Double sumMoney);

    /*Вычитание суммы денег из банкомата, и, соответственно, вычитание суммы денег из офиса банка и банка,
     * которому принадлежит данный банкомат, с проверкой того, достаточно ли денег в банкомате, чтобы их вычесть.
     * Если не достаточно, то возвращается false, иначе true. И с учётом того, работает ли банкомат и есть ли в
     * нём деньги. Если он работает и в нём есть деньги, то деньги вычитаются и возвращается true, иначе false*/
    Boolean subtractMoney(BankATM bankATM, Double sumMoney);

    

    /*Привязать банкомат к банку.*/
    //void linkBank(BankATM bankATM, Bank bank);
    /*Привязать банкомат к офису.*/
    //void linkBankOffuce(BankATM bankATM, BankOffice bankOffice);

    /*Привязать банкомат к сотруднику.*/
    //void linkBankOffuce(BankATM bankATM, Employee employee);
}