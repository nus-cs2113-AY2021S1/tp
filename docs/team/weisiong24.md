# Ng Wei Siong - Project Portfolio Page

## Project: WhereGotTime
WhereGotTime is an application for students to add, edit and check their timetable schedules.
It is also able to compare timetable with their peers to find a common timeslot 
so that they can schedule a study time together.

Given below are my contributions to the project.

### Summary of Contributions

* New Feature:  Added the ability to Add user timetable details into our application
    * What it does: allows user to enter their timetable schedules (time,day,location and description) into the timetable using AddCommand Class
    * Justification: This feature allows user to add in their timetable schedules using the application add format so that their inputs will be captured

* New Feature: Added checks to user input when using the Add Command
    * What it does: 
        * Implemented time, day and description checks on the user input and display error message to allow user to retype to the correct format
        * Implemented format checks (NumberFormat Exception and Out of bound Exception) into Add command
        * Implemented check on the add command so that user follows the specified format when entering data
        * Implemented duplication check for user to indicate duplicate entries entered
    * Justification: This enhancement guides our user to enter their data correctly into our application for further usage
    
* Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=weisiong24&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* Contributions to team-based tasks : 
    * Creates and updates the team project board in Github with user stories and features for the milestone v1.0 
    * Added more labels for issue tracking in GitHub for teammates to use
    
* Documentation:
    * User Guide:
        * Added the format of the user guide to allow teammates to add on
        * Added User guide for add command ,exit command and Command summary
        * Updated the introduction, quick start and feature to base on our Project
    
    * Developer guide:
        * Added the design and implementation of Add command with uml and architecture and sequence design
        * Provided a format for Developer Guide for other teammates to add on
        * Updated the Target User Profile, Value Proposition and User Stories based on our Project
        * Updated the Manual testing commands 