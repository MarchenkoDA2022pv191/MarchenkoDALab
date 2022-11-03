package bank.service.impl;

import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.service.BankOfficeService;

import java.util.ArrayList;
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
        if (!office.getMaySetATM() || bankATM.getBankOffice() != null
                || Objects.equals(bankATM.getBank(), office.getBank()) || bankATM.getBank() == null
                || office.getBank() == null)
            return false;

        if (office.getBankATMS() == null) {
            ArrayList<BankATM> array = new ArrayList<>();
            array.add(bankATM);
            office.setBankATMS(array);
        }
        else{
            ArrayList<BankATM> array = office.getBankATMS();
            array.add(bankATM);
            office.setBankATMS(array);
        }
        bankATM.setBankOffice(office);
        return true;
    }

    @Override
    public Boolean deleteATM(BankOffice office, BankATM bankATM) {
        if (!Objects.equals(bankATM.getBankOffice(),office))
            return false;
        ArrayList<BankATM> array = office.getBankATMS();
        array.remove(bankATM);
        if (array.size() == 0)
            office.setBankATMS(null);
        else
            office.setBankATMS(array);

        return true;
    }
}