package ui;

import model.OldColumn;
import model.Log;
import model.OldColumn;
import model.OldRow;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class OldMatrix {

    private boolean isRows = false;
    private int columnCount;
    private int rowCount;
    private ArrayList<OldRow> listOfRows;
    private ArrayList<OldColumn> listOfColumns;
    private final Scanner input = new Scanner(System.in);
    Log printHistory = new Log();
    boolean runCalc = true;


    public OldMatrix() {
        runUserInterface();
    }

    private void runUserInterface() {
        initRows();
        initColumns();
        System.out.println("You entered a matrix with " + rowCount + " rows and " + columnCount + " columns");
        setUp();

        runOperationMenu();

        System.out.println("\nGoodbye!");
    }

    private void runOperationMenu() {
        String command = "";

        while (runCalc) {
            displayOperationMenu();
            command = input.next();

            switch (command) {
                case "q":
                    runCalc = false;
                    break;
                case "s":
                case "+":
                case "-":
                case "m":
                    processCommand(command);
                    break;
                case "o":
                    runOtherMenu();
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    private void runOtherMenu() {
        String command = "";

        while (!(command.equals("o") || command.equals("h"))) {
            displayOtherMenu();
            command = input.next();
        }

        if (command.equals("o")) {
            runOperationMenu();
        } else {
            printHistory.result();
            runOtherMenu();
        }

    }

    private void displayOtherMenu() {
        printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\to -> Operations");
        System.out.println("\th -> History");
    }

    //EFFECTS: Display Operations Menu
    private void displayOperationMenu() {
        printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\ts -> swap");
        System.out.println("\t+ -> add");
        System.out.println("\t- -> subtract");
        System.out.println("\tm -> scalar multiply");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        int r1 = verifyRowSelection() - 1;
        int r2 = -1;
        if (!Objects.equals(command, "m")) {
            r2 = verifyRowSelection() - 1;
        }

        while (r2 == r1) {
            r2 = verifyRowSelection() - 1;
        }

        runOptions(command, r1, r2);
    }

    private void runOptions(String command, int r1, int r2) {
        switch (command) {
            case "s":
                swapRow(r1,r2);
                break;
            case "+":
                addRow(r1,r2);
                break;
            case "-":
                subtractRow(r1,r2);
                break;
            case "m":
                float c = verifyConstant();
                multiplyRow(r1,c);
                break;
        }
    }

    //REQUIRES: None
    //EFFECTS: Ask for input again if non-float input
    private float verifyConstant() {
        boolean isFloat = false;
        float selected = 0;
        String selection;

        while (!isFloat) {
            System.out.println("Enter scalar multiple: ");
            isFloat = input.hasNextFloat();
            selection = input.next();
            if (isFloat) {
                selected = Float.parseFloat(selection);
            }
        }
        return selected;
    }

    //REQUIRES: None
    //EFFECTS: Ask for input again if input > row size or input < 0
    private int verifyRowSelection() {
        boolean isInt = false;
        int selected = 0;
        String selection;

        while (!isInt || selected > listOfRows.size() || selected < 0) {
            System.out.println("Enter row:");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }
        return selected;
    }

    //REQUIRES: -1 < int < listOfRows.size()
    //EFFECTS: Swap row objects based on index
    private void swapRow(int r1, int r2) {
        OldRow temp = this.listOfRows.get(r1);
        this.listOfRows.set(r1,this.listOfRows.get(r2));
        this.listOfRows.set(r2,temp);
        printHistory.entryArithmetic(r1, r2, "swap");
    }

    //REQUIRES: -1 < int < listOfRows.size()
    //EFFECTS: Add row objects based on index, replaces second index
    private void addRow(int r1, int r2) {
        OldRow toAdd = this.listOfRows.get(r1);
        OldRow toReplace = this.listOfRows.get(r2);

        for (int i = 0; i < columnCount; i++) {
            float c = toAdd.getValue(i);
            toReplace.add(i, c);
        }

        printHistory.entryArithmetic(r1, r2, "+");
    }

    //REQUIRES: -1 < int < listOfRows.size()
    //EFFECTS: Subtract row objects based on index, replaces second index
    private void subtractRow(int r1, int r2) {
        OldRow toAdd = this.listOfRows.get(r1);
        OldRow toReplace = this.listOfRows.get(r2);

        for (int i = 0; i < columnCount; i++) {
            float c = toAdd.getValue(i);
            toReplace.subtract(i, c);
        }
        printHistory.entryArithmetic(r1, r2, "-");
    }

    //REQUIRES: -1 < int < listOfRows.size()
    //EFFECTS: Multiplies row and constant, replaces given index
    private void multiplyRow(int r1, float c) {
        OldRow toMultiply = this.listOfRows.get(r1);
        toMultiply.multiply(c);
        printHistory.entryMultiplicative(r1, c);
    }



    //EFFECTS: Sets up size of matrix rows
    private void initRows() {
        boolean isInt = false;
        String selection;
        int selected = -1;

        while (!isInt || selected < 0) {
            System.out.println("Please enter number of rows");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }

        rowCount = selected;
    }

    //EFFECTS: Sets up size of matrix columns
    private void initColumns() {
        boolean isInt = false;
        String selection;
        int selected = -1;

        while (!isInt) {
            System.out.println("Please enter number of columns");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }
        columnCount = selected;
    }

    //EFFECTS: Create matrix objects
    private void setUp() {
        String selection = "";

        while (!selection.equals("r") && !selection.equals("c")) {
            System.out.println("Select how you would like to set up your calculator");
            System.out.println("\tr -> row operations");
            System.out.println("\tc -> column operations");
            selection = input.next();
        }

        if (selection.equals("c")) {
            isRows = false;
        } else if (selection.equals("r")) {
            isRows = true;
            makeMatrixRows();
        }
    }

    //Display matrix
    private void printMatrix() {
        if (isRows) {
            for (OldRow row : listOfRows) {
                row.printElements();
            }
        } else {
            for (OldColumn column : listOfColumns) {
                column.printElements();
            }
        }
    }

    private void makeMatrixRows() {
        boolean isInt = false;
        String selection = "";
        int tempVal;

        listOfRows = new ArrayList<>(columnCount);

        for (int i = 0; i < rowCount; i++) {
            OldRow tempRow = new OldRow(columnCount);

            for (int j = 0; j < columnCount; j++) {
                while (!isInt) {
                    System.out.println("Enter value of row: " + (i + 1) + " and column: " + (j + 1));
                    isInt = input.hasNextInt();
                    selection = input.next();
                }
                tempVal = Integer.parseInt(selection);
                tempRow.initializeElement(j, tempVal);
                isInt = false;
            }
            listOfRows.add(i,tempRow);
        }
    }
}
