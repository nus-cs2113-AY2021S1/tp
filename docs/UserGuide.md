# User Guide

## Table of Contents
<pre>
<a href =#intro>1. Introduction </a>
<a href =#start>2. Quick Start </a>
<a href =#init>3. Initialisation </a>
<a href =#features>4. Features </a>      
   <a href =#subjects>4.1 Subjects </a>
   <a href =#topics>4.2 Topics </a>
   <a href =#tasks>4.3 Tasks </a>
   <a href =#flashcards>4.4 Flashcards </a>
   <a href =#takeQuiz>4.5 Taking Quizzes </a>
   <a href =#result>4.6. Viewing quiz results </a>
   <a href =#store>4.7. Storing data </a>
   <a href =#export>4.8. Exporting data </a>
<a href =#faq>5. FAQ </a>
<a href =#summary>6. Command Summary </a>
</pre>
## 1. Introduction <a name="intro"></a>

revisED aims to help students to revise their subjects through the creation of flashcards or tasks that have
to be completed. The user can store the flashcards under different topics, which can be created under different
subjects. The tasks can be stored under subjects. This application ensures that students would be
able to revise, even if they are doing it at the last minute.

This user guide provides an in-depth documentation on the revisED installation process, the program features, and
the program usage to get you started.


## 2. Quick Start <a name="start"></a>

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `revisED` from [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).

3. Transfer the jar file to the folder that you prefer to use.
4. Open command prompt and use the command `java -jar revised.jar` to begin the application.
* If you see these symbols in the application `[?]`, unicode is not enable in your operating system. Do the
following steps:
    * Go to Properties >> Font >> Font and change the font of the command prompt to NSimsum.


## 3. Initialisation <a name="init"> </a>

The user would see the logo of the application once it is opened.
<pre><code>
Hello from
                               __________
                              |  __ |  _ \
 ____  ______      _____      |  |__| | | |
|  __|/ __ \ \    / /| | ____ |   __| | | |
| |  |  __/ \ \__/ / | | \____|  |__| |_| |
| |   \___|  \____/  |_| ____/|_____|_____/
</code></pre> 

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

Subsequently, users can begin using the application.
## 4. Features <a name="features"></a>
* Subjects
* Topics
* Flashcards
* Tasks
* Quiz 
* Results
* Storing data

### 4.1. Subjects <a name="subjects"></a>
#### 4.11. `help` - Display all commands 

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
delete 1:      deletes the 1st subject in the list.
subject abc:   enters the subject called abc, now you can create, find, list, delete and enter the topics of subject abc
quiz abc:      starts a quiz for all the flashcards present in all the topics of subject abc,
               answer the questions of the current flashcards to test your knowledge
results abc:   gives you the results of all attempted quizzes for abc subject
bye:           exits the application
________________________________________________________________________________________________________________________
```

#### 4.12. `add` - Add subject

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

#### 4.13. `list` - List all subjects

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

#### 4.14. `delete` - Delete a subject

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

#### 4.15. `find` Find subjects

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

####  4.16. `subject` - Enter a subject

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

### 4.2. Topics <a name = "topics"> </a>
Topic commands can only be used when looking at a subject.
#### 4.21. `help` - Display all commands

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
delete topic 1:      deletes the 1st topic in the list of topics.
delete task 1:       deletes the 1st task in the list of tasks.
done 1:              marks the 1st task in the list of tasks as done
topic abc:           enters the topic called abc, now you can create, find, list and delete the flashcards of topic abc
quiz abc:            starts a quiz for all the flashcards of the topic abc,
                     answer the questions of the prompted flashcards to test your knowledge
results abc:         gives you the results of all attempted quizzes for abc topic
bye:                 exits the subject to return to the main screen, where you can work with subjects
________________________________________________________________________________________________________________________
```
#### 4.22. `add` - Add topic

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

#### 4.23. `delete topic` - Delete a topic

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

#### 4.24. `list` - List all topics and tasks

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

#### 4.25. `find` - Find topics and tasks

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

#### 4.26. `topic` - Enter a topic

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

### 4.3. Tasks <a name="tasks"> </a>
Task commands can only be used when looking at a subject.

#### 4.31. `todo` - Add todo

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

#### 4.32. `deadline` - Add deadline

Add a deadline task.

Format:

`deadline [DESCRIPTION] /by [TIME]`

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

#### 4.33. `event` - Add event

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

#### 4.34. `delete` - Delete Task

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

#### 4.35. `done` - Mark task as completed

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

### 4.4. Flashcards <a name ="flashcards> </a>
Flashcard commands can only be used when looking at a topic.
#### 4.41. `help` - Display all commands

Displays all the available commands.

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
delete 1:          deletes the 1st flashcard in the list
exit:              exits the topic to return to the subject level, where you can work with tasks and topics
________________________________________________________________________________________________________________________
```
#### 4.42. `add` - Add flashcard

Add a topic.

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

#### 4.43. `delete` - Delete a flashcard

Deletes the specified flashcard.

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
#### 4.44. `list` - List all flashcards

Prints a list of all flashcards of the topic you are currently viewing.
  
Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
Here are the flashcard(s) under speed: 
1.What is the formula for time?; Time = Distance/Speed.
2.What is the difference between speed and velocity? Speed, being a scalar quantity, is the rate at which an object covers distance. On the other hand, velocity being a vector quantity, is the rate at which the position changes.
```

### 4.5. Taking quizzes <a name = "takeQuiz"> </a>
Prints the questions from the flashcards and requires the user to complete the quiz within a
certain time limit.There are two types of quizzes :
* SubjectQuiz
* TopicQuiz

#### 4.51. Subject quiz
Prints out all the questions that have been saved in all of the topics in the subjects.The user is given
2 minutes to complete the quiz.

Format: `quiz NAMEOFSUBJECT`<br>
Example: `quiz Maths`

Output: `You are about to begin the quiz for maths.You have 2 minutes.`

#### 4.52. Topic quiz
Prints out all the questions that have been saved in the specific topic topics .The user is given
1 minute to complete the quiz.

Format: `quiz NAMEOFTOPIC`<br>
Example: `quiz speed`

Output: `You are about to begin the quiz for speed.You have 1 minute.`

Subsequently, the application prints out the questions from the flashcards.The user has to enter
the answer.</br>

Format: `Question NAMEOFQUESTION`<br>
Example: `Question: What is distance` <br>

Once the quiz has been completed, the application prints out the scores obtained by the user and
the questions that the user did not answer incorrectly. 

The score is printed in the following formats:<br>
* If the user gets full marks : `Result:4/4 -- Excellent`<br>
* If the user gets enough marks to pass: `Result:2/4 -- Pass`<br>
* If the user fails: `Result:0/4 -- Fail`<br>

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

The user can stop the quiz by entering `stop`. The application then prints the score for the user.<br>
Format: `stop`<br>
Output:`The quiz has been stopped!`.
<pre><code>
____________________________________________________________
The quiz has been stopped!
____________________________________________________________
Result:1/2 -- Pass
____________________________________________________________
</code></pre>

### 4.6. Viewing quiz results <a name ="result"> </a>
Shows the results that the user has obtained in all of the quizzes so far.This can be done for the subjects or for the
topics.

Format: `results NAMEOFTOPIC` or `results NAMEOFSUBJECT`
Example:`results maths` or `results speed`

<pre><code>
Quiz 1: 1/1 -- Excellent
Quiz 2: 0/1 -- Fail
</code></pre>

### 4.7. Storing data <a name="store"> </a>
The data added to the program is automatically saved to the disk when the application exits. Similarly, the application
loads the saved data from the disk automatically when it launches. The data is stored under the `data/` folder in the 
same folder where the application runs.

Instead of storing all data in one file, revisED creates a folder hierarchy under `data/` following the logical structure of the
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

#### 4.71. tasks.txt
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

#### 4.72. results.json
This file can be found under each subject and topic folder. It stores the quiz results you have obtained from attempting
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

#### 4.73. flashcards.json
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

### 4.8. Exporting data <a name= "export"> </a>
You can export all the data, including the quiz results and tasks, to a `json` file so that it can be imported into
other applications that understand the data. To export the data, run the command:

```
export
```

when you are in the subject level of the application. After running the command, the data will be exported to 
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

## 5. FAQ <a name = "faq"> </a>

## 6. Command Summary <a name="summary"></a>
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