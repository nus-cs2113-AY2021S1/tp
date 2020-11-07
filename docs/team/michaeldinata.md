# Michael Dinata - Project Portfolio Page

## Overview

**AniChan** is a desktop application created for anime translators to help efficiently manage their workload and time. It has a wide variety of tools that can help improve their workflow efficiency and translation accuracy. **AniChan** is built for fast typists, as such the user will interact with it through a CLI. It is written in Java with almost 14K LOC. 

### Summary of Contributions

*   **New Feature**: Added the ability to add and remove an anime to and from his active watchlist.
    *   What it does: Allows the user to add a specific anime into his active watchlist, and he can also remove an anime from his active watchlist.
    *   Justification: The user may want to keep track of the anime he wants to watch or translate next. After watching a certain anime in his watchlist, he is then able to remove the anime from the watchlist to keep it clean of anime that he has watched.
    *   Highlights: The `add` feature needs to ensure that the anime the user wants to add is not something that is out of the range of anime that the program currently has. On the other hand, the `remove` feature first ensures that there is an anime in the watchlist, before ensuring that the anime the user wants to delete is within the watchlist.

*   **New Feature:** Added the ability to view all the anime in active watchlist, or a specific watchlist.
    *   What it does: Allows the user to view all the anime in his active watchlist, or in a specific watchlist that he specifies in the command.
    *   Justification: In the event that the user forgot what anime he wanted to watch or translate, and has added it into his watchlist before, he is able to check all the anime.
    *   Highlights: This enhancement allows users to quickly view the anime in his current watchlist, but he is able to view all the anime in other watchlists by specifying an optional command.

*   **New Feature:** Added the ability to view the information of a specific anime.
    *   What it does: Allows the user to find out more about a specific anime.
    *   Justification: The user may want to find out more details of an anime such as the number of episodes and its release date. The `info` command will provide users with the information that they need.

*   **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=michaeldinata&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=michaeldinata&tabRepo=AY2021S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

*   **Documentation:**
    *   User Guide:
        *   Added documentation for the features `info`, `add`, `remove`, and `view`: [#189](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/189)

    *   Developer Guide:
        *   Added implementation details of `info`, `add`, `remove` and `view` features: [#202](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/202)
        *   Added sequence diagrams and user stories: [#209](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/209)

*   **Team-based Tasks:**
    *   Fixed bugs: [#223](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/223)
    *   Implemented Help feature: [#110](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/110)
    *   Wrote additional tests for existing features to improve coverage: [#265](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/265)

*   **Beyond Project Team Tasks**
    *   Review the User Guide and Developer Guide of other teams and gave feedbacks on them.
    *   Reported bugs and suggestions for other teams in PE-D.
