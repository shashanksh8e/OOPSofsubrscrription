@echo off
echo Compiling Java OOP Demo Project...

REM Create output directory
if not exist "out" mkdir out

REM Compile interfaces
javac -d out src\main\java\com\oopdemo\interfaces\*.java
if %errorlevel% neq 0 (
    echo Error compiling interfaces
    exit /b 1
)

REM Compile enums
javac -d out -cp out src\main\java\com\oopdemo\enums\*.java
if %errorlevel% neq 0 (
    echo Error compiling enums
    exit /b 1
)

REM Compile users
javac -d out -cp out src\main\java\com\oopdemo\users\*.java
if %errorlevel% neq 0 (
    echo Error compiling users
    exit /b 1
)

REM Compile utils
javac -d out -cp out src\main\java\com\oopdemo\utils\*.java
if %errorlevel% neq 0 (
    echo Error compiling utils
    exit /b 1
)

REM Compile demo
javac -d out -cp out src\main\java\com\oopdemo\demo\*.java
if %errorlevel% neq 0 (
    echo Error compiling demo
    exit /b 1
)

echo Compilation successful!
echo.
echo To run the User Hierarchy Demo:
echo java -cp out com.oopdemo.demo.UserHierarchyDemo
echo.