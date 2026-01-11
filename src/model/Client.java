package model;

import java.util.Random;

public class Client implements Runnable {
    private final int clientId;
    private final IBankAccount sharedAccount;
    private final Random random;
    private boolean running = true;
    private double totalTransactions = 0;

    public Client(int clientId, IBankAccount sharedAccount) {
        this.clientId = clientId;
        this.sharedAccount = sharedAccount;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (running) {
            double amount = 1 + (99 * random.nextDouble());

            boolean deposit = random.nextBoolean();

            if (deposit) {
                sharedAccount.deposit(amount);
                synchronized (this) {
                    totalTransactions += amount;
                }
            } else {
                boolean success = sharedAccount.withdraw(amount);
                if (success) {
                    synchronized (this) {
                        totalTransactions -= amount;
                    }
                }
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Client " + clientId + " is connecting. ");

    }

    public void stop() {
        running = false;
    }

    public int getClientId() {
        return clientId;
    }

    public synchronized double getTotalTransactions() {
        return totalTransactions;
    }

}