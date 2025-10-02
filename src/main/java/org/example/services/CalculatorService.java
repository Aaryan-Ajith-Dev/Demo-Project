package org.example.services;

public class CalculatorService {
    public double sqrt(double x) {
        return Math.sqrt(x);
    }
    public long fact(int x) {
        assert x >= 0;
        if (x == 0) return 1;
        return x * fact(x - 1);
    }
    public double naturalLog(double x) {
        return Math.log(x);
    }

    public double raiseToPower(double x, double y) {
        // Computes x ^ y
        return Math.pow(x, y);
    }
}
