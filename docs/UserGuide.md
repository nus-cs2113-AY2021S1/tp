# Fitr User Guide

* Table of Contents
{:toc}

# 1. Introduction
**Fitr** is a command line application, helping you keep track of your food intake and exercises. 
Fitr is targeted at university students, who want a comprehensive application to track your food consumption and exercises. 
You can enter your food intake and the exercises that you have done for the day, and track your net calories along the way. 
If you are unsure of what exercises to do, you can also ask for recommendations from Fitr.

This user guide provides a detailed documentation on how to use Fitr, and also shows you how to get started using Fitr.

## 1.1 Prerequisites
You must have **Java 11** or above installed on your computer. You must also be comfortable using the command line or the terminal.

## 1.2 How to use this guide
Note the formatting used in this guide:
- A command that you need to enter at the command line is formatted as `command`.
- Important information is indicated with :bulb:.
- Warnings are indicated with :warning:.
- Words formatted as `UPPER_CASE` are to be supplied by you.

## 1.3 Quick start
This quick start section provides a step-by-step procedure to get you started with Fitr.
1. Ensure that you have **Java 11** or above installed.
2. Download the latest version of `Fitr` from [here](https://github.com/AY2021S1-CS2113T-W13-2/tp/releases).
3. Open the command prompt/terminal on your computer, and navigate to the location where you stored `fitr.jar`.
4. Run the command `java -jar {filename}.jar` e.g., `java -jar fitr.jar` (i.e., run the command in the same folder as the jar file).
5. If you have performed the previous steps correctly, you should see a welcome screen, shown below in Figure 1.
6. Follow the setup process by entering your name, age, height, weight, gender and your fitness level. Note that the height is in metres and weight is in kilograms.
7. Once done, you are ready to use Fitr. You can type help to find out what commands are supported in Fitr.
   
# 2. Features
This section introduces the features available in Fitr, explaining how you can use each feature and its expected outcomes.

## 2.1 Adding Commands

## 2.2 Viewing Commands

## 2.3 Editing Commands

Adding an exercise: `exercise`
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

Adding a food item: `food`
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

Deleting an item: `delete`
Deletes a particular item at a specified index in a specified list.

Format: `delete LIST INDEX`

- The `LIST` has to be either `food` or `exercise`, otherwise an exception will be thrown.
- The `INDEX` has to be a positive number smaller than or equals to the size of the list.

Example of usage: 
- `delete food 1`
- `delete exercise 2`

Viewing the exercise list: `view exercise`
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

Viewing the food list: `view food`
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

Viewing the user profile: `view profile`
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

Viewing the user's BMI: `view bmi`
Displays the user's BMI (body mass index) based on the user's height and weight in the user profile.

Format: `view bmi`

Expected outcome:
```
Your BMI is: 
24.69
```

Viewing the user's caloric summary: `view summary`
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

Editing the user's profile: `edit`
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
Editing previous exercise entry: `edit exercise`
Edits a previous exercise entry in the exercise list.

Format: `edit exercise INDEX NAME_OF_EXERCISE /AMOUNT_OF_CALORIES_BURNT`
- `INDEX` and `AMOUNT_OF_CALORIES_BURNT` must be a positive integer: 1, 2, 3, ...

Example of usage: `edit exercise 1 Push ups /500`

Expected outcome:
```
Successfully edited exercise to: Push ups
```

Editing previous food entry: `edit food`
Edits a previous food entry in the food list.

Format: `edit food INDEX NAME_OF_FOOD /AMOUNT_OF_CALORIES_CONSUMED QUANTITY`
- `INDEX`, `AMOUNT_OF_CALORIES_CONSUMED` and `QUANTITY` must be a positive integer: 1, 2, 3, ...

Example of usage: `edit food 1 Rice /2000`

Expected outcome:
```
Successfully edited food to: rice
```

Viewing help: `help`
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

## 2.4 Deleting Commands
If you would like to delete a particular entry, you can utilise the various delete commands as listed below. 

> :warning: **Warning:** The delete action is irreversible.

### 2.4.1 Deleting a food entry

### 2.4.2 Deleting an exercise entry

### 2.4.3 Deleting a goal entry

## 2.5 Clearing Commands
Want to start anew? Instead of deleting entries one by one, you can clear all your entries in just one command. These features are documented below.

> :warning: **Warning:** The clear action is irreversible.

### 2.5.1 Clearing all food entries

### 2.5.2 Clearing all exercise entries

### 2.5.3 Clearing all goal entries

### 2.5.4 Clearing all entries

## 2.6 Other Commands
Other than the commands mentioned above, Fitr is also able to give you a workout to do, mark your goal as complete and if you have finished using the application, you can exit as well. The following documents the other commands available. 

### 2.6.1 Getting a recommended workout

### 2.6.2 Marking a goal as complete

### 2.6.3 Exiting the application

## 2.7 Saving your Data
Your profile, food consumed and exercises done are saved automatically after any command that changes the data.
There is no need to save manually. All your data will be saved in the same location as `fitr.jar`.

## 2.8 Tip of the Day

# 3. FAQ
In this section, you can find answers to some of the frequently asked questions (FAQs).

**Q: How do I transfer my data to another computer?**

A: Install Fitr on another computer, and overwrite the empty data files that it creates with the files with your data files.

# 4. Command Summary
Table 1 below summarises the commands Fitr understands and how they can be used. Examples are also provided for reference.

_Table 1: Commands supported in Fitr_

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
