# **Developer Guide**


## **Introduction**
 ![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/zoomwhitebg.png) <br/><br/>
### **Welcome to Zoomaster!**
Zoomaster is a Java application for the Command Line. It provides you with a simple and intuitive way to store your Zoom links for your online classes, alongside other useful links for the lesson at hand.<br/>

Zoomaster can also intelligently determine the current lesson you are having, allowing you to launch the correct links quickly and elegantly from the command line.


### Table of contents
* [Getting Started](#getting-started)
* [Design](#design)
  * [Architecture](#architecture)
  * [UI component](#ui-component)
  * [Logic component](#logic-component)
  * [Model component](#model-component)
  * [Storage component](#storage-component)
  * [Common classes](#common-classes)
* [Implementation](#implementation)
  * [Bookmark and Timetable modes feature](#bookmark-and-timetable-modes-feature-tys)
  * [Add Module and Slot feature](#add-module-and-slot-feature-xing-rong)
* [Documentation, logging, testing, configuration, dev-ops](#architecture)
* [Appendix: Requirements](#architecture)
  * [Product Scope](#architecture)
  * [User Stories](#architecture)
  * [Use cases](#architecture)
  * [Non-Functional Requirements](#architecture)
  * [Glossary](#glossary)
* [Appendix: Instructions for manual testing](#faq)
  * [Launch and shutdown](#command-summary)


## **Getting Started**
Refer to the setting up guide over [here](https://github.com/AY2021S1-CS2113T-W11-1/tp/releases).






## **Design**

### Architecture

The figure below shows a high-level design for the architecture of Zoomaster. 
![](https://github.com/TYS0n1/tp/blob/team-Branch2/docs/diagrams/archiveture2-01.png?raw=true)
*<center/> Figure 1.1 Architecture diagram of Zoomaster </center> <br/></br>*

Our Program can be split up into 8 components
* Initialization
* User Interface
* Parser
* Commands
* Temp Lists
* Storage
* Local Files
* Exceptions

These components interact with each other as shown in Figure 1.1 to execute the functionalities of Zoomaster.

### Initialization
The diagram below shows a class-level diagram for Zoomaster. <br/></br>
![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/initial.png)
*<center/> Figure 1.2 Class diagram of Initialization </center> <br/></br>*

**API**:`Zoomaster.java`

The Initialization component is responsive for setting up Zoomaster for it to be used by users. 

It consists of `Zoomaster`, `Ui`, `Storage`, `BookmarkList`, `Timetable` and `Module` classes.

Its main roles are:
* Retrieving bookmark, timetable and planner lists from storage if it exists.
* Set the list of Modules for Zoomaster 
* Initializes the User Interface object, as well as the 3 Storage  objects for `BookmarkList`, `Timetable` and `Planner`.

### User Interface component

![](images/UiClassDiagram.png)
*<center/> Figure 1.3 Class diagram of User Interface </center> <br/></br>*

**API**:`Ui.java`

The UI component is responsible for all visual output to the User from the app. 

The only class carrying out the component's function is the `Ui` class.

It utilises the `Jansi` library to enable the usage of ANSI escape codes to format the console, allowing the app to output in colour. <br/>

The UI also receives input from the User using a `Scanner` object. It returns the input as a String to the main function. <br/>

In addition, the UI contains the different exception and error messages which can be displayed. When a particular exception is thrown (eg. **UNKNOWN_INPUT**), the corresponding function is called in UI to print out the error message (**printUnknownInputMessage()**). 


Its main roles are:

* Receiving user commands and returning it to the Main function.
* Prints visual output in the console for the User


### Parser component



![]()
*<center/> Figure 1.4 Class diagram of Parser </center> <br/></br>*


The Parser component is responsible for decoding the user's input and telling the Main function which command to execute.

It also contains the **programMode** which indicates which mode the program currently is in.


It initializes the different commands according to which mode the program currently is in.

It consists of `Parser` and the Command interface classes.


Its main roles are:

* Decoding users commands and returning the correct command to the Main function to be executed
* Catch errors in users commands and return the appropriate exception to the Main function
* Storing the mode Zoomaster is in (Bookmark/Timetable/Planner)

The interaction of the Parser component with the Command component is covered in greater detail below. 

### Commands component
Figures 1.3 to 1.6 below show the class-level diagrams for Parser and Command for each different mode. <br/>
The diagrams are colour coded as such:
* Orange -> Global
* Green -> Bookmark Mode
* Blue -> Timetable Mode
* Red -> Planner Mode

![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode1.png)
*<center/> Figure 1.3 Class diagram of Commands valid in all modes (Global) </center> <br/></br>*
![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode2.png)
*<center/> Figure 1.4 Class diagram of Commands valid in Bookmark Mode </center> <br/></br>*
![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode3.png)
*<center/> Figure 1.5 Class diagram of Commands valid in Timetable Mode</center> <br/></br>*
![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode4.png)
*<center/> Figure 1.6 Class diagram of Commands valid in Planner Mode</center> <br/></br>*

The Command component is responsible for carrying out the functions of Zoomaster.

Usually, a successful command will return a message to indicate a successful execution or updates to Zoomaster. Otherwide it will create error messages for the Ui to display to the users.

It consists of `ChangeModeCommand`, `ClearCommand`,  `ExitCommand`,  `HelpCommand`,   `LaunchNowCommand`, `AddBookmarkCommand`,   `DeleteBookmarkCommand`,  `FindBookmarkCommand`,  `LaunchBookmarkCommand`, `ShowBookmarkCommand`, `AddTimetableCommand`, `DeleteTimetableCommand`, `ShowTimetableCommand`, `EditTimetableCommand`, `LaunchTimetableCommand`, `AddMeetingCommand`, `LoadPlannerCommand` and `SavePlannerCommand` classes.

Its main roles are:

* Executing commands to carry out functionalities of Zoomaster
* Signal to Ui successful execution of commands
* Create messages for Ui on updates to Zoomaster
* Catch errors or conflicts in users commands and return the appropriate exception to the Main function

### Temp List component

![]()

*<center/> Figure 1.7 Class diagram of Temp List </center> <br/></br>*

The Temp List component is responsible for holding on to temporary data of Zoomaster to be used by Commands.

It consists of `BookmarkList`, `SlotList`, `Module` and `Timetable`

Its main role is:

* Hold on to temporary data about Zoomaster

### Storage component

![]()

*<center/> Figure 1.8 Class diagram of Storage </center> <br/></br>*

The Storage component is responsible for saving and retrieving Zoomaster data to and from an external text file.

It uses `Gson` library to encode temporary data from Temp List into a HTML format. Then it writes the encoded data to an external text file. On the other hand, it decodes the HTML format from the external text file and update the Temp List of Zoomaster

The only class carrying out the component's function is the `Storage` class.

Its main roles are:

* Store Zoomaster data to an external text file for long term storage
* Retrieve Zoomaster data on Initialization
* Return error messages to the users during extraction or writing

### Local Files component

![]()
*<center/> Figure 1.9 Class diagram of Local Files </center> <br/></br>*


The Local Files component is where Zoomaster's long term storage of data is kept

Its main role is:

* Store Zoomaster data

### Exceptions component

![]()

*<center/> Figure 1.10 Class diagram of Exceptions </center> <br/></br>*

The Exceptions component is responsible for responding to the different errors different components of Zoomaster sends back to the Main function.

It extends the `Exception` class and uses it to catch unique exceptions thrown by different components of Zoomaster.

It consists of  `ZoomasterException` and `ZoomasterExceptionType` classes.

Its main role is:

* Create unique exceptions thrown by different components to signal the Main function what error has occured


## **Implementation**

This section explains the implementations of Zoomaster features. It goes through the step-by-step proccess, expected outcomes of each feature and the design considerations.

### Bookmark and Timetable modes feature (TYS)

Zoomaster stores two lists of information from users. One the list of bookmarks with link to online resources and second the list of timetable slots. To simplify input commands for users, both lists has the same keywords for adding, deleting, editing? and showing items in the lists. Hence by having seperating both list into different modes allows both lists to access the same keywords without causing conflicts when parsing commands.

#### Implementation

This feature extends Command class with a way to toggle between different modes of Zoomaster. The integer variable used to control the modes is stored in the Parser class called "programMode". Additionally, it implements the following operation:
* getModeFromCommand() - Decodes the command sent by the users to figure out which mode the user wants to move to.

Given below is a sequential diagram of how changing between modes occur.

![](https://github.com/TYS0n1/tp/blob/team-Branch2/docs/diagrams/ChangeModeCommand%20seq%20dia.png?raw=true) <br/><br/>

*<center/>Figure 2.1 Sequential diagram for ChangeModeCommand</center> <br/></br>*


1. When Zoomaster gets a command from the user to change modes, a new ChangeModeCommand object is created.

2. The ChangeModeCommand passes the command through getModeFromCommand() function to decode the mode the user wishes to change to.

3. Zoomaster now executes the command and changes to the respective mode. If an invalid mode was given by the user or if the input field was empty, the execute function throws an exception and tells the user valid modes for Zoomaster.

The following activity diagram summarizes what happens when a user executes a new command:

![](https://github.com/TYS0n1/tp/blob/team-Branch2/docs/diagrams/activity%20diagram%20change%20mode%20command.png?raw=true) 

*<center/> Figure 2.2 Activity diagram for ChangeModeCommand </center> <br/></br>*

#### Design consideration:

##### Aspect: How to store programMode variable for security

* **Alternative 1 (Current choice):** No security
    * Pros: Easy to implement
    * Cons: May introduce errors to the App if variable is changed outside of ChangeModeCommand class objects.
* **Alternative 2:** Private variable and implement mode changing inside Parser class
    * Pros: Ensure the App does not change modes outside commands to change modes.
    * Cons: Reduces OOP standard of code by decoupling ChangeModeCommand from Command class and increases code complexity.


### Add Module and Slot feature (Xing Rong)
This feature allows the user to add modules and lesson slots into the timetable.
Users can also add bookmarks to specific modules and slots.
Users can enter one-shot-commands, adding multiple slots and bookmarks to a module.

Given below is a sequence diagram of how the feature works.
![](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/diagrams/addSlotSequenceDiagram.png?raw=true)
*<center/> Figure 2.3 Sequence diagram for AddSlotCommand </center> <br/></br>*

1. After calling execute() method of the AddSlotCommand object, there will be a check on whether the module code entered by the user already exists in the timetable. If it does not exist, then the module will be created.

2. There will then be a check for additional commands pertaining to the module entered by the user.

3. The code will then check if the command is to add a module bookmark or a lesson slot, and do so accordingly.

4. If the command is to add a lesson slot, then there will be check for a bookmark entry in the command. If there is one, then the bookmark will be added to the lesson slot.

5. Loop to step 3 if there are additional commands which have not been executed.

#### Design consideration:

##### Aspect: How to enable fast typing users to add modules, slots and related bookmarks faster
* **Alternative 1 (Current choice):** allow one shot command to add slots and bookmarks to a module
    * Pros: Fast typers can input faster
    * Cons: Difficult to implement
* **Alternative 2:** separate adding of modules, lesson slots and related bookmarks into different commands
    * Pros: Easy to implement
    * Pros: Lower chance of error
    * Cons: User has to enter multiple commands each at a time to perform the functions, which takes up more time.

### Edit Slot feature (Francisco)

This feature allows users to edit a slot's title or time. Users can also move slots over to another module.

Given below is an example usage scenario and how the edit mechanism works.

1. The user enters "edit title mon 2 new_title"
2. A new EditSlotCommand instance is created and the execute() method is called.
3. The slot corresponding to the day and index given in the user input is retrieved by calling the getSlotByIndexInDay method of the timetable. In this case, the 2nd slot on Monday will be returned.
4. Based on the chosen field in the user input, different methods are called:
    a. If the command is "edit module", the moveSlotToAnotherModule method in timetable is called to move the slot to a given module.
    b. If the command is "edit title", the setTitle method of the retrieved slot is called.
    c. If the command is "edit time", changeSlotTime is called. This method will call setDay(), setStartTime(), and setEndTime() of the retrieved slot.

The sequence diagram below explains how this feature is executed:

 ![](https://raw.githubusercontent.com/fchensan/tp/docs-images/docs/images/editslotsequence.png)




## **Product scope**
### Target user profile

* NUS student
* has fast typing skills 
* comfortable with using the command line interface

### Value proposition

Zoomaster was developed during the coronavirus pandemic whereby many NUS classes have been transitioned towards online lessons. NUS lessons are mainly conducted on Zoom video conferencing software. However, as Zoom does not store recurring nor past meetings, it is hard for students to easily access their online lessons. Hence, Zoomaster helps to organise students’ Zoom links for easy access to their lesson.

## **User Stories**

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|studious NUS student| bookmark important websites from different modules|launch them easily when needed|
|v1.0|NUS student|label my bookmarks|know quickly the topics of each link|
|v1.0|busy NUS student|create a timetable within the app which syncs up with the system time|avoid missing my lessons|
|v1.0|NUS student|take a look at my modules for the day, or the entire week|plan out my day/week|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
|v2.0|first time user of Zoomaster|be able to see the list commands available|easily navigate through the Zoomaster|
|v2.0|advanced user|be able to launch multiple links at the same time if the links are grouped together|save time by not doing multiple launching commands|
|v2.0|advanced user|edit my bookmarks and timetable lists according to changes in my module and timetable|quickly make changes to my lessons|
|v2.0|busy user|have an indicator telling me the current time|easily check on the time in a hurry|
|v2.0|fast typer|to be able to type a one-shot-command when entering the details of the lessons in my timetable|add the lesson details faster|

## **Non-Functional Requirements**

1. The App should work on any mainstream OS as long as it has Java `11` installed.
2. A user with above average typing speed should be able to accomplish most of the tasks faster using commands than using the mouse.

## **Glossary**

### Zoom
> A popular video communication program by a company of the same name

### UML 
> Unified Modeling Language, a standard to visualize the design of a system

### UI
> User Interface
    
### ANSI 
> American National Standards Institute, ANSI characters is a popular character set used by programmers
    
### NUS
> National University of Singapore

## **Instructions for manual testing**

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

1. Initial launch
    1. Download the jar file and copy into an empty folder.
    2. Open the command prompt and change directory to the location of the jar file.
    3. Enter `java -jar zoomaster.jar` in the command line. You should expect to see the welcome screen of the application.
