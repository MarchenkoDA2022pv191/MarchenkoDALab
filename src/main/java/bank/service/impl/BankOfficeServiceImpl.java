package bank.service.impl;

import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.service.BankOfficeService;

import java.util.Objects;

public class BankOfficeServiceImpl implements BankOfficeService {

    @Override
    public void addMoney(BankOffice office, Double sumMoney) {
        Double sumBank = office.getBank().getMoney();
        Double sumOffice = office.getMoney();
        office.setMoney(sumOffice + sumMoney);
        office.getBank().setMoney(sumBank + sumMoney);
    }


    @Override
    public Boolean subtractMoney(BankOffice office, Double sumMoney) {
        Double sumBank = office.getBank().getMoney();
        Double sumOffice = office.getMoney();
        if (sumOffice < sumMoney)
            return Boolean.FALSE;
        office.setMoney(sumOffice + sumMoney);
        office.getBank().setMoney(sumBank + sumMoney);
        return Boolean.TRUE;
    }


    @Override
    public Boolean addATM(BankOffice office, BankATM bankATM) {
        if (!office.getMaySetATM() || bankATM.getBank() != null)
            return Boolean.FALSE;
        office.setCountATM(office.getCountATM() + 1);
        office.getBank().setCountATM(office.getBank().getCountATM() + 1);

        bankATM.setBankOffice(office);
        bankATM.setBank(office.getBank());

        return Boolean.TRUE;
    }


    @Override
    public Boolean subtractATM(BankOffice office, BankATM bankATM) {
        if (!Objects.equals(bankATM.getBankOffice(),office))
            return Boolean.FALSE;
        office.setCountATM(office.getCountATM() - 1);
        office.getBank().setCountATM(office.getBank().getCountATM() - 1);

        bankATM.setBankOffice(null);
        return true;
    }
}