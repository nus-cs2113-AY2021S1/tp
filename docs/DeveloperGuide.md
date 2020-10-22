# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

The application has been broken down into different segments so that users would find it easy to navigate from one
segment to the next one. There are three segments within the application:
* Subjects: This segment edits the list of all the subjects present within the application and takes care of relevant
commands such as add subject, delete subject or find subject.
* Subject: This segment is accessed through the `subject NAMEOFSUBJECT` command. The user would then look at the individual 
subjects and then would be able to add either tasks or topics.
* Topic : This segment is accessed through the `topic NAMEOFTOPIC` command. The user can add, delete or find flashcards
in this segment.

The code is broken down into different packages, which combine classes that perform a similar functionality. Here
are the list of packages:
* Card: In charge of classes related to the subject, topic, flashcard and quiz functionalities
    - Quiz
        * Quiz
        * Result
        * ResultList
        * TopicQuiz
        * SubjectQuiz
    - Flashcard
    - Subject
    - SubjectList
    - Topic
    - TopicList
    

* Command: In charge of reading the command and calling the relevant methods. The package itself holds
the following packages.
    - Flashcard
    - Subject
    - Topic
    - Task
    
![Flashcard Commands](https://user-images.githubusercontent.com/46095141/96748916-c9027580-13fc-11eb-8c5b-4266d750aa6f.png) 
**Flashcard Commands**

The diagram above shows how the flashcard commands extend the abstract command class.  
* Exceptions: Deals with the various exceptions which are thrown in the application.
    -flashcard
    - storage
    - subject
    - task
    - topic
* Parser: Parses the commands and instantiates the relevant command classes.
* Storage: Stores the data in an external folder
* Task: Contains the classes for the different types of tasks.
* Ui: In charge of interaction with the user.

![Package Diagram](https://user-images.githubusercontent.com/46095141/96674190-c7a06100-139a-11eb-95e6-64bc2721e216.png)
**Package Diagram**
The Package Diagram above shows how the Revised class interacts with all of these packages.

### Storage package
There are two classes inside this package, both of which are inside `Storage.java`.

![Storage Class Diagram](https://user-images.githubusercontent.com/15065550/96747993-8e4c0d80-13fb-11eb-8b28-9171daf23098.png)

`StorageBuilder` class is a static inner class of `Storage` class. It follows the builder pattern and its sole purpose
is to create `Storage` instances. The builder pattern is used to deal with the decrease in clarity due to the increase 
of the number parameters.

## Product scope


### Target user profile

The targeted audience are university students , aged 18 - 25 who have access to a computer. The students must be familiar
with applications that run on the command-line interface. Currently, this application is created for students who study in 
Singaporean universities.

### Value proposition


This application aims to help students to keep track of their revision. Students should be
able to revise their subjects effectively through this application by adding various questions
and answers in the form of flashcards. After that, students can refer to the flashcards to
revise the subjects. Furthermore, they can take quizzes and see if they grasp the topics or subjects well
enough to attend their school based exams.

Students who find it hard to keep track of their assignments would also find this application
useful since it lets the students add their tasks. The application then reminds the students
regarding the upcoming tasks, which would prompt the students to prepare them.

Students who find it difficult to organise woudl find this app useful since it
offers a one stop solution to manage both their revision and their upcoming tasks .
## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user| add a flash card intuitively|don't get put off by learning a new program |
|v1.0|long time user| categorize the flashcards to different categories|I know where to look for which flashcard|
|v1.0|organised person| i would want a program that looks neat| will want to use the program|
|v1.0|potential user| default flashcard being shown to me| understand how a flashcard looks in this application|
|v1.0|language student|type in all my notes|practice the spelling of the words|
|v1.0|student|revise all my notes for different subjects|do not have to use multiple applications/websites to revise|
|v1.0|student| to have colour codes for my notes|quickly glance through and know how much I need to study|
|v1.0|student|to be able to review them at once or print them|use them offline or as cheat sheets|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
|v2.0|forgetful student|be notified of assignments which are due in a week|prepare for them|
|v2.0|developer|log messages from the application|troubleshoot for errors|


## Non-Functional Requirements

The application satisfies the following requirements:
 * Portability: Users can transfer their data from the application since all the data is stored in a txt file.
 * Modifiability: Since the code is broken down into different packages and classes, developers would
 be able to modify the application with ease.
 * Simplicity: The application comes with a detailed user guide along with the help commands. Hence, users can always 
 refer to these to understand on how to use the application.
 * Compatibility: The application runs on various operating systems: Windows, Linux etc
 * Testability: The application is built using Gradle, a tool that offers various testing capabilities. Furthermore,
 developers could also modify the input.txt file to provide a set of inputs and test if the application returns the 
 expected output.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

## Development

### Logging
Whenever you need to use logging in a class, add this line 
`private static final Logger logger = Logger.getLogger(<CurrentClass>.class.getName());` to the start
of the class, where \<CurrentClass\> is replaced by the class name you are adding the logger to.

The logging configuration can be found under `src/main/resources/logging.properties`. The current configuration directs
the logs to a file named `revisED%u.log` in the project root directory. 