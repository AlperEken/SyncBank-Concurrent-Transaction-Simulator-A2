package controller;

import model.Account;
import model.TransferTask;

public class DeadlockSimulator {
    public void runSimulation() {
        Account accountA = new Account(1, 1000.0);
        Account accountB = new Account(2, 1000.0);

        Thread thread1 = new Thread(new TransferTask(accountA, accountB, 100));
        Thread thread2 = new Thread(new TransferTask(accountB, accountA, 200));

        System.out.println("Starting simulation with deadlock\n");

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n ====If you see this, a deadlock has probably occured.====");
    }
}


