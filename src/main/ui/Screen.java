package ui;

import java.awt.*;

import javax.swing.*;

//Panel containing supplement menu GUI elements
public class Screen extends JPanel {
    private JLabel mainLabel;
    private JLabel matrixLabel;
    private JLabel imageAsLabel;
    //private String text;
    private CalculatorControllerUI calculatorControllerGUI;
    private ImageIcon swap;
    private ImageIcon add;
    private ImageIcon multiply;
    private ImageIcon subtract;
    private ImageIcon blank;

    //private JPanel operationPanel;

    //Constructor creates screen display area.
    public Screen(CalculatorControllerUI calculatorControllerUI) {
        calculatorControllerGUI = calculatorControllerUI;
        //text = "";
        setLayout(new BorderLayout());
        JPanel operationPanel = new JPanel();

        operationPanel.setLayout(new GridLayout(1,2));

        mainLabel = new JLabel("Matrix Calculator");
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(mainLabel, CENTER_ALIGNMENT);
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.NORTH);

        add(operationPanel, BorderLayout.CENTER);

        loadImages();

        addScreen(operationPanel);

        imageAsLabel = new JLabel(blank);
        operationPanel.add(imageAsLabel);

    }

    //MODIFIES: this
    //EFFECTS: adds screen panel
    private void addScreen(JPanel p) {
        matrixLabel = new JLabel("Please load calculator");
        p.add(matrixLabel);
    }

    //MODIFIES: imageAsLabel
    //EFFECTS: changes icon to addition
    public void addIcon() {
        imageAsLabel.setIcon(add);
    }

    //MODIFIES: imageAsLabel
    //EFFECTS: changes icon to subtraction
    public void subtractIcon() {
        imageAsLabel.setIcon(subtract);
    }

    //MODIFIES: imageAsLabel
    //EFFECTS: changes icon to multiplication
    public void multiplyIcon() {
        imageAsLabel.setIcon(multiply);
    }

    //MODIFIES: imageAsLabel
    //EFFECTS: changes icon to swap
    public void swapIcon() {
        imageAsLabel.setIcon(swap);
    }

    //MODIFIES: imageAsLabel
    //EFFECTS: changes icon to blank
    public void blankIcon() {
        imageAsLabel.setIcon(blank);
    }

    //EFFECTS: loads images
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        swap = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "swap.jpg");
        multiply = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "multiply.jpg");
        subtract = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "subtract.jpg");
        add = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "add.jpg");
        blank = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "blank.jpg");

    }


    //MODIFIES: this
    //EFFECTS: updates label with given string
    public void refreshLabel(String str) {
        matrixLabel.setText(str);
        matrixLabel.repaint();
    }

}


