# AniChan User Guide

![AniChan Logo](https://i.imgur.com/VhbC59Q.png)

## Table of Contents
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)
<br/>&nbsp;3.5 [Watchlist management: `watchlist`](#35-watchlist-management-watchlist)
<br/>&nbsp;3.9 [Saving and loading data](#39-saving-and-loading-data)
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)

## 1. Introduction

AniChan is an all-rounded tool to effectively create and organize anime lists with viewing statistics, efficiency-focused features, and tools to improve anime-watching experience.


## 2. Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `AniChan` from [here](https://github.com/AY2021S1-CS2113T-F12-2/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your AniChan.
4. Open cmd and change directory into the folder. Run “java -jar anichan.jar”.
5. Type a command into the command prompt and press ‘Enter’ to execute it. e.g. typing `help` and pressing ‘Enter’ will display the help message.


## 3. Features 
**Command Format**

* Commands are case-sensitive.
E.g. Only `help` will work, and so `HELP`, `hElp`, and its other variant will not work.

* Words in UPPERCASE are values that can be supplied by the user.
E.g. in `browse -s SORT_CATEGORY -p PAGE_NUMBER` where SORT_CATEGORY and PAGE_NUMBER are 
parameters that can be used as `browse -s name -o asc`.

* Square brackets indicate optional parameters.
E.g. `browse [-s SORT_CATEGORY]` can be used simply as `browse` or `browse -s name`.
  
* The order of parameters are not important.
E.g. Both `-n USERNAME -dob DATE_FORMAT` and `-dob DATE_FORMAT -n USERNAME` are 
both acceptable and will produce the same output.  


### 3.1 View the help: `help`
This command will provide the details of all available commands and their usage. 
This is done by displaying the ‘Command Summary’ as listed below to the user. 

Format: `help`

<br/>

### 3.2 Adding a user: `adduser`
Adds a new user to AniChan.

Format: `adduser -n <USERNAME> -dob <dd/MM/yyyy> -g <GENDER>`

Sample input: `adduser -n Timothy Wright -dob 12/12/1997 -g male`

The expected outcome:

    Successfully added new user: 
    Name: Timothy Wright
    Birthdate: 12/12/1997
    Gender: Male

<br/>

### 3.3 Switching users: `switchuser`
Switch the current active user to another user.

Format: `switchuser -n <USERNAME>`

Note:

The name in the command prompter has changed as well to reflect the new user.

Sample input: `Barkley-san (Default) #> switchuser -n Isaac Asimov`

The expected outcome:
```
 Welcome back, Isaac Asimov-san

 Isaac Asimov-san (Default) #> 
```

<br/>

### 3.4 Browse through all anime: `browse`
Browse through all anime from the source. It can be displayed in sorted order.

Format: `browse [-s SORT_CATEGORY] [-o DISLAY_ORDER] [-p PAGE_NUMBER`]

Note: 

`-s name` will sort the list by alphabetical order

`-s rating` will sort the list by rating

`-o asc` will arrange it in ascending order

`-o dsc` will arrange it in descending order

`-p <N>` will display page N of the list

`-p <N>` N must be a positive integer value

The order of the parameter does not matter

If no parameters or only `-o` is specified then it will display in its anime id order.

Sample input: `browse -s name -p 1 -o dsc`

The expected outcome:
```
1. .hack//Gift
2. .hack//Legend Of The Twilight
3. .hack//Liminality
4. .hack//Sign
5. 3x3 Eyes
6. A Chinese Ghost Story
7. ARIA The ANIMATION
8. Abashiri Ikka
9. Ace wo Nerae!
10. Ace wo Nerae! 2
11. Ace wo Nerae: Final Stage
12. After War Gundam X
13. Agatha Christie's Great Detectives Poirot and Marple
14. Agent Aika
15. Ah! My Goddess: The Movie
16. Ai Yori Aoshi
17. Ai Yori Aoshi: Enishi
18. Aim for the Ace! (1979)
19. Air
20. Air Master
Browsing Page: 1
```

<br/>

### 3.5 Watchlist management: `watchlist`
This command handles all watchlist management related operations: 
* Create a new watchlist.
* List all created watchlist(s).
* Select another watchlist to use.
* Delete a watchlist that is no longer needed.

Format: 

* `watchlist -n <WATCHLIST_NAME>`
* `watchlist -l`
* `watchlist -s <WATCHLIST_INDEX>`
* `watchlist -d <WATCHLIST_INDEX>`

Examples: 

* `watchlist -n Adventure Anime` <br/>
Creates a watchlist named `Adventure Anime`.

* `watchlist -l` <br/>
Lists all watchlist(s) or prints an empty watchlist message if there is none.

* `watchlist -s 2` <br/>
Selects the 2nd watchlist to be used.

* `watchlist -d 2` <br/>
Deletes the 2nd watchlist.

<br/>

### 3.6 Add an anime to the select watchlist: `add`
Add an anime to the currently selected watchlist

Format: `add -a <ANIME_NAME>`

Sample input: `add -a Fullmetal Alchemist: Brotherhood`

The expected outcome: 

`Anime added to watchlist!`

<br/>

### 3.7 Bookmark an Anime: `bookmark`
This command handles all bookmark related operations: 
* List all anime within bookmark.
* Add an anime into bookmark.
* Delete an anime from bookmark. 
* Edit episode for an anime within bookmark
* View details of an anime within bookmark [v2.0]

Format: 

`bookmark -l` will list bookmark

`bookmark -a <ANIME_ID>` will add the Anime into bookmark

`bookmark -d <BOOKMARK_ID>` will delete the Anime with bookmark id from bookmark

`bookmark <BOOKMARK_ID> -e <EPISODE>` will edit the episode for Anime with bookmark id

Sample input: `bookmark -l`

The expected outcome: 
```
 Listing all anime in bookmark: 
	1. InuYasha the Movie 3: Swords of an Honorable Ruler Ep: 5
	2. To Heart
```

Sample input: `bookmark -a 410`

The expected outcome: 
```
Saving 410. InuYasha the Movie 2: The Castle Beyond the Looking Glass to bookmark.
```

Sample input: `bookmark -d 1`

The expected outcome: 
```
Removing InuYasha the Movie 3: Swords of an Honorable Ruler! :(
```

Sample input: `bookmark 1 -e 5`

The expected outcome: 
```
Editing InuYasha the Movie 3: Swords of an Honorable Ruler to have 5 episode
```

<br/>

### 3.8 Search `search`
Searches for a specific anime or to search for a specific genre

Format:

`search -n <SEARCH_TERM>` will search for all anime series that contains the search term

`search -g <SEARCH_TERM>` will list all genres that matches the search term

Sample input: `search -n bey`

The expected outcome:
```
[ID:216] Haruka: Beyond the Stream of Time – A Tale of the Eight Guardians
[ID:257] Beyblade
[ID:410] InuYasha the Movie 2: The Castle Beyond the Looking Glass
```

Sample input: `search -g Slice of Life`

The expected outcome:
```
[ID:7] Honey and Clover
[ID:8] Hungry Heart: Wild Striker
[ID:35] Ai Yori Aoshi
[ID:39] Beck: Mongolian Chop Squad
[ID:48] Azumanga Daioh: The Animation
[ID:81] Air
...
[ID:446] Strawberry Marshmallow
[ID:447] KamiChu!
[ID:464] I My Me! Strawberry Eggs
[ID:467] Kiki's Delivery Service
[ID:475] Marmalade Boy Movie
[ID:488] Teacher's Time
```

### 3.9 Exit AniChan: `exit`
Exit AniChan 

Format: `exit`

Sample input: `exit`

The expected outcome:
```
Sayonara <NAME>!
```

<br/>

### 3.9 Saving and loading data
User, workspace(s), watchlist(s), and bookmark(s) data will be **saved automatically** when they are 
created or modified, and will be **loaded automatically** when AniChan is launched. 

In the folder where AniChan is launched, there will be a `data` folder which would contain these data:
* User data is stored in `data/user.txt`.
* Watchlist(s) data are stored in `data/<WORKSPACE-NAME>/watchlist.txt`, e.g. if your workspace is named "AniTranslator",
then the watchlist data can be found in `data/AniTranslator/watchlist.txt`.
* Bookmark(s) data are also stored in the same location as watchlist data, `data/WORKSPACE-NAME/bookmark.txt`.

## 4. FAQ
Coming soon!


## 5. Command Summary

|Feature|Command|
|---|---|
| Help               | `help`                                                   |
| add user           | `adduser -n <USERNAME> -dob <dd/MM/yyyy> -g <GENDER>`    |
| Switch user        | `switchuser -n <USERNAME>`                               |
| Browse             | `browse -s [name/rating] -p <1-26> -o [asc/dsc]`         |
| Create watchlist   | `watchlist -n <WATCHLIST_NAME>`                          |
| List all watchlist | `watchlist -l`                                           |
| Select watchlist   | `watchlist -s <WATCHLIST_INDEX>`                         |
| Delete watchlist   | `watchlist -d <WATCHLIST_INDEX>`                         |
| Add to watchlist   | `add -a <ANIME_NAME>`                                    |
| Search by title    | `search -n <SEARCH_TERM>`                                |
| search by genre    | `search -g <SEARCH_TERM>`                                |