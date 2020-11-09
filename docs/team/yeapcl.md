# Yeap Chun Lik - Project Portfolio Page

## Overview
WhereGotTime is an application for students to add, edit and check their timetable schedules.
It is also able to compare timetable with their peers to find a common timeslot 
so that they can schedule a study time together.

Given below are my contributions to the project.

### Summary of Contributions

* Code Contributed:
[Link to RepoSense](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=yeapcl)

* #### General
    - Provided skeleton framework (adapted from my iP) for which the application is built upon
    - Provided general direction on user experience (UX) and how the application should behave to ensure high usability
    - Ensured all features form a cohesive UX for our target user
    - 100% JUnit test coverage for EditCommand

* #### Enhancements
    * New Feature: Added the structure and ability to store and load user's credentials and timetable information automatically
        * What it does: Allows storage and access of users' credentials and timetable so that information is preserved
        * Justification: This feature is important to save users' information so that they do not have to go through the process of manually entering the information again
        * Highlights:
            * The storage saves the encrypted password to protect the security of the account.
            * Storage saves all information on-the-go during program runtime. This protects against unsaved data loss caused by unexpected crash
    
    * New Feature: Added the ability to edit an existing lesson's time information
        * What it does: Allows user to edit either the start or end timings of an existing lessons
        * Justification: This feature allows user to edit or update their timetables as lessons during initial course planning can be fluid.
        * Highlights:
            * Provided on-screen prompts for intuitive and user-friendly editing UX
            * Many checks are in place to prevent invalid or illogical timings, e.g. new timings are identical to old ones, start time is later than end time, and many more.
            
    * New Feature: Added help feature for user to get an overview of the available commands and input format
        * What it does: displays a comprehensive list of commands, format, instructions and tips
        * Justifications: the many features of our application can be overwhelmed especially for first-time user, hence a help instruction is always helpful in situation like this.
        Furthermore, the help instructions are written as dummy-proof and as meticulous as far as possible to ensure swift understanding for fast-typist.

### Contributions to the User Guide
- Updated all expected outputs for all commands
- Provided comprehensive documentations for Main Menu, `help`, and `edit` commands

### Contributions to the Developer Guide 
- Provided overall architecture, Class Diagram and Sequence Diagram of the EditCommand feature

### Contributions to team-based tasks
 - Added and managed issues on GitHub and assigning tasks to relevant teammates
 - Added user stories and suggested features on GitHub's issue page
 - Review PRs to check if codes can be improved or refactored
 - Keeping track of all issues assigned to ensure project achieves milestone on time
 
### Review/mentoring and other contributions
* Assisted with code reviews and suggested improvements to teammates' PRs
    * [Example 1](https://github.com/AY2021S1-CS2113-T13-3/tp/pull/181#pullrequestreview-523577115)
    * [Example 2](https://github.com/AY2021S1-CS2113-T13-3/tp/pull/77#pullrequestreview-516325424)
    * [Example 3](https://github.com/AY2021S1-CS2113-T13-3/tp/pull/187#discussion_r517997089)
