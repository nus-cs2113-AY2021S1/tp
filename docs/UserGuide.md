# FinanceIt: A finance management application with a CLI Interface.
FinanceIt is an all-in-one desktop application that handles the finance tracking needs of university students who are comfortable with a CLI interface.
It includes 5 different finance tools, all of which take in typed commands from the users to execute their respective functions.
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
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
    Status: 
    =====================================================================================================
    = Welcome to Main Menu                                                                              =
    =====================================================================================================
    | No.            |  Feature                                           |  Commands                    |
    -----------------------------------------------------------------------------------------------------
    | [1]            |  Manual Income/Expense Tracker                     |  manual                      |
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    | [2]            |  Recurring Income/Expense Tracker                  |  recur                       |
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    | [3]            |  Account Summary                                   |  acc                         |
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    | [4]            |  Goals Tracker                                     |  goal                        |
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    | [5]            |  Financial Calculator                              |  financial                   |
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    | [6]            |  Toggle Log On or Off                              | logger                       |
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    | [7]            |  Quit The Program                                  |  exit                        |
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    ____________________________________________________________
    >>> 
   ```
   
# Features : Main Menu
Gateway to the various other features of the application. 
Users can enter an input to access the application they wish to use.

## Main Menu 1: Exit
Exits from the program. If you have an outstanding list, it will be saved automatically as lastSave.txt in
the saveStates folder. This folder will be automatically created when you first run the program.

>Syntax

    exit

> Example: 

    example

## Main Menu 2: 
Exits from the program. If you have an outstanding list, it will be saved automatically as lastSave.txt in
the saveStates folder. This folder will be automatically created when you first run the program.

>Syntax

    exit

> Example: 

    example
    
## Main Menu 3: 
Exits from the program. If you have an outstanding list, it will be saved automatically as lastSave.txt in
the saveStates folder. This folder will be automatically created when you first run the program.

>Syntax

    exit

> Example: 

    example
    
    
# Features : Manual Tracker
Users can manage their daily entries of expenses/ income, which is organised in ledgers representing each day of transactions.

## Manual Tracker 1: Add ledger
Add a ledger to the record, representing a date.

>Syntax

    ledger add <param type> <parameter> 
        <param type> @ /date: Date of the ledger, and all the entries under that ledger.
            <parameter:String>: Input string of the date in YYMMDD or YY*MM*DD.

> Example: 

    example
    
## Manual Tracker 2: Remove ledger
Remove a specified ledger from the record, referenced by date or id on the list.

>Syntax

    ledger delete <param type> <parameter> 
        <param type> @ /date: Date of the ledger, and all the entries under that ledger.
            <parameter:String>: Input string of the date in YYMMDD or YY*MM*DD.
        <param type> @ /id: Index of the ledger on the ledger list.
            <parameter:Integer>: Input number that is between 1 and the last index in the ledger list.

> Example: 

    example
    
## Manual Tracker 3: Open ledger
Users will gain access into the entries associated with the specified ledger.
Tracker switches mode to track entries in the ledger that has been opened.
Refer to Feature Entry Tracker onwards for instructions concerned with the 
state of the application following this command.

>Syntax

    ledger open <param type> <parameter> 
        <param type> @ /date: Date of the ledger, and all the entries under that ledger.
            <parameter:String>: Input string of the date in YYMMDD or YY*MM*DD.
        <param type> @ /id: Index of the ledger on the ledger list.
            <parameter:Integer>: Input number that is between 1 and the last index in the ledger list.

> Example: 

    example
    
## Manual Tracker 4: Show ledger list
Shows the record of ledgers that has been added.

>Syntax

    ledger list

> Example: 

    example
    
## Manual Tracker 4: Exit to Main Menu
Exit to main menu where users can choose another feature to use.

>Syntax

    exit

> Example: 

    example
    
# Features : Entry Tracker
Subroutine that is subsidiary off the ManualTracker. 
Users can manage entries associated with the ledger they have opened.

## Entry Tracker 1: Add entry
Add an entry to the ledger record.

>Syntax

    entry add <param type> <parameter> 
        <param type> @ /time: Time of entry
            <parameter:String>: Input string of the time in HHMM or HH*MM*SS.
        <param type> @ /amt: Amount associated with the entry, in $.
            <parameter:Double>: Amount of money in at most 2 decimal places.
        <param type> @ /desc: Details of the transaction that users can refer to when they view the list.
            <parameter:String>: Input string of the description of the transaction.
        <param type> @ /cat: Category of transaction
            <parameter:String>: {tpt, fd, tvl, shp, bll, slr, alw}
                                Only the above strings are allowed for entries. 
                                They represent shortcuts to recognised entry categories that were specified 
                                within the application.
        <param type> @ -i/-e: Represents income and expenses for the entry type.
> Example: 

    example
    
## Entry Tracker 2: Edit entry
Add an entry to the ledger record.
Users only need specify the param to edit, there is no need to fill out all params.
Parameter 1 is however compulsory, as there is a need to reference a particular entry.

>Syntax

    entry edit <param type 1> <parameter 1> <param type> <parameter> 
        <param type 1> @ /id: Index of the ledger in the entry list to edit.
            <parameter 1:Integer>: Input number that is between 1 and the last index in the entry list.
        <param type> @ /time: Time of entry
            <parameter:String>: Input string of the time in HHMM or HH*MM*SS.
        <param type> @ /amt: Amount associated with the entry, in $.
            <parameter:Double>: Amount of money in at most 2 decimal places.
        <param type> @ /desc: Details of the transaction that users can refer to when they view the list.
            <parameter:String>: Input string of the description of the transaction.
        <param type> @ /cat: Category of transaction
            <parameter:String>: {tpt, fd, tvl, shp, bll, slr, alw}
                                Only the above strings are allowed for entries. 
                                They represent shortcuts to recognised entry categories that were specified 
                                within the application.
        <param type> @ -i/-e: Represents income and expenses for the entry type.
> Example: 

    example
    
## Entry Tracker 3: Remove entry
Remove a specified entry from the record, referenced by id on the list.

>Syntax

    entry delete <param type> <parameter> 
        <param type> @ /id: Index of the ledger in the entry list.
            <parameter:Integer>: Input number that is between 1 and the last index in the entry list.

> Example: 

    example
    
## Entry Tracker 4: Show entry list
Shows the record of entries that has been added.

>Syntax

    entry list

> Example: 

    example
    
## Entry Tracker 5: Exit to Manual Tracker main routine
Exit to Manual tracker where users can choose another ledger.

>Syntax

    exit

> Example: 

    example
    
# Features : FinanceTools
FinanceTools contains tools related to financial calculations.

## FinanceTools 1: Simple Interest Calculator
Calculate simple interest earned.

## Parameters
* ```/a``` - Amount (Mandatory)
* ```/r``` - Interest Rate (Mandatory)

>Syntax:
    
    simple /a {AMOUNT} /r {INTEREST_RATE}
   
> Example: 

    simple /a 1000 /r 5
![Example](screenshots/financetools/SimpleInterest(1).PNG)
## FinanceTools 2: Yearly Compound Interest Calculator
Calculate yearly compound interest earned with optional yearly deposit.

### Parameters
* ```/a``` - Amount (Mandatory)
* ```/r``` - Interest Rate (Mandatory)
* ```/p``` - Number of Years (Mandatory)
* ```/d``` - Yearly Deposit (Optional)

>Syntax:
    
    cyearly /a {AMOUNT} /r {INTEREST_RATE} /p {YEARS} /d {YEARLY_DEPOSIT}
   
> Example: 

    cyearly /a 1000 /r 3 /p 2
    cyearly /a 1000 /r 3 /p 2 /d 1200
![Example](screenshots/financetools/YearlyCompoundInterest(1).PNG)
<br />
<br />
![Example](screenshots/financetools/YearlyCompoundInterest(2).PNG)
## FinanceTools 3: Monthly Compound Interest Calculator
Calculate monthly compound interest earned with optional monthly deposit.

### Parameters
* ```/a``` - Amount (Mandatory)
* ```/r``` - Interest Rate (Mandatory)
* ```/p``` - Number of Months (Mandatory)
* ```/d``` - Monthly Deposit (Optional)

>Syntax:
    
    cyearly /a {AMOUNT} /r {INTEREST_RATE} /p {MONTHS} /d {MONTHLY_DEPOSIT}
   
> Example: 

    cmonthly /a 1000 /r 3 /p 2
    cmonthly /a 1000 /r 3 /p 2 /d 100
![Example](screenshots/financetools/MonthlyCompoundInterest(1).PNG)
<br />
<br />
![Example](screenshots/financetools/MonthlyCompoundInterest(2).PNG)
## FinanceTools 4: Cashback Calculator
Calculate cashback earned.

### Parameters
* ```/a``` - Amount (Mandatory)
* ```/r``` - Cashback Rate (Mandatory)
* ```/c``` - Cashback Cap (Mandatory)

>Syntax:
    
    cashb /a {AMOUNT} /r {CASHBACK_RATE} /c {CASHBACK_CAP}
   
> Example: 

    cashb /a 1000 /r 5 /c 1000
    cashb /a 1000 /r 5 /c 10
![Example](screenshots/financetools/Cashback(1).PNG)
<br />
<br />
![Example](screenshots/financetools/Cashback(2).PNG)
## FinanceTools 5: Miles Credit Calculator
Calculate cashback earned.

## Parameters
* ```/a``` - Amount (Mandatory)
* ```/r``` - Miles Rate (Mandatory)

>Syntax:
    
    miles /a {AMOUNT} /r {MILES_RATE}
   
> Example: 

    miles /a 1000 /r 5
![Example](screenshots/financetools/Miles(1).PNG)
## FinanceTools 6: Account Storage
Store account information.
<br />

Additionally, it implements the following operations:
* ```info``` - list account(s) information
* ```clearinfo``` - clear all information
* ```store /rm <ACCOUNT_NO>``` - delete corresponding account number in list

### Parameters
* ```/n``` - Account Name (Optional)
* ```/ir``` - Interest Rate (Optional)
* ```/r``` - Cashback Rate (Optional)
* ```/c``` - Cashback Cap (Optional)
* ```/o``` - Other Notes (Optional)
* ```/rm``` - Account Number (Optional)

>Syntax:
    
    store /n {ACCOUNT_NAME} /ir {INTEREST_RATE} /r {CASHBACK_RATE} /c {CASHBACK_CAP} /o {OTHER_NOTES}
   
> Example: 

    store
    store /n myaccount
    store /n myaccount /ir 2
    store /n myaccount /ir 2 /r 2
    store /n myaccount /ir 2 /r 2 /c 100
    store /n myaccount /ir 2 /r 2 /c 100 /o main account
    store /rm 1
![Example](screenshots/financetools/AccountStorage(1).PNG)
<br />
<br />
![Example](screenshots/financetools/AccountStorage(2).PNG)
<br />
<br />
![Example](screenshots/financetools/AccountStorage(3).PNG)
<br />
<br />
![Example](screenshots/financetools/AccountStorage(4).PNG)
<br />
<br />
![Example](screenshots/financetools/AccountStorage(5).PNG)
<br />
<br />
![Example](screenshots/financetools/AccountStorage(6).PNG)
<br />
<br />
![Example](screenshots/financetools/AccountStorage(7).PNG)

## FinanceTools 7: Command and Calculation History
Store the commands inputted and results from calculations in FinanceTools.

>Syntax:
    
    history
   
> Example: 

    history
![Example](screenshots/financetools/History(1).PNG)
## FinanceTools 8: Exit FinanceTools
Exit FinanceTools to Main Menu.

>Syntax:
    
    exit
   
> Example: 

    exit
![Example](screenshots/financetools/Exit(1).PNG)
## 2. List
Shows full list of appended tasks. You may modify the output format for each task with the appropriate arguements. 

>Syntax

    list <parameter type> <parameter 1> 
        @ <parameter type>:     /format , this parameter is optional.
            # <parameter 1>:        {datetime, day, month, week, year}. 
                                    (You may string these keywords in a single entry for your viewing preferences.)
>Example:

   ```
   >>> list
   1. [T][X] run the dog
   2. [D][O] walk the park (by: FRIDAY SEPTEMBER 2020)
   3. [E][X] run the cat (at: FRIDAY SEPTEMBER 2020 to FRIDAY SEPTEMBER 2020)
   4. [T][X] asad
   ____________________________________________________________________________________________________
   >>> list /format datetime
   1. [T][X] run the dog
   2. [D][O] walk the park (by: 2020/09/18 20:00:00)
   3. [E][X] run the cat (at: 2020/09/18 15:00:00 to 2020/09/18 16:00:00)
   4. [T][X] asad
   ____________________________________________________________________________________________________
   >>> list /format day year
   1. [T][X] run the dog
   2. [D][O] walk the park (by: FRIDAY 2020)
   3. [E][X] run the cat (at: FRIDAY 2020 to FRIDAY 2020)
   4. [T][X] asad
   ____________________________________________________________________________________________________
   >>>
   ```


## 3. Commands/ Command
Displays all outstanding commands executable by the user, with guidance on acceptable syntaxes.

>Syntax

    command
    commands

>Example

   ```
   >>> command

   These are implemented commands that you can use.

   1. bye
           < Exit the program >

   2. list <parameter type> <parameter 1>
           < Show full list of appended tasks. >
           @ <parameter type>:      /format , this parameter is optional.
                   # <parameter 1>:         {datetime, day, month, week, year}. You may string these keywords in a single entry for your viewing preferences.

   3. commands
           < Show full list of commands >

   4. done <integer>
           < Mark a task by number <integer> as done. >
           @ <integer>:     Task number on the list. Out-of-bounds and negative inputs are not allowed.

   5. todo <string>
           < Will be interpreted as input tasks. Input task will then be added to the list. >
           <!> Tasks added this way are assumed to not be done and recorded accordingly.

   6. event <string> <parameter type> <parameter 1> <parameter 2> to <parameter 3> <parameter 4>
           < Add a task which is happening in the future with specific date and time >
           @ <string>:      Task name.
           @ <parameter type>:      /at
                   # <parameter 1>:         Date in this format: YYMMDD or YYYY/M/D.
                   # <parameter 2>:         Start time
                   # <parameter 3>:         Date in this format: YYMMDD or YYYY/M/D. Feel free to omit this if the event starts and ends on the same day.
                   # <parameter 4>:         End time

   7. deadline <string> <parameter type> <parameter 1> <parameter 2>
           < Add a task with a specific deadline>
           @ <string>:      Task name.
           @ <parameter type>:      /by
                   # <parameter 1>:         Date in this format: YYMMDD or YYYY/M/D.
                   # <parameter 2>:         Deadline time

      Note that commands 6 and 7 accepts the following date and time formats:
           @Date: YYYY*MM*DD or YYMMDD or YY/M/D or YY/MM/D
           @Time: HH*MM*SS or HH*MM or HHMM or H
           Note that * represents any non-numeric symbol.

   8. remove <integer>
           < Remove task by number <integer> from list. >
      @ <integer>:  Task number on the list. Out-of-bounds and negative inputs are not allowed.

   9. save <parameter type 1> <parameter 1> <parameter type 2> <parameter 1>
           < Saves current task to local disk. A default folder is: >
           [~\savestates\]
           @ <parameter type 1>:    /name
                   # <parameter 1>:         File name, with or without extension. Only .txt files accepted.
           @ <parameter type 2>:    /dir
                   # <parameter 1>:         Specify a custom save folder path.

   10. load <parameter type 1> <parameter 1> <parameter type 2> <parameter 1>
           < Loads saved task from local disk. A default folder is: >
           [~\savestates\]
           @ <parameter type 1>:    /name
                   # <parameter 1>:         File name, with or without extension. Only .txt files accepted.
           @ <parameter type 2>:    /dir
                   # <parameter 1>:         Specify a custom save folder path.

   11. saves
           < Show full list of save states in default directory >

   12. find <string>
           < Conducts 1-to-1 search over all tasks for the string match. >
   ```


## 4. Done
Marks a task with index number specified as done. The index number starts from 1.
   
>Syntax

   done <integer>
      @ <integer>:     Task number on the list. Out-of-bounds and negative inputs are not allowed.
   
>Example
   
   ```
   >>> list
   1. [T][X] run the dog
   2. [D][O] walk the park (by: FRIDAY 2020)
   3. [E][X] run the cat (at: FRIDAY 2020 to FRIDAY 2020)
   4. [T][X] asad
   ____________________________________________________________________________________________________
   >>> done 1
   Nice! I've marked this task as done:
     [T][O] run the dog
   ____________________________________________________________________________________________________
   ```
## 5. Todo
Adds a Todo task to the list. 
* Contains only the task name. 
* Does not take in datetime inputs.   

>Syntax
 
   todo <string>
        @ <string>:      Task name.
        <!> Tasks added this way are assumed to not be done and recorded accordingly.

>Example

   ```
   >>> todo this is a todo
   Got it! I've added this task:
     [T][X] this is a todo
   Now, you have 1 task  in the list.
   ____________________________________________________________________________________________________
   ```

## 6. Event
Adds an Event task to the list. 
* Contains the task name, the start datetime and the end datetime. 
* The start and end datetimes can be registered via a myriad of input formats.

>Syntax  

    event <string> <parameter type> <parameter 1> <parameter 2> to <parameter 3> <parameter 4>
        @ <string>:      Task name.
        @ <parameter type>:      /at
                # <parameter 1>:         Date in this format: YYMMDD or YYYY/M/D.
                # <parameter 2>:         Start time
                # <parameter 3>:         Date in this format: YYMMDD or YYYY/M/D. 
                                         (Feel free to omit this if the event starts and ends on the same day.)
                # <parameter 4>:         End time


>Example

   ```
   >>> event this is an event /at 200919 1222 thisseperatorcanbeanythingsolongitdoesnotcontainnumbers 1300
   Got it! I've added this task:
     [E][X] this is an event (at: SATURDAY SEPTEMBER 2020 to SATURDAY SEPTEMBER 2020)
   Now, you have 2 tasks in the list.
   ____________________________________________________________________________________________________
   ```
   >Note that commands 6 and 7 accepts the following date and time formats:
        <br>@Date: YYYY*MM*DD or YYMMDD or YY/M/D or YY/MM/D
        <br>@Time: HH*MM*SS or HH*MM or HHMM or H
        <br>Note that * represents any non-numeric symbol.


## 7. Deadline 
Adds an Event task to the list. 
* Contains the task name and a deadline datetime. 
* The deadline datetime can be registered via a myriad of input formats.

>Syntax

    deadline <string> <parameter type> <parameter 1> <parameter 2>
        < Add a task with a specific deadline>
        @ <string>:      Task name.
        @ <parameter type>:      /by
                # <parameter 1>:         Date in this format: YYMMDD or YYYY/M/D.
                # <parameter 2>:         Deadline time
>Example

   ```
   >>> deadline this is a deadline /by 200919 1222
   Got it! I've added this task:
     [D][X] this is a deadline (by: SATURDAY SEPTEMBER 2020)
   Now, you have 3 tasks in the list.
   ____________________________________________________________________________________________________
   ```
   
   >Note that commands 6 and 7 accepts the following date and time formats:
        <br>@Date: YYYY\*MM\*DD or YYMMDD or YY/M/D or YY/MM/D
        <br>@Time: HH\*MM\*SS or HH*MM or HHMM or H
        <br>Note that * represents any non-numeric symbol.

## 8. Remove task 
Removes a task with index number specified from the list. The index number starts from 1.

>Syntax

    remove <integer>
        @ <integer>:  Task number on the list. 
                      (Out-of-bounds and negative inputs are not allowed.)
>Example

   ```
   >>> list
   1. [T][X] this is a todo
   2. [E][X] this is an event (at: SATURDAY 2020 to SATURDAY 2020)
   3. [D][X] this is a deadline (by: SATURDAY 2020)
   ____________________________________________________________________________________________________
   >>> remove 1
   Process completed successfully!
           [NOTE]: You have 2 task/s left.
   Noted! I've removed this task:
     [T][X] this is a todo
   ____________________________________________________________________________________________________
   >>> list
   1. [E][X] this is an event (at: SATURDAY 2020 to SATURDAY 2020)
   2. [D][X] this is a deadline (by: SATURDAY 2020)
   ____________________________________________________________________________________________________
   ```
   
## 9. Manual save 
Saves current list of tasks onto local disk as a save state. 
* Users can specify the save directory, and the name of the save state.
* Should a save state with the same input name already exists, the user will be prompted on whether they wish for the save file to be overwritten or not.

>Syntax

    save <parameter type 1> <parameter 1> <parameter type 2> <parameter 1>
        @ <parameter type 1>:    /name
                # <parameter 1>:         File name, with or without extension. Only .txt files accepted.
        @ <parameter type 2>:    /dir
                # <parameter 1>:         Specify a custom save folder path.

>Example

   ```
   >>> saves
   Save states in [~\savestates\]:
   1.      lastSave.txt
   2.      test.txt
   ____________________________________________________________________________________________________
   >>> save /name test.txt
   The file name supplied already exists in the directory. Are you sure you want to override it? [Y\N]
   ____________________________________________________________________________________________________
   >>> y
   Process completed successfully!
           [NOTE]: Alright, save state below will be overwritten:  [~\savestates\test.txt\]
   Noted! I've saved the list to the following directory: [~\savestates\]

   1. [E][X] this is an event (at: SATURDAY 2020 to SATURDAY 2020)
   2. [D][X] this is a deadline (by: SATURDAY 2020)
   ____________________________________________________________________________________________________
   ```

## 10. Manual load
Loads an existing save state from local disk into the program as a list of tasks. 
* Users can specify the save directory, and the name of the save state.
* Should there be an open list in the program, the user will be prompted on whether they wish for the list to be overwritten or save onto local disk first.

>Syntax

    load <parameter type 1> <parameter 1> <parameter type 2> <parameter 1>
        @ <parameter type 1>:    /name
                # <parameter 1>:         File name, with or without extension. Only .txt files accepted.
        @ <parameter type 2>:    /dir
                # <parameter 1>:         Specify a custom save folder path.
                
>Example

   ```
   >>> list
   1. [T][X] run the dog
   2. [D][O] walk the park (by: FRIDAY 2020)
   3. [E][X] run the cat (at: FRIDAY 2020 to FRIDAY 2020)
   4. [T][X] asad
   ____________________________________________________________________________________________________
   >>> load /name test
   There is a list currently being constructed. Would you like to save it first? [Y\N]
   ____________________________________________________________________________________________________
   >>> y
   Process completed successfully!
           [NOTE]: Alright, Enter the save command now:
   >>> save /name anothersave
   Noted! I've saved the list to the following directory: [~\savestates\]

   1. [T][X] run the dog
   2. [D][O] walk the park (by: FRIDAY 2020)
   3. [E][X] run the cat (at: FRIDAY 2020 to FRIDAY 2020)
   4. [T][X] asad
   ____________________________________________________________________________________________________
   Noted! I've loaded the list from the following directory: [~\savestates\]

   1. [E][X] this is an event (at: SATURDAY 2020 to SATURDAY 2020)
   2. [D][X] this is a deadline (by: SATURDAY 2020)
   ____________________________________________________________________________________________________
   ```

## 11. Show save states
Show full list of save states in the default directory.

>Syntax

    saves

>Example

   ```
   >>> saves
   Save states in [~\savestates\]:
   1.      anothersave.txt
   2.      lastSave.txt
   3.      test.txt
   ____________________________________________________________________________________________________
   ```

## 12. Find 
Conducts 1-to-1 word search over all tasks in their task name.

>Syntax

    find <string>

>Example

  ```
  >>> list
  1. [T][X] run the dog
  2. [D][O] walk the park (by: FRIDAY 2020)
  3. [E][X] run the cat (at: FRIDAY 2020 to FRIDAY 2020)
  4. [T][X] asad
  ____________________________________________________________________________________________________
  >>> find the
  0. [T][X] run the dog
  1. [D][O] walk the park (by: FRIDAY SEPTEMBER 2020)
  2. [E][X] run the cat (at: FRIDAY SEPTEMBER 2020 to FRIDAY SEPTEMBER 2020)
  Total number of results: 3!
  ____________________________________________________________________________________________________
  ```        

# Summary of Commands
## Main menu
No. | Command | Purpose | Syntax
----|---------|---------|-------
1|bye|Exit the program|_bye_ 
2|list | Lists all tasks|_list /format \<format\>_
3|command/s|Lists all commands|_command_, _commands_
4|done|Mark task as done|_done \<index\>_
5|todo|Add todo to list|_todo \<task name\>_
6|event|Add event to list|_event \<task name\> /at \<start datetime\> to \<end datetime\>_
7|deadline|Add deadline to list|_deadline \<taskname\> /by \<deadline datetime\>_  
8|remove|Remove task from list|_remove \<index\>_
9|save|Manually saves list to save state|_save /name \<name\> /dir \<path\>_
10|load|Manually loads save state to list|_load /name \<name\> /dir \<path\>_
11|saves|Shows all save states|_saves_
12|find|Search task by string|_find \<word\>_

## Manual Tracker
No. | Command | Purpose | Syntax
----|---------|---------|-------
1|bye|Exit the program|_bye_ 
2|list | Lists all tasks|_list /format \<format\>_
3|command/s|Lists all commands|_command_, _commands_
4|done|Mark task as done|_done \<index\>_
5|todo|Add todo to list|_todo \<task name\>_
6|event|Add event to list|_event \<task name\> /at \<start datetime\> to \<end datetime\>_
7|deadline|Add deadline to list|_deadline \<taskname\> /by \<deadline datetime\>_  
8|remove|Remove task from list|_remove \<index\>_
9|save|Manually saves list to save state|_save /name \<name\> /dir \<path\>_
10|load|Manually loads save state to list|_load /name \<name\> /dir \<path\>_
11|saves|Shows all save states|_saves_
12|find|Search task by string|_find \<word\>_

## Entry tracker
No. | Command | Purpose | Syntax
----|---------|---------|-------
1|bye|Exit the program|_bye_ 
2|list | Lists all tasks|_list /format \<format\>_
3|command/s|Lists all commands|_command_, _commands_
4|done|Mark task as done|_done \<index\>_
5|todo|Add todo to list|_todo \<task name\>_
6|event|Add event to list|_event \<task name\> /at \<start datetime\> to \<end datetime\>_
7|deadline|Add deadline to list|_deadline \<taskname\> /by \<deadline datetime\>_  
8|remove|Remove task from list|_remove \<index\>_
9|save|Manually saves list to save state|_save /name \<name\> /dir \<path\>_
10|load|Manually loads save state to list|_load /name \<name\> /dir \<path\>_
11|saves|Shows all save states|_saves_
12|find|Search task by string|_find \<word\>_
