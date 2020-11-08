# Wong Zhen Wei - Project Portfolio Page

## Overview
My team of 4 software engineering students and I have created a command line interface project managing 
software for our CS2113T team project. The app, called SCRUMptious, aims to help project managers handle 
their project teams efficiently with the help of the SCRUM methodology and digital technologies.

### Summary of Contributions
This section summarises my contributions in terms of documentation, coding and other significant matters to the team project.

#### Enhancements Implemented
The main enhancement I have added is storage manager and automated testing.
##### 1. Storage Manager
I have implemented a storage manager with the help of a third-party library [**json-simple 3.1.1**](https://cliftonlabs.github.io/json-simple/) by _Clifton Labs_. The storage manager helps to save data to and load data from a local persistent storage. I have added logic to allow the objects to serialise its data to a string in JSON format, and allow the objects to de-serialise JSON objects to map the values to its own properties. This JSON objects comes from parsing JSON formatted string. This allows the user to retain their data even after closing their program, therefore the user do not need to manually reenter their data. 

**Pull Request:** [#31](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/31) [#67](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/67) [#81](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/81) [#117](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/117) [#118](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/118) [#187](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/187) [#196](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/196) [#209](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/209) [#215](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/215)

##### 2. Automated Testing
SCRUMptious contains components that are dependent on current date, as a result the output of the program may vary. For example, creating the first sprint without specifying the start date will result in the start of the project to be the date of the command being ran. This results in the output of the automated testing to differ when it is run on a different date from the generation of the **expected** outcome. I solve this issue by ensuring that each call for current date will return a fixed date if the automated testing is running. This is achieved by:  
 1. Adding a static variable `clock` to `Scrumptious` class which allows the `clock` to be explicitly set.   
 2. The clock can be retrieved by calling `Scrumptious.getClock()` which will return a `clock` object. 
 3. If `clock` is `null`, the clock returned corresponds to the current datetime. Otherwise, the returned clock will always be fixed to a datetime which is set by `Scrumptious.setClock()`.
 4. Each call to `LocalDate.now()` or `LocalDateTime.now()` must include the `clock` parameter using `Scrumptious.getClock()`.   

The challenge to this solution is it requires the discipline to ensure that each call to get the current date (`LocalDate.now()`) or datetime (`LocalDateTime.now()`) must include a `clock` parameter. Otherwise, the automated testing results are unexpected and will cause the test to fail.

**Pull Request:** [#208](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/208)


#### Contributions to Team-Based Tasks
* Added technique to allow teammates to conduct JUnit testing on stdin and stdout. (Pull Request [#189](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/189))  
* Standardize package naming convention (Pull Request [#190](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/190) Issue [#23](https://github.com/AY2021S1-CS2113T-F11-4/tp/issues/23))

#### Contributions to Documentation
##### User Guide  
* Add clear storage command. (Pull Request [#133](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/133))
* Updated for v2.0. (Pull Request [#138](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/138))
  * Added more information into _**2. Quick Start**_.
  * Updated _**4. Command Summary**_.
  * Fixed _**Table of Contents**_.
  * Fixed some consistency issues.
  * Add proper link to jar file (Pull Request [#139](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/139))
 
  
##### Developer Guide  
* Storage component  
  * Updated architecture's storage component section (Pull Requests [#109](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/109) [#117](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/117))  
  * Fix developer guide's v2.0 bug (Pull Request [#186](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/186))  
* Storage related implementation  
  * Added (Pull Request [#109](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/109))  
  * Updated storage (Pull Request [#138](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/138))  

#### Other Contributions
* Reviewed bugs for other team's developer guide. [[CS2113T-F14-2] Cent Wise Dollar Wise](https://github.com/nus-cs2113-AY2021S1/tp/pull/7)  
* Reported bugs for other team's program during PE dry run. [[CS2113T-W11-3] CheatLogs' bugs](https://github.com/keke101/ped/issues)  
    
**Code contributed**: [Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=keke101&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)  