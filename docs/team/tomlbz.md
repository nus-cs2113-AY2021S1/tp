# Li Bozhao (@TomLBZ) - PPP
## 1. Overview
### 1.1 Project Overview 
DOMNUS - is a desktop application that helps in module and task tracking. It was developed under the module CS2113 Software Engineering & Object Oriented Programming. Within the schedule of approximately 7 weeks, my team of 5 Computer Engineering students successfully created DOMNUS to help students to stay organize with respective to the modules they take in NUS. DOMNUS is written in Java and designed to run on Java11 JVM, which makes it cross-platform. It also has two different UI modes to cater to different user needs.

This is what out product looks like: <br>

Plain Mode UI | Fancy Mode UI
--- | ---
![plain](../Images/textui.png)|![ui](../Images/newUI.png)

## 2. Summary of Contributions
Given below are my contributions to the project. 

### 2.1 Enhancements Implemented: 

New Features in ver 2.1: 

Name | What it does | Justification | Highlights
--- | --- | --- | --- 
`edit`|Edit the fields of a Module or Task|The user may want to change the description, type or state of a task after its creation, or modify the grade or linked tasks of a module manually|Supports converting Todos to Events or Deadlines
`load`|Loads tasks to the linked task list of one specified Module without modifying the main task list|Allows the program to store linked tasks with the modules and load them on start-up so that users do not need to add tasks to modules everytime he / she restarts Domsun|The main task list is unaffected by the load command

Enhancements in ver 2.1:

Description | Justification | Highlights
--- | --- | --- 
Improved error messages|Makes it easier for the users to find out what is actually wrong|When an error happens the program may provide correct syntax together with `Notes` to the user depending on the command
Implemented automatic word wrapping for the text region of the fancy UI mode|Makes the UI more readable|The paged view also has improved headings
Added restrictions to non-applicable commands|Give the user an error message instead of ignoring the input so that users can learn the commands better|Some commands now suggests the user to use other commands based on the scenario

Previous Contributions:

|**Action** | **Format**| **Examples**|
|------------|-------------|-------------|
|**todo**|`todo [description]`|`todo borrow book`|
|**deadline**|`deadline [description] -by [time]`|`deadline project submission -by 21/9/15 1:12`|
|**event**|`event [description] -at [time]`|`event concert -at May 13 2020 8:00`|
|**list**|`list date [asc / desc / spec “date”]`|`list date asc`|
|**done**|`done [index]`|`done 2`|
|**undone**|`undone [index]`|`undone 2`|
|**find**|`find [keyword]`|`find exam`|
|**take**|`take [index / module code]`|`take CS2113T`|
|**untake**|`untake [index / module code]`|`untake CS2113T`|
|**sel**|`sel [index / module code]`|`sel 1 2 3`|
|**unsel**|`unsel [index / module code]`|`unsel CS1010 CS2113`|
|**detail**|`detail [index / module code]`|`detail CS2113T`|
|**grade (current version)**|`grade [-option] [module] [grade] {[module] [grade]...}`|`grade -a CS2113 A CG1112 A-`|
|**goal (current version)**|`goal [-option] [total MC] [target CAP] [taken MC] [current CAP]`|`goal -c 160 4.9 100 4.5`|
|**mc (current version)**|`mc [-option] [-details]`|`mc -p`|
|**cap (current version)**|`cap [-option] [module] [grade] {[module] [grade]...}`|`cap -m CS2113 A CG1112 A-`|
|**add**|`add -task [index] -mod [module code]`|`add -task 1 -mod CS2113`|
|**clear** | `clear`||
|**delete**|`delete [index]`|`delete 2`|
|**focus (current version)**|`focus [deadline / todo / event / task / mod / selected / taken]`|`focus deadline`|
|**help**|`help [options]`|`help deadline`|
|**fancy**|`fancy [option]`|`fancy`|
|**plain**|`plain [option]`|`plain`|
|**next**|`next [option]`|`next`|
|**prev**|`prev [option]`|`prev`|
|**bye**|`bye`||


**Code Contributed**: [Reposense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=tomlbz&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=TomLBZ&tabRepo=AY2021S1-CS2113-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other "My Reposense") 

### 2.2 Contributions to UG: 

Completed the ***Installation*** section of our UG, added pictures to aid understanding.<br>
Wrote parts of the ***Domsun Tutorial***.<br>
Wrote the initial update for ***all*** features in the feature list. Those are updated progressively by my Teammates later after each iteration.<br>

### 2.3 Contributions to DG:

Write up the text descriptions for Setting-up, UI layer, Command Interpreter layer.<br>
Write up sections for Take feature.<br>
Contributed to use cases and project scope.

### 2.4 Contributions to team-based tasks: 
Managing PRs after v1.0<br>
Debugging and fixing the majority of bugs after peer review<br>
Managing priorities and Severities and Types of issues and PRs after v1.0<br>
Implement the backbone and overall structure code of Domsun<br>
Implement APIs of Domsun, such as package `lexical` and `visualize`, `messages`, `io`, etc.<br>
Implement the majority of the features of Actions for Commands.