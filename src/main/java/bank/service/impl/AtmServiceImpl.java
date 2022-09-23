package bank.service.impl;

import bank.entity.BankATM;
import bank.entity.helpClass.StatusATM;
import bank.service.AtmService;

import java.util.Objects;

public class AtmServiceImpl implements AtmService {
    /*Добавление суммы денег в банкомат, а, соответственно, добавление суммы денег в офис банка
    * и в банк, которому принадлежит данный банкомат, с учётом того, работает ли банкомат. Если он
    * работает, то деньги вносятся и возвращается true, иначе false*/
    @Override
    public Boolean addMoney(BankATM bankATM, Double sumMoney) {
        if (!Objects.equals(bankATM.getStatus(), StatusATM.Work)) {
            return Boolean.FALSE;
        }
        bankATM.setMoneyATM(bankATM.getMoneyATM() + sumMoney);
        bankATM.setMoneyOffice(bankATM.getMoneyOffice() + sumMoney);
        bankATM.setMoneyBank(bankATM.getMoneyBank() + sumMoney);
        return Boolean.TRUE;
    }

    /*Вычитание суммы денег из банкомата, и, соответственно, вычитание суммы денег из офиса банка и банка,
    * которому принадлежит данный банкомат, с проверкой того, достаточно ли денег в банкомате, чтобы их вычесть.
    * Если не достаточно, то возвращается false, иначе true. И с учётом того, работает ли банкомат и есть ли в
    * нём деньги. Если он работает и в нём есть деньги, то деньги вычитаются и возвращается true, иначе false*/
    @Override
    public Boolean subtractMoney(BankATM bankATM, Double sumMoney) {
        if ((Objects.equals(bankATM.getStatus(), StatusATM.NotWork)) || (Objects.equals(bankATM.getStatus(),
                StatusATM.NoMoney)) || (bankATM.getMoneyATM() < sumMoney))
            return Boolean.FALSE;
        if (Objects.equals(bankATM.getMoneyATM(), sumMoney))
            bankATM.setStatus(StatusATM.NoMoney);
        bankATM.setMoneyATM(bankATM.getMoneyATM() - sumMoney);
        bankATM.setMoneyOffice(bankATM.getMoneyOffice() - sumMoney);
        bankATM.setMoneyBank(bankATM.getMoneyBank() - sumMoney);
        return Boolean.TRUE;
    }
}
