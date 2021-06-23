# The Schwarzenegger - User Guide

By: `Team F11-1` Since: `Sept 2020` License: `MIT`

## Table of Contents

1. [**Introduction**](userguide.md#intro)
2. [**Quick Start**](userguide.md#quickstart)
3. [**Features**](userguide.md#features)  


   3.1. [Main Menu](userguide.md#main-menu)  


       3.1.1. [Viewing Help: `help`](userguide.md#main-help)  


       3.1.2. [Entering Profile Menu: `profile`](userguide.md#main-profile)  


       3.1.3. [Entering Diet Menu: `diet`](userguide.md#main-diet)  


       3.1.4. [Entering Workout Menu: `workout`](userguide.md#main-workout)  


       3.1.5.  [Ending the Schwarzenegger: `end`](userguide.md#main-end)  


   3.2. [Profile Menu](userguide.md#profile-menu)  


       3.2.1. [Viewing Help: `help`](userguide.md#profile-help)  


       3.2.2. [Adding a Profile: `add`](userguide.md#profile-add)  


       3.2.3. [Viewing a Profile: `view`](userguide.md#profile-view)  


       3.2.4. [Editing a Profile: `edit`](userguide.md#profile-edit)  


       3.2.5. [Deleting a Profile: `delete`](userguide.md#profile-delete)  


       3.2.6. [Returning to Main Menu: `end`](userguide.md#profile-end)  


   3.3. [Diet Menu](userguide.md#diet-menu)  


       3.3.1. [Viewing Help: `help`](userguide.md#diet-help)  


       3.3.2. [Starting a New Diet Session: `new`](userguide.md#diet-start)  


           3.3.2.1. [Viewing Help in Diet Session: `help`](userguide.md#meal-help)  


           3.3.2.2. [Adding Food Items to the Current Diet Session: `add`](userguide.md#meal-add)  


           3.3.2.3. [Listing Food Items from the Current Diet Session: `list`](userguide.md#meal-list)  


           3.3.2.4. [Deleting Food Items from the Current Diet Session: `delete`](userguide.md#meal-delete)  


           3.3.2.5. [Clearing All Food Items from the Current Diet Session: `clear`](userguide.md#meal-clear)  


           3.3.2.6. [Searching for Food Items from the Current Diet Session: `search`](userguide.md#meal-search)  


           3.3.2.7. [Ending the Current Diet Session: `end`](userguide.md#meal-end)  


       3.3.3. [Listing All Past Diet Sessions: `list`](userguide.md#diet-list)  


       3.3.4. [Editing a Past Diet Session: `edit`](userguide.md#diet-edit)  


       3.3.5. [Deleting a Past Diet Session: `delete`](userguide.md#diet-delete)  


       3.3.6. [Clearing All Past Diet Sessions: `clear`](userguide.md#diet-clear)  


       3.3.7. [Searching for Past Diet Sessions: `search`](userguide.md#diet-search)  


       3.3.8. [Returning to Main Menu: `end`](userguide.md#diet-end)  


   3.4. [Workout Menu](userguide.md#workout-menu)  


       3.4.1. [Viewing Help: `help`](userguide.md#workout-help)  


       3.4.2. [Starting a New Workout Session: `new`](userguide.md#workout-start)  


           3.4.2.1. [Viewing Help in Workout Session: `help`](userguide.md#ws-help)  


           3.4.2.2. [Adding a Move to the Current Workout Session: `add`](userguide.md#ws-add)  


           3.4.2.3. [Listing All Moves from the Current Workout Session: `list`](userguide.md#ws-list)  


           3.4.2.4. [Deleting a Move From the Current Workout Session: `delete`](userguide.md#ws-delete)  


           3.4.2.5. [Searching for a Keyword in the Current Workout Session: `search`](userguide.md#ws-search)  


           3.4.2.6. [Ending the Current Workout Session: `end`](userguide.md#ws-end)  


       3.4.3. [Listing All Past Workout Sessions: `list`](userguide.md#workout-list)  


       3.4.4. [Editing a Workout Session: `edit`](userguide.md#workout-edit)  


       3.4.5. [Deleting a Workout Session: `delete`](userguide.md#workout-delete)  


       3.4.6. [Searching a List of Workout Sessions: `search`](userguide.md#workout-search)  


       3.4.7. [Clearing All Workout Sessions: `clear`](userguide.md#workout-clear)  


       3.4.8. [Returning to Main Menu: `end`](userguide.md#workout-end)

4. [**Command Summary**](userguide.md#command-summary)
5. [**Notes**](userguide.md#notes)
6. [**FAQ**](userguide.md#faq)

## 1. [Introduction](userguide.md) <a id="intro"></a>

The Schwarzenegger is a desktop command line interface-based app for managing all your needs regarding fitness. With the built-in personal assistant, you are able to track your daily workout and diet sessions based on your profile. If you can type fast, The Schwarzenegger can help you maximise your efficiency for maintaining fitness.

## 2. [Quick Start](userguide.md) \[Khoa\] <a id="quickstart"></a>

When you first start using The Schwarzenegger, please ensure that you follow the instructions below:

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `CS2113T-F11-1.TheSchwarzenegger.jar` from [here](https://github.com/AY2021S1-CS2113T-F11-1/tp/releases).
3. Copy the file to the folder you want to use as the home folder for The Schwarzenegger.
4. Open Command Prompt \(on Windows\)  or Terminal \(on MacOS/ Linux\) and change to the directory of the folder of the application in step 3.
5. Key in `java -jar [CS2113T-F11-1][TheSchwarzenegger].jar` and press Enter. If the setup is correct, you should see something like below:

   ```text
        _________________________________________________________________________________________________
           _____        _                                                                           
          / ____|      | |                                                                          
         | (___    ___ | |__ __      __ __ _  _ __  ____ ___  _ __    ___   __ _   __ _   ___  _ __ 
          \___ \  / __|| '_ \\ \ /\ / // _` || '__||_  // _ \| '_ \  / _ \ / _` | / _` | / _ \| '__|
          ____) || (__ | | | |\ V  V /| (_| || |    / /|  __/| | | ||  __/| (_| || (_| ||  __/| |   
         |_____/  \___||_| |_| \_/\_/  \__,_||_|   /___|\___||_| |_| \___| \__, | \__, | \___||_|   
                                                                            __/ |  __/ |            
                                                                           |___/  |___/             
        _________________________________________________________________________________________________

        _________________________________________________________________________________________________
        Welcome new user to Schwarzenegger! :D
        Please add your profile under Profile Menu.
        For more information on command syntax, please type "help".
        _________________________________________________________________________________________________
   ```

6. To use The Schwarzenegger, simply type a valid command into the terminal and press Enter to run the command.  **Example**: Typing `help` command in Main Menu and pressing Enter will show you a list of available commands in Main Menu of The Schwarzenegger and their descriptions.
7. Some example commands you can try:
   * `help` : Shows all the available commands under Main Menu.
   * `profile` : Directs you to Profile Menu where you can add and manage your profile.
   * `diet` : Directs you to Diet Menu where you can add and manage your diet records.
   * `workout` : Directs you to Workout Menu where you can add and manage your workout records.
8. The text before the cursor indicates which menu you are currently in \(e.g. `Main menu >>>>>` indicates that you are currently in the Main Menu\).
9. A summary of all the supported commands in The Schwarzenegger can be found in [Section 4. Command Summary](userguide.md#command-summary).  


   You can refer to [Section 3. Features](userguide.md#features) for the detailed instructions of the various features in The Schwarzenegger.

## 3. [Features](userguide.md) <a id="features"></a>

This section includes 4 sub-sections which will guide you through the features available in Main Menu, Profile Menu, Workout Menu and Diet Menu of the Schwarzenegger. In explaining the syntax, we will adhere to the following format:

**Command Format** \[Khoa\]

* Words that are in `[UPPER_CASE]` format are the parameters to be supplied by you.  


  **Example**: in   
  `add /n [NAME] /h [HEIGHT] /w [CURRENT_WEIGHT] /e [EXPECTED_WEIGHT] /c [DAILY_CALORIE_INTAKE]`   
  command, `[NAME]`, `[HEIGHT]`, `[CURRENT_WEIGHT]`, `[EXPECTED_WEIGHT]` and `[DAILY_CALORIE_INTAKE]` are parameters which can be used as `add /n Schwarzenegger /h 188 /w 113 /e 100 /c 2500`.

* Words that are enclosed with `<` and `>` are optional parameters. If all of the parameters are optional, you need to supply at least 1 of them.   


  **Example**: in   
  `edit </n [NAME]> </h [HEIGHT]> </w [CURRENT_WEIGHT]> </e [EXPECTED_WEIGHT]> </c [DAILY_CALORIE_INTAKE]>`   
  command, `</n [NAME]>`, `</h [HEIGHT]>`, `</w [CURRENT_WEIGHT]>`, `</e [EXPECTED_WEIGHT]>` and `</c [DAILY_CALORIE_INTAKE]>` are optional parameters which can be used as `edit /h 180`, `edit /h 180 /w 50` or `edit /h 180 /w 50 /e 55`. Since all of them are optional parameters, you need to supply at least 1 of them.

* Optional parameters with `…` after them can be used multiple times including zero times.   


  **Example**: for `<TAG>...`, the following format for Search Command in Workout Menu: `search </d [DATE]> </t [TAG]...>` can be used as `search /t le` or `search /t chest, bicep`.

* Parameters can be in any order.   
 

  **Example**: if the command specifies `/n [NAME] /h [HEIGHT] /w [CURRENT_WEIGHT] /e [EXPECTED_WEIGHT] /c [DAILY_CALORIE_INTAKE]`, `/h [HEIGHT] /w [CURRENT_WEIGHT] /n [NAME] /c [DAILY_CALORIE_INTAKE] /e [EXPECTED_WEIGHT]` is also acceptable.

**Notations Used** \[Khoa\]

Below are the meaning of icons for you to take note of while using a feature.

* :bulb: indicates a tip.
* :warning: indicates a warning.

### 3.1. [Main Menu](userguide.md) \[Khoa\] <a id="main-menu"></a>

You can get access to Profile Menu, Diet Menu and Workout Menu from Main Menu. The available commands in Main Menu are listed below:

#### 3.1.1. [Viewing Help: `help`](userguide.md) <a id="main-help"></a>

You can see a complete list of available commands under Main Menu and how to use them.

**Format:** `help`  


**Example:** Whenever you want to see get assistance in the Main Menu, you follow the steps below: 1. Type `help` into your console and press Enter to execute it.

```text
![main-help-1](pictures/UG_screenshots/main-help-step-1.png)
```

1. You should be able to see a list of available commands like the screenshot below:

   ![main-help-2](../.gitbook/assets/main-help-step-2.png)

#### 3.1.2. [Entering Profile Menu: `profile`](userguide.md) <a id="main-profile"></a>

The program will direct you to the Profile Menu.

**Format:** `profile`

**Example**: 1. Type `profile` into your console and press Enter to execute it. 1. If the redirection is successful, you should be able to see the message below:

```text
        _________________________________________________________________________________________________
        Starting Profile Menu......
        _________________________________________________________________________________________________
```

> :bulb: You can verify that you are in Profile Menu if the text before your cursor becomes `Profile Menu >>>>>`.
>
> :bulb: You can return to Main Menu from Profile Menu by entering command `end`. You can refer to [Section 3.2.6. Returning to Main Menu](userguide.md#profile-end) for more details.

#### 3.1.3. [Entering Diet Menu: `diet`](userguide.md) <a id="main-diet"></a>

The program will direct you to the Diet Menu.

**Format:** `diet`

**Example**: 1. Type `diet` into your console and press Enter to execute it.

1. If the redirection is successful, you should be able to see the message below:

   ```text
        _________________________________________________________________________________________________
        Starting Diet Menu...
        _________________________________________________________________________________________________
   ```

> :bulb: You can verify that you are in Diet Menu if the text before your cursor becomes `Diet Menu >>>>>`.
>
> :bulb: You can return to Main Menu from Diet Menu by entering command `end`. You can refer to [Section 3.3.8. Returning to Main Menu](userguide.md#diet-end) for more details.

#### 3.1.4. [Entering Workout Menu: `workout`](userguide.md) <a id="main-workout"></a>

The program will direct you to the Workout Menu.

**Format:** `workout`

**Example**: 1. Type `workout` into your console and press Enter to execute it. 1. If the redirection is successful, you should be able to see the message below:

```text
        _________________________________________________________________________________________________
        Starting Workout Menu...
        _________________________________________________________________________________________________
```

> :bulb: You can verify that you are in Workout Menu if the text before your cursor becomes `Workout Menu >>>>>`.
>
> :bulb: You can return to Main Menu from Workout Menu by entering command `end`. You can refer to [Section 3.4.8. Returning to Main Menu](userguide.md#workout-end) for more details.

#### 3.1.5. [Ending The Schwarzenegger: `end`](userguide.md) <a id="main-end"></a>

You use this command when you want to terminate The Schwarzenegger.

**Format:** `end`

**Example**: 1. Type `end` into your console and press Enter to execute it. 1. If the program exits successfully, you should be able to see the message below:

```text
        ______________________________________________________________________________
        Bye, you have exited The Schwarzenegger.
        ______________________________________________________________________________
```

### 3.2. [Profile Menu](userguide.md) \[Khoa\] <a id="profile-menu"></a>

Profile Menu manages your profile including your name, physique data and expected daily calories intake.

Click [here](userguide.md#main-profile) to learn how to enter Profile Menu.

#### 3.2.1. [Viewing Help: `help`](userguide.md) <a id="profile-help"></a>

You can see a complete list of available commands under Profile Menu and how to use them.

**Format:** `help`

**Example:** 1. Type `help` into your console and press Enter to execute it. 2. If the execution is successful, you should be able to see the message below:

```text
        _________________________________________________________________________________________________
        Add      FORMAT:      add /n [NAME] /h [HEIGHT] /w [CURRENT_WEIGHT] /e [EXPECTED_WEIGHT] /c [DAILY_CALORIE_INTAKE]
              DESCRIPTION: Add your new profile
        View     FORMAT:      view
              DESCRIPTION: View your profile
        Edit     FORMAT:      edit </n [NAME]> </h [HEIGHT]> </w [CURRENT_WEIGHT]> </e [EXPECTED_WEIGHT]> </c [DAILY_CALORIE_INTAKE]>
              DESCRIPTION: Edit your existing profile. You may edit from 1 field to all fields
        Delete   FORMAT:      delete
              DESCRIPTION: Delete your existing profile
        End      FORMAT:      end
              DESCRIPTION: Go back to Main Menu
        _________________________________________________________________________________________________
```

#### 3.2.2. [Adding a Profile: `add`](userguide.md) <a id="profile-add"></a>

You can add your profile for the most customized experience in The Schwarzenegger. Your height should in centimeters, your current weight and expected weight should be in kilograms, and your expected daily calorie intake should be in calories.

**Format:** `add /n [NAME] /h [HEIGHT] /w [CURRENT_WEIGHT] /e [EXPECTED_WEIGHT] /c [DAILY_CALORIE_INTAKE]`

**Example:** When you want to add your profile to the application, you follow the steps below: 1. Ensure that you are in Profile Menu.  
 Refer to [Section 3.1.2. Entering Profile Menu](userguide.md#main-profile) to learn how to enter Profile Menu. ![add-profile-step-1](../.gitbook/assets/add-profile-step-1.png)

1. Type `add /n Schwarzenegger /h 188 /w 113 /e 100 /c 2500` into your console and press Enter to execute it.  
    This command adds a profile with the name Schwarzenegger, height 188 cm, weight 113 kg, expected weight 100 kg and expected daily intake of 2500 calories.

   ![add-profile-step-2](../.gitbook/assets/add-profile-step-2.png)

2. If the execution is successful, you should be able to see the confirmation message below:

   ![add-profile-step-3](../.gitbook/assets/add-profile-step-3.png)

> :warning: The Schwarzenegger currently does not support names containing "/" character.

#### 3.2.3. [Viewing a Profile: `view`](userguide.md) <a id="profile-view"></a>

You can view your profile recorded in the program. The Schwarzenegger will also show the additional information below:

* Your current BMI classification so that you can have a good suggestion on your current fitness level.
* Comparison between your calorie intake today with your expected daily calorie intake.
* Suggestion on adjusting your weight expectation for better fitness where applicable. For example, if your expected weight results in the Underweight BMI classification, The Schwarzenegger will show you a tip on editing your weight expectation.

**Format:** `view`

**Example**: 1. Type `view` into your console and press Enter to execute it. 2. If the execution is successful, you should be able to see the message below:

```text
     _________________________________________________________________________________________________
     Here's your profile:
            Name: Schwarzenegger
            Height: 188 cm
            Weight: 113.0 kg
            Expected Weight: 100.0 kg
            Expected daily calorie intake: 2500.0 calories
            Your current BMI: 32.0 (Obesity Class 1)
     By the way, take 2500.0 more calories to achieve your target for today!
     TIP: Edit your expected weight to 76.7 kg to have Normal Weight BMI classification.
          Just type "edit /e 76.7"!
     _________________________________________________________________________________________________
```

> :bulb: If you do not see the tip, it means that your current height and weight expectation is classified as Normal Weight already.

#### 3.2.4. [Editing a Profile: `edit`](userguide.md) <a id="profile-edit"></a>

You can edit the profile after adding to the program.

**Format:** `edit </n [NAME]> </h [HEIGHT]> </w [WEIGHT]> </e [EXPECTED_WEIGHT]> </c [DAILY_CALORIES_INTAKE]>`

**Example**:

* If you want to edit your height, you follow the steps below: 1. Type `edit /h 180` into your console and press Enter to execute it.   
   This command edits your current height to 180 centimeters. 2. If the execution is successful, you should be able to see the confirmation message below:

  ```text
            ______________________________________________________________________________
            Yay! Your profile is edited successfully. Here's your new profile:
                Name: Schwarzenegger
                Height: 180 cm
                Weight: 113.0 kg
                Expected Weight: 100.0 kg
                Expected daily calories intake: 2500.0 calories
                Your BMI: 32.0 (Obesity Class 1)
            ______________________________________________________________________________
  ```

* If you want to edit your height and weight, you follow the steps below: 1. Type `edit /h 180 /w 50` into your console and press Enter to execute it.  
   This command edits your current height to 180 centimeters and current weight to 50 kilograms. 2. If the execution is successful, you should be able to see the confirmation message below:

  ```text
             ______________________________________________________________________________
             Yay! Your profile is edited successfully. Here's your new profile:
                    Name: Schwarzenegger
                    Height: 180 cm
                    Weight: 50.0 kg
                    Expected Weight: 100.0 kg
                    Expected daily calories intake: 2500.0 calories
                    Your BMI: 15.4 (Underweight)
             ______________________________________________________________________________
  ```

* If you want to edit your height, weight and expected weight, you follow the steps below: 1. Type `edit /h 180 /w 50 /e 55` into your console and press Enter to execute it.  
   This command edits your current height to 180 centimeters, current weight to 50 kilograms and expected weight to 55 kilograms. 2. If the execution is successful, you should be able to see the confirmation message below:

  ```text
             ______________________________________________________________________________
             Yay! Your profile is edited successfully. Here's your new profile:
                    Name: Schwarzenegger
                    Height: 180 cm
                    Weight: 50.0 kg
                    Expected Weight: 55.0 kg
                    Expected daily calories intake: 2500.0 calories
                    Your BMI: 15.4 (Underweight)
             ______________________________________________________________________________
  ```

> :bulb: You may edit from 1 field to all fields in your profile.

#### 3.2.5. [Deleting a Profile: `delete`](userguide.md) <a id="profile-delete"></a>

You can use this command to delete your profile from our system.

This command is dangerous as you will not be able to recover the data. After typing this command, you will be asked to reconfirm it by typing in `YES`. Any other input will abort the deletion.

**Format:** `delete`

**Example**: 1. Type `delete` into your console and press Enter to execute it. 2. Type `YES` into your console and press Enter to confirm the deletion. 3. If the execution is successful, you should be able to see the message below:

```text
    _________________________________________________________________________________________________
    Alright, your profile has been cleared!
    _________________________________________________________________________________________________
```

> :warning: Your profile data cannot be recovered once cleared!

#### 3.2.6. [Returning to Main Menu: `end`](userguide.md) <a id="profile-end"></a>

You use this command to exit Profile Menu and return to the Main Menu.

**Format:** `end`

**Example**: 1. Type `end` into your console and press Enter to execute it. 2. If the execution is successful, you should be able to see the message below:

```text
    _________________________________________________________________________________________________
    Returning to Main Menu...
    _________________________________________________________________________________________________
```

### 3.3. [Diet Menu](userguide.md) \[Zeon\] <a id="diet-menu"></a>

Diet Menu manages your diet sessions which record food items and calories intake.

Click [here](userguide.md#main-diet) to learn how to enter Diet Menu.

#### 3.3.1. [Viewing Help: `help`](userguide.md) <a id="diet-help"></a>

You can see a complete list of available commands under Diet Menu and how to use them.

**Format:** `help`

**Example:** `help`

Expected outcome:

```text
     _________________________________________________________________________________________________
     New      FORMAT:      new </d [DATE]> </t [TAG]>
              DESCRIPTION: Create a new diet session
     List     FORMAT:      list
              DESCRIPTION: Show all past diet sessions
     Delete   FORMAT:      delete [INDEX]
              DESCRIPTION: Delete the diet session at the input index
     Edit     FORMAT:      edit [INDEX]
              DESCRIPTION: Edit the diet session at the input index
     Search   FORMAT:      search </s [STARTING_DATE]> </e [END_DATE]> </t [TAG]>
              DESCRIPTION: Search the diet session in between starting and end dates with a specific tag
     Clear    FORMAT:      clear
              DESCRIPTION: Clear all past diet sessions
     End      FORMAT:      end
              DESCRIPTION: Go back to Main Menu
     _________________________________________________________________________________________________
```

#### 3.3.2. [Starting a New Diet Session: `new`](userguide.md) <a id="diet-start"></a>

You can create a new diet session with this command. The date and tag can be added on creation with "/d" for date and "/t" for meal type.

You will be directed immediately into the new diet session. You may verify that as seen from how the cursor changes from

`Diet Menu >>>>>`

to

`Diet Menu > New Diet Session >>>>>`.

**Format:** `new </d [DATE]> </t [TAG]>`

**Example:**

* `new`  


  This command creates a new diet session tagged as unspecified with today's date.  

**Example:** Whenever you want to create a new Diet Session, you follow the steps below: 1. Ensure that you are in the Diet Menu.  
 Refer to [Section 3.1.3. Entering Diet Menu](userguide.md#main-diet) to learn how to enter the Diet Menu.

```text
![new-diet-session-1](pictures/UG_screenshots/new-dietsession-step-1.png)
```

1. Type new /d 2020-11-09 /t lunch into your console and press Enter to execute it. This command starts a Diet Session of date 9th September 2020 with tag lunch.

   ![new-diet-session-2](../.gitbook/assets/new-dietsession-step-2.png)

2. If the execution is successful, you should be able to see the confirmation message below.

   ![new-diet-session-3](../.gitbook/assets/new-dietsession-step-3.png)

3. You will also be redirected to the Diet Session interface, as seen below.

   ![new-diet-session-4](../.gitbook/assets/new-dietsession-step-4.png)

> :warning: You may choose not to add the date or tag, but diet sessions with the same date and tag will be overwritten!

#### 3.3.2.1. [Viewing Help in Diet Session: `help`](userguide.md) \[Shukai\] <a id="meal-help"></a>

You can see a complete list of available commands under Diet Session and how to use them.

**Format:** `help`

**Example:** `help`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Add      FORMAT:      add [FOOD_NAME] /c [CALORIES]
              DESCRIPTION: Add a new food item
     List     FORMAT:      list
              DESCRIPTION: Show all food items
     Delete   FORMAT:      delete [INDEX]
              DESCRIPTION: Delete the food item at the input index
     Search   FORMAT:      search [FOOD_NAME]
              DESCRIPTION: Search the diet session for food with the name specified
     Clear    FORMAT:      clear
              DESCRIPTION: Clear all food items
     End      FORMAT:      end
              DESCRIPTION: Go back to the Diet Menu.
     _________________________________________________________________________________________________
```

#### 3.3.2.2. [Adding Food Items to the Current Diet Session: `add`](userguide.md) <a id="meal-add"></a>

This command adds a food item into the current diet session.

**Format:** `add [FOOD_NAME] /c [CALORIES]`

**Example:** Whenever you want to add new food items in the diet session, you follow the steps below: 1. Locate yourself in the diet session menu as shown in the screenshot below:

```text
![add-new-food-item-1](pictures/UG_screenshots/add-new-food-item-step-1.JPG)
```

1. You can add food items by following the format `add [FOOD_NAME] /c [CALORIES]` after the prompt as shown in the screenshot:

   ![add-new-food-item-2](../.gitbook/assets/add-new-food-item-step-2.JPG)

2. You should be able to see a message showing that you have added the food item like the screenshot below:

   ![add-new-food-item-3](../.gitbook/assets/add-new-food-item-step-3.JPG)

Expected outcome:

```text
     _________________________________________________________________________________________________
     Yay! You have added chicken nuggets with calories: 120.0
     _________________________________________________________________________________________________
```

> :bulb: Your calories per food item is capped at 200,000

#### 3.3.2.3. [Listing Food Items from the Current Diet Session: `list`](userguide.md) <a id="meal-list"></a>

Lists all the added food items for the current diet session, with a numbered sequence according to sequence entered.

**Format:** `list`

**Example:** `list`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Index   Food            Calories 
     1       chicken nuggets 120.0     
     2       fries           240.0

     Your total calories for this meal is 360.0.
     _________________________________________________________________________________________________
```

#### 3.3.2.4. [Deleting Food Items from the Current Diet Session: `delete`](userguide.md) <a id="meal-delete"></a>

You can remove food items from your list according to the index in the current meal session list.

**Format:** `delete [INDEX]`

**Example:** `delete 1`

Expected outcome:

```text
     _________________________________________________________________________________________________
     You have deleted chicken nuggets with calories: 120.0 from your list!
     _________________________________________________________________________________________________
```

#### 3.3.2.5. [Clearing All Food Items from the Current Diet Session: `clear`](userguide.md) <a id="meal-clear"></a>

You can clear all the food items in the current diet session list.

This command is dangerous as you will not be able to recover the data. After typing this command, you will be asked to reconfirm it by typing in `YES`. Any other input will abort the clearing.

**Format:** `clear`

**Example:** `clear`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Are you sure you want to clear all records? This action is irrevocable.
     Key in "YES" to confirm.
     _________________________________________________________________________________________________

 Diet Menu > New Diet Session >>>>> YES
     _________________________________________________________________________________________________
     Oops you have cleared all the food items.
     _________________________________________________________________________________________________
```

> :warning: Your food items cannot be recovered once cleared!

#### 3.3.2.6. [Searching for Food Items from the Current Diet Session: `search`](userguide.md) <a id="meal-search"></a>

You can search for all food items that contain the word entered, in the current diet session.

**Format:** `search [FOOD_NAME]`

**Example:** `search rice`

Expected outcome:

```text
Diet Menu > Diet Session 1 >>>>> search rice
     _________________________________________________________________________________________________
     Here are the search results: 
     Index   Food         Calories 
     1       chicken rice 332.0     
     2       fried rice   452.0     

     You have 2 record(s)
     _________________________________________________________________________________________________
```

#### 3.3.2.7. [Ending the Current Diet Session: `end`](userguide.md) <a id="meal-end"></a>

You can return to the diet menu by ending your current diet session.

**Format:** `end`

**Example:** `end`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Exiting Diet Session!
     _________________________________________________________________________________________________
```

#### 3.3.3. [Listing All Past Diet Sessions: `list`](userguide.md) \[Zeon\] <a id="diet-list"></a>

You can obtain a list of information about past diet sessions together with their numbered index and calories.

**Format:** `list`

**Example:** `list`

Expected outcome:

```text
    _________________________________________________________________________________________________
    You have 2 records
    Index   Tags        Date        Calories
    1       dinner      2020-10-29  110.0
    2       lunch       2020-10-29  120.0
    _________________________________________________________________________________________________
```

#### 3.3.4. [Editing a Past Diet Session: `edit`](userguide.md) <a id="diet-edit"></a>

You can edit a previous diet session based on a numbered index that can be found in the `list` command.

**Format:** `edit [INDEX]`

**Example:** `edit 2`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Starting Diet Session!
     _________________________________________________________________________________________________

Diet Menu > Diet Session 2 >>>>>
```

> :bulb: Editing a diet session works exactly like how it does when you create a new diet session.

#### 3.3.5. [Deleting a Past Diet Session: `delete`](userguide.md) <a id="diet-delete"></a>

You can delete a previously created diet session based on a numbered index that can be found in the `list` command.

**Format:** `delete [INDEX]`

**Example:** `delete 2`

Expected outcome:

```text
     _________________________________________________________________________________________________
     You have deleted that diet session!
     _________________________________________________________________________________________________
```

> :warning: Deleted diet sessions cannot be recovered!

#### 3.3.6. [Clearing All Past Diet Sessions: `clear`](userguide.md) <a id="diet-clear"></a>

You can clear all previously saved diet sessions.

**Format:** `clear`

**Example:** `clear`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Are you sure you want to clear all records? This action is irrevocable.
     Key in "YES" to confirm.
     _________________________________________________________________________________________________

Diet Menu >>>>> YES
     _________________________________________________________________________________________________
     You have cleared all diet sessions!
     _________________________________________________________________________________________________
```

> :warning: This command is dangerous as you will not be able to recover the data. After typing this command, you will be asked to reconfirm it by typing in `YES`. Any other input will abort the clearing.

#### 3.3.7. [Searching for Past Diet Sessions: `search`](userguide.md) <a id="diet-search"></a>

Searches for specified range of diet sessions with identifiers such as start date, end date and tags.

**Format:** `search </s [START DATE]> </e [END DATE]> </t [TAG]>`

**Example:** `search /s 2020-05-06 /e 2020-05-10 /t breakfast`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Here are the search results!
     Index   Date             Tag         Calories  
     1       2020-05-08       breakfast   112.0
     2       2020-05-09       breakfast   250.0

     You have 2 record(s)
     _________________________________________________________________________________________________
```

#### 3.3.8. [Returning to Main Menu: `end`](userguide.md) <a id="diet-end"></a>

You can use this command to exit Diet Menu and return to the Main Menu.

**Format:** `end`

**Example:** `end`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Returning to Main Menu...
     _________________________________________________________________________________________________
```

### 3.4. [Workout Menu](userguide.md) \[Zesong\] <a id="workout-menu"></a>

This section shows the command you can use when you are in workout menu.

Click [here](userguide.md#main-workout) to learn how to enter Workout Menu.

#### 3.4.1. [Viewing Help: `help`](userguide.md) <a id="workout-help"></a>

You can see a complete list of available commands under Workout Menu and how to use them.

**Format:** `help`

1. Type `help` into your console and press Enter to execute it.

   ![main-help-1](../.gitbook/assets/workout-help-step-1.png)

2. You should be able to see a list of available commands like the screenshot below: ![main-help-2](../.gitbook/assets/workout-help-step-2.png)

#### 3.4.2. [Starting a New Workout Session: `new`](userguide.md) <a id="workout-start"></a>

You can a new workout session and go into the session. You can add tags with “/t”.

> :bulb: Tags are optional and more than one tag can be attached to a session. If you want to add more than one tags, you should separate them by `,`.

After Enter, you will be directed into workout session to manage the given session. You may verify that as seen from how the cursor changes from

`Workout Menu >>>>>`

to

`Workout Menu > New Workout Session >>>>>`.

**Format:** `new </t [TAG]...>`

**Example:** `new /t legs day, tricep`

Expected outcome:

```text
     ______________________________________________________________________________
     You have started a new workout session!
     ______________________________________________________________________________

Workout Menu > New Workout Session >>>>>
```

#### 3.4.2.1. [Viewing Help in Workout Session: `help`](userguide.md) \[Jinyang\] <a id="ws-help"></a>

You can see a complete list of available commands under Workout Session and how to use them.

**Format:** `help`

**Example:** `help`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Add      FORMAT:      add [NAME_OF_MOVE] /n [NUMBER_OF_REPETITIONS] /w [WEIGHT]
              DESCRIPTION: Add a new move
     List     FORMAT:      list
              DESCRIPTION: Show all moves in this current session
     Delete   FORMAT:      delete [INDEX]
              DESCRIPTION: Delete a move according to the index in the list
     Search   FORMAT:      search [NAME_OF_MOVE]
              DESCRIPTION: Show a list of moves that match the entered keyword
     End      FORMAT:      end
              DESCRIPTION: Go back to the Workout Menu
     _________________________________________________________________________________________________
```

#### 3.4.2.2. [Adding a Move to the Current Workout Session: `add`](userguide.md) <a id="ws-add"></a>

Adds a move with number of moves per set and weights of equipment \(if the move does not require weights, input 0 for weight\).

**Format:** `add [NAME_OF_MOVE] /n [NUMBER_OF_MOVES_PER_SET] /w [WEIGHT]`

**Example:** When you want to add an exercise to the current Workout Session, you follow the steps below: 1. Ensure that you are in a Workout Session.  
 Refer to [Section 3.4.2. Starting a New Workout Session](userguide.md#workout-start) to learn how to start a Workout Session. ![add-exercise-step-1](../.gitbook/assets/AddCommandStart.png)

1. Type `add benchpress /w 45.5 /n 6` into your console and press Enter to execute it.  
    This command adds an exercise with the name benchpress, weight of 45.5 and 6 repetititons.

   ![add-exercise-step-2](../.gitbook/assets/AddCommand.png)

2. If the execution is successful, you should be able to see the confirmation message below:

   ![add-exercise-step-3](../.gitbook/assets/AddCommandMessage.png)

#### 3.4.2.3. [Listing All Moves from the Current Workout Session: `list`](userguide.md) <a id="ws-list"></a>

Lists all the added moves for the current workout session, with a numbered sequence according to sequence entered.

**Format:** `list`

**Example:** `list`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Index   Exercise             Repetitions    Weight
     1       squat                15             40.0
     _________________________________________________________________________________________________
```

#### 3.4.2.4. [Deleting a Move From the Current Workout Session: `delete`](userguide.md) <a id="ws-delete"></a>

Deletes a move according to move index in the current workout session list.

**Format:** `delete [INDEX]`

**Example:** `delete 1`

Expected outcome:

```text
     _________________________________________________________________________________________________
     You have deleted squat from your list!
     [Repetitions: 15 || Weight: 40.0]
     _________________________________________________________________________________________________
```

#### 3.4.2.5. [Searching for a Keyword in the Current Workout Session: `search`](userguide.md) <a id="ws-search"></a>

Searches the current workout session for the keyword and shows the relevant data found in a neat list.

**Format:** `search [NAME_OF_MOVE]`

**Example:** `search bench`

Expected outcome:

```text
     _________________________________________________________________________________________________
          Index   Exercise             Repetitions    Weight    
          1       bench                324            342.0
          2       benchpress           324            342.0
          3       bench press          324            342.0
     _________________________________________________________________________________________________
```

#### 3.4.2.6. [Ending the Current Workout Session: `end`](userguide.md) <a id="ws-end"></a>

Ends the current workout session and saves the relevant data.

**Format:** `end`

**Example:** `end`

Expected outcome:

```text
     _________________________________________________________________________________________________
     Congratulations! You have finished today's workout!
     _________________________________________________________________________________________________
```

#### 3.4.3. [Listing All Past Workout Sessions: `list`](userguide.md) \[Zesong\] <a id="workout-list"></a>

You can see all your past workout sessions. They will be summarised and printed in a table with their index, creation date and tags.

You can specify start date and end date to show sessions created in a selected period using `\s` for start date and `\e` for end date. If start date is not specified, it will take the earliest date a start date. If end date is not specified, it will take today as the end date.

**Format:** `list </s [START_DATE]> </e [END_DATE]>`

Example `list /e 20201026`

Expected outcome:

```text
     ______________________________________________________________________________
     You have 2 records in the given period:
     Index   Creation date   Tags    
     1       2020-10-26      [legs day, tricep]
     2       2020-10-26      [chest]
     ______________________________________________________________________________
```

#### 3.4.4. [Editing a Workout Session: `edit`](userguide.md) <a id="workout-edit"></a>

You can edit a past workout session in the record list.

**Format:** `edit [INDEX]`

You will go into the specific workout session after typing this command. You may verify by seeing the cursor changes from

`Workout Menu >>>>>`

to

`Workout Menu > Workout Session X >>>>>` .

The index can be found by listing all results or searching the target record.

**Example:** `edit 1`

Expected outcome:

```text
Workout Menu > Workout Session 1 >>>>>
```

#### 3.4.5. [Deleting a Workout Session: `delete`](userguide.md) <a id="workout-delete"></a>

You can delete a past workout session in the record list.

**Format:** `delete [INDEX]`

The index can be found by listing the results.

Example： `delete 1`

Expected outcome:

```text
    ______________________________________________________________________________
    You have deleted that record!
    ______________________________________________________________________________
```

> :warning: Your workout session record cannot be recovered once deleted!

#### 3.4.6. [Searching a List of Workout Sessions: `search`](userguide.md) <a id="workout-search"></a>

You can search for a list of workout sessions that match certain conditions. For example, you can search for sessions created on a specific day or sessions that contain certain tags. All sessions that satisify the condition will be summaried into a table and printed out.

**Format:** `search </d [DATE]> </t [TAG]...>`

* Tag condition

You can search records containing \(a list of\) tags with `/t` followed by the tags. Multiple tags should be separated by `,`. If you give multiple tags, only sessions that contains all the tags will be selected. You can search with part of the tag as well. For example searching with  
 `search /t leg`   
will match any tags that contains `leg`, e.g. `legs`.

* Date condition

You can search records created on a specific day with `/d` followed by a date. You should key in your date following one of the supported formats. [See here](userguide.md#notes) for a complete list of format supported.

Both date and tag conditions are optional. You may have zero, one or both conditions while searching. If both conditions are given, only sessions that meet both conditions will be selected.

**Example:** `search /t le`

Expected outcome:

```text
     ______________________________________________________________________________
     1  records are found:
     Index   Creation date   Tags    
     2       2020-10-26      [legs day, tricep]
     ______________________________________________________________________________
```

#### 3.4.7. [Clearing All Workout Sessions: `clear`](userguide.md) <a id="workout-clear"></a>

You can erase all workout sessions.

**Format:** `clear`

Example `clear`

Expected outcome:

```text
     ______________________________________________________________________________
     Are you sure you want to clear all records? This action is irrevocable.
     Key in "YES" to confirm.
     ______________________________________________________________________________

Workout Menu >>>>> YES
     ______________________________________________________________________________
     You have cleared all records!
     ______________________________________________________________________________

Workout Menu >>>>>
```

> :warning: This command is dangerous as you will not be able to recover the data. After typing this command, you will be asked to reconfirm it by typing in `YES`. Any other input will abort the clearing.

#### 3.4.8. [Returning to Main Menu: `end`](userguide.md) <a id="workout-end"></a>

You can return to the main menu.

**Format:** `end`

After typing in this, you will see your prompt in your terminal changes from `Workout Menu>>>` to `Main Menu>>>`.

Example `end`

Expected output

```text
     ______________________________________________________________________________
     Returning to Main menu...
     ______________________________________________________________________________

Main Menu >>>>>
```

## 4. [Command Summary](userguide.md) <a id="command-summary"></a>

**Main Menu** \[Khoa\]

| **Action** | **Format** |
| :--- | :--- |
| Help | `help` |
| Profile Menu | `profile` |
| Diet Menu | `diet` |
| Workout Menu | `workout` |
| End | `end` |

**Profile Menu** \[Khoa\]

| **Action** | **Format** |
| :--- | :--- |
| Help | `help` |
| Add | `add /n [NAME] /h [HEIGHT] /w [CURRENT_WEIGHT] /e [EXPECTED_WEIGHT] /c [DAILY_CALORIE_INTAKE]`  E.g. `add /n Schwarzenegger /h 188 /w 113 /e 100 /c 2500` |
| View | `view` |
| Edit | `edit </n [NAME]> </h [HEIGHT]> </w [CURRENT_WEIGHT]> </e [EXPECTED_WEIGHT]> </c [DAILY_CALORIE_INTAKE]>`  E.g. `edit /w 110`, `edit /h 175 /w 110`, `edit /h 175 /w 110 /e 90` |
| Delete | `delete` |
| Return to Main Menu | `end` |

**Diet Menu** \[Zeon\]

| **Action** | **Format** |
| :--- | :--- |
| Help | `help` |
| Start diet session | `new </d [DATE]> </t [TAG]>`  E.g. `new /d 2020-05-04 /t breakfast` |
| List | `list` |
| Edit | `edit [INDEX]`  E.g. `edit 1` |
| Delete | `delete [INDEX]`  E.g. `delete 1` |
| Search | `search </s [STARTING_DATE]> </e [END_DATE]> </t [TAG]>`  E.g. `search /t lunch` |
| Clear | `clear` |
| Return to Main Menu | `end` |

**Diet Session** \[Shukai\]

| **Action** | **Format** |
| :--- | :--- |
| Help | `help` |
| Add | `add [FOOD_NAME] /c [CALORIES]`  E.g. `add spinach /c 90` |
| List | `list` |
| Delete | `delete [INDEX]`  E.g. `delete 1` |
| Search | `search [FOOD_NAME]`  E.g. `search rice` |
| Clear | `clear` |
| Return to Diet Menu | `end` |

**Workout Menu** \[Zesong\]

| **Action** | **Format** |
| :--- | :--- |
| Help | `help` |
| Start workout session | `new </t [TAG]...>`  E.g. `new /t leg, chest` |
| List | `list </s [START_DATE]> </e [END_DATE]>`  E.g. `list /s 20201001 /e 2020/10/25` |
| Edit | `edit <INDEX>`  E.g. `edit 1` |
| Delete | `delete [INDEX]`  E.g. `delete 1` |
| Search | `search </t [TAG]...> </d [DATE]>`  E.g. `search /t leg day, chest /d 2020-10-18` |
| Clear | `clear` |
| Return to Main Menu | `end` |

**Workout Session** \[Jinyang\]

| **Action** | **Format** |
| :--- | :--- |
| Help | `help` |
| Add | `add [NAME_OF_MOVE] /n [NUMBER_OF_REPETITIONS] /w [WEIGHT]`  E.g. `add squat /n 15 /w 40` |
| List | `list` |
| Delete | `delete [INDEX]`  E.g. `delete 1` |
| Search | `search [NAME_OF_MOVE]`  E.g. `search bench` |
| Return to Workout Menu | `end` |

## 5. [Notes](userguide.md) \[Zesong\] <a id="notes"></a>

\[1\] Here shows all 12 valid formats.

```text
`yyyyMMdd HH:mm`
`yyyy-MM-dd HH:mm`
`yyyy MM dd HH:mm`

`yyyyMMdd HHmm`
`yyyy-MM-dd HHmm`
`yyyy MM dd HHmm`

`yyyyMMdd`
`yyyy-MM-dd`
`yyyy MM dd`

`dd MM yyyy`
`ddMMyyyy`
`dd-MM-yyyy`
```

## 6. [FAQ](userguide.md) \[Khoa\] <a id="faq"></a>

Below are the answers to some frequently asked questions about The Schwarzenegger:

**Q**: Can I use The Schwarzenegger on another operating systems apart from Windows?  
 **A**: Yes. The Schwarzenegger is compatible with Windows, MacOS and Linux.

**Q**: How do I transfer my data to another Computer?  
 **A**: Install The Schwarzenegger in the other computer and overwrite the `saves` folder it creates with the `saves` folder of your previous The Schwarzenegger.

**Q**: Can I exit The Schwarzenegger without typing `end` command?  
 **A**: Yes. Your data is saved automatically to `saves` folder whenever it changes. Therefore, you can exit The Schwarzenegger worry-free.

