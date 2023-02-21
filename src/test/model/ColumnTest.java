package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ColumnTest {
    private Column columnTest;
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
        row0.setRow(0,TEST0);
        row1.setRow(0,TEST1);
        row2.setRow(0,TEST2);
        columnTest.setColumn(0, row0);
        columnTest.setColumn(1, row1);
        columnTest.setColumn(2, row2);
    }

    @Test
    void testColumnPrint() {
        ArrayList<String> lineToPrint = columnTest.columnPrint();
        ArrayList<String> testString = new ArrayList<>();
        testString.add(STR0);
        testString.add(STR1);
        testString.add(STR2);
        assertTrue(lineToPrint.equals(testString));
    }

    @Test
    void testSwapRow() {
        columnTest.swapRow(0,1);
        assertEquals(columnTest.getColumn(0),row1);
        assertEquals(columnTest.getColumn(1),row0);
    }

    @Test
    void testAddRow() {
        columnTest.addRow(0, 1);
        Row r0 = columnTest.getColumn(1);
        float r0Val = r0.getValue(0);
        Row r1 = columnTest.getColumn(1);
        float r1Val = r1.getValue(0);
        assertEquals(r1Val, TEST0 + TEST1);
    }

    @Test
    void testSubtractRow() {
        columnTest.subtractRow(1,2);
        Row r1 = columnTest.getColumn(1);
        float r1Val = r1.getValue(0);
        Row r2 = columnTest.getColumn(2);
        float r2Val = r2.getValue(0);
        assertEquals(r2Val, TEST1 - TEST2);
    }

    @Test
    void testMultiplyRow() {
        columnTest.multiplyRow(0, TEST0);
        Row row0Compare = columnTest.getColumn(0);
        float compareVal = row0Compare.getValue(0);
        assertEquals(compareVal, TEST0 * TEST0);
    }

    @Test
    void testSetColumn() {
        columnTest.setColumn(0,row1);
        Row checkRow = columnTest.getColumn(0);
        assertEquals(checkRow, row1);
    }

    @Test
    void testGetRow() {
        Row checkRow = columnTest.getColumn(2);
        assertEquals(checkRow, row2);
    }
}
