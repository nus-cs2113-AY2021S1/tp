
# AniChan User Guide
## Table of Contents
1. [Introduction](#1-introduction)
1. [Quick Start](#2-quick-start)
1. [Features](#3-features)
1. [FAQ](#4-faq)
1. [Command Summary](#5-command-summary)

## 1. Introduction

AniChan is an all-rounded tool to effectively create and organize anime lists with viewing statistics, efficiency-focused features, and tools to improve anime-watching experience.

## 2. Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `AniChan` from [here](http://link.to/duke).
1. Copy the file to the folder you want to use as the home folder for your AniChan.
1. Open cmd and change directory into the folder. Run “java -jar anichan.jar”.
1. Type a command into the command prompt and press ‘Enter’ to execute it. e.g. typing `help` and pressing ‘Enter’ will display the help message.


## 3. Features 
Some notes about the command format:

- Words in UPPERCASE are values that can be supplied by the user.
E.g. in `browse -s SORT_CATEGORY -p PAGE_NUMBER` where SORT_CATEGORY and PAGE_NUMBER are 
parameters that can be used as `browse -s name -o asc`.

- Square brackets indicate optional parameters.
E.g. `browse [-s SORT_CATEGORY]` can be used simply as `browse` or `browse -s name`.
  
- The order of parameters are not important.
E.g. Both `-n USERNAME -dob DATE_FORMAT` and `-dob DATE_FORMAT -n USERNAME` are 
both acceptable and will produce the same output.  


### 3.1 View the help: `help`
This command will provide the details of all available commands and their usage. 
This is done by displaying the ‘Command Summary’ as listed below to the user. 

Format: `help`

### 3.2 Adding a User: `adduser`
Adds a new user.

Format: `adduser -n USERNAME -dob DATE_FORMAT -g GENDER`

Sample Input: `adduser -n TimothyWright -dob 12/12/1997 -g male`

The expected outcome:

    Successfully added new user: 
    Name: TimothyWright
    Birthdate: 12/12/1997
    Gender: Male


### 3.3 Browse through all anime: `browse`
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

Sample Input: `browse -s name -p 1 -o dsc`

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
### 3.4 Create an Anime watchlist: `watchlist`

### 3.5 Add an Anime to the select watchlist: `add`

### 3.6 Bookmark an Anime: `bookmark`

### 3.7 Exit AniChan: `exit`
Exit AniChan 

Format: `exit`

Sample Input: `exit`

The expected outcome:
```
Sayonara!
```
## 4. FAQ
1. 


## 5. Command Summary

|Feature|Command|
|---    |---|
|Help| `help`|
|Browse | `browse -s [name/rating] -p <1-26> -o [asc/dsc]` |