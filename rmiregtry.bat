@echo off
IF NOT EXIST "jrobot/classes/." (
echo Error: jrobot/Classes directory not found!
) ELSE (
echo Loading RMI REGISTRY...
path C:\Program Files\Sun\AppServer\jdk\bin
cd .\jrobot\classes
start rmiregistry
cd..)
