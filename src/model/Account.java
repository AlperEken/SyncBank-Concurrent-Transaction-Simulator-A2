package model;

public class Account {
    private final int clientId;
    private double balance;

    public Account(int clientId, double initialBalance) {
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

    public void transfer(Account toAccount, double amount) {
        synchronized (this) {
            System.out.println("[" + clientId + "] locked account " + clientId + ", waiting on " + toAccount.getClientId());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (toAccount) {
                System.out.println("[" + clientId + "] also locked account " + toAccount.getClientId());

                if (balance >= amount) {
                    this.withdraw(amount);
                    toAccount.deposit(amount);
                    System.out.println("[" + clientId + "] Transferred " + amount + " kr to " + toAccount.getClientId());
                }
            }
        }
    }
}


