package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimulationFrame extends JFrame{
    private JTextField numOfClientsField;
    private JTextField numOfQueuesField;
    private JTextField simulationTimeField;
    private JTextField minArrivalTimeField;
    private JTextField maxArrivalTimeField;
    private JTextField minServiceTimeField;
    private JTextField maxServiceTimeField;
    private JComboBox<String> strategyComboBox;
    private JButton runButton;
    private JTextArea queuesTextArea;

    private Log log;
    public SimulationFrame() {
        setTitle("Simulation Frame");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        addComponents();
        setVisible(true);
    }

    private void initComponents() {
        numOfClientsField = new JTextField(4);
        numOfQueuesField = new JTextField(4);
        simulationTimeField = new JTextField(4);
        minArrivalTimeField = new JTextField(4);
        maxArrivalTimeField = new JTextField(4);
        minServiceTimeField = new JTextField(4);
        maxServiceTimeField = new JTextField(4);
        strategyComboBox = new JComboBox<>(new String[]{"Shortest Queue", "Shortest Time"});
        runButton = new JButton("Run");
        queuesTextArea = new JTextArea(10, 30);
        queuesTextArea.setEditable(false);
    }

    private void addComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Simulation Time:"));
        inputPanel.add(simulationTimeField);
        inputPanel.add(new JLabel("Number Of Clients:"));
        inputPanel.add(numOfClientsField);
        inputPanel.add(new JLabel("Number Of Queues:"));
        inputPanel.add(numOfQueuesField);
        inputPanel.add(new JLabel("Min Arrival Time:"));
        inputPanel.add(minArrivalTimeField);
        inputPanel.add(new JLabel("Max Arrival Time:"));
        inputPanel.add(maxArrivalTimeField);
        inputPanel.add(new JLabel("Min Service Time:"));
        inputPanel.add(minServiceTimeField);
        inputPanel.add(new JLabel("Max Service Time:"));
        inputPanel.add(maxServiceTimeField);
        inputPanel.add(new JLabel("Strategy:"));
        inputPanel.add(strategyComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(runButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(queuesTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runSimulation();
            }
        });
    }

    private void runSimulation() {

        int simulationTime = Integer.parseInt(simulationTimeField.getText());
        int numOfClients = Integer.parseInt(numOfClientsField.getText());
        int numOfQueues = Integer.parseInt(numOfQueuesField.getText());
        int minArrivalTime = Integer.parseInt(minArrivalTimeField.getText());
        int maxArrivalTime = Integer.parseInt(maxArrivalTimeField.getText());
        int minServiceTime = Integer.parseInt(minServiceTimeField.getText());
        int maxServiceTime = Integer.parseInt(maxServiceTimeField.getText());
        String strategy = (String) strategyComboBox.getSelectedItem();

        int s;
        if (strategy.equals("Shortest Queue"))
            s=0;
        else
            s=1;
        SimulationManager simulationManager = new SimulationManager(
                simulationTime, minArrivalTime,maxArrivalTime,minServiceTime, maxServiceTime,
                numOfClients,  numOfQueues, s);
        Thread t=new Thread(simulationManager);
        t.start();
    }

    public void write(){

    }
}
