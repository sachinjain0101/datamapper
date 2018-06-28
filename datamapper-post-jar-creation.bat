@ECHO OFF
ECHO.
ECHO ------------------------------------------------
ECHO Starting post build steps after JAR creation
ECHO ------------------------------------------------
ECHO.
ECHO.
ECHO ************************************************
ECHO Deleting service
ECHO ************************************************
sc delete datamapper
ECHO.
ECHO.
ECHO ************************************************
ECHO Deleting service setup files
ECHO ************************************************
del /F /Q %cd%\target\datamapper.exe
del /F /Q %cd%\target\datamapper.xml
ECHO.
ECHO.
ECHO ************************************************
ECHO Copying resources/* to target/
ECHO ************************************************
cp %cd%\resources\datamapper.exe %cd%\target\
cp %cd%\resources\datamapper.xml %cd%\target\
ECHO.
ECHO.
ECHO ************************************************
ECHO Installing service
ECHO ************************************************
cd %cd%\target
echo %cd%
datamapper.exe install
ECHO.
ECHO.
ECHO ************************************************
ECHO Starting service
ECHO ************************************************
sc start datamapper
ECHO.
ECHO.
EXIT /B