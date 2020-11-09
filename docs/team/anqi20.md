# Lim An Qi - Project Portfolio Page

## Overview

E-Duke-8 (pronounced "Educate") helps CS2113/T students learn and understand software engineering and Object-Oriented Programming (OOP) principles through a gamified platform and enhances their learning experience. 

## Summary of Contributions

- **New Feature:** Implemented the timer function for the quiz
  - What it does: The user would be able to choose the timer set for each question. If the time is up, and the user have yet to complete the question, then it will be marked as an incorrect answer. 
  - Justification: E-Duke-8 is able to simulate a timed examination environment for the students. Moreover, since they get to choose the timing , E-Duke-8 is able to cater to all students at different learning stages. 
  - Highlights: This timer feature is challenging as we had to cater to users using different operating systems. In the User Interface, there is two ways to detect the time passed when the user has not inputted anything. 
    Kingston has helped in implementing this feature for the Windows Operating System (OS) while I worked on implementing this feature for the MacOS and Linux Operating System. 
    The implementation in Windows OS uses the Robot object which cannot be used in the implementation in the MacOS and Linux as they are a headless environment. 
- **New Feature:** Added delay between each question during the quiz
  - What it does: The users need to press the "Enter" button on the computer before they can proceed to the next question.
  - Justification: This allows the users to read and understand the explanation of the question first, before moving on to the next question. This makes the quiz function more effective in helping the students to revise. 
- **New Feature:** Implemented the User Interface (UI) class
  - What it does: It would be the point of contact between E-Duke-8 and the users. It would take in input from the user while printing out prompts for the users to read. 
  - Justification: This also allows the users to know if the command they give is successfully handled by E-Duke-8. This is because, E-Duke-8 will echo back if it is a successful command, and tells the user the error if it is an unsuccessful command. 
- **New Feature:** Implemented the `Option` and `OptionList` classes
  - What it does: The `Option` class holds the one of the option of a question while the `OptionList` class holds all 4 options of the same question. 
  - Justification: When we have classes for the `Option` and `OptionList`, our program will be more Object-oriented, which brings about many benefits. 

- **Code contributed:** [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=anqi20&tabRepo=AY2021S1-CS2113T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

- **Enhancements to existing features:**
  - Implemented testing for IncompleteCommand. [#256](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/256)
  - Added explanations class. [#118](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/118)
  - Implemented the UI class to do error handling. [#79](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/79)
  - Implemented some testing for Option and OptionList classes. [#65](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/65)
 
- **Documentation:** 
  - User Guide: 
    - Did cosmetic tweaks to existing documentation of features: [#165](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/165), [#222](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/222)
    - Added documentation for the timer feature in `quiz` system. [#165](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/165)
    - Added documentation for the Command Summary. [#165](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/165)
  
  - Developer Guide: 
    - Added the implementation of timer feature [#222](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/222)
    - Added the design and implementation of Option and OptionList classes [#125](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/125), [#165](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/165)
    - Added the user stories of V1.0 and V2.0 [#125](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/125)
    - Added the implementation of User Interface [#125](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/125)

- **Community:**
  - PRs reviewed (with non-trivial review comments): [#34](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/34), [#46](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/46), [#51](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/51), [#67](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/67), [#124](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/124)
  - Reported bugs and suggestions for other teams in the class. (examples: [1](https://github.com/anqi20/ped/issues/1), [2](https://github.com/anqi20/ped/issues/2), [3](https://github.com/anqi20/ped/issues/5), [4](https://github.com/anqi20/ped/issues/7), [5](https://github.com/anqi20/ped/issues/8), [6](https://github.com/anqi20/ped/issues/11), [7](https://github.com/anqi20/ped/issues/12))
