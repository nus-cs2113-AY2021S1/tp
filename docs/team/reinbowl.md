# Marcus Ng - Project Portfolio Page

## Overview

Scheduler--; (S--) is a desktop app for managing deadlines from different sources.
The user will use a Command Line Interface to control it.
It is specially designed for Computing students who are comfortable in using CLI and have Git project deadlines as well
as consolidated Zoom session links due which will suit home based learning in this COVID period.

Given below are my contributions to the project.

## Summary of Contributions

* **New Feature**: Added the structure to store user's data.
    * What it does: Allows the user's data to be stored and accessed of easily during the program runtime.
    * Justification: This feature is essential to keep track of the user's data in a manner that is not complicated and
    can then be retrieved with simple code.
    * Highlights: Because of the different data to keep track of, having a single list of event was not an ideal
    solution. Multiple lists and each list with a name to keep track of the data had to be implemented for easy tracking
    of the different data. 

* **New Feature**: Added the ability to list events.
    * What it does: Allows the user to list events of a specific type or all events according to the order the events
    are add in.
    * Justification: This feature is essential to retrieving data and displaying the data back to the user.
    * Highlights: This feature required the capability to check if user had specified a type of event to list.
    
* **New Feature**: Added the ability to set goal.
    * What it does: Allows the user to set a goal which is different from an event in that goal is a motivation.
    * Justification: This feature allows user to remind themselves of their current goal in the midst of their busy
    schedule.

<div style="page-break-after: always;"></div>

* **New Feature**: Added the ability to format events into a calendar.
    * What it does: Allows the user to list events in the chronological order and in a format that is easy to read.
    * Justification: This features allows user to sort their events according to date and time which every scheduler
    should have.
    * Highlights: This feature required a design that could take in multiple list of events and sort all first according
    date then within each date according to time. This implementation was challenging as it had to factor in that
    certain events are repeating with the repeat command.

* **Code Contributed**: 
[RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=reinbowl&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project Management**:
    * Review pull requests to check if code can be refactored or improved.
    * Ensure that issues are assigned and were done in time for each milestone.

* **Enhancements to existing features**:
    * Add DateTimeParser to accept different formats build upon Java DateTime parse. (Pull request [\#30](https://github.com/AY2021S1-CS2113T-T12-4/tp/pull/30))
    * Updated repeat command and its data structure. (Pull request [\#83](https://github.com/AY2021S1-CS2113T-T12-4/tp/pull/83))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list`, `goal` and `calendar`. (Pull request [\#161](https://github.com/AY2021S1-CS2113T-T12-4/tp/pull/161))
        
    * Developer Guide:
        * Added the architecture details. (Pull request [\#126](https://github.com/AY2021S1-CS2113T-T12-4/tp/pull/126))
        * Added implementation details of the `list` feature. (Pull request [\#126](https://github.com/AY2021S1-CS2113T-T12-4/tp/pull/126))
        * Added implementation details of the `goal` feature. (Pull request [\#96](https://github.com/AY2021S1-CS2113T-T12-4/tp/pull/96))
        * Added implementation details of the `calendar` feature. (Pull request [\#255](https://github.com/AY2021S1-CS2113T-T12-4/tp/pull/255))
    
* **Community**:
    * Assisted with peer review for other group's PR 
    [1](https://github.com/nus-cs2113-AY2021S1/tp/pull/4/files)
    * Assisted in testing other group's tp based on their User Guide and for bugs 
    [2](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases)
