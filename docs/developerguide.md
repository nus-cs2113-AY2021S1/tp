# The Schwarzenegger - Developer Guide

By: `CS2113T-F11-1` Since: `2020`

![Supported Java versions](https://img.shields.io/badge/Java-11-blue.svg) ![Supported OS](https://img.shields.io/badge/Supported%20OS-Windows|MacOS|Linux-yellow.svg) ![Java CI](https://github.com/AY2021S1-CS2113T-F11-1/tp/workflows/Java%20CI/badge.svg)

## Table of Contents

1. [**Introduction**](developerguide.md#intro)  


   1.1. [Background](developerguide.md#background)  


   1.2. [Purpose](developerguide.md#purpose)  


   1.3. [Scope](developerguide.md#scope)  

2. [**Setting Up**](developerguide.md#setting-up)  


   2.1. [Prerequisites](developerguide.md#prerequisites)  


   2.2. [Setting up the Project in Your Computer](developerguide.md#setting-up-the-project-in-your-computer)  

3. [**Design**](developerguide.md#design)  


   3.1. [Architecture](developerguide.md#architecture)  


   3.2. [Ui Component](developerguide.md#ui-component)  


   3.3. [Logic Component](developerguide.md#logic-component)  


   3.4. [Model Component](developerguide.md#model-component)  


   3.5. [Storage Component](developerguide.md#workoutSessionStorage-component)  


       3.5.1. [Storage for Profile](developerguide.md#workoutSessionStorage-for-profile)  


       3.5.2. [Storage for Diet](developerguide.md#workoutSessionStorage-for-diet)  


       3.5.3. [Storage for Workout](developerguide.md#workoutSessionStorage-for-workout)  

4. [**Implementation**](developerguide.md#implementation)  


   4.1. [Main Menu-related Features](developerguide.md#main-menu-related-features)  


   4.2. [Profile-related Features](developerguide.md#profile-related-features)  


       4.2.1. [Adding a Profile](developerguide.md#adding-a-profile)  


       4.2.2. [Viewing a Profile](developerguide.md#viewing-a-profile)  


       4.2.3. [Editing a Profile](developerguide.md#editing-a-profile)  


       4.2.4. [Deleting a Profile](developerguide.md#deleting-a-profile)  


   4.3. [Diet-related Features](developerguide.md#diet-related-features)  


       4.3.1. [List Out All Commands](developerguide.md#list-out-all-commands)  


       4.3.2. [Start Recording Diet Data](developerguide.md#start-recording-diet-data)  


           4.3.2.1. [Showing Help Message](developerguide.md#showing-help-message)  


           4.3.2.2. [Adding Food Items for the Current Diet](developerguide.md#adding-food-items-for-the-current-diet)  


           4.3.2.3. [Listing Data for the Current Diet](developerguide.md#listing-data-for-the-current-diet)  


           4.3.2.4. [Deleting Data from the Current Diet](developerguide.md#deleting-data-from-the-current-diet)  


           4.3.2.5. [Clearing Data from the Current Diet](developerguide.md#clearing-data-from-the-current-diet)  


           4.3.2.6. [Stopping the Recording of Diet Data](developerguide.md#stopping-the-recording-of-diet-data)  


       4.3.3. [List All Past Diet Sessions](developerguide.md#list-all-past-diet-sessions)  


       4.3.4. [Edit a Past Diet Session](developerguide.md#edit-a-past-diet-session)  


       4.3.5. [Delete a Past Diet Session](developerguide.md#delete-a-past-diet-session)  


       4.3.6. [Clear all Past Diet Sessions](developerguide.md#clear-all-past-diet-sessions)  


       4.3.7. [Search for Past Diet Sessions](developerguide.md#search-for-past-diet-sessions)  


       4.3.8. [Exit the Diet Manager](developerguide.md#exit-the-diet-manager)  


   4.4. [Workout-related Features](developerguide.md#workout-related-features)  


       4.4.1. [Creating a New Workout Session](developerguide.md#creating-a-new-workout-session)  


           4.4.1.1. [Adding an Exercise](developerguide.md#adding-an-exercise)  


           4.4.1.2. [Deleting an Exercise](developerguide.md#deleting-an-exercise)  


           4.4.1.3. [Listing All Exercises in This Session](developerguide.md#listing-all-exercises-in-this-session)  


           4.4.1.4. [Searching for Related Exercises](developerguide.md#searching-for-related-exercises)  


       4.4.2. [Listing Past Workout Sessions](developerguide.md#listing-past-workout-sessions)  


       4.4.3. [Editing Workout Session](developerguide.md#editing-workout-session)  


       4.4.4. [Deleting a Workout Session](developerguide.md#deleting-a-workout-session)  


       4.4.5. [Searching Based on Conditions](developerguide.md#searching-based-on-conditions)  


   4.5. [Logging](developerguide.md#logging)  

5. [**Testing**](developerguide.md#testing)  


   5.1. [Running Tests](developerguide.md#running-tests)  


   5.2. [Types of Tests](developerguide.md#types-of-tests)  

6. [**Dev Ops**](developerguide.md#dev-ops)  


   6.1. [Build Automation](developerguide.md#build-automation)  


   6.2. [Continuous Integration](developerguide.md#continuous-integration)  


   6.3. [Coverage Report](developerguide.md#coverage-report)  


   6.4. [Making a Release](developerguide.md#making-a-release)  


   6.5. [Managing Dependencies](developerguide.md#managing-dependencies)  

7. [**Appendices**](developerguide.md#appendices)
   * [Appendix A: Product Scope](developerguide.md#appendix-a-product-scope)
   * [Appendix B: User Stories](developerguide.md#appendix-b-user-stories)
   * [Appendix C: Non-Functional Requirements](developerguide.md#appendix-c-non-functional-requirements)
   * [Appendix D: Glossary](developerguide.md#appendix-d-glossary)
   * [Appendix E: Supported Formats of Date Input](developerguide.md#appendix-e-supported-formats-of-date-input)

## 1. [Introduction](developerguide.md) <a id="intro"></a>

### 1.1.  [Background](developerguide.md) <a id="background"></a>

The Schwarzenegger is a desktop command line interface-based app for managing all your needs regarding fitness. With the built-in personal assistant, you are able to track your daily workout and diet sessions based on your profile. If you can type fast, The Schwarzenegger can help you maximise your efficiency for maintaining fitness.

### 1.2. [Purpose](developerguide.md) <a id="purpose"></a>

This document contains the specified architecture and software design specifications for the application, The Schwarzenegger.

### 1.3. [Scope](developerguide.md) <a id="scope"></a>

This document describes the software architecture and software design requirements for The Schwarzenegger. This guide is mainly for developers, designers and software engineers that are or going to work on The Schwarzenegger.

[Return to Top](developerguide.md#intro)

## 2. [Setting Up](developerguide.md) <a id="setting-up"></a>

### 2.1. [Prerequisites](developerguide.md) <a id="prerequisites"></a>

1. JDK `11`.
2. IntelliJ IDEA IDE.

### 2.2. [Setting up the Project in Your Computer](developerguide.md) <a id="setting-up-the-project-in-your-computer"></a>

1. Fork [this repository](https://github.com/AY2021S1-CS2113T-F11-1/tp), and clone the fork to your computer.
2. Open IntelliJ \(if you are not in the welcome screen, click `File` &gt; `Close Project` to close the existing project dialog first\).
3. Set up the correct JDK version for Gradle  
   1. Click `Configure` &gt; `Structure for New Projects` and then `Project Settings` &gt; `Project` &gt; `Project SDK`.
   2. If `JDK 11` is listed in the drop down, select it. Otherwise, click `New…` and select the directory where you installed `JDK 11`.
   3. Click `OK`.
4. Click `Import Project`.
5. Locate the `build.gradle` file and select it. Click `OK`.
6. Click `Open as Project`.
7. Click `OK` to accept the default settings if prompted. 

[Return to Top](developerguide.md#intro)

## 3. [Design](developerguide.md) <a id="design"></a>

This section provides a high level overview of our application, The Schwarzenegger.

### 3.1. [Architecture](developerguide.md) <a id="architecture"></a>

![Architecture](../.gitbook/assets/Architecture.png)

The image above explains the design of the application, The Schwarzenegger.

The main driver of the application is `Main: Duke`. It is responsible for mainly two phases:

* At application launch
  * This class will initialise the components in the correct sequence and is in charge of connecting them with each other.
* At shut down
  * This class will invoke cleanup method for the components when necessary.

In addition to that, the architecture of The Schwarzenegger is broken down into several packages, mainly the following:

* `Ui`: This class mainly handles the interactions with user of the application.
* `Parser`: This class mainly handles the parsing and handling of user commands.
* `Command`: This class handles the type of command.
* `Profile`: This class manages the data of the user.
* `Diet`: This class manages the diet recording sessions.
* `Workout`: This class manages the data workout recording sessions.
* `Storage`: This class reads data from and writes data back into a text file for future uses.

[Return to Top](developerguide.md#intro)

### 3.2. [Ui Component](developerguide.md) <a id="ui-component"></a>

![Ui Component](../.gitbook/assets/Ui.png)

The `Ui` package is a combination class where the interactions with the user are formatted in a consistent way.

The `Ui` component,

* Takes in user commands
* Formats messages and prints out responses

[Return to Top](developerguide.md#intro)

### 3.3. [Logic Component](developerguide.md) <a id="logic-component"></a>

![Logic Component](../.gitbook/assets/Logic.png)

1. `The Schwarzenegger` uses `Parser` classes to parse the user command.
2. This splits the user input into interpretable portions by other functions.
3. All commands inherits from base class Command with an `execute()` method. They are stored in a hashmap `CommandLib` and retrieved using user's input as key.
4. Command interacts with parsers, models and storage to carry out the user's command.
5. The result of the command execution is encapsulated as a CommandResult object which is passed back to CommonUi to display the message. 

[Return to Top](developerguide.md#intro)

### 3.4. [Model Component](developerguide.md) <a id="model-component"></a>

![Model Component](../.gitbook/assets/model.png)

The Model component contains `Profile`, `DietManager`, `PastRecord` and `WorkoutSession` classes.

* Profile: Stores the user profile data.
* Food: Stores food data that user consumes in a meal.
* PastRecord: Stores meta information of each WorkoutSession files.
* Exercises: Stores the exercise data done in each workout session.

[Return to Top](developerguide.md#intro)

### 3.5. [Storage Component](developerguide.md) <a id="workoutSessionStorage-component"></a>

Storage in the application refers to storing files of user profile and workout, diet sessions into respective local subdirectories sorted based on time in a local directory called `/saves` which is in the same directory as the project root.

#### 3.5.1. [Storage for Profile](developerguide.md) <a id="workoutSessionStorage-for-profile"></a>

Storage for profile saves user profile created as `profile.json` in the `/saves/profile` directory. Profile data file is created as follows:

* `profile.json` is updated in the local hard disk after the user adds/ edits a profile by calling `ProfileAdd.execute()`/ `ProfileEdit.execute()`.
* `profile.json` content will be cleared after the user deletes a profile by calling `ProfileDelete.execute()`.

**Implementation** Profile workoutSessionStorage handles reading of file data by calling `loadData()` and overwriting of file data by calling `saveData()`.

[Return to Top](developerguide.md#intro)

#### 3.5.2. [Storage for Diet](developerguide.md) <a id="workoutSessionStorage-for-diet"></a>

Storage for diet saves diet sessions created as individual files sorted based on the time created in the `/saves/diet` directory. Each diet session file is created as follows:

* Each file is created as a json file and named as `[DATE] [TAG].json`.
* A corresponding file is updated in the local file after the user enters a command into a diet session by calling DietSessionEdit.execute\(\), or DietSessionCreate.execute\(\).
* A corresponding file is deleted in the local file when the user deletes a diet session by calling DietSessionDelete.execute\(\) or clears all diet sessions by calling DietSessionClear.execute\(\).

**Implementation** Storage handles reading of file data by calling readDietSession\(\) and overwriting of file data by calling writeToStorageDietSession\(\).

[Return to Top](developerguide.md#intro)

#### 3.5.3. [Storage for Workout](developerguide.md) <a id="workoutSessionStorage-for-workout"></a>

Storage for workout saves workout sessions created as individual files named based on the time created in `/saves/workout` directory. The metainformation of the files such as createion date and last edit date is saved in `/saves/workout/history.json`.

Only history.json file is load when initilizing the application. The rest of Session files are load on request, e.g. `edit`. When a new workout session is created, a new file will be stored and its meta information will be appended to `history.json`. When a workout session is deleted, the file will be removed and its record will be removed from `history.json`.

[Return to Top](developerguide.md#intro)

## 4. [Implementation](developerguide.md) <a id="implementation"></a>

This section describes some details on how the features are being implemented. All profile/ diet/ workout-related features.

All profile/ diet/ workout-related features can be broken down into 4 distinct functionality, addition, viewing/ listing, deletion and editing. For diet and workout-related features, there is an additional functionality of searching.

### 4.1. [Main Menu-related Features](developerguide.md) <a id="main-menu-related-features"></a>

This feature allows user to access the different menu for workout, diet and profile. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command. The action will be aborted, and the program will advise the user to type "help" for command syntax reference.

If the command is successful,the user will be put into the respective menu and a starting message on the entered menu will be displayed to the user.

**Implementation**

When the user attempts to access different menu for workout, diet and profile menu, the Duke, CommonUi, CommonParser and CommandLib classes will be accessed, and the following sequence of actions is called to prompt execution result to user:

1. User executes a command
   1. `Duke` calls `CommonUi.getCommand()` to receive user input.
   2. `Duke` calls `CommonParser.parseCommand()` to parse user input into a string array.
2. `Duke` calls `CommandLib.getCommand` with the string arry containing the inputs.
3. Depending on the input,`Duke` creates `ProfileSession` or `DietManager` or `WorkoutManager` object.
4. After entering the `ProfileSession` or `DietManager` or `WorkoutManager` objects, the menus will have their own separate tasks.

The sequence diagram below summarizes how Main Menu works:

![Load Data Sequence Diagram](../.gitbook/assets/MainMenu.png)

[Return to Top](developerguide.md#intro)

### 4.2. [Profile-related Features](developerguide.md) <a id="profile-related-features"></a>

#### 4.2.1. [Adding a Profile](developerguide.md) <a id="adding-a-profile"></a>

This feature allows user to add a new profile. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command format. The action will be aborted, and the program will advise the user to type "help" for command syntax reference.

If the creation is successful, a confirmation message on the newly created profile will be displayed to the user.

**Implementation**

When the user attempts to add a new profile, the ProfileSession, CommonUi, ProfileParser, Command, CommandLib, ProfileStorage, Profile and CommandResult classes will be accessed, and the following sequence of actions is called to prompt execution result to user:

1. User executes `add /n Schwarzenegger /h 188 /w 113 /e 100 /c 2500`
   1. `ProfileSession` calls `CommonUi.getCommand()` to receive user input.
   2. `ProfileSession` calls `ProfileParser.parseCommand()` to parse user input into a string array.
2. Creating `ProfileAdd` object.
   1. Based on the parsed input, `ProfileSession` calls `CommandLib` to return the correct Command Object `ProfileAdd`.
3. Executing command.
   1. `ProfileSession` calls `ProfileAdd.execute()` with the rest of parsed input.
   2. `ProfileAdd` calls `ProfileStorage.loadData()` to load existing profile in the system. If there is an existing profile, `ProfileAdd` returns a failure result to `ProfileSession`. Otherwise, the process continues with step `3`.
   3. `ProfileAdd` calls `ProfileParser.extractCommandTagAndInfo()` to parse user input into specific tags and information. 
   4. Based on the parsed information from `ProfileParser.extractCommandTagAndInfo()`, `ProfileAdd` creates a new `Profile`.
   5. `ProfileAdd` calls `ProfileStorage.saveData()` to save the `Profile` object.
   6. `ProfileAdd` returns a successful result to `ProfileSession`.
4. Prompting result to user.
   1. `ProfileSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message.
   2. `ProfileSession` calls `CommonUi.showToUser()` to show result to the user.

All descriptions, warnings and responses will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how creating a new profile works:

![Load Data Sequence Diagram](../.gitbook/assets/AddProfile.png)

Below are the sub-diagrams: 

![Figure 4.2.1](../.gitbook/assets/ParseInput%20%281%29.png)  
 **Figure 4.2.1.** _Sub-diagram for Parsing Input in ProfileSession_

  
 **Figure 4.2.2.** _Sub-diagram for Showing Message to User in ProfileSession_

**Design considerations**

Parsing of the user’s input command:

* **Alternative 1 \(current choice\):** User’s command is split into size 2 array first containing command type and command arguments. Then arguments are split into command tag and information pairs.
  * Pros: Command tags do not have to follow a fixed order. 
  * Cons: It takes multiple steps in parsing the command.
* **Alternative 2:** User’s command is divided by space.
  * Pros: The parsing can be easily done by calling Java built-in function `split()`. Supports multiple tags or no tags.
  * Cons: Values for each variable cannot contain spaces which makes the application restrictive, especially for user's name.

[Return to Top](developerguide.md#intro)

#### 4.2.2. [Viewing a Profile](developerguide.md) <a id="viewing-a-profile"></a>

This feature allows user to view added profile with calculated BMI based on height and weight. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. redundant parameters. The action will be aborted, and the program will advise the user to type "help" for command syntax reference.

If the data loading is successful, a message on the added profile will be displayed to the user.

**Implementation**

When the user attempts to view an added profile, the ProfileSession, CommonUi, ProfileParser, Command, CommandLib, ProfileStorage, Profile, DietManager and CommandResult classes will be accessed. The following sequence of steps will then occur:

1. User executes `view`
   1. `ProfileSession` calls `CommonUi.getUserCommand()` to receive user input.
   2. ProfileSession`calls`ProfileParser.parseCommand\(\)\` to parse user input into a string array.
2. Creating `ProfileView` object.
   1. Based on the parsed input, `ProfileSession` calls `CommandLib` to return the correct Command Object `ProfileView`.
3. Executing command.
   1. `ProfileSession` calls `ProfileView.execute()` with the rest of parsed input.
   2. `ProfileView` calls `ProfileStorage.loadData()` to load existing profile in the system. If there is no existing profile, `ProfileView` returns a failure result to `ProfileSession`. Otherwise, the process continues with step `3`.
   3. `ProfileView` calls `DietManager.getTodayTotalCalories()` to get user's calories intake today.
   4. Based on user's calories intake today and string representation of `Profile`, `ProfileView` returns a result to `ProfileSession`.    
4. Prompting result to user.
   1. `ProfileSession` calls `CommandResult.getCommandResult()` to get the `CommandResult` object.
   2. `ProfileSession` calls `CommonUi.showToUser()` to show result to the user.

All descriptions, warnings and responses will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how viewing an added profile works:

![Load Data Sequence Diagram](../.gitbook/assets/ViewProfile.png)

You can refer to [Figure 4.2.1. Sub-diagram for Parsing Input in ProfileSession](developerguide.md#figure-4-2-1) and [Figure 4.2.2. Sub-diagram for Showing Message to User in ProfileSession](developerguide.md#figure-4-2-2) for the corresponding sub-diagrams.

**Design considerations**

Aspects: Loading of stored data

* **Alternative 1 \(current choice\):** call public methods of Storage class to load the profile from hard disk every time the user wants to view profile.
  * Pros: Profile data is up-to-date if the user prefers to edit it in text file rather than using commands in The Schwarzenegger.
  * Cons: Execution time is slow down due to multiple times of loading the data.
* **Alternative 2:** call public methods of Storage class to load the profile from hard disk only when user enters Profile Menu.
  * Pros: Execution time is fast. 
  * Cons: Profile data is not updated in real time if user edits it in text editor while running The Schwarzenegger.

[Return to Top](developerguide.md#intro)

#### 4.2.3. [Editing a Profile](developerguide.md) <a id="editing-a-profile"></a>

This feature allows user to anytime go back to edit a profile created in the past such as editing physique data and expected daily calories intake. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command format. The action will be aborted, and the program will advise the user to type "help" for command syntax reference.

If the editing is successful, a confirmation message on the edited profile will be displayed to the user.

**Implementation**

When the user attempts to edit a profile, the ProfileSession, CommonUi, ProfileParser, Command, CommandLib, ProfileStorage, Profile and CommandResult classes will be accessed, and the following sequence of actions is called to prompt execution result to user:

1. User executes `edit /w 60`
   1. `ProfileSession` calls `CommonUi.getCommand()` to receive user input.
   2. `ProfileSession` calls `ProfileParser.parseCommand()` to parse user input into a string array.
2. Creating `ProfileEdit` object.
   1. Based on the parsed input, `ProfileSession` calls `CommandLib` to return the correct Command Object `ProfileEdit`.
3. Executing command.
   1. `ProfileSession` calls `ProfileEdit.execute()` with the rest of parsed input.
   2. `ProfileEdit` calls `ProfileStorage.loadData()` to load existing profile in the system. If there is no existing profile, `ProfileAdd` returns a failure result to `ProfileSession`. Otherwise, the process continues with step `3`.
   3. `ProfileEdit` calls `ProfileParser.extractCommandTagAndInfo()` to parse user input into specific tags and information.
   4. Based on the parsed information from `ProfileParser.extractCommandTagAndInfo()`, `ProfileEdit` creates a new `Profile`.
   5. `ProfileEdit` calls `Profile.equals()` to compare the newly created and existing profile. If there are no changes, `ProfileEdit` returns a failure result to `ProfileSession`. Otherwise, the process continues with step `6`.
   6. `ProfileEdit` calls `ProfileStorage.saveData()` to save the newly created `Profile` object.
   7. `ProfileAdd` returns a successful result to `ProfileSession`.
4. Prompting result to user.
   1. `ProfileSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message.
   2. `ProfileSession` calls `CommonUi.showToUser()` to show result to the user.

All descriptions, warnings and responses will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how creating a new profile works:

![Load Data Sequence Diagram](../.gitbook/assets/EditProfile.png)

You can refer to [Figure 4.2.1. Sub-diagram for Parsing Input in ProfileSession](developerguide.md#figure-4-2-1) and [Figure 4.2.2. Sub-diagram for Showing Message to User in ProfileSession](developerguide.md#figure-4-2-2) for the corresponding sub-diagrams.

**Design considerations**

Parsing of the user’s input command:

* **Alternative 1 \(current choice\):** User’s command is split into size 2 array first containing command type and command arguments. Then arguments are split into command tag and information pairs.
  * Pros: Command tags do not have to follow a fixed order.
  * Cons: It takes multiple steps in parsing the command.
* **Alternative 2:** User’s command is divided by space.
  * Pros: The parsing can be easily done by calling Java built-in function `split()`. Supports multiple tags or no tags.
  * Cons: Values for each variable cannot contain spaces which makes the application restrictive.

[Return to Top](developerguide.md#intro)

#### 4.2.4. [Deleting a Profile](developerguide.md) <a id="deleting-a-profile"></a>

This feature allows user to delete a profile created in the past. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. redundant parameters. The action will be aborted, and the program will advise the user to type "help" for command syntax reference.

If the deletion is successful, a confirmation message on the profile deletion will be displayed to the user.

**Implementation**

When the user attempts to delete an added profile, the ProfileSession, CommonUi, ProfileParser, Command, CommandLib, ProfileStorage, Profile and CommandResult classes will be accessed. The following sequence of steps will then occur:

1. User executes `delete`
   1. `ProfileSession` calls `CommonUi.getUserCommand()` to receive user input.
   2. ProfileSession`calls`ProfileParser.parseCommand\(\)\` to parse user input into a string array.
2. Creating `ProfileDelete` object.
   1. Based on the parsed input, `ProfileSession` calls `CommandLib` to return the correct Command Object `ProfileDelete`.
3. Executing command.
   1. `ProfileSession` calls `ProfileDelete.execute()` with the rest of parsed input.
   2. `ProfileDelete` calls `ProfileStorage.loadData()` to load existing profile in the system. If there is no existing profile, `ProfileDelete` returns a failure result to `ProfileSession`. Otherwise, the process continues with step `3`.
   3. `ProfileDelete` calls `CommonUi.CheckConfirmation()` to get user's confirmation on the deletion since this action is irrevocable. If user  fails to confirm, `ProfileDelete` returns an abort result to `ProfileSession`. Otherwise, the process continues with step `4`.
   4. `ProfileDelete` calls `ProfileStorage.saveData()` to save a `null` object which represents a deleted profile.
   5. `ProfileDelete` returns a result to `ProfileSession`.   
4. Prompting result to user.
   1. `ProfileSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message.
   2. `ProfileSession` calls `CommonUi.showToUser()` to show result to the user.

All descriptions, warnings and responses will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how deleting an added profile works:

![Load Data Sequence Diagram](../.gitbook/assets/DeleteProfile.png)

You can refer to [Figure 4.2.1. Sub-diagram for Parsing Input in ProfileSession](developerguide.md#figure-4-2-1) and [Figure 4.2.2. Sub-diagram for Showing Message to User in ProfileSession](developerguide.md#figure-4-2-2) for the corresponding sub-diagrams.

**Design considerations**

Aspects: Loading of stored data

* **Alternative 1 \(current choice\):** call public methods of Storage class to load the profile from hard disk every time the user wants to delete profile.
  * Pros: Profile data is up-to-date if the user prefers to edit it in text file rather than using commands in The Schwarzenegger.
  * Cons: Execution time is slow down due to loading the data.
* **Alternative 2:** call public methods of Storage class to load the profile from hard disk when user enter Profile Menu.
  * Pros: Execution time is fast. 
  * Cons: Profile data is not updated in real time if user edits it in text file while running The Schwarzenegger.

[Return to Top](developerguide.md#intro)

### 4.3. [Diet-related Features](developerguide.md) <a id="diet-related-features"></a>

#### 4.3.1. [Listing out all commands:](developerguide.md) `help` <a id="list-out-all-commands"></a>

This command lists out all help commands in a typed list that indicates to the user all the commands available and how to use them.

**Implementation**  
When the user types `help` in a Diet Manager instance, the following sequence occurs.

1. User executes `help`
   1. `DietManager` calls `dietManagerUi.getCommand()` to receive user input.
   2. `DietManager` calls `DietManagerParser.parseCommand()` to parse user input into a string array.
2. Creating `DietSessionHelp` object.
   1. Based on the parsed input, `DietManager` calls `CommandLib` to return the correct Command Object `DietSessionHelp`.
3. Executing command.
   1. `DietManager` calls `DietSessionHelp.execute()` with the rest of parsed input.
   2. `DietSessionHelp` appends onto a string builder a list of typed help commands.
   3. `DietSessionHelp` returns a `CommandResult` object with the help message.
4. Prompting result to user.
   1. `DietManager` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message.
   2. `CommandResult` calls `Ui.showToUser()` to show result to the user.

[Return to Top](developerguide.md#intro)

#### 4.3.2. [Start recording diet data:](developerguide.md) `new` <a id="start-recording-diet-data"></a>

The feature allows users to start recording diet data.

**Implementation**  
When the user types `new </d [DATE]> </t [TAG]>` the following sequence occurs.

1. User executes `new /d 2020-05-04 /t breakfast`
   1. `DietManager` calls `dietManagerUi.getCommand()` to receive user input.
   2. `DietManager` calls `DietManagerParser.parseCommand()` to parse user input into a string array.
2. Creating `DietSessionHelp` object.
   1. Based on the parsed input, `DietManager` calls `CommandLib` to return the correct Command Object `DietSessionCreate`.
3. Executing command.
   1. `DietManager` calls `DietSessionCreate.execute()` with the rest of parsed input.
   2. `DietSessionCreate` calls the `start()` method within an instantiated DietSession created with the parsed input.
   3. `DietSession` then proceeds to completion until the user types "end", saving after every command with `DietStorage`.
   4. `DietSessionHelp` returns a `CommandResult` object with the help message of the diet manager.
4. Prompting result to user.
   1. `DietManager` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message.
   2. `CommandResult` calls `Ui.showToUser()` to show result to the user.

The sequence diagram below summarizes how creating new diet session works:

![Load Data Sequence Diagram](../.gitbook/assets/CreateDietSession.png)

Figure 4.3.2.1. CreateDietSession-diagram for Parsing Input in DietManager

[Return to Top](developerguide.md#intro)

#### 4.3.2.1. [Showing help message:](developerguide.md) `help` <a id="showing-help-message"></a>

This command lists out all help commands in a typed list that indicates to the user all the commands available and how to use them.

**Implementation**  
When the user types `help` the following sequence occurs. 1. The user keys in `help`. 1. `DietSession` calls `dietSessionUi.getCommand()` to receive user input. 1. `DietSession` calls `DietSessionParser.parseCommand()` to parse user input into a string array. 1. Creating `FoodItemHelp` object. 1. Based on the parsed input, `DietSession` calls `CommandLib` to return the correct Command Object `FoodItemHelp`. 1. Executing command. 1. `DietSession` calls `FoodItemHelp.execute()`. 1. `FoodItemHelp` appends onto a string builder a list of typed help commands. 1. `FoodItemHelp` returns a `CommandResult` object with the help message. 1. Prompting result to user. 1. `DietSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

[Return to Top](developerguide.md#intro)

#### 4.3.2.2. [Adding food items for the current diet:](developerguide.md) `add` <a id="adding-food-items-for-the-current-diet"></a>

The feature allows users to add food items into the current diet session.

**Implementation**  
When the user types `add [FOOD_NAME] /c [CALORIES]` the following sequence occurs. 1. The user keys in `add bologna /c 123`. 1. `DietSession` calls `dietSessionUi.getCommand()` to receive user input. 1. `DietSession` calls `DietSessionParser.parseCommand()` to parse user input into a string array. 1. Creating `FoodItemAdd` object. 1. Based on the parsed input, `DietSession` calls `CommandLib` to return the correct Command Object `FoodItemAdd`. 1. Executing command. 1. `DietSession` calls `FoodItemAdd.execute()`. 1. A `Food` object is instantiated with the rest of the parameters, `bologna` and `123`. 1. The instantiated `Food` object is added to an ArrayList of Food objects in `DietSession` 1. `FoodItemHelp` returns a `CommandResult` object with the add food item message. 1. Prompting result to user. 1. `DietSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

The sequence diagram below summarizes how adding a new food to the diet session works:

![Load Data Sequence Diagram](../.gitbook/assets/FoodItemAdd.png)

Below is the sub-diagram: 

![Load Data Sequence Diagram](../.gitbook/assets/ParseInput.png)

Figure 4.3.2.2.1. Sub-diagram for Parsing Input in DietSession

[Return to Top](developerguide.md#intro)

#### 4.3.2.3. [Listing data for the current diet:](developerguide.md) `list` <a id="listing-data-for-the-current-diet"></a>

This command allows users to view all food items in the current diet session.

**Implementation**  
When the user types `list` the following sequence occurs. 1. The user keys in `list`. 1. `DietSession` calls `dietSessionUi.getCommand()` to receive user input. 1. `DietSession` calls `DietSessionParser.parseCommand()` to parse user input into a string array. 1. Creating `FoodItemList` object. 1. Based on the parsed input, `DietSession` calls `CommandLib` to return the correct Command Object `FoodItemList`. 1. Executing command. 1. `DietSession` calls `FoodItemList.execute()`. 1. The ArrayList of Food objects is iterated through and stored in a String. 1. `FoodItemList` returns a `CommandResult` object with the list of food items. 1. Prompting result to user. 1. `DietSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

![Load Data Sequence Diagram](../.gitbook/assets/FoodItemList.png)

**Design considerations**

Aspects: Displaying of listed data

* **Alternative 1 \(current choice\):** Print out a neatly formatted list of food items.
  * Pros: The information is easy to read due to neat formatting.
  * Cons: Execution time is slower as it requires more calculations.
* **Alternative 2:** Print out toString\(\) for each Food item.
  * Pros: Execution time is fast. 
  * Cons: The information is harder to filter through.    

[Return to Top](developerguide.md#intro)

#### 4.3.2.4. [Deleting data from the current diet session:](developerguide.md) `delete` <a id="deleting-data-from-the-current-diet"></a>

The feature allows users to remove food items into the current diet session.

**Implementation**  
When the user types `delete [INDEX_OF_FOOD]` the following sequence occurs. 1. The user keys in `delete 1`. 1. `DietSession` calls `dietSessionUi.getCommand()` to receive user input. 1. `DietSession` calls `DietSessionParser.parseCommand()` to parse user input into a string array. 1. Creating `FoodItemDelete` object. 1. Based on the parsed input, `DietSession` calls `CommandLib` to return the correct Command Object `FoodItemDelete`. 1. Executing command. 1. `DietSession` calls `FoodItemDelete.execute()`. 1. The index-1 of the ArrayList for the food is removed. 1. `FoodItemDelete` returns a `CommandResult` object with the delete success message. 1. Prompting result to user. 1. `DietSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

![Load Data Sequence Diagram](../.gitbook/assets/FoodItemDelete.png)

[Return to Top](developerguide.md#intro)

#### 4.3.2.5. [Clearing all data from the current diet session](developerguide.md) `clear` <a id="clearing-data-from-the-current-diet"></a>

The feature allows users to remove food items into the current diet session.

**Implementation**  
When the user types `clear` the following sequence occurs. 1. The user keys in `clear`. 1. `DietSession` calls `dietSessionUi.getCommand()` to receive user input. 1. `DietSession` calls `DietSessionParser.parseCommand()` to parse user input into a string array. 1. Creating `FoodItemClear` object. 1. Based on the parsed input, `DietSession` calls `CommandLib` to return the correct Command Object `FoodItemClear`. 1. Executing command. 1. `DietSession` calls `FoodItemClear.execute()`. 1. A new ArrayList of Food is assigned to the original, leaving it with no data inside. 1. `FoodItemClear` returns a `CommandResult` object with the clear success message. 1. Prompting result to user. 1. `DietSession` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

![Load Data Sequence Diagram](../.gitbook/assets/FoodItemClear.png)

**Design considerations**

Aspects: Ram usage

* **Alternative 1 \(current choice\):** Assigning a new ArrayList to the current variable.
  * Pros: Fast.
  * Cons: Garbage collection has to pick up the unassigned ArrayList.
* **Alternative 2:** delete every item in the ArrayList one by one.
  * Pros: Less memory needed as there is nothing new to allocate. 
  * Cons: A lot slower as it has to iterate through every item.

[Return to Top](developerguide.md#intro)

#### 4.3.2.6. [Stopping the recording of diet session data:](developerguide.md) `end` <a id="stopping-the-recording-of-diet-data"></a>

The feature allows users to end the current diet session and return back to the diet manager.

**Implementation**  
When the user types `end` the following sequence occurs. 1. The user keys in `end`.

1. A `DietSessionUi` component will call `dietSessionUI.getInput()`. 
2. Input will be parsed in `processCommand()`.   
3. Exiting of inputLoop\(\)

    The inputLoop\(\) exits when userInput.equals\("end"\).

[Return to Top](developerguide.md#intro)

#### 4.3.3. [List all past diet sessions:](developerguide.md) `list` <a id="list-all-past-diet-sessions"></a>

The feature allows users to view all past created diet sessions.

**Implementation**  
When the user types `list` in a diet manager instance the following sequence occurs. 1. The user keys in `list`. 1. `DietManager` calls `dietManagerUi.getCommand()` to receive user input. 1. `DietManager` calls `DietManagerParser.parseCommand()` to parse user input into a string array. 1. Creating `DietSessionList` object. 1. Based on the parsed input, `DietManager` calls `CommandLib` to return the correct Command Object `DietSessionList`. 1. Executing command. 1. `DietManager` calls `DietSessionList.execute()` with the rest of parsed input. 1. The execute method opens a directed save folder on the drive then assigns it to a File array. 1. `DietSessionList` then calls the `formatList()` method which takes the File Array and converts it into an ArrayList. 1. `DietSessionList` then calls the `formatRow()` method from within formatList\(\) which converts the files into a formatted table output. 1. `DietSessionList` returns a CommandResult object with the entire table message of the diet sessions. 1. Prompting result to user. 1. `DietManager` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

The sequence diagram below summarizes how listing past Diet sessions work:

![Load Data Sequence Diagram](../.gitbook/assets/DietSessionList.png)

* **Alternative 1 \(current choice\):** Print out a neatly formatted list of diet sessions.
  * Pros: The information is easy to read due to neat formatting.
  * Cons: Execution time is slower as it requires a lot more calculations.
* **Alternative 2:** Print out the file name.
  * Pros: Execution time is fast. 
  * Cons: The information is harder to filter through.    

[Return to Top](developerguide.md#intro)

#### 4.3.4. [Edit a past diet session:](developerguide.md) `edit` <a id="edit-a-past-diet-session"></a>

The feature allows users to edit previously created diet sessions.

**Implementation**  
When the user types `edit [INDEX_OF_SESSION]` the following sequence occurs. 1. The user keys in `edit 1`. 1. `DietManager` calls `dietManagerUi.getCommand()` to receive user input. 1. `DietManager` calls `DietManagerParser.parseCommand()` to parse user input into a string array. 1. Creating `DietSessionEdit` object. 1. Based on the parsed input, `DietManager` calls `CommandLib` to return the correct Command Object `DietSessionEdit`. 1. Executing command. 1. `DietManager` calls `DietSessionEdit.execute()` with the rest of parsed input. 1. The execute method then calls `readDietSession()` from DietStorage which returns a dietSession instance. 1. `DietSessionEdit` then calls the `start()` method within an instantiated DietSession created with the parsed input. 1. `DietSession` then proceeds to completion until the user types "end", saving after every command with `DietStorage`. 1. `DietSessionHelp` returns a CommandResult object with the help message of the diet manager. 1. Prompting result to user. 1. `DietManager` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

The sequence diagram below summarizes how editing Diet session works:

![Load Data Sequence Diagram](../.gitbook/assets/DietSessionEdit.png)

**Design considerations** Saving of the user’s Diet sessions:

* **Alternative 1:** Saving at the end of a diet session
  * Pros: The cost of saving is low, file writes only happen once per Diet session instance. 
  * Cons: If any crashes occur during a diet session, no input data will be saved.
* **Alternative 2 \(current choice\):** Saving during any alterations made to the Diet session
  * Pros: The files will still be saved even if a crash occurs.
  * Cons: Saving often might be taxing on the user's computer especially on slower models.

[Return to Top](developerguide.md#intro)

#### 4.3.5. [Delete a previously created diet session:](developerguide.md) `delete` <a id="delete-a-past-diet-session"></a>

The feature allows users to delete previously created diet sessions.

**Implementation**  
When the user types `delete [INDEX_OF_SESSION]` from a Diet manager instance the following sequence occurs. 1. The user keys in `delete 1`. 1. `DietManager` calls `dietManagerUi.getCommand()` to receive user input. 1. `DietManager` calls `DietManagerParser.parseCommand()` to parse user input into a string array. 1. Creating `DietSessionDelete` object. 1. Based on the parsed input, `DietManager` calls `CommandLib` to return the correct Command Object `DietSessionDelete`. 1. Executing command. 1. `DietManager` calls `DietSessionDelete.execute()` with the rest of parsed input. 1. The execute method then deletes the file at the indicated index `1` if a file was present there. 1. `DietSessionDelete` returns a CommandResult object with the delete confirmation message from DietmanagerUi. 1. Prompting result to user. 1. `DietManager` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

The sequence diagram below summarizes how Diet sessions are deleted:

![Delete\_Diet\_Session\_Sequence\_Diagram](../.gitbook/assets/DietSessionDelete.png)

* **Alternative 1 \(current choice\):** Provides an indexed array for the user to choose from to delete.
  * Pros: The user can delete things easier as it only requires typing a number.
  * Cons: Execution time is slower as it requires more calculations.
* **Alternative 2:** Delete based on a user string input of the file name.
  * Pros: Easier to implement.
  * Cons: Users are greatly inconvenienced by how much they have to type.    

[Return to Top](developerguide.md#intro)

#### 4.3.6. [Clear all past diet session:](developerguide.md) `clear` <a id="clear-all-past-diet-sessions"></a>

The feature allows users to clear all previously created diet sessions at once.

**Implementation**  
When the user types `clear` the following sequence occurs. 1. The user keys in `clear`. 1. `DietManager` calls `dietManagerUi.getCommand()` to receive user input. 1. `DietManager` calls `DietManagerParser.parseCommand()` to parse user input into a string array. 1. Creating `DietSessionClear` object. 1. Based on the parsed input, `DietManager` calls `CommandLib` to return the correct Command Object `DietSessionClear`. 1. Executing command. 1. `DietManager` calls `DietSessionDelete.execute()` with the rest of parsed input. 1. The execute method then deletes the file at the indicated index `1` if a file was present there. 1. `DietSessionDelete` returns a CommandResult object with the delete confirmation message from DietmanagerUi. 1. Prompting result to user. 1. `DietManager` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

The sequence diagram below summarizes how Diet sessions are all cleared:

![Delete\_Diet\_Session\_Sequence\_Diagram](../.gitbook/assets/DietSessionClear.png)

* **Alternative 1 \(current choice\):** Iterate through an array of files and delete everything.
  * Pros: The file structure is more homogeneous.
  * Cons: Execution time is slower as it requires iterating through every file in the array.
* **Alternative 2:** delete the folder with the save files in it.
  * Pros: Execution time is faster though still limited by storage speed. 
  * Cons: File structure of the entire program is not as stable.    

[Return to Top](developerguide.md#intro)

#### 4.3.7. [Search for Past Diet Sessions:](developerguide.md) `search` <a id="search-for-past-diet-sessions"></a>

The feature allows users to search for previously created diet sessions within a date range or with a specified tag.

**Implementation**  
When the user types `search /s 2020-11-01 /e 2020-11-03 /t breakfast` the following sequence occurs. 1. The user keys in `search /s 2020-11-01 /e 2020-11-03 /t breakfast`. 1. `DietManager` calls `dietManagerUi.getCommand()` to receive user input. 1. `DietManager` calls `DietManagerParser.parseCommand()` to parse user input into a string array. 1. Creating `DietSessionSearch` object. 1. Based on the parsed input, `DietManager` calls `CommandLib` to return the correct Command Object `DietSessionSearch`. 1. Executing command. 1. `DietManager` calls `DietSessionSearch.execute()` with the rest of parsed input. 1. The execute method then iterates through the entire folder and looks for empty tags and folders with the methods `checkEmptyTag()` and `checkEmptyFolder`. 1. `DietSessionSearch` calls the `addToSearchResult()` method which from within calls the `addRow()` method that converts the file output into a table format. 1. `DietSessionSearch` returns a `CommandResult` object with the search results. 1. If the starting search date is after the ending search date, the method will return with an exception which is then returned with the `CommandResult` message. 1. Prompting result to user. 1. `DietManager` calls `CommandResult.getFeedbackMessage()` to get the execution feedback message. 1. `CommandResult` calls `Ui.showToUser()` to show result to the user.

The sequence diagram below summarizes how Diet sessions is searched:

![Search\_Diet\_Session\_Sequence\_Diagram](../.gitbook/assets/SearchDietSession.png)

* **Alternative 1 \(current choice\):** Search by date and tags.
  * Pros: Users can get a precise range of dates for their diet sessions.
  * Cons: Execution time is slower as it requires more calculations.
* **Alternative 2:** Search only by tags.
  * Pros: Easier to implement. 
  * Cons: The information is harder to filter through.    

[Return to Top](developerguide.md#intro)

#### 4.3.8. [Exit the Diet manager:](developerguide.md) `end` <a id="exit-the-diet-manager"></a>

The function returns the user back to the main menu of The Schwarzenegger.

**Implementation**  
When the user types `end` the following sequence occurs. 1. The user keys in `end`.

1. A `DietSessionUi` component will call `dietSessionUI.getInput()`. 
2. Input will be parsed in `processCommand()`.  
3. Exiting of inputLoop\(\)

    The inputLoop\(\) exits when userInput.equals\("end"\), returning to the `Start()` method, then ending the `DietManager` instance.

[Return to Top](developerguide.md#intro)

### 4.4. [Workout-related Features](developerguide.md) <a id="workout-related-features"></a>

#### 4.4.1. [Creating a New Workout Session](developerguide.md) <a id="creating-a-new-workout-session"></a>

Users can create a new workout session. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command or IO related errors. The action will be aborted. If the creation is successful, the user will go into the new workout session to edit the exercises in that session.

The user can specify tags for the session. Creation time, last edit time and saving file name will be auto generated by the application and saved.

**Implementation**

When the user attempts to create a new workout session, the Ui, WorkoutManagerParser and CommandLib class will be accessed and the following sequence of actions are called to return a command object NewWs.

1. User executes `new /t leg chest`
   1. `WorkoutManager` calls `Ui.getUserCommand()` to receive user input.
   2. `WorkoutManager` calls `WorkoutManagerParser.parse` into a string array
2. Creation of command object.
   1. Based on the parsed input, `WorkoutManager` calls `CommandLib` to return the correct Command Object `NewWs`.
3. Executing Command
   1. `WorkoutManager` calls `NewWS.execute()` with the rest of parsed input.
   2. `NewWS` parse the arguments to identify the tags
   3. `NewWS` calls `PastRecordList.add()` to create a new file to store information in this session. 

      If the creation fails, the action is aborted. Else, this record will be stored and the file path will 

      be returned.

   4. `NewWS` creates a new `WorkoutSession` Object with the file path. 
   5. `NewWS` calls `workoutSession. workoutSessionStart()` so that user can add information into this session.
   6. After user exits this workout, `WorkoutManager` returns a `CommandResult`.
4. Based on `CommandResult`, correct response will be printed to user.

All description, warnings and response will be handled by `Ui` to ensure consistence across the app. The following sequence diagram shows how the new command works

The sequence diagram below summarizes how creating new workout session works: ![Load Data Sequence Diagram](../.gitbook/assets/NewWS.png) **Design considerations** Parsing of the user’s input command:

* **Alternative 1 \(current choice\):** User’s commands are divided by space.
  * Pros: The parsing can be easily done by calling Java built-in function .split\(\). Supports multiple tags or no tags.
  * Cons: Values for each variable cannot contain spaces which makes the application restrictive.
* **Alternative 2:** Multiple prompts for user’s input of a workout data.
  * Pros: Users would not have to make sure that their command is syntactically right. 
  * Cons: The constant prompting could subject the application to a negative experience in the difficulty to use the commands.

[Return to Top](developerguide.md#intro)

#### 4.4.1.1. [Adding an Exercise](developerguide.md) <a id="adding-an-exercise"></a>

Users can add a new exercise. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command or IO related errors. The action will be aborted. If the addition is successful, a new exercise will be added to the exerciselist.

**Implementation**

When the user attempts to add a new exercise, the CommonUi, WorkoutSession, WorkoutSessionParser , CommandLib, WorkoutSessionAdd and WorkoutSessionStorage class will be accessed and the following sequence of actions are called to return a CommandResult object containing a message to show to user.

1. User executes `add benchpress /n 6 /w 120`
   1. `WorkoutSession` calls `CommonUi.getUserCommand()` to receive user input.
   2. `WorkoutSession` calls `WorkoutSessionParser.workoutSessionParser` to convert the input to a string array.
2. Creation of command object.
   1. Based on the parsed input, `WorkoutSession` calls `CommandLib` to return the correct Command Object `WorkoutSessionAdd`.
3. Executing Command
   1. `WorkoutSession` calls `WorkoutSessionAdd.execute()` with the rest of parsed input.
   2. `WorkoutSessionAdd` parse the arguments to identify the repetitions and weight for the exercise.
   3. `WorkoutSessionAdd` calls `WorkOutSession.Storage.writeToFile()` to store information of all exercises recorded. 
   4. `WorkoutSessionAdd` returns a `CommandResult` to WorkoutSession\`.
4. Based on `CommandResult`, correct response will be printed to user.

All description, warnings and response will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how the add command works: ![Load Data Sequence Diagram](../.gitbook/assets/WorkoutSessionAdd.png)

Below are the sub-diagrams: 

![Load Data Sequence Diagram](../.gitbook/assets/ParseInputWorkoutSession.png)

Figure 4.4.1.1.1. Sub-diagram for Parsing Input in WorkoutSession

Figure 4.4.1.1.2. Sub-diagram for Showing Message to User

**Design considerations** Aspects: Making add and its parameters as seperate or a single input

* **Alternative 1 \(current choice\):** Making add and its parameters as a single input
  * Pros: Would be easier for the user to enter as it takes less time to enter and it is not too difficult of a command.
  * Cons: It might cause the user to miss the format for inputting add.
* **Alternative 2:** Making add and its parameters as separate inputs
  * Pros: Would make it neater and more clear to the user what to enter as they would only enter one input every time.
  * Cons: It would take multiple actions to perform a single task, depending on the number of parameters.

[Return to Top](developerguide.md#intro)

#### 4.4.1.2. [Deleting an Exercise](developerguide.md) <a id="deleting-an-exercise"></a>

Users can delete an exercise from a pre-existing list of exercise. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command or IO related errors. The action will be aborted. If the deletion is successful, a new exercise will be added to the exerciselist.

**Implementation**

When the user attempts to delete an exercise, the CommonUi, WorkoutSession, WorkoutSessionParser , CommandLib, WorkoutSessionDelete and WorkoutSessionStorage class will be accessed and the following sequence of actions are called to return a CommandResult object containing a message to show to user.

1. User executes `delete 1`
   1. `WorkoutSession` calls `CommonUi.getUserCommand()` to receive user input.
   2. `WorkoutSession` calls `WorkoutSessionParser.workoutSessionParser` to convert the input to a string array.
2. Creation of command object.
   1. Based on the parsed input, `WorkoutSession` calls `CommandLib` to return the correct Command Object `WorkoutSessionDelete`.
3. Executing Command
   1. `WorkoutSession` calls `WorkoutSessionDelete.execute()` with the rest of parsed input.
   2. `WorkoutSessionDelete` parse the arguments to identify the index of the exercise to be deleted.
   3. `WorkoutSessionDelete` calls `exerciseList.remove()` to delete the respective exercise.
   4. `WorkoutSessionDelete` calls `WorkOutSession.Storage.writeToFile()` to store information of all exercises recorded. 
   5. `WorkoutSessionDelete` returns a `CommandResult` to WorkoutSession\`.
4. Based on `CommandResult`, correct response will be printed to user.

All description, warnings and response will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how the delete command works: ![Load Data Sequence Diagram](../.gitbook/assets/WorkoutSessionDelete.png)

You can refer to [Figure 4.4.1.1.1. Sub-diagram for Parsing Input in WorkoutSession](developerguide.md#figure-4-4-1-1-1) and [Figure 4.4.1.1.2. Sub-diagram for Showing Message to User](developerguide.md#figure-4-4-1-1-2) for the corresponding sub-diagrams.

**Design considerations** Aspects: Making delete and index to delete as separate or a single input

* **Alternative 1 \(current choice\):** Making delete and index to delete as a single input
  * Pros: Would be easier for the user to enter as it takes less time to enter and it is not too difficult of a command.
  * Cons: It might cause the user to miss the format for inputting delete.
* **Alternative 2:** Making delete and index to delete as separate inputs
  * Pros: Would make it neater and more clear to the user what to enter as they would only enter one input every time.
  * Cons: It would take two actions to perform a single task.

[Return to Top](developerguide.md#intro)

#### 4.4.1.3. [Listing All Exercises in This Session](developerguide.md) <a id="listing-all-exercises-in-this-session"></a>

Users can list all exercise from a pre-existing list of exercise. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command or IO related errors. The action will be aborted. If the listing is successful, the user will be able to see the full list of exercises.

**Implementation**

When the user attempts to list all exercises, the CommonUi, WorkoutSession, WorkoutSessionParser , CommandLib, WorkoutSessionList and WorkoutSessionStorage class will be accessed and the following sequence of actions are called to return a CommandResult object containing a message to show to user.

1. User executes `list`
   1. `WorkoutSession` calls `CommonUi.getUserCommand()` to receive user input.
   2. `WorkoutSession` calls `WorkoutSessionParser.workoutSessionParser` to convert the input to a string array.
2. Creation of command object.
   1. Based on the parsed input, `WorkoutSession` calls `CommandLib` to return the correct Command Object `WorkoutSessionList`.
3. Executing Command
   1. `WorkoutSession` calls `WorkoutSessionList.execute()` with the rest of parsed input.
   2. `WorkoutSessionList` calls `WorkoutSessionList.printList()` to check if the list is empty.
   3. `WorkoutSessionList.printList()` calls `WorkoutSessionList.formatList()` to arrange the list in a readable and dynamic format for the user.
   4. `WorkoutSessionList.formatList()` returns a String of formatted output to `WorkoutSessionList.printList()` then to `WorkoutSessionList`. 
   5. `WorkoutSessionList` returns a `CommandResult` to `WorkoutSession`.
4. Based on `CommandResult`, correct response will be printed to user.

All description, warnings and response will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how the list command works: ![Load Data Sequence Diagram](../.gitbook/assets/WorkoutSessionList.png)

**Design considerations** Aspects: Length of results

* **Alternative 1 \(current choice\):** Make the length for displaying exercise dynamic
  * Pros: The table would look more appealing as the spacing would be dynamic.
  * Cons: It is a lot more difficult to code.
* **Alternative 2:** Make the length allocated for exercise really long
  * Pros: The code would be simpler.
  * Cons: The table would look ugly for the user to look at.

[Return to Top](developerguide.md#intro)

#### 4.4.1.4. [Searching for Related Exercises](developerguide.md) <a id="searching-for-related-exercises"></a>

Users can search for an exercise from a pre-existing list of exercise. The failure to do so will trigger an exception where the user will be notified of the reason, e.g. invalid command or IO related errors. The action will be aborted. If the searching is successful, the user will be able to see the list of exercises that match.

**Implementation**

When the user attempts to search for an exercise from all exercises, the CommonUi, WorkoutSession, WorkoutSessionParser , CommandLib, WorkoutSessionSearch and WorkoutSessionStorage class will be accessed and the following sequence of actions are called to return a CommandResult object containing a message to show to user.

1. User executes `search bench`
   1. `WorkoutSession` calls `CommonUi.getUserCommand()` to receive user input.
   2. `WorkoutSession` calls `WorkoutSessionParser.workoutSessionParser` to convert the input to a string array.
2. Creation of command object.
   1. Based on the parsed input, `WorkoutSession` calls `CommandLib` to return the correct Command Object `WorkoutSessionSearch`.
3. Executing Command
   1. `WorkoutSession` calls `WorkoutSessionSearch.execute()` with the rest of parsed input.
   2. `WorkoutSessionSearch` checks if the search term is empty. If it is empty, `WorkoutSessionSearch` returns a failure result to `WorkoutSession`. Otherwise, the process continues with step `3`
   3. `WorkoutSessionSearch` calls `WorkoutSessionSearch.formatList()` to search the search term with the exerciseList. If it is empty, `WorkoutSessionSearch.formatList()` returns a failure result to `WorkoutSession`. Otherwise, the process continues with step `4`
   4. `WorkoutSessionSearch` returns a `CommandResult` to `WorkoutSession`.
4. Based on `CommandResult`, correct response will be printed to user.

All description, warnings and response will be handled by `CommonUi` to ensure consistence across the app.

The sequence diagram below summarizes how the search command works: ![Load Data Sequence Diagram](../.gitbook/assets/WorkoutSessionSearch.png)

**Design considerations** Aspects: Length of results

* **Alternative 1 \(current choice\):** Make the length for displaying exercise dynamic
  * Pros: The table would look more appealing as the spacing would be dynamic.
  * Cons: It is a lot more difficult to code.
* **Alternative 2:** Make the length allocated for exercise really long
  * Pros: The code would be simpler.
  * Cons: The table would look ugly for the user to look at.

[Return to Top](developerguide.md#intro)

#### 4.4.2. [Listing Past Workout Sessions](developerguide.md) <a id="listing-past-workout-sessions"></a>

The feature to list workoutSessions allows the user to view a summary of all the history workout sessions, including their index, creation date and tags.

**Implementation** When the user attempts to list workoutSessions, the WorkoutManger, WorkoutManagerParse, ListWS and WorkoutManagerStorage class will be called upon. The following sequence of steps will then occur:

1. User executes `list /s 20201010 /e 20201025`
   1. `WorkoutManager` calls `Ui.getUserCommand()` to receive user input.
   2. `WorkoutManager` calls `WorkoutManagerParser.parse` into a string array
2. Creation of command object.
   1. Based on the parsed input, `WorkoutManager` calls `CommandLib` to return the correct Command Object `ListWS`.
3. Executing Command
   1. `WorkoutManager` calls `ListWS.execute()` to execute the command
   2. `ListWS` calls `PastRecordList.list()`
   3. `PastRecordList` will return formatted list.
   4. `WorkoutManager` returns a `CommandResult` which contains the formatted list and execution result.
4. Based on `CommandResult`, correct response will be printed to user.

![Load Data Sequence Diagram](../.gitbook/assets/ListWS.png)

**Design considerations** Aspects: Security of stored data

* **Alternative 1 \(current choice\):** call public methods of Storage class to print the list
  * Pros: pastRecord are private and it can only be manipulated through designed public methods. Only selected data will be printed and viewed.
  * Cons: Most methods Storage needs to be a static.
* **Alternative 2:** Storage return a readonly list of pastRecord.
  * Pros: More versatile operations can be done.
  * Cons: All data of pastRecord will be exposed.

[Return to Top](developerguide.md#intro)

#### 4.4.3. [Editing Workout Session](developerguide.md) <a id="editing-workout-session"></a>

User can anytime go back to edit a workout session created in the past such as adding or removing exercies in that session.

Each past workout session is stored in a different file name following its creation time. The meta information of these past records such as file name, creation time are stored in another file which will be loaded as the program initlises. The actual workout session record will only be loaded if needed e.g. when editting is called.

**Implementation** When the user attempts to edit a past workout session, the Ui, WorkoutManagerParser, CommandLib and WorkoutStorage class will be accessed and the following sequence of actions are called.

1. User executes `edit 1`
   1. `WorkoutManager` calls `Ui.getUserCommand()` to receive user input.
   2. `WorkoutManager` calls `WorkoutManagerParser.parse` into a string array
2. Creation of command object.
   1. Based on the parsed input, `WorkoutManager` calls `CommandLib` to return the correct Command Object `EditWS`.
3. Executing Command
   1. `WorkoutManager` calls `EditWS.execute()` with the rest of parsed input.
   2. `EditWS` calls `PastRecordList.edit()` to locate the file. If the does not exist, the action is aborted. Else, `PastRecordList` updates the meta information of the file and write to local workoutSessionStorage. The file path will be returned.
   3. `EditWS` creates a new `WorkoutSession` Object with the file path. `WorkoutSession` is initilised by loading the data in the file.
   4. `EditWS` calls `workoutSession.workoutSessionStart()` so that user start editing this session.
   5. After user exits this workout, `WorkoutManager` returns a `CommandResult`.
4. Based on `CommandResult`, correct response will be printed to user.

All description, warnings and response will be handled by `Ui` to ensure consistence across the app. The following sequence diagram shows how the new command works

The sequence diagram below summarizes how editting past record works: ![Load Data Sequence Diagram](../.gitbook/assets/EditWS.png) **Design considerations** Past record workoutSessionStorage and model design:

* **Alternative 1 \(current choice\):** store past workout sessions in different files and their meta information in a separate file
  * Pros: Initialization will be faster as data loaded grows little even in long terms. 
  * Cons: Deleting files and creating files need to handle file names carefully.
* **Alternative 2:** Load all past records during initialization.
  * Pros:  Run time can retrieve data faster as there is no need to access data in hard disk. 
  * Cons: The application initialization will grow quickly as the application scales.

[Return to Top](developerguide.md#intro)

#### 4.4.4. [Deleting a workout Session](developerguide.md) <a id="deleting-a-workout-session"></a>

User can delete a workout session created in the past by giving its index.

Each past workout session is stored in a different file name following its creation time. The meta information of these past records such as file name, creation time are stored in another file which will be loaded as the program initlises. When the user tries to delete a file, the application refers to the meta information of the file to locate the file and delete it. Then the meta information of the record will be deleted.

User can clear all data by iteratively delete the record until the meta data file is empty. To simplify that, user can use `clear` command to achieve that.

**Implementation**

When the user attempts to delete a past workout session, the Ui, WorkoutManagerParser, CommandLib and WorkoutStorage class will be accessed and the following sequence of actions are called.

1. User executes `delete 1` or `clear`
   1. `WorkoutManager` calls `Ui.getUserCommand()` to receive user input.
   2. `WorkoutManager` calls `WorkoutManagerParser.parse` into a string array
2. Creation of command object.
   1. Based on the parsed input, `WorkoutManager` calls `CommandLib` to return the correct Command Object `DeleteWS`

      or `clearWS`.
3. Executing Command
   1. `WorkoutManager` calls `DeleteWS.execute()` with the rest of parsed input.
   2. `DeleteWS` calls `PastRecorList.delete()` to locate the file. If the does not exist, the action is aborted. Else, `PastRecorList` remove the meta information of the file and delete the local workoutSessionStorage file.
   3. After user exits this workout, `WorkoutManager` returns a `CommandResult`.
4. Based on `CommandResult`, correct response will be printed to user.

All description, warnings and response will be handled by `Ui` to ensure consistence across the app.

The sequence diagram below summarizes how deleting past record works: ![Load Data Sequence Diagram](../.gitbook/assets/DeleteWS.png)

**Design considerations**

* **Alternative 1 \(current choice\):** Delete `workoutSession` by specifying index of it.
  * Pros: Quick and easy deletion by using ArrayList.get\(\).
  * Cons: Lesser alternatives for the user and user would have to identify the index first by executing `list` to get index of the session to be deleted.
* **Alternative 2:** Delete `workoutSession` by specifying `workoutSession` tags or dates.
  * Pros: More alternatives for users. Can bulk delete files with certain attributes. 
  * Cons: Tags and dates does not uniquely identify the record hence may result in accidental wrong deletion.

[Return to Top](developerguide.md#intro)

#### 4.4.5. [Searching Based on Conditions](developerguide.md) <a id="searching-based-on-conditions"></a>

The feature `search` allows the user to view a summary of all the history workout sessions which satisfies certain conditions.

The user can search by the date of creation, or the tags that the session has. User can put in 0 or 1 or 2 criteria during search.

The user can attach variable number of tags after `/t` and one date after `/d`. The date must be specified in certain formats for it to be recognisable. Else, it will be treated as there is no date criteria given. [See here](developerguide.md#appendix-e-supported-formats-of-date-input) for all supported formats.

The tag criterion selects sessions which contains all the tags that the user specified in the search. The date criterion selects the sessions which is created on that date. Only sessions that satisfies all conditions will be selected and displayed.

The result is displayed in a table with the index of the selected records so that users can easily do further operations on them, e.g. `delete` or `edit`.

**Implementation**

When the user attempts to list workoutSessions, the WorkoutManger, DeleteWS, WorkoutManagerStorage and WorkoutManagerParse class will be called upon. The following sequence of steps will then occur:

1. User executes `search /t leg /d 20201017`
   1. `WorkoutManager` calls `Ui.getUserCommand()` to receive user input.
   2. `WorkoutManager` calls `WorkoutManagerParser.parse` into a string array
2. Creation of command object.
   1. Based on the parsed input, `WorkoutManager` calls `CommandLib` to return the correct Command Object `SearchWS`.
3. Executing Command
   1. `WorkoutManager` calls `SearchWS.execute()` to execute the command
   2. `SearchWS` calls `PastRecorList.search()`
   3. `PastRecordList` will call `WorkoutManagerParser.parse` to parse the arguments into an array of predicates
   4. `PastRecordList` filters the pastRecord arraylist and return a string representation of the filtered records to `WorkoutManager`
   5. `WorkoutManager` returns a `CommandResult`.
4. Based on `CommandResult`, correct response will be printed to user.

The sequence diagram below summarizes how searching record works: ![Load Data Sequence Diagram](../.gitbook/assets/SearchWS.png) **Design considerations** Aspects: indexing the selected results

The index of a record is not stored in the schema because it easily varies with addition and deletion. Thus given a record, searching for its index will have higher time complexity.

* **Alternative 1 \(current choice\):** print out the actual index of the record in the meta info file.
  * Pros: The index is useful for user to use for future actions.
  * Cons: Checking for the actual location complicates the search time complexity.
* **Alternative 2:** print out the index of the element in the result list.
  * Pros: Easy to implement. Low time complexity.
  * Cons: Since the index in result list is not the same as the index in actual record meta, user cannot use the index for further actions.

[Return to Top](developerguide.md#intro)

### 4.5. [Logging](developerguide.md) <a id="logging"></a>

Logging in the application refers to storing exceptions, warnings and messages that occur during the execution of Kitchen Helper. It was included to help developers to identify bugs and to simplify their debugging process.

The `java.util.logging` package in Java is used for logging. The logging mechanism can be managed from the `SchwarzeneggerLogger` class through the `logger` attribute.

All controls of the logger for the application can be viewed/ altered in the class construction. The current settings for the logger are as follow:

* All information is logged into a log file, `SchwarzeneggerLogs.log`.
* Logging is made to be displayed in the `SimpleFormatter` style where the date, class and error description are recorded.

Logging Levels:

* `Level.SEVERE`: a serious failure, which prevents normal execution of the program, for end users and system administrators.
* `Level.WARNING`: a potential problem, for end users and system administrators.
* `Level.INFO`: reasonably significant informational message for end users and system administrators.
* `Level.CONFIG`: hardware configuration, such as CPU type.
* `Level.FINE`, `Level.FINER`, `Level.FINEST`: three levels used for providing tracing information for the software developers.

`SchwarzeneggerLogger` follows singleton design pattern. Thus, other classes can access the `logger` by calling `SchwarzeneggerLogger.getInstanceLogger()`, and logging can be done by invoking the function `log()`. This will ensure that all loggings will be made to the same file across the various classes.

An example is shown below:

```text
private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();
logger.log(Level.WARNING, DESCRIPTION_OF_WARNING, e.toString());
```

[Return to Top](developerguide.md#intro)

## 5. [Testing](developerguide.md) <a id="testing"></a>

### 5.1. [Running Tests](developerguide.md) <a id="running-tests"></a>

There are two ways to run tests for The Schwarzenegger.

**Method 1: Using IntelliJ JUnit test runner**

* To run all tests, right-click on the `src/test/java` folder and choose `Run 'All Tests'`.
* To run a subset of tests, you can right-click on a test package, test class, or a test and choose `Run 'ABC'`.

**Method 2: Using Gradle**

* To run all tests, open a console and run the command `gradlew clean test` \(MacOS/Linux: `./gradlew clean test`\)

  > **Note:** If you are new to Gradle, refer to this [Gradle Tutorial](developerguide.md#https://se-education.org/guides/tutorials/gradle.html) to get more tips on how to use Gradle commands.

[Return to Top](developerguide.md#intro)

### 5.2. [Types of Tests](developerguide.md) <a id="types-of-tests"></a>

We have use types of tests:

1. Unit tests targeting the lowest level methods/classes.  e.g. profile.UtilsTest
2. Integration tests that are checking the integration of multiple code units \(those code units are assumed to be working\). e.g. logic.commands.workout.workoutsession.WorkoutSessionAddTest
3. Hybrids of unit and integration tests. These test are checking multiple code units as well as how they are connected together. e.g. profile.ProfileSessionTest

[Return to Top](developerguide.md#intro)

## 6. [Dev Ops](developerguide.md) <a id="dev-ops"></a>

### 6.1. [Build Automation](developerguide.md) <a id="build-automation"></a>

We use Gradle for tasks related to build automation, such as running tests, and checking code for style compliance.

To run all build-related tasks:

1. Open a terminal in the project’s root directory.
2. Run the command:
   * Windows: `gradlew build`
   * MacOS/Linux: `./gradlew build`
3. A message stating `BUILD SUCCESSFUL` will be shown in the terminal if all tasks run successfully.  


   Otherwise, use the error report provided to resolve the issue before trying again. 

[Return to Top](developerguide.md#intro)

### 6.2. [Continuous Integration](developerguide.md) <a id="continuous-integration"></a>

We use Github Actions for continuous integration. No setup will be required for users who fork from the main The Schwarzenegger repository.

Whenever you create a pull request to the main repository for The Schwarzenegger:

* Various checks will automatically be executed on your pull request.
* If any checks fail, click on it to view the cause of the error, and fix it in your branch before pushing it again.
* Ensure that all checks pass before merging your pull request.

[Return to Top](developerguide.md#intro)

### 6.3. [Coverage Report](developerguide.md) <a id="coverage-report"></a>

We use the IntelliJ IDEA’s coverage analysis tool for coverage reporting. A tutorial on how to install and use this tool can be found [here](https://www.youtube.com/watch?v=yNYzZvyA2ik).

[Return to Top](developerguide.md#intro)

### 6.4. [Making a Release](developerguide.md) <a id="making-a-release"></a>

You can follow the steps below to make a new release: 1. Generate the JAR file using Gradle by opening a terminal in the project’s root directory, and run the command:

* Windows: `gradlew clean shadowJar`
* MacOS/Linux: `./gradlew clean shadowJar`
  1. Find the JAR file in the `build/libs` directory.
  2. Tag the repository with the new version number \(e.g. `v2.1`\).
  3. Create a new release using Github and upload the JAR file found in step 3.

[Return to Top](developerguide.md#intro)

### 6.5. [Managing Dependencies](developerguide.md) <a id="managing-dependencies"></a>

Currently, the [Gson library](developerguide.md#https://github.com/google/gson) is being used for JSON parsing, and the [Apache Commons Lang](developerguide.md#https://commons.apache.org/proper/commons-lang) for being used for string processing in The Schwarzenegger. Below are 2 ways to manage these dependencies.

* Use Gradle to manage and automatically download dependencies \(Recommended\).
* Manually download and include those libraries in the repo \(this requires extra work and bloats the repo size\). 

[Return to Top](developerguide.md#intro)

## Appendices

### Appendix A: Product Scope

**Target user profile**:

* Can type fast.    
* Is comfortable with using command line interface.  
* Gyms regularly
* Keeps track of their diet.

**Value Proposition**:

* Manages workout and diet faster with greater efficiency than a typical GUI based fitness manager application.
* Gives users health advice based on their calorie intake of the day and weight expectation.

[Return to Top](developerguide.md#intro)

### Appendix B: User Stories

| Priority | Version | As a ... | I want to ... | So that I can ... |
| :--- | :--- | :--- | :--- | :--- |
| `HIGH` | v1.0 | New user | View the available commands easily | Learn more about the product before I use it |
| `HIGH` | v1.0 | New user | Create a user profile | Add a new profile to store my data |
| `HIGH` | v1.0 | User | View my profile in the database | Reference my added data and know my fitness classification |
| `HIGH` | v1.0 | User | Save my profile into the database | Retrieve it in subsequent launches of the app |
| `HIGH` | v1.0 | User | Load my profile from the database at the start of the app | view my added user profile |
| `HIGH` | v1.0 | User | Delete my profile from the database | Correct accidental typos |
| `HIGH` | v1.0 | User | Create a new workout session | Start a recorded workout session |
| `HIGH` | v1.0 | User | Add moves into a workout session | Personalise and record moves in each workout session |
| `HIGH` | v1.0 | User | Delete workout session record | Correct accidental typos |
| `HIGH` | v1.0 | User | End my current workout session | Be sure that my workout has ended |
| `HIGH` | v1.0 | User | Check my current workout session record | Do my workout and keep track of everything easily |
| `HIGH` | v1.0 | User | List out all my past diet session records | Check what I have eaten in the past |
| `HIGH` | v1.0 | User | Create a diet session with date and tags | Identify when I ate which meal |
| `HIGH` | v1.0 | User | Add different kinds of food into my diet | Keep track fo what I eat |
| `HIGH` | v1.0 | User | Save my diet records | View it next time |
| `MEDIUM` | v1.0 | User | Edit user profile | Change my data if something changes |
| `MEDIUM` | v2.0 | User | Clear all my diet sessions | Clear memory space on my storage to store new things |
| `MEDIUM` | v2.0 | User | Clear all my workout session records | Clear all past redundant data |
| `LOW` | v2.0 | User | Search for past workout sessions | Easily filter through the data that I don't need |
| `LOW` | v2.0 | User | Search for my past diet sessions | See whether I have been eating properly lately |
| `LOW` | v2.1 | User | View how much more I need to eat in a day | plan my later meals easier |
| `LOW` | v2.1 | User | Get recommendation on my weight expectation | Adjust accordingly to achieve the Normal Weight BMI classification |

[Return to Top](developerguide.md#intro)

### Appendix C: Non-Functional Requirements

Below are the non-functional requirements of The Schwarzenegger: 1. Should work on any mainstream OS as long as it has Java `11` or above installed. 2. A user with above average typing speed for regular English text \(i.e. not code, not system admin commands\) should be able to accomplish most of the tasks faster using commands than a program that uses the mouse. 3. Should not require user to install program file. 4. Should work for single user. 5. Should be able to run without internet connection.

[Return to Top](developerguide.md#intro)

### Appendix D: Glossary

* _Mainstream OS_ - Windows, Linux, Unix, MacOS  

[Return to Top](developerguide.md#intro)

### Appendix E: Supported Formats of Date Input

Here shows all 12 valid formats.

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

[Return to Top](developerguide.md#intro)

