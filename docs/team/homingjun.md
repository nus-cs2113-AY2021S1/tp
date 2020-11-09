# Ho Ming Jun - Project Portfolio Page

## Overview
My team of 4 software engineering students and I created a command line interface project managing 
software for our CS2113T team project. The app, called SCRUMptious, aims to help project managers handle 
their project teams efficiently with the help of the SCRUM methodology and digital technologies.

### Summary of Contributions
This section shows a summary of my coding, documentation, and other helpful contributions to the team project.

**Functionality added**

**1. `ParserManager` and `Command`**
 I created a `ParserManager` and `Command` class with several subclasses for SCRUMptious to handle the user input.
* What it does: The `ParserManager` mainly parses the user input and checks to see what kind of `Command` has been 
given by the user. Subsequently, once the `Command` has been identified, the `ParserManager` then passes on the parameters to
the respective subparsers to validate the parameters. If the validation is unsuccessful, an error message with the corresponding details 
will be printed out for the user to see. If validation is successful, the subparser will then call the respective `Command` classes 
before returning the repsective `Command` to the `ParserManager`. After which, the `ParserManager` will return the command to the 
main SCRUMptious for execution of the command. 
(Pull requests [#19](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/19), [#90](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/90), [#94](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/94), [#97](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/97), [#114](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/114))

**Code contributed**: [Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=mingjun&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=homingjun&tabRepo=AY2021S1-CS2113T-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other) to my codes.

**Other contributions**:
* Quality of life features:
    * Added a help menu to SCRUMptious so that users can learn how to use the commands at a glance. (Pull requests [#116](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/116), [#182](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/182))
* Enhancements to existing features:
    * Apply SLAP to the `ParserManager` for more use of OOP and increase overall cohesion and decrease coupling of classes. (Pull requests [#97](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/97), [#114](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/114))
    * Added JUnit tests for `ParserManager` and the subparsers. (Pull requests [#65](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/65), [#197](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/197))
    * Added logging for `ParserManager` and `HelpCommand` and it's subclasses. (Pull request [#200](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/200))
* Documentation: 
    * Set up the initial skeleton and format for the user guide markdown. (Pull requests [#74](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/74), [#99](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/99))
    * Added help menu information to the user guide. (Pull requests [#136](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/136), [#246](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/246))
    * Added logic component explanations and class diagrams to the developer guide. (Pull requests [#125](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/125), [#182](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/182), [#211](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/211), [#220](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/220), [#245](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/245))
    * Added JavaDoc comments to `ParserManager`, `HelpCommand` and `ScrumptiousException` classes and subclasses. (Pull request [#213](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/213))
    * Update user guide table of content anchors to ensure that the links to the respective headers work. (Pull request [#214](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/214))
    * Update developer guide table of content anchors to ensure that the links to the respective headers work. (Pull requests [#227](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/227), [#235](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/235), [#239](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/239), [#242](https://github.com/AY2021S1-CS2113T-F11-4/tp/pull/242))
* Community:
    * Reviewed Developer Guide (with non-trivial comments): [[CS2113T-F12-2] AniChan #54](https://github.com/nus-cs2113-AY2021S1/tp/pull/54)
    * Reported bugs for other team during PE dry run: [[CS2113-T16-3] bugs](https://github.com/homingjun/ped/issues)