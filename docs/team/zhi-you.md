# Tang Zhi You - Project Portfolio Page

## Overview

E-Duke-8 (pronounced "Educate") helps CS2113/T students learn and understand software engineering and Object-Oriented Programming (OOP) principles through a gamified platform and enhances their learning experience. 


## Summary of Contributions

- **New Feature:** Added the ability for users to earn points from answering questions in quizzes and view their quiz attempts' statistics.
  - What it does: Calculates user's aggregated quiz statistics using `UserStatsCalculator` and calculates their topic-level quiz statistics using `TopicalStatsCalculator`. Relevant statistics include how accurate they are and the points they have earned from answering questions in quizzes. When the user gives the command to show his or her statistics, the relevant data will be calculated and displayed clearly to him or her. 
  - Justification: This feature improves the product significantly because it allows users to understand how well they did for the quizzes on a topical level and hence allows them to keep track of their learning progress across CS2113/T topics. By having a point system for the quiz and recording their stats, users will feel rewarded as they get the right answers and become motivated to attempt more quizzes to improve their current stats. Therefore, the addition of this feature enables E-Duke-8 to better achieve its objectives of gamifying the learning process for CS2113/T students.
  - Highlights: Due to its complete implementation, the calculation of the statistics is not limited to the current number of questions and topics in E-Duke-8, hence allowing the project to grow smoothly in terms of adding more topics and questions. While alternative designs were discussed, the current one was chosen as it provides users with a clear summary page of their statistics. Since the feature is one of the last few features to implement and there had to be a wait for the others to be finalized, the implementation was challenging due to the short timeframe to complete it before v2.0. In-depth planning had to be done in order to get it completed in time. 
- **New Feature:** Implemented the logic to start a quiz with randomly selected questions and facilitate the process of the quiz.
  - What it does: Based on the user's specified number of questions and topic, `QuizQuestionsManager` randomly selects the indicated number of questions from the list of questions of the indicated topic at the start of the quiz. It also ensures that a quiz will not have duplicated questions. Furthermore, It provides the ability to navigate to the next question, and the ability to determine the end of the quiz. 
  - Justification: The random selection of questions will ensure that users get a variety of questions from the topic. 


Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=zhi-you&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Enhancements to existing features:**
  - Personalized the project by changing packages' names and settings. (Pull request [#33](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/33))
  - Improved display messages to enhance user experience. (Pull request [#90](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/90))    

- **Documentation:**
  - User Guide:
    - Added documentation for `quiz` system and `stats` feature. [#130](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/130), [#146](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/146)
    - Did cosmetic tweaks and maintained existing overall documentation. [#103](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/103), [#140](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/140) 
  - Developer Guide:
    - Added design and implementation details of the `quiz` feature. [#124](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/124), [#167](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/167)
    - Added design and implementation details of the `stats` feature. [#143](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/143), [#145](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/145), [#209](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/209)
    - Did cosmetic tweaks to existing overall documentation. [#129](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/129) 

- **Community:**
  - PRs reviewed (with non-trivial review comments): [#39](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/39), [#46](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/46), [#51](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/51), [#56](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/56), [#79](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/79), [#125](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/125)
  - Contributed to forum discussions (example: [1](https://github.com/nus-cs2113-AY2021S1/forum/issues/77))
  - Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Zhi-You/ped/issues/3), [2](https://github.com/Zhi-You/ped/issues/4), [3](https://github.com/Zhi-You/ped/issues/7), [4](https://github.com/Zhi-You/ped/issues/8), [5](https://github.com/Zhi-You/ped/issues/9))
  
