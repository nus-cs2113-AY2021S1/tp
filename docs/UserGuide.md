# User Guide

## Table of content
### [1. Introduction](#1-Introduction1)
### [2. Quick start](#2-Quick-start1)
### [3. Features](#3-Features1)
### [4. FAQ](#4-FAQ1)
### [5. Command Summary](#5-Command-Summary1)


## 1. Introduction

During peak hours, people may not be able to know the dining situation and the density of the canteen they want to go to. To solve this problem, we want to create a program to store and display basic information of stalls in each canteen (e.g. Open hours, current menu, change order etc) to all their potential users. CanteenHelper is a command line (CLI) application that helps students, staff and even tourists order food in canteens efficiently. CanteenHelper will allow users to choose their favorite dishes from different stalls in different canteens based on the date and time provided by the users. They also can check the comments of the dishes and operating hours of the stalls and canteens.


## 2. Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `CanteenHelper` from [here](http://link.to/duke).


## 3. Features 

### 3.1 begin the order: `order`
begin the order of a customer

#### 3.1.1 Firstly

Format: `order`

Example of usage:
'order'

#### 3.1.2 The order method will be activated and print open stalls, user just type the corresponding index before the canteen to select canteen. 

Format: `[number]`

Example of usage:
'2'

#### 3.1.3 Then the open stalls in this canteen will be printed, user just type the corresponding index before the stall to select stall. 

Format: `[number]`

Example of usage:
'3'

#### 3.1.4 Then the available dishes and price in this stall will be printed, user just type number of dishes he wants to order and chooses dishes in the same way as they select canteen and stall. 

Format: `[number]`

Example of usage:
'1'

#### 3.1.5 Finally, choosing the order type by typing the corresponding index before the type.

Format: `[number]`

Example of usage:
'2'

### 3.2 delete the specific order: `delete [number]`
delete the order based on the number from user

Format: `delete [number]`

Example of usage:
'delete 2'

### 3.3 finde the specific order: `find [dish name]`
find the specific dish based on the user input dish name

Format: `find [dish name]`

Example of usage:
'find Pork Chop'

### 3.4 list all the orders: `list`
check all the orders made by this user

Format: `list`

Example of usage:
'list'

### 3.5 change the order: `change/[number]/[type]`
Change the order type 

Format: `change/[number]/[type]`

Example of usage:
'change/2/dine in'

### 3.6 check Canteen Operating Time: `checkcanteen`
check the operating hours of a canteen 

Format: `checkcanteen`

Example of usage:
'checkcanteen'

### 3.7 check Stall Operating Time: `checkstall`
check the operating hours of a stall 

Format: `checkstall`

Example of usage:
'checkstall'

### 3.8 view all available commands: `help`
print all the commands with required format that the user can input

Format: `help`

Example of usage:
'help'


## 4. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}


## 5. Command Summary

|Action|Command|Example|
|------|-------|-------|
|order|`order`|`order`|
|delete|`delete [number]`|`delete 2`|
|find|`find [dish name]`|`find Pork Chop`|
|list|`list`|`list`|
|change|`change/[number]/[type]`|`change/3/take away`|
|check Canteen Operating Time|`checkcanteen`|`checkcanteen`|
|check Stall Operating Time|`checkstall`|`checkstall`|
|help|`help`|`help`|

