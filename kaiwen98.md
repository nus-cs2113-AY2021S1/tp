# Looi Kai Wen's Project Portfolio Page

# Project: FinanceIt

# Overview
FinanceIt is an all-in-one desktop application that handles the finance tracking needs of university students who are 
comfortable with a CLI interface. In the application, it consists of 5 sub-application that helps student to manage
their finance. <br/>

# Summary of Contributions: Code management
1. Project Management
    * Managed releases `v1.0` - `v-2.0` on GitHub (2 releases)
1. Contributions to the project on RepoSense. <br/>
    * **Code contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kaiwen98&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
    
1. Contributions to TP repository 
    1. Report and maintenance of [project issues and milestones](https://github.com/AY2021S1-CS2113-T16-1/tp/issues?q=+is%3Aissue+author%3Akaiwen98+)
        1. Bug reporting: [#16](https://github.com/AY2021S1-CS2113-T16-1/tp/issues/16), [#19](https://github.com/AY2021S1-CS2113-T16-1/tp/issues/19), [#3](https://github.com/AY2021S1-CS2113-T16-1/tp/issues/3)
        1. Features (ManualTracker): [#24](https://github.com/AY2021S1-CS2113-T16-1/tp/issues/24)

    1. [Pull Requests](https://github.com/AY2021S1-CS2113-T16-1/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Akaiwen98) to TP for code contribution 
        1. Functional code contributions:
            1. Dynamic Table Printer to be used for output throughout the software: [#7](https://github.com/CS2113-AY2021S1-T16-1/tp/pull/7)
            1. Contributions to ManualTracker feature: [#28](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/28), [#1](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/1)
            1. Contributions to code quality and organisation: [#99](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/99), [#105](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/105), [#70](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/70), [#50](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/50)   
        1. Error Handling contributions: [#14](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/14)
        1. Code testing contributions: [#240](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/240), [#57](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/57), [#45](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/45)

<div style="page-break-after: always;"></div> 

# Summary of Contributions: Documentation
# Contributions to UG
# FinanceIt: A Finance Management Application with a CLI Interface
FinanceIt is an all-in-one desktop application that handles the finance tracking needs of university students who are comfortable with a CLI interface.
It includes 5 different finance tools, all of which take in typed commands from the users to execute their respective functions.

* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

# Set-up
## Setting up for Testers (IMPORTANT)

Prerequisites: A computer
1. Download the executable from our [latest release](https://github.com/AY2021S1-CS2113-T16-1/tp/releases/) .
1. Save the executable file in your preferred folder.
1. Run the program via the command line. The command is: ```java -jar financeit.jar```.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.
1. Download the latest release from the release page.
1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/seedu.financeit/Financeit.java` file, right-click it, and choose `Run Financeit.main()`. If the setup is correct, you should see something like the below:

__Output:__

![](developerGuide_images/screenshots_mainmenu/main_menu.png)


<div style="page-break-after: always;"></div>   

# Overview
## Main Features

Feature|Command|Description|
-------|-------|-----------|
Manual Tracker|```manual```| Manual entry of transactions. In Manual Tracker, the user manages daily lists of entries called Ledgers. 
Entry Tracker|```entry```| Subsidiary subroutine of Manual Tracker. In Entry Tracker, the user manages ledger entries, which represents a unit of transaction for a particular day.
Recurring Tracker|```recur```| In recurring Tracker, the user manages special recurring entries that are deducted on a regular basis, which are too cumbersome to record regularly with Entry Tracker.
Goal Tracker|```goal```| Sets income or expense goals, whereby the tracker will report to user his progress towards them whenever an entry is added.
Save Manager|```saver```| Allows save of multiple program running states and load them anytime you want. May also delete and reset running state.
Finance Tools|```finance```| FinanceTools contains tools related to financial calculations.

<div style="page-break-after: always;"></div>  

# Main Menu
Gateway to the various other features of the application. 
Upon running the application, you should be greeted by the main menu. Enter the corresponsing commands shown on the table to visit the desired feature!

Feature|Command|
-------|-------|
Manual Tracker|```manual```| 
Entry Tracker|```entry```| 
Recurring Tracker|```recur```| 
Goal Tracker|```goal```| 
Save Manager|```saver```| 
Finance Tools|```financial```| 
Quit|```quit```|

## Exit from Main Menu
Exits from the program. If you have an outstanding list, it will be saved automatically as lastSave.txt in
the saveStates folder. This folder will be automatically created when you first run the program.

>Syntax

    exit

> Example: 

![](developerGuide_images/screenshots_mainmenu/main_menu_exit.png)

<div style="page-break-after: always;"></div>  

# Manual Tracker
Users can manage lists of entries, which are known as ledgers. Each list represents a single date of record.

> Example
    If I wish to record my income and expenditures on 30 October 2020, I will use the program as follows:
    1. Use Manual Tracker to create a ledger of date 20-10-03
    2. Open the ledger of date 20-10-03 
    3. Use Entry Tracker to create entries to record the transactions for that particular date

## Add ledger
Add a ledger to the record, representing a date.

>Syntax

    new {PARAM_TYPE} {PARAM} 
    
Param Type| Param | Param Format
----------|-------|------------|
`/date`|Date of the ledger, and all the entries under that ledger.| Input string of the date in YYMMDD, YY-MM-DD or YY-M-D

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_new.png)
    
## Remove ledger
Remove a specified ledger from the record, referenced by date or id on the list.
This means that the user only need to specify one of the two param types, either ```/date``` or ```/id```.

>Syntax
 
    delete {PARAM_TYPE} {PARAM}
    
Param Type| Param | Param Format
----------|-------|------------|
`/date`|Date of the ledger, and all the entries under that ledger.| Input string of the date in YYMMDD, YY-MM-DD or YY-M-D
`/id`|Index of the ledger in the list, where the first ledger is of index 1. | Input positive integer 

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_delete1.png)
    
## Open ledger
Users will gain access into the entries associated with the specified ledger, referenced by date or id on the list.
This means that the user only need to specify one of the two param types, either ```/date``` or ```/id```.

* Automatic creation of non-existing ledgers 
    * If the ledger specified do not exist and if the param type supplied is "/date", the program will create a new ledger
    in accordance to the date supplied.
    * If the user specifies a non-existing index, then the program will not have enough information to create the new ledger instance. Automatic creation
    will not apply in this particular case.
    
Refer to Feature Entry Tracker onwards for further instructions.

>Syntax

    open {PARAM_TYPE} {PARAM}


Param Type| Param | Param Format
----------|-------|------------|
`/date`|Date of the ledger, and all the entries under that ledger.| Input string of the date in YYMMDD, YY-MM-DD or YY-M-D
`/id`|Index of the ledger in the list, where the first ledger is of index 1. | Input positive integer 

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_open.png)
    
## Show ledger list
Shows the record of ledgers that has been added.

>Syntax

    list

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_list.png)

## Print command list
Prints available commands that users can enter in for manualTracker.

>Syntax

    commands

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_commands.png)
    
## Exit to Main Menu
Exit to main menu where users can choose another feature to use.

>Syntax

    exit

> Example: 
    
![](developerGuide_images/screenshots_manualtracker/manual_exit.png)


<div style="page-break-after: always;"></div>   

# Entry Tracker
Subroutine that is subsidiary off the ManualTracker. 
Users can manage entries associated with the ledger they have opened.
Entries are specified by the following parameters:

* Time of transaction
   * Time in which the transaction ocurred
* Type of transaction 
   * Income or Expense
* Category of transaction. The user is only limited to the following set of categories. 
   * Income: {Salary, Allowance, Others}
   * Expense: {Transport, Food, Travel, Shopping, Bills, Others}
* Amount
   * Amount involved in the transaction. 
      * If the transaction type is expense, the amount is considered as a deduction to the account.
      * If the transaction type is income, the amount is considered as a credit to the account.
* Description
   * User input texts to help them record the details of the transaction.

> Example

    Ledger of date 20-10-03
        Entry 1: Shopping at MBS : $1500
        Entry 2: Salary : $3000
    
    From the above, we can infer that on the date 20-10-03, the user has one expense entry and one income entry.
    
## Add entry
Add an entry to the ledger record.

__Note:__
* For transaction categories, we require the users to enter the shortcut equivalent to the categories listed above. Refer to the following table.

Category|Category shortcut|Compatible transaction type
----------|-------|------------|
FOOD|fd|Expense
BILLS|bll|Expense
TRANSPORT|tpt|Expense
TRAVEL|tvl|Expense
SALARY|slr|Expense
ALLOWANCE|alw|Income
OTHERS|oth|Income

>Syntax

    new {PARAM_TYPE} {PARAM}

        
Param Type| Param | Param Format
----------|-------|------------|
`/time`|Time of the entry.| Input string of the date in HHMM, HHMMSS or H.
`/amt`|Amount involved in the transaction.| Input positive floating point number in 2 d.p. <br/> Can be $XX.XX, XX, etc.
`/cat`|Category of transaction. | Input string belonging in the set: {tpt, fd, tvl, shp, bll, slr, alw}
`-i or -e`|Type of transaction. | No parameter required. 

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_create.png)


## Edit entry
Add an entry to the ledger record.
Users only need specify the param to edit, there is no need to fill out all params.

__Note__
* While the index is compulsory to be provided, the rest of the params are not compulsory.
The users will include the params that they wish to modify.
* Hence, a sample command ```entry edit /id 1 /time 1600``` will modify entry at __index 1__ to __time 1600__.

>Syntax

    edit /id {INDEX} {PARAM_TYPE} {PARAM} ... 
        
Param Type| Param | Param Format
----------|-------|------------|
`/id`|Index of the entry in the list, where the first entry is of index 1. | Input positive integer
`/time`|Time of the entry.| Input string of the date in HHMM, HHMMSS or H.
`/amt`|Amount involved in the transaction.| Input positive floating point number in 2 d.p. <br/> Can be $XX.XX, XX, etc.
`/cat`|Category of transaction. | Input string belonging in the set: {tpt, fd, tvl, shp, bll, slr, alw}
`-i or -e`|Type of transaction. | No parameter required. 
        
> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_edit_list.png)

    
## Remove entry
Remove a specified entry from the record, referenced by id on the list.

>Syntax

    delete {PARAM_TYPE} {PARAM} 
            
Param Type| Param | Param Format
----------|-------|------------|
`/time`|Time of the entry.| Input string of the date in HHMM, HHMMSS or H.
`/id`|Index of the entry in the list, where the first entry is of index 1. | Input positive integer

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_delete_list.png)

    
## Show entry list
Shows the record of entries that has been added.

>Syntax

    list

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_list.png)

## Print command list
Prints available commands that users can enter in for manualTracker.

>Syntax

    commands

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_commands.png)

## Print categories
Prints expenditure category shortcuts that the user can input in entry creation/ edit commands.

>Syntax

    cat

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_cat.png)

    
## Exit to Manual Tracker Main Routine
Exit to Manual tracker where users can choose another ledger.

>Syntax

    exit

> Example: 

![](developerGuide_images/screenshot_entrytracker/entry_exit.png)

## Manual Tracker

No. | Feature | Syntax |
----|---------|---------|
1.|Open Ledger|_open /date {YYMMDD} or delete /id {INDEX}_|
2.|New Ledger|_new /date {YYMMDD}_|
3.|List Ledgers|_list_|
4.|Delete Ledgers|_delete /date {YYMMDD} or delete /id {INDEX}_ |;
5.|Exit to Main Menu|_exit_|

<div style="page-break-after: always;"></div> 

# Contributions to DG

## Overview of Architecture
__Architecture Diagram__

![](uml_images/images_updated/Overall.png)

There are 5 distinct features that exists within the FinanceIt application, all of which are accessed via the main menu 
interface facilitated in FinanceIt.java.

The design of the software can be split into 5 distinct components:
* Logic Manager component
* Logic component
* Input Manager component
* Data component
* Storage component

## Logic Manager Component

![](uml_images/images_updated/Handler_arch.png)

__Description__

The Logic Manager component serves as the bridge between user interface and program operations.
<br />It includes 5 classes: 
* ```ManualTracker```
* ```EntryTracker```
* ```RecurringTracker```
* ```GoalTracker```
* ```FinanceTools```

__API__
* ```ManualTracker```, `RecurringTracker` and ```EntryTracker``` maintains an instance of a ```DataList``` (```LedgerList``` and ```EntryList```) in ```Model``` respectively, 
 and provides an interface for the user can append, remove or perform other ```Data``` operations with the contents of the ```Datalist```.
* ```GoalTracker``` maintains a list of income or expense ```Goals``` to track against entries in the ```EntryList```, 
and provides an interface for the user to append or remove ```Goals```.
* ```Finance Tools``` class provides an interface for users to utilize an array of 
finance calculator tools within it.
* All ```LogicManager``` classes use the ```InputManager``` component to process user input, then use ```Logic``` component
to perform the operation associated with the param handling.

## Logic Component

![](uml_images/images_updated/Logic_arch.png)

__Description__

The Logic Component executes logic operations passed via a `CommandPacket`, by handling individual params 
contained in the `CommandPacket`.

__API__

* Different `CommandHandler` classes are used in `LogicManager` classes to handle various operations e.g. new, edit, delete
* If `CommandHandler` classes recognises a `param` from the `CommandPacket` instance, it performs a sub-operation
associated with the `param`. For instance, `/date` will cause `CreateLedgerCommand` instance to set the date of the
newly created ledger.  
* `CommandHandler` in turn uses `ParamChecker` to verify validity of inputs before setting.

### Input Manager
* Note: Refer to [Input Manager Component](#input-manager-component) above for class diagram 
illustration of the below subsections.

__Input Conventions__
* The user input is composed of the following format:
```
    <command> <param type> <parameter> <param type> <parameter> ...
```
* The ```command``` string determines the current state of the Finite State Machine, and
hence the function executed. 
* The remainder of the string includes a series of  ```param type``` - ```param``` combinations, whereby
```param type``` indicates the type of the parameter which is to be identified by the user class,
and ```param``` indicates the parameter that is associated with the ```param type```. 

* Param types are restricted to two types: 
    * ```/<string>```, requires a corresponding parameter.
        * Eg. ```param type```: ```/date```
              <br>  ```param``` : ```2020-04-04```
    * ```-<string>```, does not require a corresponding parameter. 
        * Reserved for param types which are used to specify a property to be true/false
        * Eg. ```-auto```, to specify if an entry has automatic deduction. 
        
<a name="commandPacket"></a> __CommandPacket class__ 
* A helper class. Contains two particular attributes to store the user input in an organised fashion.
    * ```commandString``` :  ```String``` Store the command string from the input.
    * ```paramMap``` : ```HashMap``` Store the pairs of ```param type``` and ```param``` present in the input string.
        * Key: ```param type```
        * Value:  ```param```

__InputParser class__
* A helper class. Parses the input string and returns a corresponding [```commandPacket```](#commandPacket).
    * ```parseInput()```: 
        * Initializes a ```commandPacket``` and populates the ```commandString``` attribute.
        * Calls ParamParser instance to parse the segment of the input string
        that corresponds with the sequence of ```param type``` - ```param``` pairs, and
        return a HashMap populated with the aforementioned pairs.
        * Returns a fully populated ```commandPacket``` to be used by user classes.
         
__ParamsParser class__
* A helper class. Parses the subsequence of the input string that corresponds with sequence of 
```param type``` - ```param``` pairs.
    * Parsing of input for params via ```parseParams()```:
        * __Step 1__: Use a regex helper class ```RegexMatcher``` to identify and extract ```param type``` that matches the 
        pattern specified in "Input conventions":
        
            * Param types are restricted to two types: 
                * `/abcd`, requires a corresponding parameter.
                    * Example: <br>param type: `/date`, param: `2020-04-04`
                * `-abcd`, does not require a corresponding parameter. 
                    * Reserved for param types which are used to specify a property to be true/false
                    * Example: <br>`-auto`, to specify if an entry has automatic deduction. 
        
        * __Step 2__: Identify the substring of the rest of the input string before the next ```param type``` or end-of-line, 
        as the ```param``` to the previously identified ```param type```. Extract it from the input string.
        * __Step 3__: Put the ```param type``` - ```param``` pair into a ```HashMap```.
        * __Step 4__: Repeat steps 1 to 4 until there is the input string is fully extracted.
        * __Step 5__: Return a ```HashMap``` populated with the aforementioned pairs.

### Main Menu
- Loading up user data
- Access to various features
- Saving outstanding user data to respective save files

&nbsp;  
### Manual Tracker & Entry Tracker
**Overview** <br />

__Ledgers and Entries__

In this feature, we represent the transactions incurred by the users as ```Entry``` instances.
Instances of ```Entry``` class are categorised by the date of origin, which is represented by
```Ledger``` instances.

```Entry``` instances are characterized by the following: 
* Time of transaction
* Type of transaction: Income/ Expense 
* Amount in transaction
* Category of spending/ expenditure
* Description

```Ledger``` instances are characterized by the following: 
* Time of transaction
* Collection of ```Entry```instances

**Manual Tracker** <br />

The Manual Tracker is a feature that allows users to manage Ledgers with create, delete
and open operations. Ledgers is a class that maintains a list of transactions that are 
recorded for a given date. 

The Entry Tracker is fundamentally similar to the Manual Tracker, except it manages ```Entry``` instances
instead of ```Ledger```. Entry Tracker is initialized when a ```Ledger``` instance is "opened", whereby 
the Entry Tracker facilitate the manipulation of the collection of ```Entry``` instances that are associated with
that particular ```Ledger``` instance.

For the sake of brevity, this section will focus on the discussion of the Manual Tracker. 
The edit operation of the Entry Tracker will be discussed at the [end of this section](#entryseq); it is sufficiently unique to Manual Tracker operations to merit detailed discussion.

The Manual Tracker is capable of executing the following states of operation:

|States| Operations | 
|--------|----------|
|```MAIN_MENU```|Go to main menu for users to choose the available operations
|```CREATE_LEDGER```|Create a ledger specified by date, and append it to ```ledgerList```.
|```DELETE_LEDGER```|Delete an existing ledger, referenced by date or index.
|```OPEN_LEDGER```|Go to subroutine "Entry Tracker" for the entries recorded  under the specified ledger.

**Architecture in Context** 

**Logic Manager and Parser** 

![](uml_images/images_updated/Handler.png)

|Class| Function |	
|--------|----------|	
|```InputParser```| Breaks input string by user into ```commandString``` and a sequence of ```paramTypes```-```param``` pairs. <br><br> The latter subsequence of the string is passed into ParamParser for further processing. <br><br> Information obtained from input parsing will be used to populate an instantiated ```CommandPacket``` instance, which will then be passed to the entity that called the parsing function.	
|```ParamParser```| Process the sequence of ```paramTypes```-```param``` pairs and populate the ```paramMap``` in the instantiated ```CommandPacket``` instance.	
|```ManualTracker```| [Refer to section](#logicManager_handler).
|```EntryTracker```| Omitted for brevity.


**<a name = logic_data></a>Logic Manager and Data** <br />

![](uml_images/images_updated/Handler_Data.png)

|Class| Function |
|--------|--------|
|```ManualTracker```| [Refer to section](#logicManager_handler).
|```EntryTracker```| Omitted for brevity.
|```EntryList```| Omitted for brevity.
|```Entry```| Omitted for brevity.
|```LedgerList```| Extends ItemList. Refer to Ledgers and Entries section for class behavior.
|```Ledger```| Extends DateTimeItem. Refer to Ledgers and Entries section for class behavior.
|```ItemList```| Class with defined list behavior specified with helper methods such as retrieval, checking of Duplicates and deletion.
|```DateTimeItem```| Abstract class that extends ```Item``` class; instances will have ```LocalDate``` or ```LocalTime``` attributes and corresponding helper methods.
|```Item```| Abstract class to define behavior of entities that need are stored in ```ItemList``` instances.

**<a name = handler_logic></a>Handler and Logic** <br />

![](uml_images/images_updated/Commands_Logic.png)

|Class| Function |
|--------|----------|
|```RetrieveLedgerHandler```| Process ```paramTypes```-```param``` pairs from the ```CommandPacket``` instance to identify specified ```Ledger``` instance, then retrieves the instance from the existing ```LedgerList```.
|```CreateLedgerHandler```| Process ```paramTypes```-```param``` pairs from the ```CommandPacket``` instance to identify specified ```Ledger``` instance to be created, then creates the instance and append to existing ```LedgerList```.
|```retrieveEntryHandler```| Omitted for brevity.
|```CreateEntryHandler```| Omitted for brevity.
|```EditEntryHandler```| Omitted for brevity.
|```ParamChecker```| Class contains a collection of methods that verify the correctness of the ```param``` supplied. <br><br> For instance, ```ParamChecker.checkAndReturnIndex``` checks if the index provided is out of bounds relative to the specified list, and throws the relevant exception if the input index is invalid. 
|```ParamHandler```| Abstract class that outlines the general param handling behavior of ```commands``` instances and other classes that need to handle ```params``` in its operation.  


**<a name = logicManager_handler> </a>Logic Manager and Handler** <br />

![](uml_images/images_updated/Handler_Commands.png)

|Class| Function |
|--------|----------|
|```RetrieveLedgerHandler```| [Refer to section](#handler_logic).
|```CreateLedgerHandler```| [Refer to section](#handler_logic).
|```retrieveEntryHandler```| Omitted for brevity.
|```CreateEntryHandler```| Omitted for brevity.
|```EditEntryHandler```| Omitted for brevity.
|```ManualTracker```| Implements Manual Tracker. Contains handler methods that implements a particular operation capable by the Manual Tracker. <br><br> These methods use the above ```command``` instances for param handling operations from user input.
|```EntryTracker```| Omitted for brevity.


**Functions with Sequence Diagrams** <br />

**Creation of Ledger([Sequence Diagram](#diag1))** <br />
1. At ```ManualTracker.handleMainMenu()```, the user's input is registered via ```java.util.Scanner``` instance.
1. Input is parsed by ```InputParser.parseInput()```, and ```ManualTracker.packet``` is set to the returned ```CommandPacket``` instance.
1. The ```commandString``` of the ```CommandPacket``` instance is evaluated, and the corresponding handle method() is executed.<br/>In this case, ```handleCreateLedger()``` will be called.
1. At `handleCreateLedger()`, the following processes will be executed:
    1. A new instance of ```CreateLedgerHandler``` is created. The input String array will be passed into `CreateLedgerHandler.setRequiredParams()` to set required params for a successful parse.
    1. A new instance of `Ledger` will be instantiated and set to `CreateLedgerHandler.currLedger`.
    1. ```CreateLedgerHandler.handlePacket(packet)``` is called to handle params in the packet.
        1. Refer to the section on [Param Handling](#impl_logic) for more details pertaining to general param handling. 
        1. For `CreateLedgerHandler`, the `handleSingleParam` abstract method will be implemented as shown in the [following table](#table1).
        
1. From ```ManualTracker```, the configured ```Ledger``` instance will be retrieved from the ```CreateLedgerHandler``` instance
and added into the ```LedgerList``` instance at ```ManualTracker.ledgerList```.

#### <a name = table1></a> Param Handling Behavior

|ParamType|ParamType String| Expected Param | Operation | Verification method |
|---------|----------------|----------------|-----------|---------------------|
|```PARAM.DATE```|"/date"|Various format of date in string, eg. "2020-03-02"| Call ```currLedger.setDate()``` to set date for the ```Ledger``` instance. | ```ParamChecker.checkAndReturnDate(packet)```|

#### <a name = diag1></a> Sequence Diagram

![](uml_images/images_updated/manualTrackerCreateLedgerSeqDiagram.png)

**Deletion of Ledger ([Sequence Diagram](#diag2))** <br />
The deletion of a specified ledger is performed in two phases: Ledger Retrieval and Ledger Delete.
1. __Phase 0: Instruction retrieval__ 
    1. At ```ManualTracker.handleMainMenu()```, the user's input is registered via ```java.util.Scanner``` instance.
    1. Input is parsed by ```InputParser.parseInput()```, and ```ManualTracker.packet``` is set to the returned ```CommandPacket``` instance.
    1. The ```commandString``` of the ```CommandPacket``` instance is evaluated, and the corresponding handle method() is executed.<br>
    In this case, ```handleDeleteLedger()``` will be called.
1. __Phase 1: Ledger retrieval__
    1. At ```handleDeleteLedger()```, the following processes will be executed:
        1. A new instance of ```RetrieveLedgerHandler``` is created. The input String array will be passed into 
        ```CreateLedgerHandler.setRequiredParams()``` to set required params for a successful parse.
        1. ```RetrieveledgerHandler.handlePacket(packet)``` is called to handle params in the packet.
            1. Refer to the section on [Param Handling](#impl_logic) for more details pertaining to general param handling. 
            1. For ```CreateLedgerHandler```, the ```handleSingleParam``` abstract method will be implemented as shown in the [following table](#table2):
                * Note that only one of the two params need to be invoked from the input. 
1. __Phase 2: Ledger Deletion__
    1. From ```ManualTracker```, call ```ledgerList.RemoveItemAtCurrIndex()``` to remove the ledger specified by the index set to modify earlier.

#### <a name = table2></a> Param Handling Behavior
    
|ParamType|ParamType String| Expected Param | Operation | Verification method |
|---------|----------------|----------------|-----------|---------------------|
|```PARAM.DATE```|"/date"|Various format of date in string, eg. "2020-03-02"| Call ```ledgerList.setIndexToModify()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnDate(packet)```|
|```PARAM.INDEX```|"/index"|Valid index on the list from 1 onwards.|Call ```ledgerList.setIndexToModify()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnIndex(packet)```|

#### <a name = diag2></a> Sequence Diagram

![](uml_images/images_updated/manualTrackerDeleteLedgerSeqDiagram.png)

**<a name = entryseq></a>Entry Tracker: Edit of entries** <br />
The editing of details within the entry is performed in two phases: Entry Retrieval and Entry Edit.

![](uml_images/images_updated/entryTrackerEditEntrySeqDiagram0.png)

1. __Phase 0: Instruction retrieval__ 
    1. At ```EntryTracker.handleMainMenu()```, the user's input is registered via ```java.util.Scanner``` instance.
    1. Input is parsed by ```InputParser.parseInput()```, and ```EntryTracker.packet``` is set to the returned ```CommandPacket``` instance.
    1. The ```commandString``` of the ```CommandPacket``` instance is evaluated, and the corresponding handle method() is executed.<br>
    In this case, ```handleEditEntry()``` will be called.
1. __Phase 1: Entry retrieval([Sequence Diagram](#diag3))__
    1. At ```handleEditEntry()```, the following processes will be executed:
        1. A singleton instance of ```RetrieveEntryHandler``` is retrieved. The input String array will be passed into 
        ```retrieveEntryHandler.setRequiredParams()``` to set required params for a successful parse.
        1. ```retrieveEntryHandler.handlePacket(packet)``` is called to handle params in the packet.
            1. Refer to the section on [Param Handling](#impl_logic) for more details pertaining to general param handling. 
            1. For ```retrieveEntryHandler```, the ```handleSingleParam``` abstract method will be implemented as shown in the [following table](#table3).
            1. From ```EntryTracker```, call ```entryList.getItemAtCurrIndex``` to retrieve the entry specified by the index set to modify earlier.

#### <a name = table3></a> Param Handling Behavior

|ParamType|ParamType String| Expected Param | Operation | Verification method |
|---------|----------------|----------------|-----------|---------------------|
|```PARAM.INDEX```|"/index"|Valid index on the list <br/>from 1 onwards.|Call ```entryList.setIndexToModify()``` <br/>to set index of retrieved item. | ```ParamChecker.checkAndReturnIndex(packet)```|

#### <a name = diag3></a> Sequence Diagram 

![](uml_images/images_updated/entryTrackerEditEntrySeqDiagram2.png)

1. __Phase 2: Entry edit ([Sequence Diagram](#diag4))__ 
    1. Following Phase 1, the following processes will be executed:
        1. The singleton instance of ```EditEntryHandler``` is retrieved. There is no need to call ```EditEntryHandler.setRequiredParams()```
        ; this command does not require params to modify. Instead, it acceps any params supplied and performs the edit accordingly.
        1. `editeEntryHandler.setPacket(packet)` is called to set packet.
    1. ```EditEntryHandler.handlePacket()``` is called to handle params in the packet.
        1. Refer to the section on [Param Handling](#impl_logic) for more details pertaining to general param handling. 
        1. For ```EditEntryHandler```, the ```handleSingleParam``` abstract method will be implemented as shown in the [following table](#table4).

#### <a name = table4></a> Param Handling Behavior           

|ParamType|ParamType String| Expected Param | Operation | Verification method |
|---------|----------------|----------------|-----------|---------------------|
|```PARAM.AMOUNT```|"/amt"|Positive Double in 2 decimal places|Call ```entryList.setAmount()``` to set amount | ```ParamChecker.checkAndReturnDoubleSigned(packet)```|
|```PARAM.TIME```|"/time"|Various format of time in string, eg. "15:00"|Call ```entryList.setTime()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnTime(packet)```|
|```PARAM.INC```|"-i"|Income entry type flag|Call ```entryList.setEntryType(EntryType.INC)``` to set index of retrieved item. | ```nil```|
|```PARAM.EXP```|"-e"|Expense entry type flag|Call ```entryList.setEntryType(EntryType.EXP)``` to set index of retrieved item. | ```nil```|
|```PARAM.DESCRIPTION```|"/desc"|Description in string, ';' character is illegal.|Call ```entryList.setDescription()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnDescription(packet)```|
|```PARAM.CATEGORY```|"/cat"|A set of strings that corresponds with entry type|Call ```entryList.setCategory()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnCategories(packet)```|

#### <a name = diag4></a> Sequence Diagram 

![](uml_images/images_updated/entryTrackerEditEntrySeqDiagram3.png)

<div style="page-break-after: always;"></div>

&nbsp;
