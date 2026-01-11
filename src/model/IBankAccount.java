package model;

public interface IBankAccount {
    void deposit(double amount);
    boolean withdraw(double amount);
    double getBalance();
}


