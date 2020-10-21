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
- [Instructions for manual testing](#instructions-for-manual-testing)

## Design & implementation

This section provides an overview of the Fitr application.

### Architecture

![Main class diagram](images/MainClassDiagram.png)

The _**Architecture Diagram**_ above explains the high-level design of Fitr. Given below is a quick overview of each component.

The main driver of the application is `Fitr` is responsible for mainly two phases:
* At app launch: Initialises or loads the respective lists and user profile in the correct sequence and is in charge of connecting the various components with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

The rest of the App consists of:
* `Ui`: The user interface of the application.
* `Parser`: Handles and parses user commands.
* `Command`: Handles the various commands Fitr understands.
* `Storage`: Reads data from and writes data back into a text file for future use.

Each of the component defines its _API_ in an `interface` with the same name as the Component.

The sequence diagram below shows how the components work with one another, when a user enters the following command `food chicken rice /600 1`:

![Add food sequence diagram](images/AddFoodSequenceDiagram.png)
 
### Ui component

**API**: `Ui.java`

The `Ui` class handles all user inputs and system output.

This component also listens to other components and outputs the desired messages in specified formats.

### Storage component

The `Storage` class handles the read and write operations of the `FoodList`, `ExerciseList` and `User` classes.

![Storage class diagram](images/StorageClassDiagram.png)

The figure above shows the associations and dependencies present with the `Storage` class.

On startup, the `Fitr` class creates a new `Storage` object with the file paths of the text files. 
This will cause the `Storage` object to retrieve the text files from the given file paths and loads the data into the program. 
If the files do not exist, the Storage object will create the files.

Each time there is a change in the `FoodList`, `ExerciseList` or `User` objects, or when the program is exiting, `writeExerciseList()`, `writeFoodList()` and `writeUserProfile()` methods are invoked. 
This will save the data onto the respective text files on the hard disk.

### Common classes

Classes used by multiple components are in the `fitr.common` package.

## Product scope
### Target user profile

Our target user profile is university students, at all fitness levels, who values an application that integrates the logging of food, exercises performed and the tracking of calories in one application. 
It is also aimed at those who prefer typing over mouse interactions and are reasonably comfortable with using command-line applications.

### Value proposition

Our application helps users to keep fit without needing them to do any research on healthy living or workout options. 
We will be collecting user information such as age, weight, fitness level, and the application will recommend users preset exercises.

## User stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|student|enter my food intake|log my calories|
|v1.0|student|view my past calories intake|track my calories|
|v1.0|student|store the food I consumed together with its calories in the application|add food that I previously consumed without having to enter the calories again|
|v1.0|new user|access the help function|I know what to do with the application|
|v1.0|student|print my past exercise records|see my workout progress|
|v1.0|student|enter my height and weight|the application can calculate my BMI|
|v1.0|student|save my past exercise and food records|access my past records at all times|
|v2.0|student|edit my previous exercise and food entries|fix any mistakes I made previously|
|v2.0|student|clear my previous exercise and food entries|declutter my exercise and food records|
|v2.0|student|indicate my fitness level|exercise based on the recommendations of the application|
|v2.0|student|edit my profile|update my information|
|v2.0|student|set goals|see if I am on track|
|v2.0|student|be recommended a set of workout|save time finding exercises to do|
|v2.0|student|unlock achievements|be motivated to workout|

## Non-functional requirements

1. This application should work on any mainstream operating system, such as Windows or macOS, as long as Java 11 or above is installed.
2. A user with above average typing speed for regular English text should be able to accomplish most of the tasks faster using commands than using the mouse.

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