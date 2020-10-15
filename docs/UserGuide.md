# User Guide

## Introduction

SCRUMptious is a command line software for project management designed for project managers following the scrum methodology to develop it. 

## Table of Contents

1. [Quick Start](#quick-start) 
1. [Features](#features) 
    1. [Project `project`](#project-project)
        1. [Create a new project](#create-project)
        1. [View project information](#view-project-information)
        1. [Add team members](#add-team-members-into-project-member)
        1. [Remove team members](#remove-team-members-from-project)
    1. [Project Backlog `task`](#project-backlog-task)
        1. [Add tasks](#add-tasks)
        1. [View tasks](#view-tasks)
        1. [Delete tasks](#delete-tasks)
        1. [Change the priority of tasks](#change-priority-of-tasks)
        1. [Mark tasks as complete](#mark-tasks-as-complete)
    1. [Sprint `sprint`](#sprint-sprint)
        1. [Create a new sprint](#create-a-new-sprint)
        1. [View sprint information](#view-the-sprint)
        1. [Add tasks to the sprint](#add-tasks-to-the-sprint)
        1. [Delete tasks from the sprint](#delete-tasks-from-the-sprint)
        1. [Allocate tasks to team members](#allocate-tasks-to-team-members)
1. [Command Summary](#command-summary)


## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `Scrumptious.jar` from [here](http://link.to/duke)
1. Move the jar file into an empty folder.
1. Ensure that you have a functional Command Line Interface (CLI).
1. Run the command `java -jar C:\Scrumptious\Scrumptious.jar`. Ensure that the `Scrumptious.jar` filepath is specified 
correctly according to where you copied it to.
1. Type the command `project /create -title Scrumptious -desc A java project -end 30 -sd 10` 
into the command line and press `Enter` to execute it.
1. If the setup is correct, you should see something like this:
    ```
   Project successfully created.
   ```

## Features 

### Project `project`
#### Create Project
Create a new project  
* Format: `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>`  
* Tags:  
    * `-title`: Specify the title of the project
    * `-esc`: Specify the project description
    * `dur`: Specify the duration of the project. (Number of days)
    * `-sd`: Specify the duration of a sprint. (Number of days)
* Constraints: All tags are to be specified when creating a new project.
* Example: `project /create -title MeTube -desc video streaming software -dur 90 -sd 10`
    * Creates a project titled MeTube which is a video streaming software. 
    * The project will last for 90 days and the sprints will run on a 10 day interval.

#### View project information
View project information which includes title, description, time-period and team members of the project. 
It displays the project backlog, and the details of the ongoing sprint.
* Format: `project /view`
* Constraints:
    * A project must be created by the user before using this command.
* Example: `project /view`
* Expected outcome:
     ```
    ====================== PROJECT ======================
    [Title: MeTube ]
    [Description: video streaming software ]
    [No members added]
    [Project will start along with the first sprint]
    [Project backlog is empty]
    [There are no Sprints]
    =====================================================
    ```
  
#### Add team members into project `member`
Add team members into the project.  
* Format: `member /add <username> [<username> ...]`
* Constraints:
    * At least one username must be specified
    * Username must be an alphanumeric String without spaces
* Example: `member /add john mary`
* Expected outcome:
    ```
  john has been added to the project.
  mary has been added to the project.
  ```


#### Remove team members from project 
Remove team members from the project
* Format: `member /del <username> [<username> ...]`
* Constraints:
    * At least one username must be specified
    * Username must be an alphanumeric String
* Example: `member /del john mary`
* Expected outcome:
    ```
  john has been removed from the project.
  mary has been removed from the project.
  ```
  
### Project Backlog `task`
#### Add tasks
Adds a task to the backlog.
* Format: `task /add -title <title> -desc <description> -priority <category>`
* Tags:
    * `-title`: Specify the task title
    * `-desc`: Specify the task description
    * `-priority`: Specify the task priority as HIGH, MEDIUM or LOW.
* Constraints: All tags are to be specified when adding a new task.
* Example: `task /add -title Add parser -desc add an interactive UI -priority HIGH`
* Expected outcome:
    ```
    
    ```

#### View tasks
Views the specified task.
* Format: `task /view <taskid>`
* Constraints:
    * `task id` must be a positive integer
    * `task id` must be specified
* Example: `task /view 3`
* Expected outcome:
    ```
    
    ```
  
#### Delete tasks
Deletes the specified task.
* Format: `task /del <task id> [<task id>...]`
* Constraints:
    * `task id` must be a positive integer
    * `task id` must be specified
* Example: `task /del 5 7 9`
* Expected outcome:
    ```
    
    ```
#### Change priority of tasks
Change the priority of the specified task.
* Format: `task /priority -priority <category> -id <task id>`
* Constraints:
    * `task id` must be a positive integer
    * `task id` must be specified
    * `-category` must be HIGH, MEDIUM or LOW.
* Example: `task /priority -priority HIGH -id 4`
* Expected outcome:
    ```
    
    ```
  
#### Mark tasks as complete
Marks specified task as complete.
* Format: `task /done <task id>`
* Constraints:
    * `task id` must be a positive integer
    * `task id` must be specified
* Example: `task /done 3`
* Expected outcome:
    ```
    
    ```
  
### Sprint `sprint`
#### Create a new sprint
Creates a new sprint for the project.
* Format: `sprint /create -goal <goal_input> -start <start_date>`
* Tags:
    * `-goal`: Specify the goal for the sprint
    * `-start`: Specify the start date of the sprint (Only used for the first sprint)
* Constraints:
    * `goal_input` must be specified
    * `start_date` will only be accepted in `YYYYMMDD` format
* Optional field:
    * `start_date` will be set as the date of command execution if not specified
* Example: `sprint /create Shopping Cart -start 20201010`
* Expected outcome:
    ```
    Project will start along with the newly created sprint
    Project period: 2020-10-10 to 2020-11-08
    
    ============================ SPRINT =============================
    [ID: 1]
    [Goal: Shopping Cart ]
    [Period: 2020-10-10 - 2020-10-19] 
    [No allocated tasks]
    =================================================================
    ```
  
#### View the sprint
Display the information of the current sprint.
* Format: `sprint /view`
* Example: `sprint /view`
* Expected outcome:
    ```
    ========================= CURRENT SPRINT ========================
    [ID: 1]
    [Goal: Shopping Cart ]
    [Period: 2020-10-10 - 2020-10-19] 
    [Remaining: 4 days]
    [No allocated tasks]
    =================================================================
    ```
  
#### Add tasks to the sprint
Add task(s) from the project backlog to the current sprint
* Format: `sprint /addtask <task_id> [<task_id> ...]`
* Constraints:
    * `task_id` must be a positive integer
    * `task_id` must be specified (1 or more)
* Example: `sprint /addtask 1 3 4`
* Expected outcome:
    ```
    DummyTask1  added to sprint.
    DummyTask3  added to sprint.
    DummyTask4  added to sprint.
    ```
  
#### Delete tasks from the sprint
Removing the specified task(s) from the current sprint
* Format: `sprint /deltask <task_id> [<task_id> ...]`
* Constraints:
    * `task_id` must be a positive integer
    * `task_id` must be specified (1 or more)
* Example: `sprint /deltask 1 3 4`
* Expected outcome:
    ```
    DummyTask1 removed from sprint.
    DummyTask3 removed from sprint.
    DummyTask4 removed from sprint.
    ```
  
#### Allocate tasks to team members
Assigning a task to a team member
* Format: `sprint /assign -task <task_id> -user <user_id> [<user_id> …]`
* Tags:
    * `-task`: Specify the task to be allocated
    * `-user`: Specify the user(s) to be assign with a task
* Constraints:
    * `task_id` must be a positive integer
    * `task_id` must be specified
    * `user_id` must be specified (1 or more)
* Example: `sprint /assign -task 1 -user johntan mary jane`
* Expected outcome:
    ```
    DummyTask1  assigned to [johntan, mary, jane]
    ```

## Command Summary

| **Action** | **Format** | **Examples** |
| --- | --- | --- |
| Create | project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval> | `project /create -title MeTube -desc video streaming software -dur 90 -sd 10` |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |


      