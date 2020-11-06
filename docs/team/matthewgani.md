# Matthew Gani - Project Portfolio Page

## Overview
Scheduler--; (S--) is a desktop app for managing deadlines from different sources. The user will use a Command Line Interface to control it. It is specially designed for Computing students who are comfortable in using CLI and have Git project deadlines as well as consolidated Zoom session links due which will suit home based learning in this COVID period. 

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature:** Added the ability to add events
    - What it does: Allows the user to add events under 3 categories, Personal, Zoom and Timetable. These events all can take in multiple parameters to be created.
    - Justification: This feature is one of the basic features a scheduling app should have, adding events to the user's list.
    - Highlights: This feature required splitting up the user's command and checking individually if the fields like data and time were valid. There was also 2-3 combinations that takes in different numbers of parameters for each category of event that we needed to detect properly.

- **New Feature:**  Added the ability to extract dates, times and zoom links from any text
    - What it does: Allows the user to copy and paste emails into the CLI and the feature will extract valid dates, times and zoom links. The user can then choose from them if there are multiple dates etc.
    - Justification: This feature is a unique feature that was implemented so users didn't need to spend so long reading emails for important details and also serves as an alternative to adding an event quickly.
    - Highlights: This feature required the use of Regex to match patterns for the date, time and zoom links. It required the ability to take in copy pasted texts that had multiple paragraphs (with newlines) in them. A lot of variations of the date were detected, for example with suffixes or short form like Sep instead of September. It also has the ability to add the event as Personal or Zoom depending on if a Zoom link was detected.
    
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=matthewgani&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project Management**
    - Managed releases `v1.0` - `v2.0` (2 releases) on GitHub
    
- **Documentation:**
    - User guide:
        - Added documentation for the `add` and `extract` features.
        - Added and maintained the table of contents with hyperlinks, made the headings with numberings
    
    - Developer Guide:
        - Added implementation details for the `add` and `extract` feature, with UML diagrams.
        - Added and maintained the table of contents with hyperlinks
        - Wrote the Setting up, getting started section
        - Wrote the first 2 Use cases
        
- **Community:**
    - Assisted with peer review for other group's PR [1](https://github.com/nus-cs2113-AY2021S1/tp/pull/1/files)
    - Assisted in testing other group's tp based on their User Guide and finding bugs [1](https://github.com/matthewgani/ped/issues)
