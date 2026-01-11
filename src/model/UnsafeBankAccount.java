package model;

public class UnsafeBankAccount implements IBankAccount{
    private double balance;
    private final int accountId;

    public UnsafeBankAccount(int accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        System.out.println("UnsafeBankAccount is being used!");
    }

    public void deposit(double amount) {
        double newBalance = balance + amount;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        balance = newBalance;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            double newBalance = balance - amount;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            balance = newBalance;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountId() {
        return accountId;
    }
}