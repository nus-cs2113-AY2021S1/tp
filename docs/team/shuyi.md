# Wang Shuyi - Project Portfolio Page

## Overview
#### Project: Nav@NUS
Nav@NUS is a Command Line Interface (CLI) application that aids users in getting around NUS, tailor made for anyone 
unfamiliar to the campus. 

Nav@NUS consists of three main features, namely Route, Dine and Fav, which helps the user to 
plan their bus routes, find dining options, and personalize the application respectively.

## Summary of Contributions
Given below are my contributions to the project.

#### New features 
* Find dining options within a faculty: `/dine`
    + Allows the user to see all dining options within a user-specified faculty.
    + **Justification:** This is one of the main features of the product as it helps the user find and discover dining 
    outlets in NUS. 
    
* Find specific dining outlet information: `/dineinfo`
    + Allows the user to see the location and opening hours of a user-specified dining outlet.
    + **Justification:** This is one of the main features of the product as it helps the user find important information 
    regarding the dining outlet of interest, such as its location and opening hours.
    
* List all faculties: `/faculty`
    + Provides the user with a list of faculties in NUS.
    + **Justification:** This feature allows users to see the list of faculties that can be used for the `/dine` 
    feature. It improves the usability of the product since some users may not be familiar with the faculties in NUS.

* List routes of all buses: `/allbus`
    + Provides the user with a list containing the routes of all shuttle buses available in NUS.
    + **Justification:** This feature improves the usability of the product as it informs the user of all available 
    shuttle buses in NUS and their routes, so that they can plan their travel routes better.

* List all favorite commands: `/listfav`
    + Allows the user to see a list of all his favorite commands with indexes and descriptions.
    + **Justification:** This feature improves the usability of the product as it allows the user to see the indexes 
    and descriptions of the favorite commands, which are needed for the `/deletefav`, `/execfav`, `/descfav` features.

* Clear all favorite commands: `/clearfav`
    + Allows the user to clear his list of favorite commands.
    + **Justification:** This feature improves the usability of the product as it allows the user to delete all the 
    commands in the list of favorite commands, instead of removing them one by one using the `/deletefav` feature.

#### Code contribution
* RepoSense [link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=F14-3&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=mrwsy1&tabRepo=AY2021S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

#### Contributions to documentation
* User guide:
    + Added the documentation for the following features: `/dine`, `/dineinfo`, `/faculty`, `/allbus`, `/listfav`, 
    `/clearfav`. ([#85](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/85), [#145](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/145))
    + Helped to fix formatting issues and typos.
* Developer guide:
    + Added implementation details and UML diagrams for the following features: `/dine`, `/dineinfo`, `/faculty`, 
    `/allbus`, `/listfav`, `/clearfav`. ([#114](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/114), [#186](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/186))

#### Review/mentoring contributions:
* PRs reviewed (with non-trivial review comments): [#205](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/205)

#### Contributions beyond the project team
* Reported bugs for another team's [project](https://github.com/mrwsy1/ped/issues)
* Provided suggestions for another team's [developer guide](https://github.com/nus-cs2113-AY2021S1/tp/pull/50/files/8633eb176251c1920fbfc15b46c51d59c7ef1e4d)