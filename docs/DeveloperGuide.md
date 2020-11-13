# Developer Guide

![](CCAManager_logo.png)

# Table of Contents

- [1. Introduction](#1-introduction)
- [2. Setting up](#2-setting-up)
- [3. Design and Implementation](#3-design-and-implementation)
  * [3.1. Input Parsing](#31-input-parsing)
  * [3.2. Commands](#32-commands)
  * [3.3. Finance](#33-finance)
  * [3.4. Event](#34-event)
  * [3.5. HR](#35-hr)
  * [3.6. Storage](#36-storage)
- [4. Product Scope](#4-product-scope)
  * [4.1. Target user profile](#41-target-user-profile)
  * [4.2. Value proposition](#42-value-proposition)
- [5. User Stories](#5-user-stories)
- [6. Non-Functional Requirements](#6-non-functional-requirements)
- [7. Glossary](#7-glossary)
- [8. Instructions for manual testing](#8-instructions-for-manual-testing)


## 1. Introduction 
CCA Manager is a revolutionary all-in-one management tool that changes the way you can manage interest groups with unrivaled efficiency and simplicity. Its lightweight Command Line Interface (CLI) allows administrators to breeze through tasks quickly and easily while offering powerful features to advanced users.

This developer guide is written to document the implementation of CCA Manager. This document is intended for people who
are interested in learning more about the technical details of the various features and the organization of the application.

You can also find CCA Manager's user guide [here](https://ay2021s1-cs2113t-f14-1.github.io/tp/UserGuide.html)

## 2. Setting up
For running the software release, refer to the guide [here](https://github.com/AY2021S1-CS2113T-F14-1/tp/blob/master/README.md).

To set up the project for development. Follow the following steps:

* Ensure that Java 11 or higher is installed on the development machine.
* Download the source code from the CCA Manager repository [here](https://github.com/AY2021S1-CS2113T-F14-1/tp).
* You may choose to set up an IDE to facilitate easy development of the project. The team uses [Intellij IDEA](https://www.jetbrains.com/idea/) for developing the project. Other IDEs may be used, but have not been verified to work.
* Import the project folder into your IDE.

## 3. Design and Implementation
This section seeks to explain the high-level design of the application. Given below is a quick overview of each component and the explanation of the design architecture in greater detail. 
Diagrams found in our documentation were generated using PlantUML, in compliance to the UML standards defined in the module requirements.

### 3.1. Input Parsing
![Parser](BackendDiagram/ParserFlow.png)

Input parsing describes the process of converting the user's input into an executable command. The diagram above shows the execution flow required to run a single command.

The `Parser` is responsible for the input conversion to a `UserInput` object. Subsequently, we use `validate()` in a loop to identify the command to execute, then we execute the command action with `execute()`.

**3.1.1 Current Implementation**  
The `Parser` class in `seedu.duke.backend` handles most of the input parsing. The `Parser` is a standalone class. Its purpose is to handle the conversion of read Strings from the `Ui` to UserInput objects
safely for the rest of the program to handle. It implements the following operations:

* `Parser#parse()` - Converts the supplied input `String` to a `UserInput` object
* `Parser#checkCategory()` - Convert the supplied `String` to a `String` category. This function implements Shorthand category detection.  
Shorthand Commands is a feature which aims to reduce the amount of typing required to execute commands to improve efficiency. In this case, the key word for a category such as `events` can be shortened to `e`.
* `Parser#sanitize()` - Check for unsupported, illegal or potentially malicious input and remove it from the `String`.  
For example the EICAR Test String `X5O!P%@AP[4\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*` can cause data loss by tricking antivirus software into quarantining data files.

Given below is an explanation on the logical flow of the `parse()` function.

1. The supplied `String` is sent to `sanitize()` to remove any potentially unwanted input that may cause undefined application behavior.
2. `sanitize()` will analyze the `String` and run a set list of checks to verify that the input is safe for the rest of the routine to handle.
3. `parse()` will split the `String` to a `String[]` to identify the number of arguments.
4. `checkCategory()` is invoked to identify if the command belongs to a specific category.
5. A `HashMap<String,String>` is created to store all the arguments supplied by the user.
6. A new `UserInput` object is created with the arguments in the HashMap, the category of the command and the command.
7. The function returns the `UserInput` to the `Ui` for further execution.

**3.1.2 Design Considerations**  
Aspect: Statefulness of Parser object
* Alternative 1 (Current Choice): Parser does not preserve state between parses.
    * Pros: Easy to implement. Easy to test. Promotes use of single commands over multi-step commands.
    * Cons: Unable to implement intelligent functionality where previous input influences the behavior of the next.
    * Reason for choice: Our focus for this application is simplicity and efficiency. Having stateful commands runs counter to this design philosophy.
      
* Alternative 2: Parser preserves stateful information
    * Pros: Able to implement multi-step commands. Can easily implement confirmation step for commands that manipulate large volume of data.
    * Cons: More complicated to implement. Harder to ensure the behaviour of the parser is consistent. Harder to debug.

Aspect: Design of parser
* Alternative 1 (Current Choice): Dedicated parser class creates an object to be passed into all other Command objects
    * Pros: Allows other classes to check for the required arguments without having to do low level string handling. Enforces consistent parsing across all commands. Enables `/` arguments to be added and read in any order.
    * Cons: Incurs additional overhead from adding an extra step between the input operation and the command invocation.
   
* Alternative 2: Each Command handles its own input independently
    * Pros: Command classes are free to simplify the parsing step depending on the required complexity of the command. No intermediate step and overhead.
    * Cons: More difficult to enforce parsing standards across Commands. String manipulation becomes required in every command.

[Return to top](#Developer-guide)

### 3.2. Commands
**3.2.1 Current Implementation**  
The abstract `Command` class in `seedu.duke` defines how the rest of the commands interact with the `UI` and `UserInput` objects.
Its purpose is to ensure that all commands conform to the same design and coding standards to be compatible with the `UI` layer while also being
sufficiently flexible to allow for complex commands to be created. It specifies the following *abstract* methods:

* `Command#help()` - Allows commands to specify a default help `String` to be displayed if the argument supplied is incorrect.
* `Command#validate()` - Checks if the supplied `UserInput` was intended for this command and validates if the supplied arguments are correct. This is akin to knocking the doors of houses on a street to look for an individual.
* `Command#execute()` - Performs the command action. This is only run if `validate()` returns `ACCEPT`.

Given below is the logical flow of the `Command` input to execution flow:

1. The `Ui` reads the user input.
2. The `Ui` calls the `Parser` to parse the input
3. The `Parser` returns the `UserInput` to the `Ui`
4. The `Ui` checks through the list of available commands and runs `validate()` on each of them until one command returns either `ARGUMENT_ERR` or `ACCEPT`
    * If the `Ui` receives an `ARGUMENT_ERR`, it calls the `help()` function of that command and prints the `String` to the `Ui`.
    * If the `Ui` receives an `ACCEPT`, it proceeds with the execution flow from 5.
    * If the `Ui` receives no `ACCEPT`s or `ARGUMENT_ERR` after going through all commands, the `Ui` prints a list of available commands.
5. The `Ui` calls the `execute()` method of the command that `ACCEPT` the `UserInput`.
6. The `Ui` prints the output String returned from the `execute()` method.

**3.2.2 Design Considerations**    
Aspect: The need to instantiate a `Command`
* Alternative 1 (Current Choice): Classes that inherit `Command` are instantiated on `UI` initialization.
    * Pros: Easy to implement. Less overhead from executing commands. Locality of the code allows for minimal merge conflicts when developing collaboratively.
    * Cons: Requires more memory at load to hold all the objects.
    * Reason for choice: Since we do not have a stateful parser, this option was chosen as the simplest implementation that gets the job done.
    
* Alternative 2: `Command`s only contain static methods
    * Pros: Conceptually more sensible than having exactly one instance of each command.
    * Cons: More complicated to implement, java has no elegant simple way to exploit inheritance and static functions in a list of classes making this option unpractical without implementing a bunch of hacks.

Aspect: Design of `Command` criteria checking
* Alternative 1 (Current Choice): Each class is free to specify its own matching patterns and criterion.
    * Pros: Allows for more complex criteria evaluation without having a dedicated class for resolving commands. Makes good use of abstraction and inheritance and puts all the `Command` related functions in the same class.
    * Cons: Searching of the command list is `O(n)` but the individual validation functions may not be `O(1)`, resulting in higher potential overhead if validation functions are not optimized.
    * Reason for choice: We wanted development of command related functions to all be housed in the same class. This design achieves that goal while giving us a great deal of flexibility.
    
* Alternative 2: Dedicated class for command resolution and validation
    * Pros: Further separates the job of command resolution from the `Ui` and `Command`. Simplifies `Command` class.
    * Cons: Would be a class which features a very un-elegant large `if-else` block or `switch` block. Requires every new command to update this class with a substantial amount of new lines. Harder to develop collaboratively, increases chances of merge conflicts.

**3.2.3 Creating a Command**  
This subsection demonstrates how simple it is to create a new command in the application.

1. Create a new command class. The class should extend the `Command` class in `seedu.duke.`  
![](BackendDiagram/command1.png)
2. Override 3 functions, `execute()`, `validate(UserInput)` and `help()`.
3. In `validate()`, write code to check if the `UserInput` object is intended for your command.  
![](BackendDiagram/command2.png)
    1. Check if the category is for your command’s category (Red box in image above)
    2. Check if the command matches the name of your command (Blue box in image above)
    3. Optionally you can check the number of arguments if your command accepts arguments.  
    The figure below shows how to check for the minimum number of arguments  
    ![](BackendDiagram/command3.png)  
    The figure below shows how to check if an argument exists and is supplied by the user  
    ![](BackendDiagram/command4.png)
    4. Validate should return `ACCEPT`, `NO_MATCH` or `ARGUMENT_ERR`. Refer to the function javadoc for the meaning of each value, shown below.  
    ![](BackendDiagram/command5.png)  
    5. You may want to save additional information like the `UserInput` object to a local variable as it is not supplied directly to the `execute()` function.  
4. Set `help()` to return some useful information when the user enters the command syntax incorrectly.  
![](BackendDiagram/command6.png)
5. Write the command working code in `execute()`. It should return a message to be displayed to the user.  
![](BackendDiagram/command7.png)
6. Add your command to the `initializeCommands()` section of `seedu.duke.backend.Ui`  
![](BackendDiagram/command8.png)
7. If all steps were completed correctly, you should be able to use your new command after compiling and running the program.

[Return to top](#Developer-guide)

### 3.3. Finance  
(by: Wang Zixin)  
The diagram below shows the architecture of Finance feature:  
![Architecture of Finance](financeDiagramPic/Architecture.png)  

**3.3.1. Add/delete finance log entry feature**  
**3.3.1.1. Current Implementation**  
The `CommandFinanceAdd` class in `seedu.duke.finance` handles adding finance log entry. It adds a new `FinanceLog` instance according to `userInput` into `FinanceList`.  
The `CommandFinanceDel` in the same package handles deleting finance log entry. It deletes a certain `FinanceLog` instance according to the index provided by `userInput` from `FinanceList`.  
They implement the following operations:  
* `CommandFinanceAdd#execute()` - Adds a new finance log entry into the `FinanceList` according to `userInput`.  
* `CommandFinanceDel#execute()` - Deletes a certain finance log entry from `FinanceList` according to the index provided by `userInput`.  

Given below is an example usage scenario and how the add/delete finance log entry behaves at each step.  

Step 1. The user launches the application for the first time. The `FinanceList` will be initialized with no `FinanceLog` in it.  

![](financeDiagramPic/1-1S1.png)

Step 2. The user executes `finance addLog iphone12 1299` command to add a finance log entry with content "iphone12" and value "1299" into finance list. The `finance addLog` command
calls `CommandFinanceAdd#execute()`, then `FinanceList` will be added a `FinanceLog` with its `finLog` as `iphone12` and its value as `1299`.  

![](financeDiagramPic/1-1S2.png)

Step 3. The user executes `finance delLog 1` command to delete the 1st finance log entry in the finance list. The `finance delLog`
command calls `CommandFinanceDel#execute()`, causing the `FinanceLog` of index 1 removed from `FinanceList`.  

![](financeDiagramPic/1-1S3.png)

The sequence diagram for adding a finance log entry is shown below:  

![](financeDiagramPic/CommandFinanceAdd.png)  

The sequence diagram for deleting a finance log entry is shown below:  

![](financeDiagramPic/CommandFinanceDel.png)  

**3.3.1.2. Design Considerations**  
Aspect: User input format for adding a finance log entry  
* Alternative 1(Current Choice): The user inputs command in format `finance addLog ITEM_NAME ITEM_VALUE`.  
    * Pros: It is more convenient for the user to type commands and easier to memorize the command format.  
    * Cons: It takes longer time to execute the command for the program has to identify which part is `ITEM_NAME` and which part is
    `ITEM_VALUE`. If the user inputs a separate number for `ITEM_NAME` but forgets to type `ITEM_VALUE`, then the program will mistake 
    the separate number in `ITEM_NAME` for its `ITEM_VALUE`. For example, if the user just input `finance summary iphone 12` but forgot to
    type the price, then the finance log entry will become `iphone $12`.    
    
* Alternative 2: The user inputs command in format `finance addLog /n ITEM_VALUE /v ITEM_VALUE`.  
    * Pros: The program can easily detect if the input command is valid.  
    * Cons: It is harder for the user to memorize the command format. It also costs more time when executing.  
    

**3.3.2. List the summary of finance log entries**  
**3.3.2.1. Current Implementation**  
The `CommandFinanceSummary` class in `seedu.duke.finance` handles listing all the finance log entries in `FinanceList` and 
showing the total budget of all the `FinanceLog`.  
It implements the following operation:  
* `CommandFinanceSummary#execute()` - Lists all `FinanceLog` in `FinanceList` and shows the total budget of them.  

Given below is an example usage scenario and how the program list the summary of finance log entries.  

Step 1. After some `finance addLog` commands, the user created a `FinanceList` with two `FinanceLog`. The first `FinanceLog` is 
"iphone12 $1299" and the second `FinanceLog` is "chicken rice $3.5".  

![](financeDiagramPic/1-2S1.png)

Step 2. The user executes `finance summary` command to list the summary of `FinanceList`. The `finance summary` command calls 
`CommandFinanceSummary#execute()`, then every `FinanceLog` in `FinanceList` will be output and the total budget will be printed out at the bottom. Nothing will be changed in `FinanceList`.  

![](financeDiagramPic/1-2S2.png)  

The sequence diagram of listing summary of finance log entries is shown below:  

![](financeDiagramPic/CommandFinanceSummary.png)  


**3.3.2.2. Design Considerations**  
Aspect: Repeated items  
* Alternative 1(Current Choice): The summary will output all the repeated items.  
    * Pros: It can display all the indexes of the repeated items so that when user wants to delete any one of them, 
    he can just refer to this summary.  
    * Cons: It cannot display the total budget for these repeated items. The user has to find a way to calculate it 
    by himself.  
    
* Alternative 2: The summary will combine all the repeated items then output them.  
    * Pros: The user do not have to calculate the total budget for repeated items by himself.  
    * Cons: The summary cannot show each index of the repeated items that it is confusing when user wants to delete 
    any one of them.  
    

**3.3.3. Change the information of a finance log entry**  
**3.3.3.1. Current Implementation**
The `CommandFinanceChange` class in `seedu.duke.finance` handles changing a particular `FinanceLog`'s `finLog` and 
`finLogVal` in `FinanceList` whose index is provided by the user.  
It implements the following operation:  
* `CommandFinanceChange#execute()` - Change the `FinanceLog`'s `finLog` and `finLogVal` whose index is provided by the user.  

Given below is an example usage scenario and how the program change the information of a `FinanceLog`.  

Step 1. After some `finance addLog` commands, the user created a `FinanceList` with two `FinanceLog`. The first is 
"iphone12 $1299", the second is "rent room $40".  

![](financeDiagramPic/1-3S1.png)  

Step 2. The user executes `finance changeLog /i 2 /n rent field 50` to change the second `FinanceLog`'s information. 
The `finance changeLog` command calls `CommandFinanceChnage#execute()`, then the second `FinanceLog`'s `finLog` is changed 
to "rent field" and its `finLogVal` is changed to "$50".  

![](financeDiagramPic/1-3S2.png)  


**3.3.3.2. Design Considerations**  
Aspect: User input format  
* Alternative 1(Current Choice): It changes both `finLog` and `finLogVal` together at the same time.  
    * Pros: The user does not need to remember two different command formats and the current format can increase the 
    efficiency of the program.  
    * Cons: Every time the user has to type in both `ITEM_NAME` and `ITEM_VALUE`, it may waste some time for the user.  
    
* Alternative 2: Split the command into changeName and changeNum.  
    * Pros: The user can choose whether just change only `finLog` or `finLogVal` and it is easier to debug.  
    * Cons: If the user want to change both `finLog` and `finLogVal`, it will waste more time on typing commands. Also, 
    it takes longer time to execute the commands, including others.  


The sequence diagram of changing information of a finance log entry is shown below:  

![](financeDiagramPic/CommandFinanceChange.png)


[Return to top](#Developer-guide)

### 3.4. Event

(By: Varsha)<br/>

The diagram below shows the overall architecture for `Event` feature. <br/>


![](EventDiagram/EventSteps/eventArchi.png)



There are a total of 9 commands under `Event` feature.
 `CommandEventAdd`, `CommandEventDel`, `CommandEventList`  ,`CommandEventStatus`, `CommandSearchEvent` , `CommandEventCountdown` , `CommandAddEventAttendance`,`CommandDelEventAttendance`, `CommandViewEventAttendence`. 

 The implementation for each `Event` command is described in detail below.
                                                             
**3.4.1. Add/delete events feature** `CommandEventAdd` , `CommandEventDel` 

(By: Varsha)<br/>
**3.4.1.1. Current Implementation** <br/>

The `CommandEventAdd` class in `seedu.duke.event` handles the adding of events. According to the `userInput`, it adds a new event to the `EventList`. 
The `CommandEventDel` class in the same package handles deleting of a event. It deletes an `Event` instance according to the index provided by `userInput` from the `EventList`.  
They implement the following operations:  
* `CommandEventAdd#execute()` - Adds a new `Event` into the `EventList` according to `userInput`.  
* `CommandEventDel#execute()` - Deletes an `Event` from `EventList` or deletes all the events in the list. 
 
Note: To delete a particular event, enter the index of the event. For example, `event delEvent 2` <br/>
Note: To delete all the events in the list, enter `all` instead of the index of the event. For example, `event delEvent all` <br/>
Note: When a new event is added, if the event name and date matches to an existing event in the list, it is considered a duplicate event. It will not be added
      to the event list. <br/>

Given below is an example usage scenario and how add/delete event function behaves at each step.  

Step 1. The user launches the application for the first time.   

![](EventDiagram/EventSteps/Step1.png)

Step 2. The user executes `event addEvent /n arduino course /d 2020-12-30 /t 18-00` command to add a new event with the name "arduino course", 
the date of the event "2020-12-30" and the time "18-00" into event list. 
The `event addEvent` command calls `CommandEventAdd#execute()`, then `EventList` will add a new `Event` with event name as `arduino course`, date as `2020-12-30` and time as `18-00`.  

![](EventDiagram/EventSteps/Step2.png)

Step 3. The user executes `event delEvent 1` command to delete the 1st event in the event list. The `event delEvent`
command calls `CommandEventDel#execute()`, causing the `Event` at index 1 to be removed from `EventList`.  

![](EventDiagram/EventSteps/Step3.png)

The sequence diagram for adding an event is as shown below:

![CommandEventAdd](EventDiagram/SequenceDiagram/CommandEventAdd.png)

The sequence diagram for deleting **a particular event** or **all events** is as shown below:

![CommandEventDelete](EventDiagram/SequenceDiagram/CommandEventDelete.png)

**3.4.1.2. Design Considerations** <br/>

Aspect : User input format for adding an event <br/>
* Alternative 1 (current choice) : The user will input the command in the format `event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME`. <br/>
    * Pros: Easy to detect if user input is valid for each parameter, `/n`,`/d`and`/t`. <br/>
    * Cons : It may be hard for the user to memorise the command format at the beginning. <br/>
    
* Alternative 2 : User input with the format `event addevent EVENT_NAME EVENT_DATE EVENT_TIME` <br/>
    * Pros: It is more convenient for the user to type commands and easier to memorise the command format. <br/>
    * Cons : It takes longer to execute the command as the program will take time to identify the respective parameters within the command entered. <br/>

[Return to top](#Developer-guide)

**3.4.2. Listing Events** `CommandEventList`

(By: Varsha)<br/>
**3.4.2.1 Current implementation**

The `CommandEventList` class in `seedu.duke.event` handles listing all the events in `EventList`.

It implements the following operation:  
* `CommandEventList#execute()` - Lists all `Event` in `EventList`.  

Given below is an example usage scenario and how the program list the events.  

Step 1. Assume there are 2 `Event` in the `EventList`.
The first `Event` has the name arduino course on 30 December 2020 at 18-00 and the second `Event` has the name Autodesk course on 25 May 2021 from 12-00.

![](EventDiagram/EventSteps/2Step1.png)

Step 2.The user executes `event listEvent` command to list the `EventList`. The `event listEvent` command calls 
`CommandEventList#execute()`, then every `Event` in `EventList` will be printed out. Nothing will be changed in `EventList`.  

![](EventDiagram/EventSteps/2Step2.png)

**3.4.2.2. Design Considerations** <br/>

Aspect: Repeated items  <br/>
* Alternative 1 (Current Choice): `event listEvent` command will only list unique events present in the list. It will not show repeated events.
When a new event is added, if the event name and date matches to an existing event in the list, it is considered a duplicate event. It will not be added
to the event list. <br/>
    * Pros : The resulting event list does not contain duplicates. The number of events in the list will be valid. <br/>
    * Cons :  Requires more methods to be written.
    
* Alternative 2 : Program accepts duplicated events and filters the duplicates for the user. <br/>
    * Pros: It can display all the indexes of the repeated items which user can refer to delete the duplicates. <br/>
    * Cons : The duplicate list is redundant to the user. <br/>

 
The sequence diagram for listing events is as shown below:

![](EventDiagram/SequenceDiagram/CommandEventList.png)

[Return to top](#Developer-guide)

**3.4.3. Searching for an event via name or date** `CommandSearchEvent`

(By: Varsha)<br/>
**Current Implementation**

The `CommandSearchEvent` class in `seedu.duke.event` handles searching of an event via its name or its date.

It implements the following operation:  
* `CommandSearchEvent#execute()` - Search all `Event` in `EventList` for the name or date entered by user.
 
 The sequence diagram for searching for an event is as shown below:

 ![](EventDiagram/SequenceDiagram/CommandSearchEvent.png)
 
**3.5.4.2. Design Considerations**  
Aspect: Search conditions  <br/>
* Alternative 1(Current choice): It will search for `Event` by the name or date entered.  
    * Pros: Its faster. If the name or date of the first `Event` in the does not match it skips to the next `Event` instead of checking other conditions.
    * Cons: If the user input contains some common strings like "and" or "the", there will be too many results shown to the user.
          
* Alternative 2: It will search for `Event` whose information matches all the conditions provided by user input.  
    * Pros: There will not be too many results when the user input includes common strings like "The" or "and".
    * Cons: Since it's a linear search, therefore, the bigger the data size, the longer the search, especially with more conditions to check.

[Return to top](#Developer-guide)
 
**3.4.4. Displaying countdown to upcoming events** `CommandEventCountdown`

(By: Varsha)<br/>
**Current Implementation**

The `CommandEventCountdown` class in `seedu.duke.event` handles displaying of countdown as an additional feature in the `EventList`.
 
It implements the following operation: <br/>
* `CommandEventCountdown#execute()` -  displays countdown feature for all upcoming `Event` in the `EventList`. It shows the number of days remaining to the respective event and sorts them such that the 
earliest upcoming events is first on the list. (Earliest Deadline First,EDF)

The sequence diagram for displaying countdown is as shown below:

![](EventDiagram/SequenceDiagram/CommandEventCountdown.png)

**3.5.4.2. Design Considerations**  
 Aspect: Format of countdown feature <br/> 
 * Alternative 1(Current choice): `event countdown` events are sorted such that the most upcoming events is displayed ahead of others. <br/>
    * Pros: Users can view the most urgent event easily. It lists out all the events with the countdown feature. <br/>
    * Cons: The more events added, the longer it will take to list the events. <br/>
 * Alternative 2: `event countdown EVENT_INDEX` Will only display the countdown for the event in the index given. <br/>
    * Pros: Faster, can easily retrieve the event from the list. <br/>
    * Cons: Less useful to user as compared to alternative 1, where the EDF algorithm is used to sort the list. <br/>
 
  

[Return to top](#Developer-guide)
 
**3.4.5. Mark an event as completed** `CommandEventStatus`

(By: Varsha)<br/>
**Current Implementation**

The `CommandEventStatus` class in `seedu.duke.event` handles marking of an event. It can manually mark an event as done.
 
It implements the following operation: <br/>
* `CommandEventStatus#execute()` -  Marks an `Event` in the `EventList` as done.

The sequence diagram for marking an event as done is as shown below:

![CommandEventStatus](EventDiagram/SequenceDiagram/CommandEventStatus.png)

[Return to top](#CCA-manager-developer-guide)

**3.4.6. Add/delete event participants feature** `CommandAddEventAttendance` , `CommandDelEventAttendance` 

(By: Ye Yutong)<br/>
**3.4.6.1. Current Implementation**  
The `CommandAddEventAttendance` class in `seedu.duke.event` handles the adding of event participants. According to the `userInput`, it adds a new participant to the specified event in the `EventList`. 
The `CommandDelEventAttendance` class in the same package handles deleting of an event participant. It deletes a `Member` instance from the event participants list of the specified `Event`.  
They implement the following operations:  
* `CommandAddEventAttendance#execute()` - Adds a new participant into the event participant list of the `Event`, according to `userInput`.  
* `CommandDelEventAttendance#execute()` - Deletes a participant from the event participant list of the `Event`, according to `userInput`.

Given below is an example usage scenario and how add/delete event participants function behaves at each step.  

Step 1. After a `event addEvent` command, the user has created a `EventList` with some `Event`. Assuming there is an `Event` in the list, 
with the name arduino course on 30 December 2020 at 18-00 and the second `Event` has the name Autodesk course on 25 May 2021 at 12-00.
        
![](EventDiagram/EventSteps/6Step1.png)

Step 2. After a `hr addMember` command, the user created a `MemberList` with some `Member`. Assuming there is 1 `Member` in the list, 
with the name "Harry Potter", phone number "88888888", email "qaz@gmail.com", role "president". <br/>
        
![](EventDiagram/EventSteps/6Step2.png)

Step 3. The user executes `event addAttendance /n arduino course /m harry potter` command to add a new participant with the name "Harry Potter" to the event with the name "arduino course", 
into eventParticipants list. 
The `event addAttendance` command calls `CommandAddEventAttendance#execute()`, then `EventList` will add a `Member` with the member name `Harry Potter` in the `MemberList`, to the `Event` with event name `arduino course` in the `EventList`.  

![](EventDiagram/EventSteps/6Step3.png)

Step 4. The user executes `event delAttendance /n arduino course /m harry potter` command to delete `Member` with the member name `Harry Potter` from the event participants list. 
The `event delAttendance` command calls `CommandDelEventAttendance#execute()`, causing the specified `Member` to be removed from the event participants list of the specified `Event`.  

![](EventDiagram/EventSteps/6Step4.png)

The sequence diagram for adding a participant into a particular event is as shown below:

![CommandAddEventAttendance](EventDiagram/SequenceDiagram/CommandAddEventAttendance.png)

The sequence diagram for deleting a participant from a particular event is as shown below:

![CommandDelEventAttendance](EventDiagram/SequenceDiagram/CommandDelEventAttendance.png)

**3.4.6.2. Design Considerations** <br/>  

Aspect: Delete participant attendance from an event  <br/>
* Alternative 1 (Current Choice): `event delAttendance` command will only delete member from each event by the member name. <br/>
    * Pros : The user can delete quickly if he is familiar with the name of the targeted participant. <br/>
    * Cons : The user needs to type in the full name of the participant in order to delete the person, might be less convenient if the user is not familiar with the names.
    
* Alternative 2 : `event delAttendance` command will only delete member from each event by the member's index in the participant list. <br/>
    * Pros : It is easier to implement.  
    * Cons : The user needs to view the participant list of the event first to view the index, hence requires more typing and less convenient. <br/>

[Return to top](#Developer-guide)

**3.4.7. Listing event participants** `CommandViewEventAttendance`

(By: Ye Yutong)<br/>
**3.4.7.1 Current implementation**  
The `CommandViewEventAttendance` class in `seedu.duke.event` handles listing all the participants of the given event in the event participants list.

It implements the following operation:  
* `CommandViewEventAttendance#execute()` - Lists all `Member` of the given `Event` the event participants list.  

Given below is an example usage scenario and how the program list the participants.  

Step 1. After a `event addEvent` commands, the user has created a `EventList` with a `Event`. 
        The`Event` has the name arduino course on 30 December 2020 at 18-00.
        
![](EventDiagram/EventSteps/7Step1.png)

Step 2. After some `hr addMember` commands, the user created a `MemberList` with some `Member`. Assuming there are 2 members in the list.
        The first `Member` has the name "John Sterling" with phone number "12345678", email "123@gmail.com", role "member".
        The second `Member` has the name "Harry Potter", phone number "88888888", email "qaz@gmail.com", role "president". <br/>
        
![](EventDiagram/EventSteps/7Step2.png)

Step 3. After some `event addAttendance` commands, the user created a `MemberList` with some `Member`. Assuming the 2 participants in the event participants list are the 2 members in the `MemberList`.
        
![](EventDiagram/EventSteps/7Step3.png)

Step 4.The user executes `event listAttendance` command to list the event participants list. The `event listAttendance` command calls 
`CommandViewEventAttendance#execute()`, then every `Member` in event participants list of the `Event` will be printed out. Nothing will be changed in the event participants list.  

![](EventDiagram/EventSteps/7Step4.png) 

The sequence diagram for listing participants in an event is as shown below:

![](EventDiagram/SequenceDiagram/CommandViewEventAttendance.png)

[Return to top](#Developer-guide)

### 3.5. HR
The diagram below shows the overall architecture for HR feature.<br/>

![](hrDiagramPic/HrArchi.png)

There are a total of 7 commands under HR feature:
 `CommandAddMember`, `CommandDelMember`, `CommandViewMember`  ,`CommandListConnection`, `CommandSearchMember` , `CommandListProfAdmin` and `CommandChangeMemberInfo`. 
 
The implementation for each command is described in detail below.

**3.5.1. Add/delete member feature**  

(By: Ye Yutong)<br/>
**3.5.1.1. Current Implementation**  
The add/delete member mechanism is facilitated by `CommandAddMember` and `CommandDelMember` classes. The 
`CommandAddMember` class in `seedu.duke.hr` handles adding members. It adds a new `Member` instance according to 
`userInput` into `MemberList`.  
The `CommandDelMember` class in the same package handles deleting members. It deletes a certain `Member` instance 
according to the index provided by `userInput` from `MemberList`.  
These two classes implement the following operations:  
* `CommandAddMember#execute()` - Adds a new member into the `MemberList` according to `userInput`.  
* `CommandDelMember#execute()` - Deletes a certain member from `MemberList` according to the index provided by 
`userInput`.  

Given below is an example usage scenario and how the add/delete member behaves at each step.  

Step 1. The user launches the application for the first time. The `MemberList` will be initialized with no `Member` in 
it.  

![](hrDiagramPic/2-1S1.png)

Step 2. The user executes `hr addMember /n john sterling /p 12345678 /e 123@gmail.com /r member` command to add a member
 with name "John Sterling", phone number "12345678", email "123@gmail.com" and role "member" into member list. The 
 `hr addMember` command calls `CommandAddMember#execute()`, which then calls `MemberList#findByName()`, 
 `MemberList#standardizeMemberName()` and `MemberList#addToList()`. `MemberList#findByName()` finds the `Member` in the 
 list by the given member name. `MemberList#standardizeMemberName()` standardize the member name input by the user by 
 capitalizing the first letter in each word in the name. Then, `MemberList#addToList()` adds a `Member` with its 
 `memberName` as `John Sterling`, `memberPhone` as `12345678`, `memberEmail` as `123@gmail.com`, and `memberRole` as 
 `member` into `MemberList`. 

The following shortcut commands can achieve the same result: <br/>
`hr add /n john sterling /p 12345678 /e 123@gmail.com /r member`<br/>
`hr a /n john sterling /p 12345678 /e 123@gmail.com /r member`<br/>

![](hrDiagramPic/2-1S2.png)

Step 3. The user executes `hr delMember 1` command to delete the member in the member list. The `hr delMember`
command calls `CommandDelMember#execute()`, causing the `Member` of index 1 removed from `MemberList`, and the same 
`Member` removed from the list of event participants of each `Event` in the `EventList`. 

 The following shortcut commands can achieve the same result: <br/>
 `hr delete 1`<br/>
 `hr d 1`<br/>

![](hrDiagramPic/2-1S3.png)

The sequence diagram for adding a member is as shown below:

![CommandAddMember](hrDiagramPic/CommandAddMember.png)

The sequence diagram for deleting a member is as shown below:

![CommandDelMember](hrDiagramPic/CommandDelMember.png)

The method `MemberList#updateAttendanceRate()` referenced in the above diagrams is as shown below:

![CommandDelMember](hrDiagramPic/UpdateAttendanceRate.png)

The method `MemberList#deleteFromEvents()` referenced in the above diagram is as shown below:

![CommandDelMember](hrDiagramPic/DeleteFromEvents.png)

Refer to section 3.4.6.1 for the sequence diagram of the method `EventList#deletAttendance()` referenced in the above diagram. 

[Return to top](#Developer-guide)

**3.5.2. List the members**  

(By: Ye Yutong)<br/>
**3.5.2.1. Current Implementation**  
The `CommandViewMember` class in `seedu.duke.hr` handles listing all the members in `MemberList` and 
showing the contacts and role information of all the `Member`.  
It implements the following operation:  
* `CommandViewMember#execute()` - Lists all `Member` in `MemberList` and shows their contacts and roles.  

Given below is an example usage scenario and how the program list the information of members.  

Step 1. After some `hr addMember` commands, the user created a `MemberList` with two `Member`. <br/>
The first `Member` is "John Sterling" with phone number "12345678", email "123@gmail.com", role "member". <br/>
The second `Member` is "Harry Potter", phone number "88888888", email "qaz@gmail.com", role "president". <br/>

![](hrDiagramPic/2-2S1.png)

Step 2. The user executes `hr listMember` command to list the summary of `MemberList`. The `hr listMember` command calls 
`CommandViewMember#execute()`, then every `Member` in `MemberList` and the contacts and roles will be printed out within
 the same line, separated by "|". Nothing will be changed in `MemberList`.  
 
 The following shortcut commands can achieve the same result: <br/>
 `hr list`<br/>
 `hr l`<br/>
 
 The sequence diagram for listing the members is as shown below:
 
 ![CommandViewMember](hrDiagramPic/CommandViewMember.png)
 
[Return to top](#Developer-guide)

**3.5.3. Change member information**  

(By: Ye Yutong)<br/>
**3.5.3.1. Current Implementation**  
The `CommandChangeInfo` class in `seedu.duke.hr` handles changing contacts and roles information of the members in 
`MemberList` and showing the contacts and roles of the changed `Member`.  
It implements the following operation:  
* `CommandChangeInfo#execute()` - Changes any of the their contacts and roles `Member` in `MemberList` and shows the 
modified member information.  

Given below is an example usage scenario and how the program list the information of members.  

Step 1. After some `hr addMember` commands, the user created a `MemberList` with two `Member`. <br/>
The first `Member` is "John Sterling" with phone number "12345678", email "123@gmail.com", role "member". <br/>
The second `Member` is "Harry Potter", phone number "88888888", email "qaz@gmail.com", role "president". <br/>

Step 2. The user executes `hr changeInfo /n john sterling /p 11111111 /r publicity director` command to change the phone
 number and role of the member with name "John Sterling" in the list. The `hr changeInfo` command calls 
`CommandChangeInfo#execute()`, then `Member` with the `memberName` `John Sterling` in the `MemberList` will have its 
`memberPhone` changed to `11111111`, and `memberRole` changed to `publicity director`. The `memberName` is not case 
sensitive. 

The following shortcut commands can achieve the same result: <br/>
`hr c /n john Sterling /p 11111111 /r publicity director`<br/>

The sequence diagram for changing contacts and role information of a member is as shown below:

![CommandChangeMemberInfo](hrDiagramPic/CommandChangeMemberInfo.png)

**3.5.3.2. Design Considerations** 
Aspect: Changing member information <br/>
* Alternative 1(Current Choice): `Member` information is to be modified based on the member's full name.  
    * Pros: Easy to implement. Also, if the user knows the name of the target `Member`, which is a likely case in actual 
    practice, he can change the member's information quickly.
    * Cons: Member name cannot be easily modified. If the user wants to change the name of the `Member`, the user has to delete 
    the target `Member`, and add the `Member` back using the new name.
    
* Alternative 2: `Member` Information is to be modified based on the member's index in the list.  
    * Pros: `Member` name can be easily modified. 
    * Cons: This feature is very dependent on the list `Member` feature. The user will always need to call the `hr listMember` 
    command to find out the index of the target `Member`, before he can change the member's information.  

[Return to top](#Developer-guide)


**3.5.4. Search for members**  
(by: Wang Zixin)  

**3.5.4.1 Current Implementation**  
The `CommandSearchMember` class in `seedu.duke.hr` handles searching for any `Member` in `MemberList` whose information matches 
any one of the conditions provided by the user input and then prints all the results.  
It implements the following operation:  
* `CommandSearchMember#execute()` - Search for any `Member` whose information matches any conditions 
provided by the user input then print all the results.  

Given below is an example usage scenario and how the program search particular members.  

Step 1. After some `hr addMember` commands, the user created two `Member`s in `MemberList`. The first `Member` is 
"John Sterling" with phone number "12345678", email "123@gmail.com", role "member". The second `Member` is 
"Harry Potter", phone number "88888888", email "qaz@gmail.com", role "president".  

![](hrDiagramPic/3-5S1.png)  

Step 2. The user executes `hr search President` command to search for any `Member` whose information includes "President". 
The program first check if the first `Member` matches the condition. This `Member` does not match the search condition.  

![](hrDiagramPic/3-5S2.png)  

Step 3. Then the program check if the second `Member` matches the condition. This `Member` now matches the search condition. 
Because there is no more `Member`s, program will print out information of the second `Member`.  

![](hrDiagramPic/3-5S3.png)  

The sequence diagram for searching is given below:  

![](hrDiagramPic/CommandSearchMember.png)  

**3.5.4.2. Design Considerations**  
Aspect: Search condition  
* Alternative 1(Current choice): It will search for `Members` whose inforamtion matches any conditions provided by user input.  
    * Pros: It can maximize the number of results that are provided to user just like what google search is doing now. It can 
    also decrease the running time in some degree because it can jump to the next `Member` if the previous `Member`'s name or phone 
    number or email matches the search condition.  
    *Cons: If the user input contains some common strings like ".com" or "a", there will be too many results shown to the user.  
    
* Alternative 2: It will search for `Members` whose information matches all the conditions provided by user input.  
    * Pros: There will not be too many results when the user input includes common strings with other conditions.  
    * Cons: It will increase the running time a lot because it has to check all the information of `Member`s, especially 
    when there are many members.  

[Return to top](#Developer-guide)

**3.5.5. List Professors and Administrators**  
(by: Wang Zixin)  

**3.5.5.1 Current Implementation**  
The `CommandListProfAdmin` class in `seedu.duke.hr` handles listing all the `Member`s in `MemberList` whose roles are professor 
or administartor.  
It implements the following operation:  
* `CommandListProfAdmin#execute()` - List all the `Member`s who are professors or administrators  

Given below is an example usage scenario and how the program list all the professors or administrators.  

Step 1: After some `hr addMember` commands, the user has created three `Member` in `MemberList`. The first `Member` is 
"John Sterling" with phone number "12345678", email "123@gmail.com", role "member". The second `Member` is 
"Harry Potter", phone number "88888888", email "qaz@gmail.com", role "professor". The third `Member` is 
"Tony Parker", phone number "114514", email "tp9@gmail.com", role "Administrator".  

![](hrDiagramPic/35S1.png)  

Step 2. Then the user executes `hr list prof&admin`. After sifting, the remaining `Member`'s information will be 
printed.  

![](hrDiagramPic/35S2.png)  

The sequence diagram for listing professors and administrators is shown below:  

![](hrDiagramPic/CommandListProfAdmin.png)  

**3.5.5.2. Design Considerations**  
Aspect: Just use `hr search` or use `hr list prof&admin`  
* Alternative 1(Current choice): Use `hr list prof&admin`
    * Pros: The user can just type one command, instead of typing `hr search prof` and `hr search admin`.  
    * Cons: It increases the time of searching all the `Command`s in command list and this command looks similar 
    to `hr list` that may confuse the user.  
    
* Alternative 2: Use `hr search`  
    * Pros: The time of searching all `Command`s in command list will not be influenced.
    * Cons: The user has to type `hr search` twice to list professors and administrators and the lists are separated.  

[Return to top](#Developer-guide) 


**3.5.6. List Connection**  
(by: Wang Zixin)  

**3.5.6.1 Current Implementation**  
The `CommandListConnection` class in `seedu.duke.hr` handles listing all the `Member`s in `MemberList` whose roles are speakers 
or alumni.  
It implements the following operation:  
* `CommandListConnection#execute()` - List all the `Member`s who are speakers or alumni  

Given below is an example usage scenario and how the program list all the `Member` in connection.  

Step 1. After some `hr addMember` commands, the user has created three `Member` in `MemberList`. The first `Member` is 
        "John Sterling" with phone number "12345678", email "123@gmail.com", role "member". The second `Member` is 
        "Harry Potter", phone number "88888888", email "qaz@gmail.com", role "speaker". The third `Member` is 
        "Tony Parker", phone number "114514", email "tp9@gmail.com", role "Alumni".  

![](hrDiagramPic/3-6S1.png)  
 
Step 2. Then the user executes `hr list connections`. After sifting, the remaining `Member`'s information will be 
        printed.  
      
![](hrDiagramPic/3-6S2.png)    
  
The sequence diagram for listing connection is shown below:  

![](hrDiagramPic/CommandListConnection.png)  

**3.5.5.2. Design Considerations**  
Aspect: Just use `hr search` or use `hr list connections`  
* Alternative 1(Current choice): Use `hr list connections`
    * Pros: The user can just type one command, instead of typing `hr search speaker` and `hr search alumni`.  
    * Cons: It increases the time of searching all the `Command`s in command list and this command looks similar 
    to `hr list` that may confuse the user.  
    
* Alternative 2: Use `hr search`  
    * Pros: The time of searching all `Command`s in command list will not be influenced.
    * Cons: The user has to type `hr search` twice to list speakers and alumni and the lists are separated.  

[Return to top](#Developer-guide) 


### 3.6. Storage

![](BackendDiagram/StorageFlow.png)

The storage component is responsible for storing persistent data to disk. This involves objects from all 3 categories of the application.
The above sequence diagram shows the program flow involving only the `FileManager` component, other aspects like the callers `Duke`, execution of `Ui` etc are ommitted for simplicity.

The main process of the program in Duke invokes the `readAll()` function on start-up. This reads all the data saved on disk to memory.
During the program loop, the main process invokes `saveAll()` after every command run. This saves the current state of the application to file automatically.

**Current Implementation**

The `FileManager` class in `seedu.duke.backend` manages all the file related operations. Its purpose is to provide an abstraction layer for saving and reading the current state of the application to and from disk.
It is also used by the `import` command to perform importing of other CSV files.

* `FileManager#getPath()` - Retrieves the working directory of the `FileManager`.
* `FileManager#setPath()` - Changes the working directory of the `FileManager`.
* `FileManager#saveAll()` - Saves the current state of the program. This function invokes `saveEvent()`, `saveFinance()` and `saveMembers()` in sequence.
* `FileManager#readAll()` - Reads the saved data in the working direction to program memory. This functions invokes `readEvent()`, `readFinance()` and `readMembers()` in sequence.
* `FileManager#saveEvent()` - Saves the event data to disk.
* `FileManager#saveFinance()` - Saves the finance data to disk.
* `FileManager#saveMembers()` - Saves the HR data to disk.
* `FileManager#saveFile()` - Saves a `String` to the specified filename.
* `FileManager#readFile()` - Reads a CSV file from disk and returns a `HashMap<String, ArrayList<String>>` containing the header of each table mapped to an `ArrayList` of all rows in that column. This function is also used by the `import` command.
* `FileManager#readFinance()` - Reads the finance data from disk.
* `FileManager#readEvents()` - Reads the event data from disk.
* `FileManager#readMembers()` - Reads the hr data from the disk.

**Design Considerations**    
Aspect: When is the file saved
* Alternative 1 (Current Choice): Saving is performed automatically on every command completion.
    * Pros: Convenient for users. No requirement to save all 3 categories independently.
    * Cons: Requires more IO overhead to write the data to disk. Potentially wastes IO cycles writing unchanged data.
    * Reason for choice: Our design philosophy is to make the program as convenient and easy to use as possible. This alternative allows us to align with that goal.
* Alternative 2: Dedicated saving command
    * Pros: Only saves to the disk when required. Can revert accidental changes easily.
    * Cons: The user may forget to save their data, resulting in data loss.
* Alternative 3: Each command calls the relevant saving function.
    * Pros: Minimizes wasted IO cycles. Convenient for users.
    * Cons: Need to ensure that all commands that change the state of the program actually save the data. Harder to check for bugs.

Aspect: The format of the file
* Alternative 1 (Current Choice): CSV File
    * Pros: Commonly used file format, easy to edit. Compatible with other programs.
    * Cons: None
    * Reason for choice: This the best choice as it is an already established file format compatible with other programs.
* Alternative 2: Use `serializable` Java interface
    * Pros: Extremely easy to write and read from file. Very good retention of data and it's relationships. Easy to implement.
    * Cons: Filetype is not user editable as it is written by the java serializer.
* Alternative 3: Use a proprietary file format designed specifically for CCA Manager
    * Pros: Able to tailor the design of the file format to suit the requirements of the program.
    * Cons: May not be editable by the user with a text editor. Does not offer compatibility with any existing programs.
    
[Return to top](#Developer-guide)

## 4. Product Scope
### 4.1. Target user profile

Our product targets people who manage interest groups and CCAs. 
However, our software solution allows us to easily expand the target audience to target schools and corporate enterprise clients in the future.

[Return to top](#Developer-guide)
### 4.2. Value proposition

Management software is expensive and complex, training employees to use it is time-consuming. CCA Manager aims to solve these
problems by offering an all-in-one solution focused on simplicity and efficiency. 
Our use of industry standard csv format ensures compatibility with leading industry tools. 
Shorthand Commands and Relative Time allow advanced users to enter up to 70% more commands per minute. The import command allows users to migrate existing data quickly and get started in no time.


[Return to top](#Developer-guide)
## 5. User Stories

(By: Varsha)

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|add/delete members to the list |keep track of the members in the CCA|
|v1.0|user|view a summary of members |view information of people in the various roles|
|v1.0|user|add/delete events to the list|so that i can manage the schedule|
|v1.0|user|view a summary of events |keep track of future and completed events|
|v1.0|user|add/delete entries|keep track of financial records in the CCA|
|v1.0|user|view financial summary |keep track of cash flow information at a glance|
|v2.0|user|view the number of days remaining for the events|remind myself of upcoming events |
|v2.0|user|perform a search on member/events|find the details of the member/event quickly|
|v2.0|user|view the list of contacts of the prof/admin|so that i know how to contact them for admin matters|
|v2.0|user|reassign member roles |so that I can update their roles and responsibilities|
|v2.0|user|change member phone numbers and emails |so that I can update their contacts|
|v2.0|user|take attendance | so that I can keep track of members participation in the club|
|v2.0|user|view members absence rate | so that I can identify members with low participation rate|
|v2.0|user|view the list of contacts of the connections (alumni, speakers) | So that I can source for collaborators or speakers for our events easily|
|v2.0|user| import other csv files | So that I can transfer my existing data into the program easily|

[Return to top](#Developer-guide)

## 6. Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold hundreds of thousands of data entries without losing the data.
3. A user with average typing speed should be able to accomplish most of the tasks faster using commands than using the mouse.
4. The program should support writing to a universally supported and easy to edit non-proprietary file format such as RFC 4180 .csv files.

[Return to top](#Developer-guide)
## 7. Glossary

**CCA** - Co-curricular Activity <br/>
**CLI** - Command Line interface <br/>
**UML** - Unified Modelling Language <br/>
**CSV** - Comma-seperated values. This typically refers to the file type with extension .csv <br/>
**EDF** - Earliest Deadline First <br/>
**OS** - Operating Systems  <br/>
**RFC** - Request for Comments, an internet standard specifying various applications of technology or methodology.  
**IDE** - Integrated Development Environment. A software application that provides facilities for software development, such as IntelliJ.  
**EICAR** - European Institute for Computer Antivirus Research   
**Cash flow** - Real or virtual movement of money.  
**IO** - Input/Output. Also known as the process of communicating within various parts of the operating system. The most common IO task is file related operations such as opening a file.
**Proprietary format** - A non-standard File format designed by a particular company, organization or individual. Could be designed with the details of the implementation kept secret.
**HR** - Human Resource

[Return to top](#Developer-guide)

## 8. Instructions for manual testing

This section contains information on how to test CCA Manager to ensure that the basic functionalities are working.

1. Start the application from a terminal window by using `java -jar CCAManager.jar`
2. Add some entries with commands
    * Use `hr addMember /n Harry Potter /p 12345678 /e H_P@gmail.com /r member` to add a new member 
    * Use `finance addLog iPhone 12 Pro 1800` to add a new finance entry
    * Use `event addEvent /n Autodesk course /d 2020-12-20 /t 8-10.30pm` to add a new event
3. Check that the items have been added with the respective list commands
    * Use `hr listmember` to show all the HR entries 
    * Use `event listEvent` to list all the events
    * Use `finance summary` to show a summary of all finance entries
4. Type `bye` to exit the program
5. Launch the application again.
6. Repeat step 3 to verify that all the information entered has been saved 
7. Type `event search /s course` to search for the event that you have entered
8. Type `event countdown` to see a list of events sorted by days in increasing order
9. Type `hr search /r member` to search for all members with the role of member
10. Type `finance changeLog /i 1 /n buy cake 5.5` to modify the finance entry that you have entered
11. Type `hr changeInfo /n Harry Potter /p 12345678 /e 123@gmail.com /r admin` to modify the member entry entered earlier
12. Type `event done 1` to set the status of the event to completed
13. Type `hr list prof&admin` to show a list of members with the role prof or admin
14. Type `event addAttendance /n Autodesk course /m Harry Potter` to add the member to the list of attendees
15. Repeat step 3 to verify that all the information has been modified successfully
16. Type `event listAttendance /n Autodesk course` to view the event attendance
17. Type `event delAttendance /n Autodesk course /m Harry Potter` to delete the attendance record
17. Clean up the entries by deleting them
    * Use `event delEvent 1` to remove an event
    * Use `event delEvent all` to remove all events
    * Use `finance dellog 1` to remove the finance log
    * Use `hr delMember 1` to remove the member
18. Type `bye` to exit the program
19. Launch the application again and repeat step 3 to verify that all the entries have been deleted.

[Return to top](#Developer-guide)
