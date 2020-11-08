# Ong Zong Xian - Project Portfolio Page

## Overview
SmartHomeBot is a **desktop application that consolidates all home appliance’s control into a 
centralized system via a Command Line Interface (CLI)**. SmartHomeBot is our team project submission for 
**CS2113: Software Engineering & Object Oriented Programming** Module. 

### Summary of Contributions

#### Code contributed:
Below is the link to my code contribution for this project: 
* Code Contribution: [RepoSense](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=zongxian-ctrl&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Main Function implemented:
I am mainly responsible for the Parser class and some Commands the following commands for SmartHomeBot. 

**Logic**
* `Parser`: Responsible for ensuring that all User input are parser correctly as well as acting 
as the first filter to detect any error in the user input to prevent further error in the program. 
* `CreateCommand`: Command to add new Location to SmartHomeBot.
* `RemoveCommand`: Command to remove specified Location from SmartHomeBot.
* `AddCommand`: Command to add a new Appliance to a certain location in SmartHomeBot.
* `DeleteCommand`: Command to delete specified Appliance from SmartHomeBot.

#### Enhancements implemented:

Implemented the `Parser` to ensure that User entered the correct format for Commands into SmartHomeBot.
Functions include:  
1. Ensuring that User enter the correct format as stated in User Guide.
2. Ensuring that User does not give empty parameter or illegal parameter if the command requires it. 
3. Detecting any Illegal character such as `space` or `\|` or `/` which might ensure in error of the CLI program. 
4. Implemented `StringUtils.replaceOnce` to prevent special character from ending the program, as Java String Library .replaceFirst 
only supports regex.
* Implementation of Parser involved frequent updates from v1.0 to v2.1 as any changes to the implementation and functions
of SmartHomeBot normally involve needing to parse the command differently to prevent bugs and error.

Implemented the `CreateCommand` to make sure User does not create duplicate Location of the same name with existing Locations
as well as Appliance name. 

Implemented the `RemoveCommand` to make sure User does not remove a Location that does not exist. 
As well as deleting appliances in the Location that is being removed as well. 

Implemented the `AddCommand` to make sure User add an Appliance with the type available specified in the UserGuide. 
Furthermore, making sure it is added to a Location that is available in SmartHomeBot with the name specified not being a duplicate.

Implemented the `DeleteCommand` to make sure User does not delete an Appliance that does not exist.

Minor enhancement: Wrote the logger setup to allow teammates to perform logging in their respective sections. 

#### Contribution to User Guide:

1. I have written the documentation explaining on how to use the Commands available as well as the Format to ensure standardisation.
2. I am also responsible for updating table for Command Summary in UserGuide and making sure the content in UserGuide are in order.

#### Contribution to Developer Guide:
1. Added UML Sequence Diagrams for Parser, CreateCommand and RemoveCommand.
2. Wrote documentation for Parser, CreateCommand and RemoveCommand. 

#### Contributions to team-based tasks: 
* Set up Team repo's issue tracker and milestones for `v1.0`, `v2.0` and `v2.1`. 
* Some examples of providing feedback to team member's pull requests: e.g [#208](https://github.com/AY2021S1-CS2113-T14-1/tp/pull/208), [#210](https://github.com/AY2021S1-CS2113-T14-1/tp/pull/210) 
* In addition, meets up regularly with my team to update and discuss the developments of SmartHomeBot
through Zoom, and our group personal Telegram group. 

#### Contributions beyond the project team: 
Provided feedback to other CS2113 project teams on the bug encounterd during the semester. 
Links below are some feedback to other project teams.

1. [PE Dry Run](https://github.com/zongxian-ctrl/ped/issues)
2. [Review DG of Peer Team #1](https://github.com/nus-cs2113-AY2021S1/tp/pull/131)
3. [Review DG of Peer Team #1](https://github.com/nus-cs2113-AY2021S1/tp/pull/4)
4. [Review Peer IP PRs #1](https://github.com/nus-cs2113-AY2021S1/ip/pull/28)
5. [Review Peer IP PRs #2](https://github.com/nus-cs2113-AY2021S1/ip/pull/151)
