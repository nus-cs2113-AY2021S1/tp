# Developer Guide

## Table of Content
1. [Introduction](#1-introduction)
<br/>&nbsp;1.1 [Purpose](#11-purpose)
<br/>&nbsp;1.2 [Using this Guide](#12-using-this-guide)
2. [Setting up](#2-setting-up-and-getting-started)
3. [Design](#3-design)
<br/>&nbsp;3.1 [Architecture](#31-architecture)
<br/>&nbsp;3.2 [UI Component](#32-ui-component)
<br/>&nbsp;3.3 [Parser Component](#33-parser-component)
<br/>&nbsp;3.4 [Command Component](#34-command-component)
<br/>&nbsp;3.5 [AnimeData Component](#35-animedata-component)
<br/>&nbsp;3.6 [User Component](#36-user-component)
<br/>&nbsp;3.7 [StorageManager Component](#37-storagemanager-component)
4. [Implementation](#4-implementation)
<br/>&nbsp;4.1 [Workspace Feature](#41-workspace-feature)
<br/>&nbsp;4.2 [Estimation Feature](#42-estimation-feature)
<br/>&nbsp;4.3 [Bookmark Feature](#43-bookmark-feature)
<br/>&nbsp;4.4 [Browse Feature](#44-browse-feature)
5. [Produce scope](#5-product-scope)
<br/>&nbsp;5.1 [Target user profile]()
<br/>&nbsp;5.2 [Value proposition]()
6. [User stories](#6-user-stories)
7. [Non-functional requirements](#7-non-functional-requirements)
8. [Documentation, logging, testing, configuration, dev-ops](#8-documentation-logging-testing-configuration-dev-ops)
9. [Glossary](#9-glossary)
10. [Appendices](#10-appendices)
<br/>&nbsp;10.1 [Instructions for manual testing]()


## 1. Introduction
**AniChan** is a command-line application written in **Java 11**. It is written using the Object-Oriented Programming (OOP) paradigm which provides us with means to structure a software program into organized, reusable and reusable pieces of code that makes it good for future improvements and revisions.

### 1.1 Purpose

This content of this guide is aimed at current and new developers of AniChan. It contains the basic steps to set up a development environment, organize your source code, and then build and test AniChan. This guide also aids developers in understanding the overall architecture design and lays out the current implementation details of our notable features with the rationale and considerations behind each one.

### 1.2 Using this Guide
The content of this developer guide is aimed at both current and new developers who are keen on contributing to AniChan. The guide will contain the basic steps to using AniChan to set up a development environment, organize your source code, and then build and test your application. This developer guide is an essential tool that will introduce you to the various features and design concepts which you can use to further develop and maintain AniChan. 
<br/>


## 2. Setting Up
### Setting up the project in your computer

Ensure that you have the following installed: 
* JDK 11.
* IntelliJ IDE (highly recommended).

Firstly, **fork** this repo and **clone** a copy into your computer.

If you plan to use Intellij IDEA: 
1. **Ensure IntelliJ is configured to use JDK 11**.
    1. Click on `Configure` > `Structure for New Projects` > `Project Settings` > `Project`, 
       and ensure the `Project SDK` is using **JDK 11**.
2. **Import the project as a Gradle project**.
    1. Click on `Import Project` and locate the `build.gradle` file and select it. Click `OK`
    2. If asked, choose to `Open as Project` (not `Open as File`).
    3. Click `OK` to accept the default settings but do ensure that the selected version of `Gradle JVM` matches the JDK being used for the project.
    4. The import process could take a few minutes to finish.
3. **Verify the setup**: 
    1. Run the `seedu.anichan.Main` and try a few commands. 
    2. You may want to refer to our [User Guide](UserGuide.md) for the list of commands.

----

### Before writing code
1. **Configuring the coding style**

    If using IDEA, follow this guide 
    [IDEA: Configuring the code style](https://se-education.org/guides/tutorials/intellijCodeStyle.html) 
    to setup IDEA’s coding style to match ours.

2. **Set up CI**

    There is no set up required as the project comes with a GitHub Actions config files, 
    located in `.github/workflows` folder. When GitHub detects these files, it will run the CI for the project
    automatically at each push to the master branch or to any PR.

3. **Learn the design**

    When you are ready to start writing codes, 
    we recommended that you have a look at AniChan's overall design 
    by reading about it at [AniChan's architecture](DeveloperGuide.md#31-architecture).

<br/>

## 3. Design 