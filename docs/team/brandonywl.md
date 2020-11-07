---
layout : page
title : Brandon - Project Portfolio Page
---


### Overview
NotUS is a quick and simple, Command Line Interface (CLI) based, note-taking application for keyboard-inclined users. NotUS allows for users to categorize notes by tagging as well as pinning the more important notes. NotUS is also designed to assist in planning timetables to highlight possible clashes.

*Roles (short for ‘in charge of role’): Description*

- In-charge of Events and Timetable of the code: Handles area of events, reminders and timetable scheduling and reviews changes done in this area.

## Summary of Contributions

### Features implemented
1. Set up and designed the architecture used for Event scheduling in the timetable.
    - Handled `Event`, `RecurringEvent`, `Timetable`, `Reminder` and other relevant `Command` subclasses for Event scheduling functionality.
    - Event functionality was designed with scalability in mind. Immutability was largely enforced to ensure consistency of Events even across re-occurrences. 
1. Implemented Single-Responsibility-Principle, Tell-Don't-Ask, and Liskov Substitution Principle in Designs.
    - Designed to reduce circular dependencies to reduce coupling.
1. Assisted in the development of two common `Parser` methods.

### Code contributed
[Reposense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=brandonywl&tabRepo=AY2021S1-CS2113-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
### Contributions to User Guide
1. Maintain portions on commands related to Events.
    - [`add-e`](https://ay2021s1-cs2113-t13-1.github.io/tp/UserGuide.html#add-e)
        - Added ability to add an event with custom end timings, multiple reminders and can be set to recurring.
    - [`edit-e`](https://ay2021s1-cs2113-t13-1.github.io/tp/UserGuide.html#edit-e)
        - Provide functionality to edit any Event that is stored in the Timetable without deleting and adding it back.
    - [`list-e`](https://ay2021s1-cs2113-t13-1.github.io/tp/UserGuide.html#list-e)
        - Provided an "overloaded" command where a user can see only all stored timetable or timetable for a specific year/month.
    - [`delete-e`](https://ay2021s1-cs2113-t13-1.github.io/tp/UserGuide.html#delete-e)
        - Provided a functionality to delete events stored. Removes all recurring one if used on a recurring event.
    - [`remind-e`](https://ay2021s1-cs2113-t13-1.github.io/tp/UserGuide.html#remind-e)
        - Gets all reminders for today and displays it to the user.

### Contributions to Developer Guide
1. [AddEventCommand Sequence Diagram](https://ay2021s1-cs2113-t13-1.github.io/tp/DeveloperGuide.html#commands)
1. [RemindCommand Sequence Diagram](https://ay2021s1-cs2113-t13-1.github.io/tp/DeveloperGuide.html#event)
1. [Timetable Class Diagram](https://ay2021s1-cs2113-t13-1.github.io/tp/DeveloperGuide.html#event)
1. [Event and RecurringEvent Class Diagram](https://ay2021s1-cs2113-t13-1.github.io/tp/DeveloperGuide.html#event)



### Community
#### Review Contributions:
1. [Code Review](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/47)
1. [Code Cleanup](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/66)
1. [Assist in Standardization](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/71)
1. [Provided Assistance in Event Functionality Saving & Edge Case](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/122)
