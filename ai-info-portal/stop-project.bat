@echo off
chcp 65001 >nul
echo ========================================
echo   AI Info Portal 项目停止脚本
echo ========================================
echo.

REM 函数：终止占用指定端口的进程
:kill_port_process
set PORT=%~1
for /f "tokens=2,5" %%a in ('netstat -ano ^| findstr "LISTENING" ^| findstr ":%PORT%"') do (
    echo 正在终止端口 %PORT% 的进程 (PID: %%b)...
    taskkill /pid %%b /f >nul 2>&1
    if !errorlevel! equ 0 (
        echo   [成功] 已终止进程 %%b
    ) else (
        echo   [失败] 无法终止进程 %%b
    )
)
goto :eof

echo 正在停止服务...
echo.

REM 终止后端进程
call :kill_port_process 9004

REM 终止前端进程
call :kill_port_process 9011

echo.
echo ========================================
echo   所有服务已停止
echo ========================================
pause