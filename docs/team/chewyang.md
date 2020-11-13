# Owen Chew - Project Portfolio Page for traKCAL

## Overview
**traKCAL** is a desktop application for managing calorie intake, optimized for use via Command Line Interface (CLI) whilst retaining the benefits of a Graphical User Interface (GUI). **traKCAL** can manage your activities faster than traditional GUI applications.

### Summary of Contributions
I have implemented the following -
* List command.
* Classes used to display the list (listDrawer and findDrawer).
* Move command.
* Data structure of the DayMap
* Data encoding component of the dayMap to a CSV file.
* Logging class that provides the logging for traKCAL.

#### Code contributed
Repo sense link:
[Link to RepoSense](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chewyang&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Enhancements implemented

1. List drawing classes (listDrawer and findDrawer)
	* Implemented a responsive list when the users want to see a list of activities for a specific day. In the list, users can see their list of activities, respective calorie gain or lost, date of the list and their net calorie for that day in a more user-friendly environment.
    
    * Implemented a responsive list when the users use the ‘find’ command to see a list of activities containing the keyword/s corresponding to the type of ‘find’ command. In the list, users can see the activities containing the keyword/s, the respective date of each activity and the respective calorie gain or lost.
    
    * Implementing the drawer classes for the list command was difficult as there were many things to be taken into consideration when creating this list. For example,
        * When the numbering of the activities become very large, the formatting of the list may get distorted and the alignment will be off. Therefore, it was important for the start index of each column to be responsive to the index of the numbering.
        
        * When the description of each activities become too long, the formatting of the list will once again get distorted. It was important for there to be a fixed length per line for the description and let the trailing lines roll over to the next line, aligned to the start index of the description above so as to maximise the user experience of the user when looking  at the list of activities.
        
        * Making this list responsive allowed me to integrate the date column for the findDrawer class in between the numbering and the type of activity easily without affecting the other columns.
<br>

2. Implementing the data structure
    * Implemented the data structure of how the dates and the respective activities are stored in the system. Using a hashmap, with the key being the date and the value being the activityList for that day.
    
    * Implementing the correct data structure from the start of the project was essential so as to avoid unnecessary refactoring. Using a hashmap was the only logical way to store the data and improve the performance of our application.



#### Contributions to the User Guide

I contributed to section 5.0 (List), section 8.0(Move), contributed to the overall formatting and proofreading of the User Guide. 


#### Contributions to the Developer Guide

I contributed to the -
* Model section and its respective UML diagrams.
* List command section and displaying the list section and its respective UML diagrams.
* Move command and its respective UML diagrams.

#### Contributions to team-based tasks

I contributed to the -
* Updating user/developer docs that are not specific to a feature


#### Review/mentoring contributions

Link to PRs reviewed:
[Link to PR](https://github.com/AY2021S1-CS2113T-T09-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)

#### Contributions beyond the project team

Link to contributions:
[Link to contributions](https://github.com/chewyang/ped/issues)