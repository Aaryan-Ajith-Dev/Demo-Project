package org.example.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit 5 test class for the CalculatorService.
 * This class ensures that the basic arithmetic operations (add, mult)
 * work as expected across various scenarios.
 */
public class CalculatorServiceTest {

    // The instance of the class being tested (Subject Under Test)
    private CalculatorService calculatorService;

    /**
     * Setup method run before each individual test case (@Test method).
     * This ensures the calculatorService instance is fresh for every test.
     */
    @BeforeEach
    void setUp() {
        // Initialize the service instance
        calculatorService = new CalculatorService();
    }

    /**
     * Test case to ensure the service object is successfully initialized.
     */
    @Test
    void testServiceInitialization() {
        // Assert that the object is not null after setUp() runs
        assertNotNull(calculatorService, "CalculatorService instance should be initialized.");
    }

    /* --- Tests for the 'add' method --- */

    /**
     * Test case for the add method with two positive numbers.
     */
    @Test
    void testAdd_PositiveNumbers() {
        int result = calculatorService.add(5, 3);
        assertEquals(8, result, "5 + 3 should equal 8.");
    }

    /**
     * Test case for the add method with two negative numbers.
     */
    @Test
    void testAdd_NegativeNumbers() {
        int result = calculatorService.add(-10, -5);
        assertEquals(-15, result, "-10 + (-5) should equal -15.");
    }

    /**
     * Test case for the add method with a positive number and zero.
     */
    @Test
    void testAdd_WithZero() {
        int result = calculatorService.add(7, 0);
        assertEquals(7, result, "7 + 0 should equal 7.");
    }

    /* --- Tests for the 'mult' method --- */

    /**
     * Test case for the mult method with two positive numbers.
     */
    @Test
    void testMult_PositiveNumbers() {
        int result = calculatorService.mult(4, 5);
        assertEquals(20, result, "4 * 5 should equal 20.");
    }

    /**
     * Test case for the mult method where the result is negative.
     */
    @Test
    void testMult_OneNegativeNumber() {
        int result = calculatorService.mult(-6, 2);
        assertEquals(-12, result, "-6 * 2 should equal -12.");
    }

    /**
     * Test case for the mult method with zero, which should always result in zero.
     */
    @Test
    void testMult_WithZero() {
        int result = calculatorService.mult(99, 0);
        assertEquals(0, result, "Any number multiplied by 0 should equal 0.");
    }
}
