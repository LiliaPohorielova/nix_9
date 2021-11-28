@echo off
call mvn clean install
call java -jar .\target\maven.jar
