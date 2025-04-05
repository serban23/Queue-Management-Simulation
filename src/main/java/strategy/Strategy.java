package strategy;

import org.example.Server;
import org.example.Task;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);
}

