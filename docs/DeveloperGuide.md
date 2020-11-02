# Developer Guide

## Table of contents
* <a href ="#intro">1. Introduction</a> <br>
* <a href ="#design">2. Design & Implementation</a> <br>
    * <a href ="#card">2.1 Card Package</a> <br>
        * <a href ="#quiz">Quiz Package</a> <br>
            * <a href ="#Qclass">Quiz Class</a> <br>
            * <a href ="#Qsubject">SubjectQuiz Class</a> <br> 
            * <a href ="#Qtopic">TopicQuiz Class</a> <br> 
            * <a href ="#Qresult">Result Class</a> <br>
        * <a href ="#task">Task Package</a> <br>
            * <a href ="#Ttask">Task Class</a> <br>
            * <a href ="#Ttodo">Todo Class</a> <br>
            * <a href ="#Tdeadline">Deadline Class</a> <br>
            * <a href ="#Tevent">Event Class</a> <br>
        * <a href ="#flashcard">Flashcard Class</a> <br>
        * <a href ="#subject">Subject Class</a> <br>
        * <a href ="#topic">Topic Class</a> <br>
    * <a href ="#command">2.2 Command Package</a> <br>
    * <a href ="#list">2.3 List Package</a> <br>
    * <a href ="#parser">2.4 Parser Package</a> <br>
    * <a href ="#ui">2.5 Ui Package</a> <br>
    * <a href ="#storage">2.6 Storage Package</a> <br>
       - <a href ="#Sbuilder">Storage Builder</a> <br>
       - <a href ="#Sstorage">Result</a> <br>
* <a href ="#scope">3. Product Scope</a> <br>
   - <a href ="#Ptarget">Target User Profile</a> <br>
   - <a href ="#Pvalue">Value Proposition</a> <br>
* <a href ="#user">4. User Stories</a> <br>
* <a href ="#nfr">5. Non functional Requirements</a> <br>
* <a href ="#glossary">6. Glossary</a> <br>
* <a href ="#manual">7. Testing</a> <br>
    - <a href ="#jartest">Testing the Jar File</a> <br>
    - <a href ="#sourcetest">Testing the Source Code</a> <br>
* <a href ="#develop">8. Development</a> <br>
    - <a href ="#log">Logging</a> <br>

## 1. Introduction <a name="intro"> </a>

RevisED is a command line application to help students revise by creating flashcards and taking quizzes, 
and keeping track of their deadlines, tasks and any other important dates. This application allows users to add subjects, 
their different topics and create different flashcards in form of question and answers on each topic. Students can also 
take quizzes which asks them the questions that they added in form of flashcards and prompted to answer them. 
The student can view results of these quizzes immediately and are stored so that students can view them later. 
Additionally, students can delete, and list subjects, topics, and flashcards and find and enter subjects and topics.   

This developer guide is for developers who wish to understand and/or develop ReviseED further. 
This guide includes design, implementation, product scope, user stories, non-functional requirements, glossary, 
testing and development sections to help developers better understand the application.

Note the following symbols and formatting used in this document:

Symbols / Formatting|Meaning
------|------
ℹ️ **_NOTE:_** | Important or additional information
⚠️ **_WARNING:_** | Things to avoid or pay extra attention to
✔️ **_TODO:_** | Things that should be but have not been done
`Grey highlight` | Code or terms related to the code/application

## 2. Design & implementation <a name= "design"> </a>

This section describes how RevisED has been designed and implemented so that developers can better understand the 
designing of the code, with the help of visual aids.

The application has been broken down into different segments so that users would find it easy to navigate from one segment 
to the next one. There are three segments within the application:
* Main: This segment edits the list of all the subjects present within the application and takes care of relevant commands 
such as add subject, delete subject or find subject.
* Subject: This segment is accessed through the `subject NAMEOFSUBJECT` command. The user would then look at the individual 
subjects and then would be able to add, delete, list or find either tasks or topics.
* Topic : This segment is accessed through the `topic NAMEOFTOPIC` command. The user can add, delete or list flashcards
in this segment.

The code contains the main class Revised, as well as different packages, which combine classes that perform a similar 
functionality. Here are the list of packages:

* Card: In charge of classes related to the subject, topic, flashcard and quiz functionalities
* Command: In charge of reading the command and calling the relevant methods. The package itself holds
the following packages.
* Exceptions: Deals with the various exceptions which are thrown in the application.
* Parser: Parses the commands and instantiates the relevant command classes.
* Storage: Stores the data in an external folder
* Task: Contains the classes for the different types of tasks.
* Ui: In charge of interaction with the user.

### 2.1 Card Package
The Card package consists of different classes that holds information on the main functionalities of the application,
and is split into 2 packages, quiz and task, as well as Subject, Topic and Flashcard classes.
- Quiz Package
    * Quiz class
    * Result class
    * TopicQuiz class
    * SubjectQuiz class
- Task Package
    * Deadline class
    * Event class
    * Task class
    * Todo class
- Flashcard class
- Subject class
- Topic class

Each of the classes in the card package contains:
* Getters and Setters of its own attributes
* methods that alter an instance of its own class

#### Quiz package <a name = "quiz"> </a>
The `Quiz` package holds the necessary classes for the quiz functionality of this application. The following are
the classes in the package. An abstract`Quiz` class, a `Result` class, a `SubjectQuiz` class and a `TopicQuiz` class.

#### Quiz class <a name = "Qclass"> </a>
`Quiz` class is an abstract class in the `Quiz` package. It is holds the result of a quiz class and the list of flashcards
from any subject or topic classes which the user calls the quiz for. Furthermore, it contains a checkAnswer() method the
checks the answer that the user had given with the correct answer of the quiz. If the user enters the correct answer, the 
existing score is incremented by one. Else, the contents of the flashcards and the incorrect answer provided by
the user are transferred to the  `incorrectAnswers` list. Once the user finished the quiz, the application would print the 
questions that the users did not answer correctly, along the the answer that was provided by the user. 

#### SubjectQuiz <a name = "Qsubject"> </a>
`SubjectQuiz` class inherits from the `Quiz` class and initiates the quiz. The `startQuiz` method calls for the  `setupQuiz` method checks for the presence
of topics or flashcards. Else, the application throws the `NoTopicException` for the former, and the 
`NoFlashcardException` for the latter. If the topics have flashcards, then these are transferred to the 
`SubjectQuiz` class, while the maximum score of the quiz is set to be the total number of flashcards in the list of flashcards
present in the `SubjectQuiz` class . 

The application now returns to the `startQuiz` method and the current score of the quiz is set to zero. Subsequently, the
application begins printing the questions from the flashcards and checks the answer that you provide. If you want to stop the 
quiz, use the `stop` command. The application will then print out the score that you obtain. If you complete the quiz
then the application not only prints the score, but it also prints the incorrect answers from the quiz.

The following diagram shows how you can initiate the quiz for a subject.
![first](https://user-images.githubusercontent.com/46095141/97313097-3866e200-18a1-11eb-9525-73e38ceb7cbe.png)

#### TopicQuiz <a name = "Qtopic"> </a>
`TopicQuiz` class is similar to the `SubjectQuiz` class, except for the fact that it initiates the quiz
only for the specific topic. Furthermore, this class only throws the `NoFlashcardException` for when the topic does not 
have any flashcards, which is detected by the `setupQuiz` method. The implementation of the `startQuiz` method is similar
to that of the SubjectQuiz class.

##### Result <a name = "Qresult"> </a>
`Result` class  stores the result of a quiz . It has three instance variables, namely the `score` variable
which tracks the score during the quiz, the  `maxScore` variable which is the maximum score that you can get from doing the 
quiz, while the  `description` variable will be a grade given to you depending on your performance.
The  `UpdateResult` method  updates the score of the quiz during the quiz and changes the grade of the quiz. 

#### Task Package <a name = "task"> </a>
The `Task` package holds the necessary classes for the Task functionality of this application. The following are
the classes in the package. A `Task` class, a `Todo` class, a `Deadline` class and an `Event` class.

##### Task class <a name = "Ttask"> </a>
The `Task` class is the superclass of `Todo`, `Deadline` and `Event`. It has attributes such as the description of the task-`String`
and an attribute to check if the task is completed-`Boolean`. It holds basic getters for its attributes, getters such as getting the 
status icon to check if a Task is done-`String`, and getters for the DateTime features of the Deadline and Event classes. It also has
 a toString methods to print the Task.
 
###### Todo Class <a name = "Ttodo"> </a>
The `Todo` class is the class to store information on basic tasks of the user. It contains methods and attributes similar to the
ones found in its superclass `Task`. It has an additional Override toString method, to differentiate between itself and other
tasks, such as Deadline and Event.

An example of how a Todo task "return books" will look like in the command line for user intepretation is shown below.
```
____________________________________________________________
Got it. I've added this task:
 [T][✘] return books
Now you have 1 task in the list.
____________________________________________________________
```

###### Deadline Class <a name = "Tdeadline"> </a>
The `Deadline` class is the class to store information on tasks of the user that has a deadline. It contains methods and attributes similar to the
ones found in its superclass `Task`, and additional attributes to make it a `Deadline` Task, such as an additional dateTime attribute 
to keep track of the deadline of the task-`LocalDateTime`, an Override toString method, to differentiate between itself and other tasks, such as Deadline 
and Event.

An example of how a Deadline task "return books /by 23:59 10-11-2020" will look like in the command line for user intepretation is shown 
below.
```
____________________________________________________________
Got it. I've added this task:
  [D][✘] return books (by: 11:59 PM 10 Nov 2020)
Now you have 2 tasks in the list.
____________________________________________________________
```

###### Event Class <a name = "Tevent"> </a>
The `Event` class is the class to store information of tasks of the user that is an event with a specific date and time occurrence. 
It contains methods and attributes similar to the ones found in its superclass `Task`, and additional attributes to make it a `Deadline`
Task, such as an additional dateTime attribute to keep track of the date and time of the event-`LocalDateTime`, an Override toString method,
to differentiate between itself and other tasks, such as `Todo` and `Deadline`.

An example of how an Event task "event CS2113T meeting /at 12:00 10-11-2020" will look like in the command line for user intepretation is shown 
below.
```
____________________________________________________________
Got it. I've added this task:
  [E][✘] CS2113T meeting (at: 12:00 PM 10 Nov 2020)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### Flashcard Class <a name = "flashcard"> </a>
The `Flashcard` class is a class to store information of flashcards. It has attributes such as the question
and answer of each flashcard, a constructor, as well as getters for both question and answer of the flashcards.


#### Subject Class <a name = "subject"> </a>
The `Subject` class is a class to store information of subjects. It has various attributes such as the title of the 
subject-`String`, a list of various topics in a subject-`TopicList`, a list of various tasks present in a subject-`TaskList`
and a list of various results of the quiz of a subject-`ResultList`. It also consists of a constructor, getters to all of the
attributes in the `Subject` class and a toString method to return the title of the subject.

#### Topic Class <a name = "topic"> </a>
The `Topic` class is a class to store information of topics. It has various attributes such as the title of the subject-`String`,
a list of various flashcards in a topic-`List<Flashcard>`, and a list of the results for all topic quizzes-`ResultList`. It also 
contains various constructors, getters for all its attributes, and a toString method that returns the title of the topic.

An example of how classes in the card package interact with each other is shown in the figure below. 
In this example, a student has 2 main subjects, mainly Math and Science. Math has topics algebra and calculus, while science has topics speed and light. He also made flashcards the various topics.

### 2.2 Command Package
The Command package consists of different classes that executes the various commands of the application due to user input,
and is split into 4 packages, flashcard, subject, task and topic, as well as a abstract command class, that is a superclass of the various classes in the different packages.

* Command: In charge of reading the command and calling the relevant methods. The package itself holds
the following packages.
    - Flashcard
    - Subject
    - Topic
    - Task

### 2.6 Storage Package <a name = "storage"> </a>
There are two classes inside this package, both of which are inside `Storage.java`. The following diagram shows the
relationship between the two classes.

![StorageClass](https://user-images.githubusercontent.com/15065550/97547893-9cfe6b80-198b-11eb-8475-0992c8274ee3.png)

<sub>***Figure 2.6.1** UML class diagram for storage package*</sub>

#### StorageBuilder <a name = "Sbuilder"> </a>

`StorageBuilder` class is a static inner class of the `Storage` class. It follows the builder pattern and its sole purpose
is to create `Storage` instances. The [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) is used to deal 
with the decrease in clarity due to the increase of the number parameters. Every instance of the `Storage` class should 
be created through `StorageBuilder`, and this is enforced by disabling the public constructor of the `Storage` class.

#### Storage <a name = "Sstorage"> </a>

`Storage` class handles every operation that involves data storage, including storing, loading, and exporting of 
application data. Storing of data only happens when the application exits while loading of data only happens
when the application launches. On the other hand, exporting of data happens when the user runs `export` command.

##### Storing data 

`Storage` class stores the data following the same logical structure (subject -> topic) of the application to 
make the stored data more presentable to the user. `Subject` and `Topic` data are stored as nested directories, while 
`Flashcard` and `Result` data are stored in [JSON](https://en.wikipedia.org/wiki/JSON) format so that they are readable 
to the users and allows them to change the saved data manually. 
[Gson](https://github.com/google/gson/blob/master/UserGuide.md) library is used to create the json files.
`Task` data are stored differently as text files due to 
legacy reason, but they are also readable and can be changed manually as well. 

> ℹ️ **_NOTE:_** If the users modify the file contents with wrong syntax, the data in the affected files will
> not be loaded and empty data will be returned instead so that the application can still run.

An example of the structure mentioned is shown in the figure below.

<pre>
data                            <em>--> Data main folder</em>
└── Maths                       <em>--> Maths subject folder</em>
    ├── Algebra                 <em>--> Algebra topic folder under Maths subject</em>
    │   ├── topicResults.json
    │   └── flashcards.json
    ├── subjectResults.json
    └── tasks.txt
</pre>
<sub>***Figure 2.6.2** Sample directory structure*</sub>

###### File formats

One `tasks.txt` file is created under each subject folder, storing the tasks (Todo, Event, Deadline) data under 
that subject. An example of the file content is shown below.

<pre>
T | 1 | someTodoTask                                    <em>--> Todo task</em>
D | 0 | someDeadlineTask | 11:59 PM 20 Dec 2020         <em>--> Deadline task</em>
E | 0 | someEventTask | 1:00 PM 10 Nov 2020             <em>--> Event task</em>
</pre>
<sup>***Figure 2.6.3** Sample tasks.txt content*</sup>

The first column of data shows the type of task, where T corresponds to `Todo` task, D corresponds to `Deadline` 
task, and E corresponds to `Event` task. The second column shows if a task is completed, where 0 means not completed 
while 1 means completed. The third column shows the name of a task. Lastly, the fourth column shows the time and date of 
a deadline or event task. 

Moving on, one `subjectResults.json` is created under each subject folder, and one `topicResults.json` is created 
under each topic folder. `subjectResults.json` stores the quiz results the users have obtained from doing quizzes under 
a subject, while `topicResults.json` stores the quiz results the users have obtained from doing quizzes under a topic. 
Both of the files have the same content format, and an example of 
the file content is shown below.

<pre>
[
  {                                    <em>--> Result record 1</em>
    "score": 1.0,                      <em>--> Score obtained</em>
    "maxScore": 1.0,                   <em>--> Maximum score that can be obtained</em>
    "description": "Excellent"         <em>--> Result description</em>
  },
  {                                    <em>--> Result record 2</em>
    ...
  },
  ...
]
</pre>
<sup>***Figure 2.6.4** Sample subjectResults.json content*</sup>

This content format is a result of converting a list of `Result` data into json form. 

Lastly, one `flashcards.json` file is created under each topic folder, storing all the flashcard data the users 
have added under a specific subject and topic. An example of the file content is shown below.

<pre>
[
  {                                            <em>--> Flashcard 1</em>
    "question": "x + y = 4. y = ? ",
    "answer": "4 - x"
  },
  {                                            <em>--> Flashcard 2</em>
    ...
  },
  ...
]
</pre>
<sup>***Figure 2.6.5** Sample flashcard.json content*</sup>

Similarly, this content format is a result of converting a list of `Flashcard` data into json form.

###### Implementation

The implementation stores the data in a cascading manner, meaning that when a call to save topics is made, all the data 
under the topics will be saved. Similarly, when a call to save subjects is made, all the data under the subjects
including topics will be saved. The behavior mentioned is shown in the diagram below.

![StorageSaveSubjects](https://user-images.githubusercontent.com/15065550/97779044-cdcfd380-1b38-11eb-8e5e-a4f258a726e5.png)

<sub>***Figure 2.6.6** UML sequence diagram for storing subjects*</sub>

![StorageSaveTopics](https://user-images.githubusercontent.com/15065550/97779045-ce686a00-1b38-11eb-821f-1b468dcb996e.png)

<sub>***Figure 2.6.7** UML sequence diagram for storing topics*</sub>

As can be seen in figure , all the data is deleted from the disk before saving of data takes place. It is implemented this way
to make sure the deletion of a subject or topic by the user is also saved. Otherwise, the subject or topic will not be
deleted since the rest of the method calls only replace or create data.

> ✔️ **_TODO:_** As the amount of data grows larger, this saving algorithm can get very slow since it only saves the data
> when the application exits, not to mention the (most of the time) needless deletion of all data before each save. 
> To speed up the process, the implementation can be changed such that data is saved right after each user command that involves data manipulation.

##### Loading data

For the data stored in json format, they are loaded using the same [Gson](https://github.com/google/gson/blob/master/UserGuide.md)
library, whereas for the data `tasks.txt`, they are parsed line by line and converted into corresponding `Task` objects.
The loading of data follows a similar cascading manner as saving, as can be seen in the diagram below.

![StorageLoadSubjects](https://user-images.githubusercontent.com/15065550/97828501-1cd25180-1c7c-11eb-94e9-9c7731711e30.png)

<sub>***Figure 2.6.8** UML sequence diagram for loading subjects*</sub>

![StorageLoadTopics](https://user-images.githubusercontent.com/15065550/97779042-cc9ea680-1b38-11eb-9e06-f36274dd4286.png)

<sub>***Figure 2.6.9** UML sequence diagram for loading topics*</sub>

Note that only `Subject` and `Topic` data are sorted; `Result`, `Flashcard`, and `Task` data still follow the added 
order. This is because subjects and topics are stored as directories, and the order of the directories loaded is not 
guaranteed by the Java, so the sorting is just to fix the inconsistency of the order. The rest of the data, on the
other hand, is guarenteed to have the same order every time they are loaded, so they are not sorted. 

##### Exporting data

The user can export all data of the application to the pre-defined `export/data.json` in the same directory where 
the application runs. An example of the file content is shown below.

<pre>
[
  {                                                                  <em>--> Subject 1</em>
    "title": "Maths",                                                
    "topics": {
      "topics": [                                                    <em>--> Topics under Maths subject</em>
        {                                                            <em>--> Topic 1</em>
          "title": "Algebra",                                        
          "flashcards": <em>[same as the content of flashcards.json]</em>,    <em>--> Flashcards under Algebra topic</em>
          "results": {
            "resultList": <em>[same as the content of topicResults.json]</em> <em>--> Results under Algebra topic</em>
          }
        },
        ...
      ]
    },
    "tasks": {                                                       <em>--> Tasks under Maths subject</em>
      "taskList": [
        {                                                            <em>--> Task 1</em>
          "description": "someTodoTask",
          "isDone": false
        },
        {                                                            <em>--> Task 2</em>
          "dateTime": {
            "date": {
              "year": 2020,
              "month": 12,
              "day": 20
            },
            "time": {
              "hour": 23,
              "minute": 59,
              "second": 0,
              "nano": 0
            }
          },
          "description": "someDeadlineTask",
          "isDone": false
        },
        ...                                                       
      ]
    },                            
    "results": {
      "resultList": <em>[same as the content of subjectResults.json]</em>    <em>--> Results under Maths subject</em>
    }
  },
  ...                                                               <em>--> More subjects</em>
]
</pre>
<sup>***Figure 2.6.10** Sample data.json content*</sup>

This content format is a result of converting a list of `Subject` objects with populated data into json form.

> ✔️ **_TODO:_** Importing of the exported data is not yet supported, and it would make sense to implement it in the
> future versions. However, it may not be straightforward as type conversion is needed to convert the data into the
> right types 
> (refer to [Gson documentation](https://github.com/google/gson/blob/master/UserGuide.md#TOC-Collections-Limitations) for more details).

## 3. Product scope <a name = "scope"> </a>

This section describes the scope of the application. To be specific, the target audience and 
the uses and benefits of this application.

### Target user profile <a name = "Ptarget"> </a>

The targeted audience are university students , aged 18 - 25 who have access to a computer. The students must be familiar
with applications that run on the command-line interface. Currently, this application is created for students who study in 
Singaporean universities.

### Value proposition <a name = "Pvalue"> </a>


This application aims to help students to keep track of their revision. Students should be
able to revise their subjects effectively through this application by adding various questions
and answers in the form of flashcards. After that, students can refer to the flashcards to
revise the subjects. Furthermore, they can take quizzes and see if they grasp the topics or subjects well
enough to attend their school based exams.

Students who find it hard to keep track of their assignments would also find this application
useful since it lets the students add their tasks. The application then reminds the students
regarding the upcoming tasks, which would prompt the students to prepare them.

Students who find it difficult to organise would find this app useful since it
offers a one stop solution to manage both their revision and their upcoming tasks .
## 4. User Stories <a name = "user"> </a>

This section summarises various user stories of this application. These are summarised in the table below.

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


## 5. Non-Functional Requirements <a name = "nfr"> </a>

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

## 6. Glossary <a name = "glossary"> </a>

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

## 7. Testing <a name = "manual"> </a>

This section describes how to carry out testing of the application. It focuses on testing the jar file and the source code.

### Testing the Jar File <a name = "jartest"> </a>
1. Refer to the [UserGuide](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html) on how to download the application.
2. Open the application in command prompt.
3. Try out various commands in the userguide to check if it works.
4. Try out invalid commands and check if the application responds correspondingly.
5. Exit the application and check the data files to check if all the data has been saved.
6. Open the application again and check if the data has been loaded correctly. Use the `list` command for this step.

### Testing the source code <a name = "sourcetest"> </a>

This section describes how different parts of the source code can be tested, to make sure everything works properly.

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
                                    ___________
                                    |  __ |  _ \
 ____  ______      _____   ________ |  |__| | | |
|  __|/ __ \ \    / /| |  /  _____/ |   __| | | |
| |  |  __/ \ \__/ / | | /_____  /  |  |__| |_| |
| |   \___|  \____/  |_|/_______/   |_____|_____/

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


## 8. Development <a name="develop"></a>

### Logging <a name = "log"> </a>
Whenever you need to use logging in a class, add this line 

```java
private static final Logger logger = Logger.getLogger(CurrentClass.class.getName());
```

to the start of the class to get a logger instance,
where `CurrentClass` is replaced by the class name you are adding the logger to. To learn more about
how logging works, read the [official logging documentation](https://docs.oracle.com/javase/7/docs/technotes/guides/logging/overview.html).

The current configuration logs all the messages with log levels above `FINE` into a file (`revisED%u.log`) in the same
directory where the application is run. You can find more details about the logging configurations (and change them or add more)
in `src/main/resources/logging.properties`. 
For consistency, you should only change the behavior of logging via this file.
