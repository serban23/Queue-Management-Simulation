package org.example;

import strategy.ShortestQueueStrategy;
import strategy.Strategy;
import strategy.TimeStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
    private List<Server> servers=new ArrayList<>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    public enum SelectionPolicy {
        SHORTEST_QUEUE, SHORTEST_TIME;
    }

    public Scheduler(int maxNoServers, int maxTasksPerServer,int policy){
        this.maxNoServers=maxNoServers;
        this.maxTasksPerServer=maxTasksPerServer;
        if(policy==0)
            changeStrategy(SelectionPolicy.SHORTEST_QUEUE);
        else
            changeStrategy(SelectionPolicy.SHORTEST_TIME);
        ExecutorService executorService = Executors.newFixedThreadPool(maxNoServers);
        for(int i=0;i<maxNoServers;i++)
        {
            Server s=new Server();
            servers.add(s);
            executorService.execute(s);
        }
        executorService.shutdown();
    }

    public void changeStrategy(SelectionPolicy policy){
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public void dispatchTask(Task t){
        for(Server s:servers)
            if(s.getTasksSize()<maxTasksPerServer)
            {
                strategy.addTask(servers,t);
                return;
            }
    }

    public List<Server> getServers(){
        return servers;
    }

    public String printQueues() {
        int i=0;
        String str = "";
        for(Server server : servers)
            str = server.printQueue(++i, str);
        return str;
    }

}
