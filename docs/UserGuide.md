# User Guide

## Table of Contents

## 1. Introduction

revisED aims to help students to revise their subjects through the creation of flashcards or tasks that have
to be completed. The user can store the flashcards under different topics, which can be created under different
subjects. The tasks can be stored under subjects. This application ensures that students would be
able to revise, even if they are doing it at the last minute.

This user guide provides an in-depth documentation on the revisED installation process, the program features, and
the program usage to get you started.

### 1.1 Subject

TODO: explains terminology 

### 1.2 Topic

TODO: ...


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `revisED` from [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).

3. Transfer the jar file to the folder that you prefer to use.
4. Open command prompt and use the command `java -jar revised.jar` to begin the application.
* If you see these symbols in the application `[?]`, unicode is not enable in your operating system. Do the
following steps:
    * Go to Properties >> Font >> Font and change the font of the command prompt to NSimsum.


## Initialisation

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
1:[D][?] homework (by: 6:00 PM 21 Oct 2020)
2:[E][?] match (at: 6:00 PM 22 Oct 2020)
science
1:[E][?] fund-raising (at: 6:00 PM 22 Oct 2020)
2:[D][?] assignment (by: 6:00 PM 21 Oct 2020)
</code></pre> 

Subsequently, users can begin using the application.
## Features 
* Subjects
* Topics
* Flashcards
* Tasks
* Quiz 
* Results
* Load 

### Subjects

#### Adding a subject: `add`

Format: `add SUBJECT_NAME`

Example of usage:
```
add CS2113T
add CS2101
```
Output:
```
____________________________________________________________
Got it. I've added this subject:
  CS2113T
Now you have 4 subjects in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this subject:
  CS2101
Now you have 5 subjects in the list.
____________________________________________________________
```

#### Listing subjects: `list`

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

#### Deleting a subject: `delete`

Format: `delete SUBJECT_INDEX`

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

#### finding a subject: `find`

Format: `find KEYWORD`

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

####  Entering a subject: `subject`

Format: `subject SUBJECT_NAME`

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

### Topics
Topic commands can only be used when looking at a subject.

#### `add` - Add topic

Add a topic.

Format:

`add` `[DESCRIPTION]`

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

#### `delete topic` - Delete a topic

Deletes the specified topic.

Format:

`delete topic` `[TOPIC NUMBER]`

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

#### `list` - List all topics and tasks

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
1.[T][?] laundry
2.[E][?] tutorial (at: 4:00 PM 21 Oct 2020)
3.[D][?] assignment (by: 11:59 PM 21 Oct 2020)
____________________________________________________________

```

#### `find` - Find topics and tasks

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
[T][?] laundry
____________________________________________________________
```

#### `topic` - Enter a topic

Enters a preexisting topic.

Format: `topic` `[TOPIC NAME]`

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

### Tasks
Task commands can only be used when looking at a subject.

#### `todo` - Add todo

Add a todo task.

Format:

`todo` `[DESCRIPTION]`

Example of usage: 

`todo laundry`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
	[T][N] laundry
Now you have 4 tasks in the list.
____________________________________________________________
```

#### `deadline` - Add deadline

Add a deadline task.

Format:

`deadline [DESCRIPTION] /by [TIME]`

Example of usage: 

`deadline assignment /by 23:59 21-10-2020`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [D][?] assignment (by: 11:59 PM 21 Oct 2020)
Now you have 2 tasks in the list.
____________________________________________________________
```

#### `event` - Add event

Add an event task.

Format:

`event [DESCRIPTION] /at [TIME]`

Example of usage: 

`event tutorial /at 16:00 21-10-2020`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [E][?] tutorial (at: 4:00 PM 21 Oct 2020)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### `delete` - Delete Task

Deletes the specified task.

Format:

`delete task [TASK NO.]`

Example of usage: 

`delete task 2`

Expected outcome:

```
____________________________________________________________
 Noted. I've removed this task:
   [E][?] tutorial (at: 4:00 PM 21 Oct 2020)
 Now you have 2 tasks in the list.
____________________________________________________________
```

#### `done` - Mark task as completed

Marks the specified task as done.

Format:

`done [TASK NO.]`

Example of usage: 

`done 2`

Expected outcome:

```
____________________________________________________________
 Nice! I've marked this task as done:
   [D][?] assignment (by: 11:59 PM 21 Oct 2020)
____________________________________________________________
```

### Flashcards

### Quiz
Prints the questions from the flashcards and requires the user to complete the quiz within a
certain time limit.There are two types of quizzes :
* SubjectQuiz
* TopicQuiz

#### SubjectQuiz
Prints out all the questions that have been saved in all of the topics in the subjects.The user is given
2 minutes to complete the quiz.

Format: `quiz NAMEOFSUBJECT`<br>
Example: `quiz Maths`

Output: `You are about to begin the quiz for maths.You have 2 minutes.`

#### TopicQuiz
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

### Results
Shows the results that the user has obtained in all of the quizzes so far.This can be done for the subjects or for the
topics.

Format: `results NAMEOFTOPIC` or `results NAMEOFSUBJECT`
Example:`results maths` or `results speed`

<pre><code>
Quiz 1: 1/1 -- Excellent
Quiz 2: 0/1 -- Fail
</code></pre>

### Storing of data 
The data added to the program is automatically saved to the disk when the application exits. Similarly, the application
loads the saved data from the disk automatically when it launches. The data is stored under the `data/` folder in the 
same folder where the application resides.

TODO: add hierarchy diagram

Instead of storing all data in one file, revisED creates a folder hierarchy following the logical structure of the
subjects and topics added. For example, if you add a `Maths` subject and a `Algebra` topic under it, a
`Maths/` folder will be created under the `data/` folder and an `Algebra` folder will be created under the `Maths/`
folder.

#### tasks.txt
There will be one `tasks.txt` file under each subject folder. This file contains the tasks (Todo, Event, Deadline) you
have added to the specific subject.

TODO: explain the format

#### results.json
This file can be found under each subject and topic folder. It stores the quiz results you have obtained from attempting
the quiz under a specific subject or topic.

TODO: explain the format

#### flashcards.json
This file stores all the flashcard data you have added under a specific subject and topic. It can be found under
each topic folder.

TODO: explain the format


### Exporting data
You can export all the data including the quiz results and tasks to a `json` file so that it can be imported into
other applications that understand the data.

Command: `export`

After running the command, the data will be exported to `export/data.json` under the same folder where the
application resides.

TODO: explain the format

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary <a name="summary"></a>
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
Exit application|`bye`