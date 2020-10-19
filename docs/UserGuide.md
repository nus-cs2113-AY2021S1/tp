# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [Show help messages: `help`](#showing-the-help-messages-help)
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

## Features

### Show help messages: `help`
Show help messages with all valid commands.

Format: `help`

Example of usage: 

`help`

Expected outcome:

<pre>These are commands Fitr understands:<br/>
food              Adds food entry to Fitr program<br/>
                  Format: food &lt;Name of food&gt; / &lt;Number of Calories&gt; &lt;Quantity (Optional)&gt;<br/>
exercise          Adds exercise entry to Fitr program<br/>
                  Format: exercise &lt;Name of exercise&gt; / &lt;Number of Calories> &lt;Quantity (Optional)&gt;<br/>
view profile      View your profile information`<br/>
view bmi          View your BMI<br/>
view food         View food entries<br/>
view exercise     View exercise entries<br/>
view summary      View calorie summary<br/>
delete            Deletes selected entry<br/>
                  Format: delete food &lt;Index from Food List&gt; or delete exercise &lt;Index from Exercise List&gt;<br/>
bye               Exits the program<br/></pre>


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
clear | `clear` | `clear`, `clear exercise`, `clear food`
bye | `bye`
