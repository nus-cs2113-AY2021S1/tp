
# AniChan User Guide
## Table of Contents
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)
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


### 3.2 Adding a User: `adduser`
Adds a new user to AniChan.

Format: `adduser -n <USERNAME> -dob <dd/MM/yyyy> -g <GENDER>`

Sample input: `adduser -n Timothy Wright -dob 12/12/1997 -g male`

The expected outcome:

    Successfully added new user: 
    Name: Timothy Wright
    Birthdate: 12/12/1997
    Gender: Male


### 3.3 Switching Users: `switchuser`
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
### 3.5 Create an Anime watchlist: `watchlist`
This command handles all watchlist management related operations: 
* Create a new watchlist.
* List all created watchlist(s).
* Activate another watchlist to use.    [coming in v2.0]
* Delete a watchlist that is no longer needed. [coming in v2.0]

Format: 

`watchlist -n <WATCHLIST_NAME>`

`watchlist -l`

`watchlist -s <WATCHLIST_INDEX>` [coming in v2.0]

`watchlist -d <WATCHLIST_INDEX>` [coming in v2.0]

Sample input: `watchlist -n Adventure Anime`

The expected outcome: 

`Watchlist created successfully.`

### 3.6 Add an anime to the select watchlist: `add`
Add an anime to the currently selected watchlist

Format: `add -a <ANIME_NAME>`

Sample input: `add -a Fullmetal Alchemist: Brotherhood`

The expected outcome: 

`Anime added to watchlist!`

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

### 3.8 Exit AniChan: `exit`
Exit AniChan 

Format: `exit`

Sample input: `exit`

The expected outcome:
```
Sayonara <NAME>!
```


### 3.9 Saving and loading data
User profile and watchlist(s) data will be **saved automatically** whenever changes are made to the data, and will also be **loaded automatically** when AniChan is launched.

These data can be found in the folder where AniChan is launched, in the subfolder, `data/AniChan`, saved in their respective file names `userprofile.txt` and `watchlist.txt`.


## 4. FAQ
Coming soon!


## 5. Command Summary

|Feature|Command|
|---    |---|
|Help | `help`|
|Add User | `adduser -n <USERNAME> -dob <dd/MM/yyyy> -g <GENDER> ` |
|Switch User | `switchuser -n <USERNAME>` |
|Browse | `browse -s [name/rating] -p <1-26> -o [asc/dsc]`  |
|Watchlist | `watchlist -n <WATCHLIST_NAME>` <br /> `watchlist -l` |
|Add To Watchlist | `add -a <ANIME_NAME>` |



