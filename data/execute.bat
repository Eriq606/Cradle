set projectJar="../[appname]_web/WEB-INF/lib/[appname].jar"

@echo off
echo Menu principal:
echo 1) Compiler
echo 2) Compresser vers l'app web
echo 3) Deployer vers tomcat
echo 4) Generer une classe a partir de la base
echo 5) Generer un controller
echo 6) Generer une page
set /p choice=Votre choix:

IF "%choice%"=="1" GOTO COMPILE
IF "%choice%"=="2" GOTO COMPRESS
IF "%choice%"=="3" GOTO DEPLOY
IF "%choice%"=="4" GOTO GENESIS
IF "%choice%"=="5" GOTO EXODUS
IF "%choice%"=="6" GOTO FACADE
GOTO END

:COMPILE
.\tools\compile.bat
GOTO END

:COMPRESS
.\tools\compress.bat
GOTO END

:DEPLOY
.\tools\deploy.bat
GOTO END

:GENESIS
cd tools
java -jar Genesis.jar
GOTO END

:EXODUS
cd tools
java -jar Exodus.jar
GOTO END

:FACADE
cd tools
java  -jar Facade.jar "%projectJar%"
GOTO END