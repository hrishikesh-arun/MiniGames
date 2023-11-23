@echo off

call :sub %1
exit /b
:sub

javac %1
ECHO --Compiled--
java %1

:eof