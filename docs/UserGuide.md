
# User Guide  
  
## Introduction  
  
Zoomaster is a desktop app for organizing website links, optimized for use via a 
Command Line Interface (CLI) while retaining benefits of a Graphical User Interface (GUI). 
If you can type fast, Zoomaster can help fetch useful website links for you quicker than the bookmark function on your browser.

### Table of contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [Global](#global)
    * [Switch mode:](#mode) **mode**
    *  [Exit:](#exit)  **exit**
  * [Bookmark mode](#bookmarkmode)
    * [Show bookmarks:](#showbookmark) **show**
    * [Add bookmark:](#addbookmark)  **add**
    * [Delete bookmark:](#deletebookmark)  **delete**
    * [Find bookmark:](#findbookmark)  **find**
    * [Launch bookmark:](#launchbookmark)  **launch**
  * [Timetable mode](#timetablemode)
    * [Show timetable:](#showtimetable) **show**
    * [Show module details:](#showmoduledetails) **show**
    * [Add module, time slot and bookmark:](#addtimeslot) **add**
    * [Delete module, time slot and bookmarks:](#deletetimeslot)  **delete**
* [FAQ](#faq)
* [Command Summary](#command-summary)
  
## Quick Start  
  
1. Ensure that you have Java 11 or above installed.  
2. Download the latest version of `Zoomaster` from [here](https://github.com/AY2021S1-CS2113T-W11-1/tp/releases).
3. Copy the `.jar` file to the folder that you want to use as the home folder.
4. Launch the file using the `java` command: `java -jar zoomaster.jar`.  
  
## Features   

>[i] Notes on command format:
>* Word contained in {currly brackets} are parameters to be supplied by the user. <br>
>eg. in delete {BOOKMARK_NUMBER}, BOOKMARK_NUMBER is a parameter which can be used as `delete 1`.
> * Parameters with `/` inside are parameters which accept different types of inputs. <br>
> eg. show launch {INDEX/DESCRIPTION} shows that either INDEX or DESCRIPTION can be used.
>* Items in with (optional) are optional inputs. <br>
>eg. show {DAY(optional)} can be used as `show` or as `show mon`.
>

<a name="global"></a> 
### Global

The commands below can be used in both modes of the app.  
<br>

<a name="mode"></a>  
#### Switch mode: `mode`
You can switches between the “bookmark” and “timetable” modes. Depending on the mode you select, 
the behaviour of the commands below changes. <br>
There are two modes for Zoomaster, Bookmark and Timetable.

```
Format: mode {bookmark/timetable}
```

Example of usage:   
* `mode bookmark`
* `mode timetable` 

<br/><br/> 
<a name="launchnow"></a>  
#### Launch the bookmarks of the current lesson: `launch now`  
Launches the bookmarks of a current lesson slot on your timetable. 
The time depends on the system time of your machine.
  
```
Format: launch now    
```

<br/><br/> 
<a name="exit"></a>  
#### Exit the app: `exit`  
Exits the application 
  
```
Format: exit    
```

<br/>
---
<br/> 


<a name="bookmarkmode"></a>  
### Bookmark Mode  

<a name="showbookmark"></a>  
#### Show bookmarks: `show`  
Prints all bookmark in the bookmark list  
```
Format: show
```
<br/><br/> 
<a name="addbookmark"></a>  
#### Add bookmark: `add`  
Adds a URL bookmark with a description.  
```
Format: add {DESCRIPTON} {URL}
``` 
* The `DESCRIPTION` can only contain one word (no whitespace inside).  
* The `URL` has to start with `www.` or `https://`.  
* There should be strictly one space between the `DESCRIPTION` and `URL`.    
  
Example of usage:
* `add cs2113t-website www.cs2113t.nus.edu.sg/`  
* `add cs2113t-website https://cs2113t.nus.edu.sg/`  

<br/><br/> 
<a name="deletebookmark"></a>  
#### Delete bookmarks: `delete`  
Deletes a bookmark with the specified index.  
The index will correspond to the index of that bookmark in the list. 
You can do a `show` command to check the bookmark indexes.  

```
Format: delete {INDEX}
```

Example of usage:
* `delete 2`  
* `delete 4`  

<br/><br/> 
<a name="findbookmark"></a>  
#### Find bookmarks: `find`  
Finds bookmarks with matching description and prints them.
```
Format: find {DESCRIPTION}
```
  
* The `DESCRIPTION` can only contain one word.  

Example of usage: 
* `find cs2113t-website`
* `find notes`  


<br/><br/> 
<a name="launchbookmark"></a>  
#### Launch bookmarks: `launch`  
Launches bookmarks in the default browser using either:
* Index
* Matching description

The index will correspond to the index of that bookmark in the list. 
You can do a `show` command to check the bookmark indexes.  
```
Format: launch {INDEX/DESCRIPTION}
```

* The `DESCRIPTION` can only contain one word.   

Example of usage:  
* `launch 1`
* `launch cs2113t-website`  
  
<br/>
---
<br/> 

<a name="timetablemode"></a>  
### Timetable mode    

<a name="showtimetable"></a>
#### Show timetable: `show`
Shows the timetable for a certain day or the whole week.  
Day inputs are in three letter abbreviations eg `mon`, `tue`, `wed`.  
`today` can also be a day input to show the timetable for the current day based on system time.  
You can also view the information of module or its bookmarks.
```
Format: show {DAY(optional)}
```

Example of usage:   
* `show`
* `show mon`
* `show today`

<br/><br/>
<a name="showmoduledetails"></a>
#### Show module details: `show`
Shows the details of a module that has been added.  
You can see the respective indexes of each of the slots from the module 
and using the `bookmarks` keyword will show the bookmarks which are saved in the module and its slots.
```
Format: show {MODULE} bookmarks(optional)
```

Example of usage:   
* `show cs2113t`
* `show cs2113t bookmarks`

<br/><br/> 
<a name="addtimeslot"></a>
#### Add module, time slot and bookmark: `add`  
Adds module, time slot and bookmark.
You can chain commands when adding multiple slots and bookmarks to a module by using `,` as a separator.
  
```
Format (adding a module): 
* add {MODULE}

Format (adding a slot to a module): 
* add {MODULE} {DESCRIPTION} {DAY} {START_TIME} {END_TIME} 

Format (adding a bookmark to a module): 
* add {MODULE} {DESCRIPTION} {URL}

Format (adding a bookmark to a slot): 
* add {MODULE} {DESCRIPTION} {DAY} {START_TIME} {END_TIME} {URL}
* add {MODULE} {DESCRIPTION} {INDEX} {URL}

Format (chaining commands): 
* add {MODULE} {DESCRIPTION} {DAY} {START_TIME} {END_TIME} {URL}, {DESCRIPTION} {URL}, ...
```

* The `START TIME` and `END TIME` is the format `HH:mm`.  
* The `DAY` can be one of `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`.  
* There should be strictly one space between each parameter.
* Multiple bookmarks can be added to a module and a slot.
  * To add another bookmark to an existing module, you have to enter the module code of the 
    existing module in the timetable.
  * To add another bookmark to an existing slot, you can enter the command with the matching details 
    of the existing slot or use the index number of the slot in the module to access the slot. 
    Index number of the slot can be found by using the command `show {MODULE}`. 
* Each command works by checking if each of the components (module, slot, bookmark) 
  exists or is valid from left to right of the input before adding them.
  * In the command `add cs2113t lecture fri 16:00 18:00`, if `cs2113t` module already exists, 
    then it will not be added into the timetable. The slot `lecture fri 16:00 18:00` 
    which is valid and not a duplicate will then be added to the existing `cs2113t` module.
* The chaining of commands only performs on one module which is {MODULE}.    
  
Example of usage:   
* `add cs2113t`  
* `add cs2113t lecture fri 16:00 18:00`
* `add cs2113t notes www.google.com`
* `add cs2113t lecture fri 16:00 18:00 www.google.com`
* `add cs2113t 1 www.yahoo.com`
* `add cs2113t lecture fri 16:00 18:00 www.google.com, notes www.google.com, tutorial fri 10:00 12:00`


<br/><br/> 
<a name="deletetimeslot"></a>
#### Delete module, time slot and bookmarks: `delete`  
Delete module, time slot or bookmarks.
```
Format (deleting a module): 
* delete {MODULE}

Format (deleting a slot of a module): 
* delete {MODULE} {INDEX} 

Format (deleting bookmarks of a module): 
* delete {MODULE} bookmarks 

Format (deleting bookmarks of a slot of a module): 
* delete {MODULE} {INDEX} bookmarks 
```

* Deleting bookmarks will delete all bookmarks associated with the module or slot.

Example of usage:   
* `delete cs2113t`  
* `delete cs2113t 1`
* `delete cs2113t bookmarks`
* `delete cs2113t 1 bookmarks`

## FAQ  
  
**Q**: How do I transfer my data to another computer?   
  
**A**: Copy the `data/bookmarks.txt` and `data/slots.txt` file to the directory where the `jar` file is at.
Start the application and all the data should be loaded.
  

## Command Summary
**Action** | **Format, Examples**
------------ | -------------
**mode**|`mode {bookmark/timetable}`<br>example: `mode bookmark`
**exit**|`exit`
**Bookmark Mode**|
**show**|`show`
**add**|`add {MODULE(optional)} {DESCRIPTON} {URL}` <br>example: `add CS2113T tutorial www.yahoo.com`
**delete**|`delete {INDEX}`<br>example: `delete 2`
**find**|`find {MODULE} {DESCRIPTION(optional)}` <br>example: `find CS2113 tutorial`
**launch**|`launch {MODULE} {DESCRIPTION(optional)}` <br>example: `launch CS2113`
**Timetable Mode**|
**slotadd**|`before  <yyyyMMdd HHmm>`<br>example: `before 20210909 1159`
**slotdel**|`after <yyyyMMdd HHmm>`<br>example: `after 19990101 0100`
**show**|`today`

