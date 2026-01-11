package model;

public class AccountNoDeadlock {
    private final int clientId;
    private double balance;

    public AccountNoDeadlock(int clientId, double initialBalance) {
        this.clientId = clientId;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getClientId() {
        return clientId;
    }

    public void transfer(AccountNoDeadlock toAccount, double amount) {
        AccountNoDeadlock firstLock, secondLock;

        if (this.clientId < toAccount.clientId) {
            firstLock = this;
            secondLock = toAccount;
        } else {
            firstLock = toAccount;
            secondLock = this;
        }

        synchronized (firstLock) {
            System.out.println("[" + clientId + "] locked account " + firstLock.clientId);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (secondLock) {
                System.out.println("[" + clientId + "] locked account " + secondLock.clientId);
                if (balance >= amount) {
                    this.withdraw(amount);
                    toAccount.deposit(amount);
                    System.out.println("[" + clientId + "] transferred " + amount + " kr to " + toAccount.getClientId());
                }
            }
        }
    }
}