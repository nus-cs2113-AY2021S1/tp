---
layout: page
title: Johnson Yee's Project Portfolio Page
---

## Project: Nav@NUS

Nav@NUS is a useful command line interface (CLI) application to guide you in navigating around the NUS campus
via the school's shuttle services. It is written in Java, and has about 4 kLoC.

Given below are my contributions to the project.

* **RouteMapCommand**: Added the ability to check for full bus routes of a user-selected bus
  * What it does: Allows users to check for full bus routes of selected buses
  * Justification: This feature improves the product significantly because the user could use this information to plan
  indirect bus routes.
  * Highlights: This enhancement affects commands to be added in later stages of this application eg.indirect bus routes.

* **Search Frequency**: Implemented search frequencies of bus stops to inform users about their most search bus stop.
* **Delete Fav**: Added the ability to delete favourite commands to customise list of favourite commands.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Johnson-Yee&tabRepo=AY2021S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Enhancements to existing features**:
  * Updated error handling on freqStorage 

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_