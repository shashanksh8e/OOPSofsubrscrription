package com.oopdemo.interfaces;

/**
 * Interface defining contract for objects that can be billed.
 * Demonstrates interface implementation in OOP design.
 */
public interface Billable {
    /**
     * Calculates the total cost for billing.
     * @return double representing the total cost
     */
    double calculateTotalCost();
    
    /**
     * Gets the currency used for billing.
     * @return String representing the billing currency (e.g., "USD", "EUR")
     */
    String getBillingCurrency();
}