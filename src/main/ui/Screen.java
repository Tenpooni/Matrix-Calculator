package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen extends JPanel {
    private JLabel label;
    private String text;
    private CalculatorControllerUI calculatorControllerGUI;

    /**
     * Constructor creates keypad and code display area.
     */
    public Screen(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        text = "";
        setLayout(new BorderLayout());
        JPanel operationPanel = new JPanel();

        label = new JLabel("Matrix Calculator");
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(label, CENTER_ALIGNMENT);
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.NORTH);

        add(operationPanel, BorderLayout.CENTER);

    }

    public void refreshLabel(String str) {
        label.setText(str);
        label.repaint();
    }


}


