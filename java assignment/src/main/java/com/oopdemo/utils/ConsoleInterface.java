package com.oopdemo.utils;

import java.util.Scanner;

/**
 * Utility class for console input/output operations.
 * Provides methods for user interaction and input validation.
 * 
 * This class demonstrates:
 * - Utility class design pattern
 * - Input validation
 * - Error handling
 * - User-friendly console interaction
 */
public class ConsoleInterface {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Reads user input from console.
     * @return String containing user input
     */
    public static String readUserInput() {
        return scanner.nextLine().trim();
    }
    
    /**
     * Reads user input with a prompt message.
     * @param prompt The prompt message to display
     * @return String containing user input
     */
    public static String readUserInput(String prompt) {
        System.out.print(prompt);
        return readUserInput();
    }
    
    /**
     * Displays a message to the console.
     * @param message The message to display
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }
    
    /**
     * Displays a formatted header message.
     * @param title The header title
     */
    public static void displayHeader(String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title);
        System.out.println("=".repeat(50));
    }
    
    /**
     * Displays a menu with numbered options.
     * @param title The menu title
     * @param options Array of menu options
     */
    public static void showMenu(String title, String[] options) {
        displayHeader(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("\nSelect option (1-" + options.length + "): ");
    }
    
    /**
     * Reads and validates integer input within a range.
     * @param min Minimum valid value
     * @param max Maximum valid value
     * @return Valid integer input
     */
    public static int readIntegerInput(int min, int max) {
        while (true) {
            try {
                String input = readUserInput();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
    
    /**
     * Reads and validates double input.
     * @param prompt The prompt message
     * @return Valid double input
     */
    public static double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = readUserInput();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
    
    /**
     * Validates that a string is not empty or null.
     * @param input The string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    /**
     * Reads and validates non-empty string input.
     * @param prompt The prompt message
     * @return Valid non-empty string
     */
    public static String readValidString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = readUserInput();
            if (isValidString(input)) {
                return input;
            } else {
                System.out.print("Input cannot be empty. Please try again: ");
            }
        }
    }
    
    /**
     * Displays an error message with formatting.
     * @param error The error message
     */
    public static void displayError(String error) {
        System.out.println("ERROR: " + error);
    }
    
    /**
     * Displays a success message with formatting.
     * @param message The success message
     */
    public static void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }
    
    /**
     * Pauses execution and waits for user to press Enter.
     */
    public static void pauseForUser() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Closes the scanner (should be called when application exits).
     */
    public static void closeScanner() {
        scanner.close();
    }
}