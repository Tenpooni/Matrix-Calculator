package ui;

import model.Row;
import model.Matrix;
import model.Log;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//Matrix Calculator App
public class Calculator implements Writable {

    private int columnCount;
    private int rowCount;
    private final Scanner input = new Scanner(System.in);
    Matrix matrix = new Matrix();

    boolean runCalc = true;

    private static final String JSON_STORE = "./data/matrix.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs Calculator application
    public Calculator() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runUserInterface();
    }

    // MODIFIES: this
    // EFFECTS: initializes matrix setup and runs regular operation
    private void runUserInterface() {
        boolean toLoad = runStartMenu();

        if (!loadMatrix() || !toLoad) {
            initiateRows();
            initiateColumns();
            matrix.clearHistory();
            System.out.println("You entered a matrix with " + rowCount + " rows and " + columnCount + " columns");
            setUpMatrix();
        }

        runOperationMenu();
    }

    //EFFECTS: asks user to load matrix or set up new matrix
    private boolean runStartMenu() {
        String command = "";
        boolean toLoad = false;

        while (!(command.equals("y") || command.equals("n"))) {
            System.out.println("Would you like to load saved matrix? (y/n)");
            command = input.next();
        }

        switch (command) {
            case "y":
                toLoad = true;
                break;
            case "n":

                break;
        }

        return toLoad;
    }

    //EFFECTS: Runs operations menu
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

    //EFFECTS: Runs operation processes in calculator
    private void runOperations(String command, int r1, int r2) {
        switch (command) {
            case "s":
                matrix.swapRow(r1, r2);
                break;
            case "+":
                matrix.addRow(r1, r2);
                break;
            case "-":
                matrix.subtractRow(r1, r2);
                break;
            case "m":
                float c = verifyConstant();
                matrix.multiplyRow(r1, c);
                break;
        }
    }


    //EFFECTS: Runs supplement menu with history log. TA permission given to suppress checkstyle
    @SuppressWarnings("methodlength")
    private void runSupplementMenu() {
        String command = "";

        while (!(command.equals("o") || command.equals("h") || command.equals("r")
                || command.equals("i") || command.equals("s") || command.equals("l"))) {
            displaySupplementMenu();
            command = input.next();
        }

        switch (command) {
            case "o":
                runOperationMenu();
                break;
            case "h":
                System.out.println("Operation history: ");
                printHistory();
                break;
            case "r":
                removeRow();
                break;
            case "i":
                insertRow();
                break;
            case "s":
                saveMatrix();
                break;
            case "l":
                loadMatrix();
                break;
        }
    }


    //EFFECTS: prints matrix and Menu options
    private void displayOperationMenu() {
        printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\ts -> swap");
        System.out.println("\t+ -> add");
        System.out.println("\t- -> subtract");
        System.out.println("\tm -> scalar multiply");
        System.out.println("\to -> other menu options");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: prints matrix and additional menu options
    private void displaySupplementMenu() {
        printMatrix();
        System.out.println("\nSelect from:");
        System.out.println("\ts -> save");
        System.out.println("\tl -> load");
        System.out.println("\ti -> insert row vector");
        System.out.println("\tr -> remove row vector");
        System.out.println("\to -> Operations");
        System.out.println("\th -> History");
    }



    //EFFECTS: Prevents double selection of same row
    private void verifyIndex(String command) {
        int r1 = verifyRowSelection(rowCount) - 1;
        int r2 = -1;
        if (!Objects.equals(command, "m")) {
            r2 = verifyRowSelection(rowCount) - 1;
        }

        while (r2 == r1) {
            r2 = verifyRowSelection(rowCount) - 1;
        }

        runOperations(command, r1, r2);
    }

    //EFFECTS: Ensures selected row choice is valid
    private int verifyRowSelection(int bounds) {
        boolean isInt = false;
        int selected = -1;
        String selection;

        while (!isInt || selected > bounds || selected <= 0) {
            System.out.println("Enter row:");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }
        return selected;
    }

    //EFFECTS: Checks entry for constant is valid
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


    //EFFECTS: Prints visual representation of matrix as one row per line
    private void printMatrix() {
        for (String str : matrix.printMatrix()) {
            System.out.println(str);
        }
    }


    //EFFECTS: Prints out previous calculator actions
    private void printHistory() {
        ArrayList<String> lines = matrix.getResult();
        for (String str : lines) {
            System.out.println(str);
        }
    }

    //MODIFIES: this
    //EFFECTS: checks row is removable before removing row vector
    private void removeRow() {
        if (this.rowCount > 1) {
            int index = verifyRowSelection(rowCount) - 1;
            matrix.removeMatrixRow(index);
            rowCount = matrix.getMatrixColumnSize();
            matrix.enterVector(index, "Removed");
        } else {
            System.out.println("invalid, no rows left...");
        }
    }

    //REQUIRES: integer within 1, rowCount + 1
    //EFFECTS: Verify index for insertRow function
    private void insertRow() {
        int index = verifyRowSelection(rowCount + 1) - 1;
        Row toInsert = makeNewRow();
        matrix.insertMatrixRow(index, toInsert);
        rowCount = matrix.getMatrixColumnSize();
        matrix.enterVector(index, "Added");
    }

    //MODIFIES: this
    //EFFECTS: asks user to set new inserted vector
    private Row makeNewRow() {
        boolean isInt = false;
        String selection = "";
        int tempVal;
        Row tempRow = new Row(columnCount);

        for (int j = 0; j < columnCount; j++) {
            while (!isInt) {
                System.out.println("Enter value of row vector: " + (j + 1));
                isInt = input.hasNextInt();
                selection = input.next();
            }
            tempVal = Integer.parseInt(selection);
            tempRow.setRow(j, tempVal);
            isInt = false;
        }
        return tempRow;
    }


    //MODIFIES: this
    //EFFECTS: Initiates row size based on user input
    private void initiateRows() {
        boolean isInt = false;
        String selection;
        int selected = -1;

        while (!isInt || selected <= 0) {
            System.out.println("Please enter number of rows");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }

        rowCount = selected;
        matrix.setRowCount(selected);
    }

    //MODIFIES: this
    //EFFECTS: Initiates column size based on user input
    private void initiateColumns() {
        boolean isInt = false;
        String selection;
        int selected = -1;

        while (!isInt || selected <= 0) {
            System.out.println("Please enter number of columns");
            isInt = input.hasNextInt();
            selection = input.next();
            if (isInt) {
                selected = Integer.parseInt(selection);
            }
        }
        columnCount = selected;
        matrix.setColumnCount(selected);
    }

    //MODIFIES: this
    //EFFECTS: Creates row and column objects based on arbitrary user input sizes
    private void setUpMatrix() {
        boolean isInt = false;
        String selection = "";
        int tempVal;

        this.matrix.newMatrix(rowCount);

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
            matrix.setColumn(i, tempRow);
        }
    }


    //EFFECTS: write both matrix and history log as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Matrix", matrix.matrixToJson());
        json.put("History", matrix.logToJson());
        return json;
    }

    // EFFECTS: saves matrix to file including history
    private void saveMatrix() {
        try {
            jsonWriter.open();
            jsonWriter.write(matrix);
            jsonWriter.close();
            System.out.println("Saved matrix to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads matrix from file
    private boolean loadMatrix() {
        try {
            matrix.setMatrix(jsonReader.readMatrix());
            matrix.setLog(jsonReader.readLog());

            this.rowCount = matrix.getMatrixColumnSize();
            this.columnCount = matrix.getMatrixRowSize();
            return true;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return false;
        }
    }


}

