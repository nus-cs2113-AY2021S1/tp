# Ong De Zhi - Project Portfolio Page

## Project: AniChan

**AniChan** is a desktop application created for anime translators to help efficiently manage their workload and time. It has a wide variety of tools that can help improve their workflow efficiency and translation accuracy. **AniChan** is built for fast typists, as such the user will interact with it through a CLI. It is written in Java with almost 14K LOC. 

### Summary of Contributions

*   **New Feature:** Added the ability to estimate the time needed to translate a script.
    *   What it does: Allows user to estimate the time needed to translate a script.
    *   Justification: This feature allows users to find out the time needed to translate a script, and this helps them to better manage and plan their time.
    *   Highlights: A file name could contain special characters, whitespaces, and even file extensions in some cases, and that makes the validation process highly complicated, and hence, this feature would need to perform extensive checks on the file name to ensure that it is a single valid file. Moreover, there is a need to check for the existence of the optional parameter to ensure it is not missed or accidentally validated as part of the file name, and also to ensure the parameter value supplied is valid.

*   **New Feature:** Added the ability to create, list, select, and delete watchlist.
    *   What it does: Allows the user to create, list, select, and delete watchlist.
    *   Justification: This feature provides users with a simple way to keep track of animes and group them based on their own criteria, and this helps them to be more organized.
    *   Highlights: Besides the need to validate the inputs (i.e. not empty, is a positive integer, etc.), this feature is also in-charge of maintaining the active watchlist pointer. This pointer is used by the input prompt, and if it is ever invalid, the application would not function as intended.

*   **New Feature:** Added the ability to save and load watchlist data automatically.
    *   What it does: Helps the user to automatically save their watchlist data into the file `watchlist.txt`, and loads these data automatically when the application is launched.
    *   Justification: Being an application to help users keep track of their animes and ensure they stay organized, persistent storage would be needed otherwise, users would have to re-enter their data every time they use the application and that can result in a frustrating experience.
    *   Highlights: During the loading of the watchlist data, each entry recorded in `watchlist.txt` would be validated to ensure they are not corrupted. **If a watchlist is corrupted, then only that watchlist will not be loaded**, the rest of the watchlist(s) will still be loaded if they are valid. A corrupted watchlist is defined as one that has information missing, has an invalid format, or it contains anime ids that are not found in our database.

*   **New Feature:** Added the ability to save and load user data automatically.
    *   What it does: Helps the user to automatically save their profile details into the file `user.txt`, and load these data automatically when the application is launched.
    *   Justification: This feature aims to allow existing users of AniChan to feel welcomed when they use our application.
    *   Highlights: During the loading of user profile data, the data would be validated to ensure they are not corrupted (i.e. invalid username or gender), and if the data is found to be corrupted, the user profile data would not be loaded into the application. In addition, the failure to load user profile data does not prevent existing watchlist and bookmark data from being loaded.

*   **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ongdezhi&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=OngDeZhi&tabRepo=AY2021S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

*   **Documentation:**
    *   User Guide: 
        *   Added documentation for the features `watchlist` and storage: [#138](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/138).
        *   Added documentation for the feature `estimate`: [#146](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/146).
    *   Developer Guide:
        *   Added Setting Up Guide, Architecture Design, and StorageManager component: [#151](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/151).
        *   Added implementation details of `estimate` and `watchlist` feature: [#197](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/197).
        *   Contributed to the sections on target user profile, user stories, and non-functional requirements: [#207](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/207).

*   **Team-based Tasks**
    *   Helped to organize the classes into packages: [#63](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/63).
    *   Increased code coverage: [#98](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/98), [#139](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/139), [#146](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/146).
    *   Fixed bugs: [#158](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/158).
    *   Helped to fix formatting issues with the User Guide: [#199](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/199), [#226](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/226).

*   **Beyond Project Team Tasks**
    *   Reported bugs and offered suggestions for other team in PE-D.
    *   Reviewed other team's User Guide and Developer Guide.
