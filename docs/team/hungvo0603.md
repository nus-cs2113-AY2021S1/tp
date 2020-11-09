# Vo Quang Hung - Project Portfolio Page

## 1. Overview

### 1.1 Project Overview

Fluffle is a **desktop app for creative writers**, optimized for **use via a Command Line Interface** (CLI). It aids users in creating and managing their writings and ideas. As a fast-typing writer, this CLI app is highly suitable for you. This product is created on IntelliJ IDE with Java programming language.
<br>

### 1.2 Accessing Fluffle

The latest release of Fluffle can be accessed from **[here](https://github.com/AY2021S1-CS2113T-W11-4/tp/releases/tag/v2.1)**.

## 2. Summary of Contributions

### 2.1 Code contributed

- The functional and test code contributed can be accessed via **[here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=hungvo0603&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)**.

- Implemented `WordsLoader` and `WordsSaver` classes in `storage.word` package. 
- Implemented the `filter words` and `list filter words` commands, which depends on the Words List. All lines of code about `filter words` and `list filer words` commands are stored in `wordlist.wordfilter` package.
- Implemented the `remind` commands in `writing` package. The code can be accessed in `WritingReminder` class. 

### 2.2 Enhancement Implemented

#### 2.2.1 Main features enhancements

- Implemented storage for words by `WordsLoader` and `WordsSaver` classes. [#204](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/204)
    - Functionality: This helps store the words in the user's customized words list into the hard drive location and load them whenever the user enters the program. The word types that the program can save and load are Noun, Verb and Adjective.
    - Justification: This is necessary because when an expert writer uses the application frequently, their words list increases in size. In order to help him manage the words list better, local storage was implemented.
- Implemented `filter words` and `list filter words` commands. [#133](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/133) [#174](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/174)
    - Functionality: This helps the user to get the words as they wish. Words can be filtered based on their types or some of their substrings.
    - Justification: This feature helps creative writers to get the words they need in their writings, or simply helps them brainstorm for new ideas.
- Implemented `remind` command for writings. [#214](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/214)
    - Functionality: This helps absent minded writers to stick to a piece of their writing by reminding them which writings they should continue on a specific date.
    - Justification: Some writers end up writing a part of their whole story and totally forget about it later on. This helps encourage the writers to develop their short pieces to a full-length story and continuously practice their creative writing skills. 

#### 2.2.2 Miscellaneous enhancements

- Fixed print format for `noun`, `verb` and `adjective` commands. [#293](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/293)
- Enhanced print format for some word-related `help` commands and make them in sync with the User Guide. [#306](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/306)
- Added JUnit test methods for filter-words-related classes. [#150](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/150)

### 2.3 Contributions to documentation

#### 2.3.1 Contributions to Javadoc comments

Java-doc comments are provided for most of the public classes and methods that I implemented. This helps subsequent developers to catch up with the project easily.

#### 2.3.2 Contributions to the User Guide

- Added the Preface and Command format sections for Feature section.
- Added "Filtering words in word list: `filter word`" and "Listing your filter list: `list filter words`" sections.
- Added "Getting reminders for your writings scheduled for a specific date: `remind`" section.
- Increased the navigability between sections in the User Guide (add Table of Contents, Jump to top, ...).
- Proofread User Guide and gave feedback to team members.

#### 2.3.3 Contributions to the Developer Guide

- Provided the skeleton for the whole document (headings, subheadings, etc...) [#309](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/309)
- Added Table of Contents.
- Added "Introduction/Background" section
- Modified "Setting up the project in your computer"
- Added "Design/Architecture" section together with "Figure 1: Overview of Fluffle Architecture".
- Added `Implementation/Writing Features/Getting reminder for writings scheduled on a specific date` section.
- Added "Implementation/Word Features/Filtering words" section together with Figure 6 and Figure 7.
- Added v2.0 entries in "Appendix C: User Stories" table.
- Added "Appendix D: Non-Functional Requirements" section.
- Added "Appendix E: Instruction fors manual testing".

### 2.5 Contributions to team-based tasks

- Setup the team repo as instructed by the details on the module website.
- Kept an eye on the module's requirements and remind team members to finish their tasks and credit on the tP progress dashboard.
- Assigned team members to their issues.
- Released v1.0 and v2.0. [v1.0](https://github.com/AY2021S1-CS2113T-W11-4/tp/releases/tag/v1.0) [v2.0](https://github.com/AY2021S1-CS2113T-W11-4/tp/releases/tag/v2.0)
- Reported issues and evaluated team members' code, sometimes helped them fix the issues, too. [#187](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/187) [#223](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/223/files) [#274](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/274)
- Refactored the packages to be more OOP. [#315](https://github.com/AY2021S1-CS2113T-W11-4/tp/pull/315)

### 2.6 Contributions beyond the project team
- Reviewed and gave thoughtful feedback on other team's Developer Guide in tutorial. [#12](https://github.com/nus-cs2113-AY2021S1/tp/pull/12)
- Reported bugs for other team's project in mock Practical Exam. [ped](https://github.com/hungvo0603/ped/issues)
