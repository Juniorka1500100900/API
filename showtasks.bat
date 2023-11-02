@echo off

call runcrud.bat

if %errorlevel% neq 0 (
    echo Compilation error! The runcrud.bat script failed.
    pause
) else (
    echo The runcrud.bat script completed successfully.
    pause
)

start chrome http://localhost:8080/crud/v1/task/tasks