package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LogTest {
    private Log testLog;
    private final int r1 = 5;
    private final int r2 = 2;
    private final float c = -8;
    private final String operation1 = "+";
    private final String line1 =  "R" + r1 + " " + operation1 + " " + "R" + r2;
    private final String line2 =  "R" + r1 + " * " + c;

    @BeforeEach
    void runBefore() {
        testLog = new Log();
    }

    @Test
    void testEntryArithmetic() {
        testLog.entryArithmetic(r1, r2, operation1);
        String testString = testLog.getLogList(0);
        assertEquals(testString, line1);
    }

    @Test
    void testEntryMultiplicative() {
        testLog.entryMultiplicative(r1, c);
        String testString = testLog.getLogList(0);
        assertEquals(testString, line2);
    }

    @Test
    void testResult() {
        ArrayList<String> testEmpty = testLog.result();
        assertEquals(testEmpty.get(0), "No operations yet");
        testLog.entryMultiplicative(r1, c);
        String testString = testLog.getLogList(0);
        assertEquals(testEmpty.get(0), testString);
    }

    @Test
    void getLogList() {
        testLog.entryMultiplicative(r1, c);
        String testString = testLog.getLogList(0);
        assertEquals(testLog.getLogList(0), testString);
    }
}
