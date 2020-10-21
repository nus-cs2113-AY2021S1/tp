# Developer Guide

## Introduction

## Setting Up

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Overall_Architecture.JPG" alt="" width="300"/> <br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Archi_SD.JPG" alt="" width="750"/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/Model_Class_Diagram.JPG" alt="" width="750"/>

## Implementation
This section describes some noteworthy details on how certain features are implemented.

### Mark a task as done feature
This feature is facilitated by `DoneCommand`. It extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/done_command_SD.JPG" alt="" width="450"/>
Note: It first extracts the task number from the user input prior to `convertTaskNumberToCalendarNumber`. 
There will be a check in the function `markTaskAsDone(calendarNumber)` to ensure that the calendar item being marked as done is a task. 


### Additional information of an event feature
This feature is facilitated by `AddInfoCommand` and the `ViewInfoCommand`. Both extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation of `AddInfoCommand` works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/addInfoCommand_SD.JPG" alt="" width="450"/>
The following sequence diagram show how the `execute()` operation of `ViewInfoCommand` works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/viewInfoCommand_SD.JPG" alt="" width="450"/>

Note: It first extracts the event number from the user input prior to `convertEventNumberToCalendarNumber`. 

### Delete a calendar item feature
This feature is facilitated by `DeleteCommand`. It extends the `Command` class and overrides the `execute()` function.
The following sequence diagram show how the `execute()` operation works:<br/>
<img src="https://github.com/AY2021S1-CS2113T-T12-2/tp/blob/master/images/deleteCommand_SD.JPG" alt="" width="450"/>
Note: It first extracts the task/event number from the user input prior to `convertTaskNumberToCalendarNumber` and `convertEventNumberToCalendarNumber` respectively. 


### Find a calendar item feature

## Documentation

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|user|set my tasks as done|track my tasks better|
|v1.0|user|delete my calendar items|remove unwanted items and organise my calendar better |
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
|v2.0|NUS student|add information about my classes|locate all the information about my class on this app|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
