# User Guide
## Introduction
Welcome to EZ Manager! 

EZ Manager is a CLI (Command Line Interface) software tool for Software Engineering Project Managers like yourself to manage your projects, tasks and team members in an all in one app.

With Ez Manager's command line interface, you can easily make changes and updates with a few simple keystrokes! Our app also conveniently presents the most important information at a glance with just two main views: Home View and Project View.

### How to use this guide
This guide provides a documentation of the commands in EZ Manager. 

These commands are categorised into the two main views they can be called from: Home View and Project View. 

Click on any of the links on the Table of Contents to go directly to the specific commands you want to call. 

At the end of the document, the command summary section provides a helpful summary of all of EZ Manager's commands.

:warning: : This refers to any formatting issues to look out for
when keying in the commands.

:exclamation: : This refers to any other constraints to look out for
besides formatting issues.

:bulb: : This refers to any helpful tips that might prove helpful to you.  

---
<div style="page-break-after: always;"></div>

### Structure
Under each command, a description of what the command does is provided. 

Then the format of the command is specified in a `code snippet`.

You can see an example usage of the command followed by the expected output. 

```
command PARAMETER_TYPE/VALUE
________________________________
example output
```  
---  
<div style="page-break-after: always;"></div>  

## Table of Contents
1. [Quick Start](#1-quick-start)
2. [Terminologies](#terminologies)
3. [Home View](#home-view)
    1. [Commands](#31-home-view-commands)
        1. [List: Viewing the updated Home View](#311-viewing-the-updated-home-view-list)
        2. [Project: Adding a project](#312-adding-a-project-project)
        3. [Select: Selecting a project](#313-selecting-a-project-select)
        4. [Done: Marking a project as done](#314-marking-a-project-as-done-done)
        5. [Deadline: Adding a deadline to a project](#315-adding-a-deadline-to-a-project-deadline)
        6. [Description: Adding a description to a project](#316-adding-a-description-to-a-project-description)
        7. [Delete: Deleting a project](#317-deleting-a-project-delete)
        8. [Member: Adding a member](#318-adding-a-member-member)
        9. [Assign: Assigning a member to a project](#319-assigning-a-member-to-a-project-assign)
        10. [Remove: Removing a member](#3110-removing-a-member-remove)
        11. [Hours: Hours worked by member](#3111-view-hours-worked-by-member-hours)
        12. [Bye: Exit Program](#3112-exiting-ez-Manager-bye)
4. [Project View](#project-view)
    1. [Commands](#project-view-commands)
        1. [List: Viewing the updated Project View](#411-viewing-the-updated-project-view-list)
        2. [Task: Adding a task](#412-adding-a-task-task)
        3. [Edit: Editing a task name](#413-editing-a-task-name-edit)
        4. [Done: Marking a task as done](#414-marking-a-task-as-done-done)
        5. [Deadline: Adding a deadline to a task](#415-adding-a-deadline-to-a-task-deadline)
        6. [Priority: Adding a priority to a task](#416-adding-a-priority-to-a-task-priority)
        7. [Delete: Deleting a task](#417-deleting-a-task-delete)
        8. [Assign: Assigning a member to a task](#418-assigning-a-member-to-a-task-assign)
        9. [Remove: Removing a member from the project](#419-removing-a-member-remove)
        10. [Estimate: Adding estimated time to a task](#4110-add-estimated-time-estimate)
        11. [Actual: Adding actual time to a task](#4111-add-actual-time-taken-actual)
        12. [Sort: Sort tasks](#4112-sort-tasks-sort)
        13. [Bye: Exit Program](#4113-exiting-ez-manager-bye)
5. [FAQ](#faq)
6. [Command Summary](#command-summary)  
  
 ---  
<div style="page-break-after: always;"></div>      

## 1. Quick Start
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `EZ Manager` from [here](https://github.com/AY2021S1-CS2113T-T09-1/tp/releases).
3. Copy the JAR  file into an empty new folder.
4. Open Command Prompt (on Windows) or Terminal (on Mac) and cd into the new folder.
5. Then type java -jar ezManager.jar to run EZ Manager.


## 2. Terminologies
- **Home View**: Refers to the state of the program in Home View.
In this view, you can execute project and member commands but
you cannot execute task commands.
- **Project View**: Refers to the state of the program in Project View.
In this view, you can execute task and member commands but
you cannot execute project commands.
- **Project list**: Refers to the list of projects currently
existing in the system
- **Task list**: Refers to the list of tasks currentl
existing in the system
- **Member list**: Refers to the list of membersrrently
existing in the system
- **PROJECT_INDEX**: Every existing project would be assigned
a positive integer. Hence, the PROJECT_INDEX refers to this
positive integer. You can retrieve the PROJECT_INDEXes of 
all projects by using the `list` command in HomeView
- **TASK_INDEX**: Every existing task would be assigned
a positive integer. Hence, the TASK_INDEX refers to this
positive integer. You can retrieve the TASK_INDEXes of 
all tasks by using the `list` command in Project View
- **MEMBER_INDEX**: Every existing member would be assigned
a positive integer. Hence, the MEMBER_INDEX refers to this
positive integer. You can retrieve the MEMBER_INDEXes of 
Members by using the `list` command in Home View or in Project View  


>  :warning: Project Index, Task Index and Member Index must be positive integers.

---
<div style="page-break-after: always;"></div>


## 3. Home View (Samuel Leow Wei Han)
As a project manager, you want to have a quick overview of all the projects that are under your charge.
Apart from just projects, you will also like to be able to see what your members are working on, so that you can allocate 
work evenly among your members.
The Home View does just that. You get a concise display of the full list of projects and members that are under your purview. 

At a glance, you will notice the few most important details about your project such as its status, deadline and number of tasks completed.

The Home View also conveniently provides you with warnings for uncompleted tasks with imminent deadlines.

Remarks shown for each project depends on the deadline of its tasks.
Task that you have not completed and has an upcoming deadline due in 5 days or less will have `!!!Warning!!!` shown in the remarks as well as a countdown to the deadline.
Task that you have not completed but has a deadline due in 6 days or more will only have the name of task and date of deadline displayed in the remarks. 

You can return to the Home View with the command shown below.

> ### :bulb: Accessing Home View
>
> Format: `home`
>
> Displays the Home View. If the user is in the Project View, this command changes the view to Home View and 
> commands specific to the Home View is enabled.  


Example of usage and output:

![ProjectView2](https://i.ibb.co/xKvJZj0/ss2.png)

---
<div style="page-break-after: always;"></div>

## 3.1. Home View Commands

> ### Command Format
>
> Commands are specified in the following format:
>
>> `COMMAND_TYPE`  `PARAMETER1` `PARAMETER2` ...
>
> **:bulb: Parameters can be specified in any order!**
>
> **:bulb: Commands and parameter types can be in either lowercase or uppercase** 
>
> Parameters are specified in the following format:
>
> `PARAMETER_TYPE/PARAMETER_VALUE`
>
> **:bulb: Parameter values will take in whatever that has been entered including unnecessary spacing** 



### 3.1.1. Viewing the Home View: `list` (Samuel Leow Wei Han)
As a project manager, you want to keep an eye on your team's progress and workload. Viewing the Home View provides you with the big picture, allowing you to focus your attention on the projects that are more important.
Additionally, after making some changes to your project information or assigning members to a new project, 
you will want to see the updated Home View immediately. 

You can display the Home View with the command shown below. You will not have to worry about drowning in a sea of information from now on!

Format: `list`

Example of usage and output:

![ProjectView2](https://i.ibb.co/xKvJZj0/ss2.png)


### 3.1.2. Adding a project: `project` (Samuel Leow Wei Han)
You have just struck a deal with Company X and are being tasked to design a particular software.
But first things first, you have to create a new project before you start creating tasks and allocating them to your team members.
Why is this important? Organising your tasks, members, deadlines and more will enable you to have a clearer understanding on what to do.

You can simply list a new project on your project list with the command shown below.

Format: `project n/PROJECT_NAME`

>  :warning: Project names should not include slashes or an error will be shown.

Example usage and output: 

>  Adds the project 'Web Development' to the project list.

```
project n/Web Development
____________________________________________________________
Project "Web Development" created!
```  

**Examples of Exception Handling for this command**
> Adding a Project without providing the n/ parameter
```
project New Project
Certain Parameters are missing!
```  
---
<div style="page-break-after: always;"></div>    

### 3.1.3. Selecting a project: `select` (Samuel Leow Wei Han)
There are many requirements to fulfil in order to develop a new software product. 
With numerous products to look after, you will want to be able to zoom in on a certain project and start working on it.
You want to ensure that everything you have entered pertaining to a certain project will be stored accordingly.

This can be accomplished with the command shown below, bringing you to the Project View of the project you have specified.

> :exclamation: The project must exist before it can be selected.

Format: `select p/PROJECT_INDEX`

Example of usage: 

> Selects the first project in the project list and displays Project View of first project.

```
select p/1
```
**Examples of Exception Handling for this command**
> Adding a Project without providing the p/ parameter
```
select 1
Certain Parameters are missing!
```

> Providing Project name instead of Project index
```
select p/"Web Development"
Index must be an integer!
```

> Selecting a invalid Project-ID
```
select p/8
_____________________________________________________________________________________
Project ID does not exist!
```  
---
<div style="page-break-after: always;"></div>  
  
### 3.1.4. Marking a project as done: `done` (Samuel Leow Wei Han)
Now that you have finally finished developing your project, you will want to mark the project as done 
so that you can free your mind from that project and focus on the other unfinished ones.

To mark a project as done, you can use the command shown below.

> :exclamation: The project must exist before it can be marked as done.

Format: `done p/PROJECT_INDEX`

Example of usage: 

> Marks the first project in the project list as done.

```
done t/1
____________________________________________________________
Task "New Task" is done!
____________________________________________________________
``` 
**Examples of Exception Handling for this command**
> Marking a project as done without the p/ parameter
```
done 1
Certain Parameters are missing!
```
> Providing Project name instead of Project index
```
done p/"p3"
Index must be an integer!
```
> Marking an invalid Project-ID as done
```
_____________________________________________________________________________________
done p/8
_____________________________________________________________________________________
Project ID does not exist!
_____________________________________________________________________________________
```  
---
<div style="page-break-after: always;"></div>  



### 3.1.5. Adding a deadline to a project: `deadline` (Samuel Leow Wei Han)
Deadlines make clear of when and what you are expected to deliver. Failing to meet them can result in bad business reputation and should not be tolerated.
With multiple projects to work on, you want to be able to keep track of these projects and deliver them to your clients on time.
You can simply add a deadline to an existing project. 
We will sort the projects in the list for you according to their deadlines to inform you the projects that are most urgent.

You can add a deadline to a specified project with the command shown below.

> :exclamation: The project must exist before a deadline can be added.

Format: `deadline p/PROJECT_INDEX d/DATE`

> :warning: The `DATE` must be of the form `YYYY-MM-DD`

Example of usage: 

>  Adds the deadline 04/03/2022 to the third project.

![ProjectView1](https://i.ibb.co/7VPSfCW/ss.png)
  
---
<div style="page-break-after: always;"></div>    

> Adds an earlier deadline 12/12/2020 to the third project to show sorting of projects.

![HomeView](https://i.ibb.co/0rwVkQD/ss3.png)

**Examples of Exception Handling for this command**
> Providing a deadline to a project in in the DD-MM-YYYY format
```
deadline p/1 d/22-11-2020
Date must be specified in format YYYY-MM-DD
```
> Providing a deadline to a project in in the MM-DD-YYYY format
```
deadline p/1 d/11-27-2020
Date must be specified in format YYYY-MM-DD
```    



### 3.1.6. Adding a description to a project: `description` (Riaz Ahamed)
As a project manager, you would like to see the descriptions of different projects.
The `description` command allows you to add descriptions to a project as shown below.

>:exclamation: The project must exist before a description can be added.

You can use the following format to add descriptions to a specific project.
Format: `description p/PROJECT_INDEX d/DESCRIPTION`

> :warning: Project descriptions should not include slashes or an error will be shown.
  
---
<div style="page-break-after: always;"></div>    

Example of usage: 

>  In the example shown below, the user adds the description `This is my Software Engineering Module` to the first project.
>  The user inputs the `p` parameter followed by the index of the project that the user wants to add a description to.
>  The user then inputs the `d` parameter followed by the project description that he would like to add to the first project.
>  Once the description has been added successfully, you can see an acknowledgement message being sent to the user.

```
description p/1 d/This is my Software Engineering Module.
____________________________________________________________
Project description added "This is my Software Engineering Module.".
```

>  :bulb: You can also input the `d` parameter first followed by the `p` parameter as shown below.

```
description d/This is my Software Engineering Module p/1.
____________________________________________________________
Project description added "This is my Software Engineering Module.".
```

**Examples of Exception Handling for the `description` command** 
Shown below are some examples of misuse of the `description` command that you could potentially make as a user.

> If you did not include the `d` parameter, you will be responded with an error message as shown below.
```
description p/1 this is a new project
Certain Parameters are missing!
```  
---
<div style="page-break-after: always;"></div>  

### 3.1.7. Deleting a project: `delete` (Riaz Ahamed)
As a project manager, you would like to delete projects for various reasons. Some reasons
include; creating a project by mistake, project is no longer in progress or the project has been discontinued.
Hence, the `delete` command allows you to delete a specific project, one at a time, as shown below.

You can use the following format to delete a specific project.
Format: `delete p/PROJECT_INDEX`

> :exclamation:  The project must exist in the project list before it can be deleted.
> :warning:  The `PROJECT_INDEX` must be a positive integer.

Example of usage: 

>  In the example shown below, the user deletes the second project in the list. 
>  The user uses the `p` parameter followed by the index of the project from the list of projects.
>  Once the project has been deleted successfully, you can see an acknowledgement message being sent to the user.

```
delete p/2
____________________________________________________________
Project "Home Improvement" deleted
____________________________________________________________
```
**Examples of Exception Handling for the `delete` command**
Shown below are some examples of misuse of the `delete` command that you could potentially make as a user.

> In the example shown below, there are only 7 projects in the list of projects.
> If you try to delete a project that does not exist, for example, deleting the eighth project, you will be responded with an error message as shown below.
```
_____________________________________________________________________________________
deleting p/8
_____________________________________________________________________________________
Project ID does not exist!
_____________________________________________________________________________________
```
  
---
<div style="page-break-after: always;"></div>  
  
  
### 3.1.8. Adding a member: `member` (Riaz Ahamed)
As a project manager, you would like to add members to join your team. You can then assign members 
to projects or tasks in the future using the `assign` command. 
The `member` command allows you to add members to your team as shown below.

You can use the following format to add a member to your team.
Format: `member n/MEMBER_NAME`

Example of usage: 

>  In the example shown below, the user adds the member `John Doe`. 
>  The user uses the `n` parameter followed by the name of the member.
>  Once the member has been added successfully, you can see an acknowledgement message being sent to the user.

```
member n/John Doe
____________________________________________________________
Team member "John Doe" has been added
```
**Examples of Exception Handling for the `member` command** 
Shown below are some examples of misuse of the `member` command that you could potentially make as a user.

> If you did not include the `n` parameter, you will be responded with an error message as shown below.
```
member steve
Certain Parameters are missing!
```

### 3.1.9. Assigning a member to a project: `assign` (Riaz Ahamed)
As a project manager, you would like to assign members to specific projects.
You can then keep track of which members are assigned to which projects using the `list` command in the Home View.
By using the `assign` command, you can assign members to a project as shown below.

> :exclamation: The project must exist before it can be assigned a member.
> :exclamation: The member must exist before they can be assigned a project.

You can use the following format to assign a member to a specific project of your choice.
Format: `assign p/PROJECT_INDEX m/MEMBER_INDEX`
  
---
<div style="page-break-after: always;"></div>  

Example of usage: 

>  In the example shown below, the user assigns the first member from the list of members to the first project from the list of projects.
>  The user inputs the `m` parameter followed by the index of the member that the user wants to assign to a project.
>  The user then inputs the `p` parameter followed by the index of the project that the user wants to assign to a member.
>  Once the member has been added successfully, you can see an acknowledgement message being sent to the user.

```
assign m/1 p/1
____________________________________________________________
Tom assigned to Project "CS2113T"
```

>  :bulb: You can also input the `p` parameter first followed by the `m` parameter as shown below.

```
assign p/1 m/1
____________________________________________________________
Tom assigned to Project "CS2113T"
```

**Examples of Exception Handling for the `assign` command** 
Shown below are some examples of misuse of the `assign` command that you could potentially make as a user.

> In the example shown below, there are only 8 projects in the list of projects.
> If you try to assign a member to a project that does not exist, for example, assigning a member to the ninth project, you will be responded with an error message as shown below.
```
assign m/1 p/9
_____________________________________________________________________________________
Project ID does not exist!
```
> In the example shown below, there are only 3 members in the list of members.
> If you try to assign a project to a member that does not exist, for example, assigning a project to the fourth member, you will be responded with an error message as shown below.
```
assign m/4 p/1
_____________________________________________________________________________________
Team Member ID does not exist!
```
  
---
<div style="page-break-after: always;"></div>  

### 3.1.10. Removing a member: `remove` (Riaz Ahamed)
As a project manager, you would like to remove specific members from your team for various reasons.
Some reasons include; the member has been accidentally added to your team or the member is no longer working with you.
The `remove` command allows you to remove members from your team as shown below.

> :exclamation: The member must exist before they can be removed.

You can use the following format to remove a member from a specific project of your choice.
Format: `remove m/MEMBER_INDEX`

Example of usage: 

>  In the example shown below, the user removes the first member from the list of members.
>  The user uses the `m` parameter followed by the index of the member from the list of members.
>  Once the member has been removed successfully, you can see an acknowledgement message being sent to the user.

```
remove m/1
___________________________________________________________
Team member "Mike" has been removed from program entirely
```

**Examples of Exception Handling for `remove` command**
Shown below are some examples of misuse of the `remove` command that you could potentially make as a user.

> If you did not include the `m` parameter, you will be responded with an error message as shown below.
```
remove 2
Certain Parameters are missing!
```
> In the example shown below, there are only 2 members in the list of members. 
> If you specified a member that does not exist, for example, specifying a third member, you will be responded with an error message as shown below.
```
_____________________________________________________________________________________
remove m/3
_____________________________________________________________________________________
Team Member ID does not exist!
_____________________________________________________________________________________
```
  
---
<div style="page-break-after: always;"></div>  

### 3.1.11. View hours worked by member: `hours` (Shreyas Kumar)
As a project manager you might want to keep track of the number of hours each member in a project has contributed.
This command achieves just and allows you to view the total number of hours worked by a worker across all tasks assigned in all projects.

Now, you know which of your workers are overworked and be a better manager by shifting work to members who are more free!

> :exclamation: ​The member must exist before hours worked can be viewed.

Format: `hours m/MEMBER_INDEX`

Example usage and output:

```
hours m/1
-------------------------------
John worked for 2.5 hours.
```

**Examples of Exception Handling for this command**
> Providing an Invalid member index 
```
_____________________________________________________________________________________
hours m/3
_____________________________________________________________________________________
The member ID entered does not exist
_____________________________________________________________________________________
```    


### 3.1.12. Exiting EZ Manager: `bye` (Shreyas Kumar)
When you are done with your work for the day you can exit the program with the `bye` command and this returns
you back to the commmand line.

Format: `bye`
  
---
<div style="page-break-after: always;"></div>  

## 4. Project View (Shreyas Kumar)

The Project View displays the full list of tasks and members in a particular project.
The manager can add and edit tasks and assign members to tasks.



> ### :bulb: Accessing Project View
>
> This view is accessed by [selecting a project](#selecting-a-project-select) from the Home View.

![ProjectView4](https://i.ibb.co/Fq5gFjC/ss4.png)

---
<div style="page-break-after: always;"></div>  

## 4.1. Project View Commands

### 4.1.1. Viewing the updated Project View: `list` (Shreyas Kumar)
When you select a project you might want to view an overview of all the tasks along with their respective priorities and deadlines. You may also be interested in viewing a list of all members associated to the project and which tasks they are working on.
This command allows you to view the Project View of a project and is progressively updated everytime you add in a new task and assign in a member to the project.

Format: `list`

### 4.1.2. Adding a task: `task` (Shreyas Kumar)
Each project have their own activities and assignments associated with them. To record these activities this command allows you to create a new task and the program adds it to the task list.

Format: `task n/TASK_NAME`

> :warning: Task names should not include slashes or it will be disregarded.

Example of usage: 

>  Adds the task 'Deploy Version 2.0' to the task list.

```
task n/Deploy Version 2.0
____________________________________________________________
Task "Deploy Version 2.0" created!
```

**Examples of Exception Handling for this command**
> Providing a task name without n/ parameter
```
task task4
Certain Parameters are missing!
```     
  
---
<div style="page-break-after: always;"></div>  

### 4.1.3. Editing a task name: `edit` (Shreyas Kumar)
As you and your team porgess to working on the project you might want to make updates to an existing task name with the new name. This
commmand helps you achieve that.

Format: `edit t/TASK_INDEX n/NEW_TASK_NAME`

> :warning: Task names should not include slashes or it will be disregarded.  
> :warning: The `TASK_INDEX` must be a positive integer.

Example of usage: 

>  Edits the existing task, `Read documentation` to `Update documentation`.

```
task n/Read documentation
____________________________________________________________
Task "Read documentation" created!
____________________________________________________________
edit t/1 n/Update documentation
____________________________________________________________
Task "Read documentation" has been updated to "Update documentation"
```
**Examples of Exception Handling for this command** 
>Editing the name of an invalid task  

```
_____________________________________________________________________________________
edit t/4 n/task4
_____________________________________________________________________________________
Task ID does not exist!
_____________________________________________________________________________________

```  
  
---
<div style="page-break-after: always;"></div>  

### 4.1.4. Marking a task as done: `done` (Shreyas Kumar)
During your course of a project you can mark certain tasks as done and this command is used for that purpose. This command allows you to differentiate a task that is completed from a task that is still pending. This ultimately makes your management of a particular project more organised.

> :exclamation: The task must exist before it can be selected.

Format: `done t/TASK_INDEX`

Example of usage: 

> Marks the first task in the task list as done.  

```
done t/1 
____________________________________________________________
Task "Coding" is done!
```
**Examples of Exception Handling for this command** 
> Marking a task as done without the t/ parameter
```
done 1
Certain Parameters are missing!
```
> Providing task name instead of Project index
```
done t/"task1"
Index must be an integer!
```
> Marking an invalid task-ID as done  

```
_____________________________________________________________________________________
done p/8
_____________________________________________________________________________________
Project ID does not exist!
_____________________________________________________________________________________
```  
  
---
<div style="page-break-after: always;"></div>  

### 4.1.5. Adding a deadline to a task: `deadline` (Sean Tan)
In a typical software engineering project, implementation of various tasks are dependent on the
completion of other tasks. Easily add deadlines to your tasks with the deadline command. Now your team
can finish tasks on time and start work on further tasks. 

Simply specify the task followed by the deadline according to the format below.

Format: `deadline t/TASK_INDEX d/DATE`

> :warning: The `DATE` must be of the form `YYYY-MM-DD`
>
> :bulb: EZManager allows you to easily sort and view tasks by deadline. (See: Deadline Command)


* The task must exist before a deadline can be added.

Example of usage: 

>  Adds the deadline 25/10/2020 to the first task in the task list.

```
deadline t/1 d/2020-10-25
____________________________________________________________
Deadline 25/10/2020 added to Task Coding
```

**Examples of Exception Handling for this command**
> Providing a deadline to a task in in the DD-MM-YYYY format
```
deadline t/1 d/22-11-2020
Date must be specified in format YYYY-MM-DD
```
> Providing a deadline to a project in in the MM-DD-YYYY format
```
deadline t/1 d/11-27-2020
Date must be specified in format YYYY-MM-DD
```
  
---
<div style="page-break-after: always;"></div>  

### 4.1.6. Adding a priority to a task: `priority` (Sean Tan)
Over time, you discover new bugs add new features and your project starts to fill up with
tasks after tasks. Add priorities to tasks and ensure your team stays focused and work on the most important tasks first. 

All you have to do is specify the task index followed by the priority according to the format below.

> :bulb: 1 denotes the highest priority.
>
> :bulb: EZManager will allow you to easily sort and view these tasks by priority (See: Sort command).
>
> :exclamation: The task must exist before a deadline can be added.

Format: `priority t/TASK_INDEX p/PRIORITY`

:warning: The `PRIORITY` must be a positive integer.

Example of usage: 

> Adds the highest priority, 1, to the first task in the task list.

```
priority t/1 p/1
____________________________________________________________
Priority "1" has been assigned to "Coding"
```

**Examples of Exception Handling for this command**
> Setting priority to an invalid task-ID
```
priority t/4 p/1
_____________________________________________________________________________________
Task ID does not exist!
```

> Setting an invalid priority.
```
priority t/4 p/-1
_____________________________________________________________________________________
Invalid priority! Please input a positive integer for priority.
```  
  
---
<div style="page-break-after: always;"></div>  


### 4.1.7. Deleting a task: `delete` (Sean Tan)
As your project evolves, you might soon find certain tasks unnecessary or outdated. 

Easily delete a task from the task list with the delete command!

> :exclamation: The task must exist in the task list before it can be deleted.

Format: `delete t/TASK_INDEX`

Example of usage: 

> Deletes the first task in the task list.

```
delete t/1
____________________________________________________________
Task "Coding" removed!
```

**Examples of Exception Handling for this command**
> Delete a task with an invalid task-ID
```
_____________________________________________________________________________________
delete t/4 
_____________________________________________________________________________________
Task ID does not exist!
_____________________________________________________________________________________
```

### 4.1.8. Assigning a member to a task: `assign` (Sean Tan)
Easily delegate work to your team members with the assign commmand! 

Now each member knows exactly what they have to do and not be overwhelmed with the full list of tasks in a project. 

Besides, EZManager allows you to assign multiple members to tasks so they can discuss and collaborate with team members 
that are assigned the same task as them! 


> :exclamation: Members must belong to a project before they can be assigned tasks.

Format: `assign t/TASK_INDEX m/MEMBER_INDEX`

* The `TASK_INDEX` must be a positive integer.
* The `MEMBER_INDEX` must be a positive integer.
* The task must exist before it can be assigned a member.

Example of usage: 

> Assigns the first member in the member list to the first task in the task list

```
assign m/1 t/1
____________________________________________________________
Member "Tom" has been assigned to "Code Review"
```

**Examples of Exception Handling for this command**
> Assigning a member to a task that does not exist
```
assign m/1 t/11
_____________________________________________________________________________________
Task ID does not exist!
```
> Assigning a member to a task who is not assigned to the respective project 
```
assign m/4 t/1
_____________________________________________________________________________________
Team Member ID does not exist!
```  


### 4.1.9. Removing a member: `remove` (Sean Tan)
If you decide to remove a member from a project, it's incredibly simple! 

Easily remove a member from a project with the remove command. The member will continue to be part of your members list in
the Home View so you can assign him/her to another project instead.

> :exclamation: The member must exist before they can be removed.

Format: `remove m/MEMBER_INDEX`

Example of usage: 

>  Removes the first member from the member list.

```
remove m/1
___________________________________________________________
Team member "Mike" has been removed from Project "CS2113T"
```  
  
---
<div style="page-break-after: always;"></div>    

**Examples of Exception Handling for this command**
> Removing a team member without the m/ parameter
```
remove 2
Certain Parameters are missing!
```
> Removing a team member that was not assigned to the respective project
```
_____________________________________________________________________________________
remove m/3
_____________________________________________________________________________________
Team Member ID does not exist!
_____________________________________________________________________________________
```



### 4.1.10. Add estimated time: `estimate` (Samuel Paul Christopher)
As a project manager, you need to ensure that your projects are run profitably. This means that projects cannot 
over-run the timeline that has been agreed with your stakeholders. To ensure that time is spend well, you can add
estimate the amount of time that should be spent on a task. This ensures that your team will have a rough gauge on how
much time they can spend on a task before it becomes unprofitable. 

Apart from maintaining profitability, this allows you to catch red flags early on when tasks require more time than 
what has been forecasted. This gives you ample time to communicate foreseeable delays with your clients and ensure  that 
unwanted surprises are minimised throughout your project management journey.

To add an estimated time, you can use the format shown below.

Format: `estimate t/TASK_INDEX h/HOURS m/MINUTES`

> :warning:  `HOURS` and `MINUTES ` must both be positive numbers.
>


Example usage and output:

```
estimate t/1 h/12 m/30
____________________________________________________________
Task "New Task" has estimated time of 12 hours and 30 minutes

```  
  
---
<div style="page-break-after: always;"></div>  

**Examples of Exception Handling for this command**
> Assigning a time period to a task that does not exist
```
estimate t/11 h/6 m/30
_____________________________________________________________________________________
Task ID does not exist!
```


### 4.1.11. Add actual time taken: `actual` (Samuel Paul Christopher)
Expectations are different from reality. You may set out to finish a task in one hour but it might end up taking twice 
that much time. Having a record of the actual time that your team members spent on a task is invaluable. This opens up 
the opportunity for you to gauge timelines better in the future. Ensuring that your projects remain profitable.

Here is best part. You can use the records of actual time taken to facilitate reviews with your team. Allowing them to 
learn about the difference between perceived and actual effort needed for tasks.


To add the actual time taken for task to be completed, the format below can be used.

> :exclamation: Task must be marked as done before actual time taken can be added.

Format: `actual t/TASK_INDEX h/HOURS m/MINUTES`

> :warning:  `HOURS` and `MINUTES   `  must both be positive numbers.


Example usage and output:

```
done t/1
actual t/1 h/12 m/30
____________________________________________________________
Task "New Task" took 12 hours and 30 minutes to be completed.

```
**Examples of Exception Handling for this command**
> Assigning a time period to a task that does not exist
```
actual t/11 h/5 m/20    
_____________________________________________________________________________________
Task ID does not exist!
```

### 4.1.12. Sort tasks: `sort` (Samuel Paul Christopher)

What do you do when you start a week? A meticulous project manager like yourself always plans ahead. The most difficult part
is determining where you and your team should spend their energy. With this sorting feature, you can determine which
tasks you need to focus on at a glance. Simply sort tasks by priority, deadline or actual time taken.

Here are a few things you can take note of:  
:bulb: Highest priority of 1 will be displayed at top.

:bulb: Earliest deadline will be displayed at top.

:bulb: Shortest actual time will be displayed at top.

:bulb: Shortest estimated time will be displayed at top.

Format: `sort s/SORTING_TYPE`

* Sorting type `a` refers to actual time, `e` refers to estimated time,  `p` refers to priority and `d` refers to deadline.

Example usage and output:

```
sort s/d
____________________________________________________________
Task List sorted based on deadline
```
**Examples of Exception Handling for this command** 
> Using the sort functionality with the s/ parameter
```
_____________________________________________________________________________________
sort p
Certain Parameters are missing!
_____________________________________________________________________________________
```  
  
---
<div style="page-break-after: always;"></div>    

### 4.1.13. Exiting EZ Manager: `bye` (Samuel Paul Christopher)

After a hard day's work, you can end your session with EZ Manager using the bye command. Fret not, when you are ready to
resume work, EZ Manager will automatically load the previous data into your session so that you can pick up where you 
left off.

Format: `bye`

Note: If you use `Ctrl + C` to end your EZ Manager session, we have made the provisions to still ensure that your data 
is saved and ready for you when you have recharged.

## 5. FAQ (Samuel Paul Christopher)

EZ Manager is here to make your life a little bit easier. Here are the most common questions that we get, we hope it 
answers some of the questions that you may have not found answers to yet.

**Q**: Does the program saves the data entered when I terminate the program abruptly? 

**A**: Yes, the program saves the data entered after every command which allows the data to be stored 
even if the program is terminated abruptly.

**Q**: How do I transfer my data to another computer? 

**A**: Simply transfer the ezmanager.txt file to another computer and run our program once to load the data.

**Q**: How long does it take to get up and running? 

**A**: Once you have the JAR file downloaded, you can run `java -jar ezmanager.jar` in the terminal 
at the specific folder the JAR file is stored and you have begun.    

---
<div style="page-break-after: always;"></div>    


## 6. Command Summary

Home View Commands
![Command Summary One](https://i.ibb.co/GMgn96K/cs1.png)

Project View Commands
![Command Summary Two](https://i.ibb.co/P4z4vV4/cs2.png)
