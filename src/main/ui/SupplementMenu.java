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

//Panel containing supplement menu GUI elements
public class SupplementMenu extends JPanel implements KeyListener {
    private JButton[] keys;
    private JLabel label;
    private String code;
    private SupplementMenu.ClickHandler keyHandler;
    private CalculatorControllerUI calculatorControllerGUI;

    //Constructor creates supplemental menu display area.
    public SupplementMenu(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        code = "";
        keyHandler = new SupplementMenu.ClickHandler();
        setLayout(new BorderLayout());
        JPanel operationPanel = new JPanel();

        operationPanel.setLayout(new GridLayout(1,3));
        addButtons(operationPanel);
        add(operationPanel, BorderLayout.CENTER);

        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.NORTH);


    }

    //MODIFIES: this
    //EFFECTS: Adds buttons to operation panel
    private void addButtons(JPanel p) {
        keys = new JButton[3];

        keys[0] = new JButton("Save");
        keys[0].addActionListener(keyHandler);
        keys[1] = new JButton("Load");
        keys[1].addActionListener(keyHandler);
        keys[2] = new JButton("History");
        keys[2].addActionListener(keyHandler);

        p.add(keys[0]);
        p.add(keys[1]);
        p.add(keys[2]);

    }


    //EFFECTS: Listener for events in menu: load, save and history
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals("Load")) {
                calculatorControllerGUI.loadMatrix();
                calculatorControllerGUI.refreshScreen();
                calculatorControllerGUI.setBlank();
            } else if (src.getText().equals("Save")) {
                calculatorControllerGUI.saveMatrix();
                calculatorControllerGUI.refreshScreen();
                calculatorControllerGUI.setBlank();
            } else if (src.getText().equals("History")) {
                calculatorControllerGUI.showHistory();
                calculatorControllerGUI.setBlank();
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
