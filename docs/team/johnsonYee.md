#Johnson Yee's Project Portfolio Page


## Project: Nav@NUS

Nav@NUS is a useful command line interface (CLI) application to guide you in navigating around the NUS campus
via the school's shuttle services. It is written in Java, and has about 4 kLoC.

## Summary of Contributions
Given below are my contributions to the project.

* **RouteMapCommand**: Added the ability to check for full bus routes of a user-selected bus.
  * What it does: Allows users to check for full bus routes of selected buses.
  * Justification: This feature improves the product significantly because the user could use this information to plan
  for indirect bus routes.
  * Highlights: This enhancement affects commands to be added in later stages of this application e.g. indirect bus routes.

* **Search Frequency**: Implemented search frequencies of bus stops to inform users about their most search bus stop.
  * What it does: Allows users to check for most-searched bus route.
  * Justification: This feature personalises the application for users and give the memory jolt needed to key in their
  commands.
  * Highlights: This enhancement affects commands to be added in later stages of this application e.g. indirect bus routes.
* **Delete Fav**: Added the ability to delete favourite commands to customise list of favourite commands.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=johnson-yee)

* **Enhancements to existing features**:
  * Updated error handling on freqStorage to account for corrupted data

* **Documentation**:
  * User Guide:
    * Added documentation for the features `/routemap`, `/reset`, `/deletefav` 
    * Designed Nav@NUS logo 
    * Added purpose of UG, introduction and quick start
  
  * Developer Guide:
    * Added implementation details of the `/routemap`, `/deletefav` and `/reset` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments):
   [\#23](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/23),
   [\#29](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/29),
   [\#78](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/78), 
   [\#195](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/195),
   [\#203](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/203)
  * Reported bugs and suggestions for other teams (examples: [1](https://github.com/AY2021S1-CS2113T-W13-2/tp/issues/172)
  ,[2](https://github.com/AY2021S1-CS2113T-W13-2/tp/issues/170),
   [3](https://github.com/AY2021S1-CS2113T-W13-2/tp/issues/169))
   * Reviewed developer's guide for one other team ([click here](https://github.com/nus-cs2113-AY2021S1/tp/pull/8/files/65a23531bf8d85984e3d339cfc455cedb7e0cccc)

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_