# Lyu Jiawen - Project Portfolio Page
## Table of contents
* [Overview](#overview)
* [Summary of Contributions](#summary-of-contributions)
  * [Code contributed](#code-contributed)
  * [Enhancements implemented](#enhancements-implemented)
    * [Enhancements to new features](#enhancements-to-new-features)
    * [Enhancement to existing features](#enhancement-to-existing-features)
  * [Contributions to team based tasks](#contributions-to-team-based-tasks)
  * [Contributions to documentation](#contributions-to-documentation)
    * [Contributions to the developer guide](#contributions-to-the-developer-guide)
    * [Contributions to the user guide](#contributions-to-the-user-guide)
  * [Contributions beyond the project team](#contributions-beyond-the-project-team)
    
## Overview
Our product, **_25HoursADay_** is a scheduling application catered for NUS students. 
It is optimised for use via the Command Line Interface (CLI) and it serves as an efficient one stop application for our users to manage their time. <br/>
<br/>
Given below are my contributions to the project. <br/>
## Summary of Contributions
### Code contributed:
https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jiawenlyu
### Enhancements implemented:
#### Enhancements to New features:
* Added ability to add lecture, lab, and tutorial events.
 ([\#15](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/15),
  [\#30](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/30))
  * What it does: Add the ability to add new lecture, lab, and tutorial events.
  * Justification: The feature allows users to manage the lecture, lab, and tutorial events using our app.
  * Highlights: This feature is one of the basic features of the program, which is not complex but essential.
* Added ability to print the progress of tasks.
  ([\#15](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/15),
   [\#30](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/30),
   [\#41](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/41),
   [\#244](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/244))
   * What it does: Print user's progress of tasks.
   * Justification: This feature allows users to see their progress of their tasks 
                    by viewing the proportion of finished tasks on all tasks.
   * Highlights: This feature print out the result both in percentages and fractions for the users to keep track of.
* Added ability to prioritize a task.
  ([\#84](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/84),
   [\#150](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/150))
   * What it does: Add the ability to mark a task as important.
   * Justification: This feature allows users to give higher priority to important tasks.
   * Highlights: This feature distinguishes the ordinary tasks and important events for users to manage their tasks better.
* Added ability to print prioritized tasks.
  ([\#84](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/84))
   * What it does: Add the ability to print all the important tasks.
   * Justification: This feature allows users to view all the important tasks they have.
* Added ability to print suggestions.
  ([\#96](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/96),
   [\#142](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/142))
   * What it does: Add the ability to give users simple suggestions about preparing for which tasks first.
   * Justification: This feature allows users to get simple suggestions when they do not know what to do to manage their time better.
   * Highlights: This function just gives the basic suggestions considering both the importance and the urgency of tasks. 
     The number of suggestions given varies and at most three.
#### Enhancement to existing features:
* Added `isOver` for all events to automatically check whether the event is over.
  ([\#52](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/52))
* Fixed the input and output format of all events.
  ([\#52](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/52))
* Wrote Junit tests to increase coverage.
  ([\#98](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/98))
### Contributions to team-based tasks:
* Reviewed team members' PR, and gave them suggestions.
  ([\#29](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/29),
   [\#90](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/90),
   [\#94](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/94))
* Update the code as well as test files regularly to make sure it passes CI on GitHub.
### Contributions to documentation:
#### Contributions to the Developer Guide:
* Added part of the `Design` to the Developer Guide.
  ([\#110](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/110))
* Added implementation for the features `prioritize a task`, `print prioritized tasks`, `print progress`, `print suggestions`.
  ([\#133](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/133),
   [\#147](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/147))
* Added sequence diagram for features `prioritize a task`, `print prioritized tasks`, `print progress`, `print suggestions`.
  ([\#134](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/134),
   [\#135](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/135),
   [\#137](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/137),
   [\#144](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/144),
   [\#228](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/228))
* Added part of the user stories.
  ([\#165](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/165))
#### Contributions to the User Guide:
* Documented features `adding an event`, `prioritizing a task`, `printing important tasks`, 
 `printing progress`, and `giving suggestion`.
  ([\#179](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/179))
#### Contributions beyond the project team:
* Reported bugs in other team's project during PE.
  ([\#1](https://github.com/JiawenLyu/ped/issues/1),
   [\#2](https://github.com/JiawenLyu/ped/issues/2),
   [\#3](https://github.com/JiawenLyu/ped/issues/3),
   [\#4](https://github.com/JiawenLyu/ped/issues/4),
   [\#5](https://github.com/JiawenLyu/ped/issues/5),
   [\#6](https://github.com/JiawenLyu/ped/issues/6))