# Developer Guide for E-Duke-8

- [Developer Guide for E-Duke-8](#developer-guide-for-e-duke-8)
  - [Introduction](#introduction)
    - [Software Overview](#software-overview)
    - [Setting up](#setting-up)
  - [Design & implementation](#design--implementation)
    - [Model Component](#model-component)
      - [Design of TopicList](#design-of-topiclist)
      - [Implementation of TopicList](#implementation-of-topiclist)
      - [Implementation of Notes](#implementation-of-notes)
      - [Design of Option and OptionList](#design-of-option-and-optionlist)
    - [Logic Component](#logic-component)
      - [Design of Parser](#design-of-parser)
      - [Implementation of MenuParser](#implementation-of-menuparser)
      - [Design of QuizQuestionsManager](#design-of-quizquestionsmanager)
      - [Implementation of QuizQuestionsManager](#implementation-of-quizquestionsmanager)
    - [Storage Component](#storage-component)
      - [Design of TopicsStorage](#design-of-topicsstorage)
      - [Implementation of TopicsStorage](#implementation-of-topicsstorage)
    - [UI Component](#ui-component)
      - [Implementation of Ui](#implementation-of-ui)
  - [Product scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
  - [User Stories](#user-stories)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
  - [Instructions for manual testing](#instructions-for-manual-testing)

## Introduction

### Software Overview

E-Duke-8 (pronounced "Educate") helps CS2113/T students **learn and understand software engineering and OOP principles** through a gamified
platform and enhances their learning experience. 

On this desktop application, CS2113/T students can attempt bite-sized quizzes, through the **Command Line Interface (CLI)**, to test their understanding of the concepts taught, and serves to consolidate key concepts for easy revision.

E-Duke-8, comprises of a Logic component, UI component, Storage component, and Model component. Each component comprises of multiple classes that work in tandem, to fulfil the purpose of our program. 

The purpose of this developer guide is to allow any interested contributors, who wish to develop this learning companion further, understand the inner workings of the program. 
This understanding will enable such contributors to add value to the current code, by improving its performance, level of interaction or capabilities. 

### Setting up

First, fork this repo, and clone the fork into your computer.

You are recommended to use Intellij IDEA to edit the program.

1. **Configure the JDK**: Ensure Intellij is configured to use JDK 11.
2. **Import the project as a Gradle project**: Choose the option to import the project as a Gradle project when prompted.
3. **Verify the setup**: Enter some commands to ensure E-Duke-8 functions as expected. Refer to our [User Guide](https://ay2021s1-cs2113t-f12-3.github.io/tp/UserGuide.html) for more information.

## Design & implementation

### Model Component

####  Design of TopicList

1. Listing topics in TopicList
2. Finding a topic in TopicList
3. Adding a new note
4. Deleting an existing note
5. Listing out all notes in a topic

![TopicList_Class_Diagram](./images/TopicListAndNotes.png)

TopicList is an ArrayList of type Displayable, which is one of two interfaces implemented 
in the code for EDuke8. As such, many of the commands that manipulate the TopicList make 
use of the package java.util.ArrayList. The TopicList is used to store Topics.

#### Implementation of TopicList

**Listing topics in TopicList:**

![TopicListSampleSequence](./images/TopicListSampleSequence.png)

This task is performed by the `TopicList.showTopics()` method.

Step 1: The `parseCommand()` method instantiates a `TopicsCommand` object which then calls the 
        `TopicList.showTopics()` method.
Step 2: The `TopicList.showTopics()` method then calls the method `Ui.printTopicList()`. The 
        current `TopicList` is passed into the called method.
Step 3: The `Ui.printTopicList()` method then prints out the description of each topic in the 
        `TopicList`. 

**Finding a topic in TopicList:**

This task is performed by the `TopicList.find()` method. 

Step 1: The `parseCommand()` method instantiates a `TopicsCommand` object which then calls the `TopicList.find()` 
        method. A String object derived from the user's input is passed into this method.

Step 2: The `TopicList.find()` method checks if any `Topic` object in the `TopicList` has a description that contains 
        the `String` object passed into the method. Such `Topic` objects are stored in a new `TopicList`.

Step 3: The `TopicList.find()` method then calls the `TopicList.showTopics()` method, passing in the new TopicList. The 
        `Ui.printTopicList()` method is called within the `TopicList.showTopics()` method, printing out topic 
        descriptions containing the user's input.

`NoteList` is also an `ArrayList` of type `Displayable`, which is one of two interfaces implemented in the code for 
E-Duke-8. As such, many of the commands that manipulate the `TopicList` make use of the package `java.util.ArrayList`. 
The `NoteList` stores `Note` objects. Each topic has 1 `NoteList`. 

#### Implementation of Notes

**Adding a new note:**

This task is performed by the `NoteList.add()` method.

Step 1: The `parseCommand()` method instantiates a `NoteCommand` object which then calls the `NoteList.add()` method. 
        A new `Note` object is passed into its parameter.

Step 2: The `NoteList.add()` method makes use of `ArrayList` API, specifically the `ArrayList.add()` method, to add 
        the `Note` object into `NoteList`.

**Deleting a note:**

This task is performed by the `NoteList.add()` method.

Step 1: The `parseCommand()` method instantiates a `NoteCommand` object which then calls the `NoteList.delete()` method. 
        An integer that represents the index of the `Note` object to be deleted within the `NoteList` is passed into 
        this method.

Step 2: The `NoteList.add()` method makes use of `ArrayList` API, specifically the `ArrayList.remove()` method, to 
        delete the `Note` object in `NoteList`.

**Listing out all notes in a topic**

This task is performed by the `Topic.showNotes()` method.

Step 1: The `parseCommand()` method instantiates a `TopicCommand` object which then calls the `Topic.showNotes()` method. 

Step 2: The `Topic.showNotes()` method calls the `Ui.printNoteList()` method. The topic's `NoteList` into this method. 
        `Ui.printNoteList()` prints out all the descriptions of the `Note` objects in the `NoteList`.

#### Design of Option and OptionList 

The `Option` and `OptionList` classes implements the `Displayable` and `DisplayableList` interfaces respectively. 
The `Option` object stores one option of a question while the `OptionList` object stores all 4 options of the same 
question. The class diagram below illustrates the structure of both classes. 

![Option_and_OptionList_Class](./images/Option.png)

### Logic Component

#### Design of Parser

![Parser Diagram](./images/ParserDiagram.png)

1. After constructing a new MenuParser() in the `Eduke8` class, the parseCommand() method is used to parse the 
   user command.
2. This results in a `Command` object, which is executed by `Command` class itself, using the execute() method.
3. The `Ui` object in the `Command` object is used to display the requested information, or to display the required task 
to be completed as per the user input.

#### Implementation of MenuParser

Below is the sequence diagram for how the Parser component of `Eduke8` works with commands to show output to the user.

![Parser Sample Sequence](./images/ParserSampleSequence.png)

The command parsing feature is our program’s way of reading the user’s input into the command line. It makes use of a 
single method `parseCommand` that identifies what command the user is calling for and then calls the command. There are 
two parsers in our program that implements a single `Parser` interface. One parser is for choosing menu options and is 
named `MenuParser`. The other parser is used during quizzes, in order to answer questions or request for hints, and is 
called `QuizParser`. Given below is an example usage scenario of how the command parsing feature works at each step, 
when the user types in input to get help in order to see what commands are available to the user.

Step 1. The user launches the program for the first time. The `MenuParser()` will be initialised and awaiting the user’s
        input to proceed.
        
Step 2. The user types in "help" into the command line interface and presses enter. This user input “help” is stored as 
        a string and is put into the `parseCommand()` method as a parameter, together with the list of topics. This 
        topic list is not relevant to the help command for now.
        
Step 3. The user input string is subjected to the `lang.string.trim()` and `lang.string.split()` functions of a string 
        in the Java libraries in order to remove redundant spaces around the input, and to discern the number of words 
        in the input. The `lang.string.split()` function uses a blank space string, “ “, as the delimiter to split the 
        string into its individual components.
        
Step 4. Each subsequent string separated by a space is stored in a string array named `commandArr`. The 0th index of the 
        `commandArr` array is the first word, the 1st index is the second word, and so on. In this case there is only 
        one word stored in the array, at the 0th index, which is “help”.
        
Step 5. The string at the 0th index is then used in a switch statement, where each case represents the different menu 
        options available. As such, the contents of the case with reference “help” is run, which is a return statement 
        containing a new `HelpCommand()`. This leads to the execution of the `help` command.
        
#### Design of QuizQuestionsManager

To start a quiz in E-Duke-8, the user will have to indicate the number of questions that he wants to attempt, as well as the topic to get the questions from. Thereafter, questions will be shown to the user one by one until all them are attempted. 

The Class Diagram given below explains the high-level design of the Quiz system in E-Duke-8. Given below it is a quick overview of each component.

![QuizQuestionsManager_Class_Diagram](./images/QuizQuestionsManager.png)

An object of `SingleTopicQuiz` class represents an instance of the quiz in E-Duke-8. Its `numberOfQuestions` attribute and `Topic` object correspond to the user's specified number of questions and topic for the quiz respectively.

The `startQuiz(:Ui)` method call from the `SingleTopicQuiz` object initializes an object of `QuizQuestionsManager` by passing into it `numberOfQuestions`, as well as an ArrayList of questions from the `Topic` object. The `QuizQuestionsManager` object will then randomly select `numberOfQuestions` questions from the topic the user has chosen, using its `setQuizQuestions(:int, :ArrayList<Displayable>)` method. 

Thereafter, by making use of `QuizQuestionsManager`'s `getNextQuestion()` and `areAllQuestionsAnswered()` method calls, the `goThroughQuizQuestions(:Ui, :QuizQuestionsManager)` will loop through the questions until the user has answered all of them on the command line interface.

#### Implementation of QuizQuestionsManager

As mentioned earlier in the section on the design of the quiz system, a `QuizQuestionsManager` object will randomly select the indicated number of questions from the list of questions in the `Topic` object, and these will form the quiz questions for the user.

The Sequence Diagram below shows how `QuizQuestionsManager` is implemented to achieve this for the scenario where the user indicates that he wants to attempt 5 questions from the topic on OOP, which translates to the `setQuizQuestions(5, questionsInTopic)` call:


![QuizQuestionsManager_setQuizQuestions](./images/QuizQuestionsManager_setQuizQuestions.png)

`nextInt(5)` is a method call to an object of the `Random` class. It returns a random integer between 0 (inclusive) and the number passed in as argument, 5 in this scenario, exclusive. 

To ensure that no two of the same question is selected, the selected randomQuestionIndex is checked to see if it is repeated. To determine if randomQuestionIndex is not selected before, an integer ArrayList is initialized to record all the selected integers. By checking against this collection of integers, it can be determined if a currently selected integer is repeated or not, and if it is, no question will be added for that iteration of the loop. 

An ArrayList of `Question` objects stores all the selected questions meant for the quiz.

### Storage Component

#### Design of TopicsStorage

Data is loaded automatically from JSON files in the data folder. This is mainly facilitated through the `TopicsStorage` 
class which handles accessing the file as well as converting from JSON into `Topic`, `Question` and `Option` objects. The class diagram below shows this relationship.

![TopicsStorage Class Diagram](./images/TopicsStorage.png)

The format of the JSON file is important as it is loaded in a particular way. This format was designed as an array of topics that hold the different properties for questions, options, hints and explanations. An example is as such:

```json
[
  {
    "topic": "OOP",
    "questions": [
      {
        "description": "What is encapsulation?",
        "hint": "check the textbook",
        "explanation": "because option A is the best answer",
        "options": [
          {
            "description": "A",
            "correct": true
          },
          {
            "description": "B",
            "correct": false
          },
          {
            "description": "C",
            "correct": false
          },
          {
            "description": "D",
            "correct": false
          }
        ]
      }
    ]
  }
]
```

#### Implementation of TopicsStorage

Given below is an example usage scenario of loading in two topics with two questions each.

When the user launches the app, the main program will initialize a `TopicsStorage` object and call the `load` method 
which will return a `TopicList` object. The following sequence diagram shows how the load operation works:

![TopicsStorage load](./images/TopicsStorage_load.png)

As there is a high level of nesting in the JSON file, many methods are called in loops to parse each section and return them as objects which are then used to build the next object at a higher level. For example, a `Question` object requires an `OptionList` which is created with an `ArrayList<Option>` collection. Thus, `parseToOptionObject(optionAsJson)` must first be called to return the `Option` objects to be placed in the `ArrayList<Option>`. More properties can easily be added to the classes and the storage component in a similar way, by parsing in loops.

### UI Component

#### Implementation of Ui

The `Ui` class handles all the interactions with the users. It reads the input from the users and prints out replies to 
the users. It is the point of communication between EDuke8 and the users. 

An example is provided below to illustrate how the `Ui` class prints out the starting page of the quiz for 
the user to comprehend. 

![Ui_Printing_Start_Quiz_Page](./images/PrintQuizStartPage.png)

As the user starts the quiz, the `Ui` class will print out the quiz page to show that the quiz has started. 
The user inputs the number of questions that he wants to answer and also the topics that he wants to be tested on. 
The `Ui` will go through printStartQuizQuestions() to print out the number of questions that the user has chosen. 
Afterwards, the `Ui` will go through printStartQuizTopics() to print out the topics that the user has chosen. 

## Product scope

### Target user profile

CS2113/T Students

### Value proposition

Help CS2113/T students learn and understand software engineering and OOP principles through a gamified platform and 
enhance their learning experience. Consolidate key concepts for easy revision.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|quickly see what the software has to offer|understand how to use the app|
|v1.0|new user|answer given questions|start testing myself immediately|
|v1.0|long-time user|get different questions each time|repeatedly test my understanding for the particular topic|
|v1.0|busy user|test myself on concepts using short, targeted quizzes|confirm my understanding of concepts|
|v1.0|user|get a hint for the question in the quiz|I can think about the question from a different angle|
|v1.0|user|see what the available topics are|navigate around the app effectively|
|v1.0|user|select the number of questions to do in the quiz|manage the workload and time spent on the quiz|
|v2.0|slow but hardworking user|see the explanations provided in the quiz|learn from my mistakes and revise|
|v2.0|busy, lazy user|take note of key concepts|refer to it easily at a later time|
|v2.0|frequent disorganized user|view the percentage of error in each topic|tell how well I understand the content|


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
