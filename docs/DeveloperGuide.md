# Developer Guide

## Table of Content
1. [Setting up](#1-setting-up)
1. [Design](#2-design)
    1. Architecture
    1. [UI component](#22-ui)
    1. [Parser component](#23-parser)
    1. Command component
    1. User component
    1. AnimeData component
    1. Storage component
1. [Implementation](#3-implementation)
1. [Produce scope](#4-product-scope)
    1. Target user profile
    1. Value proposition
1. [User Stories](#5-user-stories)
1. [Non-Functional Requirements](#6-non-functional-requirements)
1. [Documentation, logging, testing, configuration, dev-ops](#7-documentation-logging-testing-configuration-dev-ops)
1. [Glossary](#8-glossary)
1. [Appendices](#9-appendices)
    1. Instructions for manual testing


## 1. Setting up
Please take a look at [SettingUp.md](SettingUp.md) for more information on getting started.

## 2. Design 
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


The rest of AniChan consists of 6 components:
- `UI`: Manages the user interface of AniChan
- `Parser`: Parses the user input 
- `Command`: Performs the execution of commands
- `User`: Manages workspace and the user data
- `AnimeData`: Provides data from the anime data file.
- `StorageManager`: Reads data from, and writes data to, the hard disk.

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





## 3. Implementation


## 4. Product scope
### Target user profile


### Value proposition

{Describe the value proposition: what problem does it solve?}

## 5. User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## 6. Non-Functional Requirements

{Give non-functional requirements}

## 7. Documentation, logging, testing, configuration, dev-ops

## 8. Glossary

* *glossary item* - Definition

## 9. Appendices

### Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
