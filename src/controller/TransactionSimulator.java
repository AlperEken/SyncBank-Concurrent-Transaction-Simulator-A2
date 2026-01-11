package controller;


import model.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionSimulator {
    private final IBankAccount sharedAccount;
    private final List<Client> clients;
    private final List<Thread> threads;
    private final int numClients = 10;
    private final int durationTime = 5000;

    public TransactionSimulator(boolean threadSafeAccount) {
        if (threadSafeAccount) {
            this.sharedAccount = new BankAccount(1, 1000.0);
        } else {
            this.sharedAccount = new UnsafeBankAccount(1, 1000.0);
        }
        this.clients = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    public void runSimulation() {
        for (int i = 0; i < numClients; i++) {
            Client client = new Client(i + 1, sharedAccount);
            Thread thread = new Thread(client);
            clients.add(client);
            threads.add(thread);
        }

        System.out.println("Starting transaction simulation...");
        for (Thread t : threads) {
            t.start();
        }

        try {
            Thread.sleep(durationTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Client client : clients) {
            client.stop();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        double totalTransactions = 0;

        for (Client client : clients) {
            totalTransactions += client.getTotalTransactions();
        }

        double expectedBalance = 1000.0 + totalTransactions;
        double actualBalance;
        synchronized (sharedAccount) {
            actualBalance = sharedAccount.getBalance();
        }
        double difference = expectedBalance - actualBalance;

        System.out.println("\nSimulation results:");
        System.out.printf("Total for all clients: %.2f kr%n", totalTransactions);
        System.out.printf("Excepcted result: %.2f kr%n", expectedBalance);
        System.out.printf("Actual result: %.2f kr%n", actualBalance);
        System.out.printf("The difference: %.2f kr%n", difference);

        if (Math.abs(difference) < 0.01) {
            System.out.println("There was no race condition.");
        } else {
            System.out.println("There was race condition and the balance is incorrect.");
        }
    }
}


