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


public class Keypad extends JPanel implements KeyListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private JLabel label;
    private String code;
    private ClickHandler keyHandler;
    private Main mainGUI;

    /**
     * Constructor creates keypad and code display area.
     */
    public Keypad(Main main) {
        mainGUI = main;
        code = "";
        keyHandler = new ClickHandler();
        setLayout(new BorderLayout());
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(3,3));
        addButtons(keyPanel);
        add(keyPanel, BorderLayout.CENTER);
        //label = new JLabel(getLabel());
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        //hbox.add(label);
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.SOUTH);
    }


    /**
     * Adds buttons to button panel
     * @param p  the button panel
     */
    private void addButtons(JPanel p) {
        keys = new JButton[12];

        for (int i = 0; i < 9; i++) {
            keys[i] = new JButton(Integer.toString(i + 1));
            keys[i].addActionListener(keyHandler);
            p.add(keys[i]);
        }
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

            //label.setText(getLabel());
            label.repaint();
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


