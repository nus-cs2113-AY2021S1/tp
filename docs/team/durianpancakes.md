# Chan Xu Hui - Project Portfolio Page

## Overview
Plan&Score is a Command Line Interface (CLI) Application that possesses an in-built timetable, 
quiz and contacts tracker. It is written in Java. 

### Summary of Contributions
* New Feature: Added the ability for Plan&Score to perform I/O operations with .txt files.

* New Feature: Added the ability for Plan&Score to support Tuition events.

* Code Contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=durianpancakes&tabRepo=AY2021S1-CS2113T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* Project Management:
    * Reviewed PRs: 
        * [#356](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/356)
        * [#346](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/346)

* Community:
    * Reported bugs and suggestions for other teams: 
        * [#57](https://github.com/nus-cs2113-AY2021S1/tp/pull/57)

* Enhancement to existing features: 
    * Added the ability for Plan&Score to display current and next week's events in a Week View format. 
        * What it does: Instead of listing events in a vertical list format, Plan&Score can list them in a 
                    more intuitive and eye-pleasing way.
        * Justification: Listing a long list of events in a list format can lead to inconvenience in
                     scrolling by the user. By displaying it in a horizontal timetable manner, 
                     Plan&Score makes efficient use of the Command Line Interface's display real
                     estate.
        * Highlights: The implementation was challenging as it required me to come up with the entire 
                  logic of processing the events to be printed in the right format. The lack of
                  experience in this also added to the challenge.
    * Assisted in the migration of String date-times used in Plan&Score to Calendar type. [#206](https://github.com/AY2021S1-CS2113T-W12-4/tp/issues/206) 

* Documentation:
    * User Guide: 
        * Added documentations for `Tuition`, `list event week`, `list event nextweek` and `Troubleshooting` sections.
        * Added initial version of the `Command Summary`
    * Developer Guide:
        * Added implementation details of the `UserInterface`, `list event week/nextweek` and `Storage` features.
        * Added manual testing instructions for missing and corrupted files.
        * Added UML diagrams for figures 2, 6, 7, 8, 9, 19

