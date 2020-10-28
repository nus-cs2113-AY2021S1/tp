# Developer Guide
google docs [link](https://docs.google.com/document/d/1dGM1DYHVXxqUM8RPfAPs6MuFXK3VgL6807x5ivZ5yJk/edit)

## Table of Contents
1. [Introduction](#introduction)
2. [Getting Started](#getting-started)<br>
2.1 [Prerequisites](#prerequisites)<br>
2.2 [Setting Up]()<br>
2.3 [Running The Program]()<br>
3. [Design](#design) <br>
3.1 [Architecture](#architecture) <br>
3.2 [UI](#ui) <br>
3.3 [Storage](#storage) <br>
3.4 [Logic](#logic) <br>
3.5 [Events](#events) <br>
3.6 [Locations](#locations) <br>
4. [Implementation](#implementation) <br>
4.1 [Add events](#add-events) <br>
4.2 [Clear events](#clear-events) <br>
4.3 [Edit events](#edit-events) <br>
4.4 [Locate events](#locate-events) <br> 
4.5 [Reminders](#reminders) <br>
4.6 [Sort events](#sort-events) <br>
4.7 [View events](#view-events) <br>
4.8 [Summary of Features](#summary-of-features) <br>
5. [Documentation](#documentation) <br>
5.1 [Product scope](#product-scope) <br>
5.2 [User stories](#user-stories) <br>

## 1. Introduction
NUSchedule is a Command-Line based application that manages all of your commitments. Built with a clean and intuitive
interface, NUSchedule will help you manage your commitments without any hassle. If you are a proficient in typing, 
NUSchedule will prove itself to be the most efficient way to manage your commitments.  
  
This developer guide provides information on the architecture and design of the application, NUSchedule. This guide
provides information that will not only help you get started as a NUSchedule contributor, but that you will find useful
to refer to even if you are already a contributor



## 2.Getting Started
This section provides information to help you get NUSchedule up and running on your own computers.

### Prerequisites
1. JDK 11
2. Intellij IDEA

### Setting Up
1. Fork [this repo](https://github.com/AY2021S1-CS2113T-F14-4/tp), and clone the fork onto your computer.   
2. Open IntelliJ (if you are not in the welcome screen, click File > Close Project
to close the existing project dialog 
first).   
3. Set up the correct JDK version for Gradle  
    1. Click Configure > Project Defaults > Project Structure
    2. Click New… and set it to the directory of the JDK.
4. Click Import Project (or Open or Import in newer version of Intellij).
5. Locate the build.gradle file (not the root folder as you would do in a normal importing) and select it. Click OK.
   If asked, choose to Open as Project (not Open as File).
6. Click OK to accept the default settings but do ensure that the selected version of Gradle JVM matches the JDK 
   being used for the project.

## Design
This section describes the different components of the application and how each component interact with
each other to run the program. 

### Architecture
![architecture](../diagrams/architecture.png)

The Architecture Diagram above provides a high-level view of the design of NUSchedule. The app can be broken down into 
5 different components:  
1. Main: Initializes the other components and connects them with each other.
2. UI: Manages the User Interface that the user interacts with.
3. Logic: Interprets user commands.
4. Storage: Reads data from and writes data to the hard disk.
5. Model: Stores the data the app uses in memory.

__How the architecture components interact with each other__  
The sequence diagram below shows how each individual component interacts with each other when the user inputs a command.
![sequence](../diagrams/ArchitectureSequence.png)

### UI
 __API__:`UI.java`  
The UI consists of various parts, e.g. `printGreetingMessage`, `printEventList`, `showError`, `printNumEvent`, etc.
 
The `UI` component executes user commands according to the Logic component.

### Storage
__API__:`Storage.java`
The `Storage` component can save the list of event data in .txt format and read it back.   
### Logic
__API__:`Parser.java`  
The `logic` component parses the user input and executes commands based on the given input.
 1.	Parser reads the user input and returns a command to be executed.
 2.	The command execution affects the EventList (e.g. clearing the list).
 3.	The result passes back to the UI, which then displays relevant feedback to the user.  

### Model
__API__:`EventList.java`   
The `Model` component stores an ArrayList, events, that represents the current list of events.
## Implementation
This section describes the implementation of some noteworthy features. 

### Add events
This feature allows users to add events and relevant information about them (description, time and location) to the list
. There are three possible types of events that can be added: classes, assignments, and personal events.

Given below is an example usage scenario:

Step 1. The user launches the application to add a class to his schedule. The user executes 
`class CS1010 Lecture /t 2020-05-05 10:00 /l LT27` to add a new class to the list.  

Step 2. The Parser class parses the input as a new class with description ‘CS1010 Lecture’, time ‘2020-05-05 at 10:00’, 
and location ‘LT27’.  

Step 3. `addCommand()` is then called with the above parameters, and adds it to the list of events, events.

### Clear events
This feature allows users to completely delete the existing event information that is previously typed in by users. 

Given below is an example usage scenario:

Step 1. The user launches the application and wants to delete everything previously stored by using `clear` command.  

Step 2. If there is no information stored before the user input the `clear` command, the user will be reminded about 
that the list is already empty. 
 
### Edit events
This feature allows users to edit the information of events that was previously added. 

Given below is an example usage scenario:

Step 1. The user launches the application and inputs a command to add one event.  

Step 2. The user realises that there was a mistake in the event added and decides to edit the task information using the 
edit 1 command to edit the event that was added (index = 1 as it is the one and only event currently).  

Step 3. The Parser class parses the 'edit 1' command. The user enters a new event.  

Step 4. 'editCommand()' function replaces the original event with the edited one. 


### Locate events
This feature allows users to add locations as part of the event information. If the place is located within the school, 
additional information about the location will be provided such as nearest bus stops or buildings. If the place is a 
bus stop, the buses available will be provided instead. 

Given below is an example usage scenario: 

Step 1. The user will add location name as the last piece of information when adding an event.   

Step 2. The LocationList has a method that searches for the place with the exact same name, if such a place is not 
found, it will create a location and allocate it to be OutOfNUS.  

### Reminders
### Sort events
### View events
### Summary of Features

## Documentation

### Product scope
__Target user profile:__
* NUS student or staff
* able to type quickly
* has quite a number of events to keep track of  
* prefers to use desktop apps
* prefers using Command Line Interface (CLI) apps
* prefers typing instead of mouse interactions

### Value proposition

{Describe the value proposition: what problem does it solve?}

### User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|1.0|busy individual|keep track of both personal and school activities|avoid clashing events
|1.0|freshman|know module details and lesson venues|be on time for my activities
|1.0|forgetful person|be reminded of the deadlines for my assignments|submit on time
|1.0|tutor|know the estimated time for my students to travel to their next class|pace my lesson suitably
|1.0|hardworking student|track how much time I have spent studying\allocate my time efficiently
|2.0|exchange student|know the optimal path to reach my next destination|avoid getting lost
|2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
|2.0|professor|Know whether my students have another lesson after mine and the expected time of travelling|Pace my lesson appropriately
## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
