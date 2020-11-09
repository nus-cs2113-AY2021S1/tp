# Kingston Kuan Jun Xiang - Project Portfolio Page

## Overview

E-Duke-8 (pronounced "Educate") helps CS2113/T students learn and understand software engineering and Object-Oriented Programming (OOP) principles through a gamified platform and enhances their learning experience. 

## Summary of Contributions

- **New Feature:** Implemented and maintained the Storage component.
  - What it does: `LogStorage` saves logging data, `TopicsStorage` loads topic data and `UserStorage` saves and loads user data locally.
  - Justification: This feature improves the product significantly because a user does not need to add questions manually for the quiz through the CLI, the user's statistics will be stored which enhances gamification, and user logs are available to identfy causes of bugs in the application.
  - Highlights: The saving of user data does not actually require the implementation of a `User` class as the user attributes for each question are extracted directly from the question.
  This design was chosen so as to not increase the overall complexity of the system.
  The error handling was also challenging as the files save locally are easily editable by the user.
  For `TopicsStorage`, if the format of the JSON file is not correct then the program will not startup and users will be directed to instructions to fix this instead.
  For `UserStorage`, if the user chooses to tamper with the user data, badly formatted data will result in a safe loss of data and the program will continue to run.
  Furthermore, as it is not guaranteed that users will download the default data file, this was included as a resource in the jar file to allow normal operation while still allowing edits though the external path.
  - Credits: This feature relies heavily on the use of the `JSON.simple` library as data is stored in JSON format.
- **New Feature:** Implemented the initial quiz logic to be a separate interface from the main menu.

- **Code contributed:** [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kstonekuan&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project management:**
  - Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

<div style="page-break-after: always;"></div>

- **Enhancements to existing features:**
  - Implemented a fix for problems with the timer feature on Windows for command prompt/powershell. The user input was no showing up due to the use of the `BufferedReader` instead of the usual `Scanner`. The workaround used `Future` and `ExecutorService` to get the user input asynchronously in a separate thread while allowing for a `TimeoutException` to be used for the timer. However, this required the use of a `Robot` to complete the `Future` which did not work in headless environments like WSL. I recommened for this to be fixed in a separate issue by doing an OS-dependent timer. (Pull requests [#158](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/158))
  
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