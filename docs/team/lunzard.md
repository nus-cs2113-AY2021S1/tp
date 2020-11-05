# YILUN NI - Project Portfolio Page for Quotesify

## Overview
I am part of a team of 5 software engineering students, and we were tasked to design and implement a
Command Line Interface (CLI) desktop application for our team project. The application that we developed
is called Quotesify. I was tasked with the Progress Tracker feature (Bookmark Management and Task Management) 
in Quotesify.

### About the Project
Quotesify is a free desktop application to help book readers improve their reading experience. With Quotesify,
users can add books and quotes they wished to remember into the application. Users can also categorize their books and
quotes, as well as rate their books. Quotesify is also built with a progress tracker to manage your reading activities.

## Summary of Contributions
This section shows a brief summary of my contributions to the team project, including coding, documentation
and other helpful contributions throughout the development of Quotesify.

### Code contributed
The link to the code contributed by me can be found
[here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=lunzard&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements Implemented
I implemented the Progress Tracker feature (Bookmark Management and Task Management) feature of Quotesify:
1. Bookmark Management
**What it does:**
* The user may use the `bookmark -b /pg` command to add bookmarks to existing books in the BookList of Quotesify.
* The user may also use the same `bookmark -b /pg`command to update existing bookmarks of Books 
in the BookList of Quotesify.
* The user may use the `delete -bm` command to remove existing bookmarks in the BookmarkList of Quotesify. 
* The user may use the `list -bm` command to list existing bookmarks in the BookmarkList.
**Justification:**
The Bookmark Management is designed to allow users to track their reading progress.
This feature is useful especially when user has many books to read in the same period as they can refer to the pages 
where they stopped before to continue reading. This makes their reading more time efficient.

**Highlights:**
The bookmarks will be automatically removed from the BookmarkList when its respective book object is removed 
from the BookList. This is to prevent non-pointer errors as the bookmarks will not be able to reference its book.

2. Task Management
**What it does:**
* The user may use the `add -t /by` command to add todos to the TodoList in Quotesify. They have the option to:
    * add todo without a deadline by using the simplified `add -t` command
    * add todo with a formatted deadline by using the `add -t /by` command with the deadline 
      following the format of `YYYY-MM-DD`.
    * add todo with an unformatted deadline by using the `add -t /by` command with the deadline not following
      any format.
* The user may use the `done -t` command to mark existing todos as done in the ToDoList of Quotesify. 
* The user may use the `delete -t` command to remove existing tasks in the ToDoList of Quotesify. 
* The user may use the `list -t` command to list existing todos in the ToDoList.

**Justification:**
The Task Management is designed to allow users to set deadlines for their reading activities.
This will help users to set reading goals or get reminded to return their borrowed books.

**Highlights:**
If users add todos with formatted deadlines, they can view the full list of todos in ascending of dates 
and notice the most urgent task quickly.
One thing to note, deadlines with incorrect formats or invalid dates will be accepted for users' freedom, but 
their task objects will be shown at the last part of the ToDoList.

### Contributions to documentation:
* I contributed to the parts of the UG that describe and explain the Bookmark Management and Task Management System. 
* I also maintained the cohesiveness and formatting of the documentation. 

### Contributions to the DG:
* I contributed to the parts of the DG that describe and explain the Bookmark Management and Task Management System, 
including the UML diagrams.
* I also maintained the cohesiveness and formatting of the documentation.

### Contributions to team-based tasks:
* PRs reviewed: #81, #170, #172, #178, #292
* I contributed brainstorming in User Stories which can be found [here](https://github.com/dozenmatter/tp-stories/projects/1)

### Contributions beyond the project team: 
* I tested applications and reported bugs and offered suggestions for other teams to consider 
  during the DG review and PE tests.