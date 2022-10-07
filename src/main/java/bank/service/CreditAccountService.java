package bank.service;

import bank.entity.CreditAccount;
import bank.entity.Employee;

public interface CreditAccountService {
    /*Одобрение заявки на кредит. В случае одобрения, если выбранный сотрудник совпадает с сотрудником,
     * фактически оформляющим кредит, если банк имеет достаточную сумму для выдачи кредита и если выбранный
     * сотрудник может оформлять кредиты, то на платёжный счёт пользователя поступает запрошенная сумма, а
     * из банка списывается указанная в заявке сумма. В случае оформления возвращается true, иначе false.*/
    Boolean applyLoan(CreditAccount creditAcc, Employee employee);
}
