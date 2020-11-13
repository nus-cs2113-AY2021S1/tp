# User Guide
![](CCAManager_logo.png) 

<div style="page-break-after: always;"></div>

# Table of contents

- [1. Introduction](#1-introduction)
- [2. About this User Guide](#2-about-this-user-guide)
- [3. Quick Start](#3-quick-start)
- [4. Features](#4features)
  - [4.1 Help `help`](#41-help-help)
  - [4.2 Exit the program: `bye`](#42-exit-the-program-bye)
  - [4.3 HR features `HR`](#43-hr-features-hr)
    - [Add members: `addMember`](#add-members-addmember)
    - [Delete members: `delMember`](#delete-members-delmember)
    - [List members: `listMember`](#list-members-listmember)
    - [Change member information: `changeInfo`](#change-member-information-changeinfo)
    - [Search members: `search`](#search-members-search)
    - [View contacts of prof/admin: `list prof&admin`](#view-contacts-of-profadmin-list-profadmin)
    - [View contacts of connections: `list connections`](#view-contacts-of-connections-list-connections)
  - [4.4 Event features`event`](#44-event-featuresevent)
    - [Add an event: `addEvent`](#add-an-event-addevent)
    - [Delete an event: `delEvent`](#delete-an-event-delevent)
    - [Clear all events: `delEvent all`](#clear-all-events-delevent-all)
    - [List events: `listEvent`](#list-events-listevent)
    - [Countdown events `countdown`](#countdown-events-countdown)
    - [Mark an event as completed `done`](#mark-an-event-as-completed-done)
    - [Search for an Event `search`](#search-for-an-event-search)
    - [Add a participant to an event: `addAttendance`](#add-a-participant-to-an-event-addattendance)
    - [Delete a participant from an event: `delAttendance`](#delete-a-participant-from-an-event-delattendance)
    - [List participants in an event: `listAttendance`](#list-participants-in-an-event-listattendance)
  - [4.5 Finance features `finance`](#45-finance-features-finance)
    - [4.5.1 Add finance log entry: `addLog`](#451-add-finance-log-entry-addlog)
    - [4.5.2 Delete finance log entry: `delLog`](#452-delete-finance-log-entry-dellog)
    - [4.5.3 View financial summary: `summary`](#453-view-financial-summary-summary)
    - [4.5.4 Change finance log entry information: `changeLog`](#454-change-finance-log-entry-information-changelog)
  - [4.6 Import](#46-import)
  - [4.7 Saving the data](#47-saving-the-data)
- [5. FAQ](#5-faq)
- [6. Command Summary](#6-command-summary)
- [7. Glossary](#7-glossary)

<div style="page-break-after: always;"></div>

## 1. Introduction

Welcome to **CCA Manager!**
CCA Manager is a revolutionary tool that changes the way you can manage interest groups with unrivaled efficiency and simplicity. Its lightweight Command Line Interface (CLI) allows administrators to breeze through tasks quickly and easily while offering powerful features to advanced users.

**Feature Overview**

* Allows you to manage a HR list of members and contacts
* Allows you to create events and record member attendance for events
* Allows you to track the financial spending and budgeting of your interest group
* You can import data from other programs
* Shorthand Commands allows you to quickly enter commands by reducing the amount of typing required
* Relative Time allows you to quickly specify the day of the event without having to type in the full date format

### 2. About this User Guide 
(By: Varsha)

This user guide provides a quick start guide for you to easily setup install CCA Manager, 
documentation of all the features that CCA Manager offers, frequently asked questions and a summary of the available commands.
To navigate between the different sections, you could use the table of contents above.

Additionally, throughout this user guide, there will be various icons used as described in the legend below to provide you useful information on using the app:
> :bulb: This symbol denotes a tip which you might find useful when using the application.

> :information_source: This symbol denotes some information that you will need to take note of when using the application.

> :`code` : Text that appears on the CLI / in code

> : [Hyperlinked]() : Leads you to the appropriate section

<div style="page-break-after: always;"></div>

## 3. Quick Start
(By: Varsha)

The following steps will guide you through the process of running CCA Manager.  <br/>
 :one: Ensure that you have Java 11 or above installed.
  > :bulb: To check the version of Java on your computer, follow the instructions [here](https://www.wikihow.com/Check-Your-Java-Version-in-the-Windows-Command-Line). <br>
  > :bulb: To download the latest version of Java on your computer, visit [here](https://www.oracle.com/java/technologies/javase-downloads.html). <br>
  
 :two: Download the latest version of CCA manager from  [here](https://github.com/AY2021S1-CS2113T-F14-1/tp/releases) <br>
 
 :three: Navigate to the folder where you downloaded CCA Manager. Run the program by entering `java -jar CCAManager.jar` in a terminal. As seen in the figure below, the red arrow points to where you have to type in commands.
 
 > :information_source: To launch a terminal on Windows, open the start menu, type "Command Prompt" to search for the terminal and run it.   
> On MacOS, you can find the Terminal in the `/Applications/Utilities` folder. Double click Terminal to launch it.  
> On linux operating systems, the most common shortcut to opening the Terminal is CTRL+ALT+T.  
> To navigate to the folder with CCA Manager, use the command `cd <FOLDER NAME/PATH>` to go in to the folder. You can use `cd ..` to go back one level if you made a mistake.

 
 ![](userGuidePic/terminal.PNG)

You will see this welcome message if you have done the above steps correctly. <br/>

![Welcome Message](userGuidePic/welcomeMessage.PNG)

<div style="page-break-after: always;"></div>

## 4. Features 

This section will show the features of our program in detail.
You can follow along as we go through each of the features if you are starting out, or skip ahead to the Command Summary
to see the commands of all features at a glance.

#### Command Format 

Words in UPPER CASE are the parameters to be supplied by user.
> :information_source: Our program also supports shorthand commands.  You can use shorthand command format for faster access. Instead of typing the word in its entirety, you can use the shorthand replacement instead.
For example, as shown in the picture below, instead of typing `event`, you can type just the letter `e`. Both serve the same functionality.

![](userGuidePic/shorthandexample.PNG)


### 4.1 Help `help`
Shows a list of available commands.<br/>

This command allows you to view a summary of all the available commands and how to use them.  
Format: `help`

Expected Outcome: 

![helpCommand](userGuidePic/helpCommand.PNG)

[Return to the top](#user-guide)

### 4.2 Exit the program: `bye` 
Exits the program.

With this command you can exit the program directly from the terminal without closing it. <br/>
Format: `bye`

[Return to the top](#user-guide)

### 4.3 HR features `HR` 
#### Add members: `addMember`
(By:Ye Yutong)  

Allows you to add a member to the list of members.<br/>

Format: `hr addMember /n NAME /p PHONE_NUMBER /e EMAIL /r ROLE`<br/>

Shorthand Format:  
`hr` -> `h`  
`addMember` -> `a`/`add`  

Example of usage: <br/>
 `hr addMember /n John Sterling /p 88888888 /e js@gmail.com /r president`<br/>
 `hr add /n Harry Potter /p 12345678 /e H_P@gmail.com /r member`<br/>
 `hr a /n sakata Gintoki /p 999999999 /e Ginsan@gmail.com /r member`<br/>
 
 Expected Outcome:
 
 ![HrAddMemberCommand](userGuidePic/hrAddMemberCommand.PNG)
 
 [Return to the top](#user-guide)
 
#### Delete members: `delMember`
(By:Ye Yutong)  

Allows you to delete a member from the list of members. <br/>
Format: `hr delMember MEMBER_INDEX` <br/>
Deletes the member at the specified MEMBER_INDEX.<br/>

The MEMBER_INDEX refers to the index number shown in the list of members.<br/>
The MEMBER_INDEX must be an integer greater than 0. <br/>

Shorthand Format:  
`hr` -> `h`  
`delMember` -> `d`/`delete`  

Example of usage: <br/>
`hr delMember 1` <br/>
`hr delete 19` <br/>
`hr d 5` <br/>

 Expected Outcome:
 
 ![HrDelMemberCommand](userGuidePic/hrDelMemberCommand.PNG)
 
 [Return to the top](#user-guide)

#### List members: `listMember`
(By:Ye Yutong)  

You can use this command to view the list of members, based on the order in which they are added into the list. 
Format: `hr listMember` <br/>

Shorthand Format:  
`hr` -> `h`  
`listMember` -> `l` / `list`  

Example of usage: <br/>
`hr listMember`<br/>
`hr list`<br/>
`hr l`<br/>

 Expected Outcome:
 
 ![HrListMemberCommand](userGuidePic/hrListMemberCommand.PNG)
 
 [Return to the top](#user-guide)

#### Change member information: `changeInfo`
(By:Ye Yutong)  

You can use this command to change contact information and role of member in the list, based on the given member name. 
Format: `hr changeInfo /n MEMBER_NAME (/p PHONE_NUMBER) (/e EMAIL) (/r MEMBER_ROLE)` <br/>

MEMBER_NAME and at least one of PHONE_NUMBER, EMAIL and MEMBER_ROLE must be provided. 

Shorthand Format:  
`hr` -> `h`  
`changeInfo` -> `c`  

Example of usage: <br/>
`hr changeInfo /n john sterling /p 12345678` <br/>
`hr c /n Harry Potter /p 12345678 /e 123@gmail.com /r President` <br/>

 Expected Outcome:
 
 ![HrChangeInfoCommand](userGuidePic/hrChangeInfoCommand.PNG)
 
 [Return to the top](#user-guide)

#### Search members: `search`  
(by: Wang Zixin)  
You can use this command to search for a particular member.  
The search command will return the member that matches the criteria you specify.  
:bulb: If a member's information matches any of the conditions that the user inputs, 
it will return this member.  
Format: `hr search ITEM /n ITEM /p ITEM /e ITEM /r ITEM`  
:bulb: User can choose any parts of conditions above to search.  
:bulb: The ITEM without any symbol in front means searching the key from any parts of `Member`'s information.  

Shorthand Format:  
`hr` -> `h`  
`search` -> `s`  

Example of usage:  
`hr search peter`  
`hr search /n peter /r president`   

Expected outcomes:  
![Example of usage 1](userGuidePic/hrsearchoutcome1.png)  

![Example of usage 2](userGuidePic/hrsearchoutcome2.png)  

[Return to the top](#user-guide)  


#### View contacts of prof/admin: `list prof&admin`  
(by: Wang Zixin)  
You can use this command as a shortcut to view the contacts of the professors and administrators.  
:bulb: Professor/prof and administrator/admin are roles of members.  
Format: `hr list prof&admin`  

Shorthand Format:  
`hr` -> `h`  
`list` -> `l`  
`prof&admin` -> `pa`  

Example of usage:  
`hr list prof&admin`  

Expected outcome:  
![Example of usage](userGuidePic/hrprofadminoutcome.png)  

[Return to the top](#user-guide)  


#### View contacts of connections: `list connections`  
(by: Wang Zixin)  
You can use this command as a shortcut to list the contacts of connections(alumni, speakers).  
:bulb: Alumni and speaker are roles of members.  
Format: `hr list connections`  

Shorthand Format:  
`hr` -> `h`  
`list` -> `l`  
`connections` -> `c`  

Example of usage:  
`hr list connections`  

Expected outcome:  
![Example of usage](userGuidePic/hrlistc.png)  

[Return to the top](#user-guide)  


### 4.4 Event features`event` 
(By: Varsha)

#### Add an event: `addEvent`
Allows you to add an event to the list of events.<br/>
Format: `event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME`<br/>

> :information_source: The EVENT_INDEX refers to the index number shown in the list of events.<br/>
> :information_source: The EVENT_INDEX must be an integer greater than 0. <br/>
> :information_source: The EVENT_DATE entered should be in the format YYYY-MM-DD. <br/>
> :information_source: The EVENT_TIME entered should be in the 24-hour clock format (HH-mm). <br/>


> :bulb: You can use Relative Time by specifying date of week for convenience. For example `/d next friday` will schedule the event on the next friday from current day.


Shorthand Format: <br/>
`event` -> `e` <br/>
`addEvent` -> `add`/`a` <br/>

Example of usage: <br/>
 `event addEvent /n arduino course /d 2020-12-30 /t 08-00`<br/>
 `event add /n arduino course /d 2020-12-30 /t 23-59`<br/>
 `event a /n arduino course /d 2020-12-30 /t 08-00`<br/>
 `e a /n arduino course /d 2020-12-30 /t 23-59`<br/>
 `e a /n arduino course /d next friday /t 00-00`<br/>
 
Example scenario: <br/>

Let's say you want to add an event called `arduino course` on `2000-12-30` at time `08-00`. 
Type `event addEvent /n arduino course /d 2020-12-30 /t 08-00` into the terminal and press enter to execute the command as shown below.

![](userGuidePic/eventAdd1.png)

The result will be a message that the event has been successfully added as shown in the figure below.

![EventAddCommand](userGuidePic/eventAddCommand.PNG)

[Return to the top](#user-guide)
 
#### Delete an event: `delEvent`
(By: Varsha)

 Allows you to delete an event from the list of events.  <br/>
 Format: `event delEvent EVENT_INDEX` <br/>
 It Deletes the event at the specified EVENT_INDEX.<br/>
 
> :information_source: The EVENT_INDEX refers to the index number shown in the list of events.<br/>
> :information_source: The EVENT_INDEX must be an integer greater than 0. <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>
`addEvent` -> `del`/`d` <br/>

Example of usage:     
`event delEvent 1` <br/>
`event del 1` <br/>
`event d 19` <br/>
`e d 1` <br/>
 
Example scenario: <br/> 

Let's say you want to remove the event that you have entered above. i.e. `event addEvent /n arduino course /d 2020-12-30 /t 08-00`
To delete, type `event delEvent 1` into the terminal and press enter as shown in the figure below.


![](userGuidePic/eventdel1.PNG) 

The result will be a message that the event has been removed successfully as shown in the figure below.
 
![EventDeleteAtIndex](userGuidePic/eventDelEvent.PNG)
 
 [Return to the top](#user-guide)

#### Clear all events: `delEvent all`
(By: Varsha)

Allows you to clear all events from the list of events. <br/>
Format: `event delEvent all`

Shorthand Format: <br/>
`event` -> `e` <br/>
`delEvent` -> `del`/`d` <br/>

Example of usage:   
`event delEvent all` <br/>

Example scenario: <br/> 
You may want to clear all the events in your list. To do so, type in `event delEvent all` into the terminal and press enter. It will prompt you with a confirmation message.
Type in `Y` to clear the list as shown below.

![EventDeleteAll](userGuidePic/eventDeleteAll.PNG)

[Return to the top](#user-guide)

#### List events: `listEvent`
(By: Varsha)

You can use this command to view the list of events, based on the order in which they are added into the list. <br/>
Format: `event listEvent` <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>
`listEvent` -> `l` <br/>

Example of usage: <br/>
`event listEvent` <br/>
`event l` <br/>
`e l` <br/>

Expected Outcome:

![EventListEvent](userGuidePic/eventListEvent.PNG)

[Return to the top](#user-guide)

#### Countdown events `countdown`
(By: Varsha)

You can use this command to show the number of days remaining until an event. It also sorts them so that the event due first will be displayed first.<br/>
Format: `event countdown` <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>
`countdown` -> `c` <br/>

Example of usage: <br/>
`event countdown` <br/>
`e countdown` <br/>
`e c` <br/>

Expected Outcome:

![eventCountdown](userGuidePic/eventCountdown.PNG)

[Return to the top](#user-guide)

#### Mark an event as completed `done`
(By: Varsha)

Allows you to mark an event as done. <br/>
Format: `event done EVENT_INDEX` <br/>

> :information_source: The EVENT_INDEX refers to the index number of the event that is to be marked as completed.<br/>
> :information_source: The EVENT_INDEX must be an integer greater than 0. <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>

Example of usage: <br/>
`event done 1` <br/>
`e done 1` <br/>

Example scenario: <br/>
Let's say you have added one event to your list and the event is over. You can mark it as complete by entering `event done 1`in the terminal. The status will change 
from `Up-coming` to `Done` as shown below.

Expected Outcome: <br/>

![](userGuidePic/eventMarkAsDone.PNG)

[Return to the top](#user-guide)

#### Search for an Event `search`
(By: Varsha)

Allows you to search for a particular event by name or date. <br/>
Format: 'event search /s KEYWORD' <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>
`search` -> `s` <br/>

Example of usage: <br/>
`event search /s arduino course` <br/>
`event search /s 2000-12-16` <br/>
`e search /s 2000-12-16` <br/>
`e s /s 2000-12-16` <br/>

Expected Outcome:

The output when you search by name is as follows: <br/>
![EventSearchByName](userGuidePic/eventSearchBydate.PNG) 

The output when you search by date is as follows:  <br/>
![EventSearchByDate](userGuidePic/eventSearch1.PNG)


> :information_source: Notice that both return the same output.
>
[Return to the top](#user-guide)

#### Add a participant to an event: `addAttendance`
(By:Ye Yutong)  

Allows you to add a participant to an event in the list of events.<br/>
Format: `event addAttendance /n EVENT_NAME /m MEMBER_NAME`<br/>
 
> :information_source: Both the `EVENT_NAME` and the `MEMBER_NAME` need to be added into the list of events and members prior to using this feature. <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>
`addAttendance` -> `aa`/`addAttend` <br/>

Example of usage: <br/>
 `event addAttendance /n arduino course /m peter`<br/>
 `event addAttend /n Autodesk course /m John`<br/>
 `event aa /n Machine Learning course /m John`<br/>
 
Expected Outcome:

![EventAddAttendanceCommand](userGuidePic/eventAddAttendanceCommand.PNG)

[Return to the top](#user-guide)
 
#### Delete a participant from an event: `delAttendance`
(By:Ye Yutong)  

Allows you to delete a participant from an event in the list of events. <br/>
 Format: `event delAttendance /n EVENT_NAME /m MEMBER_NAME` <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>
`delAttendance` -> `da`/`delAttend` <br/>

 Example of usage:    
 `event delAttendance /n arduino course /m peter`<br/>
 `event delAttend /n Autodesk course /m John`<br/>
 `event da /n Machine Learning course /m John`<br/>
 
 Expected Outcome: 
 
 ![EventDeleteAttendanceCommand](userGuidePic/eventDeleteAttendanceCommand.PNG)
 
 [Return to the top](#user-guide)

#### List participants in an event: `listAttendance`
(By:Ye Yutong)  

Allows you to view the list of participants in an event, based on the order in which they are added into the list.   
Format: `event listAttendance /n EVENT_NAME` <br/>

Shorthand Format: <br/>
`event` -> `e` <br/>
`listAttendance` -> `la`/`listAttend` <br/>

Example of usage:   
`event listAttendance /n arduino course`
`event listAttend /n Autodesk course`
`event la /n Machine Learning course`

Expected Outcome:

![EventListAttendanceCommand](userGuidePic/eventListAttendanceCommand.PNG)

[Return to the top](#user-guide)

### 4.5 Finance features `finance`  
(by: Wang Zixin)  
#### 4.5.1 Add finance log entry: `addLog`  
Allows you to add an entry into the finance log.  
Format: `finance addLog ITEM_NAME ITEM_VALUE`  

Shorthand Format:  
`finance` -> `f`  
`addLog` -> `add`/`a`  

Example of usage:  
`finance addLog have lunch 4.5`  
`f addlog buy flight ticket 750`  

Expected outcomes:  
![Example of usage 1](userGuidePic/addlog%20outcome1.png)  

![Example of usage 2](userGuidePic/addlog%20outcome2.png)  

[Return to the top](#user-guide)  


#### 4.5.2 Delete finance log entry: `delLog`  
Allows you to remove an entry from finance log.  
Format: `finance delLog ITEM_INDEX`  

Shorthand Format:  
`finance` -> `f`  
`delLog` -> `del`/`d`

Example of usage:  
`finance dellog 3`  
`f del 1`  

Expected outcomes:  
![Example of usage 1](userGuidePic/dellog%20outcome1.png)  

![Example of usage 2](userGuidePic/dellog%20outcome2.png)  

[Return to the top](#user-guide)  


#### 4.5.3 View financial summary: `summary`  
This command allows you to view a summary of the financial log and shows the total amount of money expended.  
Format: `finance summary`  

Shorthand Format:  
`finance` -> `f`  
`summary` -> `s`/`l`

Example of usage:  
`finance summary`  

Expected Outcome:  
![Example of usage](userGuidePic/summary%20outcome.png)  

[Return to the top](#user-guide)  


#### 4.5.4 Change finance log entry information: `changeLog`  
Allows you to change the finance log entry's name and budget amount.  
Format: `finance changeLog /i INDEX /n ITEM_NAME ITEM_VALUE`  

Shorthand Format:  
`finance` -> `f`  
`changeLog` -> `c`

Example of usage:  
`finance changeLog /i 1 /n buy cake 5.5`  

Expected Outcome:  
![Example of usage](userGuidePic/FinanceChangeLog%20Outcome.png)  

[Return to the top](#user-guide)


### 4.6 Import

You can import data from other existing CSVs quickly with the import command.  
Format: `import FILENAME /c finance /name HEADER_NAME /value HEADER_NAME`  
OR `import FILENAME /c hr /name HEADER_NAME /phone HEADER_NAME /email HEADER_NAME /role HEADER_NAME`  
OR `import FILENAME /c event /name HEADER_NAME /date HEADER_NAME /time HEADER_NAME`

This command allows you to specify which columns of your CSV file you would like to copy over to the program.  

Example: Let's say you have a CSV file like shown below, and you would like to import it.    
![CSV File](userGuidePic/import1.png)  
In our example, the CSV file we are importing is in the same directory as CCA Manager.  
![Location of File](userGuidePic/import2.png)  
You can use the command `import sample.csv /c hr /name Member /phone Contact /email Email /role Role` to import the document.  

The expected outcome is as follows:  

![Outcome](userGuidePic/import3.png)  

If you would like to try out importing a file with a sample, we provide a sample file similar to the one shown in the above example.  
You can download the sample [here](https://raw.githubusercontent.com/AY2021S1-CS2113T-F14-1/tp/master/docs/sample.csv).

[Return to the top](#user-guide)

### 4.7 Saving the data
(By: Varsha)

CCA Manager saves all your data after every command. There is no need to save manually.

[Return to the top](#user-guide)

## 5. FAQ

This section details the frequently asked questions (FAQ) regarding the use of the application.

**Q** How do I transfer my data to another computer? <br/>
**A** Install CCA Manager in the other computer and overwrite the empty data files it creates with the file that contains the data from your previous computer. <br/>
You can find the data files in the "data" folder inside the folder that CCA Manager is running in.  

**Q** Is CCA Manager optimized for all Operating Systems(OS)? <br/>
**A** CCA Manager is available for download on all major Operating Systems such as Windows, Mac and Linux.

**Q** Where can I find the release?  
**A** You can find it here [Download V2.1](https://github.com/AY2021S1-CS2113T-F14-1/tp/releases/tag/v2.1).




[Return to the top](#user-guide)

## 6. Command Summary

The following table provides a summary of all features and command formats.


Command | Format | Example
------- | ---------- | ------------
addAttendance  | `event addAttendance /n EVENT_NAME /m MEMBER_NAME` | `event addAttendance /n arduino course /m Peter`<br/>
addMember  | `hr addMember /n NAME /p PHONE_NUMBER /e EMAIL /r ROLE` | `hr addMember /n Harry /p 12345678 /e HP@gmail.com /r member`<br/>
addEvent | `event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME` | `event addEvent /n arduino course /d 2020-09-16 /t 8pm`<br/>
addLog | `finance addLog ITEM_NAME ITEM_VALUE` | `finance addLog have lunch 4.5`
bye | `bye` | -
changeInfo | `hr changeInfo /n MEMBER_NAME (/p PHONE_NUMBER) (/e EMAIL) (/r MEMBER_ROLE)` | `hr changeInfo /n Jack /p 12345678 /r president` <br/>
changeLog | `finance changeLog /i INDEX /n ITEM_NAME ITEM_VALUE` | `finance changeLog /i 1 /n buy cake 5.5`
delAttendance  | `event delAttendance /n EVENT_NAME /p MEMBER_NAME` | `event delAttendance /n arduino course /m Peter`<br/>
delMember  | `hr delMember MEMBER_INDEX` | `hr delMember 1`
delEvent | `event delEvent EVENT_INDEX`  | `event delEvent 1`
delEvent all | `event delEvent all` | -
delLog | `finance delLog ITEM_INDEX` | `finance delLog 3`
event done   | `event done EVENT_INDEX`| `event done 2`
event search | `event search /s <KEYWORD>` | `event search /s arduino` 
event countdown | `event countdown` | -
help | `help` | -
hr search | `hr search ITEM (/n ITEM) (/p ITEM) (/e ITEM) (/r ITEM)` | `hr search /n Peter /r president`
import  | `import FILENAME /c CATEGORY (...)` | `import sample.csv /c hr /name Member /phone Contact /email Email /role Role`
listAttendance | `event listAttendance /n EVENT_NAME` | `event listAttendance /n arduino course`
listMember  | `hr listMember` | -
listEvent | `event listEvent` | -
list prof&admin | `hr list prof&admin` | -
list connections | `hr list connections` | -
summary | `finance summary` |   -



[Return to the top](#user-guide)

## 7. Glossary

This section explains certain technical terms used in the guide which may require more detail.

**CCA** - Co-curricular Activity <br/>
**CSV - Comma-separated values. This typically refers to the file type with extension .csv  <br/>
**Command Line Interface(CLI)** - Processes commands to a computer program in the form of lines of text. <br/>
**Terminal/Command Prompt** - An interface where you can type and execute text based commands. It is a basic feature of most Operating Systems.  
**Directory** - Folder. A directory is a more technical name for referring to folders. 
**Command** - A command is an instruction that will cause a program to perform a series of actions based on what was supplied to it.  
**Index** - An index (of a list) refers to which position the item is in the list. In the list (apple,pear,banana) the index of "pear" is 2 because it's the 2nd element.  
**HR** - Human Resource <br/>
**OS** - Operating Systems <br/>
