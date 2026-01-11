package controller;

import model.AccountNoDeadlock;
import model.TransferTaskNoDeadlock;

public class DeadlockFreeSimulator {
    public void runSimulation() {
        AccountNoDeadlock accountA = new AccountNoDeadlock(1, 1000.0);
        AccountNoDeadlock accountB = new AccountNoDeadlock(2, 1000.0);

        Thread thread1 = new Thread(new TransferTaskNoDeadlock(accountA, accountB, 100));
        Thread thread2 = new Thread(new TransferTaskNoDeadlock(accountB, accountA, 200));

        System.out.println("Starting the deadlock-free simulation\n");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nTransactions have succeeded without deadlock\n!");
        System.out.printf("Balance A: %.2f kr%n", accountA.getBalance());
        System.out.printf("Balance B: %.2f kr%n", accountB.getBalance());
    }
}