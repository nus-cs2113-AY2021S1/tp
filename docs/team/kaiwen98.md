# Looi Kai Wen's Project Portfolio Page

## Project: FinanceIt

## Overview
FinanceIt is an all-in-one desktop application that handles the finance tracking needs of university students who are 
comfortable with a CLI interface. In the application, it consists of 5 sub-application that helps student to manage
their finance. <br/>

### Summary of Contributions: Code management
1. Project Management
    * Managed releases `v1.0` - `v-2.0` on GitHub (2 releases)
1. Contributions to the project on RepoSense. <br/>
    * **Code contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kaiwen98&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
    
1. Contributions to TP repository 
    1. Report and maintenance of [project issues and milestones](https://github.com/AY2021S1-CS2113-T16-1/tp/issues?q=+is%3Aissue+author%3Akaiwen98+)
        1. Bug reporting: #16, #19. #3
        1. Features (ManualTracker): #24

    1. [Pull Requests](https://github.com/AY2021S1-CS2113-T16-1/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Akaiwen98) to TP for code contribution 
        1. Functional code contributions:
            1. Dynamic Table Printer to be used for output throughout the software: #7
            1. Contributions to ManualTracker feature: #28, #1
            1. Contributions to code quality and organisation: #99, #105, #70, #50   
        1. Error Handling contributions: #14
        1. Code testing contributions: #240, #57, #45
    1. Contributions to documentations
    
### Summary of Contributions: Documentation
## Contributions to UG
The below section details my contribution to the User Guide.
# <a name = manualTracker> </a> 3.2 Features : Manual Tracker
Users can manage lists of entries, which are known as ledgers. Each list represents a single date of record.
> Example
    If I wish to record my income and expenditures on 30 October 2020, I will use the program as follows:
    1. Use Manual Tracker to create a ledger of date 20-10-03
    2. Open the ledger of date 20-10-03 
    3. Use Entry Tracker to create entries to record the transactions for that particular date.
    
    
No. |Content|
----|------|
3.2.1|[Add Ledger](#manualTracker1)
3.2.2|[Remove Ledger](#manualTracker2)
3.2.3|[Open Ledger](#manualTracker3)
3.2.4|[Show Ledger List](#manualTracker4)
3.2.5|[Show commands](#manualTracker5)
3.2.6|[Exit](#manualTracker6)

## <a name = manualTracker1> </a> 3.2.1 Manual Tracker 1: Add ledger
Add a ledger to the record, representing a date.

>Syntax

    new {PARAM_TYPE} {PARAM} 
    
Param Type| Param | Param Format
----------|-------|------------|
`/date`|Date of the ledger, and all the entries under that ledger.| Input string of the date in YYMMDD, YY-MM-DD or YY-M-D

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_new.png)
    
## 3.2.2 <a name = manualTracker2> </a> Manual Tracker 2: Remove ledger
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
    
##  3.2.3 <a name = manualTracker3> </a> Manual Tracker 3: Open ledger
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
    
## 3.2.4 <a name = manualTracker4> </a> Manual Tracker 4: Show ledger list
Shows the record of ledgers that has been added.

>Syntax

    list

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_list.png)

## 3.2.5 <a name = manualTracker5> </a> Manual Tracker 5: Print command list
Prints available commands that users can enter in for manualTracker.

>Syntax

    commands

> Example: 

![](developerGuide_images/screenshots_manualtracker/manual_commands.png)
    
## 3.2.6 <a name = manualTracker6> </a> Manual Tracker 6: Exit to Main Menu
Exit to main menu where users can choose another feature to use.

>Syntax

    exit

> Example: 
    
![](developerGuide_images/screenshots_manualtracker/manual_exit.png)


    
# 3.3 <a name = entryTracker> </a> Main Feature : Entry Tracker
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
    
No. |Content|
----|------|
3.3.1|[Add Entry](#entryTracker1)
3.3.2|[Edit Entry](#entryTracker2)
3.3.3|[Remove Ledger](#entryTracker3)
3.3.4|[Show Entry List](#entryTracker4)
3.3.5|[Show commands](#entryTracker5)
3.3.6|[Show Categories](#entryTracker6)
3.3.7|[Exit](#entryTracker7)

## 3.3.1 <a name = entryTracker1> </a> Entry Tracker 1: Add entry
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

    add {PARAM_TYPE} {PARAM}

        
Param Type| Param | Param Format
----------|-------|------------|
`/time`|Time of the entry.| Input string of the date in HHMM, HHMMSS or H.
`/amt`|Amount involved in the transaction.| Input floating point number in 2 d.p
`/cat`|Category of transaction. | Input string belonging in the set: {tpt, fd, tvl, shp, bll, slr, alw}
`-i or -e`|Type of transaction. | No parameter required. 

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_create.png)


## <a name = entryTracker2> </a> 3.3.2 Entry Tracker 2: Edit entry
Add an entry to the ledger record.
Users only need specify the param to edit, there is no need to fill out all params.

__Note__
* While the index is compulsory to be provided, the rest of the params are not compulsory.
The users will include the params that they wish to modify.
* Hence, a sample command ```entry edit /id 1 /time 1600``` will modify entry at __index 1__ to __time 1600__.

>Syntax

    edit /id {PARAM_INDEX} {PARAM_TYPE} {PARAM} ... 
        
Param Type| Param | Param Format
----------|-------|------------|
`/id`|Index of the entry in the list, where the first entry is of index 1. | Input positive integer
`/time`|Time of the entry.| Input string of the date in HHMM, HHMMSS or H.
`/amt`|Amount involved in the transaction.| Input floating point number in 2 d.p
`/cat`|Category of transaction. | Input string belonging in the set: {tpt, fd, tvl, shp, bll, slr, alw}
`-i or -e`|Type of transaction. | No parameter required. 
        
> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_edit_list.png)

    
## <a name = entryTracker3> </a> 3.3.3 Entry Tracker 3: Remove entry
Remove a specified entry from the record, referenced by id on the list.

>Syntax

    delete {PARAM_TYPE} {PARAM} 
            
Param Type| Param | Param Format
----------|-------|------------|
`/time`|Time of the entry.| Input string of the date in HHMM, HHMMSS or H.
`/id`|Index of the entry in the list, where the first entry is of index 1. | Input positive integer

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_delete_list.png)

    
## <a name = entryTracker4> </a> 3.3.4 Entry Tracker 4: Show entry list
Shows the record of entries that has been added.

>Syntax

    list

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_list.png)

## <a name = entryTracker5> </a> 3.3.5 Entry Tracker 5: Print command list
Prints available commands that users can enter in for manualTracker.

>Syntax

    commands

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_commands.png)

## <a name = entryTracker6> </a> 3.3.6 Entry Tracker 6: Print categories
Prints expenditure category shortcuts that the user can input in entry creation/ edit commands.

>Syntax

    cat

> Example: 

![](developerGuide_images/screenshots_entrytracker/entry_cat.png)

    
## <a name = entryTracker7> </a> 3.3.7 Entry Tracker 7: Exit to Manual Tracker main routine
Exit to Manual tracker where users can choose another ledger.

>Syntax

    exit

> Example: 

![](developerGuide_images/screenshot_entrytracker/entry_exit.png)

## Contributions to DG
The below section details my contribution to the Developer's Guide.

#### 2.1.2.2 ParamHandler
1. After parsing from user input to produce a ```commandPacket``` instance, the instance needs to be handled by a particular ```ParamHandler``` children class,
which processes the ```commandPacket``` attributes to perform a specific function. 

1. Handling of params via```handleParams(packet)```:
    1. Initialize the state of the handler 
        1. Children class of ```ParamHandler``` call ```setRequiredParams()``` to set required Params that need to be parsed successfully to constitute an overall successful parse.
        1. Resetting String arrays in the following ```param``` arrays:
            * ```missingRequiredParams```
            * ```paramsSuccessfullyParsed```
        1. Set the ```CommandPacket``` instance in ```ParamChecker``` by calling ```ParamChecker.setPacket(packet)```.
    1. Call `handleParams()`
        1. For every```paramType``` in the ```CommandPacket``` instance, execute ```handleSingleParam(packet)``` method. 
        1. ```handleSingleParam(packet)``` is an abstract method, and it is implemented by children classes of ```ParamHandler``` depending on the needs and requirements of that particular class.
        1. If the `param` fail to be parsed due to input error, an exception from `ParamChecker`: `ParseFailParamsException` will be caught.
        The error message from `ParamChecker` will be printed.
        1. Else if the `param` parses successfully, it will be added to ```paramsSuccessfullyParsed```
    1. Check if the parse was successful. The condition below that define a successful parse is:
        1. All ```param``` in ```createLedgerCommand.requiredParams``` string array are parsed with no exceptions thrown.
        That is, all `param` in ```createLedgerCommand.requiredParams``` is also in ```paramsSuccessfullyParsed```.
    1. If parse is successful, the process ends gracefully. Else, throw ```InsufficientParamsException()```.

#### 2.1.2.3 CommandHandler
1. Extends `ParamHandler` class. Implements ```handleSingleParams()``` fully, depending on the interactions
between the operation and the `param` that it accepts. 
1. Typically used within Logic Managers to handle processing of `CommandPacket` instances to decide sub-operations
to perform to achieve full operation specified by the user. 
1. Example:`handleDeleteLedger()`
    1. Uses `retrieveLedgerCommand` to interpret the `ledger` instance to deleted, as specified by the user
    1. Retrieves the `ledger` instance and performs delete within the method.   

### 2.2.2 Feature 1: Manual Tracker & Entry Tracker
#### 2.2.2.1 Overview
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

#### 2.2.2.2 Manual Tracker

The Manual Tracker is a feature that allows users to manage Ledgers with create, delete
and open operations. Ledgers is a class that maintains a list of transactions that are 
recorded for a given date. 

The Entry Tracker is fundamentally similar to the Manual Tracker, except it manages ```Entry``` instances
instead of ```Ledger```. Entry Tracker is initialized when a ```Ledger``` instance is "opened", whereby 
the Entry Tracker facilitate the manipulation of the collection of ```Entry``` instances that are associated with
that particular ```Ledger``` instance.

For the sake of brevity, this section will focus on the discussion of the Manual Tracker. Section [2.2.2.3] (#2.2.2.3) will describe
the edit operation of the Entry Tracker, which is sufficiently unique to Manual Tracker operations to merit detailed discussion.

The Manual Tracker is capable of executing the following states of operation:

|States| Operations | 
|--------|----------|
|```MAIN_MENU```|Go to main menu for users to choose the available operations
|```CREATE_LEDGER```|Create a ledger specified by date, and append it to ```ledgerList```.
|```DELETE_LEDGER```|Delete an existing ledger, referenced by date or index.
|```OPEN_LEDGER```|Go to subroutine "Entry Tracker" for the entries recorded  under the specified ledger.

#### 2.2.2.3 Architecture in context

#### 2.2.2.4 Logic Manager and Parser

![](uml_images/images_updated/Handler_Parser.png)

|Class| Function |
|--------|----------|
|```InputParser```| Breaks input string by user into ```commandString``` and a sequence of ```paramTypes```-```param``` pairs. <br><br> The latter subsequence of the string is passed into ParamParser for further processing. <br><br> Information obtained from input parsing will be used to populate an instantiated ```CommandPacket``` instance, which will then be passed to the entity that called the parsing function.
|```ParamParser```| Process the sequence of ```paramTypes```-```param``` pairs and populate the ```paramMap``` in the instantiated ```CommandPacket``` instance.
|```ManualTracker```| [Refer to section above](#handlerAndCommand).
|```EntryTracker```| Omitted for brevity.

#### 2.2.2.5 Logic Manager and Data

![](uml_images/images_updated/Handler_Data.png)

|Class| Function |
|--------|--------|
|```ManualTracker```| [Refer to section above](#handlerAndCommand).
|```EntryTracker```| Omitted for brevity.
|```EntryList```| Omitted for brevity.
|```Entry```| Omitted for brevity.
|```LedgerList```| Extends ItemList. Refer to Ledgers and Entries section for class behavior.
|```Ledger```| Extends DateTimeItem. Refer to Ledgers and Entries section for class behavior.
|```ItemList```| Class with defined list behavior specified with helper methods such as retrieval, checking of Duplicates and deletion.
|```DateTimeItem```| Abstract class that extends ```Item``` class; instances will have ```LocalDate``` or ```LocalTime``` attributes and corresponding helper methods.
|```Item```| Abstract class to define behavior of entities that need are stored in ```ItemList``` instances.



#### 2.2.2.6 Functions with Sequence Diagrams

##### 2.2.2.6.1 Creation of Ledger
1. At ```ManualTracker.handleMainMenu()```, the user's input is registered via ```java.util.Scanner``` instance.
1. Input is parsed by ```InputParser.parseInput()```, and ```ManualTracker.packet``` is set to the returned ```CommandPacket``` instance.
1. The ```commandString``` of the ```CommandPacket``` instance is evaluated, and the corresponding handle method() is executed.<br>
In this case, ```handleCreateLedger()``` will be called.
1. At ```handleCreateLedger()```, the following processes will be executed:
    1. A new instance of ```createLedgerCommand``` is created. The input String array will be passed into 
    ```createLedgerCommand.setRequiredParams()``` to set required params for a successful parse.
    1. A new instance of ```Ledger``` will be instantiated and set to ```createLedgerCommand.currLedger```.
    1. ```createLedgerCommand.handlePacket(packet)``` is called to handle params in the packet.
        1. Refer to the [section on Param Handling](#paramHandling) for more details pertaining to general param handling. 
        1. For ```createLedgerCommand```, the ```handleSingleParam``` abstract method will be implemented as follows:
        
            |ParamType|ParamType String| Expected Param | Operation | Verification method |
            |---------|----------------|----------------|-----------|---------------------|
            |```PARAM.DATE```|"/date"|Various format of date in string, eg. "2020-03-02"| Call ```currLedger.setDate()``` to set date for the ```Ledger``` instance. | ```ParamChecker.checkAndReturnDate(packet)```|
1. From ```ManualTracker```, the configured ```Ledger``` instance will be retrieved from the ```createLedgerCommand``` instance
and added into the ```LedgerList``` instance at ```ManualTracker.ledgerList```.
  
![](uml_images/images_updated/manualTrackerCreateLedgerSeqDiagram.png)


##### 2.2.2.6.1 Deletion of Ledger
The deletion of a specified ledger is performed in two phases: Ledger Retrieval and Ledger Delete.
1. __Phase 0: Instruction retrieval__ 
    1. At ```ManualTracker.handleMainMenu()```, the user's input is registered via ```java.util.Scanner``` instance.
    1. Input is parsed by ```InputParser.parseInput()```, and ```ManualTracker.packet``` is set to the returned ```CommandPacket``` instance.
    1. The ```commandString``` of the ```CommandPacket``` instance is evaluated, and the corresponding handle method() is executed.<br>
    In this case, ```handleDeleteLedger()``` will be called.
1. __Phase 1: Ledger retrieval__
    1. At ```handleDeleteLedger()```, the following processes will be executed:
        1. A new instance of ```retrieveLedgerCommand``` is created. The input String array will be passed into 
        ```createLedgerCommand.setRequiredParams()``` to set required params for a successful parse.
        1. ```deleteLedgerCommand.handlePacket(packet)``` is called to handle params in the packet.
            1. Refer to the section on [Param Handling](#paramHandling) for more details pertaining to general param handling. 
            1. For ```createLedgerCommand```, the ```handleSingleParam``` abstract method will be implemented as follows:
                * Note that only one of the two params need to be invoked from the input. 
            
        |ParamType|ParamType String| Expected Param | Operation | Verification method |
                |---------|----------------|----------------|-----------|---------------------|
                |```PARAM.DATE```|"/date"|Various format of date in string, eg. "2020-03-02"| Call ```ledgerList.setIndexToModify()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnDate(packet)```|
                |```PARAM.INDEX```|"/index"|Valid index on the list from 1 onwards.|Call ```ledgerList.setIndexToModify()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnIndex(packet)```|

1. __Phase 2: Ledger Deletion__
    1. From ```ManualTracker```, call ```ledgerList.RemoveItemAtCurrIndex()``` to remove the ledger specified by the index set to modify earlier.


![](uml_images/images_updated/manualTrackerDeleteLedgerSeqDiagram.png)

#### 2.2.2.7 Entry Tracker: Edit of entries
The editing of details within the entry is performed in two phases: Entry Retrieval and Entry Edit.
1. __Phase 0: Instruction retrieval__ 
    1. At ```EntryTracker.handleMainMenu()```, the user's input is registered via ```java.util.Scanner``` instance.
    1. Input is parsed by ```InputParser.parseInput()```, and ```ManualTracker.packet``` is set to the returned ```CommandPacket``` instance.
    1. The ```commandString``` of the ```CommandPacket``` instance is evaluated, and the corresponding handle method() is executed.<br>
    In this case, ```handleEditEntry()``` will be called.
1. __Phase 1: Entry retrieval__
    1. At ```handleEditEntry()```, the following processes will be executed:
        1. A new instance of ```retrieveEntryCommand``` is created. The input String array will be passed into 
        ```retrieveEntryCommand.setRequiredParams()``` to set required params for a successful parse.
        1. ```retrieveEntryCommand.handlePacket(packet)``` is called to handle params in the packet.
            1. Refer to the section on [Param Handling](#paramHandling) for more details pertaining to general param handling. 
            1. For ```retrieveEntryCommand```, the ```handleSingleParam``` abstract method will be implemented as follows:
            
        |ParamType|ParamType String| Expected Param | Operation | Verification method |
                |---------|----------------|----------------|-----------|---------------------|
                |```PARAM.INDEX```|"/index"|Valid index on the list from 1 onwards.|Call ```entryList.setIndexToModify()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnIndex(packet)```|
        
        1. From ```EntryTracker```, call ```entryList.getItemAtCurrIndex``` to retrieve the entry specified by the index set to modify earlier.

1. __Phase 2: Entry edit__
    1. Following Phase 1, the following processes will be executed:
        1. A new instance of ```editEntryCommand``` is created. There is no need to call ```editEntryCommand.setRequiredParams()```
        ; this command does not require params to modify. Instead, it acceps any params supplied and performs the edit accordingly.
        1. ```editEntryCommand.handlePacket(packet)``` is called to handle params in the packet.
    1. ```editEntryCommand.handlePacket(packet)``` is called to handle params in the packet.
            1. Refer to the section on [Param Handling](#paramHandling) for more details pertaining to general param handling. 
            1. For ```editEntryCommand```, the ```handleSingleParam``` abstract method will be implemented as follows:
            
        |ParamType|ParamType String| Expected Param | Operation | Verification method |
                |---------|----------------|----------------|-----------|---------------------|
                |```PARAM.AMOUNT```|"/amt"|Double in 2 decimal places|Call ```entryList.setAmount()``` to set amount | ```ParamChecker.checkAndReturnDoubleSigned(packet)```|
                |```PARAM.TIME```|"/time"|Various format of time in string, eg. "15:00"|Call ```entryList.setTime()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnTime(packet)```|
                |```PARAM.INC```|"-i"|Income entry type flag|Call ```entryList.setEntryType(EntryType.INC)``` to set index of retrieved item. | ```nil```|
                |```PARAM.EXP```|"-e"|Expense entry type flag|Call ```entryList.setEntryType(EntryType.EXP)``` to set index of retrieved item. | ```nil```|
                |```PARAM.DESCRIPTION```|"/desc"|Description in string, ';' character is illegal.|Call ```entryList.setDescription()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnDescription(packet)```|
                |```PARAM.CATEGORY```|"/cat"|A set of strings that corresponds with entry type|Call ```entryList.setCategory()``` to set index of retrieved item. | ```ParamChecker.checkAndReturnCategories(packet)```|
            
![](uml_images/images_updated/entryTrackerEditEntrySeqDiagram.png)

