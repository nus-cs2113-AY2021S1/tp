# WatchNext Developer Guide


- [WatchNext Developer Guide](#watchnext-developer-guide)
  - [1. Introduction](#1-introduction)
    - [1.a Purpose](#1a-purpose)
    - [1.b Scope](#1b-scope)
  - [2. Setting up](#2-setting-up)
    - [2.a Prerequisites](#2a-prerequisites)
    - [2.b Setting up the project in your computer](#2b-setting-up-the-project-in-your-computer)
    - [2.c Verifying the setup](#2c-verifying-the-setup)
    - [2.d Before Writing Code](#2d-before-writing-code)
  - [3. Design](#3-design)
  - [4. Implementation](#4-implementation)
    - [Add](#add)
    - [Edit](#edit)
  - [5. Documentation](#5-documentation)
  - [6. Testing](#6-testing)
  - [7. Dev Ops](#7-dev-ops)
  - [Appendices](#appendices)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
  - [User Stories](#user-stories)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
  - [Instructions for manual testing](#instructions-for-manual-testing)
   
## 1. Introduction

### 1.a Purpose

This guide aims to provide information for you: the developers, testers and future contributors of **WatchNext** 
such that you will have an easy reference for understanding the features implemented in **WatchNext**.


### 1.b Scope

The guide outlines the architecture and design decisions for the implementation of WatchNext.The intended audience of this 
document are the developers, testers and future contributors of WatchNext.
   

## 2. Setting up
This section will show you the requirements that you need to fulfill in order to quickly start contributing to this project in no time!

### 2.a Prerequisites

1. **JDK `11`**  

[NOTE]
The `WatchNext.jar` file is compiled using the Java version mentioned above. +

2. **IntelliJ IDEA** IDE

[NOTE]
IntelliJ has Gradle and JavaFx plugins installed by default.
Do not disable them. If you have disabled them, go to `File` > `Settings` > `Plugins` to re-enable them.

### 2.b Setting up the project in your computer
 
 **Fork** this [repo](https://github.com/AY2021S1-CS2113T-W12-3/tp), and clone the fork to your computer.
 
1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)

2. You should set up the correct JDK version for Gradle

3. Click `Configure` > `Project Defaults` > `Project Structure`

4. Click `New...` and find the directory of the JDK

5. Click `Import Project`

6. Locate the `build.gradle` file and select it. Click `OK`

7. Click `Open as Project`

8. Click `OK` to accept the default settings

9. Open a console and run the command `gradlew processResources` (Mac/Linux: `./gradlew processResources`). It should finish with the `BUILD SUCCESSFUL` message. +
This will generate all the resources required by the application and tests.

### 2.c Verifying the setup

1. You can run `Duke` and try a few commands.

2. You can also run tests using our instructions for manual testing to explore our features.

### 2.d Before Writing Code
 
 1. Set up CI
 
 This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the master branch or to any PR. No set up required.
 
 2. Learn the design
 
 When you are ready to start coding, we recommend that you get some sense of the overall design by reading about WatchNext’s architecture [here](#3-design).

## 3. Design
WatchNext was designed drawing from the ideas of the __Event-driven architectural style__. <br>
<br>The Ui and the Scanner components work together as event emitters. The <code>Ui</code> class prompts the user for input, and the scanner is ready to receive the input. Should the format of the input be unrecognised or incorrect, the <code>Ui</code> class guides the user with prompts to rectify the errors.<br>
<br>Events will be passed onto the <code>InputParser</code> which serves as the dispatcher. The <code>InputParser</code> uses various string manipulation operations from the <code>StringOperations</code> class to recognise the intention of the user input. After recognising the command, the input will be parsed, and the command information will be passed onto the various command classes for processing. The <code>InputParser</code> communicates the events to event consumers which are the command classes in this case. <br>
<br>All available operations will be processed by the classes in the commands package. Every command class, like the <code>AddCommand</code> class, inherits from the <code>Command</code> class. Each individual command class is able to contain all the processing required for each command with the inputs passed in during the initiation of each command object. <br>
<br>During runtime, the show related data is all stored in the <code>ShowList</code> class. The data is accessible and can be modified statically by all the command classes. The <code>ShowList</code> contains <code>Show</code> objects which describes the attributes of a show. 
<br>Certain commands relating to the monitoring of the amount of time users spend watching shows depend on information from the <code>WatchTime</code> class. The class tracks the date and time remaining for the users to watch shows for the day. The time limit will be set by the user. <br>
<br>On the initiation of WatchNext, the <code>Storage</code> object will be initiated and retrieves any user data that has been saved from previous runs. The data is stored in plain text and can be manually edited by advanced users. The data is stored in <code>data/showList.txt</code>. After the execution of every command, the <code>Storage</code> object calls upon the save command to automatically update the save data file. The commands relating to saving and loading data can be accessed from the <code>SaveState</code> interface.<br>
<br>Throughout the lifespan of the program, various errors may occur. The <code>ErrorHandling</code> class stores the various errors that could occur. The expected errors usually stem from invalid user input or Input Output (IO) errors during file loading. The <code>Ui</code> class informs the users of the errors detected and suggests actions for rectification. <br>

## 4. Implementation

### Add

The `add` command allows users to add a new show which they are watching to the `ShowList`. It is invoked by the inputParser. The addCommand class checks for the correct number of inputs and throws an exception when the number of arguments is mismatched.
### Edit 

The `edit` command allows the user to change the details of each show that they are waatching after they have added the show. It is self-contained, including its own parser and methods which allows the user to change any parameter they wish, after the user enters `done`, `edit` replaces the old entry with the updated one.

## 5. Documentation

This project comes with 2 pieces of documentation, the developers' guide, which you are reading right now and the user guide, which helps new users get acquainted with the program.


## 6. Testing

We have written J-Unit test for the main functionalities for the program, such as `command` classes. The test can be found under `/src/test`.

When using gradle to build the project, these tests are run automatically and will catch any runtime errors. If you have added new functionality, please remember to add a J-Unit test for the new functionality.

## 7. Dev Ops

When the project is finalised and released, if you find any bugs or problems, or if you have suggestions for new functionality, please create a new issue on our [github page](https://github.com/AY2021S1-CS2113T-W12-3/tp/issues).

##  Appendices


### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v2.0|new user|limit my watching time sometimes|I do not get carried away and watch too many shows in one sitting|
|v1.0|user|be able to share my watch history |I can export and share the shows that I like with my friends.|
|v1.0|show enthusiast|revisit my ratings for shows i have watched|change the rating in the event that i want to.|
|v1.0|show enthusiast|know which current episode i am at|continue watching the show later.|
|v1.0|student|track my watchtime|not miss my deadlines.|
|v1.0|show enthusiast|revisit my ratings for shows i have watched|change the rating in the event that i want to.|
|v1.0|user|clear my watch history |I can protect my privacy.|
|v1.0|student|I want to track which zoom lectures / or webcasts that I have watched| I can make sure I don’t miss any important lessons.|


## Non-Functional Requirements

1. WatchNext will work on any mainstream OS as long as it has Java 11 installed.

2. Users who can type fast and prefer typing over other means of input should be able to use WatchNext faster using commands than using the mouse in a GUI(Graphic User Interface)-based program.

## Glossary

* *Graphic User Interface* - It is a user interface that includes graphical elements, such as windows, icons and buttons.

## Instructions for manual testing

**[NOTE]** The instructions and sample test cases only act as a guide for you to start testing on some of our application features. You are free to test our features with more test cases of your own. Refer to [Section 2.a,“Prerequisites”](#2a-prerequisites) for the instructions to set up our program on your computer.

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
