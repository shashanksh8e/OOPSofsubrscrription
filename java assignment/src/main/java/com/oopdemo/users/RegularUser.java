package com.oopdemo.users;

import java.time.LocalDate;

/**
 * RegularUser class extending User - demonstrates single inheritance.
 * 
 * This class showcases:
 * - Single inheritance from abstract User class
 * - Method overriding with @Override annotation
 * - Constructor chaining using super()
 * - Implementation of abstract method from parent class
 */
public class RegularUser extends User {
    
    /**
     * Constructor with name and email - demonstrates constructor chaining.
     * Calls parent constructor using super().
     * 
     * @param name The user's name
     * @param email The user's email address
     */
    public RegularUser(String name, String email) {
        super(name, email); // Constructor chaining to parent class
        System.out.println("RegularUser constructor called for: " + name);
    }
    
    /**
     * Full constructor - demonstrates constructor chaining with all parameters.
     * 
     * @param userId Unique identifier for the user
     * @param name The user's name
     * @param email The user's email address
     * @param registrationDate The date the user registered
     */
    public RegularUser(String userId, String name, String email, LocalDate registrationDate) {
        super(userId, name, email, registrationDate); // Constructor chaining to parent class
        System.out.println("RegularUser full constructor called for: " + name);
    }
    
    /**
     * Implementation of abstract method from User class.
     * This demonstrates method overriding of abstract methods.
     * 
     * @return String representing basic access level
     */
    @Override
    public String getAccessLevel() {
        return "BASIC";
    }
    
    /**
     * Override of parent's getAccountInfo method - demonstrates method extension.
     * This shows how to extend parent functionality using super().
     * 
     * @return String containing enhanced account information for regular users
     */
    @Override
    public String getAccountInfo() {
        // Method extension - call parent method and add additional information
        String baseInfo = super.getAccountInfo();
        return baseInfo + " | Access Level: " + getAccessLevel() + " | Type: Regular User";
    }
    
    /**
     * Method specific to RegularUser - demonstrates specialized behavior.
     * 
     * @return String containing regular user specific information
     */
    public String getSubscriptionLimits() {
        return "Regular users can have up to 3 active subscriptions";
    }
}