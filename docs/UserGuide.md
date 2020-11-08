<h1 align="center">termiNus User Guide</h1>
{:.no_toc}

* Table of contents
{:toc}

## Introduction

termiNus is an interactive Command Line Interface (CLI) task manager for undergraduate students in NUS. 
This program will help them achieve a better grip on their school life as well as assist in better management of their 
daily expenses and be reminded of any library loans. 

Fear not, this guide contains all the features found in termiNus along with detailed explanations on the usage of each
command. We hope this guide is user-friendly! 

## Quick Start

1. Ensure that you have Java 11 or above installed.

2. Download the latest version of `termiNus` from [here](https://github.com/AY2021S1-CS2113-T14-3/tp/releases/latest).

3. Copy the jar file to a new folder.

4. Navigate to the directory which contains `termiNus.jar` using the command prompt. Alternatively, on windows, you
   can open the folder which contains `termiNus.jar` and in the address bar type `cmd` and press enter. It will open up a
   command prompt with that directory.

5. Run termiNus by typing `java -jar termiNus.jar` and press enter.

6. A greeting message will be displayed as shown below

```
Hello from...
     _                           _  _   _
    | |                         (_)| \ | |
    | |_   ___  _ __  _ __ ___   _ |  \| | _   _  ___
    | __| / _ \| '__|| '_ ` _ \ | || . ` || | | |/ __|
    | |_ |  __/| |   | | | | | || || |\  || |_| |\__ \
     \__| \___||_|   |_| |_| |_||_|\_| \_/ \__,_||___/

    ____________________________________________________________
     How can termiNus assist you today?

     Unsure what to type? Start of by typing <help> to see the commands and their usage.
    ____________________________________________________________

```

7. Refer to the Features below for details of each command.

## Features 

**Take note:**

    1. Priority 0: Default , Priority 1: Highest (As number increases, priority decreases) 
    2. 
    3.

### Adding a task: `add`
Adds a new item to the list of todo tasks.

Format: `add task <description> <optional arguments>`

List of `<optional arguments>`:
- `p/<number>` sets the priority of the task.
- `c/<category>` sets the category of the task.
- `date/<dd-MM-yyyy>` sets the date of the task.

🚩: By default, there is **no category and date**, and the **priority is set to 0**.

🚩: Optional arguments can be typed in **any** order.


🚩: Duplicate entry of tasks are **allowed**. 


Example of usage: 

`add task tP meeting`

`add task tP meeting c/cs2113 p/1`

`add task tP meeting p/1 c/cs2113 date/13-05-2020`

`add task tP meeting date/13-05-2020 p/1 c/cs2113`

Output:

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][N] tP meeting (p:1) (category: cs2113) (date: 13 May 2020)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

### Adding recurring tasks: `addr`
Adds multiple tasks to the list of tasks that occur weekly on a given day.

Format: `addr <description> <optional/compulsory arguments>`

List of `<optional arguments>`:
- `p/<number>` sets the priority of the task.
- `c/<category>` sets the category of the task.

List of `<compulsory arguments>`:
- `s/<dd-MM-yyyy>` start date of recurring tasks (inclusive).
- `e/<dd-MM-yyyy>` end date of recurring tasks (inclusive).
- `day/<mon/tue/wed/thu/fri/sat/sun>` day of recurring task.

🚩: Optional and compulsory arguments can be typed in **any** order.


🚩: Duplicate entry of recurring tasks are **allowed**. 


Example of usage:

`addr tp meeting s/26-10-2020 e/27-11-2020 day/fri`

`addr board games club s/26-10-2020 e/27-11-2020 day/wed c/CCA`

`addr board games club s/26-10-2020 day/wed e/27-11-2020 p/1 c/CCA`

Output:

```
    ____________________________________________________________
     Got it. I've added these tasks:
       
     [T][N] board games club (p:1) (category: CCA) (date: 28 Oct 2020)
     [T][N] board games club (p:1) (category: CCA) (date: 04 Nov 2020)
     [T][N] board games club (p:1) (category: CCA) (date: 11 Nov 2020)
     [T][N] board games club (p:1) (category: CCA) (date: 18 Nov 2020)
     [T][N] board games club (p:1) (category: CCA) (date: 25 Nov 2020)
     
     Now you have 5 tasks in the list.
    ____________________________________________________________
```
### Adding a module: `add module`
Add modules to the module list.

Format: `add module <module code> <optional/compulsory arguments>`

🚩: `<module code>` matches 2 or 3 prefix characters, followed by 4 digits and optional suffix.

🚩: All characters in `<module code>` must be in **caps**. 

🚩: Duplicate module code in the same semester will **not be allowed**. 

List of `<optional arguments>`:
- `d/<1 or 0>` sets whether a module is completed or not *(1 for completed, 0 for incomplete)*.

🚩: Modules will be **set to complete by default** if `d/<1 or 0>` is not provided. This feature lets users add incomplete modules and compute a projected CAP when `list modules` is entered.

🚩: Optional and compulsory arguments can be typed in **any** order

List of `<compulsory arguments>`:
- `g/<grade>` grade of the module in **caps** (`A+`, `A`, `A-`, etc).
- `ay/<XXXXSY>` academic year of the module where `X` is an integer and `Y` is `1` or `2` (`2021S1`, `2021S2`, etc...).
- `mc/<MCs>` modular credits of the module. Valid for a range of 0 to 40 MCs.

🚩: All characters in academic year must be in **caps**. 

🚩: Grades must be in **caps**.

Example of usage:

`add module CS2113 g/A+ mc/4 ay/2021S1`

`add module CS2117 mc/4 g/B+ d/0 ay/2021S2`

`add module CS2119 g/C+ d/1 mc/4 ay/2021S2`

Output:

```
    ____________________________________________________________
     Got it. I've added this module:
       [CM][A+] CS2113 (4 MC) (AY2021S1)
     Now you have 3 module(s) in the list.
    ____________________________________________________________
```

🚩: `[CM]` indicates a completed module, and `[IC]` indicates an incomplete module.

### Adding a weblink: `add link`
Add a link for lecture/tutorial sessions through zoom 

Format: `add link m/<module code> <compulsory arguments>`

List of `<compulsory arguments>`:
- `t/<TYPE>` type of use (`lecture`, `tutorial`, `lab`, `project`).
- `u/<URL>`  the link.  

🚩: URL has to begin with either **http:// or https://**. 


🚩: All characters in `<module code>` must be in **caps**. 


Example of usage:

`add link m/CS2113 t/lecture u/https://CS2113Lecture.zoom.com`

Output:

```
    ____________________________________________________________
     Got it. I've added this link:
       CS2113 lecture
       https://cs2113Lecture.zoom.com
     Now you have 1 link(s) in the list.
    ____________________________________________________________
```

### Borrowing a book: `borrow`
Loan a book and add into the book list 

Format: `borrow <book name> <compulsory argument>`

List of `<compulsory argument>`:
- `date/<DD-MM-YYYY>` date of borrow (`23/11/2020`).

🚩: The due date will be fixed to **1 month** from the date of loan. 

🚩: Duplicate book name will **not be allowed**. 

Example of usage:

`borrow cooking book date/11-11-2011`

Output:

```
    ____________________________________________________________
     Got it. I've added this book: cooking book
       (Loan Date: 11 Nov 2011)
       (Due Date: 11 Dec 2011)
    ____________________________________________________________
```

### Adding an expense item: `spend`
Add an expense item into the expense list.

Format: `spend <description> <compulsory arguments> <optional arguments>`

List of `<compulsory arguments>`:
- `v/<value>` sets the amount of money spent

List of `<optional arguments>`:
- `currency/<currency>` sets the currency of the expense.
- `date/<yyyy-MM-dd>` sets the date of the expense.

🚩: By default, the currency is set to "SGD" and date is set to the date when the user executed the `spend` command.

Example of usage:

`spend lunch v/5`

`spend lunch v/5 currency/CNY`

`spend lunch v/5 date/2020-10-28`

`spend lunch v/5 currency/USD date/2020-11-08`

Output:

```
    ____________________________________________________________
     Got it. I've added this expense item:
       lunch (5.00 USD) (date: Sunday, November 8, 2020)
     Now you have 9 expense items in the list.
    ____________________________________________________________

```

### Creating module folders: `makefolders`
Make folders for all modules in the module list according to academic year.

Format: `makefolders`

Example of usage:

`makefolders`

Output:

```
    ____________________________________________________________
     Creating module folders...
     Created folder/sub-folders for CS1010 at ./modules/AY2021S1/CS1010/
     Created folder/sub-folders for CS1231 at ./modules/AY1920S1/CS1231/
     Created folder/sub-folders for CS2113 at ./modules/AY2021S1/CS2113/
     Created folder(s) for 3 module(s).
    ____________________________________________________________
```

### Displaying tasks on list: `list`
Lists all the tasks.

Format: `list tasks`

🚩: Lists all the tasks **including** recurring task.

Example of usage:

`list tasks`

Output:

```
    ____________________________________________________________
     Here are the task(s) in your list:

     1.[T][N] tP meeting (p:1) (category: cs2113)
     2.[T][N] iP meeting (p:2) (category: cs2113)
     3.[T][N] assignment submission (p:2) (category: cg2028)
     4.[T][N] board games club (p:1) (category: CCA) (date: 28 Oct 2020)
     5.[T][N] board games club (p:1) (category: CCA) (date: 04 Nov 2020)
     6.[T][N] board games club (p:1) (category: CCA) (date: 11 Nov 2020)
    ____________________________________________________________
```

### Displaying tasks based on priority: `list`
Lists all the tasks with the same priority.

Format: `list tasks p/<priority>`

🚩: `<priority>` must be **non-negative**.

Example of usage:

`list tasks p/2`

Output:

```
    ____________________________________________________________
     Here are the task(s) of this priority in your list:

     1.[T][N] iP meeting (p:2) (category: cs2113)
     2.[T][N] assignment submission (p:2) (category: cg2028)
    ____________________________________________________________
```

### Displaying tasks based on category: `list`
Lists all the tasks with the same category.

Format: `list tasks c/<category>`

🚩: category is **case-sensitive**.

Example of usage:

`list tasks c/cs2113`

Output:

```
    ____________________________________________________________
     Here are the tasks of this category in your list:

     1.[T][N] iP meeting (p:2) (category: cs2113)
     2.[T][N] lecture quiz (p:2) (category: cs2113)
    ____________________________________________________________
```
### Displaying weblinks on list: `list`
Lists all the links.

Format: `list links`

Example of usage:

`list links`

Output:

```
    ____________________________________________________________
     Here are the links in your list:

     1.CS2113 lecture
       https://cs2113Lecture.zoom.com
     2.CS2028 lecture
       https://zoom.com.sg
    ____________________________________________________________

```
### Displaying books borrowed and returned on list: `list`
List all books loaned and returned. 

Format: `list books`

Example of usage:

`list books`

Output:

```
    ____________________________________________________________
     Here are the books in your list:

     1.[B][L] cooking book
         (Loan Date: 11 Nov 2011)
         (Due Date: 11 Dec 2011)
     2.[B][R] java book
         (Loan Date: 10 Oct 2020)
         (Due Date: 10 Nov 2020)
    ____________________________________________________________
```
### Displaying modules on list: `list`
List all the modules in the module list and shows the computed, projected CAP and completed MCs.

Format: `list modules`

Example of usage:

`list modules`

Output:

```
    ____________________________________________________________
     Here is a list of your module(s):
     
     1.[CM][A-] GER1000 (4 MC) (AY2021S1)
     2.[CM][A+] GET1029 (4 MC) (AY2021S1)
     3.[CM][A+] CS2113 (4 MC) (AY2021S1)
     4.[IC][B] GES1041 (4 MC) (AY2021S2)
    ____________________________________________________________
     Current CAP: 4.83
     Projected CAP: 4.50
     Total MCs completed: 12
    ____________________________________________________________
```
### Displaying expense items on list: `list`
List all the expense items in the expense list and shows total amount of money spent for each currency.

Format: `list expenses <optional arguments>`

List of `<optional arguments>`:
- `currency/<currency>` displays all the expense items with currency `CURRENCY` as a list.
- `date/<yyyy-MM-dd>` displays all the expense items with date in the format of yyyy-MM-dd.
- `for/{DAY,WEEK,MONTH,YEAR}` Displays all the expense items for today/this week/this month/this year.

🚩: Expense list is sorted based on date (from the most recent to the least recent). If the date of two expense items 
are the same, the expense list is sorted based on currency lexicographically.

🚩: `currency`, `date`, and `for` arguments can be used together.

🚩: `date` must be in the format of `yyyy-MM-dd`, e.g. `2020-11-08`.

🚩: `for` argument is case-insensitive, e.g. `list expenses for/week`.

Example of usage:

`list expenses`

Output:

```
    ____________________________________________________________
     Here are the expense item(s) in your list:

     dinner (0.60 A) (date: Monday, November 9, 2020)
     breakfast (0.10 SGD) (date: Sunday, November 8, 2020)
     book (0.50 SGD) (date: Sunday, November 8, 2020)
     bread (0.20 USD) (date: Sunday, November 8, 2020)
     lunch (5.00 USD) (date: Sunday, November 8, 2020)
     bottle (3.00 SGD) (date: Saturday, November 7, 2020)
     pen (1.00 SGD) (date: Saturday, November 7, 2020)
     earphone (0.30 SGD) (date: Saturday, November 7, 2020)
     tablet (0.40 USD) (date: Saturday, November 7, 2020)

     Your total expense for today is:
     0.60 SGD
     5.20 USD

     Your total expense for this week is:
     4.90 SGD
     5.60 USD

     Your total expense for this month is:
     0.60 A
     4.90 SGD
     5.60 USD

     Your total expense for this year is:
     0.60 A
     4.90 SGD
     5.60 USD
    ____________________________________________________________

```

`list expenses currency/USD`

Output:

```
    ____________________________________________________________
     Here are the expense item(s) in your expense list of currency USD:
     bread (0.20 USD) (date: Sunday, November 8, 2020)
     lunch (5.00 USD) (date: Sunday, November 8, 2020)
     tablet (0.40 USD) (date: Saturday, November 7, 2020)

     The total amount listed:
     5.60 USD
    ____________________________________________________________
    
```

`list expenses date/2020-11-08`

Output:

```
    ____________________________________________________________
     Here are the expense item(s) in your expense list of date Sunday, November 8, 2020:
     breakfast (0.10 SGD) (date: Sunday, November 8, 2020)
     book (0.50 SGD) (date: Sunday, November 8, 2020)
     bread (0.20 USD) (date: Sunday, November 8, 2020)
     lunch (5.00 USD) (date: Sunday, November 8, 2020)

     The total amount listed:
     0.60 SGD
     5.20 USD
    ____________________________________________________________

```  

`list expenses for/WEEK`

Output:

```
    ____________________________________________________________
     Here are the expense item(s) in your expense list for this week:
     breakfast (0.10 SGD) (date: Sunday, November 8, 2020)
     book (0.50 SGD) (date: Sunday, November 8, 2020)
     bread (0.20 USD) (date: Sunday, November 8, 2020)
     lunch (5.00 USD) (date: Sunday, November 8, 2020)
     bottle (3.00 SGD) (date: Saturday, November 7, 2020)
     pen (1.00 SGD) (date: Saturday, November 7, 2020)
     earphone (0.30 SGD) (date: Saturday, November 7, 2020)
     tablet (0.40 USD) (date: Saturday, November 7, 2020)

     The total amount listed:
     4.90 SGD
     5.60 USD
    ____________________________________________________________

```  

`list expenses for/DAY`

Output:

```
    ____________________________________________________________
     Here are the expense item(s) in your expense list for today:
     breakfast (0.10 SGD) (date: Sunday, November 8, 2020)
     book (0.50 SGD) (date: Sunday, November 8, 2020)
     bread (0.20 USD) (date: Sunday, November 8, 2020)
     lunch (5.00 USD) (date: Sunday, November 8, 2020)

     The total amount listed:
     0.60 SGD
     5.20 USD
    ____________________________________________________________

```


### Deleting a task from the list: `delete`
Deletes a task from the list

Format: `delete task <taskIndexNumber>`

🚩: `<taskIndexNumber>` corresponds to the index given on `list tasks` command output.

Example of usage:

`delete task 2`

Output: 

```
    ____________________________________________________________
     Noted. I've removed this task:
       [T][N] iP meeting (p:2)
     Now you have 5 task(s) in the list.
    ____________________________________________________________

```
### Deleting all tasks of a certain priority: `delete`
Delete tasks of the same priority

Format: `delete tasks p/<priority>`

🚩: `<priority>` must be **non-negative**.

Example of usage:

`delete tasks p/2`

Output:

```    
    ____________________________________________________________
     Noted. I've removed all these task(s) with the same priority:
    ____________________________________________________________
     [T][N] tP meeting (p:2)
     [T][N] iP meeting (p:2)
     
     Now you have 3 task(s) in the list.
    ____________________________________________________________

```
### Deleting all tasks of a certain category: `delete`
Delete tasks of the same category

Format: `delete tasks c/<category>`

🚩: `<category>` is **case-sensitive**.

Example of usage:

`delete tasks c/cs2113`

Output:

```    
    ____________________________________________________________
     Noted. I've removed all these task(s) with the same category:
    ____________________________________________________________
     [T][Y] tP meeting (p:0) (category: cs2113)
     [T][N] iP meeting (p:2) (category: cs2113)
     
     Now you have 3 task(s) in the list.
    ____________________________________________________________

```
### Deleting a link from the list: `delete`
Deletes a link from the list 

Format: `delete link <linkIndexNumber>`

🚩: `<linkIndexNumber>` corresponds to the index given on `list links` command output.

Example of usage:

`delete link 2`

Output: 

```
    ____________________________________________________________
     Noted. I've removed this link:
       CS2113 lecture
       https://cs2113Lecture.zoom.com
     Now you have 2 link(s) in the list.
    ____________________________________________________________

```

### Deleting a module from the list: `delete`
Deletes a module from the list 

Format: `delete module <moduleIndexNumber>`

🚩: `<moduleIndexNumber>` corresponds to the index given on `list modules` command output.

Example of usage:

`delete module 2`

Output: 

```
    ____________________________________________________________
     Noted. I've removed this module:
       [A+] CS1010 (4 MC) (AY1920S1)
     Now you have 7 module(s) in the list.
    ____________________________________________________________
```

### Deleting an expense item from the list: `delete`

Deletes an expense items from the list.

Format: `delete expense <expenseIndexNumber>`

🚩: `<expenseIndexNumber>` corresponds to the index given on `list expenses` command output.

Example of usage:

`delete expense 2`

Output: 

```
    ____________________________________________________________
     Noted. I've removed all these expense item(s)

     breakfast (0.10 SGD) (date: Sunday, November 8, 2020)
    ____________________________________________________________

```

### Deleting all the expense items of a certain currency: `delete`

Delete expense items of the same currency

Format: `delete expenses currency/<currency>`

Example of usage:

`delete expenses currency/SGD`

Output:

```
    ____________________________________________________________
     Noted. I've removed all these expense item(s)

     earphone (0.30 SGD) (date: Saturday, November 7, 2020)
     pen (1.00 SGD) (date: Saturday, November 7, 2020)
     book (0.50 SGD) (date: Sunday, November 8, 2020)
     bottle (3.00 SGD) (date: Saturday, November 7, 2020)
    ____________________________________________________________

```

🚩: If there is no expense item to be deleted, the output will be as follows:

```
    ____________________________________________________________
     There is no expense item deleted.

    ____________________________________________________________

```

### Deleting all expense items of the same date: `delete`

Deletes expense items of the same date

Format: `delete expenses date/<yyyy-MM-dd>`

🚩: `date` argument must be in the format of `yyyy-MM-dd`, e.g. `2020-11-08`.

Example of usage:

`delete expenses date/2020-11-08`

Output:

```
    ____________________________________________________________
     Noted. I've removed all these expense item(s)

     bread (0.20 USD) (date: Sunday, November 8, 2020)
     lunch (5.00 USD) (date: Sunday, November 8, 2020)
    ____________________________________________________________

```

### Marking a task as done: `done`
Marks a given task as done.

Format: `done task <taskIndexNumber>`

🚩: `<taskIndexNumber>` corresponds to the index given on `list tasks` command output.

Example of usage:

`done task 1`

Output:

```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [Y] tP meeting
    ____________________________________________________________
```

### Setting a module as complete: `done`
Sets a module as complete.

Format: `done module <moduleIndexNumber>`

🚩: `<moduleIndexNumber>` corresponds to the index given on `list modules` command output.

Example of usage:

`done module 1`

Output:

```
    ____________________________________________________________
     Nice! I've marked this module as complete:
       [CM][A-] GER1000 (4 MC) (AY2021S1)
    ____________________________________________________________
```

### Marking a book as returned: `return`
Marks a given book as returned.

Format: `return <bookIndexNumber>`

🚩: `<bookIndexNumber>` corresponds to the index given on `list books` command output.

Example of usage:

`return 2`

Output:

```
    ____________________________________________________________
     Nice! I've marked this book as returned:
       [R] java book
    ____________________________________________________________
```
### Setting the priority of a task: `set`
Sets the priority of an existing task.

Format: `set <taskIndexNumber> p/<priority>`

🚩: `<taskIndexNumber>` corresponds to the index given on `list tasks` command output.

🚩: `<priority>` must be **non-negative**.

Example of usage:

`set 1 p/3`

`set 2 p/4`

Output:

```
    ____________________________________________________________
     Nice! I've set the priority of this task to: 4
    ____________________________________________________________
```

### Setting the category of a task: `category`
Sets the category of an existing task.

Format: `category <taskIndexNumber> c/<category>`

🚩: `<taskIndexNumber>` corresponds to the index given on `list tasks` command output.

🚩: `<category>` is **case-sensitive**

Example of usage:

`category 1 c/CCA`

Output:

```
    ____________________________________________________________
     Nice! I have set the category of this task:
       [T][N] tP meeting (p:0) (category: CCA)
    ____________________________________________________________
```

### Setting the date of a task: `date`
Sets the date of an existing task.

Format: `date <taskIndexNumber> date/<dd-MM-yyyy>`

🚩: `<taskIndexNumber>` corresponds to the index given on `list tasks` command output.

Example of usage:

`date 1 date/11-11-2020`

Output:

```
    ____________________________________________________________
     Nice! I have set the date of this task:
       [T][N] study for finals (p:0) (category: st2334) (date: 11 Nov 2020)
    ____________________________________________________________
```
### Printing task calendar: `calendar`
Prints a calendar with tasks from current date to given number of days.

Format: `calendar d/<daysToPrint>`

🚩: `<daysToPrint>` must be **non-negative** 

Example of usage:

`calendar d/7`

Output:
```
    ____________________________________________________________
     Today's date is: 26 Oct 2020
     Here's your tasks for the next 7 day(s).
    ____________________________________________________________
     MONDAY - 26 Oct 2020
     [T][N] finish tutorial (p:2) (date: 26 Oct 2020)
    ____________________________________________________________
     TUESDAY - 27 Oct 2020
     [T][N] tp meeting (p:0) (category: cs2113) (date: 27 Oct 2020)
     [T][N] meet with friend (p:1) (category: personal) (date: 27 Oct 2020)
    ____________________________________________________________
     THURSDAY - 29 Oct 2020
     [T][N] tp v2.0 submission (p:0) (category: cs2113) (date: 29 Oct 2020)
    ____________________________________________________________
```

### Searching for tasks with keyword: `find`

Finds all items in the corresponding item list with matching description.

Format: `find {tasks,links,books,expenses,modules} k/<keyword>`

🚩: `<keyword>` is **case-insensitive**.

🚩: `<keyword>` can be a **single word** or a **phrase**.
 
🚩: `<keyword>` must contain **whole words** from the item description, e.g `tp meeting`, `ip` etc.

🚩: Incomplete keywords will **not be matched**. e.g `mee`, `t` etc.

Example of usage:

`find tasks k/tp meeting`

Output:

```
    ____________________________________________________________
     Here are the matching items in your list:
     1.[T][N] tp meeting (p:0) (category: cs2113)
     2.[T][N] tp meeting 2 (p:0)
    ____________________________________________________________

```

`find expenses k/dinner`

Output:

```
    ____________________________________________________________
     Here are the matching items in your list:
     1.dinner (0.60 A) (date: Monday, November 9, 2020)
    ____________________________________________________________

```

### Clearing all tasks: `clear`
Clears all tasks, lists, books, modules from the list 

Format: `clear all`

Example of usage:

`clear all`

Output:

```
    ____________________________________________________________
     Noted. I've cleared everything.
    ____________________________________________________________

```
### Getting help: `help`
list all the available commands and their usage.

Format: `help`

Example of usage:

`help`

Output:

```
    ____________________________________________________________
     add: Adds a task to the task list.
     Parameters: TASK_NAME <optional arguments>
     List of <optional arguments>
       - p/<number> sets the priority of the task.
       - c/<category> sets the category of the task.
       - date/<dd-MM-yyyy> sets the date of the task.
     Example: add task example_task <optional arguments>

     addr: Adds multiple tasks to the list of tasks that occur weekly on a given day.
     Parameters: TASK_NAME <optional/compulsory arguments>
     List of <optional arguments>:
       - p/<number> sets the priority of the task.
       - c/<category> sets the category of the task.
       - date/<dd-MM-yyyy> sets the date of the task.
     List of <compulsory arguments>:
       - s/<dd-MM-yyyy> start date of recurring tasks (inclusive)
       - e/<dd-MM-yyyy> end date of recurring tasks (inclusive).
       - day/<mon/tue/wed/thu/fri/sat/sun> day of recurring task.
     Example: addr example_task <optional/compulsory arguments>

     borrow: Adds a book to the book list.
     Example: borrow example_book  date/DD-MM-YYYY

     bye: Exits the program.
     Example: bye

     calendar: Sets the date of a given task in the list.
     Example: calendar d/<daysToPrint>

     category: Sets the category of a task identified by the task index number in the task list
     Parameters: INDEX c/CATEGORY
     Example: category 1 c/Academics

     clear: Clears all tasks in the task list.
     Example: clear

     date: Sets the date of a given task in the list.
     Example: date <taskIndex> date/DD-MM-YYYY

     delete:
     1. Deletes the task identified by the index number used in the task listing.
     Parameters: INDEX
     Example: delete task 1
          Optional parameter 1: p/PRIORITY
             Deletes all the tasks with PRIORITY.
             Example: delete tasks p/1
          Optional parameter 2: c/CATEGORY
             Deletes all the tasks with CATEGORY.
             Example: delete tasks c/cs2113.
     2. Deletes the link identified by the index number used in the link listing.
     Parameters: INDEX
     Example: delete link 1
     3. Deletes the expense items identified by the index number used in the expense list.
          Optional parameter 1: currency/CURRENCY
             Deletes all the expense items with CURRENCY.
             Example: delete expenses currency/SGD
          Optional parameter 2: date/yyyy-MM-dd
             Deletes all the expense items on the date in the format of yyyy-MM-dd
             Example: delete expenses date/2020-11-07

     done: Marks the task/module identified by the index number used in the listing as done.
     Parameters: <type> INDEX
     Accepted <type>: task, module
     Example: done task 1

     find: Finds all items whose descriptions containthe specified keywords (case-insensitive) and displays them as a list with index numbers.
     Parameters: {tasks, links, books, expenses, modules} k/KEYWORDS
     Example: find expenses k/buy

     help: Shows program usage instructions.
     Example: help

     list: Displays all items in the list with index numbers.
     Example: list all
     1. Displays all the tasks in the task list.
          Optional parameter 1: tasks p/PRIORITY
             Displays all the tasks with priority PRIORITY as a list.
             Example: list tasks p/1
          Optional parameter 2: tasks c/CATEGORY
             Displays all the tasks with CATEGORY as a list.
             Example: list tasks c/cs2113
          Optional parameter 3: tasks sorted
             Displays all the tasks sorted by priority     2. Displays all the books in the book list.
             Example: list books
     3. Displays all the links in the link list.
             Example: list links
     4. Displays all the modules in the module list.
             Example: list modules
     5. Displays all the expense items in the expense list.
          Optional parameter 1: currency/CURRENCY
             Displays all the expense items with currency CURRENCY as a list.
             Example: list expenses currency/SGD
          Optional parameter 2: date/yyyy-MM-dd
             Displays all the expense items with date in the format of yyyy-MM-dd.
             Example: list expenses date/2020-11-07
          Optional parameter 3: for/{DAY, WEEK, MONTH, YEAR}
             Displays all the expense items for today/this week/this month/this year.
             Example: list expenses for/YEAR

     makefolders: Make folders for your modules in the modulelist

     return: Marks the book identified by the index number used in the book list as returned.
     Parameters: INDEX
     Example: return 2

     spend: Adds an expense item to the expense list.
     Parameters: DESCRIPTION <compulsory/optional argument>
     List of <compulsory arguments>:
       - v/<value> sets the value of the expense item.
     List of <compulsory arguments>:
       - currency/<currency> sets the currency of the expense item.
       - date/<yyyy-MM-dd> sets the date of the expense item.
     Example: spend example_expense_item v/5.0 currency/SGD date/2020-11-07

     set: Sets the task identified by the index number used in the task listing to the new priority.
     Parameters: INDEX p/PRIORITY
     Example: set 1 p/2
    ____________________________________________________________
  
```

### Exiting and saving the program: `bye`
Terminates and saves the program in the respective text files.

Format: `bye`

Example of usage:

`bye`

Output:

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Frequently Asked Questions

**Q**: How do I transfer my data to another computer? 

**A**: `./tasks.txt`, `./books.txt`, `./links.txt` stores the user data, which can be transferred to another computer or instance of the program by
putting it in the same folder as `termiNus.jar`.

**Q**: Are all commands case-sensitive?

**A**: The case-sensitivity has been stated for the applicable commands. 

 

## Quick Command Reference

Action | Command | Example
----- | ------ | ------
Add task | `add task <description> <optional arguments>` | `add task tP meeting c/cs2113`
Add recurring task | `addr <description> <optional/compulsory arguments>` | `addr board games club s/26-10-2020 e/27-11-2020 day/wed p/1 c/CCA`
Add module | `add module <module code> <optional/compulsory arguments>` | `add module CS2113 g/A+ mc/4 ay/2021S1`
Add web link | `add link m/<module code> <compulsory arguments>` | `add link m/CS2113 t/lecture u/https://cs2113Lecture.zoom.com`
Add book | `borrow <book name> <compulsory argument>` | `borrow cooking book date/11-11-2011`
Add expense item | `spend <description> <compulsory arguments> <optional arguements>` | `spend lunch v/5 currency/SGD date/29-10-2020`
Create module folders | `makefolders` | `makefolders`
List tasks | `list` | `list tasks`
List tasks with priority | `list p/<priority>` | `list p/2`
List tasks with category | `list c/<category>` | `list c/CS2113`
List links | `list` | `list links`
List books | `list` | `list books`
List modules | `list` | `list modules`
List expense items | `list` | `list expenses`
List expense items with currency | `list` | `list expenses currency/USD`
List expense items with date | `list` | `list expenses date/2020-11-08`
List expense items with date range | `list` | `list expenses for/WEEK`
Delete task | `delete task <taskIndexNumber>` | `delete task 2`
Delete tasks by priority | `delete p/<priority>` | `delete tasks p/2`
Delete tasks by category  | `delete c/<category>` | `delete tasks c/cs2113`
Delete link | `delete link <taskIndexNumber>` | `delete link 2`
Delete module | `delete module <moduleIndexNumber>` | `delete module 2`
Delete expense items | `delete expense <expenseIndexNumber>` | `delete expense 2`
Delete expense items by currency | `delete expenses currency/<currency>` | `delete expenses currency/SGD`
Delete expense items by date | `delete expenses date/<yyyy-MM-dd>` | `delete expenses date/2020-11-08`
Mark task as done | `done task <taskIndexNumber>` | `done task 1`
Mark module as complete | `done module <moduleIndexNumber>` | `done module 1`
Mark book as returned | `return <taskIndexNumber>` | `return 2`
Set priority of task | `set <taskIndexNumber> p/<priority>` | `set 1 p/2`
Set category of task | `category <taskIndexNumber> c/<category>` | `category 1 c/CCA`
Set date of task | `date <taskIndexNumber> date/<dd-MM-yyyy>` | `date 1 date/11-11-2020`
Print calendar | `calendar d/<daysToPrint>` | `calendar d/7`
Find tasks with matching keyword | `find {tasks,links,books,expenses,modules} k/<keyword>` | `find tasks k/tp meeting`
Clear everything | `clear all` | `clear all`
Getting help | `help` | `help`
Exiting the program | `bye` | `bye`
