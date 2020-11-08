# User Guide

## Table of Contents (Sugandha)

* <a href =#intro>1. Introduction </a>
* <a href =#start>2. Quick Start </a>
* <a href =#features>3. Features </a>      
   * <a href =#main-level>3.1 Main Level Features </a>
   * <a href =#subject-level>3.2 Subject Level Features </a>
   * <a href =#topic-level>3.3 Topic Level Features </a>
   * <a href =#other-feat>3.4 Other Features </a>
* <a href =#faq>4. FAQ </a>
* <a href =#summary>5. Command Summary </a>

## 1. Introduction <a name="intro"></a>

**revisED** aims to help students to revise their subjects through the creation of flashcards or tasks that have
to be completed. The users can store the tasks under different subjects and flashcards under different topics, 
which can be created under the subjects. This application ensures that students would be
able to revise, even if they are doing it at the last minute.

This user guide provides an in-depth documentation on the installation process, the application features, and
the application usage to get you started.

Note the following symbols and formatting used in this document:

Symbols / Formatting|Meaning
------|------
📝️   **_NOTE:_** | Important or additional information
⚠️ **_WARNING:_** | Things to avoid or pay extra attention to
`Grey highlight` | Code or terms related to the code/application


## 2. Quick Start <a name="start"></a>

To setup the application,

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `revised.jar` [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).
1. Transfer the jar file to the folder of your choice.
1. Open a command prompt in the folder you just put the jar file in. Follow this [guide](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/)
if you are unsure how.
1. Run the command `java -jar revised.jar` to start the application.

You will see the logo of the application, as shown below, once u run it.

```
Hello from
                                    ___________
                                    |  __ |  _ \
 ____  ______      _____   ________ |  |__| | | |
|  __|/ __ \ \    / /| |  /  _____/ |   __| | | |
| |  |  __/ \ \__/ / | | /_____  /  |  |__| |_| |
| |   \___|  \____/  |_|/_______/   |_____|_____/
```

Subsequently, you can begin using the application!

## 3. Features <a name="features"></a>

This section elaborates on the features of **revisED** along with their usage. We further break this section down into several
subsections to better illustrate the design of the application.

* <a href =#main-level>3.1 Main Level Features </a>
* <a href =#subject-level>3.2 Subject Level Features </a>
* <a href =#topic-level>3.3 Topic Level Features </a>
* <a href =#other-feat>3.4 Other Features </a>

To give you some context, **revisED** is divided into 3 logical levels—**main**, **subject**, and **topic** levels. In the main level,
you can add/configure different subjects; in the subject level, you can add/configure different topics and tasks (under a subject); and in the topic
level, you can add/configure different flashcards (under a topic & subject). Continue reading to learn more about the features
in each level.

> 📝️️  **_NOTE:_** The format of the command, an example of usage, and an expected output will be presented in `grey boxes` 
> for each feature that involves the use of a command.


### 3.1 Main Level Features <a name="main-level"></a>

Main level features can only be used when the user is currently viewing the main section of the program.
On this level, users are able to add and delete subjects in a subject list. 
Users are also able to access the subjects in the subject list. 
For more details on subjects, see section <a href =#subject-level>3.2 Subject Level Features </a>.
This section describes the usage of commands that can be used to add, delete and list subjects. 

#### 3.1.1 Displaying all the available commands: `help`

Displays all available commands on the main level.  

Format: `help`  

- Use this command whenever you get confused or need help to navigate the application.
- The application will print a list of all the functions available to you at the current level(main) on the command line.

Example of usage:
```
help
```

Expected output:
```
________________________________________________________________________________________________________________________
help:          shows the list of commands available at the main level
add abc:       adds a subject called 'abc'
find abc:      finds all subjects containing the letters abc
list:          shows the list of all subjects
list all:      shows the tree of all subjects, topics, tasks and flashcards
delete 1:      deletes the 1st subject in the list.
subject abc:   enters the subject called abc, now you can create, find, list, delete and enter the topics of subject abc
quiz abc:      starts a quiz for all the flashcards present in all the topics of subject abc,
               answer the questions of the current flashcards to test your knowledge
results abc:   gives you the results of all attempted quizzes for abc subject
export:        exports all the data to a JSON file
bye:           exits the application
________________________________________________________________________________________________________________________
```

#### 3.1.2 Listing all subjects: `list` <a name="list"></a>

Prints a list of all subjects in the main list.

Format: `list`

- Use this command whenever you need to see a list of all the subjects you have keyed into the application.
- The application will print the title of all the subjects in your list on the command line.

Example of usage:
```
list
```

Expected output:
```
____________________________________________________________
Here are the subject(s) in your list:
1.CS1010
2.CS2040C
3.CG2027
4.CS2113T
5.CS2101
____________________________________________________________
```

#### 3.1.3 Listing all items: `list all` <a name="list"></a>

Prints a list of all items stored in the application.

Format: `list all`

- Use this command whenever you need to see a list of all the subjects, topics and tasks you have keyed into the application.
- The application will print a tree on the command line, and will also show you that you are accessing the main level.

Example of usage:
```
list all
```

Expected output:
```
____________________________________________________________
Here's a list of all items:
(You are currently here)
├─ 1. CG2027
│  │  Topics
│  ├─ 1. Pass Transistor Multiplexers
│  ├─ 2. Arithmetic Logic Unit
│  │  Tasks
│  └─ 1. [T][✘] revise on Arithmetic Logic Unit
├─ 2. CS2101
│  │  Topics
│  ├─ 1. Oral Presentation
│  ├─ 2. Project Demo
│  │  Tasks
│  └─ 1. [E][✓] Project Demo (at: 2:00 PM 5 Nov 2020)
└─ 3. CS2113T
   │  Topics
   ├─ 1. Class Diagram
   ├─ 2. Abstraction
   │  Tasks
   └─ 1. [D][✘] Final Project (by: 11:59 PM 11 Nov 2020)
____________________________________________________________
```

#### 3.1.4 Adding a subject: `add`

Adds a subject to the main list.

Format: `add [SUBJECT_NAME]`

- Adds a subject with a name of `[SUBJECT_NAME]`.
- The application will print the title of the subject you have added into the subject list, as well as the amount of subjects you 
have in the subject list on the command line.

> ⚠️ **_WARNING:_** `[SUBJECT_NAME]` is case-sensitive. `cs2101` and `CS2101` will be registered as 2 different subjects. 
>Adding tasks or topics in `cs2101` will not add the same tasks and topics in `CS2101`.

Example of usage:
```
add CS2101
```

Expected output:
```
____________________________________________________________
Got it. I've added this subject:
  CS2101
Now you have 5 subjects in the list.
____________________________________________________________
```

#### 3.1.5 Deleting a subject: `delete`

Deletes the specified subject, given the subject number.

Format: `delete [SUBJECT_NUMBER]`

- Deletes the subject with the index number of `[SUBJECT_NUMBER]`.
- The application will print the title of the subject you have deleted from the subject list, as well as the amount of subjects you 
have in the subject list on the command line.
- `[SUBJECT_NUMBER]` must be a **positive integer**.

> 📝️️ **_NOTE:_** The `[SUBJECT_NUMBER]` of a subject is the number assigned to each subject when the user uses the 
>list command to print out the subjects.

> 📝️️ **_NOTE:_** After deletion of a subject, the `[SUBJECT_NUMBER]` of subjects in the list may change.

> ⚠️ **_WARNING:_** It is impossible to retrieve data deleted by this command. Make sure you do not need the data
> in this subject before you delete it!

Example of usage:
```
delete 1
```

Expected output:
```
____________________________________________________________
 Noted. I've removed this subject:
   CS1010
 Now you have 4 subjects in the list.
____________________________________________________________
```

#### 3.1.6 Finding a subject: `find`

Searches the list of subject names for all subjects that contains the query.

Format: `find [QUERY]`

- searches all subjects with `[QUERY]` in its title.
- The application will print a list of subjects with titles that contain the query.
- The find command will only search the name of subjects on this level.
- Partial words will be matched. e.g. `CS` will find `CS2101` and `CS2113T`.

> ⚠️ **_WARNING:_** `[QUERY]` is case-sensitive. e.g. `cs2113t` will not find a Subject with the name `CS2113T`.

Example of usage:
```
find CS
```

Expected output:
```
____________________________________________________________
 Here are the matching subject(s) in your list:
CS2040C
CS2113T
CS2101
____________________________________________________________
```

#### 3.1.7 Accessing a subject: `subject`

Allows the user to access a pre-existing subject.

Format: `subject [SUBJECT_NAME]`

- Access a pre-existing subject with a name of `[SUBJECT_NAME]`.
- Use this command if you want to access the subject level of a subject in the subject list.
- The application will print the subject level of the subject specified in `[SUBJECT_NAME]` on the command line.
- For more information on subject level commands, refer to <a href =#subject-level>3.2 Subject Level Features </a>.

> ⚠️ **_WARNING:_** `[SUBJECT_NAME]` is case-sensitive. e.g. `subject cs2113t` will not access a subject with the name `CS2113T`.

Example of usage:
```
subject CS2113T
```

Expected output:
```
____________________________________________________________
You are currently looking at the subject: CS2113T
____________________________________________________________
____________________________________________________________
Type help for all available commands
____________________________________________________________
```

#### 3.1.8 Starting Subject Quiz: `quiz` (Muthu) <a name="subject_quiz"> </a>

Starts a quiz for a particular subject. For more details, see <a href="#takeQuiz">takeQuiz</a>.

Format: `quiz [SUBJECT_NAME]`

- Starts a quiz of a pre-existing subject of the name `[SUBJECT_NAME]`.
- The application will quiz you on the subject specified in `[SUBJECT_NAME]` on the command line.

> ⚠️ **_WARNING:_** `[SUBJECT_NAME]` is case-sensitive. e.g. e.g. `quiz cs2101` will not start a `CS2101` quiz.


Example of usage: 
```
quiz CS2113T
```

Expected output: 
```
You are about to begin the quiz for CS2113T.You have 2 minutes.
```

#### 3.1.9 Viewing results for quizzes on a subject: `results`

This command allows you to look at the results for a subject. For more details, see <a href="#result">result</a>.

Format: `results [SUBJECT_NAME]`

- Displays the results of your previous quizzes of the subject the name `[SUBJECT_NAME]`.
- The application will print the results of all the quizzes of the subject specified in `[SUBJECT_NAME]` in the command line.

> ⚠️ **_WARNING:_** `[SUBJECT_NAME]` is case-sensitive. e.g. results cs2101 will not enable you to see your CS2101 quiz results.

Example of usage: 
```
results CS2113T
```

Expected output: 
```
Quiz 1: 1.0/2.0 -- Pass
Quiz 2: 2.0/2.0 -- Excellent
```

#### 3.1.10 Exporting data: `export` <a name="export-command"></a>

This command exports all the data of the application to an external file. For more details, see <a href="#export">Exporting Data section</a>.

Format: `export`

- Use this command if you want to save all the data in the application on an external file.
- The application will print the file location of the file the data is being saved to on the command line.

Example of usage:
```
export
```

Expected output:
```
____________________________________________________________
Your data has been successfully exported to /home/guest/revised/export/data.json.
____________________________________________________________
```

#### 3.1.11 Saving and exiting: `bye` <a name="bye"></a>

This command will save all the data in the application.

Format: `bye`

- Use this command if you want to save and exit the application.
- The application will save all the updated details and exit.

> ⚠️ **_WARNING:_** Use this command before closing the command line as this command 
>ensures all your data updated is saved. Failure to use this method to exit the application will 
>result in all work updated in the session being lost.

Example of usage:
```
bye
```

Expected output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### 3.2 Subject Level Features (Herman)<a name = "subject-level"> </a>

Subject level features can only be used when the user is currently viewing a subject.
On this level, users are able to add and delete topics and tasks. 
Users are also able to access the topics listed in the current subject. 
For more details on topics, see section <a href="#topic-level">3.3 Topic Level Features</a>.
This section describes the usage of commands that can be used to add, delete and list both topics and tasks. 

#### 3.2.1 Displaying all available commands: `help`

Displays all available commands on the subject level.

Format: `help` 

- Use this command whenever you get confused or need help to navigate the application.
- The application will print a list of all the functions available to you at the current level(subject) on the command line.
  
Example of usage:
```
help
```

Expected output:
```
________________________________________________________________________________________________________________________
help:                              shows the list of commands available at the subject level
add abc:                           adds a topic called 'abc' in the current subject
todo abc:                          adds a todo type task with the description 'abc'
deadline abc /by 12:00 13-11-2020: adds a deadline type task with description 'abc' with date/time of deadline
                                   as 12:00 AM 13 Nov 2020
event abc /at 01:00 21-11-2020:    adds an event type task with description 'abc' with date/time of event
                                   as 1:00 AM 21 Nov 2020
find abc:                          finds all topics and tasks containing 'abc' in the current subject
list:                              shows the list of all topics and tasks in the current subject
list all:                          shows the tree of all subjects, topics, tasks and flashcards
delete topic 1:                    deletes the 1st topic in the list of topics.
delete task 1:                     deletes the 1st task in the list of tasks.
done 1:                            marks the 1st task in the list of tasks as done
topic abc:                         enters the topic abc, now you can add, find, list and delete flashcards of topic abc
quiz abc:                          starts a quiz for all the flashcards of the topic abc,
                                   answer the questions of the prompted flashcards to test your knowledge
results abc:                       gives you the results of all attempted quizzes for abc topic
exit:                              exits the subject to return to the main screen, where you can work with subjects
________________________________________________________________________________________________________________________
```

#### 3.2.2 Listing all topics and tasks: `list` <a name="subject-list"></a>

Prints a list of all topics and tasks of the current subject. 
  
Format: `list`

- Use this command whenever you need to see a list of all the tasks and topics you have keyed into the subject.
- The application will print the title of all the tasks and topics in your subject on the command line.

Example of usage:
```
list
```

Expected output:
```
____________________________________________________________
Here are the topic(s) under CS2101: 
1. Oral Presentation
2. Project Demo
____________________________________________________________
Here are the tasks(s) under CS2101: 
1. [E][✘] Project Demo (at: 2:00 PM 5 Nov 2020)
____________________________________________________________
```
#### 3.2.3 Listing all items: `list all`

Prints a list of all items stored in the application.

Format: `list all`

- Use this command whenever you need to see a list of all the subjects, topics and tasks you have keyed into the application.
- The application will print a tree on the command line, and will also show you that you are accessing the subject level of 
a specific subject.

Example of usage:
```
list all
```

Expected output:
```
____________________________________________________________
Here's a list of all items:
├─ 1. CG2027
│  │  Topics
│  ├─ 1. Pass Transistor Multiplexers
│  ├─ 2. Arithmetic Logic Unit
│  │  Tasks
│  └─ 1. [T][✘] revise on Arithmetic Logic Unit
├─ 2. CS2101 (You are currently here)
│  │  Topics
│  ├─ 1. Oral Presentation
│  ├─ 2. Project Demo
│  │  Tasks
│  └─ 1. [E][✓] Project Demo (at: 2:00 PM 5 Nov 2020)
└─ 3. CS2113T
   │  Topics
   ├─ 1. Class Diagram
   ├─ 2. Abstraction
   │  Tasks
   └─ 1. [D][✘] Final Project (by: 11:59 PM 11 Nov 2020)
____________________________________________________________
```
#### 3.2.4 Adding a topic: `add` <a name="add-topic"></a>

Adds a topic to the current subject.

Format: `add [TOPIC_NAME]`
- Adds a topic with a name of `[TOPIC_NAME]`.
- The application will print the title of the topic you have added into the topic list, as well as the amount of topics you 
  have in the topic list on the command line.

> ⚠️ **_WARNING:_** `[TOPIC_NAME]` is case-sensitive. `project pitch` and `PROJECT PITCH` will be registered as 2 different topics. 
>Adding flashcards in `project pitch` will not add the same flashcards in `PROJECT PITCH`.

Example of usage: 
```
add Project Pitch
```

Expected output:
```
____________________________________________________________
Got it. I've added this topic:
  Project Pitch
Now you have 3 topics in the list.
____________________________________________________________
```

#### 3.2.5 Deleting a topic: `delete topic` <a name="delete-topic"></a>

Deletes the topic from the current subject, given the topic number.

Format: `delete topic [TOPIC_NUMBER]`

- Deletes the topic with the index number of `[TOPIC_NUMBER]`.
- The application will print the title of the topic you have deleted from the topic list, as well as the amount of topics you 
have in the topic list on the command line.
- `[TOPIC_NUMBER]` must be a **positive integer**.

> 📝️️ **_NOTE:_** The TOPIC_NUMBER` of a topic is the number assigned to each topic when the user uses the 
>list command to print out the topics.

> 📝️️ **_NOTE:_** After deletion of a topic, the `TOPIC_NUMBER` of topics in the list may change.

> ⚠️ **_WARNING:_** It is impossible to retrieve data deleted by this command. Make sure you do not need the data
> in this subject before you delete it!

Example of usage: 
```
delete topic 3
```

Expected output:
```
____________________________________________________________
 Noted. I've removed this topic:
   Project Pitch
 Now you have 2 topics in the list.
____________________________________________________________
```


#### 3.2.6 Finding a topic or task: `find` <a name="subject-find"></a>

Searches the current subject for all the topics and tasks that contains the query.

Format: `find [QUERY]`
- searches all topics and tasks in the current subject with `[QUERY]` in its name and prints a list of them.
- The application will print a list of topics and tasks with titles that contain the query.
- The `find` command will only search the name of topics and tasks on this level.
- Partial words will be matched. e.g. `Pro` will find `Project`.

> ⚠️ **_WARNING:_** `[QUERY]` is case-sensitive. e.g. `project` will not find a topic or task with the name `PROJECT`.

Example of usage: 
```
find Pro
``` 

Expected output:
```
____________________________________________________________
 Here are the matching topic(s) in your list:
Project Demo

____________________________________________________________
 Here are the matching task(s) in your list:
[E][✓] Project Demo (at: 2:00 PM 5 Nov 2020)
____________________________________________________________
```

#### 3.2.7 Accessing a topic: `topic` <a name="access-topic"></a>

Enters a pre-existing topic.

Format: `topic [TOPIC_NAME]`
- Access a pre-existing topic with a name of `[TOPIC_NAME]`.
- Use this command if you want to access the topic level of a topic in the topic list.
- The application will print the topic level of the subject specified in `[TOPIC_NAME]` on the command line.
- This command is case-sensitive. e.g. `topic project` will not allow you to access a topic with the name `PROJECT`.
- For more information on topic level commands, refer to <a href="#topic-level">3.3 Topic Level Features</a>

> ⚠️ **_WARNING:_** `[TOPIC_NAME]` is case-sensitive. e.g. `topic project` will not access a topic with the name `PROJECT`.

Example of usage:
```
topic Oral Presentation
```

Expected output:
```
____________________________________________________________
You are currently looking at the topic: Oral Presentation
____________________________________________________________
____________________________________________________________
Type help for all available commands
____________________________________________________________
```

#### 3.2.8 Adding a 'todo' task: `todo` <a name="todo"></a>

Adds a todo task.

Format: `todo [DESCRIPTION]`

Example of usage: 
```
todo look up on what is active listening
```

Expected output:
```
____________________________________________________________
Got it. I've added this task:
  [T][✘] look up on what is active listening
Now you have 2 tasks in the list.
____________________________________________________________
```

#### 3.2.9 Adding a 'deadline' task - `deadline` <a name="deadline"></a>

Adds a deadline task.

Format:

`deadline [DESCRIPTION] /by [TIME]`
- A deadline must have a `[TIME]`. If you do not wish to set a time, add a <a href="#todo">todo</a> instead.

> 📝️️ **_NOTE:_** Add the time using the HH:MM DD-MM-YYYY format. 

Example of usage: 
```
deadline assignment /by 23:59 21-10-2020
```

Expected output:
```
____________________________________________________________
Got it. I've added this task:
  [D][✘] assignment (by: 11:59 PM 21 Oct 2020)
Now you have 2 tasks in the list.
____________________________________________________________
```

#### 3.2.10 Adding an event - `event` - Add event <a name="event"></a>

Add an event task.

Format: `event [DESCRIPTION] /at [TIME]`
- A deadline must have a `[TIME]`. If you do not wish to set a time, add a <a href="#todo">todo</a> instead.

> 📝️️ **_NOTE:_** Add the time using the HH:MM DD-MM-YYYY format. 

Example of usage: 
```
event tutorial /at 16:00 21-10-2020
```

Expected output:
```
____________________________________________________________
Got it. I've added this task:
  [E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### 3.2.11 Deleting a task: `delete task`

Deletes a task from the current subject, given the task number.

Format: `delete task [TASK_INDEX]`

- `[TASK_INDEX]` must be a **positive integer**.
- Task `[TASK_INDEX]` must **already exist in the task list**.

> ⚠️ **_WARNING:_** It is impossible to retrieve data deleted by this command. Make sure you do not need the data
> in this subject before you delete it!

Example of usage: 
```
delete task 2
```

Expected output:
```
____________________________________________________________
 Noted. I've removed this task:
   [E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
 Now you have 2 tasks in the list.
____________________________________________________________
```

#### 3.2.12 Marking a task as completed: `done`

Marks the specified task as done.

Format:
```
done [TASK_INDEX]
```

- `[TASK_INDEX]` must be a **positive integer**.
- Task `[TASK_INDEX]` must **already exist in the task list**.

Example of usage: 
```
done 2
```

Expected output:
```
____________________________________________________________
 Nice! I've marked this task as done:
   [D][✓] assignment (by: 11:59 PM 21 Oct 2020)
____________________________________________________________
```

#### 3.2.13 Starting Topic Quiz - `quiz` (Muthu) <a name="topic_quiz"> </a>

This type of quiz helps you to prepare for a particular topic. For more details, see section <a href =#takeQuiz>3.4.1 Taking quizzes</a>.

Format: `quiz [TOPIC_NAME]`

- Starts a quiz of a pre-existing topic the name `[TOPIC_NAME]`.
- The application will quiz you on the topic specified in `[TOPIC_NAME]` on the command line.

> ⚠️ **_WARNING:_** `[TOPIC_NAME]` is case-sensitive. e.g. `quiz java` will not start a `Java` quiz.

Example of usage: 
```
quiz Java
```

Expected output: 
```
You are about to begin the quiz for Java. You have 1 minute.
```

#### 3.2.14 Viewing results for quizzes on a topic: `results` (Muthu)

This command allows you to look at the results for a topic. For more details, see <a href =#result>result</a>.

Format: `results [TOPIC_NAME]`

- Displays the results of your previous quizzes of the topic the name `[TOPIC_NAME]`.
- The application will print the results of all the quizzes of the topic specified in `[TOPIC_NAME]` in the command line.

> ⚠️ **_WARNING:_** `[TOPIC_NAME]` is case-sensitive. e.g. `results java` will not enable you to see your `Java` quiz results.

Example of usage: 
```
results Java
```

Expected output: 
```
Quiz 1: 1.0/2.0 -- Pass
Quiz 2: 2.0/2.0 -- Excellent
```

#### 3.2.14 Exiting the subject: `exit`

Exits the subjects and returns to the main level.

Format: `exit`

- Use this command whenever you want to return to the subject level to work with subjects.

> 📝️  **_NOTE:_** The `exit` command is not the same as the `bye` command.
> The `bye` command saves and closes the application, and can only be accessed on the main level.

Example of usage:
```
exit
```

Expected output: 
```
____________________________________________________________
Going back to the main menu.
____________________________________________________________
```

### 3.3 Topic Level Features <a name="topic-level"> </a> (Sugandha)

Topic level features can only be used when the user is currently viewing a topic.
On this level, users are able to add and delete flashcards, in the form of questions and answers.
These flashcards can also be used to take topic or subject quizzes. For more information on subject quizzes see 
<a href=#subject_quiz>3.1.8 Starting Subject Quiz</a> and for topic quizzes see <a href =#topic_quiz>3.2.13 Starting Topic Quiz</a>. 
This section describes the usage of commands that can be used to add, delete and list flashcards. 

#### 3.3.1 Displaying all the available commands: `help`

Displays all available commands.    

Format: `help`   
- Use this command whenever you get confused or need help to navigate the application.
- The application will print a list of all the functions available to you at the current level(main) on the command line.
  
Example of usage:
```
help
```

Expected output:
```
________________________________________________________________________________________________________________________
help:              shows the list of commands available at the topic level
add abc; def:      adds a flashcard with question 'abc' and answer 'def' in the current topic
list:              shows the list of all flashcards in the current topic
list all:          shows the tree of all subjects, topics, tasks and flashcards
delete 1:          deletes the 1st flashcard in the list
exit:              exits the topic to return to the subject level, where you can work with tasks and topics
________________________________________________________________________________________________________________________
```

#### 3.3.2 Listing all flashcards: `list`

Prints a list of all flashcards of the topic you are currently viewing. 

Format: `list`  

- Use this command whenever you need to see all the flashcards you have added or to take note of the index number of the flashcard you might want to delete.
- The application will print the questions and answers of all the flashcards in your topic on the command line.
Example of usage: 
```
list
```

Expected output:

```
____________________________________________________________
Here are the flashcard(s) under Java: 
1. Q: Name 3 Java primitive data types. 
   A: short, int, long.
2. Q: Is return 0 needed at the end of main method to indicate a successful execution? 
   A: No, it is considered as a successful execution unless an error is signalled specifically.
____________________________________________________________
```

#### 3.3.3 Listing all items: `list all`

Prints a list of all items stored in the application.

Format: `list all`
- Use this command whenever you need to see a list of all the subjects, topics, tasks and number of flashcards you have keyed into the application.
- The application will print a tree on the command line, and will also show you that you are accessing the topic level of 
a specific topic. 

Example of usage:
```
list all
```
Expected output:

```
____________________________________________________________
Here's a list of all items:
├─ 1. CG2027
│  │  Topics
│  ├─ 1. Arithmetic Logic Unit
│  ├─ 2. Pass Transistor Multiplexers
│  │  Tasks
│  └─ 1. [T][✘] revise on Arithmetic Logic Unit
├─ 2. CS2101
│  │  Topics
│  ├─ 1. Oral Presentation
│  ├─ 2. Project Demo
│  │  Tasks
│  ├─ 1. [E][✓] Project Demo (at: 2:00 PM 5 Nov 2020)
│  └─ 2. [T][✘] look up on what is active listening
└─ 3. CS2113T
   │  Topics
   ├─ 1. Abstraction
   ├─ 2. Class Diagram
   ├─ 3. Java (You are currently here)
   │  └─ 1 Flashcard
   │  Tasks
   └─ 1. [D][✘] Final Project (by: 11:59 PM 11 Nov 2020)
____________________________________________________________
```

#### 3.3.4 Adding a flashcard - `add`

Adds a flashcard under the topic you are currently viewing.
These flashcards can be added to take notes. 
They are added in the form of questions and answers.

Format: `add` `[QUESTION]; [ANSWER]`
- Use this command whenever you wish to take notes.
- This command adds a flashcard with the question, `[QUESTION]` and the answer, `[ANSWER]`.
- The application will print the question and answer of the flashcard you added along with the 
total number of flashcards under the current topic.
> 📝️ ️  **_NOTE:_** Do not forget the semicolon, as it separates the question and the answer.

Example of usage: 
```
add What is the version of Java used in CS2113T; 11.0
```

Expected output:

```
____________________________________________________________
Got it. I've added this flashcard:
  Q: What is the version of Java used in CS2113T
  A: 11.0
Now you have 3 flashcards in the list.
____________________________________________________________
```


#### 3.3.5 Deleting a flashcard: `delete`

Deletes the flashcard from the current topic, given the flashcard number.
This can be used if you make a mistake or no longer need a flashcard.
You can check the flashcard number from the list of flashcards, which is described next.

Format: `delete [FLASHCARD_NUMBER]`
- Deletes the flashcard with the index number of `[TOPIC_NUMBER]`.
- The application will print the question and answer of the flashcard you have deleted from the topic list, as well as 
the number of flashcards you now have under the current topic on the command line.  

> 📝️️ **_NOTE:_** The FLASHCARD_NUMBER` of a flashcard is the number assigned to each flashcard when the user uses the 
>list command to print out the flashcards.

> 📝️️ **_NOTE:_** After deletion of a flashcard, the `FLASHCARD_NUMBER` of topics in the list may change.

> ⚠️ **_WARNING:_** It is impossible to retrieve data deleted by this command. Make sure you do not need the data
> in this topic before you delete it!

Example of usage: 

```
delete 3
```

Expected output:

```
____________________________________________________________
Noted. I've removed this flashcard:
  Q: What is the version of Java used in CS2113T
  A: 11.0
Now you have 2 flashcards in the list.
____________________________________________________________
```

#### 3.3.6 Exiting the topic: `exit` 

Exits the topic and returns the to the subject level.

Format: `exit`

- Use this command whenever you want to return to the subject level to work with topics and tasks.

> 📝️  **_NOTE:_** The `exit` command is not the same as the `bye` command.
> The `bye` command saves and closes the application, and can only be accessed on the main level.

Example of usage:
```
exit
```

Expected output: 
```
____________________________________________________________
Going back to the topics and tasks list.
____________________________________________________________
```


### 3.4 Other Features <a name="other-feat"></a>
This section expands on the features that are not specific to any part of the previous sections and features that
require further explanations.

#### 3.4.1 Taking quizzes <a name = "takeQuiz"> </a> (Muthu)
Once you have added the necessary flashcards, you can use the Quiz functionality to test yourself. This would
let you gauge how much you understand from the material. This will be beneficial if you are running out of time
to revise your own notes.
 
In this feature, the application prints the questions from the flashcards stored in the application. You need to complete the quiz within a
certain time limit. There are two types of quizzes :
* SubjectQuiz
* TopicQuiz

##### Subject quiz
This type of quiz helps you prepare for a particular subject.The application prints out the questions from the topics present in a subject.
You have 2 minutes to complete the quiz.

Format: `quiz NAMEOFSUBJECT`<br>
Example: `quiz Maths`

Expected output: `You are about to begin the quiz for maths.You have 2 minutes.`

##### Topic quiz
This type of quiz helps you to prepare for a particular topic. The application prints out all the questions that have been saved in the specific topic .
You have 1 minute to complete the quiz.

> 📝️ ️  **_NOTE:_** Ensure that you start a quiz for a subject/topic that has been added to the application 
> and has a flashcard. Else, the application will not start the quiz.

Once you have entered the type of quiz you want to begin, the application starts the quiz. You
are expected to answer these questions.

Format: `Question NAMEOFQUESTION`<br>
Example: `Question: What is the version of Java used in CS2113T?` <br>

Once the quiz has been completed, the application prints out the scores that you obtained.
You will also be notified about the questions that you did not answer correctly. 

The score is printed in the following format:<br>
* If you get full marks : `Result:4/4 -- Excellent`<br>
* If you get enough marks to pass: `Result:2/4 -- Pass`<br>
* If you fail: `Result:0/4 -- Fail`<br>

The code below shows an example of a result that you can get after attempting a quiz.

<pre><code>____________________________________________________________
Result:2/4 -- Pass
____________________________________________________________
Here are the questions which you got wrong.
           
Question:What is the version of Java used in CS2113T?
Correct Answer: 11.0 
Your Answer: 11.1
____________________________________________________________
Question:What is the ide used for the project? 
Correct Answer: Intellij
Your Answer: Vim
____________________________________________________________</code></pre>

You can stop the quiz by entering `stop`. The application then prints the score.<br>
Format: `stop`<br>
Expected output:`The quiz has been stopped!`.
<pre><code>
____________________________________________________________
The quiz has been stopped!
____________________________________________________________
Result:1/2 -- Pass
____________________________________________________________
</code></pre>

> 📝️ ️  **_NOTE:_** If you stop the quiz, the application will only print the score for the particular quiz. It does not
>print the list of incorrect answers.

> ⚠️ **_WARNING:_** If you stop the quiz or if the timer ends for the quiz,you cannot go back to the same quiz to continue it. You will
> have to restart another quiz.

#### 3.4.2 Viewing quiz results <a name ="result"> </a> (Muthu)
After completing a few number of quizzes, you would want to look at the results of the previous quizzes to see if
you have improved over the time. You can use the `results` functionality to check your results.
This can be done for the subjects or for the topics.

> 📝️ ️  **_NOTE:_** Ensure that at least one quiz has been attempted under the subject or topic.

> 📝️ ️  **_NOTE:_** The results for a subject is different from the results for a topic. The application only shows
>the result of a subject or topic that you requested for. Entering `results CS2113T` will not show you the results
>that you obtained for the topics in the CS2113T subject and vice-versa.  

#### 3.4.3 Storing data <a name="store"> </a> (Chin Hang)

When you exit the application, the data you have added to the application is automatically saved to the disk. 
The data is stored under the `data/` folder in the same folder where you run the application. 

> ⚠️ **_WARNING:_** The data (or any changes to the data) are not saved if the application is closed abnormally 
> (i.e. not via `bye` command).

Instead of storing all data in one file, **revisED** creates a folder hierarchy under `data/` following the logical structure of the
subjects and topics added. For example, if you add a `CS2113T` subject and a `Java` topic under it, a
`CS2113T/` folder will be created under the `data/` folder and an `Java` folder will be created under the `Maths/`
folder, as shown in figure 3.4.1 below. 

<pre>
revised.jar                     <em>--> <b>revisED</b> Application</em>
data                            <em>--> Data main folder</em>
└── CS2113T                     <em>--> CS2113T subject folder</em>
    ├── Java                    <em>--> Java topic folder under CS2113T subject</em>
    │   ├── topicResults.json
    │   └── flashcards.json
    ├── subjectResults.json
    └── tasks.txt
</pre>
<sup>***Figure 3.4.1** Sample directory structure created*</sup>


The details of the files that are created under each subject and topic folder will be explained below.

> 📝️️   **_NOTE:_**  The name of the subject and topic folders can be changed manually, and the changes will be reflected
> in the application the next time you launch it.

##### tasks.txt
One `tasks.txt` file will be created under each subject folder. This file contains the tasks (Todo, Event, Deadline) you
have added to a specific subject. An example of the file content is shown below.

<pre>
T | 1 | someTodoTask                                    <em>--> Todo task</em>
D | 0 | someDeadlineTask | 11:59 PM 20 Dec 2020         <em>--> Deadline task</em>
E | 0 | someEventTask | 1:00 PM 10 Nov 2020             <em>--> Event task</em>
</pre>
<sup>***Figure 3.4.2** Sample tasks.txt content*</sup>

The first column of data shows the type of task, where T corresponds to `Todo` task, D corresponds to `Deadline` 
task, and E corresponds to `Event` task. The second column shows if a task is completed, where 0 means not completed 
while 1 means completed. The third column shows the name of a task. Lastly, the fourth column shows the time and date of 
a deadline or event task. 

> ⚠️ **_WARNING:_** Although you can change the content of this file manually, 
> you are not advised to do so because a mismatch in the format will corrupt the data. 
> **If the data is corrupted, it will not be loaded by the application.**
> Make a copy of the file before making changes if you have to do so manually.

##### subjectResults.json / topicResults.json
One `subjectResults.json` will be created under each subject folder, and one `topicResults.json` will be created 
under each topic folder. `subjectResults.json` stores the quiz results you have obtained from doing quizzes under 
a subject, while `topicResults.json` stores the quiz results you have obtained from doing quizzes under a topic. 
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
<sup>***Figure 3.4.3** Sample subjectResults.json content*</sup>


##### flashcards.json
One `flashcards.json` file will be created under each topic folder. This file stores all the flashcard data you 
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
<sup>***Figure 3.4.4** Sample flashcard.json content*</sup>

Each entry enclosed with the curly braces ({}) corresponds to one flashcard.

> ⚠️ **_WARNING:_** Although you can change the content of this file manually, 
> a mismatch in the format will corrupt the data. **If the data is corrupted, it will not be loaded by the application.** 
> Therefore, make a copy of the file before making changes if you have to do so.

#### 3.4.4 Loading data <a name="load"> </a> (Chin Hang)
Similar to <a href =#store>storing data</a>,
when you launch the application, the saved data is automatically loaded from the disk.

> 📝️   **_NOTE:_** Loaded subjects and topics will be sorted in alphabetical order, which can be seen when running
> <a href=#list>list</a> command. Flashcards, tasks, and results data, on the other hand, are not sorted and instead
> follow the added order.

> ⚠️ **_WARNING:_** If you have manually modified the contents of the files stored with wrong syntax, the files affected 
> will not be loaded (and, instead, empty data will be loaded) when the application launches. 

#### 3.4.5 Exporting data <a name= "export"> </a> (Chin Hang)
You can export all the data, including the quiz results and tasks, to a `json` file so that it can be imported into
other applications that understand the data. To export the data, run the <a href="#export-command">export command</a> 
when you are in the main level of the application. 

After running the command, the data will be exported to 
`export/data.json` under the same folder where **revisED** application resides. An example of the file content is shown 
below.

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
<sup>***Figure 3.4.5** Sample data.json content*</sup>

Note that the content of the file follows the same logical structure as that
in the `data/` folder.

> 📝️ ️ **_NOTE:_** **Importing** of the exported file is **currently not supported** as it is meant to be read by other 
> applications. Nevertheless, the feature may be implemented in the future versions if it is highly requested.

#### 3.4.6 Sorting tasks  <a name= "sort"> </a> (Muthu)
The tasks in the application are sorted according to their deadlines. Tasks which are due soon are placed at the front
of the list, while tasks which are due later are placed at the rear. `Todo` tasks are placed at the end of the list since
they do not have any deadlines.

Suppose you use the `list` command and get this output:
<pre><code>
____________________________________________________________
Here are the tasks(s) under CS2113T: 
1. [D][✘] homework (by: 6:00 PM 6 Nov 2020)
2. [E][✘] marathon (at: 6:00 PM 9 Nov 2020)
____________________________________________________________
</code></pre>

Adding the following deadline by `deadline project /by 18:00 07-11-2020` and using the `list` command would yield this output.
<pre><code>
____________________________________________________________
Here are the tasks(s) under maths: 
1. [D][✘] homework (by: 6:00 PM 6 Nov 2020)
2. [D][✘] project (by: 6:00 PM 7 Nov 2020)
3. [E][✘] marathon (at: 6:00 PM 9 Nov 2020)
____________________________________________________________
</code></pre>


## 4. FAQ <a name = "faq"> </a>
This section answers some common questions that you may have about **revisED**.

**_Q:_ I moved the jar file to another location. Will my previous data be erased automatically?**<br>
**_A:_** Do not worry. You previous data will still be avaiable in the `data/` folder.

**_Q:_ I accidentally saved a task as an event, when it is a deadline. Is there any method to change the type of the task?**<br>
**_A:_** Unfortunately, no. You will have to delete the event using the `delete task [TASK_INDEX]`to delete the task, and then 
add the task back using the `deadline [DESCRIPTION] /by [TIME]` command.

**_Q:_ Isit possible to change the timing of the quizzes?**<br>
**_A:_** No. This feature is not available in the current implementation.

**_Q:_ Is there any limit to the number of topics/subjects that I can store in my application?**<br>
**_A:_** No. You can store unlimited number of topics/subjects.   

**_Q:_ A file named `revisED0.log` appears in the folder after I run the application. What is it? Can I delete it?**<br>
**_A:_** This is the log file that logs down the activities of the application when you are running it. In the case where
you find any bugs or the application crashes, you can send us a bug report
[here](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues) along with the log file to help us with solving the bugs. 
Nevertheless, you can delete it if you want to, and it does not affect the behavior of the application in any way.

## 5. Command Summary <a name="summary"></a> (Muthu)
A summary of all the commands available is shown in the table below.

Action|Examples
------|------
SUBJECT COMMANDS(MAIN LEVEL)|
Add subject|`add CS2113T` 
Change to the specific subject |`subject CS2113T`
Start a quiz for a subject| `quiz CS2113T`
View results for a subject | `results CS2113T`
Find Subject | `find CS2113T`
Delete Subject | `delete 1`
List all subjects| `list`
Export data | `export`
Exit application|`bye`
TOPIC AND TASK COMMANDS(SUBJECT LEVEL)| 
Add topic|`add Java` 
Delete topic| `delete topic 4`
Change to the specific topic |`topic Java`
Start a quiz for a topic| `quiz Java`
View results for a topic | `results Java`
Add todo|`todo revise for test` 
Add deadline| `deadline assignment /by 23:59 21-10-2020`
Add an event| `event tutorial /at 16:00 21-10-2020`
Find tasks| `find assignment`
Complete task|`done 4`
Delete task|`delete task 4`
List topics and tasks | `list`
Exit subject | `exit`
FLASHCARD COMMANDS(TOPIC LEVEL)|
Add flashcard|`add What version of Java are you using; 11.0`
Delete flashcard| `delete 1`
List flashcards|`list`
Exit topic | `exit`
OTHER COMMANDS(All LEVELS)|
List all the items in the application|`list all`
List all the commands|`help`
