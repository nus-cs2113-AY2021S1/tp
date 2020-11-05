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
- Scheduling and tracking: In charge of defining, assigning, and tracking project tasks.
- In charge of `list-n`,`archive-n`, `unarchive-n`, `find-n`, of the code: In charge of the code that deals with parsing and UI

## Summary of Contributions

### Features implemented
1. Completed the following features and test code for them
    - `archive-n`
    - `find-n`
    - `help`
    - `list-n`
    - `unarchive-n`
1. In the `Notebook` class, the following methods were created
    - `archivedNotes`
    - `unarchivedNotes`
    - `getArchivedNotes`
    - `getNote`
    - `checkPinned`
    - `getSortedList`
    - `getPinnedNotes`
    - `getUnpinnedNotes`
    - `findNotes`
1. In the `Note` class, the following methods were created
    - `toggleArchived`
    - `getIsArchived`
    - `getPinned`
1. Added a couple of `formatNotes` methods into the `Formatter` class.
1. Refactored code to abstract out the successful/unsuccessful execution messages from the individual command classes to its own `CommandMessage` class.
1. Added ASCII art to make the application more user-friendly

### Code contributed


### Contributions to User Guide
Created the first draft of the Developer Guide and provided a template for the rest to follow. Also in charge of the overall formatting for the Developer Guide.
Filled up the following:
1. Introduction
1. Quick Start
1. Initial draft of the features, including description and example codes.
1. FAQ
1. Command Summary

### Contributions to Developer Guide
Created the first draft of the Developer Guide and provided a template for the rest to follow. Also in charge of the overall formatting for the Developer Guide.
Filled up the following:
1. Introduction
1. Initial draft of architecture overview including the architecture overview and PERT chart diagrams 
1. Product Scope (Target User Persona, Target User Profile and Value Proposition)
1. User Stories
1. Non-functional requirements
1. Glossary
1. Initial manual testing draft

### Community
(https://nus-cs2113-ay2021s1.github.io/addressbook-level3/team/johndoe.html)
