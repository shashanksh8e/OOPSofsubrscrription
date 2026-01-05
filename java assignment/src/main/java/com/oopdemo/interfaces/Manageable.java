package com.oopdemo.interfaces;

/**
 * Interface defining contract for objects that can be managed.
 * Demonstrates interface implementation in OOP design.
 */
public interface Manageable {
    /**
     * Gets the unique identifier of the manageable object.
     * @return String representing the unique ID
     */
    String getId();
    
    /**
     * Gets the current status of the manageable object.
     * @return String representing the current status
     */
    String getStatus();
    
    /**
     * Updates the status of the manageable object.
     * @param newStatus The new status to set
     */
    void updateStatus(String newStatus);
}