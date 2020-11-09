# Ng Hong Ming - Project Portfolio Page

## Overview
Our product, **_25HoursADay_** is a scheduling application catered for NUS students. 
It is optimised for use via the Command Line Interface (CLI) and it serves as an efficient one stop application for our users to manage their time. 

Given below are my contributions to the project.

### Summary of Contributions
#### Code contributed:
[RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=imhm)

#### New Features implemented:

##### Establishing the overall structure of the code
Created the `CalendarList`, `CalendarItem`, `SchoolEvent`, `Exam`, `Lab`, `Tutorial`, `Lecture` classes, separating
 the `Event` class and the `Task` class. [#27](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/27)
* Highlights: It requires heavy change to the existing features to fit the new design. (Changing from the task list
 in Duke to a new Calendar list. Changing existing features to fit a specific class ie. mark as done only works for
  tasks.[#47](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/47))
* Credits: The team came up with the class diagram for the CalendarList together. Used my iP's code as the base code and
 implemented the above mentioned. The overall design of the code is reused from my iP's code.

##### Additional information for event class 
* What it does: Users are able to add/view/delete a list of information pertaining to the event
* Justification: This feature is unique and useful as it serves as a one stop application to
 store all data in your calendar.

#### Enhancements to existing features implemented:

##### Find Feature [#74](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/74)

* What it does: Users can use a keyword to search for an item from their list of tasks, or their list of events or the
 entire calendar list.
* Justification: Users can narrow their search. This particularly useful if they have a long calendar list.

##### Delete Feature [#47](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/47)
* What it does: Users can delete an event or a task.
* Justification: As the users can view their events list and task lists, they can remove unwanted tasks or events
 based on the indexing of the task/event in the task/event list.

Wrote additional tests for existing features to increase coverage. [#81](https://github.com/AY2021S1-CS2113T-T12-2/tp/pull/81)

#### Contributions to documentation:
* User Guide
    * Wrote the Important Notes section.
    * Documented the following features: [5.3]Marking a task as done, [5.5] Adding additional information of an event
    , [5.6] Deleting a calendar item, [5.7] Finding a calendar item.
* Developer Guide
    * Co-written the Design section with Jiawen. Added all diagrams (Architecture diagram, example sequence diagram
    (SD), class diagrams) in the Design section.
    * Documented the following features and added their respective sequence diagrams in the Implementation section
    : Add a calendar item feature, Mark a task as done feature, Additional information of an event feature, Delete a
     calendar item feature and Find a calendar item feature.
    * Documented part of the user stories.

#### Contributions to team-based tasks:
* Manage issue tracker (allocating and closing issues), closing of milestones V1.0 and V2.0.
* Release management: Release V1.0 and V2.0.
* Update the code as well as test files regularly to make sure it passes CI on GitHub.
#### Contributions beyond the project team:
* Sharing the solution I found to to fellow peers facing the source tree issue of not being able to authenticate. [issue
 #114](https://github.com/nus-cs2113-AY2021S1/forum/issues/114#issuecomment-717809977)
* Reported bugs in other team's project during PE.
[1](https://github.com/imhm/ped/issues/1)
[2](https://github.com/imhm/ped/issues/2)
[3](https://github.com/imhm/ped/issues/3)
[4](https://github.com/imhm/ped/issues/4)
* Reported bugs in other team's DG during tutorial.
[1](https://github.com/nus-cs2113-AY2021S1/tp/pull/6#issuecomment-718349154)
[2](https://github.com/nus-cs2113-AY2021S1/tp/pull/6#issuecomment-718352506)
[3](https://github.com/nus-cs2113-AY2021S1/tp/pull/6#issuecomment-718354243)
[4](https://github.com/nus-cs2113-AY2021S1/tp/pull/6#issuecomment-718356711)
[5](https://github.com/nus-cs2113-AY2021S1/tp/pull/6#issuecomment-718358158)