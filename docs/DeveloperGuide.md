# Developer Guide

## Table of contents
* <a href="#introduction">1. Introduction</a> 
* <a href="#setting-up">2. Setting up the Project</a>
* <a href="#design">3. Design</a> 
    * <a href="#card">3.1 Card Package</a> 
    * <a href="#command">3.2 Command Package</a> 
    * <a href="#list">3.3 List Package</a> 
    * <a href="#parser">3.4 Parser Package</a> 
    * <a href="#ui">3.5 Ui Package</a> 
    * <a href="#storage">3.6 Storage Package</a> 
* <a href="#implementation">4. Implementation</a>
    * <a href="#store-imp">4.1 Storing data implementation</a> 
    * <a href="#load-imp">4.2 Loading data implementation</a> 
    * <a href="#export-imp">4.3 Exporting data implementation</a>
    * <a href="#quiz-imp">4.4 Quiz implementation</a>  
    * <a href="#sort-imp">4.5 Sorting tasks implementation</a>
    * <a href="#results-imp">4.6 Results implementation</a> 
    * <a href="#adding-imp">4.7 Adding objects implementation</a> 
    * <a href="#accessing-imp">4.8 Accessing subjects/topics implementation</a> 
* <a href="#logging">5. Logging</a>
* <a href="#documentation">6. Documentation</a>
* <a href="#testing">7. Testing</a>
* <a href ="#product-scope">Appendix A: Product Scope</a> 
* <a href ="#user-story">Appendix B: User Stories</a> 
* <a href ="#nfr">Appendix C: Non functional Requirements</a> 
* <a href ="#glossary">Appendix D: Glossary</a> 
* <a href ="#manual-testing">Appendix E: Instructions for Manual Testing</a> 

## 1. Introduction <a name="introduction"></a>

**revisED** is a CLI application that helps students to revise by allowing them to create flashcards, take quizzes, 
and keep track of their deadlines, tasks and any other important dates. The users can create different subjects, 
add different topics under a subject, and create different flashcards in the form of questions and answers under a topic. They can also 
take quizzes for the flashcards they have added and view the results. 

This developer guide is for developers who wish to understand and/or develop **reviseED** further. 
This guide includes design, implementation, logging, testing, product scope, and other
sections to help developers better understand the application.

Note the following symbols and formatting used in this document:

Symbols / Formatting|Meaning
------|------
📝️️ **_NOTE:_** | Important or additional information
⚠️ **_WARNING:_** | Things to avoid or pay extra attention to
✔️ **_TODO:_** | Things that should be but have not been done
`Grey highlight` | Code or terms related to the code/application

## 2. Setting up the Project <a name="setting-up"></a>

This section guides you through the steps to setup the project on your computer.

> 📝️️ **_NOTE:_** This guide assumes you already have [Java 11](https://openjdk.java.net/projects/jdk/11/) or above
> installed on your computer. If you do not have it yet, follow the link
> to download and install it. 

1. Fork [this repo](https://github.com/AY2021S1-CS2113T-W13-1/tp) and clone the fork to your computer. Alternatively,
download the source code from [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).
1. Open a console in the folder where `build.gradle` resides, and run the command `gradlew.bat run` if you are using Windows 
or `./gradlew run` if you are using Mac/Linux.
1. If the setup is successful, you should see the greeting screen:
```
> Task :run
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
____________________________________________________________
Alright, What can I do for you?
____________________________________________________________
____________________________________________________________
Type help for all available commands
____________________________________________________________
```

Alternatively, if you are using [IntelliJ](https://www.jetbrains.com/idea/), follow the first step above, then:

1. Configure the Intellij for JDK 11, as described [here](https://se-education.org/guides/tutorials/intellijJdk.html).
1. Import the project as a Gradle project, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. Locate and click the `run` task under `Tasks > application > run` in the [Gradle tool window](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html).


## 3. Design <a name="design"></a>

This section describes how **revisED** has been designed so that developers can better understand the 
designing of the code, with the help of visual aids.

The application has been broken down into different levels so that users would find it easy to navigate from one level 
to the next one. There are three levels within the application, and their hierarchy is ranked as shown below:
* **Main:** This level edits the list of all the subjects present within the application and takes care of relevant commands 
such as add subject, delete subject or find subject.
* **Subject:** This level is accessed through the `subject [NAMEOFSUBJECT]` command. The user would then look at the individual 
subjects and then would be able to add, delete, list or find either tasks or topics.
* **Topic:** This level is accessed through the `topic [NAMEOFTOPIC]` command. The user can add, delete or list flashcards
in this level.

Exiting from the Topic level will result in the program running at the Subject level, while exiting from the Subject level 
allows the program to run at the Main level. The program can also access the Subject level from the main level, or the Topic level
from the Subject level via user input.

The code contains the main class Revised, as well as different packages, which combine classes that perform a similar 
functionality. Here are the list of the main packages:

* <a href="#card">card</a>
* <a href="#command">command</a> 
* <a href="#list">list</a>
* <a href="#parser">parser</a>
* <a href="#ui">ui</a>
* <a href="#storage">storage</a>

### 3.1 Card Package <a name="card"></a>

![cardUML](https://user-images.githubusercontent.com/50734854/98472577-f2aa0200-222e-11eb-9b00-c23280986366.png)

<sub>***Figure 3.1** UML class diagram for card package. quizcard package is omitted.*</sub>

The Card package consists of different classes that holds information on the main functionalities of the application,
and is split into 2 packages, quiz and task, as well as Subject, Topic and Flashcard classes.
- quizcard package
    * Quiz class
    * Result class
    * TopicQuiz class
    * SubjectQuiz class
- taskcard package
    * Deadline class
    * Event class
    * Task class
    * Todo class
- Flashcard class
- Subject class
- Topic class

Each of the classes in the card package contains:
* Getters and Setters of its own attributes
* Methods that alter an instance of its own class

#### 3.1.1 quizcard Package <a name="quiz"></a>

![QuizUML](https://user-images.githubusercontent.com/50734854/98472580-f76eb600-222e-11eb-92c4-d721895cfd41.png)

<sub>***Figure 3.1.1** UML class diagram for list package*</sub>

The `quizcard` package holds the necessary classes for the quiz functionality of this application. The following are
the classes in the package. An abstract`Quiz` class, a `Result` class, a `SubjectQuiz` class and a `TopicQuiz` class.

#### 3.1.1.1 Quiz <a name="Qclass"></a>
`Quiz` class is an abstract class in the `quizcard` package. It holds the `result` variable to track the result of the quiz,
the  `flashcards` list to store the flashcards for which the quiz is initiated for and the`incorrectAnswers ` list. 
The list stores the questions which the user did not answer correctly, along with the correct answer for the question and the answer
provided by the user. The Quiz class also contains a `startQuiz` method and a `checkAnswer` method.

#### 3.1.1.2 SubjectQuiz <a name="Qsubject"></a>
`SubjectQuiz` class inherits from the `Quiz` class and initiates the quiz for a subject.It also contains a `setupQuiz`method.

#### 3.1.1.3 TopicQuiz <a name="Qtopic"></a>
`TopicQuiz` class inherits from the `Quiz` class and initiates the quiz for a topic.It also contains a `setupQuiz`method.

##### 3.1.1.4 Result <a name="Qresult"></a>
`Result` class  stores the result of a quiz . It has three instance variables, namely the `score` variable
which tracks the score during the quiz, the  `maxScore` variable which is the maximum score that you can get from doing the 
quiz, while the  `description` variable will be a grade given to you depending on your performance.
The  `UpdateResult` method  updates the score of the quiz during the quiz and changes the grade of the quiz. 

#### 3.1.2 taskcard Package <a name="task"></a>

![TaskUML](https://user-images.githubusercontent.com/50734854/98472584-fb023d00-222e-11eb-8513-eb9493cae215.png)

<sub>***Figure 3.1.2** UML class diagram for taskcard package*</sub>

The `taskcard` package holds the necessary classes for the Task functionality of this application. The following are
the classes in the package. A `Task` class, a `Todo` class, a `Deadline` class and an `Event` class.

##### 3.1.2.1 Task <a name="Ttask"></a>
The `Task` class is the superclass of `Todo`, `Deadline` and `Event`. It has attributes such as the description of the task-`String`
and an attribute to check if the task is completed-`Boolean`. It holds basic getters for its attributes, getters such as getting the 
status icon to check if a Task is done-`String`, and getters for the DateTime features of the Deadline and Event classes. It also has
 a toString methods to print the Task.
 
##### 3.1.2.2 Todo <a name="Ttodo"></a>
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

##### 3.1.2.3 Deadline <a name="Tdeadline"></a>
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

##### 3.1.2.4 Event <a name="Tevent"></a>
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

#### 3.1.3 Flashcard  <a name="flashcard"></a>
The `Flashcard` class is a class to store information of flashcards. It has attributes such as the question
and answer of each flashcard, a constructor, as well as getters for both question and answer of the flashcards.


#### 3.1.4 Subject <a name="subject"></a>
The `Subject` class is a class to store information of subjects. It has various attributes such as the title of the 
subject-`String`, a list of various topics in a subject-`TopicList`, a list of various tasks present in a subject-`TaskList`
and a list of various results of the quiz of a subject-`ResultList`. It also consists of a constructor, getters to all of the
attributes in the `Subject` class and a toString method to return the title of the subject.

#### 3.1.5 Topic <a name="topic"></a>
The `Topic` class is a class to store information of topics. It has various attributes such as the title of the subject-`String`,
a list of various flashcards in a topic-`List<Flashcard>`, and a list of the results for all topic quizzes-`ResultList`. It also 
contains various constructors, getters for all its attributes, and a toString method that returns the title of the topic.

An example of how classes in the card package interact with each other is shown in the figure below. ADD CARD DIAGRAM
In this example, a student has 2 main subjects, mainly Math and Science. Math has topics algebra and calculus, while science has topics speed and light. He also made flashcards the various topics.

### 3.2 command Package <a name="command"></a>
The Command package consists of different classes that executes the various commands of the application due to user input,
and is split into 4 packages, flashcard, subject, task and topic, as well as an abstract command class, that is a superclass of the various classes in the different packages.
As mentioned in the introduction of the design segment of the developer guide, the program is split into 3 levels, namely the main level, the subject level, as well as the topic level.
The `subjectcommand` package holds the information to allow the program to execute a user command at the main level, the `topiccommand` package holds the information
to allow the program to execute a user command at the subject level, while the `taskcommand` and `flashcardcommand` packages holds the information to allow the program to execute a user command

* Command: In charge of reading the command and calling the relevant methods. The package itself holds
the following packages. The commands also have a method isExit to exit the current level the program is at. 
Exiting from a level of the program will either up the program to run at a level of a higher level or end the program.
The levels in the program are mentioned in the introduction of the design segment of the developer guide.
    - flashcardcommand
    - subjectcommand
    - topiccommand
    - taskcommand

Each of the sub-packages hold multiple classes that are a subclass of the Command class, and contains:
* An execute command to execute the respective commands in their respective levels of the program
* An isExit method that checks if the command exits the level the program is currently running in
AccessSubjectCommand and AccessTopicCommand both have an extra method goToSubject and goToTopic methods that 
allows the program to enter Subject level from the Main level, and to the Topic level from the Subject level respectively.


#### subjectcommand package <a name="subjectcommand"></a>
The `subjectcommand` package holds all the necessary classes for executing methods at the main level. The Class that will be created and executed depends 
on user input. A Class diagram of how the classes interact with each other in the subjectcommand package is shown below.

![SubjectCommandClassDiagram](https://user-images.githubusercontent.com/47527482/98504305-9c21df80-2291-11eb-988a-916430c7270d.jpg)

<sub>***Figure 3.2.1** UML class diagram for subjectcommand package*</sub>

The `subjectcommand` package holds the following classes.
- AccessSubjectCommand
- AddSubjectCommand
- DeleteSubjectCommand
- ExitSubjectCommand
- ExportSubjectCommand
- FindSubjectCommand
- HelpSubjectCommand
- ListAllSubjectCommand
- ListSubjectCommand
- QuizSubjectCommand
- ResultSubjectCommand
- SorrySubjectCommand
- SubjectCommand

##### AccessSubjectCommand
The `AccessSubjectCommand` class has 3 methods:
- execute()
- goToSubject()
- isExit()

and is mainly used for entering the Subject level from the Main Level. This occurs when 
the execute() method of the `AccessSubjectCommand` is first run. It first decodes the full command of the user input, then 
accesses the subject level of the program. Different Subject class instances have different information contained in them, thus 
there exists multiple subject levels of the program, each subject class having their own subject level in the program. 
For example, if there are 2 subjects, like Maths and Science, then accessing the Subject level of the Maths instance is different from 
accessing the Subject level of the Science Subject, since both subjects contain different information. Accessing the different subjects depend on user input, which 
will be decoded in the execute() method in `AccessSubjectCommand`. The execute() method will then run the goToSubject() command, that will access the information 
available in the subject that is selected via user input.

The isExit() method determines whether the program exits. The `AccessSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

For more information of the implementation of the AccessCommand Class, refer to <a href="#accessing-imp">4.8 Accessing subjects/topics implementation</a>. 

##### AddSubjectCommand
The execute() method of the `AddSubjectCommand` decodes the user input, then adds a subject into the SubjectList, which title depends on the user input. For example, if the 
user input is `add Maths`, then the title of the Subject created in the SubjectList will be Maths. It also prints the title of the subject that 
is added to the SubjectList.

The isExit() method determines whether the program exits. The `AddSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

For more information of the implementation of the AddCommand Class, refer to <a href="#adding-imp">4.7 Adding objects implementation</a> 
. 

##### DeleteSubjectCommand
The execute() method of the `DeleteSubjectCommand` decodes the user input, then deletes a subject based on the index the Subject currently in the SubjectList. 
For example, when the user input is `delete 1`, the program decodes the input, and deletes the first Subject in the SubjectList, which is at index 0 of the SubjectList.

The isExit() method determines whether the program exits. The `DeleteSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### ExitSubjectCommand
The execute() method of the `ExitSubjectCommand` does nothing, but needed since this method was implemented from an abstract class.

The isExit() method determines whether the program exits. The `ExitSubjectCommand` isExit() method is hard coded to return true, so that when the isExit() command is run, the program 
will exit.

##### ExportSubjectCommand
The execute() method of the `ExportSubjectCommand` 
TODO: Add execute method documentation

The isExit() method determines whether the program exits. The `ExportSubjectCommand` isExit() method is hard coded to return true, so that when the isExit() command is run, the program 


##### FindSubjectCommand
The execute() method of the `FindSubjectCommand` decodes the user input, then find subjects whose title contains the keywords the user input into the program. 
It then prints the title of the Subjects in the SubjectList whose title contains the keyword.

The isExit() method determines whether the program exits. The `FindSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### HelpSubjectCommand
The execute() method of the `HelpSubjectCommand` prints a list of available commands at the main level of the program, 
for the user to understand the format of inputs, as well as types of input needed for execution.

The isExit() method determines whether the program exits. The `HelpSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### ListAllSubjectCommand
The execute() method of the `ListAllSubjectCommand` groups different tasks and flashcards under each topic, and different topics under each subject, then prints an organized list 
of all the subjects, topics, tasks and flashcards.

The isExit() method determines whether the program exits. The `ListAllSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### ListSubjectCommand
The execute() method of the `ListAllSubjectCommand` prints a list of all subjects in the SubjectList instance.

The isExit() method determines whether the program exits. The `ListSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### QuizSubjectCommand
The execute() method of the `QuizSubjectCommand`
TODO: Add execute method documentation

The isExit() method determines whether the program exits. The `QuizSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### ResultSubjectCommand
TODO: Add execute method documentation

The isExit() method determines whether the program exits. The `ResultSubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### SorrySubjectCommand
The execute() method of the `SorrySubjectCommand` throws a `FailedParseException`, meaning the parser has failed to decode the user input. Then, the exception will result in the Ui printing the error back 
to the user.

The isExit() method determines whether the program exits. The `SorrySubjectCommand` isExit() method is hard coded to return false since the command does not exit the code.

##### SubjectCommand
The `SubjectCommand` class is an abstract class that contains a abstract execute() method as well as a isExit() method, and is a super class for all the commands in the subjectcommand 
package. This is to ensure that all other commands in the subjectcommand package can have the same method calls, and that execute() can be called on a SubjectCommand class, 
even if they are a subclass of the SubjectCommand class.

#### topiccommand package
The `topiccommand` package holds all the necessary classes for executing methods at the subject level. The Class that will be created and executed depends 
on user input. A Class diagram of how the classes interact with each other in the topiccommand package is shown below.

![TopicCommandClassDiagram](https://user-images.githubusercontent.com/47527482/98504309-9fb56680-2291-11eb-932d-2ffac059b101.jpg)

<sub>***Figure 3.2.2** UML class diagram for topiccommand package*</sub>

The `topiccommand` package holds the following classes.
- AccessTopicCommand
- AddTopicCommand
- DeleteTopicCommand
- ExitTopicCommand
- FindTopicCommand
- HelpTopicCommand
- ListAllTopicCommand
- ListTopicCommand
- QuizTopicCommand
- ResultTopicCommand
- SorryTopicCommand
- TopicCommand

All classes in the `topiccommand` package works the same way as the ones found in the `subjectcommand` package, but it deals with the `Topic` class instead of the `Subject` class. Notably, 
there is a change in parameters, as the `TopicCommand` classes is missing the Storage parameter. This is due to the absence of `ExportCommand` in the TopicCommands, since we did not implement 
the export command for the subject level. The rest of the TopicCommands do not need the Storage parameter since none of the execute() methods of the Commands require it. 
For more information of the `subjectcommand` package, please refer to <a href="#subjectcommand">SubjectCommand</a> .

#### taskcommand package
The `taskcommand` package holds all the necessary classes for executing methods at the subject level. The Class that will be created and executed depends 
on user input. A Class diagram of how the classes interact with each other in the taskcommand package is shown below.

![TaskCommandClassDiagram](https://user-images.githubusercontent.com/47527482/98503905-9e376e80-2290-11eb-90b8-274623ec400b.png)
 
<sub>***Figure 3.2.3** UML class diagram for taskcommand package*</sub>
 
The `taskcommand` package holds the following classes.
- AddDeadlineCommand
- AddEventCommand
- AddTodoCommand
- DeleteTaskCommand
- DoneTaskCommand
- FindTaskCommand
- TaskCommand

The Classes in the `taskcommand` package is different from the ones found in the other command packages. This is because all the `taskcommand` Classes are parsed by the `TopicParser`, since they work at the 
Subject level, similar to the Topic Commands. Thus, they do not have their own Exit Commands, List Commands and Help Commands, since all those are handled by the Topic Commands.

There are 3 different Add Task Commands, due to the 3 different formats of the Tasks that the user can save, namely `Event`, `Deadline` and `Todo`. The rest of the Task Commands have similar functionality to 
the ones found in the `subjectcommand` package. For more information of the `subjectcommand` package, please refer to <a href="#subjectcommand">SubjectCommand</a> .

#### flashcardcommand package

The `flashcardcommand` package holds all the necessary classes for executing methods at the topic level. The Class that will be created and executed depends 
on user input. A Class diagram of how the classes interact with each other in the flashcardcommand package is shown below.

![FlashcardCommandClassDiagram](https://user-images.githubusercontent.com/47527482/98503810-6cbea300-2290-11eb-90c3-e0525258077b.png)

<sub>***Figure 3.2.4** UML class diagram for flashcardcommand package*</sub>

The `flashcardcommand` package holds the following classes.
- AddFlashcardCommand
- DeleteFlashcardCommand
- ExitFlashcardCommand
- HelpFlashcardCommand
- ListAllFlashcardCommand
- ListFlashcardCommand
- SorryFlashcardCommand
- FlashcardCommand

All classes in the `Flashcardcommand` package works the same way as the ones found in the `subjectcommand` package, but it deals with the `Flashcard` class instead of the `Subject` class. Notably, 
there is a change in parameters, as the `FlashcardCommand` classes is missing the Storage parameter. This is due to the absence of `ExportCommand` in the FlashcardCommands, since we did not implement 
the export command for the subject level. The rest of the FlashcardCommands do not need the Storage parameter since none of the execute() methods of the Commands require it. Additionally, the flashcardcommand 
package is also missing a few classes such as the `QuizCommand`, `ResultCommand` and `FindCommand` classes. This is due to the fact that at the topic level, there are only flashcards in each topic, and it would not 
make sense to quiz one flashcard at a time, thus the absence of the implementation. For more information of the `subjectcommand` package, please refer to <a href="#subjectcommand">SubjectCommand</a> .

### 3.3 List Package <a name="list"></a>

This package contains different list classes used for managing a list of different card package objects. 
They are used to abstract the extra processing needed (if any) before or after interacting with the underlying list.
The relationship between the classes is shown below.

![ListClass](https://user-images.githubusercontent.com/15065550/98441484-79d26b00-20b3-11eb-9899-596db27b8570.png)

<sub>***Figure 3.3.1** UML class diagram for list package*</sub>

> ✔️ **_TODO:_** Delete method should also be implemented here (just like the add method) and be called to do the delete operations 
> instead of doing it directly through the underlying list for better cohesion.

> ✔️ **_TODO:_** FlashcardList should also be created to manage the list of flashcards instead of directly operate on the
> ArrayList for better cohesion.

### 3.4 Parser Package <a name="parser"></a>

### 3.5 Ui Package <a name="ui"></a>

### 3.6 Storage Package <a name="storage"></a>
There are two classes inside this package, both of which are inside `Storage.java`. The following diagram shows the
relationship between the two classes.

![StorageClass](https://user-images.githubusercontent.com/15065550/97547893-9cfe6b80-198b-11eb-8475-0992c8274ee3.png)

<sub>***Figure 3.6.1** UML class diagram for storage package*</sub>

#### StorageBuilder <a name="Sbuilder"></a>

`StorageBuilder` class is a static inner class of the `Storage` class. It follows the builder pattern and its sole purpose
is to create `Storage` instances. The [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) is used to deal 
with the decrease in clarity due to the increase of the number parameters. Every instance of the `Storage` class should 
be created through `StorageBuilder`, and this is enforced by disabling the public constructor of the `Storage` class.

#### Storage <a name="Sstorage"></a>

`Storage` class handles every operation that involves data storage, including storing, loading, and exporting of 
application data. Storing of data only happens when the application exits while loading of data only happens
when the application launches. On the other hand, exporting of data happens when the user runs `export` command.


## 4. Implementation <a name="implementation"></a>
This section describes some noteworthy details on how certain features are implemented.

### 4.1 Storing data <a name="store-imp"></a>

`Storage` class stores the data following the same logical structure (subject -> topic) of the application to 
make the stored data more presentable to the user. `Subject` and `Topic` data are stored as nested directories, while 
`Flashcard` and `Result` data are stored in [JSON](https://en.wikipedia.org/wiki/JSON) format so that they are readable 
to the users and allows them to change the saved data manually. 
[Gson](https://github.com/google/gson/blob/master/UserGuide.md) library is used to create the json files.
`Task` data are stored differently as text files due to 
legacy reason, but they are also readable and can be changed manually as well. 

> 📝️️ **_NOTE:_** If the users modify the file contents with wrong syntax, the data in the affected files will
> not be loaded and empty data will be returned instead so that the application can still run.

An example of the structure mentioned is shown in the figure below.

<pre>
data                            <em>--> Data main folder</em>
└── CS2113T                     <em>--> CS2113T subject folder</em>
    ├── Java                    <em>--> Java topic folder under CS2113T subject</em>
    │   ├── topicResults.json
    │   └── flashcards.json
    ├── subjectResults.json
    └── tasks.txt
</pre>
<sub>***Figure 4.1.1** Sample directory structure*</sub>

##### File formats

One `tasks.txt` file is created under each subject folder, storing the tasks (Todo, Event, Deadline) data under 
that subject. An example of the file content is shown below.

<pre>
T | 1 | someTodoTask                                    <em>--> Todo task</em>
D | 0 | someDeadlineTask | 11:59 PM 20 Dec 2020         <em>--> Deadline task</em>
E | 0 | someEventTask | 1:00 PM 10 Nov 2020             <em>--> Event task</em>
</pre>
<sup>***Figure 4.1.2** Sample tasks.txt content*</sup>

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
<sup>***Figure 4.1.3** Sample subjectResults.json content*</sup>

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
<sup>***Figure 4.1.4** Sample flashcard.json content*</sup>

Similarly, this content format is a result of converting a list of `Flashcard` data into json form.

##### Behavior

The implementation stores the data in a cascading manner, meaning that when a call to save topics is made, all the data 
under the topics will be saved. Similarly, when a call to save subjects is made, all the data under the subjects
including topics will be saved. The behavior mentioned is shown in the diagram below.

![StorageSaveSubjects](https://user-images.githubusercontent.com/15065550/97779044-cdcfd380-1b38-11eb-8e5e-a4f258a726e5.png)

<sub>***Figure 4.1.5** UML sequence diagram for storing subjects*</sub>

![StorageSaveTopics](https://user-images.githubusercontent.com/15065550/97779045-ce686a00-1b38-11eb-821f-1b468dcb996e.png)

<sub>***Figure 4.1.6** UML sequence diagram for storing topics*</sub>

As can be seen in _figure 4.1.5_, all the data is deleted from the disk before saving of data takes place. It is implemented this way
to make sure the deletion of a subject or topic by the user is also saved. Otherwise, the subject or topic will not be
deleted since the rest of the method calls only replace or create data.

> ✔️ **_TODO:_** As the amount of data grows larger, this saving algorithm can get very slow since it only saves the data
> when the application exits, not to mention the (most of the time) needless deletion of all data before each save. 
> To speed up the process, the implementation can be changed such that data is saved right after each user command that involves data manipulation.

### 4.2 Loading data <a name="load-imp"></a>

For the data stored in json format, they are loaded using the same [Gson](https://github.com/google/gson/blob/master/UserGuide.md)
library, whereas for the data `tasks.txt`, they are parsed line by line and converted into corresponding `Task` objects.
The loading of data follows a similar cascading manner as saving, as can be seen in the diagram below.

![StorageLoadSubjects](https://user-images.githubusercontent.com/15065550/97828501-1cd25180-1c7c-11eb-94e9-9c7731711e30.png)

<sub>***Figure 4.2.1** UML sequence diagram for loading subjects*</sub>

![StorageLoadTopics](https://user-images.githubusercontent.com/15065550/97779042-cc9ea680-1b38-11eb-9e06-f36274dd4286.png)

<sub>***Figure 4.2.2** UML sequence diagram for loading topics*</sub>

Note that only `Subject` and `Topic` data are sorted; `Result`, `Flashcard`, and `Task` data still follow the added 
order. This is because subjects and topics are stored as directories, and the order of the directories loaded is not 
guaranteed by the Java, so the sorting is just to fix the inconsistency of the order. The rest of the data, on the
other hand, is guarenteed to have the same order every time they are loaded, so they are not sorted. 

### 4.3 Exporting data <a name="export-imp"></a>

The user can export all data of the application to the pre-defined `export/data.json` in the same directory where 
the application runs. An example of the file content is shown below.

<pre>
[
  {                                                                  <em>--> Subject 1</em>
    "title": "CS2113T",                                                
    "topics": {
      "topics": [                                                    <em>--> Topics under CS2113T subject</em>
        {                                                            <em>--> Topic 1</em>
          "title": "Java",                                        
          "flashcards": <em>[same as the content of flashcards.json]</em>,    <em>--> Flashcards under Java topic</em>
          "results": {
            "resultList": <em>[same as the content of topicResults.json]</em> <em>--> Results under Java topic</em>
          }
        },
        ...
      ]
    },
    "tasks": {                                                       <em>--> Tasks under CS2113T subject</em>
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
      "resultList": <em>[same as the content of subjectResults.json]</em>    <em>--> Results under CS2113T subject</em>
    }
  },
  ...                                                               <em>--> More subjects</em>
]
</pre>
<sup>***Figure 4.3.1** Sample data.json content*</sup>

This content format is a result of converting a list of `Subject` objects with populated data into json form.

> ✔️ **_TODO:_** Importing of the exported data is not yet supported, and it would make sense to implement it in the
> future versions. However, it may not be straightforward as type conversion is needed to convert the data into the
> right types 
> (refer to [Gson documentation](https://github.com/google/gson/blob/master/UserGuide.md#TOC-Collections-Limitations) for more details).
### 4.4 Quiz <a name="quiz-imp"></a>
The abstract quiz class  contains a checkAnswer() method that checks the answer that the user had given with the correct answer of the quiz. 
If the user enters the correct answer, the existing score is incremented by one. Else, the contents of the flashcards and the incorrect answer provided by
the user are transferred to the  `incorrectAnswers` list. Once the user finished the quiz, the application would print the 
questions that the users did not answer correctly, along the the answer that was provided by the user.

#### Initiating a subject quiz
You can start a subject quiz by entering `quiz NAMEOFSUBJECT`. Subsequently, the application retrieves the QuizSubjectCommand
after parsing the command and calls for the `startQuiz` method.
The `startQuiz` method calls for the  `setupQuiz` method checks for the presence of topics or flashcards. Else, the application throws the `NoTopicException` for the former, and the 
`NoFlashcardException` for the latter. If the topics have flashcards, then these are transferred to the 
`SubjectQuiz` class, while the maximum score of the quiz is set to be the total number of flashcards in the list of flashcards
present in the `SubjectQuiz` class . 

The application now returns to the `startQuiz` method and the current score of the quiz is set to zero. Subsequently, the
application begins printing the questions from the flashcards and checks the answer that you provide. If you want to stop the 
quiz, use the `stop` command. The application will then print out the score that you obtain. If you complete the quiz
then the application not only prints the score, but it also prints the incorrect answers from the quiz.


The following diagram shows how you can initiate the quiz for a subject.

![first](https://user-images.githubusercontent.com/46095141/98369799-7ec3fa00-2074-11eb-9f01-e656fcebc227.png)

 <sub>***Figure 4.4.1** UML sequence diagram for subject quiz*</sub>

#### Initiating a topic quiz
Provided that you have used the `subject NAMEOFSUBJECT` command to access a subject,you can start a subject quiz by entering `quiz NAMEOFTOPIC`. Subsequently, the application retrieves the QuizTopicCommand
after parsing the command and calls for the `startQuiz` method. This method class for the `setupQuiz`method, which  throws the `NoFlashcardException` for when the topic does not 
have any flashcards. The application then returns back to the `startQuiz` method. The implementation of the `startQuiz` method is similar
to that of the SubjectQuiz class.

The following diagram shows how you can initiate the quiz for a topic.

![topic](https://user-images.githubusercontent.com/46095141/98371459-2b06e000-2077-11eb-85dd-4850dbe7bba8.png)

 <sub>***Figure 4.4.2** UML sequence diagram for topic quiz*</sub>

> 📝️ **_NOTE:_** For both the subject quiz and the topic quiz, the application only prints out the incorrectAnswer
>if the user has completed the quiz. If the user stops the quiz without completing it, then the application will only
>show the score obtained by the user.

### 4.5 Sorting tasks <a name="sort-imp"></a>
The application sorts the tasks according to their dates and times. Tasks which are due soon are placed at the front
while tasks which are due later are placed at the end of the task list. `Todo` tasks are placed at the end of the tasklist
by assigning the `LocalDateTime` variable to be `LocalDateTimeMax`.

### 4.6 Results <a name="results-imp"></a>
The `updateResult` method in the `Result`class updates the result for a given quiz by setting the score and the description.
There are three categories of descriptions: `Fail` for getting a score which is lesser than half of the maximum score, `Pass`
for obtaining a score above half of the maximum score and `Excellent` for getting the maximum score in a quiz.

### 4.7 Adding objects <a name="adding-imp"></a>
Each of the add commands have an execute() method. The execute() method for AddSubjectCommand add subjects into the subject list, the execute() method for AddTopicCommand 
add topics in a subject, while the execute() method for AddTodoCommand, AddDeadlineCommand and AddEventCommand adds tasks in a subject.
subject.

#### Adding a subject
You can add a subject by entering `add [SUBJECT_NAME]`. The subject will be added to a `SubjectList` created when the program initialises. 
After the program initialises, the program will ask for user input. The `Ui` will read the user input using the readCommand() method, then the 
the `SubjectParser` will parse the command. Since the user input is `add [TOPIC_NAME]`, the program will register it as a command to add a `Subject`, 
and will create a new `AddSubjectCommand`. This `AddSubjectCommand` is then passed back to the main function, where its execute() method will run. 
The execute method decodes the user input. If the `[SUBJECT_NAME]` is not a title of another `Subject` already on the list, it will create a new `Subject`, 
and add that new `Subject` created into the `SubjectList` that was created during initialisation of the program. The `Ui` will then print the adding of the 
`Subject` into the list. The program then returns to the main function, where it waits for next user input.

A Sequence diagram of adding a `Subject` Maths is shown below.
![AddSubjectSequenceDiagram](https://user-images.githubusercontent.com/47527482/98213500-1c89cd00-1f80-11eb-9b0c-9da37446b530.png)

<sub>***Figure 4.7.1** UML sequence diagram for adding a subject*</sub>

#### Adding a topic/task
you can add a `Topic` by entering `add [TOPIC_NAME]`. Additionally, you can add a `Todo` task by entering `todo [DESCRIPTION]`, add a `Deadline` 
task by entering `deadline [DESCRIPTION] /by [TIME]`, and add an `Event` task by entering `event [DESCRIPTION] /at [TIME]`. This command can only 
be entered in the subject level of the program, which can be accessed using the command `subject [SUBJECT_NAME]`. The documentation of the 
implementation and how to access a subject can be found in <a href="#accessing-imp">4.8 Accessing subjects/topics implementation</a>.

Otherwise, adding topics and tasks follows the same implementation as adding a subject.

### 4.8 Accessing levels <a name="accessing-imp"></a>
Each of the access commands have an execute() method and a goToSubject()/goToTopic() method. The AccessSubjectCommand class allows users to 
access the subject level of a subject specified in the user input from the main level, while the AccessTopicCommand class allows users to access the topic level 
of a topic specified in the user input from a subject level.

#### Accessing a subject
You can access a subject by entering `subject [SUBJECT_NAME]`. The subject needs to be a subject that already exists in the list, and you can 
add the `Subject` into the `SubjectList` by using the `add [SUBJECT_NAME]` feature, elaborated further in <a href="#adding-imp">4.7 Adding objects implementation</a>.
For example, after adding a `Subject` CS2101, the CS2101 `Subject` can be accessed by entering `access CS2101`. The application first reads the command using 
the Ui class, then parses the user input using the `SubjectParser` class, similar to adding a subject. The program will register the user input as a command to 
access a subject. The SubjectParser will create a new `AccessSubjectCommand` class, and this class will be returned to the main function. 
The main program will run the execute() method of `AccessSubjectCommand`. The execute() method checks for subjects in the `SubjectList` whose title 
matches the `[SUBJECT_NAME]`, and if a subject matches, the subject will be passed as a parameter to the goToSubject() method. The goToSubject() method will then 
have a loop that reads user inputs and parse these inputs to create new `TopicCommand` or `TaskCommand` classes to execute.

The sequence diagram of accessing a `Subject` Maths is shown below.
![AccessSubjectSequenceDiagram](https://user-images.githubusercontent.com/47527482/98199431-171f8900-1f66-11eb-9dfe-fc263ccfe15f.png)

<sub>***Figure 4.8.1** UML sequence diagram for accessing a subject*</sub>

#### Accessing a Topic
You can access a topic by entering `topic [TOPIC_NAME]`. The topic needs to be a topic that already exists in the list, and you can 
add the `Topic` into the `TopicList` of a `Subject` by using the adding a topic feature, elaborated further in <a href="#adding-imp">4.7 Adding objects implementation</a>.

### 4.9 Implementation of `list all` command
While the syntax of the `list all` command is the same on the main, subject and topic levels,
there are 3 separate ListAll___Commands for each level. Although all 3 commands classes call `Ui.printAll(subjects,activeSubject,activeTopic)`,
the 3 classes are kept separate so that `printAll()` can recognise what object the user is accessing.

The following sequence diagram shows how you can proccess the `list all` command from the subject level.

![ListAllTopicSequenceDiagram](https://user-images.githubusercontent.com/50734854/98481821-11f95d00-2238-11eb-90c6-9bb081956da5.png)
<sub>***Figure 4.9.1** UML sequence diagram for list all at topic level*</sub>

If the `list all` command is given at the topic level, `printTreeTopic()` will check if each topic is the active topic each loop,
instead of `Ui.printAll` checking each subject.

> 📝️ **_NOTE:_** This command uses unicode characters such as "├" and "└". You may consider changing the characters to ASCII
> characters only for the sake of compatibility.

## 5. Logging <a name = "logging"> </a>
We use [java.util.logging](https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/java/util/logging/package-summary.html) 
package for logging. Whenever you need to use logging in a class, add this line 

```
java
private static final Logger logger = Logger.getLogger(CurrentClass.class.getName());
```

to the start of the class to get a logger instance,
where `CurrentClass` is replaced by the class name you are adding the logger to. 
Subsequently, you can use the logger instance created to start logging. To learn more about
how logging works, read the [official logging documentation](https://docs.oracle.com/javase/7/docs/technotes/guides/logging/overview.html).

The current configuration logs all the messages with log levels above `FINE` into a file (`revisED.log`) in the same
directory where the application is run. You can find more details about the logging configurations (and change them or add more)
in `src/main/resources/logging.properties`. 
For consistency, you should only change the behavior of logging via this file.


## 6. Documentation <a name="documentation"></a>

All project documentations are put under the `/docs` folder. The following tools are used to write the documentations:
 
* [Jekyll](https://jekyllrb.com/) to manage documentation
* [Markdown](https://guides.github.com/features/mastering-markdown/) syntax for formatting
* [PlantUML](https://plantuml.com/) for drawing diagrams

To learn how set the documentation website up and maintain it, follow the guide [Using Jekyll for project documentation](https://se-education.org/guides/tutorials/jekyll.html).

To convert the documents to PDF files for distribution, follow the guide [Saving web documents as PDF files](https://se-education.org/guides/tutorials/savingPdf.html).

## 7. Testing <a name="testing"></a>

This section describes different types of tests used in the application and the ways to carry them out. 

### 7.1 JUnit Tests
We use JUnit to write all unit tests and integration tests for the project. If you are new to JUnit, 
refer to the [JUnit Tutorial](https://se-education.org/guides/tutorials/junit.html).

All the JUnit Tests are located under `src/test` folder. To run all the tests available in the project,
open a console in the project root directory and run `gradlew.bat test` if you are using Windows or `./gradlew test` if 
you are using Mac/Linux.

Alternatively, you can run the `test` task under `Tasks > verification > test` in the Gradle tool window if 
you are using IntelliJ.

### 7.2 I/O Redirection Tests
We use I/O redirection to test the actual output of the application against the expected output using a set of different
inputs.

To run the test, open a console in the `text-ui-test` folder under the project root and run `runtest.bat` if you
are using Windows or `./runtest.sh` if you are using Mac/Linux. If the test is successful,
you will see the following message in the last line of the output.
```
Test passed!
```
If the test fails, you will instead see the following message in the last line of the output.
```
Test failed!
```
To see what goes wrong, you can compare the content of `ACTUAL.txt` and the content of `EXPECTED.txt` inside the same folder,
where `ACTUAL.txt` contains the actual output of the application and `EXPECTED.txt` shows the expected output of the 
application given the inputs.

> 📝️️ **_NOTE:_** You can change the inputs in `input.txt` and the expected output in `EXPECTED.txt` if you would like to
> test more aspects of the application or if the input/expected output changes due to code modification.

### 7.3 Checkstyle
We use [Checkstyle](https://checkstyle.sourceforge.io/) 
to check if all the developers comply to the coding standards. The rules and configurations can be found
in `checkstyle.xml` under `config/checkstyle` folder in the project root. Refer to the official documentation
to learn more about how to configure the rules.

To run the checks, open a console in the project root directory and run `gradlew.bat checkstyleMain` if you are using Windows 
or `./gradlew checkstyleMain` if you are using Mac/Linux. 

Alternatively, you can run the `checkstyleMain` task under `Tasks > other > checkstyleMain` in the Gradle tool window if 
you are using IntelliJ.

## Appendix A: Product scope <a name="product-scope"></a>

This section describes the scope of the application. To be specific, the target audience and 
the uses and benefits of this application.

### Target user profile <a name="Ptarget"></a>

The targeted audience are university students , aged 18 - 25 who have access to a computer. The students must be familiar
with applications that run on the command-line interface. Currently, this application is created for students who study in 
Singaporean universities.

### Value proposition <a name="Pvalue"></a>

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

## Appendix B: User Stories <a name="user-story"></a>

This section summarises various user stories of this application in the table below.

|Version| As a ... | I want to ... | So that I ...|
|--------|----------|---------------|------------------|
|v1.0|new user| add a flash card intuitively|don't get put off by learning a new program |
|v1.0|long time user| categorize the flashcards to different categories|know where to look for which flashcard|
|v1.0|organised person| see neat UI and flashcard organization| will want to use the program|
|v1.0|student|revise all my notes for different subjects|do not have to use multiple applications/websites to revise|
|v1.0|long time user|add flashcards efficiently |can save time when need to add multiple flashcards|
|v1.0|long time user|find a to-do item by name|can locate a to-do without having to go through the entire list|
|v1.0|student|do quizzes on the flashcards added| can know how much I remember the content|
|v2.0|forgetful student|be notified of assignments which are due in a week|do not forget them|
|v2.0|new user|see usage instructions|can refer to them when I forget how to use the application|
|v2.0|CS student|export all data into a readable format| can import the data into another application I made|
|v2.0|normal user| be able to view my data saved on the disk easily| can do modify the data without launching the app|
|v2.0|long time user| see all the subjects, topics, and number of flashcards I have added| know what I have added|

## Appendix C: Non-Functional Requirements <a name="nfr"></a>

The following are the requirements that the application should satisfy:

1. The application should be easily maintainable when bugs and errors occur. 
1. The application should be scalable for possible future expansion of the project.
1. The application should run on any mainstream OS as long as it has Java 11 or above installed.
1. Application data should be portable so that it can be transferred between devices.
1. The application should be easy to use or come with a detailed user guide/help for better user experience.
1. The application should not crash when invalid or malicious user input is received.
1. Users should be allowed to modify the saved data manually without causing disruption to the application operations.

## Appendix D: Glossary <a name="glossary"></a>
### A
1. Application - Refers to the revisED application which the user is using.
1. `AccessSubjectCommand` - A command in the subjectcommand packagewhich returns the subject that the user wants to access.
1. `AddSubjectCommand` - A command which adds a subject to the application.
1. `AddTodoCommand` - A command which adds a todo task in the application.
1. `AddDeadlineCommand` - A command which adds a deadline task in the application.
1. `AddEventCommand` - A command which adds an event task to the application.
1. `AccessTopicCommand` - A command which returns the topic that the user wants to access.
1. `AddTopicCommand` - A command which adds a topic to the application.
1. `AddFlashcardCommand` - A command which adds a flashcard in the application.
1. attributes - variables in a class
### B
1. `BaseList` - An abstract class which all others lists inherit from.
1. Builder Pattern - A methodology to ensure that the clarity does not decrease even if the
number of parameters increase.

### C
1. `card` - Package which contains the quizcard package, task package and the Flashcard,Topic and Subject classes.
1. `command` - A package which holds the 4 command packages: subjectcommand,topiccommand,taskcommand,flashcardcommand. This package 
is in charge of returning the relevant subject,task,topic or flashcard .
1. `Command` - An abstract class which is inherited by all the command classes.
1. CLI - Command Line Interface
1. Checkstyle - Tool that checks the coding standards in the application.
### D
1. `DataLoadingException` - An exception thrown if the application is not able to load data.
1. `Deadline` - A class which refers to a task with a deadline. Inherits from the Task class.
1. `DeleteTaskCommand` - A command which deletes a task from the application.
1. `DeleteSubjectCommand` - A command which deletes a subject from the application.
1. `DeleteTopicCommand` - A command which deletes a topic from the application.
1. `DeleteFlashcardCommand` - A command which deletes a flashcard from the application.
1. `description` - A variable in the Result class which describes the grade obtained during a quiz.
1. `description` - A variable in the Task class which describes the task to be completed.
1.  `DateTime` - A variable in Event and Deadline classes which tracks the time and date at which the
task has to be completed.
1.  
### E
1. `Event` - A class which refers to an event. Inherits from the Task class.
1. `ExitSubjectCommand` - A command which allows the user to exit the application.
1. `ExitTopicCommand` - A command which allows the user to exit the subject menu to return to the main menu.
1. `ExitTaskCommand` - A command which allows the user to exit the subject menu to return to the main menu.
1. `ExitFlashcardCommand` - A command which allows the user to exit the topic menu to return to the subject menu.
1. `exception` - A package which holds all the exceptions used in the application. These exceptions are delcared as individual
classes, all extending the Exception class.
1. `Exception ` - A class which holds conditions that the application would throw in the event that the input entered
is incorrect.
1. `execute` - A command in Command classes which executes the required action by the command.
1. `export` - Command to export the data to external file.
1. `Excellent` - A description for a quiz which states that the user obtained full marks for the quiz.
### F
1. `Flashcard` - A class which instantiates the flashcard object.Contains the question and answer feature of the flashcard.
1. `FindSubjectCommand` - A command which finds the subject which the user requested for.
1. `FindTaskCommand` - A command which finds the task which the user requested for.
1. `FindTopicCommand` - A command which finds the topic which the user requested for.
1. `FailedParseException` - An exception thrown in the SorrySubjectCommand if the application is not able to read the commands.
1. `FlashcardParser` - A class which reads the user commands to call the relevant Flashcard command.
1. `flashcardcommand` - A package which holds the different FlashcardCommand classes.
1. `FlashcardCommand` - An abstract class that is inherited by all the other FlashcardCommand classes in the FlashCardCommand package.
1. `flashcardexception` - A package which holds all the exceptions for methods related to flashcard operations.
1. `Fail` - A description for a quiz which states that the user failed the quiz.
1. `FINE` - A log level, which indicates the severity of the message.
### G
1. `goToSubject`- A method in the AccessSubjectCommand class which allows the application to transition to the
Subject Level from the main level.
1. `goToTopic` - A method in the AccessTopicCommand class which allows the application to transfer from 
the Subject Level to the Topic Level.
1.  GSON - Used to create the Json files in the external data file.
1.  GRADLE -  An automated tool to build and compile the application.
### H
1. `HelpSubjectCommand` - A command which lists all the commands in the Main level.
1. `HelpTopicCommand` - A command which lists all the commands in the Subject level.
1. `Revised` - The main class which initiates the whole application.It is the name of the application as well.
1. `ui` - Package which contains the Ui class, in charge of user interactions.
1. `Ui` - A class which takes care of interaction with the users. Prints the correct output to the console.

### I
1. `InvalidFlashcardException` - An exception thrown if a flashcard is not created using the question ; answer format.
1. `InvalidSubjectException` - An exception that is thrown if the user does not add a title of a subject for
the commands in the main menu.
1. `InvalidTopicException` - An exception thrown if the TopicCommands are used solely without a topic.
1. `incorrectAnswers` - List which stores the questions that the user answered incorrectly, along with the answer provided
by the user and the correct answer.
1. `isExit` - A method which checks if the user wants to change levels/exit application. This
method is implemented in all the command classes.
1. I/O - Input/Output
### J
1. JSON - Javascript Object Notation. A form of data representation used in the external data files.
1. Jekyll - A software used by Github to create websites.
1. JUnit - A testing framework in Java that runs automated tests.
### L
1. `ListAllSubjectCommand` - A command which all the items in the application.
1. `ListSubjectCommand` - A command which lists all the subjects in the application.
1. `ListAllTopicCommand` - A command which prints all the topics in the Subject level.
1. `ListTopicCommand` - A command which lists all the topics and tasks in the application.
1. `ListTaskCommand` - A command which lists all the  tasks in the application.
1. `ListFlashcardCommand` - A command which lists all the  flashcards in the application.
1. `ListAllFlashcardCommand` - A command which lists all the items in the application in the topic menu.
1. `list` - A package which holds all the lists used in the application.
1.  `LocalDateTime` - A class in Java which contains both the time and date of a given task.
1. `LocalDateTimeMax`- Maximum time available in the LocalDateTime library.
1. `Logging` - Procedure of writing information to an external file, to track the flow of the program.
### M
1. `maxScore` - Maximum score obtainable from a quiz.
1. Markdown - Syntax used in Github.
### N
1. `NoFlashcardException` - An exception thrown if a quiz is initiated for a subject or topic without flashcards.
1. `NoSubjectException` - An exception thrown if the user requests for a subject that is not present in the application.
1. `NoTopicException` - An exception thrown if the user requests for a topic that has not been added to the application.

### P
1. `parser` - A package which parses the user commands and initialises the necessary topics,subjects or tasks.
1. `Pass` - A description for a quiz which states that the user passed the quiz.
1.  PlantUML - Software used to create UML diagrams.

### Q
1. `quizcard` - Package which contains the Quiz,Result,SubjectQuiz,TopicQuiz classes.
1. `Quiz` - An Abstract class that holds the result, list of flashcards and a list of incorrectAnswers for the 
SubjectQuiz class and the TopicQuiz class to inherit from.
1. `QuizSubjectCommand` - A command which initiates a quiz for a subject.
1. `QuizTopicCommand` - A command which initiates a quiz for a topic.

### R
1. `Result` - A class which stores the marks from a quiz and a description which indicates the performance of the user 
for that quiz.
1. `result` - An object of the Result class.
1. `ResultSubjectCommand` - A command which shows all the results of subjectQuizzes obtained for the subject.
1. `ResultTopicCommand` - A command which shows all the results of topicQuizzes obtained for the topic.
1. `RepeatedFlashcardException` - An exception thrown if the user tries to add a flashcard which already exists.
1. `RepeatedSubjectException` - An exception thrown if the user adds a subect which is already present in the application.
1. `ResultList` - A class which holds an ArrayList of Results.
1. `RepeatedTopicException` - An exception thrown if the users adds a topic that is already present in the application.
1. repo - The repository in Github which holds the revisED project.

### S
1. `SubjectQuiz` - Instantiated when the user calls for a quiz on a subject. 
1. `Subject` - A class which instantiates the subject object. Holds the title of the subject and lists of flashcards, 
results and topics.
1. `subjectcommand` - A package which holds the different subjectcommand classes.
1. `SubjectCommand` - An abstract class that is inherited by all the other SubjectCommand classes in the subjectcommand package.
1. `SorrySubjectCommand` - A command which highlights invalid commands in the main menu.
1. `SorryTopicCommand` - A command which highlights invalid commands in the subject menu.
1. `SorryTaskCommand` - A command which highlights invalid commands in the subject menu.
1. `SorryFlashcardCommand` - A command which highlights invalid commands in the topic menu.
1. `storage` - A package which holds the Storage class, which is in charge of storing and loading data
into the application.
1. `Storage` - A class which is saves the data when the user exits the application and loads the data once the
user starts the application.
1. `storageexception` - A package which holds the DataLoadingException.
1. `subjectexception` - A package which holds the exceptions thrown in the main menu.
1. `SubjectParser` - A class which reads the user commands to call the relevant Subject command.
1. `SubjectList` - A class which holds an ArrayList of Subjects.
1. `score` - A variable in the Result class which tracks the score during the quiz.
1. `setupQuiz` - Method in SubjectQuiz and TopicQuiz class which sets up the quiz by transfering the flashcards.
1. `StorageBuilder` - Inner class of storage class and creates storage instances.

### T
1. `TopicQuiz` - Instantiated when the user calls for a quiz on a topic. 
1.  `task` - A package which contains the TaskList class.
1. `TaskList ` - A class which holds the list of classes.
1. `taskcard` - Package which contains the task classes.
1. `Task` - An abstract class which Todo, Deadline and Event classes inherit from.
1. `Todo` - A class which refers to a task without deadline.Inherits from the Task class.
1. `Topic` - A class which is instantiated when the user creates the topic. Holds the title of the topic, an arraylist of
flashcards and an arraylist of results.
1. `topiccommand` - A package which holds the different topiccommand classes.
1. `TopicCommand` - An abstract class that is inherited by all the other TopicCommand classes in the topiccommand package.
1. `taskcommand` - A package which holds the different taskcommand classes.
1. `TaskCommand` - A  class that is inherited by all the other TaskCommand classes in the taskCommand package.
1. `taskexception` - A package which holds the exceptions related to task operations.
1. `TaskParser` - A class which reads the user commands to call the relevant task command.
1. `TopicParser` - A class which reads the user commands to call the relevant topic command.
1. `TopicList` - A class which holds an ArrayList of Topics.
1. `TaskList` - A class which holds an ArrayList of Tasks.
1. `TaskDeadlineException` - An exception thrown if an invalid deadline task is added to the applcation.
1. `TaskEventException` - An exception thrown if an invalid event task is added to the application.
1. `TaskTodoException` - An exception thrown if an invalid todo task is added to the application.
1. `topicexception` - A package which holds the exceptions that are related to topic operations.
1. `toString` - A method provided by Java to convert the current object into a String. This method has been overriden 
in classes such as Event,Deadline to provide the accurate output .

### U
1.  `updateResult` - Method in Result class which updates the score of a quiz.

## Appendix E: Instructions for Manual Testing

Given below are the instructions to test the app manually.

> 📝️️ **_NOTE:_** These instructions only provide a starting point for testers to work on; testers are expected to do more exploratory testing.

### Launching

1. Refer to the [Quick Start](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html#start) to download and launch the application.
1. **Expected:** The application should show the greeting screen as illustrated in the quick start section.

### Testing features

1. Run each command as demonstrated in the example of usage in the [Features](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html#features) section
of the user guide.
1. **Expected:** The output should match the sample output given.

### Shutdown

1. Run `exit` command until you reach the main level of the application (or until an error message saying the command is not recognized is displayed).
1. Run `bye` command.
1. **Expected:** A bye message should be displayed and the application should exit.

### Saving data

#### When shutdown normally  <a name="man-save-normal"></a>
1. Create a new folder and copy the jar file used in the above steps to the new folder. 
1. Navigate to the new folder and launch the application.
1. Run `add CS2113T` then `subject CS2113T`.
1. Run `add Java`.
1. Run `exit` then `bye`.
1. **Expected:** A `data/` folder containing the same structure as mentioned <a href="#store-imp">here</a> should be created
in the new folder.

#### When shutdown abruptly
1. Follow steps 1 to 4 <a href="#man-save-normal">above</a> to add data.
1. Close the console running the application, then navigate to the new folder via file explorer.
1. **Expected:** No folder is found created in the new folder.

### Loading data

#### When data is not modified externally
1. Follow all the steps <a href="#man-save-normal">above</a> to create saved data.
1. Launch the application again.
1. **Expected:** CS2113T subject and Java topic persist in the application.

#### When data is modified externally
1. Follow all the steps <a href="#man-save-normal">above</a> to create saved data.
1. Go into the `data/` folder in the new folder.
1. Rename the `CS2113T/` folder to `CS2040/`.
1. Go back to the new folder and launch the application.
1. **Expected:** CS2113T subject is changed to CS2040, and Java topic remains the same in the application.


