# Duan Yu Hang's Project Portfolio Page

## Project: FinanceIt

## Overview
FinanceIt is an all-in-one desktop application that handles the finance tracking needs of university students who are 
comfortable with a CLI interface. In the application, it consists of 5 sub-application that helps student to manage
their finance. <br/>

## Summary of Contributions

### Code contributed
[RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=Artemis-Hunt&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements implemented
* RecurringTracker and associated RecurringEntry as well as RecurringEntryList
    * Modified version of Entry Tracker with enhancements specific to recurring entries
        * Attributes specific to RecurringEntry
            * Day of month, auto/manual, notes
            * different toString() method as it is displayed differently
        * Methods specific to RecurringEntryList
            * getEntryFromDayXtoY, which returns a list of entries between a given period
    * If user inputs a day which not every month has (feb does not have day 30, for instance), 
    it will remind the user, and this info will be displayed when the user types `list`
* ReminderGenerator for upcoming recurring entries
    * Checks system date and generate reminders for entries upcoming in next 5 days
    * If the reminder period overflows to next month, reminds entries that are end of current month
    and start of next month
    * Reminders are not provided for entries that fall on a day which does not exist in the current 
    month (no reminder in feb for an entry on day 30)
* Logic for params parsing
    * Recursive parsing of param type and param details, by looking for the current param type and the next param type or end of input
* Schema of CommandPacket, which stores the command and all params
    * HashMap structure to store the inputted params

### Contribution to UG
* Wrote section on Recurring Tracker
* Wrote summary of features and manual testing instructions for Recurring Tracker

### Contributions to DG
* Wrote section on Recurring Tracker
    * Created the class diagram as well as the two sequence diagrams
* Updated the class diagram in Design > Logic Component 
* Updated writeup for Design > Input manager.
* Updated writeup for Implementation > Input manager.
* Summarized and updated writeup for Implementation > Logic Managers
* Wrote section on "Handling of params by `XYZCommandHandler`"
* Looked through and cleaned up the Design and Module-level Implementation sections e.g. summarize portions
that were too wordy, rewording sentences for clarity, offered suggestions on UML diagram improvements (omit less 
important details, generalize certain components/interactions, break diagram into smaller pieces etc.)

### Contribution to team-based tasks
* Coding standard compliance
    * Refactored code to achieve higher degree of SLAP and to adhere to good design practices
        * Refactor long chunks of code to individual methods/classes
        * Abstracted out common portions of code into a standalone class to reduce copy-pasting
        * Refactored some classes to singletons
    * Renamed variables, methods and classes for clarity
* Code clarity
    * Added JavaDoc comments for a significant portion of the methods
    * Added in-code comments for clarity
* Testing
    * Wrote some JUnit tests for common components e.g. ParamChecker, which is used by 3 features.
    * Wrote functions to generate random strings/double/integer to facilitate testing
    
    
### Review/mentoring contributions
* Reviewed [PR #74](https://github.com/AY2021S1-CS2113-T16-1/tp/pull/74)
    * Comprehensive checking of code, and many suggestions on improvement
* Provided suggestions on code structure and DG structure.

### Contributions to Developer Guide (Extracts)

