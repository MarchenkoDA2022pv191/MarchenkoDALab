package bank.entity;

import bank.entity.common.Account;

public class PaymentAccount extends Account {
   private Double sum;

    public PaymentAccount(Integer id, User user, Bank bank) {
        super(id,user,bank);
        this.setSum(0.0D);
    }

    @Override
    public String toString() {
        String str =  "Банк: " + bank.getName() +
                "\nПользователь: " + user.getName().toString() +
                "\nСумма денег: " + sum;
        return str;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
