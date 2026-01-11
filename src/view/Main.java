package view;

import controller.DeadlockFreeSimulator;
import controller.DeadlockSimulator;
import controller.TransactionSimulator;

public class Main {
    public static void main(String[] args) {

        /*for (int i = 1; i <= 100; i++) {
            System.out.println("\n Round #" + i);
            *Den valda simulatorn*
        }*/

        // true = trådsäker, false = ej trådsäker - för race condition
        TransactionSimulator simulator = new TransactionSimulator(false);
        simulator.runSimulation();

        // För att visa Deadlock
        //new DeadlockSimulator().runSimulation();

        // För att visa Deadlock-free
        //new DeadlockFreeSimulator().runSimulation();


    }
}