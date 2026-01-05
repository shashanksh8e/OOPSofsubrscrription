# Java OOP Demonstration System

## Project Overview

This project demonstrates fundamental Object-Oriented Programming (OOP) concepts in Java through a subscription management system. The system showcases inheritance types, method overriding, dynamic method dispatch, constructor chaining, abstract classes, and interfaces.

## Project Structure

```
src/main/java/com/oopdemo/
├── interfaces/          # Interface definitions
│   ├── Billable.java    # Interface for billable objects
│   ├── Interactive.java # Interface for interactive components
│   ├── Manageable.java  # Interface for manageable objects
│   └── Notifiable.java  # Interface for notifiable objects
├── users/              # User hierarchy (Single Inheritance)
│   ├── User.java       # Abstract base class
│   ├── RegularUser.java # Regular user implementation
│   └── AdminUser.java  # Admin user implementation
├── subscriptions/      # Subscription hierarchy (Multilevel Inheritance)
│   ├── AbstractSubscription.java # Abstract base class
│   ├── Subscription.java         # Concrete base class
│   ├── BasicSubscription.java    # Basic subscription type
│   ├── PremiumSubscription.java  # Premium subscription type
│   └── EnterpriseSubscription.java # Enterprise subscription type
├── billing/            # Billing hierarchy (Hierarchical Inheritance)
│   ├── BillingProcessor.java     # Abstract base class
│   ├── CreditCardProcessor.java  # Credit card processor
│   ├── PayPalProcessor.java      # PayPal processor
│   └── BankTransferProcessor.java # Bank transfer processor
├── services/           # Service classes
│   ├── SubscriptionManager.java # Subscription management
│   └── BillingService.java      # Billing service
├── demo/              # Demonstration classes
│   ├── InteractiveDemo.java     # Main interactive demo
│   └── UserHierarchyDemo.java   # User hierarchy demo
├── utils/             # Utility classes
│   └── ConsoleInterface.java    # Console I/O utilities
└── enums/             # Enumeration classes
    ├── SubscriptionStatus.java # Subscription status enum
    └── PaymentResult.java      # Payment result enum
```

## OOP Concepts Demonstrated

### 1. Inheritance Types
- **Single Inheritance**: User → RegularUser/AdminUser
- **Multilevel Inheritance**: AbstractSubscription → Subscription → SpecializedSubscriptions
- **Hierarchical Inheritance**: BillingProcessor → Multiple processor types

### 2. Method Overriding
- Abstract method implementation
- Method extension using super()
- Complete method replacement
- @Override annotation usage

### 3. Dynamic Method Dispatch
- Polymorphic method calls
- Runtime method resolution
- Collections of parent references

### 4. Abstract Classes
- Abstract methods and concrete methods
- Constructor usage in abstract classes
- Template pattern implementation

### 5. Constructor Chaining
- super() calls to parent constructors
- this() calls for constructor overloading
- Multi-level constructor chaining

### 6. Interface Implementation
- Multiple interface implementation
- Contract definition and enforcement
- Interface-based polymorphism

## How to Run

1. Compile the project:
   ```bash
   javac -d out src/main/java/com/oopdemo/**/*.java
   ```

2. Run the interactive demo:
   ```bash
   java -cp out com.oopdemo.demo.InteractiveDemo
   ```

3. Run specific demonstrations:
   ```bash
   java -cp out com.oopdemo.demo.UserHierarchyDemo
   ```

## Features

- Interactive console interface
- Custom parameter input for object creation
- Real-time demonstration of OOP concepts
- Clear explanatory output
- Error handling and input validation
- Comprehensive examples of all inheritance types

## Educational Value

This project serves as a comprehensive learning tool for understanding:
- Class hierarchy design
- Proper use of inheritance
- Method overriding best practices
- Polymorphism in action
- Abstract class usage
- Interface implementation
- Constructor chaining patterns
- Java coding best practices