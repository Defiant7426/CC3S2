package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

public class LCOMCalculatorTest {

    @Test
    public void testHighCohesion() {
        LCOMCalculator.ClassInfo classInfo = new LCOMCalculator.ClassInfo();
        classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1", "attr2")));// p = 0, q = 1
        classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr1", "attr2")));
        int lcom = LCOMCalculator.calculateLCOM(classInfo);
        assertEquals(0, lcom, "LCOM deberia de ser 0");
    }

    @Test
    public void testLowCohesion() {
        LCOMCalculator.ClassInfo classInfo = new LCOMCalculator.ClassInfo();
        classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1")));// p = 1, q = 0
        classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr2")));
        int lcom = LCOMCalculator.calculateLCOM(classInfo);
        assertEquals(1, lcom, "LCOM deberia de ser 1");
    }

    @Test
    public void testNoMethods() {
        LCOMCalculator.ClassInfo classInfo = new LCOMCalculator.ClassInfo(); // p = 0, q = 0
        int lcom = LCOMCalculator.calculateLCOM(classInfo);
        assertEquals(0, lcom, "LCOM deberia de ser 0");
    }
}