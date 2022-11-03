package bank.service.impl;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.service.BankService;

import java.util.ArrayList;
import java.util.Objects;

public class BankServiceImpl implements BankService {
    @Override
    public void addMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoney();
        bank.setMoney(sum + sumMoney);
    }

    @Override
    public Boolean subtractMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoney();
        if (sumMoney > sum)
            return Boolean.FALSE;
        bank.setMoney(sum - sumMoney);
        return Boolean.TRUE;
    }

    @Override
    public Boolean addBankATM (Bank bank, BankATM bankATM){
        if (bankATM.getBank() != null || Objects.equals(bankATM.getBank(),bank))
            return false;

        if (bank.getBankATMS() == null) {
            ArrayList<BankATM> array =new ArrayList<BankATM>();
            array.add(bankATM);
            bank.setBankATMS(array);
        }
        else{
            ArrayList<BankATM> array = bank.getBankATMS();
            array.add(bankATM);
            bank.setBankATMS(array);
        }

        bankATM.setBank(bank);
        return true;
    }

    @Override
    public Boolean deleteBankATM(Bank bank, BankATM bankATM){
        if (!Objects.equals(bankATM.getBank(),bank))
            return false;

        BankOfficeServiceImpl bankOfficeService =new BankOfficeServiceImpl();

        if (bank.getBankOffices() != null){
            ArrayList<BankOffice> bankOffices =bank.getBankOffices();
            bankOfficeService.deleteATM(bankOffices.get(bankOffices.indexOf(bankATM.getBankOffice())),bankATM);
            bank.setBankOffices(bankOffices);
        }


        ArrayList<BankATM> bankATMS = bank.getBankATMS();
        bankATMS.remove(bankATM);
        if (bankATMS.size() != 0)
            bank.setBankATMS(bankATMS);
        else
            bank.setBankATMS(null);
        return true;
    }

    @Override
    public Boolean addOffice(Bank bank, BankOffice bankOffice){
        if (bankOffice.getBank() != null || Objects.equals(bankOffice.getBank(),bank))
            return false;

        if (bank.getBankOffices() == null) {
            ArrayList<BankOffice> array =new ArrayList<>();
            array.add(bankOffice);
            bank.setBankOffices(array);
        }
        else{
            ArrayList<BankOffice> array = bank.getBankOffices();
            array.add(bankOffice);
            bank.setBankOffices(array);
        }
        return true;
    }

    @Override
    public Boolean deleteOffice(Bank bank, BankOffice bankOffice){
        if (!Objects.equals(bankOffice.getBank(),bank))
            return false;

        ArrayList<BankATM> bankATMS = bankOffice.getBankATMS();

        return true;
    }
}
