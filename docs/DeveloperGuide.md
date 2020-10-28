# Developer Guide

## Table of Contents
* [Setting up](#setting-up)
  * [Prerequisites](#prerequisites)
  * [Get Started](#get-started)
* [Design](#design) 
  * [Architecture](#architecture) 
  * [UI Component](#ui-component)
  * [Logic Component](#logic-component)
* [Implementation](#implementation)
  * [Model Component](#model-component)
  * [Command Component](#command-component)
  * [Storage Component](#storage-component)
  * [Parser Component](#parser-component)
* [Implementation](#implementation) 

## **Introduction**

SmartHomeBot is a desktop application tailored for users who are comfortable using a Command Line Interface (CLI), it allows users to control their smart devices at home and record all appliance power usage.

## **Setting up**

### Prerequisites

+ *JDK `11`* or above
+ Permission rights to create a file and folder in machine
+ At least 10Mb of free space on disk

### **Get Started**

Fork this repo and clone it into your computer.

Intellij IDEA User (highly recommended):
1. Configure the JDK to JDK 11.
2. Make sure you have enabled Grade extension, if disable please re-enable it back by going to `File`>`Settings`>`Plugins`.
3. Import the project as a Gradle project by selecting *build.gradle*
4. Verify the setup:
    Run the seedu.smarthomebot.Main and try a few commands.
    Run the Tests to ensure they all pass.
- - -

## Design

### Architecture

![Architecture](images/diagrams/ArchitectureDiagram.JPG)

The *Architecture Diagram* shown above explains the high-level design of the SmortHomeBot Application. Given below is a brief overview of each component.

`Main` is responsible for initializing other components in correct sequence, and connects them up with each other.

`Commons` represents a collection of classes used by multiple components. 

The rest of the App consists of four components.
* `Ui`: The user interface where user can enter instructions and view output.
* `Logic` The command executor which consists of,
   * `Paser`: Extract the keyword from user input
   * `Commands`: Execute the specific command according to the keyword
* `Model` Holds the data in-app-memory while program is running. Consists of ...

* `Storage` Reads and writes data from and to a text file.


### Model Component
![Model Component](images/diagrams/ClassDiagram_DataOverview.png)

 
## Implementation

### Data Component
### Detailed Model Component 
The *Model Component* shown above explains the summarised model of SmartHomeBot. The four appliances classes are extended
from the abstract appliance class. 

![Detailed Model Component](images/diagrams/ClassDiagram_DetailedData.png)

To create an Appliance, we pass the name, location, wattage, power and the entire locationList. We first check from the 
locationList if the name of the appliance is inside the locationList, and the location exists within the locationList. 
If all these conditions are met, the Appliance will be created. It will be appended into the ApplianceList with the 
`addAppliance()` method. 

The Power class is responsible for computing the appliance’s power consumption.

The public methods in the Appliances accessible via the ApplianceList are used to manipulate the appliances such as
turning `ON` and `OFF`. From the appliance class, the program will be able to retrieving the name, location, statuses of
the appliances. 

### Command Component

#### Create Command
![Sequence of Create Command](images/diagrams/Sequence_CreateCommand.png) <br>
The *CreateCommand* shown above explains the Sequence Diagram of the Create Command.
#### Remove Command 
![Sequence of Remove Command](images/diagrams/Sequence_RemoveCommand.png) <br>
The *RemoveCommand* shown above explains the Sequence Diagram of the Remove Command.
#### Add Command
![Sequence of Add Command](images/diagrams/Sequence_AddCommand.png) <br>
The *AddCommand* shown above explains the Sequence Diagram of the Add Command.
#### Delete Command
![Sequence of Delete Command](images/diagrams/Sequence_DeleteCommand.png) <br>
The *DeleteCommand* shown above explains the Sequence Diagram of the Delete Command.
#### On Command

The *OnCommand* shown below explains the Sequence Diagram of the On Command. When the Main class calls the execute() function there will be are 2 cases for on command to flow:
1. OnByLocation
2. OnByAppliance

![Sequence of On Command](images/diagrams/Sequence_OnCommand.png) <br>

The program will determine if the user inputted: key is a name of an appliance, or a location.THis is done by checking the key in the LocationList, if it exists, the program will deem it as 'OnByLocation' vice versa.

##### 1. OnByLocation
The first condition checks if the user types in any parameter into the command, if so, the program will be unable to turn on
the appliances in the location. This is done as not all parameters are accepted by every appliance, for example,
setTemperature is only accepted for Air Conditioner. Thus, the parameter option is only opened to the OnByAppliance method.
Then, onByApplianceLoop will be called to turn on all appliances in the location.

##### 2. OnByAppliance
The first condition checks the index of the tagged appliance in the applianceList. If the index is negative, this signifies
that that key does not exist in the location or appliance list. Else, we will call the onAppliance method.

##### CommandResult
Each condition will correspond to a reciprocal CommandResult printout.


#### Off Command

The *OffCommand* shown below explains the Sequence Diagram of the Off Command. When the Main class calls the `execute()` function there are 2 cases for off command:
1. OnByLocation
2. OnByAppliance

![Sequence of Off Command](images/diagrams/Sequence_OffCommand.png) <br>

The program will determine if the user inputted: key is a name of an appliance or a location. This is done by checking the key in the LocationList, if it exists, the program will deem it as 'OffByLocation' vice versa.

##### 1. OffByLocation
This method will call offByApplianceLoop which turns off every appliance in that location.

##### 2. OffByAppliance
The first condition checks the index of the tagged appliance in the applianceList. If the index is negative, this signifies that that key does not exist in the location or appliance list. Else, we will call the offAppliance method.

##### CommandResult
Each condition will correspond to a reciprocal CommandResult printout.


#### Invalid Command
![Sequence of Invalid Command](images/diagrams/Sequence_InvalidCommand.png) <br>
The *InvalidCommand* shown above explains the Sequence Diagram of the Invalid Command.
#### Help Command
![Sequence of Help Command](images/diagrams/Sequence_HelpCommand.png) <br>
The *HelpCommand* shown above explains the Sequence Diagram of the Help Command.
#### Exit Command
![Sequence of Off Command](images/diagrams/Sequence_ExitCommand.png) <br>
The *ExitCommand* shown above explains the Sequence Diagram of the Exit Command.
#### CommandResult Command
![Sequence of CommandResult Command](images/diagrams/Sequence_CommandResult.png) <br>
The *CommandResult* shown above explains the Sequence Diagram of the CommandResult.

### Storage Component

#### Storage Module
The Storage module manages the load and save operations of the SmartHomeBot. It consist of two different classes, 
the ReadStorageFile class, and the WriteToFile class. As of the name stated, the function of WriteStorageFile class is 
to store the data into a .txt file for future usage. The ReadStorageFile class is to load the previously stored data 
from the .txt file back into the SmartHomeBot when it start up. 

The class diagram for storage module is shown below:

![StorageFile Model Component](images/diagrams/ClassDiagram_Storage.png)

##### Process of writing data into Storage File
After each command input by the user, SmartHomeBot will perform the execute() method in SaveStorageFile. This allows
the program to continuously save the changes made by the user into the .txt file and prevent the lost of updates made 
by the user.

Within the execute() method, the createFile() method will be performed to create a new .txt file if there is no .txt 
file exist. Then the clearFile() method will be performed to empty .txt file. After that all the data updated by the 
user will be written into the .txt file. This process will be carried out each time the user input a command. The first
line in the .txt file will always be the LocationList and subsequent lines will be all the appliances, each occupying 
one line each. The appliances will be written together with their parameters in each line. 

The sequence diagram for WriteStorageFile is shown below:

![StorageFile Model Component](images/diagrams/Sequence_WriteStorageFile.png)

##### Process of retrieving data back into the program
On startup, SmartHomeBot will perform the execute() method in ReadStorageFile. This allows the program to retrieve all 
stored data saved previously from the .txt file back into the program to be used again.

Within the execute() method, a Scanner object will be created to parse individual lines in the .txt file back into the 
program and converting them into String. The first line parsed into the program will be the locationlist, which will 
then be parse into the readToLocationList(locations) method. In the readToLocationList method, the locations will be 
added back into the locationList one by one. Then the program will return back to execute(). 

The second line onwards in the .txt file will be the appliances. These appliances will be converted into String and parse into the 
readToApplianceList method. In the readToApplianceList(appliance) method, each line will be split up into their 
specific parameters(name,location, power, type, powerConsumption and parameter). Then the appliance will be converted 
into static (Fan, AirConditioner, Lights, SmartPlug) depending on their types. Then the appliance will be added back to
the applianceList. The powerConsumption of each appliance will also be updated to keep track of the power Usage of each 
appliance.

The sequence diagram for ReadStorageFile is shown below:

![StorageFile Model Component](images/diagrams/Sequence_ReadStorageFile.png)

### Parser Component

This section will describe how the Parser feature is implemented as well as the design consideration and rational. 

The main purpose of the Parser Class is to interpret the user inputs so that the correct command can be executed.  

Sequence Diagram when `parseCommand(userCommandText)` is initially called. <br>
![Parser Model Component](images/diagrams/Sequence_MainParser.png)

The `userCommandText` is first capture by TextUi and then passed into parseCommand(userCommandText) method in Parser.
As shown in the Sequence Diagram above, different userCommandText will result in different paths being taken. 

Below is the list of commands available. 
* Help: `help`
* Create a location: `create`
* Remove a location: `remove`
* Add an appliance: `add`
* Delete an appliance: `delete`
* Switch ON an appliance: `on`
* Switch OFF an appliance: `off`
* Listing appliance/location: `list`
* Displaying usage of appliance: `usage`
* Resetting usage of appliance: `p_reset`
* Exiting the application: `exit`
 	
Sequence Diagram for `create`

![Parser Model Component](images/diagrams/Sequence_Parser_Create.png)

Sequence Diagram for `remove`

![Parser Model Component](images/diagrams/Sequence_Parser_Remove.png)

Sequence Diagram for `add`

![Parser Model Component](images/diagrams/Sequence_Parser_Add.png)

Sequence Diagram for `delete`

![Parser Model Component](images/diagrams/Sequence_Parser_Delete.png)

Sequence Diagram for `on`

![Parser Model Component](images/diagrams/Sequence_Parser_On.png)

Sequence Diagram for `off`

![Parser Model Component](images/diagrams/Sequence_Parser_Off.png) 

Sequence Diagram for `list`

![Parser Model Component](images/diagrams/Sequence_Parser_List.png)

Sequence Diagram for `commandword`

![Parser Model Component](images/diagrams/Sequence_Parser_Commandword.png)

`commandword` refers to the following commands `help`, `usage`, `p_reset`, `exit`.
As these 4 commands does not require any additional parsing. The sequence diagram referred above will return their
respective CommandObject to execute the command. 



Sequence Diagram for `default`

![Parser Model Component](images/diagrams/Sequence_Parser_Default.png)



## Product scope

### Target user profile

Disabled Home Users who want to automate/monitor their home with smart home devices.

### Value proposition

This program consolidates all the home appliance’s control into a centralised system. Users can also review and 
monitor electricity usage; having a clearer picture of their electrical usage patterns. We can extract the latest 
electricity price to calculate users’ utility bills. A backlog of the usage can also be recorded. 

## User Stories

|Version| As a(n) ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Inexperienced user|list all the functions of the app |To see what he can do. |
|v1.0|New user |add new locations into SmartHomeBot|Tag these locations into appliances |
|v1.0|New user |add new appliances|Manipulate and view his appliances usage|
|v1.0|disabled user |On/Off appliances|Turn on and off appliances without moving much|
|v1.0|Lazy user|list all the appliances in SmartHomeBot|Remove unnecessary locations and appliances|
|v1.0|Calculative user |View usage of his appliances|View all the appliances and see their status|
|v2.0|Lazy user|Change the temperature of the air-conditioner and the speed of the fan from SmartHomeBot|Monitor his smart home appliances|
|v2.0|Experienced SmartHomeBot user|On/OFF appliances by location and list appliances by location|Easily view and manipulate appliances by location|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
