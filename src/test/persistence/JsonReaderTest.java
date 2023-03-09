package persistence;

import model.Row;
import model.Column;
import model.Log;
import model.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    private Column column;
    private Matrix matrix;
    private Log log;
    private final Row row0 = new Row(1);
    private final Row row1 = new Row(1);
    private final Row row2 = new Row(1);
    private final float TEST0 = 3;
    private final float TEST1 = 0;
    private final float TEST2 = -1;
    private final String stringTest = "R0 swap R1";

    @BeforeEach
    void runBefore() {
        column = new Column(3);
        log = new Log();

        row0.setRow(0,TEST0);
        row1.setRow(0,TEST1);
        row2.setRow(0,TEST2);
        column.setColumn(0, row0);
        column.setColumn(1, row1);
        column.setColumn(2, row2);
        log.setLogLine(0,stringTest);

        matrix = new Matrix();
        matrix.setMatrix(column);
        matrix.setLog(log);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Column nonExistentMatrix = reader.readMatrix();
            Log nonExistentLog = reader.readLog();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMatrix() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMatrix.json");
        try {
            Column column = reader.readMatrix();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMatrix.json");
        try {
            Log log = reader.readLog();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCalculator() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMatrix.json");
        try {
            Column matrixTest = reader.readMatrix();
            assertEquals(matrixTest.getColumn(0).getValue(0), column.getColumn(0).getValue(0));
            assertEquals(matrixTest.getColumn(1).getValue(0), column.getColumn(1).getValue(0));
            assertEquals(matrixTest.getColumn(2).getValue(0), column.getColumn(2).getValue(0));
            Log logTest = reader.readLog();
            assertEquals(logTest.getLogLine(0), stringTest);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
