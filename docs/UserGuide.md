# User Guide

## Introduction

Fluffle is a **desktop app for creative writers**, optimized for **use via a Command Line Interface** (CLI). It aids users in creating and managing their writings and ideas. As a fast-typing writer, this CLI app is highly suitable for you.

## Quick Start

1. Check that your computer has Java **11** installed.
1. Get the latest **Fluffle** from [here](https://github.com/AY2021S1-CS2113T-W11-4/tp/releases).
1.Copy the file to the folder you want to use as the home folder.
1.Type in a command prompt ./duke.jar and press Enter.
1.Type the `help` command and press Enter to get started.
1.Some example commands you can try:
    1.`list words` : List the vocabulary stored in the word list.
    1.`filter bunny g/fantasy` : Filters bunny ideas that are of the fantasy genre.
    1.`exit` : Exits the app.
1.Refer to the Features below for details of each command.

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data .txt files of your previous Fluffle home folder.

**Q**: Do I need to leave a space before each input tag?

**A**: Yes, the space is necessary. (e.g., add t\science st\chemistry)

**Q**: Do the parameters need to be keyed in in order?

**A**: No. Our code will detect the indicators in any order as long as they are properly spaced out.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
