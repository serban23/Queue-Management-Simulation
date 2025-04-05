package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{

    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public int minArrivalTime;
    public int maxArrivalTime;
    private double averageWaitingTime=0;
    private int clientsDone=0;
    public Scheduler.SelectionPolicy selectionPolicy;
    private List<Task> generatedTasks=new ArrayList<>();
    private  Scheduler scheduler;

    public SimulationManager(int a, int b, int c, int d, int e, int f, int g, int p){
        this.timeLimit = a;
        this.minArrivalTime = b;
        this.maxArrivalTime = c;
        this.minProcessingTime = d;
        this.maxProcessingTime = e;
        this.numberOfClients = f;
        this.numberOfServers = g;
        scheduler = new Scheduler(numberOfServers, numberOfClients,p);
        generateRandomTasks();
    }

    private void generateRandomTasks(){

        Random r=new Random();
        for(int i=0;i<numberOfClients;i++) {
            int arrivalTime = r.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int serviceTime = r.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;

            Task t= new Task(i+1,arrivalTime, serviceTime);
            generatedTasks.add(t);
            averageWaitingTime+=serviceTime;
        }
        Collections.sort(generatedTasks,new ComparatorTask());
    }

    @Override
    public void run() {
        int currentTime = 0;

        Log log = new Log(numberOfClients, numberOfServers, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime, timeLimit);
        while (currentTime < timeLimit) {

            System.out.println("Time " + currentTime);
            log.append(currentTime + "\n");

            ArrayList<Task> tasksAdded = new ArrayList<>();
            for (Task task : generatedTasks)
                if (task.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(task);
                    tasksAdded.add(task);
                }
            for (Task task : tasksAdded)
                generatedTasks.remove(task);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
            }

            System.out.print("Waiting list: ");
            log.append("Waiting list: ");

            for (Task task : generatedTasks) {
                System.out.print(task + ", ");
                log.append(task + ", ");
            }

            log.append("\n");
            System.out.println();
            log.append(scheduler.printQueues());
            currentTime++;
        }
    }

    public static void main(String[] args){
        SimulationFrame sim=new SimulationFrame();
    }


}
