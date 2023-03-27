package ui;

import model.Matrix;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.*;

public class Operations extends JPanel implements KeyListener, PropertyChangeListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private JLabel label;
    private String code;
    private Operations.ClickHandler keyHandler;
    private CalculatorControllerUI calculatorControllerGUI;
    private JFormattedTextField entryField1;
    private JFormattedTextField entryField2;
    private int r1;
    private int r2;
    private NumberFormat numberFormat;
    private Matrix matrix;

    /**
     * Constructor creates keypad and code display area.
     */
    public Operations(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        matrix = calculatorControllerGUI.getMatrix();

        //sets up number Format
        numberFormat = NumberFormat.getNumberInstance();

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
        JPanel p = new JPanel(new GridLayout(2, 2));

        entryField1 = new JFormattedTextField(numberFormat);
        entryField1.setValue(-1);
        entryField1.addPropertyChangeListener(this);

        entryField2 = new JFormattedTextField(numberFormat);
        entryField2.setValue(-1);
        entryField2.addPropertyChangeListener(this);

        JLabel label1 = new JLabel("Enter Row number : ");
        JLabel label2 = new JLabel("Next Row/Constant : ");


        p.add(label1);
        p.add(entryField1);
        p.add(label2);
        p.add(entryField2);

        return p;
    }


    //verifies the entries are integer
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == entryField1) {
            r1 = ((Number)entryField1.getValue()).intValue();
        } else if (source == entryField2) {
            r2 = ((Number)entryField2.getValue()).intValue();
        }

    }


    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals("swap")) {
                verifyRowSwap(r1, r2);
                calculatorControllerGUI.refreshScreen();
            } else if (src.getText().equals("-")) {
                verifyRowSubtraction(r1, r2);
                calculatorControllerGUI.refreshScreen();
            } else if (src.getText().equals("+")) {
                verifyRowAddition(r1, r2);
                calculatorControllerGUI.refreshScreen();
            } else if (src.getText().equals("*")) {
                verifyMultiply(r1, r2);
                calculatorControllerGUI.refreshScreen();

            }
        }
    }

    private void verifyMultiply(int val1, int val2) {
        if (val1 > 0 && val1 <= matrix.getMatrixColumnSize()) {
            float c = val2;
            int rowSelection = val1 - 1;
            matrix.multiplyRow(rowSelection, c);
        }
    }

    private void verifyRowAddition(int val1, int val2) {
        if (val1 > 0 && val1 <= matrix.getMatrixColumnSize()
                && val2 > 0 && val2 <= matrix.getMatrixColumnSize() && val1 != val2) {
            matrix.addRow(r1 -  1, r2 - 1);
        }
    }

    private void verifyRowSubtraction(int val1, int val2) {
        if (val1 > 0 && val1 <= matrix.getMatrixColumnSize()
                && val2 > 0 && val2 <= matrix.getMatrixColumnSize() && val1 != val2) {
            matrix.subtractRow(r1 -  1, r2 - 1);
        }
    }

    private void verifyRowSwap(int val1, int val2) {
        if (val1 > 0 && val1 <= matrix.getMatrixColumnSize()
                && val2 > 0 && val2 <= matrix.getMatrixColumnSize() && val1 != val2) {
            matrix.swapRow(r1 -  1, r2 - 1);
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

    }

}
