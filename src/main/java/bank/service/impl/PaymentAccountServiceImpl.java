package bank.service.impl;

import bank.entity.PaymentAccount;
import bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    @Override
    public void addMoney(PaymentAccount payAcc, Double sumMoney) {
        payAcc.setSum(payAcc.getSum() + sumMoney);
    }


    @Override
    public Boolean subtractMoney(PaymentAccount payAcc, Double sumMoney) {
        if (payAcc.getSum() < sumMoney)
            return Boolean.FALSE;
        payAcc.setSum(payAcc.getSum() - sumMoney);
        return Boolean.TRUE;
    }
}
