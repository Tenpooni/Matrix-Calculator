package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    private Row testRow;
    private final int COLUMNCOUNT = 3;
    private final int POSITIVECONSTANT = 3;
    private final int NEGATIVECONSTANT = -3;
    private final float TEST0 = 3;
    private final float TEST1 = 0;
    private final float TEST2 = -1;

    @BeforeEach
    void runBefore() {
        testRow = new Row(COLUMNCOUNT);
        testRow.setRow(0, TEST0);
        testRow.setRow(1, TEST1);
        testRow.setRow(2, TEST2);
    }

    @Test
    void testRowPrint() {
        String test = "|" + TEST0 + "|" + TEST1 + "|" + TEST2;
        assertEquals(testRow.rowPrint(),test);
    }

    @Test
    void testMultiply() {
        testRow.multiply(POSITIVECONSTANT);
        assertEquals(testRow.getValue(0), TEST0 * POSITIVECONSTANT);
        assertEquals(testRow.getValue(1), TEST1 * POSITIVECONSTANT);
        assertEquals(testRow.getValue(2), TEST2 * POSITIVECONSTANT);
    }

    @Test
    void testAddition() {
        testRow.addition(0, POSITIVECONSTANT);
        assertEquals(testRow.getValue(0), TEST0 + POSITIVECONSTANT);
        testRow.addition(1, NEGATIVECONSTANT);
        assertEquals(testRow.getValue(1), TEST1 + NEGATIVECONSTANT);
    }

    @Test
    void testSubtraction() {
        testRow.subtract(2, NEGATIVECONSTANT);
        assertEquals(testRow.getValue(2), NEGATIVECONSTANT - TEST2);
        testRow.subtract(1, POSITIVECONSTANT);
        assertEquals(testRow.getValue(1), POSITIVECONSTANT - TEST1);
    }

    @Test
    void testSetRow() {
        testRow.setRow(0,POSITIVECONSTANT);
        assertEquals(testRow.getValue(0), POSITIVECONSTANT);
        testRow.setRow(2,NEGATIVECONSTANT);
        assertEquals(testRow.getValue(2), NEGATIVECONSTANT);
    }

    @Test
    void testGetValue() {
        assertEquals(testRow.getValue(0), TEST0);
        assertEquals(testRow.getValue(1), TEST1);
        assertEquals(testRow.getValue(2), TEST2);
    }

    @Test
    void testGetRowLength() {
        assertEquals(testRow.getColumnCount(), COLUMNCOUNT);
    }

}