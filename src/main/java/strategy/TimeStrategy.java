package strategy;

import org.example.Server;
import org.example.Task;
import strategy.Strategy;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        Server s = servers.get(0);
        for (Server server : servers)
            if (server.getWaitingPeriod() < s.getWaitingPeriod())
                s = server;
        s.addTask(t);
    }
}
