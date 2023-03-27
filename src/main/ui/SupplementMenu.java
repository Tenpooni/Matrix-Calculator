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

public class SupplementMenu extends JPanel implements KeyListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private JLabel label;
    private String code;
    private SupplementMenu.ClickHandler keyHandler;
    private CalculatorControllerUI calculatorControllerGUI;

    /**
     * Constructor creates keypad and code display area.
     */
    public SupplementMenu(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        code = "";
        keyHandler = new SupplementMenu.ClickHandler();
        setLayout(new BorderLayout());
        JPanel operationPanel = new JPanel();

        operationPanel.setLayout(new GridLayout(1,5));
        addButtons(operationPanel);
        add(operationPanel, BorderLayout.CENTER);

        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.NORTH);


    }

    /**
     * Adds buttons to operations panel
     * @param p  the operation panel
     */
    private void addButtons(JPanel p) {
        keys = new JButton[5];

        keys[0] = new JButton("Save");
        keys[0].addActionListener(keyHandler);
        keys[1] = new JButton("Load");
        keys[1].addActionListener(keyHandler);
        keys[2] = new JButton("History");
        keys[2].addActionListener(keyHandler);
        keys[3] = new JButton("Test");
        keys[3].addActionListener(keyHandler);
        keys[4] = new JButton("Test2");
        keys[4].addActionListener(keyHandler);

        p.add(keys[0]);
        p.add(keys[1]);
        p.add(keys[2]);
        p.add(keys[3]);
        p.add(keys[4]);

    }




    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals("Load")) {
                calculatorControllerGUI.loadMatrix();
                calculatorControllerGUI.refreshScreen();
            } else if (src.getText().equals("Save")) {
                calculatorControllerGUI.saveMatrix();
                calculatorControllerGUI.refreshScreen();
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

    }

}
