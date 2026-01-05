# Minimal Console Demo Files

## Required Files (6 total):

### 1. Main Demo Class:
- `UserHierarchyDemo.java` - Demonstrates all OOP concepts

### 2. Core Classes (3 files):
- `User.java` - Abstract base class with interfaces
- `RegularUser.java` - Concrete implementation
- `AdminUser.java` - Enhanced implementation

### 3. Required Interfaces (2 files):
- `Manageable.java` - Management contract (getId, getStatus, updateStatus)
- `Notifiable.java` - Notification contract (sendNotification, getContactInfo)

### 4. Build Script:
- `compile.bat` - Compilation script

## Total: 6 Java files + 1 batch file = 7 files

## Not Needed for Console:
- ConsoleInterface.java (utility, not used)
- Interactive.java (web-specific)
- Billable.java (not used in demo)
- SubscriptionStatus.java (not used in demo)
- PaymentResult.java (not used in demo)
- All web files (SimpleWebServer, UserController, index.html)
- Web build scripts

## How to Run Minimal Console:
```bash
compile.bat
java -cp out com.oopdemo.demo.UserHierarchyDemo
```