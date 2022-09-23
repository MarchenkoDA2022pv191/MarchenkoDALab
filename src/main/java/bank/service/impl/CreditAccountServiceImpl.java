package bank.service.impl;

import bank.entity.Employee;
import bank.entity.CreditAccount;
import bank.service.CreditAccountService;

public class CreditAccountServiceImpl implements CreditAccountService {
    /*Одобрение заявки на кредит. В случае одобрения, если выбранный сотрудник совпадает с сотрудником,
    * фактически оформляющим кредит, если банк имеет достаточную сумму для выдачи кредита и если выбранный
    * сотрудник может оформлять кредиты, то на платёжный счёт пользователя поступает запрошенная сумма, а
    * из банка списывается указанная в заявке сумма. В случае оформления возвращается true, иначе false.*/
    @Override
    public Boolean applyLoan(CreditAccount creditAcc, Employee employee) {
        if ((creditAcc.getAmountCreditAcc() > creditAcc.getMoneyBank()) || (!employee.getMayApplyLoan()))
            return Boolean.FALSE;
        creditAcc.setEmployeeId(employee.getIdEmployee());
        creditAcc.setAmountPayAcc(creditAcc.getAmountPayAcc() + creditAcc.getAmountCreditAcc());
        creditAcc.setMoneyBank(creditAcc.getMoneyBank() - creditAcc.getAmountCreditAcc());
        return Boolean.TRUE;
    }
}
