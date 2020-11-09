# Fitr User Guide

* Table of Contents
{:toc}

<!-- @@author -->

# 1. Introduction
**Fitr** is a command line application, helping you keep track of your food intake, exercises and goals. 
Fitr is targeted at university students, who want a comprehensive application to track your food consumption, exercises and goals. 
You can enter your food intake and the exercises that you have done for the day, and track your net calories along the way. 
Moreover, you can set goals to keep yourself motivated and if you are unsure of what exercises to do, you can also ask Fitr for recommendations.

This user guide provides a detailed documentation on how to use Fitr and shows you how to get started using Fitr.

## 1.1 Prerequisites
You must have **Java 11** or above installed on your computer. You must also be comfortable using the command line or the terminal.

## 1.2 How to use this guide
Note the formatting used in this guide:
- A command that you need to enter at the command line is formatted as `command`.
- Additional information is indicated with :bulb:.
- Warnings are indicated with :warning:.
- Words formatted as `UPPER_CASE` are to be supplied by you.

## 1.3 Quick start
This quick start section provides a step-by-step procedure to get you started with Fitr.
1. Download the latest version of `Fitr` from [here](https://github.com/AY2021S1-CS2113T-W13-2/tp/releases).
2. Open the command prompt/terminal on your computer, and navigate to the location where you stored `fitr.jar`.
3. Run the command `java -jar {filename}.jar` e.g., `java -jar fitr.jar` (i.e., run the command in the same folder as the jar file).
4. If you have performed the previous steps correctly, you should see a welcome screen, shown below in Figure 1.
5. Follow the setup process by entering your name, age, height, weight, gender, and your fitness level. Note that the height is in metres and weight is in kilograms.
6. Once done, you are ready to use Fitr. You can type `help` to find out what commands are supported in Fitr.

> :bulb: **Additional information:** Please resize your window to ensure that the separator lines are not wrapped.

<p align="center"><img src="images/Figure1.png" width="350"></p>
<p align="center">Figure 1: Welcome screen of Fitr for new users</p>
   
# 2. Features
This section introduces the features available in Fitr, explaining how you can use each feature and its expected outcomes.

## 2.1 Adding Commands
You can add various types of entries to Fitr, such as your food intake, exercise completed and goals you wish to achieve. These features are documented below.

<!-- @@author sixletters -->

### 2.1.1 Adding a food entry
You can add a food entry to keep track of what you have eaten, and the calories consumed from that food.

Format: `food NAME_OF_FOOD /NUMBER_OF_CALORIES` or `food NAME_OF_FOOD /NUMBER_OF_CALORIES AMOUNT_OF_FOOD`

> :bulb: **Additional information:** 
> * `NUMBER_OF_CALORIES` must be between 0 and 10,000 (inclusive)
> * `AMOUNT_OF_FOOD` must be between 1 and 1000 (inclusive)
> * If `AMOUNT_OF_FOOD` is omitted, the default amount of food is 1

For example, you can enter `food apple /50` and this will be the result after the application records the food entry:

```
----------------------------------------------------------------------------------------------------------------------------------------
The following food has been added:
Name of Food: apple
Calorie Consumed: 50
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.1.2 Adding an exercise entry
You can add an exercise entry to keep track of what exercise you have done, as well as the calories burnt from that exercise.

Format: `exercise NAME_OF_EXERCISE /CALORIES_BURNT`

> :bulb: **Additional information:** 
> * `CALORIES_BURNT` must be between 1 and 10,000 (inclusive)

For example, you can enter `exercise push ups /500` and this will be the result after the application records the exercise entry:

```
----------------------------------------------------------------------------------------------------------------------------------------
The following exercise has been added:
Name of Exercise: push ups
Burnt Cal: 500
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author hui444 -->

### 2.1.3 Adding a food goal

You can add a food goal to the application to encourage yourself to eat more or less. 

Format: `goal food GOAL_DESCRIPTION`

For example, you can enter `goal food eat less food than yesterday` and this will be the result after the application records the food goal entry:

```
----------------------------------------------------------------------------------------------------------------------------------------
Okay! The following goal has been added: 
    [F] eat less food than yesterday
----------------------------------------------------------------------------------------------------------------------------------------
Tip: You may also add a smart food goal using the format 
    "goal food < NUMBER_OF_CALORIES or goal food > NUMBER_OF_CALORIES"
----------------------------------------------------------------------------------------------------------------------------------------
```

#### 2.1.3.1 Adding a smart food goal
Fitr also understands particular keywords. If you would like to set a target for your calorie intake, you can key in your goal following the format below. By adding a smart food goal, Fitr will help you calculate your percentage completion and update it accordingly.

> :bulb: **Additional information:** 
> * Smart food goals are linked to your calorie intake of its created date
> * The progress of smart goals are automatically updated by Fitr
> * Target number of calories can only be between 0 (inclusive) and 100 000 for 'more than' goals
> * Target number of calories can only be between 0 and 100 000 for 'less than' goals

Format: `goal food > NUMBER_OF_CALORIES`
        or `goal food < NUMBER_OF_CALORIES`

For example, if your goal is to consume less than 800 calories, you can enter `goal food < 800` and the expected result after the application records the smart food goal entry should be as follows:

```
----------------------------------------------------------------------------------------------------------------------------------------
Okay! The following goal has been added: 
    [F] Eat less than 800 calories
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.1.4 Adding an exercise goal
You can add an exercise goal to the application to encourage yourself to exercise more or less. 

Format: `goal exercise GOAL_DESCRIPTION`

For example, you can enter `goal exercise do 50 push ups` and below shows the expected result after the application records the exercise goal entry:

```
----------------------------------------------------------------------------------------------------------------------------------------
Okay! The following goal has been added: 
    [E] do 50 push ups
----------------------------------------------------------------------------------------------------------------------------------------
Tip: You may also add a smart exercise goal using the format
    "goal exercise < CALORIES_BURNT or goal exercise > CALORIES_BURNT"
----------------------------------------------------------------------------------------------------------------------------------------
```

#### 2.1.4.1 Adding a smart exercise goal
Fitr also understands particular keywords. If you would like to set a target for your calorie burnt, you can key in your goal following the format below. By adding a smart exercise goal, Fitr will help you calculate your percentage completion and update it accordingly.

> :bulb: **Additional information:** 
> * Smart exercise goals are linked to your calories burnt of its created date
> * The progress of smart goals are automatically updated by Fitr
> * Target number of calories can only be between 0 (inclusive) and 100 000 for 'more than' goals
> * Target number of calories can only be between 0 and 100 000 for 'less than' goals

Format: `goal exercise > NUMBER_OF_CALORIES` 
        or `goal exercise < NUMBER_OF_CALORIES`

For example, you can enter `goal exercise > 3800` and you should expect the following:

```
----------------------------------------------------------------------------------------------------------------------------------------
Okay! The following goal has been added: 
    [E] Burn more than 3800 calories
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author -->

## 2.2 Viewing Commands
You can view various aspects of Fitr, such as commands the application supports and its usages, your profile details, previous food and exercise entries, and your goals. These features are documented below.

### 2.2.1 Viewing help
If you are unsure of what commands the application supports or the format and usage of them, you can use this command.

Format: `help`

<!-- @@author gohsonghan98 -->

### 2.2.2 Viewing your profile	
If you want to check your profile, you can use this command. The profile includes your name, age, gender, height, weight, and your fitness level.	

Format: `view profile`

Example of an expected outcome is as follows:
```
----------------------------------------------------------------------------------------------------------------------------------------
User profile:
Name: Tom
Age: 20
Gender: Male
Height (in m): 2.00
Weight (in kg): 60.00
Your fitness level: Fit
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.2.3 Viewing your BMI	
Body mass index (BMI) is a value derived from the mass and height of a person (BMI = mass (kg) / height (m) ^2). The BMI
is a convenient rule of thumb used to broadly categorize a person as underweight, normal weight, overweight, or obese. 
You can view your BMI using this command.	

Format: `view bmi`	

Example of an expected outcome is as follows:
```
----------------------------------------------------------------------------------------------------------------------------------------
Your BMI is: 15.00
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.2.4 Viewing food entries	
You can view the past food entries you have previously added to Fitr. All food entries will be grouped by their dates and displayed in a list form within their groups.	

To view all food entries, you may use the `view food` command.	

Format: `view food`	

Example of an expected outcome is as follows:	

```
----------------------------------------------------------------------------------------------------------------------------------------
Here is the list of your food:
Date: 23/10/2020
[1] Food: Apple
    Quantity: 1
    Total Calorie(s): 52

Date: 28/10/2020
[1] Food: Fish
    Quantity: 1
    Total Calorie(s): 300

Date: 31/10/2020
[1] Food: Orange
    Quantity: 1
    Total Calorie(s): 52
[2] Food: Sandwich
    Quantity: 1
    Total Calorie(s): 78

Date: 5/11/2020
[1] Food: salad
    Quantity: 1
    Total Calorie(s): 600
[2] Food: chicken
    Quantity: 5
    Total Calorie(s): 150
----------------------------------------------------------------------------------------------------------------------------------------
Tip: You may also view food entries by day using the format "view food dd/MM/yyyy"
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author dmbclub -->

#### 2.2.4.1 Viewing food entries on a specific date
To view food entries for a specific day, you may enter the date at the back of the `view food` command. 

Format: `view food dd/MM/yyyy`

For example, if you would like to view your food entries on 31/10/2020, you may key in `view food 31/10/2020`. The expected outcome should be similar to below:

```
----------------------------------------------------------------------------------------------------------------------------------------
Here is the list of your food:
Date: 31/10/2020
[1] Food: Orange
    Burnt Cal: 52
[2] Food: Sandwich
    Burnt Cal: 78
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author gohsonghan98 -->

### 2.2.5 Viewing exercise entries
You can view the past exercise entries you have previously added to Fitr. All exercise entries will be grouped by their 
dates and displayed in a list form within their groups.

To view all exercise entries, you may use the `view exercise` command.

Format: `view exercise`

Example of an expected outcome is as follows:
```
----------------------------------------------------------------------------------------------------------------------------------------
Here is the list of your exercises:
Date: 23/10/2020
[1] Exercise: Step ups
    Burnt Cal: 50
[2] Exercise: Russian Twists
    Burnt Cal: 23
[3] Exercise: Spinal rotations
    Burnt Cal: 6

Date: 27/10/2020
[1] Exercise: Triceps dips
    Burnt Cal: 7
[2] Exercise: Side Lunges
    Burnt Cal: 14
[3] Exercise: Russian Twists
    Burnt Cal: 14
[4] Exercise: Cobra stretch
    Burnt Cal: 6

Date: 31/10/2020
[1] Exercise: Side Lying Leg lifts
    Burnt Cal: 23

Date: 5/11/2020
[1] Exercise: jumping rope
    Burnt Cal: 30
----------------------------------------------------------------------------------------------------------------------------------------
Tip: You may also view exercise entries by day using the format "view exercise dd/MM/yyyy"
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author dmbclub -->

#### 2.2.5.1 Viewing exercise entries on a specific date
To view exercises for a specific day, you may enter the date at the back of the `view exercise` command. 

Format: `view exercise dd/MM/yyyy`

For example, if you would like to view your exercises on 31/10/2020, you may key in `view exercise 31/10/2020`. The expected outcome should be similar to below:
```
----------------------------------------------------------------------------------------------------------------------------------------
Here is the list of your exercises:
Date: 31/10/2020
[1] Exercise: Side Lying Leg lifts
    Burnt Cal: 23
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author hui444 -->

### 2.2.6 Viewing goal entries
You can view the goals you have previously set for yourself and track your progress using this command. 

> :bulb: **Additional information:** The goal list is sorted by the progress of the goals, and its created date

The letter in the first square bracket denotes the goal type, `[F]` for food goals and `[E]` for exercise goals.
The second square bracket shows the status of that goal, `[Y]` for completed goals, `[N]` for incompleted goals and percentages indicate the completeness of that goal.
 
Format: `view goal`

Example of an expected outcome is as follows:
```
----------------------------------------------------------------------------------------------------------------------------------------
Here is the list of your goals:
1. [E][N] run more (created on: 4/11/2020)
2. [E][N] Burn more than 4000 calories (created on: 4/11/2020)
3. [E][1.7%] Burn more than 3000 calories (created on: 6/11/2020)
4. [F][Y] Eat less than 2000 calories (created on: 5/11/2020)
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author gohsonghan98 -->

### 2.2.7 Viewing calorie summary
If you want to see the calories you consumed from food or burnt from exercise, you can use this command. 
Note that positive net calorie does not mean you are gaining weight. It does not take calories burnt from performing 
everyday tasks into account, and you may actually burn much more calories from performing everyday tasks than doing a
workout.

Format: `view summary`

Example of an expected outcome is as follows:
```
----------------------------------------------------------------------------------------------------------------------------------------
Date: 23/10/2020
Total calorie consumed:
52
Total calorie burnt:
79
Net calorie:
-27

Date: 27/10/2020
Total calorie consumed:
0
Total calorie burnt:
41
Net calorie:
-41

Date: 31/10/2020
Total calorie consumed:
382
Total calorie burnt:
74
Net calorie:
308
----------------------------------------------------------------------------------------------------------------------------------------
Tip: You may also view summary by day using the format "view summary dd/MM/yyyy"
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author dmbclub -->

#### 2.2.7.1 Viewing calorie summary of a specific date
You may also choose to view the summary for a specific day by specifying a date at the back of `view summary`.

Format: `view summary dd/MM/yyyy`

For example, if you would like to view your caloric summary on 31/10/2020, you may key in `view summary 31/10/2020`, and you should get an output similar to below:
```
----------------------------------------------------------------------------------------------------------------------------------------
Date: 31/10/2020
Total calorie consumed:
382
Total calorie burnt:
74
Net calorie:
308
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author -->

## 2.3 Editing Commands
You can edit various aspects of Fitr, such as your profile, previous food and exercise entries, and your goals. These features are documented below.

<!-- @@author gohsonghan98 -->

### 2.3.1 Editing your profile
You can edit your name, age, gender, height, weight, and fitness level in your profile using commands within sections 2.3.1.1 to 2.3.1.6.

#### 2.3.1.1 Editing your name
Format: `edit name NEW_NAME`

For example, you can enter the following input `edit name Tom`, and expect the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Your current name is: Tom
----------------------------------------------------------------------------------------------------------------------------------------
```
> :bulb: **Additional information:** Inputs given for `NEW_NAME` can only contain alphabets with or without spaces.

#### 2.3.1.2 Editing your age
Format: `edit age NEW_AGE`

For example, you can enter the following input `edit age 20`, and expect the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Your current age is: 20
----------------------------------------------------------------------------------------------------------------------------------------
```
> :bulb: **Additional information:** Inputs given for `NEW_AGE` can only be integers ranging from 1 to 130.

#### 2.3.1.3 Editing your gender
Format: `edit gender NEW_GENDER`

For example, you can enter the following input `edit gender m`, and expect the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Your current gender is: Male
----------------------------------------------------------------------------------------------------------------------------------------
```
> :bulb: **Additional information:** 
> * Inputs given for `NEW_GENDER` can only be alphabetical `M` or `F`.
> * Inputs given are not case-sensitive.

#### 2.3.1.4 Editing your height
Format: `edit height NEW_HEIGHT`

For example, you can enter the following input `edit height 1.7`, and expect the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Your current height (in m) is: 1.70
----------------------------------------------------------------------------------------------------------------------------------------
```
> :bulb: **Additional information:** 
> * Inputs given for `NEW_HEIGHT` is in meters, and can only be given as an integer 
> or decimal number ranging from 0.50 to 4.00. 
> * Inputs will be rounded up to 2 decimal places.

#### 2.3.1.5 Editing your weight
Format: `edit weight NEW_WEIGHT`

For example, you can enter the following input `edit weight 60`, and expect the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Your current weight (in kg) is: 60.00
----------------------------------------------------------------------------------------------------------------------------------------
```
> :bulb: **Additional information:** 
> * Inputs given for `NEW_WEIGHT` is in kilograms, and can only be given as an integer or decimal number ranging from
> 2.00 to 1000.00. 
> * Inputs will be rounded up to 2 decimal places.

#### 2.3.1.6 Editing your fitness level
Format: `edit fitness NEW_FITNESS_LEVEL`

For example, you can enter the following input `edit fitness 2`, and expect the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Your current fitness level is: Fit
----------------------------------------------------------------------------------------------------------------------------------------
```
> :bulb: **Additional information:** 
> * Inputs given for `NEW_FITNESS` can only be integers `0` for unfit, `1` for normal, or `2` for fit. 
> * The fitness level you set will change the intensity of the exercises recommended by the `recommend` command.

<!-- @@author jerichochua -->

### 2.3.2 Editing a food entry
You can edit your previous food entries, for example, if you previously made a mistake when entering a food that you consumed.

Format: `edit food dd/MM/yyyy INDEX NAME_OF_FOOD /CALORIES_OF_FOOD QUANTITY`

> :bulb: **Additional information:**
> * `INDEX` must be a positive integer
> * `CALORIES_OF_FOOD` must be between 0 and 10,000 (inclusive)
> * `QUANTITY` must be between 1 and 1000 (inclusive)

For example, you can enter `edit food 31/10/2020 1 apple /100 1`, and you should expect to see the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Successfully edited food to: apple, calories (per qty): 100, amount: 1
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.3.3 Editing an exercise entry
You can edit your previous exercise entries, for example, if you previously made a mistake when entering an exercise that you performed.

Format: `edit exercise dd/MM/yyyy INDEX NAME_OF_EXERCISE /CALORIES_BURNT`

> :bulb: **Additional information:**
> * `INDEX` must be a positive integer
> * `CALORIES_BURNT` must be between 1 and 10,000 (inclusive)

For example, you can enter `edit exercise 31/10/2020 1 Push ups /500`, and you should expect to see the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Successfully edited exercise to: Push ups, calories burnt: 500
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author hui444 -->

### 2.3.4 Editing a goal entry
You can edit your previous goal entries, for example, if you previously made a mistake when entering the type or description of a goal.

Format: `edit goal INDEX TYPE_OF_GOAL GOAL_DESCRIPTION`

For example, you can enter `edit goal 1 exercise run 2.4km`, and you should expect to see the following output:
```
----------------------------------------------------------------------------------------------------------------------------------------
Successfully edited goal to: [E] run 2.4km
----------------------------------------------------------------------------------------------------------------------------------------
```

> :bulb:  **Additional information:** 
> * As goals are ordered based on its created date and progress status, please view your goals before editing a goal
> * By editing the goal entry, it will reset the status of the goal
> * Smart goals will be automatically updated based on the created date's calorie intake and calories burnt

<!-- @@author -->

## 2.4 Deleting Commands
If you would like to delete a particular entry, you can utilise the various delete commands as listed below. 

> :warning: **Warning:** The delete action is irreversible.

<!-- @@author sixletters -->

### 2.4.1 Deleting a food entry
You can delete a food entry using this command. You would need to know the index of that particular food entry as well as the date in which you wish to delete. You can try to use the view food command to find the index of the food before deleting.

Format: `delete food dd/MM/yyyy INDEX_OF_FOOD`

> :bulb: **Additional information:**
> * `INDEX_OF_FOOD` must be a positive integer

For example, you can enter `delete food 31/10/2020 1`, and you should expect to see the following:
```
----------------------------------------------------------------------------------------------------------------------------------------
The following food has been deleted for the 31/10/2020: apple
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.4.2 Deleting an exercise entry
You can delete an exercise entry using this command. You would need to know the index of that particular exercise entry as well as the date of its entry. You can try to use the view exercise command to find the index of the exercise before deleting.

Format: `delete exercise dd/MM/yyyy INDEX_OF_EXERCISE`

> :bulb: **Additional information:**
> * `INDEX_OF_EXERCISE` must be a positive integer

For example, you can enter `delete exercise 31/10/2020 1`, and you should expect to see the following:
```
----------------------------------------------------------------------------------------------------------------------------------------
The following exercise has been deleted for the 31/10/2020: Triceps dips
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.4.3 Deleting a goal entry
To delete a particular goal entry, you can use this command. However, you would need to know the index of that particular goal entry to utilise this command.

Format: `delete goal INDEX`

> :bulb:  **Additional information:** 
> * As goals are ordered based on its created date and progress status, please view your goals before deleting a goal
> * `INDEX` must be a positive integer

For example, you can enter `delete goal 1`, and you should expect to see the following:
```
----------------------------------------------------------------------------------------------------------------------------------------
The following has been deleted from the list of goals: Burn more than 30000 calories
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author -->

## 2.5 Clearing Commands
Want to start anew? Instead of deleting entries one by one, you can clear all your entries in just one command. These features are documented below.

> :warning: **Warning:** The clear action is irreversible.

<!-- @@author jerichochua -->

### 2.5.1 Clearing all food entries
If you wish to clear all your previous food entries, you can do so with this command.

Format: `clear food`

Expected outcome:
```
----------------------------------------------------------------------------------------------------------------------------------------
Food list is cleared!
----------------------------------------------------------------------------------------------------------------------------------------
```

### 2.5.2 Clearing all exercise entries
If you wish to clear all your previous exercise entries, you can do so with this command.

Format: `clear exercise`

Expected outcome:
```
----------------------------------------------------------------------------------------------------------------------------------------
Exercise list is cleared!
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author hui444 -->

### 2.5.3 Clearing all goal entries
If you do decide to reset your whole goal list (i.e. removing all goal entries stored), you can utilise this command.

Format: `clear goal`

Expected outcome:
```
----------------------------------------------------------------------------------------------------------------------------------------
Goal list is cleared!
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author jerichochua -->

### 2.5.4 Clearing all entries
If you decide to clear all past entries made, regardless of the type (food, exercise or goal), you can utilise this command.

Format: `clear`

Expected outcome:
```
----------------------------------------------------------------------------------------------------------------------------------------
Food, exercise and goal lists are all cleared!
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author -->

## 2.6 Other Commands
Other than the commands mentioned above, Fitr is also able to give you a workout to do, mark your goal as complete and if you have finished using the application, you can exit as well. The following documents the other commands available. 

<!-- @@author sixletters -->

### 2.6.1 Getting a recommended workout
If you wish to be recommended a workout that is tailored to your fitness level, you can use this command. The command will recommend a workout and ask if you wish to accept this workout or reject it.

You can either just type in `recommend`, or you can follow recommend with one of the specified exercise categories. 
The four categories are `upperbody`, `lowerbody`, `aerobic` and `stretch`. For example, you can key in `recommend upperbody` and a workout that is just for your upperbody will be recommended

If you do accept it, it will automatically be added into your exercise entries, however you can also choose which exercises you want to add to your exercise entries by keying in the index, separated by spaces.

Format: `recommend` or `recommend WORKOUT_CATEGORY`

For example, if you key in `recommend`, the expected outcome is as follows:
```
[1] Exercise: Triceps dips
    Intensity: 3 sets of 0.5 minutes
    Burnt Cal: 6
[2] Exercise: Side Lunges
    Intensity: 3 sets of 1.0 minutes
    Burnt Cal: 12
[3] Exercise: Jumping rope
    Intensity: 3 sets of 4.0 minutes
    Burnt Cal: 167
[4] Exercise: Mountain stretch
    Intensity: 4 sets of 0.5 minutes
    Burnt Cal: 5
Will you be doing this workout?
Type 'y' to add all 4 to your exercise list,
or you can type in the index of the exercises you want added to you exercise list (separated by a space).
Any other input will be taken as a no.
```

or if you key in `recommend upperbody`, the expected output is as follows:
```
[1] Exercise: Planks to Pushup
    Intensity: 3 sets of 0.5 minutes
    Burnt Cal: 8
[2] Exercise: Plank Rotations
    Intensity: 3 sets of 0.5 minutes
    Burnt Cal: 6
[3] Exercise: Inclined Pullups
    Intensity: 3 sets of 0.5 minutes
    Burnt Cal: 6
[4] Exercise: Pullups
    Intensity: 2 sets of 0.5 minutes
    Burnt Cal: 4
Will you be doing this workout?
Type 'y' to add all 4 to your exercise list,
or you can type in the index of the exercises you want added to you exercise list (separated by a space).
Any other input will be taken as a no.
```

If you follow this up with a `y`, you will then see the exercises added in as follows:
```
----------------------------------------------------------------------------------------------------------------------------------------
The following exercise has been added:
[1] Exercise: Triceps dips
    Burnt Cal: 6
[2] Exercise: Side Lunges
    Burnt Cal: 12
[3] Exercise: Jumping rope
    Burnt Cal: 167
[4] Exercise: Mountain stretch
    Burnt Cal: 5
----------------------------------------------------------------------------------------------------------------------------------------
```

If you decide you only want exercise 2 and 4 to be added, you can key in `2 4` and you will see as follows:
```
----------------------------------------------------------------------------------------------------------------------------------------
The following exercise has been added:
[1] Exercise: Side Lunges
    Burnt Cal: 12
[2] Exercise: Mountain stretch
    Burnt Cal: 5
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author hui444 -->

### 2.6.2 Marking a goal as complete
> :warning: **Warning:** The marking goal as complete action is irreversible.

After completing a goal, you can mark it as complete. 
If it is a smart goal, Fitr will automatically update the goal's status according to your calorie intake or calorie burnt.

Format: `complete goal INDEX`

> :bulb:  **Additional information:**
> * As goals are ordered based on its created date and progress status, please view your goals before marking a particular one as complete
> * You are able to override the smart goal to mark them as complete
> * When you mark a goal as complete, it is moved to the bottom of the list

For example, after marking a goal as complete with the command `complete goal 1` , you should expect something similar to below:
```
----------------------------------------------------------------------------------------------------------------------------------------
Yay! You completed:
    run more
----------------------------------------------------------------------------------------------------------------------------------------
```

<!-- @@author jerichochua -->

### 2.6.3 Exiting the application
Once you are done with Fitr, you can exit the application by running the command below.

Format: `bye`

## 2.7 Saving your Data
Your profile, food consumed and exercises done are saved automatically after any command that changes the data.
There is no need to save manually. All your data will be saved in the same location as `fitr.jar`.

<!-- @@author dmbclub -->

## 2.8 Displaying Tip of the Day
Fitr will tell you a fun fact every time when you open the application. It can be an exercise tip or an interesting trivia, which can give you some motivation to do exercise! An example is shown below:

<p align="center"><img src="images/WelcomeScreen.png" width="800"></p>

<!-- @@author -->

# 3. FAQ
In this section, you can find answers to some of the frequently asked questions (FAQs).

**Q: How do I transfer my data to another computer?**

A: Install Fitr on another computer, and overwrite the empty data files that it creates with the files with your data files.

**Q: Can I edit the text files created by Fitr?**

A: Yes, you can edit the text files. Note that if the entries are not recognised by Fitr, it will be ignored and deleted subsequently.

**Q: Can I add my own exercises to add to the standard list of exercise recommendations?**

A: Currently, the standard exercises are maintained by us. You will not be able to alter or manipulate the list of standard exercises.

**Q: What if I only like certain exercises in the recommendation?**

A: As mentioned above, you can choose to do and add specific exercises that have been recommended to you rather than the whole list. You can also recommend again repeatedly until you add all the exercises that you are comfortable doing.

**Q: Will my fitness level be automatically increased for me over time?**

A: You have to increase your own fitness level according to what you deem fit as Fitr will not do it for you. This is because every single person's body react differently to a different set of exercises, hence automating it may not be the best for you.

**Q: Will Fitr automatically know or save the calories for a food?**

A: You will have to know or search the amount of calories for a certain food and key it in respectively. Fitr does not save the calories for specific foods as we have no way of making sure if what you entered in is an actual food product.

# 4. Command Summary
Table 1 below summarises the commands Fitr understands and how they can be used. Examples are also provided for your reference.

_Table 1: Commands supported in Fitr_

Action | Format | Examples
------ | ------ | --------
Add food entry | `food NAME_OF_FOOD /CALORIES_OF_FOOD` or `food NAME_OF_FOOD /CALORIES_OF_FOOD AMOUNT_OF_FOOD` | `food pear /57` or `food apple /50 1`
Add exercise entry | `exercise NAME_OF_EXERCISE /CALORIES_BURNT` | `exercise run /360`
Add food goal | `goal food GOAL_DESCRIPTION` | `goal food eat more`
Add smart food goal | `goal food < NUMBER_OF_CALORIES` or `goal food > NUMBER_OF_CALORIES` | `goal food < 2000` or `goal food > 3800`
Add exercise goal | `goal exercise GOAL_DESCRIPTION` | `goal exercise run more`
Add smart exercise goal | `goal exercise < CALORIES_BURNT` or `goal exercise > CALORIES_BURNT` | `goal exercise < 4000` or `goal exercise > 3800`
View Help | `help` |
View User Profile | `view profile` |
View User BMI | `view bmi` |
View Food Entry | `view food` |
View Food Entries on a Specified Date | `view food dd/MM/yyyy` | `view food 31/10/2020`
View Exercise Entry | `view exercise` | 
View Exercise Entries on a Specified Date | `view exercise dd/MM/yyyy` | `view exercise 31/10/2020`
View Goal Entry | `view goal` | 
View Calorie Summary | `view summary` |
View Calorie Summary on a Specified Date | `view summary dd/MM/yyyy` | `view summary 31/10/2020`
Get a recommended general workout | `recommend` | 
Get a recommended specific workout| `recommend WORKOUT_CATEGORY` | `recommend upperbody` or `recommend lowerbody` or `recommend aerobic` or `recommend stretch`
Edit User Profile | `edit name NEW_NAME` or `edit age NEW_AGE` or `edit gender NEW_GENDER` or  `edit height NEW_HEIGHT` or `edit weight NEW_WEIGHT` or `edit fitness NEW_FITNESS_LEVEL` | `edit name John` or `edit age 21` or `edit gender m` or `edit height 1.7` or `edit weight 65` or `edit fitness 1`
Edit Food Entry | `edit food dd/MM/yyyy INDEX NAME_OF_FOOD /CALORIES_OF_FOOD QUANTITY` | `edit food 31/10/2020 1 green apple /50 1`
Edit Exercise Entry | `edit exercise dd/MM/yyyy INDEX NAME_OF_EXERCISE /CALORIES_BURNT` | `edit exercise 31/10/2020 1 5km run /360`
Edit Goal Entry | `edit goal INDEX TYPE_OF_GOAL GOAL_DESCRIPTION` | `edit goal 1 food eat healthier`
Delete a Food Entry | `delete food dd/MM/yyyy INDEX` | `delete food 31/10/2020 3`
Delete an Exercise Entry | `delete exercise dd/MM/yyyy INDEX` | `delete exercise 31/10/2020 3`
Delete a Goal Entry | `delete goal INDEX` | `delete goal 5`
Clear All Food Entries | `clear food` |
Clear All Exercise Entries | `clear exercise` | 
Clear All Goal Entries | `clear goal` | 
Clear All Entries | `clear` |
Mark a Goal Entry as Completed | `complete goal INDEX` | `complete goal 4`
Exit Fitr | `bye` | 
