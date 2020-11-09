# KAJI - Developer Guide
By: `Team CS2113T-F11-3` Since: `August 2020`

<a name="top"></a>
## Table of Contents
1. [<b>Introduction</b>](#1-introduction)<br>
1.1. [Overview](#11-overview)<br>
1.2. [Purpose](#12-purpose)<br>
1.3. [Scope](#13-scope)<br>
2. [<b>Setting Up</b>](#2-setting-up)<br>
2.1. [Prerequisites](#21-prerequisites)<br>
2.2. [Setting Up the Project in your Computer](#22-setting-up-the-project-in-your-computer)<br>
2.3. [Verifying the Setup](#23-verifying-the-setup)<br>
3. [<b>Design (Architecture)</b>](#3-design-architecture)<br>
3.1. [Ui Component](#31-ui-component)<br>
3.2. [Logic Component](#32-logic-component)<br>
3.3. [Model Component](#33-model-component)<br>
3.4. [Storage Component](#34-storage-component)<br>
3.5. [Common Component](#35-common-component)<br>
4. [<b>Implementation</b>](#4-implementation)<br>
4.1. [Admin Features](#41-admin-features)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.1. [Add Module Feature](#411-add-module-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.2. [List Modules Feature](#412-list-modules-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.3. [Edit Module Name Feature](#413-edit-module-name-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.4. [Remove Module Feature](#414-remove-module-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.5. [Access Module Level Feature](#415-access-module-level-feature)<br>
4.2. [Module Features](#42-module-features)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.1. [Add Chapter Feature](#421-add-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.2. [List Chapters Feature](#422-list-chapters-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.3. [Edit Chapter Name Feature](#423-edit-chapter-name-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.4. [Remove Chapter Feature](#424-remove-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.5. [Access Chapter Level Feature](#425-access-chapter-level-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.6. [Return to Admin Level Feature](#426-return-to-admin-level-feature)<br>
4.3. [Chapter Features](#43-chapter-features)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.1. [Add Flashcard Feature](#431-add-flashcard-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.2. [List Flashcards Feature](#432-list-flashcards-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.3. [Edit Flashcard Content Feature](#433-edit-flashcard-content-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.4. [Remove Flashcard Feature](#434-remove-flashcard-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.5. [Return to Module Level Feature](#435-return-to-module-level-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.6. [Check Overall Performance for a Chapter Feature](#436-check-overall-performance-for-a-chapter-feature)<br>
4.4. [Revise with Scheduling Feature](#44-revise-with-scheduling-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.1. [Revise Feature](#441-revise-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.2. [Scheduling The Chapters Feature](#442-scheduling-the-chapters-feature)<br>
4.5. [Viewing and Customising the Schedule Feature](#45-viewing-and-customising-the-schedule-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.1. [View Due Chapters Feature](#451-view-due-chapters-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.2. [Preview Upcoming Dues Feature](#452-preview-upcoming-dues-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.3. [Exclusion Feature](#453-exclusion-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.4. [Reschedule Chapter Feature](#454-reschedule-chapter-feature)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.5. [View Revision History Feature](#455-view-revision-history-feature)<br>
5. [<b>Appendices</b>](#5-appendices)<br>
5.1. [Appendix A: Product Scope](#51-appendix-a-product-scope)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.1. [Target User Profile](#511-target-user-profile)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1.2. [Value Proposition](#512-value-proposition)<br>
5.2. [Appendix B: User Stories](#52-appendix-b-user-stories)<br>
5.3. [Appendix C: Use Cases](#53-appendix-c-use-cases)<br>
5.4. [Appendix D: Non-Functional Requirements](#54-appendix-d-non-functional-requirements)<br>
5.5. [Appendix E: Glossary](#55-appendix-e-glossary)<br>
5.6. [Appendix F: Instructions for Manual Testing](#56-appendix-f-instructions-for-manual-testing)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.1. [Launch and Shutdown](#561-launch-and-shutdown)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.2. [Showing a list of commands](#562-showing-a-list-of-commands)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.3. [Adding a module/chapter/flashcard](#563-adding-a-modulechapterflashcard)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.4. [Listing modules/chapters/flashcards](#564-listing-moduleschaptersflashcards)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.5. [Editing a module/chapter/flashcard](#565-editing-a-modulechapterflashcard)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.6. [Removing a module/chapter/flashcard](#566-removing-a-modulechapterflashcard)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.7. [Accessing the next level](#567-accessing-the-next-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.8. [Going back to the previous level](#568-going-back-to-the-previous-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.9. [Rescheduling a chapter](#569-rescheduling-a-chapter)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.10. [Starting a revision session](#5610-starting-a-revision-session)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.11. [Checking percentage of rating for the cards in a chapter](#5611-checking-percentage-of-rating-for-the-cards-in-a-chapter)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.12. [Listing all chapters that are due on current date](#5612-listing-all-chapters-that-are-due-on-current-date)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.13. [Previewing list of chapters due in a week](#5613-previewing-list-of-chapters-due-in-a-week)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.14. [Viewing history of revision completed in a day](#5614-viewing-history-of-revision-completed-in-a-day)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.15. [Excluding a module/chapter](#5615-excluding-a-modulechapter)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6.16. [Including a module/chapter](#5616-including-a-modulechapter)<br>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 1. Introduction

### 1.1. Overview
KAJI is a schedule manager that implements Spaced Repetition for students, optimised for use via a Command Line Interface (CLI).

### 1.2. Purpose
This document describes the architecture and system design for the application, KAJI.

### 1.3. Scope
This documentation describes the software architecture and software design decisions for the implementation of KAJI. The intended audience of this document is the developers, designers, and software testers of Kaji.
##### <a href="#top">Back to Top ^</a>

--------------------------------------------------------------------------------------------------------------------

## 2. Setting Up

### 2.1. Prerequisites
1. JDK 11
2. IntelliJ IDEA

### 2.2. Setting up the Project in your Computer
1. Fork this [repository](https://github.com/AY2021S1-CS2113T-F11-3/tp), and clone the fork into your computer.
2. Open IntelliJ (if you are not in the welcome screen, click `File` → `Close Project` to close the existing project dialog first).
3. Set up the correct JDK version for Gradle.
    1. Click `Configure` → `Project Defaults` → `Project Structure`.
    2. Click `New…` and set it to the directory of the JDK.
4. Click `Import Project` (or `Open or Import` in newer version of Intellij).
5. Locate the `build.gradle` file (not the root folder as you would do in a normal importing) and select it. Click `OK`.
If asked, choose to `Open as Project` (not `Open as File`).
6. Click `OK` to accept the default settings.

### 2.3. Verifying the Setup
1. In an IntelliJ terminal, run `gradlew build`.
2. Navigate to the folder `build` > `libs` by executing `cd build/libs/` and then run: `java -jar kaji.jar`.
    1. To use **KAJI**, type a valid command into the terminal and press the enter key to run the command.<br> 
    e.g. Typing `help` and pressing the enter key will show the list of commands available.
    2. Some example commands you can try to get familiar with **KAJI**:
        * `help`: Lists the commands that **KAJI** supports.
        * `add CS2113T`: Adds a module **CS2113T**.
        * `list`: Shows a list of modules available.
        * `exit`: Exits **KAJI**.

##### <a href="#top">Back to Top ^</a>

--------------------------------------------------------------------------------------------------------------------

## 3. Design (Architecture)

<p align="center">
  <img src="DG_Images/Architecture.png" width="500" alt="Architecture Diagram"/>
  <br/>Figure 1. Architecture diagram of KAJI 
</p>

The Architecture Diagram given above explains the high-level design of the App. Given below is a quick overview of each component.

The main class is `Kaji.java`. It is responsible for:

* Initializing the components in the correct sequence, and connects them up with each other at app launch.
* Shutting down the components and invoking cleanup methods where necessary at exit.

The rest of the App consists of 8 components:
* `UI`: The UI of the App.
* `Logic`: The command executor. 
* `Model`: Holds the data of the App in memory.
* `Storage`: Reads data from, and writes data to, the hard disk.

The sections below give more details of each component.

### 3.1. Ui Component 

<p align="center">
  <img src="DG_Images/UiComponent.PNG" width="600" alt="Class Diagram of Ui Component"/>
  <br/>Figure 2. Class diagram of Ui component 
</p>

The Ui component consists of a main class — `Ui.java`. While `KAJI` has `Ui` as its class variable so that it can instantiate a `Ui` object at each run of the program, the `Model`, `Logic` and `Storage` components have a dependency on the Ui component due to the need to take in user input and show the results of execution.

The Ui component is responsible for:
* Taking in user input.
* Printing result of execution for the different commands. 
* Printing error messages when certain exceptions occur. 

### 3.2. Logic Component 

The Logic component consists of the `Parser`, `Command` and `Scheduler` classes as shown in the class diagram below:

<p align="center">
  <img src="DG_Images/LogicClassDiagram.png" width="700" alt="Logic Class Diagram"/>
  <br/>Figure 3. Class diagram of Logic component  
</p>

1. `KAJI` uses the `Parser` class to parse the user command.
2. This results in a `Command` object which is executed by `KAJI`.
3. The command execution can affect the `Model` and `Storage` (e.g. adding a module).
4. The `Scheduler` is used in some command execution to schedule the due date of a chapter.
5. The result of the command execution is passed to the `Ui`.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `parse("edit 1 CS2113T")` API call:

<p align="center">
  <img src="DG_Images/LogicSequenceDiagram.png" width="800" alt="Logic Sequence Diagram"/>
  <br/>Figure 4. Sequence diagram of Logic component  
</p>

>:information_source: <b>Note:</b> The lifeline for `Parser` and `EditCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

### 3.3. Model Component

The Model component consists of the `Access`, `History`, `DueChapter`, `ModuleList`, `ChapterList` and `CardList` classes as shown in the class diagram below:

<p align="center">
  <img src="UML/model.png" width="600" alt="Architecture Diagram of Model"/>
  <br/>Figure 5. Class diagram of Model component
</p>

The Model component
* stores a `Access` object that holds temporary data for user's access level and contents of level being accessed.
* stores a `History` object that saves revision history of a day.
* stores a `DueChapter` object that manages due chapter data.
* provides an `Admin` class that can be created and accessed by `Access` object. The `Admin` class contains a `ModuleList` object to manage data for modules.
* provides a `Module` class that can be created and accessed by `Access` object. The `Module` class contains a `ChapterList` object to manage data for chapters.
* provides a `Chapter` class that can be created and accessed by `Access` object. The `Chapter` class contains a `CardList` object to manage data for cards.
* provides a `Card` object that can be created and accessed by `CardList`. A `Card` object represents a flashcard with question, answer and mastery level of revision.


### 3.4. Storage Component 

The Storage component consists of the `Storage`, `StorageWrite`, `StorageParser` and `StorageLoad` classes.

<p align="center">
  <img src="DG_Images/storage.png" width="600" alt="Storage Class Diagram"/>
  <br/>Figure 6. Class diagram of Storage component  
</p>

The Storage component
* can save the User's database of `Module`s, `Chapter`s and `Card`s as directories, text files and text within the data directory respectively.
* can parse the contents of the database stored in the data directory back into the software to load the User's database back into KAJI as `Module`s, `Chapter`s and `Card`s
* can save the due dates for a `Chapter` into a text file within the dues directory located in the `Module` directory that contains it.
* can parse the due dates into LocalDate objects from the dues directory so that KAJI can utilise them.
* can save the exclusions from the scheduling feature into a text file "exclusions.txt" within the "admin" directory.
* can parse the exclusions into an ArrayList<String> so that KAJI can determine which chapters are excluded.


### 3.5. Common Component

The Common component consists of `KajiLog` and `Messages` as shown in the class diagram below: 

<p align="center">
  <img src="UML/commonClass.png" width="400" alt="Common Class Diagram"/>
  <br/>Figure 7. Class diagram of Common component  
</p>

The Common component
* stores user visible messages.
* generates a log for KAJI.

##### <a href="#top">Back to Top ^</a>

--------------------------------------------------------------------------------------------------------------------

## 4. Implementation
This section will describe the significant details of how the features in **KAJI** are being implemented.

### 4.1. Admin Features
This section will elaborate the available features to users at **Admin Level**. <br>
At Admin Level, users are able to: <br>
* Add module
* List modules
* Edit module name
* Delete module
* Access module

#### 4.1.1. Add Module Feature

##### Implementation
The add modules feature allows the user to create new modules at Admin level.

`AddModuleCommand` facilitates the proposed add module feature. It extends `AddCommand` with an `AddCommand#prepareResult()` method. This method formates message about the result of the action to user in `Ui`. 

Shown as the class diagram below, because of the inheritance of `AddCommand`, `Command` and `AddModuleCommand`, `Kaji` is able to execute the operation `AddModuleCommand#excute()`. 

<p align="center">
  <img src="UML/AddModuleCommandClass.png" width="800" alt="Class Diagram of add module command"/>
  <br/>Figure 8. Class diagram of add module command
</p>

Given below is an example usage scenario at Admin level and how the add module feature behaves at each step:

* Step 1: The user is currently at admin level and wants to add a new module CS2113 and enters `add CS2113` command.

* Step 2: The `add` command creates `AddModuleCommand` which will then be executed.

* Step 3: The `AddModuleCommand` object creates a new `Module` object with the `Module#moduleName` of CS2113. 

* Step 4: The new `CS2113 : Module` object will be passed into the `AddModuleCommand#addModule()` with `Access` and `Storage`.

* Step 5: The `AddModuleCommand#addModule()` gets the current `Admin` object via `Access#getAdmin()` method, then it adds the new `CS2113: Module` object into the `Admin#ModuleList`. 

* Step 6: The new `Admin` object with the `CS2113: Module` object is updated to `Access` via the method `Access#setAdmin(Admin)`.

* Step 7: The new `Module` is updated in the folder by executing the `Storage#createModule()`. A 'CS2113' folder is created under the 'data/admin' folder.

* Step 8: The `AddModuleCommand#addModule()` method returns a result message to the `AddModuleCommand#execute()` method which uses `Ui#showToUser()` to print the success message.

The following diagram shows how the add module command feature works:

<p align="center">
  <img src="UML/AddModuleCommand.png" width="800" alt="Sequence Diagram of add module command"/>
  <br/>Figure 9. Sequence diagram of add module command 
</p>

#### 4.1.2. List Modules Feature

##### Implementation
The list modules feature allows the user to list all modules in admin level 

The list modules mechanism is facilitated by `ListModulesCommand`. It extends from the abstract class `ListCommand`. 

In addition, it implements the following operations:
* `ListModulesCommand#execute()` — oversees entire execution for listing modules.
* `ListModulesCommand#listModules()` — gets and lists all modules in admin level.

The following diagram shows the class diagram of the list modules feature:

<p align="center">
  <img src="DG_Images/listmod_class_diagram.png" width="800" alt="List Modules Class Diagram"/>
  <br/>Figure 10. Class diagram of list modules feature  
</p>

For instance, the user wants to list all modules available in `admin`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `admin` level. 

* Step 2: The user enters `list` command to list all modules in `admin` level. 

The following sequence diagram shows how the list modules feature works:

<p align="center">
  <img src="UML/listmod_seq_diagram.png" width="800" alt="Sequence Diagram of List Modules"/>
  <br/>Figure 11. Sequence diagram of list modules
</p>

#### 4.1.3. Edit Module Name Feature

##### Implementation
The edit module name feature allows the user to edit the name of any existing module.

The user can edit the name of an existing module with the `edit` command, which follows the following format: `edit MODULE_INDEX MODULE_NAME`.

The edit module name feature is facilitated by `ModuleList` and `Module`. The list of user's modules are stored internally as `ModuleList`.
In addition, it implements the following operations:
* `ModuleList#getModule()` - Gets a module based on the specified index from the list of modules.
* `Module#setModuleName()` — Sets the name of the module.

The following diagram shows the class diagram of the edit module name feature:

<p align="center">
  <img src="DG_Images/EditModuleClassDiagram.png" width="800" alt="Edit Module Class Diagram"/>
  <br/>Figure 12. Class diagram of edit module name feature  
</p>

For instance, the user wants to edit the module `CS2113`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `admin` level.

* Step 2: The user enters `edit 1 CS2113T` command to edit the first module in the list of modules — which in this case is `CS2113`.

* Step 3: The user input is parsed by `Parser`, which results in a new `EditModuleCommand` object.

* Step 4: `EditModuleCommand` is executed and calls the method `EditModuleCommand#editModule()`.

* Step 5: `EditModuleCommand#editModule()` gets the module based on the index provided by the method `ModuleList#getModule()`.

* Step 6: The module name is edited to `CS2113T` by the method `Module#setModuleName()`.

* Step 7: The result message from `EditModuleCommand#editModule()` is returned to `EditModuleCommand#execute()` and shown to the user by calling `Ui#showToUser()`.

The following sequence diagram shows how the edit module name feature works:

<p align="center">
  <img src="DG_Images/EditModuleSequenceDiagram.png" width="800" alt="Edit Module Sequence Diagram"/>
  <br/>Figure 13. Sequence diagram of edit module name feature
</p>

>:information_source: <b>Note:</b> The lifeline for `Admin` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

#### 4.1.4. Remove Module Feature

##### Implementation
The remove module feature allows the user to remove a module by specifying the index of the module in the list. 

The remove module mechanism is facilitated by `RemoveModuleCommand`. It extends from the abstract class `RemoveCommand`. 

In addition, it implements the following operations:
* `RemoveModuleCommand#execute()` — oversees entire execution for removing a module.
* `RemoveModuleCommand#removeModule()` — removes module from list of modules including the chapters and flashcards under it.
* `RemoveCommand#prepareResult()` — prepares the resulting message of the execution.

The following diagram shows the class diagram of the remove module feature:

<p align="center">
  <img src="DG_Images/RemoveModuleCommandClassDiagram.png" width="800" alt="Class Diagram of Remove Module"/>
  <br/>Figure 14. Class diagram of remove module
</p>

For instance, the user wants to start a remove the module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `admin` level. 

* Step 2: The user enters `remove 1` command to delete the first module in the list of modules — which in this case is `CS2113T`. The `remove` command creates `RemoveModuleCommand` which will then be executed. 

* Step 3: `RemoveModuleCommand#execute` gets the `module` based on the index provided and passes it to `Storage#deleteDirectory` to delete the module folder as well as the chapters and flashcards under it. 

The following sequence diagram shows how the remove module feature works:

<p align="center">
  <img src="DG_Images/RemoveModuleCommandSeqDiagram.png" width="800" alt="Sequence Diagram of Remove Module"/>
  <br/>Figure 15. Sequence diagram of remove module  
</p>

#### 4.1.5. Access Module Level Feature

##### Implementation
The go modules feature allows the user to access the existing module from Admin level.

`GoModuleCommand` class facilitates the proposed access module level feature. It extends `GoCommand` class. 

It implements the following operations:
* `GoModuleCommand#execute()` -- Inherits from the abstract `GoCommand`. It is a override to calls `GoModuleCommand#goModule()` and print message to user.
* `GoModuleCommand#goModule()` -- Accesses a new module level from admin level based on the index entered by user. If the index is out of bounds, KAJI returns an error message to user. Otherwise, this operation returns a successful message to the `GoModuleCommand#execute` operation.

Shown as the class diagram below, with the inheritance of `GoCommand` and `Command`, `Kaji` is able to execute the operation `GoModuleCommand#execute()` directly. 

<p align="center">
  <img src="UML/AccessModuleCommandClass.png" width="800" alt="Class Diagram of go module command"/>
  <br/>Figure 16. Class diagram of go module command
</p>

Given below is an example usage scenario at Admin level and how the access module level feature behaves at each step:

* Step 1: The user is currently at admin level and wants to access a module level named 'CS2113'. 

* Step 2: The user enters `go 1` command because module named 'CS2113' is first item on displayed list. This example is based on the assumption that the module 'CS2113' already has been added.

* Step 3: The `go` command creates `GoModuleCommand` which will then be executed.

* Step 4: `GoModuleCommand#goModule()` gets the module via the method `ModuleList#get(int)` based on the index parsed by `GoCommandParser`.
          
* Step 5: User's current access level is set to `module` level and a new temporary `CS2113` module object is created via the method `Access#setModule(Module)`.

* Step 6: `GoModuleCommand#execute()` prints the successful message to the user via `Ui#showToUser()` method.

The following diagram shows how the add chapter command feature works:

<p align="center">
  <img src="UML/AccessModuleCommand.png" width="800" alt="Sequence Diagram of add chapter command"/>
  <br/>Figure 17. Sequence diagram of add chapter command 
</p>

##### <a href="#top">Back to Top ^</a>

### 4.2. Module Features
This section will elaborate the available features to users at **Module Level**. <br>
At Module Level, users are able to: <br>
* Add chapter
* List chapters
* Edit chapter name
* Delete chapter
* Access chapter
* Return to Admin Level


#### 4.2.1. Add Chapter Feature

##### Implementation
The add chapter feature allows the user to create new chapters at Module level.

`AddChapterCommand` facilitates the proposed add chapter feature. It extends `AddCommand` with an `AddCommand#prepareResult()` method. This method formats message about the result of the action to user in `Ui`. 

Shown as the class diagram below, because of the inheritance of `AddCommand`, `Command` and `AddChapterCommand`, `Kaji` is able to execute the operation `AddChapterCommand#excute()`. 

<p align="center">
  <img src="UML/AddChapterCommandClass.png" width="800" alt="Class Diagram of add chapter command"/>
  <br/>Figure 18. Class diagram of add chapter command  
</p>

Given below is an example usage scenario at Module level and how the add chapter feature behaves at each step:

* Step 1: The user is currently at module level and wants to add a new chapter chapter1 and enters `add chapter1` command.

* Step 2: The `add` command creates `AddChapterCommand` which will then be executed.

* Step 3: The `AddChapterCommand` object creates a new `Chapter` object with the `Chapter#chapterName` of chapter1. 

* Step 4: The new `chapter1 : Chapter` object will be passed into the `AddChapterCommand#addChapter()` with `Access` and `Storage`.

* Step 5: The `AddChapterCommand#addChapter()` gets the current `Module` object via `Access#getModule()` method, then it adds the new `chapter1: Chapter` object into the `Module#ChapterList`. 

* Step 6: The new `Module` object with the `chapter1: Chapter` object is updated to `Access` via the method `Access#setModule(Module)`.

* Step 7: The new `Chapter` is updated in the folder by executing the `Storage#createChapter()`. A 'chapter1' folder is being created under the 'data/module' folder.

* Step 8: The `AddChapterCommand#addChapter()` method returns a result message to the `AddChapterCommand#execute()` method which uses `Ui#showToUser()` to print the success message.

The following diagram shows how the add chapter command feature works:

<p align="center">
  <img src="UML/AddChapterCommand.png" width="800" alt="Sequence Diagram of add chapter command"/>
  <br/>Figure 19. Sequence diagram of add chapter command  
</p>

#### 4.2.2. List Chapters Feature

##### Implementation
The list chapters feature allows the user to list all chapters in module level 

The list chapters mechanism is facilitated by `ListChaptersCommand`. It extends from the abstract class `ListCommand`. 

In addition, it implements the following operations:
* `ListChaptersCommand#execute()` — oversees entire execution for listing chapters.
* `ListChaptersCommand#listChapters()` — gets and lists all chapters in module level.

The following diagram shows the class diagram of the list chapters feature:

<p align="center">
  <img src="DG_Images/listchap_class_diagram.png" width="800" alt="List Chapters Class Diagram"/>
  <br/>Figure 20. Class diagram of list chapters feature  
</p>

For instance, the user wants to list all chapters available in `CS2113T` (module name), a detailed description of what happens is shown below:

* Step 1: The user is currently in `CS2113T` level. 

* Step 2: The user enters `list` command to list all chapters in `CS2113T` level. 

The following sequence diagram shows how the list chapters feature works:

<p align="center">
  <img src="UML/listchap_seq_diagram.png" width="800" alt="Sequence Diagram of List Chapters"/>
  <br/>Figure 21. Sequence diagram of list chapters
</p>

#### 4.2.3. Edit Chapter Name Feature

##### Implementation
The edit chapter name feature allows the user to edit the name of any existing chapter.

The user can edit the name of an existing chapter with the `edit` command, which follows the following format: `edit CHAPTER_INDEX CHAPTER_NAME`.

The edit module name feature is facilitated by `ChapterList` and `Chapter`. The list of user's chapters are stored internally as `ChapterList`.
In addition, it implements the following operations:
* `ChapterList#getChapter()` - Gets a chapter based on the specified index from the list of chapters.
* `Chapter#setChapterName()` — Sets the name of the chapter.

The following diagram shows the class diagram of the edit chapter name feature:

<p align="center">
  <img src="DG_Images/EditChapterClassDiagram.png" width="800" alt="Edit Chapter Class Diagram"/>
  <br/>Figure 22. Class diagram of edit chapter name feature  
</p>
 
For instance, the user wants to edit the chapter `chap 1` from the module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `CS2113T` at the module level.

* Step 2: The user enters `edit 1 Chapter 1` command to edit the first chapter in the list of chapters — which in this case is `chap 1`.

* Step 3: The user input is parsed by `Parser`, which results in a new `EditChapterCommand` object.

* Step 4: `EditChapterCommand` is executed and calls the method `EditChapterCommand#editChapter()`.

* Step 5: `EditChapterCommand#editModule()` gets the chapter based on the index provided by the method `ChapterList#getChapter()`.

* Step 6: The chapter name is edited to `Chapter 1` by the method `Chapter#setChapterName()`.

* Step 7: The result message from `EditChapterCommand#editChapter()` is returned to `EditChapterCommand#execute()` and shown to the user by calling `Ui#showToUser()`.

The following sequence diagram shows how the edit chapter name feature works:

<p align="center">
  <img src="DG_Images/EditChapterSequenceDiagram.png" width="800" alt="Edit Chapter Sequence Diagram"/>
  <br/>Figure 23. Sequence diagram of edit chapter name feature  
</p>

>:information_source: <b>Note:</b> The lifeline for `Module` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

#### 4.2.4. Remove Chapter Feature

##### Implementation
The remove chapter feature allows the user to remove a chapter by specifying the index of the chapter in the list. 
The remove chapter mechanism is facilitated by `RemoveChapterCommand`. It extends from the abstract class `RemoveCommand`. 

In addition, it implements the following operations:
* `RemoveChapterCommand#execute()` — oversees entire execution for removing a chapter.
* `RemoveChapterCommand#removeModule()` — removes chapter from list of chapters in a module including the flashcards under it.
* `RemoveCommand#prepareResult()` — prepares the resulting message of the execution.

The following diagram shows the class diagram of the remove chapter feature:

<p align="center">
  <img src="DG_Images/RemoveChapterCommandClassDiagram.png" width="800" alt="Class Diagram of Remove Chapter"/>
  <br/>Figure 24. Class diagram of remove chapter
</p>

For instance, the user wants to start a remove the chapter `Chapter 1` from the module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `CS2113T` at the module level. 

* Step 2: The user enters `remove 1` command to delete the first chapter in the list of chapters — which in this case is `Chapter 1`. The `remove` command creates `RemoveChapterCommand` which will then be executed. 

* Step 3: `RemoveChapterCommand#execute` gets the `chapter` based on the index provided and passes it to `Storage#deleteDirectory` to delete the chapter file as well as the flashcards under it. 

The following sequence diagram shows how the remove chapter feature works:

<p align="center">
  <img src="DG_Images/RemoveChapterCommandSeqDiagram.png" width="800" alt="Sequence Diagram of Remove Chapter"/>
  <br/>Figure 25. Sequence diagram of remove chapter
</p>

#### 4.2.5. Access Chapter Level Feature

##### Implementation
If a user wishes to go to the Chapter Level from the Module Level by accessing a Chapter within the Module he is currently in, he can do so with the Access Chapter Level Feature.

To execute this feature, the following class was created:
* `GoChapterCommand` - extends the abstract class `GoCommand`, brings the User down to the Chapter level if the target Chapter exists.

To support the Access Chapter Level Feature, `GoChapterCommand` implements the following operations:
* `GoChapterCommand#goChapter()` - Parses through the Module that the User is currently on to search for the specified Chapter. If the Chapter is found but not empty, there will be no output message, while if the found Chapter is empty, a message prompting the user to add Cards to the Chapter will be returned.
* `GoChapterCommand#execute()` - Calls `GoChapterCommand#goChapter()` and prints the output message returned if there is one.

The following diagram shows the class diagram of the Access Chapter feature:

<p align="center">
  <img src="DG_Images/AccessChapterClass.png" width="800" alt="Class Diagram of Access Chapter"/>
  <br/>Figure 26. Class diagram of access chapter
</p>

***Example***

For instance, the user is currently in the Module `CS2113T` and would like to go to the Chapter 'Chapter 1' on the Chapter level. A detailed description of what happens is shown below:

Step 1: The user enters `go 1` as `CS2113T` is the first Module in list.

Step 2: The user input is parsed by `Parser`, and `Parser` creates a `GoCommandParser` object, which in turn
 creates a `GoChapterCommand` object, with `1` as the command argument.

Step 3: `GoChapterCommand` is executed, and `GoChapterCommand#goChapter()` is called.

Step 4: `GoChapterCommand#goChapter()` calls `Access#getModule()`, `Access#getChapters()` and `ChapterList#getAllChapters()` to obtain an ArrayList of Chapters.

Step 5: Using the command argument as the `chapterIndex`, and all the Cards within the Chapter are loaded with `Storage#loadCard()` into an ArrayList of Cards.
 
Step 6: Lastly, to finish the transition into the Chapter level, `Access#setChapterLevel()`, `Chapter#setCards()` and `Access#setChapter` are called.

The following sequence Diagrams illustrates how the Access Chapter Level Feature is executed:

<p align="center">
  <img src="DG_Images/goChapter.png" width="1000" alt="Go Chapter Sequence Diagram"/>
  <br/>Figure 27. Sequence diagram of access chapter level feature
</p>

#### 4.2.6. Return to Admin Level Feature

##### Implementation

If a user has completed the tasks he has on the Module Level and wish to edit Modules from the Admin level, he can do so with the return to Admin Level Feature.

To execute this feature, the following class was created:
* `BackAdminCommand` - Extends the abstract class `BackCommand`, returns the User to the Admin Level

To support the Return to Admin Level Feature, `BackAdminCommand` implements the following operation:
* `BackAdminCommand#execute()` - Calls `Access#setModuleLevel()` to set the `Access` Object's `level` attribute's value to `adminLevel` if its current value is the `moduleLevel`

The following diagram shows the class diagram of the Return to Admin Level feature:

<p align="center">
  <img src="DG_Images/BackAdminClass.png" width="800" alt="Class Diagram of Access Chapter"/>
  <br/>Figure 28. Class diagram of access chapter
</p>

***Example***

For instance, the user is currently in the Module `CS2113T` and would like to return to the Admin Level.  A detailed description of what happens is shown below:

Step 1: The user enters `back`.

Step 2: The user input is parsed by `Parser`, and `Parser` creates a `BackCommandParser` object, which in turn
 creates a `BackAdminCommand` object

Step 3: `BackAdminCommand` is executed, and `Access#setModuleLevel()` is called.

Step 4: `Access#setModuleLevel()` verifies that the user is not on the Admin level at the moment, and calls `Access#setBackAdminLevel()`, which sets the user back into the Admin Level.

The following sequence Diagrams illustrates how the Return to Admin Level Feature is executed:

<p align="center">
  <img src="DG_Images/backAdmin.png" width="450" alt="Back Admin Sequence Diagram"/>
  <br/>Figure 29. Sequence diagram of return to admin level feature
</p>

##### <a href="#top">Back to Top ^</a>

### 4.3. Chapter Features
This section will elaborate the available features to users at **Chapter Level**. <br>
At Chapter Level, users are able to: <br>
* Add flashcard
* List flashcards
* Edit question and answer of a flashcard
* Delete flashcard
* Return to Module Level
* Check overall revision performance

#### 4.3.1. Add Flashcard Feature

##### Implementation
The add flashcard feature allows the user to add a flashcard to a chapter.

The user can add a flashcard with the `add` command, which follows the following format: `add q:QUESTION | a: ANSWER`.

The add flashcard feature is facilitated by `CardList`. The list of user's flashcards are stored internally as `CardList`.
In addition, it implements the following operation:
* `CardList#addCard()` - Adds a flashcard to the list of flashcards.

The following diagram shows the class diagram of the add flashcard feature:

<p align="center">
  <img src="DG_Images/AddCardClassDiagram.png" width="800" alt="Add Card Class Diagram"/>
  <br/>Figure 30. Class diagram of add flashcard feature  
</p>
 
For instance, the user wants to add a flashcard `[Q] 1+1 | [A] 2` to the chapter `Chapter 1` for module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `Chapter 1` at the chapter level of the module `CS2113T`.

* Step 2: The user enters `add q:1+1 | a:2` command to add a flashcard to the list of flashcards.

* Step 3: The user input is parsed by `Parser`, which results in a new `AddCardCommand` object.

* Step 4: A `Card` object is created within `AddCardCommand`.

* Step 5: `AddCardCommand` is executed and calls the method `AddCardCommand#addCard()`.

* Step 6: `AddCardCommand#addCard()` adds a flashcard to the list of flashcards by calling the method `CardList#addCard()`.

* Step 7: The result message from `AddCardCommand#addCard()` is returned to `AddCardCommand#execute()` and shown to the user by calling `Ui#showToUser()`.

The following sequence diagram shows how the add flashcard feature works:

<p align="center">
  <img src="DG_Images/AddCardSequenceDiagram.png" width="600" alt="Add Card Sequence Diagram"/>
  <br/>Figure 31. Sequence diagram of add flashcard feature  
</p>

>:information_source: <b>Note:</b> The lifeline for `Chapter` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

#### 4.3.2. List Flashcards Feature

##### Implementation
The list flashcards feature allows the user to list all flashcards in chapter level 

The list flashcards mechanism is facilitated by `ListCardsCommand`. It extends from the abstract class `ListCommand`. 

In addition, it implements the following operations:
* `ListCardsCommand#execute()` — oversees entire execution for listing flashcards.
* `ListCardsCommand#listCards()` — gets and lists all flashcards in chapter level.

The following diagram shows the class diagram of the list flashcards feature:

<p align="center">
  <img src="DG_Images/listcard_class_diagram.png" width="800" alt="List Flashcards Class Diagram"/>
  <br/>Figure 32. Class diagram of list flashcards feature  
</p>

For instance, the user wants to list all flashcards available in `Chapter 1` (chapter name), a detailed description of what happens is shown below:

* Step 1: The user is currently in `Chapter 1` level. 

* Step 2: The user enters `list` command to list all flashcards in `Chapter 1` level. 

The following sequence diagram shows how the list flashcards feature works:

<p align="center">
  <img src="UML/listcard_seq_diagram.png" width="800" alt="Sequence Diagram of List Cards"/>
  <br/>Figure 33. Sequence diagram of list cards
</p>

#### 4.3.3. Edit Flashcard Content Feature

##### Implementation
The edit flashcard content feature allows the user to edit the content of any existing flashcard.

The user can edit the content of an existing flashcard with the `edit` command, which follows the following format: `edit FLASHCARD_INDEX q:QUESTION | a: ANSWER`.

The edit flashcard content feature is facilitated by `CardList` and `Card`. The list of user's flashcards are stored internally as `CardList`.
In addition, it implements the following operations:
* `CardList#getCard()` - Gets a flashcard based on the specified index from the list of flashcards.
* `Card#setQuestion()` — Sets the question of a flashcard.
* `Card#setAnswer()` — Sets the answer of a flashcard.

The following diagram shows the class diagram of the edit flashcard content feature:

<p align="center">
  <img src="DG_Images/EditCardClassDiagram.png" width="800" alt="Edit Flashcard Class Diagram"/>
  <br/>Figure 34. Class diagram of edit flashcard content feature  
</p>

For instance, the user wants to edit the flashcard `[Q] 2*1 | [A] 2` from the chapter `Chapter 1` for module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `Chapter 1` at the chapter level of the module `CS2113T`.

* Step 2: The user enters `edit 1 q:1+1 | a:` command to edit the first flashcard in the list of flashcards — which in this case is `[Q] 2*1 | [A] 2`.

* Step 3: The user input is parsed by `Parser`, which results in a new `EditCardCommand` object.

* Step 4: `EditCardCommand` is executed and calls the method `EditCardCommand#editCard()`.

* Step 5: `EditCardCommand#editCard()` gets the chapter based on the index provided by the method `CardList#getCard()`.

* Step 6: As there is no content to edit the answer, the method `Card#getAnswer()` is called to get the answer of the existing flashcard - which in this case is `2`.

* Step 7: The question is edited to `1+1` by the method `Card#setQuestion()`.

* Step 8: The answer is edited to `2` by the method `Card#setAnswer()`.

* Step 9: The result message from `EditCardCommand#editCard()` is returned to `EditCardCommand#execute()` and shown to the user by calling `Ui#showToUser()`.

The following sequence diagram shows how the edit flashcard content feature works:

<p align="center">
  <img src="DG_Images/EditCardSequenceDiagram.png" width="800" alt="Edit Card Sequence Diagram"/>
  <br/>Figure 35. Sequence diagram of edit flashcard content feature  
</p>

>:information_source: <b>Note:</b> The lifeline for `Chapter` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

#### 4.3.4. Remove Flashcard Feature

##### Implementation
The remove flashcard feature allows the user to remove a flashcard by specifying the index of the flashcard in the list. 
The remove flashcard mechanism is facilitated by `RemoveFlashcardCommand`. It extends from the abstract class `RemoveCommand`. 

In addition, it implements the following operations:
* `RemoveFlashcardCommand#execute()` — oversees entire execution for removing a flashcard.
* `RemoveFLashcardCommand#removeModule()` — removes flashcard from list of flashcards in a chapter.
* `RemoveCommand#prepareResult()` — prepares the resulting message of the execution.

The following diagram shows the class diagram of the remove flashcard feature:

<p align="center">
  <img src="DG_Images/RemoveCardCommandClassDiagram.png" width="800" alt="Class Diagram of Remove  Flashcard"/>
  <br/>Figure 36. Class diagram of remove flashcard
</p>

For instance, the user wants to start a remove the flashcard `[Q] 1+1 | [A] 2` from the chapter `Chapter 1`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `Chapter 1` at the chapter level of the module `CS2113T`. 

* Step 2: The user enters `remove 1` command to delete the first flashcard in the list of flashcards — which in this case is `[Q] 1+1 | [A] 2`. The `remove` command creates `RemoveCardCommand` which will then be executed. 

* Step 3: `RemoveCardCommand#execute` gets the `flashcard` based on the index provided and removes it from the `CardList` 

* Step 4: The updated `CardList` is passed to `Storage#saveCards()` to update the contents of the chapter with the removed card. 

The following sequence diagram shows how the remove flashcard feature works:

<p align="center">
  <img src="DG_Images/RemoveCardCommandSeqDiagram.png" width="800" alt="Sequence Diagram of Remove Flashcard"/>
  <br/>Figure 37. Sequence diagram of remove flashcard
</p>

#### 4.3.5. Return to Module Level Feature

##### Implementation
The return to module level feature allows the user to return to the module level from the chapter level.
The return to module level mechanism is facilitated by `BackModuleCommand`. It extends from the abstract class `BackCommand`. 

In addition, it implements the following operation:
* `BackModuleCommand#execute` — lowers access level of the user.

The following diagram shows the class diagram of the return to module feature:

<p align="center">
  <img src="DG_Images/BackModuleCommandClassDiagram.png" width="600" alt="Class Diagram of Return to Module"/>
  <br/>Figure 38. Class diagram of return to module
</p>

For instance, the user wants to return to the module level from the chapter he is currently at in the module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `Chapter 1` at the chapter level in the module `CS2113T`. 

* Step 2: The user enters `back` command to return to `CS2113T`. The `back` command creates `BackModuleCommand` which will then be executed.

* Step 3: `BackModuleCommand#execute` passes an empty string to `Access#setChapterLevel()` to check the chapter level and calls `Access#setIsModuleLevel` to set the user back to module level.

The following sequence diagram shows how the return to module feature works:

<p align="center">
  <img src="DG_Images/BackModuleCommandSeqDiagram.png" width="600" alt="Sequence Diagram of Return to Module"/>
  <br/>Figure 39. Sequence diagram of return to module
</p>

#### 4.3.6. Check Overall Performance for a Chapter Feature

##### Implementation
The show rate feature allows the user to check their overall performance of revision with the chapter they are currently accessing.

`ShowRateCommand` facilitates the proposed check overall performance feature. It extends an abstract `Command` class with the abstract `Command#execute()` and `Command#isExit()`.

It implements the following operations:
* `ShowRateCommand#execute()` -- Inherits from the abstract `Command`. It is an override to calls `ShowRateCommand#computePercentage()` and print message that specific to performance checking feature.
* `ShowRateCommand#computePercentage()` -- Computes the overall performance by mastery levels and updates the `ShowRateCommand#easyPercentage`, `ShowRateCommand#mediumPercentage`, `ShowRateCommand#hardPercentage` and `ShowRateCommand#cannotAnswerPercentage`, then it returns number of cards in the `Chapter`.
* `ShowRateCommand#isExit()` -- Inherits from the abstract `Command`. It is an override to return `false`.

Shown as the class diagram below, with the inheritance of `Command`, `Kaji` is able to execute the operation `ShowRateCommand#execute()` directly. 

<p align="center">
  <img src="UML/ShowRateCommandClass.png" width="600" alt="Class Diagram of show overall performance command"/>
  <br/>Figure 40. Class diagram of show overall performance command  
</p>

Given below is an example usage scenario at Chapter level and how the show overall performance feature behaves at each step:

* Step 1: The user is currently at chapter level.

* Step 2: The user enters `showrate` command to check the overall revision performance of the current chapter. The `showrate` command creates `ShowRateCommand` which will then be executed.

* Step 3: The `ShowRateCommand` object gets the reference of current chapter level via the `Access#getChapter()` and stores the reference as a `Chapter` object. 

* Step 4: The `Chapter` object will be passed into the operation `ShowRateCommand#computePercentage()`.

* Step 5: The `ShowRateCommand#computePercentage()` gets all the `Card` objects in the current `Chapter` object via the methods `Chapter#getCards()` and `CardList#getAllCards()` respectively. 

* Step 6: If there is no `Card` object in the current `Chapter`, the `ShowRateCommand#computePercentage()` operation returns int `0` after checking the `ArrayList<Card>` size. If there are `Card` objects, a 'for' loop is used to check the `Card#rating` of every `Card` objects and compute the overall performance. 

* Step 7: `ShowRateCommand#computePercentage()` updates the variables `ShowRateCommand#easyPercentage`, `ShowRateCommand#mediumPercentage`, `ShowRateCommand#hardPercentage` and `ShowRateCommand#cannotAnswerPercentage` at the end and returns the number of cards in the chapter

* Step 8: `GoModuleCommand#execute()` prints the performance message to the user via `Ui#showToUser` if the `ShowRateCommand#computePercentage()` is greater than int `0`.

The following diagram shows how the show overall performance feature works:

<p align="center">
  <img src="UML/ShowRateCommand.png" width="600" alt="Sequence Diagram of show overall performance command"/>
  <br/>Figure 41. Sequence diagram of show overall performance command 
</p>

##### <a href="#top">Back to Top ^</a>

### 4.4. Revise with Scheduling Feature

The revise feature allows the user to start a revision on a chapter and can only be done when the user is in the module level. 

#### 4.4.1. Revise Feature

##### Implementation
The revise mechanism is facilitated by `ReviseCommand`. It extends from the abstract class `Command`. 

In addition, it implements the following operations:
* `ReviseCommand#execute()` — oversees the entire revise process and calls the respective methods when necessary.
* `ReviseCommand#getChapter()` — gets `chapter` based on the index the user provided.
* `ReviseCommand#getCards()` — gets a list of `card` in `chapter`.
* `ReviseCommand#promptNotDue()` — prompts user if he still wants to revise a `chapter` that is not due.
* `ReviseCommand#reviseCard()` — makes use of `ui` to show the contents of each card to the user and gets rating input for the particular card.
* `ReviseCommand#rateCard()` — gets user input on difficulty of a flashcard.
* `ReviseCommand#repeatRevision()` — repeats revision for cards which user could not answer. 

The following diagram shows the class diagram of the revise feature:

<p align="center">
  <img src="DG_Images/ReviseCommandClassDiagram.png" height="600" width="1023" alt="Class Diagram of Revise"/>
  <br/>Figure 42. Class diagram of revise
</p>

For instance, the user wants to start a revision for `Chapter 1` in the module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `CS2113T` at the module level.

* Step 2: The user enters `revise 1` command to revise the first chapter in the module — which in this case is `Chapter 1`. The `revise` command creates `ReviseCommand` which will then be executed.

* Step 3: `ReviseCommand#execute` gets `Chapter1` based on the index provided as well as a list of `card` under the particular chapter by calling `ReviseCommand#getChapter` and `ReviseCommand#getCards` respectively.

* Step 4: If the `chapter` is not due for revision yet, `ReviseCommand#promptNotDue()` will prompt the user if he still wants to revise a `chapter` that is not due. If the user enters `Y`, the Revise feature will continue execution, else it will return to the main class Kaji, and wait for the next command.

* Step 5: `ReviseCommand#execute` loops through each flashcard and shows the user its contents by calling `ReviseCommand#reviseCard()`  and inside it, `ReviseCommand#rateCard()` is called and makes use of `Ui#getUserInput()` to get user to rate the difficulty of each flashcard. 

* Step 6: A success message of completing the revision will be shown to the user through `Ui#showToUser()`.

* Step 7: `Scheduler#computeChapterDeadline()` then calculates the new deadline for the `chapter` and passes the result to `Chapter#setueBy()` to set the new deadline for the `chapter`.

* Step 6: `ReviseCommand#repeatRevision` then repeats the revision session on cards which the user could not answer if any.

* Step 7: Lastly, `ReviseCommand#execute()` calls `HistoryCommand#addHistory()` to keep a record of the chapter revised so that the user can review what revision has been completed.

The following sequence diagram shows how the revise feature works:

<p align="center">
  <img src="DG_Images/ReviseCommandSeqDiagram.png" height="700" width="1200" alt="Sequence Diagram of Revise"/>
  <br/>Figure 43. Sequence diagram of revise
</p>

<p align="center">
  <img src="DG_Images/ReviseGetChap.png" width="500" alt="Sequence Diagram of Revise Get Chapter"/>
  <br/>Figure 44. Sequence diagram of get chapter for revision
</p>

<p align="center">
  <img src="DG_Images/ReviseNotDue.png" width="400" alt="Sequence Diagram of Revise Not Due"/>
  <br/>Figure 45. Sequence diagram of revise for chapter that is not due
</p>
 
<p align="center">
  <img src="DG_Images/ReviseGetCards.png" width="500" alt="Sequence Diagram of Revise Get Chapter"/>
  <br/>Figure 46. Sequence diagram of get cards for revision
</p>

#### 4.4.2. Scheduling The Chapters Feature

##### Implementation
In KAJI, each `Chapter` stores a `CardList` of `Card`s, each with their own `int` attribute `previousInterval`. Each `Chapter` also has a `LocalDate` attribute named `dueBy` that determines when the `Chapter` is due for revision. 
At the end of a revision session, the `Scheduler` class implements Spaced Repetition by computing the `deckInterval`, the mean (rounded off to the nearest integer) of the `previousInterval`s of every `Card` within the `Chapter`, and updates the `dueBy` attribute of the `Chapter` to `deckInterval` days after the day of revision.

To support this feature, `Scheduler` implements the following operations:
* `Scheduler#computeEasyInterval()` - returns the product of the `Card`'s `previousInterval` and the Easy Multiplier(4.4) rounded to the nearest integer
* `Scheduler#computeMediumInterval()` - returns the product of the `Card`'s `previousInterval` and the Medium Multiplier(2.2) rounded to the nearest integer
* `Scheduler#computeHardInterval()` - returns the product of the `Card`'s `previousInterval` and the Hard Multiplier(1.1) rounded to the nearest integer
* `Scheduler#computeChapterInterval()` - returns the mean of the `previousInterval` of each `Card` within the `Chapter` rounded off to the nearest integer
* `Scheduler#computeChapterDeadline()` - returns a `LocalDate` object containing the new `dueBy`, which is interval(returned from `Scheduler#computeChapterInterval()`) days away from the current day

`Scheduler#computeEasyInterval()`, `Scheduler#computeMediumInterval()` and `Scheduler#computeHardInterval()` are exposed in the `ReviseCommand` class as `ReviseCommand#rateCard()` while `Scheduler#computeDeckDeadline()` is exposed as `ReviseCommand#execute()`.

##### <a href="#top">Back to Top ^</a>

### 4.5. Viewing and Customising the Schedule Feature
KAJI schedules the user's database automatically for them based on their [revision sessions](#), chapter by chapter, using Spaced Repetition. Users should be able to view their schedule for the current day to know which tasks they need to complete on the day itself and to view their schedule for the upcoming week so that they can plan ahead. However, to effectively use the scheduling feature, users should also be able to customise their scheduling system to include or exclude chapters from their schedule with ease.

To utilise this feature, the following commands and their corresponding features are introduced:
* [`due`](#451-View-Due-Chapters-Feature) - Viewing their schedule for the current day (View Due)
* [`preview`](#452-Preview-Upcoming-Dues-Feature) - Viewing their schedule for the upcoming week (Preview Upcoming Due)
* [`exclude`](#453-Exclusion-Feature) - Removing Chapters from the Scheduler (Exclusion)
* [`include`](#453-Exclusion-Feature) - Including Chapters back into the Scheduler (Exclusion)


#### 4.5.1. View Due Chapters Feature

##### Implementation
Each Chapter has a [`deadline assigned to it`](#) from the point of creation, which forms the core of the Scheduling process. The View Due Chapters Feature builds on that by allowing users to view every chapter in the database that is due on the current day with a single command.

To support this feature, the following command was added to KAJI:
* `due` - A command that displays every `Chapter` in the database that is due on a given day.
A corresponding Class `ListDueCommand` was also created to carry out the functions related to the command.

This feature takes the [`Exclusions`](#) of the database into account and will not display `Chapter`s from the Exclusion List when the user calls them even if their deadlines are due.

As this Feature can be activated at any point, the following class is created to identify `Chapter`s easily.
* `DueChapter` - object contain two variables, `String parentModule` to identify the `Chapter`'s parent `Module`, and `Chapter chapter` to hold a reference to the actual `Chapter`

To support the View Due feature, `ListDueCommand` implements the following operations:
* `ListDueCommand#loadAllDueChapters()` - Calls `Storage#loadAllDueChapters()` to load every `Chapter` in the database as a `DueChapter` and stores them into a `ArrayList<DueChapter> allDueChapters` 
* `ListDueCommand#setDueDueChapters()` - Checks each `DueChapter` in `ArrayList<DueChapter> allDueChapters` to verify if the underlying `Chapter` is due by the current day by calling `Scheduler#isDeadlineDue()` on the `Chapter`'s deadline', and adds the due `DueChapters` into `ArrayList<DueChapter> dueDueChapters`
* `ListDueCommand#execute()` - Calls `ListDueCommand#loadAllDueChapters()`, `ListDueCommand#setDueDueChapters()` and prints the `DueChapter`s that are due

On top of that, `Storage` implements the following operations:
* `Storage#checkChapterDeadline()` - Reads the deadline for each `Chapter`, prompts if they are corrupted, and adds a `DueChapter` formed with the `Chapter` into `ArrayList<DueChapter> allDueChapters`
* `Storage#checkAllChaptersForDue()` - Obtains the name of every `Chapter` from the list of Modules passed to it and calls `Storage#checkChapterDeadline()` for each of them
* `Storage#loadAllDueChapters()` - Obtains the names of every `Module` in the user database and calls `Storage#checkAllChaptersForDue()`

<p align="center">
  <img src="DG_Images/listDueClass.png" width="950" alt="Class Diagram of ListDue"/>
  <br/>Figure 47. Class diagram of ListDue
</p>

***Example***

For instance, the user wants to check what Chapters are due at the moment.  A detailed description of what happens is shown below:

Step 1: The user enters `due`.

Step 2: The user input is parsed by `Parser`, and `Parser` creates a `ListDueCommand` object.

Step 3: `ListDueCommand` is executed and calls the method `ListDueCommand#loadAllDueChapters()`, which will call `Storage#loadAllDueChapters()`, which will then call `StorageLoad#loadAllChaptersAsDueChapters()`. The name of every  Chapter, their deadlines, and the name of the Module that they belong to, will be stored as a `DueChapter` and be returned to `ListDueCommand`.

Step 4: `ListDueCommand` will then call `ListDueCommand#setDueDueChapters()` to parse each `DueChapter` for those
 that are already due by the current date, and add them to its `dueDueChapters` attribute.
 
Step 5: `ListDueCommand` will then call `Ui#printDueByTodayMessage()` to print the prompt, and then call `Ui#printDueChapters()`, to print out the contents of `dueDueChapters`

The following sequence Diagrams illustrates how the View Due Chapters Process is executed:

<p align="center">
  <img src="DG_Images/listDueSeq.png" width="800" alt="Sequence Diagram of the View Due Feature"/>
  <br/>Figure 48. Sequence Diagram of the view due Feature 
</p>

#### 4.5.2. Preview Upcoming Dues Feature

##### Implementation
Similar to the View Due Chapters Feature, the Preview Upcoming Dues Feature builds on the Scheduling process by allowing users to view every chapter in the database that is due in the upcoming week with a single command.

To support this feature, the following command was added to KAJI:
* `preview` - A command that displays every `Chapter` in the database that is due in the upcoming week.
A corresponding Class `PreviewCommand` was also created to carry out the functions related to the command.

This feature also takes the [`Exclusions`](#) of the database into account and will not display `Chapters` from the Exclusion List when the user calls them even if they are due in the upcoming week.

As this Feature can be activated at any point, the following class is also (similar to View Due Chapters) used to identify `Chapter`s easily.
* `DueChapter` - object contain two variables, `String parentModule` to identify the `Chapter`'s parent `Module`, and `Chapter chapter` to hold a reference to the actual `Chapter`

To support the Preview Upcoming Dues feature, `PreviewCommand` implements the following operations:
* `PreviewCommand#loadAllDueChapters()` - Calls `Storage#loadAllDueChapters()` to load every `Chapter` in the database as a `DueChapter` and stores them into a `ArrayList<DueChapter> allDueChapters` 
* `PreviewCommand#setDueDueChapters()` - Checks each `DueChapter` in `ArrayList<DueChapter> allChapters` to verify if the underlying `Chapter` is due in an increment number of days from the current day by calling `Scheduler#isDeadlineDueIn()` on the `Chapter`'s deadline, and adds the due `DueChapters` into `ArrayList<DueChapter> dueDueChapters`
* `PreviewCommand#execute()` - Calls `PreviewCommand#loadAllDueChapters()`, before calling `PreviewCommand#setDueDueChapters()` for the current day, then one to six days after the current day, and prints the `DueChapter`s for each day individually

On top of that, the following operations from `Storage` are used:
* `Storage#checkChapterDeadline()` - Reads the deadline for each `Chapter`, prompts if they are corrupted, and adds a `DueChapter` formed with the `Chapter` into `ArrayList<DueChapter> allDueChapters`
* `Storage#checkAllChaptersForDue()` - Obtains the name of every `Chapter` from the list of Modules passed to it and calls `Storage#checkChapterDeadline()` for each of them
* `Storage#loadAllDueChapters()` - Obtains the names of every `Module` in the user database and calls `Storage#checkAllChaptersForDue()`

<p align="center">
  <img src="DG_Images/previewClass.png" width="950" alt="Class Diagram of Preview"/>
  <br/>Figure 49. Class diagram of preview
</p>

***Example***

For instance, the user wants to check what Chapters are due on in the upcoming week.  A detailed description of what happens is shown below:

Step 1: The user enters `preview`.

Step 2: The user input is parsed by `Parser`, and `Parser` creates a `PreviewCommand` object.

Step 3: `PreviewCommand` is executed and calls the method `PreviewCommand#loadAllDueChapters()`, which will call
 `Storage#loadAllDueChapters()`, which will then call `StorageLoad#loadAllChaptersAsDueChapters()`. The name of every
  Chapter, their deadlines, and the name of the Module that they belong to, will be stored as a `DueChapter` and be
   returned back to `PreviewCommand`.

Step 4: To check for each day in the upcoming week, `PreviewCommand` runs a for loop with to carry out the following
 steps for `increment` of value from 0 to 6
 
Step 5: `PreviewCommand` will then call `PreviewCommand#setDueDueChapters()` to parse each `DueChapter` for those
 that are already due on the current date + `increment` days, and add them to its `dueDueChapters` attribute.
 
Step 5: `PreviewCommand` will then call `Ui#printDueByTodayMessage()` to print the prompt if the value of `increment` is 0, and `Ui#printDueByIncrementMessage()` to print the prompt if the value of `increment` is from 1 to 6.

Step 6: `PreviewCommand` will then finally call `Ui#printDueChapters()`, to print out the contents of `dueDueChapters` for the current iteration.

Step 7: Increment the value of `increment` by 1, and go back to Step 4 if the value of `increment` is smaller than 7.

The following sequence Diagrams illustrates how the Preview Upcoming Dues Process is executed:

<p align="center">
  <img src="DG_Images/previewSeq.png" width="800" alt="Sequence Diagram of the Preview Upcoming Dues Feature"/>
  <br/>Figure 50. Sequence diagram of the preview upcoming dues feature 
</p>

#### 4.5.3. Exclusion Feature

##### Implementation
KAJI allows users to customise which Chapters are to be excluded from their scheduling by maintaining an Exclusion List: a list of `Chapter`s that KAJI will ignore as it parses for `Chapter`s that are due in the `due` and `preview` commands. 

This is to allow users to exclude and include `Chapter`s from and to their schedules without having to remove and add the `Chapter`s from their database, which can be tedious.

To support this feature, the following commands were added to KAJI:
* `exclude` - A command that adds `Chapter`s to the Exclusion List.
* `include` - A command that removes `Chapter`s from the Exclusion List.
Two corresponding Classes `ExecuteCommand` and `IncludeCommand` were also created to carry out the functions related to the commands.

To load and store the Exclusion List, a Exclusion File is created and maintained using these two methods from the `Storage` Class:
* `Storage#loadExclusionFile()` - Reads the contents of the Exclusion File, parses it into the Exclusion List, stored as a `ArrayList<String>`, and returns it.
* `Storage#updateExclusionFile()` - Writes the `ArrayList<String>` Exclusion List into the Exclusion File.

#### 4.5.3.1 Excluding Chapters from the Schedule Feature
The `exclude` command can be called with either `exclude chapter` or `exclude module`, which adds a `Chapter` or every `Chapter` from a `Module` to the Exclusion List respectively. 

To determine if a single `chapter` or an entire `module` is to be added to the Exclusion List, `excludecommand` implements the operation `excludecommand#attemptToExclude()`.
* `excludecommand#attemptToExclude()` - responds to various forms of `exclude` command input as follows:
    * calls `excludecommand#addChapterToExclusion()` if `exclude chapter` is entered
    * calls `excludecommand#addModuleToExclusion()` if `exclude module` is entered
    * or throws an exception if the specified command argument is invalid.

Items are added into the `ArrayList<String>` Exclusion List using two pairs of commands in both `ExcludeCommand` and
 `Storage`:
* Excluding a Chapter from Scheduling
    * `ExcludeCommand#addChapterToExclusion()` - gets the name of the `Chapter` to be excluded and the name of the `Module` it belongs to, and calls `Storage#appendChapterToExclusionFile()`
    * `Storage#appendChapterToExclusionFile()` - appends the target `Chapter` to the Exclusion File if the target `Chapter` exists and is not already in the Exclusion File
* Excluding a Module from Scheduling
    * `ExcludeCommand#addModuleToExclusion()` - gets the name of the `Module` to be excluded, and calls `Storage#appendModuleToExclusionFile()`
    * `Storage#appendModuleToExclusionFile()` - appends every `Chapter` of the target `Module` not already in the Exclusion File to it if the target `Module` exists

<p align="center">
  <img src="DG_Images/excludeClass.png" width="1000" alt="Class Diagram of Exclude"/>
  <br/>Figure 51. Class diagram of exclude
</p>

***Example***

For instance, the user wants to exclude the Module `CS2113T` from his schedule. A detailed description of what happens is shown below:

Step 1: The user enters `exclude module`

Step 2: The user input is parsed by `Parser`, and `Parser` creates a `ExcludeCommand` object.

Step 3: `ExcludeCommand` is executed and calls the method `ExcludeCommand#attemptToExclude()`.

Step 4: `ExcludeCommand#attemptToExclude()` checks the command argument, `module` for the `exclude` command and calls
 `ExcludeCommand#addModuleToExclusion()`

Step 5: `ExcludeCommand` calls `Ui#getExcludedModuleName()` to obtain the name of the module that is to be excluded from the scheduling process, so the user will enter `CS2113T` here

Step 6: `ExcludeCommand` will then call `Storage#appendModuleToExclusionFile()`, which will in turn call
 `StorageWrite#appendModuleToExclusionFile()` to begin the process of Updating the Exclusion File.
 
Step 7: `StorageWrite` will load the contents of the Exclusion File into `excludedChapters` using `StorageLoad#loadExclusionFile()` and
 load the Chapters of the `CS2113T` by calling `StorageLoad#loadChaptersFromSpecifiedModule()`.
 
Step 8: `StorageWrite` will then check through the contents of the Exclusion file for each of the Chapters in `CS2113T`, and
 add them into `excludedChapters` if it is not already in it.
 
Step 9: After the inclusion of every Chapter in the `CS2113T` into `excludedChapters`, the contents of it are
 written back by `StorageWrite` into the Exclusion File by calling `StorageWrite#updateExclusionFile()`.
 
The following sequence Diagrams illustrates how the "exclude" command is executed:

<p align="center">
  <img src="DG_Images/excludeSeq.png" width="1000" alt="Sequence Diagram of the exclude command"/>
  <br/>Figure 52. Sequence diagram using the exclusion feature to exclude content from the schedule
</p>

<br>

#### 4.5.3.2 Including Chapters back into the Schedule Feature
On the other hand, the `include` command can be called with `include chapter` or `include module` which removes a `Chapter` or every `Chapter` from a `Module` from the Exclusion List.

Similarly, to determine if a single `chapter` or an entire `module` is to be removed from the Exclusion List, `includecommand` implements the operation `includecommand#attemptToInclude()`.
* `includecommand#attemptToInclude()`. - responds to various forms of `exclude` command input as follows:
    * calls `includecommand#addChapterToExclusion()` if `exclude chapter` is entered
    * calls `includecommand#addModuleToExclusion()` if `exclude module` is entered
    * or throws an exception if the specified command argument is invalid.
    
Items are removed from the `ArrayList<String>` Exclusion List using two pairs of commands in both `IncludeCommand
` and `Storage`:
* Including a Chapter from Scheduling
    * `IncludeCommand#removeChapterFromExclusion()` - gets the name of the `Chapter` to be included and the name of the `Module` it belongs to, and calls `Storage#removeChapterFromExclusionFile()`
    * `Storage#removeChapterFromExclusionFile()` - removes the target `Chapter` from the Exclusion File if the target `Chapter` is in the Exclusion File
* Including a Module from Scheduling
    * `IncludeCommand#removeModuleFromExclusion()`- gets the name of the `Module` to be included, and calls `Storage#removeModuleFromExclusionFile()`
    * `Storage#removeModuleFromExclusionFile()` - removes every `Chapter` of the target `Module` that is in the Exclusion File

<p align="center">
  <img src="DG_Images/includeClass.png" width="1000" alt="Class Diagram of Include"/>
  <br/>Figure 53. Class diagram of include
</p>

***Example***

For instance, the user wants to include the Module `CS2113T` back into his schedule. A detailed description of what happens is shown below:

Step 1: The user enters `include module`

Step 2: The user input is parsed by `Parser`, and `Parser` creates a `IncludeCommand` object.

Step 3: `IncludeCommand` is executed and calls the method `IncludeCommand#attemptToInclude()`.

Step 4: `IncludeCommand#attemptToInclude()` checks the command argument, `module` for the `include` command and calls
 `IncludeCommand#removeModuleFromExclusion()`

Step 5: `IncludeCommand` calls `Ui#getIncludedModuleName()` to obtain the name of the module that is to be included back into the scheduling process, so the user will enter `CS2113T` here

Step 6: `IncludeCommand` will then call `Storage#removeModuleFromExclusionFile()`, which will in turn call
 `StorageWrite#removeModuleFromExclusionFile()` to begin the process of Updating the Exclusion File.
 
Step 7: `StorageWrite` will load the contents of the Exclusion File into `excludedChapters` using `StorageLoad#loadExclusionFile()` and
 load the Chapters of the `CS2113T` by calling `StorageLoad#loadChaptersFromSpecifiedModule()`.
 
Step 8: `StorageWrite` will then check through the contents of the Exclusion file for each of the Chapters in `CS2113T`, and
 remove them from `excludedChapters` if found.
 
Step 9: After the removal of every Chapter in the `CS2113T` from `excludedChapters`, the contents of it are
 written back by `StorageWrite` into the Exclusion File by calling `StorageWrite#updateExclusionFile()`.

The following sequence Diagrams illustrates how the "include" command is executed:

<p align="center">
  <img src="DG_Images/includeSeq.png" width="1000" alt="Sequence Diagram of the include command"/>
  <br/>Figure 54. Sequence diagram using the exclusion feature to include content into the schedule
</p>

<br>

#### 4.5.4. Reschedule Chapter Feature

##### Implementation
The reschedule chapter feature allows the user to reschedule the due date of any existing chapter.

The user can reschedule the due date of an existing chapter with the `reschedule` command, which follows the following format: `reschedule CHAPTER_INDEX DATE(yyyy-MM-dd)`.

The reschedule chapter feature is facilitated by `ChapterList` and `Chapter`. The list of user's chapters are stored internally as `ChapterList`.
In addition, it implements the following operations:
* `ChapterList#getChapter()` - Gets a chapter based on the specified index from the list of chapters.
* `Chapter#setDueBy()` — Sets the due date of the chapter.

The following diagram shows the class diagram of the reschedule chapter feature:

<p align="center">
  <img src="DG_Images/RescheduleChapterClassDiagram.png" width="800" alt="Reschedule Chapter Class Diagram"/>
  <br/>Figure 55. Class diagram of reschedule chapter feature  
</p>
 
For instance, the user wants to reschedule the due date `2020-12-12` of the chapter `Chapter 1`  from the module `CS2113T`, a detailed description of what happens is shown below:

* Step 1: The user is currently in `CS2113T` at the module level.

* Step 2: The user enters `reschedule 1 2020-12-20` command to reschedule the first chapter in the list of chapters — which in this case is `Chapter 1`.

* Step 3: The user input is parsed by `Parser`, which results in a new `RescheduleCommand` object.

* Step 4: `RescheduleCommand` is executed and calls the method `RescheduleCommand#reschedule()`.

* Step 5: `RescheduleCommand#reschedule()` gets the chapter based on the index provided by the method `ChapterList#getChapter()`.

* Step 6: The due date of the chapter is rescheduled to `2020-12-20` by the method `Chapter#setDueBy()`.

* Step 7: The result message from `RescheduleCommand#reschedule()` is returned to `RescheduleCommand#execute()` and shown to the user by calling `Ui#showToUser()`.

The following sequence diagram shows how the reschedule chapter feature works:

<p align="center">
  <img src="DG_Images/RescheduleChapterSequenceDiagram.png" width="800" alt="Reschedule Chapter Sequence Diagram"/>
  <br/>Figure 56. Sequence diagram of reschedule chapter feature  
</p>

>:information_source: <b>Note:</b> The lifeline for `Module` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

#### 4.5.5. View Revision History Feature

##### Implementation
The view revision history feature allows the user to list the revision completed in the session/in a day. In case the user enters `history` follow by a `date(example: 2020-10-10)`, `Kaji` will list the revision completed in `October 10, 2020`. Otherwise, `Kaji` will list the revision completed in the day the user uses `HelpCommand`. This feature can be accessed at any level.

The history mechanism is facilitated by `HistoryCommand`. It extends from the abstract class `Command`. 

In addition, it implements the following operations:

* `HistoryCommand#execute()` — calls the list method to list the history. 
* `HistoryCommand#listHistory()` — lists the revision completed in the session/in a day.

The following diagram shows the class diagram of the list revision history feature:

<p align="center">
  <img src="DG_Images/listhistory_class_diagram.png" width="800" alt="List Revision History Class Diagram"/>
  <br/>Figure 57. Class diagram of list revision history feature  
</p>

Given below is an example usage scenario and how the history mechanism behaves at each step:

Step 1: The user launches the application and is currently in the admin level. 

Step 2: The user executes `history` command to load and list the revision completed in current day.

Step 3: The user can also execute `history 2020-11-09` command to load and list the revision completed in 2020-11-09.

The following sequence diagram shows how the list revision history feature works:

<p align="center">
  <img src="UML/listhistory_seq_diagram.png" width="800" alt="Sequence Diagram of List Revision History"/>
  <br/>Figure 58. Sequence Diagram of List Revision History
</p>

##### <a href="#top">Back to Top ^</a>

--------------------------------------------------------------------------------------------------------------------

## 5. Appendices
### 5.1. Appendix A: Product Scope
#### 5.1.1 Target User Profile

* Students who use a computer often, and are reasonably comfortable with the command line interface
* Needs to have an effective study schedule
* Willing to keep track of the content they need to study
* Prefers typing to mouse interactions

#### 5.1.2. Value Proposition

The application aims to provide students with an effective studying technique. 
In order to make studying easier for students, the application implements a technique known as spaced repetition, 
which help with memory retention. Content are scheduled automatically, and information is organised in the form of flashcards which makes it convenient to revise. 

##### <a href="#top">Back to Top ^</a>

### 5.2. Appendix B: User Stories

| Version | As a ... | I want to ... | So that I can ... |
|--------|----------|---------------|------------------|
| v1.0 | student | add modules | study based on modules |
| v1.0 | student | add chapters for each module | study based on chapters for a certain module |
| v1.0 | student | add flashcards inside each chapter for the content of each chapter | study the content of each chapter |
| v1.0 | student | list my modules | have an overview of what modules I have |
| v1.0 | student | list my chapters in each module | have an overview of what chapters I have for a module |
| v1.0 | student | list my flashcards in each chapter | have an overview of the content I have for a chapter |
| v1.0 | student | modify the module name | correct the module name if it is incorrect |
| v1.0 | student | modify the chapter name | correct the chapter name if it is incorrect |
| v1.0 | student | modify the content of the flashcards | correct the content if it is incorrect |
| v1.0 | student | remove modules/chapters/flashcard | remove modules/chapters/flashcards that I no longer need |
| v1.0 | student | access my module | revise based on module |
| v1.0 | student | access my chapters for a module | revise based on chapter for a certain module |
| v1.0 | student | revise the flashcards by chapter | do my revision |
| v1.0 | student | set a rating for a newly created chapter | rate a chapter without going through a revision session |
| v1.0 | student | state whether a flashcard is easy, medium or hard | revise the flashcards based of the difficulty of it |
| v1.0 | student | check the scheduled tasks daily | plan my schedule accordingly |
| v1.0 | student | rely entirely on the application to implement spaced repetition for me | enjoy the benefits of spaced repetition without having to implement it myself |
| v1.0 | student | have a help command | have an overview of how the application works |
| v1.0 | student | terminate the program | exit the application when I am done using it |
| v2.0 | student | see a forecast of the my revision schedule | plan ahead and make time to accommodate the revision of the corresponding number of chapters |
| v2.0 | student | reschedule a chapter | change the schedule according to my situation |
| v2.0 | student | view the module and chapters I have completed in a session/in a day | track my progress |
| v2.0 | student | get a sense of how well I have mastered each chapter | have an idea of how well I am doing for a module |
| v2.0 | student | include or exclude certain modules/chapters from the scheduler | revise only the modules/chapters that I need to |

##### <a href="#top">Back to Top ^</a>

### 5.3. Appendix C: Use Cases

This section will describe the use cases of KAJI. 

(For all use cases below, the **System** is the `KAJI` application and the **Actor** is the `user`, unless specified otherwise.)

#### Use Case: `add` a new `module`
**MSS**
1. User requests to add a new `module`.
2. KAJI creates and saves the new `module` with the `module` name specified by the user.

    Use case ends.
    
#### Use Case: `list` all current `module`s
**MSS**
1. User requests to list all current `module`s.
2. KAJI shows a list of `module`s available.

    Use case ends.
    
#### Use Case: `edit` a `module`
**MSS**
1. User requests to edit a `module`.
2. KAJI makes the changes and saves the `module` with the new `module` name specified by the user.

    Use case ends.
   
#### Use Case: `remove` a `module` from the current list of `module`s
**MSS**
1. User requests to remove an existing `module`.
2. KAJI deletes the `module` from the list of `module`s.

    Use case ends.
    
#### Use Case: `access` a `module` level
**MSS**
1. User requests to access a `module` level.
2. KAJI changes the current `admin` level of the user to the `module` level specified by the user.

    Use case ends.
    
#### Use Case: `revise` a `chapter` from the current list of `chapter`s in a particular `module`
**MSS**
1. User requests to revise a `chapter` from the current list of `chapter`s in a particular `module`.
2. KAJI starts a revision session for the user.

    Use case ends.
    
**Extensions**

User will be prompted to acknowledge that he wants to start a revision session for a `chapter` that is not due. <br>
There will be no revision for an empty `chapter`.

#### Use Case: view a list of `chapter`s which are `due`
**MSS**
1. User requests to view a list of `chapter`s which are `due`.
2. KAJI shows a list of `chapter`s that are `due` to the user.

    Use case ends.
    
#### Use Case: `preview` a list of `chapter`s that are `due` in the week
**MSS**
1. User requests to `preview` a list of `chapter`s that are `due` in the week.
2. KAJI shows a list of `chapter`s that are `due` in the week to the user.

    Use case ends.
    
#### Use Case: `exclude` an existing `module` or `chapter` 
**MSS**
1. User requests to `exclude` a `module` or `chapter`.
2. KAJI `exclude`s the `module` or `chapter` specified by the user so that it will no longer be scheduled for revision.

    Use case ends.
    
#### Use Case: `include` an existing `module` or `chapter` 
**MSS**
1. User requests to `include` a `module` or `chapter`.
2. KAJI `include`s the `module` or `chapter` specified by the user so that it will be scheduled for revision once again.

    Use case ends.
    
#### Use Case: `reschedule` the due date of an existing `chapter` 
**MSS**
1. User requests to `reschedule` the due date of an existing `chapter`.
2. KAJI `reschedule`s the due date of the `chapter` specified by the user.

    Use case ends.
    
#### Use Case: view the `history` of revision completed in a day 
**MSS**
1. User requests to view the `history` of revision completed in a day.
2. KAJI shows the `history` of revision completed by the user in a day.

    Use case ends.
    
#### Use Case: check overall performance for an existing chapter using `showrate` 
**MSS**
1. User requests to check overall performance for an existing chapter using `showrate`.
2. KAJI shows the list of number of cards that were rated easy/medium/hard in the chapter to the user.

    Use case ends.
   
**Extensions**

There will only be changes to the rating after a revision session. 

#### Use Case: view the list of commands available using `help`
**MSS**
1. User requests to view the list of commands available using `help`.
2. KAJI shows the list of commands available to the user.

    Use case ends.

##### <a href="#top">Back to Top ^</a>

### 5.4. Appendix D: Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 installed.
2. Should be able to hold up to 1000 flashcards without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Data saved in a session should be persistent and carry forward to the next session of use.
5. The file containing the saved data should be portable so that the user can transfer to the other machine with any mainstream OS and continuing using the app without any additional configuration.
6. Application should not crash, application should always recover from error gracefully with an error message.
7. Should work mostly without the need for the Internet.
8. Should be designed for a single user.
9. Should work on both 32-bit and 64-bit environments.

##### <a href="#top">Back to Top ^</a>

### 5.5. Appendix E: Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command Line Interface

##### <a href="#top">Back to Top ^</a>

### 5.6. Appendix F: Instructions for Manual Testing
#### 5.6.1. Launch and Shutdown
1. Initial launch
    1. Download the jar file from the [release page](https://github.com/AY2021S1-CS2113T-F11-3/tp/releases) and copy into an empty folder.
    2. Launch a terminal and navigate to the folder containing the jar file.
    3. Run the jar file by entering command `java -jar kaji.jar` and press enter (replace kaji.jar by the actual file name you downloaded from the release page).
       Expected output: Welcome message of **Kaji** is displayed.
    
2. Shutdown of application
    1. Test case: `exit`<br>
       Expected output: **KAJI** program terminates.
       
#### 5.6.2. Showing a list of commands
1. Prerequisites: Launch `KAJI` successfully.
2. Test case: `help`<br>
   Expected output: A message listing how to use each command will be shown.
3. Other incorrect commands to try: `help abcd` (where there are extra arguments)<br>
   Expected output: An error message stating that there should not be any arguments will be shown.

#### 5.6.3. Adding a module/chapter/flashcard
1. Adding a module
    1. Prerequisites: Ensure that you are at the admin level.
    2. Test case: `add CS2113T`<br>
       Expected output: A success message stating that there is a new module added will be shown.
    3. Test case: `add cs2113t` after running the `add` command as stated above<br>
       Expected output: An error message stating that the module already exist will be shown.
    4. Other incorrect commands to try: `add ../CS2113T` (where non-alphanumeric characters are present)<br>
       Expected output: An error message stating that only alphanumeric characters and spaces will be shown.
2. Adding a chapter
    1. Prerequisites: At least one module in the module list. Ensure that you are at the module level.
    2. Test case: `add Chapter 1`<br>
       Expected output: A success message stating that there is a new chapter added will be shown.
    3. Test case: `add chapter 1` after running the `add` command as stated above<br>
       Expected output: An error message stating that the chapter already exist will be shown.
    4. Other incorrect commands to try: `add ../Chapter 1` (where non-alphanumeric characters are present)<br>
       Expected output: An error message stating that only alphanumeric characters and spaces will be shown.
3. Adding a flashcard
    1. Prerequisites: At least one module and chapter in the module and chapter list respectively. Ensure that you are at the chapter level.
    2. Test case: `add q:1+1 | a:2`<br>
       Expected output: A success message stating that there is a new flashcard added will be shown.
    3. Test case: `add q:1+1 | a:2` after running the `add` command as stated above<br>
       Expected output: An error message stating that the flashcard already exist will be shown.
    4. Other incorrect commands to try: `add q:1+1 | a:` (where parameter is missing)<br>
       Expected output: An error message stating that the question or answer is missing will be shown.  

#### 5.6.4. Listing modules/chapters/flashcards
1. Listing all modules
    1. Prerequisites: At least one module in the list. No chapters in the module list. Ensure that you are at the module level.
    2. Test case: `list`<br>
       Expected output: A message stating that there are no chapters will be shown.
    3. Prerequisites: At least one module in the list. Chapter list contains some chapters. Ensure that you are at the module level.
    4. Test case: `list`<br>
       Expected output: A message listing all the chapters will be shown.
    3. Other incorrect commands to try: `list abcd` (where there are extra arguments)<br>
       Expected output: An error message stating that there should not be any arguments will be shown.
2. Listing all chapters
    1. Prerequisites: No modules in the module list. Ensure that you are at the admin level.
    2. Test case: `list`<br>
       Expected output: A message stating that there are no modules will be shown.
    3. Prerequisites: Module list contains some modules. Ensure that you are at the admin level.
    4. Test case: `list`<br>
       Expected output: A message listing all the modules will be shown.
    3. Other incorrect commands to try: `list abcd` (where there are extra arguments)<br>
       Expected output: An error message stating that there should not be any arguments will be shown.
3. Listing all flashcards
    1. Prerequisites: At least one module and chapter in the module and chapter list respectively. 
       No flashcards in the flashcard list. Ensure that you are at the chapter level.
    2. Test case: `list`<br>
       Expected output: A message stating that there are no flashcards will be shown.
    3. Prerequisites: At least one module and chapter in the module and chapter list respectively. 
       Flashcard list contains some flashcards. Ensure that you are at the chapter level.
    4. Test case: `list`<br>
       Expected output: A message listing all the flashcards will be shown.   
    5. Other incorrect commands to try: `list abcd` (where there are extra arguments)<br>
       Expected output: An error message stating that there should not be any arguments will be shown. 
 
#### 5.6.5. Editing a module/chapter/flashcard
1. Editing a module
    1. Prerequisites: At least one module in the module list. Ensure that you are at the admin level.
    2. Test case: `edit 1 CS2113`<br>
       Expected output: A message stating the module name before and after the edit will be shown.
    3. Test case: `edit 0 CS2113`<br>
       Expected output: No module is edited. An error message will be shown.
    4. Other incorrect commands to try: `edit`, `edit x CS2113` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.
2. Editing a chapter
    1. Prerequisites: At least one module and chapter in the module and chapter list respectively. 
       Ensure that you are at the module level.
    2. Test case: `edit 1 Chapter 2`<br>
       Expected output: A message stating the chapter name before and after the edit will be shown.
    3. Test case: `edit 0 Chapter 2`<br>
       Expected output: No chapter is edited. An error message will be shown.
    4. Other incorrect commands to try: `edit`, `edit x Chapter 2` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.
3. Editing a flashcard
    1. Prerequisites: At least one module, chapter and flashcard in the module, chapter and flashcard list respectively. 
       Ensure that you are at the chapter level.
    2. Test case: `edit 1 q:1+1 | a:`<br>
       Expected output: A message stating the flashcard content before and after the edit will be shown.
    3. Test case: `edit 0 q:1+1 | a:`<br>
       Expected output: No flashcard is edited. An error message will be shown.
    4. Other incorrect commands to try: `edit`, `edit x q:1+1 | a:` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.

#### 5.6.6. Removing a module/chapter/flashcard
1. Removing a module
    1. Prerequisites: At least one module in the module list. Ensure that you are at the admin level. List all modules using the `list` command.
    2. Test case: `remove 1`<br>
       Expected output: First module is removed from the module list. A message stating the details of the removed module will be shown.
    3. Test case: `remove 0`<br>
       Expected output: No module is removed. An error message will be shown.
    4. Other incorrect commands to try: `remove`, `remove x` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.
2. Removing a chapter
    1. Prerequisites: At least one module and chapter in the module and chapter list respectively. 
       Ensure that you are at the module level. List all modules using the `list` command.
    2. Test case: `remove 1`<br>
       Expected output: First chapter is removed from the chapter list. A message stating the details of the removed chapter will be shown.
    3. Test case: `remove 0`<br>
       Expected output: No chapter is removed. An error message will be shown.
    4. Other incorrect commands to try: `remove`, `remove x` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.
3. Removing a flashcard
    1. Prerequisites: At least one module, chapter and flashcard in the module, chapter and flashcard list respectively. 
       Ensure that you are at the chapter level. List all flashcards using the `list` command.
    2. Test case: `remove 1`<br>
       Expected output: First flashcard is removed from the chapter list. A message stating the details of the removed flashcard will be shown.
    3. Test case: `remove 0`<br>
       Expected output: No flashcard is removed. An error message will be shown.
    4. Other incorrect commands to try: `remove`, `remove x` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.

#### 5.6.7. Accessing the next level
1. Accessing module level
    1. Prerequisites: At least one module in the module list. Ensure that you are at the admin level.
    2. Test case: `go 1`<br>
       Expected output: Accessed the first module in the module list. Access level message will be changed.
    3. Test case: `go 0`<br>
       Expected output: Still at admin level. An error message will be shown.
    4. Other incorrect commands to try: `go`, `go x` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.
2. Accessing chapter level
    1. Prerequisites: At least one module and chapter in the module and chapter list respectively. 
       Ensure that you are at the module level.
    2. Test case: `go 1`<br>
       Expected output: Accessed the first chapter in the chapter list. Access level message will be changed.
    3. Test case: `go 0`<br>
       Expected output: Still at module level. An error message will be shown.
    4. Other incorrect commands to try: `go`, `go x` (where x is larger than the list size)<br>
       Expected output: Similar to previous or a message stating how to use the command will be shown.

#### 5.6.8. Going back to the previous level
1. Going back to admin level
    1. Prerequisites: Ensure that you are at module level.
    2. Test case: `back`<br>
       Expected output: Back to admin level. Access level message will be changed.
    3. Other incorrect commands to try: `back abcd` (where there are extra arguments)<br>
       Expected output: An error message stating that there should not be any arguments will be shown.
2. Going back to module level
    1. Prerequisites: Ensure that you are at chapter level.
    2. Test case: `back`<br>
       Expected output: Back to module level. Access level message will be changed.
    3. Other incorrect commands to try: `back abcd` (where there are extra arguments)<br>
       Expected output: An error message stating that there should not be any arguments will be shown.

#### 5.6.9. Rescheduling a chapter
1. Prerequisites: At least one module and chapter in the module and chapter list respectively. 
   Ensure that you are at the module level.
2. Test case: Substitute DATE with the current date<br>
   `reschedule 1 DATE`<br>
   Expected output: A message stating the due date of the chapter before and after the rescheduling will be shown.
3. Test case: Substitute DATE with the date before the current date<br>
   `reschedule 1 DATE`<br>
   Expected output: Chapter will not be rescheduled. A message stating that the due date cannot be before the current date will be shown.
4. Test case: Substitute DATE with the current date<br>
   `reschedule 0 DATE`<br>
   Expected output: No chapter will be rescheduled. An error message will be shown.
5. Other incorrect commands to try: `reschedule`, `reschedule x DATE` (where x is larger than the list size)<br>
   Expected output: Similar to previous or a message stating how to use the command will be shown.

#### 5.6.10. Starting a revision session
1. Prerequisites: At least one flashcard is in the chapter to be revised. Ensure that you are at the module level.
2. Test case: `revise 1` <br>
   Expected output: A revision session will start on the first chapter in the module.
3. Other incorrect commands to try: `revise Chapter 1` (where chapter name instead of its index integer is provided)<br>
   Expected output: An error message stating to specify chapter index in integer will be shown.
       
#### 5.6.11. Checking percentage of rating for the cards in a chapter
1. Prerequisites: At least one flashcard is in the chapter. Ensure that you are at the chapter level.
2. Test case: `showrate` <br>
   Expected output: A list of the percentage for easy/medium/hard based on the number of cards will be shown.
3. Other incorrect commands to try: `showrate blah` (where extra arguments are added)<br>
   Expected output: An error message stating that there should be no extra arguments will be shown.
       
#### 5.6.12. Listing all chapters that are due on current date
1. Prerequisites: Launch `KAJI` successfully.
2. Test case: `due` <br>
   Expected output: A list of chapters that are due on the current date will be shown. If no chapters are due, a message stating that no chapters are due for the day will be shown.
3. Other incorrect commands to try: `due blah` (where extra arguments are added)<br>
   Expected output: An error message stating that there should be no extra arguments will be shown.
       
#### 5.6.13. Previewing list of chapters due in a week
1. Prerequisites: Launch `KAJI` successfully.
2. Test case: `preview` <br>
   Expected output: A list of chapters that are due in a week will be shown. If no chapters are due, every date will be accompanied by a message that no chapters are due.
3. Other incorrect commands to try: `preview blah` (where extra arguments are added) <br>
   Expected output: An error message stating that there should be no extra arguments will be shown.

#### 5.6.14. Viewing history of revision completed in a day
1. Prerequisites: Launch `KAJI` successfully.
2. Test case: `history` <br>
   Expected output: Lists the module and chapter which you have completed revision for. If no revision was done on the day, a message stating that no revision for that session will be shown.
3. Test case: `history 2020-11-09`
   Expected output: List the module and chapter which you have completed revision for on 2020-11-09. If no revision was done on the day, a message stating that no revision for that session will be shown.
4. Other incorrect commands to try: `history blah` (where the argument is not in the correct date format) <br>
   Expected output: An error message stating that the date should be in `yyyy-MM-dd` format will be shown.
       
#### 5.6.15. Excluding a module/chapter
1. Excluding a module
    1. Prerequisites: Launch `KAJI` successfully. The module you want to exclude should exist.
    2. Test case: `exclude module` <br>
       Expected output: A prompt for which module to be excluded will be shown and you can enter an existing module which you want to exclude.
    3. Other incorrect commands to try: `exclude blah` (where the argument is not module/chapter)
       Expected output: An error message stating that only module/chapter should be specified will be shown.

2. Excluding a chapter
    1. Prerequisites: Launch `KAJI` successfully. The chapter you want to exclude should exist.
    2. Test case: `exclude chapter` <br>
       Expected output: A prompt for which chapter to be excluded will be shown and you can enter an existing chapter which you want to exclude.
    3. Other incorrect commands to try: `exclude blah` (where the argument is not module/chapter)
       Expected output: An error message stating that only module/chapter should be specified will be shown.
       
#### 5.6.16. Including a module/chapter
1. Including a module
    1. Prerequisites: Launch `KAJI` successfully. The module you want to include should exist.
    2. Test case: `include module` <br>
       Expected output: A prompt for which module to be included will be shown and you can enter an existing module which you want to include.
    3. Other incorrect commands to try: `include blah` (where the argument is not module/chapter)
       Expected output: An error message stating that only module/chapter should be specified will be shown.
       
2. Including a chapter
    1. Prerequisites: Launch `KAJI` successfully. The chapter you want to include should exist.
    2. Test case: `include chapter` <br>
       Expected output: A prompt for which chapter to be included will be shown and you can enter an existing chapter which you want to include.
    3. Other incorrect commands to try: `include blah` (where the argument is not module/chapter)
       Expected output: An error message stating that only module/chapter should be specified will be shown.
           
##### <a href="#top">Back to Top ^</a>
--------------------------------------------------------------------------------------------------------------------