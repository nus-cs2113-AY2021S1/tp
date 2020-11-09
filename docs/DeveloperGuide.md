# Developer Guide

## Table of Contents
1. [Introduction](#introduction)<br>
&nbsp;&nbsp;1.1. [Background](#background)<br>
&nbsp;&nbsp;1.2. [Purpose](#purpose)<br>
&nbsp;&nbsp;1.3. [Scope](#scope)<br>
1. [Getting Started](#getting-started)<br>
&nbsp;&nbsp;2.1. [Prerequisites](#prerequisites)<br>
&nbsp;&nbsp;2.2. [Setting Up](#setting-up)<br>
&nbsp;&nbsp;2.3. [Running the Program](#running-the-program)<br>
1. [Design](#design)<br>
&nbsp;&nbsp;3.1. [Architecture](#architecture)<br>
&nbsp;&nbsp;3.2. [UI Component](#ui-component)<br>
&nbsp;&nbsp;3.3. [Logic Component](#logic-component)<br>
&nbsp;&nbsp;3.4. [Model Component](#model-component)<br>
&nbsp;&nbsp;3.5. [Storage Component](#storage-component)<br>
1. [Implementation](#implementation) <br>
&nbsp;&nbsp;4.1. [Project](#project)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.1.1. [Create Project](#create-project)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.1.2. [List Project](#list-project)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.1.3. [Select Project](#select-project)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.1.4. [View Project](#view-project)<br>
&nbsp;&nbsp;4.2. [Task](#task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.2.1. [Add Task](#add-task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.2.2. [View Task](#view-task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.2.3. [Delete Task](#delete-task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.2.4. [Change Task Priority](#change-task-priority)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.2.5. [Mark Task as Complete](#mark-task-as-complete)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.2.6. [View Task by Descending Priority](#view-task-by-descending-priority)<br>
&nbsp;&nbsp;4.3. [Sprint](#sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.3.1. [Create Sprint](#create-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.3.2. [View Sprint](#view-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.3.3. [Add Task to Sprint](#add-task-to-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.3.4. [Remove Task from Sprint](#remove-task-from-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.3.5. [Allocate Sprint Tasks to Members](#allocate-sprint-tasks-to-members)        <br>
&nbsp;&nbsp;4.4. [Storage](#storage)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.4.1. [Location](#location)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.4.2. [Loading Data](#loading-data)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.2.1. [Converting and Mapping of JSON to Objects](#converting-and-mapping-of-json-to-objects)<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.4.3. [Saving Data](#saving-data)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.3.1. [When the Program Exits](#when-the-program-exits)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.3.2. [Changes Made to the Data](#changes-made-to-the-data)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.3.3. [Serialising Objects to JSON](#serialising-objects-to-json)<br>
1. [Appendix: Requirements](#appendix-requirements)<br>
&nbsp;&nbsp;5.1. [Target User Profile](#target-user-profile)<br>
&nbsp;&nbsp;5.2. [Value Proposition](#value-proposition)<br>
&nbsp;&nbsp;5.3. [User Stories](#user-stories)<br>
&nbsp;&nbsp;5.4. [Non-Functional Requirements](#non-functional-requirements)<br>
&nbsp;&nbsp;5.5. [Glossary](#glossary)<br>
1. [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)<br>

## 1. <a id="introduction">Introduction</a>
### 1.1. <a id="background">Background</a>
SCRUMptious is a Java-based command line interface application for you to efficiently manage the development of a project. Leveraging the robust SCRUM/Agile framework, it allows you to delegate tasks to your team members and organize project requirements with ease. As a bonus, if you are a keyboard warrior, you can reach peak efficiency using SCRUMptious to manage your projects.

### 1.2. <a id="purpose">Purpose</a>
This guide illustrates the general architecture, and software design of SCRUMptious.

### 1.3. <a id="scope">Scope</a>
This guide is geared towards developers who wish to enhance or create their own version of SCRUMptious. As such, it contains important information regarding the software architecture and design considerations of SCRUMptious.

## 2. <a id="getting-started">Getting Started</a>
### 2.1. <a id="prerequisites">Prerequisites</a>
1. JDK 11.
1. IntelliJ IDEA.

### 2.2. <a id="setting-up">Setting Up</a>
1. Use a Git tool to fork this repository, or download the .zip file from GitHub and extract the contents into a new folder.
1. Right-Click on the folder and select “Open folder as Intellij IDEA Community Edition Project”.
1. Ensure JDK 11 is selected for Gradle, by navigating to Configure > Structure for New Projects > Project Settings > Project > Project SDK.

### 2.3. <a id="running-the-program">Running the Program</a>
This program can be run once it is compiled. If you have built its artifacts (.jar) file, you may run it using java -jar <filename.jar> on your command line.

## 3. <a id="design">Design</a>
This section seeks to explain the high-level design of the application. Given below is a quick overview of each component and the explanation of the design architecture in greater detail.
SCRUMptious is the main class of the application, and handles the initializing and execution of the appropriate classes.
### 3.1. <a id="architecture">Architecture</a>
![Figure 3.1: Architecture Diagram](./image/developerguide/architecturediagram.png "Architecture Diagram UML")  

_Figure 3.1: Architecture Diagram_  

The **Architecture Diagram** shown above describes the high level association operations of the application. 
A quick overview of the components is as follows:

`Main` is single-class component of `SCRUMptious`. It is responsible for:
1. At app launch: Initializes the components in the correct sequence(Storage, UI, Parser), and links them together where appropriate.
1. At program exit: Invokes Storage component to save all unsaved data.

The other packages are described below: 
1. `UI` : The user interface of the app, reads user input and is visible to the user.
1. `Parser Manager` : The module that reads user inputs, and creates a suitable parser based on the command to make 
 sense of user input. Respective parser then tells the Command module what to execute.
1. `ProjectManager` : Manages and stores all the projects added by the user, keeps track of selected project.    
1. `SprintManager` : Stores all sprints associated with a project.
1. `TaskManager` : Stores all tasks in backlog associated with a project.
1. `ProjectMembers` : Stores all team-members associated to a project. 
1. `Parser` : Creates a suitable parser, based on the command to make sense of the user input. Respective parser then
 make use the information and call respective commands. 
 
Each of the modules listed above are a collection of constituent classes, with each handling specialized tasks in-line with the SLAP principle.

### 3.2. <a id="ui-component">UI Component</a>
![Figure 3.2: Simplified class diagram for UI Component](./image/developerguide/UI.png "User Interface")  

_Figure 3.2: Simplified class diagram for UI Component_

The `UI` component contains the `Ui` and a few subclasses to print different types of messages.
The `Ui` consist of:  
&nbsp; &nbsp; &nbsp; &nbsp; 1. `printWelcomeScreen()`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 2. `getUserCommand()`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 3. `showToUser()`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 4. `showToUserLn()`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 5. `showError()`<br>

The subclasses are called directly by other functions. For example, if `TaskCommand` wants to show an
error message, it will call `Ui.showError(<error message>)` directly.

### 3.3. <a id="logic-component">Logic Component</a>
The `Logic` component contains the `ParserManager` and its subclasses, and the `Command` class and its subclasses, which mainly handles the commands input by the user. 
![Figure 3.3.1: Simplified class diagram for Logic Component](./image/developerguide/parserManagerClassDiagram.png)

_Figure 3.3.1: Simplified class diagram for Logic Component_

When a user types a command, `SCRUMptious` calls the `ParserManager`. The `ParserManager` then parses commands from the user. Subsequently, 
the `ParserManager` passes the commands on to the respective exceptions parsers which inherit from the `ExceptionsParser` interface. 
The exceptions parsers consist of:  
&nbsp; &nbsp; &nbsp; &nbsp; 1. `ProjectParser`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 2. `MemberParser`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 3. `TaskParser`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 4. `SprintParser`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 5. `HelpParser`<br>
&nbsp; &nbsp; &nbsp; &nbsp; 6. `StorageParser`<br>

1. The `ProjectParser` validates the parameters of the command. If the command is valid, it returns the respective `XYZProjectCommand` to the `ParserManager`.
If the command is invalid, the `ProjectParser` returns an appropriate warning message to the user.  

1. The `MemberParser` validates the parameters of the command. If the command is valid, it returns the respective `XYZMemberCommand` to the `ParserManager`.
If the command is invalid, the `TaskParser` returns an appropriate warning message to the user.  

1. The `TaskParser` validates the parameters of the command. If the command is valid, it returns the respective `XYZTaskCommand` to the `ParserManager`.
If the command is invalid, the `TaskParser` returns an appropriate warning message to the user.  

1. The `SprintParser` validates the parameters of the command. If the command is valid, it returns the respective `XYZSprintCommand` to the `ParserManager`.
If the command is invalid, the `SprintParser` returns an appropriate warning message to the user.  

1. The `HelpParser` validates the parameters of the command. If the command is valid, it returns the respective `XYZHelpCommand` to the `ParserManager`.
If the command is invalid, the `HelpParser` returns an appropriate warning message to the user.

1. The `StorageParser` validates the parameters of the command. If the command is valid, it returns the respective `ClearStorageCommand` to the `ParserManager`.
If the command is invalid, the `StorageParser` returns an appropriate warning message to the user.

![Figure 3.3.2: Simplified class diagram for Logic Component](./image/developerguide/commandClassDiagram.png)

_Figure 3.3.2: Simplified class diagram for Logic Component_

The subcommand classes `XYZHelpCommand`, `XYZProjectCommand`, `XYZMemberCommand`, `XYZTaskCommand`, `XYZSprintCommand`, `ClearStorageCommand` all inherit from an abstract `Command` class, 
which has an execute function and its respective constructors.

The `ParserManager` then returns the command back to `SCRUMptious`, which then executes the command.



### 3.4. <a id="model-component">Model Component</a>
![Figure 3.4: Simplified class diagram for Model Component](./image/developerguide/modelcomponent.png "Storage Component UML") 

_Figure 3.4: Simplified class diagram for Model Component_
 
[Model Package](https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/model)  

The Model package defines all the object classes that are used by SCRUMptious and this section will explain how these objects interact with other components and each other.

#### Initialisation</a>
* Upon starting the program, SCRUMptious will initialise one instance of `ProjectManager` which will be used to facilitate all the operation in the program.

#### Operation - Command Execution</a>
When a `Command` from the [Logic component](#logic-component) is executed, it will work on the same `ProjectManager` initialised previously and will branch down to the necessary packages as required.

##### Project Operations</a>
* `ProjectManager` facilitate the management of multiple `Project` instances.
    * Creation of `Project` adds an entry to `ProjectManager`.
    * Deletion of `Project` removes an entry from `ProjectManager`.
* `Project` contain necessary information about the project such as:
    * Project ID
    * Project Title
    * Project Description
    * Project Duration
    * Sprint Length
    * Project Start Date
    * Project End Date
* `Project` hold three additional object that are initialise upon its creation:
    * `ProjectMembers` to facilitate the management of `Members` that are working on the `Project`.
    * `SprintManager` to facilitate the management of `Sprint` iterations that belongs to the `Project`.
    * `TaskManager` to facilitate the management of `Tasks` that are broken down from the `Project`.
##### Members Operations</a>
* `ProjectMembers` facilitate the management of multiple `Members` instances and is dependent on the `Project` that initialises it.
    * Creation of `Member` adds an entry to `ProjectMembers`.
    * Deletion of `Member` removes an entry from `ProjectMembers`.
* `Member` contain the Member's user ID.

##### Task Operations</a>
* `TaskManager` facilitate the management of multiple `Task` instances and is dependent on the `Project` that initialises it.
    * Creation of `Task` adds an entry to `TaskManager`.
    * Deletion of `Task` removes an entry from `TaskManager`.
* `Task` contain necessary information about the task such as:
    * Task ID
    * Task Title
    * Task Description
    * Task Priority
    * Task Completion Status
* `Task` contain two additional ArrayList that are initialise upon its creation:
    * ArrayList of Member's user ID to keep track `Members` who are assigned to work on the task.
    * ArrayList of Sprint ID to keep track `Sprints` that the task are allocated to.

##### Sprint Operations</a>
* `SprintManager` will facilitate the management of multiple `Sprint` instances and is dependant on the `Project` that initialises it.
    * Creation of `Sprint` adds an entry to `SprintManager`.
* `Sprint` contain necessary information about the iteration such as:
    * Sprint ID
    * Sprint Goal
    * Sprint Start Date
    * Sprint End Date
* `Sprint` contain one additional ArrayList that are initialise upon its creation:
    * ArrayList of Task IDs to keep track `Tasks` that are allocated to the `Sprint`.
### 3.5. <a id="storage-component">Storage Component</a>
![Figure 3.5: Simplified class diagram for Storage Component, Model and json.simple](./image/developerguide/storagecomponent.png "Storage Component UML")

_Figure 3.5: Simplified class diagram for Storage Component, Model and json.simple_
  
API: [StorageManager.java]( https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/storage/StorageManager.java)  

The Storage component is using the JavaScript Object Notation (JSON) to save the data. The library used for serialising and deserializing the data is _json.simple 3.1.1_ by **Clifton Labs**.  
As shown in the diagram above, `JsonableObject` and `JsonableArray` are interfaces which inherits the `Jsonable` interface. The following model class inherits only one of the two interfaces:  
- ProjectManager  
- Project  
- SprintManager  
- TaskManager  
- ProjectMembers  
- Sprint  
- Task  
- Member  

This requires the model classes to implement two methods required for JSON serialisation and deserialisation:  
- `toJson()`: Contains logic required to convert the model object into JSON string.  
- `fromJson()`: Contains logic required to convert JSON object into its respective model class.    

## 4. <a id="implementation">Implementation</a>
### 4.1. <a id="project">Project</a>
![Figure 4.1: Project Class Diagram](./image/developerguide/ProjectClassDiagram.png
 "Project Class Diagram")
 
 _Figure 4.1: Project Class Diagram_
 
#### 4.1.1. <a id="create-project">Create Project</a>
![Figure 4.1.1: Sequence diagram of CreateProjectCommand](./image/developerguide/createProjectSequenceDiagram.png
 "Add Project Sequence Diagram") 
 
_Figure 4.1.1: Sequence diagram of CreateProjectCommand_
 
 Link: [CreateProjectCommand.java](https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/command/project/CreateProjectCommand.java) 
A project is created with a clear title and description of what the team is working on 
for delivery, as well as the project length and the sprint duration specified. `ProjectManager` stores all the projects
in a hash table with `projectID`, `project` as key,value pair.

Before execution:
1. Parse user input `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>` into Command

    SCRUMptious will receive user input using the `Ui` class and parse it into `CreateProjectCommand` with `Parser` and
     `ProjectParser`.
1. Execute CreateProjectCommand

    SCRUMptious calls `Command.execute()` which will execute the command as mentioned in the implementation.

Implementation:

1. Prepare parameters
    1. Extracts required fields, to be passed as parameters for project creation.
        
1. `projectManager.addProject()` adds a project using the parameters provided.
    
1. Output to User
    
    `printCreatedProject()` is then called to output the newly created Project in `addProj.toString` via `Ui
    .showToUserLn()`

#### 4.1.2. <a id="list-project">List Project</a>
All the projects added by the user are shown as an output.
Before execution:
1. Parse user input `project /list` into Command

    SCRUMptious will receive user input using the `Ui` class and parse it into `ListProjectCommand` with `Parser` and
     `ProjectParser`.
     
1. Execute ListProjectCommand

    SCRUMptious calls `Command.execute()` which will execute the command as mentioned in the implementation.

Implementation:

1. The program iterates through `projectManager.getProjectList()` to get a list of all the projects stored in
 `ProjectManager`.
    
1. Output to User
    
    `proj.getTitle()` and `proj.getDescription()` is then called to output all the projects in `ProjectManager` via
     `Ui.showToUserLn()`.

#### 4.1.3. <a id="select-project">Select Project</a>
Select the project on which all the commands are executed.

Before execution:
1. Parse user input `project /select <id>` into Command

    SCRUMptious will receive user input using the `Ui` class and parse it into `SelectProjectCommand` with `Parser` and
     `ProjectParser`.
     
1. Execute SelectProjectCommand

    SCRUMptious calls `Command.execute()` which will execute the command as mentioned in the implementation. The
     required project will then be chosen as reference and stored in `ProjectManager`.

Implementation:

1. The input `id` is used to access the corresponding project stored in a hashtable in the `projectManager`.
    
1. Output to User
    
    `parameters.get('0')` is used to get the selected id and generate output  via
     `Ui.showToUserLn()`.


#### 4.1.4. <a id="view-project">View Project</a>
View the details of the project on which the user is currently working on.
Before execution:
1. Parse user input `project /view` into Command

    SCRUMptious will receive user input using the `Ui` class and parse it into `ViewProjectCommand` with `Parser` and
     `ProjectParser`.
     
1. Execute ViewProjectCommand

    SCRUMptious calls `Command.execute()` which will execute the command as mentioned in the implementation.

Implementation:

1. The selected project is accessed by using `projectManager.getSelectedProject()`.
    
1. Output to User
    
    The project is shown to the user by `proj.toString()` via `Ui.showToUserLn()`

### 4.2. <a id="task">Task</a>
#### 4.2.1. <a id="add-task">Add Task</a>
A task is created following the creation of a project, with a clear title, description 
and priority of the task. `TaskManager` stores all the tasks in an array list.

Prerequisites:
1. There must be at least one project.

Before execution:

1. Parse user input `task /add -title <title> -desc <description> -priority <priority>` into Command

    SCRUMptious will receive user input using the `Ui` class and parse it into CreateTaskCommand using `Parser` and `TaskParser`.

1. Check whether a project exists. If there are no projects, an error is displayed and no tasks are created.   
    
1. Execute AddTaskCommand

    SCRUMptious calls `Command.execute()` which will execute the command.    

Implementation:

1. Prepare parameters
    1. Extracts required fields, to be passed as parameters for task creation.
    1. Checks the title for duplicates. If duplicate found, an error is displayed and the task is not created.
    
1. `taskManager.addTask()` adds a task using the provided parameters.

1. User output
    The overridden function `toString()` is called to output the new Task using Ui.showToUserLn().
    
#### 4.2.2. <a id="view-task">View Task</a>
![Figure 4.2.2: Sequence diagram of ViewTaskCommand](./image/developerguide/viewTask.png
 "Add Project Sequence Diagram") 
 
_Figure 4.2.2: Sequence diagram of ViewTaskCommand_
 
 The user specifies one or more task IDs to view the corresponding tasks. 
 
 Prerequisites:
 1. There must be at least one project.
 
 Before execution:
 
 1. Parse user input `task /view <taskid> [<taskid>...]` into Command
 
     SCRUMptious will receive user input using the `Ui` class and parse it into ViewTaskCommand using `Parser` and `TaskParser`.
 
 1. Check whether a project exists. If there are no projects, an error is displayed.   
     
 1. Execute ViewTaskCommand
 
     SCRUMptious calls `Command.execute()` which will execute the command.    

Implementation:

1. Prepare parameters
    1. Extracts the task IDs, to be passed as integers for task viewing.
    1. Checks IDs for invalid IDs. Any entry of invalid IDs will show corresponding errors.
    
1. Obtain task list
    1. The task list is obtained from the project.

1. User output
    The overridden function `toString()` is called to output the requested Tasks in the task list using Ui.showToUserLn().
    
#### 4.2.3. <a id="delete-task">Delete Task</a>

Users may choose to delete tasks that are deemed unnecessary or incorrect.
The task IDs are provided.

Prerequisites:
1. There must be at least one project.
1. There must be at least one task for the command to delete.

Implementation:

1. UI receives user input
1. Parser parse user input
1. Execute DeleteTaskCommand
    1. Check all IDs are valid. Invalid IDs will display corresponding errors.
    1. On TaskList
        1. Delete the task from the task list
    1. On sprints
        1. De-link the task from all sprints.
    1. UI output to user
          
    
#### 4.2.4. <a id="change-task-priority">Change Task Priority</a>

A user can change the priority of an existing task after changes to project requirements.
The task ID and new priority are provided.

Prerequisites:
1. There must be at least one project.
1. There must be at least one task for the command to edit its priority.

Implementation:

1. UI receives user input
1. Parser parse user input
1. Execute ChangeTaskPriorityCommand
    1. Check the new priority is valid. If not valid, an error will be displayed.
    1. Check all IDs are valid. Invalid IDs will display corresponding errors.
    1. The corresponding task will be updated its priority
    1. UI output to user

#### 4.2.5. <a id="mark-task-as-complete">Mark Task as Complete</a>  
The user can mark tasks as complete when the team completes the task. The task
IDs are provided.

Prerequisites:
1. There must be at least one project.
1. There must be at least one task for the command to be marked complete.

Implementation:

1. UI receives user input
1. Parser parse user input
1. Execute DoneTaskCommand
    1. Check all IDs are valid. Invalid IDs will display corresponding errors.
    1. The corresponding tasks will be marked as complete.
    1. UI output to user

#### 4.2.6. <a id="view-task-by-descending-priority">View Task by Descending Priority</a>
A user may choose to view all tasks in order of priority associated with the project. No parameters are supplied.

Prerequisites:
1. There must be at least one project.

Implementation:

1. UI receives user input
1. Parser parse user input
1. Execute PriorityViewCommand
    1. UI output to user
    
### 4.3. <a id="sprint">Sprint</a>
In SCRUMptious, a Project will be broken down into smaller iterations known as Sprints. The Sprint will contain information about the Tasks allocated for that iteration and Members that are assigned to complete the Tasks.

The following section will explain how the management of Sprints is implemented in the program.

#### 4.3.1. <a id="create-sprint">Create Sprint</a>

![Figure 4.3.1: Sequence diagram of CreateSprintCommand](./image/developerguide/createSprint.png "Create Sprint Sequence Diagram")  
  
_Figure 4.3.1: Sequence diagram of CreateSprintCommand_ 
  
Link: [CreateSprintCommand.java](https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/command/sprint/CreateSprintCommand.java) 

A Sprint can be created when there is an existing Project.
When the Project is created, the duration of the Project and length of the Sprints are specified, thus, there will be a finite number of Sprints for each Project.


Usage scenario:
1. Parse user input into Command

    SCRUMptious will receive user input using the `Ui` class and parse it into `CreateSprintCommand` with `Parser` and `SprintParser`.
1. Execute CreateSprintCommand

    SCRUMptious calls `Command.execute()` which will execute the command as mentioned in the implementation.

1. Choose the Project to add the new Sprint

    `chooseProject()` will be called to check for the optional `-project` tag in the user specified parameters.
    
    Note: If the tag is not specified, the default Project in the ProjectManager indexed by `selectedProject` will be chosen.
1. Prepare parameters
    
    `prepareParameters()` will be called to check for the mandatory `-goal` tag in the user specified parameters.
    In addition, It will also prepare the optional `-start` tag as required in the following two scenarios:
    1. New Sprint is first Sprint in Project
        
        Being the first Sprint in the Project, the `-start` tag will determine the start date for both the new Sprint and Project.
        Thus, the `String` parameter will be sent to _DateTimeParser_ via `parseDate()` to parse it into a _LocalDate_ object.
        
        The end date for the Project and Sprint will also be determined by adding `projectDuration` and `sprintLength` to the start date respectively.
        
    1. New Sprint is not first Sprint in Project
    
        As there is a previous Sprint before the newly created Sprint, the new Sprint will start the day after the previous 
        Sprint ends. Thus, the `-start` tag will be ignored even if specified by user.

1. Check all sprint created

    With all the necessary parameters prepared, the command will check if there is still room to add new Sprint by checking
    if the prepared `sprintEndDate` is after the `projectEndDate`.
    
    Note: This check is done after the `prepareParameters()` as the _LocalDate_ `sprintEndDate` is required.
    
1. Update Project Start and End date if new Sprint is first Sprint

    As mentioned above, if the new Sprint is the first Sprint in the Project, the `-start` tag will determine the start and end date of the Project.
1. Add Sprint to Sprint Manger
    
    `addSprint()` is finally called to add a new Sprint to the Sprint Manager.
   
1. Output to User
    
    `printCreatedSprint()` is then called to output the newly created Sprint in `createdSprint.toString()` via `Ui
    .showToUserLn()`

#### 4.3.2. <a id="view-sprint">View Sprint</a>

Link: [ViewSprintCommand.java](https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/command/sprint/ViewSprintCommand.java) 

A Sprint can only be viewed when there is an existing Sprint. When the user request to view the sprint, the Sprint number is specified and the program will output the information about the Sprint corresponding to the Sprint number.

Prerequisites:    
1. At least one Sprint in the SprintList

Implementation:
1. UI receive user input
1. Parser parse user input
1. Execute ViewSprintCommand
    1. Get Sprint from SprintList
    1. UI output to user

#### 4.3.3. <a id="add-task-to-sprint">Add Task to Sprint</a>

Link: [AddSprintTaskCommand.java](https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/command/sprint/AddSprintTaskCommand.java) 

Users can add Tasks existing in the Project Backlog to the Sprint, indicating that the Tasks are to be worked on during the iteration. 

Prerequisites:    
1. At least one Sprint in the SprintList
1. At least one Task in the ProjectBacklog

Implementation:
1. UI receive user input
1. Parser parse user input
1. Execute AddSprintTaskCommand
    1. On Sprint 
        1. Add Task ID into sprintTaskIds
    1. On Task
        1. Add Sprint Number into sprintAllocatedTo
    1. UI output to user


#### 4.3.4. <a id="remove-task-from-sprint">Remove Task from Sprint</a>

Link: [RemoveSprintTaskCommand.java](https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/command/sprint/RemoveSprintTaskCommand.java) 

Users can remove Tasks from Sprint, indicating that the Tasks are deemed to not be worked on during the iteration. 

Prerequisites:    
1. At least one Sprint in the SprintList
1. At least one Task is added to the selected Sprint

Implementation:
1. UI receive user input
1. Parser parse user input
1. Execute RemoveSprintTaskCommand
    1. On Sprint 
        1. Remove Task ID from sprintTaskIds
    1. On Task
        1. Remove Sprint Number from sprintAllocatedTo
    1. UI output to user

#### 4.3.5. <a id="allocate-sprint-tasks-to-members">Allocate Sprint Tasks to Members</a>  

Link: [AllocateSprintTaskCommand.java](https://github.com/AY2021S1-CS2113T-F11-4/tp/tree/master/src/main/java/com/scrumptious/command/sprint/AllocateSprintTaskCommand.java) 
 
Users can allocate Sprint Tasks to Members, indicating that the Tasks are assigned to the selected member to work on during the iteration. 

Prerequisites:    
1. At least one Task is added to the selected Sprint
1. At least one Member is added to the Project

Implementation:
1. UI receive user input
1. Parser parse user input
1. Execute AllocateSprintTaskCommand
    1. On Task
        1. Add Member ID into membersAllocatedTo
    1. On Member
        1. Add Task ID into allocatedTaskIds


### 4.4. <a id="storage">Storage</a>
To make the data persistent and portable, JSON has been chosen as the format for data to be saved to a persistent storage such as storage drives, thumb drives and any other storage medium which stores the program. JSON is also **human-readable** which allows users to directly modify the data file easily. 
This can be useful in certain scenarios such as fixing the data file in the event of data corruption.

#### 4.4.1. <a id="location">Location</a>
![Figure 4.4.1: Running the Jar](image/developerguide/storage_save_directory.png "Running the Jar")  

_Figure 4.4.1: Running the Jar or in IDE_

As shown in the above diagram, the program will save the data as _"data.json"_. The data file is saved in the _“data/”_ folder that is located in the folder of the program. If you are testing the program using Intellij IDE, the _“data/”_ folder will be in the root of the project folder.  
When you start the program, the program will load the data file from its respective location and deserialise it into its respective objects. Data will be saved when the program exits or whenever the user makes changes to the data.  

#### 4.4.2. <a id="loading-data">Loading Data</a>
![Figure 4.4.2: Loading Data](image/developerguide/storage_load.png "Loading Data")  

_Figure 4.4.2: Loading Data_  

The program will only load the data file in the persistent storage during the initialisation process of the program. With reference to the sequence diagram above, the flow of the logic is as follows:  
1. When the user starts the program, it will first call `init()` to initialise the program. 
2. A `StorageManager` object will be instantiated with the reference to a `ProjectManager` object that is used during load and save operations.
3. `load()` will read the data file from the persistent storage, deserialise it into a `JsonObject` object and attempt to convert the object into its respective types.  
  
**Failure to Load**  
If the program fails to load the data file, it will proceed in an empty state. Any subsequent saves invoked by any command that changes the empty state, or exiting the program using `bye` will cause the erroneous data file (if any) **to be deleted**.  

The program will fail to load the data file if **any of the following conditions are met**:
* I/O error trying to read the file.
* Error parsing due to incorrect JSON format.
* Conversion error due to missing properties.
* Mapping error due to invalid property type (e.g. "name" is expecting a `String` but data read is an `Integer`).

#### 4.4.2.1. <a id="converting-and-mapping-of-json-to-objects">Converting and Mapping of JSON to Objects</a>  
![Figure 4.4.2.1: Parsing Sequence](image/developerguide/storage_load_parse.png "Parsing Sequence")

_Figure 4.4.2.1: Parsing Sequence_ 
 
Due to the limitations of the library, parsing of the JSON string only converts it into either JsonObject or JsonArray objects which requires additional operations to map the data back to the respective model classes.  
  
As explained in [Storage Component](#storage-component), each model class except for `Priority` will inherit either `JsonableObject` or `JsonableArray` which are custom interfaces inheriting the `Jsonable` interface of _**json.simple**_. This requires the classes to implement the methods `toJson()` and `fromJson()`. This section will focus on `fromJson()`, which is used to implement the logic for **converting and mapping of JSON to objects of their respective type**.  
  
1. After loading the raw JSON string, `StorageManager` will call `Jsoner.deserialize()` to convert it into an object of `JsonObject`.
2. At this point, `JsonObject` contains all the properties of `ProjectManager` and needs to be mapped back to a `ProjectManager` object.
3. `StorageManager` will call `ProjectManager.fromJson()`, passing the `JSONObject` object from _Step 2_ as the parameter. 
4. In `fromJson()`, there are two type of actions depending on the property it is mapping:  
   1. **Type Casting**: Properties that are primitive or standard can be mapped directly through type casting (e.g. `int`, `boolean`, `String` etc.)
   2. **\*Calling `fromJson()` of the Actual Type**: Any property that is originally a type of the **Scrumptious model classes** will be mapped with the following steps:
      1. Create an empty object of the respective type (e.g. `Sprint`).
      2. Type cast the property as `JsonObject` or `JsonArray` depending on the actual type of the property (e.g. `(JsonArray) object`).
      3. Call `fromJson()` of the newly created object, passing the property as the parameter (e.g. `Sprint.fromJson()`).
      4. New object's `fromJson()` will **repeat the same process again under Step 4** for its own properties.  
      
\*`Priority` is an **enum** and is the only model which does not follow this strictly. It is mapped by type casting
 the property as `String` first, then calling the `Priority.valueOf()` method to convert it into its respective **enum**.  

#### 4.4.3. <a id="saving-data">Saving Data</a>  

Data will be saved under two scenarios: 
1. When the program exits. 
1. Changes made to the data. 

##### 4.4.3.1. <a id="when-the-program-exits">When the Program Exits</a>
![Figure 4.4.3.1: Saving Data When Program Exits](image/developerguide/storage_save_1.png "Saving Data When Program Exits")  

_Figure 4.4.3.1: Saving Data When Program Exits_  

`Scrumptious` will call `destroy()` which calls `save()` before it returns.

##### 4.4.3.2. <a id="changes-made-to-the-data">Changes Made to the Data</a>
Changes made to the data during the runtime of the program can only be made by executing a command.  

![Figure 4.4.3.2: Saving Data When Changes Made](image/developerguide/storage_save_2.png "Saving Data When Changes Made")  

_Figure 4.4.3.2: Saving Data When Changes Made_  
   
As shown in the diagram above, each command class inherits the `shouldSave` property from `Command` class. `shouldSave` is a boolean variable and is initialised inside the constructor. `shouldSave` will be set to `true` if the command results in a change of data (e.g. adding a task, creating a sprint etc.), otherwise it is set to `false` (e.g. viewing projects, sprints etc.).

After executing the command by calling `execute()`, the program will call `save()` from `StorageManager` object if the `shouldSave` property is set to `true`.

##### 4.4.3.3. <a id="serialising-objects-to-json">Serialising Objects to JSON</a>
![Figure 4.4.3.3: Serialising Sequence](image/developerguide/storage_save_serialise.png "Serialising Sequence")  

_Figure 4.4.3.3: Serialising Sequence_  

As explained in [Storage Component](#storage-component), each model class except for `Priority` will inherit either `JsonableObject` or `JsonableArray` which are custom interfaces inheriting the `Jsonable` interface of _**json.simple**_. This requires the classes to implement the methods `toJson()` and `fromJson()`. This section will focus on `toJson()`, which is used to implement the logic for **serialising objects into JSON string**.  
When saving the data as JSON file, `StorageManager` will call `Jsoner.serialize()` of the _**json.simple**_, passing in the `ProjectManager` and `FileWriter` (points to the data file) object as the parameters. The library will automatically serialise the objects and sub-objects into JSON string depending on the type of the objects:
 1. **Primitive and Standard Types (e.g. `int`, `String`, `Collection`)**: The library can directly serialise these types into JSON string.
 2. **\*Scrumptious Model Types (e.g. `Project`, `Task`)**: The library will serialise these types by calling its `toJson()` method which contains the logic for the serialisation.  
    
\*`Priority` is an exception, it is serialised by calling `name()` of the **enum** which will return its `String
` representation.
    
## 5. <a id="appendix-requirements">Appendix: Requirements</a>

### 5.1. <a id="target-user-profile">Target User Profile</a>

**_SCRUMptious_** is a command-line software targeted to project managers, team leaders and enterprises using the
SCRUM methodology (agile) for product and project development. It is primarily catered to users who wish to
develop a product in team using an organized framework. 

### 5.2. <a id="value-proposition">Value Proposition</a>

**_SCRUMptious_** solves the problem faced by teams during development. 
It helps teams to keep up with rapid change and innovations that come in the course of development,
mainly due to change in requirements by the customer. It is suitable for teams with clear focus, and limits
distractions whilst promoting teamwork and collaboration.

## 5.3. <a id="user-stories">User Stories</a>

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Project Manager|Specify the goal for new sprint|Be clear about the main objective to be achieved.|
|v1.0|Project Manager|Want to pre plan future sprints |Have a clear structure regarding project progress.|
|v1.0|New user|Have access to a help menu|Learn to use the program easily|
|v1.0|Project Manager|Want to add and remove tasks from future sprints|Pre-plan the development phase|
|v1.0|Project Manager|Be able to manage many projects simultaneously|Work on different projects at the same time|
|v1.0|Student/User|Be able to create a new project and give it a name|Identify the project I am working on|
|v2.0|User/Manager|Clear data stored from previous sessions.|Start fresh when previous projects have finished|
|v2.0|User/Team Lead|Be able to add and delete members associated with a project|Effectively keep track of everyone working on a project|
|v2.0|Team Lead|Be able to assign tasks to team members|Keep track of who is working on which feature|
|v2.0|Team member|Add tasks with specific priority|Understand what features/tasks are of greater urgency|
|v2.0|Team member|Change the priority of added task|Update the task urgency depending on the feedback achieved after every sprint|


## 5.4. <a id="non-functional-requirements">Non-Functional Requirements</a>

* Should work on any _OS_ as long as it has Java 11 installed.
* The average user is someone who manages teams in development phases, and takes responsibility of 
delivering the product (eg: Team leader in University projects, Project Manager in enterprises etc).
* The user prefers typing instructions and commands rather than using a GUI for management purposes.

## 5.5. <a id="glossary">Glossary</a>
The terms listed in this glossary are in alphabetical order.
* _GUI_ - Graphical User Interface
* I/O - Input/Output is the communication between the computer and the user through GUI or terminal.

## 6. <a id="appendix-instructions-for-manual-testing">Appendix: Instructions for manual testing</a>

1. Download the jar file and copy it into an empty folder.
1. Open a new terminal window and navigate to the same directory where the SCRUMptious.jar is located.
1. Enter the command `java -jar SCRUMptious.jar` into the terminal window to launch the application. The application
 should now be running.
