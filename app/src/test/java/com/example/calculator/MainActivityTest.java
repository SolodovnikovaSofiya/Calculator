package com.example.calculator;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;

public class MainActivityTest extends TestCase {
    @Test
    public void testAddition() {
        double result = MainActivity.performOperation(5, 3, "+");
        assertEquals(8, result, 0.001);
    }

    @Test
    public void testSubtraction() {
        double result = MainActivity.performOperation(10, 4, "-");
        assertEquals(6, result, 0.001);
    }

    @Test
    public void testMultiplication() {
        double result = MainActivity.performOperation(2, 3, "*");
        assertEquals(6, result, 0.001);
    }

    @Test
    public void testDivision() {
        double result = MainActivity.performOperation(10, 2, "/");
        assertEquals(5, result, 0.001);
    }

    @Test
    public void testDivisionByZero() {
        double result = MainActivity.performOperation(10, 0, "/");
        assertTrue(Double.isNaN(result));
    }

}