# User Guide

## Table of content
### [1. Introduction](#1-Introduction-1)
### [2. Quick start](#2-Quick-start-1)
### [3. Features](#3-Features-1)
#### [3.1 Before the order](#31-Before-the-order-1)
#### [3.2 Begin the order](#32-Begin-the-order-1)
##### [3.2.1 Firstly](#321-Firstly-1)
##### [3.2.2 The order method will be activated and print open stalls, user just type the corresponding index before the canteen to select canteen](#322-The-order-method-will-be-activated-and-print-open-stalls,-user-just-type-the-corresponding-index-before-the-canteen-to-select-canteen-1)
##### [3.2.3 Then the open stalls in this canteen will be printed, user just type the corresponding index before the stall to select stall](#323-Then-the-open-stalls-in-this-canteen-will-be-printed,-user-just-type-the-corresponding-index-before-the-stall-to-select-stall-1)
##### [3.2.4 Then the available dishes and price in this stall will be printed, user just type number of dishes he wants to order](#324-Then-the-available-dishes-and-price-in-this-stall-will-be-printed,-user-just-type-number-of-dishes-he-wants-to-order-1)
##### [3.2.5 User chooses dishes in the same way as they select canteen and stall](#325-User-chooses-dishes-in-the-same-way-as-they-select-canteen-and-stall-1)
##### [3.2.6 User will be asked whether he wants to see the comment of the dish, just type y/n](#326-User-will-be-asked-whether-he-wants-to-see-the-comment-of-the-dish,-just-type-y/n-1)
##### [3.2.7 User choose the order type by typing the corresponding index before the type](#327-User-choose-the-order-type-by-typing-the-corresponding-index-before-the-type-1)
#### [3.3 delete the specific order](#33-delete-the-specific-order-1)
#### [3.4 find the specific order](#34-find-the-specific-order-1)
#### [3.5 list all the orders](#35-list-all-the-orders-1)
#### [3.6 change the order](#36-change-the-order-1)
#### [3.7 check Canteen Operating Time](#37-check-Canteen-Operating-Time-1)
##### [3.7.1 Firstly](#371-Firstly-1)
##### [3.7.2 User enters the number before canteen to check that specific canteen](#372-User-enters-the-number-before-canteen-to-check-that-specific-canteen-1)
#### [3.8 ccheck Stall Operating Time](#38-check-Stall-Operating-Time-1)
##### [3.8.1 Firstly](#381-Firstly-1)
##### [3.8.2 User enters the number before canteen to check that specific canteen](#382-User-enters-the-number-before-canteen-to-check-that-specific-canteen-1)
##### [3.8.3 User enters the number before stall to check that specific stall](#383-User-enters-the-number-before-stall-to-check-that-specific-stall-1)
#### [3.9 view all available commands](#39-view-all-available-commands-1)
#### [3.10 quit the system](#310-quit-the-system-1)
### [4. Command Summary](#4-Command-Summary-1)


## 1. Introduction

During peak hours, people may not be able to know the dining situation and the density of the canteen they want to go to. To solve this problem, we want to create a program to store and display basic information of stalls in each canteen (e.g. Open hours, current menu, change order etc) to all their potential users. CanteenHelper is a command line (CLI) application that helps students, staff and even tourists order food in canteens efficiently. CanteenHelper will allow users to choose their favorite dishes from different stalls in different canteens based on the date and time provided by the users. They also can check the comments of the dishes and operating hours of the stalls and canteens.


## 2. Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `CanteenHelper` from [here](https://github.com/AY2021S1-CS2113-T16-2/tp/releases/tag/v2.1).


## 3. Features 

### 3.1 Before the order: 
`[name]/[day of week]/[time]`

Format: `[name]/[day of week]/[time]`

Example of usage:
'wy/2/1800'
‘fqy/1/1200

* day of week should between 1-7 (includes)
* time should follow this format: 900 or 1600, represent 09:00 and 16:00 respectively.

Result is like this:

![image](img/before.png)

### 3.2 Begin the order:
`order`
begin the order of a customer

#### 3.2.1 Firstly

Format: `order`

Example of usage:
'order'

Result is like this:

![image](img/order.png)

#### 3.2.2 The order method will be activated and print open stalls, user just type the corresponding index before the canteen to select canteen. 

Format: `[number]`

Example of usage:
'2'

Result is like this:

![image](img/canteen.png)

#### 3.2.3 Then the open stalls in this canteen will be printed, user just type the corresponding index before the stall to select stall. 

Format: `[number]`

Example of usage:
'1'

Result is like this:

![image](img/stall.png)

#### 3.2.4 Then the available dishes and price in this stall will be printed, user just type number of dishes he wants to order.

Format: `[number]`

Example of usage:
'2'

Result is like this:

![image](img/num.png)

#### 3.2.5 User chooses dishes in the same way as they select canteen and stall. 

Format: `[number]`

Example of usage:
'2'

Result is like this:

![image](img/dish.png)

#### 3.2.6 User will be asked whether he wants to see the comment of the dish, just type y/n.

Format: `y/n`

Example of usage:
'y', 'n'

Result is like this:

![image](img/comment.png)

#### 3.2.7 User choose the order type by typing the corresponding index before the type.

Format: `[number]`

Example of usage:
'2'

Result is like this:

![image](img/type.png)

### 3.3 delete the specific order: 
`delete [number]`
delete the order based on the number from user
user needs to input the number of order 

Format: `delete [number]`

Example of usage:
'delete 1'

Result is like this:

![image](img/ddlete.png)

### 3.4 find the specific order: 
`find [dish name]`
find the specific dish based on the user input dish name
from the order list made by the user

Format: `find [dish name]`

Example of usage:
'find Hawaiian'

Result is like this:

![image](img/findd.png)

### 3.5 list all the orders: 
`list`
check all the orders made by this user

Format: `list`

Example of usage:
'list'

Result is like this:

![image](img/lis.png)

### 3.6 change the order: 
`change/[number]/[type]`
Change the order type to the type user inputed

Format: `change/[number]/[type]`

type should choose from:
1. dine in
2. delivery
3. take away

Example of usage:
'change/2/dine in'
'change/1/delivery

* order type is case sensitive

Result is like this:

![image](img/changeorderr.png)

### 3.7 check Canteen Operating Time: 
`checkcanteen`
check the operating hours of a canteen 

#### 3.7.1 Firstly

Format: `checkcanteen`

Example of usage:
'checkcanteen'

#### 3.7.2 User enters the number before canteen to check that specific canteen

Format: `[number]`

Example of usage:
'4'

Result is like this:

![image](img/checkcanteen.png)

### 3.8 check Stall Operating Time: 
`checkstall`
check the operating hours of a stall 

#### 3.8.1 Firstly

Format: `checkstall`

Example of usage:
'checkstall'

#### 3.8.2 User enters the number before canteen to check that specific canteen

Format: `[number]`

Example of usage:
'3'

#### 3.8.3 User enters the number before stall to check that specific stall

Format: `[number]`

Example of usage:
'2'

Result is like this:

![image](img/checkstall.png)

### 3.9 view all available commands: 
`help`
print all the commands with required format that the user can input

Format: `help`

Example of usage:
'help'

![image](img/help.png)

### 3.10 quit the system: 
`bye`
quit the system

Format: `bye`

Example of usage:
'bye'

Result is like this:

![image](img/bye.png)


## 4. Command Summary

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

