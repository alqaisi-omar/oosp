/**
 * Сервис для выполнения операций перевода между счетами.
 */
public class TransactionService {
    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }
}
