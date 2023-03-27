package ui;

import model.Matrix;
import model.Row;
import persistence.Writable;
import model.Log;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class CalculatorControllerUI extends JFrame implements Writable {
    private JFrame frameObj;
    private Screen screen;
    private Operations operationPad;
    private SupplementMenu supplementMenu;

    private int columnCount;
    private int rowCount;
    private final Scanner input = new Scanner(System.in);
    Matrix matrix = new Matrix();

    boolean runCalc = true;

    private static final String JSON_STORE = "./data/matrix.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public CalculatorControllerUI() throws FileNotFoundException {
        frameObj = new JFrame();
        frameObj.setLayout(new GridLayout(3,1));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addScreen(frameObj);
        addOperationsPad(frameObj);

        //checks this button can print loaded matrix.
        updateTestButton(frameObj);

        frameObj.setSize(450, 300);
        frameObj.setVisible(true);
        pack();
        setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        loadMatrix();
    }



    //IMPORTING METHODS FROM CALCULATOR


    // EFFECTS: loads matrix from file
    private void loadMatrix() {
        try {
            matrix.setMatrix(jsonReader.readMatrix());
            matrix.setLog(jsonReader.readLog());

            this.rowCount = matrix.getMatrixColumnSize();
            this.columnCount = matrix.getMatrixRowSize();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
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







    //NOTE NOW CALLS CONTROLLER UI NOT CALCULATOR
    public static void main(String[] args) {
        try {
            new CalculatorControllerUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }











    //current WIP
    public Matrix getMatrix() {
        return this.matrix;
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
    private void saveMatrix() {
        try {
            jsonWriter.open();
            jsonWriter.write(matrix);
            jsonWriter.close();
            System.out.println("Saved matrix to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


















    //UNUSED YET BELOW, SAVED FOR REFERENCE

    private void addSupplementMenu(JFrame frame) {
        supplementMenu = new SupplementMenu(this);
        addKeyListener(supplementMenu);
        frame.add(supplementMenu);
    }

}