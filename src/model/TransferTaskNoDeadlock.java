package model;

public class TransferTaskNoDeadlock implements Runnable {
    private final AccountNoDeadlock fromAccount;
    private final AccountNoDeadlock toAccount;
    private final double amount;

    public TransferTaskNoDeadlock(AccountNoDeadlock fromAccount, AccountNoDeadlock toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        fromAccount.transfer(toAccount, amount);
    }
}


