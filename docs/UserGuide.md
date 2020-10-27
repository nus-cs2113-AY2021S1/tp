# User Guide
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding an exercise: `exercise`](#adding-an-exercise-exercise)
    - [Adding a food item: `food`](#adding-a-food-item-food)
    - [Deleting an item: `delete`](#deleting-an-item-delete)
    - [Viewing the exercise list: `view exercise`](#viewing-the-exercise-list-view-exercise)
    - [Viewing the food list: `view food`](#viewing-the-food-list-view-food)
    - [Viewing the user profile: `view profile`](#viewing-the-user-profile-view-profile)
    - [Viewing the user's BMI: `view bmi`](#viewing-the-users-bmi-view-bmi)
    - [Viewing the user's caloric summary: `view summary`](#viewing-the-users-caloric-summary-view-summary)
    - [Editing the user's profile: `edit`](#editing-the-users-profile-edit)
    - [Editing previous exercise entry: `edit exercise`](#editing-previous-exercise-entry-edit-exercise)
    - [Editing previous food entry: `edit food`](#editing-previous-food-entry-edit-food)
    - [Viewing help: `help`](#viewing-help-help)
    - [Clearing the lists: `clear`](#clearing-the-lists-clear)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction
**Fitr** is a command-line application, helping you keep track of your food intake and exercises, and also provide exercise recommendations, in one application. This application is aimed at university students at all fitness levels.

## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Fitr` from [here](https://github.com/AY2021S1-CS2113T-W13-2/tp/releases).
3. Open command prompt/terminal.
4. Run the command `java -jar {filename}.jar` e.g., `java -jar Fitr.jar` (i.e., run the command in the same folder as the jar file).
5. If the setup is correct, you should see something like below:
      ``` 
       _______   __   __
      |    ___| |__| |  |_  .----.
      |    ___| |  | |   _| |   _|
      |___|     |__| |____| |__|
      Hello! Welcome to Fitr. 
      ```
   
## Features
### Adding an exercise: `exercise`
Adds a new exercise to the list of exercises you have done.

Format: `exercise NAME_OF_EXERCISE /AMOUNT_OF_CALORIES_BURNT`

- The `NAME_OF_EXERCISE` can be in a natural language format.
- The `AMOUNT_OF_CALORIES_BURNT` has to be a positive number.  

Example of usage: 
- `exercise Pushups /500`
- `exercise 5km run /1000`

Expected outcome:
- `The following exercise has been added: Pushups`
- `The following exercise has been added: 5km run`

### Adding a food item: `food`
Adds a new food to the list of foods you have eaten.

Format: `food NAME_OF_FOOD /AMOUNT_OF_CALORIES_CONSUMED`

- The `NAME_OF_FOOD` can be in a natural language format.
- The `AMOUNT_OF_CALORIES_CONSUMED` has to be a positive number.  

Example of usage: 
- `food chicken rice /500`
- `food Mcspicy /600`

Expected outcome:
- `The following food has been added: chicken rice`
- `The following food has been added: Mcspicy`

### Deleting an item: `delete`
Deletes a particular item at a specified index in a specified list.

Format: `delete LIST INDEX`

- The `LIST` has to be either `food` or `exercise`, otherwise an exception will be thrown.
- The `INDEX` has to be a positive number smaller than or equals to the size of the list.

Example of usage: 
- `delete food 1`
- `delete exercise 2`

### Viewing the exercise list: `view exercise`
Displays the user's exercise entries from the exercise list.

Format: `view exercise`

Expected outcome:
```
Here is the list of your exercises:

Date: 05/10/2020
[1] Exercise: Thigh stretch
    Burnt Cal: 6

Date: 23/10/2020
[1] Exercise: Thigh stretch
    Burnt Cal: 6

Date: 24/10/2020
[1] Exercise: Triceps dips
    Burnt Cal: 7
[2] Exercise: Cossack Squat
    Burnt Cal: 20
[3] Exercise: Russian Twists
    Burnt Cal: 15
[4] Exercise: Thigh stretch
    Burnt Cal: 6
```

### Viewing the food list: `view food`
Displays the user's food entries from the food list.

Format: `view food`

Expected outcome:
```
Here is the list of your food:

Date: 22/10/2020
[1] Food: chicken
    Cal: 10
[2] Food: chicken
    Cal: 10

Date: 23/10/2020
[1] Food: chicken
    Cal: 10
[2] Food: chicken
    Cal: 10
```

### Viewing the user profile: `view profile`
Displays the user's profile: name, age, gender, height (in m), weight (in kg).

Format: `view profile`

Expected outcome:
```
User profile:
Name: John Doe
Age: 22
Gender: Male
Height (in m): 1.8
Weight (in kg): 80.0
Your fitness level: Fit
```

### Viewing the user's BMI: `view bmi`
Displays the user's BMI (body mass index) based on the user's height and weight in the user profile.

Format: `view bmi`

Expected outcome:
```
Your BMI is: 
24.69
```

### Viewing the user's caloric summary: `view summary`
Displays a summary of the calories consumed, calories burnt, and net calories based on the entries in the exercise and food list.

Format: `view summary`

Expected outcome:
```
Date: 05/10/2020
Total calorie consumed:
0
Total calorie burnt:
-6
Net calorie:
-6

Date: 22/10/2020
Total calorie consumed:
2
Total calorie burnt:
0
Net calorie:
2

```

### Editing the user's profile: `edit`
Changes the user's name, age, gender, height (in m), or weight (in kg).

Format: `edit TYPE`

Type | Format | Description | Notes for input
----- | ------ | -------- | ------
name | `edit name` | Changes name in user profile | Not case-sensitive
age | `edit age` | Changes age in user profile | Only number inputs allowed, decimal number is not allowed
gender | `edit gender` | Changes gender in user profile | Not case-sensitive, only takes in `m` or `f` input.
height | `edit height` | Changes height in user profile | Only number inputs allowed, decimal number is allowed
weight | `edit weight` | Changes weight in user profile | Only number inputs allowed, decimal number is allowed
fitness | `edit fitness` | Changes fitness level in user profile | Only number inputs 0,1 or 2 is allowed

Examples of usage:

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
edit weight
Change your weight (in kg) to: 
90
Weight (in kg) changed to: 90.0
```
Edit fitness
```
edit fitness
Please indicate your fitness level to be used for determining intensity of exercises.
(0 for Unfit; 1 for Normal; 2 for Fit): 
2
Fitness level changed to: Fit
```
### Editing previous exercise entry: `edit exercise`
Edits a previous exercise entry in the exercise list.

Format: `edit exercise INDEX NAME_OF_EXERCISE /AMOUNT_OF_CALORIES_BURNT`
- `INDEX` and `AMOUNT_OF_CALORIES_BURNT` must be a positive integer: 1, 2, 3, ...

Example of usage: `edit exercise 1 Push ups /500`

Expected outcome:
```
Successfully edited exercise to: Push ups
```

### Editing previous food entry: `edit food`
Edits a previous food entry in the food list.

Format: `edit food INDEX NAME_OF_FOOD /AMOUNT_OF_CALORIES_CONSUMED QUANTITY`
- `INDEX`, `AMOUNT_OF_CALORIES_CONSUMED` and `QUANTITY` must be a positive integer: 1, 2, 3, ...

Example of usage: `edit food 1 Rice /2000`

Expected outcome:
```
Successfully edited food to: rice
```

### Viewing help: `help`
Show help messages with all the valid commands supported by Fitr.

Format: `help`

Expected outcome:
```
These are commands Fitr understands:
food              Adds food entry to Fitr program
                  Format: food <Name of food> / <Number of Calories> <Quantity (Optional)>
exercise          Adds exercise entry to Fitr program
                  Format: exercise <Name of exercise> / <Number of Calories> <Quantity (Optional)>
view profile      View your profile information
view bmi          View your BMI
view food         View food entries
view exercise     View exercise entries
view summary      View calorie summary
delete            Deletes selected entry
                  Format: delete food <Index from Food List> or delete exercise <Index from Exercise List>
bye               Exits the program
```

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

**A**: Install `Fitr` on another computer, and overwrite the empty data files that it creates with the files with your data files.

## Command Summary

In this section, you can view a summary of the commands supported in Fitr.

Table 1: Commands supported in Fitr

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
