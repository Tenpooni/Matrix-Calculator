package model;

import java.util.ArrayList;

public class Row {
    private ArrayList<Float> elements;
    private int columnCount;

    public Row(int colNum) {
        this.elements = new ArrayList<>(colNum);
        this.columnCount = colNum;
    }

    public String rowPrint() {
        String line = "";
        String divider = "|";

        for (Float num : this.elements) {
            line = line + divider + num;
        }
        return line;
    }

    public void multiply(float c) {
        for (int i = 0; i < this.columnCount; i++) {
            float num = this.elements.get(i);
            num = num * c;
            this.elements.set(i,num);
        }
    }

    public void addition(int i, float c) {
        float num = this.elements.get(i);
        num = num + c;
        this.elements.set(i,num);
    }

    public void subtract(int i, float c) {
        float num = this.elements.get(i);
        num = c - num;
        this.elements.set(i,num);
    }

    public void setRow(int i, float val) {
        this.elements.add(i, val);
    }

    public float getValue(int j) {
        return this.elements.get(j);
    }

    public int getColumnCount() {
        return this.columnCount;
    }

}