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
1. Download the latest version of `revisED` from [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).

## Features 
* Subjects
* Topics
* Flashcards
* Tasks
* Quiz 
* Results
* Load 

### Subjects
### Topics
### Flashcards
### Tasks

#### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

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
Add todo|`todo assignment` 
Add deadline|
Add an event|
Find tasks|
Complete task|`done 4`
Delete task|`delete 4`
Change to the specific subject |`subject maths`
Change to the specific topic |`topic speed `
Start a quiz for a subject| `quiz maths`
Start a quiz for a topic| `quiz speed`
View results for a subject | `results maths`
View results for a topic | `results speed`
Export data | `export`
Exit application|`bye`