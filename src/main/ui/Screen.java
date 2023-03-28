package ui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Panel containing supplement menu GUI elements
public class Screen extends JPanel {
    private JLabel label;
    private String text;
    private CalculatorControllerUI calculatorControllerGUI;

    //Constructor creates screen display area.
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

    //MODIFIES: this
    //EFFECTS: updates label with given string
    public void refreshLabel(String str) {
        label.setText(str);
        //label.setAlignmentX(CENTER_ALIGNMENT);
        label.repaint();
    }


}


