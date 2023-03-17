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
    private String[] colours = { "red", "green", "yellow", "purple", "blue" };
    private static final String STATUS_OK = "System OK";
    private Calculator calculator;
    private Keypad kp;
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

        // Create the selection of light combo box
        final JComboBox colourCombo = new JComboBox(colours);
        colourCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String colourChoice = (String) colourCombo.getSelectedItem();
            }
        });

        add(colourCombo);

        add(Box.createVerticalStrut(VGAP));

        area = new JPanel();
        area.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        add(area);

    }


}
