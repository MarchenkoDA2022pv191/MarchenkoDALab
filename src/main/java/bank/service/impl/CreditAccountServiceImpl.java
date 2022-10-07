package bank.service.impl;

import bank.entity.Employee;
import bank.entity.CreditAccount;
import bank.service.CreditAccountService;

public class CreditAccountServiceImpl implements CreditAccountService {

    @Override
    public Boolean applyLoan(CreditAccount creditAcc, Employee employee ) {
        if ((creditAcc.getAmount() > creditAcc.getBank().getMoney()) || (!employee.getCanLend()))
            return Boolean.FALSE;
        creditAcc.setEmployee(employee);
        creditAcc.getBank().setMoney(creditAcc.getBank().getMoney() - creditAcc.getAmount());
        return Boolean.TRUE;
    }
}
