# Developer Guide

- [Design & implementation](#design--implementation)
    - [Storage component](#storage-component)
- [Product scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
- [User stories](#user-stories)
- [Non-functional requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Instructions for manual testing](#instructions-for-manual-testing)

## Design & implementation

![Main class diagram](images/MainClassDiagram.png)

The figure above shows a high-level overview of the application.

### Storage component

The `Storage` class handles the read and write operations of the `FoodList`, `ExerciseList` and `User` classes.

![Storage class diagram](images/StorageClassDiagram.png)

The figure above shows the associations and dependencies present with the `Storage` class.

On startup, the `Fitr` class creates a new `Storage` object with the file paths of the text files. 
This will cause the `Storage` object to retrieve the text files from the given file paths and loads the data into the program. 
If the files do not exist, the Storage object will create the files.

Each time there is a change in the `FoodList`, `ExerciseList` or `User` objects, or when the program is exiting, `writeExerciseList()`, `writeFoodList()` and `writeUserProfile()` are invoked. 
This will save the data onto the respective text files on the hard disk.

## Product scope
### Target user profile

Our target user profile is university students, at all fitness levels, who values an application that integrates the logging of food, exercises performed and the tracking of calories in one application. It is also aimed at those who prefer typing over mouse interactions and are reasonably comfortable with using command-line applications.

### Value proposition

Our application helps users to keep fit without needing them to do any research on healthy living or workout options. We will be collecting user information such as age, weight, fitness level, and the application will recommend users preset exercises.


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

## Non-functional requirements

1. This application should work on any mainstream operating system, such as Windows or macOS, as long as Java 11 or above is installed.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
