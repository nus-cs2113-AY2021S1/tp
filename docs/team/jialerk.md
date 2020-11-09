# Gan Jia Lerk - Project Portfolio Page

## Overview

**revisED** is a CLI application to help students revise by allowing them to create flashcards, take quizzes, 
and keep track of their deadlines, tasks and any other important dates. It is written in Java.

## Summary of Contributions

* **Code contributed:** [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jialerk&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements implemented:**
    - Did up the base code for the project for easier development
        [#12](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/12/files)
        
    - Implemented most SubjectCommand features which includes:
        - JavaDoc in subjectcommand package excluding QuizSubjectCommand and ResultSubjectCommand which are from [Muthu](https://github.com/syncode98)
        - All classes under the subjectcommand package excluding ExportSubjectCommand
        which is from [Chin Hang](https://github.com/cookiehoodie), and QuizSubjectCommand and ResultSubjectCommand which are from [Muthu](https://github.com/syncode98)
        - Logging in classes in subjectcommand package
        - Assertions in classes in subjectcommand package
        
    - Implemented some methods in the Ui class which includes:
        - All relevant Subject Error messages as well as printing all subject related methods in Ui
    
    - Implemented exceptions which includes:
        - All exceptions in the subjectexception package
        - FailedParserException
        - Reformat all exceptions to output error messages instead of printing the errors straight 
        [#53](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/53)
        - Changed the structure of exceptions and added some exceptions the other exception packages
        [#52](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/52)

    - Implemented almost all the test cases under the command package which includes:
        - FlashcardCommandTest
        - SubjectCommandTest but the storage component of setup was inspired by [Chin Hang](https://github.com/cookiehoodie)
        - TaskCommandTest
        - TopicCommandTest
        [#168](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/168) and [#128](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull128)
    
    - Implemented most assertions in Ui class
    
* **Contributions to documentation:**
    - Did the Main level features
    - Did the restructuring of the UG together with [Chin Hang](https://github.com/cookiehoodie)

* **Contributions to the DG:** 
    - Did all the design for the command package, inlcuding diagrams [#128](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/128)
    - Did implementation for 4.7 Adding objects implementation and 4.8 Accessing subjects/topics implementation, including diagrams
    [#159](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/159)
    - Did the first level of restructuring to the DG [#86](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/86)
    - Did the base part of design in card package which was later done by [Herman](https://github.com/rashien3) 

* **Review/mentoring contributions:** 
    - Approved and commented on the following pull requests:
    [#172](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/172)
    [#158](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/158)
    [#150](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/150)
    [#129](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/129)
    [#127](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/127)
    [#126](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/126)
    [#125](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/125)
    [#88](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/88)
    [#75](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/75)
    [#59](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/59)
    [#38](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/38)
    [#22](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/22)
    [#21](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/21)
    [#11](https://github.com/AY2021S1-CS2113T-W13-1/tp/pull/11)

    - Inspired code for some of the other Classes in the project since the project is based on a level system, and I did the code for the main level.



