# Joseph Abraham - Project Portfolio Page

## Overview
E-Duke-8 (pronounced "Educate") helps CS2113/T students learn and understand software engineering and Object-Oriented Programming (OOP) principles through a gamified platform and enhances their learning experience. 


### Summary of Contributions

- **New Feature:** Added the functionality to be able to bookmark questions during a quiz.
    - What it does: Store's a question of the user's choice in an array list of type `Displayable`. Whenever the user is in a quiz and wishes to store a particular question of interest for the future, the user can
    type in "bookmark" in the command-line, and the question will be stored. Once the user finishes the quiz, the user may type "bookmark" in the main menu to view the list of bookmarked question,
    at which point, each question's options will be printed under the question, with the words "[Correct Answer]" next to the option whose attribute `isCorrectAnswer` is true.
    The user may also delete a bookmark by typing "bookmark delete <index number of the question>", at which point the question indicated will be deleted.
    - Justification: This feature is important in terms of using the product, as from time to time questions that are hard to figure out may get tested, and the user
    would want to have a way to store this question for perusal later. By consolidating all these bookmarks in one place, the user is capable of referring to all the questions that they have had doubts about
    in one central location. This makes revising an easier process as well.
    - Highlights: This command requires interaction with the `Ui`, `Question` and `Option` classes in order to be able to store and display the questions effectively. It requires altering the code of some pre-existing
    commands, and required some fine-tuning to ensure the `Ui` object was responding with the correct statements whenever prompted.
    
    Code contributed: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=josephhhhhhhhh&tabRepo=AY2021S1-CS2113T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
    
- **Enhancements to existing features:**
    - Increased flexibility of parsing quiz commands by allowing the topic field and number of questions field to be interchangeable in position. (Pull request [#126](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/126))
    
- **Documentation:**
    - User Guide:
        - Added documentation for the `bookmarks` feature. [#169](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/169)
    - Developer Guide:
        - Added documentation for the Logic Component of the Developer Guide. [#123](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/123), [#138](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/138)  

- **Community:**
    - PRs reviewed (with non-trivial review comments): [#51](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/51), [#59](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/59), [#207](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/207) ,[#212](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/212)
    - Reported bugs and suggestions for other teams in the class. [#1](https://github.com/josephhhhhhhhh/ped/issues/1), [#2](https://github.com/josephhhhhhhhh/ped/issues/2), [#3](https://github.com/josephhhhhhhhh/ped/issues/3), [#4](https://github.com/josephhhhhhhhh/ped/issues/4), [#5](https://github.com/josephhhhhhhhh/ped/issues/5), [#6](https://github.com/josephhhhhhhhh/ped/issues/6), [#7](https://github.com/josephhhhhhhhh/ped/issues/7), [#8](https://github.com/josephhhhhhhhh/ped/issues/8),