# Developer Guide

## Design & implementation

This section describes how certain features are implemented.

### Remove feature

#### Implementation

The remove mechanism is executed by `RemoveCommand`. It extends from the abstract class `Command`. 
In addition, it implements the following operations:
* RemoveCommand#execute() — checks the Access level of user and calls the respective remove methods.
* RemoveCommand#removeModule() — removes module from list of modules based on the index provided and the chapters 
and flashcards under it.
* RemoveCommand#removeChapter() — removes chapter from list of chapter based on the index provided and 
the flashcards under it.
* RemoveCommand#removeCard() — removes flashcard from list of flashcards based on the index provided.

Given below is an example usage scenario and how the remove mechanism behaves at each step:

Step 1: The user launches the application and is currently in the module level. 

Step 2: The user executes `remove 1` command to delete the first module in the list of modules. 
The `remove` command creates `RemoveCommand` which will then be executed. 

Step 3: `RemoveCommand#execute` gets the module object based on the index provided and calls 
`Storage#deleteDirectory` to delete the module folder as well as the chapters and flashcards under it. 

### Revise feature

#### Implementation

The revise mechanism is executed by `ReviseCommand`. It extends from the abstract class `Command`. 
In addition, it implements the following operations:
* ReviseCommand#execute() — oversees the entire revise process and calls the respective methods when necessary.
* ReviseCommand#getChapter() — gets Chapter 
* RemoveCommand#getCards() — removes chapter from list of chapter based on the index provided and 
the flashcards under it.
* ReviseCommand#reviseCard() — removes flashcard from list of flashcards based on the index provided.
* ReviseCommand#addHistory() — adds the revision history to a storage to track past revisions.
* ReviseCommand#rateCard() — gets user input on difficulty of a flashcard.
* ReviseCommand#repeatRevision() — repeats revision for cards which user could not answer. 

Given below is an example usage scenario and how the remove mechanism behaves at each step:

Step 1: The user launches the application and is currently in the module level.


Step 2: The user executes `revise 1` command to revise the first chapter in the module. The `revise` command 
creates `ReviseCommand` which will then be executed.

Step 3: `ReviseCommand#execute` gets the chapter object based on the index provided as well as the flashcards 
under the particular chapter.

Step 4: Each flashcard will be shown to the user one by one and the `Ui#getInput` is called each time to get 
user input to rate the difficulty of the questions. 

Step 5: `ReviseCommand#repeatRevision` then repeats the revision session on cards which the user could not answer.

Step 6: `ReviseCommand#addHistory` will call `Storage#createHistory` and `Storage#saveHistory` to keep a record 
of the chapter revised so that the user can look back next time.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|revise the flashcards by chapter|do my revision|
|v1.0|user|remove modules/chapters/flashcard|remove modules/chapters/flashcards that I no longer need from the scheduler|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
