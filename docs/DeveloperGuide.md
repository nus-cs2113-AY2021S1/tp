# Developer Guide

## Introduction

RevisED is a command line application to help students revise by creating flashcards and taking quizzes, 
and keep track of their deadlines, tasks and any other important dates. This application allows users to add subjects, 
their different topics and create different flashcards in form of question and answers on each topic. Students can also 
take quizzes which asks them the questions that they added in form of flashcards and prompted to answer them. 
The student can view results of these quizzes immediately and are stored so that students can view them later. 
Additionally, students can delete, and list subjects, topics, and flashcards and find and enter subjects and topics.   

This developer guide is for developers who wish to understand and/or develop ReviseED further. 
This guide includes design, implementation, product scope, user stories, non-functional requirements, glossary, 
testing and development sections to help developers better understand the application.

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
There are two classes inside this package, both of which are inside `Storage.java`. The following diagram shows the
relationship between the two classes.

![Storage uml s](https://user-images.githubusercontent.com/15065550/97129522-0eff6680-177a-11eb-85f5-41f4917bf8c3.png)

#### StorageBuilder

`StorageBuilder` class is a static inner class of the `Storage` class. It follows the builder pattern and its sole purpose
is to create `Storage` instances. The [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) is used to deal 
with the decrease in clarity due to the increase of the number parameters. Every instance of the `Storage` class should 
be created through `StorageBuilder`, and this is enforced by disabling the public constructor of the `Storage` class.

#### Storage

`Storage` class handles every operation that involves data storage, including saving, loading, and exporting of 
application data. As such, it depends on classes whose data should be saved, which is shown in the diagram below.

![card dependency](https://user-images.githubusercontent.com/15065550/97129797-c09e9780-177a-11eb-8b65-b81fdc869810.png)

##### Storing data

`Storage` class stores the data following the same logical structure (subject -> topic) of the application to 
make the stored data more presentable to the user. `Subject` and `Topic` data are stored as nested directories, while 
`Flashcard` and `Result` data are stored in [JSON](https://en.wikipedia.org/wiki/JSON) format so that they are readable 
to the users and allows them to change the saved data manually. `Task` data are stored differently as text files due to 
legacy reason, but they are also readable and can be changed manually as well. An example of the structure mentioned is 
shown in the figure below.

```
Maths
├── Algebra
│   ├── results.json
│   └── flashcards.json
├── results.json
└── tasks.txt
```

This class also stores the data in a cascading manner, meaning that when a call to save topics is made, all the data 
under the topics will be saved. Similarly, when a call to save subjects is made, all the data under the subjects
including topics will be saved. The behavior mentioned is shown in the diagram below.

![subject sq](https://user-images.githubusercontent.com/15065550/97132939-43c3eb80-1783-11eb-91c2-09b79eb2e2f5.png)
![topic save sq](https://user-images.githubusercontent.com/15065550/97132990-74a42080-1783-11eb-91f8-f6143c0e38c4.png)

##### Loading data

The loading of data follows a similar cascading manner as saving, as can be seen in the diagram below.

![subject load sq](https://user-images.githubusercontent.com/15065550/97138326-e59f0480-1792-11eb-8161-7653625d71c4.png)
![topic load sq](https://user-images.githubusercontent.com/15065550/97138329-e6d03180-1792-11eb-8853-77c3d9e80f6d.png)

Before returning topics and subjects, the methods sort them in alphabetical order to let the users locate their 
subjects or topics more easily. 

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

* *Revised* - The main class which initiates the whole application.
* *ui* - Package which contains the Ui class, in charge of user interactions.
* *task* - Package which contains the task classes.
* *Task* - An abstract class which Todo,Deadline and Even classes inherit from.
* *Deadline* - A class which refers to a task with a deadline. Inherits from the Task class.
* *Event* - A class which refers to an event. Inherits from the Task class.
* *TaskList* - A class which initialises an ArrayList of tasks.
* *Storage* - A package which holds the Storage class, which is in charge of storing and loading data
into the application.
* *parser* - A package which parses the user commands and initialises the necessary topics,subjects or tasks.
* *FlashcardParser* - A class which reads the user commands to call the relevant Flashcard command.
* *SubjectParser* - A class which reads the user commands to call the relevant Subject command.
* *TaskParser* - A class which reads the user commands to call the relevant task command.
* *TopicParser* - A class which reads the user commands to call the relevant topic command.
* *card* - Package holds the necessary classes for the quiz, subject,topic and flashcard functionality.
* *quiz* - Package holds the classes for the quiz functionality.
* *Quiz* - An Abstract class that holds the result, list of flashcards and a list of incorrectAnswers for the 
SubjectQuiz class and the TopicQuiz class to inherit from.
* *SubjectQuiz* - Instantiated when the user calls for a quiz on a subject. 
* *TopicQuiz* - Instantiated when the user calls for a quiz on a topic. 
* *Result* - A class which stores the marks from a quiz and a description which indicates the performance of the user 
for that quiz.
* *ResultList* - A class which holds an ArrayList of results.
* *Flashcard* - A class which instantiates the flashcard object.Contains the question and answer feature of the flashcard.
* *Subject* - A class which instantiates the subject object. Holds the title of the subject and lists of flashcards, 
results and topics.
* *SubjectList* - A class which holds an ArrayList of Subjects.
* *Topic* - A class which is instantiated when the user creates the topic. Holds the title of the topic, an arraylist of
flashcards and an arraylist of results.
* *TopicList* - A class which holds an ArrayList of Topics.

## Instructions for manual testing

### Testing the Jar File
1. Refer to the [UserGuide](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html) on how to download the application.
2. Open the application in command prompt.
3. Try out various commands in the userguide to check if it works.
4. Try out invalid commands and check if the application responds correspondingly.
5. Exit the application and check the data files to check if all the data has been saved.
6. Open the application again and check if the data has been loaded correctly. Use the `list` command for this step.

### Testing the source code

#### Setting up the project
1. Download the source code from [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).
2. The application is optimised for IntelliJ users. Download IntelliJ if you do not have it.
3. Configure the Intellij for JDK 11, as described [here](https://se-education.org/guides/tutorials/intellijJdk.html).
4. Import the project as a Gradle project, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
5. After the importing is complete, locate the `src/main/java/seedu.revised/Revised.java` file, right-click it, and choose
`Run Revised.main()`.
6. If the setup is correct, you should see soemthing like this:
<pre><code>
> Task :compileJava
> Task :processResources UP-TO-DATE
> Task :classes

> Task :Revised.main()
Hello from
                               __________
                              |  __ |  _ \
 ____  ______      _____      |  |__| | | |
|  __|/ __ \ \    / /| | ____ |   __| | | |
| |  |  __/ \ \__/ / | | \____|  |__| |_| |
| |   \___|  \____/  |_| ____/|_____|_____/

____________________________________________________________
 Hello! I'm revisED
____________________________________________________________
Alright, What can I do for you?
____________________________________________________________
</code></pre>


#### Build Automation Using Gradle
* This project uses Gradle for build automation and dependency management.
 It includes a basic build script as well (i.e. the build.gradle file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

#### Testing

#### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` folder. Open it in the terminal
by right-clicking on the folder and click `open in terminal`.
*  Run the `runtest(.bat/.sh)` script.
* If the tests are successful, you would see this:
<pre><code>
BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed
Test passed!
</code></pre>
* If the test fails, you would see this :
<pre><code>
BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed
Test failed!</code></pre>
* Navigate to the `text-ui-test` folder and compare the `ACTUAL.txt` file and the `EXPECTED.txt` file to 
observe the error.


#### JUnit tests
The project has multiple JUnit tests for different functions.
* A skeleton JUnit test (`src/test/java/seedu/revised/RevisedTest.java`) is provided with this project template. 
* Under the `src/test/java/seedu/revised` folder, you would be able to access the different tests for
the functionalities. Right click on the folder and click the `Run Tests in ...` option to run the tests
in the folder.  
* The results would be displayed in the terminal.If a test fails, click on the hyperlink provided
to navigate to the failed test.
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

#### Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* Right click on the Gradle bar on the right of the IDE and click `tp/Tasks/other/checkstyleMain` to test the checkstyle in the
code.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

#### Documentation

`/docs` folder contains the project documentation.It contains the following pages:

* Individual contributions under the  `team` folder
* The .md files for the UserGuide,README,and the DeveloperGuide


## Development

### Logging
Whenever you need to use logging in a class, add this line 
`private static final Logger logger = Logger.getLogger(<CurrentClass>.class.getName());` to the start
of the class, where \<CurrentClass\> is replaced by the class name you are adding the logger to.

The logging configuration can be found under `src/main/resources/logging.properties`. The current configuration directs
the logs to a file named `revisED%u.log` in the project root directory. 