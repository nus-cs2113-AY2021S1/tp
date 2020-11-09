# User Guide

WhereGotTime is an application for the student to check their timetable and compare them against their peers for a common timeslot to be able so that they can schedule a study time together

* Table of Contents
{:toc}

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
1. Down the latest version of  WhereGotTime.jar from [here](https://github.com/AY2021S1-CS2113-T13-3/tp/releases/tag/v2.1).
1. Copy the jar file into an empty folder you want to use as the home folder for your WhereGotTime.
1. Open a command window in that folder
1. Run the command java -jar {filename}.jar e.g., java -jar Duke.jar (i.e., run the command in the same folder as the jar file)
1. Type the command in the command box and press Enter to execute it. e.g. typing bye and pressing Enter will close the app.<br/>
Some example commands you can try:
    1. login /JohnSnow /123123: login as JohnSnow
    1. add /CS2113 Lec /mon /1600-1800 /LT23: Adds an event named CS2113 Lecture for current logged in the student (JohnSnow) to Time Table
    1. edit /mon <br/>
       /1 /1300-1400 : Edits first event named CS2113 on Monday for current logged in (JohnSnow) in the TimeTable
    1. list /all: list of all the classes that JohnSnow has in a new line, enumerated.
    1. find /CS2113: finds all the classes that JohnSnow has containing "CS2113"
    1. delete /mon /1 : Deletes the 1st class shown in the current list.
    1. clear /mon: Deletes all classes in the current list for JohnSnow for his Monday timetable
    1. remove /JohnSnow /123123: Remove JohnSnow and all his timetable

## Features 
* Login user
* Remove user
* Add timetable
* Edit timetable
* List timetable
* Compare timetable
* Find class
* Delete timetable
* Clear timetable
* Help command
* Exit program

### Main menu
The greeting screen of WhereGotTime is preceded by Storage initialisation messages that indicate if previously saved data, if any, is 
loaded successfully.

For example, if this is your first time running the application, the expected greeting screen is as follows:
```
Data folder not found! Creating one...
No existing WhereGotTime.txt found! Creating one...
Storage initialisation completed without issue.
____________________________________________________________
 _    _ _                   _____       _ _____ _                
| |  | | |                 |  __ \     | |_   _(_)               
| |  | | |__   ___ _ __ ___| |  \/ ___ | |_| |  _ _ __ ___   ___ 
| |/\| | '_ \ / _ \ '__/ _ \ | __ / _ \| __| | | | '_ ` _ \ / _ \
\  /\  / | | |  __/ | |  __/ |_\ \ (_) | |_| | | | | | | | |  __/
 \/  \/|_| |_|\___|_|  \___|\____/\___/ \__\_/ |_|_| |_| |_|\___|
                                                                 
Hello! Welcome to WhereGotTime, a special timetable program that helps 
you and your friend find common unoccupied slots in the timetable!

You're currently not logged in.

Tip: enter 'help' for a list of commands.
____________________________________________________________
```

Note that the Storage initialisation message is dependent on the status of WhereGotTime.txt, for example, if this is second time using the application,
and provided that the folder containing the previously stored information is not altered, the expected output is as follows:
```
Data folder found! Finding WhereGotTime.txt...
Existing WhereGotTime.txt found. Loading previously saved timetable information...
Timetable information loaded successfully!
Storage initialisation completed without issue.
```



### Viewing help menu: `help`
Whenever you require assistance and want to see a list of valid commands and their input format, type `help` in the console and press Enter.

Expected outcome:
```
____________________________________________________________
You're currently not logged in.
Here are the list of commands for WhereGotTime.
	1. Login command	: login /(username) /(6-digit password)
	2. Add command		: add /(module name with optional descriptions) /(day) /(startTime-endTime) /(location)
	3. List command		: list /all OR list /(day)
	4. Edit command		: edit /(day)
	5. Delete command	: delete /(day) /(index)
	6. Clear command	: clear /(day) OR clear /all
	7. Find command		: find /(keyword)
	8. Compare command	: compare
	9. Remove command	: remove /(username) /(6-digit password)
	10. Bye command		: bye

Note:
- the brackets shown above should be omitted when entering commands
- if this is your first time using WhereGotTime, using the Login command would 
  create a new user profile that matches (username) and (6-digit password)
- command and 'day' are not case sensitive, but username and password are.
- startTime and endTime should be in 24-hour format and in 1-hour block. e.g. 0900, 1300, 2300, etc.
- 'day' should be 3-letter, e.g. Mon, TUE, wed, etc.
____________________________________________________________
```

Tips:
- `help` is invalid in `edit` and `compare` modes due to the presence of intuitive on-screen prompts to guide you through the process.
- if you're logged in, entering `help` will also display your username.

### Login user: `login`
Creates a new User with inputted username and password (if first time user).
Logs the existing user back into the app. (existing user)

Format: 

`login /(username) /(6-digit password)`

Example of usage: 

`login /JohnSnow /123456`

Expected Output:
```
____________________________________________________________
 _    _ _                   _____       _ _____ _                
| |  | | |                 |  __ \     | |_   _(_)               
| |  | | |__   ___ _ __ ___| |  \/ ___ | |_| |  _ _ __ ___   ___ 
| |/\| | '_ \ / _ \ '__/ _ \ | __ / _ \| __| | | | '_ ` _ \ / _ \
\  /\  / | | |  __/ | |  __/ |_\ \ (_) | |_| | | | | | | | |  __/
 \/  \/|_| |_|\___|_|  \___|\____/\___/ \__\_/ |_|_| |_| |_|\___|
                                                                 
Hello! Welcome to WhereGotTime, a special timetable program that helps 
you and your friend find common unoccupied slots in the timetable!

You're currently not logged in.

Tip: enter 'help' for a list of commands.
____________________________________________________________
login /JohnSnow /123456
____________________________________________________________
Hello JohnSnow!
____________________________________________________________
```

### Remove user: `remove`
Remove existing User and their timetable with inputted username and password.

Format: 

`remove /username /password(6-digit)`

Example of usage: 

`remove /JohnSnow /123456`

Expected Output:
```
____________________________________________________________
remove /JohnSnow /123456
____________________________________________________________
JohnSnow has been removed.
____________________________________________________________
```

### Adding a timetable: `add`
Adds a new timetable to the list of timetable arraylist.

Format: 

`add /(module name with optional descriptions) /(day) /(startTime-endTime) /(location)`
  
Example of usage: 

`add /CS2113 Lec /Mon /0900-1200 /LT33`

`add /CS2040C Tut /MON /1300-1500 /COM1-2`

`add /CG2028 Tut /wed /1200-1300 /E4-4-1`

Expected Output:
```
____________________________________________________________
add /CS2113 Lec /Mon /0900-1200 /LT33
____________________________________________________________
Got it! I've added the following event on mon
CS2113 Lec LT33 0900-1200
____________________________________________________________
add /CS2040C Tut /MON /1300-1500 /COM1-2
____________________________________________________________
Got it! I've added the following event on mon
CS2040C Tut COM1-2 1300-1500
____________________________________________________________
add /CG2028 Tut /wed /1200-1300 /E4-4-1
____________________________________________________________
Got it! I've added the following event on wed
CG2028 Tut E4-4-1 1200-1300
____________________________________________________________
```


### Editing a timetable : `edit`
This command allows the currently logged-in user to edit a lesson's time, provided the lesson exists.
The newly provided timings will be subject to a series of checks (stated in the conditions below), 
before a successful edit can be allowed.

Conditions:
- the user must first be logged in
- there should be at least one lesson in that `day`
- newStartTime and newEndTime will be rejected if:
    1. either one is not in 4-digit format (e.g. 09000)
    2. either one is not in 1-hour block (e.g. 0903)
    3. either one has invalid hours and minutes (e.g. 2861, 0989)
    4. both are identical (e.g. 0900-0900)
    5. start time is later than end time (e.g. 1100-0900)
    

Format:

`edit /day`

`/(index) /(newStartTime-newEndTime)`

Example of usage:

`edit /mon`

`/2 /1400-1500`

`edit /wed`

`/1 /1100-1300`

Expected Output:
```
____________________________________________________________
edit /mon
____________________________________________________________
Hey JohnSnow, here are the lessons in your mon timetable, sorted from the earliest class.
    1. CS2113 Lec LT33 0900-1200
    2. CS2040C Tut COM1-2 1300-1500

To edit, enter:
/(index) /(newStartTime-newEndTime)
____________________________________________________________
/2 /1400-1500
Got it! I have edited the lesson as follows:
ORIGINAL    : CS2040C Tut COM1-2 1300-1500
EDITED      : CS2040C Tut COM1-2 1400-1500
____________________________________________________________
edit /wed
____________________________________________________________
Hey JohnSnow, here are the lessons in your wed timetable, sorted from the earliest class.
    1. CG2028 Tut E4-4-1 1200-1300

To edit, enter:
/(index) /(newStartTime-newEndTime)
____________________________________________________________
/1 /1100-1300
Got it! I have edited the lesson as follows:
ORIGINAL    : CG2028 Tut E4-4-1 1200-1300
EDITED      : CG2028 Tut E4-4-1 1100-1300
____________________________________________________________
```

Note: if you do not have a lesson on that day, for example if you enter `edit /fri`, the expected output is:
```
____________________________________________________________
edit /fri
____________________________________________________________
Hey JohnSnow, there is no class in your fri timetable!
____________________________________________________________
```

Note: if the new timings are identical to the lesson's original timings, the expected output is:
```
____________________________________________________________
edit /mon
____________________________________________________________
Hey Alex, here are the lessons in your mon timetable, sorted from the earliest class.
    1. CS2113 NUS 0900-1200
    2. CS2040C NUS 1400-1500

To edit, enter:
/(index) /(newStartTime-newEndTime)
____________________________________________________________
/1 /0900-1200
:( OOPS!!! You have entered a timing that is exactly the 
same as the original one! Hence, no changes were made!
____________________________________________________________
```


### Listing a timetable: `list`
Lists all the classes on a particular day or on all days

Format: 

`list /all` or `list /(day)`

Example of usage:

`list /mon`

`list /all`

Expected Output:
```
____________________________________________________________
list /all
____________________________________________________________
Here are the classes in your timetable for mon, sorted according to time:
    1. CS2113 Lec LT33 0900-1200
    2. CS2040C Tut COM1-2 1400-1500

There is no class in your timetable for tue!

Here are the classes in your timetable for wed, sorted according to time:
    1. CG2028 Tut E4-4-1 1100-1300

There is no class in your timetable for thu!

There is no class in your timetable for fri!

There is no class in your timetable for sat!

There is no class in your timetable for sun!
____________________________________________________________
```

### Comparing timetables: `compare`
Compare the timetables between current user and another user and returns a range of common available free timeslots.<br/>

Scenario
- assuming the currently logged in user is JohnSnow, and he didn't clear his monday timetable as shown above
- assuming there is another user named "WhiteWalker" with his own set of monday timetable
- to compare the currently logged in user (i.e. "JohnSnow") vs "WhiteWalker"

Format: <br/>

`compare`<br/>
`TargetUser's index`<br/>
`day`<br/>
  
Example of usage: <br/>

`compare`<br/>
`1`<br/>
`mon`<br/>

Expected output: <br/>
```
____________________________________________________________
compare
____________________________________________________________
Hey JohnSnow, please enter the index number of the user that you would like to compare with.
____________________________________________________________
	1. WhiteWalker
____________________________________________________________
1
____________________________________________________________
Please input the day (Mon-Sun) that you would like to compare.
____________________________________________________________
mon
Your common unoccupied timeslots are: 

0000HR to 0959HR
1300HR to 2359HR
____________________________________________________________
```

### Finding a class: `find`
Lists all the classes based on the keyword entered by user

Note: the keyword is case-sensitive.

Format: 

`find /(keyword)`

Example of usage:

`find /CG2028`

`find /Tut`

Expected output:
```
____________________________________________________________
find /CG2028
____________________________________________________________
Here are the class(es) in your timetable that matches the keyword "CG2028":
1. (Wednesday) CG2028 Tut E4-4-1 1100-1300
____________________________________________________________
find /Tut
____________________________________________________________
Here are the class(es) in your timetable that matches the keyword "Tut":
1. (Monday) CS2040C Tut COM1-2 1400-1500
2. (Wednesday) CG2028 Tut E4-4-1 1100-1300
____________________________________________________________
```

### Deleting a class: `delete`
Deletes a class as specified by the user 

Format: 

`delete /(day) /(index)`

Example of usage:

`delete /wed /1`

Expected Output:
```
____________________________________________________________
delete /wed /1
____________________________________________________________
Noted. I have removed this class from your timetable:
CG2028 Tut E4-4-1 1100-1300
Now you have 0 class(es) for wed in the timetable.
____________________________________________________________
```

### Clearing timetable: `clear`
Clears all the classes on the day (or all days) as specified by the user 

Format: 

`clear /(day)` or `clear /all`

Example of usage:

`clear /wed`

`clear /all`

Expected Output:
```
____________________________________________________________
clear /wed
____________________________________________________________
:( OOPS!!! There is no class in your timetable for wed!
____________________________________________________________
clear /all
____________________________________________________________
I have removed these classes from your mon timetable:
	1. CS2113 Lec LT33 0900-1200
	2. CS2040C Tut COM1-2 1400-1500
Your timetable has been cleared.
____________________________________________________________
```

### Exiting the program: `bye`
Exits the program<br/>

Format: 

`bye`

Expected output:
```
____________________________________________________________
Thanks for using WhereGotTime. Hope to see you again soon!
____________________________________________________________
```

## Command Summary
* Login user 

    `login /(username) /(6-digit password)`
* Add timetable 

    `add /(module name with optional descriptions) /(day) /(startTime-endTime) /(location)`
* Edit timetable
 
    `edit /(day)` <br/>
    `/(index) /(newStartTime-newEndTime)`
* List timetable 
    
    `list /all` OR `list /(day)`
* Compare timetables 
    
    `compare`
* Find 

    `find /(keyword)`
* Delete timetable 
    
    `delete /(day) /(index)`
* Clear timetable 
    
    `clear /(day)` OR `clear /all`
* Help Command 
    
    `help`
* Remove user 

    `remove /(username) /(6-digit password)`  
* Exit program 
    
    `bye`  
