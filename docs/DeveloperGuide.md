# Developer Guide

* Table of Contents
{:toc} 

## Design 

### UI component
![Ui component](diagrams/Ui component.png)

**API**: [`Ui.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/Ui.java)

The `UI` component makes use of the following classes:

* [`Ui`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/Ui.java): Responsible for communication between the other classes in the `UI` component and with the `Logic` component.
* [`UiHelper`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiHelper.java): Responsible for providing helper methods to the other classes in the `UI` component.
* [`UiInput`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiInput.java): Responsible for reading in the user commands and checking if it is empty.
* [`UiOuput`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiOutput.java): Responsible for printing the outputs.
* [`UiMessage`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiMessage.java): Responsible for storing output messages in methods so that they can be retrieved and printed when necessary.
    
The `UiMessage` class has **dependencies** with the following enumeration classes:

* **Rationale**: Increased coupling was sacrificed to reduce code duplicates and increase ease of code extension/editing.
* [`FitnessLevel`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/person/FitnessLevel.java): Descriptions of the five `FitnessLevel` are required in `UiMessage#getAskForUserInfoMessage(String name)` as shown in the code snippet below.
```             
+ "- Your fitness level, represented by a number from 1 to 5." + UiHelper.LINE_SEPARATOR
   + "  1 = " + FitnessLevel.NONE.getDescription() + UiHelper.LINE_SEPARATOR
   + "  2 = " + FitnessLevel.LOW.getDescription() + UiHelper.LINE_SEPARATOR
   + "  3 = " + FitnessLevel.MEDIUM.getDescription() + UiHelper.LINE_SEPARATOR
   + "  4 = " + FitnessLevel.HIGH.getDescription() + UiHelper.LINE_SEPARATOR
   + "  5 = " + FitnessLevel.EXTREME.getDescription() + UiHelper.LINE_SEPARATOR             
```
* [`Gender`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/person/Gender.java): Descriptions of the three `Gender` are required in `UiMessage#getAskForUserInfoMessage(String name)`as shown in the code snippet below.
```             
 "- Your gender either F for " + Gender.FEMALE.getDescription() + " or M for "
 + Gender.MALE.getDescription() + " or O for " + Gender.OTHERS.getDescription() + "."
```

In summary, the `UI` component,
* Takes in user command, ensure that it is not empty before passing it to the `Logic` component for command execution.
* Updates the user about any changes in the data after executing the command or errors encountered when executing the commands as instructed by the `Logic` component.

## Implementation

### Enter user information feature

#### Implementation

**This feature utilises two commands words**

* [`name`](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#entering-username-name): Saves the user's name or nickname into the application. 
* [`info`](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#entering-user-information-info): Saves the user's age, gender, height, fitness level, original, current and target weight into the application. 

**Main classes and methods used** 

* [`Manager`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/Manager.java): Stores a `Person` object.
    * `Manager#setPerson(String newName, Gender newGender, int newAge, int newHeight, int newOriginalWeight
    , int newCurrentWeight, int newTargetWeight, ActivityLevel newActivityLevel)`: Calls a method in `Person` class (listed below) to set the attribute values of the `Person` object.
* [`Person`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/person/Person.java): Stores all user information provided.
    * `Person#setAll(String newName, Gender newGender, int newAge, int newHeight, int newOriginalWeight, int newCurrentWeight, int newTargetWeight, ActivityLevel newActivityLevel)`: Updates the attribute values of the `Person` object.
                                             

**Example usage scenario and how the feature work**<br/>
_Summary_: Only one instance of `Person` is ever instantiated. A default person is instantiated at the start
 with default attribute values and when the user enters their information for the first time during the set up, all the default values would be updated to the inputted values. Therefore, the command to enter the user information will result in a change in the attribute values and not the creation of a new `Person` object.

Step 1. When the user launches the application for the first time. A default `Person` object will be initialised by `Manager` and the user will be prompted to enter their name.
 
![Enter Info Step1](diagrams/Enter Info Step1.png)
 
Step 2. The user executes `name Jack` command to enter their name into DietBook. The `name` command calls `Manager#setName(Jack)`, to store the name in `Manager` first. After which, user will be prompted to enter all other details.
  
![Enter Info Step2](diagrams/Enter Info Step2.png)
  
Step 3. The user executes a command like the following `info g/M a/21 h/175 o/85 c/85 t/75 l/2` to enter all other personal information including age, gender, height, activity level, original, current and target weight. The `info` command then calls `Parse#executeProcessedInfo(info g/M a/21 h/175 o/85 c/85 t/75 l/2, manager)` which would parse the user command, check input validity by using methods in `InputChecker` and calls `Manager#setPerson(Jack, Gender.MALE, 21, 175, 85, 85, 75, ActivityLevel.LOW)` which proceeds to call `Person#setAll(Jack, Gender.MALE, 21, 175, 85, 85, 75, ActivityLevel.LOW)`.

![Enter Info Step3](diagrams/Enter Info Step3.png)
   
The following sequence diagrams shows how the feature works.

`name` command

![Name sequence diagram](diagrams/Name sequence diagram.png)
   
`info` command

![Info sequence diagram](diagrams/Info sequence diagram.png)

#### Design considerations:

Aspect: Whether to enter name and other information separately or together

* **Alternative 1 (current choice)**: Enter name and other information separately
    * Pros: Increase user interaction and engagement.
    * Cons: Enter information using two commands.

* **Alternative 2**: Enter name and other information together
    * Pros: Enter all information at once.
    * Cons: Decrease user interaction and engagement.

Aspect: Whether to use singleton pattern for Person class

* **Alternative 1 (current choice)**: Did not use singleton pattern for `Person`
    * Pros: Reduce coupling and increase testability.
    * Cons: Risk of creating multiple `Person` object by mistake and there might be negative consequence in creating multiple objects.
    
    However, there  is minimal risk of creating multiple `Person` object by mistake and minimal negative consequence in creating multiple objects as long as the `Manager` refers the correct instance of `Person`.
      
* **Alternative 2**: Use singleton pattern for `Person`    
    * Pros: Easy to implement, prevent the instantiation of more than one `Person` object.
    * Cons: Increase coupling and reduce testability

Aspect: Changing attribute values in `Person` object or creating new `Person` object

* **Alternative 1 (current choice)**: Changing attribute values in `Person` object 
    * Pros: Reduce the number of objects being created to reduce memory usage and reduce the risk of creating multiple objects which can potentially lead to negative consequences and bugs.
    * Cons: Unable to write tests as method chains.

* **Alternative 2**: Creating new `Person` object
    * Pros: Ability to write tests as method chains.
    * Cons: Creation of many objects, which take up memory spaces and ensure that only the correct `Person` instance is kept and referred to.
    
## Save/Load Feature

The Save/Load feature is implemented by the saveload package.
At the base of the package, there is the <mark> Saver </mark> 
and <mark> Loader </mark>  class.

### Architecture
![Alt text](save_load_feature/Architecture.png)
Note only the Saver and Loader class is flexible. They can be adapted to new situations without modifying
the code. The FoodSaveLoadManager and PersonSaveLoadManager are written specifically for this version. They
will have to be modified/replaced for future versions.

#### Saver class

Stores data in a internal table with length and height specified.
Handles the storage of its data by writing to a text file.

##### Constructor
Specifies the length and height of the internal Saver table
##### Main Methods
* Saver#save() saves the current data to the file in the folder with the given file name
* Saver#add() Store String data in the x,y position in the table

#### Loader class
Loads data from a text file and stores it in a internal table just like the saver
##### Constructor
static method Loader.load(folder name , file name) : creates a Loader object with 
a table storing the data found in the text file
##### Main Methods
* Loader#get() retrives the data stored in the loader

#### FoodSaveLoadManager class
Built on top of Saver and Loader class to implement save/load functionality
for list of food items the user has input into the dietbook. Contains a instance
of both <mark> Saver </mark> and <mark> Loader </mark>. It has its own folder to work with,
the user only has to specify the file name.
##### Main Methods
* FoodSaveLoadManager#save() saves the list of food objects to the specified file name
* FoodSaveLoadManager#load() loads the file and returns the list of food objects stored inside it

#### PersonSaveLoadManager class
Built on top of Saver and Loader class to implement save/load functionality for user information
Same as FoodSaveLoadManager, it has its own folder to work with, the user only has to specify the file name
Unlike the FoodSaveLoadManager, it stores the data inside itself and can be updated.
##### Main Methods
* PersonSaveLoadManager#save() save the current state into the file
* PersonSaveLoadManager#load() loads the file 
* Setters and Getters for all the personal data in this current version

#### UML diaghram
##### FoodSaveLoadManager#save()
![Alt text](save_load_feature/FoodSaveLoadManager_save.png)
##### FoodSaveLoadManager#load()
![Alt text](save_load_feature/FoodSaveLoadManager_load.png)
similiar diaghrams for PersonSaveLoadManager

## Product scope
### Target user profile

NUS students living on campus who would like to track their daily food and nutritional intake.

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

1. Should work on any mainstream OS as long as `Java 11` is installed in the system.

## Glossary

* *Mainstream OD* - Windows, Linux, Unix, OS-X
* *Food items* - Includes both food and drinks 

## Instructions for manual testing
