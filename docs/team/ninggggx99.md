# Low Qing Ning - Project Portfolio Page

## Overview
Scheduler--; is a desktop app for managing deadlines from different sources. The user will use a Command Line Interface to control it. It is specially designed for Computing students who are comfortable in using CLI and have Git project deadlines as well as consolidated Zoom session links due which will suit home based learning in this COVID period.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature:** Added the ability to set/update deadline for personal events
    * What it does: Allows the user to set or change the deadline for personal events.
    * Justification: This feature is one of the basic feature that allows user to add in date and time for their personal events.
    * Highlights: This feature required splitting up the user's command and checking individually if the fields like index, data and time were valid.

- **New Feature:** Added the ability to remind users of the events today
    * What it does: Prompt the user at the start with the events happening today also allows the user to check the events that they are having today.
    * Justification: This feature is one of the secondary feature that allows user to be reminded of the events happening on that day.
    * Highlights: This feature compares the current date with event's date and list them in the order that it is happening. Events with date but no time will also be included at the top of the list. 

- **New Feature:** Added the ability to allow users to key notes for an event
    * What it does: Allow user to key notes for event they specified. Timestamp is used to differentiate the newer notes from the older ones and newer notes will be appended to the older one.
    * Justification: This feature is one of the secondary feature that allows user to key in notes and attaching it to the events.
    * Highlights: This feature takes in user input after Scheduler has prompted, user have to avoid using ` as this will be the indicator for a new line. Users are required to follow the user guide and type ```/end note``` on a new line to indicate the end of notes.

- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ninggggx99&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=ninggggx99&tabRepo=AY2021S1-CS2113T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

- **Project Management**
    * Managed releases `v1.0` - `v2.0` (2 releases) on GitHub
- **Documentation:**
    * User Guide:
        *  Added documentation for the `deadline`, `notes`, `bye`  and `reminder` features.
        *  Added and maintained command summary with the commands and examples of usage
        
    * Developer Guide:
        *  Added implementation details for Command Component
        *  Added implementation details for the `deadline` feature, with a sequence diagram and diagram showing how the program behave at each state
        *  Added user stories 
- **Community:**
    * Reported bugs and suggestions for other teams [1](https://github.com/ninggggx99/ped/issues)
  
     