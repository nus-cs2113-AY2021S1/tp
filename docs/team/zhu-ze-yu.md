# Zhu Zeyu - Project Portfolio Page

## Project: KAJI

### 1. Overview 
KAJI is a schedule manager that implements Spaced Repetition, optimised for use via a Command Line Interface (CLI).
It is written in Java, and has about 2 kLoC.

### 2. Summary of Contributions
Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=F11-3&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=functional-code&tabOpen=true&tabType=authorship&tabAuthor=Jane-Ng&tabRepo=AY2021S1-CS2113T-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code)
* **New feature:** list module/chapters/flashcards base on different level.
* **New feature:** save and load flashcards(.txt file) in corresponding chapter(folder).
* **Enhancement implemented:** Save the revision completed in the session/in a day.
* **Documentation:**
    * User Guide:
        * Added the overview section
        * Added the description for the features: `list modules/chapters/flashcards`, `edit module`, `view history` and `exit`
        * Added command summary
    * Developer Guide:
        * Added overall architecture diagram 
        * Added implementation details of the `ListModulesCommand`, `ListChaptersCommand`, `ListCardsCommand` and `HistoryCommand` features. 
        