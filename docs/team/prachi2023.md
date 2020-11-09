---
layout : page
title : Prachi - Project Portfolio Page
---

<!-- @@author prachi2023 -->
**Overview**

NotUS is a quick and simple, Command Line Interface (CLI) based, note-taking application for keyboard-inclined users. NotUS allows for users to categorize notes by tagging as well as pinning the more important notes. NotUS is also designed to assist in planning timetables to highlight possible clashes.

*Roles (short for ‘in charge of role’): Description*

- Documentation: responsible for the parts regarding saving and loading of files.
- Implementation of the data storage (saving and loading data from the hard disk).
- In charge of `pin-n`, `view-n`.

**Summary of Contributions**

*Features implemented*

1. Completed the following features and test code
   - `pin-n`
	- Allows the user to pin the note that is deemed more important.
	- These notes will be listed first when the user lists notes.
   - `view-n`
	- Shows the user a specific note that is stored in the program.
	- User will be able to view the note when revising or studying.       
1. Ensure proper loading of previously saved data
	- After exiting the program, user will be able to access human editable text files for their notes.
	- When restarting the program, the user will not have to re enter all the information again and can seemlessly access previously saved data.
1. Ensure that all the data is saved when changes are made, in case the program is closed halfway the data is still saved. These changes occur specifically in the following classes
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
1. Saving of all the notes and events when the programme ends.

*Code contributed*

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

[Reposense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=prachi2023&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zA=prachi2023&zR=AY2021S1-CS2113-T13-1%2Ftp%5Bmaster%5D&zACS=197.20472673559823&zS=2020-09-27&zFS=&zU=2020-11-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

*Contributions to User Guide*

1. Maintain portions on the following commands 
     - `pin-n`
     - `view-n` 

*Contributions to Developer Guide*

1. StorageManager related class and sequence diagrams 
1. PinCommand and ViewNoteCommand sequence diagram 