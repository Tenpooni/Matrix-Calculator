package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a column of arbitrary size
public class Column implements Writable {
    private ArrayList<Row> column;   //stores rows in the column
    private int rowCount;            //size of column


    //MODIFIES: this
    //EFFECTS: creates column with memory to store arbitrary number of rows
    public Column(int rowNum) {
        this.column = new ArrayList<>(rowNum);
        this.rowCount = rowNum;
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
    }

    //REQUIRES: 0 < r1, r2 < rowCount
    //MODIFIES: this
    //EFFECTS: Adds row values, replaces second row given
    public void addRow(int r1, int r2) {
        Row toAdd = this.column.get(r1);
        Row toReplace = this.column.get(r2);
        int rowLength = toReplace.getColumnCount();

        for (int i = 0; i < rowLength; i++) {
            float c = toAdd.getValue(i);
            toReplace.addition(i, c);
        }
    }

    //REQUIRES: 0 < r1, r2 < rowCount
    //MODIFIES: this
    //EFFECTS: subtracts row values, replaces second row given
    public void subtractRow(int r1, int r2) {
        Row toSubtract = this.column.get(r1);
        Row toReplace = this.column.get(r2);
        int rowLength = toReplace.getColumnCount();

        for (int i = 0; i < rowLength; i++) {
            float c = toSubtract.getValue(i);
            toReplace.subtract(i, c);
        }
    }

    //REQUIRES: 0 < r1, r2 < rowCount
    //MODIFIES: this
    //EFFECTS: multiplies every value in row with given constant
    public void multiplyRow(int r1, float c) {
        Row toMultiply = this.column.get(r1);
        toMultiply.multiply(c);
    }

    //TEST FUNCTION
    public void removeRow(int r) {
        this.column.remove(r);
    }

    //TEST FUNCTION
    public void insertRow(int index, Row row) {
        this.column.add(index, row);
    }

    public int getRowCount() {
        return this.column.size();
    }

    public int getColCount() {
        return this.rowCount;
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





    //JSON WORK
    @Override
    public JSONArray toJson() {
        JSONArray json = new JSONArray();
        for (Row row : column) {
            json.put(row.toJson());
        }

        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray rowsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Row row : column) {
            jsonArray.put(row.toJson());
        }

        return jsonArray;
    }



}
