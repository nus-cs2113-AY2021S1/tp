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
    to ensure most invalid user input can be handled. Further more there is mathematical calculation involved in finding 
    the dates of lesson for recuring classes base on the user input.
    * Credits: The code was coded from scratch.
  
  * **Sub feature:** Added show schedule command
    * What it does: Display the full schedule the user has planned for the next seven days
    * Justification: This feature serve as the basic command for the user to view the activities and classes
    for the next seven days
    * Highlights: This feature was challenging as a fix table needed to be printed without the help of GUI classes.
    Initially the table was design to be printed horizontally however the result tend to differ with different 
    command prompt window size. Hence, a vertical table is used and rotation of array is needed for the data to be
    displayed correctly. Correct data can be easily retrieved due to the data structure of the class and activity 
    designed previously.
    * Credits: The code was coded from scratch.
  
  * **Sub feature:** Added show link command
    * What it does: Allow users to see the links they need, or the venue they need to go to for the next two hours.
    * Justification: This feature allows the user to experience the ease of accessing links they need for events
    during the COVID period when many links needed to be managed for school and work.
    * Highlight: This feature is simple to implement with help of LocalDateTime class, 
    yet it is a really useful feature for students having online lesson during the Covid period
    * Credits: The code was coded from scratch
    
  * **Sub feature:** Added list class, list activity, delete class, and delete activity command
    * What it does: Allow user to see the full list of activities/classes the user has added and delete any unwanted once.
    * Justification: This feature allows user to see the full list of event so that they can amend any unwanted ones.
    *Credits: The program was coded from scratch but mainly adapting the design architecture from ip.
  
  * **Sub feature:** Added clean up command
    * What it does: allow user to clean up events that are over more than seven days ago
    *Justification: This feature allows user to free up storage space by deleting all overed event with a single command,
    so that user do not have to mannually delete overed event which are taking up storage spaces but is not
    used anymore.
    * Credits: The code was coded from scratch
  
### Code contributed: 
  
[RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=slightlyharp&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Project Management
* Reviewed and approved a number of PRs.
* Set up the PR structure my team on github so that the PRs have to be view by others before merging into master.
* Set up some Issue for team to work on.
* Managed and closed some of the Issues and Milestones.

### Documentation:
* [User Guide:](../UserGuide.md)
    * Added documentation for all the commands in timetable mode, listed under section 2 of the User Guide.

* [Developer Guide:](../DeveloperGuide.md)
    * Added the implementation details timetable component, including the UMLs used in that section.
    
### Community:
* Reported bugs and suggestions for other teams in the class 
[#1](https://github.com/slightlyharp/ped/issues/1)
[#2](https://github.com/slightlyharp/ped/issues/2) 
[#3](https://github.com/slightlyharp/ped/issues/3)
[#4](https://github.com/slightlyharp/ped/issues/4)
