---
layout: page
title: John Doe's Project Portfolio Page
---

## Project: Nav@NUS

Nav@NUS is a useful command line interface (CLI) application to guide users in navigating around the NUS campus
via the school's shuttle services.

Given below are my contributions to the project.

* **New Feature**: Added an /addfav command
  * What it does: allows the user to add a previous command that is valid to their list of favourites.
  * Justification: This feature improves the product significantly because some users might not be familiar enough with
   the commands and have to constantly refer to the user guide.
   Through this command it allows them to save previous commands will greatly improve convenience.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. 
  The implementation too was challenging as it required changes to existing commands to determine if command about to be added is valid.
  
* **New Feature**: Added a /liststops command that allows the user to view all bus stops and their description.
* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `/addfav`, `/liststops`, `/help` and `/exit` [\#72]()
  * Developer Guide:
    * Added implementation details of the `/addfav` and `/liststops` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())