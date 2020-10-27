# Developer Guide

* Table of Contents
{:toc}

## 1. Introduction

**Fitr** is a command line application, helping university students keep track of your food intake and exercises.

This developer guide documents the design and implementation of Fitr.
This guide is targeted towards current and future developers, who wish to understand and work on this application.

## 2. Design & implementation

This section provides an overview of the design and implementation of the Fitr application.

### 2.1 Architecture

This section illustrates the high-level architecture of Fitr, as well as an illustration of the components used in Fitr.

![Main class diagram](images/MainClassDiagram.png)

_Figure 1: Architecture diagram of Fitr_

Figure 1 above explains the high-level design of Fitr. Given below is a quick overview of each component.

The main driver of the application is `Fitr` is responsible for mainly two phases:
* At app launch: Initialises or loads the respective lists and user profile in the correct sequence and is in charge of connecting the various components with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

The rest of the App consists of:
* `Ui`: The user interface of the application.
* `Parser`: Handles and parses user commands.
* `Command`: Handles the various commands Fitr understands.
* `StorageManager`: Reads data from and writes data back into a text file for future use.
* `ListManager`: Handles all the list operations in Fitr.

Figure 2 below shows how the components work with one another, when a user enters the following command `food chicken rice /600 1`:

![Add food sequence diagram](images/AddFoodSequenceDiagram.png)

_Figure 2: Sequence diagram when adding a food into Fitr_
 
### 2.2 Ui component

The `Ui` class handles all user inputs and system output.

This component also listens to other components and outputs the desired messages in specified formats.

### 2.3 Storage component

Under the storage component, the `StorageManager` class handles the read and write operations of the various list classes available, such as `ExerciseList` and `FoodList`, through classes such as `ExerciseStorage` and `FoodStorage`.

![StorageManager class diagram](images/StorageClassDiagram.png)

_Figure 3: `StorageManager` class diagram_

Figure 3 above shows the associations and dependencies present with the `StorageManager` class.

On startup, the `Fitr` class creates a new `StorageManager` object. The `StorageManager` class will then create the various storage objects required to load that particular file into the program. 
This will cause the various storage objects to retrieve the text files from the given file paths and loads the data into the program. 
If the files do not exist, the various storage objects will create the files. For example, if the file that contains the user's exercise data does not exist, the `ExerciseStorage` object will create a new file to store the user's exercise data.

Each time there is a change in the `FoodList`, `ExerciseList` or `User` objects, or when the program is exiting, `writeExerciseList()`, `writeFoodList()` and `writeUserProfile()` methods in `StorageManager` are invoked. 
This will save the data onto the respective text files on the hard disk.

### 2.4 User component

The `User` class contains all information related to the user data (i.e. `name`, `age`, `height`, `weight`
and `gender`) and handles all operations on these user data (i.e. `getBmi()`)

The `User` class has two constructors and implementations differ based on the constructor used. 

If a `User` object is instantiated without parameters (i.e. `User user = new User()`), the `setup` method in the
 `user` object will be invoked, allowing users to go through a set of instructions to input their `name`, `age`, `gender`, `height`, `weight` and
 `fitnessLevel`.
 
If a `User` object is instantiated with defined parameters (i.e. `User user = new User(name, age, height, weight
, gender)`), the values from the given parameters will be stored within the user class without going through
 `setup()`.

On startup, the `Fitr` class instantiates a `Storage` object and calls its `loadUserProfile()` method. This method
returns a `User` type object and is referenced by a pre-declared `User` type variable, which is used throughout the
running session. 

### 2.5 Common classes

Classes used by multiple components are in the `fitr.common` package.

## Appendix A: Product scope
### Target user profile

Our target user profile is university students, at all fitness levels, who values an application that integrates the logging of food, exercises performed and the tracking of calories in one application. 
It is also aimed at those who prefer typing over mouse interactions and are reasonably comfortable with using command-line applications.

### Value proposition

Our application helps users to keep fit without needing them to do any research on healthy living or workout options. 
We will be collecting user information such as age, weight, fitness level, and the application will recommend users preset exercises.

## Appendix B: User stories

_Table 1: User stories_
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

## Appendix C: Non-functional requirements

1. This application should work on any mainstream operating system, such as Windows or macOS, as long as Java 11 or above is installed.
2. A user with above average typing speed for regular English text should be able to accomplish most of the tasks faster using commands than using the mouse.

## Appendix D: Instructions for manual testing

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
