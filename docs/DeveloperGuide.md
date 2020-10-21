# Developer Guide

{Table of contents goes here}

## Setting up, getting started

## Design

## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Check feature

#### Implementation

The check feature is implemented using the `CheckCommand` class. `CheckCommand` accesses the `Event`s stored within `EventList`s in order to determine if events are occurring within a given time period. It implements the following operations:

- `CheckCommand#getDate(stringDate)` -- Parses a given string to get a LocalDate variable (either the start or end date for the time period).
- `CheckCommand#getTime(stringTime)` -- Parses a given string to get a LocalTime variable (either the start or end time for the time period).
- `CheckCommand#checkEventsInTimeRange(eventsList, startDate, endDate, startTime, endTime)` -- Checks each event in the eventsList to see if they occur within the time period defined in the command, and saves all coinciding events in an ArrayList.

These operations are not exposed, and are used as private methods within the `CheckCommand` interface.

Given below is an example usage scenario and how the check feature functions.

Step 1. The user inputs `check 11/5/2020; 5:15 PM; 15/5/2020; 9 PM` in order to check for events occurring between 11th May 2020, 5:15 pm and 15th May 2020, 9:00 pm. This input is received by the Ui, which processes it into a string. The string is parsed by the Parser, which returns a CheckCommand with the date and time parameters stored in it as a string.

Step 2. `CheckCommand#execute()` is called. The command string containing the date and time parameters are split into a String array to separate the different parameters. 

Step 3. Within `CheckCommand#execute()`, `CheckCommand#getDate()` is called to parse the start and end dates, and `CheckCommand#getTime()` is called to parse the start and end times.

This sequence diagram shows how the `getDate` method functions:

![Sequence Diagram for getDate](/docs/diagrams/getDate_seq_diagram.jpg)

Step 4. Within `CheckCommand#execute()`, the start date time and end date time is passed to `CheckCommand#checkEventsInTimeRange()` along with an `EventList` (i.e. Zoom, Personal or Timetable). This method checks each `Event` in the `EventList` to determine if the event occurs within the time period. If the event date time coincides with the time period, the event is added to an ArrayList that stores all the coinciding events in the current `EventList`. This is done for each `EventList`. 

Step 5. The contents of the ArrayLists returned by `CheckCommand#checkEventsInTimeRange()` are combined into a single ArrayList, and a new `EventList` ("coinciding") is created using this combined list of events.

Step 6. `Ui#printList()` is called to print the list of coinciding events.

The following sequence diagram shows how the check operation works:

![Sequence Diagram for CheckCommand](/docs/diagrams/CheckCommand_seq_diagram.jpg)

## Documentation, logging, testing, configuration, dev-ops (not sure what this entails)

## Appendix: Requirements

### Product scope

##### Target user profile

{Describe the target user profile}

##### Value proposition

{Describe the value proposition: what problem does it solve?}

### User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

### Use Cases

### Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
