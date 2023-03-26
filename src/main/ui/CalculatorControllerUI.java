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


public class CalculatorControllerUI extends JFrame {
    private Calculator calculator;
    private JFrame frameObj;
    private Keypad keypad;
    private Screen screen;
    private Operations operationPad;
    private SupplementMenu supplementMenu;

    private int columnCount;
    private int rowCount;
    private final Scanner input = new Scanner(System.in);
    Matrix matrix = new Matrix();

    boolean runCalc = true;


    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public CalculatorControllerUI() {
        frameObj = new JFrame();
        frameObj.setLayout(new GridLayout(3,1));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addScreen(frameObj);
        updateTestButton(frameObj);

        frameObj.setSize(450, 300);
        frameObj.setVisible(true);
        pack();
        setVisible(true);

        createCalculator();


    }

    public String updateMatrix() {
        String lineStart = "<html>";
        String lineEnd = "</html>";
        String lineBreak = "<br>";

        String temp = "";

        int rowSize = matrix.getMatrixRowSize();
        ArrayList<String> testList = matrix.getResult();

        for (int i = 0; i < rowSize; i++) {
            temp = testList.get(i) + lineBreak + temp;
        }
        temp = lineStart + temp + lineEnd;
        return temp;
    }


    private void refreshScreen() {
        String str = updateMatrix();
        screen.refreshLabel(str);
    }



    private void updateTestButton(JFrame frame) {
        JButton advanceButton = new JButton("Test");
        advanceButton.setActionCommand("test");
        advanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshScreen();
            }
        });
        frame.add(advanceButton);
    }

    private void addScreen(JFrame frame) {
        screen = new Screen(this);
        frame.add(screen);
    }

    public static void main(String[] args) {
        //try {
            //new Calculator();
        //} catch (FileNotFoundException e) {
            //System.out.println("Unable to run application: file not found");
        //}
        new CalculatorControllerUI();
    }














    private void createCalculator() {
        try {
            this.calculator = new Calculator();
            this.matrix = this.calculator.getCalculatorMatrix();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    private void addKeyPad(JFrame frame) {
        keypad = new Keypad(this);
        addKeyListener(keypad);
        frame.add(keypad);
    }

    private void addOperationsPad(JFrame frame) {
        operationPad = new Operations(this);
        addKeyListener(operationPad);
        frame.add(operationPad);
    }

    private void addSupplementMenu(JFrame frame) {
        supplementMenu = new SupplementMenu(this);
        addKeyListener(supplementMenu);
        frame.add(supplementMenu);
    }



}