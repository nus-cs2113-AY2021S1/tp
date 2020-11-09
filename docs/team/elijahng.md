---
layout: page
title: Elijah Ng's Project Portfolio Page
---
<!--- Would require jerkyll to display nicely --->


# Overview
DietBook is a Command Line Interface (CLI) desktop application designed mainly _NUS students staying on campus_. It helps users **track their food and nutritional intake** as well as provide them with their **daily calorie recommendation**. It also has a **database prepopulated with food items commonly found around NUS** so that thse food items can be easily added to the list of food items consumed for tracking. DietBook is written mainly in Java.

# Summary of Contributions

My main contribution is the [`seedu.dietbook.list`](https://github.com/AY2021S1-CS2113-T14-4/tp/tree/master/src/main/java/seedu/dietbook/list) package, which functions as the model for the application, supporting storing and retrieval of data from memory.

## Code Contributed
[Reposense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=mxksowie)

## Enhancements Implemented
- Implemented `FoodList`, `FoodListManager` and `ListFunctions` to store and retrieve data on the user's diet. This directly supports the command `add` and `list`. It also indirectly supports other commands such as `calculate` or storage/loading of data. A functional programming paradigm was compilmentarily used to reduce code repetition.
- Implemented `DatedFoodEntry` and `FoodManager` to store the relevant data and provide DateTime functionalities (filtering/sorting by datetime) as well as support optional inputs in the `add` command. A fascade pattern was used to design this segment of code and obscure details via `FoodManager`.
- Implemented class pathing and the use of Gradle resource files for the [database](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/database/DataBase.java).

## Contributions to DG
- Added the `Model` section under `design`, this includes all UML diagrams in this section.
- Added section in `implementation` describing the implementation of optional fields and estimated nutritional information, this includes all UML diagrams in this section.

## Contributions to team-based tasks:
- Helped in last minute bug fix to database for v1.0 release. Helped team to use class pathing and Gradle build tasks to put together a jar with .txt files.
- Helped with catching bugs, such as issue with missing spaces in `add` command. Also performed master branch clean-up and bug fixes such as in #103
- Authored 10 issues with the issue tracker.
- Contributed to weekly discussions on features and implementation.

## Review/mentoring contributions
- Helped team mates with bug fixing their code segments, such as that in the bug created in #71. Followed up offline too.
- Examples of helping to catch errors or suggest changes in PR reviews: #14, #162, and #184
- Followed up with team mates on code issues: static referencing of calculator, use of class pathing and gradle resource files.

