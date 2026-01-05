package com.oopdemo.users;

import com.oopdemo.interfaces.Manageable;
import com.oopdemo.interfaces.Notifiable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Abstract base class representing any system user.
 * Demonstrates single inheritance, abstract methods, final methods, and constructor chaining.
 * 
 * This class showcases several OOP concepts:
 * - Abstract class that cannot be instantiated
 * - Abstract method that must be implemented by subclasses
 * - Final method that cannot be overridden
 * - Constructor chaining using this() and super()
 * - Interface implementation
 * - Proper encapsulation with protected fields
 */
public abstract class User implements Manageable, Notifiable {
    // Protected fields - accessible to subclasses but not external classes
    protected String userId;
    protected String name;
    protected String email;
    protected LocalDate registrationDate;
    protected String status;
    
    /**
     * Constructor with name and email - demonstrates constructor chaining.
     * This constructor calls the full constructor using this().
     * 
     * @param name The user's name
     * @param email The user's email address
     */
    public User(String name, String email) {
        // Constructor chaining - calls the full constructor with generated ID and current date
        this(generateId(), name, email, LocalDate.now());
        System.out.println("User constructor (name, email) called for: " + name);
    }
    
    /**
     * Full constructor - demonstrates proper initialization.
     * This is the main constructor that initializes all fields.
     * 
     * @param userId Unique identifier for the user
     * @param name The user's name
     * @param email The user's email address
     * @param registrationDate The date the user registered
     */
    public User(String userId, String name, String email, LocalDate registrationDate) {
        System.out.println("User full constructor called for: " + name);
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.registrationDate = registrationDate;
        this.status = "ACTIVE";
    }
    
    /**
     * Abstract method - must be implemented by all subclasses.
     * This demonstrates the abstract method concept in OOP.
     * 
     * @return String representing the user's access level
     */
    public abstract String getAccessLevel();
    
    /**
     * Concrete method - provides default implementation but can be overridden.
     * This method can be overridden by subclasses to provide specialized behavior.
     * 
     * @return String containing basic account information
     */
    public String getAccountInfo() {
        return String.format("User: %s (%s) - Registered: %s", 
                           name, email, registrationDate);
    }
    
    /**
     * Final method - cannot be overridden by subclasses.
     * This demonstrates the final method concept in OOP.
     * 
     * @return String containing the user's unique ID
     */
    public final String getUserId() {
        return userId;
    }
    
    // Implementation of Manageable interface
    @Override
    public String getId() {
        return userId;
    }
    
    @Override
    public String getStatus() {
        return status;
    }
    
    @Override
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("User " + name + " status updated to: " + newStatus);
    }
    
    // Implementation of Notifiable interface
    @Override
    public void sendNotification(String message) {
        System.out.println("Notification to " + name + " (" + email + "): " + message);
    }
    
    @Override
    public String getContactInfo() {
        return email;
    }
    
    // Utility method for generating unique IDs
    private static String generateId() {
        return "USER-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    // Getters for protected fields (for demonstration purposes)
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}