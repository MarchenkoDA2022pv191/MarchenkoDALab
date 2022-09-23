package bank.service.impl;

import bank.entity.BankOffice;
import bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    /*Добавление суммы денег в офис, и, соответственно, добавление суммы денег в банк,
    которому принадлежит данный офис*/
    @Override
    public void addMoney(BankOffice office, Double sumMoney) {
        Double sumBank = office.getMoneyBank();
        Double sumOffice = office.getMoneyOffice();
        office.setMoneyBank(sumBank + sumMoney);
        office.setMoneyOffice(sumOffice + sumMoney);
    }

    /*Вычитание суммы денег из офиса, и, соответственно, вычитание суммы денег из банка, которому принадлежит
     * данный офис, с проверкой того, достаточно ли денег в офисе, чтобы их вычесть. Если не достаточно, то
     * возвращается false, иначе true*/
    @Override
    public Boolean subtractMoney(BankOffice office, Double sumMoney) {
        Double sumBank = office.getMoneyBank();
        Double sumOffice = office.getMoneyOffice();
        if (sumOffice < sumMoney)
            return Boolean.FALSE;
        office.setMoneyBank(sumBank - sumMoney);
        office.setMoneyOffice(sumOffice - sumMoney);
        return Boolean.TRUE;
    }

    /*Добавление нового банкомата в офис, и, соответственно, добавление нового банкомата в банк, которому
     * принадлежит данный офис, с проверкой того, можно ли добавить в этот офис новый банкомат.
     * Если нельзя достаточно, то возвращается false, иначе true*/
    @Override
    public Boolean addATM(BankOffice office) {
        if (!office.getMaySetATM())
            return Boolean.FALSE;
        office.setCountATMOffice(office.getCountATMOffice() + 1);
        office.setCountATMBank(office.getCountATMBank() + 1);
        return Boolean.TRUE;
    }

    /*Вычитание банкомата из офиса, и, соответственно, вычитание банкомата из банка, которому
     * принадлежит данный офис*/
    @Override
    public void subtractATM(BankOffice office) {
        office.setCountATMOffice(office.getCountATMOffice() - 1);
        office.setCountATMBank(office.getCountATMBank() - 1);
    }
}