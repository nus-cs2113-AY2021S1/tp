# User Guide
## Setting Up
Prerequisites : [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* Refer to [Setting Up](https://github.com/AY2021S1-CS2113T-F11-4/tp/blob/master/README.md#setting-up-in-intellij) guide for instructions to set up the
 project in IntelliJ.
 
* Download the [JAR file](https://github.com/AY2021S1-CS2113T-F11-4/tp) 
 and run the command `java -jar tp.jar`


## Features 
### Project `project`
#### Viewing project information
View project information which includes title, description, time-period and team members of the project. 
It displays the project backlog, and the details of the ongoing sprint.
* Format: `project /view`
* Usage command: `project/view`
* Constraints:
    * A project must be created by the user before using this command.
* Expected outcome:
    ```
    ================= PROJECT =================
    [Title: MeTube ]
       [Description: video streaming software ]
       [No members added]
       [Project will start along with the first sprint]
       [Project backlog is empty]
       [There are no Sprints]
    ===============================================
    ```
    
#### Create project
Create a new project.
* Format: `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>`
* Usage command: `project /create -title MeTube -desc video streaming software -dur 90 -sd 10`
    * Creates a project titled MeTube which is a video streaming software. 
    * The project will last for 90 days and the sprints will run on a 10 day interval.

* Constraints:
    * Duration and sprint interval must be integers.
    * All the fields must hava an input, else a project is not added.
* Expected outcome:
    ```
    Project successfully created.
    ================= PROJECT =================
    [Title: MeTube ]
       [Description: video streaming software ]
       [No members added]
       [Project will start along with the first sprint]
       [Project backlog is empty]
       [There are no Sprints]
    ===============================================
    ```
    
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
Assuming the third task have the following attributes: Title: `Add parser`, Description: `add UI`, Priority: `HIGH`, Done: `false`:
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
Assuming the tasks have the following titles, respectively: `Add parser`, `Del UI` and `UI`:
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
* Expected outcome:
```
The task Add parser has its priority changed to:
    High priority
```
#### Marking task as complete


      
