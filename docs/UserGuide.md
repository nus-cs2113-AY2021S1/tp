# User Guide

![](diagrams/Logo.png)

Yang Jiaqi

Zhuang Mengjin

Xu Che

Wu Nan

Wan Shi Jie Brendan


## Introduction

NuSchedule is a straightforward but sophisticated application that helps you manage your events. It allows you 
and students to not only keep track of your school events or activities (eg. lectures, tutorials, assignments), but 
also monitor your personal businesses. Its command line interface (CLI) would also mean simplicity and speed, 
which sets itself apart from other apps such as NUSMods and Luminus. 

* [Quick Start](#quick-start)
* [Features](#features)
    * [View available commands](#view-available-commands)
    * [Add an event](#add-an-event)
    * [Edit an event](#edit-an-event)
    * [Sort Events](#sort-events)
    * [Find location](#find-a-location)
    * [List all events](#list-all-events)
    * [Clear events](#clear-events)
    * [Mark event as done](#mark-event-as-done)
    * [Reminder of deadlines](#reminder-of-deadlines)
* [FAQs](#FAQ)
* [Command Summary](#command-summary)


## Quick Start
1. Ensure that you have Java 11 or above installed in your Computer.
2. Download the latest `nuschedule.jar` from here.
3. Copy the file into a new folder, which will serve as the home folder for NuSchedule
4. Open the command prompt and navigate to the home folder.
5. Run `nuschedule.jar` using the following command: `java -jar nuschedule.jar`



## Features 

### View available commands
Shows list of commands and provides a link to the user guide for more details. 

Format: `help`


### Add an event
Adds events/tasks to your schedule. Can support various types of tasks: 

* Personal events  
  Format: `personalEvent EVENTNAME /t DATE TIME /l LOCATION`

* Assignments  
  Format: `assignment EVENTNAME /t DATE TIME /l LOCATION`

* Classes  
  Format: `class EVENTNAME /t DATE TIME /l LOCATION`


Example of usage: 

`personalEvent Mom’s Birthday /t 2020-10-26 19:00 /l home`  
`class CS2113 Lecture /t 2020-09-23 10:00 /l LT27`

### Edit an event
Edits existing events/tasks.  

Format: `edit INDEX EVENT_TYPE DESCRIPTION /t YYYY-MM-DD HH:MM /l LOCATION`  

Example: `edit 1 assignment cs2113t homework /t 2020-02-02 20:00 /l home`

### Sort events
Sorts events based on a sorting criteria. Supported criteria includes time, description and
location.

Format: `sort SORT_CRITERIA`  

Example: `sort description`

### Find a location
Shows you the location of the specified event/task.  

Format: `locate n/EVENTNAME`  

Example: `locate n/CS2113t Tutorial`

### List all events
Shows you a list of all events.  

Format: `list`  

### Clear events
Deletes events based on their number on the list.  

Format: `clear NUMBER` OR `clear ALL`  

Example: `clear 3`  


### Mark event as done
Mark a event that is completed with `DONE`.  

Format: `done INDEX`  

Example: `done 1`  

### Reminder of deadlines
No command required. Automatically sends notifications 24 hours before the event occurs. 

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous AddressBook home folder.

## Command Summary

|Action|Command|Example|
|------|-------|-------|
|add|`EVENTTYPE EVENTNAME /t DATE TIME /l LOCATION`|`personalEvent Mom’s Birthday /t 2020-10-26 19:00 /l home`|
|clear|`clear NUMBER` OR `clear ALL` |`clear 3`|
|done|`done INDEX` |`done 1` |
|edit|`edit INDEX EVENT_TYPE DESCRIPTION /t YYYY-MM-DD HH:MM /l LOCATION`|`edit 1 assignment cs2113t homework /t 2020-02-02 20:00 /l home`|
|help|`help`||
|list|`list`||
|locate|`locate n/EVENTNAME` |`locate n/CS2113t Tutorial`|
|sort|`sort SORT_CRITERIA` |`sort description`|
