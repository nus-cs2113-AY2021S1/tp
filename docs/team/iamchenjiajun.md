# Chen Jia Jun's Project Portfolio Page

## Overview

termiNus is an interactive task manager for students in NUS. The program uses a Command Line Interface (CLI) and is written in Java.

## Summary of Contributions

Here are my contributions to the project.

- Features added to Parser
  - Regular expression parsing of commands and module code.
  - Parse arguments with regular expressions into a `HashMap` so that arguments can be optional and non-positional.
    - Allows arguments to be checked using a method and a `HashSet` of allowed arguments in the `Command` class which follows polymorphism and reusable code principles.

- Features added to Task List
  - Using the optional argument feature of the Parser, allow users to optionally label tasks with dates, priorities and categories.
    - Added setting of task priorities and dates after a task has been added.
  - Print a calendar in chronological order for the next X days, with a divider for each day.
  - Add recurring weekly tasks to the task list with a simple command by specifying a start and end date range.

- Added the Module List
  - Add modules to a module list with optional arguments.
  - Allow adding incomplete modules using optional arguments to compute the projected CAP.
  - Print a list of modules with current CAP and projected CAP.
  - Add functionality to automatically create folders for all the user's modules in the module list.

- Code contributed: ~2.9k SLOC, [RepoSense Report](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=iamchenjiajun&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=iamchenjiajun&tabRepo=AY2021S1-CS2113-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other).

- Code enhancements:
  - Apply SLAP and DRY principles to refactor duplicated code in the code base, moving code to new functions where necessary, thus reducing SLOC.
  - Applied software design patterns like facade to refactor and repackage code into new classes and make the code more OOP.
  - Used functional programming and wrote declarative code where applicable (while maintaining OOP).
  - Wrote JUnit tests for bug fixes and new code.

- Project management:
  - Managed issue tracker and releases `v1.0` and `v2.0` on GitHub.
  - Authored PRs with detailed descriptions to ensure that the team is kept updated with the new changes, especially during refactors.
    - Examples: 
    [Refactoring Model class](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/146),
    [AddRecurringCommand](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/90),
    [AddCommand](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/22).
  - Communicated regularly with teammates on issues, bugs and tasks to be done, as well as what I'm currently working on.
  - Advised teammates on how code could be organised better and where potential bugs could arise.
  - Managed and reviewed teammates PRs and wrote comments about code quality.
    - Examples:
    [#89](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/89),
    [#93](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/93),
    [#155](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/155),
    [#168](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/168),
    [#179](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/179).

- Documentation:
  - User Guide:
    - Added user guide sections for tasks and module features.
    - Added quick command reference section as a summary of commands.
  - Developer Guide:
    - Added an overall architecture diagram for the major components.
    - Explained usage for `Duke`, `Parser`, `Ui`, `Command` and `CommandCreator` components.
    - Added sequence diagrams for `Duke` and `Parser`, and class diagrams for `Model` generated with PlantUML.
    - Added diagrams to describe how `Parser` parses user commands into different parts.
    - Described implementation details of regular expression parsing of arguments and the calendar command.
  - [Configured](https://github.com/AY2021S1-CS2113-T14-3/tp/pull/173) project to use Kramdown so that we could have an automatically generated Table of Contents through [suggestions](https://github.com/nus-cs2113-AY2021S1/forum/issues/108#issuecomment-716930261) on module forum.
