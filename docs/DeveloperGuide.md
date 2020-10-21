# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

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
