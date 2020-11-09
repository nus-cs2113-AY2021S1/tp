# Project: NuSchedule

NuSchedule is a task management application written in Java. It is operated on the command line interface.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit previous events.
  * What it does: Allows the user to edit previous commands. User can choose to edit individual fields of an event such as just choosing to edit the location of an event.
  * Justification: This feature improves the product significantly because it provides the user a quick and simple method to correct any mistakes made in the addition of an event. Instead of deleting the event and creating a new one, the user can just edit that particular event with the correct field information.
  * Highlights: This command has to be constantly updated whenever a new event class is added to allow for the editing of an event into that event class. Testing and implementation of this command was difficult as the command has to cover for all of the event classes.

* **New Feature**: Added a history command that allows the user to sort events.
  * What it does: Allows the user to sort previous commands. User can organise their events to by sorting them in a specified order.
  * Justification: This feature improves the product significantly as it allows the user to order the events, allowing the user to prioritise certain events over others. For example, the user can choose to sort by time, which will cause eventing that are due soon to be sorted on top of events that are due later in the future.
  * Highlights: This command was implemented by making use of comparators. Sorting by location was challenging to implement as there were two location types, online and offline locations and the command had to account for situations where either of them where null.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=xuche123&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `edit` and `sort` [\#167](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/167)
    * Added sections on Introduction, quick start and command summary [\#78](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/78)
  * Developer Guide:
    * Added implementation details and sequence diagrams of the `edit` and `sort` feature. [\#171](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/171)
    * Added sections on Introduction, Architecture, Documentation and Appendix [\#74](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/74), [\#171](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/171)

* **Community**:
  * Revied developer guide for team [Study It](https://github.com/nus-cs2113-AY2021S1/tp/pulls?q=is%3Aopen+is%3Apr+CS2113T-T12-1+).
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2113T-W12-3/tp/issues/177), [2](https://github.com/AY2021S1-CS2113T-W12-3/tp/issues/175), [3](https://github.com/AY2021S1-CS2113T-W12-3/tp/issues/174))

