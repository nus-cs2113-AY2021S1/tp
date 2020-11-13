# Developer Guide

## Welcome to Ez Manager
![EZ Manager Welcome](https://i.ibb.co/n7zphMR/ezmanagerterminal.png)

## **Changelog**
![EZ Manager Changelog](https://i.ibb.co/NNcdxnh/changelog.png)

---

## **Table of Contents**
- [Introduction — Welcome to EZ Manager](#introduction---welcome-to-ez-manager)
- [Setting Up](#setting-up)
- [Design](#design)
- [Implementation](#implementation)
- [Testing](#running-tests)
- Dev Ops
  - [Making A Release](#devops)
- Appendices
  - [Appendix A: Product Scope](#Appendix-A-Product-Scope-Samuel-Leow)
  - [Appendix B: Commands Summary](#Appendix-B-Command-Summary-Samuel-Paul-Christopher)
  - [Appendix C: Instructions for Manual Testing](#Appendix-C-Instructions-for-Manual-Testing-shreyas-kumar)

---

## **Introduction - Welcome to Ez Manager!**
The Ez Manager command line application is designed to help you get more done as a Software Engineering Project Manager. Organize your projects, keep track of team members and analyze the tasks pertaining to your projects.

This guide is designed to help you understand the inner workings of Ez Manager from the first steps of setting up the project to the high-level application structures and even the sequence of operations when a command is triggered.

Let us hit the ground running with the next section about setting up!

---

## **Setting Up** 

### **Prerequisites**
1. Java Development Kit (JDK) version 11 and above. [Here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) is an installation guide for JDK 11.
2. The Intellij Intergrated Development Environment (IDE). [Here](https://www.jetbrains.com/help/idea/installation-guide.html) is an installation guidefor Intellij.

### **Setting up the project on your computer**
1. Fork this [repo](https://github.com/AY2021S1-CS2113T-T09-1/tp), and clone the fork to your computer
2. Open IntelliJ (if you are not in the welcome screen, click `File > Close Project` to close the existing project dialog first)
3. Set up the correct JDK version for Gradle
    * Go to `File > Project Structure`, then select Project and ensure that the Project SDK is Java 11.
    * Else, click `New...` and find the directory of the JDK
4. Click `Import Project`
5. Locate the `build.gradle` file and select it. Click `OK`
6. Click `Open as Project`
7. Click `OK` to accept the default settings
8. After the importing is complete, verify that the project is working, locate the `src/main/java/seedu/duke/Duke.javafile`, right-click it, and choose `Run Duke.main()`. 

If the setup is correct, you should see the welcome screen and greeting.

PDF developer guide available [here](https://we.tl/t-UkcnzA4i8P) (NEED TO UPDATE THIS)

---

## **Design (Sean Tan)**
Developers are welcome to contribute by submitting issues or pull requests on our repository. The design section is a good place to start learning about Ez Manager’s architecture and various components. Most developers will contribute to the app mainly through addition of new user commands. The section “Addition of new commands” will provide a step-by-step walkthrough to ensure new commands follow the overall architecture. Figure 2 below shows the overall class diagram for EZ Manager.  

<center><img src = "https://i.ibb.co/92p7mtp/Figure2.png"></center>

### **Consideration (Sean Tan)**
Eazy was developed via a breadth first iterative approach with new commands progressively added. An n-tier architecture ensured separation of concern between various layers of the architecture but much of the program’s logic remained in the Command classes. This design architecture ensured minimal changes to the codebase when new commands were added. Often, new commands or feature addition required changes to only a single data class and addition of a new independent command class. 

### **Overall Architecture (Sean Tan)**
Ez Manager consists of 4 main layers:  
* Ui: Handles the output of the app 
* Logic: Command parser and executor 
* Model: Data classes 
* Storage: Handles the saving and loading of data to the hard disk 

The Ui and Storage layer contains a single class. The model layer contained a member, a task and a project class and the logic layer contained a single parser class and various command classes. Functionality was exposed directly through these command classes, eliminating the need for redundant classes with few methods. In a small app like Ez Manager, this enhanced code maintainability and readability. 
 
Now, we will delve into more detail about each component. 

### **UI Component (Riaz Ahamed)**
API: Ui.java 
Introduction: 
The Ui.java is a class made up of various acknowledgement messages to be displayed after the user inputs a command. 

Abstraction: 
Every message in the class is stored as a String and it is abstracted to a method. This method is called after the execution of a command.  

Access: 
Every method in the Ui class is a static method. Hence, every command class calls the appropriate static methods directly from the Ui class. 

### **Logic Component (Sean Tan)**
As user commands follow a fixed format, a generic parser can extract command types and parameters for all commands. This is handled by the parser class. The parser then passes the extracted values to specific command classes. Below are the workings of the Parser and Commands. 

**Parser**

User inputs are passed to the static parse method of the parser class. The parser then calls instances of command classes for command execution. The following are handled by the parser. 
* Command type is extracted and used to determine which command class is called for command execution.  
* Command parameters are extracted using a regex and stored in a hashmap. This hashmap is passed to the command class.  
    * Extracting parameters using a regex allows for parameters to be specified in any sequence.
    * The regex splits the parameters into groups of parameter TYPE/VALUE.
    * To recognise the boundaries of each group, and to prevent capturing of the next group, it includes a positive look-ahead of the next group.
* A projectIndex pointer keeps track of the view the user is currently in.  
    * If the user is in a project view, this allows the parser to know which project the task to be manipulated is in.  
    * If the user is in the home view, this allows the parser to know that projects are to be manipulated instead. 
    * This pointer is passed to the command class. 
 
**Commands** 

Each command class represents a specific user command. The bulk of the program’s logic resides here. It manipulates data objects (model layer) and tasks the Ui to display output. The following is a more specific description of command classes. 
* The constructor extracts individual command parameters from the HashMap given to it from the parser. 
* From main(), executeCommand method is called which passes these parameters to methods in the model layer, which manipulates data objects. 
* ExecuteCommand also calls methods in the Ui to display output messages to the user. 

### **Model Component (Samuel Leow)**

The model component is to provide you with a conceptual understanding of how the data is stored in Ez Manager. Ez Manager has three classes which store data, the Project class, the Task class and the Team Member class. This section will explain how each of these classes interact with one another. The diagram below shows the attributes associated with each of the objects. 

<center><img src="https://i.ibb.co/QYVtfRv/model-Componentdiagram.png"></center>

Whenever a project, task or team member is created, an instance of Project, Task and TeamMember is also created. These instances are stored in array lists of projects, tasks and teamMembers respectively when the Parser class processes the user input. 

### **Storage Component (Samuel Paul Christopher)**

This is section will explain how Ez Manager ensures that the data in the application persists after each session has been terminated (with the bye command). Figure 3 outlines the responsibility of the Storage component for loading and storing the data during the lifecycle of the application. 

<center><img src="https://i.ibb.co/5c8vH3T/Figure3.png"></center>

More specifically, Figure 4 shows us how the text file would look like when it is populated. In the load method of Storage that is called at the beginning of each application, the first thing that is recalled are the team members that are part of the overall organization that is being managed by Ez Manager.  

<center><img src="https://i.ibb.co/qnHLWR0/Figure4.png" alt="Figure4" border="0"></center>

Once the team members are in, the load method registers the projects. Each project is stored in a block that starts with “Project ProjectNameHere” so in Figure 4, the project is project “how are you”. Data pertaining to the project, which includes, the project’s status (whether it is done or not), the project description all the way to the tasks and members associated with the project can be extracted from here and loaded into the current instance of the application. That completes the loading process. 
 
Once the loading process is over, the application proceeds to its normal operation where it receives input from you and responds accordingly. The next important part is when you input the exit command “bye” once this is triggered, before the application terminates, we call the save method. 
 
The save method from the Storage component clears the ezmanager.txt file and then populates it with the current data using the saveFormat function (available in the Project, Task and TeamMember) to ensure that the formatting is consistent with EZ Manager’s saving convention. Once this step is done, you can be assured that your data has been saved.  

---

## **Implementation**

## **Views**

### **Home View (Samuel Leow)**
The home view mechanism is facilitated by Ui.homeViewMessage() method. This feature will be executed automatically whenever EZ Manager is booted up, or when “list” command is called. Below is an example of the display of Home-View

<center><img src="https://i.ibb.co/L6GvCdg/Figure5-1.png"></center>

Given below is the scenario of how the mechanism behaves at each step when the program is booted up and when “list” command is called. 

Step 1: The user launches Ez Manager for the first time and the Home-View display will be shown. The list of projects, the list of members and other details will be loaded from the hard disk. The state of the program will be set to Home-View by default. 

<center><img src="https://i.ibb.co/84KCTMB/Figure5-2.png"></center>

Step 2: The user executes project n/ Developer Guide command to add a new project, followed by deadline p/3 d/2020-10-23 (assuming there is a total of 3 projects) to add a new deadline to the new project.  

Step 3: The user now decides to display the Home View to have an overview of all his projects. The display command will call HomeViewCommand(), which will execute the command with executeCommand(), which takes in projects ArrayList and teamMembers ArrayList and storage. 

**Note:** This step applies for the addition of the other features such as Deadline, Members, Description and Priority. Whenever a new information is added, and when the HomeViewCommand() is called, it will generate the display according to the information that is available in the program. If a particular information is empty, a dash symbol will be used instead. 

The following sequence diagrams shows how the “Home View” command works in both scenarios: 

Scenario 1 (Ez Manager start up): 
<center><img src="https://i.ibb.co/pJPjSxX/Figure5-3.png"></center>

Scenario 2 (display command): 
<center><img src="https://i.ibb.co/0VMf92d/Home-View-SD2.png"></center>

### **Project View (Samuel Paul Christopher)**
The project view mechanism is facilitated by Ui.projectViewMessage() method, which takes in an argument of type Project. The project view is displayed when a user uses the “select” command to select a particular project to get more details on the project. Figure 5.1 is an example of the output of Project View. Note, if the fields are blank, a dash symbol would be used to indicate that there is no data about that field. 

<center><img src="https://i.ibb.co/gg3y8xw/Figure6-1.png" ></center>

For the command to work, the user needs to have previously selected a project. 

Given below is an example usage scenario and how the Project View mechanism behaves at each step. 

Step 1: As shown in figure 6.2, the user launches EZ Manager for the first time. The user will be in the home view by default which has been initialized with the previously entered projects and team members data.

<center><img src="https://i.ibb.co/3fYN3cS/Figure6-2.png"></center>

Step 2: As shown in Figure 6.3, the user switches to Project View by selecting an existing Project using select p/index. If no such project exists, the user must create the project using project n/name before selecting. This sets the projectIndex variable to be the index of the project in the Projects List.   

<center><img src="https://i.ibb.co/xFQgxTs/Figure6-3.png"></center>

Once this step is done, the Project View will be displayed as in Figure 6.1.  

The Figure 6.4 sequence diagram shows how the Project-View is displayed through the project select command. The ellipses have been used for brevity to show that other different data about the project and task will be obtained from getters. For example, in the image below only shows that the status of the task is involved but other aspects of the tasks will also be needed like the task description and the team members who are involved in the task. 

<center><img src="https://i.ibb.co/zhCFK65/Project-View-SD.png"></center>

### **Home Command (Riaz Ahamed)**
This section will explain the usages of the home command and how it affects the states of the app. 

The home command is facilitated by the HomeCommand class and it allows the user to switch from one states of the program to the other but not vice versa. The 2 states of the program are as follows: 

* Home-View 
* Project-View 

The home command is executed when the executeCommand method is called in the main Duke class. The command then switches the state of the program from the Project-View to the Home-View but not the other way around. 

Step 1: The user launches the application. The application initializes with the Home-View state on startup. 

<center><img src="https://i.ibb.co/cvB2hNX/Figure7-1.png"></center>

Step 2: The user creates a new project with the project command.  

Step 3: The user selects the project with the select command. At this instance, the app state switches from Home-View to Project View. 

<center><img src="https://i.ibb.co/ggFvm9n/Figure7-2.png"></center>

Step 4: The user switches back to the Home-View with the home command. 

<center><img src="https://i.ibb.co/9rh7ft1/Figure7-3.png"></center>

---

## **Project Features**
### **Adding a new project (Riaz Ahamed)**
This section will explain the creation of new projects and how the objects interact with each other. 
The user can create new projects to be added to the list of projects with the project command. 
This command is facilitated with the help of the ProjectCommand class. An instance of the ProjectCommand class will have the following properties: 
* tasks: An array list of tasks 
* members: An array list of members 
* projectName: The name of the project as a String object 
*projectDescription: The description of the project as a String object 
projectDeadline: The deadline of the project as a Local Date object.

Step 1: The user types the project command followed by the name of the project e.g. `project n/Project One`. The main Duke class will call the Parser class. 

Step 2: The Parser class will check which state the app is in and will 
then call the appropriate class constructor. In this case, the Parser will call the ProjectCommand constructor. The ProjectCommand constructor will also check for the validity of the user’s input. 

Step 3: The Parser class will then initialize a new instance of the ProjectCommand constructor with the project name “Project One” and return it back to the main Duke class. 

Step 4: The Duke class will call the executeCommand function from the returned instance which will execute the command. 
* The executeCommand function will create a new Project instance 
* It will then add that instance to the static projects list.  
*  Lastly, it will call the printProjectCreated method from the Ui class and return it to the main Duke class. 

Step 5: The Duke class will then receive the acknowledgement message and display it to the user in the terminal. 
The above is illustrated below in a sequence diagram. The sequence diagram will only encompass the sequence in the executeCommand function. 

The above is illustrated below in a sequence diagram. The sequence diagram will only encompass the sequence in the executeCommand function. 

<center><img src="https://i.ibb.co/xG48W0g/Figure8.png" ></center>

### **Deleting a project (Samuel Leow)**
This section will explain the deletion of a specified project and how the objects interact with each other. 

The user can delete a specified project with the delete command. 

This command is facilitated with the help of the ProjectDeleteCommand class. An instance of the ProjectDeleteCommand class will have the following properties: 

projectIndex: The index of the project as an Integer 

Step 1: The user executes project n/Project One command to create a new project, and the current state of the app is Home View. 

<center><img src="https://i.ibb.co/HC7yZhc/Figure9-1.png"></center>

Step 2: The user now decides that adding the project was a mistake and decides to delete that project by executing the `delete p/1` command. The delete command will call ProjectDeleteCommand() which will execute the command with executeCommand(). printProjectDeleteMessage() from the UI class will be returned and printed. 

The following sequence diagram shows how the project delete command works: 

<center><img src="https://i.ibb.co/3CFDS75/Figure9-2.png"></center>

### **Adding a deadline to a project (Samuel Leow)**
This section will explain the addition of a deadline to a specified project and how the objects interact with each other. 

The user can add a deadline to a specified project with the deadline command. 

This command is facilitated with the help of the ProjectDeadlineCommand class. An instance of the ProjectDeadlineCommand class will have the following properties: 

* projectDeadline: The deadline of the project entered by the user stored as a LocalDate 

* projectIndex: The index of the project as an Integer 

Step 1: The user executes `project n/Project One` command to create a new project, and the current state of the app is Home View. 

<center><img src="https://i.ibb.co/br6DKf4/Figure10-1.png"></center>

Step 2: The user types the deadline command followed by the index of the project then the deadline of the project e.g. `deadline p/1 d/2020-12-12`. The deadline command will call ProjectDeadlineCommand() which will execute the command with executeCommand(). printProjectDeadlineAddedMessage() from the UI class will be returned and printed. 

The following sequence diagram shows how the project deadline command works: 

<center><img src="https://i.ibb.co/N9Cr3h6/Figure10-2.png"></center>

### **Adding a project description (Samuel Leow)** 
This section will explain the addition of project description to a specified project and how the objects interact with each other. 

The user can add a project description to a specified project with the description command. 

This command is facilitated with the help of the ProjectDescriptionCommand class. An instance of the ProjectDescriptionCommand class will have the following properties: 

* projectDescription: The description of the project entered by the user stored as a String 

* projectIndex: The index of the project as an Integer 

Step 1: The user executes `project n/Project One` command to create a new project, followed by select p/1 to enter Project One, which at this instance, switches the app state from Home View to Project View. 

<center><img src="https://i.ibb.co/tsx27Yw/Figure11-1.png"></center>

Step 2: The user types the description command followed by the index of the project then the description of the project e.g. `description p/1 d/Submission` of User Guide. The description command will call ProjectDescriptionCommand() which will execute the command with executeCommand(). printProjectDescriptionAddedMessage() from the UI class will be returned and printed. 

The following sequence diagram shows how the project description command works: 

<center><img src="https://i.ibb.co/FnK6xqD/Figure11-2.png"></center>

### **Marking a project as done (Samuel Leow)**
This section will explain the mark of a specified project as done and how the objects interact with each other. 

The user can mark a specified project as done with the done command. 

This command is facilitated with the help of the ProjectDoneCommand class. An instance of the ProjectDoneCommand class will have the following properties: 

* projectIndex: The index of the project as an Integer 

Step 1: The user executes `project n/Project One` command to create a new project, and the current state of the app is Home View. 

<center><img src="https://i.ibb.co/pzysmyc/Figure12-1.png"></center>

Step 2: After some time, the user would have completed the project and now decides to mark the project as done by executing `done p/1` command. The done command will call ProjectDoneCommand() which will execute the command with executeCommand(). printProjectDoneMessage() from the UI class will be returned and printed. 

The following sequence diagram shows how the project done command works: 

<center><img src="https://i.ibb.co/NSDGbkL/Figure12-2.png"></center>

### **Selecting an existing project (Riaz Ahamed)**
This section will explain the selection of existing projects and how the objects interact with each other. 

The user can select existing projects from the list of projects with the select command. 

This command is facilitated with the help of the ProjectSelectCommand class. An instance of the ProjectSelectCommand class will have the following properties: 

projectIndex: The index of the project as an Integer 

Step 1: The user types the select command followed by the index of the project e.g. `select p/1`.  The main Duke class will call the Parser class. 

Step 2: The Parser class will check which state the app is in and will then call the appropriate class constructor. In this case, the Parser will call the ProjectSelectCommand constructor. The ProjectSelectCommand constructor will also check for the validity of the user’s input. The constraints of the input are as follows 

* Not a number 
* Not a positive number 
* An index of a project that does not exist 

Step 3: The Parser class will then initialize a new instance of the ProjectSelectCommand constructor with the projectIndex as “1” and return it back to the main Duke class. 

Step 4: The Duke class will call the executeCommand function from the returned instance which will execute the command. 

* The executeCommand function will get the instance of the Project from the static projects list using the projectIndex.  

* It will call the projectSelectedMessage from the Ui class and return it to the main Duke class. 

Step 5: The Duke class will then receive the acknowledgement message and display it to the user in the terminal. 

The above is illustrated below in a sequence diagram. The sequence diagram will only encompass the sequence in the executeCommand function. 

<center><img src="https://i.ibb.co/cvHR9Fb/Figure13.png"></center>

---

## **Member-specific Features**
### **Add Member (Samuel Paul Christopher)**
The add a member task allows you to add a member to the organization. These members can be referenced and assigned to projects or tasks at a later stage. 

This can be done using the member command like this: `member m/Member-Name` this will add a member with name “Member Name” to the organization. 

<center><img src="https://i.ibb.co/p063cXy/Figure14-1.png"></center>

After adding a member, you should see a friendly message like the one in Figure 6.2 to indicate success. 

<center><img src="https://i.ibb.co/pZ97syb/Figure14-2.png"></center>

To view the index of a member, please have a look at the view members command which lists the members that have been added.

### **Assigning a member to a project (Samuel Paul Christopher)**
The “Assign person to a project” command allows a software manger to specify which member should be associated with which project in the organization. 

This command is facilitated with the help of the AssignMemberToProjectCommand class that is present in the commands package. 

The command makes use of the teamMembers ArrayList that is present in the Main Class.  

The following examples explains the working of this functionality in the application. 

Step 1. When the user first starts up the application, they by default are presented with the home view with an empty list of projects and an empty list of team members.  

<center><img src="https://i.ibb.co/mq6G5Gx/Figure15-1.png"></center>

Step 2. When present in the home view, the user can add members who belong to their respective organization and by default the added team member as no assigned project to their name. This is done by initializing the assingedProjectId whenever an instance of the TeamMember is created. 

Step 3. After projects are added to the project ArrayList we can assign a TeamMember to each project using command `assign m/MemberId p/ProjectId`. If a member id or project id specified in the command is invalid the program throws an exception to the user to enter a valid Id. 

Step 4. Once a member has been assigned a particular project, the assignedProjectId of the TeamMember object is set to the Id of the project the member is assigned to. 

Step 5. Once the members are assigned to respective projects you can view the list of all the members with their assigned projects using the members command. 

The following sequence diagram provides a visualization of how this command works. 

<center><img src="https://i.ibb.co/YXj60Qp/Figure15-2.png"></center>

### **Assigning a member to a task (Samuel Paul Christopher)**
The “Assign person to task” command allows the user to specify a person and a task and assigns the specified person to the specified task.  

The command is facilitated by TeamMemberAssignToTaskCommand(). It extends Command() which executes the command with executeCommand() which takes in 2 parameters, namely projects ArrayList and teamMembers ArrayList. 

The command utilizes a state and 3 array lists: 

* State: Project View 
* Array List 1: Projects List 
* Array List 2: Tasks List 
* Array List 3: teamMembers List 

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step. 

Step 1: The user launches the application for the first time. The state of the program will be set to Home View by default. 

<center><img src="https://i.ibb.co/fD8r2tF/Figure16-1.png"></center>

Step 2: The user switches to Project View by selecting an existing Project using `select p/index`. If no such project exists, the user must create the project using `project n/name` before selecting. This sets the projectIndex variable to be the index of the project in the Projects List.  

Step 3: After the user has selected the project, the state changes from Home View to Project View 

<center><img src="https://i.ibb.co/XZSMc84/Figure16-2.png"></center>

Step 4: The user then requires an existing task and an existing member to assign the member to the task using `assign t/taskIndex m/memberIndex`. If any one of then does not exist, create tasks with `task n/name` and create members with `member n/name`. 

Step 5: Once the member has been assigned, the task instance of the specified task will have its teamMember field linked to the specified teamMember instance. 

The following sequence diagram shows how the “Assign member to task” command works:

<center><img src="https://i.ibb.co/vdBdtg2/Figure16-3.png"></center>

### **Hours Worked By Worker Command (Sean Tan)**
This command allows project managers to view the total hours worked by a worker 
The logic for this command is primarily written in TeamMemberHoursCommand class. It extends from the abstract Command class. 
The steps below show how such a class is initialized and used to execute the command. 

Step 1: Parser initializes TeamMemberHoursCommand by passing a hashmap of input parameters together with projectIndex into its constructor. 
Step 2: Parse() method of TeamMemberDeleteCommand extracts the index of the member to be retrieved from the hashmap. 
Step 3: ExecuteCommand() method of TeamMemberDeleteCommand is called by Duke main class, which passes it the program’s arraylist of members. 
* The method getTasks() of the specific member is called which retrieves all the tasks the member was assigned. 
* The method getActual() of each of these tasks is called which retrieves the actual time taken to complete these tasks. 
* The total hours of these tasks are then summed up. 
* The Ui class then prints the total number of hours worked by these workers. 

The following sequence diagram shows how the “Hours Worked by worker” command works:

<center><img src="https://i.ibb.co/7Qms8Hc/hours.png"></center>

### **Removing a member from Home View(Sean Tan)**
This command allows project managers to remove members from the main members list in the home view. 

The logic for this command is primarily written in TeamMemberDeleteCommand class. It extends from the abstract Command class. 

The steps below show how such a class is initialized and used to execute the command. 

Step 1: The Parser initializes TeamMemberDeleteCommand by passing a hashmap of input parameters together with memberIndex that was provided by the user using `remove m/1` into its constructor. 

Step 2: The Parse() method of the TeamMemberDeleteCommand class extracts the index of the member to be deleted from the parameter hashmap. 

Step 3: The ExecuteCommand() method of TeamMemberDeleteCommand class is called by EzManager main class, which passes it the program’s arraylist of members and arraylist of projects.

Step 4: TeamMember is removed from the arraylist of TeamMember in the entire program.

Step 5: Loop through each project in the arraylist of Project and remove member from the arraylist of TeamMember if it is present.

Step 6: While still in the Project, loop through each task in the arrayList of Task in the and remove member from the arraylist of TeamMember from each task if it is present.

* The Ui class prints an acknowledgement that the member has been removed. 

### **Removing a member from Project View(Samuel Leow)**
This command allows project managers to remove members from the project in the project view. 

The logic for this command is primarily written in TeamMemberDeleteCommand class. It extends from the abstract Command class. 

The steps below show how such a class is initialized and used to execute the command. 

Step 1: The Parser initializes TeamMemberDeleteCommand by passing a hashmap of input parameters together with memberIndex that was provided by the user using `remove m/1` into its constructor. 

Step 2: The Parse() method of the TeamMemberDeleteCommand class extracts the index of the member to be deleted from the parameter hashmap. 

Step 3: The ExecuteCommand() method of TeamMemberDeleteCommand class is called by EzManager main class, which passes it the program’s arraylist of members and arraylist of projects.

Step 4: TeamMember is removed from the arraylist of TeamMember in the current project.

Step 5: Loop through each task in the arraylist of Task and remove member from the arraylist of TeamMember from each task if it is present.

* The Ui class prints an acknowledgement that the member has been removed. 

---

## **Task-specific Features**
The user enters the following tasks-specific commands when he is in the Project View. As such projectIndex state variable in Parser will point to the project index. 

<center><img src="https://i.ibb.co/C7KW0RM/Figure18.png"></center>

### **Creating a task (Shreyas Kumar)**
This command allows you to create a task in a selected project. 

The logic for this command is written in the TaskCommand class which inherits its general properties from the abstract Command class. 

The following states how the functionality is used in the application and how it is implemented: 

Step 1: The parser class uses the TaskCommand constructor to initialize a new instance of that class type when the user enter the input `task n/this is a task`. 

Step 2: The parse() method in the TaskCommand class extracts the task name for the newly created task. 

Step 3: The executeCommand() of the TaskCommand class is called by the main () method of the Duke class which passes the project and team members array list to the respective method. 

* The current project is obtained using the project array list and the project index that was passed during the instantiation of the TaskCommand object. 

* Now, the method creates a new Task object by passing in the description that was extracted earlier. 

* This Task object is then added to the Task array list that is present in that respective project. 

* The Ui class then prints an acknowledgement message to let the user know that a task has been created. 

The following sequence diagram provides a visualization of how this command works. 

<center><img src="https://i.ibb.co/2WshJqq/Figure19.png"></center>

### **Editing an existing task name (Riaz Ahamed)**

This command allows project managers to delete tasks from projects. 

The logic for this command is primarily written in TaskEditCommand class. It extends from the abstract Command class. 

The steps below show how such a class is initialized and used to execute the command. 

Step 1: The Parser initializes TaskEditCommand by passing a hashmap of input parameters from the `edit t/1 n/new task name` command that was entered by the user. 

Step 2: The parse() method of TaskEditCommand extracts the task index from the hashmap. 

Step 3: The executeCommand() method of TaskEditCommand is called by Duke main class, which passes it the program’s arraylist of projects. 

* Existing project is retrieved from the arraylist of projects using projectIndex. 

* The method editTaskDescription() of the project is called with taskIndex. 

    * This simply updates the task name from the arraylist of tasks the project contains. 

* The Ui class prints an acknowledgement that the task name has been updated. 

The following sequence diagram provides a visualization of how this command works. 

<center><img src="https://i.ibb.co/3Y9mKsq/editTask.png"></center>

### **Deleting a task (Sean Tan)**
This command allows project managers to delete tasks from projects.

The logic for this command is primarily written in TaskDeleteCommand class. It extends from the abstract Command class. 

The steps below show how such a class is initialized and used to execute the command. 

Step 1:The parser class uses the TaskDeleteCommand constructor to initialize a new instance of that class type when the user enters the input `delete t/3`. 

Step 2: The parse() method of TaskDeleteCommand extracts the task index from the input provided by the user. 

Step 3: executeCommand() method of TaskDeleteCommand is called by Duke main class, which passes it the program’s arraylist of projects. 
    
* Existing project is retrieved from the arraylist of projects using projectIndex. 
    
* The method deleteTask of the project is called with taskIndex. 
    
* This simply removes the task from the arraylist of tasks the project contains. 
    
* The Ui class prints an acknowledgement that the task has been deleted. 

The following sequence diagram provides a visualization of how this command works. 

<center><img src="https://i.ibb.co/W6BQkh5/Figure20.png"></center>


### **Adding Deadline to Tasks (Sean Tan)**

This command allows project managers to add deadline to tasks. 

The logic for this command is primarily written in DeadlineCommand class. It extends from the abstract Command class.

The steps below show how such a class is initialized and used to execute the command. 

Step 1: The parser initializes DeadlineCommand by passing a hashmap of input parameters from the command `deadline t/1 d/2020-10-12`. 

Step 2: The parse() method of DeadlineCommand extracts the task index and deadline date from the hashmap. 

Step 3: executeCommand() method of DeadlineCommand is called by Duke main class, which passes it the program’s arraylist of projects. 
   
* Existing task is retrieved with taskIndex after retrieving the project it belongs to from the arraylist of project. 
    
* The method addDeadline of the task is called that sets its date property. 
    
* The Ui class then prints the deadline and the task description. 

The following sequence diagram provides a visualization of how this command works.

<center><img src="https://i.ibb.co/wWtfVLY/Figure21.png"></center>

### **Assign Actual Duration to Tasks (Sean Tan)**    
This command allows project managers to record the actual duration that completed tasks take. 

The logic for this command is primarily written in ActualTimeCommand class. It extends from the abstract Command class. 

The steps below show how such a class is initialized and used to execute the command. 

Step 1: The parser initializes ActualTimeCommand by passing a hashmap of input parameters taken from the command `actual t/3 h/2 m/3` which is entered by the user. 

Step 2: The parse() method of ActualTimeCommand extracts the task index, hours and minutes from the parameter hashmap. 

Step 3: ExecuteCommand() method of ActualTimeCommand is run and the following actions take place. 
  
* Existing task is retrieved using projectIndex and taskIndex. 

* The task is checked to determine if it is completed. 

* If the task is completed, the task instance will have its actualInMinutes property set based on the hours and minutes retrieved. 

* Otherwise, an exception is thrown. 
  
* The Ui class then prints the task description and the duration the task took. 
    
The following sequence diagram provides a visualization of how this command works.

<center><img src="https://i.ibb.co/QF311Zc/Figure22.png"></center>

### **Assign Estimated Duration to Tasks (Sean Tan)**    
This command allows project managers to add an estimate for the duration tasks will take. 

The logic for this command is primarily written in EstimatedTimeCommand class. It extends from the abstract Command class. 

The steps below show how such a class is initialized and used to execute the command. 

Step 1: The parser initializes ActualTimeCommand by passing a hashmap of input parameters taken from the command `estimate t/1 h/4 m/35` which is entered by the user.

Step 2: The parse() method of EstimatedTimeCommand extracts the task index, hours and minutes from the hashmap.

Step 3: ExecuteCommand() method of EstimateTimeCommand is run. 
    
* Existing task is retrieved using projectIndex and taskIndex. 
    
* addEstimate() method of task is called, and the task will have its estimateInMinutes property set based on the hours and minutes retrieved. 

* The Ui class then prints the task description and the estimated duration added to the task. 
 
The following sequence diagram provides a visualization of how this command works.

<center><img src="https://i.ibb.co/rHkZQRd/Figure23.png"></center>

### **Assign priority to task (Riaz Ahamed)** 
This section will explain the assigning of priorities to tasks and how the objects interact with each other. 

The user can assign a priority to any existing task with the `priority` command. 

This command is facilitated with the help of the TaskAssignPriorityCommand class. An instance of the TaskAssignPriorityCommand class has the following properties. 
* projectIndex: The index of the project as an Integer 
* taskIndex: The index of the task as an Integer 
* priority: The priority to be assigned as a String 

Step 1: The user types the priority command e.g. `priority t/1 p/1` and the main Duke class will call the Parser class. 

Step 2: The Parser class will check which state the app is in and will then call the appropriate class constructor. In this case, the Parser will call the ProjectListCommand constructor. The ProjectListCommand constructor will also check for the validity of the user’s input. The constraints of the input are as follows:
  * Not a number for either task index or priority 
  * Not a positive number for either task index or priority
  * An index of a task that does not exist 

Step 3: The Parser class will then initialize a new instance of the TaskAssignPriorityCommand constructor and return it back to the main Duke class. 

Step 4: The Duke class will call the executeCommand function from the returned instance which will execute the command. 
  * The executeCommand will get the specified task using the taskIndex. 
  * It will then set the priority property of the task as the specified priority. 
  * Lastly, it will call the printPriorityAssignedToTaskMessage method from the Ui class and send the acknowledgement message to the main Duke class. 

Step 5: The Duke class will then display the acknowledgement message to the user in the terminal 

The above is illustrated below in a sequence diagram. The sequence diagram will only encompass the sequence in the executeCommand function. 

<center><img src="https://i.ibb.co/xJ4D6Br/Figure24.png"></center>

### **Marking a task as done (Shreyas Kumar)** 
This command allows you to mark a task in a selected project. 

The logic for this command is written in the TaskDoneCommand class which inherits its general properties from the abstract Command class. 

The following states how the functionality is used in the application and how it is implemented: 

Step 1: The parser class uses the `TaskDoneCommand` constructor to initialize a new instance of that class type by passing a hash map which consists of the parameters of that command done t/1 which was entered by the user.

Step 2: The `parse()` method in the `TaskDoneCommand` class extracts the task index of the task that is to be marked done. If the task index is not present in the task list of that project, an exception is thrown. 

Step 3: The `executeCommand()` of the `TaskDoneCommand` class is called by the `main()` method of the `Duke` class and which passes the project and team members array list to the respective method.
  * The current project is obtained using the project array list and the project index that was passed during the instantiation of the `TaskDoneCommand` object.
  * Now, the method fetches the required task by using task index extracted from the  `parse()`.
  * The task object obtained is used to call the member function `markAsDone()` which sets the Boolean field in a task from false to true which the indicates that the task is completed. 
  * The `Ui` class then prints an acknowledgement message to let the user know that a task has been marked as done. 

The above is illustrated below in a sequence diagram. The sequence diagram will only encompass the sequence in the executeCommand function. 

<center><img src="https://i.ibb.co/cxLnHJB/Figure25.png"></center>

### **Sorting Tasks in the TaskList (Shreyas Kumar)**
This command allows you to sort the tasks in a task list in a selected project. 

The logic for this command is written in the `TaskSortCommand` class which inherits its general properties from the abstract Command class. 

The following states how the functionality is used in the application and how it is implemented: 

Step 1: The parser class uses the `TaskSortCommand` constructor to initialize a new instance of that class type by passing the input which consists of the parameter of the command sort p/ , sort d/ or sort a/. 

Step 2: The `parse()` method in the `TaskSortCommand` class extracts the sorting type (deadline, priority, actual time). 

Step 3 The `executeCommand()` of the `TaskDoneCommand` class is called by the `main()` method of the `Duke` class and which passes the project and team members array list to the respective method.

  * The current project is obtained using the project array list and the project index that was passed during the instantiation of the TaskSortCommand object. 
  * Now, based on the sorting type the method will choose a case statement in a switch-case that reflects the type of sorting.
  * The `Ui` class then prints an acknowledgement message to let the user know that the tasks have been sorted according to the respective sorting type. 

The above is illustrated below in a sequence diagram. The sequence diagram will only encompass the sequence in the executeCommand function. 

<center><img src="https://i.ibb.co/QXwcYxR/Figure27.png" ></center>

## **List Feature**
The list command provides different outputs based on the view the user is current in.

1. List command when the user is in Home-View
<center><img src="https://i.ibb.co/51CBNXc/Figure27-1.png" ></center>

2. List command when the user is in Project-View
<center><img src="https://i.ibb.co/yBQkgqH/Figure27-2.png"></center>

The sequence diagram for the the list feature in the Home-View is given below.
<center><img src="https://i.ibb.co/9pzwF12/Figure27-3.png"></center>

The sequence diagram for the the list feature in the Project-View is given below.
<center><img src="https://i.ibb.co/TT1C8fN/Figure27-4.png"></center>

The outputs of the list command is the same as that of specified in the Views section.

## **Running Tests (Samuel Paul Christopher)** 
There are two ways to run tests for EZ manager. 
> To check for test coverage, please ensure that you `Run with coverage` when right clicking on the tests to run. 

**Method 1: Using IntelliJ JUnit test runner**
- To run all tests available, right-click on the `src/test/java` folder and choose `Run  All Tests`
- To run a some of the tests, you can right-click on a test package, test class, or a test and choose `Run Particular Test Name Here` 

**Method 2: Using Gradle**
- On Windows, run the command `gradlew clean allTests` in a terminal 
- On Mac or Linux, run the command `./gradlew clean allTests` in a terminal 

## **DevOps (Samuel Paul Christopher)** 
Here are the steps to create a new release. 
1. Update the version number in Duke.java 
2. Generate a JAR file using Gradle
3. Tag the repo with the version number. e.g. v0.1 
4. Create a new release using GitHub and upload the JAR file you created. 

## **Appendix A: Product Scope (Samuel Leow)** 
Target user profile: 
- Project Manager of Software Engineering projects 
- Needs to manage teams for different projects 

Value proposition:
Manage projects in a smooth and seamless way to allow the team to get more done 

**User Stories**

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

Priority | As a ... | I want to ... | So that I can ...
---------- | -------------------- | ------------------------- | -------------------------
`* * *` | new user | see usage instructions | refer to the commands when I forget how to use the App
`* * *` | project manager | add a new project | track the progress of my new project 
`* * *` | project manager | add tasks under a project | track the project tasks needed to finish the project
`* * *` | project manager | add a new member | manage members in my team 
`* * *` | project manager | select a project | go into a particular project to make changes such as adding tasks and deadline specific to the project
`* * *` | project manager | navigate between home view and project view | switch from one project to the other project
`* * *` | project manager | delete an existing project | remove projects that are not required anymore
`* * *` | project manager | delete an existing task | remove tasks that are not required anymore
`* * *` | project manager | remove an existing member | remove members that have left the team
`* * *` | project manager | add a deadline to an existing project | keep the project on track and deliver the product on time
`* * *` | project manager | add a deadline to an existing task | keep the task on track and finish it on time
`* * *` | project manager | mark completed projects as done | see which projects are done and which still needs to be done
`* * *` | project manager | mark completed tasks as done | see which tasks are done and which still needs to be done
`* * *` | project manager | add a project description | have a better understanding of the concept and context of the project
`* * *` | project manager | add a task description | have a better understand of the task and know what needs to be done
`* * *` | project manager | assign a member to a project | allocate projects to team members 
`* * *` | project manager | assign a member to a task | allocate equal workload to every team member
`* * *` | project manager | assign priority to a task | organise the tasks in order of priority and focus on those that are more urgent
`* *` | project manager | assign estimated time needed to complete a task | give my team a rough estimate of how long is expected for them to finish the task
`* *` | project manager | assign actual time used to complete a task | find out the amount of time spent and understand which task is taking more time than expected
`* *` | project manager | display all the projects and members all in one view | have an overview of all the projects and members
`* *` | project manager | display all the tasks and members allocated to the project all in one view | have an overview of the unfinished tasks and members assigned to those tasks
`* *` | project manager | sort my list of projects in terms of deadline | prioritise projects that are urgent and focus on completing the project before the deadline
`* *` | project manager | sort my list of tasks in terms of deadline | focus on the tasks that have a closer deadline
`* *` | project manager | sort my list of tasks in terms of priority | focus on the tasks that are most important as some are base level tasks which are required for the project to be up and running
`* *` | project manager | sort my list of tasks in terms of actual time spent | have an overview of which tasks are taking up more time and required more manpower in the future
`*` | project manager | add the roles of my team members | allocate appropriate tasks to appropriate members
`*` | project manager | send out reminders to my team members | have them shift gears or change something in real time  

## **Appendix B: Command Summary (Samuel Paul Christopher)** 

|Command  |Description and Examples  |
|---------|--------------------------|
|project| Creates a new project in the project list.  <br> Example: `project n/Web Development Project `|
|task |Creates a new task in the task list.  <br> Example: `task n/Deploy Version 2.0`|
|member|Creates a new member in the member list. <br> Example: `task n/Deploy Version 2.0`|
|list|If in Home-View, displays the project list and members list. <br> If in Project-View displays task list and members of that project <br> `list`|
|select |Selects a specified project in Home-View and program enters ProjectView  <br> Example: `select p/1 `|
|delete|If in HomeView, it deletes the specified project. <br> If in ProjectView, it deletes the specified task  <br> Example: `delete p/1`,`delete t/1`|
|remove|If in Home-View, it removes a member from the member and they are removed from their assigned projects as well <br> remove m/1|
|done|If in HomeView, marks the specified project as done <br> If in ProjectView, marks the specified task as done  <br> Example: `done p/1`,`done t/1`|
|description |If in HomeView, it assigns a description to the specified project <br> Example: `description p/1 d/Project for Company X` |
|deadline|If in HomeView, it assigns a deadline to the specified project <br> If in ProjectView, it assigns a deadline to the specified task <br> Example: `deadline p/1 d/2020-10-25`,`deadline t/1 d/2020-10-25`|
|priority |If in ProjectView, it assigns a priority to the specified task <br> Example: `priority t/1 p/1`|
|edit|If in Project-View it changes the name of a current task to a name specified by the user. <br> Example: `edit t/1 n/new name`|
|priority|If in ProjectView, it assigns a priority to the specified task <br> Example: `priority t/1 p/1 `|
|estimate|If in ProjectView, it assigns an estimated completion time to the specified task <br> Example: `estimate t/1 h/3 m/20`|
|actual|If in ProjectView, it assigns the actual completion time to the specified task <br> Example: `actual t/2 h/1 m/20`|
|assign |If in HomeView, it assigns a member to a specified project. <br> If in ProjectView, assigns member to specified task <br> Example: `assign p/1 m/1 `, `assign t/2 m/3`|
|sort|If in Project view it assigns tasks either by actual time, deadline or priority <br> Example: `sort s/p, sort s/a, sort s/d`|
|home|Switches from ProjectView to HomeView and vice versa <br> Example: `home`|
|bye|prints a goodbye message. <br> Example: `bye`|


## **Appendix C: Instructions for Manual Testing (Shreyas Kumar)**

### <ins>Project-Specific Tests</ins>

#### C.1 Creating a Project
1. Creating a Project when you are in the Home-View
    * Prerequisites: Only Required to be in the Home-View 
    * Test case: `project n/p1`: A new project named as p1 will be created and added to the project list. A message will be displayed on the screen to ensure the user that the project has been created.
    * Test case: `project p1`: Since the n/ parameter has not been provided, it is considered as an incorrect command in the application and hence the program throws an exception.
    
2. Creating a Project when you are in the Project-View
    * Prerequisites: Required to be in Project-View
    * Test case: `project n/p1`: Since the state of the program is in project view, the user will not be allowed to create a project and hence the program throws an exception.
    
#### C.2 Deleting a Project
1. Deleting a Project when you are in the Home-View
    * Prerequisites: Only Required to be in the Home-View
    * Test case: `delete p/1`: The project named as p1 will be deleted and from the project list. A message will be displayed on the screen to ensure the user that the project has been deleted.
    * Test case: `delete p1`: Since the p/ parameter has not been provided, it is considered as an incorrect command in the application and hence the program throws an exception.

2. Deleting a Project whose project-id is greater that the total number of projects
    * Prerequisites: Required to be in the Home-View and have three projects created.
    * Test case: `delete p/4`: Since there are only three projects in the project list, the project-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid project-id.

3. Creating a Project when you are in the Project-View
    * Prerequisites: Required to be in Project-View.
    * Test case: `delete p/1`: Since the state of the program is in project view, the user will not be allowed to delete a project and hence the program will not be able to recognize the p/ parameter and will hence throw an exception.

#### C.3 Selecting a Project
1. Selecting a Project when you are in the Home-View
    * Prerequisites: Only Required to be in the Home View (Initial state of the application) and, the project p1 has already been created.
    * Test case: `select p/1`: The project with the index of 1 will be will selected from the
      project list and, you will enter project view of that respective project.
    * Test case: `select p1`: Since the p/ parameter has not been provided, it is considered as an incorrect command in the application and hence the program throws an exception.

2. Selecting a Project whose project-id is greater that the total number of projects
    * Prerequisites: Required to be in the Home View and have three project created.
    * Test case: `select p/4`: Since there are only three projects in the project list, the project-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid project-id.

3. Selecting a Project when you are in the Project-View
    * Prerequisites: Required to be in Project-View
    * Test case: `select p/1`: Since the state of the program is in project view, the user will not be allowed to delete a project and hence the program will not be able to recognize the p/ parameter and will hence throw an exception.
      
#### C.4 Providing a Description for a Project (need to update this)
1. Providing a description for the Project when you are in the Project-View
    * Prerequisites: Required to be in the Project-View.
    * Test case: `description p/1 d/This is a description`: Since we are in Project-View, we would not be able to add a description to any of them and hence the program will throw an exception.
   
2. Providing a description for the Project when you are in the Home-View 
    * Prerequisites: Required to be in Home-View.
    * Test case: `description p/1 d/This is a description`: Since the state of the program is in Home-View, the description 'This is a description' will be added to the project having an index of 1. An acknowledgement message is printed on the screen to ensure the user that the project has been provided with a description.
    
### <ins>Task-Specific Tests</ins>

#### C.5 Creating a Task
1. Creating a Task when you are in the Project-View
    * Prerequisites: Only Required to be in Project-View.
    * Test case: `task n/t1`: A new task named as t1 should be created and added to the task list of that project. A acknowledgement message will be displayed on the screen to ensure the user that the task has been created.

2. Creating a Task when you are in the Home-View
    * Prerequisites: Required to be in Home-View.
    * Test case: `task n/1`: Since the state of the program is in Home-View, the user will not be allowed to create a task and hence the program throws an exception.
    
#### C.6 Deleting a Task
1. Deleting a Task when you are in the Project-View
    * Prerequisites: Required to be in Project-View. 
    * Test case: `delete t/1`: The first task in the task list of the project will be deleted and, an acknowledgement message will be displayed on the screen to ensure the user that the required task has been deleted.

2. Deleting a Task whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in the Project-View and have five tasks created.
    * Test case: `select t/7`: Since there are only five tasks in the task list, the task-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid task-id.    

3. Deleting a Task when you are in the Home-View
    * Prerequisites: Required to be in Home-View.
    * Test case: `delete t/1`: Since the state of the program is in Home view, the user will not be allowed to delete a task and hence the program throws an exception.
    
#### C.7 Marking a Task as Completed
1. Marking a Task as done in Project-View
    * Prerequisites: Required to be in Project-View of a created project and have tasks created in that respective project.
    * Test case: `done t/1`: The first task in the task list of that project will be marked as completed and an acknoweldegement message will be displayed on the screen to ensure the user that the task has been marked as completed.

2. Marking a Task as done whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in the Project-View and have five tasks created.
    * Test case: `done t/7`: Since there are only five tasks in the task list, the task-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid task-id. 

3. Marking a Task as done in Home-View 
    * Prerequisites: Required to be in Home-View
    * Test case: `done t/1`: Since the state of the program is in Home view, there will only be projects present so the user will not be able to mark a task as completed and the program throws an exception.

#### C.8 Assigning a Deadline to a Task
1. Assigning a Deadline to a Task in Project-View
    * Prerequisites: Required to be in Project-View of a created project and have tasks created in that respective project.
    * Test case: `deadline t/1 d/2020-11-09`: Since the state of the program is in Project-View and the deadline mentioned is in the format YYYY-MM-DD the program assigns the date 2020-11-09 as the deadline to task 1. An acknoweldegment message is also displayed to ensure the user that the required task has been assigned to the respective deadline. 
    * Test case: `deadline t/1 d/09-10-2020`: Eventhough the state of the program is in Project-View the deadline is of the format DD-MM-YYYY which is the incorrect format while inputting the date. An exception mesaage is also thrown by the program to tell the user to enter the date in the YYYY-MM-DD format.
    * Test case: `deadline t/1 d/11-25-2020`: Eventhough the state of the program is in Project-View the deadline is of the format MM-DD-YYYY which is the incorrect format while inputting the date. An exception mesaage is also thrown by the program to tell the user to enter the date in the YYYY-MM-DD format.

2. Assigning a Deadline to a Task in Home-View
    * Prerequisites: Required to be in Home-View.
    * Test case: `deadline t/1 d/2020-11-09`: Since the state of the program is in Home-View, there will only be projects present, so the user will not be able to mark a task as completed and the program throws an exception.

3. Assigning a Deadline to a Task whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in the Project-View and have five tasks created.
    * Test case: `deadline t/7 d/2020-12-22`: Since there are only five tasks in the task list, the task-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid task-id.

#### C.9 Assigning a Priority to a Task
1. Assigning a Priority to a Task in Project-View. 
    * Prerequisites: Required to be in Project-View of a created project and have tasks created in that respective project.
    * Test case: `priority t/1 p/1`: Since the state of the program is in Project-View, the priority 1 will be assigned to task 1 and an acknoweldegment message is also displayed on the screen to ensure the user that the priority has been assigned.

2. Assigning a Priority to a Task in Home-View
    * Prerequisites: Required to be in Home-View.
    * Test case: `priority t/1 p/1`: Since the state of the program is in Home-View, there will only be projects present, so the user will not be able to assign a prirority to a task and the program throws an exception.

3. Assigning a Priority to a Task whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in the Project-View and have five tasks created.
    * Test case: `priority t/7 p/3`: Since there are only five tasks in the task list, the task-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid task-id.

#### C.10 Assigning Estimated Completion Time to Task
1. Assigning an Estimated completion time to a Task in Project-View
    * Prerequisites: Required to be in Project-View of a created project and have tasks created in that respective project.
    * Test case: `estimate t/1 h/3 m/10`:  Since the state of the program is in Project-View an estimated completion time of 1 hour and 10 minutes will be assigned to task 1 and an acknoweldegment message is also displayed on the screen to ensure the user that the estimated completion time has been assigned.

2. Assigning an Estimated completion time to a Task in Home-View
    * Prerequisites: Required to be in Home-View
    * Test case: `estimate t/1 h/3 m/10`: Since the state of the program is in Home-View, there will only be projects present, so the user will not be able to assign an estimated completion time to a task and the program throws an exception.

3. Assigning an Estimated completion time to a Task whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in the Project-View and have five tasks created.
    * Test case: `estimate t/7 h/3 m/20`: Since there are only five tasks in the task list, the task-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid task-id.

#### C.11 Assigning Actual Completion Time to Task
1. Assigning an Actual completion time to a completed Task in Project-View
    * Prerequisites: Required to be in Project-View of a created project and have atleast one completed task in that respective project.
    * Test case: `actual t/1 h/3 m/10`:  Since the state of the program is in Project-View and the task in marked as completed, an actual completion time of 1 hour and 10 mimnutes will be assigned to task 1 and an acknoweldegment message is also displayed on the screen to ensure the user that the actual completion time has been assigned.

2. Assigning an Actual completion time to a incompleted Task in Project-View
    * Prerequisites: Required to be in Project-View of a created project and have tasks created in that respective project.
    * Test case: `actual t/1 h/3 m/10`:  Since the state of the program is in Project-View and task 1 has not been completed an actual completion time cannot be assigned to the task and the program will display an exception message that informs the user that the task should be marked as completed.

3. Assigning an Actual completion time to a Task in Home-View
    * Prerequisites: Required to be in Home-View.
    * Test case: `actual t/1 h/3 m/10`: Since the state of the program is in Home-View, there will only be projects present, so the user will not be able to assign an actual completion time to a task and the program throws an exception.

4. Assigning an Actual completion time to a Task whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in the Project-View and have five tasks created.
    * Test case: `actual t/7 h/3 m/25`: Since there are only five tasks in the task list, the task-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid task-id.
#### C.12 Sorting Tasks in TaskList

1. Sorting Tasks by deadline in Project-View
    *Prerequisites: Required to be in Project-View of a created project and have tasks in that respective project.
    *Test case: `sort s/d`: Since the state of the program is in project-view, all the tasks in the list that have a deadline will be sorted in ascending order and the tasks that do not have a deadline are placed towards the end.

2. Sorting Tasks by completion time in Project-View
    *Prerequisites:  Required to be in Project-View of a created project and have tasks in that respective project.
    *Test case: `sort s/t`: Since the state of the program is in Project-View, all the tasks in the list that have an actual time will be sorted in ascending order and the tasks that do not have an actual time are placed towards the end of the list.

3. Sorting Tasks by priority in Project-View
    *Prerequisites:  Required to be in Project-View of a created project and have tasks in that respective project.
    *Test case: `sort s/p`: Since the state of the program is in Project-View, all the tasks in the list that have a priority will be sorted in ascending order of priority and the tasks that do not have a priority are placed towards the end of the list.

4. Sorting Tasks in Home-View.
    * Prerequisites: Required to be in Home-View.
    * Test case: `sort s/t`: Since the state of the program is in Home-View, there will only be projects present, so the user will not be able to sort any sets of tasks and the program throws an exception.

### <ins>Member-Specific Tests</ins>

#### C.13 Adding a Member

1. Adding a member in Home-View 
    * Prerequisites: Required to be in Home-View.
    * Test case: `member n/John Doe`: Since the state of the program is in home view, the member will be created and added to the member arraylist. The program also displays an acknoweldegment message to ensure the user that a new member has been added.

2. Adding a member in Project-View 
    * Prerequisites: Required to be in Project-View.
    * Test case: `member n/John Doe`: Since the state of the program is in Project-View, the program will throw an exception message to the user because a member can only be added in the Home-View.

#### C.14 Removing a Member

1. Removing a member in Home-View 
    * Prerequisites: Required to be in Home-View.
    * Test case: `remove m/1`: Since the state of the program is in Home-View, the member will be removed from the member arraylist. The program also displays an acknoweldegment message to ensure the user that the member has has been removed.

2. Removing a member in Project-View 
    * Prerequisites: Required to be in Project-View
    * Test case: `remove m/2`: Since the state of the program is in Project-View, the program will throw an exception message to the user because a member can only be removed in the Home-View.

3. Removing a member from a Task whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in Home-View with five members added to the members list.
    * Test case: `remove m/6`: Since there are only five members in the members list, the member-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid member-id.

#### C.15 Assigning a Member to a Project

1. Assigning a member to a project in Home-View 
    * Prerequisites: Required to be in Home-View.
    * Test case: `assign m/2 p/1`: Since the state of the program is in Home-View, the member will be assigned to the respective project. The program also displays an acknoweldegment message to ensure the user that a new member has been added.

2. Assigning a member to a project in Project-View 
    * Prerequisites: Required to be in Project-View.
    * Test case: `assign m/1 p/1`: Since the state of the program is in Project-View, the program will throw an exception message to the user because a members can only be assigned to projects in the Home-View.

#### C.16 Assigning a Member to a Task

1. Assigning a member to a project in Home-View 
    * Prerequisites: Required to be in Home-View.
    * Test case: `assign t/1 m/2`: Since the state of the program is in Project-View, the program will throw an exception message to the user because a member can only be assigned to tasks in the Project-View.

2. Assigning a member to a project in Project-View 
    * Prerequisites: Required to be in Project-View.
    * Test case: `assign t/2 m/1`: Since the state of the program is in  Project-View, the member will be assigned to the respective project. The program also displays an acknoweldegment message to ensure the user that the new member has been assigned to the respective task.

4. Assigning a member to a Task whose task-id is greater than the total number of tasks
    * Prerequisites: Required to be in the Project-View and have five tasks created.
    * Test case: `assign m/1 t/8`: Since there are only five tasks in the task list, the task-id provided to the program is invalid and the program will throw an exception to tell the user to enter a valid task-id.









