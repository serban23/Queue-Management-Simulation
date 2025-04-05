# Queue Management Simulation

## 📌 Project Overview

This Java application simulates a queue management system where `N` clients arrive and are assigned to `Q` queues based on different strategies. The simulation uses threads to handle client processing and logs each event to a file.

## 🛠️ Key Features

- Multithreading (each queue runs on its own thread)
- Random generation of clients
- Two dispatch strategies:
  - **Shortest Queue**: Assign to the queue with the fewest clients
  - **Minimum Waiting Time**: Assign to the queue with the lowest total waiting time
- GUI for simulation control
- Logging system to record events

## 🧩 Class Structure

- **Task**: Represents a client (`id`, `arrivalTime`, `serviceTime`, `waitingTime`)
- **Server**: Represents a queue, runs as a thread, serves clients from a `BlockingQueue`
- **Scheduler**: Manages queue list and dispatch strategy
- **SimulationManager**: Controls simulation flow, generates clients, assigns them, and logs events
- **Log**: Appends simulation logs to a file

