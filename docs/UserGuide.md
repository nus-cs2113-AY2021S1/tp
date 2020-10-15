
# User Guide  
  
## Introduction  
  
SCRUMptious is a command line software for project management designed for project managers following the scrum methodology to develop it.   
  
## Table of Contents  
  
1. Quick Start  
1. Features  
   1. Project `project`  
 1. Create a new project  
       1. View project information  
       1. Add team members  
       1. Remove team members  
   1. Project Backlog `task`  
 1. Create backlog items  
       1. View backlog items  
       1. Add tasks  
       1. View tasks  
       1. Delete tasks  
       1. Change the priority of tasks  
       1. Mark tasks as complete  
   1. Sprint `sprint`  
 1. Create a new sprint  
       1. View sprint information  
       1. Add tasks to the sprint  
       1. Delete tasks from the sprint  
       1. Allocate tasks to team members  
1. Command Summary  
  
  
## Quick Start  
  
1. Ensure that you have Java 11 or above installed.  
1. Down the latest version of `Scrumptious.jar` from [here](http://link.to/duke)  
1. Copy the jar file into an empty folder.  
1. Ensure that you have a functional Command Line Interface (CLI).  
1. Run the command `java -jar C:\Scrumptious\Scrumptious.jar`. Ensure that the `Scrumptious.jar` filepath is specified   
correctly according to where you copied it to.  
1. Type the command `project /create -title Scrumptious -desc A java project -end 30 -sd 10` into the command line and press `Enter` to execute it.  
1. If te setup is correct, you should see something like this:  
    ```  
 Project successfully created. ```  
## Features   
### Project `project`  
#### Create Project  
Create a new project    
* Format: `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>` * Tags:    
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
 ================= PROJECT ================= [Title: MeTube ] [Description: video streaming software ] [No members added] [Project will start along with the first sprint] [Project backlog is empty] [There are no Sprints]     ===============================================  
 ```  #### Add team members into project `member`  
Add team members into the project.    
* Format: `member /add <username> [<username> ...]`  
* Constraints:  
  * At least one username must be specified  
  * Username must be an alphanumeric String  
* Example: `member /add john mary`  
* Expected outcome:  
    ```  
 john has been added to the project. mary has been added to the project. ```  
  
#### Remove team members from project Remove team members from the project  
* Format: `member /del <username> [<username> ...]`  
* Constraints:  
  * At least one username must be specified  
  * Username must be an alphanumeric String  
* Example: `member /del john mary`  
* Expected outcome:  
    ```  
 john has been removed from the project. mary has been removed from the project. ```  ### Project Backlog `task`  

    
### Project Backlog (All features) `task`
#### Adding task
Adds a task to the backlog.
* Format: `task /add -title <title> -desc <description> -priority <category>`
* Usage command: `task /add -title Add UI -desc add an interactive UI -priority HIGH`
    * Adds a task of title `Add UI`, description `add an interactive UI` and priority `HIGH` into the backlog.
    
* Constraints:
    * The title, description and category must be specified, otherwise the task is not added.
    * The priority entered must belong to the standard options as prescribed by the enum: [“HIGH”, “MEDIUM”, “LOW”]
    * The task will be added as "not done" status. Users need to manually mark a task as done after creation.
    * Tasks can only be created if a project has been created.
* Expected outcome:
`Add UI has been added.`

#### Viewing task
Views the specified task.
* Format: `task /view <taskid>`
* Usage command: `task /view 3`
    * Views the third task.
    
* Constraints:
    * The task ID entered must be a positive integer smaller or equal to the total number of tasks added, and must be specified.
* Expected outcome:
(Assuming the third task have the following attributes: Title: `Add parser`, Description: `add UI`, Priority: `HIGH`, Done: `false`)
```
[Task]
    ID: 1
    Add parser 
    Description: add an interactive UI 
    Priority: High priority
    Completion: Completed
 ```
#### Deleting task
Deletes the specified task.
* Format: `task /del <taskid> [<taskid>...]`
* Usage command: `task /del 5 7 9`
    * Deletes the fifth, seventh and ninth task.

* Constraints:
    * There must be at least one supplied task ID for deletion.
    * All task ID supplied must be a positive integer smaller or equal to the total number of tasks added.
* Expected outcome:
(Assuming the tasks have the following titles, respectively: `Add parser`, `Del UI` and `UI`)
`The corresponding task Add parser has been removed from project.`
`The corresponding task Del UI has been removed from project.`
`The corresponding task UI has been removed from project.`

#### Changing the priority of task
Change the priority of the specified task.
* Format: `task /priority -priority <category> -id <taskid>`
* Usage command: `task /priority -priority HIGH -id 1`
    * Sets the first task with a priority of HIGH, regardless of its previous priority level
    
* Constraints:
    * Task ID and priority level must be entered.
    * The Task ID entered must be a positive integer smaller or equal to the total number of tasks added.
    * The priority entered must belong to the standard options as prescribed by the enum: [“HIGH”, “MEDIUM”, “LOW”]
* Expected outcome: (Assuming task 1 has title `Add parser`)
```
The task Add parser has its priority changed to:
    High priority
```
#### Marking task as complete
Marks specified task as complete.
* Format: `task /done <taskid>`
* Usage Command: `task /done <taskid>`
    * Marks the first task as done.
* Constraints:
    * The Task ID must be entered.
    * The Task ID entered must be a positive integer smaller or equal to the total number of tasks added.
* Expected outcome: (Assuming task 1 has title `Add parser`)
`Add parser has been marked as done.`
  ### Sprint `sprint`  
#### Create a new sprint  
Creates a new sprint for the project.  
* Format: `sprint /create -goal <goal> -start <DDMMYYYY>`  
* Constraints:  
  * `goal` must be specified  
* Example: `sprint /create Shopping Cart`  
* Expected outcome:  
    ```  
     ```  
  #### View the sprint  
Display the information of the sprint.  
* Format: `sprint /view`  
* Example: `sprint /view`  
* Expected outcome:  
    ```  
     ```  
  #### Add tasks to the sprint  
Adding the specified task(s) from the backlog items to the current sprint  
* Format: `sprint /addtask <task id> [<task id> …]`  
* Constraints:  
  * `task id` must be a positive integer  
  * `task id` must be specified  
* Example: `sprint /addtask 123 456 789`  
* Expected outcome:  
    ```  
     ```  
  #### Delete tasks from the sprint  
Removing the specified task(s) from the sprint  
* Format: `sprint /deltask <task id> [<task id> …]`  
* Constraints:  
  * `task id` must be a positive integer  
  * `task id` must be specified  
* Example: `sprint /deltask 123 456 789`  
* Expected outcome:  
    ```  
     ```  
  #### Allocate tasks to team members  
Assigning a task to a team member  
* Format: `sprint /assign <task id> <user id> [<user id> …]`  
* Constraints:  
  * `task id` must be a positive integer  
  * `task id` must be specified  
  * `user id` must be specified  
* Example: `sprint /assign 123 johntan mary jane`  
* Expected outcome:  
    ```  
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