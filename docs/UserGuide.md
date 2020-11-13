# User Guide for traKCAL

The purpose of this user guide is to guide you on the commands available in this application, their respective uses and the expected inputs.

By: CS2113-T09-4  <br>   Since: October 2020  <br>  Licence: MIT

<br>

# Introduction

**Welcome to traKCAL**

**traKCAL** is a desktop application for managing and visualizing your calorie intake, optimized for use via Command Line Interface (CLI) whilst retaining the benefits of a Graphical User Interface (GUI). 

If you are a fast typist, **traKCAL** is perfect for you!

<br>

## Table of Contents

* Table of Contents
{:toc}

<br>

# Quick Start

This section gives the steps you need to get started quickly.

1. Ensure that you have Java 11 or above installed, if not, it can be found [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
2. Down the latest version of `traKCAL` from [here](https://github.com/AY2021S1-CS2113T-T09-4/tp/releases)
3. Create an empty folder in a convenient location eg. Desktop and copy jar file over to it.
4. Open command window/terminal in that window and navigate into the file directory,
5. Run the command `java -jar {filename}.jar` e.g., `java -jar traKCAL.jar`.
6. Upon successful run, you will get the following greeting message. **traKCAL** is now ready for use!

```
====================================================================================
| Hello from                                                                       |
|  _                  _  __   ___     _     _                                      |
| | |_   _ _   __ _  | |/ /  / __|   /_\   | |                                     |
| |  _| | '_| / _` | | ' <  | (__   / _ \  | |__                                   |
|  \__| |_|   \__,_| |_|\_\  \___| /_/ \_\ |____|                                  |
|                                                                                  |
| Hello! I'm traKCAL.                                                              |
| Please input 'help' for the commands and their respective input format.          |
====================================================================================
```
   * Tip: 
     Increase your windows length if the greeting message appears congested like this:
     ![Congested opening message](diagrams/openingMessageError.png)

6. You can type in a command in the command box and press Enter to execute it. For example, typing `help` and pressing Enter will display the help message.

   Some example commands you can try:
    * `list`: Lists all entries for today.
    * `add f/ apple c/ 30`: Adds a food entry named `apple` and of calories `30` to today's list **traKCAL**.
    * `delete 3`: Deletes the 3rd entry shown in the current list.
    * `bye`: Exits the application.
    
7. You can refer to the [Features](#features) below for more details of each command.

<br>

# Features 

This section gives you a detailed description of each feature available in **traKCAL**.

>Things to take note of:
>* Inputs that look like `**THIS**` are compulsory parameters for you to fill in.
>* Inputs that look like `<THIS>` are optional parameters that you do not need to fill in to achieve the desired outcome.  
>* Input format should adhere to the one in the help list or in this user guide.
>* All features such as `add`, `edit`, `list`, etc. are not case-sensitive, but it is recommended for you to follow the format stated in help list or this user guide.


<br>
<br>

## 1.0 Viewing help [Puah Siew Wen]

Do you need a reminder of what are the available features, and their respective input format? Well, this section solves it for you!

Format: `help`

Example of usage:
* `help`

```
====================================================================================================
This section displays the commands available and their respective input format.
> Words in CAPS are parameters to be filled in by you!
> Variables in <here> are optional!
Viewing help:
help                   - Prints out commands available and their input format

User Profiling
user l/                - Prints out current user profile
user c/                - Creates new user profile
user e/ <n/ NAME>, <g/ GENDER>, <w/ WEIGHT>, <h/ HEIGHT>, <age/ AGE>, <al/ ACTIVITY_FACTOR>,
                         <goal/ GOAL>
                       - Edits user profile to name, NAME, gender(male/female), GENDER,
                         height(in cm), HEIGHT, age, AGE, activity factor(1-5), ACTIVITY_FACTOR,
                         goal(lose/maintain/gain), GOAL

Creating shortcut:
*[This command is extensive, there are a lot of variations. The following is one such example]*
createSet SHORTCUT_NAME f/ FOOD_DESCRIPTION c/ CALORIE_COUNT +
e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT + f/ FOOD_DESCRIPTION c/ CALORIE_COUNT
*[Another possible example is:]*
createSet SHORTCUT_NAME f/ FOOD_DESCRIPTION c/ CALORIE_COUNT + f/ FOOD_DESCRIPTION c/ CALORIE_COUNT
                       - Creates shortcut, SHORTCUT_NAME for adding food(s) and/or exercise(s)
                         depending on the format entered
addSet SHORTCUT_NAME   - Adds SHORTCUT_NAME that was created in createSet into today's list

Adding:
add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT
                       - Adds food consumed, FOOD_DESCRIPTION and calories gained, CALORIE_COUNT
                         to today's date
add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE
                       - Adds food consumed, FOOD_DESCRIPTION, calories gained, CALORIE_COUNT
                         and date(YYYY-MM-DD), DATE
add e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT
                       - Adds exercise done, EXERCISE_DESCRIPTION and calories lost, CALORIE_COUNT
                         to today's date
add e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT d/ DATE
                       - Adds exercise done, EXERCISE_DESCRIPTION, calories lost, CALORIE_COUNT
                         and date(YYYY-MM-DD), DATE

Listing:
list                   - Prints out the list of entries.
list DATE              - Prints out the list of entries for the date(YYYY-MM-DD), DATE

Editing:
edit LIST_INDEX f/ FOOD_DESCRIPTION c/ CALORIE_COUNT
                       - Edits entry at index LIST_INDEX of latest list printed out
                         to food consumed, FOOD_DESCRIPTION, calories gained, CALORIE_COUNT
edit LIST_INDEX e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT
                       - Edits entry at index LIST_INDEX of latest list printed out
                         to exercise done, EXERCISE_DESCRIPTION, calories lost, CALORIE_COUNT

Finding:
find d/ DESCRIPTION    - Searches for all entries description with the DESCRIPTION keyword
find c/ CALORIE_COUNT  - Searches for all entries with calories of CALORIE_COUNT
find a/ DESCRIPTION1 / DESCRIPTION2 ... / DESCRIPTION
                        - Searches for all entries with ALL matching keywords from
                          DESCRIPTION1 to DESCRIPTION
find e/ DESCRIPTION1 / DESCRIPTION2 ... / DESCRIPTION
                        - Searches for all entries with AT LEAST one matching keyword from
                          DESCRIPTION1 to DESCRIPTION

Moving:
move from/ INDEX1 below/ INDEX2
                       - Moves the entry at index INDEX1 to the index below INDEX2

Deleting:
delete LIST_INDEX      - Deletes entry located at index LIST_INDEX of latest list printed out
delete all/            - Deletes all entries in current date list

Graphing:
graph                  - Generates a graph of target calorie and net calorie obtained up to
                         last 7 days

Chaining:
*[This command is extensive, there are a lot of variations,
                          but is only available to add, list and edit.]*
*[One possible example is:]*
add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE && list && edit LIST_INDEX f/ FOOD_DESCRIPTION
add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE && list && edit LIST_INDEX f/ FOOD_DESCRIPTION
                         c/ CALORIE_COUNT
*[Another possible example is:]*
add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE && add e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT

Exiting:
bye                    - Terminates the application
====================================================================================================
```

<br>
<br>

## 2.0 User Profile [Jenny Lin]

### 2.1 Creating a new user profile

If this is your first time using **traKCAL**, you will be automatically prompted to create a user profile. **traKCAL** will then use these details from your user profile to calculate the following: 

* BMI
* Recommended daily calorie intake
* Calories needed to reach your weight goal

Format: `user c/`

Example of usage:
* `user c/`

> For this section only, words that are enclosed within star brackets (*) like `*this*` are user inputs.

```
Hey there! We do not have a record of your profile. Please create one now! :)

====================================================================================
What is your name?
====================================================================================
*Sam*
====================================================================================
What is your gender (male/female)?
====================================================================================
*female*
====================================================================================
What is your weight in kg?
====================================================================================
*50*
====================================================================================
What is your height in cm?
====================================================================================
*100*
====================================================================================
What is your age?
====================================================================================
*11*
====================================================================================
How active are you on a scale of 1-5? With 1 being least active and 5 being most active.
====================================================================================
*4*
====================================================================================
Do you wish to lose/maintain/gain weight?
====================================================================================
*gain*

Your BMI is 50.0
Your recommend daily calories intake is 1576.65 calories.
To gain weight, you should consume 2076.65 calories instead.
```

<br>
<br>

### 2.2 Editing user profile

This feature allows you to edit an existing user profile easily.

Format: `user e/ <n/ **NAME**>, <g/ **GENDER**>, <w/ **WEIGHT**>, <h/ **HEIGHT**>, <age/ **AGE**>, <al/ **ACTIVITY_FACTOR**>, <goal/ **WEIGHT_GOALS**>`

Parameters: 
* `**NAME**`: Your name.
* `**GENDER**`: Your gender.
* `**WEIGHT_KG**`: Your weight in kg.
* `**HEIGHT_CM**`: Your height in cm.
* `**AGE**`: Your age.
* `**ACTIVITY_FACTOR**`: How active you are, with 1 being most active and 5 being least active.
* `**WEIGHT_GOALS**`: Whether you want to lose/maintain/gain weight.

Example of usage: 
* `user e/ n/ Tom, w/ 90`

```
Your name has been updated to Tom.
Your weight has been updated to 90kg.
```

<br>
<br>

### 2.3 Viewing your profile

Do you want to view your profile? This feature will be just right for you!

Format: `user l/`

Example of usage:
* `user l/`

```
Here is your user profile:
Name : Sam
Gender : female
Weight : 50
Height : 100 
Age : 10
Activity Level : 4
Weight Goal : gain
```

<br>
<br>

## 3.0 Shortcut [Jenny Lin]

### 3.1 Creating a shortcut for a set of entries 

This command helps you create a shortcut for a set of commonly used exercise and/or food entries, reducing the amount of time needed for you to add multiple common entries. 

Format: `createSet **SHORTCUT_NAME** ...`

Examples of formats accepted:
>* `createSet **SHORTCUT_NAME** f/ **FOOD_DESCRIPTION** c/ **CALORIE_COUNT** + f/ **FOOD_DESCRIPTION** c/ **CALORIE_COUNT**`
>* `createSet **SHORTCUT_NAME** e/ **EXERCISE_DESCRIPTION** c/ **CALORIE_COUNT**`
>* `createSet **SHORTCUT_NAME** e/ **EXERCISE_DESCRIPTION** c/ **CALORIE_COUNT** + e/ **EXERCISE_DESCRIPTION** c/ **CALORIE_COUNT** + f/ **FOOD_DESCRIPTION** c/ **CALORIE_COUNT**`

Parameters:
* `**FOOD_DESCRIPTION**`: Description of food consumed.
* `**CALORIE_COUNT**`: Amount of calories lost/gained.
* `**EXERCISE_DESCRIPTION**`: Description of exercise done.
* `**SHORTCUT_NAME**`: Name of shortcut.

Example of usage: 
*  `createSet bfast f/ ice cream c/ 78 + e/ jumping jacks c/ 100`

```
=====================================================================================================
You have created a shortcut containing:
1. Food: ice cream, Calories: 78
2. Exercise: jumping jacks, Calories: 100
=====================================================================================================
```

<br>
<br>


### 3.2 Adding a shortcut of repeated task to today's list

Do you want to add in a set of entries all at once? This feature teaches you how to do so!

Format: `addSet **SHORTCUT_NAME**`

Parameters:
* `**SHORTCUT_NAME**`: Name of shortcut created beforehand in [createSet](#31-creating-a-shortcut-for-a-set-of-entries).

Example of usage: 
* `addSet bfast`

```
=====================================================================================================
We are attempting to add activities listed in this shortcut.
=====================================================================================================
=====================================================================================================
Noted! The following has been added into list:
[F] | ice cream | 78
The current activity list has been saved.
=====================================================================================================
=====================================================================================================
[E] | jumping jacks | 100
The current activity list has been saved.
=====================================================================================================
```

<br>
<br>

## 4.0 Add [Puah Siew Wen]

### 4.1 Adding a food entry

Do you want to add a food entry? This feature solves it for you by adding a food entry with its respective calories gained to the list!

Format: `add f/ **FOOD_DESCRIPTION** c/ **CALORIE_COUNT** <d/ **DATE**>`

Parameters:
* `**FOOD_DESCRIPTION**`: Description of food consumed.
* `**CALORIE_COUNT**`: Amount of calories gained.
* `<**DATE**>`: Date in the format YYYY-MM-DD, where YYYY = year, MM = month, DD = day.

> REMINDER: 
>* `d/ **DATE**` is optional, if you entered an `add` command without the date, it will be added to today's list. 

Examples of usage: 

Adding a food entry without the date specified:
* `list` before adding:

```
-----------------------------------------
|  2020-11-07  |  Net Calorie: 30 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple_____________________________________________________30
```

* `add f/ mushroom soup c/ 77`

```
====================================================================================
Noted! The following has been added into list:
====================================================================================
[F] | mushroom soup | 77
====================================================================================
The current activity list has been saved.
====================================================================================
```

* `list` after adding

```
------------------------------------------
|  2020-11-07  |  Net Calorie: 107 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple_____________________________________________________30

2          Food           mushroom soup_____________________________________________77
```

Adding a food entry with the date specified:
* `list 2020-11-05` before adding

```
-----------------------------------------
|  2020-11-05  |  Net Calorie: 77 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           jelly_____________________________________________________77
```

* `add f/ banana cake c/ 70 d/ 2020-11-05`

```
====================================================================================
Noted! The following has been added into list:
====================================================================================
[F] | banana cake | 70
====================================================================================
The current activity list has been saved.
====================================================================================
```

* `list 2020-11-05` after adding

```
------------------------------------------
|  2020-11-05  |  Net Calorie: 147 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           jelly_____________________________________________________77

2          Food           banana cake_______________________________________________70
```

<br>
<br>

### 4.2 Adding an exercise entry

Do you want to add an exercise entry? This feature solves it for you by adding an exercise entry with its respective calories lost to the list.

Format: `add e/ **EXERCISE_DESCRIPTION** c/ **CALORIE_COUNT** <d/ **DATE**>`

Parameters:
* `**EXERCISE_DESCRIPTION**`: Description of exercise done.
* `**CALORIE_COUNT**`: Amount of calories lost.
* `<**DATE**>`: Date in the format YYYY-MM-DD, where YYYY = year, MM = month, DD = day.

> REMINDER: 
>* `d/ **DATE**` is optional, if you entered an `add` command without the date, it will be added to today's list. 

Examples of usage: 

Adding an exercise entry without the date specified:
* `list` before adding

```
------------------------------------------
|  2020-11-07  |  Net Calorie: 177 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple_____________________________________________________30

2          Food           mushroom soup_____________________________________________77

3          Food           banana cake_______________________________________________70
```

* `add e/ jumping c/ 65`

```
====================================================================================
Noted! The following has been added into list:
====================================================================================
[E] | jumping | 65
====================================================================================
The current activity list has been saved.
====================================================================================
```

* `list` after adding

```
------------------------------------------
|  2020-11-07  |  Net Calorie: 112 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple_____________________________________________________30

2          Food           mushroom soup_____________________________________________77

3          Food           banana cake_______________________________________________70

4        Exercise         jumping___________________________________________________65
```

Adding an exercise entry with the date specified:
* `list 2020-11-05` before adding

```
------------------------------------------
|  2020-11-05  |  Net Calorie: 147 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           jelly_____________________________________________________77

2          Food           banana cake_______________________________________________70
```

* `add e/ jumping c/ 65`

```
====================================================================================
Noted! The following has been added into list:
====================================================================================
[E] | jumping | 65
====================================================================================
The current activity list has been saved.
====================================================================================
```

* `list 2020-11-05` after adding

```
-----------------------------------------
|  2020-11-05  |  Net Calorie: 82 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           jelly_____________________________________________________77

2          Food           banana cake_______________________________________________70

3        Exercise         jumping___________________________________________________65
```

<br>
<br>

## 5.0 List [Owen Chew Yang]

### 5.1 Listing entries

Do you want to view your entries for a specific day? You can do this by using this `list` command.

Format: `list <**DATE**>`

Parameters:
* `<**DATE**>`: Date in the format YYYY-MM-DD, where YYYY = year, MM = month, DD = day.

>REMINDER: 
>* The `**DATE**` parameter is optional, if were to you enter list command without the date, it will print out the list of entries for today's date.


Examples of usage: 

Listing without the date specified:
* `list`

```
------------------------------------------
|  2020-11-03  |  Net Calorie: 165 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           rice______________________________________________________150

2        Exercise         running___________________________________________________75

3          Food           ice cream_________________________________________________90
```

Listing with the date specified:
* `list 2020-10-11`

```
------------------------------------------
|  2020-10-11  |  Net Calorie: 270 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1        Exercise         morning run_______________________________________________100

2          Food           rice with veg_____________________________________________200

3          Food           dinner____________________________________________________150

4        Exercise         Gym_______________________________________________________200

5          Food           Supper____________________________________________________200

6          Food           Biscuit___________________________________________________20
```

<br>
<br>

## 6.0 Edit [Puah Siew Wen]

### 6.1 Editing an entry in list

Made a typographical error when entering input? Do you want to edit attributes of a particular entry in the list? This feature solves it for you!

Format: `edit **LIST_INDEX** f/ **FOOD_DESCRIPTION** c/ **CALORIE_COUNT**` OR `edit **LIST_INDEX** e/ **EXERCISE_DESCRIPTION** c/ **CALORIE_COUNT**`

Examples of formats accepted:
1. Editing an entry to food: `edit **LIST_INDEX** f/ **FOOD_DESCRIPTION** c/ **CALORIE_COUNT**`
2. Editing an entry to exercise: `edit **LIST_INDEX** e/ **EXERCISE_DESCRIPTION** c/ **CALORIE_COUNT**`


Parameters:
* `**LIST_INDEX**`: Index of entry to be edited in the latest list pulled out.
* `**FOOD_DESCRIPTION**`: New description of food consumed.
* `**EXERCISE_DESCRIPTION**`: New description of exercise done.
* `**CALORIE_COUNT**`: New amount of calories gained/lost.


>IMPORTANT:
>* Please ensure that you have pulled out the list you want by using `list` command before performing any `edit` commands.
>* This command edits the latest list pulled out. For example, if `list 2020-10-21` is the latest list pulled out, then `edit` will edit according to the index as shown in the list for 2020-10-21.
>* This feature cannot be exercised on the list pulled from the `find` feature.

Example of usage:

Editing an entry to food:
* `list` before editing

```
-----------------------------------------
|  2020-11-07  |  Net Calorie: 92 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple_____________________________________________________30

2          Food           mushroom soup_____________________________________________77

3          Food           banana cake_______________________________________________70

4        Exercise         jumping___________________________________________________65

5        Exercise         brisk walking_____________________________________________20
```

* `edit 1 f/ orange c/ 35`

```
====================================================================================
Noted! The following has been edited:
====================================================================================
[F] | orange | 35
====================================================================================
The current activity list has been saved.
====================================================================================
```

* `list` after editing

```
-----------------------------------------
|  2020-11-07  |  Net Calorie: 97 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           orange____________________________________________________35

2          Food           mushroom soup_____________________________________________77

3          Food           banana cake_______________________________________________70

4        Exercise         jumping___________________________________________________65

5        Exercise         brisk walking_____________________________________________20
```

Editing an entry to exercise: 
* `list 2020-11-05` before editing

```
-----------------------------------------
|  2020-11-05  |  Net Calorie: 97 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           orange____________________________________________________35

2          Food           mushroom soup_____________________________________________77

3          Food           banana cake_______________________________________________70

4        Exercise         jumping___________________________________________________65

5        Exercise         brisk walking_____________________________________________20
```

* `edit 3 e/ 50 sit ups c/ 75`

```
====================================================================================
Noted! The following has been edited:
====================================================================================
[E] | 50 sit ups | 75
====================================================================================
The current activity list has been saved.
====================================================================================
```

* `list 2020-11-05` after editing

```
------------------------------------------
|  2020-11-05  |  Net Calorie: -48 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           orange____________________________________________________35

2          Food           mushroom soup_____________________________________________77

3        Exercise         50 sit ups________________________________________________75

4        Exercise         jumping___________________________________________________65

5        Exercise         brisk walking_____________________________________________20
```

<br>
<br>

## 7.0 Find [Poon Chuan An]

### 7.1.1 Finding entries via description

Do you want to find specific entry/entries? You can search for them based on keywords entered. This feature searches through the descriptions of your entries and lists them out for you.

Format: `find d/ **DESCRIPTION**`

Parameters:
* `**DESCRIPTION**`: Keyword that the entry/entries should contain.

Example of usage:
* `find d/ rice with veg`

```
No.        Date                Type                                Description                        Calories gain or lost
---------------------------------------------------------------------------------------------------------------------------
1        2020-10-11            Food              rice with veg________________________________________________200

2        2020-10-09            Food              rice with veg________________________________________________200

```

<br>

### 7.1.2 Finding entries via calories

Do you want to find specific entry/entries? You can search for them based on keywords entered. This feature searches through the calories of your entries and lists them out for you.


Format: `find c/ **CALORIES**`

Parameters:
* `**CALORIES**`: Number of calories that the entry/entries should contain.

Example of usage:
* `find c/ 100` 

```
No.        Date                Type                                Description                        Calories gain or lost
---------------------------------------------------------------------------------------------------------------------------
1        2020-10-11          Exercise              morning run__________________________________________________100

```

<br>
<br>

### 7.2.1 Finding entries via keywords - all

You can make use of this advanced find command to perform more complex find operations.
This command will search through your entries and return entry/entries matching ALL the description keywords you have entered. 

Format for find by all descriptions: `find a/ **DESCRIPTION1** / **DESCRIPTION2** / **DESCRIPTION3** ...`

> IMPORTANT:
>* There is no limit to the number of descriptions allowed.

Parameters:
* `**DESCRIPTION1**`: Keyword to look for from the list.
* `**DESCRIPTION2**`: Keyword to look for from the list.
* so on...

Example of usage:
 * `find a/ running / 10km / 5pm`
 
```
No.        Date                Type                                Description                        Calories gain or lost
---------------------------------------------------------------------------------------------------------------------------
1        2020-10-11          Exercise              running at stadium for 10km at 5pm evening_________________100
```

<br>
<br>

### 7.2.2 Finding entries via keywords - either

Do you want to show even more search results? As long as just one of your entry has a description containing the keyword(s), that entry will be listed. 

Format for find by just one matching description: `find e/ **DESCRIPTION1** / **DESCRIPTION2** / **DESCRIPTION3** ...`

> IMPORTANT:
>* There is no limit to the number of keywords allowed.

Parameters:
* `**DESCRIPTION1**`: Keyword to look for from the list.
* `**DESCRIPTION2**`: Keyword to look for from the list.
* so on...

Example of usage:
* `find e/ sleeping / 5pm` 

```
No.        Date                Type                                Description                        Calories gain or lost
---------------------------------------------------------------------------------------------------------------------------
1        2020-10-11          Exercise              running at stadium for 10km at 5pm evening_________________100
```

<br>
<br>

## 8.0 Move [Owen Chew Yang]

### 8.1 Moving an entry to another position in the list
Have you entered the entries in the wrong order?
You can use this `move` command to move an entry to another position in the list to organize your entries!

Format: `move from/ **INDEX_1** below/ **INDEX_2**`

Parameters:
* `**INDEX_1**`: Index of the entry to be moved from.
* `**INDEX_2**`: Index of the entry to be inserted below.

Examples of usage:

*`list 2020-10-11` before moving.

```
------------------------------------------
|  2020-10-11  |  Net Calorie: 450 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           breakfast_______________________________________________100

2          Food           second breakfast________________________________________200

3          Food           tea break_______________________________________________150

```

* `move from/ 3 below/ 1`
```
====================================================================================
Activity has been successfully moved!
====================================================================================
```

*`list 2020-10-11` after `move from/ 3 below/ 1`. You will notice that the entry 'tea break' has been moved from index 3 to index 2.

```
------------------------------------------
|  2020-10-11  |  Net Calorie: 450 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           breakfast_______________________________________________100

2          Food           tea break_______________________________________________150

3          Food           second breakfast________________________________________200

```

<br>
<br>

## 9.0 Delete [Rani Karthigeyan Rajendrakumar]

### 9.1 Deleting an entry in list

You can delete an entry in the list using the `delete` command.

>IMPORTANT:
>* Please ensure that you have pulled out the list you want by using `list` command before performing any `delete` commands.

Format: `delete **INDEX**`

Parameters:
* `**INDEX**`: Index of entry to be deleted in the latest list pulled out.

Example of usage:
* `list` before deleting

```
------------------------------------------
|  2020-10-11  |  Net Calorie: 650 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple___________________________________________________100

2          Food           melon___________________________________________________200

3          Food           banana__________________________________________________150

4          Food           orange__________________________________________________200

```

* `delete 2`

```
====================================================================================
Activity removed!
====================================================================================
====================================================================================
The current activity list has been saved.
====================================================================================
```

* `list` after deleting

```
------------------------------------------
|  2020-10-11  |  Net Calorie: 450 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple___________________________________________________100

2          Food           banana__________________________________________________150

3          Food           orange__________________________________________________200

```

<br>
<br>

### 9.2 Deleting all entries in list

You can delete all your entries from today's list at once with this feature!

Format : `delete all/`

Example of usage:
* `list` before deleting

```
------------------------------------------
|  2020-10-11  |  Net Calorie: 450 kcal  |
------------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           apple___________________________________________________100

2          Food           banana__________________________________________________150

3          Food           orange__________________________________________________200
```

* `delete all/`

```
Are you sure you want to delete all activities in today's list? [yes/no]
```

* `yes` to confirm deletion

```
====================================================================================
All activities have been deleted
====================================================================================
```

* `list` after deleting

```
====================================================================================
List is empty!
====================================================================================
```


<br>
<br>

## 10.0 Graph [Rani Karthigeyan Rajendrakumar]

### 10.1 Showing past net calories

Do you want to see your calorie progress all at once? You can view a graphical representation of your recent net calories relative to your target calories for up to 7 days using this feature.

Format: `graph`

Example of usage:
* `graph`

```
2100|      |-|                           
    |      | |                           
    |      | |                           
1886|******|*|***************************
    |      | |         |-|               
    ||-|   | |         | |               
    || |   | |   |-|   | |               
    || |   | |   | |   | |               
    || |   | |   | |   | |               
    || |   | |   | |   | |   |-|         
1210|| |   | |   | |   | |   | |   |-|   
    |-+-----+-----+-----+-----+-----+--
    21/10 22/10 23/10 24/10 25/10 26/10 
```

> Things to take note of:
>* `***` shows the target calories.
>* Displays up to latest 7 days of entries.

<br>
<br>

## 11.0 Chaining [Puah Siew Wen]

### 11.1 Chaining of features

Do you wish to save time from individually typing in the features? Do you want to type out all the commands you want at one go?
Chaining is the right feature for you! 

>IMPORTANT:
>* Chaining is only available for 4 features:
>* [list](#50-list-owen-chew-yang), [add](#40-add-puah-siew-wen), [edit](#60-edit-puah-siew-wen) and [graph](#100-graph-rani-karthigeyan-rajendrakumar).
>* The respective formats to adhere to for each feature still applies.

Format: *Not extensive, there is a lot of combinations available.* <br> 
        *Your commands just need to be separated by `&&`.*
        
Examples of formats accepted:
>* `add f/**FOOD_DESCRIPTION** c/**CALORIE_COUNT** <d/**DATE**> && add e/**EXERCISE_DESCRIPTION** c/**CALORIE_COUNT** <d/**DATE**>`
>* `add f/**FOOD_DESCRIPTION** c/**CALORIE_COUNT** <d/**DATE**> && list <**DATE**>`
>* `edit INDEX e/**EXERCISE_DESCRIPTION** c/**CALORIE_COUNT** <d/**DATE**> && add e/**EXERCISE_DESCRIPTION** c/**CALORIE_COUNT** <d/**DATE**> && list <**DATE**>`
>* `list <**DATE**> && list <**DATE**> && list <**DATE**>`
>* `list <**DATE**> && graph`

Examples of usage: 

1st example:
* `add f/ ice cream c/ 90 && add e/ running c/ 50 && list`

```
====================================================================================
Noted! The following has been added into list:
====================================================================================
[F] | ice cream | 90
====================================================================================
The current activity list has been saved.
====================================================================================

====================================================================================
Noted! The following has been added into list:
====================================================================================
[E] | running | 50
====================================================================================
The current activity list has been saved.
====================================================================================

-----------------------------------------
|  2020-11-07  |  Net Calorie: -8 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           orange____________________________________________________35

2          Food           mushroom soup_____________________________________________77

3        Exercise         50 sit ups________________________________________________75

4        Exercise         jumping___________________________________________________65

5        Exercise         brisk walking_____________________________________________20

6          Food           ice cream_________________________________________________90

7        Exercise         running___________________________________________________50
```

2nd example:
* `add f/ ice cream c/ 90 d/ 2020-11-07 && list && edit 7 e/ walking c/ 15`

```
====================================================================================
Noted! The following has been added into list:
====================================================================================
[F] | ice cream | 90
====================================================================================
The current activity list has been saved.
====================================================================================

-----------------------------------------
|  2020-11-07  |  Net Calorie: 82 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           orange____________________________________________________35

2          Food           mushroom soup_____________________________________________77

3        Exercise         50 sit ups________________________________________________75

4        Exercise         jumping___________________________________________________65

5        Exercise         brisk walking_____________________________________________20

6          Food           ice cream_________________________________________________90

7        Exercise         running___________________________________________________50

8          Food           ice cream_________________________________________________90

====================================================================================
Noted! The following has been edited:
====================================================================================
[E] | walking | 15
====================================================================================
The current activity list has been saved.
====================================================================================
```

3rd example:
* `list && graph`

```
-----------------------------------------
|  2020-11-08  |  Net Calorie: 15 kcal  |
-----------------------------------------
No.        Type                        Description                        Calories gain or lost
-----------------------------------------------------------------------------------------------
1          Food           bun_______________________________________________________80

2        Exercise         jumping___________________________________________________65

2766|******************************************
    |                                          
    |                                          
    |                                          
    |                                          
    |                                          
    |                                          
    |                                          
    |                                          
    ||-|                                       
15  || |   |-|   |-|   |-|   |-|   |-|   |-|   
    |-+-----+-----+-----+-----+-----+-----+--
    02/11 03/11 04/11 05/11 06/11 07/11 08/11 
```

<br>
<br>

## 12.0 Exiting the program

Do you want to exit **traKCAL**? Use this command to do so.

Format: `bye`

Example of usage:
* `bye`

```
=====================================================================================================
| Thank you for using traKCAL. See you again!                                                       |
=====================================================================================================
```

<br>
<br>

# Frequently asked questions
This section contains frequently asked questions.

**Q1**: How do I transfer my data to another computer? 

**A1**: Install the application in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous **traKCAL** folder.

**Q2**: How do I know if the data I have input is saved?

**A2**: **traKCAL** autosaves data. Unless an error message is printed out, the data entered is saved.

**Q3**: I keep getting errors with the storage files when using **traKCAL**, how do I solve them?

**A3**: Make sure your csv file is not open when **traKCAL** is running to prevent write conflicts.
        Alternatively, you can delete the tpdata file to reset your data. 

**Q4**: Is **traKCAL** optimized for all operating systems?

**A4**: **traKCAL** is available for the download on all major Operating Systems(OS) such as Windows, Mac and Linux.

**Q5**: How do I contact the developers if I have doubts or issues to raise?

**A5**: We have tried to make **traKCAL** as bug-free as possible, but if you still encounter issues, you can reach us via email(e0425705@u.nus.edu) or by raising a github issue [here](https://github.com/AY2021S1-CS2113T-T09-4/tp/issues) and we will attempt to fix it as soon as possible.

<br>

# Command Summary

This section gives you a cheat sheet of commands available. 
The following are only examples of what the input command can look like.
For more information, please look at the respective section for the feature details.

>Things you should take note of:
>* Words that look like THIS are parameters to be supplied by you.
>* Words that look like <this> are optional parameters

Action         | Format | Example
-------------- | ---------- | --------
[Help](#10-viewing-help-puah-siew-wen)| `help` | `help`
[Create User Profile](#21-creating-a-new-user-profile) | `user c/` |  `user c/`
[Edit User Profile](#22-editing-user-profile) | `user e/ <n/ NAME>, <g/ GENDER>, <w/ WEIGHT>, <h/ HEIGHT>, <age/ AGE>, <al/ ACTIVITY_FACTOR>, <goal/ WEIGHT_GOALS>` | `user e/ n/ Sammy`
[List User Profile](#23-viewing-your-profile) | `user l/` | `user l/`
[Create Shortcut](#31-creating-a-shortcut-for-a-set-of-entries) | `createSet SHORTCUT f/ FOOD_DESCRIPTION c/ CALORIE COUNT + e/ EXERCISE_DESCRIPTION c/ CALORIE COUNT` | `createSet morning routine f/ oatmeal c/ 200 + e/ yoga c/ 200`
[Add Shortcut](#32-adding-a-shortcut-of-repeated-task-to-todays-list) | `addSet SHORTCUT` | `addSet morning routine`
[Add Food](#41-adding-a-food-entry) | `add e/ FOOD_DESCRIPTION c/ CALORIE_COUNT <d/ DATE>` | `add e/ ice cream c/ 78 d/ 2020-10-19`
[Add Exercise](#42-adding-an-exercise-entry) | `add e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT <d/ DATE>` | `add e/ jumping c/ 65`
[List](#50-list-owen-chew-yang) | `list <DATE>` | `list 2020-10-24` 
[Edit Activity to Food](#61-editing-an-entry-in-list) | `edit LIST_INDEX f/ FOOD_DESCRIPTION c/ CALORIE_COUNT` | `edit 1 f/ ice kacang c/ 150`
[Edit Activity to Exercise](#61-editing-an-entry-in-list) | `edit LIST_INDEX e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT` | `edit 1 e/ running c/ 100`
[Find Description](#711-finding-entries-via-description) | `find d/ DESCRIPTION` | `find d/ apple`
[Find Calorie](#712-finding-entries-via-calories) | `find c/ CALORIE` | `find c/ 55`
[Find Either](#722-finding-entries-via-keywords---either) | `find e/ DESCRIPTION1 / DESCRIPTION2 / DESCRIPTION3` | `find e/ apple / orange / grape`
[Find All](#721-finding-entries-via-keywords---all) | `find a/ DESCRIPTION1 / DESCRIPTION2 / DESCRIPTION3` | `find a/ running / jumping / cake`
[Move Activity](#80-move-owen-chew-yang) | `move from/ INDEX1 below/ INDEX2` | `move from/ 5 below/ 2`
[Delete Entry](#91-deleting-an-entry-in-list) | `delete INDEX` | `delete 2`
[Delete All for Today](#92-deleting-all-entries-in-list)| `delete all/` | `delete all/`
[Graph](#100-graph-rani-karthigeyan-rajendrakumar) | `graph` | `graph` 
[Chain](#110-chaining-puah-siew-wen) | `add e/ FOOD_DESCRIPTION c/ CALORIE_COUNT <d/ DATE> && list && graph` | `add e/ ice cream c/ 78 && list && graph`
[Exit](#120-exiting-the-program) | `bye` | `bye`