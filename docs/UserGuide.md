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
* Adding subjects
* Adding topics
* Adding flashcards
* Adding tasks
* Taking quizzes 
* Viewing quiz results
* Storing data

### Adding subjects
### Adding topics
### Adding flashcards
### Adding tasks

#### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Taking quizzes
Prints the questions from the flashcards and requires the user to complete the quiz within a
certain time limit.There are two types of quizzes :
* SubjectQuiz
* TopicQuiz

#### Subject quiz
Prints out all the questions that have been saved in all of the topics in the subjects.The user is given
2 minutes to complete the quiz.

Format: `quiz NAMEOFSUBJECT`<br>
Example: `quiz Maths`

Output: `You are about to begin the quiz for maths.You have 2 minutes.`

#### Topic quiz
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

### Viewing quiz results
Shows the results that the user has obtained in all of the quizzes so far.This can be done for the subjects or for the
topics.

Format: `results NAMEOFTOPIC` or `results NAMEOFSUBJECT`
Example:`results maths` or `results speed`

<pre><code>
Quiz 1: 1/1 -- Excellent
Quiz 2: 0/1 -- Fail
</code></pre>

### Storing data 
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

#### tasks.txt
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

#### results.json
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

#### flashcards.json
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

### Exporting data
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

## FAQ

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