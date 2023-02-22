package model;

import java.util.ArrayList;

//Represents a column of arbitrary size
public class Column {
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

}
