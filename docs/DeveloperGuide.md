# Developer Guide

## Table of content
1. [<b>Introduction</b>](#1-introduction)<br>
1.1. [Overview](#11-overview)<br>
1.2. [Purpose](#12-purpose)<br>
1.3. [Scope](#13-scope)<br>
2. [<b>Setting Up</b>](#2-setting-up)<br>
2.1. [Prerequisites](#21-prerequisites)<br>
2.2. [Setting up the project in your computer](#22-setting-up-the-project-in-your-computer)<br>
3. [<b>Design</b>](#3-design)<br>
3.1. [Architecture](#31-architecture)<br>
4. [<b>Implementation</b>](#4-implementation)<br>
4.1. [Print prompt feature](#41-print-prompt-feature)<br>
4.2. [Remove feature](#42-remove-feature)<br>
4.3. [Revise feature](#43-revise-feature)<br>
4.4. [List feature](#44-list-feature)<br>
4.5. [Add flashcard feature](#45-add-flashcard-feature)<br>
4.6. [Scheduler feature](#46-scheduler-feature)<br>
4.7. [History feature](#47-history-feature)<br>
4.8. [Exclusion feature](#48-exclusion-feature)<br>
5. [<b>Appendix: Requirements</b>](#5-appendix-requirements)<br>
5.1. [Product scope](#51-product-scope)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.1. [Target user profile](#511-target-user-profile)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.2. [Value proposition](#512-value-proposition)<br>
5.2. [User Stories](#52-user-stories)<br>
5.3. [Use Cases](#53-use-cases)<br>
5.4. [Non-Functional Requirements](#54-non-functional-requirements)<br>
5.5. [Glossary](#55-glossary)<br>
6. [<b>Appendix: Instructions for manual testing</b>](#6-appendix-instructions-for-manual-testing)<br>

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

### 4.1. Print prompt feature

#### 4.1.1. Implementation

`Ui` and `Access` facilitate the proposed print prompt feature. The `Access` class stores user's temporary 
access level as a variable `Access#level`. Before any read command execution in `Kaji`, `Ui#showLevel(Access)` 
method runs and prints user's current access level. 

`Access` are exposed in both the `GoCommand` class and `BackCommand` class as `Access#setChapterLevel()` and 
`Access#setModuleLevel()` respectively.

![Class Diagram of print prompt](UML/printPrompt1.png)

Given below is an example usage scenario and how the print prompt feature behaves at each step.

Step 1. The user launches the application. The `Access` will be initialized with the initial access level 
which is the admin level.

Step 2. In the `Kaji`, the `Ui` will also be initialized, then `Kaji` calls `Ui#showLevel(Access)` to get 
the access level from `Access` and prints the prompt.


Given below is another example usage scenario and how the print prompt feature behaves at each step.

Step 1. The user executes the `GoCommand` to modify access level from admin to module. The `GoCommand` modifies 
`Access` via the `Access#setModuleLevel()` method. 

Step 2. In the `Access` instance, `Access` call itself `Access#setLevel()` to modify the `Access#level`
variable. 

Step 3. `Ui#showLevel(Access)` method therefore prints different prompt based on the modification in `Access`.

The following sequence diagram shows how the `GoCommand` modify `Access` and results in different prompt.
![Sequence Diagram of print prompt](UML/printPrompt2.png)


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

### 4.4. List feature
#### 4.4.1. Implementation

The revise mechanism is executed by `ListCommand`. It extends from the abstract class `Command`. 
In addition, it implements the following operations:
* `ListCommand#execute()` — checks the `Access` level of user and calls the respective list methods. 
* `ListCommand#listModules()` — lists all modules in admin level
* `ListCommand#listChapters()` — lists all chapters in module level
* `ListCommand#listCards()` — lists all flashcards in chapter level

Given below is an example usage scenario and how the list mechanism behaves at each step:

Step 1: The user launches the application and is currently in the module level. 

Step 2: The user executes `list` command to list all chapters in current module. 

### 4.5. Add flashcard feature
#### 4.5.1. Implementation
The add flashcard mechanism is facilitated by `AddCommand`. It extends from the abstract class `Command`.
In addition, it implements the following operations:
* `AddCommand#addCard()` — Adds a new flashcard to a chapter.

Given below is an example usage scenario and how the add flashcard mechanism behaves at each step.

Step 1: The user is using the application and is currently in the chapter level.

Step 2: The user executes `add q:1+1 | a:2` command to add a flashcard with question `1+1` and answer `2`. The `add` command creates `AddCommand` which will then be executed.

Step 3: `AddCommand#execute` gets the `CardList` object based on the chapter and add the flashcard to the `CardList` object.

### 4.6. Scheduler feature
#### 4.6.1. Implementation
In KAJI, each `Chapter` stores a `CardList` of `Card`s, each with their own `int` attribute `previousInterval`. Each `Chapter` also has a `LocalDate` attribute named `dueBy` that determines when the `Chapter` is due for revision. 
The Scheduler mechanism implements Spaced Repetition by computing the `deckInterval`, the mean (rounded off to the nearest integer) of the `previousInterval`s of every `Card` within the `Chapter`, and updates the `dueBy` attribute to `deckInterval` days after the day of revision.

`Scheduler` implements the following operations:
* `Scheduler#computeEasyInterval()`
* `Scheduler#computeMediumInterval()`
* `Scheduler#computeHardInterval()`
* `Scheduler#computeDeckInterval()`
* `Scheduler#computeDeckDeadline()`

`Scheduler#computeEasyInterval()`, `Scheduler#computeMediumInterval()` and `Scheduler#computeHardInterval()` are exposed in the `ReviseCommand` class as `ReviseCommand#rateCard()` while `Scheduler#computeDeckDeadline()` is exposed as `ReviseCommand#execute()`.


#### 4.6.2 Prerequisites
* There must at least be one `Chapter` with at least one `Card` in the `CardList` attribute of said `Chapter`

#### 4.6.3 Example
Given below is an example usage scenario and how the Scheduler mechanism behaves at each step when: 
`revise 1` is called in a `Module` that contains only one `Chapter` with three `Card`s in its `CardList` attribute and confirmation is given to proceed with revision. (For more details on revision, refer to [Revise feature](#43-Revise-feature))

Step 1:
* The user enters `revise 1` within the `Module` and `ReviseCommand` is instantiated. 
* Upon confirmation and check that `CardList` of the designated `Chapter` is not empty, `ReviseCommand` proceeds to create an `ArrayList<Card>` named `allCards` comprising of all `Card`s within in the `CardList`.

Step 2:
* For each `Card` in `allCards`, `ReviseCommand#reviseCard()` is called upon completion of either `ReviseCommand#execute()` or `ReviseCommand#repeateRevision()`.
* This operation then calls `Scheduler#computeEasyInterval()`, `Scheduler#computeMediumInterval()` or `Scheduler#computeHardInterval()` depending on the user input to compute and update the new value of `previousInteral` for each card.

Step 3:
* Upon completion of all revision, `ReviseCommand#execute()` will call `Scheduler#computeDeckDeadline()`, which in turn calls `Scheduler#computeDeckInterval()`. `Scheduler#computeDeckInterval()` computes `deckInterval`, the mean (rounded off to the nearest integer) of the `previousInterval`s of each `Card` in `allCards,` and returns it to `Scheduler#computeDeckDeadline()`.

Step 4:
* Using `deckInterval`, `Scheduler#computeDeckDeadline()` computes the new value of `dueBy` for the Chapter, which is then returned to `ReviseCommand#execute()`, where it will then update the value of `dueBy` for the `Chapter` that was just revised.

### 4.7. History feature
#### 4.7.1. Implementation

The history mechanism is executed by `HistoryCommand`. It extends from the abstract class `Command`. 
In addition, it implements the following operations:

* `HistoryCommand#execute()` — calls the list method to list the history. 
* `HistoryCommand#listHistory()` — lists the revision completed in the session/in a day.

Given below is an example usage scenario and how the history mechanism behaves at each step:

Step 1: The user launches the application and is currently in the admin level. 

Step 2: The user executes `history` command to load and list the revision completed in the session/in a day.

### 4.8 Exclusion Feature
##### 4.8.1 Implementation
An ArrayList of `DueChapter`s comprising the `Chapter`s that are to be excluded from the scheduling is stored externally in a text file at relative path "/data/admin/exclusions.txt", and is maintained by `Storage` and `ExcludeCommand`.

`Storage` implements the following relevant operations:
* `Storage#appendModuleToExclusionFile()` - includes every `Chapter` in the specified `Module` to the exclusion text file.
* `Storage#appendChapterToExclusionFile()` - include the specified `Chapter` to the exclusion text file.
* `Storage#removeModuleFromExclusionFile()` - removes every `Chapter` in the specified `Module` from the exclusion text file.
* `Storage#removeChapterFromExclusionFile()` - remove the specified `Chapter` from the exclusion text file.
* `Storage#updateExclusionFile()` - writes the `ArrayList<DueChapter>` of excluded chapters into the exclusion text file.
* `Storage#loadExclusionFile()` - retrieves the `ArrayList<DueChapter>` of excluded chapters from the exclusion text file.

#### 4.8.2 Prerequisites
* The application must have read and write access to both the "data/admin" folder and to the file "data/admin/exclusions.txt" if the file already exists at excecution.
    * If these requisites are not met, an ExclusionFileException will be thrown.

#### 4.8.3 Example
Given below is an example usage scenario and how the remove mechanism behaves at each step:

Step 1: 
* The user launches the application with a database of flashcards without any exclusions. 

Step 2: 
* The user executes `exclude more` command to exclude a part of the database from Scheduling and specifies that a `Module` is to be excluded. 
* The `exclude` command calls `Storage#appendModuleToExclusionFile()` which in turn calls the `Storage#loadExclusionFile()`. 
* `Storage#loadExclusionFile()` loads the existing list of excluded chapters, which is empty, as a `ArrayList<DueChapter>`. 

Step 3: 
* `Storage#appendModuleToExclusionFile()` calls `Storage#loadChaptersFrom()` to create a `String[]` comprising the names of the `Chapter`s belonging to the specified `Module`.

Step 4: 
* `Storage#appendModuleToExclusionFile()` then creates each `DueChapter`s from each `String` and adds them to the `ArrayList<DueChapter>` if they do not already exist.

Step 5: 
* `Storage#appendModuleToExclusionFile()` then calls `Storage#updateExclusionFile()` to store the updated `ArrayList<DueChapter>` into the exclusion text file.

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