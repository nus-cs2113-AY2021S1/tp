# Eyo Wei Chin - Project Portfolio Page

## Project: AniChan

AniChan is a desktop application created for anime translators to help efficiently manage their workload and time. It has a wide variety of tools that can help improve
thier workflow effiency and translation accuracy. AniChan is built for fast typers, as such the user will interact with it through a CLI. It is written in Java with almost 14K LOC. 

Given below are my contributions to the project.

### Summary of Contributions

*   **New Feature**: Added `Browse` which is the ability to access and view all anime with ease.
    *   What it does: Is a useful feature that allows users to look through all available anime series
    The browse feature utilises a page system, so that the user can 'flip' through the list of anime series. Users will be able to 
    browse through highly rated anime or in alphabetical order.
    *   Justification: By having a `browse` feature, users can easily access and view all anime series that are available
    As a translator, this will be an essential tool to find a series for potential work opportunity or for future references
    *   Highlights: The implementation was challenging as many of the anime titles have special unicode characters which is viewed by the system as more than 1 char, 
    which seen by users as 1 char only. This made it difficult to print the anime in a consistent format. Additionally, there was several design considerations that I had to make in order
    to ensure that `browse` was able to fulfil its purpose and adhere to the constraints, these considerations could is further elaborated in the developer guide [here](#docs/DeveloperGuide.md)

*   **New Feature**: Added `Search` which is the ability to find any anime with a search term.
    *   What it does: Allows users to search for a specific anime by its full title, just a keyword or with a genre.
    *   Justification: `search` was built to find difficult to remember anime titles such as `MUSHI-SHI` or long anime titles (>52 characters)
    This feature should be used in conjunction with the watchlist and bookmark feature to effectively find the anime that the translator wants quickly without much hassle.
    *   Highlights: The challenges of this implementation was with input validation, parsing the parameters into the command and finding the anime that fits the search term.
    
*   **Existing Features**
    *   Helped contributed to development of the Switch Workspace feature [#106](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/106) [#101](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/101) [#99](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/99)
    *   Helped contribute to writing of the CommandParser logic [#128](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/128)
    *   Helped to refactor Main.java [#261](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/261)
    *   Decoupled UI from Parser, Command classes for better cohesiveness [#80](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/80)
    *   Contributed to the development of Anime Class [#22](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/22)

*   Code contributed: [RepoSense](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#search=EYOWEICHIN&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=EyoWeiChin&tabRepo=AY2021S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

*   **Documentation**
    *   User Guide:
        *   Add documentation for the features `browse` and `search` [#190](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/190) [#111](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/111/files)
    *   Developer Guide:
        *   Add Command and Starting Up sequence diagrams [#261](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/261)
        *   Add implementation details of `browse` feature [#196](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/196) [#161](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/161)
        *   Contributed to the design of Architectural diagram
        *   Contributed to the design and their write-ups of Ui, Parser, Command, User Class diagram designs [#173](https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/173) [#149](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/149/files)

*   **Team-based tasks**
    *   Created labels and milestones for the team GitHub
    *   Created issues based on team's discussions and meeting 
    *   Compiled and built the final jar files for releases
    *   Added comprehensive JUnit testing to increase code coverage [#182](#https://github.com/AY2021S1-CS2113T-F12-2/tp/pull/182)

*   **Beyond Project Team Tasks**
    *   Reported bugs for a team in PE-D
    *   Reviewed another team's Developer Guide 
    *   Reviewed another team's User Guide 
