# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding an exercise: `exercise`](#adding-an-exercise-exercise)
    - [Adding a food item: `food`](#adding-a-food)
    - [Deleting an item: `delete`](#deleting-an-item)
    - [View list of exercises: `view exercise`](#view-exercise-list-view-exercise)
    - [View list of food: `view food`](#view-food-list-view-food)
    - [View user profile: `view profile`](#view-profile-view-profile)
    - [View user bmi: `view bmi`](#view-user-bmi-view-bmi)
    - [View user caloric summary: `view summary`](#view-calorie-summary-view-summary)
    - [edit user profile: `edit ARGUMENT`](#edit-user-profile-edit)
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
1. Open Command Prompt / Terminal
1. Type the following command `java -jar` followed by the location of the Fitr JAR file, to run it.

## Features

### Adding an exercise: `exercise`

Adds a new exercise to the list of exercises you have done.

Format: `exercise NameOfExercise /AmountOfCaloriesBurnt`

* The `NameOfExerCise` can be in a natural language format.
* The `AmountOfCaloriesBurnt` has to be a positive number.  

Example of usage: 

`exercise Pushups /500`

`exercise 5km run /1000`

The expected outcome:

`The following exercise has been added: Pushups`

`The following exercise has been added: 5km run`

### Adding a food item: `food`

Adds a new food to the list of foods you have eaten.

Format: `exercise NameOfFood /AmountOfCaloriesConsumed`

* The `NameOfFood` can be in a natural language format.
* The `AmountOfCaloriesConsumed` has to be a positive number.  

Example of usage: 

`food chicken rice /500`

`food Mcspicy /600`

The expected outcome:

`The following food has been added: chicken rice`

`The following food has been added: Mcspicy`

### Deleting an item: 'delete'

Deletes a particular item at a specified index in a specified list.

Format: `delete ListName index`

* The `ListName` has to be either food or exercise if not an exception will be thrown.
* The `index` has to be a positive number smaller than or equals to the size of the list.

Example of usage: 

`delete food 1`

`delete exercise 2`

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

### Show help messages: `help`

Show help messages with all valid commands.

Format: `help`

Example of usage: 

`help`

Expected outcome:

<pre>
These are commands Fitr understands:<br/>
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
