#Johnson Yee's Project Portfolio Page


## Project: Nav@NUS

Nav@NUS is a useful command line interface (CLI) application to guide you in navigating around the NUS campus
via the school's shuttle services. It is written in Java, and has about 4 kLoC.

## Summary of Contributions
Given below are my contributions to the project.

* **RouteMapCommand**: Displays the full bus route of a selected bus 
  * What it does: Allows users to check for full bus routes of selected buses.
  * Justification: This feature improves the product significantly because the user could use this information to plan
  for indirect bus routes.
  * Highlights: This features form the foundation for future commands to be added in later stages of this application
   e.g. finding indirect bus routes.

* **Search Frequency**: Implemented search frequencies of bus stops to inform users about their most search bus stop.
  * What it does: Allows users to check for most-searched bus route.
  * Justification: This feature personalises the application for users and gives the memory jolt needed to key in their
  commands.
  
* **Delete Fav**: Added the ability to delete favourite commands.
  * What it does: Allows users to delete favourite commands to customise favourite commands list.
  * Justification: This feature allows users to remove commands in the favourite list that are no longer useful to them.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=johnson-yee)

* **Enhancements to existing features**:
  * Removal of nested user inputs to facilitate fast typing (Pull request [#72](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/72))
  * Updated error handling on search frequencies' storage to account for corrupted data 
  (Pull request [#191](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/191))
  * Updated error handling for delete favourite 
  (Pull request [#103](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/103))
  * Wrote tests for route map command and search frequency storage.(Pull requests 
  [#31](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/31),
   [#200](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/200))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `/routemap`, `/reset`, `/deletefav` (Pull requests
    [#81](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/81),
    [#87](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/87),
    [#147](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/147))
    * Designed Nav@NUS logo used in user guide and developer guide.
    ([#142](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/142))
    * Added sections on introduction and quick start. (Pull request[#142](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/142))
  
  * Developer Guide:
    * Added implementation details, class diagrams and sequence diagrams for the `/routemap`, `/deletefav` and `/reset`
     features. (Pull request [#116](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/116)) 
     !!!!!Remember to update after you finish!!!!
     * Updated target user profile and value proposition. (Pull request[#91](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/91))
     

* **Community**:
  * PRs reviewed (with non-trivial review comments):
   Pull requests[\#23](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/23),
   [\#29](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/29),
   [\#78](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/78), 
   [\#195](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/195),
   [\#205](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/205)
  * Reported bugs and suggestions for other teams ([Click here](https://github.com/Johnson-Yee/ped/issues)
   * Reviewed developer guide for team DomNUS ([Click here](https://github.com/nus-cs2113-AY2021S1/tp/pull/8/files/65a23531bf8d85984e3d339cfc455cedb7e0cccc)
