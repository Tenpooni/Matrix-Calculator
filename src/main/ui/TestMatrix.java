package ui;

import model.RowOperations;

import java.util.Scanner;

public class TestMatrix {

    private boolean isRows = false;
    private int columnCount;
    private int rowCount;
    private final Scanner input = new Scanner(System.in);
    RowOperations row = new RowOperations();
    boolean runCalc = true;

    public TestMatrix() {
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
                    row.processCommand(command);
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

    private void runSupplementMenu() {
        String command = "";

        while (!(command.equals("o") || command.equals("h"))) {
            displaySupplementMenu();
            command = input.next();
        }

        if (command.equals("o")) {
            runOperationMenu();
        } else {
            row.printHistory();
            runSupplementMenu();
        }

    }

    //EFFECTS: Display Supplementary Menu
    private void displaySupplementMenu() {
        //row.printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\to -> Operations");
        System.out.println("\th -> History");
    }

    //EFFECTS: Display Operations Menu
    private void displayOperationMenu() {
        row.printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\ts -> swap");
        System.out.println("\t+ -> add");
        System.out.println("\t- -> subtract");
        System.out.println("\tm -> scalar multiply");
        System.out.println("\to -> other menu items");
        System.out.println("\tq -> quit");
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

        while (!isInt || selected < 0) {
            System.out.println("Please enter number of columns");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }
        columnCount = selected;
        row.setColumn(columnCount);
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
            row.makeMatrix(rowCount,columnCount);
        }
    }

}
