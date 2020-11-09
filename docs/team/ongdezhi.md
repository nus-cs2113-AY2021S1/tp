# Ong De Zhi - Project Portfolio Page

## Overview

**AniChan** is an all-rounded desktop application meant to improve the workflow efficiency and time management of anime translators. It has also been optimized for fast typists, as such the user will interact with it through a command-line interface.

### Summary of Contributions

*   **New Feature:** Added the ability to estimate the time needed to translate a script.
    *   What it does: Allows the user to figure out the time needed to translate a script based on the amount of words they can translate in an hour (words per hour), or by using the average translator's speed (3 different words per hour values) to generate 3 timings.
    *   Justification: Being able to estimate the time needed more accurately can help the user in better managing and planning their time.
    *   Highlights: The challenge in this implementation is in ensuring a single valid file is provided, since the file name is a user input, it could contain special characters, whitespaces, file extensions, and even invalid file name characters. Hence, it needs to consider all these factors to ensure a valid file is provided for estimation. Moreover, it also needs to ensure the optional parameter is not accidentally validated as part of the file name.
                
*   **New Feature:** Added the ability to create, list, select, and delete watchlist.
    *   What it does: Allows the user to create, list, select, and delete watchlist.
    *   Justification: This feature provides the user with a way to keep track of animes and group them based on their own criteria, and this helps them to be more organized.
    *   Highlights: Besides the need to validate the inputs (e.g. not empty, is a positive integer, etc.), this feature also maintains the active watchlist which is used by the input prompt. Therefore, it also needs to ensure the active watchlist remains valid at all times because the application could fail if it ever becomes invalid.
                
*   **New Feature:** Added the ability to save and load watchlist data automatically.
    *   What it does: Helps the user to automatically save their watchlist data into the file `watchlist.txt`, and load these data automatically when the application is launched.
    *   Justification: Being an application to help users keep track of their animes and ensure they stay organized, persistent storage would be needed otherwise, users would have to re-enter their watchlist data every time they use the application.
    *   Highlights: During the loading of the watchlist data, each watchlist entry (line) recorded in `watchlist.txt` is validated to ensure they are not corrupted such that it can form a valid watchlist object. **If a watchlist is corrupted, then only that watchlist will not be loaded**, the rest of the watchlist(s) will still be loaded if they are valid.
    
*   **New Feature:** Added the ability to save and load user data automatically.
    *   What it does: Helps the user to automatically save their profile details into the file `user.txt`, and load these data automatically when the application is launched.
    *   Justification: This allows the application to greet the user so they can feel welcomed.
    *   Highlights: The user data that is loaded needs to be validated to ensure it is not corrupted (e.g. invalid name or gender), and it should not be loaded if it is corrupted. Also, it needs to ensure that the failure to load user data should not prevent the watchlist and bookmark data from being loaded.
    
*   **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ongdezhi&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=OngDeZhi&tabRepo=AY2021S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other).

*   **Documentation:**
    *   User Guide: 
        *   Add documentation for the features `watchlist`, and data saving and loading: [#138](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/138).
        *   Add documentation for the feature `estimate`: [#146](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/146).
    *   Developer Guide:
        *   Add Setting Up Guide, Architecture Design, and StorageManager component: [#151](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/151).
        *   Add implementation details of `estimate` and `watchlist` feature: [#197](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/197).
        *   Contributed to the sections on target user profile, user stories, and non-functional requirements: [#207](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/207).

*   **Team-based Tasks**
    *   Helped to organize the classes into packages: [#63](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/63).
    *   Increased code coverage: [#98](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/98), [#139](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/139), [#140](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/140).
    *   Fixed bugs: [#158](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/158).
    *   Contributed to User Guide formatting: [#199](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/199), [#226](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/226).

*   **Beyond Project Team Tasks**
    *   Reported bugs and offered suggestions for a team in PE-D.
    *   Reviewed other teams' User Guide and Developer Guide.
