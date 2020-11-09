# Project: NUSchedule

NuSchedule is a desktop task management application used for keeping track of user's events. The user interacts with it using a CLI. It is written in Java and has around 5 kLoC.

Given below are my contributions to the project.  

* **Main Frame**
  * Events and its subclasses: the basic elements in this product. All commands depend on them.
  * EventList class: provides the functions to operate on the list of events. 
  * Storage: the way to store the data as well as load them from the hard disk.
  * UI,Parser, Command, and the main running class: makes the product object oriented. 
  
* **New Feature**: Added the ability to add events, and manage different cases accordingly.
  
* **New Feature**: Added the ability to record amount of time spent on study on certain date.
  * What it does: allows the user to check what is the amount of time spent on study on the day.
  * Justification: this feature provides the way to know the amount of effort the user spent on study, which is useful for NUS students.
  * Highlights: the implementation uses several filters of the stream of the list of events, and only the appropriate ones are 
  recorded. It also considers the case when certain event is across more than one day, the study time will be calculated correctly
  for different dates.
 
* **New Feature**: Added the ability to mark an event as done, as well as reject it if the current time is before the ending time.

* **New Feature**: Added the ability to delete an event.

* **New Feature**: Added the ability to set the name of the user, as well as indicate the occupation. (i.e. student or professor)

* **New Feature**: Added the ability to find the events with given keyword in the description.

* **New Feature**: Added the ability to find the events happening on the given date.

* **New Feature**: Added the ability to Repeat of required events for several weeks.
  * Highlights: Added copy constructor for Event and its subclasses, because Java does not pass reference as a copy, so 
  we need to create a copy to avoid the edit being effective to the original object.
  * Credits: The code to compare whether 2 localDateTimes are within the same week. 
  Provided by Sunil Katti [here](https://stackoverflow.com/a/56246095)  
  
* **New Feature**: Added the ability to exit the application. 

* **New Feature**: Added the ability to print the list of locations at the selected area in NUS. 

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=Lee-Juntong&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Lee-Juntong&tabRepo=AY2021S1-CS2113T-F14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Fix bugs happening to the features. (Pull requests [\#37](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/37), [\#145](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/145))
  * Make Auto Clear as an option that user can turn on or off. (Pull request [\#182](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/182))
  
* **Documentation**:
  * User Guide:
    * Add documentation for the features delete, find, studyTime, add events, exit, repeat, find base on date, make the app know you, and find locations exist in a certain group  [\#199](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/199)
    * Did cosmetic tweaks to existing documentation of features `done`, `autoClear` : [\#199](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/199)
  * Developer Guide:
    * Make the hyperlinks work properly [\#92](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/92)

* **Community**:
  * PRs reviewed (with non-trivial review comments):[\#162](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/162), [\#172](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/172), [\#147](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/147), [\#144](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/144)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2113-AY2021S1/forum/issues/48#issuecomment-709282848))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Lee-Juntong/ped/issues/9), [2](https://github.com/Lee-Juntong/ped/issues/13), [3](https://github.com/Lee-Juntong/ped/issues/14))
