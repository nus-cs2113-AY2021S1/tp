# User Guide

## Introduction

PlaNUS is a desktop application for NUS students to manage their tasks and schedule, 
optimized for use via Command Line Interface (CLI). We aim for PlaNUS to become a 
one stop location for students to be able to view all their commitments and assignments 
and prioritize their work and commitment with a user-friendly text interface.

- [Quick start](#quick-start)
- [Features](#features)
    - [Adding Todo](#adding-a-task--add)
    - [Displaying tasks](#displaying-tasks--list)
    - [Removing task](#removing-a-task--delete)
    - [Clearing task](#clearing-tasks--clear)
    - [Searching task](#searching-relevant-tasks--search)
    - [Exiting program](#exiting-program--bye)
- [FAQ](#faq)
- [Command summary](#command-summary)


## Quick start


1. Ensure that you have Java 11 or above installed.

2. Download the latest "Duke.jar" from [here](https://github.com/AY2021S1-CS2113T-W12-1/tp/releases/download/v1.0/planus.jar).

3. For first time Windows user, open command prompt and run the following line:

    `reg add HKCU\Console /v VirtualTerminalLevel /t REG_DWORD /d 1`
    
4. Open a **new** command prompt/terminal and run "Duke.jar" with following command:

    `java -jar planus.jar`

5. Type a command in command prompt/terminal and press Enter to execute it.

6. Refer to the [Features](#features) below for details of each command.


## Features 


### Notes about command format:
- Words in UPPER_CASE are the parameters to be supplied by the user. 
    - e.g. in "add DESCRIPTION", DESCRIPTION is a parameter which can be used 
      as "todo user guide".
      
- Parameter in square bracket is optional. 
    - e.g. in "add DESCRIPTION [d/DATE] [st/START_TIME] [et/END_TIME] [p/PRIORITY]", 
    DATE, START_TIME, END_TIME and PRIORITY are optional parameters which can be omitted.
    
- Input date format is dd-MM-yyyy.
    - e.g. 20/02/2020
      
- Input time format is HHmm in 24-hrs.
    - e.g. 1830
    
- Input priority format is 1, 2 or 3 represents LOW, MEDIUM and HIGH respectively.
    - e.g. 1

- Index of a task is the number displayed after "#" and before description of the task.
    - e.g. "#1029 meeting", 1029 is the index.

### Adding a task : `add`

Add a task to the task list and display a message.
Default date will be the day when the tasked is added.
Default priority is low.

Format: `add DESCRIPTION [d/DATE] [st/START_TIME] [et/END_TIME] [p/PRIORITY]`

Example of usage: 

`add borrow book`
`add meeting st/1000`

Expected outcome:


### Editing a task : `edit`

Edit a task in the task list using index and display a message.

Format: `edit INDEX [des/DESCRIPTION] [d/DATE] [st/START_TIME] [et/END_TIME] [p/PRIORITY]`

Example of usage: 

`edit 128 des/borrow book`
`edit 2389 st/1400 et/1600 p/3`

Expected outcome:



### Removing a task : `delete`

Delete a task from the task list.

Format: `delete INDEX`

Example of usage: 

`delete 2`

Expected outcome:



### Clearing tasks : `clear`

Delete a task from the task list.

Format: `clear`

Example of usage: 

`clear`

Expected outcome:



### Displaying tasks : `list` 

Display all tasks in the list.

Format: `list`

Example of usage: 

`list`

Expected outcome:




### Searching relevant task(s) : `search`

Search and display task(s) with given keyword, this feature is case-insensitive.

Format: `search KEYWORD`

Example of usage: 

`search Report`

Expected outcome:



### Exiting program : `bye`

Display a message and exits the program.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:


	
### Saving data to file

Data will be automatically saved after modification commands like 
`add`, `delete`, `edit` and so forth.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the existing data file to the computer, then create a "data" folder under the 
same folder as "planus.jar". Lastly, copy the data file to "data" folder and name it "data.json".

## Command Summary

Action | Format | Example
------ | ------ | -------
add | `add DESCRIPTION` | `add user guide`
edit |
delete | `delete INDEX` | `delete 2`
clear |
search | `search KEYWORD` | `search meet`
list | `list` | `list`
bye | `bye` | `bye`
