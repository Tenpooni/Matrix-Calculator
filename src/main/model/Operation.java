package model;

import java.util.Objects;
import java.util.Scanner;

public abstract class Operation {
    private final Scanner input = new Scanner(System.in);
    Log log = new Log();


    float verifyConstant() {
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




    abstract void processCommand(String command);

    abstract void runOptions(String command, int i, int j);

    abstract int verifySelection();

    abstract void swap(int i, int j);

    abstract void add(int i, int j);

    abstract void subtract(int i, int j);

    abstract void multiply(int i, float c);

    abstract void printMatrix();

    abstract void makeMatrix(int rows, int columns);

}
