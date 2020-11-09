# Huang Shaohang - Project Portfolio Page

## Project: WhereGotTime
WhereGotTime is an application for students to add, edit and check their timetable schedules.
It is also able to compare timetables with their peers to find a common timeslot 
so that they can schedule a study time together.

Given below are my contributions to the project.

### Summary of Contributions

* New Feature:  Added the ability to COMPARE users' timetables and return the common range(s) of available timings
    * What it does: allows user to enter the specified target user he or she would like to COMPARE timetables with, followed by entering a day of the week
    * Justification: This feature encapsulates the main part of the application, which is to allow users to find out their common timeslots with their friends / peers for projects / meetings

* New Feature: Added the ability to retrieve a user's timetable with a String input
    * What it does: Retrieve a target user's timetable for processing purposes
    
* New Feature: Added checks to user input when using the COMPARE Command
    * What it does: User can only input the index of the target user as per displayed in the list of users. Any non-integer inputs / out of range inputs are rejected and user will be prompted to input again.
    * Justification: This enhancement guides our user to enter their data correctly into our application for further usage

    
* Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=yellow-fellow&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* Contributions to team-based tasks : 
    * Creates and updates the team project board in Github with user stories and features for the milestone v1.0 
    * Added more labels for issue tracking in GitHub for teammates to use
    
* Documentation:
    * User Guide:
        * Added User Guide for COMPARE command
        * Updated expected outputs of COMPARE command
    
    * Developer guide:
        * Added the design and implementation of COMPARE command with uml and architecture and sequence design
        * Updated the Manual Testing commands 
