# User Guide

# Table of contents

- [1.Introduction](#1introduction)
- [2.Quick Start](#2quick-start)
- [3.Features](#3features)
  - [3.1 Help `help`](#31-help-help)
  - [3.2 Exit the program: `bye`](#32-exit-the-program-bye)
  - [3.3 HR Features `HR`](#33-hr-features-hr)
    - [Add members: `addMember`](#add-members-addmember)
    - [Delete members: `delMember`](#delete-members-delmember)
    - [List members: `listMember`](#list-members-listmember)
    - [Change member's information: `changeInfo`](#change-member-information-changeinfo)
    - [Search members: `search`](#search-members-search)
    - [View contacts of prof/admin: `list prof&admin`](#view-contacts-of-profadmin-list-profadmin)
    - [View contacts of connections: `list connections`](#view-contacts-of-connections-list-connections)
  - [3.4 Event Features`event`](#34-event-featuresevent)
    - [Add events: `addEvent`](#add-events-addevent)
    - [Delete an event: `delEvent`](#delete-an-event-delevent)
    - [Clear all events: `delEvent all`](#clear-all-events-delevent-all)
    - [List events: `listEvent`](#list-events-listevent)
    - [Countdown events `countdown`](#countdown-events-countdown)
    - [Mark an event as completed `done`](#mark-an-event-as-completed-done)
    - [Search for an Event `search`](#search-for-an-event-search)
  - [3.5 Finance Features `finance`](#35-finance-features-finance)
    - [Add finance log entry: `addLog`](#add-finance-log-entry-addlog)
    - [Delete finance log entry: `delLog`](#delete-finance-log-entry-dellog)
    - [View financial summary: `summary`](#view-financial-summary-summary)
- [4. FAQ](#4-faq)
- [5. Command Summary](#5-command-summary)
- [6. Glossary](#6-glossary)

## 1.Introduction

CCA Manager is a revolutionary tool that changes the way you can manage interest groups with unrivaled efficiency and simplicity. Its lightweight Command Line Interface (CLI) allows administrators to breeze through tasks quickly and easily while offering powerful features to advanced users.

## 2.Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of CCA manager from  ![here](https://github.com/AY2021S1-CS2113T-F14-1/tp/releases)
3. Run the program by entering java -jar cca.java in a terminal.

## 3.Features 

###Command Format
Words in `UPPER CASE` are the parameters to be supplies by user 
### 3.1 Help `help`
Shows a list of available commands<br/>
Format: help
### 3.2 Exit the program: `bye` 
Exits the program.
Format: `bye`

![helpCommand](userGuidePic/helpCommand.PNG)

### 3.3 HR features `HR` 
#### Add members: `addMember`
Adds a member to the list of members.<br/>

Format: `hr add /n NAME /p PHONE_NUMBER /e EMAIL /r ROLE`<br/>

Example of usage: <br/>
 `hr addMember /n John Sterling /p 88888888 /e js@gmail.com /r president`<br/>
 `hr addMember /n Harry Potter /p 12345678 /e H_P@gmail.com /r member`<br/>
 
 #### Delete members: `delMember`
 Deletes a member from the list of members. <br/>
 Format: `hr delMember MEMBER_INDEX` <br/>
 Deletes the member at the specified MEMBER_INDEX.<br/>
 
 The MEMBER_INDEX refers to the index number shown in the list of members.<br/>
 The MEMBER_INDEX must be an integer greater than 0. <br/>
 
 Example of usage: 
 `hr delMember 1` <br/>
 `hr delMember 19` <br/>

#### list members: `listMember`
Prints the list of members, based on the order in which they are added into the list. 
Format: `hr listMember` <br/>

Example of usage:
`hr listMember`  

#### change member information: `changeInfo`
Changes contacts and role of member in the list, based on the given member name. 
Format: `hr changeInfo /n MEMBER_NAME (/p PHONE_NUMBER) (/e EMAIL) (/r MEMBER_ROLE)` <br/>

MEMBER_NAME and at least one of PHONE_NUMBER, EMAIL and MEMBER_ROLE must be provided. 

Example of usage: <br/>
`hr changeInfo /n jack sterling /p 12345678` <br/>
`hr changeInfo /n Harry Potter /p 12345678 /e 123@gmail.com /r President`

#### search members: `search`  
Search the members whose information matches user input.  
Format: `hr search ITEM (/n ITEM) (/p ITEM) (/e ITEM) (/r ITEM)`  

Example of usage:  
`hr search peter`  
`hr search /n peter /r president`  


#### view contacts of prof/admin: `list prof&admin`  
List the contacts of the professors and administrators.  
Format: `hr list prof&admin`  

Example of usage:  
`hr list prof&admin`  


#### view contacts of connections: `list connections`  
List the contacts of connections(alumni, speakers).  
Format: `hr list connections`  

Example of usage:  
`hr list connections`  


### 3.4 Event features`event` 
#### Add events: `addEvent`
Adds an event to the list of events.<br/>

Format: `event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME`<br/>

Example of usage: <br/>
 `event addEvent /n arduino course /d 2020-12-30 /t 8pm`<br/>
 `event addEvent /n Autodesk course/d 2020-12-20 /t 8-10.30pm`<br/>
 
#### Delete an event: `delEvent`
 Deletes an event from the list of events  <br/>
 Format to clear a particular event: `event delEvent EVENT_INDEX` <br/>
 Deletes the event at the specified EVENT_INDEX.<br/>
 
 The EVENT_INDEX refers to the index number shown in the list of events.<br/>
 The EVENT_INDEX must be an integer greater than 0. <br/>
 
 Example of usage: 
 `event delEvent 1` <br/>
 `event delEvent 19` <br/>

#### Clear all events: `delEvent all`
Clear all events from the list of events <br/>
Format to clear all events in the list: `event delEvent all`

Example of usage:
`event delEvent all` <br/>

A confirmation message will be prompted. To clear, type `Y` in terminal.
![EventDeleteAll](userGuidePic/eventDeleteAll.PNG)

#### list events: `listEvent`
Prints the list of events, based on the order in which they are added into the list. 
Format: `event listEvent` <br/>

Example of usage: 
`event listEvent`

#### countdown events `countdown`
lists the events with the number of days left. It also sorts them so that the event due first will be displayed first.<br/>
Format: `event countdown` <br/>

Example of usage: 
`event countdown`

#### Mark an event as completed `done`
Mark an event as done. 
Format: `event done EVENT_INDEX` <br/>

The EVENT_INDEX refers to the index number of the event that is to be marked as completed.<br/>
The EVENT_INDEX must be an integer greater than 0. <br/>

Example of usage:
`event done 1`

#### Search for an Event `search`
Search for a particular event by name or date.
Format: 'event search /s KEYWORD' <br/>

Example of usage:
`event search /s arduino course`
`event search /s 2000-12-16`


### 3.5 Finance features `finance`  
#### Add finance log entry: `addLog`  
Adds an entry into the finance log.  
Format: `finance addLog ITEM_NAME ITEM_VALUE`  

Example of usage:  
`finance addLog have lunch 4.5`  
`f addlog buy flight ticket 750`  

#### Delete finance log entry: `delLog`  
Removes an entry from finance log.  
Format: `finance delLog ITEM_INDEX`  

Example of usage:  
`finance dellog 3`  
`f delLog 10`  

#### View financial summary: `summary`  
Brings up a summary of the financial log and shows the total amount of money expended.  
Format: `finance summary`  


## 4. FAQ

## 5. Command Summary
Command | Format | Example
------- | ---------- | ------------
help | `help` | -
bye | `bye` | -
addMember  | `hr addMember /n NAME /p PHONE_NUMBER /e EMAIL /r ROLE` | `hr addMember /n Harry /p 12345678 /e HP@gmail.com /r member`<br/>
delMember  | `hr delMember MEMBER_INDEX` | `hr delMember 1`
listMember  | `hr listMember` | -
changeInfo | `hr changeInfo /n MEMBER_NAME (/p PHONE_NUMBER) (/e EMAIL) (/r MEMBER_ROLE)` | `hr changeInfo /n Jack /p 12345678 /r president` <br/>
addEvent | `event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME` | `event addEvent /n arduino course /d 2020-09-16 /t 8pm`<br/>
delEvent | `event delEvent EVENT_INDEX`  | `event delEvent 1`
delEvent all | `event delEvent all` | -
listEvent | `event listEvent` | -
countdown | `event countdown` | -
done   | `event done EVENT_INDEX`| `event done 2`
event search | `event search /s <KEYWORD>` | `event search /s arduino` 
addLog | `finance addLog ITEM_NAME ITEM_VALUE` | `finance addLog have lunch 4.5`
delLog | `finance delLog ITEM_INDEX` | `finance delLog 3`
summary | `finance summary` |   -
hr search | `hr search ITEM (/n ITEM) (/p ITEM) (/e ITEM) (/r ITEM)` | `hr search /n Peter /r president`
list prof&admin | `hr list prof&admin` | -
list connections | `hr list connections` | -

## 6. Glossary



