# Ong Chin Hang - Project Portfolio Page

## Overview

**revisED** is a CLI application to help students revise by allowing them to create flashcards, take quizzes, 
and keep track of their deadlines, tasks and any other important dates. It is written in Java.

## Summary of Contributions

* **Code contributed:** [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=cookiehoodie&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) 
* **Features implemented:** 
    * Storing of data in the form of nested folders and files for easier navigation and manual changes by the users. 
        * This was challenging because a lot of testings needed to be done to make sure the hierarchy was created as intended, 
        as compared to storing all data to one place.
        * Credit: the methods for storing and loading tasks are taken from [JiaLerk](https://github.com/jialerk), though with 
        quite some modifications to improve on them. (Pull requests: [#83](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/83))
    * Loading of the saved data
        * loading the data stored in this way posed challenges because the user can make a lot more variation of changes
        to the stored data, as compared to storing all data in one file. Thus, a lot of exception handling needed to be done
        to make sure the application still works with corrupted data.
    * Exporting of all data to one Json file for the use of other applications.
* **Enhancements implemented:** 
    * Enhanced the code to reduce redundancy and improve design.
        (Issues: 
        [#29](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues/29) 
        [#33](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues/33)
        [#26](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues/26))
    * Included `Result` data to storage, which was not in consideration. (Pull requests: [#42](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/42))
    * Modified `Storage` class to use builder design pattern for less error prone instance creation. (Pull requests: 
    [#47](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/47))
* **Contributions to the UG:** 
    * [Export](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html#export-command) feature command.
    * Explanation to [storing](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html#store), [loading](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html#load)
    and [exporting](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html#export) of data.
    * Restructuring of features section, adding of symbols legend to the introduction, improvement on quick start.
    (Pull requests: [#126](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/126))
   
* **Contributions to the DG:** 
    * [Storage](https://ay2021s1-cs2113t-w13-1.github.io/tp/DeveloperGuide.html#storage) section under design (along with its class diagram).
    * [Storing, loading, and exporting data](https://ay2021s1-cs2113t-w13-1.github.io/tp/DeveloperGuide.html#storage) sections
    under implementation (along with all the sequence diagrams contained in them).
    * Restructuring of whole DG; improvement on setting up the project section, logging, testing, user stories,
    non-functional requirements, manual testing. (Pull requests: 
    [#142](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/142)
    [#129](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/129))
* **Contributions to team-based tasks:**
    * Maintained the issue tracker - opening and assigning of most issues.
    * Setup the logging configuration. (Pull requests: [#51](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/51))
    * Enhanced the code to reduce redundancy and improve design.
    (Issues: 
    [#29](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues/29) 
    [#33](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues/33)
    [#26](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues/26))
    * Restructured DG and UG and revamped/enhanced common sections such as introduction, user stories, manual testing, etc. 
    (Pull requests: [#142](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/142)
    [#129](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/129)
    [#127](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/127)
    [#126](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/126))
* **Review/mentoring contributions:** Pull requests: 
[#124](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/124)
 [#86](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/86)
 [#82](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/82)
 [#52](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/52)
 [#141](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/141)
 [#32](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/32)
 [#12](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/12)
 [#69](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/69)
 [#54](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/54)
