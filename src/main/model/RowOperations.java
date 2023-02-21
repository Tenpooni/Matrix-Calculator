package model;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class RowOperations extends Operation {
    private int columns;
    private ArrayList<OldRow> listOfRows;
    private final Scanner input = new Scanner(System.in);
    Log log = new Log();

    @Override
    public void processCommand(String command) {
        int r1 = verifySelection() - 1;
        int r2 = -1;
        if (!Objects.equals(command, "m")) {
            r2 = verifySelection() - 1;
        }

        while (r2 == r1) {
            r2 = verifySelection() - 1;
        }

        runOptions(command, r1, r2);
    }

    @Override
    void runOptions(String command, int r1, int r2) {
        switch (command) {
            case "s":
                swap(r1,r2);
                break;
            case "+":
                add(r1,r2);
                break;
            case "-":
                subtract(r1,r2);
                break;
            case "m":
                float c = verifyConstant();
                multiply(r1,c);
                break;
        }
    }

    @Override
    int verifySelection() {
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
    @Override
    void swap(int r1, int r2) {
        OldRow temp = this.listOfRows.get(r1);
        this.listOfRows.set(r1,this.listOfRows.get(r2));
        this.listOfRows.set(r2,temp);
        log.entryArithmetic(r1, r2, "swap");
    }

    //REQUIRES: -1 < int < listOfRows.size()
    //EFFECTS: Add row objects based on index, replaces second index
    @Override
    void add(int r1, int r2) {
        OldRow toAdd = this.listOfRows.get(r1);
        OldRow toReplace = this.listOfRows.get(r2);

        for (int i = 0; i < columns; i++) {
            float c = toAdd.getValue(i);
            toReplace.add(i, c);
        }
        log.entryArithmetic(r1, r2, "+");
    }

    //REQUIRES: -1 < int < listOfRows.size()
    //EFFECTS: Subtract row objects based on index, replaces second index
    @Override
    void subtract(int r1, int r2) {
        OldRow toAdd = this.listOfRows.get(r1);
        OldRow toReplace = this.listOfRows.get(r2);

        for (int i = 0; i < columns; i++) {
            float c = toAdd.getValue(i);
            toReplace.subtract(i, c);
        }
        log.entryArithmetic(r1, r2, "-");
    }

    //REQUIRES: -1 < int < listOfRows.size()
    //EFFECTS: Multiplies row and constant, replaces given index
    @Override
    void multiply(int r1, float c) {
        OldRow toMultiply = this.listOfRows.get(r1);
        toMultiply.multiply(c);
        log.entryMultiplicative(r1, c);
    }

    //Display matrix
    @Override
    public void printMatrix() {
        for (OldRow row : listOfRows) {
            row.printElements();
        }
    }

    @Override
    public void makeMatrix(int rows, int columns) {
        boolean isInt = false;
        String selection = "";
        int tempVal;

        listOfRows = new ArrayList<>(columns);

        for (int i = 0; i < rows; i++) {
            OldRow tempRow = new OldRow(columns);

            for (int j = 0; j < columns; j++) {
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

    public void setColumn(int r) {
        this.columns = r;
    }

    public void printHistory() {
        log.result();
    }

}
