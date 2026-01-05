@echo off
echo ========================================
echo   Java OOP Demo - Simple Execution
echo ========================================
echo.
echo Choose what you want to run:
echo.
echo 1. Console Demo (Text-based, shows OOP concepts)
echo 2. HTML Frontend (Visual interface in browser)
echo 3. Both (Console first, then HTML)
echo.
set /p choice="Enter your choice (1, 2, or 3): "

if "%choice%"=="1" goto console
if "%choice%"=="2" goto html
if "%choice%"=="3" goto both
echo Invalid choice. Please run the script again.
pause
exit

:console
echo.
echo Running Console Demo...
echo ========================================
compile.bat
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit
)
echo.
echo Starting OOP Demonstration...
java -cp out com.oopdemo.demo.UserHierarchyDemo
echo.
echo Demo completed!
pause
exit

:html
echo.
echo Opening HTML Frontend...
echo ========================================
echo Opening frontend/index.html in your default browser...
start frontend/index.html
echo.
echo The HTML page should open in your browser.
echo If it doesn't open automatically, navigate to:
echo %cd%\frontend\index.html
echo.
pause
exit

:both
echo.
echo Running Console Demo first...
echo ========================================
compile.bat
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit
)
echo.
echo Starting Console OOP Demonstration...
java -cp out com.oopdemo.demo.UserHierarchyDemo
echo.
echo Console demo completed! Now opening HTML frontend...
echo.
start frontend/index.html
echo.
echo Both demos are now running!
echo - Console demo has finished
echo - HTML frontend should be open in your browser
echo.
pause