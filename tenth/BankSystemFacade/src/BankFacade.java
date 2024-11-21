/**
 * Класс-фасад, предоставляющий упрощенный интерфейс для операций с банковским счетом.
 */
public class BankFacade {
    private final BankAccount account;
    private final TransactionService transactionService;

    public BankFacade(String accountNumber, double initialBalance) {
        this.account = new BankAccount(accountNumber, initialBalance);
        this.transactionService = new TransactionService();
    }

    public boolean deposit(double amount) {
        return account.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public double checkBalance() {
        return account.getBalance();
    }

    public boolean transfer(String toAccountNumber, double amount) {
        BankAccount toAccount = new BankAccount(toAccountNumber, 0);
        return transactionService.transfer(account, toAccount, amount);
    }
}
