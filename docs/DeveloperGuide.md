# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}  
Fresh computing graduates who are just starting to enter the workforce.
* Have limited income/budget
* Little experience in personal financial management
* Busy juggling their various job applications and interview which might cause them to lose track of their expenditure/ 
bill payments
* First time drawing salary and lack experience in income tax filling

### Value proposition

{Describe the value proposition: what problem does it solve?}  
**Expenditure Tracker**
* Input itemised spending on a daily basis
* Sum the monthly/weekly expenditure, as well as average weekly/daily expenditure
* Categorise expenditures (e.g. food, transport etc) and sort by category

**Budget Management**
* Set a budget (monthly/weekly/daily)
* Remind users of how much budget they have left for that month/week/day

**Bill Tracker**
* Remind users of payment due dates
* Monthly tracker for each individual bill, visualise trends in bill spending

**Finance Tools**
* Calculate simple interest
* Calculate compound interest with optional monthly/yearly deposit
* Calculate cashback earned
* Calculate miles credit earned
* Save account information for reference

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

## Design & Implementation
### Account Storage feature
The account information storage feature is facilitated by ```FinanceTools```. It allows user to store account
information such as name of account, interest rate, cashback rate, etc. When user inputs ```store``` as command,
```handleInfoStorage``` will handle user inputted parameters and store information accordingly. Afterwards, this
information is stored into a txt file which is done by ```updateFile```.
<br />

 Additionally, it implements the following operations:
 * ```info``` - list account(s) information
 * ```clearinfo``` - clear all information
 * ```store /delete <ACCOUNT_NO>``` - delete corresponding account number in list
 
 #### Details
 ```handleInfoStorage``` stores the user inputted information into an ArrayList which is then passed into
 ```updateFile``` to update the txt file. The purpose of using txt file is so that when the user exits and enters the
 program again, the information is retained, and the user does not have to re-enter the account information(s) again.
 <br />
 
 When user first enters FinanceTools in the program, ```readFileContents``` reads 5 lines in the txt file consecutively
 in a ```while``` loop because these 5 lines consists of information that belong to a particular account. These
 categories include: Name, Interest Rate, Cashback Rate, Cashback Cap and Notes". Doing so helps to facilitate
 the ```delete``` option where instead of deleting single lines, we can delete the entire account information
 which correspond to a particular account because the information is stored in one index of the ArrayList.
 <br />
 
 The following class diagram shows how the account information storage feature works:


 The following sequence diagram shows how the account information storage feature works: