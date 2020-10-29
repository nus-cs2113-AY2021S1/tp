# Developer Guide

## Introduction
This section provides an introduction to the developer guide for *25HoursADay*.

### Introduction to *25HoursADay*
*25HoursADay* is a scheduling app optimized for users via a convenient yet powerful Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, *25HoursADay* can schedule your tasks and events faster than traditional GUI apps.

### Purpose and scope
The purpose of this developer guide is to describe the architecture and software design decisions for the task scheduling application *25HoursADay*. This developer guide covers an overview of the program architecture, the logical view of major components, and the mechanism of the functions.

The intended audience of this guide are the developers and software testers of *25HoursADay*.

### Developer guide organization
The table below shows the content of the developer guide.

|Section| Purpose | 
|--------|----------|
|[Section 2: Setting up](#setting-up)|To provide instructions on how to download and set up *25HoursADay* on your computer.|
|[Section 3: Design](#design)|To explain the architecture of *25HoursADay*,  and describe the major components of *25HoursADay*, the roles of major components, as well as their organization and interaction of major components.|
|[Section 4: Implementation](#implementation)|To explain the mechanism of functions added to *25HoursADay* and the features added to *25HoursADay*.|
|[Section 5: Documentation](#documentation)|To represent documents describing the system and its parts.|

## Setting Up & Getting started
This section provides on setting up the *25HoursADay* application.

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
When you are ready to start coding, we recommend that you get some sense of the overall design by reading about [*25HoursADay*’s architecture](#architecture).

## Design
This section describes the design overview of the *25HoursADay* application.

### Architecture

The figure below shows the overall design of the application. 
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/Overall_Architecture.JPG" alt="" width="300"/> <br/>

The sequence diagram below shows how the components interact with each other for the scenario where the user issues the command `todo`.
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/Archi_SD.JPG" alt="" width="750"/>

An overview of each components is listed below.

### Ui
The `Ui` class is responsible for the user interface of the application. It receives the input from the user and prints the results to the user.

### Parser

The `Parser` class is responsible for interpreting the user's input and calling the respective command. 

### Command

The different `Command` classes are responsible for receiving the user's input from the `Parser` class and executing the corresponding commands. 
The figure belows shows the class diagram of the command class: <br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/command.JPG" alt="" width="200"/><br/>
All Command classes inherit from the `Command` class.

### Storage

The `Storage` class is responsible for reading and writing data to the hard disk.


### Model
The model is responsible for holding the data of the application in memory. The figure below shows the structure of the Model in this application.

<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/Model_Class_Diagram.JPG" alt="" width="750"/>

 When executing commands,the `CalendarItem` class updates the information or provides the information of different types of items if needed. 
It is split into two subclasses: `Task` and `Event` class.
The `CalendarList` class holds the array of `CalendarItem` and keeps track of the number of total items, total tasks, and total events.

The `Task` and `Event` classes are further explained below.

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
This section describes the details of how each feature is implemented.

### Add a calendar item feature
This feature is facilitated by `AddCommand`.
The following sequence diagram shows how the `execute()` operation works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/add_command_SD.JPG" alt="" width="750"/><br/>

Note: A self-call to a specific method to add the new `CalendarItem` to the `CalendarList` is done based on the `CalendarItem` being added.  

`CalendarItems` with deviations to sequence diagram above: 
1. All `CalendarItems` with a date or time attribute calls the `TimeParser` class to interpret the date and time input of the user. 
2. `Lecture`, `Lab`, `Tutorial`, or `Exam` type does a self-call for an additional check using the function `isValid(command)`. It ensures the module code included in the item is valid.
3. `Lecture`, `Lab`, or `Tutorial`can be added as recurring items (multiple addition of the same class).   

### Mark a task as done feature
This feature is facilitated by `DoneCommand`.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/done_command_SD.JPG" alt="" width="750"/><br/>
Note: The command first extracts the task number from the user input prior to `convertTaskNumberToCalendarNumber`. 
There will be a check in the function `markTaskAsDone(calendarNumber)` to ensure that the calendar item being marked as done is a task. 


### Additional information of an event feature
#### Add additional information
This feature is facilitated by `AddInfoCommand`.
The following sequence diagram show how the `execute()` operation of `AddInfoCommand` works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/addInfoCommand_SD.JPG" alt="" width="750"/><br/>

#### View additional information
This feature is facilitated by `ViewInfoCommand`.

The following sequence diagram show how the `execute()` operation of `ViewInfoCommand` works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/viewInfoCommand_SD.JPG" alt="" width="750"/><br/>

#### Delete additional information
This feature is facilitated by `DeleteInfoCommand`.

The following sequence diagram show how the `execute()` operation of `DeleteInfoCommand` works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/deleteInfoCommand_SD.JPG" alt="" width="750"/><br/>

Note: The commands first extracts the event number from the user input prior to `convertEventNumberToCalendarNumber`. 

### Delete a calendar item feature
This feature is facilitated by `DeleteCommand`.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/deleteCommand_SD.JPG" alt="" width="750"/><br/>

Note: The command first extracts the task/event number from the user input prior to `convertTaskNumberToCalendarNumber` and `convertEventNumberToCalendarNumber` respectively. 


### Find a calendar item feature
This feature is facilitated by `FindCommand`.
The following sequence diagram show how the `execute()` operation works when the user searches the entire calendar.:<br/>
<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-T12-2/tp/master/images/findCommand_SD.JPG" alt="" width="500"/><br/>

The search for tasks or events feature has a similar sequence diagram with a slight difference to the varying condition. Depending
on whether the user searches for tasks or events, the condition will check for the instance of either the task or event respectively.

### Print tasks feature
This feature is facilitated by `PrintTasksCommand`.
The following sequence diagram shows how the `execute()` operation works when the user wants to print the list of tasks stored in the program.
![print_tasks_command_sd](../images/PrintTasksCommand_SD.png)

### Print events feature
This feature is facilitated by `PrintEventsCommand`.
The following sequence diagram shows how the `execute()` operation works when the user wants to print the list of events stored in the program.
![print_events_command_sd](../images/PrintEventsCommand_SD.png)

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

### Print countdown feature
This feature is facilitated by `CountdownCommand`.

The following sequence diagram shows how the `execute()` operation works when the user decide to see the countdown of exams or deadlines:<br/>
![countdown_command_sd](../images/countdown_command_SD.jpg)

Note: Before printing the countdown, `countdown()` function will calculate the countdown of exams or deadlines, and the countdowns for
exams or deadlines will be sorted in ascending sequence by function `sortDeadlinesAndPrintCountdown()` or `sortExamsAndPrintCountdown()`


### Saving data feature

When you close the program, or you make some changes to the program such as adding an event, the data for the tasks and events 
are automatically saved locally into file `tasks.txt`. So there is no need for users to save manually.

Next time when you open the program, all the data will automatically be loaded from the local file `tasks.txt` to the program.

### Check the validity of a module code
This feature is facilitated by `ModuleChecker` class and `NusModule` class.

The `NusModule` class consists of an empty constructor, a getter and setter to retrieve and set the module code of a `NusModule` object.
The `ModuleChecker` class has a *generateNusModsMap* which generates a HashMap<String, NusModule>. This is achieved by reading
the JSON file from the NUS Mod website and parser it into a list of Module objects.

After which, the isModuleValid function will analyse the module code entered by the user, and returns TRUE if the code is 
valid, FALSE otherwise.

The following sequence diagram further illustrates the above process. 
![ModuleChecker_SD]()

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

### 1. Build automation
This project uses Gradle for **build automation and dependency management.**
**You are highly recommended to read [this Gradle Tutorial from the se-edu/guides](https://se-education.org/guides/tutorials/gradle.html).

Given below are how to use Gradle for some important project tasks.

* `clean`: Deletes the files created during the previous build tasks (e.g. files in the `build` folder).
e.g. `./gradlew clean`

* `shadowJar`: Uses the ShadowJar plugin to creat a fat JAR file in the `build/lib` folder, if the current file is outdated.
e.g. `./gradlew shadowJar`

* `run`: Builds and run the program.
  `runShadow`: Builds the application as a fat JAR, and then runs it.
    
* `checkstyleMain`: Runs the code style check for the main code base.
  `checkstyleTest`: Runs the code style check for the test code base.
  
* `test`: Runs all tests.
    * `./gradlew test` - Runs all tests
    * `./gradlew clean test` - Cleans the project and runs tests

### 2. Continuous integration (CI)
This project uses GitHub Actions for CI. The project comes with the necessary GitHub Actions configurations files 
(in the `.github/workflows` folder). No further setting up required.

### 3. Make a release
Here are the stpes to create a new release after you have implemented new features.

1. Update the version number in `Main.java`.
1. Generate a fat JAR file using Gradle (i.e. `gradle shadow`).
1. Tag the repo with the version number e.g: `V2.0`.
1. [Create a new release using Github](https://docs.github.com/en/free-pro-team@latest/github/administering-a-repository/managing-releases-in-a-repository).
1. Upload the JAR file you created.

## Appendix A: About the product 
This section provides a description of the product.

### Product scope
*25HoursADay* is a task scheduling program optimized for use via a Command Line Interface (CLI) while still 
having the benefits of a Graphical User Interface (GUI). It serves as a one-stop application for the users to keep track of their
tasks, events and any 
It is specially catered to NUS students, offering features relevant to a NUS student. 

### Target user profile

* NUS student
* has the need to manage a significant number of day-to-day matters
* prefer desktop command line apps over other types
* prefer typing to using mouse interactions
* comfortable with using command line apps
* forgetful person who needs reminders
* a fast typer


### Value proposition

By using *25HoursADay*, it provides an all-in-one app for the users to keep track of his/her day-to-day matters. Without the
need to search through different platforms for information. *25HoursADay* can manage one's day-to-day matters faster than a 
typical mouse/GUI driven app.

## Appendix B: User Stories
This section describes the user stories considered when implementing the features.

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|user|add different tasks and events|better manage my day-to-day matters|
|v1.0|user|set my tasks as done|track my tasks better|
|v1.0|user|delete my calendar items|remove unwanted items and organise my calendar better |
|v1.0|user|print the items added based on their categories|have a clear picture of what I have on hand|
|v1.0|user|see my progress of tasks|know the percentage of tasks I have finished and adjust my working pace| 
|v1.0|user|store all the data locally|load my saved data to the app next time|
|v2.0|user|find an item in my calendar|locate an item without having to go through the entire list|
|v2.0|NUS student|add information about my classes|locate all the information about my class on this app|
|v2.0|NUS student|delete information about my classes|can keep the information about my class relevant at all times |
|v2.0|user|mark my tasks as important|distinguish important tasks and the ordinary tasks|
|v2.0|user|get some suggestions when I do not know what to do|prepare for important and urgent tasks first|
|v2.0|NUS student|add my school events recursively|save my time typing out the events one by one|
|v2.0|NUS student|see the exam and deadline countdown|be conscious about the coming exams and deadlines and manage my time more wisely|

## Appendix C: Non-Functional Requirements

1. The program should work on any _mainstream_ OS as long as it has **Java 11.0.8** or above installed.
1. The program should be able to hold up to 1000 tasks without a noticeable slowness in performance for typical usage.
1. A user with an above average typing speed for regular English text (e.g not code, not system admin commands) should 
find it handy to use command lines rather than using the mouse.
1. The program should be able to detect all NUS modules, provided there is proper connection established between the program
and the NUS Mods API.   

{Give non-functional requirements; More to add}

## Appendix D: Glossary

* *Task* - a todo item or a deadline item.
* *School event* - a lecture, tutorial, lab session or an examination.
* *Event* - an activity or a school event.
* *Calendar item* - a task or an event.	
* *Task list* - a list that stores all the tasks added to the app.
* *Event list* - a list that stores all the events added to the app.
* *Calendar list* - a list that stores all the calendar items added to the app.


## Appendix E: Contact the initial developers
The table below shows the information and contact details of developers.

|Developer| Contact details | 
|--------|----------|
|Liu Jingming|E0424608@u.nus.edu|
|Liu Yifeng|E0425960@u.nus.edu|
|Lyu Jiawen|E0376928@u.nus.edu|
|Ng Hong Ming|E0426149@u.nus.edu|
|Zhang Yilin|E0377000@u.nus.edu|

## Appendix F: Instructions for manual testing

General steps for manual testing:
1. Execute an "Add" action.
2. Execute a "Delete" / "Find" / "Print" / "Set" / "View" action of the same command type.

Refer to [Command Summary](#command-summary) to view the list of actions, command types and command format. 

### Command summary
The following table contains the list of commands available in the application.

|Action| Command Type | Command Format | 
|--------|----------|----------|
|Add|activity event|`act <activity_description> @<venue> / <date> <time>`|
|Add|additional information for event|`/a <event_number> - <additional_information>`|
|Add|deadline task|`deadline <task_description>/ <due_date>`|
|Add| exam event | `exam<module_name> @<venue> / <date> <time>` | 
|Add| lab event  | `lab <module_name> @<venue> -r <recurring_number> / <date> <time>` | 
|Add| lecture event | `lect <module_name> @<venue> -r <recurring_number> / <date> <time>` | 
|Add| todo task | `todo <task_description>` | 
|Add| tutorial event | `tut <module_name> @<venue> -r <recurring_number> / <date> <time>` | 
|Delete| events | `-e <event_number>` |
|Delete|tasks|`-t <task_number>`|
|Delete|additional information for event|`/- <event_number> a <additional_information_number>`|
|Find|all items|`/f <keyword>`|
|Find|events|`/fe <keyword>`|
|Find|tasks|`/ft <keyword>`|
|Print |countdown for all items|`countdown`|
|Print |countdown for deadlines|`countdown deadlines`|
|Print |countdown for exams|`countdown exams`|
|Print |events|`print events`|
|Print |important tasks|`print *`|
|Print |progress|`print progress`|
|Print |tasks|`print tasks`|
|Print|timeline (default)|`print timeline`|
|Print|timeline before a date|`print timeline date <DATE>`|
|Print|timeline for current month|`print timeline month`|
|Print|timeline for current week|`print timeline week`|
|Set|tasks as done|`done <task number>`|
|Set|tasks as important|`*t <task_number>`|
|View|additional information|`/v <event_number>`|
|View|available commands|`help`|
|View|suggestions|`suggestion`|
|Quit|exit the program|`bye`|














