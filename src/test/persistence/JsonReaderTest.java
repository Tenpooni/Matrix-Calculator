package persistence;

import model.Row;
import model.Column;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    private Column matrixReference;
    private final Row row0 = new Row(1);
    private final Row row1 = new Row(1);
    private final Row row2 = new Row(1);
    private final float TEST0 = 3;
    private final float TEST1 = 0;
    private final float TEST2 = -1;

    @BeforeEach
    void runBefore() {
        matrixReference = new Column(3);
        row0.setRow(0,TEST0);
        row1.setRow(0,TEST1);
        row2.setRow(0,TEST2);
        matrixReference.setColumn(0, row0);
        matrixReference.setColumn(1, row1);
        matrixReference.setColumn(2, row2);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Column nonExistentMatrix = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMatrix.json");
        try {
            Column matrix = reader.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMatrix.json");
        try {
            Column matrixTest = reader.read();
            assertEquals(matrixTest.getColumn(0).getValue(0), matrixReference.getColumn(0).getValue(0));
            assertEquals(matrixTest.getColumn(1).getValue(0), matrixReference.getColumn(1).getValue(0));
            assertEquals(matrixTest.getColumn(2).getValue(0), matrixReference.getColumn(2).getValue(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
