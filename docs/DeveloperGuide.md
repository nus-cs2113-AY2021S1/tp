# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Logic Component

##### Design

![Parser Diagram](./images/ParserDiagram.png)

1. After constructing a new MenuParser() in the `Eduke8` class, the parseCommand() method is used to parse the 
   user command.
2. This results in a `Command` object, which is executed by `Command` class itself, using the execute() method.
3. The `Ui` object in the `Command` object is used to display the requested information, or to display the required task to be 
   completed as per the user input.
   
Below is the sequence diagram for how the Parser component of `Eduke8 works with commands to show output to the user.

![Parser Sample Sequence](./images/ParserSampleSequence.png)

#### Command parsing feature

##### Implementation

The command parsing feature is our program’s way of reading the user’s input into the command line. It makes use of a 
single method parseCommand that identifies what command the user is calling for and then calls the command. There are 
two parsers in our program that implements a single Parser interface. One parser is for choosing menu options and is 
named `MenuParser`. The other parser is used during quizzes, in order to answer questions or request for hints, and is 
called `QuizParser`. Given below is an example usage scenario of how the command parsing feature works at each step, when 
the user types in input to get help in order to see what commands are available to the user.

Step 1. The user launches the program for the first time. The parser will be initialised and awaiting the user’s input 
        to proceed.
        
Step 2. The user types in help into the command line interface and presses enter. This user input “help” is stored as 
        a string and is put into the parseCommand() method as a parameter, together with the topic list. This topic 
        list is not relevant to the help command for now.
        
Step 3. The user input string is subjected to the trim() and split() functions of a string in the Java libraries in 
        order to remove redundant spaces around the input, and to discern the number of words in the input. The split() 
        function uses a blank space string, “ “, as the delimiter to split the string into its individual components.
        
Step 4. Each subsequent string separated by a space is stored in a string array named commandArr. The 0th index of the 
        commandArr array is the first word, the 1st index is the second word, and so on. In this case there is only one 
        word stored in the array, at the 0th index, which is “help”.
        
Step 5. The string at the 0th index is then used in a switch statement, where each case represents the different menu 
        options available. As such, the contents of the case with reference “help” is run, which is a return statement 
        containing a new HelpCommand(). This leads to the execution of the `help` command.
        
### Loading Data

Data is loaded automatically from JSON files in the data folder. This is mainly facilitated through the `TopicsStorage` class which handles accessing the file as well as converting from JSON into `Topic`, `Question` and `Option` objects.

![TopicsStorage Class Diagram](./images/TopicsStorage.png)

Given below is an example usage scenario of loading in two topics with two questions each.

When the user launches the app, the main program will initialize a `TopicsStorage` object and call the `load` method which will return a `TopicList` object. The following sequence diagram shows how the load operation works:

![TopicsStorage load](./images/TopicsStorage_load.png)

### Design of the Quiz system

![QuizQuestionsManager_Class_Diagram](./images/QuizQuestionsManager.png)

The Class Diagram given above explains the high-level design of the Quiz system in E-Duke-8. Given below is a quick overview of each component.

An object of `SingleTopicQuiz` class represents an instance of the quiz in E-Duke-8.

To start a quiz in E-Duke-8, a user will have to indicate the number of questions that he wants to attempt, as well as the topic to get the questions from. These correspond to the `numberOfQuestions` attribute, and the ‘Topic’ object in `SingleTopicQuiz ` respectively.
The `startQuiz(ui)` method call initializes an object of `QuizQuestionsManager` by passing into it `numberOfQuestions`, as well as an ArrayList of questions from the `Topic` object. The `QuizQuestionsManager` object will then randomly select `numberOfQuestions` questions from the topic the user has chosen. Thereafter, by making use of `QuizQuestionsManager`'s `getNextQuestion()` and `areAllQuestionsAnswered` method calls, the `goThroughQuizQuestions` will loop through the questions until the user answers all of them on the command line interface.

### Implementation of QuizQuestionsManager

As mentioned in the section on the design of the quiz system, a `QuizQuestionsManager` will randomly select the indicated number of questions from the list of questions in the `Topic` object, and these will form the quiz questions for the user.

The *Sequence Diagram* below shows how `QuizQuestionsManager` achieves this for the scenario where the user indicates that he wants to attempt 5 questions from the topic on OOP, which translates to the `setQuizQuestions(5, questionsInTopic)` call:


![QuizQuestionsManager_setQuizQuestions](./images/QuizQuestionsManager_setQuizQuestions.png)

Note: The questionsInTopic corresponds to the OOP `Topic` object, and therefore the questions passed into the method call are that of the OOP topic.

`nextInt(5)` is a static method call to an object of the `Random` class. It returns a random integer between 0 (inclusive) and the number passed in as argument, 5 in this scenario, exclusive. 
To determine if randomQuestionIndex is not selected before, an integer ArrayList is initialized to record all the selected integers. By checking against this collection of integers, it can be determined if the current integer is repeated or not, and if it is, no question will be added for that iteration of the loop. 
An ArrayList of `Question` objects is used to store all of the selected questions meant for the quiz.


## Product scope
### Target user profile

CS2113/T Students

### Value proposition

Help CS2113/T students learn and understand software engineering and OOP principles through a gamified platform and enhance their learning experience. Consolidate key concepts for easy revision.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|answer given questions|start testing myself immediately|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
