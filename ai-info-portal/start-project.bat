@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

set SCRIPT_DIR=%~dp0

echo ========================================
echo   AI Info Portal - Starting...
echo ========================================

REM Kill existing processes on port 9004
for /f "tokens=5" %%a in ('netstat -ano 2^>nul ^| findstr ":9004.*LISTENING"') do (
    echo Stopping backend process [PID: %%a]...
    taskkill /pid %%a /f >nul 2>&1
)

REM Kill existing processes on port 9011
for /f "tokens=5" %%a in ('netstat -ano 2^>nul ^| findstr ":9011.*LISTENING"') do (
    echo Stopping frontend process [PID: %%a]...
    taskkill /pid %%a /f >nul 2>&1
)

echo Waiting for ports to be released...
timeout /t 3 /nobreak >nul

echo.
echo Starting backend service...
start "Backend - Port 9004" cmd /k "cd /d %SCRIPT_DIR%backend && mvn spring-boot:run"

echo Waiting for backend initialization...
timeout /t 15 /nobreak >nul

echo.
echo Starting frontend service...
start "Frontend - Port 9011" cmd /k "cd /d %SCRIPT_DIR%frontend && npm run dev"

echo.
echo ========================================
echo   Services started successfully!
echo ========================================
echo.
echo   Frontend: http://localhost:9011
echo   Backend:  http://localhost:9004
echo   H2 Console: http://localhost:9004/h2-console
echo.
echo ========================================
pause