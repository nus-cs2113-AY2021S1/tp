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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.8. [Rating a chapter: `rate`](#328-rating-a-chapter-rate)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.9. [Rescheduling a chapter: `reschedule`](#329-rescheduling-a-chapter-reschedule)<br>
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

In the past learning experience, have you encountered these problems? A large number of lecture notes and materials have made your computer desktop messy, and there is no way to find the materials you want. When the exam is approaching, you don’t know which subject to review first, or suddenly find that you have forgotten everything you learned before. No one wants to forget what they have dedicated time to learn.

Don't worry! <strong>Kaji</strong> will help you solve all these problems!


### 1.1. About Kaji
KAJI is a schedule manager that implements Spaced Repetition, optimised for use via a Command Line Interface (CLI).

### 1.2. About this User Guide
This User GUide is divided into four sections, Overview, Quik Start, Features, and Command Summary.

<b>Overview section</b> will briefly introduce Kaji and its User Guide, also the Command Line Interface to you.

<b>Quik Start section</b> will guide you to make some preparations in advance and teach you how to run Kaji.

<b>Features section</b> will introduce all features of Kaji according to different levels, you can learn features in details in this section.

<b>Command summary section</b> will sum up different command type in a table.

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
1. Copy the file to the folder you want to use as the <I>home folder</I> for your Kaji.
1. Double-click the file to start the app or open a command window in the folder you saved Kaji and run the command `java -jar kaji.jar`. You should see the welcome message `Welcome to Kaji` as well as a list of commands available.
1. Type the command in the command window and press Enter to execute it. 
   e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:
   * `help` : List commands available
   * `exit` : Exits the app.
1. Refer to [Features](#4-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features

### 3.1. Admin Level

#### 3.1.1. Adding a module: `add`
(by Jiayi)

#### 3.1.2. Listing modules available: `list` 
(by Zeyu)

#### 3.1.3. Editing a module name: `edit` 
(by Zeyu)

#### 3.1.4. Removing a module: `remove` 
(by Jia Ern)

#### 3.1.5. Accessing the module level: `go`
(by Jiayi)

### 3.2. Module level

#### 3.2.1. Adding a chapter: `add`
(by Jiayi)

#### 3.2.2. Listing chapters available: `list`
(by Zeyu)

#### 3.2.3. Editing a chapter name: `edit`
(by Jane)

#### 3.2.4. Removing a chapter: `remove`
(by Jia Ern)

#### 3.2.5. Accessing the chapter level: `go` 
(by Yan An)

#### 3.2.6. Returning to admin level: `back`
(by Yan An)

#### 3.2.7. Starting a revision session: `revise`
(by Jia Ern)

#### 3.2.8. Rating a chapter: `rate`
(by Jiayi)

#### 3.2.9. Rescheduling a chapter: `reschedule`
(by Jane)

### 3.3. Chapter Level

#### 3.3.1. Adding a flashcard: `add`
(by Jane)

#### 3.3.2. Listing flashcards available: `list`
(by Jane)

#### 3.3.3. Editing a flashcard content: `edit`
(by Jane)

#### 3.3.4. Removing a flashcard: `remove`
(by Jia Ern)

#### 3.3.5. Returning to module level: `back`
(by Jia Ern)

#### 3.3.6. Checking overall performance for a chapter: `showrate`
(by Jiayi)

### 3.4. General

#### 3.4.1. Showing a list of commands available: `help`
(by Zeyu)


#### 3.4.2. Listing the chapters due for today: `due`
(by Yan An)

#### 3.4.3. Listing the chapters due in the upcoming week: `preview`
(by Yan An)

#### 3.4.4. Viewing the revision history: `history`
(by Zeyu)

#### 3.4.5. Excluding or including modules and chapters: `exclude`
(by Yan An)

#### 3.4.6. Exiting the program: `exit`
(by Zeyu)



--------------------------------------------------------------------------------------------------------------------

## 4. Command Summary

### 4.1. Admin Level

| Action | Format, Examples |
|--------|------------------|

### 4.2. Module Level

| Action | Format, Examples |
|--------|------------------|

### 4.3. Chapter Level

| Action | Format, Examples |
|--------|------------------|

### 4.4. General

| Action | Format, Examples |
|--------|------------------|
