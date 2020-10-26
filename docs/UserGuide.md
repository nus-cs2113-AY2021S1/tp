# User Guide

## Table of Contents
<pre>
<a href =#intro>1 Introduction </a>
<a href =#start>2 Quick Start </a>
<a href =#init>3 Initialisation </a>
<a href =#features>4 Features </a>      
   <a href =#subjects>4.1 Subjects </a>
   <a href =#topics>4.2 Topics </a>
   <a href =#tasks>4.3 Tasks </a>
   <a href =#flashcards>4.4 Flashcards </a>
   <a href =#takeQuiz>4.5 Taking Quizzes </a>
   <a href =#result>4.6 Viewing quiz results </a>
   <a href =#store>4.7 Storing data </a>
   <a href =#export>4.8 Exporting data </a>
<a href =#faq>5 FAQ </a>
<a href =#summary>6 Command Summary </a>
</pre>
## 1 Introduction <a name="intro"></a>

**revisED** aims to help students to revise their subjects through the creation of flashcards or tasks that have
to be completed. The user can store the flashcards under different topics, which can be created under different
subjects. The tasks can be stored under subjects. This application ensures that students would be
able to revise, even if they are doing it at the last minute.

This user guide provides an in-depth documentation on the revisED installation process, the program features, and
the program usage to get you started.

Here is a guide on how to use this UserGuide effectively:<br>

1. Use the hyperlinks in the table of contents to navigate about the user guide quickly.
2. The code is always marked out like `this` to distinguish it from the rest of the text.
3. The :warning: symbol indicates things that you should avoid doing while using this application.Else, 
you will not get the expected outcome.
4. The :scroll: symbol indicates notes, any informational that is optional to the section.



## 2 Quick Start <a name="start"></a>

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `revisED` from [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).
3. Transfer the jar file to the folder that you prefer to use.
4. Open command prompt and use the command `java -jar revised.jar` to begin the application.


## 3 Initialisation <a name="init"> </a>

You will see the logo of the application, as shown below,  once u begin running it.
<pre><code>
Hello from
                                    ___________
                                    |  __ |  _ \
 ____  ______      _____   ________ |  |__| | | |
|  __|/ __ \ \    / /| |  /  _____/ |   __| | | |
| |  |  __/ \ \__/ / | | /_____  /  |  |__| |_| |
| |   \___|  \____/  |_|/_______/   |_____|_____/
</code></pre> 
Figure 1: **revisED** logo

After that, the application shows any tasks that are due within a week.
<pre><code>
Hello from
Here are the tasks that are due by next week

maths
1:[D][✘] homework (by: 6:00 PM 21 Oct 2020)
2:[E][✘] match (at: 6:00 PM 22 Oct 2020)
science
1:[E][✘] fund-raising (at: 6:00 PM 22 Oct 2020)
2:[D][✘] assignment (by: 6:00 PM 21 Oct 2020)
</code></pre> 

Subsequently,you can begin using the application.
## 4 Features <a name="features"></a>
* Subjects
* Topics
* Flashcards
* Tasks
* Quiz 
* Results
* Storing data

### 4.1 Subjects <a name="subjects"></a>
#### 4.1.1 `help` - Display all commands 

Displays all the available commands.

Format: `help`  
 
Example of usage:
```
help
```
Output:
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

#### 4.1.2 `add` - Add subject

Add a subject.

Format: `add` `[SUBJECT_NAME]`

Example of usage:
```
add CS2101
```
Output:
```
____________________________________________________________
Got it. I've added this subject:
  CS2101
Now you have 5 subjects in the list.
____________________________________________________________
```

#### 4.1.3 `list` - List all subjects

Prints a list of all subjects stored

Format: `list`

Example of usage:
```
list
```
Output:
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

#### 4.1.4 `delete` - Delete a subject

Deletes the specified subject.

Format: `delete` `[SUBJECT_NUMBER]`

Example of usage:
```
delete 1
```
Output:
```
____________________________________________________________
 Noted. I've removed this subject:
   CS1010
 Now you have 4 subjects in the list.
____________________________________________________________
```

#### 4.1.5 `find` Find subjects

Searches the list of subjects for all subjects that contains the query

Format: `find` `[QUERY]`

Example of usage:
```
find CS
```
Output:
```
____________________________________________________________
 Here are the matching subject(s) in your list:
CS2040C
CS2113T
CS2101
____________________________________________________________

```

####  4.1.6 `subject` - Enter a subject

Enters a pre-existing subject

Format: `subject` `[SUBJECT_NAME]`

Example of usage:
```
subject CS2113T
```
Output:
```
____________________________________________________________
You are currently looking at the subject: CS2113T
____________________________________________________________
```

### 4.2 Topics <a name = "topics"> </a>
Topic commands can only be used when looking at a subject.
#### 4.2.1 `help` - Display all commands

Displays all the available commands.

Format: `help` 
  
Example of usage:
```
help
```
Output:
```
________________________________________________________________________________________________________________________
help:                shows the list of commands available at the subject level
add abc:             adds a topic called 'abc' in the current subject
todo abc:            adds a todo type task with the description 'abc'
deadline abc /by 1:  adds a deadline type task with description 'abc' and date/time of deadline as 1
event abc /at 1:     adds an event type task with description 'abc' and date/time of event as 1
find abc:            finds all topics and tasks containing the letters abc in the current subject
list:                shows the list of all topics and tasks in the current subject
list all:            shows the tree of all subjects, topics, tasks and flashcards
delete topic 1:      deletes the 1st topic in the list of topics.
delete task 1:       deletes the 1st task in the list of tasks.
done 1:              marks the 1st task in the list of tasks as done
topic abc:           enters the topic called abc, now you can create, find, list and delete the flashcards of topic abc
quiz abc:            starts a quiz for all the flashcards of the topic abc,
                     answer the questions of the prompted flashcards to test your knowledge
results abc:         gives you the results of all attempted quizzes for abc topic
exit:                 exits the subject to return to the main screen, where you can work with subjects
________________________________________________________________________________________________________________________
```
#### 4.2.2 `add` - Add topic

Add a topic.

Format:

`add` `[TOPIC_NAME]`

Example of usage: 

`add speed`

Expected outcome:

```
____________________________________________________________
Got it. I've added this topic:
  speed
Now you have 1 topic in the list.
____________________________________________________________
```

#### 4.2.3 `delete topic` - Delete a topic

Deletes the specified topic.

Format:

`delete topic` `[TOPIC_NUMBER]`

Example of usage: 

`delete topic 1`

Expected outcome:

```
____________________________________________________________
 Noted. I've removed this topic:
   speed
 Now you have 0 tasks in the list.
____________________________________________________________
```

#### 4.2.4 `list` - List all topics and tasks

Prints a list of all topics and tasks of the subject you are currently viewing.
  
Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
Here are the topic(s) under math: 
1.speed
____________________________________________________________
Here are the tasks(s) under math: 
1.[T][✘] laundry
2.[E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
3.[D][✘] assignment (by: 11:59 PM 21 Oct 2020)
____________________________________________________________

```

#### 4.2.5 `find` - Find topics and tasks

Searches the current subject for all the topics and tasks that contains the query.

Format:

`find [QUERY]`

Example of usage: 

`find laund`

Expected outcome:

```
____________________________________________________________
 Sorry! I could not find any topics with laund in the list.

 Here are the matching task(s) in your list:
[T][✓] laundry
____________________________________________________________
```

#### 4.2.6 `topic` - Enter a topic

Enters a pre-existing topic.

Format: `topic` `[TOPIC_NAME]`

Example of usage:
```
topic speed
```
Output:
```
____________________________________________________________
You are currently looking at the topic: speed
____________________________________________________________
```

### 4.3 Tasks <a name="tasks"> </a>
Task commands can only be used when looking at a subject.

#### 4.3.1 `todo` - Add todo

Add a todo task.

Format:

`todo` `[DESCRIPTION]`

Example of usage: 

`todo laundry`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
	[T][✘] laundry
Now you have 4 tasks in the list.
____________________________________________________________
```

#### 4.3.2 `deadline` - Add deadline

Add a deadline task.

Format:

`deadline [DESCRIPTION] /by [TIME]`

> :warning: Add the time using the HH:MM DD-MM-YYYY format. 
>

Example of usage: 

`deadline assignment /by 23:59 21-10-2020`
Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [D][✘] assignment (by: 11:59 PM 21 Oct 2020)
Now you have 2 tasks in the list.
____________________________________________________________
```

#### 4.3.3 `event` - Add event

Add an event task.

Format:

`event [DESCRIPTION] /at [TIME]`

Example of usage: 

`event tutorial /at 16:00 21-10-2020`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### 4.3.4 `delete` - Delete Task

Deletes the specified task.

Format:

`delete task [TASK_INDEX]`

Example of usage: 

`delete task 2`

Expected outcome:

```
____________________________________________________________
 Noted. I've removed this task:
   [E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
 Now you have 2 tasks in the list.
____________________________________________________________
```

#### 4.3.5 `done` - Mark task as completed

Marks the specified task as done.

Format:

`done [TASK_INDEX]`

Example of usage: 

`done 2`

Expected outcome:

```
____________________________________________________________
 Nice! I've marked this task as done:
   [D][✓] assignment (by: 11:59 PM 21 Oct 2020)
____________________________________________________________
```

### 4.4 Flashcards <a name ="flashcards"> </a>

Flashcards can be added to take notes, in the form of questions and answers.
These flashcards can also be used to take quizzes, which is described in the next section.
This section describes the usage of commands that can be used to list all the available commands, 
and add, delete and list flashcards. 

NOTE: Flashcard commands can only be used when looking at a topic.

#### 4.4.1 `help` - Displaying all commands

The help command displays all the available commands so that you can refer to them whenever you get confused 
or need help to navigate the application.   

Format: `help`
   
Example of usage:
```
help
```
Expected Outcome:
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
WARNING: Do not add extra spaces after or before help.

#### 4.4.2 `add` - Adding a flashcard

The add command allows you to add a flashcard, under the topic you are currently viewing.
These flashcards can be added to take notes. 
They are added in the form of questions and answers.

Format:

`add` `[QUESTION]; [ANSWER]`

Example of usage: 
```
add How to calculate speed?; Speed=distance/time.
```

Expected outcome:

```
____________________________________________________________
Got it. I've added this flashcard:
  How to calculate speed?; Speed=distance/time.
Now you have 3 flashcard in the list.
____________________________________________________________
```
WARNING: Do not forget the semicolon, as it separates the question and the answer.

#### 4.4.3 `delete` - Deleting a flashcard

The delete command deletes the specified flashcard. 
This can be used if you make a mistake or no longer need a flashcard.
You can check the flashcard number from the list of flashcards, which is described next.

Format:

`delete` `[FLASHCARD NUMBER]`

Example of usage: 

`delete 1`

Expected outcome:

```
____________________________________________________________
 Noted. I've removed this flashcard:
   How to calculate speed?; Speed=distance/time.
 Now you have 2 flashcards in the list.
____________________________________________________________```
```
#### 4.4.4 `list` - List all flashcards

The list command prints a list of all flashcards of the topic you are currently viewing. 
This will help you to keep track of all the flashcards you have added 
and to take note of the number of the flashcard you might want to delete.
  
Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
Here are the flashcard(s) under speed: 
1.What is the formula for time?; Time = Distance/Speed.
2.What is the difference between speed and velocity? Speed, being a scalar quantity, is the rate at which an object covers distance. On the other hand, velocity being a vector quantity, is the rate at which the position changes.
```
WARNING: Do not add extra spaces after or before list.

### 4.5 Taking quizzes <a name = "takeQuiz"> </a>
Once you have added the necessary flashcards, you can use the Quiz functionality to test yourself. This would
let you gauge how much you understand from the material. This will be beneficial if you are running out of time
to revise your own notes.
 
In this feature, the application prints the questions from the flashcards stored in the application. You need to complete the quiz within a
certain time limit. There are two types of quizzes :
* SubjectQuiz
* TopicQuiz

#### 4.5.1 Subject quiz
This type of quiz helps you prepare for a particular subject.The application prints out the questions from the topics present in a subject.
You have 2 minutes to complete the quiz.

Format: `quiz NAMEOFSUBJECT`<br>
Example: `quiz Maths`

Output: `You are about to begin the quiz for maths.You have 2 minutes.`

#### 4.5.2 Topic quiz
This type of quiz helps you to prepare for a particular topic. The application prints out all the questions that have been saved in the specific topic .
You have 1 minute to complete the quiz.

Format: `quiz NAMEOFTOPIC`<br>
Example: `quiz speed`

Output: `You are about to begin the quiz for speed.You have 1 minute.`

> :scroll: Ensure that you start a quiz for a subject/topic that has been added to the application 
> and has a flashcard. Else, the application will not start the quiz.

Once you have entered the type of quiz you want to begin, the application starts the quiz. You
are expected to answer these questions.

Format: `Question NAMEOFQUESTION`<br>
Example: `Question: What is distance` <br>

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
           
Question:distance 
Correct Answer: speed x time 
Your Answer: speed x time x time
____________________________________________________________
Question:velocity 
Correct Answer: displacement / time
Your Answer: none
____________________________________________________________</code></pre>

You can stop the quiz by entering `stop`. The application then prints the score.<br>
Format: `stop`<br>
Output:`The quiz has been stopped!`.
<pre><code>
____________________________________________________________
The quiz has been stopped!
____________________________________________________________
Result:1/2 -- Pass
____________________________________________________________
</code></pre>

### 4.6 Viewing quiz results <a name ="result"> </a>
After completing a few number of quizzes, you would want to look at the results of the previous quizzes to see if
you have improved over the time. You can use the `results` functionality to check your results.
This can be done for the subjects or for the topics.

Format: `results NAMEOFTOPIC` or `results NAMEOFSUBJECT`
Example:`results maths` or `results speed`

<pre><code>
Quiz 1: 1/1 -- Excellent
Quiz 2: 0/1 -- Fail
</code></pre>
> :scroll: Ensure that at least one quiz has been attempted under the subject or topic.

### 4.7 Storing data <a name="store"> </a>
When you exit the application, the data you have added to the program is automatically saved to the disk. Similarly, 
when you launch the application, the saved data is automatically loaded from the disk. The data is stored under the 
`data/` folder in the same folder where you run the application.

> **_WARNING:_** The data is not saved if the application is closed abnormally.

Instead of storing all data in one file, **revisED** creates a folder hierarchy under `data/` following the logical structure of the
subjects and topics added. For example, if you add a `Maths` subject and a `Algebra` topic under it, a
`Maths/` folder will be created under the `data/` folder and an `Algebra` folder will be created under the `Maths/`
folder, as shown in the figure below.

```
data
└── Maths
    ├── Algebra
    │   ├── results.json
    │   └── flashcards.json
    ├── results.json
    └── tasks.txt

```

The details of the files that are created under each subject and topic folder will be explained below.

> **_NOTE:_**  The name of the subject and topic folders can be changed manually, and the changes will be reflected
> the next time you launch the application.

#### 4.7.1 tasks.txt
One `tasks.txt` file will be created under each subject folder. This file contains the tasks (Todo, Event, Deadline) you
have added to a specific subject. An example of the file content is shown below.

```
T | 1 | someTodoTask
D | 0 | someDeadlineTask | 11:59 PM 20 Dec 2020
E | 0 | someEventTask | 1:00 PM 10 Nov 2020
```

The first column of data shows the type of task, where T corresponds to Todo task, D corresponds to Deadline task, and
E corresponds to Event task. The second column shows if a task is completed, where 0 means not completed while
1 means completed. The third column shows the name of a task. Lastly, the fourth column shows the time and date of a 
deadline or event task. 

> **_WARNING:_** Although you can change the content of this file manually, and the changes will be reflected the 
> next time you launch the application, you are not advised to do so because a mismatch in format will corrupt the data. 
> Make a copy of the file before making changes if you have to do so manually.

#### 4.7.2 results.json
You can find this file under each subject and topic folder. It stores the quiz results you have obtained from attempting
the quiz under a specific subject or topic. An example of the file content is shown below.

```
[
  {
    "score": 1.0,
    "maxScore": 1.0,
    "description": "Excellent"
  },
  ...
]
```

Each entry enclosed with the curly braces ({}) corresponds to one quiz result.

#### 4.7.3 flashcards.json
One `flashcards.json` file will be created under each topic folder. This file stores all the flashcard data you 
have added under a specific subject and topic. An example of the file content is shown below.

```
[
  {
    "question": "x + y = 4. y = ? ",
    "answer": "4 - x"
  },
  ...
]
```

Each entry enclosed with the curly braces ({}) corresponds to one flashcard.

> **_WARNING:_** Although you can change the content of this file manually, and the changes will be reflected the 
> next time you launch the application, a mismatch in format will corrupt the data. Therefore, make a copy of the file 
> before making changes if you have to do so.

### 4.8 Exporting data <a name= "export"> </a>
You can export all the data, including the quiz results and tasks, to a `json` file so that it can be imported into
other applications that understand the data. To export the data, run this command when you are in the subject level of 
the application:

```
export
```

After running the command, the data will be exported to 
`export/data.json` under the same folder where the application resides. An example of the file content is shown below.

```
[
  {
    "title": "maths",
    "topics": {
      "topics": [
        {
          "title": "algebra",
          "flashcards": <same as the content of flashcards.json>,
          "results": {
            "resultList": <same as the content of results.json>
          }
        },
        ...
      ]
    },
    "tasks": {
      "taskList": [
        {
          "description": "someTodoTask",
          "isDone": false
        },
        {
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
      "resultList": <same as the content of results.json>
    }
  },
  ...
]
```

Each first-level entry corresponds to one subject, and the content inside it follows the same logical structure as that
in the `data/` folder.

## 5 FAQ <a name = "faq"> </a>
Commonly asked questions from users.

Question: I moved the jar file to another location. Will my previous data be erased automatically?<br>
Answer: Do not worry. You previous data will still be avaiable in the `data/` folder.

Question: I accidentally saved a task as an event, when it is a deadline. Is there any method to change the type of the task?<br>
Answer: Unfortunately, no. You will have to delete the event using the `delete task [TASK_INDEX]`to delete the task, and then 
add the task back using the `deadline [DESCRIPTION] /by [TIME]` command.

Question: I stopped a quiz without completing it. Can I resume it?<br>
Answer: No. You will have to start a new quiz.

Question: Is there any limit to the number of topics/subjects that I can store in my application?<br>
Answer: No. You can store unlimited number of topics/subjects.   

## 6 Command Summary <a name="summary"></a>
Action|Examples
------|------
Add subject|`add math` (while in main menu)
Add topic|`add speed` (while looking at a subject)
Add todo|`todo revise for test` 
Add deadline| `deadline assignment /by 23:59 21-10-2020`
Add an event| `event tutorial /at 16:00 21-10-2020`
Find tasks| `find math`
Complete task|`done 4`
Delete task|`delete task 4`
Delete topic| `delete topic 4`
Change to the specific subject |`subject maths`
Change to the specific topic |`topic speed `
Start a quiz for a subject| `quiz maths`
Start a quiz for a topic| `quiz speed`
View results for a subject | `results maths`
View results for a topic | `results speed`
Export data | `export`
Exit a topic or subject | `exit`
Exit application|`bye`