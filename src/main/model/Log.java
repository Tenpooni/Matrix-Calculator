package model;

import java.util.ArrayList;

public class Log {
    private ArrayList<String> logList;
    private final String empty = "No operations yet";

    public Log() {
        this.logList = new ArrayList<>();
    }

    public void entryArithmetic(int r1, int r2, String operation) {
        String line =  "R" + r1 + " " + operation + " " + "R" + r2;
        this.logList.add(line);
    }

    public void entryMultiplicative(int r1, float c) {
        String line =  "R" + r1 + " * " + c;
        this.logList.add(line);
    }

    public ArrayList<String> result() {
        checkEmptyLog();
        return this.logList;
    }

    public void checkEmptyLog() {
        if (this.logList.isEmpty()) {
            this.logList.add(empty);
        } else if (this.logList.size() > 1) {
            this.logList.remove(empty);
        }
    }

    public String getLogLine(int i) {
        return logList.get(i);
    }

    public ArrayList<String> getLogList() {
        return logList;
    }
}
