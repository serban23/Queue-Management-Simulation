package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    String name;

    public Log(int noc, int nos, int minat, int maxat, int minpt, int maxpt, int st) {
        name = noc + ".txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(name));
            writer.write("n = " + noc + "\n");
            writer.write("q = " + nos + "\n");
            writer.write("simulation time: " + st + "\n");
            writer.write("arrival time: [" + minat + ", " + maxat + "]\n");
            writer.write("process time: [" + minpt + ", " + maxpt + "]\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void append(String s) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(name, true));
            writer.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
