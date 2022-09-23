package bank.service.impl;

import bank.entity.Bank;
import bank.service.BankService;

public class BankServiceImpl implements BankService {
    /*Добавление суммы денег в банк*/
    @Override
    public void addMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoneyBank();
        bank.setMoneyBank(sum + sumMoney);
    }

    /*Вычитание суммы денег из банка*/
    @Override
    public Boolean subtractMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoneyBank();
        if (sumMoney > sum)
            return Boolean.FALSE;
        bank.setMoneyBank(sum - sumMoney);
        return Boolean.TRUE;
    }
}
