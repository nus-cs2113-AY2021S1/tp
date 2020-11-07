# Goh Song Han - Project Portfolio Page

## Overview

Fitr is a command-line application, helping you keep track of your food intake and exercises.

## Summary of Contributions

### Code contributed
[Link to code contributed](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=gohsonghan98&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements implemented

- Implemented View Command 
    - This class enables users to view profile information, help, food entries, exercise entries
     , and calorie summary. Different non-trivial methods are designed for non-similar view command types. 
    - A note-worthy design is with the view summary command. In this method, both exercise and food list have to be
     compared together,entry by entry, and sorted into a new combined ArrayList according to the dates of the entry
     . A separate date formatter is created to facilitate the sorting process. Upon successful sorting, the program
     finally outputs a list of caloric summary in ascending date.  

- Implemented User Class: 
    - This class stores all the user profile information (i.e. name, age, gender, height, weight
    , fitness level) and includes get and set methods for such information. Methods used for caloric calculation
    are also implemented here.
    - Ensured that there is sufficient OOP here to support more diverse implementations and scalability of Fitr.
 
- User Experience improvement:
    - Added tips and error messages (for edit profile and view command) to give users convenience and improve
     self-sufficiency of the Fitr program.

- JUnit tests:
    - Added some JUnit tests for product's quality assurance.
    
### Contributions to user guide

- Viewing Commands section
    - Viewing help
    - Viewing your profile
    - Viewing food entries
    - Viewing exercise entries
    - Viewing calorie summary
    
- Editing Commands (For User Profile)
    - Edit profile name
    - Edit profile age
    - Edit profile gender
    - Edit profile height
    - Edit profile weight
    - Edit profile fitness
    
### Contributions to developer guide
- Added detailed description of User component.
- Added sequence diagram for Edit command (implementation) to illustrate interaction with User class.
- Added sequence diagram for View command (implementation) to illustrate interaction with User class.

### Contributions to team-based tasks
- Create and assign some tasks in issue tracker
- Approved and merged some PRs

### Reviewing/mentoring contributions
- Suggested some code improvements to teammates after reviewing their PRs.

### Contributions beyond the project team
- Found 4 bugs during PE Dry Run [Link](https://github.com/gohsonghan98/ped/issues)
- Reviewed other group's team project PR [Link](https://github.com/nus-cs2113-AY2021S1/tp/pull/31)