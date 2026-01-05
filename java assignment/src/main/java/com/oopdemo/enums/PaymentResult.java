package com.oopdemo.enums;

/**
 * Enumeration for payment processing results.
 * Demonstrates proper use of enums in Java OOP design.
 */
public enum PaymentResult {
    SUCCESS("Payment processed successfully"),
    FAILED("Payment processing failed"),
    DECLINED("Payment was declined"),
    INSUFFICIENT_FUNDS("Insufficient funds"),
    INVALID_CARD("Invalid card information"),
    NETWORK_ERROR("Network error occurred"),
    TIMEOUT("Payment processing timed out");
    
    private final String message;
    
    /**
     * Constructor for PaymentResult enum.
     * @param message Descriptive message for the result
     */
    PaymentResult(String message) {
        this.message = message;
    }
    
    /**
     * Gets the message for the payment result.
     * @return String representing the result message
     */
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return message;
    }
}