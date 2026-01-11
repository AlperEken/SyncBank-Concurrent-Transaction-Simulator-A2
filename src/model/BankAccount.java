package model;

public class BankAccount implements IBankAccount {
    private double balance;
    private final int accountId;
    private final Object lock = new Object();

    public BankAccount(int accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        System.out.println("BankAccount (synchronized) is used");
    }

    public void deposit(double amount) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        synchronized (lock) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }

    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

    public int getAccountId() {
        return accountId;
    }
}
