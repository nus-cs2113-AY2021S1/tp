# Developer Guide

## Table of Content
1. [Setting up](#1-setting-up)
2. [Design](#2-design)
<br/>&nbsp;2.1 [Architecture](#21-architecture)
<br/>&nbsp;2.2 [UI component](#22-ui)
<br/>&nbsp;2.3 [Parser component](#23-parser)
<br/>&nbsp;2.4 [Command component]()
<br/>&nbsp;2.5 [User component]()
<br/>&nbsp;2.6 [AnimeData component]()
<br/>&nbsp;2.7 [StorageManager component](#25-storagemanager)
3. [Implementation](#3-implementation)
4. [Produce scope](#4-product-scope)
<br/>&nbsp;4.1 [Target user profile]()
<br/>&nbsp;4.2 [Value proposition]()
5. [User stories](#5-user-stories)
6. [Non-functional requirements](#6-non-functional-requirements)
7. [Documentation, logging, testing, configuration, dev-ops](#7-documentation-logging-testing-configuration-dev-ops)
8. [Glossary](#8-glossary)
9. [Appendices](#9-appendices)
<br/>&nbsp;9.1 [Instructions for manual testing]()


## 1. Setting up and getting started
Please take a look [here](SettingUp.md) for more information on setting up and getting started.

## 2. Design 

### 2.1 Architecture

![Architectural Diagram](images/Architectural-Class-Diagram.png)

The `Main` class contains the `main` and `run` method.
It is responsible for initializing the various components and loading of any saved data into AniChan.
* At launch: Initializes the various components in the correct sequence, 
connects them together, and loads any saved data into AniChan.
* At shut down: AniChan invokes any clean up methods where necessary.

The rest of AniChan consists of 6 components:
- `UI`: Manages the user interface of AniChan
- `Parser`: Parses the user input 
- `Command`: Performs the execution of commands
- `User`: Manages workspace and the user data
- `AnimeData`: Provides data from the anime data file.
- `StorageManager`: Reads data from, and writes data to, the hard disk.

**LifeCycle of AniChan**

Here is an overall Sequence Diagram to help illustrate the general program flow and how the different objects 
interact with each other
![Main Sequence Diagram](images/Overall-Sequence-Diagram.png)


### 2.2 UI
API: `Ui.java`
The UI consists of a `UI` class that will handle all user inputs and system output. This includes the result of each Command execution. 

The `UI` component

Handles user commands by calling on Parser component
Listens for the execution of commands to print the result of the Command
Listens for any exceptions thrown to show an error message to the user, instead of a program termination

### 2.3 Parser
![Parser Diagram](images/Parser-Class-Diagram.png)

API: `Parser.java`

- Parser.java will parse the user command first to obtain the command type.
- With the command type known, it will call the respective `XYZParser`.
- `XYZParser` will parse the parameters and create the Command object.


Given below is the Sequence Diagram for interactions within the `Parser` component for the execution of `browse -p 1` API call

### 2.4 Command


### 2.5 User

API: `User.java`  

The User inherits from the abstract `Human` class and stores the name and gender of the user. It represents the user's interaction with `Workspace`. 

The `User`component:  
* Can provide user information like `name`, `gender`, and `honorific name`
* Stores an array list of type `Workspace`
* Can add, set, and switch between workspaces 



### 2.6 Workspace

API: `Workspace.java`  

The `Workspace` component:  
* Can allow `User` to create and get `Bookmark` 
* Can allow `User` to create and get `Watchlist` array list which user owns
* Can allow `User` to change his active `Watchlist`




### 2.7 StorageManager
![StorageManager Diagram](images/StorageManager-Class-Diagram.png)

API: `StorageManager.java`

The `StorageManager `component:
* Can save user data in `.txt`format and read it back.
* Can save the watchlist data in `.txt`format and read it back.
* Can save the bookmark data in `.txt` format and read it back.
* Can read script files that are in `.txt` format.

These data are saved in `.txt` format so advanced users will be able to 
see and manipulate these saved data easily.


## 3. Implementation


## 4. Product scope
### Target user profile


### Value proposition

{Describe the value proposition: what problem does it solve?}

## 5. User Stories

| Version |  As a ... | I want to ... | So that I can … |
| -------- | ---------- | --------------- |------------------ |
| v1.0 | user | manage my own watchlist to keep track of animes | be aware of the animes that I have watched or intending to watch |
| v1.0 | user | store my own anime watching information | I do not have to remember these details and could see them whenever I open the application |
| v2.0 | translator | estimate the amount of time needed to translate a script | give my clients a good estimate of how much time I need |

## 6. Non-Functional Requirements

{Give non-functional requirements}

## 7. Documentation, logging, testing, configuration, dev-ops

### 7.2 Logging
* We are using  `java.util.logging`  package for logging.
* The  `AniLogger`  class is used to manage the logging levels and logging destinations.
* The  `Logger`  for a class can be obtained using  `AniLogger.getAniLogger(Class)`  which will log messages according to the specified logging level.
* Log messages are output through the console and to a file  `/data/AniChan.log` which will be automatically created.
* When choosing a level for a log message, follow the conventions given in  [_[se-edu/guides] Java: Logging conventions_](https://se-education.org/guides/conventions/java/logging.html).


## 8. Glossary

* *glossary item* - Definition

## 9. Appendices

### Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
