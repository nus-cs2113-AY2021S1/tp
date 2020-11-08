---
layout : page
title : R Ramana - Project Portfolio Page
---

## Overview
NotUS is a quick and simple, Command Line Interface (CLI) based, note-taking application for keyboard-inclined users. NotUS allows for users to categorize notes by tagging as well as pinning the more important notes. NotUS is also designed to assist in planning timetables to highlight possible clashes.

*Roles (short for ‘in charge of role’): Description*

- Co-team lead: Responsible for overall project coordination alongside Jun Xiang.
- Code quality: Looks after code quality, ensures adherence to coding standards, etc.
- Documentation: Responsible for the quality of various project documents.
- Integration: Worked with Jun Xiang for the versioning of the code, maintaining the code repository, integrating various parts of the software to create a whole.
- Scheduling and Tracking: In charge of defining, assigning, and tracking project tasks.
- In charge of `list-n`,`archive-n`, `unarchive-n`, `find-n`, of the code: In charge of the code that deals with listing, archiving/unarchiving, and finding notes.

## Summary of Contributions
### Features implemented
1. Completed the following features and test code for them
    - `archive-n`
        - Allows the user to archive a note so their notebook is more organized and less cluttered.
        - Users now have a more organized and neater notebook list and are able to access the more pertinent notes quicker. The user may not want to delete the note as the note may become more relevant at a later time.
    - `find-n`
        - Allows the user to filter and find a note by the note title.
        - Users are able to find for notes and retrieve them as opposed to scanning for them in the list. It makes the process more efficient for the user.
    - `help`
        - Allows the user a quick access on the list of commands available and how to use them.
        - As the application has a myriad of commands, the user might not recall all of them and this is a simple way to assist the user.  
    - `list-n`
        - Allows the user to see all available notes, regardless of if it is pinned or not.
        - User can also sort the notes in ascending or descending order for convenience.
        - User can view all archived notes, or list out notes by tags.
        - Ensures an all round convenient design and product for the user.
    - `unarchive-n`
        - Allows the user to unarchive a note in an event the user wants to view/make changes to such a note.
        - Allows a convenient way for the user to sort notes by order of importance during a particular time in point.
1. In the `Notebook` class, the following methods were created:
    - `archivedNotes`
    - `unarchivedNotes`
    - `getArchivedNotes`
    - `getNote`
    - `checkPinned`
    - `getSortedList`
    - `getPinnedNotes`
    - `getUnpinnedNotes`
    - `findNotes`
1. In the `Note` class, the following methods were created:
    - `toggleArchived`
    - `getIsArchived`
    - `getPinned`
1. Added a couple of `formatNotes` methods into the `Formatter` class.

### Enhancements to existing features
1. Added ASCII art (PR [#90](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/90) and [#80](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/80)) to make the application more user-friendly. 
1. Refactored code to abstract out the successful/unsuccessful execution messages (PR [#143](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/143)) from the individual command classes to its own `CommandMessage` class.

### Code contributed
[RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=r-ramana&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to view a report of the code contribution.

### Contributions to Team Based Task
1. Setting up the GitHub team organization and repository.
1. Maintained Issues and Reviewed almost all PRs alongside Jun Xiang.
1. Updating of User/Developer documents that are not feature specific.
1. Set up [PERT Chart](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DevelopmentPractices.md#pert).
1. Set up and maintained [Gantt Chart](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DevelopmentPractices.md#pert).
1. Created a [Development Practices Document](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DevelopmentPractices.md).

### Contributions to User Guide
Created the first draft of the [User Guide](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/UserGuide.md) and provided a template for the rest to follow. Also in charge of the overall formatting for the User Guide.
Filled up the following:
1. [Introduction](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89)
1. [Quick Start](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89)
1. [Initial draft of the features](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89), including description and example codes
1. [FAQ](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89)
1. [Command Summary](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89)

### Contributions to Developer Guide
Created the first draft of the [Developer Guide](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DeveloperGuide.md) and provided a template for the rest to follow. Also in charge of the overall formatting for the Developer Guide.
Filled up the following:
1. [Introduction](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/55)
1. Initial draft of [architecture overview](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89)
1. [Product Scope](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/55) (Target User Persona, Target User Profile and Value Proposition)
1. [User Stories](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/55)
1. [Non-functional requirements](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/103)
1. [Glossary](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/103)
1. Initial [manual testing draft](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/119)

### Community
- [PRs reviewed](https://github.com/AY2021S1-CS2113-T13-1/tp/pulls?q=is%3Apr+is%3Aclosed) (with non-trivial review comments): [#33](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#36](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/36), [#37](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/37), [#40](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/40), [#45](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/45), [#46](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/46), [#47](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/47), [#49](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/49), [#52](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/52), [#53](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/53), [#58](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/58), [#61](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/61), [#65](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/65), [#83](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/83), [#88](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/88), [#104](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/104), [#107](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/107), [#108](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/108), [#111](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/111), [#114](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#116](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#117](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/114), [#120](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/120), [#121](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/121), [#126](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#177](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#180](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#182](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/126), [#189](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/189), [#192](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/192), [#195](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/195), [#196](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/196), [#203](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/203), [#205](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/205), [#207](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/207), [#213](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/213).
    - [Comment History](https://nus-cs2113-ay2021s1.github.io/dashboards/contents/tp-comments.html)
