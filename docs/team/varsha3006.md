# Varsha - Project Portfolio Page

## Overview
I am part of a team of 4 software engineering students and we were tasked to design and implement a Command Line Interface (CLI) desktop application for our team project. 
Our application is called CCA Manager. 

### About the project
CCA Manager is a revolutionary all-in-one management tool that changes the way 
you can manage interest groups with unrivaled efficiency and simplicity. It has 3 features 
in total, HR, Finance and Events. I was tasked with the Event Feature for our program.

### Summary of Contributions
This section shows a brief summary of my contributions to the team project, including coding, documenting and other helpful contributions throughout the development of CCA Manager.

#### Code Contributed

[Reposense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=varsha3006) to access the code and documentation
that I have contributed in this project.

#### Features Added
 **New features:** \
I added to the event feature in this project. Specifically  `CommandEventAdd`, `CommandEventDel`, `CommandEventList`  ,`CommandEventStatus`, `CommandSearchEvent` and `CommandEventCountdown`

The user will be able to use `event addEvent` to add a new event to the list.   <br/>
The user may also use `event delEvent <index>` or `event delEvent all` command to delete an existing event or all events in the list. <br/>
To mark an event as completed, user can use `event done <index>` <br/>
The user can use `event listEvent` to list all the events in the list. <br/>
The user can use `event search` to find events. They have the option to:
 - Search by Event date <br/>
 - Search by Event name <br/>
The user can use   `event countdown` to see number of days left to the upcoming event. <br/>

**Enhancements Added:**
* User will not be able add an event that matches in name and date with an existing event.
* User can delete all events in the list with a confirmation message so that user doesn't accidentally delete all events data.
* User will be able to search by both date and name
* User will be able to see a countdown to upcoming events sorted by the earliest deadline first.


**Contributions to User Guide**
Created the first draft of the User Guide and provided a template for the rest to follow. 
Section Added:
* Table of Contents
* About this User Guide
* Quick  Start
* Glossary
* Contributed to Event features in Command Summary
* Event Features in UG:
  * Add an event
  * Delete an event
  * Clear all events
  * List events
  * Countdown events
  * Mark an event as completed
  * Search for an Event
  
**Contributions to Developer Guide:**
* All event features and descriptions under Section 3.4.1 to 3.4.6 (including all sequential diagrams, state diagrams and architecture diagram)
* User Stories
* Setting up
* Glossary 
