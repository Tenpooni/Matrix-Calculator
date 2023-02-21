package ui;

import model.Log;
import model.Column;
import model.Row;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Matrix {

    private int columnCount;
    private int rowCount;
    private final Scanner input = new Scanner(System.in);
    Log log = new Log();
    Column matrix = new Column(rowCount);
    boolean runCalc = true;

    public Matrix() {
        runUserInterface();
    }

    private void runUserInterface() {
        initiateRows();
        initiateColumns();
        System.out.println("You entered a matrix with " + rowCount + " rows and " + columnCount + " columns");
        setUpMatrix();

        runOperationMenu();
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
                    verifyIndex(command);
                    break;
                case "o":
                    runSupplementMenu();
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    private void verifyIndex(String command) {
        int r1 = verifyRowSelection() - 1;
        int r2 = -1;
        if (!Objects.equals(command, "m")) {
            r2 = verifyRowSelection() - 1;
        }

        while (r2 == r1) {
            r2 = verifyRowSelection() - 1;
        }

        runOperations(command, r1, r2);
    }

    private void runOperations(String command, int r1, int r2) {
        switch (command) {
            case "s":
                this.matrix.swapRow(r1,r2);
                this.log.entryArithmetic(r1, r2, "swap");
                break;
            case "+":
                this.matrix.addRow(r1,r2);
                this.log.entryArithmetic(r1, r2, "+");
                break;
            case "-":
                this.matrix.subtractRow(r1,r2);
                this.log.entryArithmetic(r1, r2, "-");
                break;
            case "m":
                float c = verifyConstant();
                this.matrix.multiplyRow(r1,c);
                this.log.entryMultiplicative(r1,c);
                break;
        }
    }

    private void runSupplementMenu() {
        String command = "";

        while (!(command.equals("o") || command.equals("h"))) {
            displaySupplementMenu();
            command = input.next();
        }

        if (command.equals("o")) {
            runOperationMenu();
        } else {
            System.out.println("Operation history: ");
            printHistory();
            runSupplementMenu();
        }
    }

    private void printHistory() {
        ArrayList<String> lines = this.log.result();
        for (String str : lines) {
            System.out.println(str);
        }
    }

    private int verifyRowSelection() {
        boolean isInt = false;
        int selected = -1;
        String selection;

        while (!isInt || selected > columnCount || selected < 0) {
            System.out.println("Enter row:");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }
        return selected;
    }

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

    private void displayOperationMenu() {
        printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\ts -> swap");
        System.out.println("\t+ -> add");
        System.out.println("\t- -> subtract");
        System.out.println("\tm -> scalar multiply");
        System.out.println("\tq -> quit");
    }

    private void displaySupplementMenu() {
        printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\to -> Operations");
        System.out.println("\th -> History");
    }

    private void printMatrix() {
        for (String str : this.matrix.columnPrint()) {
            System.out.println(str);
        }
    }

    private void initiateRows() {
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

    private void initiateColumns() {
        boolean isInt = false;
        String selection;
        int selected = -1;

        while (!isInt || selected < 0) {
            System.out.println("Please enter number of columns");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }
        columnCount = selected;
    }

    private void setUpMatrix() {
        boolean isInt = false;
        String selection = "";
        int tempVal;

        this.matrix = new Column(rowCount);

        for (int i = 0; i < rowCount; i++) {
            Row tempRow = new Row(columnCount);

            for (int j = 0; j < columnCount; j++) {

                while (!isInt) {
                    System.out.println("Enter value of row: " + (i + 1) + " and column: " + (j + 1));
                    isInt = input.hasNextInt();
                    selection = input.next();
                }
                tempVal = Integer.parseInt(selection);
                tempRow.setRow(j, tempVal);
                isInt = false;
            }
            this.matrix.setColumn(i, tempRow);
        }
    }
}

