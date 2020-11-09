# Fitr Developer Guide

* Table of Contents
{:toc}

<!-- @@author -->
## 1. Introduction

**Fitr** is a command line application, helping university students keep track of your food intake and exercises.

This developer guide documents the architecture, software design decisions and implementation of Fitr.
This guide is targeted towards current and future developers, who wish to understand and work on this application, as well as designers and software testers.

## 2. Setting Up 
This section explains the prerequisites, and the steps to setting up Fitr on your computer.

### 2.1 Prerequisites
* You must have **JDK 11** or above installed on your computer.
* You must have a GitHub account.
* You must have IntelliJ IDEA IDE installed on your computer.

### 2.2 Setting up Fitr in your computer

> :exclamation: **Caution:** Follow the steps in the following guide precisely. Things will not work out if you deviate in some steps.

This section provides a step-by-step procedure to set up Fitr in your computer.

1. **Fork** this [repository](https://github.com/AY2021S1-CS2113T-W13-2/tp).
1. **Clone** the fork to your computer using [Sourcetree](https://sourcetreeapp.com/) or using any other _Git GUI_.
1. Make sure your IDE is configured as **JDK 11**. If not, follow the steps below:
    1. Ensure you have the correct JDK version installed in your computer.
    1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first).
    1. Set up the correct JDK version for Gradle.
        1. Click `Configure` > `Project Defaults` > `Project Structure`
        1. Click `New...` and set it to the directory of the JDK.
1. When prompted, **import** the project as a **Gradle project** (this can take a few minutes to complete). You can follow the following steps to import the project as a **Gradle Project**:

    > :exclamation: **Note:** Importing a Gradle project is slightly different from importing a normal Java project.

    1. IntelliJ IDEA by default has the Gradle plugin installed. If you have disabled it, click `File` > `Settings` > `Plugins` to enable them.
    1. Click `Import Project` (or `Open or Import` in newer version of IntelliJ).
    1. Locate the `build.gradle` file (not the root folder as you would do in normal importing) and select it. Click `OK`. If asked, choose to `Open as Project`, not `Open as File`.
    1. Click `OK` to accept the default settings but do ensure that the selected version of `Gradle JVM` matches the JDK 11.

### 2.3 Verifying Setup

 1. Run Fitr and enter a few commands to ensure that the application functions are as expected. You may refer to the _User Guide_ [here](https://ay2021s1-cs2113t-w13-2.github.io/tp/UserGuide.html) or enter `help` to find out what commands are supported in Fitr.
 1. Run the tests to ensure that they all pass, by executing the command `gradlew build` in IntelliJ's terminal.

## 3. Design

This section provides an overview of the design of the Fitr application.

### 3.1 Architecture

This section illustrates the high-level architecture of Fitr, as well as an illustration of the components used in Fitr.

<p align="center"><img src="images/ArchitectureDiagram.png"></p>
<p align="center">Figure 1: Architecture diagram of Fitr</p>

Figure 1 above explains the high-level design of Fitr. Given below is a quick overview of each component.

The main driver of the application is `Fitr` is responsible for mainly two phases:
* At app launch: Initialises or loads the respective lists and user profile in the correct sequence and is in charge of connecting the various components with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

The rest of the App consists of:
* `Ui`: The user interface of the application.
* `Parser`: Handles and parses user commands.
* `Command`: Handles the various commands Fitr understands.
* `User`: Contains and handles all information related to user data.
* `StorageManager`: Reads data from and writes data back into a text file for future use.
* `ListManager`: Handles all the list operations in Fitr.
* `Recommender`: Handles the recommendation of the exercises.

Figure 2 below shows how the components work with one another, when a user enters the following command `food chicken rice /600 1`:

<p align="center"><img src="images/AddFoodSequenceDiagram.png"></p>
<p align="center">Figure 2: Sequence diagram when adding a food into Fitr</p>

> :information_source: **Note**: Take note that the lifeline should end at the destroy symbol. Due to a limitation of PlantUML, the lifeline continues after the delete symbol.

In Figure 2 above, the `Ui` class reads the user's input, which is then parsed by the `Parser` class. Once parsed, it creates a new `AddFoodCommand` class, which is then returned to the `Fitr` class to be executed. The `Food` object is created, then added into `FoodList`. The `FoodList` is then saved to local storage by the `StorageManager` class.
 
### 3.2 Components

This section expands on the different components seen in the architecture section.

<!-- @@author hui444 -->
#### 3.2.1 Ui component

The `Ui` class handles all user inputs and system output.

This component also listens to other components and outputs the desired messages in specified formats.

#### 3.2.2 Parser component

The Parser component takes in the user input from the `Ui` class and handles the various commands. If the command is invalid, it calls the `Ui` class to prompt the user until valid commands are entered.
When a valid command is keyed in, the `Parser` class returns a `Command` object to execute the command.

<!-- @@author sixletters -->
#### 3.2.3 Command component

The Command component consists of an abstract `Command` class and the various different commands that inherit the `Command` class.
Each type of command class (e.g. `addFoodCommand`, `addExerciseCommand` etc) implements an abstract `execute()` method that carries out the command. 
Referring to figure 3, `XYZCommand` = HelpCommand, RecommendCommand etc.

<p align="center"><img src="images/CommandClass.png"></p>
<p align="center">Figure 3: <code>Command</code> class diagram</p>

<!-- @@author gohsonghan98 -->
#### 3.2.4 User component

The `User` class contains all information related to the user data (i.e. `name`, `age`, `height`, `weight`
and `gender`) and handles all operations on these user data (i.e. `getBmi()`)

<p align="center"><img src="images/UserClassDiagram.png"></p>
<p align="center">Figure 4: <code>User</code> class diagram</p>

Figure 4 above shows the attributes and methods implemented in `User` class.

The `User` class has two constructors and implementations differ based on the constructor used. 

If a `User` object is instantiated without parameters (i.e. `User user = new User()`), the `setup` method in the
 `user` object will be invoked, allowing users to go through a set of instructions to input their `name`, `age`, `gender`, `height`, `weight` and
 `fitnessLevel`.
 
If a `User` object is instantiated with defined parameters (i.e. `User user = new User(name, age, height, weight
, gender)`), the values from the given parameters will be stored within the user class without going through
 `setup()`.

On startup, the `Fitr` class instantiates a `Storage` object and calls its `loadUserProfile()` method. This method
returns a `User` type object and is referenced by a pre-declared `User` type variable, which is used throughout the
running session. 

<!-- @@author jerichochua -->
#### 3.2.5 StorageManager component

Under the `StorageManager` component, the `StorageManager` class handles the read and write operations of the various list classes available, such as `ExerciseList` and `FoodList`, through classes such as `ExerciseStorage` and `FoodStorage`.

<p align="center"><img src="images/StorageClassDiagram.png"></p>
<p align="center">Figure 5: <code>StorageManager</code> class diagram</p>

Figure 5 above shows the associations and dependencies present with the `StorageManager` class.

On startup, the `Fitr` class creates a new `StorageManager` object. The `StorageManager` class will then create the various storage objects required to load that particular file into the program. 
This will cause the various storage objects to retrieve the text files from the given file paths and loads the data into the program. 
If the files do not exist, the various storage objects will create the files. For example, if the file that contains the user's exercise data does not exist, the `ExerciseStorage` object will create a new file to store the user's exercise data.

Each time there is a change in the `FoodList`, `ExerciseList` or `User` objects, or when the program is exiting, `writeExerciseList()`, `writeFoodList()` and `writeUserProfile()` methods in `StorageManager` are invoked. 
This will save the data onto the respective text files on the hard disk.

#### 3.2.6 ListManager component

Under the `ListManager` component, the `ListManager` class handles operations on the exercise, food and goal lists
, such as adding or deleting an item and clearing the lists. Figure 6 below shows the associations and dependencies present with the `ListManager` class.

<p align="center"><img src="images/ListManagerClassDiagram.png"></p>
<p align="center">Figure 6: <code>ListManager</code> class diagram</p>

On startup, the `Fitr` class creates a new `ListManager` object, with `StorageManager` as its parameter. The `ListManager` object then creates `ExerciseList`, `FoodList` and `GoalList` objects, and attempt to load the data into the lists through the `StorageManager` object. If no data is found, then an empty list will be created.

<!-- @@author sixletters -->
#### 3.2.7 Recommender component
The `Recommender` component handles the recommendation of the exercises. Based on the user inputs, it either returns a list of type `StandardExerciseList` full of general or specific workouts. For example, it either recommends a mix of exercises from the 4 different categories of aerobic, upperbody, lowerbody and stretch, or it chooses only workouts from each of the category.

When the `Recommend` class is instantiated, the constructor calls onto the `Storage` class to load multiple different `StandardExerciseList` instances as attributes. 

The `recommend` method in the class then chooses and adds different permutations or combinations of `StandardExercise` instances from the multiple different `StandardExerciseList` instances to load into a new instance of `StandardExerciseList`.

<p align="center"><img src="images/RecommenderClassDiagram.png"></p>
<p align="center">Figure 7: <code>Recommender</code> class diagram</p>

<!-- @@author -->
#### 3.2.8 Common classes

Classes used by multiple components are in the `fitr.common` package.

## 4. Implementation

This section describes how some of the features in Fitr are implemented.

<!-- @@author sixletters -->
### 4.1 Add Exercise and Add Food Command
The `AddFoodCommand` and `AddExerciseCommand` are similar in implementation. It simply adds the user input into either the `ExerciseList` or the `Foodlist` using the ListManager.

<p align="center"><img src="images/AddExerciseSequence.png"></p>
<p align="center">Figure 8: Sequence diagram for <code>AddExerciseCommand</code></p>

<!-- @@author jerichochua -->
### 4.2 Edit command
When the user enters an edit command, it first passes through `Parser`. Once the input is parsed as an edit command, it is then passed to `EditCommandParser`, where it further parses the user's input. As the user is able to edit either the individual profile characteristics, food or exercise entries, the `EditCommandParser` is able parse what the user intends to edit. For example, if the user intends to edit a food entry, the input is then passed to `EditEntryCommand`, which parses the remaining arguments, and performs the required edit.
Figure 9 below shows the sequence diagram when the user enters `edit exercise 25/10/2020 1 push ups /100`.

<p align="center"><img src="images/EditExerciseSequenceDiagram.png"></p>
<p align="center">Figure 9: Sequence diagram for <code>edit</code> command</p>

<!-- @@author gohsonghan98 -->
If an edit command is passed to change individual profile characteristics, the `EditCommandParser` will pass the
 input to `EditProfileCommand` instead. Figure 10 below shows the sequence diagram when the user enters `edit name Tom`.

<p align="center"><img src="images/EditProfileSequenceDiagram.png"></p>
<p align="center">Figure 10: Sequence diagram for <code>edit</code> command</p>

<!-- @@author hui444 -->
### 4.3 Help command
When the user enters `help`, the `Ui` class reads it and passes it through `Parser`.  Once the input is parsed as a help command, the user input is passed to `HelpCommand`, which calls `Ui` to print the help message.
Figure 11 below shows the sequence diagram when the user enters `help`.

<p align="center"><img src="images/HelpCommandSequenceDiagram.png"></p>
<p align="center">Figure 11: Sequence diagram for <code>help</code> command</p>

### 4.4 View command
The view command allows user to view certain information available in the `User` class and `ListManager` class. When
the user enters a view command, the `Ui` class reads it and passes it through `Parser`. Once the input is parsed as
a view command, it is then passed to `ViewCommand`, where it is handled based on the type of view command. 
Then the respective view method is called to output the messages via the `Ui` class.

For example, as illustrated in Figure 12, if you intend to view your goal entry with the command `view goal`, the input is passed to `ViewCommand`
, which checks the arguments after 'view' and calls the `viewGoal()` method. It then performs the required steps to
retrieve the goal status for each entry and prints the results using the `printCustomMessage()` method in the `Ui` class.

<p align="center"><img src="images/ViewGoalSequenceDiagram.png"></p>
<p align="center">Figure 12: Sequence diagram for <code>view goal</code> command</p>

<!-- @@author gohsonghan98 -->
The sequence diagram in Figure 13 below shows how the `ViewCommand` class can interact with the `User` class when the
user keys in `view profile`.
 
<p align="center"><img src="images/ViewProfileSequenceDiagram.png"></p>
<p align="center">Figure 13: Sequence diagram for <code>view profile</code> command</p>

<!-- @@author jerichochua -->
### 4.5 Clear command
The `clear` command allows the user to clear either the exercise list, food list, or goal list, by specifying in the user's input as an argument (i.e. `clear exercise`, `clear food`, or `clear goal`). The user is also able to clear all the lists at the same time, if no argument is specified (i.e. `clear`).

The user's input is first parsed by the `Parser` class. It is then passed to the `ClearCommand` class, which is then executed. The arguments are parsed in the `ClearCommand` class, and the required list(s) is then cleared. After clearing, it then writes the new empty list(s) to local storage.

Figure 14 below shows the sequence diagram when the user inputs the `clear` command.

<p align="center"><img src="images/ClearCommandSequenceDiagram.png"></p>
<p align="center">Figure 14: Sequence diagram for <code>clear</code> command</p>

<!-- @@author dmbclub -->
### 4.6 Delete command
The `delete` command allows the user to delete an entry from either the exercise list, food list or goal list. The user can only delete one entry each time.

The user's input is first parsed by the `Parser` class, which returns a `DeleteCommand` to `Fitr`. Then `DeleteCommand` is executed to delete the entry in the list (i.e. food list, exercise list or goal list) by calling `ListManager` and update the corresponding local data file by calling `StorageManager`.

Figure 15 below shows the sequence diagram when the user inputs the `delete` command.

<p align="center"><img src="images/DeleteCommandSequenceDiagram.png"></p>
<p align="center">Figure 15: Sequence diagram for <code>delete</code> command</p>

<!-- @@author sixletters -->
### 4.7 Recommend command
The `recommend` command allows the user to get either a general recommended workout or a workout to a specific body part or type. The commands that can follow recommend are `aerobic`, `upperbody`, `lowerbody` and `stretch`.

The user's input is first parsed by the `Parser` class, which returns a `RecommendCommand` to `Fitr`. The `RecommendCommand` is then executed to recommend workouts. The `RecommendCommand` calls on the `recommend` method in `Recommender`.

The `Recommender` class then returns a list of type `StandardExerciseList`. The user input is then read in by the `Ui` class to determine which `StandardExercise` objects in the `StandardExerciseList` should be converted to `Exercise` and added to the exerciseList of type `ExerciseList`

<p align="center"><img src="images/RecommendSequence.png"></p>
<p align="center">Figure 16: Sequence diagram for <code>recommend</code> command</p>

<!-- @@author dmbclub -->
### 4.8 Tip of the day

Fitr can give an interesting fact or a tip of exercise every time the user opens the app.

When the user opens the program, a `TipList` is automatically created by `Fitr`, which loads the tipList from `StorageManger` and passes it to `Fitr`. Then `Fitr` creates a `TipManager` and passes the tipList to `TipManager` to generate a random tip. Finally, the tip is passed to `Ui` and printed in yellow using `printMessageInYellow()`.

Figure 17 below shows the sequence diagram for giving a tip.

<p align="center"><img src="images/TipCommandSequenceDiagram.png"></p>
<p align="center">Figure 17: Sequence diagram for giving a tip</p>

<!-- @@author -->
## Appendix A: Product Scope
### Target user profile

Our target user profile is university students, at all fitness levels, who values an application that integrates the logging of food, exercises performed, the tracking of calories and goals in one application. 
It is also aimed at those who prefer typing over mouse interactions and are reasonably comfortable with using command-line applications.

### Value proposition

Our application helps users to keep fit without needing them to do extensive research on healthy living or workout options. 
We will be collecting user information such as age, weight, fitness level, and the application will recommend users preset exercises.

## Appendix B: User Stories

_Table 1: User stories_

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|student|enter my food intake|log my calories|
|v1.0|student|view my past calories intake|track my calories|
|v1.0|student|store the food I consumed together with its calories in the application|add food that I previously consumed without having to enter the calories again|
|v1.0|new user|access the help function|I know what to do with the application|
|v1.0|student|print my past exercise records|see my workout progress|
|v1.0|student|enter my height and weight|the application can calculate my BMI|
|v1.0|student|save my past exercise and food records|access my past records at all times|
|v2.0|student|edit my previous food, exercise and goal entries|fix any mistakes I made previously|
|v2.0|student|clear my previous food, exercise and goal entries|declutter my exercise and food records|
|v2.0|student|indicate my fitness level|exercise based on the recommendations of the application|
|v2.0|student|edit my profile|update my information|
|v2.0|student|set goals|see if I am on track|
|v2.0|student|be recommended a set of workout|save time finding exercises to do|
|v2.0|student|set goals linked to my calories|know my goal status without calculating and comparing my calories|
|v2.0|student|view my food, exercise and goal entries on a specified date|find my entries easily

## Appendix C: Non-functional Requirements

1. This application should work on any mainstream operating system, such as Windows or macOS, as long as Java 11 or above is installed.
2. A user with above average typing speed for regular English text should be able to accomplish most of the tasks faster using commands than using the mouse.

## Appendix D: Instructions for manual testing

1. Initial launch
    1. Download the latest version of `Fitr` from [here](https://github.com/AY2021S1-CS2113T-W13-2/tp/releases).
    1. Open Command Prompt / Terminal.
    1. Run the command `java -jar fitr.jar` (i.e., run the command in the same folder as the jar file).
    1. If the setup is correct, you should see something like below:
       ``` 
        _______   __   __
       |    ___| |__| |  |_  .----.
       |    ___| |  | |   _| |   _|
       |___|     |__| |____| |__|
       Hello! Welcome to Fitr. 
       ```
1. Clear list feature
    1. Add a few food entries.
    1. View food entries, using the command `view food`.
    1. Enter the command `clear food` to clear the food list.
    1. View food entries again to check if the list has been cleared (i.e empty).
    1. If done correctly, you should see something like the following:
       ```
       The food list is empty...
       ```
1. Saving your data feature
    1. Enter a few goal entries.
    1. View goal entries, using the command `view goal`.
    1. Exit the application by entering `bye`.
    1. Run Fitr again and view your goal entries with the same `view goal` command.
    1. If done correctly, you should see the same goals as previously shown.
