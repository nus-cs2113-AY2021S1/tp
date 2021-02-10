# Testing Guide

![Trippie Logo](https://i.imgur.com/jSwGL7O.png)

<!-- @@author ShawnTanzc -->
## Table of Content
* [1.0 Introduction](#10-Introduction)
* [2.0 Running tests](#20-Running-tests)
* [3.0 Types of tests](#30-Types-of-tests)

## 1.0 Introduction

The following content entails a list of tests to aid you in testing the application.

## 2.0 Running tests

There are 2 methods to run tests on the Trippie programme.
- Method 1: Using IntelliJ JUnit test runner
    - To run all test, right-click on the `src/test/java` folder and choose  `Run 'Test in 'tp.test''`.
    - To run a subset of tests, you can right-click on a test package, test class, or a single test and select `Run <TEST NAME>`
- Method 2: Using Gradle
    - `gradlew clean test` for Windows
    - `./gradlew clean test` for Mac and Linux
- Read [this Gradle Tutorial from the se-edu/guides](https://se-education.org/guides/tutorials/gradle.html) to learn more about using Gradle.

## 3.0 Types of tests
This project has three types of tests:
1. Unit tests targeting the lowest level methods/classes.
eg. `src.test.java.seedu.trippie.command.CalculateCurrencyCommandTest`.
2. Integration testing that are checking the integration of multiple code units (those code units are assumed to be working).
eg. `src.test.java.seedu.trippie.command.ListExpenseCommandTest`.
3. Hybrids of unit and integration tests. These test are checking multiple code units as well as how they are connected together.
eg. `src.test.java.seedu.trippie.command.DeleteExpenseCommandTest`.
<!-- @@author-->