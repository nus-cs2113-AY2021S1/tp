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

### Storage component

![Storage](images/StorageClassDiagram.png)

The `Storage` class handles the read and write operations of the `FoodList`, `ExerciseList` and `User` classes.

On startup, the `Fitr` class creates a new `Storage` object with the file paths of the text files. 
This will cause the `Storage` object to retrieve the text files from the given file paths and loads the data into the program. 
If the files do not exist, the Storage object will create the files.

Each time there is a change in the `FoodList`, `ExerciseList` or `User` objects, or when the program is exiting, `writeExerciseList()`, `writeFoodList()` and `writeUserProfile()` are invoked. 
This will save the data onto the respective text files on the hard disk.

## Product scope
### Target user profile

University students

### Value proposition

Our program helps users to keep fit without needing them to do any research on healthy living or workout options. We will be collecting user information such as age, weight, fitness level and recommend users to preset exercises.


## User stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|student|enter my food intake|log my calories|
|v1.0|student|view my past calories intake|track my calories|
|v1.0|student|store the food I consumed together with its calories in the app|add food that I previously consumed without having to enter the calories again|
|v1.0|new user|access the help function|I know what to do with the app|
|v1.0|student|print my exercise track record|see my workout progress|
|v1.0|student|enter my height and weight|the system can calculate my bmi|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-functional requirements

1.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
