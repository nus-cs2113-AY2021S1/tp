---
layout : page
title : Prachi - Project Portfolio Page
---

## Overview
NotUS is a quick and simple, Command Line Interface (CLI) based, note-taking application for keyboard-inclined users. NotUS allows for users to categorize notes by tagging as well as pinning the more important notes. NotUS is also designed to assist in planning timetables to highlight possible clashes.

*Roles (short for ‘in charge of role’): Description*

- Documentation: responsible for the parts regarding saving and loading of files 
- Implementation of I/O
- In charge of `pin-n`, `view-n`

## Summary of Contributions

### Features implemented
  1. Completed the following features and test code
     - `pin-n`
     - `view-n`
        
  1. Ensure proper loading of previously saved data 
  1. Ensure that all the data is saved when changes are made, specifically in the following classes
     - `AddEventCommand`
     - `AddNoteCommand`
     - `ArchiveNoteCommand`
     - `UnarchiveNoteCommand`
     - `CreateTagCommand`
     - `DeleteEventCommand`
     - `DeleteNoteCommand`
     - `EditEventCommand`
     - `EditNoteCommand`
     - `PinCommand`
     - `TagEventCommand`
     - `TagNoteCommand`
   
   1. Saving of all the notes and events when the programme ends 

   
### Code contributed
 1. In the 'StorageManager' class, created the following methods
    - `createFiles`
    - `createFile`
    - `createDirectory`
    - `loadAllNotes`
    - `loadTimetable`
    - `noteExists`
    - `getNoteContent`
    - `saveAllNoteDetails`
    - `saveNote`
    - `saveNoteContent`
    - `saveNoteDetails`
    - `deleteNoteContentFile`
    - `saveTimetable`
    - `getEventDetailsSaveFormat`
    - `saveAll`
    
 1. In the `Note` class, created the following methods
    - `toSaveString`
    - `togglePinned`
    - `getContentString`
### Contributions to User Guide
 1. Maintain portions on the following commands 
     - `pin-n`
     - `view-n` 
### Contributions to Developer Guide
 1. StorageManager related class and sequence diagrams 
 1. PinCommand and ViewNoteCommand sequence diagram 