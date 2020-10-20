# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Storage feature
The storage feature saves the data of the user so that 
the ModTracker application continues from where the user left off the previous time. 

#### Proposed Implementation
The `Storage` class facilitates the data saving and loading mechanism. 
The constructor `Storage(filePath)` accepts a String which is the file path to an external file.
This external file stores the user's data locally.

Inside the external file, it stores the name of the user 
followed by any valid inputs which modifies the data.
The format of the data inside the external file is as follows:

````
username
valid user input 1
valid user input 2
valid user input 3
...
````

When the user runs the program again, 
the program will first load data from the external file.
The `Parser` class then parses these data 
before the program prompts the user for input.

Given below is an example usage scenario, showing the 2 different times 
when a user starts the application.

##### First Use
The user launches the application for the first time.

1. `ModTracker` creates a new `Storage` object 
with file path at `data/modtracker.txt`. 

1. The newly created `Storage` object checks that there is no file 
at the specified file path, and creates a new file there.

1. The program prompts the user to input his/ her name.

1. The `Storage` object writes this username into the external file.

1. The program prompts the user for further inputs.

1. The user enters `help`, and the program displays the help message.

1. The user enters `addmod CS1010 10`, 
and the program adds the new module correspondingly. 

1. Since this is a valid command which modifies the data,
 the `Storage` object appends `addmod CS1010 10` to the external file.

##### Second Use
The same user starts the application again (on the same device as the first use).

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|user|save my data|I do not need to re-enter my data everytime I start the app|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
