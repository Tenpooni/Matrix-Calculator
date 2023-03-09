package persistence;

import model.Row;
import model.Column;
import model.Log;
import model.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    private Column columnTest;
    private Log logTest;
    private Matrix matrixTest;
    private final Row row0 = new Row(1);
    private final Row row1 = new Row(1);
    private final Row row2 = new Row(1);
    private final float TEST0 = 3;
    private final float TEST1 = 0;
    private final float TEST2 = -1;
    private final String stringTest = "R0 swap R1";

    @BeforeEach
    void runBefore() {
        columnTest = new Column(3);
        logTest = new Log();
        matrixTest = new Matrix();

        logTest.setLogLine(0, stringTest);

        row0.setRow(0,TEST0);
        row1.setRow(0,TEST1);
        row2.setRow(0,TEST2);
        columnTest.setColumn(0, row0);
        columnTest.setColumn(1, row1);
        columnTest.setColumn(2, row2);

        matrixTest.setMatrix(columnTest);
        matrixTest.setLog(logTest);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Column col = new Column(0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMatrix() {
        try {
            Column emptyMatrix = new Column(0);
            Row emptyRow = new Row(0);
            Log emptyLog = new Log();
            emptyLog.setLogLine(0,"");
            matrixTest.setMatrix(emptyMatrix);
            matrixTest.setLog(emptyLog);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMatrix.json");
            writer.open();
            writer.write(matrixTest);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMatrix.json");
            emptyMatrix = reader.readMatrix();
            emptyLog = reader.readLog();

            assertEquals(emptyMatrix.getColumnSize(), 0);
            assertEquals(emptyLog.getLogLine(0), "");



        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMatrix() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMatrix.json");

            writer.open();
            writer.write(matrixTest);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMatrix.json");
            Column matrixTestRead = reader.readMatrix();

            Row checkRow0 = matrixTestRead.getColumn(0);
            assertEquals(checkRow0.getValue(0), row0.getValue(0));
            Row checkRow1 = matrixTestRead.getColumn(1);
            assertEquals(checkRow1.getValue(0), row1.getValue(0));
            Row checkRow2 = matrixTestRead.getColumn(2);
            assertEquals(checkRow2.getValue(0), row2.getValue(0));

            Log checkLog = matrixTest.getLog();
            ArrayList<String> testHasString = new ArrayList<>();
            testHasString.add(stringTest);
            assertEquals(checkLog.result(), testHasString);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}