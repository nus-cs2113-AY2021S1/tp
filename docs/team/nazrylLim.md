---
layout : page
title : Nazryl Lim - Project Portfolio Page
---

## Overview
NotUS is a quick and simple, Command Line Interface (CLI) based, note-taking application for keyboard-inclined users. NotUS allows for users to categorize notes by tagging as well as pinning the more important notes. NotUS is also designed to assist in planning timetables to highlight possible clashes.

*Roles (short for ‘in charge of role’): Description*

- Architecture: Worked with Jun Xiang on setting up the project, planned on logic of the project structure. Provides coding standards for programmers to follow.
- In charge of `add-n`,`delete-n`, `edit-n` of the code.

## Summary of Contributions

### Features implemented

1. Set up the skeleton code used in the project, including the following classes:
    * `Notebook` Class
    * `Note` Class
    * `InterfaceManager` Class
    * `SystemException` Class
    * `PrefixSyntax` Class
    
1. Completed the following features and test code for
    * `add-n`
    * `delete-n`
    * `edit-n`
    
1. Set up demo commands and bug testing for v1.0.
1. Edited v1.0 and v2.1 demo videos.

### Code contributed

1. Implemented the following classes
    * `ParserAddNoteCommand`
    * `AddNoteCommand`
    * `ParserDeleteNoteCommand`
    * `DeleteNoteCommand`
    * `ParserEditNoteCommand`
    * `EditNoteCommand`
    
1. Implemented test code for the following classes
    * `AddNoteCommandTest`
    * `DeleteNoteCommandTest`
    * `ParserManagerTest`
    
1. Maintained Notebook and Note classes.
    
1. Created inputContent() function for `add-n` and `edit-n`

1. Added formatNote method into the Formatter class.
    
1. Implemented prefix handling method used in all Parser classes with Jun Xiang.

1. Multiple improvements/bug-fixes made for `add-n`, `delete-n` and `edit-n` after each milestone.

### Contributions to User Guide

1. Maintain portions on commands related to Note.
    * `add-n`
    * `delete-n`
    * `edit-n`

### Contributions to Developer Guide

1. Maintain Commands section on:
    * AddNoteCommand
    
1. Maintain Notebook/Note section.

### Community
(https://nus-cs2113-ay2021s1.github.io/addressbook-level3/team/johndoe.html)

Reviewed some of the PRs to ensure the Classes and functions follow the project architecture as well code logic of the programme.
#29, #47, #51, #57, #63, #74, #78, #125, #126, #201
