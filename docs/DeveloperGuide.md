# Developer Guide

## Design & implementation


**Bookmark Component**

This bookmark section consists of how the bookmark feature is implemented. 
The bookmark feature is implemented similarly as the main architecture, however, 
in a smaller scale and a more bookmark-specific way. Figure 1 illustrates the general overview, 
the associations and the multiplicity of the bookmark classes.

[uml diagram]
![Bookmark Class Diagram](Images/BookmarkRun_Class.png)

API: java.bookmarkRun

The bookmark component consists of six major classes: `BookmarkRun`, `BookmarkStorage`, `BookmarkUi`, 
`BookmarkCategory`, `BookmarkList` and `BookmarkParser`. 
As shown in figure 1, `BookmarkRun` is 
* The main class to be called when the bookmark mode is accessed.
* The main class to access other bookmark classes.

Given below, Figure 2, is the sequence diagram of how the classes interact with each 
other when bookmark mode is accessed from the main function.

[uml diagram]
![Bookmark Sequence Diagram](Images/sequence_bookmark.png)

The bookmark component has two modes: the main bookmark mode and the category mode. 
As shown in Figure 2, when `BookmarkRun` is called, 
`BookmarkParser` will be called and return a `BookmarkCommand`. 
Afterwards, `BookmarkRun` will then call `executeCommand` in `BookmarkCommand` which executes 
the intended actions 
Then, it will call `getCategorymode` to get the current mode the user is in. 

**Bookmark Implementation**

A more detailed explanation of `BookmarkCommand`, `BookmarkCategory` and `BookmarkList` will be 
shown below. `BookmarkUi` and `BookmarkStorage` follow the same design implementations as the main 
architecture. The below figure shows the command classes available and they are called based on the 
`BookmarkParser` class as illustrated in Figure 2. 

![BookmarkCommand ClassDiagram](Images/bookmarkCommand_Class.png)

Figure 4 shows a more detailed sequence diagram of how the `BookmarkCategory`, `BookmarkCommand` 
and `BookmarkList` interacts with each other for the scenario when the user input an `AddLinkCommand` 
in Bookmark mode.

![BookmarkCommand ClassDiagram](Images/AddCommand_sequenceDiagram.png)




**Academic Component**

This section will describe in detail how some features inside the academic tracker section have been implemented.
Figure x illustrates the general overview, the associations and the multiplicity of the academic classes.

![Academic_Class_Diagram](Images/Academic_Class_Diagram.png)

API: `java.academic`

The above diagram looks at the overall structure of how the academic tracker is being implemented. 
This component is split into 7 different classes, 
their associations and multiplicity as explained in the above diagram x. 
The functions of the academic tracker will be called through the `AcademicRun` class 
when the program is in academic mode, which will subsequently call 
the functions in `PersonBook` or `GradeBook`. 

The academic component:
* initialises two arraylists, `ArrayList<Grade>` and `ArrayList<People>` 
to store the relevant `Grade` and `People` objects.
* uses `AcademicCommandParser` to parse the user command.
* identifies `AcademicCommandType` to decide 
which of the commands under `PersonBook` or `GradeBook` is to be executed.
* calls `AcademicStorage` to store the current set of data into the local storage file.

**Academic Implementation**

This section explains the details on how certain features 
are implemented in the academic tracker.

**GradeBook Features**
The grade features are facilitated `Gradebook`, which further make use of `Grade`. 
All grades are stored internally under `AcademicRun` as an array list `ArrayList<Grade>`.
It implements the following operations:
* `addGrade(String[], ArrayList<Grade>)`:Adds a `Grade` to the `ArrayList<Grade>`. 
* `printCap(ArrayList<Grade>)`:Calculate the current CAP based on the `ArrayList<Grade>`. 
* `printListOfGrades(ArrayList<Grade>)`:Print out all the `Grade` that are currently stored inside `ArrayList<Grade>`.
* `deleteGrade(Integer, ArrayList<Grade>)`:Delete a `Grade` from a specified index inside `ArrayList<Grade>`.
* `suGradeInGradeBook(Integer, ArrayList<Grade>)`:Su a `Grade` from a specified index inside `ArrayList<Grade>`.
* `starGrade(Integer, ArrayList<Grade>)`:Star a `Grade` from a specified index inside `ArrayList<Grade>`.
* `combineGradeDetails(Grade)`:Print out the details of a particular `Grade`.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
