# Chan Wei Ling - Project Portfolio Page


## Overview
My team of 4 software engineering students and I have created a command line interface project managing 
software for our CS2113T team project. The app, called SCRUMptious, aims to help project managers handle 
their project teams efficiently with the help of the SCRUM methodology and digital technologies.

### Summary of Contributions
This section summarises my contributions in terms of documentation, coding and other significant matters to the team project.

#### Enhancements Implemented
##### 1. Sprint Management
I have implemented a set of commands to facilitate the management of Sprints (Iterations broken down from a project), which covers 
functionalities such as Add/Edit Sprints, Add/Remove tasks to Sprints, Allocate/Deallocate tasks to members. I have also 
added support to execute these commands across multiple projects/sprints, in accordance to our teams version 2.0 update for
multiple project management.


**Pull Request:** 
    [#33](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/33) 
    [#67](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/67) 
    [#71](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/71)
    [#91](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/91)
    [#112](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/112)
    [#123](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/123) 
 
##### 2. Parsers
In regard to sprint creations, a date object is required to facilitate the duration for each Sprint. Thus, I have implemented
a DateTime parser that will parse a user input (String) to the required format (DateTime/LocalDate). The parser will 
pint custom error for invalid dates in correct format. (E.g. 20200231) to better inform user about the incorrect input.
In addition, the parser also includes a method that will calculate the difference between two LocalDate object.


A SprintParser (extended Parser) is also added to facilitate parsing and validation of user specified parameters for all Sprint related commands.

**Pull Request:** 
    [#123](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/123) 
    [#193](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/193) 
    [#221](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/221) 

##### 3. Logger
I have also updated the test logger which previous I added for v1.0 into ScrumLogger which can be used to facilitate all
logging activities for the whole program. 


**Pull Request:** 
    [#87](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/87) 
    [#193](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/193) 
    [#226](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/226)
##### 4. Ui
At the initial phase of the project, I was in charged of break down the project into major components (Main, Ui, Parser, Command and Model).
I was therefore assigned to work on the Ui component to add the basic skeleton code to receive input/deliver output to users.
In addition, I was in charged of designing the output (toString()) of all the model classes to facilitate the printing of command output. 


**Pull Request:** 
    [#60](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/60) 
    [#71](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/71)
    [#123](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/123)
    [#224](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/224)

##### 1. Other minor enhancements
1. Standardise the program workflow to integrate all components (Ui, Parser, Commands, Model) 
[#39](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/39) 
[#60](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/60) 
1. Associate model classes together 
[#60](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/60)
[#112](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/112)
1. JUnit Test scripts for SprintCommands 
[#71](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/71) 
[#193](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/193) 
1. Segregate validation tasks for Parser and Command class.
[#123](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/123) 
1. Bug fix to prepare for PE dry run.
[#135](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/135) 

#### Documentation
##### User Guide 
  * Add detailed guide for all Sprint Commands 
  * Add navigability by including hyperlinks for _**Table of Contents**_.
  * Add _**Command Summary**_.
  * Standardise format for command description.
  * Standardise formats for user guide (Bulletin, numbering and _**Table of Contents**_)
  * Update guide for all Sprint Commands V2.0
  * Update _**4. Command Summary**_.
  
 Pull Request: [#80](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/80) [#130](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/130) [#132](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/132))
##### Developer Guide 
* Model component  
  * Updated model component section under Architecture
* Sprint related implementation  
    * Added description for all Sprint Commands
(Pull Request: [#129](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/129))
#### Other Contributions
* Reviewed bugs for other team's developer guide. [[CS2113T-F14-4] NUSchedule](https://github.com/nus-cs2113-AY2021S1/tp/pull/131)  
* Reported bugs for other team's program during PE dry run. [[CS2113-T13-3] WhereGotTime' bugs](https://github.com/chocomango/ped/issues)  
    
**Code contributed**: [Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chocomango&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)  