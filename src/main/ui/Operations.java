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

public class Operations extends JPanel implements KeyListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private JLabel label;
    private String code;
    private Operations.ClickHandler keyHandler;
    private CalculatorControllerUI calculatorControllerGUI;

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

        operationPanel.setLayout(new GridLayout(1,4));
        addButtons(operationPanel);
        add(operationPanel, BorderLayout.CENTER);

    }

    /**
     * Adds buttons to operations panel
     * @param p  the operation panel
     */
    private void addButtons(JPanel p) {
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

    }




    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals(CLR_STR)) {
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
