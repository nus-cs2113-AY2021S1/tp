# Harris Maung - Project Portfolio Page

## Overview

**Fitr** is a command-line application, helping you keep track of your food intake and exercises.

## Summary of Contributions

### Code Contributed

A summary of my contributions can be found [here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=sixletters&tabRepo=AY2021S1-CS2113T-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code).


### Enhancements Implemented

- Implemented Exercise class and Add Exercise Command.
    - This class enabled users to log and add into our system the exercises they have done as well as the calories they have burnt from the exercises.

- Implemented Food class and Add Food Command.
    - This class enabled users to log what food they have eaten, and the calories consumed from it.
    
- Implemented Standard Exercise Class and Standard Exercise List.
    - researched on frequent exercises that are done and came up with the appropriate intensity to store in our database.

- Implemented Recommender and Recommend Command
    - This class assessed our data base of standard exercises and recommended the users a workout.
    - Initially, I thought that a general recommendation would be enough, however I decided to implement it such that users would also be free to choose which body part or category they would want a recommendation for.
    - Class contained Standard Exercise Lists for different body parts and categories.
    - Users do not have to take the whole workout, rather they were free to choose and pick which exercise from the recommendation they wanted to do.

- Added JUnit tests
    - Added some JUnit tests to the Recommender component as well as individual classes such as Food, Exercise etc.

### Contributions to the User Guide

Contributed the following sections in the user guide:
- Adding a Food or Exercise entry
- Deleting a Food, Exercise or Goal Entry.
- Getting a recommended workout.

### Contributions to the Developer Guide

In the Developer Guide, I also wrote the sections related to the features and enhancements I implemented.
Contributed the following sections in the developer guide:
- Command component
- Recommender component
- Add command
- recommend command


### Contributions to Team-Based Tasks

In terms of contribution to team based tasks, the following areas were where I contributed the most:
- Approved and merge PRs.
- Made sure our application was accurate by researching on human metabolic levels.
- Created and assigned a few tasks in issue tracker.


### Review / Mentoring Contributions

Throughout the project, I made sure to review to my teammates to abstract out their code to follow OOP standards. This was not easy as we were not taught on OOP concepts until this year, however, by enforcing OOP standards, our code was generally neater and more specific.

### Contributions Beyond the Project Team

Throughout the project, I browsed through different exercise applications as well as researched on intensity level for exercises as well as their effect on the human body. This information would be key in making sure our product was accurate and provided the best experience for our users. I also led and brainstormed for more pragmatic features that we could add to our product, considering the rest of the team were following my direction.