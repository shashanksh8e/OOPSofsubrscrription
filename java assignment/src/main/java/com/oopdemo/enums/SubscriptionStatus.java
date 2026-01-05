package com.oopdemo.enums;

/**
 * Enumeration for subscription status values.
 * Demonstrates proper use of enums in Java OOP design.
 */
public enum SubscriptionStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    CANCELLED("Cancelled"),
    PENDING("Pending");
    
    private final String displayName;
    
    /**
     * Constructor for SubscriptionStatus enum.
     * @param displayName Human-readable display name
     */
    SubscriptionStatus(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Gets the display name for the status.
     * @return String representing the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}