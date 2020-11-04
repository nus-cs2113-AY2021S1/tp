# Kingston Kuan - Project Portfolio Page

*Table of Contents
{:toc}

## Overview

E-Duke-8 (pronounced "Educate") helps CS2113/T students **learn and understand software engineering and Object-Oriented Programming (OOP) principles through a gamified platform and enhances their learning experience. 

## Summary of Contributions

- **New Feature:** I implemented and maintained the Storage component which included logging, topics and user data.
  - What it does: allows the data to be saved locally and loaded upon startup.
  - Justification: This feature improves the product significantly because a user does not need to add questions manually for the quiz through the CLI, the user's statistics will be stored which unhances gamification, and we can see user logs to identfy causes of bugs in the application.
  - Highlights: The saving of user data does not actually require the implementation of a `User` class as the user attributes for each question are extracted directly from the question.
  - Credits: This feature relies heavily on the use of the `JSON.simple` library as data is stored in JSON format.
- **New Feature:** Implemented the intial quiz logic to be a seperate interface from the main menu.

Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kstonekuan&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project management:**
  - Managed releases `v1.0` - `v2.0` (2 releases) on GitHub

- **Enhancements to existing features:**
  - Implemented a fix for problems with the timer feature on Windows for command prompt/powershell (Pull requests [#158](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/158))
  
- **Documentation:**
  - User Guide:
    - Added documentation for loading and saving data [#100](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/100), [#211](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/211)
  - Developer Guide:
    - Added implementation details of the Storage component [#116](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/116), [#142](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/142), [#152](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/152)
  
- **Community:**
  - PRs reviewed (with non-trivial review comments): [#23](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/23), [#46](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/46), [#59](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/59), [#91](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/91), [#125](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/125), [#132](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/132), [#145](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/145), [#150](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/150)
  - Contributed to forum discussions (examples: [1](https://github.com/nus-cs2113-AY2021S1/forum/issues/64), [2](https://github.com/nus-cs2113-AY2021S1/forum/issues/79), [3](https://github.com/nus-cs2113-AY2021S1/forum/issues/47), [4](https://github.com/nus-cs2113-AY2021S1/forum/issues/56), [5](https://github.com/nus-cs2113-AY2021S1/forum/issues/91), [6](https://github.com/nus-cs2113-AY2021S1/forum/issues/117))
  - Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/kstonekuan/ped/issues/4), [2](https://github.com/kstonekuan/ped/issues/2), [3](https://github.com/kstonekuan/ped/issues/6), [4](https://github.com/kstonekuan/ped/issues/5))
  
- **Tools:**
  - Integrated a third party library (JSON.simple) to the project ([#13](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/13))