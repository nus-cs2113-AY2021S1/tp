# tienkhoa16

## Nguyen Tien Khoa's Project Portfolio Page

## Project: The Schwarzenegger

### Overview

The Schwarzenegger is a desktop command line interface-based app for managing all your needs regarding fitness. With the built-in personal assistant, you are able to track your daily workout and diet sessions based on your profile. If you can type fast, The Schwarzenegger can help you maximise your efficiency for maintaining fitness.

### Summary of Contributions

* **Major Enhancement**:
  * **Implemented the CommonUi class to support the interactions with users**
    * Functionality: This enhancement supports the reading of user's commands and presenting the output messages.
    * Justification: Creating a class to handle the output formatting helps to alleviate the concerns of output displaying from the logic classes. In addition, the output formatting can be changed easily and consistently throughout the software development life cycles.
    * Highlights: Although the implementation of this class is not technically challenging, it is used frequently by other classes and plays an essential part to maintain the good coding quality. 
  * **Implemented the enhancement to allow users to view fitness data**
    * Functionality: This enhancement enables the users to view their fitness data, including: physique measures, fitness classification \(e.g. underweight, overweight, obesity, etc.\), and the health advice based on the calorie intake of the day. In addition, the users also receive the advice on the weight target they set in the app. For example, if they are currently overweight, they will be advised to set the expected weight within the range of the normal weight.  
    * Justification: This enhancement is important as it informs the user on their fitness and how far they are from the diet goal. Based on the information and advice given in the app, the users can act on their daily diet to achieve the goal.
    * Highlights: The development of this feature requires careful thoughts of the user story to provide the comprehensive information that meets the users' needs.
  * **Implemented the enhancement to parse the users' commands**
    * Functionality: This enhancement supports the parsing of users' commands for the program to execute. The arguments in the command can be specified in any order.
    * Justification: This enhancement is essential as it identifies the operations to perform and extracts the relevant data from the users' commands. The parser can inteprete the arguments in any order, which gives the users more flexibility and thus enhances the user experience.
    * Highlights: To parse the arguments arranged in an unknown order requires more efforts than to parse the arguments arranged in a fixed, pre-defined order. Efforts were spent on the string processing to identify the start and end indices and the type of the arguments. In addition, the implementation of this parser allows future extensions to support more types of arguments with minimum changes in the code.
* **Minor Enhancement**:
  * Implemented `CommandResult` class to handle user's command execution result.
  * Implemented `SchwarzeneggerLogger` with singleton patter to log program execution into file. [\#60](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/60)
  * Implemented `ExceptionHandler` class to create consistent format to handle exception. [\#119](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/119)
  * Added `logic/commands/main` to execute command under Main Menu.
  * Added `formatList` method in `logic/commands/workout/workoutsession/WorkoutSessionList` to create dynamic column width while listing.
* **Code contribution**: [Functional and Test code](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=tienkhoa16&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship)
* **Contributions to the User Guide:**
  * Add instructions for Main Menu and Profile Menu commands. \(This section was in several pull requests\).
  * Update command formats in the User Guide. [\#126](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/126)
  * Update screenshots for commands under Main Menu. [\#200](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/200)
  * Add tips and notes for understanding our command format. [\#198](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/198)
* **Contributions to the Developer Guide:**
  * Add Section 4.2. Profile-related Features. [\#99](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/99)
  * Add Section 4.5.1. Storage for Profile. [\#156](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/156)
  * Add Section 4.6. Logging. [\#135](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/135)
  * Add Section 5. Testing and Section 6. Dev Ops. [\#199](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/199)
  * Fix broken links in the Developer Guide.
* **Community**:
  * Review Developer Guide from other teams in the class \(with non-trivial review comments\). [\#3](https://github.com/nus-cs2113-AY2021S1/tp/pull/3)
  * Reported bugs for other teams in the class [ped](https://github.com/tienkhoa16/ped/issues).
  * Evaluated code, tested and provided feedback to teammates during many online meetups. [\#29](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/29), [\#90](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/90), [\#94](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/90), [\#96](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/96), [\#120](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/120), [\#127](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/127), [\#131](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/131), [\#134](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/134), [\#139](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/139), [\#140](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/140), [\#144](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/144), [\#160](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/160), [\#202](https://github.com/AY2021S1-CS2113T-F11-1/tp/pull/202)

