# User Guide

## Table of content
1. [Introduction](#1-introduction)<br>
2. [Quick start](#2-quick-start)<br>
3. [Architecture](#3-architecture)<br>
4. [Features](#4-features)<br>
4.1. [Admin level](#41-admin-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.1. [Adding a module: `add`](#411-adding-a-module-add-jiayi)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.2. [Listing modules available: `list`](#412-listing-modules-available-list-zeyu)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.3. [Editing a module name: `edit`](#413-editing-a-module-name-edit-jane)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.4. [Removing a module: `remove`](#414-removing-a-module-remove-jia-ern)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.5. [Accessing the module level: `go`](#415-accessing-the-module-level-go-jiayi)<br>
4.2. [Module level](#42-module-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.1. [Adding a chapter: `add`](#421-adding-a-chapter-add-jiayi)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.2. [Listing chapters available: `list`](#422-listing-chapters-available-list-zeyu)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.3. [Editing a chapter name: `edit`](#423-editing-a-chapter-name-edit-jane)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.4. [Removing a chapter: `remove`](#424-removing-a-chapter-remove-jia-ern)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.5. [Accessing the chapter level: `go`](#425-accessing-the-chapter-level-go-yan-an)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.6. [Returning to admin level: `back`](#426-returning-to-admin-level-back-yan-an)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.7. [Starting a revision session: `revise`](#427-starting-a-revision-session-revise-jia-ern)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.8. [Rating a chapter: `rate`](#428-rating-a-chapter-rate-jiayi)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.9. [Rescheduling a chapter: `reschedule`](#429-rescheduling-a-chapter-reschedule-jane)<br>
4.3. [Chapter level](#43-chapter-level)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.1. [Adding a flashcard: `add`](#431-adding-a-flashcard-add-jane)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.2. [Listing flashcards available: `list`](#432-listing-flashcards-available-list-zeyu)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.3. [Editing a flashcard content: `edit`](#433-editing-a-flashcard-content-edit-jane)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.4. [Removing a flashcard: `remove`](#434-removing-a-flashcard-remove-jia-ern)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.5. [Returning to module level: `back`](#435-returning-to-module-level-back-jia-ern)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.6. [Checking overall performance for a chapter: `showrate`](#436-checking-overall-performance-for-a-chapter-showrate-jiayi)<br>
4.4. [Unrestricted](#44-unrestricted)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.1. [Showing a list of commands available: `help`](#441-showing-a-list-of-commands-available-help-zeyu)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.2. [Listing the chapters due for today: `due`](#442-listing-the-chapters-due-for-today-due-yan-an)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.3. [Listing the chapters due in the upcoming week: `preview`](#443-listing-the-chapters-due-in-the-upcoming-week-preview-yan-an)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.4. [Viewing the revision history: `history`](#444-viewing-the-revision-history-history-zeyu)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.5. [Excluding or including modules and chapters: `exclude`](#445-excluding-or-including-modules-and-chapters-exclude-yan-an)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.6. [Exiting the program: `exit`](#446-exiting-the-program-exit-zeyu)<br>
5. [Command Summary](#5-command-summary)<br>
5.1. [Admin level](#51-admin-level)<br>
5.2. [Module level](#52-module-level)<br>
5.3. [Chapter level](#53-chapter-level)<br>
5.4. [Unrestricted](#54-unrestricted)<br>

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

KAJI is a schedule manager that implements Spaced Repetition, optimised for use via a Command Line Interface (CLI).

--------------------------------------------------------------------------------------------------------------------

## 2. Quick start
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

## 3. Architecture



--------------------------------------------------------------------------------------------------------------------

## 4. Features

### 4.1. Admin level

#### 4.1.1. Adding a module: `add` (Jiayi)

#### 4.1.2. Listing modules available: `list` (Zeyu)

#### 4.1.3. Editing a module name: `edit` (Jane)

#### 4.1.4. Removing a module: `remove` (Jia Ern)

#### 4.1.5. Accessing the module level: `go` (Jiayi)

### 4.2. Module level

#### 4.2.1. Adding a chapter: `add` (Jiayi)

#### 4.2.2. Listing chapters available: `list` (Zeyu)

#### 4.2.3. Editing a chapter name: `edit` (Jane)

#### 4.2.4. Removing a chapter: `remove` (Jia Ern)

#### 4.2.5. Accessing the chapter level: `go` (Yan An)

#### 4.2.6. Returning to admin level: `back` (Yan An)

#### 4.2.7. Starting a revision session: `revise` (Jia Ern)

#### 4.2.8. Rating a chapter: `rate` (Jiayi)

#### 4.2.9. Rescheduling a chapter: `reschedule` (Jane)

### 4.3. Chapter level

#### 4.3.1. Adding a flashcard: `add` (Jane)

#### 4.3.2. Listing flashcards available: `list` (Zeyu)

#### 4.3.3. Editing a flashcard content: `edit` (Jane)

#### 4.3.4. Removing a flashcard: `remove` (Jia Ern)

#### 4.3.5. Returning to module level: `back` (Jia Ern)

#### 4.3.6. Checking overall performance for a chapter: `showrate` (Jiayi)

### 4.4. Unrestricted

#### 4.4.1. Showing a list of commands available: `help` (Zeyu)

#### 4.4.2. Listing the chapters due for today: `due` (Yan An)

#### 4.4.3. Listing the chapters due in the upcoming week: `preview` (Yan An)

#### 4.4.4. Viewing the revision history: `history` (Zeyu)

#### 4.4.5. Excluding or including modules and chapters: `exclude` (Yan An)

#### 4.4.6. Exiting the program: `exit` (Zeyu)



--------------------------------------------------------------------------------------------------------------------

## 5. Command Summary

### 5.1. Admin level

| Action | Format, Examples |
|--------|------------------|

### 5.2. Module level

| Action | Format, Examples |
|--------|------------------|

### 5.3. Chapter level

| Action | Format, Examples |
|--------|------------------|

### 5.4. Unrestricted

| Action | Format, Examples |
|--------|------------------|
