# Heng Fu Yuen - Project Portfolio Page

## Overview
DietBook is a Command Line Interface (CLI) desktop application designed mainly _NUS students staying on campus_. It helps users **track their food and nutritional intake** as well as provide them with their **daily calorie recommendation**. It also has a **database prepopulated with food items commonly found around NUS** so that thse food items can be easily added to the list of food items consumed for tracking. DietBook is written mainly in Java.

* Table of Contents
{:toc} 

### Summary of Contributions

#### Code contributed

* Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=hengfuyuen&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* Authored more than 30 [PRs](https://github.com/AY2021S1-CS2113-T14-4/tp/pulls?q=is%3Apr+author%3AHengFuYuen+)

#### Features implemented

* Implemented [`Person.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/person/Person.java), [`FitnessLevel.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/person/FitnessLevel.java) and [`Gender.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/person/Gender.java) to support four commands `name`, `info`, `userinfo` and `editinfo`
    * Implemented relevant assertions, logging and JUnit for the above three classes.

* Implemented [`Ui.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/Ui.java), [`UiHelper.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiHelper.java), [`UiInput.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiInput.java), [`UiOuput.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiOutput.java), [`UiMessage.java`](https://github.com/AY2021S1-CS2113-T14-4/tp/blob/master/src/main/java/seedu/dietbook/ui/UiMessage.java) to support the `Ui Component` which is responsible for taking in user commands, communicating with `Logic` to execute the command and printing out the relevant output or error messages
    * Implemented relevant assertions, exceptions, logging and JUnit tests for the above five classes. 

#### Documentation

* **User Guide**<br/>
    * Added four features related to user information - [Entering username](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#entering-username-name), [Entering user information](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#entering-user-information-info), [Viewing user information](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#viewing-user-information-userinfo) and [Editing user information](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#editing-user-information-editinfo): [#72](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/72/files) [#77](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/77/files) [#94](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/94/files) [#178](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/178/files)
    * Added `name`, `info`, `userinfo` and `editinfo` commands to the [Command Summary](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#command-summary): [#72](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/72/files) [#77](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/77/files) [#94](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/94/files) [#178](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/178/files)
    * Updated the [Introduction](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#introduction), [Quick start](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#quick-start), and [FAQ](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#faq): [#72](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/72/files) [#77](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/77/files) [#94](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/94/files) [#178](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/178/files)
    * Added notes about command format under [Features](https://ay2021s1-cs2113-t14-4.github.io/tp/UserGuide.html#features): [#72](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/72/files) [#77](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/77/files) [#94](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/94/files) [#178](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/178/files) [#188](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/188/files) [#190](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/190/files)

* **Developer Guide**
    * Added the [UI Component](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#ui-component) in the Design section: [#94](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/94/files) [#97](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/97/files) [#194](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/194) [#176](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/176)
    * Added the descriptions and diagrams for three features in the implementation section - [Enter user information feature](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#enter-user-information-feature), [Edit user information feature](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#edit-user-information-feature) and [View user information feature](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#view-user-information-feature): [#94](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/94/files) [#97](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/97/files) [#100](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/100/files) [#194](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/194) [#176](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/176)
    * Added five [User Stories](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#user-stories) related to user information features: [#194](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/194)
    * Updated instructions for manual testing section for three features - [Entering user information](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#entering-user-information), [Editing user information](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#editing-user-information) feature and [Viewing user information](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#viewing-user-information): [#194](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/194)
    * Updated the [Target User Profile](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#target-user-profile), [Value Proposition](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#value-proposition), [Non-Functional Requirement](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#non-functional-requirements) and [Glossary](https://ay2021s1-cs2113-t14-4.github.io/tp/DeveloperGuide.html#glossary): [#194](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/194)

* **Product website**
    * Updated the product [Home page](https://ay2021s1-cs2113-t14-4.github.io/tp/) [#171](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/171)
    * Added a trivial introduction in [AboutUs Page](https://ay2021s1-cs2113-t14-4.github.io/tp/AboutUs.html): [#176](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/176)

#### Contributions to team-based tasks

* Initiate weekly meetings
* Helped in managing `v2.1` submissions
* Helped in managing `v1.0` release
* Managed release [DietBook v2.0](https://github.com/AY2021S1-CS2113-T14-4/tp/releases/tag/v2.0.2) on GitHub
* Authored more than 45 [Issues](https://github.com/AY2021S1-CS2113-T14-4/tp/issues/created_by/HengFuYuen) in the team repo issue tracker
* Helped in bug catching (examples: [#40](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/40) [#104](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/104) [#61](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/61) [#105](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/105))
* Configure the build.gradle file [#46](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/46/files) [#68](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/68)
* Helped in setting up the tP Organisation and Team Repo - Set up a `developers` team in the organisation, enabled the issue tracker, updated the labels in issue tracker and created the milestones used for managing the project
* Refer to [Documentation](#documentation) section for contributions to user guide, developer guide, etc that are not specific to a feature

#### Review/mentoring contributions

* Reviewed more than 30 [PRs](https://github.com/AY2021S1-CS2113-T14-4/tp/pulls?q=is%3Apr+is%3Aopen+reviewed-by%3A%40me+) most of which contains only trivial comments
* Reviewed about 5-10 PRs with non-trivial review comments (examples: [#14](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/14) [#40](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/40) [#71](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/71) [#166](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/166) [#199](https://github.com/AY2021S1-CS2113-T14-4/tp/pull/191))

#### Contributions beyond the project team

* Participated in forum discussions (examples: [#13](https://github.com/nus-cs2113-AY2021S1/forum/issues/13) [#52](https://github.com/nus-cs2113-AY2021S1/forum/issues/52) [#87](https://github.com/nus-cs2113-AY2021S1/forum/issues/87))
* Reported [seven bugs and suggestions](https://github.com/AY2021S1-CS2113T-W11-4/tp/issues?q=is%3Aissue+HengFuYuen) for another team during the PE Dry run
* Reported [Major bug](https://github.com/AY2021S1-CS2113-T16-4/tp/issues/61) for differnt team during the PE Dry run
