# Wu Yuxin - Project Portfolio Page

## Overview
#### Project: Nav@NUS
Nav@NUS is a desktop application that aids users in getting around NUS. The user interacts with it using a CLI and thus,
this application is appropriate for users who can type fast.

Given below are my contributions to the project.

* **New Feature**: Added an /addfav command
  * What it does: allows the user to add a previous command that is valid to their list of favourites.
  * Justification: This feature improves the product significantly because some users might not be familiar enough with
   the commands and have to constantly refer to the user guide.
   Through this command it allows them to save previous commands will greatly improve convenience.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. 
  The implementation too was challenging as it required changes to existing commands to determine if command about to be added is valid.
  
* **New Feature**: Added a /liststops command.
 * What it does: allows the user to view all bus stops and their description.
 * Justification: This feature enhances the usability of this application as users of this application is most likely unfamiliar with all the bus stops in NUS.
 By listing all bus stops and their description, it allows the user to know more information about the location of the bus stop, and the surrounding infrastructures.
 
* **Code contributed**: [my RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=Lezn0&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Created the Nav@NUS logo to be displayed during the welcome message.
  * Added bus stops enum class.
  * Wrote additional tests for /addfav feature.

* **Documentation**:
  * User Guide:
    * Add documentation for the features `/addfav`, `/liststops`, `/help` and `/exit` [\#72]()
  * Developer Guide:
    * Added implementation details of the `/addfav` and `/liststops` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#26](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/26), [\#74](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/74)
  * Reported bugs and suggestions for other teams in the class (example: [click here](https://github.com/Lezn0/ped/issues))
  * Provided suggestions for the developer guide of another team (example: [click here](https://github.com/nus-cs2113-AY2021S1/tp/pull/36#discussion_r514890722))