# User Guide

## Introduction

Zoomaster is a desktop app for organizing website links, optimized for use via a 
Command Line Interface (CLI) while retaining benefits of a Graphical User Interface (GUI). 
If you can type fast, Zoomaster can help fetch useful website links for you quicker 
than the bookmark function on your browser.

### Table of contents
* [QuickStart](#quick-start)
* [Features](#features)
  * [Switch mode](#mode)
  * [Bookmark mode](#bookmark-mode)
    * [Add bookmark](#addbookmark)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

<a name="mode"></a>
#### Switch mode: `mode`
Switches between the “bookmark” and “timetable” modes. Depending on the mode, 
the behaviour of the commands below changes.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`


###Bookmark mode

<a name="addbookmark"></a>
####Add bookmarks: `add`
Add a URL bookmark under a certain topic and description.

Format: `todo [<TOPIC>] <DESCRIPTION> <URL> `

* The `TOPIC` is optional.
* The `TOPIC` and `DESCRIPTION` can only contain one word each.
* The `URL` has to start with `www.` or `https://`.
* There should be strictly one space between the `TOPIC`, `DESCRIPTION` and `URL`.  

Example of usage: 

`add CS2113T tutorial https://cs2113t.nus.edu.sg/`

`add website https://cs2113t.nus.edu.sg/`




## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the `data/data.txt` and `data/slots.txt` file to the directory where the `jar` file is at. 
       Start the application and all the data should be loaded.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
* Add todo `todo n/TODO_NAME d/DEADLINE`
* Add todo `todo n/TODO_NAME d/DEADLINE`
* Add todo `todo n/TODO_NAME d/DEADLINE`
* Add todo `todo n/TODO_NAME d/DEADLINE`
