# Developer Guide

## Introduction
### Introduction to *25 hours a day*
*25 hours a day* is a scheduling app optimized for users via a convenient yet powerful Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, 25 hours a day can schedule your tasks and events faster than traditional GUI apps.

### Purpose and scope
The goal of this developer guide is to describe the architecture and software design decisions for the task scheduling application 25 hours a day. This developer guide covers an overview of the program architecture, the logical view of major components, and the mechanism of the functions that is helpful for you to get started or refer to.

The intended audience of the developer guide is the developers and software testers of 25 hours a day.

### Developer guide organization
The table below shows the current content of the developer guide.

|Section| Purpose | 
|--------|----------|
|[Section 2: Setting up](#setting-up)|To provide instructions on how to download and set up 25 hours a day on your computer.|
|[Section 3: Design](#design)|To explain the architecture of 25 hours a day,  and describe the major components of 25 hours a day, the roles of major components, as well as their organization and interaction of major components.|
|[Section 4: Implementation](#implementation)|To explain the mechanism of functions added to 25 hours a day and the features added to 25 hours a day.|
|[Section 5: Documentation](#documentation)|To represent documents describing the system and its parts.|

### Information for developer
The table below shows the information and contact details of developers.

|Developer| Contact details | 
|--------|----------|
|Liu Jingming|E0424608@u.nus.edu|
|Liu Yifeng|E0425960@u.nus.edu|
|Lyu Jiawen|E0376928@u.nus.edu|
|Ng Hong Ming|E0426149@u.nus.edu|
|Zhang Yilin|E0377000@u.nus.edu|

## Setting Up & Getting started

### Setting up

Before diving into the project development and testing, here are some essential tools required in this project:

1. **JAVA 11.0.8**
    * Launch your terminal and type "java -version" to ensure you have the correct version number
    * If you have no Java installed or different version number, please proceed to this [website](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
    to download the **Java 11.0.9** installer.
1. **Intellij IDEA**
    * If you are new to IntelliJ, please proceed to this [website](https://www.jetbrains.com/help/idea/installation-guide.html)
    to download and install the IDE.
    
After installing the required tools, proceed to [Github Page](https://github.com/AY2021S1-CS2113T-T12-2/tp) and 
fork the project to your own repository, and clone your fork into your working computer.    

Now, Open Intellij 
1. If you are not in the welcome screen, click `File` → `Close Project` to navigate to the starting project dialog)
1. Set up the correct JDK version for Gradle.
        1. Click `Configure` → `Project Structure for New Project`
        1. Click `New...` and set it to the directory of the JDK
1. Upon the successful configuration, you should be able to see the following screenshot:
![JDK Config](images/JDK_Config_Screenshot.png)
1. Lastly, **Import the project as a Gradle Project.**
    * Intellij IDEA by default has the Gradle plugin installed. If you have disabled it, go to 
    `File` → `Settings` → `Plugins` to re-enable them.
    * Click Import Project (or Open or Import in newer version of Intellij).
    * Locate the **build.gradle file** (not the root folder as you would do in a normal importing) and select it. Click `OK`.
    * If asked, choose to `Open as Project` (not Open as File).
    * Click `OK` to accept the default settings but do ensure that the selected version of Gradle JVM matches the 
    JDK being used for the project.
    * Wait for the importing process to finish (may take a few minutes, so get some :coffee: :relaxed:)
1. **Verify the set up**:
    * Run the `..main/java/seedu.duke/Duke`
    * Upon a successful configuration, you should be able to see the welcome screen of the project as shown below:
    ![verify_set_up](images/Verify_setup.PNG)
    * Run some tests to ensure it is working properly.

### Getting started

1. **Configure the coding style**
If using IDEA, follow the guide If using IDEA, follow the guide [se-edu/guides IDEA: Configuring the code style](https://se-education.org/guides/tutorials/intellijCodeStyle.html) 
to set up IDEA’s coding style to match ours.
    :bulb: Tips: Optionally, you can follow the guide  [se-edu/guides Using Checkstyle](https://se-education.org/guides/tutorials/checkstyle.html) 
    to find how to use the CheckStyle within IDEA e.g., to report problems as you write code.
1. **Set up CI**
This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, 
it will run the CI for your project automatically at each push to the master branch or to any PR. No set up required.
1. **Learn the design**
When you are ready to start coding, we recommend that you get some sense of the overall design by reading about [25HoursADay’s architecture](#architecture).

## Design
This section describes the design overview of the application.

### Architecture

The figure below shows the overall design of the application. Given below is a sequence diagram when adding a Todo task.
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/Overall_Architecture.JPG" alt="" width="300"/> <br/>


UI: The user interface of the application.
Parser: Interprets the user's input.
Command: The command executor.
Model: Holds the data of the application in memory.
Storage: Reads data from, and writes data to, the hard disk. 

<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/Archi_SD.JPG" alt="" width="750"/>

The quick overview of components and the workflow is given below.

### Ui
The `Ui` class receives the input from the keyboard and passes to the `Parser` class to handle it. After executing commands, 
most of the output will be done by the `Ui` class.

### Parser

The `Parser` class receives the user's input from the `Ui` class. It interprets the user's input and returns the resepective command. 

### Command

The different `Command` classes receives the user's input from the `Parser` class and executes corresponding to the commands. 
The figure belows shows the class diagram of the command class: <br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/command.JPG" alt="" width="200"/><br/>
All the different Command classes inherit from the `Command` class.

### Storage

The `Storage` class will create a local file when the user launches the application for the first time to save the data. 
After the first launch, every time the user reopens the application, it will load the information to the Model from the local file. 
Whenever the calendarList is updated, all information will be automatically saved to the same local file.

### Model

<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/Model_Class_Diagram.JPG" alt="" width="750"/>

The figure given above shows the structure of the Model in this application. When executing commands, 
the `CalendarItem` class updates the information or provides the information of different types of items if needed. 
It is split into two subclasses: `Task` and `Event` class.
The `CalendarList` class holds the array of `CalendarItem` and keeps track of the number of total items, total tasks, and total events.
Given below is the simple overview of Task and Event classes.

#### Task

The `Task` class stores the information of all task items, including description and status, such as isDone and isImportant. 
It has two subclasses: `Todo` and `Deadline`. `Deadline` items also have the date information and countdown for the deadline date, which are not included in `Todo` tasks. 

#### Event

The `Event` class stores the information of all `Event` items, including date, time, venue, status whether it is overdue, and any other information if added. 
It has two subclasses: SchoolEvent and Activity. `Activity` items can have other details.
All `SchoolEvent` items must have a module code.  
Furthermore, the `SchoolEvent` class has four subclasses which are `Lecture`, `Tutorial`, `Lab`, and `Exam`. Among them only `Exam` items have a countdown for the exam date. 
All `Event` items update the information or provide needed information about `Event` items when executing commands related to events or saving the information to `Storage`.

Design consideration: `SchoolEvent` is modelled after NUS modules to cater to our intended users.

## Implementation
This section describes some noteworthy details on how certain features are implemented.

### Add a calendar item feature
This feature is facilitated by `AddCommand`.
The following sequence diagram shows how the `execute()` operation works:

It checks the type of the new event first, 
then it analyses the attached information and saves the event with the information in the event list.  
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/add_command_SD.JPG" alt="" width="750"/><br/>

Note: If the event is in `Lecture`, `Lab`, `Tutorial`, or `Exam` type, 
there will be a check in the function `isValid(command)` to ensure the module code included in the item is valid.

Note: Multiple `Lecture`, `Lab`, and `Tutorial`events can be added by one command since they are recurring,
while only one `Exam` or `Activity` event can be added at a time.   

### Mark a task as done feature
This feature is facilitated by `DoneCommand`.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/done_command_SD.JPG" alt="" width="750"/><br/>
Note: It first extracts the task number from the user input prior to `convertTaskNumberToCalendarNumber`. 
There will be a check in the function `markTaskAsDone(calendarNumber)` to ensure that the calendar item being marked as done is a task. 


### Additional information of an event feature
This feature is facilitated by `AddInfoCommand` and the `ViewInfoCommand`.
The following sequence diagram show how the `execute()` operation of `AddInfoCommand` works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/addInfoCommand_SD.JPG" alt="" width="750"/><br/>
The following sequence diagram show how the `execute()` operation of `ViewInfoCommand` works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/viewInfoCommand_SD.JPG" alt="" width="750"/><br/>

Note: It first extracts the event number from the user input prior to `convertEventNumberToCalendarNumber`. 

### Delete a calendar item feature
This feature is facilitated by `DeleteCommand`.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/deleteCommand_SD.JPG" alt="" width="750"/><br/>

Note: It first extracts the task/event number from the user input prior to `convertTaskNumberToCalendarNumber` and `convertEventNumberToCalendarNumber` respectively. 


### Find a calendar item feature
This feature is facilitated by `FindCommand`.
The following sequence diagram show how the `execute()` operation works when the user searches the entire calendar.:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/findCommand_SD.JPG" alt="" width="500"/><br/>

The search for tasks or events feature has a similar sequence diagram. The difference is the varying condition. Depending
on whether the user searches for tasks or events, the condition will check for the instance of either the task or event respectively.

### Print personal calendar feature
This feature is facilitated by `PrintTimelineCommand`.
The following sequence diagram show how the `execute()` operation works when the user wants to print the personalised calendar.:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/PrintTimelineCommand_SD.png" alt="" width="750"/><br>

### Prioritize a task feature 
This feature is facilitated by `PrioritizeCommand`. 
The following sequence diagram shows how the `execute()` operation works when the user wants to prioritize a task:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/PrioritizeCommand_SD.png" alt="" width="750"/><br/>

Note: It first extracts the task number from the user input prior to convertTaskNumberToCalendarNumber. 
There will be a check in the function `markTaskAsImportant(calendarNumber)` to ensure that the calendar item being marked as important is a task.

### Print prioritized tasks feature
This feature is facilitated by `PrintPriorityCommand`.
The following sequence diagram shows how the `execute()` operation works when the user wants to print all the prioritized tasks:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/PrintPriorityCommand_SD.png" alt="" width="750"/><br/>

Note: It uses the `getIsImportant()` function of task items to identify whether it is a prioritized task.

### Print progress feature
This feature is facilitated by `PrintProgressCommand`.
The following sequence diagram shows how the `execute()` operation works when the user wants to see the progress of all tasks:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/PrintProgressCommand_SD.png" alt="" width="500"/><br/>

Note: It uses `calculateNumTotal()` and `calculateNumFinshed()` to calculate the numbers of only tasks and finished tasks in the list, not including events. 
Then it uses these numbers to calculate the progress. 

### Print suggestions feature
This feature is facilitated by `PrintSuggestionCommand`.
The following sequence diagram shows how the `execute()` operation works when the user wants to see suggestions about preparing which tasks:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/PrintSuggestionCommand_SD.png" alt="" width="500"/><br/>

Note: It uses `getEarliestDeadline()` function to get the earliest ordinary deadline in the list, and `getEarImportantDeadline()` function to get the earliest important deadline in the list.
Similarly, it uses`getFirstTodo()` function to get the first added todo task in the list, and `getFirImportantTodo()` function to get the first added important todo task in the list.
If no corresponding items, the functions will return null.

### Printing countdown feature
The feature is facilitated by `CountdownCommand`.

The following sequence diagram shows how the `execute()` operation works when the user decide to see the countdown of exams or deadlines:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/countdown_command_SD.jpg" alt="" width="750"/><br/>

Note: Before printing the countdown, `countdown()` function will calculate the countdown of exams or deadlines, and the countdowns for
exams or deadlines will be sorted in ascending sequence by function `sortDeadlinesAndPrintCountdown()` or `sortExamsAndPrintCountdown()`


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

* *Task* - a todo item or a deadline item.
* *School event* - a lecture, tutorial or lab session.
* *Event* - an activity, lecture, tutorial or lab session.
* *Calendar item* - a todo item, deadline item, activity, lecture, tutorial or lab.	
* *Task list* - a list that stores all the tasks added to the app.
* *Event list* - a list that stores all the events added to the app.
* *Calendar list* - a list that stores all the calendar items added to the app.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
