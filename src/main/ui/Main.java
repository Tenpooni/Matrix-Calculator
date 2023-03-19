package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.io.FileNotFoundException;



public class Main extends JFrame {
    private static final String STATUS_OK = "System OK";
    private Calculator calculator;
    private JLabel statusLabel;
    private JFrame frameObj;
    private CalculatorControllerUI calculatorArea;
    private Keypad keypad;
    private Operations operationPad;
    private SupplementMenu supplementMenu;


    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public Main() {
        super("TEST UI");

        frameObj = new JFrame();

        frameObj.setLayout(new GridLayout(3,1));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //statusLabel = new JLabel(STATUS_OK);
        //add(statusLabel, BorderLayout.NORTH);

        //calculatorArea = new CalculatorControllerUI(this);
        //add(calculatorArea, BorderLayout.CENTER);


        addKeyPad(frameObj);
        addOperationsPad(frameObj);
        addSupplementMenu(frameObj);

        frameObj.setSize(450, 300);
        frameObj.setVisible(true);

        pack();
        setVisible(true);

        createCalculator();

    }

    private void createCalculator() {
        try {
            calculator = new Calculator();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }



    private void addKeyPad(JFrame frame) {
        keypad = new Keypad(this);
        addKeyListener(keypad);
        //add(keypad, BorderLayout.NORTH);
        frame.add(keypad);
    }

    private void addOperationsPad(JFrame frame) {
        operationPad = new Operations(this);
        addKeyListener(operationPad);
        //add(operationPad, BorderLayout.CENTER);
        frame.add(operationPad);
    }

    private void addSupplementMenu(JFrame frame) {
        supplementMenu = new SupplementMenu(this);
        addKeyListener(supplementMenu);
        //add(supplementMenu, BorderLayout.SOUTH);
        frame.add(supplementMenu);
    }

    public static void main(String[] args) {
        //try {
            //new Calculator();
        //} catch (FileNotFoundException e) {
            //System.out.println("Unable to run application: file not found");
        //}

        new Main();
    }








}