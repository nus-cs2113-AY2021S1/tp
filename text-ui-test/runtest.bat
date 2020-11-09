@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

IF EXIST .\data\data.json DEL /F .\data\data.json

REM java -jar %jarloc% < ..\..\text-ui-test\input.txt > ..\..\text-ui-test\ACTUAL.TXT
java -ea -cp %jarloc% com.scrumptious.test.TextUiTest < ..\..\text-ui-test\input.txt > ..\..\text-ui-test\ACTUAL.TXT

cd ..\..\text-ui-test

FC /w ACTUAL.TXT EXPECTED.TXT >NUL && ECHO Test passed! || Echo Test failed!
