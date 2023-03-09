package model;

import org.json.JSONArray;

import java.util.ArrayList;

//Represents a row of arbitrary size
public class Row {
    private ArrayList<Float> elements;   //stores floats in row
    private int rowSize;             //size of row


    //MODIFIES: this
    //EFFECTS: creates row with memory to store arbitrary number of rows
    public Row(int colNum) {
        this.elements = new ArrayList<>(colNum);
        this.rowSize = colNum;
    }

    //EFFECTS: returns text representation of the row
    public String rowPrint() {
        String line = "";
        String divider = "|";

        for (Float num : this.elements) {
            line = line + divider + num;
        }
        return line;
    }

    //MODIFIES: this
    //EFFECTS: multiplies every float in row by given constant
    public void multiply(float c) {
        for (int i = 0; i < this.rowSize; i++) {
            float num = this.elements.get(i);
            num = num * c;
            this.elements.set(i,num);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds value at index to constant and replaces as new index
    public void addition(int i, float c) {
        float num = this.elements.get(i);
        num = num + c;
        this.elements.set(i,num);
    }

    //MODIFIES: this
    //EFFECTS: subtracts value at index with constant and replaces as new index
    public void subtract(int i, float c) {
        float num = this.elements.get(i);
        num = c - num;
        this.elements.set(i,num);
    }

    //MODIFIES: this
    //EFFECTS: add row value at index
    public void setRow(int i, float val) {
        this.elements.add(i, val);
        //this.columnCount = elements.size();
    }

    //EFFECTS: return value at index
    public float getValue(int j) {
        return this.elements.get(j);
    }

    //EFFECTS: return columnCount
    public int getRowSize() {
        return this.elements.size();
    }


    //EFFECTS: Writes each float value to JsonArray
    public JSONArray rowToJson() {
        JSONArray json = new JSONArray();
        for (Float e : elements) {
            json.put(e);
        }
        return json;
    }
}