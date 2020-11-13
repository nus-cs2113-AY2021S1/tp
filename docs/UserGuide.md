# Fluffle User Guide

## Table of Contents

[Introduction](#introduction) <br>
[About this document](#about-this-document) <br>
[Quick start](#quick-start) <br>
[Features](#features) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Basic CLI commands](#basic-cli-commands) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Viewing help: `help`](#viewing-help-help) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Changing line divider in Fluffle: `divider`](#changing-line-divider-in-fluffle-divider) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Clearing certain object in the database: `clear`](#clearing-a-certain-object-in-the-database-clear) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Closing the program: `exit`](#closing-the-program-exit) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Words list commands](#words-list-commands) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Adding a noun: `noun`](#adding-a-noun-noun) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Adding a verb: `verb`](#adding-a-verb-verb) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Adding an adjective: `adj`](#adding-an-adjective-adj) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Listing words: `list words`](#listing-words-list-words) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Generating three random words: `three words`](#generating-three-random-words-three-words) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Filtering words in word list: `filter words`](#filtering-words-in-word-list-filter-words) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Listing your filter list: `list filter words`](#listing-your-filter-list-list-filter-words) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Clear all of words stored in the database: `reset words`](#clear-all-of-words-stored-in-the-database-reset-words) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Bunnies list commands](#bunnies-list-commands) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Adding bunny idea: `bunny`](#adding-a-bunny-idea-bunny) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Listing bunny ideas: `list bunny`](#listing-bunny-ideas-list-bunny) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Filtering bunny ideas: `filter bunny`](#filtering-bunny-ideas-filter-bunny) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Saving bunny ideas: `save bunny`](#saving-bunny-ideas-save-bunny) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Deleting a bunny idea: `delete bunny`](#deleting-a-bunny-idea-delete-bunny) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Generating a random bunny idea: `random bunny`](#generating-a-random-bunny-idea-random-bunny) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Resetting the entire list of Bunny ideas: `reset bunny`](#resetting-the-entire-list-of-bunny-ideas-reset-bunny) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Names list commands](#names-list-commands) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Generating names from name database: `name`](#generating-names-from-name-database-name) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Listing names from name database: `list name`](#listing-names-from-name-database-list-name) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Finding names from name database: `filter name`](#finding-names-from-name-database-filter-name) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Adding name to the name database: `add name`](#adding-names-from-name-database-add-name) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Removing a name from the name database: `delete name`](#removing-names-from-name-database-delete-name) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[Writings list commands](#writings-list-commands) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Beginning your writing session: `start`](#beginning-your-writing-session-start) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Choosing the “type” of your writing: `type`](#choosing-the-type-of-your-writing-type) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Choosing the “topic” of your writing: `topic`](#choosing-the-topic-of-your-writing-topic) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Tracking your past writings: `stats`](#tracking-your-past-writings-stats) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Getting reminders for your writings scheduled for a specific day: `remind`](#getting-reminders-for-your-writings-scheduled-for-a-specific-date-remind) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Reset the entire writing list: `reset writings`](#reset-the-entire-writing-list-reset-writings) <br>
[FAQ](#faq) <br>
[Glossary](#glossary) <br>
[Command summary](#command-summary) <br>

[Jump to top](#fluffle-user-guide)

## Introduction

Fluffle is a **desktop app for creative writers**, optimized for **use via a Command Line Interface** (CLI). 
It aids users in creating and managing their writings and ideas. 
As a fast-typing writer, this CLI app is highly suitable for you.

[Jump to top](#fluffle-user-guide)

## About This Document

This document aims to provide you with the best way to utilize Fluffle for your own writing experience. 
You may want to first have a look at the glossary to be clear on the terminology used in the document.

[Jump to top](#fluffle-user-guide)

## Quick Start
If you are a first-time user of Fluffle, make use you follow the instructions below:

1. Check that your computer has `Java 11` installed.
2. Get the latest **Fluffle** from [here](https://github.com/AY2021S1-CS2113T-W11-4/tp/releases).
3. Copy the file to the folder you want to use as the home folder.
4. Open a command prompt window in the folder by typing 'cmd' in the navigation bar at the top of the window.
5. In the command prompt window, type`java -jar duke.jar` and press Enter. If the setup is correct, you should see something like below:
```
--------------------------------------------------------------
Write a story with
  ______ _        __  __ _
 |  ____| |      / _|/ _| |
 | |__  | |_   _| |_| |_| | ___
 |  __| | | | | |  _|  _| |/ _ \
 | |    | | |_| | | | | | |  __/
 |_|    |_|\__,_|_| |_| |_|\___|
--------------------------------------------------------------
Hello User! Welcome to Fluffle!
What can I do for you?
--------------------------------------------------------------
What is your name?
```
5. After entering your name, type the `help` command and press Enter to get started.
6. Some example commands you can try:
    1. `list words`: List the vocabulary stored in the word list.
    1. `filter bunny g\fantasy`: Filters bunny ideas that are of the fantasy genre.
    1. `exit`: Exits the app.
7. Refer to the [Features](#features) below for details of each command. A [Command Summary Table](#command-summary) is provided at the end of this User Guide.

[Jump to top](#fluffle-user-guide)

## Features 

(Quang Hung)

This section includes five subsections which will guide you through all the commands in the five main components of Fluffle: 
[`Basic CLI`](#basic-cli-commands), [`Words list`](#words-list-commands), [`Bunnies list`](#bunnies-list-commands), [`Names list`](#names-list-commands) and [`Writings list`](#writings-list-commands).
We will adhere to the following format in explaining the syntax of the commands in Fluffle.

***
**Command format**

* Words that are in `UPPER_CASE` are the compulsory parameters that you should provide for the command.
  
  Example: in the command `noun WORD d\ DESCRIPTION`, `WORD` and `DESCRIPTION` are compulsory parameters that you should key in to add a new noun to the words list.
* Words that are enclosed in `[square brackets]` are the optional parameters. You can either include or exclude them from your command without getting any errors.
  
  Example: in the command `list filter words [limit\PRINT_LIMIT]`, `limit\PRINT_LIMIT` is optional, and you won’t get any errors if you don’t provide it in the command.
* Words that are followed by three dots `...` can be used as many times as you wish.
  
  Example: in the command `filter words by\START -TARGET_STRING…`, you can provide as many target strings as you wish. 
  For more examples, please refer to the [filter words](#filtering-words-in-word-list-filter-words) command below.
***

[Jump to top](#fluffle-user-guide)

### Basic CLI Commands
Basic CLI commands consist of commands that are standard CLI application commands such as help and exit, as well as an aesthetic option to change the line divider.

#### Viewing help: `help`
Shows a list of commands you can use and what the commands do. Prints the instructions at each stage of the program.  
Output:  
```
Type 'help FUNCTION_NAME' to view help for each command.
Available commands:
- help
- divider
- noun
- verb
- adj
- three words
- bunny
- list bunny
- filter bunny
- save bunny
- delete bunny
- random bunny
- list
- list filter words
- start
- filter words
- stats
- reset
- name
- list name
- filter name
- add name
- delete name
- remind
- clear
- exit
```

Format: `help [COMMAND_NAME]`

Example of usage: 

`help`

`help filter name`

Example output: 
```
help filter name
--------------------------------------------------------------
Find all the names from the list of names
Format: filter name NAME
--------------------------------------------------------------
```

#### Changing line divider in Fluffle: `divider`

(Amelia Tan)

Allows the user to change the line divider used in Fluffle.
Format: `divider DIVIDER_OPTION`  
The `DIVIDER_OPTION` is a parameter indicating your preferred type of line divider, and can take values from 1 to 3. The list of dividers is:

1. \----------------------------------------------------------------
1. =^..^=   =^..^=   =^..^=    =^..^=    =^..^=    =^..^=    =^..^=
1. +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

Example usages:
* `divider 1` changes divider option to 1

Example output: 
```
divider 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
Changing line divider...
--------------------------------------------------------------
```
```
divider 3
=^..^=  =^..^=  =^..^=   =^..^=   =^..^=   =^..^=   =^..^=
Changing line divider...
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
```

#### Clearing a certain object in the database: `clear`

(Minh Thong)

Deletes an item of choice from a list in Fluffle. A warning prompt will be generated before deletion.  

Format: 
- `clear type\TYPE_OF_ITEM item\SPECIFICATION_MARK_OF_THE_OBJECT` 

Example usage:  
`clear type\word item\-noungrass`  
Example Output:  
`Are you sure you want to delete this item grass? Yes/no`

There are two major types of clearing:

**Clearing a writing:**

* <strong>Clear a writing with respective order in the arraylist (in which the counter starts from 0)</strong>

Format: `clear type\writing item\0`

Expected effect: Clear the first writing stored in the database (the writing stays on the top expected to see when use command `stats` before apply command `clear`)

* <strong>Clear a writing with respective ID number in the arraylist</strong>

Format: `clear type\writing item\-id5`

Expected effect: Clear the writing(s) with ID 5 in the database

**Clearing a word:**

* <strong>Remove a noun from the database</strong>

Format: `clear type\word item\-noungrass`

Expected effect: Check the existence of word "grass" with type "noun" in the database and then remove it

* <strong>Remove an adjective from the database</strong>

Format: `clear type\word item\-adjbeautiful`

Expected effect: Check the existence of word "beautiful" with type "adj" in the database and then remove it

* <strong>Remove a verb from the database</strong>

Format: `clear type\word item\-verbkill`

Expected effect: Check the existence of word "kill" with type "verb" in the database and then remove it

#### Closing the program: `exit`
Exits the program. All data is auto-saved.  

Format: `exit`

[Jump to top](#fluffle-user-guide)

### Words List Commands
(Yu Xuan)  
Fluffle has a word list which stores the words that you wish to store. It can be used to store words that you wish to research more on, or words that you find interesting and may add value to your stories. The more words you add to it, the more useful it becomes.

#### Adding a noun: `noun`
(Yu Xuan)  
Adds a noun, together with its meaning, into the word bank in the program.  
Format: `noun WORD d\DESCRIPTION`

#### Adding a verb: `verb`
(Yu Xuan)  
Adds a verb, together with its meaning, into the word bank in the program.  
Format: `verb WORD d\DESCRIPTION`

#### Adding an adjective: `adj`
(Yu Xuan)  
Adds an adjective, together with its meaning, into the word bank in the program.  
Format: `adj WORD d\DESCRIPTION`

#### Listing words: `list words`
(Yu Xuan)  
Lists all words stored in the program.  
Format: `list words`

#### Generating three random words: `three words`
(Yu Xuan)  
Generates three random words from the Fluffle word bank. You may use this function when you need inspiration for story ideas.  
Format: `three words`

#### Filtering words in word list: `filter words`

(Quang Hung)

Suppose you need to list out all the nouns in your word bank, or you want to find out the words starting with the 
strings “st” and “cg”. In such cases, you can use the `filter words` command to achieve your goal.

**Note**: You must key in the parameters in order for the application to work properly.

Format: `filter words [-continue] [limit\PRINT_LIMIT] by\TYPE_OF_FILTER -TARGET_STRING...`: 
- `-continue` is an optional parameter. You can use this tag to tell the program to continue filtering on the last filter list. 
Without this tag, the previous filter list would be cleared, and the program will filter on the entire words list. 
- `limit\PRINT_LIMIT` is an optional parameter. You can use this tag to indicate the number of words that you want to print 
from your filter list. If your filter list has more words than the expected limit, the program will ask whether 
you want to print out all the words in the list, or you only want to print out the first PRINT_LIMIT words.
- `by\TYPE_OF_FILTER`: 
    1. If your `TYPE_OF_FILTER` is `type`, the next `TARGET_STRING` should be some combination of `-verb`, `-noun` and `-adjective`.
    2. If your `TYPE_OF_FILTER` is `start`, the next `-TARGET_STRING` can be one or many strings. 
    If you provide more than one target string, the strings should be separated by the dash symbol `-`.
    3. If your `TYPE_OF_FILTER` is `include`: the next `-TARGET_STRING` can be one or many strings. 
    If you provide more than one target string, the strings should be separated by the dash symbol `-`.

**Step-by-step example usages and example outputs:**
* Suppose you have a word list of ten words:

```
  1. house: a building for people to live in, usually for one family
  2. grass: a common wild plant with narrow green leaves and stems 
  3. eat: to put food in your mouth, bite it and swallow it.
  4. computer: an electronic machine that can store, organize and find information
  5. class: a group of students who are taught together
  6. study: the activity of learning or gaining knowledge
  7. beautiful: having beauty; giving pleasure to the senses or to the mind
  8. nice: pleasant or attractive
  9. meaningful : important and serious
  10. attack: to use violence to try to hurt or kill somebody
```

* Now you want to get all nouns and adjectives in your word list. You use the command `filter words by\type -noun -adjective`

```
There are cases that
- You didn't specify the print limit
- Your limit is not an integer

The program will print out all the words in your filter list.

Printing all 7 word(s) from your filter list:
  1. house: a building for people to live in, usually for one family
  2. grass: a common wild plant with narrow green leaves and stems
  3. computer: an electronic machine that can store, organize and find information
  4. class: a group of students who are taught together
  5. beautiful: having beauty; giving pleasure to the senses or to the mind
  6. nice: pleasant or attractive
  7. meaningful : important and serious
```

* Next, you want to get 3 words starting with “h”, “gr”, “co”, “ni”. You can use the command `filter words -continue limit\3 by\start -h -gr -co -ni`. 
After filtering, you get a total of 4 words, but your limit is 3. The program will ask again whether you would like to print all 4 words:

```
Your filter list has more than 3 word(s)
Do you want to print all the words in the filter list? y/n
```

* You want to print all the words, so you key in `y` or `yes`. The expected output is:

```
Printing all 4 word(s) from your filter list:
  1. house: a building for people to live in, usually for one family
  2. grass: a common wild plant with narrow green leaves and stems
  3. computer: an electronic machine that can store, organize and find information
  4. nice: pleasant or attractive
```

* Now you want to get all the words that include the letter “a”. You use the command `filter words -continue limit\1 by\include -a`. 
Since there is only one word, the program will just print it out:

```
Printing all 1 word(s) from your filter list:
  1. grass: a common wild plant with narrow green leaves and stems
```

#### Listing your filter list: `list filter words`

(Quang Hung)

Suppose you want to refer back to the words that have just been filtered recently. 
In this scenario, you can use the command `list filter words` to print out the filtered list.

**Note**: You must key in the parameters in order for the application to work properly.

Format: `list filter words [limit\PRINT_LIMIT]`
- `limit\PRINT_LIMIT` is an optional parameter. You can use this tag to indicate the number of words that you want
 to print from your filtered list. If your filtered list has more words than the expected limit, the program will ask 
 again whether you want to print out all the words in the list, or you only want to print out the first PRINT_LIMIT words.


**Example of usage and example outputs:**
* In this example, suppose you have a filtered list of 5 words. If you want to print the first three words from your 
filtered list, you can use the command `list filter words limit\3`. Since the list has 5 words, the program will ask for your confirmation.


```
Your filter list has more than 3 words
Do you want to print all the words in the filter list? y/n
```

* Now that you don’t want to see the whole list, your input is `n` or `no`.

```
Printing 3 out of 5 word(s) from your filter list:
  1. house: a building for people to live in, usually for one family
  2. grass: a common wild plant with narrow green leaves and stems 
  3. computer: an electronic machine that can store, organize and find information
```

#### Clear all of words stored in the database: `reset words`

(Minh Thong)

Remove all of your added words automatically
```
reset words
--------------------------------------------------------------
--------------------------------------------------------------
```

[Jump to top](#fluffle-user-guide)

### Bunnies List Commands

(Amelia Tan)

Bunnies List commands allows you to collate your list of ideas in one place, making it easy for you to find specific ideas. It can randomly pick one for you to work on so you don’t have to waste time deciding on one on your own.

#### Adding a bunny idea: `bunny`

(Amelia Tan)

Adds a bunny idea to the list of bunny ideas in the current run of the program.
This list is not automatically saved.  

Format: `bunny i\IDEA g\[GENRE]`  

Note:
* If the `GENRE` is not indicated, it is set as "none" by default.
* The program allows duplicate bunnies as some users may have more than 1 idea for the same writing prompt.

Example usages:
- `bunny i\test idea 1`
- `bunny i\test idea 2 g\ fantasy`

Example output:  
```
bunny i\test idea 2 g\ fantasy  
 --------------------------------------------------
 New bunny added!
   idea: test idea 2
   genre:  fantasy
 ---------------------------------------------------
```

#### Listing bunny ideas: `list bunny`

(Amelia Tan)

If you want to view the list of plot bunnies you have collected, you can use the `list bunny` command to print the full list of bunnies.

Format: `list bunny`  

Example usage: `list bunny`  

Example output:  
```
list bunny
-----------------------------------------------------
Here are the bunnies stored in the program:
1.
  idea: test idea 1
  genre: fantasy
  
2.
  idea: test idea 2
  genre: romance

3.
  idea: test idea 3
  genre: none
------------------------------------------------------
```

#### Filtering bunny ideas: `filter bunny`

(Amelia Tan)

Filters specific bunny ideas from the list by searching the idea or the genre for specific key terms.  

Format: `filter bunny i\[IDEA] g\[GENRE]`  

Note:
* If you indicate the tag but leave the parameter blank (eg `filter bunny i\`) then it will not restrict the output of filter by that tag (ie in this case all ideas are selected by the filter).
* You must include either the `i\` or `g\` tag in the command, but the remainder of the command can be left blank.

Example usages:
- filter bunny i\test idea 1  
- filter bunny i\test idea 2 g\ fantasy

Example output:
```
filter bunny g\ fantasy
------------------------------------------------------
1.
  idea: bunny idea 1
  genre: fantasy

2.  
  idea: test idea 2
  genre: fantasy

number bunny filter: 2
-------------------------------------------------------
```

#### Saving bunny ideas: `save bunny`

(Amelia Tan)

Saves the current list of bunnies in the program into the designated text file. The existing text file is automatically read at the start of the program, and is overwritten when the save function is called.

Format: `save bunny`  

Example usage:
- `save bunny`

Example output:  
```
save bunny
--------------------------------------------------------------
Bunny list saved!
--------------------------------------------------------------
```

#### Deleting a bunny idea: `delete bunny`

(Amelia Tan)

You can delete a selected plot bunny from the list of bunny ideas when you have written it or are no longer interested in writing it.

Format: `delete bunny BUNNY_INDEX`
* `BUNNY_INDEX` is the index of the plot bunny you want to delete from the list

Example usage:
- `delete bunny 2`

Example output:  
```
delete bunny 2
--------------------------------------------------------------
Bunny deleted: 
  idea: test idea 2
  genre: none
--------------------------------------------------------------
```

#### Generating a random bunny idea: `random bunny`

(Amelia Tan)

The app can pick a random bunny from your list of plot bunnies for you to work on.

Format: `random bunny`

Example usages:
- `random bunny`

Example output:  
```
random bunny
--------------------------------------------------------------
Random Bunny: 
  idea: some very funny idea
  genre: humor
--------------------------------------------------------------
```

#### Resetting the entire list of Bunny ideas: `reset bunny`

(Amelia Tan)

The app can help you clear the entire list of Bunny ideas if you decide that you want a fresh start with your ideas.

Note:
* There is a failsafe such that if you accidentally type in the `reset bunny` command, you can still prevent it from permanatly deleting your list of Bunny ideas saved in `bunny.txt` by typing in `no` when prompted to type save bunny as confirmation. Any Bunny you previously saved would still remain in the list.

Format: `reset bunny`

Example usages:
- `reset bunny`

Example output:  
```
reset bunny
--------------------------------------------------------------
Type in "save bunny" to confirm your deletion of the bunny ideas!
--------------------------------------------------------------
save bunny
--------------------------------------------------------------
Bunny list saved!
--------------------------------------------------------------
```

[Jump to top](#fluffle-user-guide)

### Names List Commands

(Yong Wee)

Names List commands allow you to look through a pre-built list of character names offline. You may also compile your own list, and the program can help you pick one out at random so you can start writing right away.

#### Generating names from name database: `name`

(Yong Wee)

You can generate a name randomly from the stored database of names.

Format: `name`

Example output:  
```
name
--------------------------------------------------------------
name 2
--------------------------------------------------------------
```

#### Listing names from name database: `list name`

(Yong Wee)

You can display all the names currently stored in the names database. 

Format: `list name`

Example usages:
- `list name`

Example output:  
```
list name
--------------------------------------------------------------
1. name 1
2. name 2
3. name 3
--------------------------------------------------------------
```

#### Finding names from name database: `filter name`

(Yong Wee)

You can get the list of names after filtering from the stored list of names. 

Format: `filter name NAME`

Example usage:
- `filter name 2`

Example output:  
```
filter name 2
--------------------------------------------------------------
name 2
--------------------------------------------------------------
```

Example usage:
- `filter name name`

Example output:  
```
filter name name
--------------------------------------------------------------
1. name 1
2. name 2
3. name 3
--------------------------------------------------------------
```

#### Adding names from name database: `add name`

(Yong Wee)

You can add a name to the list of names currently stored in the names database.

Format: `add name NAME`

Example usages:
- `add name name 3`

Example output:  
```
add name name 3
--------------------------------------------------------------
name 3 has been added to the Names list!
--------------------------------------------------------------
```

#### Removing names from name database: `delete name`

(Yong Wee)

You can remove a name from the list of names currently stored in the names database.

Format: `delete name INDEX`

Example usages:
- `delete name 3`

Example output:  
```
delete name 3
--------------------------------------------------------------
name 3 has been deleted from the Names list!
--------------------------------------------------------------
```

[Jump to top](#fluffle-user-guide)

### Writings List Commands

Writings List allows you to keep a collection of short pieces within the program, and can help you track your writing statistics as motivation to consistently practice writing.

#### Beginning your writing session: `start`

(Minh Thong) 

Requires you to type the following commands for your writings’ configurations:
- `type`
- `topic`  

After choosing the “type” and the “topic” configurations, we can start writing our poems and essays.

#### Choosing the “type” of your writing: `type`

(Minh Thong)

Currently, our application offers you with 2 options of “type”, which are “poem” or “essay”.

#### Choosing the “topic” of your writing: `topic` 

(Minh Thong)

#### (Reserved for v2.1, currently you are only able to choose any arbitrary topic for your writing)
Lists the available topics in the list and pop out the relevant keywords for your writing.
```
start
--------------------------------------------------------------
Please indicate your type by typing in "type" command
type
Please let us know your type of writings, either poem or essay
poem
Please let us know your preferred topic, the available are travelling, life story, ghost story, sci-fi, historical
ghost story
Great! The words we have found for you are killing, death and terrify!
Please let us know the title of your writing
Der Erlkönig
Now you can type your content, terminate by typing "end"
Wer reitet so spät durch Nacht und Wind?
Es ist der Vater mit seinem Kind;
Er hat den Knaben wohl in dem Arm,
Er faßt ihn sicher, er hält ihn warm.

Dem Vater grauset's; er reitet geschwind,
Er hält in Armen das ächzende Kind,
Erreicht den Hof mit Mühe und Not;
In seinen Armen das Kind war tot.
end
Please indicate the date you want to continue with this writing by keying the date in the format "dd/MM/yyyy"
01/12/2020
This Poem, Der Erlkönig has been added
Done! We have added your writing to our storage! You can type "stats" for future reference!
```

#### Tracking your past writings: `stats`

(Minh Thong)

Informs the user detailed specifications like IDs, authors, contents, and other attributes of the writings stored in the database. 
 
```
stats
This is a Poem
Written by Goethe

Id: 5
DER ERLKÖNIG

Wer reitet so spät durch Nacht und Wind?
Es ist der Vater mit seinem Kind;
Er hat den Knaben wohl in dem Arm,
Er faßt ihn sicher, er hält ihn warm.

Dem Vater grauset's; er reitet geschwind,
Er hält in Armen das ächzende Kind,
Erreicht den Hof mit Mühe und Not;
In seinen Armen das Kind war tot.

This writing was created on 18/10/2020
You want to continue on this writing on 01/12/2020
--------------------------------------------------------------
```

#### Getting reminders for your writings scheduled for a specific date: `remind`

(Quang Hung)

Let’s say you are an absent-minded person, and you would like the program to remind you which writings you are about to continue on a specific day. In this scenario, you can use the `remind` command.

**Format**: `remind DATE`
* `DATE` is the date that you want to continue on some of your writings. `DATE` should be in the form of 
dd/MM/yyyy, where dd is a 2-digit day, MM is a 2-digit month, and yyyy is a 4-digit year.

**Example usage and example output:**
* You want to know which writings you want to continue on 01/11/2020, so you use the command `remind 01/11/2020`. 
The output is as follows:
```
On 01/11/2020, you should continue on the following writing(s):
1.
  Id: 5
  Title: DER ERLKÖNIG
```

#### Reset the entire writing list: `reset writings`

(Minh Thong)

Clear the whole writings stored in the database
```
reset writings
--------------------------------------------------------------
We have clear all data in the writings list
--------------------------------------------------------------
```

[Jump to top](#fluffle-user-guide)

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data .txt files of your previous Fluffle home folder.

**Q**: Do I need to leave a space before each input tag?

**A**: Yes, the space is necessary. (e.g., noun computer engineering d\the best engineering major)

**Q**: Do the parameters need to be keyed in in order?

**A**: No. Our code will detect the indicators in any order as long as they are properly spaced out. However, there are 
still some features that will require you to type in the command in the exact order for the program to work properly.

[Jump to top](#fluffle-user-guide)

## Glossary

(Amelia Tan)

**Bunny** is a short form for the slang term plot bunny, often used in creative writing, which refers to short writing ideas that have yet to be written.

[Jump to top](#fluffle-user-guide)

## Command Summary

The following table summarizes all the commands that you need to know when using Fluffle.

| Command      | Format, Examples                                                                                      |
|--------------|-------------------------------------------------------------------------------------------------------|
| divider      | `divider DIVIDER_OPTION` <br>Example: `divider 2` <br> The list of dividers is: <br>   1. -------------------------------------------------------------- <br>  2. =^..^=   =^..^=   =^..^=    =^..^=    =^..^=    =^..^=    =^..^= <br>  3. +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |  
| help         | `help` <br> Look through help guide
| clear        | `clear`<br>`clear type\[TYPE_OF_ITEM] item\[SPECIFICATION_MARK_OF_THE _OBJECT]`<br>TYPE_OF_ITEM:<br>- word: specify that you are trying to clear a word from word list<br>- Writing: specify that you are trying to clear a writing from writing list<br>SPECIFICATION_MARK<br>- If the object is a word, then clear with the respective name<br>- If the object is a writing, then clear with respective id|                                  |
| exit         | `exit`<br>Exits the program.  
| noun         | `noun [WORD] d\[DESCRIPTION]`<br>Example: `noun bus d\vehicle`                                       |
| verb         | `verb [WORD] d\[DESCRIPTION]`<br>Example: `verb eat d\to consume`                                    |
| adjective    | `adj [WORD] d\[DESCRIPTION]`<br>Example: `adj hungry d\having the desire to consume food`            |
| three words  | `three words`<br>Generates three random words from the word list in the program.                     |
| list words   | `list words`<br>Lists all the words stored in the program.
| filter words | `filter [-continue] [limit\PRINT_LIMIT] by\TYPE_OF_FILTER -TARGET_STRING...  `<br>`-continue` (optional): If you want to continue with your last filter list, provide this argument in your command.<br>`limit\PRINT_LIMIT` (optional): indicate number of words that you want to print from your filter list<br>`by\TYPE_OF_FILTER`:<br>- type: arguments can be `-verb`, `-noun` and `-adjective`<br>- start: arguments can be -STRING<br>- include: arguments can be -STRING|
| list filter words  | `list filter [limit\PRINT_LIMIT]`<br>Print the filter list with a limited number of words indicated by `PRINT_LIMIT` (optional parameter)|
| bunny        | `bunny i\IDEA g\[GENRE]`<br>Adds a bunny idea to the list of bunnies in the current run.             |
| list bunny   | `list bunny`<br>Lists all the bunny ideas.                                                           |
| filter bunny | `filter bunny i\IDEA g\[GENRE]`<br>Filters bunny by terms in idea or genre (must have at least 1 filter parameter)|
| save bunny   | `save bunny`<br>Saves all the bunny ideas into a text file.                                          |
| delete bunny | `delete bunny BUNNY_INDEX` <br>Deletes a selected bunny from the list. <br>`BUNNY_INDEX` is the index of the bunny in the list|
| random bunny | `random bunny` <br>Selects a random bunny from the list.|
| name         | `name`<br>Generates a random name.                                                                   |
| list name    | `list name`<br>List all the stored names.                                                            |
| filter name  | `filter name <NAME>`<br>Gets the list of names after filtering.                                      |
| add name     | `add name <NAME>`<br>Adds a name to the list of stored names.                                        |
| delete name  | `delete name <INDEX>`<br>Removes a name from the list of stored names given the index.               |
| stats        | `stats`<br>Show the content of past writings as well as their basic specifications, e.g: number of lines/sentences/type of the writings/date created/ …..<br>This is a poem  <br>Written by Goethe<br>Id: 5 <br> DER ERLKÖNIG<br>Wer reitet so spät, durch Nacht und Wind?<br>Es ist der Vater mit seinem Kind<br>Er hält den Knaben wohl in den Armen.<br>Er faßt ihn sicher, er hält ihn warm.<br>This writing was created on 18/10/2020<br>This poem has 4 lines, 31 words.<br>----------------------------------------------------------------|
| remind       | `remind DATE`<br> Show you which writings are scheduled on a specific day.<br> Example usage: `remind 30/10/2020`|
| list         | `list`                                                                                                |
| reset        | `reset <NAME_OF_CATEGORY>`<br>NAME_OF_CATEGORY:<br>- Reset the respective input category database (e.g, bunny, writings, words,...) |                                                                                  |    

[Jump to top](#fluffle-user-guide)
