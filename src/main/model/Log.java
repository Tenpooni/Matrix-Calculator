package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents record of previous calculator actions
public class Log implements Writable {
    private ArrayList<String> logList;                //tracks one calculator action per line
    private final String empty = "No operations yet"; //default message

    //EFFECTS: Initializes Log
    public Log() {
        this.logList = new ArrayList<>();
    }

    public void clearLog() {
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

    //MODIFIES: this
    //EFFECTS: Adds line for calculator history involving remove/added vectors
    public void entryVectors(int r1, String operation) {
        String line =  operation + " row vector at R" + (r1 + 1);
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

    public void setLogLine(int i, String str) {
        this.logList.add(i, str);
    }

    //EFFECTS: returns log
    public ArrayList<String> getLogList() {
        return logList;
    }

    @Override
    public JSONObject toJson() {
        checkEmptyLog();
        JSONObject json = new JSONObject();
        json.put("History", logToJson());
        return json;
    }

    public JSONArray logToJson() {
        JSONArray json = new JSONArray();
        for (String line : logList) {
            json.put(line);
        }
        return json;
    }

}
