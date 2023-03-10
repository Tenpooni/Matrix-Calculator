package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    private Column columnTest;
    private Matrix matrixTest;
    private Log logTest;
    private final Row row0 = new Row(1);
    private final Row row1 = new Row(1);
    private final Row row2 = new Row(1);
    private final float TEST0 = 3;
    private final float TEST1 = 0;
    private final float TEST2 = -1;
    private final String STR0 = "|" + TEST0;
    private final String STR1 = "|" + TEST1;
    private final String STR2 = "|" + TEST2;
    private final String stringTest = "R0 swap R1";

    @BeforeEach
    void runBefore() {
        columnTest = new Column(3);
        matrixTest = new Matrix();
        logTest = new Log();
        row0.setRow(0,TEST0);
        row1.setRow(0,TEST1);
        row2.setRow(0,TEST2);
        columnTest.setColumn(0, row0);
        columnTest.setColumn(1, row1);
        columnTest.setColumn(2, row2);
        logTest.setLogLine(0, stringTest);
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
    void testGetColumnCount() {
        matrixTest.setColumnCount(6);
        int check = matrixTest.getColumnCount();
        assertEquals(check,6);
    }

    @Test
    void testGetRowCount() {
        matrixTest.setRowCount(6);
        int check = matrixTest.getRowCount();
        assertEquals(check,6);
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

    @Test
    void testGetResult() {
        ArrayList<String> testing = matrixTest.getResult();
        assertEquals(testing, matrixTest.log.result());
    }

    @Test
    void testPrintMatrix() {
        ArrayList<String> testing = matrixTest.printMatrix();
        ArrayList<String> testString = new ArrayList<>();
        testString.add(STR0);
        testString.add(STR1);
        testString.add(STR2);
        assertEquals(testing, testString);
    }

    @Test
    void testClearHistory() {
        matrixTest.clearHistory();
        ArrayList<String> test = new ArrayList<>();
        test.add("No operations yet");
        assertEquals(matrixTest.log.result(),test);
    }

    @Test
    void testMatrixSwapRow() {
        matrixTest.swapRow(0,1);
        assertEquals(columnTest.getColumn(0),row1);
        assertEquals(columnTest.getColumn(1),row0);
        assertEquals(matrixTest.log.getLogLine(0), "R0 swap R1");
    }

    @Test
    void testAddRow() {
        matrixTest.addRow(0, 1);
        Row r0 = columnTest.getColumn(1);
        float r0Val = r0.getValue(0);
        Row r1 = columnTest.getColumn(1);
        float r1Val = r1.getValue(0);
        assertEquals(r1Val, TEST0 + TEST1);
        assertEquals(matrixTest.log.getLogLine(0), "R0 + R1");
    }

    @Test
    void testSubtractRow() {
        matrixTest.subtractRow(1,2);
        Row r1 = columnTest.getColumn(1);
        float r1Val = r1.getValue(0);
        Row r2 = columnTest.getColumn(2);
        float r2Val = r2.getValue(0);
        assertEquals(r2Val, TEST1 - TEST2);
        assertEquals(matrixTest.log.getLogLine(0), "R1 - R2");
    }

    @Test
    void testMultiplyRow() {
        matrixTest.multiplyRow(0, TEST0);
        Row row0Compare = columnTest.getColumn(0);
        float compareVal = row0Compare.getValue(0);
        assertEquals(compareVal, TEST0 * TEST0);
        assertEquals(matrixTest.log.getLogLine(0), "R0 * " + TEST0);
    }

    @Test
    void testEnterVector() {
        matrixTest.enterVector(0,"Remove");
        assertEquals(matrixTest.log.getLogLine(0), "Remove row vector at R" + 1);
    }

    @Test
    void testMatrixToJson() {
        JSONArray json = new JSONArray();
        json = matrixTest.matrixToJson();
        assertEquals(json.get(0).toString(), "[3]");
        assertEquals(json.get(1).toString(), "[0]");
        assertEquals(json.get(2).toString(), "[-1]");
    }

    @Test
    void testLogToJson() {
        JSONArray json = new JSONArray();
        matrixTest.setLog(logTest);
        json = matrixTest.logToJson();
        assertEquals(json.get(0).toString(), "R0 swap R1");
    }

    @Test
    void testToJson() {
        JSONObject json = new JSONObject();
        json = matrixTest.toJson();
        assertEquals(json.getJSONArray("Matrix").toString(), "[[3],[0],[-1]]");
        assertEquals(json.getJSONArray("History").toString(), "[]");
    }

}
