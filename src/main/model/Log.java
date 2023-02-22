package model;

import java.util.ArrayList;

//Represents record of previous calculator actions
public class Log {
    private ArrayList<String> logList;                //tracks one calculator action per line
    private final String empty = "No operations yet"; //default message

    //EFFECTS: Initializes Log
    public Log() {
        this.logList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Adds line for calculator history involving swap, addition, subtraction
    public void entryArithmetic(int r1, int r2, String operation) {
        String line =  "R" + r1 + " " + operation + " " + "R" + r2;
        this.logList.add(line);
    }

    //MODIFIES: this
    //EFFECTS: Adds line for calculator history involving multiplication
    public void entryMultiplicative(int r1, float c) {
        String line =  "R" + r1 + " * " + c;
        this.logList.add(line);
    }

    //EFFECTS: returns calculator history
    public ArrayList<String> result() {
        checkEmptyLog();
        return this.logList;
    }

    //MODIFIES: this
    //EFFECTS: Present default text when log is empty,
    // remove default text when log has entries
    public void checkEmptyLog() {
        if (this.logList.isEmpty()) {
            this.logList.add(empty);
        } else if (this.logList.size() > 1) {
            this.logList.remove(empty);
        }
    }

    //EFFECTS: returns string content of individual line
    public String getLogLine(int i) {
        return logList.get(i);
    }

    //EFFECTS: returns log
    public ArrayList<String> getLogList() {
        return logList;
    }
}
