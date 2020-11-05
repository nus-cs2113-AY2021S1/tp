# **Developer Guide**


## **Introduction**
 ![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/zoomwhitebg.png) <br/><br/>
### Introduction to Zoomaster
Zoomaster is a Java application for the Command Line. It provides a simple and intuitive way to store Zoom links for 
online classes abd other useful links for the lesson at hand.<br/>

Zoomaster can also intelligently determine the current lesson users are having, allowing them to launch the correct 
links quickly and elegantly from the command line.

### Target audience
This developer guide is for experienced programmers with knowledge of object oriented programming.


### Purpose
This guide seeks to introduce to you the design and implementation of Zoomaster features. It will share our reasoning 
behind the way we implemented different features and its logic flow. With this, you would be able to tweak and further 
develop Zoomaster without confusion or introduce unwanted bugs to the App.

### Scope
First, the guide will help you set up Zoomaster in its current iteration so that you can familiarise with it. <br/> 
Secondly, the guide will explain the design of Zoomaster and its various components. <br/>
Next, the guide will showcase how we implement various features into Zoomaster with 
step by step explanations and diagram. <br/>
Finally, the guide has several appendixes explaining the scope of the product, user stories, non-function requirements, 
glossary and instructions for manual testing.

### Navigation
You can navigate the guide via the table of contents below. <br/>
Otherwise, each major section is distinguished by a bold header and underline. Sub-sections are of a smaller font than 
major sections but still larger than normal paragraphs to distinguish them.

### Table of contents
* [Getting Started](#getting-started)
* [Design](#design)
  * [Architecture](#architecture)
  * [Initialization](#initialization)
  * [UI component](#ui-component)
  * [Parser component](#parser)
  * [Commands component](#command)
  * [Temporary list component](#temp-list)
  * [Storage component](#storage-component)
  * [Local files component](#local-files)
  * [Exceptions component](#exceptions)
* [Implementation](#implementation)
  * [Bookmark and Timetable modes feature](#mode)
  * [Show timetable feature](#show-timetable)
  * [Add Module and Slot featurea](#add-module-slot)
* [Appendix A: Product Scope](#appendix-a)
* [Appendix B: User Stories](#appendix-b)
* [Appendix C: Non-Functional Requirements](#appendix-c)
* [Appendix D: Glossary](#appendix-d)
* [Appendix E: Instructions for manual testing](#appendix-e)
  * [Launch and shutdown](#command-summary)

<a name="getting-started"></a>
## **Getting Started**
First, download the source cod and jar file of Zoomaster [here](https://github.com/AY2021S1-CS2113T-W11-1/tp/releases).

Next, follow the startup procedures as stated in the 
[User Guide](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/UserGuide.md) and familiarize yourself with 
Zoomaster's features.

Now, you can dive into the source code and explore the inner workings of Zoomaster with this guide.

<a name="design"></a>
## **Design**

This section explains the design behind Zoomaster by first sharing the grand architecture of the code 
then its various components.

<a name="architecture"></a>
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

<a name="initialization"></a>
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

<a name="ui-component"></a>
### User Interface component

**API**:`Ui.java`

The UI component is responsible for all visual output to the User from the app. 

The only class carrying out the component's function is the `Ui` class.

It utilises the `Jansi` library to enable the usage of ANSI escape codes to format the console, 
allowing the app to output in colour. <br/>

The UI also receives input from the User using a `Scanner` object. It returns the input as a String 
to the main function. <br/>

In addition, the UI contains the different exception and error messages which can be displayed. 
When a particular exception is thrown (eg. **UNKNOWN_INPUT**), the corresponding method is called in UI to 
print out the error message (**printUnknownInputMessage()**). 


Its main roles are:

* Receiving user commands and returning it to the Main function.
* Prints visual output in the console for the User

<a name="parser"></a>
### Parser component

![](https://github.com/TYS0n1/tp/blob/master/docs/diagrams/parser%20class%20diagram.png?raw=true)
*<center/> Figure 1.3 Class diagram of Parser </center> <br/></br>*


The Parser component is responsible for decoding the user's input and telling the Main function 
which command to execute.

It also contains the **programMode** which indicates which mode the program currently is in.


It initializes the different commands according to which mode the program currently is in.

It consists of `Parser` and the Command interface classes.


Its main roles are:

* Decoding users commands and returning the correct command to the Main function to be executed
* Catch errors in users commands and return the appropriate exception to the Main function
* Storing the mode Zoomaster is in (Bookmark/Timetable/Planner)

The interaction of the Parser component with the Command component is covered in greater detail below. 

<a name="command"></a>
### Commands component
Figures 1.4 to 1.7 below show the class-level diagrams for Parser and Command for each different mode. <br/>
The diagrams are colour coded as such:
* Orange -> Global
* Green -> Bookmark Mode
* Blue -> Timetable Mode
* Red -> Planner Mode

![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode1.png)
*<center/> Figure 1.4 Class diagram of Commands valid in all modes (Global) </center> <br/></br>*
<br></br>
![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode2.png)
*<center/> Figure 1.5 Class diagram of Commands valid in Bookmark Mode </center> <br/></br>*
<br></br>
![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode3.png)
*<center/> Figure 1.6 Class diagram of Commands valid in Timetable Mode</center> <br/></br>*
<br></br>
![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/mode4.png)
*<center/> Figure 1.7 Class diagram of Commands valid in Planner Mode</center> <br/></br>*
<br></br>
The Command component is responsible for carrying out the functions of Zoomaster.

Usually, a successful command will return a message to indicate a successful execution or updates to Zoomaster. 
Otherwise, it will create error messages for the Ui to display to the users.

It consists of `ChangeModeCommand`, `ClearCommand`,  `ExitCommand`,  `HelpCommand`,   `LaunchNowCommand`, 
`AddBookmarkCommand`,   `DeleteBookmarkCommand`,  `FindBookmarkCommand`,  `LaunchBookmarkCommand`, 
`ShowBookmarkCommand`, `AddTimetableCommand`, `DeleteTimetableCommand`, `ShowTimetableCommand`, `EditTimetableCommand`, 
`LaunchTimetableCommand`, `AddMeetingCommand`, `LoadPlannerCommand` and `SavePlannerCommand` classes.

Its main roles are:

* Execute commands to carry out functionalities of Zoomaster
* Signal to Ui successful execution of commands
* Create messages for Ui on updates to Zoomaster
* Catch errors or conflicts in users commands and throw the appropriate exception to the Main function

<a name="temp-list"></a>
### Temp List component

The Temp List component is responsible for holding on to temporary data of Zoomaster to be used by Commands.

It consists of `BookmarkList`, `SlotList`, `Module` and `Timetable`

Its main role is:

* Hold on to temporary data about Zoomaster

<a name="storage"></a>
### Storage component

The Storage component is responsible for saving and retrieving Zoomaster data to and from an external text file.

It uses `Gson` library to encode temporary data from Temp List into a HTML format. Then it writes the encoded data to 
an external text file. On the other hand, it decodes the HTML format from the external text file and update the 
Temp List of Zoomaster.

The only class carrying out the component's function is the `Storage` class.

Its main roles are:

* Store Zoomaster data to an external text file for long term storage
* Retrieve Zoomaster data on Initialization
* Return error messages to the users during extraction or writing

<a name="local-files"></a>
### Local Files component

The Local Files component is where Zoomaster's long term storage of data is kept

Its main role is:

* Store Zoomaster data

<a name="exceptions"></a>
### Exceptions component

The Exceptions component is responsible for responding to the different errors different components of Zoomaster sends 
back to the Main function.

It extends the `Exception` class and uses it to catch unique exceptions thrown by different components of Zoomaster.

It consists of  `ZoomasterException` and `ZoomasterExceptionType` classes.

Its main role is:

* Create unique exceptions thrown by different components to signal the Main function what error has occurred


## **Implementation**

This section explains the implementations of Zoomaster's features. It goes through the step-by-step process, 
expected outcomes of each feature and the design considerations.

<a name="mode"></a>
### Bookmark, Timetable and Planner modes feature (TYS)

Zoomaster has three modes for users to interact in. First, bookmark mode has the list of bookmarks with links to online resources. 
Secondly, timetable mode has a list of timetable slots. Lastly, planner mode which helps users plan their timetable. 
To simplify input commands for users, all lists has the same keywords for adding, deleting, and showing items in the lists. 
Hence by having seperating both list into different modes allows both lists to access the same keywords without causing conflicts when parsing commands.

#### Implementation

This feature extends Command class with a way to toggle between different modes of Zoomaster. The integer variable used to control the modes is stored in the Parser class called "programMode". Additionally, it implements the following operation:
* getModeFromCommand() - Decodes the command sent by the users to figure out which mode the user wants to move to.

Given below is a sequence diagram of how changing between modes occur.

![](https://github.com/TYS0n1/tp/blob/master/docs/diagrams/ChangeModeCommand%20seq%20dia.png?raw=true) <br/><br/>

*<center/>Figure 2.01 sequence diagram for ChangeModeCommand</center> <br/></br>*


1. When Zoomaster gets a command from the user to change modes, a new ChangeModeCommand object is created.

2. The ChangeModeCommand passes the command through getModeFromCommand() function to decode the mode the user wishes to change to.

3. Zoomaster now executes the command and changes to the respective mode. 

4. If an invalid mode was given by the user or if the input field was empty, the execute function throws an exception and tells the user valid modes for Zoomaster.

The following activity diagram summarizes what happens when a user executes a new command:

![](https://github.com/TYS0n1/tp/blob/master/docs/diagrams/activity%20diagram%20change%20mode%20command.png?raw=true) 

*<center/> Figure 2.02 Activity diagram for ChangeModeCommand </center> <br/></br>*

#### Design consideration:

##### Aspect: How to store programMode variable for security

* **Alternative 1 (Current choice):** No security
    * Pros: Easy to implement
    * Cons: May introduce errors to the App if the variable is changed outside of ChangeModeCommand class objects.
* **Alternative 2:** Private variable and implement mode changing inside Parser class
    * Pros: Ensure the App does not change modes outside commands to change modes.
    * Cons: Reduces OOP standard of code by decoupling ChangeModeCommand from Command class and increases code complexity.

<a name="show-timetable"></a>
### Show timetable feature (Tan Yu Shing)
Users can see the timetable they have created in the App using the **show** command. The can see complete timetable from monday to sunday, the timetable of a specified day of the week or the timetable today. The commands for these are **show**, **show {DAY}** eg. **show mon**, **show tue** and **show today**. 

#### Implementation
This feature extends the command class. It is a simple retrieval algorithm which firstly gets data from the Timetable class. Then sorts it by timing and add additional indicators for the users. And finally, prints it our using the User Interface. </br> It uses SlotContainer class sortSlotsByTime method to help sort the list of lessons and it's module code by timing. </br>
Additionally, it implements the following operations:
* getMessageSlotsInADay(List<Module> **modules**, List<Slot> **slots**, String **day**) - Retreives all the lesson **slots** and it's respective **module** code on the **day** specified. It then sorts the lessons by timing and returns it as a **message string**. </br> If valid, additional formatting such as current lesson indicator or current time indicator is added to the **message string**.
* getMessageTimetable(List<Module> **modules**, List<Slot> **slots**) - Retreives all the lesson **slots** and it's respective **module** code for the whole week. It then sorts the lessons by timing and returns it as a **message string**.  </br> If valid, additional formatting such as current lesson indicator or current time indicator is added to the **message string**.
* getMessageLessonAtTime(List<Module> **modules**, List<Slot> **slots**, String **dayInput**) - Decodes the mode the user wants the timetable to be printed out in using **dayInput**. </br> Then calls the appropriate methods such as getMessageSlotsInADay and getMessageTimetable to get the **message string** which is then returned to the execute function to be printed out by the User Interface. If the the timetable is empty or an invalid **dayInput** is given, an **exception** is thrown to tell users their mistake.
* hasLessonNow(Slot **slot**) - Checks if a **slot** timing is overlapping with the current system time. Returns a **boolean** true or false based on the check.
* getIndicatorMessage() - Returns a **String** containing a indicator with the current system time.
* getHighlighBoxUpperMessage() - Returns a **String** containing a indicator with a message "lesson now".
* getHighlighBoxLowerMessage() - Returns a **String** containing a indicator.

Given below is a sequence diagram of how printing the timetable occurs.
![](https://github.com/TYS0n1/tp/blob/master/docs/diagrams/ShowTimetableCommand%20seq%20dia%201.png?raw=true)
*<center/>Figure 2.03 sequence diagram for ShowTimetableCommand</center> <br/></br>*

![](https://github.com/TYS0n1/tp/blob/master/docs/diagrams/ShowTimetableCommand%20seq%20dia%202.png?raw=true)
*<center/>Figure 2.04 sequence diagram for "Initialize ShowTimetableCommand" Block</center> <br/></br>*

![](https://github.com/TYS0n1/tp/blob/master/docs/diagrams/ShowTimetableCommand%20seq%20dia%203.png?raw=true)
*<center/>Figure 2.05 sequence diagram for "Execute ShowTimetableCommand" Block</center> <br/></br>*

1. When Zoomaster gets a command from the user to show the timetable, a new ShowTimetableCommand object is created.

2. The ShowTimetableCommand passes the command through getModeFromCommand() function to decode the mode the user wishes to view the timetable in.

3. If an invalid input day is entered by the user, the input day will be set as **null**.

4. Zoomaster now executes the command and displays the timetable in the requested mode. 

5. If the input day is **null**, no timetable will be printed out. Instead, the program checks for does checks for Show Lesson Bookmarks feature.

The following activity diagram summarizes what happens when a user executes a new command:
![](https://github.com/TYS0n1/tp/blob/master/docs/diagrams/activity%20diagram%20show%20timetable%20command.png?raw=true)
*<center/>Figure 2.06 Activity diagram for ShowTimetableCommand</center> <br/></br>*


#### Design consideration:

##### Aspect: What keyword for the user to input to get show timetable feature.

* **Alternative 1 (Current choice):** Using `show` keyword and a valid `DAY(optional)` input.
    * Pros: Able to use `show` keyword for the show module and slot details feature too.
    * Cons: Unable to show error message for an invalid `DAY(optional)` input 
    as the program reads the input as a `MODULE` input instead. Users have to use `help` command or
    to refer to the User Guide to receive help.
* **Alternative 2:** Using `list` keyword and a valid `DAY(optional)` input.
    * Pros: Easier to create code. No need to have an algorithm figure out if user wants to access show timetable
    feature or show module and slot details feature.
    * Cons: Less user-friendly. Users have to remember another keyword for showing data from Zoomaster.
* **Alternative 3:** Using `show timetable` keyword and a valid `DAY(optional)` input.
    * Pros: Less complex code. An additional keyword allows program to easily recognise show timetable feature.
    * Cons: Less user-friendly. Users have to type an additional phrase to show their timetable. Experienced users
    whom can memorise the command would not encounter the error message of Alternative 1, thus would find typing the
    additional keyword troublesome.


<a name="add-module-slot"></a>
### Add Module and Slot feature (Xing Rong)
This feature allows the user to add modules and lesson slots into the timetable.
Users can also add bookmarks to specific modules and slots.
Users can enter one-shot-commands, adding multiple slots and bookmarks to a module.

Given below is a sequence diagram of how the feature works.
![](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/diagrams/addSlotSequenceDiagram.png?raw=true)  
*<center/> Figure 2.07 Sequence diagram for AddSlotCommand </center> <br/></br>*
<br></br>
![](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/diagrams/addSlotSequenceDiagram2.png?raw=true)  
*<center/> Figure 2.08 Sequence diagram for 
"Get module if it exist, else create a new module" Block </center> <br/></br>*
<br></br>
![](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/diagrams/addSlotSequenceDiagram3.png?raw=true)  
*<center/> Figure 2.09 Sequence diagram for 
"Create bookmark for module" Block </center> <br/></br>*
<br></br>
![](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/diagrams/addSlotSequenceDiagram4.png?raw=true)  
*<center/> Figure 2.10 Sequence diagram for 
"Create bookmark for existing slot base on its index" Block </center> <br/></br>*
<br></br>
![](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/diagrams/addSlotSequenceDiagram5.png?raw=true)  
*<center/> Figure 2.11 Sequence diagram for 
"Get slot if it exist, else create a new slot" Block </center> <br/></br>*
<br></br>
![](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/diagrams/addSlotSequenceDiagram6.png?raw=true)  
*<center/> Figure 2.12 Sequence diagram for 
"Create bookmark for slot" Block </center> <br/></br>*
<br></br>
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
*<center/> Figure 2.13 Sequence diagram for EditSlotCommand </center> <br/></br>*


### Planner feature (Jusuf)

This feature is an extension of the timetable feature that allows users to find common empty slots from each individual timetable. The users can then add a new meeting, and the app will automatically write the meeting to each timetable.

Below is the general flow on how the mechanism works:
1. The different timetables must first be loaded to the `planner` folder manually.
2. In the app, the user can enter the `load` command to load all the timetables and initialise the common empty slots.
3. To view the slots, the user can enter the `show` command with or without the day (optional).
4. The user can also call the `add` command to add a new meeting, similar to the timetable feature.
5. Finally, the user can call the `save` command to store the newly added meeting(s) to each individual timetable.


<a name="appendix-a"></a>
## **Appendix A: Product scope**
### Target user profile

* NUS students
* Students with fast typing skills 
* Students comfortable using the command line interface

### Value proposition

Zoomaster was developed during the coronavirus pandemic whereby many NUS classes have been transitioned towards online lessons. 
NUS lessons are mainly conducted on Zoom video conferencing software. 
However, as Zoom does not store recurring nor past meetings, it is hard for students to easily access their online lessons. 
Hence, Zoomaster helps to organise students’ Zoom links for easy access to their lesson.

<a name="appendix-b"></a>
## **Appendix B: User Stories**

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

<a name="appendix-c"></a>
## **Appendix C: Non-Functional Requirements**

1. The App should work on any mainstream OS as long as it has Java `11` installed.
2. A user with above average typing speed should be able to accomplish most of the tasks faster using commands than using the mouse.

<a name="appendix-d"></a>
## **Appendix D Glossary**

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

### Mainstream OS
> Windows, Linux, Unix, OS-X

<a name="appendix-e"></a>
## **Appendix E: Instructions for manual testing**

1. Initial launch
    1. Download the jar file and copy into an empty folder.
    2. Open the command prompt and change directory to the location of the jar file.
    3. Enter `java -jar zoomaster.jar` in the command line. You should expect to see the welcome screen of the application.
2. Testing
    1. Download the test cases text file from github [here](https://github.com/AY2021S1-CS2113T-W11-1/tp/tree/master/text-ui-test). <br></br>
    Its name is input.txt. 
    2. These are some the sample test cases used to test if the program is working as intended. You should get the same results <br></br>
    as shown in EXPECTED.txt file.
    3. You can refer to the [User Guide](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/docs/UserGuide.md) for the full list <br></br>
    of features and available commands for Zoomaster.
    4. Now you can manually input test cases into Zoomaster and see the results.
    5. Note that the output of the application is dependent on the system time of your machine.
3. Saving and Loading
    1. Saving and loading of data is done automatically in Zoomaster. 
    2. The application uses the Gson library to convert the java objects in Zoomaster into JSON constructs, then 
    writing them into the text files stored locally in the machine.
    3. Data is stored in 3 separate text files; `bookmarks.txt`, `modulelist.txt` and `timetable.txt` which are located
    in the `data` folder. The `data` folder is created in the directory where `zoomaster.jar` is placed.
    4. To test the saving feature, you can enter input some data into the Zoomaster through the command line interface
     such as using the commands `mode timetable` then `add CS2113T`.
    5. Exit the application by using the command `exit` and look in the `timetable.txt` file. The module "CS2113T" 
    should be recorded.
    6. You can test the loading feature by launching Zoomaster and entering the commands `mode timetable` 
    and `add CS2113T`. There should be a message that the module "CS2113T" already exists. 
    7. You can also edit the data in the text files in JSON format and test the loading of data into the Zoomaster by 
    launching the application. You can refer to the class diagrams above for the associations between the classes.
