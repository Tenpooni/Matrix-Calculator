package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.io.FileNotFoundException;



public class Main extends JFrame {
    private static final String STATUS_OK = "System OK";
    private Calculator calculator;
    private JLabel statusLabel;
    private CalculatorControllerUI calculatorArea;
    private Keypad keypad;


    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public Main() {

        super("TEST UI");


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        statusLabel = new JLabel(STATUS_OK);
        add(statusLabel, BorderLayout.NORTH);


        calculatorArea = new CalculatorControllerUI(this);
        add(calculatorArea, BorderLayout.CENTER);

        JButton advanceButton = new JButton("Advance");
        advanceButton.setActionCommand("advance");
        advanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //stub
            }
        });

        add(advanceButton, BorderLayout.SOUTH);
        addKeyPad();

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

    private void addKeyPad() {
        keypad = new Keypad(this);
        addKeyListener(keypad);
        add(keypad, BorderLayout.CENTER);
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