package model;

import java.util.ArrayList;

public class OldRow {
    private ArrayList<Float> elements;
    private int columnCount;

    public OldRow(int colNum) {
        this.elements = new ArrayList<>(colNum);
        this.columnCount = columnCount;
    }

    public void printElements() {
        String rowElement = "";
        String divider = "|";

        for (Float num : this.elements) {
            rowElement = rowElement + divider + num;
        }

        System.out.println(rowElement);
    }

    public void initializeElement(int i, float val) {
        this.elements.add(i, val);
    }

    public void updateElement(int i, float val) {
        this.elements.set(i, val);
    }

    public void multiply(float c) {
        for (int i = 0; i < this.elements.size(); i++) {
            float num = this.elements.get(i);
            num = num * c;
            this.elements.set(i,num);
        }
    }

    public float getValue(int j) {
        return this.elements.get(j);
    }

    public void add(int i, float c) {
        float num = this.elements.get(i);
        num = num + c;
        this.elements.set(i,num);
    }

    public void subtract(int i, float c) {
        float num = this.elements.get(i);
        num = c - num;
        this.elements.set(i,num);
    }

    public void setColumnCount(int i) {
        this.columnCount = i;
    }


}
