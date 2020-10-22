# Developer Guide

## Table of content
1. [Introduction](#1-introduction)<br>
1.1. [Overview](#11-overview)<br>
1.2. [Purpose](#12-purpose)<br>
1.3. [Scope](#13-scope)<br>
2. [Setting Up](#2-setting-up)<br>
2.1. [Prerequisites](#21-prerequisites)<br>
2.2. [Setting up the project in your computer](#22-setting-up-the-project-in-your-computer)<br>
3. [Design](#3-design)<br>
3.1. [Architecture](#31-architecture)<br>
4. [Implementation](#4-implementation)<br>
4.1. [Print prompt feature](#41-print-prompt-feature)<br>
4.2. [Remove feature](#42-remove-feature)<br>
4.3. [Revise feature](#43-revise-feature)<br>
4.4. [List feature](#44-list-feature)<br>
4.5. [Add flashcard feature](#45-add-flashcard-feature)<br>
4.6. [Scheduler feature](#46-scheduler-feature)<br>
4.7. [History feature](#47-history-feature)<br>
5. [Appendix: Requirements](#5-appendix-requirements)<br>
5.1. [Product scope](#51-product-scope)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.1. [Target user profile](#511-target-user-profile)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.2. [Value proposition](#512-value-proposition)<br>
5.2. [User Stories](#52-user-stories)<br>
5.3. [Use Cases](#53-use-cases)<br>
5.4. [Non-Functional Requirements](#54-non-functional-requirements)<br>
5.5. [Glossary](#55-glossary)<br>
6. [Appendix: Instructions for manual testing](#6-appendix-instructions-for-manual-testing)<br>

## 1. Introduction

### 1.1. Overview

### 1.2. Purpose

### 1.3. Scope
This documentation describes the software architecture and software design decisions for the implementation of Kaji. The intended audience of this document is the developers, designers, and software testers of Kaji.

## 2. Setting Up

### 2.1. Prerequisites
* JDK 11
* IntelliJ IDEA
### 2.2. Setting up the project in your computer
1. Fork this repository, and clone the fork into your computer.
2. Open IntelliJ (if you are not in the welcome screen, click `File` → `Close Project` to close the existing project dialog first).
3. Set up the correct JDK version for Gradle.
    1. Click `Configure` → `Project Defaults` → `Project Structure`.
    2. Click `New…` and set it to the directory of the JDK.
4. Click `Import Project` (or `Open or Import` in newer version of Intellij).
5. Locate the `build.gradle` file (not the root folder as you would do in a normal importing) and select it. Click `OK`.
If asked, choose to `Open as Project` (not `Open as File`).
7. Click `OK` to accept the default settings

## 3. Design

### 3.1. Architecture

The Architecture Diagram given above explains the high-level design of the App. Given below is a quick overview of each component.

The main class is `Kaji.java`. It is responsible for:

* Initializing the components in the correct sequence, and connects them up with each other at app launch.
* Shutting down the components and invoking cleanup methods where necessary at exit.

The rest of the App consists of 8 components:
* `Access`: Keeps track of the access level of the user.
* `UI`: The UI of the App.
* `Command`: Executes the different command types.
* `Common`: Contains common classes.
* `Manager`: Holds the data of the App in memory.
* `Paser`: Parses user input into specific command type.
* `Scheduler`: Schedules the revision schedule.
* `Storage`: Reads data from, and writes data to, the hard disk.


## 4. Implementation

### 4.2. Remove feature

#### 4.2.1. Implementation

The remove mechanism is executed by `RemoveCommand`. It extends from the abstract class `Command`. 
In addition, it implements the following operations:
* `RemoveCommand#execute()` — checks the `Access` level of user and calls the respective remove methods.
* `RemoveCommand#removeModule()` — removes module from list of modules based on the index provided and the chapters 
and flashcards under it.
* `RemoveCommand#removeChapter()` — removes chapter from list of chapter based on the index provided and 
the flashcards under it.
* `RemoveCommand#removeCard()` — removes flashcard from list of flashcards based on the index provided.

Given below is an example usage scenario and how the remove mechanism behaves at each step:

Step 1: The user launches the application and is currently in the module level. 

Step 2: The user executes `remove 1` command to delete the first module in the list of modules. 
The `remove` command creates `RemoveCommand` which will then be executed. 

Step 3: `RemoveCommand#execute` gets the module object based on the index provided and calls 
`Storage#deleteDirectory` to delete the module folder as well as the chapters and flashcards under it. 


### 4.3. Revise feature

#### 4.3.1. Implementation

The revise mechanism is executed by `ReviseCommand`. It extends from the abstract class `Command`. 
In addition, it implements the following operations:
* ReviseCommand#execute() — oversees the entire revise process and calls the respective methods when necessary.
* ReviseCommand#getChapter() — gets Chapter 
* RemoveCommand#getCards() — removes chapter from list of chapter based on the index provided and 
the flashcards under it.
* ReviseCommand#reviseCard() — removes flashcard from list of flashcards based on the index provided.
* ReviseCommand#addHistory() — adds the revision history to a storage to track past revisions.
* ReviseCommand#rateCard() — gets user input on difficulty of a flashcard.
* ReviseCommand#repeatRevision() — repeats revision for cards which user could not answer. 

Given below is an example usage scenario and how the revise mechanism behaves at each step:

Step 1: The user launches the application and is currently in the module level.

Step 2: The user executes `revise 1` command to revise the first chapter in the module. The `revise` command creates `ReviseCommand` which will then be executed.

Step 3: `ReviseCommand#execute` gets the chapter object based on the index provided as well as the flashcards under the particular chapter.

Step 4: Each flashcard will be shown to the user one by one and the `Ui#getInput` is called each time to get user input to rate the difficulty of the questions. 

Step 5: `ReviseCommand#repeatRevision` then repeats the revision session on cards which the user could not answer.

Step 6: `ReviseCommand#addHistory` will call `Storage#createHistory` and `Storage#saveHistory` to keep a record of the chapter revised so that the user can look back next time.


## 5. Appendix: Requirements
### 5.1. Product scope
#### 5.1.1. Target user profile

* needs to have an effective study schedule
* prefers typing to mouse interactions
* is comfortable with the usage of CLI applications

#### 5.1.2. Value proposition

* implements Spaced Repetition for the user 

### 5.2. User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|revise the flashcards by chapter|do my revision|
|v1.0|user|remove modules/chapters/flashcard|remove modules/chapters/flashcards that I no longer need from the scheduler|

### 5.3. Use Cases


### 5.4. Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### 5.5. Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command Line Interface

## 6. Appendix: Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}


## Implementation
### Print prompt feature
#### Proposed implementation
`<Ui>` and `<Acess>` facilitate the proposed print prompt feature. The `<Access>` class stores user's temporary 
access level as a variable `<Access#level>`. Before any read command execution in `<Kaji>`, `<Ui#showLevel(Access)>` 
method runs and prints user's current access level. 

`<Access>` are exposed in both the `<GoCommand>` class and `<BackCommand>` class as `<Access#setChapterLevel()>` and 
`<Access#setModuleLevel()>` respectively.

![Class Diagram of print prompt](UML/printPrompt1.png)

Given below is an example usage scenario and how the print prompt feature behaves at each step.

Step 1. The user launches the application. The `<Access>` will be initialized with the initial access level 
which is the admin level.

Step 2. In the `<Kaji>`, the `<Ui>` will also be initialized, then `<Kaji>` calls `<Ui#showLevel(Access)>` 
to get the access level from `<Access>` and prints the prompt.

Given below is another example usage scenario and how the print prompt feature behaves at each step.

Step 1. The user executes the `<GoCommand>` to modify access level from admin to module. The `<GoCommand>` modifies 
`<Access>` via the `<Access#setModuleLevel()>` method. 

Step 2. In the `<Access>` instance, `<Access>` call itself `<Access#setLevel()>` to modify the `<Access#level>`
variable. 

Step 3. `<Ui#showLevel(Access)>` method therefore prints different prompt based on the modification in `<Access>`.

The following sequence diagram shows how the `<GoCommand>` modify `<Access>` and results in different prompt.
![Sequence Diagram of print prompt](UML/printPrompt2.png)

#### Design consideration
##### Aspect: When access prompt shows
* **Alternative 1 (current choice)**: Keep a variable `<Access#level>` in Access class.
    * Pros: Easy to implement and easy for the Kaji.class to access.
    * Cons: Use up memory usage and hard to debug.
* **Alternative 2**: Use `<Access#chapterLevel>`, `<Access#moduleLevel>` and `<Access#adminLevel>` every time before print prompt in Kaji.class.
    * Pros: Will use less memory and increases testability.
    * Cons: We must access three variables before printing new prompt, unnecessary waste of CPU.