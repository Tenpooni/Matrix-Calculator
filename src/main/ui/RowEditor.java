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

public class RowEditor extends JPanel implements KeyListener, PropertyChangeListener {
    private JButton[] keys;
    private JLabel label;
    private String code;
    private RowEditor.ClickHandler keyHandler;
    private CalculatorControllerUI calculatorControllerGUI;
    private JFormattedTextField entryField1;
    private JFormattedTextField entryField2;
    private int e1;
    private int e2;
    private NumberFormat numberFormat;
    private Matrix matrix;
    private boolean insertingRow = false;

    private ArrayList<Integer> values = new ArrayList<Integer>();

    /**
     * Constructor creates keypad and code display area.
     */
    public RowEditor(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        matrix = calculatorControllerGUI.getMatrix();

        //sets up number Format
        numberFormat = NumberFormat.getNumberInstance();

        code = "";
        keyHandler = new RowEditor.ClickHandler();
        setLayout(new BorderLayout());
        JPanel operationPanel = new JPanel();

        label = new JLabel("Row Editor");
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
        JPanel p = new JPanel(new GridLayout(3, 1));
        keys = new JButton[3];

        keys[0] = new JButton("Insert row");
        keys[0].addActionListener(keyHandler);
        keys[1] = new JButton("Remove row");
        keys[1].addActionListener(keyHandler);
        keys[2] = new JButton("Submit");
        keys[2].addActionListener(keyHandler);

        p.add(keys[0]);
        p.add(keys[1]);
        p.add(keys[2]);

        return p;
    }

    private JPanel addEntry() {
        JPanel p = new JPanel(new GridLayout(2, 2));

        entryField1 = new JFormattedTextField(numberFormat);
        entryField1.setValue(0);
        entryField1.addPropertyChangeListener(this);

        entryField2 = new JFormattedTextField(numberFormat);
        entryField2.setValue(0);
        entryField2.addPropertyChangeListener(this);


        JLabel label1 = new JLabel("Enter row to edit : ");
        JLabel label2 = new JLabel("Enter values : ");

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
            e1 = ((Number)entryField1.getValue()).intValue();
        } else if (source == entryField2) {
            e2 = ((Number)entryField2.getValue()).intValue();
        }
    }

    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals("Remove row")) {
                if (e1 > 0 && e1 <= matrix.getMatrixColumnSize() + 1) {
                    calculatorControllerGUI.removeRow(e1);
                }
            } else if (src.getText().equals("Submit")) {
                if (insertingRow) {
                    if (values.size() < matrix.getMatrixRowSize()) {
                        advance(e2);
                    } else if (values.size() >= matrix.getMatrixRowSize()) {
                        calculatorControllerGUI.setUpNewRow(e1);
                        values.clear();
                        insertingRow = false;
                    }
                }
            } else if (src.getText().equals("Insert row")) {
                if (e1 > 0 && e1 <= matrix.getMatrixColumnSize() + 1) {
                    insertingRow = true;
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
