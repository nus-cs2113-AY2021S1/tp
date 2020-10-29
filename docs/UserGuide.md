# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Deadlines: `deadline`
Want to set a deadline after you have created your personal event? Afraid that you forget your deadlines? Deadline is here to help! You can set the date and time of the task to be completed and on the day itself, we will remind you.  

Format: `deadline EVENT_INDEX; DD/MM/YY; [HH:MM] AM/PM`

* `EVENT_INDEX` is a number. It contains the index of the personal event that will have its deadline set or changed. 

* `DD/MM/YY` contains a date string in the format `[DD/MM/YY]`. This is the date of the deadline for the personal event. 

* `[HH:MM]` is an optional argument containing the time of the deadline for the personal event. It can be written in either 12-hour or 24-hour format. 

Example of usage: 

`deadline 2; 23/07/20` sets the deadline of event number 2 to be on 23 July 2020 a 

`deadline 7; 29/08/20; 14:25` sets the deadline of event number 7 to be on the 29 August 2020 at 2:25PM. 

`deadline 7; 29/08/20; 11:20 PM` sets the deadline of event number 7 to be on the 29 August 2020 at 11:20PM. 

<div class="panel panel-info">

**Note**

<div class="panel-body">

* The time can be omitted entirely. If you leave time field as blank, the command would not set any time for the event.
* You may also omit the minutes in a time. If the minutes(MM) field of any time is empty, the time is read as when the hour begins (e.g. 4 pm would be taken as 4:00 pm)
* AM/PM is required for 12 hour format

</div>
</div>

<div class="panel panel-danger">

**Warning**
<div class="panel-body">

The event index keyed in have to be valid so that deadline can be created for the specified event index.

</div>
</div>

### Note Taking: `note`
Need to take last minutes meeting notes? Need to write some notes but don’t know where to keep them? Fear not! We have got you covered. Use this command to take notes and attach them to the event you are attending. You can also choose to open an empty note and type the notes. 

Format: `note EVENT_TYPE; EVENT_INDEX`

* The `EVENT_TYPE` have to be either personal, zoom or timetable.
* Scheduler will prompt you to type your notes
Example of usage: 

`note personal; 1 `

####How to use?

1. Find the index number of the event to write the note for 
    You can do this by typing the command list EVENT_TYPE into your application. The result for the following command is shown in the following:
    ```
    Here is a list of your Personal events:
    1. [P][X] sleep 
    ```
    In this case, the index number that you want is 1.
1. Type the command into the terminal
    ```
    note personal; 1 
   ```
1. Scheduler will prompt you to type your notes
    ```
    Please type in your notes. To stop note taking, ensure that you are in a new line and type the semicolon key, ';' and press enter 
   
   ```
1. Tell Scheduler you are done by pressing enter to go to a new line and insert a semicolon `;`
1. Voilà! Your notes have been saved! 
    ```
   You have successfully written the note for this event!
   [P][X] sleep
   ---------2020-10-30T00:53:01.907824900---------
   hello there!
   scheduler says hi:)
   _________________________________
   ```
<div class="panel panel-danger">

**Warning**
<div class="panel-body">

The event index keyed in have to be valid so that deadline can be created for the specified event index.

</div>
</div>

### Reminder: `reminder`
Fear of forgetting what you have today? Scheduler—is here to show you your events and task to be completed for the day.  Cheers to no more missed deadlines and meetings! 

Format: `reminder`

Expected Output:
```
You have the following events today:
[T][X] math, Location: S17 on 2020-10-26, 12:00
[Z][X] math, Link: www.zoom.com/blah on 2010-10-26, 12:00
[P][X] sleep on 2020-10-26, 23:00
```
### Exiting the program: `bye`
Time to have a rest? See you later! Do not worry, we will keep your events in Scheduler--.

Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

| Action | Format, Examples |
|--------|------------------|
|Add|add EVENT_TYPE EVENT_DESCRIPTION; DD/MM/YY <br> Eg: add personal Family Meeting; 18/09/20 <br> <br> add EVENT_TYPE EVENT_DESCRIPTION; LINK; DD/MM/YY; HH:MM <br> Eg: add Zoom CS2113T Meeting; zoom.com.sg;  16/09/20 2100 <br> <br> add EVENT_TYPE EVENT_DESCRIPTION; LOCATION; DD/MM/YY; HH:MM AM/PM <br> Eg: add Timetable CS2101 Lecture; NUS Computing; 18/09/2020; 3:30 pm <br>|
|List|list all <br> <br> list TYPE <br> Eg: list Zoom <br> <br> list from sd/DD/MM/YY to ed/DD/MM/YY <br> Eg: list from sd/12/04/20 to ed/19/04/20 <br>|
|Check|check [START_DATE]; [START_TIME]; [END_DATE]; [END_TIME] <br> Eg: check 20/08/20; 15:05; 25/8/2020; 1 pm; <br>|
|Repeat|repeat EVENT_TYPE EVENT_INDEX [UNIT] [COUNT] <br> Eg: repeat timetable 2 weekly 4 <br>|
|Goal|goal USERGOALS <br> Eg: goal “Get CAP 5.0 for year 2” <br>|
|Deadline|deadline EVENT_INDEX; DD/MM/YY; <br> Eg: deadline 2 23/07/20 <br> <br> deadline EVENT_INDEX; DD/MM/YY; [HHMM] <br> Eg: deadline 7 29/08/20 1425 <br> <br> deadline EVENT_INDEX; DD/MM/YY; [HH:MM] AM/PM <br> Eg: deadline 7 29/08/20 11:25 PM <br>|
|Set event as done|done EVENT_TYPE EVENT_INDEX [EVENT_DATE] <br> Eg: done personal 1 1/4/2020 <br>|
|Set event as undone|undone EVENT_TYPE EVENT_INDEX [EVENT_DATE] <br> Eg: undone zoom 1 3/12/2020 <br>|
|Delete|delete EVENT_TYPE EVENT_INDEX [EVENT_DATE] <br> Eg: delete timetable 3 <br>|
|Notes|note EVENT_TYPE; EVENT_INDEX <br> note personal; 1 <br>|
|Extract|extract TEXT SUBJECT; TEXT BODY <br>|
|Reminder|reminder <br>|
|Save|save <br>|
|Help|help <br>|
|Bye|bye<br>|

