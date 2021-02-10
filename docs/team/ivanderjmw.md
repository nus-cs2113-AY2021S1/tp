# Ivander's Project Portfolio Page

## Project: Trippie 

Trippie is a command-line desktop application used for planning trips, tracking expenses and converting exchange rates.
It is specifically catered for the needs of student travellers. It allows multiple trip plans and is designed for quick
and efficient inputs.


### Summary of Contributions
Given below are a list of my contribution to the project.

- **New Feature**: Added ability for users to have multiple trips.
    - What it does: It allows user to manage multiple trips at once.
    - Justification: This feature will allow someone to plan out multiple trips, and keep everything in the same software.
        They can switch between the different trips easily.
    
- **New Feature**: Designed and implemented storage system for multiple trips.
    - What it does: It allows user to store each of their trips in a separate file, all referred from one master file. 
    - Justification: Trippie does not need to load all files at once, therefore saving RAM. 
    - Highlight: Edited trips are saved and organized automatically, user does not need to do worry about file management.

- **New Feature**: Implemented the new trip and edit trip command.
    - What it does: Allows users to create a new trip or edit an existing trip.
    - Justification: These features are essential to ease users in managing their trips.
    - Highlight: These features are conversational and prompts the user interactively.
    
- **New Feature**: Implemented delete trip command.
    - What it does: Deletes a trip permanently from local storage.
    - Justification: An important feature if someone wants to remove a trip that is un used.
    - Highlight: It is a tricky implementation, in a case that the trip file also needs to be deleted along with its reference in the master file.
    
- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=W11&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ivanderjmw&tabRepo=AY2021S1-CS2113T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

- **Project management**: 
    - Managed and organised issues into `Trippie` project on GitHub.
    - Managed and assigned issues for `V2.1` on GitHub.
    - Managed project flow with Github Projects.
    - Making sure all the CI tests are working properly and up-to-date.
    
- **Enhancement Implementation**:
    - Smoothened the user experience when opening Trippie.
    - Enhanced the gradle checks to deal with storage file creations.

- **Contributions to documentation**:
  * User Guide:
    - Added quick start guide [here](https://ay2021s1-cs2113t-w11-2.github.io/tp/UserGuide.html#30-how-to-use-ivander).
    - Added trip commands guide, includes `new trip`, `edit trip`, `delete trip` [here](https://ay2021s1-cs2113t-w11-2.github.io/tp/UserGuide.html#51-creating-a-new-trip-new-trip-ivander).
    - Contributed to warning and information blocks in the user guide.
  * JavaDoc:
    - Added JavaDoc for the following classes and its methods:
        - Commands: `DeleteTripCommand`, `EditTripCommand`, `LoadTripCommand`, `NewTripCommand`.
        - Data: `TrippieData`, `Trip`
    
- **Contributions to the DG**:
    - Added UML sequence diagrams created using *PlantUML*.
    - Wrote detailed description for the implementation of *TrippieData*.
    - Added use cases. [#63](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/63)

- **Review contributions**:
    - PR reviewed (with non-trivial review comments): [#29](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/29), [#70](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/70)