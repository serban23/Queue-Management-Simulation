package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger();
    }

    public void addTask(Task newTask) {
        if (waitingPeriod.get() != 0)
            newTask.setWaitingTime(waitingPeriod.get() - 1);
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {
        while (true) {
            Task client = tasks.peek();
            if (client != null) {
                if (client.getServiceTime() <= 1) {
                    tasks.poll();
                    client.setServiceTime(0);
                } else {
                    client.decrementServiceTime();
                }
            }
            if (waitingPeriod.intValue() > 0)
                waitingPeriod.decrementAndGet();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Task[] getTasks() {
        Task[] res = new Task[tasks.size()];
        int n = 0;
        for (Task t : tasks)
            res[n++] = t;
        return res;
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public String printQueue(int i, String str) {
        System.out.println("Q" + i + ": " + tasks);
        str += "Q" + i + ": " + tasks + "\n";
        return str;
    }

}
