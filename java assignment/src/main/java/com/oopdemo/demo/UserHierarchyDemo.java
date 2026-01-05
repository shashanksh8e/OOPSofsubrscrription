package com.oopdemo.demo;

import com.oopdemo.users.User;
import com.oopdemo.users.RegularUser;
import com.oopdemo.users.AdminUser;
import java.time.LocalDate;

/**
 * Demonstration class for User hierarchy showcasing OOP concepts.
 * 
 * This class demonstrates:
 * - Single inheritance in action
 * - Polymorphism with parent class references
 * - Method overriding behavior
 * - Constructor chaining output
 * - Final method behavior
 * - Interface implementation
 */
public class UserHierarchyDemo {
    
    /**
     * Demonstrates the User hierarchy and OOP concepts.
     */
    public static void demonstrateUserHierarchy() {
        System.out.println("\n=== User Hierarchy Demonstration (Single Inheritance) ===");
        
        // Demonstrate constructor chaining
        System.out.println("\n1. Constructor Chaining Demonstration:");
        System.out.println("Creating RegularUser with name and email...");
        RegularUser regularUser = new RegularUser("John Doe", "john.doe@email.com");
        
        System.out.println("\nCreating AdminUser with name and email...");
        AdminUser adminUser = new AdminUser("Jane Smith", "jane.smith@admin.com");
        
        // Demonstrate polymorphism - parent class references
        System.out.println("\n2. Polymorphism Demonstration:");
        System.out.println("Storing different user types in User references...");
        User[] users = {regularUser, adminUser};
        
        for (User user : users) {
            System.out.println("\n--- User Information ---");
            System.out.println("User ID (final method): " + user.getUserId());
            System.out.println("Access Level (abstract method): " + user.getAccessLevel());
            System.out.println("Account Info (overridden method): " + user.getAccountInfo());
            System.out.println("Status (interface method): " + user.getStatus());
        }
        
        // Demonstrate method overriding
        System.out.println("\n3. Method Overriding Demonstration:");
        System.out.println("Calling sendNotification on different user types...");
        
        regularUser.sendNotification("Welcome to our service!");
        adminUser.sendNotification("System maintenance scheduled for tonight.");
        
        // Demonstrate interface implementation
        System.out.println("\n4. Interface Implementation Demonstration:");
        System.out.println("Using Manageable interface methods...");
        
        regularUser.updateStatus("PREMIUM");
        adminUser.updateStatus("SUPER_ADMIN");
        
        // Demonstrate admin-specific functionality
        System.out.println("\n5. Admin-Specific Functionality:");
        adminUser.addPermission("DELETE_USERS");
        adminUser.manageUser(regularUser, "notify");
        
        System.out.println("\nAdmin permissions: " + adminUser.getAdminPermissions());
        System.out.println("Regular user subscription limits: " + regularUser.getSubscriptionLimits());
        
        // Demonstrate dynamic method dispatch
        System.out.println("\n6. Dynamic Method Dispatch:");
        System.out.println("Calling methods through User references (runtime method selection)...");
        
        User userRef1 = new RegularUser("Bob Wilson", "bob@email.com");
        User userRef2 = new AdminUser("Alice Johnson", "alice@admin.com");
        
        System.out.println("userRef1 access level: " + userRef1.getAccessLevel()); // Calls RegularUser implementation
        System.out.println("userRef2 access level: " + userRef2.getAccessLevel()); // Calls AdminUser implementation
        
        System.out.println("\n=== User Hierarchy Demonstration Complete ===");
    }
    
    /**
     * Main method for standalone testing.
     */
    public static void main(String[] args) {
        demonstrateUserHierarchy();
    }
}