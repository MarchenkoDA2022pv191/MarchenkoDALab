package bank.exceptions;

public class BankAtmException extends Exception {
    public BankAtmException() {
        super("В банкомате недостаточно средств, необходимых клиенту");
    }
}
