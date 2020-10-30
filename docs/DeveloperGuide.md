# Developer Guide

<br><br>
![](diagrams/Logo.png)<br>

Yang Jiaqi<br>
Zhuang Mengjin<br>
Xu Che<br>
Wu Nan<br>
Wan Shi Jie Brendan<br>
<br><br>

## Table of Contents
1. [Introduction](#intro)
2. [Getting Started](#getting_started)<br>
2.1 [Prerequisites](#prerequisites)<br>
2.2 [Setting Up](#setting-up)<br>
3. [Design](#design) <br>
3.1 [Architecture](#architecture) <br>
3.2 [UI](#32-ui) <br>
3.3 [Storage](#33-storage) <br>
3.4 [Logic](#34-logic) <br>
3.5 [Model](#35-model) <br>
&nbsp;&nbsp;&nbsp;&nbsp;3.5.1 [Event](#351-event) <br>
&nbsp;&nbsp;&nbsp;&nbsp;3.5.2 [Location](#352-location) <br>
4. [Implementation](#4-implementation) <br>
4.1 [Add events](#41-add-events) <br>
4.2 [Clear events](#42-clear-events) <br>
4.3 [Edit events](#43-edit-events) <br>
4.4 [Locate](#44-locate) <br> 
4.5 [Reminders](#45-reminder) <br>
4.6 [Sort events](#46-sort-events) <br>
4.7 [View events](#47-view-events) <br>
5. [Documentation](#5-documentation) <br>
5.1 [Product scope](#51-product-scope) <br>
5.2 [Value Proposition](#52-value-proposition) <br>
5.3 [User Stories](#53-user-stories)<br>

## 1. Introduction<a id="intro"></a>
NUSchedule is a Command-Line based application that manages all of your commitments. Built with a clean and intuitive
interface, NUSchedule will help you manage your commitments without any hassle. If you are a proficient in typing, 
NUSchedule will prove itself to be the most efficient way to manage your commitments.  
  
This developer guide provides information on the architecture and design of the application, NUSchedule. This guide
provides information that will not only help you get started as a NUSchedule contributor, but that you will find useful
to refer to even if you are already a contributor.

## 2. Getting Started <a id="getting_started"></a>
This section provides information to help you get NUSchedule up and running on your own computers.

### <a id="prerequisites">2.1 Prerequisites</a>
1. JDK 11
2. Intellij IDEA

### <a id="setting-up">2.2 Setting Up</a>
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

## <a id="design">3. Design</a>
This section describes the different components of the application and how each component interact with
each other to run the program. 

### 3.1 Architecture
![architecture](diagrams/architecture.png)<br>

The Architecture Diagram above provides a high-level view of the design of NUSchedule. The app can be broken down into 
5 different components:  
1. Main: Initializes the other components and connects them with each other.
2. UI: Manages the User Interface that the user interacts with.
3. Logic: Interprets user commands.
4. Storage: Reads data from and writes data to the hard disk.
5. Model: Stores the data the app uses in memory.

### 3.2 UI
![UI Class Diagram](diagrams/Ui.png)<br>

*Figure 3.2.1 Class diagram for UI component*

 __API__:`UI.java`  
The UI consists of various parts, e.g. `printGreetingMessage`, `printEventList`, `showError`, `printNumEvent`, etc.
 
The `UI` component reads user input using readCommand() and executes user commands according to the Logic component.

### 3.3 Storage
__API__:`Storage.java`
The `Storage` component can save the list of event data in .txt format and read it back.   
### 3.4 Logic
__API__:`Parser.java`  

![Logic Class Diagram](diagrams/LogicClass.png)<br>

*Figure 3.4.1 Class diagram for Logic component*

The `logic` component parses the user input and executes commands based on the given input.
 1.	Parser reads the user input and returns a command to be executed by `NuSchedule`.
 2.	The command execution affects the EventList (e.g. clearing the list).
 3.	The result passes back to the UI, which then displays relevant feedback to the user.  

### 3.5 Model
The `Model` component stores an ArrayList, events, that represents the current list of events and available locations.

#### 3.5.1 Event
__API__:`EventList.java`   

### 3.5.2 Location
![location class diagram](diagrams/LocationClass.png) <br>
*Figure 3.6.1 Class diagram for location component*

__API:__ `LocationList.java`  

The `LocationList` is made up of an ArrayList of Locations, which is a type of variable that stores different information 
for different types of location (eg. `Building`, `Hostel`, etc.). However, do note that any place that is 
not within the list of saved locations will be saved as type `OutOfNUS`, and would not be saved into the `location.txt` 
file. The location will still be part of the location list before the app closes, and it will also be 
saved as part of the Event information.

<br>There is a separate api for bus stops (`BusStopList.java`), which stores all available bus stops 
and their information. Although the variable is declared in the main NUSchedule class, it is only referenced 
and not edited in any part of the program.

<br>The `Location` component 
* stores information about various types of locations 
* prints the list of locations that is saved in the data file 
* checks if a location is saved in the list and returns the location when asked

## 4. Implementation
This section describes the implementation of some noteworthy features. 

### 4.1 Add events
This feature allows users to add events and relevant information about them (description, time and location) to the list
. There are three possible types of events that can be added: classes, assignments, and personal events.

Given below is an example usage scenario:

Step 1. The user launches the application to add a class to his schedule. The user executes 
`class CS1010 Lecture /t 2020-05-05 10:00 /e 2020-05-05 12:00 /l LT27` to add a new class to the list.  

Step 2. The Parser class parses the input as a new class with description ‘CS1010 Lecture’,
starting time ‘2020-05-05 at 10:00’, ending time ’2020-05-05 at 12:00’, and location ‘LT27’.  

Step 3. `addCommand()` is then called with the above parameters, and adds it to the list of events. This also checks 
whether there is any event has overlap timing with the newly added event.

The sequence diagram below shows the process of adding a new event.
![AddCommand Sequence Diagram](diagrams/AddCommand.png)<br>
*Figure 4.1 Sequence Diagram for add function*


### 4.2 Clear events
This feature allows users to completely delete the existing event information that is previously typed in by users. 

Given below is an example usage scenario:

Step 1. The user launches the application and wants to delete everything previously stored by using `clear` command.  

Step 2. If there is no information stored before the user input the `clear` command, the user will be reminded about 
that the list is already empty. 

The sequence diagram below shows the process of clearing all events.
![ClearCommand Sequence Diagram](diagrams/ClearCommand.png)<br>
*Figure 4.2 Sequence Diagram for clear function*
 
### 4.3 Edit events
This feature allows users to edit the information of events that was previously added. 

Given below is an example usage scenario:

Step 1. The user launches the application and inputs a command to add one event.  

Step 2. The user realises that there was a mistake in the event added and decides to edit the task information by 
entering `edit 1 class <new event description>` to edit the one existing event.

Step 3. `editCommand()` function replaces the original event with the edited one. 
![EditCommand Sequence Diagram](diagrams/EditCommand.png)<br>
*Figure 4.3 Sequence Diagram for EditCommand()*

### 4.4 Locate
This feature allows users to add locations as part of the event information. If the place is located within the school, 
additional information about the location will be provided such as nearest bus stops or buildings. If the place is a 
bus stop, the buses available will be provided instead. The user will add location name as the last piece of information 
when adding an event. The LocationList has a method that searches for the place with the exact same name, if such a 
place is not found, it will create a location and allocate it to be OutOfNUS.  

This feature also allows users to search for a location based on a given location name, or the location where an event 
is held. It will print out additional information about the location to the user. 

The `locate` command executes with the following steps: 
  
Step 1. When the user type in `locate [input]`, the Parser class will extract the input from the command and call a 
`LocateCommand` object.  
* `input` will always be a String when passed into the command object. 

Step 2. `LocateCommand` will then try to convert the input into an integer, if possible.  

Step 3. If conversion is possible, the method will obtain the event at the given index and return its location 
information.  
* Tries to catch `IndexOutOfBoundException` to check if the given index is within the range of the
event list.

Step 4. If conversion is not possible, then it will check if the given string is a valid location.  
* Returns the location if valid
* Prints an error message if invalid   

The sequence diagram below shows exactly which methods, from which classes, are called to obtain the required location. 
![locate sequence diagram](diagrams/LocateSequence.png)<br>
*Figure 4.4 Sequence diagram for locate function*

### 4.5 Reminder
This feature allows users to get a reminder of the events that will occur today.

Given below is an example usage scenario:

Step 1. The user wants to see all the events that are happening today. He inputs the command `reminder`.

Step 2. The command filters the list of all events based on the current date.

Step 3. It then calls `UI` to print the filtered list.

The sequence diagram belows shows the process of executing the reminder command.
![ReminderCommand Sequence Diagram](diagrams/ReminderCommand.png)
*Figure 4.5 Sequence Diagram for reminder function*

### 4.6 Sort events
This feature allows users to sort the available task by a sorting criteria. Currently, the sorting criteria available
are sorting by time, description and location.  

Given below is an example usage scenario:

Step 1. The user wants to view the events in a chronological order. 

Step 2. The user enters `sort time`, which will call the `sortCommand()` function which will sort all events.  
![](diagrams/SortCommand.png)<br>
*Figure 4.6 Sequence diagram for SortCommand()*

### 4.7 View events
This feature allows users to view a numbered list of all the events/tasks they have added so far,
in the order which they were added.

Given below is an example usage scenario:

Step 1. The user wants to see a list of all the events he has added so far, and executes the command `list`.

Step 2. The command calls the `printEventList` function from `UI`, which then prints the list of events.

The sequence diagram below shows what happens when the list command is executed
![ListCommand Sequence Diagram](diagrams/ListCommand.png)
*Figure 4.7 Sequence Diagram for list function*

### 4.8 Help
This feature allows users to view a summary of all the features available for the current version of NUSchedule. 
When the user inputs `help`, all available commands and a simple description for each of them will be printed.

The sequence diagram below shows what happens when user enters `help`:

![HelpCommand Sequence Diagram](diagrams/HelpCommand.png)<br>
*Figure 4.8 Sequence Diagram for help function*

## 5. Documentation
This section provides other relevant information about the application.

### 5.1 Product scope
__Target user profile:__
* NUS student or staff
* able to type quickly
* has quite a number of events to keep track of  
* prefers to use desktop apps
* prefers using Command Line Interface (CLI) apps
* prefers typing instead of mouse interactions

### 5.2 Value proposition
NUSchedule aims to assist the target audience with:  
* managing all their events in one application (personal or work)
* easily finding out about the location of their events 

### 5.3 User stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|1.0|busy individual|keep track of both personal and school activities|avoid clashing events
|1.0|freshman|know module details and lesson venues|be on time for my activities
|1.0|forgetful person|be reminded of the deadlines for my assignments|submit on time
|1.0|tutor|know the estimated time for my students to travel to their next class|pace my lesson suitably
|1.0|hardworking student|track how much time I have spent studying|allocate my time efficiently
|2.0|exchange student|know the optimal path to reach my next destination|avoid getting lost
|2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
|2.0|professor|Know whether my students have another lesson after mine and the expected time of travelling|Pace my lesson appropriately
|2.0|student using zoom for lessons|record the virtual locations|store the links and passwords of the meetings
## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

Try different inputs with variations of the format, to check whether the exceptions are being correctly thrown.  
Also edit the stored data of events.txt to see whether the reaction is correct. Do not touch bus_stops.txt and 
locations.txt for test purposes as they form the data base.
{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
