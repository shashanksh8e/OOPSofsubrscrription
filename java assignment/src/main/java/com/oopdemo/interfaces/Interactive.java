package com.oopdemo.interfaces;

/**
 * Interface defining contract for interactive system components.
 * Demonstrates interface implementation for user interaction in OOP design.
 */
public interface Interactive {
    /**
     * Displays the menu or interface to the user.
     */
    void displayMenu();
    
    /**
     * Processes user input and performs appropriate actions.
     * @param input The user input string to process
     */
    void processUserInput(String input);
    
    /**
     * Shows the results of operations to the user.
     */
    void showResults();
}