package model;

public class TransferTask implements Runnable {
    private final Account fromAccount;
    private final Account toAccount;
    private final double amount;

    public TransferTask(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        fromAccount.transfer(toAccount, amount);
    }
}