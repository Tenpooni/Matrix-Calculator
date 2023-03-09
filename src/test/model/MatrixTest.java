package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    private Column columnTest;
    private Matrix matrixTest;
    private Row row0 = new Row(1);
    private Row row1 = new Row(1);
    private Row row2 = new Row(1);
    private final float TEST0 = 3;
    private final float TEST1 = 0;
    private final float TEST2 = -1;
    private final String STR0 = "|" + TEST0;
    private final String STR1 = "|" + TEST1;
    private final String STR2 = "|" + TEST2;

    @BeforeEach
    void runBefore() {
        columnTest = new Column(3);
        matrixTest = new Matrix();
        row0.setRow(0,TEST0);
        row1.setRow(0,TEST1);
        row2.setRow(0,TEST2);
        columnTest.setColumn(0, row0);
        columnTest.setColumn(1, row1);
        columnTest.setColumn(2, row2);
        matrixTest.setMatrix(columnTest);
    }

    @Test
    void testGetMatrixRowSize() {
        int checkRow = matrixTest.getMatrixRowSize();
        assertEquals(checkRow, 1);
    }

    @Test
    void testGetMatrixColumnSize() {
        int checkColumn = matrixTest.getMatrixColumnSize();
        assertEquals(checkColumn, 3);
    }

    @Test
    void testSetColumn() {
        matrixTest.setColumn(0, row1);
        Row checkRow = matrixTest.column.getColumn(0);
        assertEquals(checkRow, row1);
    }

    @Test
    void testSetMatrix() {
        matrixTest.setMatrix(columnTest);
        Row checkRow = matrixTest.column.getColumn(0);
        assertEquals(checkRow, row0);
    }

    @Test
    void testNewMatrix() {
        matrixTest.newMatrix(0);
        assertEquals(matrixTest.column.getColumnSize(),0);
    }

    @Test
    void testInsertMatrixRow() {
        matrixTest.insertMatrixRow(2,row2);
        Row checkRow2 = matrixTest.column.getColumn(1);
        Row checkRow3 = matrixTest.column.getColumn(2);
        Row checkRow4 = matrixTest.column.getColumn(3);
        assertEquals(checkRow2, row1);
        assertEquals(checkRow3, row2);
        assertEquals(checkRow4, row2);
    }

    @Test
    void testRemoveMatrixRow() {
        matrixTest.removeMatrixRow(2);
        Row checkRow1 = matrixTest.column.getColumn(0);
        Row checkRow2 = matrixTest.column.getColumn(1);
        assertEquals(checkRow1, row0);
        assertEquals(checkRow2, row1);
    }

}
