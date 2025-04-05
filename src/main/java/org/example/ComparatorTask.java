package org.example;

import java.util.Comparator;
public class ComparatorTask implements Comparator<Task>{

    @Override
    public int compare(Task t1,Task t2) {
        return Integer.compare(t1.getArrivalTime(),t2.getArrivalTime());
    }
}
