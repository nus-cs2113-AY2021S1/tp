# Kaji - User Guide
By: `Team F11-3` Since: `August 2020`
## Table of content
1. [Overview](#1-overview)<br>
1.1. [About Kaji](#11-about-kaji)<br>
1.2. [About this User Guide](#12-about-this-user-guide)<br>
1.3. [Understanding the Command Line Interface (CLI)](#13-understanding-the-command-line-interface-cli)<br>
1.4. [Understanding Kaji](#14-understanding-kaji)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.4.1. [Content Management](#141-content-management)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.4.2. [Schedule Management](#142-schedule-management)<br>
2. [Quick Start](#2-quick-start)<br>
3. [Features](#3-features)<br>
3.1. [Admin Level](#31-admin-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.1. [Adding a module: `add`](#311-adding-a-module-add)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.2. [Listing modules available: `list`](#312-listing-modules-available-list)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.3. [Editing a module name: `edit`](#313-editing-a-module-name-edit)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.4. [Removing a module: `remove`](#314-removing-a-module-remove)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.5. [Accessing the module level: `go`](#315-accessing-the-module-level-go)<br>
3.2. [Module Level](#32-module-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.1. [Adding a chapter: `add`](#321-adding-a-chapter-add)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.2. [Listing chapters available: `list`](#322-listing-chapters-available-list)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.3. [Editing a chapter name: `edit`](#323-editing-a-chapter-name-edit)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.4. [Removing a chapter: `remove`](#324-removing-a-chapter-remove)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.5. [Accessing the chapter level: `go`](#325-accessing-the-chapter-level-go)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.6. [Returning to admin level: `back`](#326-returning-to-admin-level-back)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.7. [Starting a revision session: `revise`](#327-starting-a-revision-session-revise)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.8. [Rescheduling a chapter: `reschedule`](#328-rescheduling-a-chapter-reschedule)<br>
3.3. [Chapter Level](#33-chapter-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.1. [Adding a flashcard: `add`](#331-adding-a-flashcard-add)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.2. [Listing flashcards available: `list`](#332-listing-flashcards-available-list)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.3. [Editing a flashcard content: `edit`](#333-editing-a-flashcard-content-edit)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.4. [Removing a flashcard: `remove`](#334-removing-a-flashcard-remove)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.5. [Returning to module level: `back`](#335-returning-to-module-level-back)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.6. [Checking overall performance for a chapter: `showrate`](#336-checking-overall-performance-for-a-chapter-showrate)<br>
3.4. [General](#34-general)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.1. [Showing a list of commands available: `help`](#341-showing-a-list-of-commands-available-help)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.2. [Listing the chapters due for today: `due`](#342-listing-the-chapters-due-for-today-due)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.3. [Listing the chapters due in the upcoming week: `preview`](#343-listing-the-chapters-due-in-the-upcoming-week-preview)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.4. [Viewing the revision history: `history`](#344-viewing-the-revision-history-history)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.5. [Excluding or including modules and chapters: `exclude`](#345-excluding-or-including-modules-and-chapters-exclude)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.6. [Exiting the program: `exit`](#346-exiting-the-program-exit)<br>
4. [Command Summary](#4-command-summary)<br>
4.1. [Admin Level](#41-admin-level)<br>
4.2. [Module Level](#42-module-level)<br>
4.3. [Chapter Level](#43-chapter-level)<br>
4.4. [General](#44-general)<br>

--------------------------------------------------------------------------------------------------------------------

## 1. Overview
This section gives an overview about Kaji and the purpose of this user guide.

### 1.1. About Kaji
In your past learning experience, have you encountered these problems? A large number of lecture notes and materials have made your computer desktop messy, 
and there is no way to find the materials you want. When the exam is approaching, you don’t know which subject to review first, or suddenly find that you have 
forgotten everything you learned before. No one wants to forget what they have dedicated time to learn.

Don't worry! <strong>Kaji</strong> will help you solve all these problems!

KAJI is a schedule manager that implements Spaced Repetition, optimised for use via a Command Line Interface (CLI).

### 1.2. About this User Guide
This User Guide explains how to use Kaji. It provides an understanding of the features and commands, as well as some common use cases of this application.

In this guide, we cover:
* How to use the Command Line Interface (CLI)
* Syntax of the commands available in different levels in Kaji
* Common use cases for each command
* Summary of all the commands


### 1.3. Understanding the Command Line Interface (CLI)
A <b>command line interface (CLI)</b> is a text-based user interface (UI) used to view and manage computer files. Command line interfaces are also called command-line user interfaces, console user interfaces and character user interfaces.

### 1.4. Understanding Kaji

#### 1.4.1. Content Management

#### 1.4.2. Schedule Management

--------------------------------------------------------------------------------------------------------------------

## 2. Quick Start
To get started on this application, please perform the following steps:

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `Kaji` from [here](https://github.com/AY2021S1-CS2113T-F11-3/tp/releases).
1. Copy the file to an empty folder you want to use as the <I>home folder</I> for your Kaji.
1. Open a command window in the folder you saved Kaji and run the command `java -jar kaji.jar`. You should get the output as shown below: <br>
![Welcome screen](images/kaji.PNG)
1. Type the command in the command window and press Enter to execute it. 
   e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:
   * `help` : List commands available
   * `exit` : Exits the app.
1. Refer to [Features](#3-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features
This section introduces the syntax and usages of the commands for the features available in Kaji. 
In explaining the syntax, do take note of the following command format which applies to all Kaji commands:
* Words in `UPPER_CASE` are the parameters that you need to provide.
    * <b>Example:</b> In `add MODULE_NAME`, `MODULE_NAME` is a parameter which can be used as `add CS2113`.
* Parameters cannot be given in any order.
    * <b>Example:</b> In `edit MODULE_INDEX MODULE_NAME`, entering `edit CS2113 1` will result in an error as 
      the `MODULE_INDEX` and `MODULE_NAME` parameters are in the wrong order.

### 3.1. Admin Level

#### 3.1.1. Adding a module: `add`
(by Jiayi)

Welcome to the first feature of Kaji! In order to use this program, you will first need to create a module deck. Creating a new module deck in Kaji is like getting a new folder to store all the handouts for one module. It is important because it will helps you better manage the mess information you got. 

##### Format: `add \MODULE_CODE`
\MODULE_CODE is the name of the new module that you would like to create, such as `CS2113`, `Module 1` or `Biology`. 

##### Key Pointers:
* Kaji does not allow duplicate of module names, therefore, if the existing module has the same name as the new module, you will get an error message
* Kaji is not case-sentive, therefore, a new module named `module`  will be treated equally as module named `MODULE`. 


##### Example: 
Input: add CS2113
Output:
```
---------------------------------------------------------------------
admin
Enter command here: add CS2113
Command Type: add
Got it. I've added this module:
CS2113
Now you have 4 module(s) in the list.
---------------------------------------------------------------------
```

#### 3.1.2. Listing modules available: `list` 
(by Zeyu)

This command shows a list of modules on the admin level.

Format: `list`

Here are some key points:
* Do not need to add `admin` after `list`.
* All list commands have the same command word `list`. 

Example of usage: 
* At Admin Level: enter the command `list`.
* Here is the expected output:
![List Modules](UG_Images/list_module.png)
* After listing all modules, you can try all commands available in **Admin Level**.

#### 3.1.3. Editing a module name: `edit` 
(by Zeyu)

This command modifies the module name you want to change.

Format: `edit INDEX MODULE_NAME`

Here are some key points:
* You can only edit content on the level below the one you are on.
* Edit the name / content at the specified `INDEX`.
* The index refers to the index number shown in the displayed content list.
* The index **must be a positive integer** 1, 2, 3, …

Example of usage: 
* At Admin Level: enter `edit 1 CS2113T` changes current Module name at index 1 to CS2113T.
* Here is the expected output:
![Edit Module Name](UG_Images/edit_module.png)
* After editing the module name, you can try all commands available in **Admin Level**.

#### 3.1.4. Removing a module: `remove` 
(by Jia Ern)

Removes a module from Kaji.

Format: `remove MODULE_INDEX`

Here are some key pointers:
* Removes the module based on the index provided.
* `MODULE_INDEX` refers to the index number shown in the current module list. 
* Index provided **must be a positive integer** 1, 2, 3, ...

Example: <br>
For instance, you are currently at the admin level and want to remove the module `CS2113T`, the steps to do so are shown below:
* Step 1: Ensure you are at the admin level: <br>
![Remove Module 1](images/RemoveMod1.PNG)
* Step 2: Enter the command `remove 1` to remove the first module in the list which in this case is `CS2113T`: <br>
![Remove Module 2](images/RemoveMod2.PNG)
* Step 3: The module as well as the chapters and flashcards in it are removed, and the output message below will be shown: <br>
![Remove Module 3](images/RemoveMod3.PNG)

#### 3.1.5. Accessing the module level: `go`
(by Jiayi)

Now you have learnt how to create, edit and delete the module deck, let's move to the next page. You can now access the module deck you have created by using the command `go \MODULE_CODE`.

##### Format: `go \MODULE_CODE`
\MODULE_CODE is the name of the module that you have created, such as `CS2113`, `Module 1` or `Biology`. 

##### Key Pointers:
* Kaji only allow access to the existing modules that are shown in the list, therefore, module code that is deleted or has never been created will result in an error message.
* Kaji is not case-sentive, therefore, a module named `module`  will be treated equally as module named `MODULE`. 

##### Example: 
Input: `go CS2113`
Output:
```
---------------------------------------------------------------------
admin
Enter command here: go CS2113
Command Type: go
This is a new module, you can try to add chapters inside!
---------------------------------------------------------------------
admin/CS2113
Enter command here: 
```

### 3.2. Module level

#### 3.2.1. Adding a chapter: `add`
(by Jiayi)

You are now at the module level! This command allows you to create a new chapter inside your current module deck. It belongs to the module level you are currently in. It is like preparing an empty paper to write notes for a lecture. Let's create a new chapter inside the module!

##### Format: `add \CHAPTER_NAME`
\CHAPTER_NAME is the name of the new chapter that you would like to create, such as `Topic 1`, `Chapter 1` or `Newton's laws of motion`. 

##### Key Pointers:
* Similar to module, Kaji does not allow duplicate of chapter names, therefore, if the existing chapter has the same name as the new chapter, you will get an error message
* Kaji is not case-sentive, therefore, a new chapter named `chapter`  will be treated equally as module named `CHAPTER`. 

##### Example 1: 
Input:
* add Topic1
* N
Output:
```
---------------------------------------------------------------------
admin/CS2113
Enter command here: add Topic1
Command Type: add
Would you like to rate this new Chapter? (Y/N)
Enter command here: N
Got it. I've added this chapter:
Topic1
Now you have 1 chapter(s) in the list.
---------------------------------------------------------------------
admin/CS2113
Enter command here: 
```

##### Example 2: 
Input:
* add Topic1
* Y
* M
Output:
```
---------------------------------------------------------------------
admin/CS2113
Enter command here: add Topic1
Command Type: add
Would you like to rate this new Chapter? (Y/N)
Enter command here: Y
Please rate this new Chapter!
You have the options of: Easy(E), Medium(M) or Hard(H)
Would your choice be E, M or H?
Enter command here: M
Got it. I've added this chapter:
Topic1
Now you have 2 chapter(s) in the list.
---------------------------------------------------------------------
admin/CS2113
Enter command here: 
```

#### 3.2.2. Listing chapters available: `list`
(by Zeyu)

This command shows a list of chapters on the module level.

Format: `list`

Here are some key points:
* Do not need to add `module_name` after `list`.
* All list commands have the same command word `list`.

Example of usage: 
* At Module Level: enter the command `list`.
* Here is the expected output:
![List Chapters](UG_Images/list_chapter.png)
* The date in the bracket is the due date for each chapter.
* After listing all chapters, you can try all commands available in **Module Level**.

#### 3.2.3. Editing a chapter name: `edit`
(by Jane)

You can edit the name of an existing chapter from the list of chapters.
You can do so by using the `edit` command, followed by the edited name of the chapter.

**Format:** `edit CHAPTER_INDEX CHAPTER_NAME`

Here are some key pointers:
* `CHAPTER_INDEX` **must be a positive integer** 1, 2, 3, ...,
and must be a valid index number for a chapter as displayed from the list of chapters.
* `CHAPTER_NAME` is the edited name of your chapter.

**Example:**

Let's say you want to edit the chapter name to `Chapter 1` for the chapter `chap 1`.
1. Type `list` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/EditChapter1.png" width="600" alt="Edit Chapter 1"/>
2. From the list of chapters displayed, you can see that the chapter `CHAPTER_INDEX` is 1.<br>
<img src="UG_Images/EditChapter2.png" width="600" alt="Edit Chapter 2"/>
3. Next, you can type `edit 1 Chapter 1` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/EditChapter3.png" width="600" alt="Edit Chapter 3"/>
4. After the chapter name has been successfully edited, the result will be displayed as shown.<br>
<img src="UG_Images/EditChapter4.png" width="600" alt="Edit Chapter 4"/>

#### 3.2.4. Removing a chapter: `remove`
(by Jia Ern)

Removes a chapter from Kaji.

Format: `remove CHAPTER_INDEX`

Here are some key pointers:
* Removes the chapter based on the index provided.
* `CHAPTER_INDEX` refers to the index number shown in the current chapter list. 
* Index provided **must be a positive integer** 1, 2, 3, ...

Example: <br>
For instance, you are currently at the module level `CS2113T` and want to remove the chapter `Chapter 1`, the steps to do so are shown below:
* Step 1: Ensure you are at the module level: <br>
![Remove Chapter 1](images/BackMod1.PNG)
* Step 2: Enter the command `remove 1` to remove the first chapter in the list which in this case is `Chapter 1`: <br>
![Remove Chapter 2](images/RemoveChap2.PNG)
* Step 3: The chapter and the flashcards in it are removed, and the output message below will be shown: <br>
![Remove Chapter 3](images/RemoveChap3.PNG)

#### 3.2.5. Accessing the chapter level: `go` 
(by Yan An)

Proceeds to the Chapter Level with reference to one of the Chapters within the module.

Format: `go CHAPTER_NAME`
 
Example: <br>
For instance, you are currently in Module level `Module` and want to head to Chapter level `Chapter1` , the steps to
 do so are shown below:

* Step 1: Enter the command `go Chapter1` to head down to the Chapter level below: <br>

<p align="center">
  <img src="UG_Images/moduleGo.png" alt="Go Command"/>
  <br/>Figure <>. Example of the "go" command 
</p>

* Step 2: You should return to the Admin level as shown below: <br>

<p align="center">
  <img src="UG_Images/moduleGoResult.png" alt="Go Result"/>
  <br/>Figure <>. Example of the "go" command Result
</p>


#### 3.2.6. Returning to admin level: `back`
(by Yan An)

Returns to the Admin level.

Format: `back`
 
Example: <br>
For instance, you are currently in Module level `Module` and want to return to the Admin level, the steps to do so
 are shown below:

* Step 1: Enter the command `back` to return to the previous level which is the module level: <br>

<p align="center">
  <img src="UG_Images/back.png" alt="Back Command"/>
  <br/>Figure <>. Example of the "back" command 
</p>

* Step 2: You should return to the Admin level as shown below: <br>

<p align="center">
  <img src="UG_Images/backComplete.png" alt="Back Result"/>
  <br/>Figure <>. Example of the "back" command Result
</p>

#### 3.2.7. Starting a revision session: `revise`
(by Jia Ern)

Starts a revision session for a chapter.

Format: `revise CHAPTER_INDEX` 

Here are some key pointers: 
* Revision can only be done at module level. 
* Starts a revision based on the index provided. 
* The index refers to the index number shown in the chapter list for the module level you are currently in.  
* Index provided **must be a positive integer** 1, 2, 3, ...

Example: <br>
For instance, you are currently in the module level `CS2113T` and want to start a revision for `Chapter 1`, the steps to do so are shown below:
* Step 1: Ensure you are at the module level: <br>
![Revise 1](images/BackMod1.PNG)
* Step 2: Enter the command `revise 1` to start a revision on the first chapter in the list which in this case is `Chapter 1`: <br>
![Revise 2](images/Revise2.PNG)
* Step 3: If the chapter is not due for revision yet, you will be shown the below message: <br>
![Revise 3](images/Revise3.PNG)
* Step 4: Enter `Y` to start the revision.
* Step 5: The message below will be shown at the start of the revision: <br>
![Revise 4](images/Revise4.PNG)
* Step 6: The question of the flashcard will be shown: <br>
![Revise 5](images/Revise5.PNG)
* Step 7: Enter `s` to see the answer for the flashcard: <br>
![Revise 6](images/Revise6.PNG)
* Step 8: Based on the difficulty of the flashcard, you may enter either `e`/`m`/`h`/`c` to rate the flashcard as shown below: <br>
![Revise 7](images/Revise7.PNG)
* Step 9: If you entered `c`, the same flashcard will be shown again after your last flashcard, and the process will repeat until you enter `e`/`m`/`h` for the particular flashcard you could not answer for.  
* Step 10: Once all the flashcards have been revised, the output message below will be shown: <br>
![Revise 8](images/Revise8.PNG)

#### 3.2.8. Rescheduling a chapter: `reschedule`
(by Jane)

You can reschedule the due date of an existing chapter from the list of chapters.
Rescheduling a chapter allows you to reschedule a chapter to an earlier or later date than the specified due date.
You can do so by using the `reschedule` command, followed by the due date that you want to reschedule the chapter to.

**Format:** `reschedule CHAPTER_INDEX DATE`

Here are some key pointers:
* `CHAPTER_INDEX` **must be a positive integer** 1, 2, 3, ...,
and must be a valid index number for a chapter as displayed from the list of chapters.
* `DATE` is the rescheduled due date of your chapter.
* `DATE` should be in the format `yyyy-MM-dd`.

**Example:**

Let's say you want to reschedule the due date to `2020-12-20` for the chapter `Chapter 1`.
1. Type `list` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/RescheduleChapter1.png" width="600" alt="Reschedule Chapter 1"/>
2. From the list of chapters displayed, you can see that the chapter `CHAPTER_INDEX` is 1.<br>
<img src="UG_Images/RescheduleChapter2.png" width="600" alt="Reschedule Chapter 2"/>
3. Next, you can type `reschedule 1 2020-12-20` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/RescheduleChapter3.png" width="600" alt="Reschedule Chapter 3"/>
4. After the due date of the chapter has been successfully rescheduled, the result will be displayed as shown.<br>
<img src="UG_Images/RescheduleChapter4.png" width="600" alt="Reschedule Chapter 4"/>

### 3.3. Chapter Level

#### 3.3.1. Adding a flashcard: `add`
(by Jane)

After adding a new chapter, the first thing you might want to do is to add a flashcard to the chapter.
You can do so by using the `add` command, followed by the details of the flashcard.

**Format:** `add q:QUESTION | a:ANSWER`

Here are some key pointers:
* `QUESTION` is the question of your flashcard.
* `ANSWER` is the answer of your flashcard.
* You need to type `q:` before the `QUESTION` parameter. 
* You need to type `a:` before the `ANSWER` parameter.
* Having `|` between `q:QUESTION` and `a:ANSWER` is required.

**Example:**

Let's say you want to add a new flashcard with `1+1` as the `QUESTION` and `2` as the `ANSWER`:
1. Type `add q:1+1 | a:2` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/AddCard1.png" width="600" alt="Add Card 1"/>
2. After the flashcard has been successfully added to the chapter, the result will be displayed as shown.<br>
<img src="UG_Images/AddCard2.png" width="600" alt="Add Card 2"/>

#### 3.3.2. Listing flashcards available: `list`
(by Jane)

After adding flashcards to the chapter, you can view the list of flashcards that you have for the chapter.
You can do so by using the `list` command.

**Format:** `list`

Here are some key pointers:
* You cannot type in any parameters after the `list` command.

**Example:**

Let's say you want to view all the flashcards for a chapter:
1. Type `list` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/ListCard1.png" width="600" alt="List Card 1"/>
2. The result for the list of flashcards will be displayed as shown.<br>
<img src="UG_Images/ListCard2.png" width="600" alt="List Card 2"/>

#### 3.3.3. Editing a flashcard content: `edit`
(by Jane)

You can edit the question and/or answer of an existing flashcard from the list of flashcards.
You can do so by using the `edit` command, followed by the details of the flashcard.

**Format:** `edit FLASHCARD_INDEX q:QUESTION | a:ANSWER`

Here are some key pointers:
* `FLASHCARD_INDEX` **must be a positive integer** 1, 2, 3, ...,
and must be a valid index number for a flashcard as displayed from the list of flashcards.
* `QUESTION` is the edited question of your flashcard.
* `ANSWER` is the edited answer of your flashcard.
* You need to type `q:` before the `QUESTION` parameter. 
* You need to type `a:` before the `ANSWER` parameter.
* Having `|` between `q:QUESTION` and `a:ANSWER` is required.
* If the question or answer of your flashcard does not need to be edited,
you do not need to type any content for the parameter `QUESTION` or `ANSWER`.

**Example:**

Let's say you want to edit the question to `2*1` for the flashcard that has `1+1` as the question and `2` as the answer.
1. Type `list` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/EditCard1.png" width="600" alt="Edit Card 1"/>
2. From the list of flashcards displayed, you can see that the flashcard `FLASHCARD_INDEX` is 1.<br>
<img src="UG_Images/EditCard2.png" width="600" alt="Edit Card 2"/>
3. Next, you can type `edit 1 q:2*1 | a:` into the command prompt and press `Enter` to execute it.<br>
<img src="UG_Images/EditCard3.png" width="600" alt="Edit Card 3"/>
4. After the flashcard has been successfully edited, the result will be displayed as shown.<br>
<img src="UG_Images/EditCard4.png" width="600" alt="Edit Card 4"/>

#### 3.3.4. Removing a flashcard: `remove`
(by Jia Ern)

Removes a flashcard from Kaji.

Format: `remove FLASHCARD_INDEX`

Here are some key pointers:
* Removes the flashcard based on the index provided.
* `FLASHCARD_INDEX` refers to the index number shown in the current flashcard list. 
* Index provided **must be a positive integer** 1, 2, 3, ...

Example: <br>
For instance, you are currently at the chapter level `Chapter 1` and want to remove the flashcard `[Q] 1+1= | [A] 2`, the steps to do so are shown below:
* Step 1: Ensure you are at the chapter level: <br>
![Remove Card 1](images/RemoveCard1.PNG)
* Step 2: Enter the command `remove 1` to remove the first flashcard in the list which in this case is `[Q] 1+1= | [A] 2`: <br>
![Remove Card 2](images/RemoveCard2.PNG)
* Step 3: The flashcard is removed, and the output message below will be shown: <br>
![Remove Card 3](images/RemoveCard3.PNG)

#### 3.3.5. Returning to module level: `back`
(by Jia Ern)

Returns to the module level.

Format: `back`
 
Example: <br>
For instance, you are currently in chapter level `Chapter 1` and want to return to the module level `CS2113T`, the steps to do so are shown below:
* Step 1: Ensure you are at the chapter level: <br>
![Back Module 1](images/ReturnMod1.PNG)
* Step 2: Enter the command `back` to return to the previous level which is the module level: <br>
![Back Module 2](images/BackMod2.PNG)
* Step 3: You should return to the module level as shown below: <br>
![Back Module 3](images/BackMod3.PNG)

#### 3.3.6. Checking overall performance for a chapter: `showrate`
(by Jiayi)

Congradulations! You have learnt how to add flashcards and revise your flash cards, and now is the time to **check your overall performance** for a chapter.

It is very simple, all you have to do is to enter the command [`showrate`](#) in prompt. 

##### Format: `showrate`


##### Key Pointers:
* This command is only accessible in the chapter level. You will gets an error message if you are in the wrong access level.
* Only overall performance of your current chapter level is computed.
>:information_source: <b>Note:</b> As you now know, it is the level that you can add/edit/delete cards.

##### Example: 
Input: showrate
Output:
```
---------------------------------------------------------------------
admin/CS2113/Topic1
Enter command here: showrate
Command Type: showrate

Card count: 2
The percentage of card that is labeled <easy> is: 1.00
The percentage of card that is labeled <medium> is: 0.00
The percentage of card that is labeled <hard> is: 0.00
The percentage of card that is labeled <cannot answer> is: 0.00
---------------------------------------------------------------------
admin/CS2113/Topic1
Enter command here: 
```

### 3.4. General

#### 3.4.1. Showing a list of commands available: `help`
(by Zeyu)

This command shows a list of commands available.

Format: `help`

Here is a key point:
* This command can be **called from any Level**.

Example of usage: 
* At Any Level: enter the command `help`.
* Here is part of the expected output, the whole output is a list of all commands useage:
![Help List Beginning](UG_Images/help1.png)
![Help list Ending](UG_Images/help2.png)
* After knowing waht are the commands, you can try any commands on the correct level.


#### 3.4.4. Viewing the revision history: `history`
(by Zeyu)

You can view the revision completed in the session/in a day by using this command.

Format:<br>
`history`<br>
`history DATE`<br>

Here are some key points:
* This command can be **called from any Level**.
* If you enter `history`, Kaji will show the revision completed today (the day you enter `history`).
* If you enter `history DATE`, the `DATE` need to be in the format of yyyy-mm-dd, then Kaji will show the revision completed on the given date.

Example of usage (`history` format): 
* At Any Level: enter the command `history`.
* Here is the expected output:
![History List](UG_Images/history.png)

Example of usage (`history DATE` format): 
* At Any Level: enter the command `history 2020-10-30`.
* Here is the expected output:
![History List](UG_Images/history_Date.png)
* After knowing the revision you have completed, you can try any commands on the correct level.

#### 3.4.6. Exiting the program: `exit`
(by Zeyu)

You can exit Kaji by using this command.

Format: `exit`

Example of usage: 
* At Any Level: enter the command `exit`
* Here is the expected output:
![Exit Kaji](UG_Images/exit.png)

### 4.5 Scheduling In KAJI
(by Yan An)

Now that you know how to make KAJI manage your Database of revision content for you, **what about scheduling?** For your benefit, the **scheduling** in KAJI is mostly **automated**! 

**You don't have to do a thing** to enjoy the benefits of Spaced Repetition. Everything is scheduled for you, so all you have to do is to use the commands [`due`](#) and [`preview`](#) to view what chapters are due and complete the revision  for them accordingly. 

Despite that, this **does not mean that you cannot customise** the scheduling process. KAJI allows you to [`reschedule`](#451) and [`exclude`](#451) Chapters manually if you wish to do so, but more on that later. First, let us get into the specific introduction of each command.
<br><br>

### 4.5.1. Listing the chapters due for today: `due`
(by Yan An)

As you now know, **each Chapter will be scheduled** to be due on a date. However, it will be **tedious** for you to go through each chapter **one by one** to find their deadlines. Our **solution** to that, is the `due` command.

The `due` command simplifies the process for by **showing you the Chapters that you have scheduled on that day** and the Modules they belong to.
<br><br>


#### Format: `due`


#### Key Pointers:
* There are **no parameters** for this command.
* This command can be **called from any Level**

#### Example: 
At any point, if you want to **see what Chapters are due**, all you have to do is enter the `due` command. Below is an example of an execution of the `due` command.

* ***Step 1***: Key the **`due`** command **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/due.png" alt="Due Command"/>
  <br/>Figure <>. Example of the "due" command 
</p>

* ***Upon completion***: This is what you will see:

<p align="center">
  <img src="UG_Images/dueComplete.png" alt="Due Command Result"/>
  <br/>Figure <>. Example of the "due" result
</p>

[Labeled expected output]

<br><br>

### 4.5.2. Listing the chapters that are due in the upcoming week: `preview`
(by Yan An)

Beyond simply being able to view the Chapters that are due on the day itself, what if you would like to **view your upcoming revision schedule** so that you can **plan ahead**? For that specific purpose, we have the `preview` command.

The `preview` command shows you **the Chapters that you have scheduled for each day of the upcoming week** and the Modules that they belong to.
<br>

#### Format: `preview`

#### Key Pointers:
* There are **no parameters** for this command.
* This command can be **called from any Level**


#### Example: 
At any point, if you **want to see a preview of which Chapters are going to be due within the upcoming week**, all you have to do is enter the `preview` command. Below is an example of an execution of the `preview` command.

* ***Step 1***: Key the **`preview`** command **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/preview.png" alt="Preview Command"/>
  <br/>Figure <>. Example of the "preview" command  
</p>

* ***Upon completion***: This is what you will see:

<p align="center">
  <img src="UG_Images/previewComplete.png" alt="Preview Command Result"/>
  <br/>Figure <>. Example of the "preview" result
</p>

[Labeled expected output]

<br><br>

### 4.5.3. Adding chapters to your list of Excluded Modules: `exclude`
(by Yan An)

Apart from viewing your schedule, what if the Semester has ended and you would like to take a Module or Chapter out of your schedule? If you were to remove the files from your database completely, it would be really tedious to add the content back Card by Card. Therefore, we created the `exclude` command.

The `exclude` command allows you to **add** a single Chapter or every Chapter from a Module to your Exclusion list so that you can **remove these items** from your schedule.
<br>

#### Format: `exclude MODULE_OR_CHAPTER`
The `exclude` command has two options for MODULE_OR_CHAPTER:
* ***module***: This option allows you to use `exclude` to add all the Chapters from a Module into the Exclusion List.
* ***chapter***: This option allows you to use `exclude` to add a Chpater into the Exclusion List.


#### Key Pointers:
* This command can be **called from any Level**
* This command **checks if the Chapter/Module** you are adding into the Exclusion List **exists**, so the List will not be filled with non-existing exclusions.
* Do note that the name of the Chapter/Module you provide has to be in the correct case as our check is **case-sensitive**.

#### Example
At any point, if you would like to **add to your Exclusion List**, all you have to do is enter the `exclude` command with the choice of "module" or "chapter" in the format specified above. Below are examples of the execution of the `exclude` command using both options.

Example of ***`exclude module`***
* ***Step 1***: Key the **`exclude module`** command **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/excludeModule.png" alt="Exclude Command Module mode: Command"/>
  <br/>Figure <>. Example of Exclude Command Module mode: Command
</p>

* ***Step 2***: Key the **Module name** that you wish to exclude from your schedule **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/excludeModuleModulePrompt.png" alt="Exclude Command Module mode: ModuleName Prompt"/>
  <br/>Figure <>. Example of Exclude Command Module mode: ModuleName Prompt
</p>

<p align="center">
  <img src="UG_Images/excludeModuleModuleFilled.png" alt="Exclude Command Module mode: ModuleName Filled"/>
  <br/>Figure <>. Example of Exclude Command Module mode: ModuleName Filled
</p>

* ***Upon completion***: This is what you will see:

<p align="center">
  <img src="UG_Images/excludeModuleResult.png" alt="Exclude Command Module mode: Result"/>
  <br/>Figure <>. Example of Exclude Command Module mode: Result
</p>

Example of ***`exclude chapter`***
* ***Step 1***: Key the **`exclude chapter`** command **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/excludeChapter.png" alt="Exclude Command Chapter mode: ChapterName Command"/>
  <br/>Figure <>. Example of Exclude Command Chapter mode: ChapterName Command
</p>

* ***Step 2***: Key the **Module name** of the Module that contains Chapter that you wish to exclude from your schedule **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/excludeChapterModulePrompt.png" alt="Exclude Command Module mode: ModuleName Prompt"/>
  <br/>Figure <>. Example of Exclude Command Chapter mode: ModuleName Prompt
</p>

<p align="center">
  <img src="UG_Images/excludeChapterModuleFilled.png" alt="Exclude Command Module mode: ModuleName Filled"/>
  <br/>Figure <>. Example of Exclude Command Chapter mode: ModuleName Filled
</p>

* ***Step 3***: Key the **Chapter name** that you wish to exclude from your schedule **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/excludeChapterChapterPrompt.png" alt="Exclude Command Chapter mode: ChapterName Prompt"/>
  <br/>Figure <>. Example of Exclude Command Chapter mode: ChapterName Prompt
</p>

<p align="center">
  <img src="UG_Images/excludeChapterChapterFilled.png" alt="Exclude Command Chapter mode: ChapterName Filled"/>
  <br/>Figure <>. Example of Exclude Command Chapter mode: ChapterName Filled
</p>

* ***Upon completion***: This is what you will see:

<p align="center">
  <img src="UG_Images/excludeChapterResult.png" alt="Exclude Command Chapter mode: Result"/>
  <br/>Figure <>. Example of Exclude Command Chapter mode: Result
</p>



### 4.5.4. Removing chapters from the list of Excluded Modules: `include`
(by Yan An)

However, what should be done if you had excluded the Chapters of a Module from your schedule, only to find that it is a prerequisite for another module the next semester. To prepare for the upcoming semester, you would like to revise the excluded content again. This is why we created the `include` command.

The `include` command allows you to **remove** a single Chapter or every Chapter from a Module from your Exclusion list so that you can **add these items** back into your schedule.
<br>

#### Format: `include MODULE_OR_CHAPTER`
The `include` command has two options for MODULE_OR_CHAPTER:
* ***module***: This option allows you to use `include` to remove all the Chapters from a Module from the Exclusion List.
* ***chapter***: This option allows you to use `include` to remove a Chpater from the Exclusion List.

#### Key Pointers:
* This command can be **called from any Level**
* Do note that the name of the Chapter/Module you provide has to be in the correct case as our check is **case-sensitive**.

#### Example
At any point, if you wish to **remove from your Exclusion List**, you can either enter the `include` command with the choice of either "chapter" or "module" in the format specified above. Below are examples of the command's execution using both options.

Example of ***`include module`***
* ***Step 1***: Key the **`include module`** command **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/includeModule.png" alt="Include Command Module mode: ModuleName Command"/>
  <br/>Figure <>. Example of Include Command Module mode: ModuleName Command
</p>

* ***Step 2***: Key the **Module name** that you wish to include back into your schedule **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/includeModuleModulePrompt.png" alt="Include Command Module mode: ModuleName Prompt"/>
  <br/>Figure <>. Example of Include Command Module mode: ModuleName Prompt
</p>

<p align="center">
  <img src="UG_Images/includeModuleModuleFilled.png" alt="Include Command Module mode: ModuleName Filled"/>
  <br/>Figure <>. Example of Include Command Module mode: ModuleName Filled
</p>

* ***Upon completion***: This is what you will see:

<p align="center">
  <img src="UG_Images/includeModuleResult.png" alt="Include Command Module mode: Result"/>
  <br/>Figure <>. Example of Include Command Module mode: Result
</p>


Example of ***`exclude chapter`***
* ***Step 1***: Key the **`exclude chapter`** command **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/includeChapter.png" alt="Include Command Chapter mode: ChapterName Command"/>
  <br/>Figure <>. Example of Include Command Chapter mode: ChapterName Command
</p>

* ***Step 2***: Key the **Module name** of the Module that contains the Chapter that you wish to include back into your schedule **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/includeChapterModulePrompt.png" alt="Include Command Chapter mode: ModuleName Prompt"/>
  <br/>Figure <>. Example of Include Command Chapter mode: ModuleName Prompt
</p>

<p align="center">
  <img src="UG_Images/includeChapterModuleFilled.png" alt="Include Command Chapter mode: ModuleName Filled"/>
  <br/>Figure <>. Example of Include Command Chapter mode: ModuleName Filled
</p>

* ***Step 3***: Key the **Chapter name** that you wish to include back into your schedule **into the prompt** as shown below and **press *[Enter]***

<p align="center">
  <img src="UG_Images/includeChapterChapterPrompt.png" alt="Include Command Chapter mode: ChapterName Prompt"/>
  <br/>Figure <>. Example of Include Command Chapter mode: ChapterName Prompt
</p>

<p align="center">
  <img src="UG_Images/includeChapterChapterFilled.png" alt="Include Command Chapter mode: ChapterName Filled"/>
  <br/>Figure <>. Example of Include Command Chapter mode: ChapterName Filled
</p>

* ***Upon completion***: This is what you will see:

<p align="center">
  <img src="UG_Images/includeChapterResult.png" alt="Include Command Chapter mode: Result"/>
  <br/>Figure <>. Example of Include Command Chapter mode: Result
</p>

--------------------------------------------------------------------------------------------------------------------

## 4. Command Summary

### 4.1. Admin Level

| Action | Format, Examples |
|--------|------------------|
| Add Module | `add MODULE_NAME` <br> e.g. `add CS2113` |
| List Modules | `list` |
| Edit Module Name | `edit INDEX MODULE_NAME` <br> e.g. `edit 1 CS2113` |
| Remove Module | `remove MODULE_INDEX` <br> e.g. `remove 1` |
| Access Module Level | `go MODULE_NAME` <br> e.g. `go CS2113` |

### 4.2. Module Level

| Action | Format, Examples |
|--------|------------------|
| Add Chapter | `add CHAPTER_NAME` <br> e.g. `add Chapter 1` |
| List Chapters | `list` |
| Edit Chapter Name | `edit INDEX CHAPTER_NAME` <br> e.g. `edit 1 Chapter 1` |
| Remove Chapter | `remove CHAPTER_INDEX` <br> e.g. `remove 1` |
| Access Chapter Level | `go CHAPTER_NAME` <br> e.g. `go Chapter 1` |
| Return Admin Level | `back` |
| Revise | `revise CHAPTER_INDEX` <br> e.g. `revise Chapter 1` |
| Reschedule | `reschedule CHAPTER_INDEX DATE` <br> e.g. `reschedule 1 2020-11-03` |

### 4.3. Chapter Level

| Action | Format, Examples |
|--------|------------------|
| Add Flashcard | `add q:QUESTION I a:ANSWER` <br> e.g. `add q:1+1 I a:2` |
| List Flashcards | `list` |
| Edit Flashcard | `edit INDEX q:QUESTION I a:ANSWER` <br> e.g. `edit 1 q:2*1 I a:2` |
| Remove Flashcard | `remove FLASHCARD_INDEX` <br> e.g. `remove 1` |
| Return Module Level | `back` |
| Show Rate | `showrate` |

### 4.4. General

| Action | Format, Examples |
|--------|------------------|
| Help | `help` |
| Show Revision History | `history` or `history DATE` <br> e.g. `history 2020-11-03` |
| Exit | `exit` |

### 4.5. Scheduling

| Action | Format, Examples |
|--------|------------------|
| List Due Chapters | `due` |
| Preview | `preview` |
| Exclude | `exclude PRIMARY_OPTION` <br> e.g. `exclude more` or `exclude less` |