# User Guide

![](diagrams/Logo.png)

Yang Jiaqi

Zhuang Mengjin

Xu Che

Wu Nan

Wan Shi Jie Brendan


## Introduction

NuSchedule is a straightforward yet sophisticated application that helps you manage your events. It allows you 
 to keep track of not only your school events or activities (eg. lectures, tutorials, assignments), but 
also your personal activities. It utilises a command line interface (CLI), which accepts user input in the form of text. 
This gives the application both simplicity and speed, setting it apart from other apps such as NUSMods and Luminus. 

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
3. Copy the file into a new folder, which will serve as the home folder for NuSchedule. 
4. Copy the latest `location.txt` and `bus_stops.txt` files into a directory named `data` in the home folder.
5. Open the command prompt and navigate to the home folder.
6. Run `nuschedule.jar` using the following command: `java -jar nuschedule.jar`



## Features 

### Tips:
In the following description, `DATE` refers to the date in the format yyyy-MM-dd. e.g. `2020-10-31` 
`TIME` refers to the time in the format HH:mm. e.g. `10:00`  

`/t` refers to the starting time for PersonalEvent and Class, and deadline for Assignment  
`/e` refers to the ending time for PersonalEvent and Class  

### View available commands

Shows list of commands and provides a link to the user guide for more details. 

Format: `help`


### Add an event

Adds events/tasks to the schedule. Can support various types of tasks: 



* Personal events  
  Format: `personalEvent EVENTNAME /t DATE TIME /e DATE TIME /l LOCATION` for underline event.  
  OR `personalEvent EVENTNAME /t DATE TIME /e DATE TIME /o MEETING_LINK /p MEETING_PASSWORD` for online events.  
  Note that `/e DATE TIME` is optional. You have a personal event without an ending time.  
  In `/e DATE TIME`, `DATE` is optional. If you enter `/e TIME`, the default date will be the same 
  as the date that the event starts.  
  For online events, `/p MEETING_PASSWORD` is optional, since not all online events require a password.

* Assignments  
  Format: `assignment EVENTNAME /t DATE TIME /l LOCATION`  
  OR `assignment EVENTNAME /t DATE TIME /o SUBMISSION_LINK`  

* Classes  
  Format: `class CLASSNAME /t DATE TIME /e DATE TIME /l LOCATION`  
  OR `class CLASSNAME /t DATE TIME /e DATE TIME /o MEETING_LINK /p MEETING_PASSWORD`  
  Same as in personal events, in `/e DATE TIME`, `DATE` is optional. For online events,`/p MEETING_PASSWORD` is optional.  
  However, `/e DATE TIME` or `/e TIME` is required, since any class should end at some time. 


Example of usage: 

`personalEvent Mom’s Birthday /t 2020-10-26 19:00 /l home`  
`class CS2113 Lecture /t 2020-09-23 10:00 /e 12:00 /l LT27`

### Edit an event
With this command, you can edit existing tasks to change their description, start/end date, location and even the
event type. You can start using this command by entering `edit INDEX`, where INDEX is the number of the task you want to
edit.  

Format: `edit INDEX`

Example:

Next, you will be prompted to enter the new values for the 5 fields which are type, description, location start and end 
date respectively. Enter your desired changes and press the `ENTER` key to go through the fields.

If you do not wish to make any changes to any specific field, you can just leave the field empty and just press
enter. The field will then remain unchanged.



### Sort events
With this command, you can sort all events by either their end time or their description. When sorting by time, events
will be sorted in a chronological order. Events with an earlier end date will be sorted on top of events with later end
dates. This way, you can prioritize the events that are due soon.   

When sorting by description, the events will be sorted in alphabetical order according to their descriptions. Events 
with descriptions starting with 'a' will be sorted on top of events with descriptions starting with 'z' for example.  

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
Deletes all events in the list.  

Format: `clear`  


### Mark event as done
Mark an event that has been completed with `DONE`.  

Format: `done INDEX`  

Example: `done 1`  

### Reminder of deadlines
Shows a list of all events for today's date.

Format: `reminder`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous AddressBook home folder.

## Command Summary

|Action|Command|Example|
|------|-------|-------|
|add|`EVENTTYPE EVENTNAME /t DATE TIME` `/e DATE TIME`(optional personal event, compulsory for class, must not have for assignment) `/l LOCATION` OR `/o LINK` `/p PASSWORD`(optional)|`personalEvent Mom’s Birthday /t 2020-10-26 19:00 /l home`|
|clear|`clear` |`clear`|
|done|`done INDEX` |`done 1` |
|edit|`edit`, then fill in each fields when prompted and press `ENTER` to move on to the next field|`edit 1` `ENTER` `class` `ENTER` `cs2113t` `ENTER` `\l school` `ENTER` `2020-10-26 19:00` `ENTER` `2020-10-26 20:00`|
|help|`help`||
|list|`list`||
|locate|`locate n/EVENTNAME` |`locate n/CS2113t Tutorial`|
|sort|`sort SORT_CRITERIA` |`sort description`|
|delete|`delete INDEX`|`delete 1`|
