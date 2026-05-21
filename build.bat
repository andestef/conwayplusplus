::remove next line to debug this batch file
::@echo off
cd src
javac Conwayplusplus.java --module-path ../build/javafx-sdk-25.0.3\lib --add-modules javafx.controls -d ../build
cd ../build
del "conwayplusplus.exe"
jar cfm Conwayplusplus.jar Conwayplusplus Conwayplusplus$1.class
"C:\Program Files (x86)\Launch4j\launch4jc.exe" conf.xml
del "Conwayplusplus.class"
conwayplusplus
cd ../