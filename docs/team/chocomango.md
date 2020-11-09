# Chan Wei Ling - Project Portfolio Page


## Overview
My team of 4 software engineering students and I have created a command line interface project managing 
software for our CS2113T team project. The app, called SCRUMptious, aims to help project managers handle 
their project teams efficiently with the help of the SCRUM methodology and digital technologies.

### Summary of Contributions
This section summarises my contributions in terms of documentation, coding and other significant matters to the team project.

#### Enhancements Implemented
##### 1. Sprint Management
* For v1.0 SCRUMptious
    * Add support to create sprint iteration in project.
    * Add support to add tasks from backlog to sprint.
    * Add support to remove task from sprints.
    * Add support to allocate multiple tasks to multiple users.
    
    **Pull Request:** [#33](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/33) 
    [#67](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/67) 
    [#71](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/71)
    [#91](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/91)

* For v2.0 SCRUMptious
    * Add support to edit sprint.
    * Add support to deallocate task from users.
    * Update all Sprint Commands to work on multiple projects/sprints.
    
    **Pull Request:** 
    [#112](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/112)
    [#123](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/123) 
##### 2. Parser
* Implement DateTimeParser to facilitate the parsing of String to LocalDate object.
    * Add support to calculate the difference between two LocalDate object
    * Add support to print custom error for invalid dates in correct format. (E.g. 20200231)
* Implement SprintParser(extended Parser) to facilitate validation of user specified parameters.
    
    **Pull Request:** 
    [#123](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/123) 
    [#193](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/193) 
    [#221](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/221) 
##### 3. Logger
* Add a test logger for v1.0.
* Implement ScrumLogger to facilitate all logging.
    * Add support to generate log in different platform.
    
    **Pull Request:** 
    [#87](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/87) 
    [#193](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/193) 
    [#226](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/226)
##### 4. Ui
* Implement the Ui component for v1.0
    * Add support to handle interruption (CTRL+C) from user
* Design toString method for all model classes to facilitate printing of information 

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
* For v1.0 User Guide (Pull Request: [#80](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/80))
  * Add detailed guide for all Sprint Commands 
  * Add navigability by including hyperlinks for _**Table of Contents**_.
  * Add _**Command Summary**_.
  * Standardise format for command description.
  
* For v2.0 User Guide (Pull Request: [#130](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/130) [#132](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/132))
  * Standardise formats for user guide (Bulletin, numbering and _**Table of Contents**_)
  * Update guide for all Sprint Commands V2.0
  * Update _**4. Command Summary**_.
  
##### Developer Guide (Pull Request: [#129](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/129))
* Model component  
  * Updated model component section under Architecture
* Sprint related implementation  
    * Added description for all Sprint Commands
    
#### Other Contributions
* Reviewed bugs for other team's developer guide. [[CS2113T-F14-4] NUSchedule](https://github.com/nus-cs2113-AY2021S1/tp/pull/131)  
* Reported bugs for other team's program during PE dry run. [[CS2113-T13-3] WhereGotTime' bugs](https://github.com/chocomango/ped/issues)  
    
**Code contributed**: [Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chocomango&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)  