package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.subtract(3, 2));
    }

    @Test
    void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(2, 3));
    }

    @Test
    void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        Calculator calc = new Calculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("Divisor cannot be zero", exception.getMessage());
    }

    @Test
    void testAddWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-1, calc.add(-2, 1));
    }

    @Test
    void testSubtractWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-1, calc.subtract(-2, -1));
    }

    @Test
    void testMultiplyWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-6, calc.multiply(-2, 3));
    }

    @Test
    void testDivideWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-2, calc.divide(-6, 3));
    }

    @Test
    void testDivideWithZeroNumerator() {
        Calculator calc = new Calculator();
        assertEquals(0, calc.divide(0, 3));
    }
}