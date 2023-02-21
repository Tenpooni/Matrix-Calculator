package model;

import java.util.ArrayList;

public class OldColumn {
    private ArrayList<Float> elements;

    public OldColumn(int rowNum) {
        this.elements = new ArrayList<>(rowNum);
    }

    public void printElements() {
        String columnElement = "";
        String divider = "|";

        for (Float num : this.elements) {
            columnElement = columnElement + divider + num;
        }

        System.out.println(columnElement);
    }

    public void initializeElement(int i, float val) {
        this.elements.add(i, val);
    }

    public void updateElement(int i, float val) {
        this.elements.add(i, val);
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
}
