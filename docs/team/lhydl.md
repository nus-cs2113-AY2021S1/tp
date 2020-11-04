# Lee Hao Yuan - Project Portfolio Page

## Overview
_ModTracker_ is a desktop app for NUS students to track the time spent 
as well as tasks to do for each of their modules.
It helps students to prioritise their work and 
balance their time spent amongst their modules. 
This app uses a Command Line Interface (CLI).


### Summary of Contributions
This section details my contribution in this project. The detailed contributions can be found in subsequent sections.

#### Code Contributed
All the code contributed by me in this project can be found [here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=lhydl&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

#### Enhancements Implemented
I implemented the main ModTracker class that acts as an entry point to this program, as well as the Parser and Ui classes.
The Parser class parses and handles user input and calls the correct methods to perform the necessary logics.
The Ui class handles the user interface of the program. I implemented part of the user interface of this program, which includes 
creating the welcome screen logo as well as the exit screen.

As for the feature enhancements, I implemented a feature where users can add tasks under an existing module, as well as 
managing the tasks such as marking a task as done or deleting a task. The user are also able to see the tasks linked to a 
specific module with the list task feature. Next, the add task feature will check if the module code 
entered by the user is valid or pre-exist in the module list by calling relevant methods created by my teammates in the
ModuleList class. All these implementations are handled in the Task and TaskList classes.

#### Contributions to User Guide
I created the general structure and format of the user guide. As for the program features, I added the details
of the _help, addtask, listtask, done, deletetask_ and _exit_ features (Section 2.1, 2.12-1.15, and 2.10) in the user guide.

#### Contributions to the Developer Guide
I contributed to the _setting up and getting started_ (Section 2) part of the developer guide. As for personal implementations, I 
added the implementations of the _add task_ (Section 4.7) and _mark task as done_ (Section 4.8) features. I added two sequence
diagrams under these implementations. 

#### Contributions to Team-based Tasks
I volunteered myself to do some team-based tasks such as releasing the jar file and doing some submissions.
Moreover, I also maintained the milestones in our repo, as well as updating both the user and developer guides that are 
not specific to my features and enhancements.


#### Contributions Beyond the Project Team
I gave constructive reviews to the pull request of other teams. The reviews include feedback on both their documentations 
and codes. Moreover, I also reported bugs on the program of the other teams that I reviewed.


[Home Page](https://ay2021s1-cs2113t-f12-4.github.io/tp/)