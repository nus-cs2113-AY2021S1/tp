# Jazhten Low - Project Portfolio Page for WatchNext

## Overview
This document outlines my contributions made (non-exhaustive) to the WatchNext project.

## About the project
**WatchNext** is a show tracker designed for users who watch their favourite shows on multiple free streaming platforms and other open source streaming websites.
**WatchNext** records your progress for the different shows you are currently watching, and even for upcoming shows that you plan to watch.
<br>It additionally serves as a tracker to limit your weekly watch time to help you better manage your time.

## Summary of contributions
I list my contributions to the team project (code,documentation and others) below.

* **Code contributed**: [Reposense Dashboard](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jazhten&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=jazhten&tabRepo=AY2021S1-CS2113T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Skeleton** 
    * Description: Setup the initial backbone for WatchNext. This included basic OOP related classes and Interfaces like the Command class and SaveState. Also, I made decisions regarding the data structures used for WatchNext (HashMap for the watch list etc.)
    * Details : Wrote the backbone code for Duke, ShowList, StringOperations, SaveState (Interface), ErrorHandling, InputParser, Command, Ui, Show,Storage and WatchTime. 
    * Justification: This is significant as it standardized the structure of the project so that the members of the team do not code in conflicting manners with different philosophies and architectures in mind as it has already been structured; preventing any major restructuring work during the lifetime of the project.
    

* **Infrastructure**: ShowList Class
    * What it does: The main data structure used during the lifetime of the program. 
    * Justification: This class is referenced in most commands. It is essential that is implemented in the most efficient and safe way, to ensure the smooth running of the app. 
    * Highlights: Made the design choice of using a HashMap instead of the typical arraylist. This is because considerations were made regarding the searching time of the shows as this has an O(1) reference time instead of O(N) for the iterative search of an arraylist.

* **New Feature**: UpdateShowProgress Command
    * What it does: Updates the current episode of the show the user is watching.
    * Justification: Essential function of the program, needed for WatchNext to serve its basic purpose. 

* **Implementation**: Duke Class
    * What it does: The 'Main' class of WatchNext, which is run on initialisation and is needed for the program to start running in the first place. 
    * Justification: Many of the moving pieces of the code is initialised here. Any errors in the design of this class would echo throughout the code and cause many errors and may warrant unsightly hot-fixes if Duke class is not written properly. 

* **Enhancement and Testing**: UppdateShowSeasonCommand code and Test
    * What it does: The command to update the current season the user is at and all the related testing portions. 
    * Justification: This was the first test that included the complicated classes and was used as a basic example by the other members to write the other tests. 


* **Contributions to documentation**:
    * Added documentation in user guide for `add` command and the introduction portions [#134](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/134)
    * General formatting updates and fixes in user guide [#134](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/134)
    * Added documentation to some methods in the InputParser Class. Included documentation to most methods in UI Class (PR [#65](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/65) , [#204](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/31))


* **Contributions to DG**:
    * Design portion of the DG [#77](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/77/files) [#75](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/75/files)
    * Testing portion of DG (initial) [#77](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/77/files)
    * Value proposition and other user facing portions [#77](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/77/files)
    * Diagrams were done together with @judohwa. Design Architecture diagram,[#122](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/122/files) Storage class diagram, design sequenece diagram [#134](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/134/files)
    * General formatting of developer guide

* **Contributions to team-based tasks** :
    *  Wrote much of the Introduction and infrastructure portions of the DG [#75](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/75/files)
    *  Noted down issues to ensure the team has proper direction and knows which portions need to be changed in the code. [#107](https://github.com/AY2021S1-CS2113T-W12-3/tp/issues/107)  [#34](https://github.com/AY2021S1-CS2113T-W12-3/tp/issues/34)

* **Review/mentoring contributions**:
    * Made major changes to the structure of the work of other team members. [#36](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/36/files)
    * Proof read and edited portions of the UG not done by me. [#89](https://github.com/AY2021S1-CS2113T-W12-3/tp/pull/89/files)
    * Made many major decisions regarding the structure of the project, having experienced writing a phone app in a language similar to Java (Dart), I anticipated a few issues that I faced during my own work and applied the lessons learnt accordingly to the project so as to avoid similar mistakes.

