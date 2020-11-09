# Jusuf Nathanael - Project Portfolio Page

## Overview

Our team of 5 NUS computer engineering students has developed a Java Command the Java Commmand Line Interface app called **Zoomaster**. Zoomaster provides a simple and intuitive solution to store Zoom links for online classes, especially during this online learning period. These links and bookmarks are stored under the timetable feature, in which Zoomaster will also manage the user's classes and schedule. Finally, Zoomaster is also integrated with a planner feature that allows the user to plan a group meeting, simply by looking at the common empty slots among the team members.

## Summary of Contributions

#### Code contributed:

Click [here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#search=jusufnathanael&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jusufnathanael&tabRepo=AY2021S1-CS2113T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code) to view my code contributions on the tP Code Dashboard.

As the Dashboard is unable to detect some of my commits, click [here](https://github.com/AY2021S1-CS2113T-W11-1/tp/pulls?q=is%3Apr+author%3Ajusufnathanael) to see my pull requests to the team Github repository. Some changes to the User Guide and Developer Guide are often directly made to the team repository.

#### Enhancements implemented:

I implemented a **planner** feature, which is an extension of the timetable feature that allows users to find common empty slots among the team members.
- This feature can combine a few individual timetables from each of the team members and generate a list of the common empty slots. The user only needs to insert the timetable files into a folder called *planner*. Then, the user will be able to load the empty slots repeatedly.
- The user can add a new group meeting (similar to the timetable feature), but I have added a validation, such that the group meeting must only be added to an empty slot.

<div style="page-break-after: always;"></div>

- In this feature, the newly-added group meeting will not be automatically written to the timetable files. This allows the user to make further modifications to the meeting itself, as well as to facilitate the possibility of adding new members to the team (adding a new timetable file). To save the meeting to each individual timetable, the user will then need to enter the `save` command.

#### Contributions to the User Guide:

- Wrote the planner feature which includes the `load`, `add`, `show`, and `save` commands. 
- Provided timetable samples for testing on planner mode.

#### Contributions to the Developer Guide:

- Wrote descriptions on the planner feature, as well as its general flow and mechanism.
- Provided sequence diagrams for the `load` and `save` commands from the planner feature.
- Described the design considerations for the planner feature.

#### Contributions to Team-Based Tasks:

- Did a bit of [formatting](https://github.com/AY2021S1-CS2113T-W11-1/tp/commit/5a377d30a6d0909233e1ce97875a97aa312d9fd2) on the User Guide.
- Reviewed any grammatical errors for the [User Guide](https://github.com/AY2021S1-CS2113T-W11-1/tp/commit/82aee165044c85901d9fadc757a2913784118951) and [Developer Guide](https://github.com/AY2021S1-CS2113T-W11-1/tp/commit/d9b600603e0d79cb31640411ac1f9570a6816e1a) before the final submission.

#### Other Contributions:

- Reported bugs during the [PE dry run](https://github.com/jusufnathanael/ped/issues). 
