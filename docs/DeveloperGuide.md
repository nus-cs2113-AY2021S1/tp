# Developer Guide

## Table of content
1. [<b>Introduction</b>](#1-introduction)<br>
1.1. [Overview](#11-overview)<br>
1.2. [Purpose](#12-purpose)<br>
1.3. [Scope](#13-scope)<br>
2. [<b>Setting Up</b>](#2-setting-up)<br>
2.1. [Prerequisites](#21-prerequisites)<br>
2.2. [Setting Up the Project in your Computer](#22-setting-up-the-project-in-your-computer)<br>
3. [<b>Design (Architecture)</b>](#3-design-architecture)<br>
3.1. [Ui Component](#31-ui-component)<br>
3.2. [Logic Component](#32-logic-component)<br>
3.3. [Model Component](#33-model-component)<br>
3.4. [Storage Component](#34-storage-component)<br>
3.5. [Common Classes](#35-common-classes)<br>
4. [<b>Implementation</b>](#4-implementation)<br>
4.1. [Admin Features](#41-admin-features)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.1. [Add Module Feature](#411-add-module-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.2. [List Modules Feature](#412-list-modules-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.3. [Edit Module Name Feature](#413-edit-module-name-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.4. [Remove Module Feature](#414-remove-module-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.5. [Access Module Level Feature](#415-access-module-level-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.6. [Example of the Admin Feature](#416-example-of-the-admin-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.7. [Conclusion](#417-conclusion)<br>
4.2. [Module Features](#42-module-features)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.1. [Add Chapter Feature](#421-add-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.2. [List Chapters Feature](#422-list-chapters-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.3. [Edit Chapter Name Feature](#423-edit-chapter-name-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.4. [Remove Chapter Feature](#424-remove-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.5. [Access Chapter Level Feature](#425-access-chapter-level-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.6. [Return to Admin Level Feature](#426-return-to-admin-level-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.7. [Rate Chapter Feature](#427-rate-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.8. [Example of the Module Feature](#428-example-of-the-module-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.9. [Conclusion](#429-conclusion)<br>
4.3. [Chapter Features](#43-chapter-features)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.1. [Add Flashcard Feature](#431-add-flashcard-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.2. [List Flashcards Feature](#432-list-flashcards-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.3. [Edit Flashcard Content Feature](#433-edit-flashcard-content-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.4. [Remove Flashcard Feature](#434-remove-flashcard-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.5. [Return to Module Level Feature](#435-return-to-module-level-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.6. [Check Overall Performance for a Chapter Feature](#436-check-overall-performance-for-a-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.7. [Example of the Chapter Feature](#437-example-of-the-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.8. [Conclusion](#438-conclusion)<br>
4.4. [Revision Features](#44-revision-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.1. [Revision Feature](#441-revision-feature)<br>
4.5. [Scheduler feature](#45-scheduler-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.1. [View Due Chapters Feature](#451-view-due-chapters-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.2. [Preview Upcoming Dues Feature](#452-preview-upcoming-dues-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.3. [Exclusion Feature](#453-exclusion-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.4. [Reschedule Chapter Feature](#454-reschedule-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.5. [View Revision History Feature](#455-view-revision-history-feature)<br>
5. [<b>Appendix: Requirements</b>](#5-appendix-requirements)<br>
5.1. [Product Scope](#51-product-scope)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.1. [Target User Profile](#511-target-user-profile)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.2. [Value Proposition](#512-value-proposition)<br>
5.2. [User Stories](#52-user-stories)<br>
5.3. [Use Cases](#53-use-cases)<br>
5.4. [Non-Functional Requirements](#54-non-functional-requirements)<br>
5.5. [Glossary](#55-glossary)<br>
6. [<b>Appendix: Instructions for Manual Testing</b>](#6-appendix-instructions-for-manual-testing)<br>

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

### 1.1. Overview

### 1.2. Purpose

### 1.3. Scope
This documentation describes the software architecture and software design decisions for the implementation of Kaji. The intended audience of this document is the developers, designers, and software testers of Kaji.

--------------------------------------------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------------------------------------------

## 3. Design (Architecture)
(Zeyu)

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

### 3.1. Ui Component 
(Jia Ern)

The UI component consists of a main class — Ui.java. The Kaji, Logic and Storage components have a dependency on the UI component due to the need to take in user input and show the results of execution.

The UI component is responsible for:
* Taking in user input.
* Printing result of execution for the different commands. 
* Printing error messages when certain exceptions occur. 

### 3.2. Logic Component 
(Jane)

### 3.3. Model Component
(Jiayi)

### 3.4. Storage Component 
(Lucas)

### 3.5. Common Classes


--------------------------------------------------------------------------------------------------------------------

## 4. Implementation

### 4.1. Admin Features
[summary + scenario]

#### 4.1.1. Add Module Feature
(Jiayi)

#### 4.1.2. List Modules Feature
(Zeyu)

#### 4.1.3. Edit Module Name Feature
(Jane)

#### 4.1.4. Remove Module Feature
(Jia Ern)

#### 4.1.5. Access Module Level Feature
(Jiayi)

#### 4.1.6. Example of the Admin Feature

#### 4.1.7. Conclusion

### 4.2. Module Features
[summary + scenario]

#### 4.2.1. Add Chapter Feature
(Jiayi)

#### 4.2.2. List Chapters Feature
(Zeyu)

#### 4.2.3. Edit Chapter Name Feature
(Jane)

#### 4.2.4. Remove Chapter Feature
(Jia Ern)

#### 4.2.5. Access Chapter Level Feature
(Lucas)

#### 4.2.6. Return to Admin Level Feature
(Lucas)

#### 4.2.7. Rate Chapter Feature
(Jiayi) 

#### 4.2.8. Example of the Module Feature

#### 4.2.9. Conclusion

### 4.3. Chapter Features
[summary + scenario]

#### 4.3.1. Add Flashcard Feature
(Jane)

#### 4.3.2. List Flashcards Feature
(Zeyu)

#### 4.3.3. Edit Flashcard Content Feature
(Jane)

#### 4.3.4. Remove Flashcard Feature
(Jia Ern)

#### 4.3.5. Return to Module Level Feature
(Jia Ern)

#### 4.3.6. Check Overall Performance for a Chapter Feature
(Jiayi)

#### 4.3.7. Example of the Chapter Feature

#### 4.3.8. Conclusion

### 4.4. Revision Feature
[summary + scenario]

#### 4.4.1. Revision Feature
(Jia Ern)

### 4.5. Scheduler Feature
[summary + scenario]

#### 4.5.1. View Due Chapters Feature
(Lucas)

#### 4.5.2. Preview Upcoming Dues Feature
(Lucas)

#### 4.5.3. Exclusion Feature
(Lucas)

#### 4.5.4. Reschedule Chapter Feature
(Jane)

#### 4.5.5. View Revision History Feature
(Zeyu)


--------------------------------------------------------------------------------------------------------------------

## 5. Appendix: Requirements
### 5.1. Product Scope
#### 5.1.1. Target User Profile

* needs to have an effective study schedule
* prefers typing to mouse interactions
* is comfortable with the usage of CLI applications

#### 5.1.2. Value Proposition

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

--------------------------------------------------------------------------------------------------------------------

## 6. Appendix: Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}