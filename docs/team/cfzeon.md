# Zeon Chua Feiyi - Project Portfolio Page

## PROJECT: The Schwarzenegger

## Overview

The Schwarzenegger is a desktop command line interface-based app for managing all your needs regarding fitness. With the built-in personal assistant, you are able to track your daily workout and diet sessions based on your profile. If you can type fast, The Schwarzenegger can help you maximise your efficiency for maintaining fitness.

### Summary of Contributions

* **Major enhancement**:
  * **Search Diet Sessions**
    * Functionality: This enhancement allows the user to search for specific diet sessions within a date range of a specified tag.
    * Justification: This feature improves the product a lot more significantly as a user would want to search things they ate within a date range, or whatever they ate within a specified tag i.e. breakfast. This makes things more convenient for users as they would not need to manually filter through so much information to find what they need.
    * Highlights: The search function allows for 3 parameters, the start date, the end date and the tag. This makes it very easy to filter for specific information. 
  * **Edit Diet Session**
    * Functionality: This enhancement lets users access and edit diet sessions that they have created before by searching through the save folder and loading the specified file.
    * Justification: The feature allows users to edit a previously created diet session instead of having to delete it and creating another one from scratch.
    * Highlights: Users can edit the file like they would with a new file, as it is an instantiation of the same DietSession.
  * **Clear all diet sessions**
    * Functionality: This enhancement allows users to remove all diet session records from their local storage.
    * Justification: The feature provides an accessible interface to allow users to delete all of their diet sessions at one go.
    * Highlights: Clear iterates through every file in the folder then deletes them one by one.
  * **Consistent file saving**
    * Functionality: This enhancement saves the diet session after every command that a user inputs into diet session.
    * Justification: The feature prevents users from accidentally deleting their data during usage.
    * Highlights: Users are protected against accidental file modifications and errors. 
* **Minor enhancement**:
  * **Get Total Calories for a specific date**

    * This feature opens every file within a specified date and sums up the total calories within the files with matching dates. It is used to calculate the required calories left to consume to reach the daily requirement in the view command in profile. 
    * **Code contribution**: [Functional and Test code](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=CFZeon&tabRepo=AY2021S1-CS2113T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
    * **Other contributions**:
      * Documentation:
        * Added instructions and format for all diet manager related commands in the User Guide. \(Pull Request: [\#84](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/84/files), [\#140](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/140)\)
        * Added and updated instructions and format for all diet manager related commands in the Developer Guide. \(Pull Request: [\#149](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/149)\)
        * Added and updated user stories in the Developer Guide \(Pull Request: [\#219](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/219)\)
      * Community:
        * Review Developer Guide from other teams in the class\(with non-trival review comments.\) \(ModTracker: [\#62](https://github.com/nus-cs2113-AY2021S1/tp/pull/62)\)
        * Reported bugs for other teams in the class \(example: [ped](https://github.com/CFZeon/ped/issues)\)
        * Evaluated code, tested and provided feedback to teammates during many online meetups.

    **Contributions to User Guide**

    ```text
    Below are my contributions to the User Guide.
    They show my ability to write documentation for end-users.
    ```

    * [Viewing Help](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-help)
    * [Starting a New Diet Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-start)
    * [Listing All Past Diet Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-list)
    * [Editing a Past Diet Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-edit)
    * [Deleting a Past Diet Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-delete)
    * [Clearing All Past Diet Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-clear)
    * [Searching for Past Diet Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-search)
    * [Returning to Main Menu](https://ay2021s1-cs2113t-f11-1.github.io/tp/UserGuide.html#diet-end)

    **Contributions to Developer Guide**

    ```text
    Below are my contributions to the Developer Guide.
    They show my ability to write technical documentation for other developers.
    ```

    * [List Out All Commands](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#list-out-all-commands)
    * [Start Recordings Diet Data](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#start-recording-diet-data)
    * [List All Past Diet Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#list-all-past-diet-sessions)
    * [Edit a Past Diet Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#edit-a-past-diet-session)
    * [Delete a Past Diet Session](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#delete-a-past-diet-session)
    * [Clear All Past Diet Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#clear-all-past-diet-sessions)
    * [Search for Past Diet Sessions](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#search-for-past-diet-sessions)
    * [Exit the Diet Manager](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#exit-the-diet-manager)
    * [Appendix B: User Stories](https://ay2021s1-cs2113t-f11-1.github.io/tp/DeveloperGuide.html#appendix-b-user-stories)

