# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 








## 3. Academic
Have you ever had to dig through tons of emails to look for the contact of a particular TA 
or sweat over your calculator when trying to estimate your CAPS this semester? 
Academic tracker provides a convenient experience where you can store 
all the information you need in one place! To access the academic tracker, 
follow the instructions below.

###3.1 Entering Academic Mode : Entering Academic Mode : 
`cd 4` / `cd academic`

This command allows you to enter academic tracker mode from the main menu or from the other modes.
After entering this mode, you can then access all the features that the academic tracker offers!

Instruction: 

1. `cd 4` / `academic`

Expected output:
![Academic_3_1](Images/AcademicUG/Academic_3_1.png)

### 3.2 Adding a contact : 
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

### 3.6 Adding a grade : 
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

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
