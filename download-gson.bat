@echo off
echo Downloading Gson library for JSON handling...

REM Create lib directory
if not exist "lib" mkdir lib

REM Download Gson JAR (you can also download manually from Maven Central)
echo Please download gson-2.10.1.jar from:
echo https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
echo.
echo Save it to the 'lib' folder in your project directory.
echo.
echo Alternative: If you have Maven or Gradle, add this dependency:
echo Maven: ^<dependency^>^<groupId^>com.google.code.gson^</groupId^>^<artifactId^>gson^</artifactId^>^<version^>2.10.1^</version^>^</dependency^>
echo Gradle: implementation 'com.google.code.gson:gson:2.10.1'
echo.
pause