# User Guide
CS2113T – T12 – 04 Scheduler--;
 
Scheduler--; Does the job

## Table of Contents
[About Scheduler--;](#about-scheduler--) <br>
&nbsp;&nbsp;[What can it do?](#what-can-it-do) <br>
&nbsp;&nbsp;[How to use this document?](#how-to-use-this-document) <br>
&nbsp;&nbsp;[Conventions used in this document](#conventions-used-in-this-document) <br>
[Quick Start](#quick-start) <br>
&nbsp;&nbsp;[System Requirements](#system-requirements) <br>
&nbsp;&nbsp;[Setup Instructions](#setup-instructions) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Windows](#windows) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Mac OS X](#mac-os-x) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Linux](#linux) <br>
&nbsp;&nbsp;[Test run](#test-run) <br>
[Features](#features) <br>
&nbsp;&nbsp;[Viewing help: help](#viewing-help-help-colin-ng) <br>
&nbsp;&nbsp;[Adding an event: add](#adding-an-event-add-matthew-gani) <br>
&nbsp;&nbsp;[List events: list](#list-events-list-marcus-ng) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[List all events](#list-all-events) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[List all events of a type](#list-all-events-of-a-type) <br>
&nbsp;&nbsp;[Calendar format list:calendar](#calendar-format-list-calendar-marcus-ng) <br>
&nbsp;&nbsp;[Deadlines: deadline](#deadlines-deadline-qing-ning) <br>
&nbsp;&nbsp;[Repeat on daily/weekly/monthly basis: repeat](#repeat-on-dailyweeklymonthly-basis-repeat-colin-ng) <br>
&nbsp;&nbsp;[Check availability on a specific date and time: check](#check-availability-on-a-specific-date-and-time-check-marcus-tan) <br>
&nbsp;&nbsp;[Goal setting and viewing: goal](#goal-setting-and-viewing-goal-marcus-ng) <br>
&nbsp;&nbsp;[Mark events as done: done](#mark-events-as-done-done-marcus-tan) <br>
&nbsp;&nbsp;[Mark events as not done: undone](#mark-events-as-not-done-undone-marcus-tan) <br>
&nbsp;&nbsp;[Deleting an event: delete](#deleting-an-event-delete-marcus-tan) <br>
&nbsp;&nbsp;[Note taking: note](#note-taking-note-qing-ning) <br>
&nbsp;&nbsp;[Reminder: reminder](#reminder-reminder-qing-ning) <br>
&nbsp;&nbsp;[Extracting events from texts: extract](#extracting-events-from-texts-extract-matthew-gani) <br>
&nbsp;&nbsp;[Save events: save](#save-events-events-colin) <br>
&nbsp;&nbsp;[Exiting the program](#exiting-the-program-bye) <br>
[Command Summary](#command-summary)

## About Scheduler--;

### What can it do?

### About this guide

### How to use this document?

### Conventions used in this document

## Quick Start

### System Requirements

### Setup Instructions

#### Windows

#### Mac OS X

#### Linux

### Test run

## Features

### Viewing help: help (Colin Ng)

### Adding an event: add (Matthew Gani)
New to creating your events using Scheduler--;?
Utilize our simple to use yet effective add feature which comprises of 3 different event types. 
You can add dates and times to these events and even website links and locations to some of them! 

Format: `add EVENT_TYPE EVENT_DESCRIPTION; [LINK/LOCATON]; DD/MM/YY; HH:MM AM/PM`

•	Types of events:
1.	Zoom Meetings: Zoom
2.	NUS timetable/lessons: Timetable
3.	Personal Events: Personal 

•	Only the full word will be recognized as a valid event type. (E.g persona will not match Personal)

•	Time can be in the format of HHMM(24-hour) or HH:MM AM/PM (12-hour)

•	Timetable events can contain: <br>
a.	Description, date and time <br>
b.	Description, location, date and time

•	Zoom events can contain: <br>
a.	Description and link <br>
b.	Description, link, date and time

•	Personal events can contain: <br>
a.	Description <br>
b.	Description and date <br>
c.	Description, date and time

Examples: <br>
•	`add Zoom CS2113T Meeting; zoom.com.sg; 16/09/20; 2100` <br>
•	`add personal Family Meeting; 18/09/20`  <br>
•	`add Timetable CS2101 Lecture; NUS Computing; 18/09/20; 3:30 pm`

Expected Output:
```
_________________________________
You have successfully added this event to your list!
[Z][✕] CS2113T Meeting, Link: zoom.com.sg on 2020-09-16, 21:00
_________________________________
```
```
_________________________________
You have successfully added this event to your list!
[P][✕] Family Meeting on 2020-09-18
_________________________________
```
```
_________________________________
You have successfully added this event to your list!
[T][✕] CS2101 Lecture, Location: NUS Computing on 2020-09-18, 15:30
_________________________________
```
Note: <br>
•	When giving the event type, take note that it is case-insensitive:
`add Zoom` is the same as `add zoom`

•	Only the full word will be recognized as the event type:

`add z` will NOT add a zoom event

Warning!<br>
Typing in the wrong format for date or time will cause the event to not be made. 


### List events: list (Marcus Ng)

#### List all events

#### List all events of a type

### Calendar format list: calendar (Marcus Ng)

### Deadlines: deadline (Qing Ning)

### Repeat on daily/weekly/monthly basis: repeat (Colin Ng)

### Check availability on a specific date and time: check (Marcus Tan)

### Goal setting and viewing: goal (Marcus Ng)

### Mark events as done: done (Marcus Tan)

### Mark events as not done: undone (Marcus Tan)

### Deleting an event: delete (Marcus Tan)

### Note Taking: note (Qing Ning)

### Reminder: reminder (Qing Ning)

### Extracting events from texts: extract (Matthew Gani)
Ever feel tired of reading long emails everyday? 
You can use our extract function which will help you read any text and extract out possible dates and times. 
You’ll be able to choose the dates and times detected and create a Personal event. 

The extract feature detects dates in the DD/Month name/YYYY format or the Month name/ DD/YYYY format which is used the most in emails. 

Format: `extract TEXT SUBJECT; TEXT BODY`

Example: `extract CG2271 Quiz 2; Hi all, we will be having the quiz on either 4th October 2020 or October 15 2020 at either 3pm or 3.30pm.`

Expected Output:
````
_________________________________
We have detected 2 dates in this text body!
Please select the date you want for this event from the list below!
_________________________________
1. 2020-10-04
2. 2020-10-15
_________________________________
2
We have detected 2 time slots in this text body!
Please select the time you want for this event from the list below!
_________________________________
1. 15:00
2. 15:30
_________________________________
2
You have successfully added this event to your list!
[P][✕] CG2271 Quiz 2 on 2020-10-15, 15:30
_________________________________
````
As shown above, the user can choose the date/time they want for the event as long as it is a valid number in the list.
 In the example, the user picks the second option for both date and time, shown by the two times '2' was input.
 
 
 Note:<br>
 •	The extract feature can also detect when there are suffixes like st/nd/rd/th for the day portion of the date.



### Save events: events (Colin)

### Exiting the program: bye 

## Command Summary

