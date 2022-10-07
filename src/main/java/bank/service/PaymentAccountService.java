package bank.service;

import bank.entity.PaymentAccount;

public interface PaymentAccountService {
    /*Добавление суммы денег на платёжный счёт*/
    void addMoney(PaymentAccount payAcc, Double sumMoney);
    /*Вычитание суммы денег с платёжного счёта*/
    Boolean subtractMoney(PaymentAccount payAcc, Double sumMoney);
}
