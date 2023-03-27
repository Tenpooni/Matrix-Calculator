package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.*;

public class Operations extends JPanel implements KeyListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private JLabel label;
    private String code;
    private Operations.ClickHandler keyHandler;
    private CalculatorControllerUI calculatorControllerGUI;
    private JFormattedTextField entryField1;
    private JFormattedTextField entryField2;
    private NumberFormat numberFormat;

    /**
     * Constructor creates keypad and code display area.
     */
    public Operations(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        code = "";
        keyHandler = new Operations.ClickHandler();
        setLayout(new BorderLayout());
        JPanel operationPanel = new JPanel();

        label = new JLabel("Operations");
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(label, CENTER_ALIGNMENT);
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.NORTH);

        operationPanel.setLayout(new GridLayout(2,1));
        //addButtons(operationPanel);

        operationPanel.add(addEntry());
        operationPanel.add(addButtons());


        add(operationPanel, BorderLayout.CENTER);

    }

    /**
     * Adds buttons to operations panel
     *
     */
    private JPanel addButtons() {
        JPanel p = new JPanel(new GridLayout(1, 4));
        keys = new JButton[4];

        keys[0] = new JButton("+");
        keys[0].addActionListener(keyHandler);
        keys[1] = new JButton("-");
        keys[1].addActionListener(keyHandler);
        keys[2] = new JButton("*");
        keys[2].addActionListener(keyHandler);
        keys[3] = new JButton("swap");
        keys[3].addActionListener(keyHandler);

        p.add(keys[0]);
        p.add(keys[1]);
        p.add(keys[2]);
        p.add(keys[3]);

        return p;
    }

    private JPanel addEntry() {
        JPanel p = new JPanel(new GridLayout(2, 3));
        entryField1 = new JFormattedTextField(numberFormat);
        entryField2 = new JFormattedTextField(numberFormat);

        JLabel label1 = new JLabel("Enter Row number : ");
        JLabel label2 = new JLabel("Next Row/Constant : ");

        JButton confirmEntry1 = new JButton("Confirm 1st entry");
        JButton confirmEntry2 = new JButton("Confirm 2nd entry");

        p.add(label1);
        p.add(entryField1);
        p.add(confirmEntry1);
        p.add(label2);
        p.add(entryField2);
        p.add(confirmEntry2);

        return p;
    }




    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals("swap")) {
                code = "";
            } else if (src.getText().equals("-")) {
                code = "";
            } else if (src.getText().equals("+")) {
                code = "";
            } else if (src.getText().equals("*")) {
                code = "";
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char key = ke.getKeyChar();

        if (key == '0') {
            keys[10].doClick();
        } else if (key > '0' && key <= '9') {
            keys[ke.getKeyChar() - '1'].doClick();
        }
    }

}
