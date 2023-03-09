package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Matrix implements Writable {

    private int columnCount;
    private int rowCount;
    Log log = new Log();
    Column column = new Column(rowCount);


    public int getMatrixRowSize() {
        return this.column.getRowSize();
    }

    public int getMatrixColumnSize() {
        return this.column.getColumnSize();
    }

    public void setColumnCount(int i) {
        this.columnCount = i;
    }

    public void setRowCount(int i) {
        this.rowCount = i;
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public int getRowCount() {
        return this.rowCount;
    }


    public void setColumn(int i, Row row) {
        this.column.setColumn(i, row);
    }

    public void setMatrix(Column col) {
        this.column = col;
    }

    public void newMatrix(int row) {
        this.column = new Column(row);
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Log getLog() {
        return this.log;
    }

    public void insertMatrixRow(int i, Row rowToInsert) {
        this.column.insertRow(i, rowToInsert);
    }

    public void removeMatrixRow(int i) {
        this.column.removeRow(i);
    }

    public ArrayList<String> getResult() {
        return this.log.result();
    }

    public ArrayList<String> printMatrix() {
        return this.column.columnPrint();
    }

    public void clearHistory() {
        this.log.clearLog();
    }

    public void swapRow(int r1, int r2) {
        column.swapRow(r1, r2);
        this.log.entryArithmetic(r1, r2, "swap");
    }

    public void addRow(int r1, int r2) {
        column.addRow(r1, r2);
        this.log.entryArithmetic(r1, r2, "+");
    }

    public void subtractRow(int r1, int r2) {
        column.subtractRow(r1, r2);
        this.log.entryArithmetic(r1, r2, "-");
    }

    public void multiplyRow(int r1, float c) {
        column.multiplyRow(r1, c);
        this.log.entryMultiplicative(r1, c);
    }

    public void enterVector(int r1, String operation) {
        this.log.entryVectors(r1, operation);
    }

    public JSONArray matrixToJson() {
        return this.column.columnToJson();
    }

    public JSONArray logToJson() {
        return this.log.logToJson();
    }

    //EFFECTS: write both matrix and history log as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Matrix", column.columnToJson());
        json.put("History", log.logToJson());
        return json;
    }

}
