# Developer Guide

- [Design & implementation](#design--implementation)
    - [Architecture](#architecture)
    - [Ui component](#ui-component)
    - [Storage component](#storage-component)
    - [Common classes](#common-classes)
- [Product scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
- [User stories](#user-stories)
- [Non-functional requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Instructions for manual testing](#instructions-for-manual-testing)

## Design & implementation

This section provides an overview of the Fitr application.

### Architecture

The _**Architecture Diagram**_ above explains the high-level design of Fitr. Given below is a quick overview of each component.

The main driver of the application is `Fitr` is responsible for mainly two phases:
* At app launch: Initialises or loads the respective lists and user profile in the correct sequence and is in charge of connecting the various components with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

The rest of the App consists of:
* `UI`: The user interface of the App.
* `Parser`: Handles and parses user commands.
* `Command`: Handles the various commands Fitr understands.
* `Storage`: Reads data from and writes data back into a text file for future use.

Each of the component defines its _API_ in an `interface` with the same name as the Component.
 
### Ui component

**API**: `Ui.java`

The `Ui` class handles all user inputs and system output.

This component also listens to other components and outputs the desired messages in specified formats.

### Storage component

{TBD}

### Common classes

Classes used by multiple components are in the `fitr.common` package.

## Product scope
### Target user profile

University students or athletes who want to track their calories and recommended workouts.

### Value proposition

Our program helps users to keep fit without needing them to do any research on healthy living or workout options. We will be collecting user information such as age, weight, fitness level and recommend users to preset exercises.


## User stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|student|enter my food intake|log my calories|
|v1.0|student|view my past calories intake|track my calories|
|v1.0|student|store the food I consumed together with its calories in the app|add food that I previously consumed without having to enter the calories again|
|v1.0|new user|access the help function|know what to do with the app|
|v1.0|student|print my exercise track record|see my workout progress|
|v1.0|student|enter my height and weight|the system can calculate my bmi|
|v2.0|student|edit my profile|update my information|
|v2.0|student|edit my previous entries|fix any mistakes I made|
|v2.0|student|remove my previous entries|declutter|
|v2.0|student|set goals|see if I am on track|
|v2.0|student|receive tips or fun facts|learn more about the exercises and human body|
|v2.0|student|be recommended a set of workout|save time finding exercises to do|
|v2.0|student|unlock achievements|be motivated to workout|

## Non-functional requirements

1. Should work on any _mainstream OS_ as long as it has Java 11 or above installed.
1. Should be able to hold up to 1000 exercise, food or goal entries without a noticeable difference in performance for typical usage.
1. A user with above average typing speed for regular English text should be able to accomplish most of the tasks faster using commands than using the mouse.


## Glossary

* *mainstream OS* - Windows, Linus, Unix, OS-X

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
1. Initial launch
    1. Download the latest version of `Fitr` from [here](https://github.com/AY2021S1-CS2113T-W13-2/tp/releases).
    1. Open Command Prompt / Terminal
    1. Run the command java -jar {filename}.jar e.g., java -jar Fitr.jar (i.e., run the command in the same folder as the jar file).
    1. If the setup is correct, you should see something like the below:
       ``` 
        _______   __   __
       |    ___| |__| |  |_  .----.
       |    ___| |  | |   _| |   _|
       |___|     |__| |____| |__|
       Hello! Welcome to Fitr. 
       ```