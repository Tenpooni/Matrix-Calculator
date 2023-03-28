package ui;

import model.Matrix;
import model.Row;
import persistence.Writable;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CalculatorControllerUI extends JFrame implements Writable {
    private JFrame frameObj;
    private Screen screen;
    private Operations operationPad;
    private SupplementMenu supplementMenu;
    private Editor editor;
    private RowEditor rowEditor;

    private int columnCount;
    private int rowCount;
    private final Scanner input = new Scanner(System.in);
    Matrix matrix = new Matrix();
    boolean valueToPass = false;

    boolean confirmed = false;

    private static final String JSON_STORE = "./data/matrix.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public CalculatorControllerUI() throws FileNotFoundException {
        frameObj = new JFrame();
        frameObj.setLayout(new GridLayout(5,1));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addScreen(frameObj);
        addOperationsPad(frameObj);
        addMatrixEditor(frameObj);
        addRowEditor(frameObj);
        addSupplementMenu(frameObj);

        //checks this button can print loaded matrix.
        //updateTestButton(frameObj);

        frameObj.setSize(750, 500);
        frameObj.setVisible(true);
        pack();
        setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        loadMatrix();
    }


    //EFFECTS: Returns string representation of matrix as one row per line using html
    private String printMatrix() {
        String lineStart = "<html>";
        String lineEnd = "</html>";
        String lineBreak = "<br>";

        String temp = "";

        for (String str : matrix.printMatrix()) {
            temp = temp + str + lineBreak;
        }

        temp = lineStart + temp + lineEnd;
        return temp;
    }

    //CALL THIS TO REFRESH MATRIX ON SCREEN
    public void refreshScreen() {
        String str = printMatrix();
        screen.refreshLabel(str);
    }


    //SET UP BUTTON PANELS
    private void addScreen(JFrame frame) {
        screen = new Screen(this);
        frame.add(screen);
    }

    private void addOperationsPad(JFrame frame) {
        operationPad = new Operations(this);
        addKeyListener(operationPad);
        frame.add(operationPad);
    }

    private void addMatrixEditor(JFrame frame) {
        editor = new Editor(this);
        addKeyListener(editor);
        frame.add(editor);
    }

    private void addRowEditor(JFrame frame) {
        rowEditor = new RowEditor(this);
        addKeyListener(rowEditor);
        frame.add(rowEditor);
    }

    private void addSupplementMenu(JFrame frame) {
        supplementMenu = new SupplementMenu(this);
        addKeyListener(supplementMenu);
        frame.add(supplementMenu);
    }


    //NOTE NOW CALLS CONTROLLER UI NOT CALCULATOR
    public static void main(String[] args) {
        try {
            new CalculatorControllerUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    //EFFECTS: matrix getter
    public Matrix getMatrix() {
        return this.matrix;
    }

    //EFFECTS: called by Editor, creates new matrix
    public void setUpMatrixValues(int e1, int e2) {
        ArrayList<Integer> vals = editor.getVal();

        this.rowCount = e1;
        this.columnCount = e2;

        this.matrix.newMatrix(rowCount);
        matrix.setRowCount(e1);
        matrix.setColumnCount(e2);

        System.out.println(rowCount);
        System.out.println(columnCount);

        for (int i = 0; i < rowCount; i++) {

            Row tempRow = new Row(columnCount);

            for (int j = 0; j < columnCount; j++) {

                int entry = vals.get(i * (columnCount) + j);
                tempRow.setRow(j, entry);

            }
            matrix.setColumn(i, tempRow);
            refreshScreen();
        }
    }


    public void setUpNewRow(int e1) {
        ArrayList<Integer> vals = rowEditor.getVal();
        Row toInsert = new Row(columnCount);

        for (int i = 0; i < columnCount; i++) {
            int tempVal = vals.get(i);
            toInsert.setRow(i, tempVal);
        }

        matrix.insertMatrixRow(e1 - 1, toInsert);

        //to refresh
        rowCount = matrix.getMatrixColumnSize();
        //matrix.enterVector(e1, "Added");

        //update to screen
        refreshScreen();
    }

    public void removeRow(int e1) {

        //checks this isn't last row
        if (this.rowCount > 1) {
            matrix.removeMatrixRow(e1 - 1);
            rowCount = matrix.getMatrixColumnSize();
            //matrix.enterVector(index, "Removed");
        }

        //update to screen
        refreshScreen();
    }






    //current WIP



    //FOR TESTING BUTTONS
    private void updateTestButton(JFrame frame) {
        JButton advanceButton = new JButton("Load Matrix");
        advanceButton.setActionCommand("Load Matrix");
        advanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println(printMatrix());
                refreshScreen();

            }
        });
        frame.add(advanceButton);
    }













    //CODE DIRECTLY TAKEN FROM CALCULATOR FOR JSON REASONS

    //EFFECTS: write both matrix and history log as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Matrix", matrix.matrixToJson());
        json.put("History", matrix.logToJson());
        return json;
    }

    // EFFECTS: saves matrix to file including history
    public void saveMatrix() {
        try {
            jsonWriter.open();
            jsonWriter.write(matrix);
            jsonWriter.close();
            System.out.println("Saved matrix to " + JSON_STORE);
            //screen.refreshLabel("Saved matrix to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            //screen.refreshLabel("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads matrix from file
    public void loadMatrix() {
        try {
            matrix.setMatrix(jsonReader.readMatrix());
            matrix.setLog(jsonReader.readLog());

            this.rowCount = matrix.getMatrixColumnSize();
            this.columnCount = matrix.getMatrixRowSize();

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            //screen.refreshLabel("Unable to read from file: " + JSON_STORE);
        }
    }
}