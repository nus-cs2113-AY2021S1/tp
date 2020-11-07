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

### 3.1 before the order: `[name]/[day of week]/[time]`

Format: `[name]/[day of week]/[time]`

Example of usage:
'wy/2/1800'

* day of week should between 1-7 (includes)
* time should follow this format: 900 or 1600, represent 09:00 and 16:00 respectively.

Result is like this:

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/before.png)

### 3.2 begin the order: `order`
begin the order of a customer

![image](

#### 3.2.1 Firstly

Format: `order`

Example of usage:
'order'

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/order.png)

#### 3.2.2 The order method will be activated and print open stalls, user just type the corresponding index before the canteen to select canteen. 

Format: `[number]`

Example of usage:
'2'

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/canteen.png)

#### 3.2.3 Then the open stalls in this canteen will be printed, user just type the corresponding index before the stall to select stall. 

Format: `[number]`

Example of usage:
'3'

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/stall.png)

#### 3.2.4 Then the available dishes and price in this stall will be printed, user just type number of dishes he wants to order and chooses dishes in the same way as they select canteen and stall. 

Format: `[number]`

Example of usage:
'1'

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/num.png)

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/dish.png)

#### 3.2.5 After that, user will be asked whether he wants to see the comment of the dish, just type y/n.

Format: `y/n`

Example of usage:
'y'

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/comment.png)

#### 3.2.6 Finally, choosing the order type by typing the corresponding index before the type.

Format: `[number]`

Example of usage:
'2'

![image](

### 3.3 delete the specific order: `delete [number]`
delete the order based on the number from user

Format: `delete [number]`

Example of usage:
'delete 2'

![image](

### 3.4 finde the specific order: `find [dish name]`
find the specific dish based on the user input dish name

Format: `find [dish name]`

Example of usage:
'find Pork Chop'

![image](

### 3.5 list all the orders: `list`
check all the orders made by this user

Format: `list`

Example of usage:
'list'

![image](

### 3.6 change the order: `change/[number]/[type]`
Change the order type 

Format: `change/[number]/[type]`

Example of usage:
'change/2/dine in'

![image](

### 3.7 check Canteen Operating Time: `checkcanteen`
check the operating hours of a canteen 

Format: `checkcanteen`

Example of usage:
'checkcanteen'

![image](

### 3.8 check Stall Operating Time: `checkstall`
check the operating hours of a stall 

Format: `checkstall`

Example of usage:
'checkstall'

![image](

### 3.9 view all available commands: `help`
print all the commands with required format that the user can input

Format: `help`

Example of usage:
'help'

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/help.png)

### 4.0 quit the system: `bye`
quit the system

Format: `bye`

Example of usage:
'bye'

![image](https://github.com/AY2021S1-CS2113-T16-2/tp/blob/master/src/img/bye.png)

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

