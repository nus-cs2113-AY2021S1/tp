# User Guide

## Introduction

_ModTracker_ is a desktop app for tracking the time spent on your modules, via a Command Line Interface (CLI).
It helps you to prioritise your work and balance your time spent among your modules. 


## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    1. [View help: `help`](#i-view-help-help)
    1. [Add a module: `addmod`](#ii-add-a-module-addmod)
    1. [Add expected module workload: `addexp`](#iii-add-expected-module-workload-addexp) 
    1. [Add actual time spent on a module: `addtime`](#iv-add-actual-time-spent-on-a-module-addtime)
    1. [List expected and actual time spent of all modules: `list`](#v-list-expected-and-actual-time-spent-of-all-modules-list)
    1. [Delete a module: `deletemod`](#vi-delete-a-module-deletemod)
    1. [Delete expected module workload: `deleteexp`](#vii-delete-expected-module-workload-deleteexp)
    1. [Minus from actual time spent on a module: `minus`](#viii-minus-from-actual-time-spent-on-a-module-minus) 
    1. [Exit the program : `exit`](#ix-exit-the-program--exit)
    1. [Save user data](#x-saving-the-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)


## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `ModTracker` from [here](https://github.com/AY2021S1-CS2113T-F12-4/tp/releases).
1. Save the jar file to a folder of your choice.
1. Open a command prompt and navigate to the folder that contains the jar file. Command: `cd [directory/filename]`
1. Type in the `java -jar modtracker.jar` command and press enter to run the program.
1. A welcome screen as shown below will be displayed if program is being executed successfully.

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

### i. View help: `help`
Shows a list of the commands available.

Format: `help`

Expected output:
````
<A list of available commands will be shown here>
````

### ii. Add a module: `addmod`
Adds a module to the database.

Format: `addmod <module code>`

* The `module code` must contain 6-8 characters without any spacing. 

Example of usage:

`addmod cs2113t`

Expected output:
````
CS2113T is added.
````

### iii. Add expected module workload: `addexp` 
Adds the expected workload of the module to the database.

Format: `addexp <module code> <expected workload>`

* The `module code` must contain 6-8 characters without any spacing.
* The `expected workload` is in hours.
* If the `module code` does not exist, this command creates a new module and adds its expected workload to the database.

Examples of usage:

`addexp cs2113t 10`

Expected output:
````
CS2113T, Expected Workload: 10h is added.
````

### iv. Add actual time spent on a module: `addtime`
Adds the time spent on the indicated module.

Format: `addtime <module code> <time spent> <week number>`

* The `module code` must contain 6-8 characters without any spacing.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Examples of usage:

`addtime CS2113T 3 1`

Expected output:
````
3 hours are added to CS2113T.
````

### v. List expected and actual time spent of all modules: `list`
Shows a list of the modules, their expected module workload, and the actual time spent for the specific week.

Format: `list <week number>`

* The `week number` must be between 1 and 13 inclusive.

Examples of usage:

`list 1`

Expected output:
````
+------+---------+----------+----------+
| Week | Module  | Expected |  Actual  |
+------+---------+----------+----------+
|  01  | CS2113T |    10    |    3.0   |
+------+---------+----------+----------+
````

### vi. Delete a module: `deletemod`
Deletes the specified module code from the database.

Format: `deletemod <module code>`

* The `module code` must contain 6-8 characters without any spacing.

Examples of usage:

`deletemod CS2113T`

Expected output:
````
CS2113T is removed.
````

### vii. Delete expected module workload: `deleteexp`
Deletes the specified module code workload from the database.

Format: `deleteexp <module code>`

* The `module code` must contain 6-8 characters without any spacing.

Examples of usage:

`deleteexp CS2113T`

Expected output:
````
Expected Workload of CS2113T is removed.
````

### viii. Minus from actual time spent on a module: `minus`
Minus the actual time spent on a specified module.

Format: `minus <module code> <time spent> <week number>` 

* The `module code` must contain 6-8 characters without any spacing.
* The `time spent` is in hours.
* The `week number` must be between 1 and 13 inclusive.

Examples of usage:

`minus CS2113T 3 1`

Expected output:
````
3 hours are removed from CS2113T
````

### ix. Exit the program : `exit`
Exits the program.

Format: `exit`

Expected output:
````
All changes saved.
Bye <username>. Hope to see you again soon!
````

### x. Save user data
Time spent is saved in the hard disk automatically after any command that changes the data. There is no need to save manually. 


## FAQ

**Q**: Are the commands case sensitive? 

**A**: No, all the commands are case insensitive. Eg. typing in `HELP` will be taken in as a `help` command.

## Command Summary

* Viewing help: `help`
* Adding a module: `addmod <module code>`
* Adding expected module workload: `addexp <module code> <expected workload>`
* Add to actual time spent on a module: `addtime <module code> <time spent> <week number>`
* Listing all modules: `list <week number>`
* Deleting a module: `deletemod <module code>`
* Deleting expected module workload: `deleteexp <module code>`
* Minus from time spent on the module: `minus <module code> <time spent> <week number>`
* Exiting the program : `exit`
