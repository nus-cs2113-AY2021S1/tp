# Herman Thong - Project Portfolio Page

## Overview

**revisED** is a CLI application to help students revise by allowing them to create flashcards, take quizzes, 
and keep track of their deadlines, tasks and any other important dates. It is written in Java.

## Summary of Contributions

* **Code contributed:** [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=rashien3&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=rashien3&tabRepo=AY2021S1-CS2113T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code) 
* **Features implemented:** 
    * All subject level features and commands
        * Credit: The repeated and similar commands was modified from the main level commands of the code by [JiaLerk](https://github.com/jialerk).
        * Total of 18 different commands in the taskcommand package and the topiccommand package.
    * Integrated task level features into project
        * Credit: task level features were modified from the ip of [JiaLerk](https://github.com/jialerk).
        * I had to change the syntax for subject-level commands in order to integrate tasks under the subject level, and ensure that features
        shared across Topics and Tasks (eg. add, delete, find) did not conflict.
    * AccessSubjectCommand class
        * This was harder than expected because the 18 different subject-level commands required different objects to be passed to them to initialise them,
        and they threw different exceptions that had to be caught, so a lot of debugging had to be done to ensure that the correct error messages were printed.
    * List all function - printing the tree of the overall structure
        * There are many corner cases in printing out the tree (eg. no tasks, last subject), so I had to do a lot of trial-and-error to control
        the flow of logic of the code. Later, I had to SLAP it to make the method less nested and confusing.
* **Enhancements implemented:** 
    * Enhanced the code to reduce redundancy and improve design.
    * Changed the cascading if-else statements into switch-case statements for parse classes. 
    (Pull requests: [#124](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/124))
    * Improve handling of command in the case of mistyped inputs (extra spaces, capital letters, etc.)
    (Pull requests: [#124](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/124))
    * Improve format of printing flashcards under list command
* **Contributions to the UG:**  TODO:
    * All [Subject level features](https://ay2021s1-cs2113t-w13-1.github.io/tp/UserGuide.html#subject-level) features.
    * Restructuring of explanation of all the commands to be consistent.
    * Update examples related to list all
* **Contributions to the DG:** 
    * Editted the write-up under [Design](https://ay2021s1-cs2113t-w13-1.github.io/tp/DeveloperGuide.html#design) section under design (along with its class diagram).
    * [Architecture](https://ay2021s1-cs2113t-w13-1.github.io/tp/DeveloperGuide.html#architecture) section under design and architecture diagram.
    * [Card package](https://ay2021s1-cs2113t-w13-1.github.io/tp/DeveloperGuide.html#card) section and its class diagrams.
    * UML sequence diagram for [accessing a topic](https://ay2021s1-cs2113t-w13-1.github.io/tp/DeveloperGuide.html#accessing-topic).
    * [list all](https://ay2021s1-cs2113t-w13-1.github.io/tp/DeveloperGuide.html#list-all-imp) under Implementation and its sequence diagram.
* **Contributions to team-based tasks:**
    * Fixed general coding standard issues (eg. variable names)
    * Restructured and reordered UG and DG for more cohesive documentation. 
* **Review/mentoring contributions:** 
  
  PRs reviewed: 
    * [#54](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/54)
    * [#60](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/60)
    * [#80](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/80)
    * [#124](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/124)
    * [#152](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/152)
    * [#163](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/163)

* **Contributions beyond the project team:**
  * Posted an [issue](https://github.com/AY2021S1-CS2113-T16-4/tp/issues/63) for T16-4 when their release jar file could not run.
