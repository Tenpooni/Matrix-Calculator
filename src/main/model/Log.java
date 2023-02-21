package model;

import java.util.ArrayList;

public class Log {
    private ArrayList<String> logList;

    public Log() {
        logList = new ArrayList<>();
    }

    public void entryArithmetic(int r1, int r2, String operation) {
        String line =  "R" + r1 + " " + operation + " " + "R" + r2;
        logList.add(line);
    }

    public void entryMultiplicative(int r1, float c) {
        String line =  "R" + r1 + " * " + c;
        logList.add(line);
    }

    public ArrayList<String> result() {
        if (this.logList.isEmpty()) {
            logList.add("No operations yet");
        }
        return logList;
    }
}
