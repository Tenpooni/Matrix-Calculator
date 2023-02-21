package model;

import java.util.ArrayList;

public class Column {
    private ArrayList<Row> column;
    private int rowCount;

    public Column(int rowNum) {
        this.column = new ArrayList<>(rowNum);
        this.rowCount = rowNum;
    }

    public void initializeColumn(int i, Row r) {
        this.column.add(i, r);
    }

    public ArrayList<String> columnPrint() {
        String line = "";
        ArrayList<String> lineToPrint = new ArrayList<>();

        for (Row row : this.column) {
            line = row.rowPrint();
            lineToPrint.add(line);
        }
        return lineToPrint;
    }

    public void swapRow(int r1, int r2) {
        Row temp = this.column.get(r1);
        this.column.set(r1,this.column.get(r2));
        this.column.set(r2,temp);
    }

    public void addRow(int r1, int r2) {
        Row toAdd = this.column.get(r1);
        Row toReplace = this.column.get(r2);

        for (int i = 0; i < this.rowCount; i++) {
            float c = toAdd.getValue(i);
            toReplace.addition(i, c);
        }
    }

    public void subtractRow(int r1, int r2) {
        Row toSubtract = this.column.get(r1);
        Row toReplace = this.column.get(r2);

        for (int i = 0; i < this.rowCount; i++) {
            float c = toSubtract.getValue(i);
            toReplace.subtract(i, c);
        }
    }

    public void multiplyRow(int r1, float c) {
        Row toMultiply = this.column.get(r1);
        toMultiply.multiply(c);
    }

}
