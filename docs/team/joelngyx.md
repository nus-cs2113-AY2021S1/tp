# Joel Ng Yi Xian - Project Portfolio Page

## Overview:

E-Duke-8 (pronounced "Educate") helps CS2113/T students learn and understand software engineering and Object-Oriented Programming (OOP) principles through a gamified platform and enhances their learning experience. 


## Summary of Contributions:

- **New Feature:** Topic and TopicList components. 
  - What it does: The TopicList component stores Topic objects, each of which stores the description, note list and question list of the topic.
  - Justification: Necessary for the compartmentalization of data

- **New Feature:** Implemented the NoteCommand component
  - What it does: This command is a type Command object. Entering an input containing the following formats: `note add`, 
  `note delete`, `note list` will throw this command and process the instruction provided.
  - Justification: Necessary to add/delete/list notes.
  - Highlight: This feature required the use of logic, as well as interactions with various classes such as `TopicList`, 
  `Ui`, `NoteList` and `Note`. Methods were added to the `Ui` class to allow for a more user-friendly way of providing 
  inputs when it comes to creating/deleting/listing out `Note` objects in the specified `NoteList` object.

- **New Feature:** Implemented the Note and NoteList components.
  - What it does: The NoteList components store Note objects, and contains methods that can modify the NoteList object.
  - Justification: Necessary to process and carry out the NoteCommand. 
  
  Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=joelngyx&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=false)

- **Enhancements to existing features:**
  - Removed redundant code in the DisplayableList interface which allowed for the better implementation of features.
  Made necessary additions and amendments to various classes and JUnits tests. [#122](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/122)
  - Made necessary additions and amendments to the UserStorage class to accommodate improvements made to the `Note` and `NoteList` classes.
  [#144](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/144/commits)

- **Contributions to documentation**
  - User Guide:
    - Added documentation for the adding, deleting, and the listing of notes: 
    [#207](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/207/files)
  - Developer Guide: 
    - Added design and implementation details of the Topic and TopicList components 
     [Design of TopicList](https://ay2021s1-cs2113t-f12-3.github.io/tp/DeveloperGuide.html#221-design-of-topiclist),
     [Implementation of TopicList](https://ay2021s1-cs2113t-f12-3.github.io/tp/DeveloperGuide.html#222-implementation-of-topiclist)
  - Added design and implementation details of the Note and NoteList components 
     [Design of NoteList](https://ay2021s1-cs2113t-f12-3.github.io/tp/DeveloperGuide.html#223-design-of-notelist),
     [Implementation of Notes](https://ay2021s1-cs2113t-f12-3.github.io/tp/DeveloperGuide.html#224-implementation-of-notes)
  - Added UML and sequence diagrams relevant to the Topic, TopicList, Note and NoteList components, as seen in the links above.

- **Community:**
  - PRs reviewed (with non-trivial review comments): 
    [#216](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/216),
    [#51](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/51),
    [#22](https://github.com/AY2021S1-CS2113T-F12-3/tp/pull/22)
  - Reported bugs for other teams in the class:
    [#1](https://github.com/joelngyx/ped/issues/1),
    [#2](https://github.com/joelngyx/ped/issues/2)



