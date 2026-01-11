# SyncBank: Concurrent Transaction Simulator

## Key Features

SyncBank: Concurrent Transaction Simulator is a specialized diagnostic and educational tool designed to model the complexities of parallel financial processing within high-concurrency environments. The system provides a multi-scenario simulation engine that allows developers to observe and analyze the catastrophic effects of race conditions and circular wait dependencies in real-time. Users can toggle between various operational states—ranging from inherently unsafe models that expose data corruption to highly optimized, deadlock-free transaction streams. By simulating thousands of rapid transfers between multiple accounts, the platform effectively demonstrates how modern banking systems maintain transactional integrity and resolve synchronization conflicts without compromising system availability.

## Technical Implementation

The engine is architected in Java, leveraging the platform’s low-level multi-threading primitives, including `Runnable` tasks and managed `Threads`, to create a heavy-load concurrent environment. The core technical achievement lies in the evolution of the synchronization strategy: starting from an unshielded model, the system introduces `synchronized` blocks and explicit locks to protect shared account resources. To resolve the complex issue of deadlocks, the simulator implements a resource-ordering algorithm—assigning unique identifiers to accounts to ensure a deterministic locking sequence, thereby breaking the circular wait condition required for a deadlock to occur.

## Challenges & Reflection

The primary challenge of this project involved identifying and debugging non-deterministic race conditions that only appeared under extreme thread saturation. Moving from an `UnsafeBankAccount` model to a thread-safe environment required a rigorous analysis of critical sections to ensure that atomicity was maintained without introducing significant performance bottlenecks. Implementing deadlock resolution strategies proved to be a pivotal learning moment, as it highlighted that simply adding locks can often lead to new systemic failures if not governed by strict hierarchical protocols. This laboratory-style approach to development emphasized the necessity of defensive programming and helped refine a sophisticated intuition for managing shared state in distributed architectures.

## Getting Started

To run the SyncBank simulations on your local system, ensure you have the JDK installed and execute the following commands in your terminal:

```bash
# Navigate to the source root directory
cd A2-4c5edb3b47da26cedbc99b8724eff5cc7d508064/src

# Compile the multi-threaded simulator modules
javac controller/*.java model/*.java view/*.java

# Launch the main simulation console
java view.Main
```
*Author: Alper Eken Course: Concurrent Programming Semester: Spring 2025*
