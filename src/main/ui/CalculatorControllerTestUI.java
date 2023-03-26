package ui;

import java.awt.*;

import javax.swing.*;


public class CalculatorControllerTestUI extends JPanel {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 325;
    private static final int VGAP = 15;
    //private Calculator calculator;
    //private Keypad kp;
    private JLabel statusLabel;
    private CalculatorControllerUI calculatorControllerUIGUI;
    private JPanel area;


    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public CalculatorControllerTestUI(CalculatorControllerUI calculatorControllerUI) {

        calculatorControllerUIGUI = calculatorControllerUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(VGAP));

        area = new JPanel();
        area.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        add(area);

    }


}
