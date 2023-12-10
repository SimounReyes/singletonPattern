package singletonPattern;

import java.util.LinkedList;
import java.util.List;

public class QueuingSystem {

    private static QueuingSystem instance;

    private String[] nomOfHelpDesks = new String[3];
    private boolean isOccupied;

    private List<String> queue = new LinkedList<>();
    private int nextQNumber = 1;

    private QueuingSystem() {
        initHelpDesks();
    }

    public static synchronized QueuingSystem getInstance() {
        if (instance == null)
            instance = new QueuingSystem();
        return instance;
    }

    private void initHelpDesks() {
        for (int i = 0; i < nomOfHelpDesks.length; i++) {
            nomOfHelpDesks[i] = "";
        }
    }

    public synchronized int getQNumber() {
        queue.add(String.valueOf(nextQNumber));
        return nextQNumber++;
    }

    public synchronized void assign() {
        if (isOccupied)
            return;

        List<Integer> unoccupied = checkAvailability();

        for (Integer i : unoccupied) {
            isOccupied = goToDesk(i);
        }
    }

    public synchronized boolean goToDesk(int deskPosition) {
        if (!queue.isEmpty()) {
            nomOfHelpDesks[deskPosition] = queue.remove(0);
            return true;
        }
        return false;
    }

    public synchronized List<Integer> checkAvailability() {
        List<Integer> availables = new LinkedList<>();
        for (int i = 0; i < nomOfHelpDesks.length; i++) {
            if (nomOfHelpDesks[i].equals("")) {
                availables.add(i);
            }
        }
        return availables;
    }

    public synchronized boolean resetingQNumber(int queueNumber) {
        String numStr = String.valueOf(queueNumber);

        for (int i = 0; i < nomOfHelpDesks.length; i++) {
            if (nomOfHelpDesks[i].equals(numStr)) {
                nomOfHelpDesks[i] = "";
                isOccupied = false;
                return true;
            }
        }

        if (!queue.isEmpty()) {
            queue.remove(numStr);
            return true;
        }
        return false;
    }

    public synchronized void currentStatus() {
        System.out.println("Centralized Queuing System for Pag-ibig Office\n" +
                "-------------------------------------------------------------------\n\nON QUEUE:");

        System.out.println("\n1st Help Desk: " + nomOfHelpDesks[0] +
                "\n2nd Help Desk: " + (nomOfHelpDesks[1]+
                "\n3rd Help Desk: " + nomOfHelpDesks[2]+
                "\n\nOTHER QUEUES: " +String.join(" ", queue) +"\n" ));
    }
}
