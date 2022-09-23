package bank.service.impl;

import bank.entity.PaymentAccount;
import bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    /*Добавление суммы денег на платёжный счёт, и, соответственно, добавление суммы денег в банк,
    которому принадлежит данный платёжный счёт*/
    @Override
    public void addMoney(PaymentAccount payAcc, Double sumMoney) {
        payAcc.setAmountPayAcc(payAcc.getAmountPayAcc() + sumMoney);
        payAcc.setMoneyBank(payAcc.getMoneyBank() + sumMoney);
    }

    /*Вычитание суммы денег с платёжного счёта, и, соответственно, вычитание суммы денег из банка, которому
     * принадлежит данный платёжный счёт, с проверкой того, достаточно ли денег на платёжном счету, чтобы их
     * вычесть.*/
    @Override
    public Boolean subtractMoney(PaymentAccount payAcc, Double sumMoney) {
        if (payAcc.getAmountPayAcc() < sumMoney)
            return Boolean.FALSE;
        payAcc.setAmountPayAcc(payAcc.getAmountPayAcc() - sumMoney);
        payAcc.setMoneyBank(payAcc.getMoneyBank() - sumMoney);
        return Boolean.TRUE;
    }
}
