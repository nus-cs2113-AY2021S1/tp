---
layout : page
title : Nazryl Bin Khairil Idham Lim - Project Portfolio Page
---

## Overview
NotUS is a quick and simple, Command Line Interface (CLI) based, note-taking application for keyboard-inclined users. NotUS allows for users to categorize notes by tagging as well as pinning the more important notes. NotUS is also designed to assist in planning timetables to highlight possible clashes.

*Roles (short for ‘in charge of role’): Description*

- Architecture: Worked with Jun Xiang on setting up the project, planned on logic of the project structure. Provides coding standards for engineers to follow.
- In-charge of `add-n`,`delete-n`, `edit-n` of the code:  Handle area of notes and notebook.

## Summary of Contributions

### Features implemented
1\. Set up the skeleton code used in the project, including the following [classes](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/6):
    * `Notebook`
    * `Note`
    * `InterfaceManager`
    * `SystemException`
    * `PrefixSyntax`

2\. Completed the following features and test code for
    * [`add-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/31) Added functionality to add note into the notebook with option to add tags, pinned note and archived note.
    * [`delete-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/32) Functionality applies to delete note from the notebook by an index and title of the note.
    * [`edit-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/121) Functionality applies to edit any note content, title and tags stored in the notebook.

<div style="page-break-after: always;"></div>

3\. Implemented the following classes
    * `ParserAddNoteCommand`
    * `AddNoteCommand`
    * `ParserDeleteNoteCommand`
    * `DeleteNoteCommand`
    * `ParserEditNoteCommand`
    * `EditNoteCommand`

4\. Implemented test code for the following classes
    * [`AddNoteCommandTest`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/61)
    * [`DeleteNoteCommandTest`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/61)
    * [`ParserManagerTest`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/61)

5\. Maintained Notebook and Note classes.

6\. Created inputContent() function for `add-n` and `edit-n` in `Parser`.

7\. Added formatNote method into the Formatter class.

8\. Implemented prefix handling method used in all Parser classes with Jun Xiang. Prefix are able to be called in any order.

9\. Set up demo commands and bug testing for v1.0 and v2.1.

10\. Edited v1.0 and v2.1 demo videos.

### Enhancements to existing features
1\. Multiple improvements/bug-fixes made for [`add-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/111), [`delete-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/88) and [`edit-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/180) after each milestone.

### Code contributed
[RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nazryl&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to view a report of the code contribution.

### Contributions to User Guide
1\. Maintain portions on commands related to Note
    * [`add-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/182)
    * [`delete-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/32)
    * [`edit-n`](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/141)

<div style="page-break-after: always;"></div>

### Contributions to Developer Guide
1\. Maintain Commands section on:
    * [AddNoteCommand](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/109)

2\. [Notebook](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/128)

### Community
- Reviewed some of the PRs to ensure the Classes and functions follow the project architecture as well code logic of the program.
[#29](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/29)
[#47](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/47)
[#51](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/51)
[#57](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/57)
[#63](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/63)
[#74](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/74)
[#78](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/78)
[#125](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/125)
[#126](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/126)
[#201](https://github.com/AY2021S1-CS2113-T13-1/tp/pull/201)