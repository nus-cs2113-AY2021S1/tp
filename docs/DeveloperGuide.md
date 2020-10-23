# Developer Guide

## Table of Contents

* [Setting Up, Getting Started](#setting-up-getting-started)
* [Design](#design)
    * [Architecture](#architecture)
    * UI component
    * Logic component
    * Model component
    * Storage component
    * Parser component
    * Common classes
* [Implementation](#implementation)
    * Add Task
    * Delete Task
    * Mark Task as Done
    * [Proposed] A better link between Task and Module.
    * [Store Data](#store-data)
* [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
* [Appendix: Requirements](#appendix-requirements)
    * [Product Scope](#product-scope)
    * [User Stories](#user-stories)
    * [Use Cases](#use-cases)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Glossary](#glossary)
* [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing)

## Setting Up, Getting Started
{Start up guide}

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture
{Insert high level architecture diagram here}

The ModTracker class is the main entry point for the ModTracker application. It contains the main, loadData, run, 
startAndGetName and runCommandLoopUntilExitCommand methods. The methods are responsible for:
* At app launch:
    * Creation of a ModTracker object.
    * Load previously saved data from the text file into ModTracker.
    * Run the ModTracker app.
* At runtime:
    * Continuously prompt the user for input until app termination.
* At shutdown:
    * Save user data into a text file.
    * Invoke clean up methods where necessary.
    
The rest of the app consists of 5 packages:
* `Ui` : The user interface of the app.
* `Parser` : Parses the user input and calls the corresponding methods.
* `Logic` : Contains all classes and methods that performs the logical operations.
* `Model` : Holds the data of the app (Task & Module) in memory.
* `Storage` : Saves and loads data to and from the hard disk.
    
## Implementation
{Insert your own respective implementations here}

### Store Data
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
Since this command does not modify the data, 
the external file remains unchanged.

1. The user enters `addmod CS1010 10`, 
and the program adds the new module correspondingly. 
Since this is a valid command which modifies the data,
 the `Storage` object appends `addmod CS1010 10` to the external file.

1. The user enters `exit`, and the program terminates.

##### Second Use
The same user starts the application again (on the same device as the first use).

1. `ModTracker` creates a new `Storage` object 
with the same specified file path at `data/modtracker.txt`.
   
1. The `Storage` object checks that the file at `data/modtracker.txt` exists,
and reads the first line of the file to obtain the user's name.

1. The `Storage` object passes the username to `ModTracker`, 
which calls `Ui` to greet the user.

1. `ModTracker` then obtains the remaining lines in the file via `Storage`,
and passes it to `Parser` to load the data into `ModuleList` and `TaskList`.

1. The program prompts the user for further inputs,
and it continues as per normal, with the data loaded.

The following sequence diagram shows how the storage feature works,
with a focus on how it obtains the username:

![Storage Sequence Diagram](diagrams/Storage%20Sequence%20Diagram.svg)

#### Design Considerations

* **Alternative 1 (current choice)**: Saves the user input to the file
    * Pros: Easy to implement as code from `Parser` can be reused
    * Cons: Increases coupling as it depends on the `Parser` class 
    to make sense of the data
* **Alternative 2**: Parses input into a different format 
storing the different modules, time expected, time spent and tasks
    * Pros: Independent of how `Parser` takes in user input
    * Cons: Requires additional work to parse data into the required
    storage format

## Documentation, Logging, Testing, Configuration, Dev-Ops
{Insert guides here for doc, testing etc}

## Appendix: Requirements
### Product Scope
#### Target User Profile

NUS students

* spend too much time on certain modules and neglecting other modules
* want a timetable to view and breakdown all the module workload easily
* need help to manage the time spent on each module


#### Value Proposition

* helps to track the time spent on every module  
* better prioritize their work for each module 
* relieves the stress of NUS students by achieving a work-life balance

### User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|student|input my modules|keep track of modules that I am taking|
|v1.0|student|input the expected module workload|keep track of the recommended time I should spend on the module|
|v1.0|busy student|view breakdown of my time spent on each module|pinpoint in detail where i spend most of time on|
|v1.0|student|input the actual time spent on each module|keep track of the actual time I spent|
|v1.0|careless user|edit the data easily|correct any mistakes I inputted wrongly/
|v1.0|user|save my data permanently|save the trouble of re-entering my data everytime I start the app|
|v2.0|user|(to be updated)|(to be updated)|

### Use Cases
{Insert some use cases examples}

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as they have Java 11 or above installed.
1. The commands should be short and succinct such that a user with average typing speed should be
able to accomplish the tasks faster than using a regular _GUI app_.


### Glossary

* *mainstream OS* - Windows, Linux, macOS
* *GUI app* - An app that utilises GUI (graphical user interface). It allows users to interact with electronic devices 
through graphical icons and audio indicator. Most apps like the mobile apps that we are familiar with utilises the GUI.

## Appendix: Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
