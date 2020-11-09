---
layout : page
title : R Ramana - Project Portfolio Page
---

<!-- @@author r-ramana -->
**Overview**

NotUS is a quick and simple, Command Line Interface (CLI) based, note-taking application for keyboard-inclined users. NotUS allows for users to categorize notes by tagging as well as pinning the more important notes. NotUS is also designed to assist in planning timetables to highlight possible clashes.

- Co-team lead: Responsible for overall project coordination alongside Jun Xiang.
- Documentation and Code Quality: Responsible for the quality of various project documents and ensures adherence to coding standards.
- Integration, Scheduling and Tracking: In charge of assigning, and tracking project tasks. Worked with Jun Xiang for the versioning of the code, maintaining the code repository, integrating various parts of the software.
- In charge of `list-n`,`archive-n`, `unarchive-n`, `find-n`, of the code.

**Summary of Contributions**

*Features implemented*

1\. Completed the following features and test code for them:
`archive-n`: Allows the user to archive a note so their notebook is more organized and less cluttered. Users can access pertinent notes quicker. `find-n`: Allows the user to filter and find a note by the note title, and retrieve them as opposed to scanning for them in the list. It makes the process more efficient. `help`: Allows the user a quick access on the list of commands available and how to use them as the user might not recall all of the commands. `list-n`: Allows the user to see all available notes. User can also sort the notes in ascending or descending order for convenience, or list by tags. User can view all archived notes. `unarchive-n`: Allows the user to unarchive a note if the user wants to make changes to the note.

2\. In the `Notebook` class, created: `archivedNotes`, `unarchivedNotes`, `getArchivedNotes`, `getNote`, `checkPinned`, `getSortedList`, `getPinnedNotes`, `getUnpinnedNotes`, `findNotes`. In the `Note` class, created: `toggleArchived`, `getIsArchived`, `getPinned`. Added a couple of `formatNotes` methods into the `Formatter` class.

<div style="page-break-after: always;"></div>

*Enhancements to existing features*

Added ASCII art (PR [#90](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/90) and [#80](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/80)). Refactored code to abstract out the (un)successful execution messages (PR [#143](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/143)) to its own `CommandMessage` class.

*Code contributed*

[RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=r-ramana&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to view a report of the code contribution.

*Contributions to Team Based Task*

Setting up the GitHub team organization and repository, maintained issues and reviewed almost all PRs alongside Jun Xiang, Uudating of User/Developer documents that are not feature specific, set up [PERT Chart](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DevelopmentPractices.md#pert), set up and maintained [Gantt Chart](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DevelopmentPractices.md#pert), created a [Development Practices Document](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DevelopmentPractices.md).

*Contributions to User Guide*

Created the first draft of the [User Guide](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/UserGuide.md). Filled up the following: [Introduction](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89), [Quick Start](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89), [Initial draft of the features](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89), including description and example codes, [FAQ](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89), [Command Summary](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89)

*Contributions to Developer Guide*

Created the first draft of the [Developer Guide](https://github.com/AY2021S1-CS2113-T13-1/tp/blob/master/docs/DeveloperGuide.md). Filled up the following: [Introduction](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/55), Initial draft of [architecture overview](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/89), [Product Scope](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/55) (Target User Persona, Target User Profile and Value Proposition), [User Stories](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/55), [Non-functional requirements](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/103), [Glossary](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/103), Initial [manual testing draft](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/119)

*Community*

- [PRs reviewed](https://github.com/AY2021S1-CS2113-T13-1/tp/pulls?q=is%3Apr+is%3Aclosed) (with non-trivial review comments): [#33](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#36](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/36), [#37](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/37), [#40](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/40), [#45](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/45), [#46](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/46), [#47](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/47), [#49](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/49), [#52](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/52), [#53](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/53), [#58](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/58), [#61](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/61), [#65](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/65), [#83](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/83), [#88](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/88), [#104](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/104), [#107](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/107), [#108](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/108), [#111](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/111), [#114](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#116](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#117](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/114), [#120](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/120), [#121](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/121), [#126](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#177](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#180](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/33), [#182](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/126), [#189](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/189), [#192](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/192), [#195](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/195), [#196](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/196), [#203](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/203), [#205](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/205), [#207](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/207), [#213](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/213).
    - [Comment History](https://nus-cs2113-ay2021s1.github.io/dashboards/contents/tp-comments.html)
