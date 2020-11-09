# Testing

## Table of Contents
1. [Running tests](#1)
2. [Types of tests](#2)


--------------------------------------------------------------------------------------------------------------------

### 1. Running tests <a name="1"></a>

There are two ways to run tests.

* **Method 1: Using IntelliJ JUnit test runner**
  * To run all tests, right-click on the `src/test/java` folder and choose `Run 'All Tests'`
  * To run a subset of tests, you can right-click on a test package,
    test class, or a test and choose `Run 'ABC'`
* **Method 2: Using Gradle**
  * Open a console and run the command `gradlew clean test` (Mac/Linux: `./gradlew clean test`)

Read this [Gradle Tutorial](https://se-education.org/guides/tutorials/gradle.html) from se-edu/guides to learn more about using Gradle.


--------------------------------------------------------------------------------------------------------------------

### 2. Types of tests <a name="2"></a>

This project uses JUnit testing in IntelliJ.

1. We test each class and its methods.<br>
   e.g. `seedu.modTrackerTest.ParserTest` Tests the methods in the `Parser` class

[Home Page](https://ay2021s1-cs2113t-f12-4.github.io/tp/)