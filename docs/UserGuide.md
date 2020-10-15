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
    ===============================================```
    
#### Create project
Create a new project.
* Format: `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>`
* Usage command: `project /create -title MeTube -desc video streaming software -dur 90 -sd 10`
    * Creates a project titled MeTube which is a video streaming software. 
    * The project will last for 90 days and the sprints will run on a 10 day interval.

* Constraints:
    * Duration and sprint interval must be integers.
    * All the fields must hava an input, else a project is not added.
* Expected outcome:```
    
    Project successfully created.
    ================= PROJECT =================
    [Title: MeTube ]
    [Description: video streaming software ]
    [No members added]
    [Project will start along with the first sprint]
    [Project backlog is empty]
    [There are no Sprints]
 
    ===============================================```


### Delete `Task`
Removes a `task` from the task manager.

### Find `Task`
Find `task` containing specified keyword in its description.

### List `Task`
List all the `task` contained in the task manager.

### Mark `task` done
Mark the specified `task` in task manager as done.

### Exit
Exit the application.

### Store `task` 
Stores the `task` updated in task manager in a `.txt` file.

## Usage
| :memo:         | Words in `<userInput>` are parameters.|
|----------------|---------------------------------------|
### Adding a `Deadline` Task - `deadline`
Adds a `task` which has to be completed by the mentioned deadline.
* Format: `deadline <description> /by <duedate>`
* Constraints:
  * The description cannot be empty
  * Follow the ISO (`YYYY-MM-DD HH:MM`) for `<duedate>`
* Usage command: 
  * `deadline finish assignment /by 2021-08-10 11:59`
* Expected outcome:
    ```
    ------------------------------------------------------------
        Got it. I've added this to your custom-list: 
            [D][✘] finish assignment (by: Aug 10 2021 11:59:00)
        Now you have 1 tasks in your list :)
    ------------------------------------------------------------
    ```
  
### Adding an `Event` Task - `event`
Adds a `task` with an attached timing/venue.
* Format: `event <description> /by <datetime>`
* Constraints:
  * The description cannot be empty.
  * Follow the ISO (`YYYY-MM-DD HH:MM`) for `<datetime>`
* Usage command: 
  * `event attend class /at 2021-08-10 12:00`
  * `event attend meeting /at University Town`
* Expected outcome:
    ```
    ------------------------------------------------------------
        Got it. I've added this to your custom-list: 
            [T][X] attend class (at: Aug 10 2021 12:00:00)
        Now you have 2 tasks in your list :)
    ------------------------------------------------------------
    ```
  
### Adding a `ToDo` Task - `todo`
Adds a `task` with to-do description.
* Format: `todo <description>`
* Constraints:
  * The description cannot be empty
* Usage command: 
  * `todo get groceries`
* Expected outcome:
    ```
    ------------------------------------------------------------
        Got it. I've added this to your custom-list: 
            [T][X] get groceries
        Now you have 1 tasks in your list :)
    ------------------------------------------------------------
    ```
  
### List `task`: `list`
List all the tasks currently stored in Task Manager
* Usage: `list` 
* Expected outcome:
    ```
    ------------------------------------------------------------
    Here are the tasks in your list:
    1. [D][X] finish assignment (by: Aug 10 2021 11:59:00)
    2. [E][X] attend class (at: Aug 10 2021 12:00:00)
    3. [T][X] get groceries
    4. [T][X] do class work
    ------------------------------------------------------------
    ```
  
### Mark `task` as done: `done`
Marks a specified `task` as done. `list` can be used to find index of the relevant `task`. 
* Format: done <taskNumber>
* Constraints
  * taskNumber must be within the limits of tasks stored in the task manager.
  * taskNumber should be positive.
* Usage command: 
  * `done 2` 
* Expected outcome:
    ```
    ------------------------------------------------------------
    Got it. I've marked this task as done: 
        [E][✓] attend class (at: Aug 10 2021 12:00:00)
    ------------------------------------------------------------
    ```

### Delete `task` : `delete`
Remove a specified `task` from the task manager. `list` can be used to find index of the relevant `task`.
* Format: delete <taskNumber>
* Constraints
  * taskNumber must be within the limits of tasks stored in the task manager.
  * taskNumber should be positive.
* Usage command: 
  * `delete 3` 
* Expected outcome:
    ```
	------------------------------------------------------------
	Noted. I've removed this task:
		[T][✓] get groceries
	Now you have 1 tasks in your list :)
	------------------------------------------------------------
    ```
  
### Find `task` : `find`
Find all `task` in task manager containing specified keyword in its description.
* Format: find <keyword>
* Constraints:
  * If no matching task found, returns a no matching statement.
* Usage command:
  * `find class`
* Expected outcome:
    ```
  	------------------------------------------------------------
  	Here are matching tasks in your list:
  		1. [E][✓] attend class (at: Aug 10 2021 12:00:00)
            2. [T][X] do class work
  	------------------------------------------------------------
    ```
  
### Exit Application: `bye`
Exits the application and prints a exit message, indicating the number of tasks not marked as done in task manager.
* Usage command:
  * `bye`
* Expected outcome:
    ```
    ------------------------------------------------------------
    Bye! Dobby is now free!
    You currently have 1 tasks left
    Happy to help you organize work. Anywhere, anytime!
    ------------------------------------------------------------  
    ```
## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the application in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Dobby -Task Manager. The filename is "data.txt" and can be located under data directory.

## Command Summary

Action | Format, Examples
--------|------------------
**Add todo task** | `todo <description>` <br> Example: `todo buy groceries`
**Add deadline task** | `deadline <description> /by <duedate>` <br> Example: `deadline assignment /by 2020-08-16 23:59`
**Add event task** | `event <description> /at <duedate>` <br> Example: `event lunch /at 2021-05-22 12:00`
**List tasks** | `list`
**Mark as done** | `done <taskNumber>`<br> Example: `done 2`
**Delete task** | `delete <taskNumber>`<br> Example: `delete 6`
**Find with keyword** | `find <keyword>`<br> Example: `find class`
**Exit application** | `bye`
      