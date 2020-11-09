# Lin Yuheng - Project Portfolio Page

## Project: Study it

Study It is an interactive desktop app that helps students manage their study related matters, 
optimized for use via a Command Line Interface (CLI). 

It encompasses 4 main features: bookmark, timetable, academic tracker and flashcard
to help student organise their study life.

Given below are my contributions to the project.

### Features added:

* **New Feature:** Design and implemented the timetable mode

  * **Sub feature:** Added add class and add activity command 
    * What it does: Allow user to add class or activities to the application. Class
    are design for recurring events with zoom link or venue attached to it while activity
    are design for a one off event.
    * Justification: This feature serves as the basic command for the timtable feature so 
    that the user can plan their schedule.
    * Highlights: This feature is design with a data structure to store all the information 
    needed by a recurring classes, or a one off activity and arrange these data in a way that is 
    easy to access given a specific date. This feature also required extensive use of LocalDateTime Class
    to have the saved data to interact real time. There is also extensive error handling involve in the feature
    to ensure most invalid user input can be handled.
    * Credits: The code was coded from scratch.
  
  * **Sub feature:** Added show schedule command
    * What it does: Display the full schedule the user has planned for the next seven days
    * Justification: This feature serve as the basic command for the user to view the activities and classes
    for the next seven days
    * Highlights: This feature was challenging as a fix table needed to be printed with 
    
  
  * **Sub feature:** Added show link command to allow user to see the 
  links the user needs for the next two hours.
    * Justification: This feature allows the user to experience the ease of 
    accessing links they need for events during the COVID period when many
    links needed to be managed for school and work
    
  * **Sub feature:** Added show activity command to allow user to see the
  full list of activities the user has added.
  
### Code contributed: 
  
[RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=slightlyharp&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
  
### Documentation:
* User Guide: 
    * Added documentation for all the commands in timetable mode, listed under section 2 of the user guide.

* Developer Guide:
    * Added the implementation details timetable component, including the UMLs used in that section.