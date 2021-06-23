# Wang Zesong - Project Portfolio Page

## PROJECT: The Schwarzenegger

## Overview

The Schwarzenegger is a desktop command line interface-based app for managing all your needs regarding fitness. With the built-in personal assistant, you are able to track your daily workout and diet sessions based on your profile. If you can type fast, The Schwarzenegger can help you maximise your efficiency for maintaining fitness.

### Summary of Contributions

* **Major enhancement**:
  * **Search Workout Sessions**
    * Functionality: This enhancement allows the user to search for specific workout sessions created on a certain date range or containing certain tags.
    * Justification: This feature improves the product significantly because a user can easily find the session she intends to edit or delete.
  * **Command Library**
    * Functionality: This enhancement organises the commands into a hashmap and retrieves correct Command object using user's input as key.
    * Justification: This feature improves the code neatness and reduce long selection statement for menus with many availble commands. 
  * **Design Workout Session Meta-info File**
    * Functionality: This enhancement creates the program create a file to store the meta-information of past workout sessions. 
    * Justification: This allows the program not to have to load every past session files at one go but only need to load the meta-information of each session. This greatly reduce the initialization time and makes the program more scalable. Past record files are only loaded on request.
* **Minor enhancement**:
  * **Date Parser**
    * This feature will parse the user's input into LocalDate. The parser supports 9 formats which gives user max flexibility when using the application.
  * **List Workout Sessions**
    * This feature allows the users to view records only ranging from a given period. This helps the users to avoid seeing long list of workout sessions from long time ago.
* **Code contribution**: [Functional and Test code](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#search=wgzesg&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=wgzesg&tabRepo=AY2021S1-CS2113T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
* **Other contributions**:
  * Project management:
    * Ensure that the team members are always aware of weekly deadlines and deliverables.
  * Documentation:
    * Added instructions and format for all workout manager related commands in the User Guide. \(Pull Request: [\#81](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/81), [\#83](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/83)\)
    * Added and updated instructions and format for all workout manager related commands in the Developer Guide. \(Pull Request: [\#132](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/132)\)
    * Added and updated diagrams in the Developer Guide \(Pull Request: [\#106](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/106)\)
  * Community:
    * Reported bugs for other teams in the class \(Pull Request: [\#36](https://github.com/nus-cs2113-AY2021S1/tp/pulls?q=is%3Aopen+is%3Apr+CS2113-T13-3+) \)
    * Perform manual testing, reported bugs and gave suggestions to teammates.

#### Contributions to the User Guide

```text
Given below are sections I contributed to the User Guide.   
They showcase my ability to write documentation targeting end-users.
```

* [Starting a New Workout Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#workout-start)
* [Listing All Past Workout Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#workout-list)
* [Editing a Past Workout Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#workout-edit)
* [Deleting a Past Workout Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#workout-delete)
* [Clearing All Past Workout Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#workout-clear)
* [Searching for Past Workout Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#workout-search)
* [Returning to Main Menu](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#workout-end)
* [Notes](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#notes)

#### Contributions to the Developer Guide

```text
      Given below are sections I contributed to the Developer Guide. 
      They showcase my ability to write technical documentation and the technical depth of my contributions
      to the project.
```

* [Architecture](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#architecture)
* [Start recordings workout data](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#creating-a-new-workout-session)
* [List all past workout sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#442-listing-past-workout-sessions)
* [Edit a past workout session](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#443-editing-workout-session)
* [Delete a past workout session](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#444-deleting-a-workout-session)
* [Search on past workout sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#445-searching-based-on-conditions)
* [Appendix F: Supported Formats of Date Input](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#appendix-f-supported-formats-of-date-input)

