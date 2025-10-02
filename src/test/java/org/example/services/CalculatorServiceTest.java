package org.example.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit 5 test class for the CalculatorService.
 * This class ensures that the mathematical functions (sqrt, fact, log, pow)
 * work as expected across various scenarios.
 */
public class CalculatorServiceTest {

    // The instance of the class being tested (Subject Under Test)
    private CalculatorService calculatorService;

    // Define a small delta for comparing doubles due to floating-point precision
    private static final double DELTA = 1e-9;

    /**
     * Setup method run before each individual test case (@Test method).
     * This ensures the calculatorService instance is fresh for every test.
     */
    @BeforeEach
    void setUp() {
        // Initialize the service instance (assuming this is where the methods live)
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

    // --- Tests for the 'sqrt' (Square Root) method ---

    /**
     * Test case for the square root of a perfect square.
     */
    @Test
    void testSqrt_PerfectSquare() {
        double result = calculatorService.sqrt(81.0);
        assertEquals(9.0, result, DELTA, "Square root of 81.0 should be 9.0");
    }

    /**
     * Test case for the square root of a non-perfect square.
     */
    @Test
    void testSqrt_NonPerfectSquare() {
        double result = calculatorService.sqrt(2.0);
        assertEquals(1.41421356237, result, DELTA, "Square root of 2.0 should be approx 1.414...");
    }

    /**
     * Test case for square root of a negative number, which should return NaN.
     */
    @Test
    void testSqrt_NegativeNumber() {
        double result = calculatorService.sqrt(-4.0);
        // Math.sqrt(-x) returns Double.NaN
        assertEquals(Double.NaN, result, "Square root of a negative number should be NaN");
    }

    // --- Tests for the 'fact' (Factorial) method ---

    /**
     * Test case for the factorial of 5.
     */
    @Test
    void testFact_PositiveNumber() {
        int result = calculatorService.fact(5);
        assertEquals(120, result, "5! should equal 120.");
    }

    /**
     * Test case for the factorial of zero (Base case).
     */
    @Test
    void testFact_OfZero() {
        int result = calculatorService.fact(0);
        assertEquals(1, result, "0! should equal 1.");
    }

    /**
     * Test case for a large number where the result will overflow 'int'.
     * Since the method is not robust against overflow, we test the exact output.
     */
    @Test
    void testFact_OverflowCheck() {
        // 13! exceeds Integer.MAX_VALUE.
        // The implementation will return a negative or incorrect number.
        // We rely on the current (non-robust) implementation's output.
        // 13! = 6,227,020,800. The int result is 1932053504.
        int result = calculatorService.fact(13);
        assertEquals(1932053504, result, "13! calculation results in overflow (1932053504).");
    }

    /**
     * Test case for a negative number, which relies on the 'assert' statement.
     * Note: Asserts are typically disabled in production, but we test the expected behavior
     * in a standard JUnit environment where assertion failure is expected.
     */
    @Test
    void testFact_NegativeNumber_ThrowsAssertionError() {
        // We use an Executable lambda to catch the AssertionError thrown by the 'assert'
        Executable executable = () -> calculatorService.fact(-1);

        // This will pass IF the 'assert' keyword is enabled (default in IDE tests)
        assertThrows(AssertionError.class, executable,
                "Factorial of a negative number should throw an AssertionError.");
    }


    // --- Tests for the 'naturalLog' (Natural Logarithm) method ---

    /**
     * Test case for ln(e), which should equal 1.0.
     */
    @Test
    void testNaturalLog_OfE() {
        double result = calculatorService.naturalLog(Math.E);
        assertEquals(1.0, result, DELTA, "ln(e) should equal 1.0");
    }

    /**
     * Test case for ln(1), which should equal 0.0.
     */
    @Test
    void testNaturalLog_OfOne() {
        double result = calculatorService.naturalLog(1.0);
        assertEquals(0.0, result, DELTA, "ln(1) should equal 0.0");
    }

    /**
     * Test case for ln of a number greater than 1.
     */
    @Test
    void testNaturalLog_GreaterThanOne() {
        double result = calculatorService.naturalLog(10.0);
        assertEquals(2.30258509299, result, DELTA, "ln(10) should be approx 2.302...");
    }

    /**
     * Test case for ln(0), which results in negative infinity.
     */
    @Test
    void testNaturalLog_OfZero() {
        double result = calculatorService.naturalLog(0.0);
        assertEquals(Double.NEGATIVE_INFINITY, result, "ln(0) should be NEGATIVE_INFINITY");
    }

    /**
     * Test case for ln of a negative number, which results in NaN.
     */
    @Test
    void testNaturalLog_NegativeNumber() {
        double result = calculatorService.naturalLog(-1.0);
        assertEquals(Double.NaN, result, "ln(-x) should be NaN");
    }


    // --- Tests for the 'raiseToPower' (Power) method ---

    /**
     * Test case for a positive base raised to a positive exponent (3^4).
     */
    @Test
    void testRaiseToPower_PositiveBaseAndExponent() {
        double result = calculatorService.raiseToPower(3.0, 4.0);
        assertEquals(81.0, result, DELTA, "3^4 should equal 81.0");
    }

    /**
     * Test case for any number raised to the power of zero (x^0).
     */
    @Test
    void testRaiseToPower_ExponentIsZero() {
        double result = calculatorService.raiseToPower(100.0, 0.0);
        assertEquals(1.0, result, DELTA, "100.0^0 should equal 1.0");
    }

    /**
     * Test case for a base raised to a negative exponent (2^-3).
     */
    @Test
    void testRaiseToPower_NegativeExponent() {
        double result = calculatorService.raiseToPower(2.0, -3.0);
        assertEquals(0.125, result, DELTA, "2.0^-3 should equal 0.125"); // 1/8
    }

    /**
     * Test case for a fractional exponent (4^0.5, i.e., square root).
     */
    @Test
    void testRaiseToPower_FractionalExponent() {
        double result = calculatorService.raiseToPower(4.0, 0.5);
        assertEquals(2.0, result, DELTA, "4.0^0.5 should equal 2.0");
    }
}