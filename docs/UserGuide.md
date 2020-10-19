# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [Clearing the lists: `clear`](#clearing-the-lists-clear)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

Fitr is a command-line application, helping you keep track of your food intake and exercises.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `Fitr` from [here](https://github.com/AY2021S1-CS2113T-W13-2/tp/releases).
1. Open Command Prompt / Terminal
1. Type the following command `java -jar` followed by the location of the Fitr JAR file, to run it.

## Features

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Clearing the lists: `clear`

Clears either the exercise list, or the food list, or both.

Format: `clear LIST`

- `LIST` can either take `exercise` or `food`.
- If `LIST` is not provided, both exercise and food lists are cleared.

Example of usage:

- `clear food` clears the food list.
- `clear` clears both the exercise and food lists.

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

The user's profile, food consumed and exercises done are saved automatically after any command that changes the data. 
There is no need to save manually.


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

Action | Format | Examples
------ | ------ | --------
add food entry | `food <name of food> / <number of calories consumed> <quantity (optional)>` | `food apple / 52 1`
add exercise entry | `exercise <name of exercise> / <number of calories burnt>` | `exercise 5km run / 400`
delete food entry | `delete food <Index from Food List>` | `delete food 3`
delete exercise entry | `delete exercise <Index from Exercise List>"` | `delete exercise 5`
view help | `help` 
view bmi | `view bmi`
view profile | `view profile`
view food entries | `view food`
view exercise entries | `view exercise`
view calorie summary | `view summary`
clear | `clear` | `clear`, `clear exercise`, `clear food`
terminate Fitr program | `bye`
