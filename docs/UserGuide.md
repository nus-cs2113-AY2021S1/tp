<link rel="stylesheet" href="trippie.css">

# User Guide

![Trippie Logo](https://i.imgur.com/jSwGL7O.png)

<!-- @@author felixhalim -->
## Table of Contents (Felix)
Below is a list of contents that is included in this document.
* [1.0 Introduction](#10-introduction-shawn-tan)
* [2.0 About this document](#20-about-this-document-felix)
* [3.0 How to use](#30-how-to-use-ivander)
* [4.0 Quick Start](#40-quick-start-ivander)
* [5.0 Features](#50-features-shawn-tan)
    * [5.1 Creating a new trip: **`new trip`**](#51-creating-a-new-trip-new-trip-ivander)
    * [5.2 Loading an existing trip: **`load trip`**](#52-loading-an-existing-trip-load-trip-ivander)
    * [5.3 Editing a trip: **`edit trip`**](#53-editing-a-trip-edit-trip-ivander)
    * [5.4 Deleting a trip: **`delete trip`**](#54-deleting-a-trip-delete-trip-ivander)
    * [5.5 Viewing help: **`help`**](#55-viewing-help-help-felix)
    * [5.6 Adding a place: **`add`**](#56-adding-a-place-add-felix)
    * [5.7 Listing all places: **`list /p`**](#57-listing-all-places-list-p-felix)
    * [5.8 Searching for place: **`search`**](#58-searching-for-place-search-wei-shuang)
    * [5.9 Deleting place: **`delete /p`**](#59-deleting-place-delete-p-wei-shuang)
    * [5.10 Editing budget: **`budget`**](#510-editing-budget-budget-wei-shuang)
    * [5.11 Adding an expense: **`buy`**](#511-adding-an-expense-buy-shawn-tan)
    * [5.12 Listing all expenses: **`list /e`**](#512-listing-all-expenses-list-e-shawn-tan)
    * [5.13 Deleting an expense: **`delete /e`**](#513-deleting-an-expense-delete-e-shawn-tan)
    * [5.14 Converting Currency: **`convert /to`**](#514-convert-currency-convert-to-kian-en)
        * [5.14.1 Convert to Local Currency (SGD): **`convert /toSGD`**](#5141-convert-to-local-currency-sgd)
        * [5.14.2 Convert to Foreign Currency (FOR): **`convert /toFROM`**](#5142-convert-to-foreign-currency-for)
    * [5.15 Exiting the Trippie: **`exit`**](#515-exiting-the-trippie-exit-kian-en)
    * [5.16 Saving the data](#516-saving-the-data-kian-en)
* [6.0 FAQ](#60-faq-kian-en)
* [7.0 Command Summary](#70-command-summary-wei-shuang)

<!-- @@author-->
<!-- @@author ShawnTanzc -->
## 1.0 Introduction (Shawn Tan)
Trippie is a command-line app to plan any of your upcoming trips. It is tailored to match the needs of student travellers. Whether you are planning for your summer holiday, or a leisure trip on your student exchange program, Trippie got you covered!

![Screenshot of Trippie](https://i.imgur.com/29TB8E8.png)

_A screenshot of Trippie's command line interface_

<!-- @@author-->
<!-- @@author felixhalim -->
## 2.0 About this document (Felix)
This section helps you in using the various features available in Trippie.

Note the following symbols and formatting used in this document:

<div class="info box" markdown="1">

ℹ️ This box indicates important information for you to take note of.

</div>

<div class="warning box" markdown="1">

⚠️ This box indicates notable warnings that you should be aware of.

</div>

`command` : This grey highlight shows command line input or output
```
This block style shows the command line output of Trippie
```
<!-- @@author-->
<!-- @@author ivanderjmw -->
## 3.0 How to use (Ivander)
Begin with the [Quick Start](#40-quick-start-ivander) section if it is your first time using Trippie. All the commands are listed out in the table of contents. To find out the details on each command, just click and follow the links. Please check the [FAQ](#60-faq-kian-en) section for frequently encountered problems.


## 4.0 Quick Start (Ivander)
Written below are the steps to be taken to set up the application:
1. Verify that you have Java 11 or above installed in your computer by running `java --version`.
2. Download the latest `trippie.jar` from [here](https://github.com/AY2021S1-CS2113T-W11-2/tp/releases/tag/v2.1).
3. Copy the file to a folder where you want to run it from.
4. Using a command line tool, navigate to the path of `trippie.jar`, by entering `cd path/to/file`
    * If your operating system is Windows, use Command Prompt.
    * If your operating system is MacOS, use Terminal.
6. Enter `java -jar trippie.jar` in your command line tool and press enter.
7. If the setup is correct, you should see a welcome message. Then, it is now ready to go.
8. Create your first trip by entering [`new trip`](#51-creating-a-new-trip-new-trip-ivander) to the command line. Then, enter your trip name, start date, exchange rate, currency, and budget.
9. Add your first place using [`add`](#56-adding-a-place-add-felix). Then buy your first item with [`buy`](#511-adding-an-expense-buy-shawn-tan).
10. Try listing your places by entering `list /p` and your expenses with `list /e`.
11. `exit` and check your folder to check your Trippie files!

<!-- @@author-->
<!-- @@author ShawnTanzc -->
## 5.0 Features (Shawn Tan)
Written below are the various features available in Trippie.

<div class="info box" markdown="1">

ℹ️ **Notes on command format:**
* The **`bolded syntax`** represents the key command.
* Words in `CAPITAL LETTERS` are arguments that you need to supply. 
    * e.g. **`add`**`/n PLACE_NAME /d DAY /t TIME`
    * `PLACE_NAME`, `DAY`, `TIME` are arguments needed accordingly.
* Items in `[square brackets]` are optional. 
    * e.g. **`list`**`/p [/d DAY]`

</div>
<!-- @@author-->
<!-- @@author ivanderjmw -->
### 5.1 Creating a new trip: `new trip` (Ivander)
Creates a new trip. You will be prompted to enter some details (i.e. name, start date, exchange rate, and budget) after entering this command. Trippie allows you to organize multiple trips, thus you can use this command to create many trips conveniently.

Format: **`new trip`**

Example: **`new trip`**

<br/>

Expected Output:
```
Enter your new trip's name: Summer Exchange Peru
Enter your new trip's start date (dd-mm-yyyy):12-12-2020
Enter the foreign exchange rate:2.65
Enter the foreign currency abbreviation (eg. MYR): SOL
Enter your budget for the trip (in SGD):500
Added the trip 4  Summer Exchange Peru 12-12-2020
```

<div class="warning box" markdown="1">

⚠️ **Warning** 
* Enter your trip name without slashes i.e. "\\" or "/".
* Input your start date with the given format.
* Currency abbreviation should only be 3 characters.
* Budget is accurate only to 2 decimal places.

</div>

---

### 5.2 Loading an existing trip: `load trip` (Ivander)
Loads a specific trip from the list of saved trips in `trippie_data`. Trippie will prompt you to enter the index of your preferred trip and sets it as the current trip. Before entering this command, Trippie will automatically load your last opened trip.

Format: **`load trip`**

Example: **`load trip`**

<br/>

Expected output:

```
Here are your existing trips.
1. Chigago Trip [1 Day]
2. Jakarta Festival [3 Days]
3. Wonderful Singapore [5 Days]
4. Malaysia KL [6 Days]

Which one do you want to load? Enter the index:3
I found a file in your directory!
Setting up the file now...
```

<div class="warning box" markdown="1">

⚠️ **Warning** 
* You need to enter a valid trip index!

</div>

---

### 5.3 Editing a trip: `edit trip` (Ivander)

Want to change the name of your trip? Or maybe you want to update your trip start date. We got you covered with this `edit trip` command. 

Enter the command then specify which trip you want to edit by index. Then fill in the new values of the trip attributes, or leave it blank to not change it.

Trip attributes are: trip name, trip start date, foreign exchange rate, foreign currency abbreviation, budget.

Format: **`edit trip`**

Example: **`edit trip`**

<br/>

Expected output:
```
Here are your existing trips.
1. Once too you [2 Days]
2. Vibrant Singapore [No places or expenses added yet]

Which one do you want to edit? Enter the index:2
Current trip is set to 'Vibrant Singapore'.
Reading the Trippie files now...
Leave the field empty if you do not want to change the specified detail.
Enter edited name, [old: Vibrant Singapore]:
Enter your new trip's start date (dd-mm-yyyy) [old:30-12-2020]:28-12-2020
Enter the foreign exchange rate [old:1.0]:1.01
Enter the foreign currency abbreviation (eg. MYR) [old: SGD]:
Enter your budget for the trip (in SGD) [old:900.0]:1000
Edited the trip 2 Vibrant Singapore 28-12-2020
```
---

### 5.4 Deleting a trip: `delete trip` (Ivander)

Deletes a specific trip from the list of saved trip in `trippie_data`. Trippie will prompt you to enter the index of your preferred trip.

Prompts user confirmation by entering *Y* (Yes) or *N* (No) before deleting the trip. If yes, then the trip will be permanently deleted (including the txt file).

Format: **`delete trip`**

Example: **`delete trip`**

<br/>

Expected output:
```
Here are your existing trips.
1. Once too you [2 Days]
2. Vibrant Singapore [6 Days]
3. Unused trip [1 Day]

Which one do you want to edit? Enter the index:3
Current trip is set to 'Some test'.
Reading the Trippie files now...
Are you sure you want to permanently delete 'Unused trip'? [Y/N]:y
Deleted trip Unused trip.
```
---

<!-- @@author-->
<!-- @@author felixhalim -->
### 5.5 Viewing help: `help` (Felix)
Shows a concise list of commands available. Think of this as a cheatsheet. If you are new to Trippie, you can take a look of what and how you can utilize each commands via `help`.

Format: **`help`**

Example: **`help`**

<br/>

Expected output:
```
[new trip] |   Creates a new trip. You will be prompted to enter some details
           |   Format: new trip
___________|______________________________________________________________
[load trip]|   Loads a specific trip from the saved list
           |   Format: load trip
___________|______________________________________________________________
[edit trip]|   Edits a specific trip from the saved list
           |   Format: edit trip
___________|______________________________________________________________
...        |   ...
...        |   ...
...        |   ...
```

---
### 5.6 Adding a place: `add` (Felix)
Adds a place to your trip. In the example below, we're adding "Dinner at Marina Bay Sands" as the `PLACE_NAME`, for `DAY` 2, from 1800 to 2000 as the `TIME`.

Format: **`add`**`/n PLACE_NAME /d DAY /t TIME`

* `PLACE_NAME` is the label of the place.
* `DAY` indicates which day you want to visit the place.
* `TIME` indicates the time in the 24-hour format. Separate starting time and ending time with `to`.

Example: **`add`**`/n Dinner at Marina Bay Sands /d 2 /t 1800 to 2000`

<br/>

Expected output:
```
Got it. I've added this place:
1800 - 2000 Dinner at Marina Bay 
Now you have 1 place in the list.
```
<div class="warning box" markdown="1">

⚠️ **Warning** 
* The tags should be in the correct order.
* Use a 24-hour format for time.

</div>

---

### 5.7 Listing all places: `list /p` (Felix)
Displays all places in a timetable format. If you specify a `DAY`, only places on the specified day will be displayed. In the example below, we are viewing the full timetable for the trip.

Format: **`list /p`**`[/d DAY]`

* `DAY` indicates the specific day which timetable should display.
* If `[/d DAY]` is not specified, displays the full timetable.

Example: **`list /p`**

<br/>

Expected output:
```
DAY 2: (2020-10-21)
[1] 1800 - 2000 Dinner at Marina Bay Sands
```

Example: **`list /p /d 2`**

<br/>

Expected output:
```
DAY 2: (2020-10-21)
1800 - 2000 Dinner at Marina Bay Sands
```
<!-- @@author-->
---
<!-- @@author weishuangtan -->

### 5.8 Searching for place: `search` (Wei Shuang)
Displays all places that includes the search keyword. In the example below, we are searching for the `KEYWORD` 'Dinner'.

Format: **`search`**`KEYWORD`

Example: **`search`**`Dinner`

<br/>

Expected output:
```
Here is your search result:
1.1800 - 2000 Dinner at Marina Bay Sands on DAY 2
```

---

### 5.9 Deleting place: `delete /p` (Wei Shuang)
Deletes the specified place from the list. In the example below, we are deleting "Dinner at Marina Bay Sands" which has the `PLACE_INDEX` of 1.

Format: **`delete /p`**`PLACE_INDEX`

* `PLACE_INDEX` refers to the index of the place that you want to delete.

Example: **`delete /p`**`1`

<br/>

Expected output:
```
Noted. I've removed this place from the place list.
1800 - 2000 Dinner at Marina Bay Sands
Now you have 0 place in the list.
```

<div class="warning box" markdown="1">

⚠️ **Warning** 
* Refer to the index from `list /p` to delete. Enter a valid place index.

</div>

---

### 5.10 Editing budget: `budget` (Wei Shuang)
Edits a budget to keep track of in expenditure list. The budget is stored in the local currency SGD. In the example below, we are setting the total budget to SGD100.

Format: **`budget`**`AMOUNT`

* `AMOUNT` indicates your planned budget to spend for the trip, in SGD.

Example: **`budget`**`100`

<br/>

Expected output:
```
Successfully set your total budget to 100.00
```

<div class="warning box" markdown="1">

⚠️ **Warning** 
* Watch out when shrinking your budget.

</div>
<!-- @@author-->
---
<!-- @@author ShawnTanzc -->
### 5.11 Adding an expense: `buy` (Shawn Tan)
Adds an item and its final cost in foreign currency into the current trip's expense list. In the example below, we are adding an expense "R&B Brown Sugar" as `ITEM_NAME`, which has the `FINAL_COST` of $3.00, on `DAY` 2.

Format: **`buy`**`/n ITEM_NAME /d DAY /c FINAL_COST`

* `ITEM_NAME` is the label of the item.
* `FINAL_COST` indicates the cost of the item you purchased.
* `DAY` indicates the specific day when you bought the item.

Example: **`buy`**`/n R&B Brown Sugar /d 2 /c 3.00`

<br/>

Expected output:
```
Got it! I've added the following item: Day 2: R&B Brown Sugar - $3.00
Now you have 1 item in the list.
```

---

### 5.12 Listing all expenses: `list /e` (Shawn Tan)
Displays all the items bought in the expenditure list and reminds you on your budget limit.

Format: **`list /e`**

Example: **`list /e`**

<br/>

Expected output:
```
Total budget: 1325.00 SOL (500.00 SGD)
Expense List:
[1] Day 2: R&B Brown Sugar - 3.00 SOL
Your current total spending is 3.00 SOL (1.13 SGD)
Your current remaining budget is 1322.00 SOL (498.87 SGD)
You are still spending within your budget.
[----------] 0.2%
```
<div class="warning box" markdown="1">

⚠️ **Warning** 
* If you were to buy an item which exceeds the total budget, Trippie will send a warning message and recommend you to increase your budget limit.

</div>



Expected output:
```
Total budget: 141680.00 SWE (5600.00 SGD)
Expense List:
[1] Day 1: glassware - 8000.00 SWE
[2] Day 1: jersey - 19900.50 SWE
[3] Day 1: tissue - 2.00 SWE
[4] Day 2: picture - 200.00 SWE
[5] Day 3: clothes - 2000.00 SWE
[6] Day 5: house - 120000.00 SWE
Your current total spending is $150102.50 SWE (5932.91 SGD)
WARNING! You have exceeded your initial budget.
Recommended action: Increase budget limit.
```

---

### 5.13 Deleting an expense: `delete /e` (Shawn Tan)
Deletes the specified place from the list. In the example below, we are removing "R&B Brown Sugar", which has the `PLACE_INDEX` of 1.

Format: **`delete /e`**`EXPENSE_INDEX`

* `EXPENSE_INDEX` refers to the index of the expense based on the list that you want to delete.

Example: **`delete /e`**`1`

<br/>

Expected output:
```
Noted. I've removed this item from the expenditure list.
Day 2: R&B Brown Sugar - 3.00 SOL
Now you have 0 item in the list.
```
<!-- @@author-->
---
<!-- @@author tengkianen -->
### 5.14 Convert currency: `convert /to` (Kian En)
Converts an amount to the requested currency. The requested currency can either be in SGD, or the foreign currency specified when creating the trip.

#### 5.14.1 Convert to Local Currency (SGD)
Converts an amount from foreign currency (FOR) to local currency (SGD).

Format: **`convert /toSGD`**`AMOUNT`

* `AMOUNT` refers to the amount to be converted into the preferred currency.

Example: **`convert /toSGD`**`500`

<br/>

Expected output:
```
Processing... Please Wait.
That amount in your local currency is 188.68 SGD.
```

#### 5.14.2 Convert to Foreign Currency (FOR)
Converts an amount from local currency (SGD) to foreign currency (FOR).

Format: **`convert /toFOR`**`AMOUNT`

* `AMOUNT` refers to the amount to be converted into the preferred currency.

Example: **`convert /toFOR`**`500`

<br/>

Expected output:
```
Processing... Please Wait.
That amount in your foreign currency is 1325.00 SOL.
```

---

### 5.15 Exiting the Trippie: `exit` (Kian En)
Exits the program and auto-saves.

Format: **`exit`**

Example: **`exit`**

<br/>

Expected output:
```
Bye. Hope to see you again soon!
```

---

### 5.16 Saving the data: (Kian En)
Saves Trippie's data in a folder named `trippie_data` automatically. This folder will be created in the same location as your `trippie.jar`.

---
## 6.0 FAQ (Kian En)
Below are some Frequently Asked Questions you might have:

**Q:** Will I be able to view my trips after exiting Trippie?

**A:** Yes, Trippie is able to automatically save your trips for your viewing every time.

**Q:** Can I transfer my old Trippie files to another computer?

**A:** Yes, you can copy the `trippie_data` folder to your new computer before running Trippie there.

**Q:** Does Trippie need Internet?

**A:** No, Trippie is fully offline.

<!-- @@author-->
<!-- @@author weishuangtan -->
## 7.0 Command Summary (Wei Shuang)
Below is a summary of all trippie commands. You can check this section if you need a quick reference.


**Action** | **Format, Examples**
------------ | -------------
**`new trip`**|`new trip`
**`load trip`**|`load trip`
**`edit trip`**|`edit trip`
**`delete trip`**|`delete trip`
**`help`**|`help`
**`add`**|`add /n PLACE_NAME /d DAY /t TIME`<br>e.g., `add /n Dinner at Marina Bay Sands /d 2 /t 1800 to 2000`
**`list /p`**|`list /p [/d DAY]` <br>e.g., `list /p`
**`search`**|`search KEYWORD` <br>e.g., `search Dinner`
**`delete /p`**|`delete /p PLACE_INDEX` <br>e.g., `delete 1`
**`budget`**|`budget AMOUNT`<br>e.g., `budget 100`
**`buy`**|`buy /n ITEM_NAME /d DAY /c FINAL_COST` <br>e.g., `buy /n R&B Brown Sugar /d 2 /c 3.00`
**`list /e`**|`list /e`
**`delete /e`**|`delete /p EXPENSE_INDEX` <br>e.g., `delete 1`
**`convert /to`**|`convert /toCURRENCY AMOUNT` <br>e.g., `convert /toSGD 500`<br>`convert /toFOR 500`
**`exit`**|`exit`

<!-- @@author-->
