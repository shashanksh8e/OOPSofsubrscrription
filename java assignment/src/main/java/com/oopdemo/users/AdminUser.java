package com.oopdemo.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * AdminUser class extending User - demonstrates single inheritance with enhanced functionality.
 * 
 * This class showcases:
 * - Single inheritance from abstract User class
 * - Method overriding with @Override annotation
 * - Constructor chaining using super()
 * - Implementation of abstract method from parent class
 * - Additional functionality specific to admin users
 */
public class AdminUser extends User {
    private List<String> adminPermissions;
    
    /**
     * Constructor with name and email - demonstrates constructor chaining.
     * Calls parent constructor using super() and initializes admin-specific fields.
     * 
     * @param name The admin user's name
     * @param email The admin user's email address
     */
    public AdminUser(String name, String email) {
        super(name, email); // Constructor chaining to parent class
        this.adminPermissions = new ArrayList<>();
        initializeAdminPermissions();
        System.out.println("AdminUser constructor called for: " + name);
    }
    
    /**
     * Full constructor - demonstrates constructor chaining with all parameters.
     * 
     * @param userId Unique identifier for the admin user
     * @param name The admin user's name
     * @param email The admin user's email address
     * @param registrationDate The date the admin user registered
     */
    public AdminUser(String userId, String name, String email, LocalDate registrationDate) {
        super(userId, name, email, registrationDate); // Constructor chaining to parent class
        this.adminPermissions = new ArrayList<>();
        initializeAdminPermissions();
        System.out.println("AdminUser full constructor called for: " + name);
    }
    
    /**
     * Implementation of abstract method from User class.
     * This demonstrates method overriding of abstract methods.
     * 
     * @return String representing admin access level
     */
    @Override
    public String getAccessLevel() {
        return "ADMIN";
    }
    
    /**
     * Override of parent's getAccountInfo method - demonstrates method extension.
     * This shows how to extend parent functionality using super().
     * 
     * @return String containing enhanced account information for admin users
     */
    @Override
    public String getAccountInfo() {
        // Method extension - call parent method and add additional information
        String baseInfo = super.getAccountInfo();
        return baseInfo + " | Access Level: " + getAccessLevel() + 
               " | Type: Administrator | Permissions: " + adminPermissions.size();
    }
    
    /**
     * Override of sendNotification to add admin-specific behavior.
     * Demonstrates complete method replacement with enhanced functionality.
     * 
     * @param message The notification message to send
     */
    @Override
    public void sendNotification(String message) {
        // Enhanced notification for admin users
        System.out.println("ADMIN NOTIFICATION to " + name + " (" + email + "): " + message);
        System.out.println("Admin notification logged for audit purposes.");
    }
    
    /**
     * Method specific to AdminUser - demonstrates specialized behavior.
     * 
     * @param permission The permission to add
     */
    public void addPermission(String permission) {
        if (!adminPermissions.contains(permission)) {
            adminPermissions.add(permission);
            System.out.println("Permission '" + permission + "' added to admin user: " + name);
        }
    }
    
    /**
     * Method specific to AdminUser - demonstrates specialized behavior.
     * 
     * @return List of admin permissions
     */
    public List<String> getAdminPermissions() {
        return new ArrayList<>(adminPermissions); // Return copy to maintain encapsulation
    }
    
    /**
     * Method specific to AdminUser - demonstrates admin-only functionality.
     * 
     * @param targetUser The user to manage
     * @param action The management action to perform
     */
    public void manageUser(User targetUser, String action) {
        System.out.println("Admin " + name + " performing action '" + action + 
                         "' on user: " + targetUser.getName());
        
        switch (action.toLowerCase()) {
            case "suspend":
                targetUser.updateStatus("SUSPENDED");
                break;
            case "activate":
                targetUser.updateStatus("ACTIVE");
                break;
            case "notify":
                targetUser.sendNotification("You have been contacted by an administrator.");
                break;
            default:
                System.out.println("Unknown action: " + action);
        }
    }
    
    /**
     * Initialize default admin permissions.
     */
    private void initializeAdminPermissions() {
        adminPermissions.add("USER_MANAGEMENT");
        adminPermissions.add("SYSTEM_CONFIGURATION");
        adminPermissions.add("BILLING_ACCESS");
        adminPermissions.add("AUDIT_LOGS");
    }
}