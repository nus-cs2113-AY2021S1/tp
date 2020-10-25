# User Guide

## Introduction

_ModTracker_ is a desktop app for tracking the time spent on your modules, via a Command Line Interface (CLI).
It helps you to prioritise your work and balance your time spent among your modules. 


## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    1. [View help: `help`](#1-view-help-help)
    1. [Add a module: `addmod`](#2-add-a-module-addmod)
    1. [Add expected module workload: `addexp`](#3-add-expected-module-workload-addexp) 
    1. [Add actual time spent on a module: `addtime`](#4-add-actual-time-spent-on-a-module-addtime)
    1. [List expected and actual time spent of all modules: `list`](#5-list-expected-and-actual-time-spent-of-all-modules-list)
    1. [Analyse actual time spent on all modules: `analyse`](#6-analyse-actual-time-spent-on-all-modules-analyse)
    1. [Delete a module: `deletemod`](#7-delete-a-module-deletemod)
    1. [Delete expected module workload: `deleteexp`](#8-delete-expected-module-workload-deleteexp)
    1. [Minus from actual time spent on a module: `minus`](#9-minus-from-actual-time-spent-on-a-module-minus) 
    1. [Delete actual time spent on a module: `deletetime`](#10-delete-actual-time-spent-on-a-module-deletetime)
    1. [Add tasks: `addtask`](#11-add-tasks-addtask)
    1. [List all tasks: `listtask`](#12-list-all-tasks-listtask)
    1. [Mark task as done: `done`](#13-mark-task-as-done-done)
    1. [Delete a task: `deletetask`](#14-delete-a-task-deletetask)
    1. [Open notification: `open`](#15-open-notification-open)
    1. [Exit the program : `exit`](#16-exit-the-program--exit)
    1. [Save user data](#17-save-user-data)
    1. [Delete user data: `reset`](#18-delete-module-and-task-data-reset)
* [FAQ](#faq)
* [Command Summary](#command-summary)


## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `ModTracker` from [here](https://github.com/AY2021S1-CS2113T-F12-4/tp/releases).
1. Save the jar file to a folder of your choice.
1. Open a command prompt and navigate to the folder that contains the jar file. Command: `cd [directory/filename]`
1. Type in the `java -jar modtracker.jar` command and press enter to run the program.
1. A welcome screen as shown below will be displayed if the program 
has successfully executed.

   ````
   Hello from
   |\\        /|         |======            ||
   ||\\      / |  __   __|  ||  __  ___ ___ ||    ___   ____
   || \\    /  |//  \//  |  ||//  \/  |/    ||// / _ \ //   \
   ||  \\  /   |||   ||  |  |||   ||  ||    ||\\ | __/ ||
   ||   \\/    |\\__/\\__|  |||   \\__|\___ || \\\___| ||
   *****************************************************|
   
   Full user guide available at: https://ay2021s1-cs2113t-f12-4.github.io/tp/
   Enter <help> for a quick view of available commands.
   
   What is your name?
   ````
1. Refer to the Features section below for more details on the application's available commands.


## Features 

### Notes:
1. Items in <> are the parameters to be supplied by the user.
   e.g. in addmod <module code>, module code is a parameter which can be used as addmod CS2113T.
1. The module code is valid if it contains 6-8 characters without any spacing. 

### 1. View help: `help` <a name="1-view-help-help"></a>
Shows a list of the commands available.

Format: `help`

Expected output:
````
<A list of available commands will be shown here>
````

### 2. Add a module: `addmod` <a name="2-add-a-module-addmod"></a>
Adds a module to the database.

Format: `addmod <module code>`

* The `module code` is valid. 
* The `module code` does not exist in the database.

Example of usage:

`addmod cs2113t`

Expected output:
````
CS2113T is added.
````

### 3. Add expected module workload: `addexp` 
Adds the expected workload of the module to the database.

Format: `addexp <module code> <expected workload>`

* The `module code` is valid.
* The `expected workload` is in hours.
* If the `module code` already exists, this command replaces its expected workload with the new expected workload.
* If the `module code` does not exist, this command creates a new module and adds its expected workload to the database.

Example of usage:

`addexp cs2113t 10`

Expected output:
````
CS2113T, Expected Workload: 10h is added.
````

### 4. Add actual time spent on a module: `addtime`
Adds the time spent on the indicated module.

Format: `addtime <module code> <time spent> <week number>`

* The `module code` is valid.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`addtime CS2113T 3 1`

Expected output:
````
3 hours are added to CS2113T.
````

### 5. List expected and actual time spent of all modules: `list`
Shows a list of the modules, their expected module workload, and the actual time spent for the specific week.

Format: `list <week number>`

* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`list 1`

Expected output:
````
+------+---------+----------+----------+
| Week | Module  | Expected |  Actual  |
+------+---------+----------+----------+
|  01  | CS2113T |    10    |    3.0   |
+------+---------+----------+----------+
````

### 6. Analyse actual time spent on all modules: `analyse`
Shows the breakdown and analysis for actual time spent for the specific week.

Format: `analyse <week number>`

* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`analyse 1`

Expected output:
````
Module    Week 1

CS2113T
Actual   | ███ 3.0
Expected | ██████████ 10.0

CS1231
Actual   | ███████ 7.0
Expected | ████████ 8.0

Total time spent: 10.0 H
30% of time is spent on CS2113T
70% of time is spent on CS1231

Analysis:
CS2113T
You seem to be spending too little time on the module.
Perhaps you should spend more time on this module.

CS1231
Good Job! Great time management!
````
 
### 7. Delete a module: `deletemod`
Deletes the specified module code from the database.

Format: `deletemod <module code>`

* The `module code` is valid.
* The `module code` exists in the database.

Example of usage:

`deletemod CS2113T`

Expected output:
````
CS2113T is removed.
````

### 8. Delete expected module workload: `deleteexp`
Deletes the expected workload of specified module code from the database.

Format: `deleteexp <module code>`

* The `module code` is valid.
* The `module code` exists in the database.

Example of usage:

`deleteexp CS2113T`

Expected output:
````
Expected Workload of CS2113T is removed.
````

### 9. Minus from actual time spent on a module: `minus`
Minus the actual time spent on a specified module.

Format: `minus <module code> <time spent> <week number>` 

* The `module code` is valid.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`minus CS2113T 3 1`

Expected output:
````
3 hours are removed from CS2113T
````

### 10. Delete actual time spent on a module: `deletetime`
Deletes the actual time spent on a specified module.

Format: `deletetime <module code> <week number>` 

* The `module code` is valid.
* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`deletetime CS2113T 3`

Expected output:
````
Actual time of CS2113T of week 3 is removed.
````

### 11. Add tasks: `addtask`
Adds a task under a specified module.

Format: `addtask <module code> <task description>`

* The `module code` is valid.
* The `module code` specified by the user must exist in the database. 

Example of usage:

`addtask CS2113T finish project`

Expected output:
````
Got it. I've added this task under CS2113T:
[X] [CS2113T] finish project
Now you have 1 task in the list.
````

### 12. List all tasks: `listtask`
Lists all tasks in the database.

Format: `listtask`

Expected output:
````
Here are the tasks in your list:
1. [X] [CS2113T] finish project
````

### 13. Mark task as done: `done`
Marks a specified task as done.

Format: `done <task number>`

* `task number` is between 1 and the number of tasks in the database inclusive.

Example of usage:

`done 1`


Expected output:
````
Nice! I've marked this task as done:
[/] [CS2113T] finish project
````

### 14. Delete a task: `deletetask`
Deletes a specified task from the database.

Format: `deletetask <task number>`

* `task number` is between 1 and the number of tasks in the database inclusive.

Example of usage:

`deletetask 1`

Expected output:
````
Noted. I've removed this task:
[/] [CS2113T] finish project
You currently have no task :-)
````


### 15. Open notification: `open`
Opens the notification with a randomised encouraging message.

Format: `open`

Example of usage:

`open`

A possible expected output:

```
Oh no! It appears you are spending too little time on CS1010.
   
The harder you work, the closer you are to success!
````

Note: The actual output may differ as the encouraging message is randomised.

### 16. Exit the program : `exit`
Exits the program.

Format: `exit`

Expected output:
````
All changes saved.
Bye <username>. Hope to see you again soon!
````

### 17. Save user data
The program will save your data such as your name, modules, time spent and tasks
to the hard disk automatically. There is no need to save these manually. 

When you exit and run the program again, your data will be loaded from the
hard disk, and you can use the app as per normal, 
continuing from where you left off.

The external file which stores your data will be locked to read-only 
when it is not in use, keeping your data safe and secure.

### 18. Delete module and task data: `reset`
Deletes all module and tasks data. 
The program will also prompt the user to confirm this action.

Format: `reset`

Expected output:
````
---WARNING!---
This will delete all your past data.
Type 'yes' if you wish to continue.
````

**Case 1: User enters `yes`**

Note: `yes` is case-insensitive here.

Expected output:
````
Okay, your data has been deleted :(
````

**Case 2: User enters `no`**

Note: any other input will fall under this case.

Expected output:
````
Reset not confirmed. Your data is safe :)
````

## FAQ

**Q**: Are the commands case-sensitive? 

**A**: No, all the commands are case-insensitive. Eg. typing in `HELP` will be taken in as a `help` command.

## Command Summary
|Feature|Command|
|--------|--------|
|Viewing help|`help`|
|Adding a module|`addmod <module code>`|
|Adding expected module workload|`addexp <module code> <expected workload>`|
|Add to actual time spent on a module|`addtime <module code> <time spent> <week number>`|
|Listing all modules|`list <week number>`|
|Analyse actual time spent on all modules|`analyse <week number>`|
|Deleting a module|`deletemod <module code>`|
|Deleting expected module workload|`deleteexp <module code>`|
|Minus from time spent on the module|`minus <module code> <time spent> <week number>`|
|Deleting actual time spent on the module|`deletetime <module code> <week number>`|
|Add tasks|`addtask <module code> <task description>`|
|List all tasks|`listtask`|
|Mark task as done|`done <task number>`|
|Delete a task|`deletetask <task number>`|
|Open notification|`open`|
|Exiting the program|`exit`|
|Deleting module and task data|`reset`|
