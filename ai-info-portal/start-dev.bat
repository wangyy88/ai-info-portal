@echo off
echo Starting AI Info Portal...

echo.
echo Starting backend server...
cd backend
start cmd /k "mvn spring-boot:run"

timeout /t 5 /nobreak >nul

echo.
echo Starting frontend server...
cd ../frontend
start cmd /k "npm install && npm run dev"