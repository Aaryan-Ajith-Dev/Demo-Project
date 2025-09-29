package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.example.services.CalculatorService;

public class Main {
    public static void main(String[] args) {
        // Create instances of the Scanner for input and the CalculatorService
        Scanner scanner = new Scanner(System.in);
        CalculatorService calculatorService = new CalculatorService();

        System.out.println("--- Simple Calculator CLI ---");

        // Display the menu and get the user's choice
        System.out.println(
                "1) Add 2 numbers\n" +
                        "2) Multiply 2 numbers\n" +
                        "Choose either 1 or 2: "
        );

        try {
            int choice = scanner.nextInt();

            // Validate the choice
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please choose 1 or 2.");
                return;
            }

            // Get the two numbers for the operation
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            int result = 0;
            String operation = "";

            // Perform the chosen operation
            if (choice == 1) {
                result = calculatorService.add(num1, num2);
                operation = "Addition";
            } else if (choice == 2) {
                result = calculatorService.mult(num1, num2);
                operation = "Multiplication";
            }

            // Display the result
            System.out.println("--- Result ---");
            System.out.println(operation + " Result: " + result);

        } catch (InputMismatchException e) {
            // Catch errors if the user enters non-numeric data
            System.err.println("Error: Invalid input. Please enter only whole numbers.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Always close the scanner to release system resources
            scanner.close();
        }
    }
}