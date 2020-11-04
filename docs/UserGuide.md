# User Guide

* Table of Contents
{:toc}

## Introduction

**Welcome to Study It User Guide!**

Study It is an interactive desktop app that helps manage your study related matters, 
optimized for use via a Command Line Interface (CLI). It can keep track of your various study matters, 
present them in an organized and thoughtful manner to help you organize your study life. 
It will also provide various functionalities to help with your studies. You will be able to make full 
use of the application if you can type fast.

This application currently has the following modes that you can access and use:
1. Main menu 	-   Central location of the app
1. Bookmark 	- 	Manage your online links at one convenient location
1. Timetable 	- 	Keep track of your weekly activities
1. Academic	- 	Track your academic performance and store useful contact information
1. Flashcard	- 	Memorize your study content better with our built-in flashcard

## About
This app is targeted towards National University of Singapore (NUS) students studying in this current online 
environment. This user guide will be providing information about how to use the Study It app by 
showing you a step-by-step guide to access each functionality the app provides. 
By utilizing these features, you will be able to manage your study matters efficiently!

The following is a list of common notations and symbols you’ll encounter throughout the user guide:

1. INDEX NUMBER
   
    Words of phrases in full capital indicates that they are inputs that you can define
   
1. `command`

    words or phrases highlighted indicate that they are actual commands you can use in the app
    
1. [website link](https://ay2021s1-cs2113t-t12-1.github.io/tp/UserGuide.html)
   
   Underlined words or phrases in blue colour are website links that you can press on to access the website

## Getting Started

Let’s get started with using Study It! This section will show you how you can run our app on your computer.

Firstly, please ensure that your computer fulfills the following requirement:
* Has Java 11.0.8
  * You may refer to this website to download the Java 11.0.8 installer
  * The installation guide can be found in the following websites:
    * Windows
    * Linux 
    * MacOS

Once you are done installing Java 11.0.8 onto your PC, you may follow these procedures to start using Study It:

1. Download the tP.jar file from our Github release and place it in an empty folder
1. Open your computer’s command prompt
   1. Windows: press Windows key + R, type “cmd” and press Enter
   1. MacOS: press Command - spacebar to launch Spotlight and type "Terminal," then double-click the search result
   1. Linux Ubuntu: press Ctrl + Alt + T
1. Change the directory of the command prompt to the folder containing tP.jar file
1. Type java -jar tp.jar into the command prompt and press Enter to execute it
1. If the application runs successfully, you’ll be greeted by the following welcome message:

![WelcomeMessage](Images/GeneralUG/welcomemessage.png)

Congratulations! You are now able to run Study It. To access its various functionalities, \
you would need to type in the command at the Command Prompt and press Enter. 
Whenever you feel lost, refer to the following sections of this User Guide or call the help function in the app 
for guidance. We hope that you have fun using our app!

## General Commands

The following section details the general commands you can use in the app. 
These commands can be used anywhere in the app to help you navigate around the app.

### 1. Prints help message: `help`

The app will print out a summarised list of functions you can call at the current mode. 
This help message will vary depending on which mode you are at. 
Use this command to get guidance on what you can do with the app!

Instruction:
1. Type `help` and press Enter

Expected output:

At main menu:
![HelpMessage](Images/GeneralUG/help.png)

At book mode:
![BookmarkHelp](Images/GeneralUG/bookmarkhelp.png)

---
**Useful information:**
* The message varies in different modes to show you the commands at that mode. 
Try the command at other modes when you are using the application!
---

### 2. Changes the current mode: `cd MODE_NAME or MODE_INDEX`

You can change to different modes of the app by using this command! 
The following are the modes currently available in the app and their corresponding index:
1. Main menu
1. Bookmark
1. Timetable
1. Academic
1. Flashcard

Instruction:
1. Type `cd` followed by the `MODE_NAME` or `MODE_INDEX`
1. Press Enter

Expected output:

Command: `cd 2`

![CDBookmark](Images/GeneralUG/cdBookmark.png)

Command: `cd academic`

![CDAcademic](Images/GeneralUG/cdacademic.png)

---
**Warning**
* An error message will appear when you type an invalid mode index or mode name

![WrongModeIndex](Images/GeneralUG/wrongmodeindex.png)

* Ensure that your index is within range or you spelt the mode names correctly when using this command!
---

---
**Useful information:**
* You can use this command anywhere in the app to switch between modes quickly!
---

### 3. Shows the current location: `location`

Calling this command will show you which mode you are currently at.

Instruction:
1. Type `location` and press Enter

Expected output:

At bookmark mode:

![](Images/GeneralUG/location_bookmark.png)

At flashcard mode:

![](Images/GeneralUG/location_flashcard.png)

### 4. Prints starred items: `highlight`

This command will print out the starred items in Bookmark and Academic

Instruction:
1. Type `highlight` and press Enter

Expected output:
![](Images/GeneralUG/highlight.png)

### 5. Exits the current mode or application: `exit`

This command behaves differently depending on which mode you are currently at.

If you are currently at the Main Menu, calling this command will exit the app as shown below:

Instruction:
1. Type `exit` and press Enter

Expected output:

![](Images/GeneralUG/exit.png)

If you are in any of the other modes (bookmark/timetable/academic/flashcard), 
calling this command will exit that mode and place you at the Main menu instead, as shown below:

Instruction:
1. Type exit and press enter when you are in any other modes besides Main menu

Expected output:

![](Images/GeneralUG/exitmode.png)

## Features 

## 1. Bookmark
Now that more lessons are moving online, are you managing your links well? You might 
want to save your time finding your zoom links in your email inbox and save all your 
links in the bookmark feature to access them easily! The bookmark mode enables you to 
compile all your links in one destination and even categorise them into different groups. 
After entering the bookmark mode (refer to General Command 2), use the following commands to 
navigate around bookmark mode. This section contains 8 commands: 1.1, 1.2, 1.3, 1.4, 1.5, 
1.6, 1.7 and 1.8. 

### 1.1 Viewing bookmarks in each category: 
`bm NUMBER`   

Managing so many links can be a hassle! However, categorising the links into different 
groups can make it even more efficient to find your links! After entering bookmark mode 
(refer to General Command 2), you can view the list of links in each category using the 
following commands. This command allows you to change the bookmark category within the
 bookmark mode. 
 
 
Instruction:
1. `bm NUMBER `

E.g. `bm 2`  
Expected output: 
![Bookmark_1.1](Images/BookmarkUG/Figure1.png)


**Useful Information:**
* You can only use this command anywhere after entering the bookmark mode. ( Refer to General Command 2)
* Zoom, Nus, Internship, Hackathon, Career Talks categories are included in the default.

If you encounter any errors, take note of the following warnings. If problems still persist, refer to FAQ at the end of the user guide. 

**Warning:**
* Ensure that the category NUMBER that you have chosen is valid. Error will be shown if the category NUMBER does not exist or the NUMBER you have entered is not a number. To find out the valid category NUMBER, go back to bookmark main. (Refer to 1.6)

### 1.2 Viewing all the bookmarks links: 
`list`   

Are you trying to find your links by entering into each category to view the links? You might want to have an easy way to view the overview of the entire list of links in each category. You can now view the entire list of links using the following commands. 
Instructions:
1. `list`  

Expected output:
![Bookmark_1.2](Images/BookmarkUG/Figure2.png)

**Useful Information:**
* You can only use this command anywhere after entering the bookmark mode.  (Refer to General Command 2)
* If your category does not have any links, it will indicate that the list is empty.

### 1.3 Adding category:  
`cat CATEGORY_NAME `

If you cannot find the perfect category to place your bookmark, why not create your own category! In addition to the default categories that are added (Zoom, NUS, Internship, Hackathon, Career Talks), create your own category to add your bookmark links. Use the following commands to add a category and navigate it (Refer to 1.1) the same way as the rest of your categories.

Instruction: 
1. `cat CATEGORY_NAME`   
  
e.g. `cat entertainment`, `cat stocks`

Expected output:   
![Bookmark_1.3](Images/BookmarkUG/Figure3.png)

**Useful Information:** 
* You can only use this command anywhere after entering the bookmark mode.  (Refer to General Command 2)
* NUS, Zoom, Internship, Hackathon and Career Talk categories are included in the default.

If you encounter any errors, take note of the following warnings. If problems still persist, refer to FAQ at the end of the user guide. 

**Warning:** 
* Ensure that the category name is not empty. Error will be shown if the category name is empty. 

### 1.4 Deleting a category:  
`delete CATEGORY_NUMBER`  

When you have a category, but you have no important links related to the category, take it out of your bookmark list! Organise and design your bookmark categories to your own personal needs by adding your own category (refer to 1.3) and deleting unnecessary categories using these following commands.

Instruction:  

1. delete CATEGORY_NUMBER  

e.g. delete 6  
Expected output:  
![Bookmark_1.4](Images/BookmarkUG/Figure4.png)

**Useful Information:**
* You can only use this command in bookmark main.  (Refer to 1.5 to go back to the bookmark main)

Before deleting a category, take note of the following warnings. If you encounter any problems, refer to FAQ at the end of the user guide. 

**Warning:**
* Ensure that the category NUMBER that you have chosen is valid. Error will be shown if the category NUMBER does not exist or the NUMBER you have entered is not a number. To find out the valid category NUMBER, go back to bookmark main. (Refer to 1.5)
* CAUTION: Deleting a category will delete all the bookmarks in the category! 

### 1.5 Going back to bookmark main: 
`back` 
There are two modes in bookmark feature: the main bookmark mode (refer to General Command 2) and the category mode (refer to 1.1). To navigate freely from mode to mode, use the following command. This command goes back to the previous mode depending on which mode you are in. 
 
Instruction: 
1. back 

Expected output:   
![Bookmark_1.5](Images/BookmarkUG/Figure5.png)
![Bookmark_1.6](Images/BookmarkUG/Figure6.png)

**Useful Information:**
* When you input back in any bookmark category, you are brought back to the bookmark main. 
* When you input back in the bookmark main, you will be prompted to type “exit” to exit bookmark mode. 

### 1.6 Adding a link to your chosen category: 
`add LINK` / `add LINK t->TITLE`
If you have a link to add to your bookmark category, follow these commands. 
Make sure that you have chosen your category (Refer to 1.1) 
and ensure that you do not add an invalid link or an empty link to your bookmark.

Instruction: 
1. `add LINK` e.g. `add https://nus-cs2113-ay2021s1.github.io/website/index.html`
1. `add LINK t->TITLE` e.g. `add https://nus-cs2113-ay2021s1.github.io/website/index.html t->Cs2113T`

e.g. add https://nus-cs2113-ay2021s1.github.io/website/index.html
Expected output:   
![Bookmark_1.7](Images/BookmarkUG/Figure7.png)

**Useful Information:**
* You can only use this command after choosing your category. (Refer to 1.1)

If you encounter any errors, take note of the following warnings. If problems still persist, refer to FAQ at the end of the user guide. 

**Warning:** 
* Ensure that you have chosen a category. You will be prompted to choose a category before you are allowed to add your links. (Refer to 1.1) 
* Ensure that the link added is valid. Error will be shown if the link you have given is empty or is not valid (contains " " or does not contain “.”). 

### 1.7 Deleting a link: 
`rm LINK_NUMBER `
If you have a link that you no longer want to bookmark, or if you have a link that you accidentally bookmarked it, you can remove the bookmark easily just by following these few commands. Make sure that you have chosen your category (Refer to 1.1)  and ensure that you do not add input an empty or a invalid link number

Instruction: 
1. rm LINK_NUMBER  

e.g. rm 1
Expected output:   
![Bookmark_1.8](Images/BookmarkUG/Figure8.png)

**Useful Information:**
* You can only use this command after choosing your category. (Refer to 1.1)

If you encounter any errors, take note of the following warnings. If problems still persist, refer to FAQ at the end of the user guide. 

**Warning:**
* Ensure that you have chosen a category. You will be prompted to choose a category before you are allowed to remove your links. (Refer to 1.1) 
* Ensure that the link number that you want to remove is valid. Error will be shown if the link number you have given is empty or is not valid. Refer to 1.2 to find out the correct link number. 

### 1.8 Marking/ Unmarking a link as a starred link: star LINK_NUMBER 
You might have many links that you need to manage, however, some of the links might be more important than others. If you have such links, you can easily mark them up by following these few commands. Make sure that you have chosen your category (Refer to 1.1)  and ensure that you do not have an empty or invalid link number.

Instruction: 
1. `star LINK_NUMBER`  

e.g. `star 1`
Expected output:   
![Bookmark_1.9](Images/BookmarkUG/Figure9.png)

**Useful Information:** 
* It is the same command for marking and unmarking any links. For example, if link 1 is not starred, star 1  will mark the link as starred. And if link 1 is starred, star 1 will unmark link 1. 

If you encounter any errors, take note of the following warnings. If problems still persist, refer to FAQ at the end of the user guide. 

**Warning:**
* Ensure that you have chosen a category. You will be prompted to choose a category before you are allowed to mark your links as starred. (Refer to 1.1) 
* Ensure that the link number that you want to mark as star is valid. Error will be shown if the link number you have given is empty or is not valid. Refer to 1.2 to find out the correct link number. 

## 2. Timetable
Do you find it a hassle to keep track of all your activities and Zoom links for online classes?
Do you wish there was a simple, visual way to organise your day and foresee any clashes in your schedule?
The timetable mode intends to address these issues, helping you keep track of your work and helping you achieve
purposeful productivity.

This section contains 5 commands to navigate the Timetable mode —
* 2.1. Entering timetable mode
* 2.2. Adding classes
* 2.3. Adding activities
* 2.4. Showing links
* 2.5. Showing schedule

### 2.1 Entering timetable mode: `cd 3` / `cd timetable`

If you find it difficult to make plans due to uncertainties in your schedule, this timetable mode presents your upcoming
events at a glance to know what to expect and stay on track. This command brings you to enter the timetable mode.

Instruction:

1. `cd 3` / `cd timetable`

Expected output:
![Timetable_2_1](Images/TimetableUG/Timetable_2_1.png)

**Useful information:**
* You can use this command from any mode to access the timetable mode easily and quickly!

### 2.2 Adding classes: `add class`

If you find it hard to keep track of all your Zoom links with an increasing number of online classes, you can now
arrange your classes in a systematic manner with this feature. Make sure that you are currently in the timetable mode
(refer to 2.1).

Instruction:

1. `add class`
2. Answer the questions as prompted

Expected output:
![Timetable_2_2](Images/TimetableUG/Timetable_2_2.png)

If you encounter any errors, take note of the following warning.

**Warning:**
* Ensure that you follow the format for answering the questions.
Error will be shown if the questions are not answered in a suitable format.

### 2.3 Adding activities: `add activity`

It can be hard to view your upcoming events at a glance.
Similar to the previous feature, this command allows you to add activities outside of classes to your schedule.

Instruction:

1. `add activity`
2. Answer the questions as prompted

Expected output:
![Timetable_2_3](Images/TimetableUG/Timetable_2_3.png)

### 2.4 Showing links: `show link`

If you find it hard to keep track of all your conference links for your classes or activities, this command will present
all links relevant to you for the next 2 hours.

Instruction:
1. `show link`

Expected output:
![Timetable_2_4](Images/TimetableUG/Timetable_2_4.png)

### 2.5 Showing schedule: `show schedule`

If you wish to view your schedule for the next seven days with all the activities, use this command.

Instruction:
1. `show schedule`

Expected output:
![Timetable_2_5](Images/TimetableUG/Timetable_2_5.png)

## 3. Academic
Have you ever had to dig through tons of emails to look for the contact of a particular TA 
or sweat over your calculator when trying to estimate your CAPS this semester? 
Academic tracker provides a convenient experience where you can store 
all the information you need in one place! To access the academic tracker, 
follow the instructions below.

###3.1 Entering Academic Mode:
`cd 4` / `cd academic`

This command allows you to enter academic tracker mode from the main menu or from the other modes.
After entering this mode, you can then access all the features that the academic tracker offers!

Instruction: 

1. `cd 4` / `academic`

Expected output:
![Academic_3_1](Images/AcademicUG/Academic_3_1.png)

### 3.2 Adding a contact: 
`add contact c/CONTACT DETAILS m/MOBILE NUMBER  e/EMAIL`

In academic mode, you can add a contact to the current list of contacts, following the instructions below. 

Instruction: 
1. `add contact c/CONTACT DETAILS  m/MOBILE NUMBER  e/EMAIL`

Example of usage: 

`add contact c/Prof Lim  m/81234567  e/E7654321@u.nus.edu` 
will add a contact with the name Prof Lim, mobile number 81234567, and email E7654321@u.nus.edu.

Expected output:  
![Academic_3_2](Images/AcademicUG/Academic_3_2.png)

*Note that contacts must be fully numerical and emails must contain an @.

### 3.3 Listing all contacts: 
`list contact`

You can view all the contacts that have been added previously 
and stored on your computer by following these sets of instructions

Instruction: 
1. `list contact`

Expected output:
![Academic_3_3](Images/AcademicUG/Academic_3_3.png)

### 3.4 Starring a contact:
`star contact INDEX`

Sometimes you may wish to highlight an important contact, and that's where
starring a contact come into use. Follow the instructions below to mark an 
important contact with a star.

Instruction: 
1. `star contact INDEX`

Example of usage: 

`star contact 1` will mark the first contact in the list with a star.

Expected output:
![Academic_3_4](Images/AcademicUG/Academic_3_4.png)

### 3.5 Deleting a contact:
`delete contact INDEX`

Do you no longer require the contact of the TA of last sem's module? 
Make use of the delete contact function to clean up your contacts by following the
instructions below.

Instruction: 
1. `delete contact INDEX`

Example of usage: 

`delete contact 1` will delete the first contact in the list.

Expected output:
![Academic_3_5](Images/AcademicUG/Academic_3_5.png)

### 3.6 Adding a grade: 
`add grade n/MODULE NAME  m/MC  g/GRADE`

In academic mode, you can add a grade to the current list of grades, following the instructions below. 

Instruction: 
1. `add grade n/MODULE NAME  m/MC  g/GRADE`

Example of usage: 

`add grade n/CS2101  m/4  g/A-` 
will add a grade with the title CS2101 that has 4 credits and scored an A-.

Expected output:  
![Academic_3_6](Images/AcademicUG/Academic_3_6.png)

*Note that module credits need to be a positive integer, and grade entered must be a valid grade.

### 3.7 Listing all grades: 
`list grade`

You can view all the grades that have been added previously 
and stored on your computer by following these sets of instructions

Instruction: 
1. `list grade`

Expected output:
![Academic_3_7](Images/AcademicUG/Academic_3_7.png)

### 3.8 Checking current cap: 
`check cap`

To calculate your current CAP based on the grades you have entered previously, 
follow the instructions below

Instruction: 
1. `check cap`

Expected output:
![Academic_3_8](Images/AcademicUG/Academic_3_8.png)

### 3.9 Starring a grade:
`star grade INDEX`

The star grade function can be used to highlight a particular grade. 
Follow the instructions below to mark an 
important grade with a star.

Instruction: 
1. `star grade INDEX`

Example of usage: 

`star grade 1` will mark the first grade in the list with a star.

Expected output:
![Academic_3_9](Images/AcademicUG/Academic_3_9.png)

### 3.10 SUing a grade:
`su grade INDEX`

SUed a module and want to exclude it from your CAP calculation? The SU grade
function allows you to do just that through these simple steps.

Instruction: 
1. `su grade INDEX`

Example of usage: 

`su grade 1` will su the first grade in the list.

Expected output:
![Academic_3_10](Images/AcademicUG/Academic_3_10.png)

### 3.11 Deleting a grade:
`delete grade INDEX`

The delete grade function can be used to delete a grade from the currently stored
 list of grades by following the instructions below.

Instruction: 
1. `delete grade INDEX`

Example of usage: 

`delete grade 1` will delete the first grade in the list.

Expected output:
![Academic_3_11](Images/AcademicUG/Academic_3_11.png)

### 3.12 Listing all the starred items in academic:
`list star`

The list star function works like the highlight function, but only displaying
the starred items inside the academic section. 

Instruction: 
1. `list star`

Expected output:
![Academic_3_12](Images/AcademicUG/Academic_3_12.png)

## 4. Flashcard

Do you wish you had a quick way to refer to the content that will be tested in your upcoming tests?
Or a more efficient way to verify your knowledge?
Perhaps it is also difficult to scour your handwritten notes to find a particular piece of information.
The flashcard mode aims to tackle these aspects that may hinder your learning process.

This section contains 6 commands to navigate the Flashcard mode —
* 4.1. Entering flashcard mode
* 4.2. Adding flashcards
* 4.3. Listing flashcards
* 4.4. Deleting flashcards
* 4.5. Testing content
* 4.6. Finding relevant flashcards

### 4.1 Entering flashcard mode: `cd 5` / `cd flashcard`

If you find it hard to be fully proficient in your study topics, this flashcard mode is a medium for you to actively
learn and memorise. This enables you to learn effectively through repetition, helping you to remember information better
in the long-term. This command allows you to enter the flashcard mode.

Instruction:
1. `cd 5` / `cd flashcard`

Expected output:
![Flashcard_4_1](Images/FlashcardUG/Flashcard_4_1.png)

**Useful information:**
* You can use this command from any mode to access the flashcard mode easily and quickly!

### 4.2 Adding flashcards: `add`

To assist you in your studies, there needs to be a knowledge base of content to be revised.
You can do this by adding new flashcards to the flashcard deck with this command, after entering flashcard mode
(refer to 4.1).

Instruction:
1. `add`
2. Enter the question and answer as prompted
 
Example of usage:

`4+4` followed by `8` will create a flashcard with question 4+4 and answer 8.

Expected output:
![Flashcard_4_2](Images/FlashcardUG/Flashcard_4_2.png)

If you encounter any errors, take note of the following warning.

**Warning:**
* “back” cannot be added as an answer for the flashcard.
This is because “back” is used as a command to exit the test mode later on.
If the flashcard answer you wish to use is similar to “back”, rephrase and use another term.
![Flashcard_4_2_1](Images/FlashcardUG/Flashcard_4_2_1.png)

### 4.3 Listing flashcards: `list`

If you wish to review the content that is currently in the flashcard deck, this command can display all flashcards that
have been added.

Instruction:
1. `list`

Expected output:
![Flashcard_4_3](Images/FlashcardUG/Flashcard_4_3.png)

### 4.4 Deleting flashcards: `delete`

When you have a flashcard that is no longer relevant to you or contains wrong information, you can delete the flashcard
from the flashcard deck.

Instruction:
1. `delete`
2. Enter the card index of the flashcard to be deleted as prompted

Example of usage:

`3` will delete the 3rd flashcard in the list.

Expected output:
![Flashcard_4_4](Images/FlashcardUG/Flashcard_4_4.png)

**Useful information:**
* You can use the `list` command to check the card index.

### 4.5 Testing content:`test`

Grading your own work is one of the best ways to revise as it helps you effectively retain information.
After adding the flashcards, you may wish to revise the content by testing yourself. 

When you are done revising, use `back` to exit the test mode, and return to the flashcard main.

Instruction:
1. `test`
2. Answer the questions as prompted
3. `back`

Expected ouput:
![Flashcard_4_5](Images/FlashcardUG/Flashcard_4_5.png)

### 4.6 Finding relevant flashcards: `find`

If you wish to look through the flashcards containing only a particular search term, use this command.

Instruction:
1. `find`
2. Enter the desired search term as prompted

Expected output:
![Flashcard_4_6](Images/FlashcardUG/Flashcard_4_6.png)

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

The following table is a compiled list of all available commands in our application that you can easily refer to! 

The commands listed under the sections besides “General” can only be called when you are in that specific mode, 
which you can access using the cd command!

![](Images/cheatsheet.png)