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
import java.util.ArrayList;

import javax.swing.*;


public class Editor extends JPanel implements KeyListener, PropertyChangeListener {
    private JButton[] keys;
    private JLabel label;
    private String code;
    private ClickHandler keyHandler;
    private CalculatorControllerUI calculatorControllerGUI;
    private JFormattedTextField entryField1;
    private JFormattedTextField entryField2;
    private JFormattedTextField entryField3;
    private int e1;
    private int e2;
    private int e3;
    int valueToPass;
    private NumberFormat numberFormat;
    private Matrix matrix;
    private boolean insertingMatrix = false;

    private ArrayList<Integer> values = new ArrayList<Integer>();

    /**
     * Constructor creates keypad and code display area.
     */
    public Editor(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        matrix = calculatorControllerGUI.getMatrix();

        //sets up number Format
        numberFormat = NumberFormat.getNumberInstance();

        code = "";
        keyHandler = new Editor.ClickHandler();
        setLayout(new BorderLayout());
        JPanel operationPanel = new JPanel();

        label = new JLabel("Matrix Editor");
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(label, CENTER_ALIGNMENT);
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.NORTH);

        operationPanel.setLayout(new GridLayout(1,2));

        operationPanel.add(addEntry());
        operationPanel.add(addButtons());


        add(operationPanel, BorderLayout.CENTER);

    }


    /**
     * Adds buttons to operations panel
     *
     */
    private JPanel addButtons() {
        JPanel p = new JPanel(new GridLayout(2, 1));
        keys = new JButton[2];

        keys[0] = new JButton("New matrix");
        keys[0].addActionListener(keyHandler);
        keys[1] = new JButton("Submit");
        keys[1].addActionListener(keyHandler);

        p.add(keys[0]);
        p.add(keys[1]);

        return p;
    }

    private JPanel addEntry() {
        JPanel p = new JPanel(new GridLayout(3, 2));

        entryField1 = new JFormattedTextField(numberFormat);
        entryField1.setValue(0);
        entryField1.addPropertyChangeListener(this);

        entryField2 = new JFormattedTextField(numberFormat);
        entryField2.setValue(0);
        entryField2.addPropertyChangeListener(this);

        entryField3 = new JFormattedTextField(numberFormat);
        entryField3.setValue(0);
        entryField3.addPropertyChangeListener(this);

        JLabel label1 = new JLabel("Enter new row size : ");
        JLabel label2 = new JLabel("Enter new column size : ");
        JLabel label3 = new JLabel("Enter values : ");

        p.add(label1);
        p.add(entryField1);
        p.add(label2);
        p.add(entryField2);
        p.add(label3);
        p.add(entryField3);

        return p;
    }



    //verifies the entries are integer
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == entryField1) {
            e1 = ((Number)entryField1.getValue()).intValue();
        } else if (source == entryField2) {
            e2 = ((Number)entryField2.getValue()).intValue();
        } else if (source == entryField3) {
            e3 = ((Number)entryField3.getValue()).intValue();
        }
    }

    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals("Insert row")) {
                //stub
            } else if (src.getText().equals("Submit")) {
                if (insertingMatrix) {
                    if (values.size() < (e1 * e2)) {
                        advance(e3);
                    } else if (values.size() >= (e1 * e2)) {
                        calculatorControllerGUI.setUpMatrixValues(e1, e2);
                        values.clear();
                        insertingMatrix = false;
                    }
                }
            } else if (src.getText().equals("New matrix")) {
                if (e1 > 0 && e2 > 0) {
                    insertingMatrix = true;
                }
            }

        }
    }

    private void advance(int val) {
        values.add(val);
    }

    public ArrayList getVal() {
        return values;
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


