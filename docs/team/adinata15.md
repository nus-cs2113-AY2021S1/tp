## 1. Overview
### 1.1 Project Overview 
DOMNUS - is a desktop application that helps in module and task tracking. It was developed under the module CS2113 Software Engineering & Object Oriented Programming. Within the schedule of approximately 7 weeks, my team of 5 Computer Engineering students successfully created DOMNUS to help students to stay organize with respective to the modules they take in NUS. 

## 2. Summary of Contributions
Given below are my contributions to the project. 

### 2.1 Enhancements Implemented: 

New Feature: 
CAP calculator (later improved by tomlbz)
<br> 
What it does:
It allows user to calculate their current/projected CAP value. They can also input custom modules and grades.
<br>
Justification:
Since "domsun" is a task and module scheduler, its user (diligent NUS student) will want to measure the result of their hard work (CAP value) after the exam.
<br>
Highlights:
This action can be easily integrated with other actions in the future. Functions like `getObjectInfo` and `modifyObject` can be passed on to actions that need cap calculation too such as `GoalAction`.
<br>
Credits: 
@tomlbz for improving the code quality and integrating CAP action with other actions in OOP manner.
<br>

<br>
New Feature: 
Goal calculator (later improved by tomlbz)
<br>
What it does:
Calculate and comment on how far user is from his dream CAP.
<br>
Justification:
As studying for a long time, the user will want to know what grade he should get on average to achieve his goal. `Goal` also will tell the user whether his goal is realistic or not.
<br>
Highlights:
This feature is synchronised with Cap Calculator and Grade Action so user can just need to input their grade once with Grade Action.
<br>
Credits: 
@tomlbz for improving the code quality and integrating CAP action with other actions in OOP manner.
<br>

<br>
New Feature: 
Grade Action (later improved by tomlbz)
<br>
What it does:
Allow user to add in and modify their grade on a particular taken module.
<br>
Justification:
After result release, user will want to register their grade to "domsun" for futher calculation (i.e. calculate CAP).
<br>
Highlights:
This fucntion can be easily implemented by other future action that needs grade registration such as CAP and Goal calculator. 
<br>
Credits:
@tomlbz for improving the code quality and integrating CAP action with other actions in OOP manner. 
<br>

Testing:
Implement testing for Add, Cap, Goal, Grade, Load, Module, and TaskActions.

**Code Contributed**: [Reposense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=adinata15&tabRepo=AY2021S1-CS2113-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

### 2.2 Contributions to UG: 
- Contributed to parts of Preliminaries.
- Contributed to explanation of general feature.
### 2.3 Contributions to DG:
- Implement sequence diagram
- Contributed to explantion of general feature.
### 2.4 Contributions to team-based tasks: 
Manual testing of codes, prepare meeting links, post issues on Github for debugging.
### 2.5 Contributions to CS2113 forum: 
- Asking questions for other to refer to.