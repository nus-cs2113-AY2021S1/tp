# Colin Ng - Project Portfolio Page

## Overview
Scheduler--; (S--) is a desktop app for managing deadlines from different sources. The user will use a Command Line Interface to control it. It is specially designed for Computing students who are comfortable in using CLI and have Git project deadlines as well as consolidated Zoom session links due which will suit home based learning in this COVID period. 

Given below are my contributions to the project

## Summary of Contributions

- **New Feature:** Added the ability to call for help in the application
    - What it does: allows the users to seek help and assistance on how to use the commands in the application
    - Justification: This features allows for users who are getting familiar with how to use the application a quick way to get help about a feature without having to dig through the thicker user manual. 
    - Highlights: This command required the implementation of a bracket system to inform the program which section of the help file to print out for each different variation of the help command called by the user. 
- **New Feature:** Assisted with the development of a repeat function
    - What it does: allows the user to repeat an event for a specified amount of iteration, each iteration is a specified amount of unit time away from the original date and time. 
    - Justification: This features was implemented as the group felt that having to manually key in events that repeat periodically into the program would be tedious, hence the repeat command is provided to help speed up typing event information.
    - Highlights: It was difficult implementing the repeat command. We fist assumed that we could just simply store the states of the repeated events along the main events, but later realised that this method proved difficult to manage the repeated events for future events. One of our team members decided to implement events as a cloneable event, and it helped to make the implementation of future functions easier. 
- **New Feature:** Added the ability for the program to load user data from the computer and save user data on the computer.

- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=colin386&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project Management:**
    - Help set up the team organisation repo
    - Necessary general code enhancements
    
- **Enhancements to Existing Features:**
    - Updated the parser to accept multiple commands in succession for users who are familar with the product and would like to execute more than one command at once
    - Assisted with the note taking application in terms of intepreting various special characters. 

- **Documentation:**
    - User guide
        - Added documentation for the features `repeat`, `help` and `save`.
        - Added documentation for setting up the program.
    - Developer Guide:
        - Added implementation details for the saving functions.
        - Added sequence diagram of the `repeat` command implementation.
        - Wrote manual testing instructions.
        
- **Community:**
    - Assisted with peer review bug reporting [1](https://github.com/nus-cs2113-AY2021S1/tp/pull/51/files)

