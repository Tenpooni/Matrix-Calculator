package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LogTest {
    private Log testLog;
    private final int r1 = 5;
    private final int r2 = 2;
    private final float c = -8;
    private final String operation = "+";
    private final String line1 =  "R" + r1 + " " + operation + " " + "R" + r2;
    private final String line2 =  "R" + r1 + " * " + c;

    @BeforeEach
    void runBefore() {
        testLog = new Log();
    }

    @Test
    void testEntryArithmetic() {
        testLog.entryArithmetic(r1, r2, operation);
        String testString = testLog.getLogLine(0);
        assertEquals(testString, line1);
        assertTrue(testLog.getLogList().contains(line1));
    }

    @Test
    void testEntryMultiplicative() {
        testLog.entryMultiplicative(r1, c);
        String testString = testLog.getLogLine(0);
        assertEquals(testString, line2);
        assertTrue(testLog.getLogList().contains(line2));
    }

    @Test
    void testCheckEmptyLog() {
        assertTrue(testLog.getLogList().isEmpty());
        testLog.checkEmptyLog();
        assertTrue(testLog.getLogList().contains("No operations yet"));

        testLog.entryMultiplicative(r1, c);
        testLog.checkEmptyLog();
        assertFalse(testLog.getLogList().isEmpty());
        assertFalse(testLog.result().contains("No operations yet"));
    }

    @Test
    void testResult() {
        assertTrue(testLog.getLogList().isEmpty());
        ArrayList<String> resultEmpty = testLog.result();
        assertTrue(resultEmpty.contains("No operations yet"));

        testLog.entryMultiplicative(r1, c);
        assertFalse(testLog.getLogList().isEmpty());
        ArrayList<String> result = testLog.result();
        assertFalse(result.contains("No operations yet"));
        assertTrue(result.contains(line2));
    }

    @Test
    void getLogLine() {
        testLog.entryMultiplicative(r1, c);
        String testString = testLog.getLogLine(0);
        assertEquals(testLog.getLogLine(0), testString);
    }

    @Test
    void testToJson() {
        JSONObject json = new JSONObject();
        json = testLog.toJson();
        assertEquals(json.getJSONArray("History").toString(), "[\"No operations yet\"]");
    }
}
