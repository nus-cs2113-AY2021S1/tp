# User Guide
1. Introduction
2. Quick Start
3. Features <br/>
   3.1 Help `help` <br/>
   3.2 Exit the program: `exit` <br/>
   3.3 HR `HR` <br/>
      *  Add Members: `addMember`<br/>
      *  Delete Members: `delMember`<br/>
      *  View summary of members: `viewMember`<br/>
   
   3.4 Events `event` <br/>
     * Add events: `addEvent`<br/>
     * Delete events: `delEvent`<br/>
     * View Summary of events: `viewEvent`<br/>
     
   3.5 Finance `finance` <br/>
     * Add finance log : `addLog`<br/>
     * Delete finance log: `delLog`<br/>
     * View financial summary: `summary`<br/>
            
4. FAQ
5. Command Summary
6. Glossary

## Introduction

CCA Manager is a revolutionary tool that changes the way you can manage interest groups with unrivaled efficiency and simplicity. Its lightweight Command Line Interface (CLI) allows administrators to breeze through tasks quickly and easily while offering powerful features to advanced users.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of CCA manager from here
3. Run the program by entering java -jar cca.java in a terminal.

## Features 

### 3.1 Help `help`
Shows a list of available commands<br/>
Format: help
### 3.2 Exit the program: `bye` 
Exits the program.
Format: `bye`

### 3.3 HR features `HR` <br/>

### 3.4 Event features`event` <br/>
#### Add events: `addEvent`<br/>
Adds an event to the list of events.<br/>

Format: `event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME`<br/>

Example of usage: <br/>
 `event addEvent /n arduino course /d 2020-09-16 /t 8pm`<br/>
 `event addEvent /n Autodesk course/d 2020-12-20 /t 8-10.30pm`<br/>
 
 #### Delete events: `delEvent`<br/>
 Deletes an event from the list of events. <br/>
 Format: `event delEvent EVENT_INDEX` <br/>
 Deletes the event at the specified EVENT_INDEX.<br/>
 
 The EVENT_INDEX refers to the index number shown in the list of events.<br/>
 The EVENT_INDEX must be an integer greater than 0. <br/>
 
 Example of usage: 
 `event delEvent 1` <br/>
 `event delEvent 19` <br/>

#### list events: `listEvent`<br/>
Prints the list of events, based on the order in which they are added into the list. 
Format: `event listEvent` <br/>

Example of usage: 
`event listEvent`

### 3.5 Finance features `finance` <br/>

##FAQ

##Command Summary
Command | Format | Example
------- | ---------- | ------------
help | `help` | -
bye | `bye` | -
hr  | | -
addEvent | `event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME` | `event addEvent /n arduino course /d 2020-09-16 /t 8pm`<br/>
delEvent | `event delEvent EVENT_INDEX`  | `event delEvent 1`
listEvent | `event listEvent` | -
finance | |-

##Glossary



