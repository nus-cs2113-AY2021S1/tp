# Developer Guide

## Introduction

## Setting Up

## Design
This section describes the design overview of the application.

### Architecture

<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Overall_Architecture.JPG" alt="" width="300"/> <br/>

The Figure given above shows the overall design of the application. Given below is a sequence diagram when adding a Todo task. 

<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Archi_SD.JPG" alt="" width="750"/>

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

<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Model_Class_Diagram.JPG" alt="" width="750"/>

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
This section describes some noteworthy details on how certain features are implemented.

### Mark a task as done feature
This feature is facilitated by `DoneCommand`. It extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/done_command_SD.JPG" alt="" width="450"/>
Note: It first extracts the task number from the user input prior to `convertTaskNumberToCalendarNumber`. 
There will be a check in the function `markTaskAsDone(calendarNumber)` to ensure that the calendar item being marked as done is a task. 


### Additional information of an event feature
This feature is facilitated by `AddInfoCommand` and the `ViewInfoCommand`. Both extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation of `AddInfoCommand` works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/addInfoCommand_SD.JPG" alt="" width="450"/>
The following sequence diagram show how the `execute()` operation of `ViewInfoCommand` works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/viewInfoCommand_SD.JPG" alt="" width="450"/>

Note: It first extracts the event number from the user input prior to `convertEventNumberToCalendarNumber`. 

### Delete a calendar item feature
This feature is facilitated by `DeleteCommand`. It extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/deleteCommand_SD.JPG" alt="" width="450"/>
Note: It first extracts the task/event number from the user input prior to `convertTaskNumberToCalendarNumber` and `convertEventNumberToCalendarNumber` respectively. 


### Find a calendar item feature
This feature is facilitated by `FindCommand`. It extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation works when the user searches the entire calendar.:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/findCommand_SD.JPG" alt="" width="450"/>

The search for tasks or events feature has a similar sequence diagram. The difference is the varying condition. Depending
on whether the user searches for tasks or events, the condition will check for the instance of either the task or event respectively.

### Print personal calendar feature
This feature is facilitated by `PrintTimelineCommand`. It extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation works when the user searches the entire calendar.:<br/>

## Documentation

### Documentation guide

#### Introduction
We use Markdown for writing documentation.

#### Setting up and maintaining the project website
- We use [Jekyll](https://jekyllrb.com/) to manage documentation.  
- The docs/ folder is used for documentation.  
- To learn how to set it up and maintain the project website, follow the guide [[se-edu/guides] Using Jekyll for project documentation](https://se-education.org/guides/tutorials/jekyll.html).
#### Style guidance
- Follow the [Google developer documentation style guide](https://developers.google.com/style).
- Also relevant is the [[se-edu/guides] Markdown coding standard](https://se-education.org/guides/conventions/markdown.html)
#### Editing diagrams
- See the [[se-edu/guides] Using PlantUML](https://se-education.org/guides/tutorials/plantUml.html) to find out how to create and update the UML diagrams in the developer guide.
#### Converting a document to the PDF format
- We use Google Chrome for converting documentation to PDF format.  
- Here are the steps to convert the project documentation to PDF format:
  - Go to your generated documentation site on GitHub using Chrome.
  - Within Chrome, click on the Print option in Chrome’s menu.
  - Set the destination to Save as PDF, then click Save to save a copy of the file in PDF format.
- See the guide [[se-edu/guides] Saving web documents as PDF files](https://se-education.org/guides/tutorials/savingPdf.html) for more details.  

### Testing guide

There are two ways to run tests.
- Method 1: Using IntelliJ JUnit test runner
  - To run all tests, right-click on the src/test/java folder and choose Run 'Tests in 'tp.test''
  - To run a subset of tests, you can right-click on a test package, test class, or a test and choose Run 'DukeTest'
- Method 2: Using Gradle
  - Open a console and run the command gradlew checkstyleMain (Mac/Linux: ./gradlew checkstyleMain)  

### Logging guide

- We are using the java.util.logging package for logging.
- The LogsCenter class is used to manage the logging levels and logging destinations.
- The Logger for a class can be obtained using LogsCenter.getLogger(Class) which will log messages according to the specified logging level.
- Log messages are output through the console and to a .log file.
- The output logging level can be controlled using the logLevel setting in the configuration file
- When choosing a level for a log message, follow the conventions given in [[se-edu/guides] Java: Logging conventions](https://se-education.org/guides/conventions/java/logging.html).
  - SEVERE : Critical problem detected which may possibly cause the termination of the application
  - WARNING : Can continue, but with caution
  - INFO : Information showing the noteworthy actions by the App
  - FINE : Details that is not usually noteworthy but may be useful in debugging e.g. print the actual list instead of just its size

### Configuration guide

### DevOps guide


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|user|set my tasks as done|track my tasks better|
|v1.0|user|delete my calendar items|remove unwanted items and organise my calendar better |
|v2.0|user|find an item in my calendar|locate an item without having to go through the entire list|
|v2.0|NUS student|add information about my classes|locate all the information about my class on this app|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
