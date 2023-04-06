package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a column of arbitrary size
public class Column implements Writable {
    private ArrayList<Row> column;   //stores rows in the column
    private int columnSize;            //size of column


    //MODIFIES: this
    //EFFECTS: creates column with memory to store arbitrary number of rows
    public Column(int rowNum) {
        this.column = new ArrayList<>(rowNum);
        this.columnSize = rowNum;
    }

    //EFFECTS: returns every row in column as a string representation
    public ArrayList<String> columnPrint() {
        String line = "";
        ArrayList<String> lineToPrint = new ArrayList<>();

        for (Row row : this.column) {
            line = row.rowPrint();
            lineToPrint.add(line);
        }
        return lineToPrint;
    }

    //REQUIRES: 0 < r1, r2 < rowCount
    //MODIFIES: this
    //EFFECTS: Exchange rows based on given column locations
    public void swapRow(int r1, int r2) {
        Row temp = this.column.get(r1);
        this.column.set(r1,this.column.get(r2));
        this.column.set(r2,temp);
        EventLog.getInstance().logEvent(new Event("Swap row " + (r1 + 1) + " and row " + (r2 + 1)));
    }

    //REQUIRES: 0 < r1, r2 < rowCount
    //MODIFIES: this
    //EFFECTS: Adds row values, replaces second row given
    public void addRow(int r1, int r2) {
        Row toAdd = this.column.get(r1);
        Row toReplace = this.column.get(r2);
        int rowLength = toReplace.getRowSize();

        for (int i = 0; i < rowLength; i++) {
            float c = toAdd.getValue(i);
            toReplace.addition(i, c);
        }

        EventLog.getInstance().logEvent(new Event("Add row " + (r1 + 1) + " and row " + (r2 + 1)));
    }

    //REQUIRES: 0 < r1, r2 < rowCount
    //MODIFIES: this
    //EFFECTS: subtracts row values, replaces second row given
    public void subtractRow(int r1, int r2) {
        Row toSubtract = this.column.get(r1);
        Row toReplace = this.column.get(r2);
        int rowLength = toReplace.getRowSize();

        for (int i = 0; i < rowLength; i++) {
            float c = toSubtract.getValue(i);
            toReplace.subtract(i, c);
        }

        EventLog.getInstance().logEvent(new Event("Subtract row " + (r1 + 1) + " and row " + (r2 + 1)));
    }

    //REQUIRES: 0 < r1, r2 < rowCount
    //MODIFIES: this
    //EFFECTS: multiplies every value in row with given constant
    public void multiplyRow(int r1, float c) {
        Row toMultiply = this.column.get(r1);
        toMultiply.multiply(c);
        EventLog.getInstance().logEvent(new Event("Multiply row " + (r1 + 1) + " with constant " + c));
    }

    //MODIFIES: this
    //EFFECTS: Remove existing row vector at index
    public void removeRow(int r) {
        this.column.remove(r);
        EventLog.getInstance().logEvent(new Event("Removed row at position " + (r + 1)));
    }

    //MODIFIES: this
    //EFFECTS: Insert Row vector at index
    public void insertRow(int index, Row row) {
        this.column.add(index, row);
        EventLog.getInstance().logEvent(new Event("Insert row at position " + (index + 1)));
    }


    //EFFECTS: return columnSize is row count
    public int getColumnSize() {
        return this.column.size();
    }

    //EFFECTS: return rowSize is column count
    public int getRowSize() {
        return this.column.get(0).getRowSize();
    }

    //MODIFIES: this
    //EFFECTS: Adds row to column
    public void setColumn(int i, Row r) {
        this.column.add(i, r);
    }

    //REQUIRES: 0 < i < rowCount
    //EFFECTS: Returns row item
    public Row getColumn(int i) {
        return this.column.get(i);
    }



    //EFFECTS: Writes each row item to JsonArray
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Matrix", columnToJson());
        return json;
    }

    public JSONArray columnToJson() {
        JSONArray json = new JSONArray();
        for (Row row : column) {
            json.put(row.rowToJson());
        }
        return json;
    }
}
