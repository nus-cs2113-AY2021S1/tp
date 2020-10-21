# Developer Guide

## Introduction

## Setting Up

## Design

### Architecture

<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Overall_Architecture.JPG" alt="" width="250"/> <br/>

The Figure given above shows the overall design of the application. Given below is a sequence diagram when adding a Todo task. 

<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Archi_SD.JPG" alt="" width="650"/>

The quick overview of components and the workflow is given below.

### Ui

The Ui class receives the input from the keyboard and passes to the Parser class to handle it. After executing commands, 
most of the output will be done by the Ui class.

### Parser

The Parser class receives commands from Ui class and converts the format to distinguish different commands. 
Then it passes the formatted commands to the Command class. 

### Command

The command class receives formatted commands from Parser class and execute corresponding commands on Module items. 
The changes will be passed to and saved in the Storage class.

### Storage

The Storage class will create a local file when the user launches the application for the first time to save the data. 
After the first launch, every time the user reopens the application, it will load the information to the Module class from the local file. 
Every time before exiting the application, all information will be automatically saved to the same local file.

### Module

<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Model_Class_Diagram.JPG" alt="" width="900"/>

The figure given above shows the structure of main items in this application. When executing commands, 
the CalendarItem class updates the information or provides the information of different types of items if needed. 
It is split into two subclasses: Task and Event class. All the items in the two classes are managed separately and can be accessed by the main CalendarItem class.
The CalendarList class keeps track of the number of total items, total tasks, and total events in CalendarItem class.
Given below is the simple overview of Task and Event classes.

#### Task

The Task class stores the information of all task items, including description and status, such as isDone and isImportant. 
It has two subclasses: Todo and Deadline. Deadline items also have the date information and countdown for the deadline date, which are not included in Todo tasks. 
All Task items update the information or provide needed information about task items when executing commands related to tasks or saving the information to Storage. 

#### Event

The event class stores the information of all event items, including date, time, venue, status whether it is overdue, and any other information if added. 
It has two subclasses: SchoolEvent and Activity. Activity items can have other details. All SchoolEvent items must have a module code.  
Furthermore, the SchoolEvent class has three subclasses which are Lecture, Tutorial, Lab, and Exam. Among them only Exam items have a countdown for the exam date. 
All Event items update the information or provide needed information about event items when executing commands related to events or saving the information to Storage.

## Implementation

## Documentation

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
