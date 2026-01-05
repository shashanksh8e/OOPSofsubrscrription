@echo off
echo Compiling Java OOP Demo Project with Web Interface...

REM Create output directory
if not exist "out" mkdir out
if not exist "lib" mkdir lib

REM Check if Gson library exists
if not exist "lib\gson-2.10.1.jar" (
    echo.
    echo ERROR: Gson library not found!
    echo Please download gson-2.10.1.jar from:
    echo https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
    echo.
    echo Save it as: lib\gson-2.10.1.jar
    echo.
    echo Then run this script again.
    pause
    exit /b 1
)

REM Set classpath
set CLASSPATH=lib\gson-2.10.1.jar

REM Compile interfaces
javac -d out -cp %CLASSPATH% src\main\java\com\oopdemo\interfaces\*.java
if %errorlevel% neq 0 (
    echo Error compiling interfaces
    exit /b 1
)

REM Compile enums
javac -d out -cp "%CLASSPATH%;out" src\main\java\com\oopdemo\enums\*.java
if %errorlevel% neq 0 (
    echo Error compiling enums
    exit /b 1
)

REM Compile users
javac -d out -cp "%CLASSPATH%;out" src\main\java\com\oopdemo\users\*.java
if %errorlevel% neq 0 (
    echo Error compiling users
    exit /b 1
)

REM Compile utils
javac -d out -cp "%CLASSPATH%;out" src\main\java\com\oopdemo\utils\*.java
if %errorlevel% neq 0 (
    echo Error compiling utils
    exit /b 1
)

REM Compile web
javac -d out -cp "%CLASSPATH%;out" src\main\java\com\oopdemo\web\*.java
if %errorlevel% neq 0 (
    echo Error compiling web classes
    exit /b 1
)

REM Compile demo
javac -d out -cp "%CLASSPATH%;out" src\main\java\com\oopdemo\demo\*.java
if %errorlevel% neq 0 (
    echo Error compiling demo
    exit /b 1
)

echo Compilation successful!
echo.
echo To run the web server:
echo java -cp "lib\gson-2.10.1.jar;out" com.oopdemo.web.SimpleWebServer
echo.
echo To run the console demo:
echo java -cp "lib\gson-2.10.1.jar;out" com.oopdemo.demo.UserHierarchyDemo
echo.