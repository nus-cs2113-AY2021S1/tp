# DevOps Guide

## Table of contents
1. [Build automation](#1)
2. [Making a release](#2)
--------------------------------------------------------------------------------------------------------------------

## 1. Build automation <a name="1"></a>

This project uses Gradle for **build automation and dependency management**.


Given below are how to use Gradle for some important project tasks.


* **`clean`**: Deletes the files created during the previous build tasks (e.g. files in the `build` folder).<br>
  e.g. `./gradlew clean`

* **`shadowJar`**: Uses the ShadowJar plugin to creat a fat JAR file in the `build/lib` folder, *if the current file is outdated*.<br>
  e.g. `./gradlew shadowJar`.

* **`run`**: Builds and runs the application.<br>
  **`runShadow`**: Builds the application as a fat JAR, and then runs it.

* **`checkstyleMain`**: Runs the code style check for the main code base.<br>
  **`checkstyleTest`**: Runs the code style check for the test code base.

* **`test`**: Runs all tests.<
  * `./gradlew test` — Runs all tests
  * `./gradlew clean test` — Cleans the project and runs tests

--------------------------------------------------------------------------------------------------------------------

## 2. Making a release <a name="2"></a>

Here are the steps to create a new release.

1. Update the version number in [`MainApp.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java).
2. Generate a fat JAR file using Gradle (i.e., `gradlew shadow`).
3. Tag the repo with the version number. e.g. `v0.1`
4. [Create a new release using GitHub](https://help.github.com/articles/creating-releases/). Upload the JAR file you created.
5. We have created 3 releases.**V1.0, V2.0 and V2.1**

[Home Page](https://ay2021s1-cs2113t-f12-4.github.io/tp/)