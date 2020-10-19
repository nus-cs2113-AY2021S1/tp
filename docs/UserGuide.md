# User Guide

## Introduction

_ModTracker_ is a desktop app for tracking the time spent on your modules, via a Command Line Interface (CLI).
It helps you to prioritise your work and balance your time spent among your modules. 


## Table of Contents
* ### Quick Start
* ### Features
    1. View help: `help`
    1. Add a module: `addmod <module code>`
    1. Add expected module workload: `addexp <module code> <expected workload>` 
    1. Add actual time spent on a module: `addtime <module code> <time spent> <week number>`
    1. List expected and actual time spent of all modules: `list <week number>`
    1. Delete a module: `deletemod <module code>`
    1. Delete expected module workload: `deleteexp <module code>`
    1. Minus from actual time spent on a module: `minus <module code> <time spent> <week number>` 
    1. Exit the program : `exit`
    1. Save user data
* ### FAQ
* ### Command summary


## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `ModTracker` from [here](https://github.com/AY2021S1-CS2113T-F12-4/tp/releases).
1. Save the jar file to a folder of your choice.
1. Open a command prompt and navigate to the folder that contains the jar file. Command: `cd [directory/filename]`
1. Type in the `java -jar modtracker.jar` command and press enter to run the program.
1. Refer to the Features and Usage section for more details on the application's available commands.


## Features 

### i. View help: `help`
Shows a list of the commands available.

Format: help

### ii. Add a module: `addmod <module code>`
Adds a module to the database.

Format: addmod <module code>

* The `module code` must contain 6-8 characters without any spacing. 

Examples of usage:

`addmod cs2113t`

`addmod CG2027`

### iii. Add expected module workload: `addexp <module code> <expected workload>` 
Adds the expected workload of the module to the database.

Format: `addexp <module code> <expected workload>`

* The `module code` must contain 6-8 characters without any spacing.
* The `expected workload` is in hours.
* If the `module code` does not exist, this command creates a new module and adds its expected workload to the database.

Examples of usage:

`addexp cs2113t 10`

`ADDEXP CS1010 8`

### iv. Add actual time spent on a module: `addtime <module code> <time> <week>`
Adds the time spent on the indicated module.

Format: `addtime <module code> <time spent> <week number>`

* The `module code` must contain 6-8 characters without any spacing.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Examples of usage:

`addtime CS2113T 3 1`

`addtime CS1010 3.5 2`

### v. List expected and actual time spent of all modules: `list <week number>`
Shows a list of the modules, their expected module workload, and the actual time spent for the specific week.

Format: `list <week number>`

* The `week number` must be between 1 and 13 inclusive.

Examples of usage:

`list 1`

`List 2`

### vi. Delete a module: `deletemod <module code>`
Deletes the specified module code from the database.

Format: `deletemod <module code>`

* The `module code` must contain 6-8 characters without any spacing.

Examples of usage:

`deletemod CS2101`

`deletemod CS2113T`

### vii. Delete expected module workload: `deleteexp <module code>`
Deletes the specified module code workload from the database.

Format: `deleteexp <module code>`

* The `module code` must contain 6-8 characters without any spacing.

Examples of usage:

`deleteexp CS2101`

`deleteexp CS2113T`

### viii. Minus from actual time spent on a module: `minus <module code> <time spent> <week number>`
Minus the actual time spent on a specified module.

Format: `minus <module code> <time spent> <week number>` 

* The `module code` must contain 6-8 characters without any spacing.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Examples of usage:

`minus CS2101 2 2`

`minus CS2113T 3 1`

### ix. Exit the program : `exit`
Exits the program.

Format: `exit`

### x. Saving the data
Time spent is saved in the hard disk automatically after any command that changes the data. There is no need to save manually. 


## FAQ

**Q**: Are the commands case sensitive? 

**A**: No, all the commands are case insensitive. Eg. typing in `HELP` will be taken in as a `help` command.

## Command Summary

* Viewing help: `help`
* Adding a module: `addmod <module code>`
* Adding expected module workload: `addexp <module code> <expected workload>`
* Add to actual time spent on a module: `addtime <module code> <time> <week>`
* Listing all modules: `list <week>`
* Deleting a module: `deletemod <module code>`
* Deleting expected module workload: `deleteexp <module code>`
* Minus from time spent on the module: `minus <module code> <time> <week>`
* Exiting the program : `exit`
