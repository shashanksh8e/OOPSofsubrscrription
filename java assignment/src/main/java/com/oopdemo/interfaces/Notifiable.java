package com.oopdemo.interfaces;

/**
 * Interface defining contract for objects that can receive notifications.
 * Demonstrates interface implementation in OOP design.
 */
public interface Notifiable {
    /**
     * Sends a notification message to the object.
     * @param message The notification message to send
     */
    void sendNotification(String message);
    
    /**
     * Gets the contact information for notifications.
     * @return String containing contact information
     */
    String getContactInfo();
}