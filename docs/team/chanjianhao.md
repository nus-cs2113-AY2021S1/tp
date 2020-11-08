# Chan Jian Hao - Project Portfolio Page

## Overview

**AniChan** is a desktop application created for anime translators to help efficiently manage their workload and time. It has a wide variety of tools that can help improve their workflow efficiency and translation accuracy. **AniChan** is built for fast typists, as such the user will interact with it through a CLI. It is written in Java with almost 14K LOC. 

### Summary of Contributions

*   **New Feature:** Added the ability to create `User`.
    *   What it does: On first execution, prompts user for his name and gender to create `User` profile.
    *   Justification: This feature allows program to know user better so that program may greet user appropriately as part of good user experience we hope to provide.
    *   Highlights: Notably, `User` is a subclass of `Human` abstract class. Other classes such as `Character` and `VoiceActor` inherits `Human` class as part of our OOP practice when coding **AniChan** which stores lots of anime data which comes with lots of voice actors and characters. **User** class contains many interesting methods meant for controlling `Workspace` feature of **AniChan**.

*   **New Feature:** Added the ability to create, list, switch, and delete `Workspace`.
    *   What it does: Allows the user to create, list, select, and delete `Workspace`.
    *   Justification: This feature enables the user to have segregation in his translation work. As `Watchlist` and `Bookmark` are contained in individual `Workspaces`.
    *   Highlights: `Workspace` works closely with core components of **AniChan** such as `User`, `Watchlist`, `Bookmark` and notably, `Storage`. To ensure secure and safe creation of `Workspace` when program exits, layers of string validation and sanitization are implemented to ensure no unexpected behaviors when creating `Workspace` in `data` folder.

*   **New Feature:** Added customised exception class, `AniException`.
    *   What it does: Allows application to throw custom exceptions specific to **AniChan**.
    *   Justification: There needs to be a way to handle exception which are caused by **AniChan**.
    *   Highlights: Made a special exception package and class which extends Java default `Exception` class.

*   **New Feature:** Added logging class `AniLogger`.
    *   What it does: Logs information during **AniChan** runtime to both console and file system `data/AniChan.log` file.
    *   Justification: There needs to be a way to handle logging for all the classes in **AniChan** without duplicating Java `logger` everywhere.
    *   Highlights: Implemented method for all classes to easily get logger for their own logging usage. Standardized application-wide **AniChan** logging settings and level for both console and file system. **AniLogger** is fully capable of handling add and removals of console and file handlers and manage logs creation and exceptions, if any.

*   **New Feature:** Implemented Workspace delete feature for `Storage`.
    *   What it does: Deletes `Workspace` on the file system when `Workspace` is deleted in **AniChan**.
    *   Justification: When `Workspace` is deleted in **AniChan**, the leftover folder and files in `data` should be deleted as well.
    *   Highlights: Implemented a safe recursive delete approach for `Workspace` deletion, where it searches recursively for leftover `Watchlist`/`Bookmark` data to delete first before deleting the folder. Exceptions and input validation built in for deletion to prevent file system related issues to crash the program or cause unexpected results.

*   **Documentation:**
    *   README.md:
        * Designed **AniChan** logo.
        * Created badges on various DevOps and statuses: [#154](https://github.com/AY2021S1-CS2113T-F12-2/tp/issues/154).
        * Add short writeup to introduce **AniChan** and provide useful links to pages like user guide and developer guide.
    *   User Guide: 
        *   Contributed to writeup on Quick Start.
        *   Documented guide on `Workspace` management.
    *   Developer Guide:
        *   Contributed to writeup on `User` architecture and diagram.
        *   Contributed to `Workspace` related user stories and manual testing guide: [#285](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/285).
        *   Documented `Workspace` implementation and its diagrams.
        *   Documented the section on 'Documentation, Logging, Testing, and DevOps': [#195](https://github.com/AY2021S1-CS2113T-F12-2/tp/issues/195).

*   **Team-based Tasks**
    *   Created team organization and GitHub repository.
    *   Contributed to input sanitization and validation of overall program.
    *   Refactored common code found in Main and Ui, and assisted to delete redundant imports and variables [#152](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/152), [#256](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/256).
    *   Setting up GitHub pages and Gradle.
    *   Maintaining the issue tracker.
    *   Setup of team's Telegram & Discord channel for communication.
    *   Implemented additional CI checks for repository quality: [#179](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/179).
    *   Assisted in team's request for Java libraries: [#43](https://github.com/nus-cs2113-AY2021S1/forum/issues/43), [#78](https://github.com/nus-cs2113-AY2021S1/forum/issues/78), [#111](https://github.com/nus-cs2113-AY2021S1/forum/issues/111).
    *   Increased code coverage: [#150](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/150), [#221](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/221).

*   **Beyond Project Team Tasks**
    *   Reported substantial amount of bugs in another team's repository: [#101](https://github.com/AY2021S1-CS2113-T13-4/tp/issues/101), [#94](https://github.com/AY2021S1-CS2113-T13-4/tp/issues/94), [#93](https://github.com/AY2021S1-CS2113-T13-4/tp/issues/93), [#96](https://github.com/AY2021S1-CS2113-T13-4/tp/issues/96).
    *   Provided helpful feedback for team NavNUS: [#9](https://github.com/nus-cs2113-AY2021S1/tp/pull/9/files/cabbf353f022bfb19f0bdeeeb17ba7572bed1484).

*   **Tools:**
    *   Integrated **Codacy** static code analysis to improve code and documentation quality within team.
    *   Integrated **Travis CI** for DevOps purposes.
    *   Integrated **Codecov** for coverage tracking.
    *   Added **Discord bot** for GitHub tracking.

*   **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chanjianhao&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=ChanJianHao&tabRepo=AY2021S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
 