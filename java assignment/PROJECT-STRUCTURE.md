# Java OOP Demo Project Structure

## 📁 Current Organization

### 🖥️ Console Demo Files
```
console-demo/
├── README.md
└── Files to move here:
    ├── src/main/java/com/oopdemo/demo/UserHierarchyDemo.java
    ├── src/main/java/com/oopdemo/utils/ConsoleInterface.java
    ├── compile.bat
    └── run-simple.bat
```

### 🌐 Web Interface Files
```
web-demo/
├── README.md
└── Files to move here:
    ├── src/main/java/com/oopdemo/web/SimpleWebServer.java
    ├── src/main/java/com/oopdemo/web/UserController.java
    ├── frontend/index.html
    ├── compile-web.bat
    └── download-gson.bat
```

### 🔄 Core Business Logic (Shared)
```
core-classes/
├── README.md
└── Files to move here:
    ├── src/main/java/com/oopdemo/users/User.java
    ├── src/main/java/com/oopdemo/users/RegularUser.java
    ├── src/main/java/com/oopdemo/users/AdminUser.java
    ├── src/main/java/com/oopdemo/interfaces/Manageable.java
    ├── src/main/java/com/oopdemo/interfaces/Notifiable.java
    ├── src/main/java/com/oopdemo/interfaces/Billable.java
    ├── src/main/java/com/oopdemo/interfaces/Interactive.java
    ├── src/main/java/com/oopdemo/enums/SubscriptionStatus.java
    └── src/main/java/com/oopdemo/enums/PaymentResult.java
```

## 🎯 Benefits of This Organization

### 1. **Clear Separation of Concerns**
- Console logic separated from web logic
- Core business logic isolated and reusable
- Each module has specific responsibility

### 2. **Independent Development**
- Console team can work independently
- Web team can work independently
- Core logic changes affect both modules

### 3. **Easier Maintenance**
- Bug fixes in specific modules
- Feature additions don't affect other modules
- Clear dependency relationships

### 4. **Better Understanding**
- Students can focus on specific aspects
- Clear distinction between UI and business logic
- Modular learning approach

## 🚀 How to Use

### Console Demo Only:
```bash
cd console-demo
compile-console.bat
run-console.bat
```

### Web Interface Only:
```bash
cd web-demo
download-gson.bat
compile-web.bat
run-web.bat
```

### Full Project:
```bash
# Compile core classes first
# Then compile and run either console or web
```

## 📋 File Categories

| Category | Purpose | Files |
|----------|---------|-------|
| **Console** | Text-based demonstration | Demo, ConsoleInterface, batch files |
| **Web** | Browser-based interface | Server, Controller, HTML, web scripts |
| **Core** | Business logic (shared) | Users, Interfaces, Enums |
| **Build** | Compilation scripts | Various .bat files |
| **Documentation** | Project information | README files |

This organization makes it easy to:
- Understand which files serve which purpose
- Work on specific modules independently
- Maintain and extend the project
- Learn OOP concepts in focused modules