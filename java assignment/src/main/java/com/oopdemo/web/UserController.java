package com.oopdemo.web;

import com.oopdemo.users.User;
import com.oopdemo.users.RegularUser;
import com.oopdemo.users.AdminUser;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for User operations.
 * Demonstrates how to expose OOP classes through web APIs.
 * 
 * Note: This is a simplified version without Spring Boot dependencies.
 * In a real application, you would use @RestController, @GetMapping, etc.
 */
public class UserController {
    private List<User> users = new ArrayList<>();
    
    /**
     * Creates a new regular user.
     * Endpoint: POST /api/users/regular
     */
    public Map<String, Object> createRegularUser(String name, String email) {
        try {
            RegularUser user = new RegularUser(name, email);
            users.add(user);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Regular user created successfully");
            response.put("user", userToMap(user));
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error creating user: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * Creates a new admin user.
     * Endpoint: POST /api/users/admin
     */
    public Map<String, Object> createAdminUser(String name, String email) {
        try {
            AdminUser user = new AdminUser(name, email);
            users.add(user);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Admin user created successfully");
            response.put("user", userToMap(user));
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error creating admin user: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * Gets all users.
     * Endpoint: GET /api/users
     */
    public Map<String, Object> getAllUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(userToMap(user));
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("users", userList);
        response.put("count", users.size());
        return response;
    }
    
    /**
     * Demonstrates polymorphism through API.
     * Endpoint: GET /api/users/polymorphism-demo
     */
    public Map<String, Object> demonstratePolymorphism() {
        List<Map<String, Object>> demonstrations = new ArrayList<>();
        
        for (User user : users) {
            Map<String, Object> demo = new HashMap<>();
            demo.put("userId", user.getUserId());
            demo.put("name", user.getName());
            demo.put("accessLevel", user.getAccessLevel()); // Polymorphic call
            demo.put("accountInfo", user.getAccountInfo()); // Polymorphic call
            demo.put("userType", user.getClass().getSimpleName());
            demonstrations.add(demo);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Polymorphism demonstration - same method calls, different implementations");
        response.put("demonstrations", demonstrations);
        return response;
    }
    
    /**
     * Sends notification to a user.
     * Endpoint: POST /api/users/{userId}/notify
     */
    public Map<String, Object> sendNotification(String userId, String message) {
        User user = findUserById(userId);
        if (user == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "User not found");
            return response;
        }
        
        // Capture the notification output (in real app, you'd log this)
        user.sendNotification(message);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Notification sent successfully");
        response.put("userType", user.getClass().getSimpleName());
        return response;
    }
    
    /**
     * Demonstrates admin functionality.
     * Endpoint: POST /api/users/admin-demo
     */
    public Map<String, Object> demonstrateAdminFunctionality(String adminId, String targetUserId, String action) {
        User admin = findUserById(adminId);
        User targetUser = findUserById(targetUserId);
        
        if (admin == null || targetUser == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Admin or target user not found");
            return response;
        }
        
        if (!(admin instanceof AdminUser)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "User is not an admin");
            return response;
        }
        
        AdminUser adminUser = (AdminUser) admin;
        adminUser.manageUser(targetUser, action);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Admin action performed successfully");
        response.put("action", action);
        response.put("adminName", adminUser.getName());
        response.put("targetUserName", targetUser.getName());
        return response;
    }
    
    // Helper methods
    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    private Map<String, Object> userToMap(User user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userId", user.getUserId());
        userMap.put("name", user.getName());
        userMap.put("email", user.getEmail());
        userMap.put("accessLevel", user.getAccessLevel());
        userMap.put("status", user.getStatus());
        userMap.put("userType", user.getClass().getSimpleName());
        userMap.put("registrationDate", user.getRegistrationDate().toString());
        
        // Add admin-specific data if it's an admin user
        if (user instanceof AdminUser) {
            AdminUser adminUser = (AdminUser) user;
            userMap.put("permissions", adminUser.getAdminPermissions());
        }
        
        // Add regular user-specific data if it's a regular user
        if (user instanceof RegularUser) {
            RegularUser regularUser = (RegularUser) user;
            userMap.put("subscriptionLimits", regularUser.getSubscriptionLimits());
        }
        
        return userMap;
    }
}