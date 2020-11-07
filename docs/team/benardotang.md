# Benardo Tang - Project Portfolio Page for WatchNext

## Overview


## About the project
**WatchNext** is a show tracker designed for users who watch their favourite shows on multiple free streaming platforms and other open source streaming websites.
**WatchNext** records your progress for the different shows you are currently watching, and even for upcoming shows that you plan to watch.
<br>It additionally serves as a tracker to limit your weekly watch time to help you better manage your time.

## Summary of contributions
Given below are my coding, documentation, and other helpful contributions to the team project.

* **Feature**: Added the ability to track daily watch time through the `updatetimelimit` and `watch` command.
    * What it does: Allows the user to set a daily time limit to watch shows and maximise productivity. The time limit decreases when the user inputs the `watch` command and alerts the user when he/she has exceeded or reached the daily limit.
    * Justification:  This feature improves the product significantly as the user has an additional benefit of maximising productivity through using the application.
    * Highlights: This enhancement is an addon to our exisitng product. It required an in-depth analysis of design alternatives. The implementation was challenging as it required additional classes for it to function the way we wanted.

* **Feature**: Added the ability for the program to intelligently process the user's input and display the correct output into the terminal, using the `InputParser` and `UI` classes.


* **Code contributed**: [Reposense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=BenardoTang&tabRepo=AY2021S1-CS2113T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Enhancements implemented**:
    * Implemented `WatchCommand` (PR [#59](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/59), [#72](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/72))
    * Implemented `UpdateTimeLimitCommand` (PR [#67](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/67))
    * Added overall structure for implementation of `InputParser` (PR [#11](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/11))
    * Sections of `InputParser` which some Command functionalities were reliant on (PR [#15](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/15), [#21](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/21))
    * Management of output messages to the user through `UI` class (PR [#70](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/70))
    * Added functionality in `SearchCommand`, that was initially implemented by teammate [Jiqing](https://github.com/judowha), to be able to recognise case-insensitive or incomplete inputs as keywords. (PR [#193](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/193)

* **Contributions to documentation**:
    * Added table of contents and overall structure for user guide. (PR [#31](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/31) , [#52](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/52))
    * Added documentation in user guide for `watch` command, `updatetimelimit` command and Command Summary. (PR [#87](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/87))
    * General formatting updates and fixes in user guide each time a new feature was added into the guide. (PR [#101](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/101) , [#105](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/105))
    * Added documentation to some methods in the `InputParser` Class. Included documentation to most methods in UI Class (PR [#65](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/65) , [#204](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/204))


* **Contributions to DG**:
    * Added table of contents and overall structure for developer guide. (PR [#53](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/53))
    * Added `WatchCommand`, `UpdateTimeLimitCommand`, various sections in the developer guide. (PR [#78](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/78) , [#120](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/120))
    * Added UML diagram for `WatchCommand` section in developer guide. (PR [#151](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/151))
    * General formatting of developer guide

* **Contributions to team-based tasks** :
    *  Necessary general code enhancements (PR [#194](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/194))
    *  Setting up the GitHub team org/repo
    *  Documenting the User Stories and Manual Testing sections in the developer guide. (PR [#123](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/123) , [#135](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/135))


* **Review/mentoring contributions**:
    * PR reviewed (with non-trivial review comments): [#133](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/133)



