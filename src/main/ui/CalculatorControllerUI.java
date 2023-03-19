package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.FileNotFoundException;


public class CalculatorControllerUI extends JPanel {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 325;
    private static final int VGAP = 15;
    //private Calculator calculator;
    //private Keypad kp;
    private JLabel statusLabel;
    private Main mainGUI;
    private JPanel area;


    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public CalculatorControllerUI(Main main) {

        mainGUI = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(VGAP));

        area = new JPanel();
        area.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        add(area);

    }


}
