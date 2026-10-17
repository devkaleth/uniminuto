@echo off
title Ej1
:menu

clear
echo === PROCESOS ===

echo 1. ejecutar procesos
echo 2. ejecutar procesos
echo 3. voler
set /p op == Opcion :
if %op% == 1 goto p1
if %op% == 2 goto p2
if %op % == 3 exit

:p1
echo Ejecutando 1...
timeout /t 2 > nul
echo Finalizado
pause
goto menu


:p2 
echo Ejecutando 2...
echo Finalizar 
pause 
goto menu



