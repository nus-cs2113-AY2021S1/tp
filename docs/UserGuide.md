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
Hello! Welcome to scheduler--;

Scheduler--; (S--) is a desktop app for managing deadlines from different sources. The user will use a Command Line Interface to control it. It is specially designed for Computing students who are comfortable in using CLI and have Git project deadlines as well as consolidated Zoom session links due which will suit home based learning in this COVID period. 

### What can it do?

Scheduler--; comes with the following list of features to help you keep organised of your schedule.  
- Check if your schedule is free between a certain date and time period
- Prioritize important task on your schedule
- Categorise your events into personal events, zoom meetings or school timetable events. 
- Be reminded on the important tasks and expected deadlines 
- Set certain events to occur repeatedly throughout your schedule 
- Set personal daily goals for yourself 
- And many more... 

### About this guide
This user guide is for your reference on how to set up the Scheduler--; program on your computer. It provides you with information on how to get things done with scheduler--; and how to use the commands in the program. 

### How to use this document?
To get started, take a look at the section labelled “Quick Start” to set up the application to run on your computer.  

Once you have set up the application, you may use the table of contents present in this document to help find the correct command, learn how to use it and what it does.  
### Conventions used in this document
Words that are highlighted in grey, for instance, list, indicate that the words are commands to be typed into the command line interface. 

- Words that are highlighted in grey and are in lower case indicates commands that are to be typed exactly. For instance, if the instructions says to type the words `list all` to run a command,  type on the program the exact words “list all” 

- Words that are highlighted in grey and are in upper case indicates command arguments or parameters that are to be provided by the user. For instance, if the usage instruction informs you to type `delete EVENT_INDEX` and you know that the EVENT_INDEX is 2, type into the computer program “delete 2”. 

{{box op="start" cssClass="boxed noteBox"}}
**Note!**

This is a note textbox. Additional information about the command will be written in boxes such as this one. 
{{box op="end"}}

Words that are highlighted in grey, in upper case and are surrounded by square brackets indicates command arguments or parameters that the user can choose to provide or not to provide. For instance, if the command instruction informs you to type `goal [NEW_GOAL]`, you can choose to omit the argument for NEW_GOAL and instead just simply type “goal”.

{{box op="start" cssClass="boxed warningBox"}}
**Warning!**

This is a warning textbox. Any user interaction with the program that may result in issues or unintended results will be written in boxes such as this one. 
{{box op="end"}}

{{box op="start" cssClass="boxed noteBox"}}
**Notes about the command format**

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
- Items in square brackets are optional
  e.g. `n/Name [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
- Items with ... after them can be used multiple times including zero times.
  e.g. `[t/TAG]...` can be used as  (i.e. 0 times), `t/friend`, `t/friend t/family` etc.
{{box op="end"}}

## Quick Start

### System Requirements
To run Scheduler--;, your computer must meet these minimum system requirements 

- Operating System: Windows 7 and above, Mac OS X or Linux 
- Java 11 installed on computer 

### Setup Instructions

#### Windows

1. Install Java 11 or above in your computer if you have not done so 
1. Download the latest scheduler.jar from here 
1. Copy the file to the folder you want to use as the folder for the Scheduler— 
1. open the command prompt by entering `cmd` into the search bar and press enter 
1. type `chcp 65001` into the command prompt and press enter 
1. navigate to the folder `C:\Duke` in the command prompt 
1. type `java -Dfile.encoding=UTF-8 -jar duke.jar` into the command prompt and press enter. 
1. The welcome message for the program should appear. 

#### Mac OS X

1. Install Java 11 or above in your computer if you have not done so 
1. Download the latest scheduler.jar from here 
1. Copy the file to the folder you want to use as the folder for the Scheduler--; 
1. Open the terminal by clicking on Launchpad->Utilities->Terminal 
1. Navigate to the folder you have stored the jar file in the command prompt 
1. Type `java -jar duke.jar` into the terminal and press enter. 
1. The welcome message for the program should appear. 

#### Linux

1. Install Java 11 or above in your computer if you have not done so 
1. Download the latest scheduler.jar from here 
1. Copy the file to the folder you want to use as the folder for the Scheduler--; 
1. Open the terminal in your linux distribution
1. Navigate to the folder you have stored the jar file in the command prompt 
1. Type `java -jar duke.jar` into the terminal and press enter. 
1. The welcome message for the program should appear. 

### Test run
1. Type the command in the command box and press Enter to execute. 

Some examples you can try: 
    - `list`: List all events 
    - `add EVENT_TYPE EVENT_NAME at DD/MM/YY HHMM`: Add an event to the scheduler 
    - `bye`: exit the program 
    
1. Refer to Features section for details of each command 

Congratulations! You have just finished setting up Scheduler--; Feel free to explore the program, or if you would like some assistance, take a look at the subsequent sections for instructions on how to use the available features of this program. Happy scheduling!
## Features
This section introduces and explains the features of Scheduler--;!

### Viewing help: help (Colin Ng)
If you are ever unsure on how to use a command, or you would like to learn more about the uses of a command, the help command is scheduler--; onboard help line for getting information. The help command allows you to get more information when you are unsure on how to use the program. It can also assist you with getting more information on how to use a specific command in the program. 

Format: `help [COMMAND]`

#### How to use? 

When you type in `help`, a generic help screen will be printed as shown below 

```
help
_________________________________
Here is a summary of all the commands that the program can run
help - Provides more information on how to use the commands available in the program
add - Records a personal, timetable or zoom event into the program
deadline - Sets or change a deadline for a personal event
repeat - repeat an event for a specified amount of times every day, week or month
goal - Sets up a goal for the user
done - mark the event as completed
delete - remove a personal, timetable or zoom event permamently from the program
note - Add in additional notes associate to the personal, timetable or zoom event
reminder - prints out events that will be occuring on the present day
extract - extracts out important event information from text to be recorded as events in the program
save - save current changes made to the program
bye - exits the program 

To find out more about each of these functions, type in "help [COMMAND]" into the terminal where COMMAND is the name of the command you would like more information about
_________________________________
```

If you want to get help for a specific command, type in `help [COMMAND]`. For example, the following screenshot shows what happens if you type in `help add`. 
```
help add
_________________________________
add - Records a personal, timetable or zoom event into the program
Format: add EVENT_TYPE EVENT_DESCRIPTION; [LINK/LOCATION]; DD/MM/YY; HH:MM AM/PM

EVENT_TYPE specify what kind of event you would like to repeat. The accepted arguments for this are “personal”, “timetable” and “zoom”
EVENT_DESCRIPTION is the name of the event
[LINK/LOCATION] is an optional argument. provide a zoom link if your event is a zoom conference and provide a location if your event is a timetable event
DD/MM/YY contains a date string in the format [DD/MM/YY]. This is the date of the deadline for the personal event.
[HH:MM] is an optional argument containing the time of the deadline for the personal event. It can be written in either 12-hour or 24-hour format.

_________________________________
```

You can even et help on how to use the help command by typing in `help help` as shown in the following screenshot
```
help help
_________________________________
help - Provides more information on how to use the commands available in the program
Format: help [COMMAND]

[COMMAND] - command which you would like to find out more information about. 

text in lowercase indicates that you should type it exactly like this when you type the command in the terminal
text in uppercase indicates compulsory arguments that you will need to provide and type into the terminal to operate the command
text in uppercase surrounded by square brackets [] indicates optional arguments that you can choose to provide and type into the terminal to operate the command
_________________________________
```

### Adding an event: `add` (Matthew Gani)
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


### List events: `list` (Marcus Ng)

After you have added your events, it is essential that you can view the events you have added.
You can do so using the list command to print out a list of your events in the order it was added in. 

Format: ```list [EVENT_TYPE]```

- ```EVENT_TYPE``` specify what kind of event you would like to list.
The accepted arguments for this are “personal”, “timetable” and “zoom”.

When the optional argument `[EVENT_TYPE]` is omitted,
list will print a list of available event types.

#### List all events of a type

Example: ```list Zoom```

Expected output:

```
list zoom
_________________________________
Here is a list of your Zoom events:
1. [Z][X] CS2113T Meeting, Link: zoom.com.sg on 2020-09-16, 21:00
_________________________________
```

{{box op="start" cssClass="boxed noteBox"}}
**Note!**

* You do not need to type the exact event type, it is case-insensitive. In other words,
`list Zoom` is the same as `list zoom`

{{box op="end"}}

#### List all events

You can print a list of all events by executing the following command: ```list all```

{{box op="start" cssClass="boxed noteBox"}}
**Note!**

* Events will be listed in the order: Personal, Timetable, Zoom.

{{box op="end"}}

### Calendar format list: `calendar` (Marcus Ng)

If you feel that the list command does not print the events in a format that you like,
you can use the calendar command to sort everything by date and time and view your events by date.

Format: ```calendar```

This brings you into calendar printing mode.
- To print the next date, simply press enter.
- To exit the calendar printing mode, simply type in ```q```.
- This mode is exited once you see ```End of calendar``` on pressing enter.

{{box op="start" cssClass="boxed noteBox"}}
**Note!**

* Events without date or time are not printed in the calendar
* In calendar printing mode, all input except ```q``` is ignored. In other words, commands cannot be executed until
you exit this mode

{{box op="end"}}

Expected output:

```
calendar
_________________________________
Calendar has 2 dates to display
1 event not on the calendar because it has no date and time
---------------------------------------------------------------------------------------
16 Sep 2020
---------------------------------------------------------------------------------------
Z | 9:00 PM | X | CS2113T Meeting | zoom.com.sg
---------------------------------------------------------------------------------------
q
End of calendar
_________________________________
```

### Deadlines: `deadline` (Qing Ning)
Want to set a deadline after you have created your personal event? Afraid that you forget your deadlines? Deadline is here to help! You can set the date and time of the task to be completed and on the day itself, we will remind you.  

Format: `deadline EVENT_INDEX; DD/MM/YY; [HH:MM] AM/PM`

- `EVENT_INDEX` is a number. It contains the index of the personal event that will have its deadline set or changed. 

* `DD/MM/YY` contains a date string in the format `[DD/MM/YY]`. This is the date of the deadline for the personal event. 

* `[HH:MM]` is an optional argument containing the time of the deadline for the personal event. It can be written in either 12-hour or 24-hour format. 

`deadline 2; 23/07/20` sets the deadline of event number 2 to be on 23 July 2020 a 

`deadline 7; 29/08/20; 14:25` sets the deadline of event number 7 to be on the 29 August 2020 at 2:25PM. 

`deadline 7; 29/08/20; 11:20 PM` sets the deadline of event number 7 to be on the 29 August 2020 at 11:20PM. 

Expected output: 
```
You have successfully updated the deadline for this event!
[P][X] sleep on 2020-08-29, 23:20
```

{{box op="start" cssClass="boxed noteBox"}}
**Note!**

* The time can be omitted entirely. If you leave time field as blank, the command would not set any time for the event.
* You may also omit the minutes in a time. If the minutes(MM) field of any time is empty, the time is read as when the hour begins (e.g. 4 pm would be taken as 4:00 pm)
* AM/PM is required for 12 hour format

{{box op="end"}}

{{box op="start" cssClass="boxed warningBox"}}
**Warning!**

The event index keyed in have to be valid so that deadline can be created for the specified event index.

{{box op="end"}}


### Repeat on daily/weekly/monthly basis: `repeat` (Colin Ng)
Sets a specific event to be repeated either weekly or monthly in the calendar. 

Do you have any events that repeat frequently? For example, a dental appointment every month, or perhaps a math lesson every week? The repeat command in the program allows you to easily schedule such events into your event planner. Just select the event that you want to repeat, type in how often this event repeats and Scheduler--; will record down the event for you.  

Format: `repeat EVENT_TYPE EVENT_INDEX [UNIT] [COUNT]` 

- `EVENT_TYPE` specify what kind of event you would like to repeat. The accepted arguments for this are “personal”, “timetable” and “zoom” 
- `EVENT_INDEX` is an number. It contains the index of the event that is to be repeated 
- `[UNIT]` can either take the word “Weekly” or “Monthly”. It tells the program to repeat the event either every week or every month. 
- `[COUNT]` takes an integer which indicates how many units of time will this event repeat itself. For instance, if the unit input is Weekly, placing a number 5 will repeat the event for 5 weeks. 

When the optional arguments of `[UNIT]` and `[COUNT]` are omitted in the code, repeat will instead display the repeat status of the event.  

#### How to use? 

We will use the example of repeating a personal event named “Dental Appointment” for 3 months from the start date. 

##### Step 1: Find the index number of the event to be repeated. 

You can do this by typing the command list personal into your application. The result for the following command is shown in the following photo. 

```
_________________________________
list personal
_________________________________
Here is a list of your Personal events:
1. [P][✕] party on 2000-10-09, 13:00
2. [P][✕] surprise on 2020-09-14, 08:00
3. [P][✕] hello there on 2002-02-02, 07:00
4. [P][✕] dental appointment on 2020-03-02, 15:00
_________________________________
```
In this case, the index number of our dental appointment is 4.

##### Step 2: Type the command into the terminal

In our example, the arguments are set as such:

- EVENT_TYPE is set to personal
- EVENT_INDEX is set to 4
- UNIT is set to monthly
- COUNT is set to 3

When you type in the command repeat personal 4 monthly 3, the program automatically notes that this event will repeat for three subsequent months at the same timing. The program reports that it has set the event to repeat as shown in the following image. 

```
repeat personal 4 monthly 3
_________________________________
[P][✕] dental appointment on 2020-03-02, 15:00
is now repeating monthly for 3 times
_________________________________
```

##### Step 3: Verify the dates that the event occurs
To check the status of the repeated event, simply key in repeat personal 4 in this case and you will obtain the status as shown in the following picture. 

```
repeat personal 4
_________________________________
[P][✕] dental appointment on 2020-03-02, 15:00 is also on:
02 Apr 2020 3:00 PM [✕]
02 May 2020 3:00 PM [✕]
02 Jun 2020 3:00 PM [✕]
_________________________________
```

{{box op="start" cssClass="boxed warningBox"}}
**Warning!**

When setting personal events to repeat, do take note that the personal event needs to contain a deadline before it cn be repeated. Use the dateline command to provide a deadline to personal events that you want to repeat, but does not have a deadline. 
{{box op="end"}}

Examples:

- `repeat personal 4 weekly 2` will repeat the personal event numbered 4 for another 2 weeks. Personal event 4 will app ear on the same day of the week for the next 2 weeks.  
- `repeat zoom 5 monthly 3` will repeat the zoom event numbered 5 for another 3 months. Zoom event 5 will appear on the same day of the month for the next 3 months.  
- `repeat personal 4` will show the repeat status of the event. In the following screenshot, running this command in the terminal reveals that the personal event will be repeated for 3 more times on a daily basis.  

```
repeat personal 4
_________________________________
[P][✕] eat dinner on 2020-01-09, 18:00 is also on:
10 Jan 2020 6:00 PM [✕]
10 Jan 2020 6:00 PM [✕]
10 Jan 2020 6:00 PM [✕]
_________________________________
```

### Check availability on a specific date and time: `check` (Marcus Tan)
Would you like to check if you happen to be free at a certain time? The check command allows you to scan through your events to check for any events you might have within a given time period.

Format: `check [START_DATE]; [START_TIME]; [END_DATE]; [END_DATE]`

Acceptable date formats: DD/MM/YYYY or DD/MM/YY

Acceptable time formats: 
- 24 Hour format – HHmm or HH:mm (e.g. 14:00)
- 12 Hour format – hh:mm am/pm or hhmm am/pm (e.g. 2:00 pm)

Example: `check 20/08/2020; 15:05; 25/08/2020; 13:00`

This will check from 20 August 2020 3:05pm to 25 August 2020 1pm. 

Expected result:
```
_________________________________
check 20/08/2020; 15:05; 25/08/2020; 13:00
_________________________________
You have no coinciding events!
_________________________________
```
{{box op="start" cssClass="boxed noteBox"}}
**Note!**

-	You may omit the DD or DD/MM in a date. If you do not fill in these fields for the date, the command takes the current date for that field by default (e.g.  input 2021 on 11 Oct 2020 would be taken as 11/10/2021)
-	The date can also be omitted entirely. If you leave a date field as blank, the command takes the current date for that field by default.
-	You may also omit the minutes in a time. If the minutes(MM) field of any time is empty, the time is read as when the hour begins (e.g. 4 pm would be taken as 4:00 pm)
-	The time can also be omitted entirely. If you leave a time field as blank, the command takes the current time by default.

{{box op="end"}}

{{box op="start" cssClass="boxed warningBox"}}
**Warning!**

- Even when you leave a time field (e.g. `[START_DATE]`) as blank, a semicolon (;) should still be used to denote the blank field (e.g. `check ; 2:00 pm; 25/12/2020; 2359`)
{{box op="end"}}


### Goal setting and viewing: `goal` (Marcus Ng)

Have a long term or short-term goal? The goal command helps you to keep track of it. 
You can use the goal command to print, set, remove or change your goal. 

Format: ```goal [GOAL]```

- `[GOAL]` specify the new goal you want to set.

When the optional argument `[GOAL]` is omitted, the current goal will be displayed.

When `[GOAL]` is specified as `delete`, the current goal will be removed.

{{box op="start" cssClass="boxed noteBox"}}
**Note!**

* You can use `na` or `nil` instead of `delete` to remove goal.

{{box op="end"}}

Examples:
- ```goal get cap 5.0``` will set your current goal as “get cap 5.0”. 
- ```goal save up $100``` after executing the above command will change your current goal to “save up $100”. 

Expected output:

```
goal save up $100
_________________________________
Goal changed to: save up $100
_________________________________
```

### Mark events as done: `done` (Marcus Tan)
If you would like to mark an event as done, you can use the done command to do so. 

Format: `done EVENT_TYPE EVENT_INDEX [EVENT_DATE]` 

Acceptable date formats: DD/MM/YYYY or DD/MM/YY 

If you wish to mark a specific event in a repeated task as done, you may enter the date of that repeated event.

Examples:
-	`done personal 1` marks the 1st Personal event as done.
-	`done personal 2 1/3/2020` if the 2nd Personal event is a repeated event with a repetition occurring on 1st March 2020, that repetition will be marked as done 

Expected output:
```
_________________________________
done personal 2 1/3/2020
_________________________________
You have successfully marked this event as done!
[P][✓] test 2 on 2020-03-01, 14:00
_________________________________
```

### Mark events as not done: `undone` (Marcus Tan)
Did you accidentally mark an event as done? Not to worry, just use the undone command to set the event status back to undone.

Format: `undone EVENT_TYPE EVENT_INDEX [EVENT_DATE]`

Acceptable date formats: DD/MM/YYYY or DD/MM/YY 

If you wish to mark a specific event in a repeated task as undone, you may enter the date of that repeated event.

Examples:
-	`undone zoom 3` marks the 3rd Zoom event as not done.
-	`undone personal 2 1/3/2020` if the 2nd Personal event is a repeated event with a repetition occurring on 1st March 2020, that repetition will be marked as undone 

Expected output:
```
_________________________________
undone personal 2 1/3/2020
_________________________________
You have successfully marked this event as undone!
[P][✕] test 2 on 2020-03-01, 14:00
_________________________________
```


### Deleting an event: `delete` (Marcus Tan)
Want to remove an event from your schedule? Use the delete command to get rid of unnecessary events.

Format: `delete EVENT_TYPE; EVENT_INDEX; [EVENT_DATE]`

Examples:
-	`delete personal 3` deletes the 3rd Personal event from Scheduler.

Expected output:
```
_________________________________
delete personal 3
_________________________________
You have successfully deleted this event!
[P][✕] test 3 on 2020-01-01
_________________________________
```

### Note Taking: `note` (Qing Ning)
Need to take last minutes meeting notes? Need to write some notes but don’t know where to keep them? Fear not! We have got you covered. Use this command to take notes and attach them to the event you are attending. You can also choose to open an empty note and type the notes. 

Format: `note EVENT_TYPE; EVENT_INDEX`

* The `EVENT_TYPE` have to be either personal, zoom or timetable.
* Scheduler will prompt you to type your notes
Example of usage: 

`note personal; 1 `

#### How to use?

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


{{box op="start" cssClass="boxed warningBox"}}
**Warning!**

The event index keyed in have to be valid so that deadline can be created for the specified event index.

{{box op="end"}}

### Reminder: `reminder` (Qing Ning)
Fear of forgetting what you have today? Scheduler—is here to show you your events and task to be completed for the day.  Cheers to no more missed deadlines and meetings! 

Format: `reminder`

Expected Output:
```
You have the following events today:
[T][X] math, Location: S17 on 2020-10-26, 12:00
[Z][X] math, Link: www.zoom.com/blah on 2010-10-26, 12:00
[P][X] sleep on 2020-10-26, 23:00
```
### Extracting events from texts: `extract` (Matthew Gani)
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
 
 •	It is also able to attach the current year to the date if it is not specified in the text.



### Save events: `events` (Colin)

### Exiting the program: `bye` 
Time to have a rest? See you later! Do not worry, we will keep your events in Scheduler--.

Format: `bye`


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


