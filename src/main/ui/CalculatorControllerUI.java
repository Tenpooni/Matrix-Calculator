package ui;

import model.Event;
import model.EventLog;
import model.Matrix;
import model.Row;
import persistence.Writable;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

//Matrix Calculator GUI
public class CalculatorControllerUI extends JFrame implements Writable, WindowListener {
    private final JFrame frameObj;
    private Screen screen;
    private Operations operationPad;
    private SupplementMenu supplementMenu;
    private Editor editor;
    private RowEditor rowEditor;

    private int columnCount;
    private int rowCount;
    Matrix matrix = new Matrix();

    private static final String JSON_STORE = "./data/matrix.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    /**
     * Constructor sets up button panel, key pad and visual matrix window.
     */
    public CalculatorControllerUI() throws FileNotFoundException {
        frameObj = new JFrame();
        frameObj.setLayout(new GridLayout(5,1));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(this);

        addScreen(frameObj);
        addOperationsPad(frameObj);
        addMatrixEditor(frameObj);
        addRowEditor(frameObj);
        addSupplementMenu(frameObj);

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

    //EFFECTS: Returns string representation of history one row per line using html
    private String printHistory() {
        String lineStart = "<html>";
        String lineEnd = "</html>";
        String lineBreak = "<br>";

        String temp = "";

        for (String str : matrix.getResult()) {
            //System.out.println(str);
            temp = temp + str + lineBreak;
        }

        temp = lineStart + temp + lineEnd;
        return temp;
    }

    //EFFECTS: prints latest matrix state to screen
    public void refreshScreen() {
        String str = printMatrix();
        screen.refreshLabel(str);
    }

    //EFFECTS: changes icon to addition
    public void setAdd() {
        screen.addIcon();
    }

    //EFFECTS: changes icon to subtraction
    public void setSubtract() {
        screen.subtractIcon();
    }

    //EFFECTS: changes icon to multiplication
    public void setMultiply() {
        screen.multiplyIcon();
    }

    //EFFECTS: changes icon to swap
    public void setSwap() {
        screen.swapIcon();
    }

    //EFFECTS: changes icon to blank
    public void setBlank() {
        screen.blankIcon();
    }


    //EFFECTS: prints history to screen
    public void showHistory() {
        String str = printHistory();
        screen.refreshLabel(str);
    }


    //EFFECTS: sets up screen GUI panel
    private void addScreen(JFrame frame) {
        screen = new Screen(this);
        frame.add(screen);
    }

    //EFFECTS: sets up operations menu panel
    private void addOperationsPad(JFrame frame) {
        operationPad = new Operations(this);
        addKeyListener(operationPad);
        frame.add(operationPad);
    }

    //EFFECTS: sets up matrix editor menu panel
    private void addMatrixEditor(JFrame frame) {
        editor = new Editor(this);
        addKeyListener(editor);
        frame.add(editor);
    }

    //EFFECTS: sets up row editor menu panel
    private void addRowEditor(JFrame frame) {
        rowEditor = new RowEditor(this);
        addKeyListener(rowEditor);
        frame.add(rowEditor);
    }

    //EFFECTS: sets up supplement menu panel
    private void addSupplementMenu(JFrame frame) {
        supplementMenu = new SupplementMenu(this);
        addKeyListener(supplementMenu);
        frame.add(supplementMenu);
    }

    //EFFECTS: matrix getter
    public Matrix getMatrix() {
        return this.matrix;
    }


    public static void main(String[] args) {
        try {
            new CalculatorControllerUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }


    //MODIFIES: this.matrix
    //EFFECTS: creates new matrix
    public void setUpMatrixValues(int e1, int e2) {
        ArrayList<Integer> valueList = editor.getVal();

        this.rowCount = e1;
        this.columnCount = e2;

        this.matrix.newMatrix(rowCount);
        matrix.setRowCount(e1);
        matrix.setColumnCount(e2);

        //System.out.println(rowCount);
        //System.out.println(columnCount);

        for (int i = 0; i < rowCount; i++) {

            Row tempRow = new Row(columnCount);

            for (int j = 0; j < columnCount; j++) {

                int entry = valueList.get(i * (columnCount) + j);
                tempRow.setRow(j, entry);

            }
            matrix.setColumn(i, tempRow);
            refreshScreen();
        }
    }

    //MODIFIES: this.matrix
    //EFFECTS: clears entries in log
    public void clearHistory() {
        matrix.clearHistory();
    }


    //MODIFIES: this.matrix
    //EFFECTS: makes new row object, adds entry to log, updates GUI screen
    public void setUpNewRow(int e1) {
        ArrayList<Integer> valueList = rowEditor.getVal();
        Row toInsert = new Row(columnCount);

        for (int i = 0; i < columnCount; i++) {
            int tempVal = valueList.get(i);
            toInsert.setRow(i, tempVal);
        }

        matrix.insertMatrixRow(e1 - 1, toInsert);
        rowCount = matrix.getMatrixColumnSize();

        matrix.enterVector(e1 - 1, "Added");
        refreshScreen();
    }


    //REQUIRES: rowCount > 1
    //EFFECTS: removes row object, adds entry to log, updates GUI screen
    public void removeRow(int e1) {
        if (this.rowCount > 1) {
            matrix.removeMatrixRow(e1 - 1);
            rowCount = matrix.getMatrixColumnSize();

            matrix.enterVector(e1 - 1, "Removed");
        }
        refreshScreen();
    }


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
            //System.out.println("Saved matrix to " + JSON_STORE);
            //screen.refreshLabel("Saved matrix to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            //System.out.println("Unable to write to file: " + JSON_STORE);
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
            //System.out.println("Unable to read from file: " + JSON_STORE);
            //screen.refreshLabel("Unable to read from file: " + JSON_STORE);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        //System.out.println("hello world");
        printLogEvents(EventLog.getInstance());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    private void printLogEvents(EventLog el) {
        for (Event next : el) {
            System.out.println(next);
            //logArea.setText(logArea.getText() + next.toString() + "\n\n");
        }
    }
}