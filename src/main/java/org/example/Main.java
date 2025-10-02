package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.example.services.CalculatorService;

public class Main {
    public static void main(String[] args) {
        // Create instances of the Scanner for input and the CalculatorService
        Scanner scanner = new Scanner(System.in);
        CalculatorService calculatorService = new CalculatorService();

        System.out.println("--- Advanced Math Function CLI ---");
        System.out.println("Available Operations:");

        // Display the streamlined menu
        System.out.println(
                "1) Square Root (double)\n" +
                        "2) Factorial (int)\n" +
                        "3) Natural Log (double)\n" +
                        "4) Raise to Power (double, double)\n" +
                        "-------------------------------------\n" +
                        "Enter your choice (1-4): "
        );

        try {
            int choice = scanner.nextInt();
            System.out.println("--- Operation Output ---");

            switch (choice) {
                case 1: { // Square Root (double)
                    System.out.print("Enter number (x >= 0) for sqrt(x): ");
                    double x = scanner.nextDouble();
                    double result = calculatorService.sqrt(x);
                    System.out.println("Square Root Result: " + result);
                    break;
                }
                case 2: { // Factorial (int)
                    System.out.print("Enter non-negative integer for factorial: ");
                    int x = scanner.nextInt();

                    if (x < 0) {
                        System.out.println("Factorial is only defined for non-negative integers.");
                    } else if (x > 12) {
                        // 13! overflows a standard 32-bit int
                        System.out.println("Warning: Factorial of " + x + " will overflow 'int' and give an incorrect result.");
                        int result = calculatorService.fact(x);
                        System.out.println("Factorial Result (Overflowed): " + result);
                    } else {
                        int result = calculatorService.fact(x);
                        System.out.println("Factorial Result: " + result);
                    }
                    break;
                }
                case 3: { // Natural Log (double)
                    System.out.print("Enter number (x > 0) for ln(x): ");
                    double x = scanner.nextDouble();
                    double result = calculatorService.naturalLog(x);
                    System.out.println("Natural Log Result (ln): " + result);
                    break;
                }
                case 4: { // Raise to Power (double, double)
                    System.out.print("Enter base (x): ");
                    double x = scanner.nextDouble();
                    System.out.print("Enter exponent (y): ");
                    double y = scanner.nextDouble();
                    double result = calculatorService.raiseToPower(x, y);
                    System.out.println("Power Result (x^y): " + result);
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }

        } catch (InputMismatchException e) {
            // Catch errors if the user enters non-numeric data
            System.err.println("Error: Invalid input format. Please ensure you enter the correct type of number (integer or decimal).");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Always close the scanner to release system resources
            scanner.close();
        }
    }
}