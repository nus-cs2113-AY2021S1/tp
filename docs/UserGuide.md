# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [View list of exercises: `view exercise`](#view-exercise-list-view-exercise)
    - [View list of food: `view food`](#view-food-list-view-food)
    - [View user profile: `view profile`](#view-profile-view-profile)
    - [View user bmi: `view bmi`](#view-user-bmi-view-bmi)
    - [View user caloric summary: `view summary`](#view-calorie-summary-view-summary)
    - [edit user profile: `edit ARGUMENT`](#edit-user-profile-edit)
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

### View exercise list: `view exercise`
Displays exercise entries in the exercise list.

Format: `view exercise`

Expected outcome:
```
Here is the list of your exercises:
[1] Exercise: swim
    Burnt Cal: 500
[2] Exercise: run
    Burnt Cal: 10
```

### View food list: `view food`
Displays food entries in the food list.

Format: `view food`

Expected outcome:
```
Here is the list of your food:
[1] Food: chicken
    Cal: 10
[2] Food: fish
    Cal: 10
[3] Food: egg
    Cal: 50
[4] Food: fish
    Cal: 500
```

### View profile: `view profile`
Displays user profile: Name, age, gender, height (in m), weight (in kg).

Format: `view profile`

Expected outcome:
```
User profile:
Name: John Doe
Age: 22
Gender: Male
Height (in m): 1.8
Weight (in kg): 80.0
```

### View user bmi: `view bmi`
Displays user's bmi based on height and weight in the user profile.

Format: `view bmi`

Expected outcome:
```
Your BMI is: 
24.69
```

### View calorie summary: `view summary`
Displays summary of calorie consumed, calorie burnt, and net calorie based on entries in the exercise and food list.

Format: `view summary`

Expected outcome:
```
Total calorie consumed:
570
Total calorie burnt:
510
Net calorie:
60
```

### Edit user profile: `edit`
Changes user's name, age, gender, height (in m), or weight (in kg).

Format: `edit ARGUMENT`

Argument | Format | Description | Notes for input
----- | ------ | -------- | ------
name | `edit name` | Changes name in user profile | Not case-sensitive
age | `edit age` | Changes age in user profile | Only number inputs allowed, fraction is not allowed
gender | `edit gender` | Changes gender in user profile | Not case-sensitive, only takes in `m` or `f` input.
height | `edit height` | Changes height in user profile | Only number inputs allowed, fraction is allowed
weight | `edit weight` | Changes weight in user profile | Only number inputs allowed, fraction is allowed

Example of usage:

Edit name
```
edit name
Change your name to: 
Tom
Name changed to: Tom
```
Edit age
```
edit age
Change your age to: 
20
Age changed to: 20
```
Edit gender
```
edit gender
Change your gender (M|F) to: 
f
Gender changed to: Female
```
Edit height
```
edit height
Change your height (in m) to: 
1.9
Height (in m) changed to: 1.9
```
Edit weight
```
Change your weight (in kg) to: 
90
Weight (in kg) changed to: 90.0
```
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
clear | `clear` | `clear`, `clear exercise`, `clear food`
bye | `bye`
