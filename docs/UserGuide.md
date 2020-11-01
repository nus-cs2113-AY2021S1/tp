# User Guide

## Introduction

_ModTracker_ is a desktop app for NUS students to track the time spent 
as well as tasks to do for each of their modules.
It helps students prioritise their work and 
balance their time spent amongst their modules. 
This app uses a Command Line Interface (CLI).

## Table of Contents
1. [Quick Start](#quickstart)
1. [Features](#features)
    <br/>&nbsp;2.1 [View help: `help`](#help)
    <br/>&nbsp;2.2 [Add a module: `addmod`](#addmod)
    <br/>&nbsp;2.3 [Add expected module workload: `addexp`](#addexp) 
    <br/>&nbsp;2.4 [Add actual time spent on a module: `addtime`](#addtime)
    <br/>&nbsp;2.5 [List expected and actual time spent of all modules: `list`](#list)
    <br/>&nbsp;2.6 [Analyse actual time spent on all modules: `analyse`](#analyse)
    <br/>&nbsp;2.7 [Delete a module: `deletemod`](#deletemod)
    <br/>&nbsp;2.8 [Delete expected module workload: `deleteexp`](#deleteexp)
    <br/>&nbsp;2.9 [Minus from actual time spent on a module: `minustime`](#minus) 
    <br/>&nbsp;2.10 [Edit actual time spent on a module: `edittime`](#edit)
    <br/>&nbsp;2.11 [Delete actual time spent on a module: `deletetime`](#deletetime)
    <br/>&nbsp;2.12 [Add tasks: `addtask`](#addtask)
    <br/>&nbsp;2.13 [List all tasks: `listtask`](#listtask)
    <br/>&nbsp;2.14 [Mark task as done: `done`](#done)
    <br/>&nbsp;2.15 [Delete a task: `deletetask`](#deletetask)
    <br/>&nbsp;2.16 [Open notification: `open`](#open)
    <br/>&nbsp;2.17 [Save user data](#save)
    <br/>&nbsp;2.18 [Clear module and task data: `clear`](#clear)
    <br/>&nbsp;2.19 [Delete user data: `reset`](#reset)
    <br/>&nbsp;2.20 [Exit the program : `exit`](#exit)
1. [FAQ](#faq)
1. [Command Summary](#summary)


## 1. Quick Start <a name="quickstart"></a>

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


## 2. Features <a name="features"></a>

### Notes:
1. Items in <> are the parameters to be supplied by the user.
   e.g. in addmod <module code>, module code is a parameter which can be used as addmod CS2113T.
1. The module code is valid if it starts with 2 - 3 characters, followed by 4 digits, followed by an optional character.
1. Definition of too much / too little time spent on module. 
    1. A user is considered to spend too little time on a module if the user's actual workload is less than the expected 
workload by more than 30%. For example, if the expected workload is 10 hours, a user is considered to spend too 
little time on the module if his actual workload is less than 7 hours.

    1. A user is considered to spend too much time on a module if the user exceeds the expected workload by at least 30%. 
For example, if the expected workload is 10 hours, a user is considered to spend too much time on the module if 
his actual workload is 13 hours or more.
    
    1. A user is considered to spend just right amount of time on a module if the user spends between 70% to 130% of the 
expected workload. For example, if the expected workload is 10 hours, a user is considered to spend just right amount of
time on the module if his actual workload is between 7 hours (inclusive) and 13 hours (exclusive).

### 2.1 View help: `help` <a name="help"></a>
Shows a list of the commands available.

Format: `help`

Expected output:
````
Available Commands:
1. addmod <module code>
   example: addmod CS2113T
2. addexp <module code> <expected workload>
   example: addexp CS2113T 5
3. addtime <module code> <actual time spent> <week number>
   example: addtime CS2113T 2 1
4. list <week number>
   example: list 2
5. deletemod <module code>
   example: deletemod CS2113T
6. deleteexp <module code>
   example: deleteexp CS2113T
7. minustime <module code> <time> <week number>
   example: minustime CS2113T 2 1
8. edittime <module code> <actual time spent> <week number>
   example: edittime CS2113T 8 1
9. deletetime <module code> <week number>
   example: deletetime CS2113T 1
10.analyse <week number>
   example: analyse 1
11.addtask <module code> <task description>
   example: addtask CS2113T revise for exam
12.deletetask <task number>
   example: deletetask 1
13.done <task number>
   example: done 1
14.listtask
15.open
16.clear
17.reset
18.exit
````

### 2.2 Add a module: `addmod` <a name="addmod"></a>
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

### 2.3 Add expected module workload: `addexp` <a name="addexp"></a>
Adds the expected workload of the module to the database.

Format: `addexp <module code> <expected workload>`

* The `module code` is valid.
* The `expected workload` is expressed in hours. It has to be a whole number between 1 and 24 inclusive.
* If the `module code` already exists, this command replaces its expected workload with the new expected workload.
* If the `module code` does not exist, this command creates a new module and adds its expected workload to the database.

Example of usage:

`addexp cs2113t 10`

Expected output:
````
CS2113T, Expected Workload: 10h is added.
````

### 2.4 Add actual time spent on a module: `addtime` <a name="addtime"></a>
Adds the time spent on the indicated module. At the end there is also a summary for the total hours spent in that week.

Format: `addtime <module code> <time spent> <week number>`

* The `module code` is valid.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`addtime CS2113T 3 1`

Expected output:
````
3 hours have been added to CS2113T.
3.0 hours have been spent on this module in week 1.
````

### 2.5 List expected and actual time spent of all modules: `list` <a name="list"></a>
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

### 2.6 Analyse actual time spent on all modules: `analyse` <a name="analyse"></a>
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
 
### 2.7 Delete a module: `deletemod` <a name="deletemod"></a>
Deletes the specified module code from the database.

Format: `deletemod <module code>`

* The `module code` is valid.
* The `module code` exists in the database.

Example of usage:

`deletemod CS2113T`

Expected output:
````
CS2113T is removed.
All tasks under CS2113T are deleted.
````

### 2.8 Delete expected module workload: `deleteexp` <a name="deleteexp"></a>
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

### 2.9 Minus from actual time spent on a module: `minustime` <a name="minus"></a>
Minus the actual time spent on a specified module. At the end there is also a summary for the total hours spent in that 
week.

Format: `minustime <module code> <time spent> <week number>` 

* The `module code` is valid.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`minustime CS2113T 3 1`

Expected output:
````
3 hours have been removed from CS2113T.
2.0 hours have been spent on this module in week 1.
````

### 2.10 Edit actual time spent on a module: `edittime` <a name="edit"></a>
Edits the actual time spent on a specified module.

Format: `edittime <module code> <time spent> <week number>` 

* The `module code` is valid.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Example of usage:

`edittime CS2113T 3 1`

Expected output:
````
3 hours is the new actual workload for the module CS2113T.
3.0 hours have been spent on this module in week 1.
````

### 2.11 Delete actual time spent on a module: `deletetime` <a name="deletetime"></a>
Deletes the actual time spent on a specified module.

Format: `deletetime <module code> <week number>` 

* The `module code` is valid.
* The `week number` must be a whole number between 1 and 13 inclusive.

Example of usage:

`deletetime CS2113T 3`

Expected output:
````
Actual time of CS2113T of week 3 is removed.
````

### 2.12 Add tasks: `addtask` <a name="addtask"></a>
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

### 2.13 List all tasks: `listtask` <a name="listtask"></a>
Lists all tasks in the database.

Format: `listtask`

Expected output:
````
Here are the tasks in your list:
1. [X] [CS2113T] finish project
````

### 2.14 Mark task as done: `done` <a name="done"></a>
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

### 2.15 Delete a task: `deletetask` <a name="deletetask"></a>
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


### 2.16 Open notification: `open` <a name="open"></a>
Opens the notification with a randomised encouraging message.
The user is notified on the progress of the current week.
The current week is defined by the latest week that has at least one actual time input.


Format: `open`

Example of usage:

`open`

A possible expected output:

```
Oh no! It appears you are spending too little time on CS1010.
   
The harder you work, the closer you are to success!
````

Note: The actual output may differ as the encouraging message is randomised.


### 2.17 Save user data <a name="save"></a>
The program will save your data such as your name, modules, time spent and tasks
to the hard disk automatically. There is no need to save these manually. 

When you exit and run the program again, your data will be loaded from the
hard disk, and you can use the app as per normal, 
continuing from where you left off.

The external file which stores your data will be locked to read-only 
when it is not in use, keeping your data safe and secure.

### 2.18 Clear module and task data: `clear` <a name="clear"></a>
Deletes all module and tasks data.
The program will also prompt the user to confirm this action.

Format: `clear`

Expected output:
````
---WARNING!---
This will delete all modules and tasks data.
Type 'yes' if you wish to continue.
Enter any key to cancel this operation.
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
Clearing of data not confirmed. Your data is safe :)
````
 
### 2.19 Delete user data: `reset` <a name="reset"></a>
Deletes all module and tasks data, and the username.
The program will also prompt the user to confirm this action.
If confirmed, the program will restart and prompt for a new username.

Format: `reset`

Expected output:
````
---WARNING!---
This will delete all your past data and reset the whole program.
Type 'yes' if you wish to continue.
Enter any key to cancel this operation.
````

**Case 1: User enters `yes`**

Note: `yes` is case-insensitive here.

Expected output:
````
Okay, the program will reset now...

Hello from
|\\        /|         |======            ||
||\\      / |  __   __|  ||  __  ___ ___ ||    ___   ____
|| \\    /  |//  \//  |  ||//  \/  |/    ||// / _ \ //   \
||  \\  /   |||   ||  |  |||   ||  ||    ||\\ | __/ ||
||   \\/    |\\__/\\__|  |||   \\__|\___ || \\\___| ||
*****************************************************|

Full user guide available at: https://ay2021s1-cs2113t-f12-4.github.io/tp/UserGuide.html
Enter <help> for a quick view of available commands.

What is your name?

````

**Case 2: User enters `no`**

Note: any other input will fall under this case.

Expected output:
````
Reset not confirmed. Your data is safe :)
````

### 2.20 Exit the program : `exit` <a name="exit"></a>
Exits the program.

Format: `exit`

Expected output:
````
All changes saved.
_______    _______
||   \\\  //||
||___//\\// ||___
||   \\ ||  ||
||___// ||  ||____

Bye <username>. Hope to see you again soon!
````

## 3. FAQ <a name="faq"></a>

**Q**: Are the commands case-sensitive? 

**A**: No, all the commands are case-insensitive. Eg. typing in `HELP` will be taken in as a `help` command.

## 4. Command Summary <a name="summary"></a>
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
|Minus from time spent on the module|`minustime <module code> <time spent> <week number>`|
|Deleting actual time spent on the module|`deletetime <module code> <week number>`|
|Add tasks|`addtask <module code> <task description>`|
|List all tasks|`listtask`|
|Mark task as done|`done <task number>`|
|Delete a task|`deletetask <task number>`|
|Open notification|`open`|
|Clear module and task data|`clear`|
|Deleting module and task data|`reset`|
|Exiting the program|`exit`|

[Home Page](https://ay2021s1-cs2113t-f12-4.github.io/tp/)