# Neil Banerjee (neilbaner): Project Portfolio Page

## Product overview

Zer0Note is a note taking and organisation application meant for students(especially those who can type fast). It combines the features of note-taking apps (like OneNote) with the interaction speed of command-line based tools like vim and emacs. It helps students (like yourself!) to keep track of deadlines and take notes quickly.

## Summary of contributions

### Code contributed

See the [RepoSense Dashboard here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=neilbaner&sort=totalCommits%20dsc&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByNone&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=neilbaner&tabRepo=AY2021S1-CS2113T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other) for my contributions to Zer0Note. 

### Enhancements implemented

* I implemented the `help` and `exit` commands at the very beginning of the project. The `help` command provides the
 user with a condensed version of the user guide for quick reference. The `exit` command exits the program. [#44](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/44)

* I implemented the file storage functionality, that allows the application to save the user's progress/data. It
involved adding a `serialize()` function to `TaskList`, `NotebookShelf` and all the contained classes, as well as
utilising these functions to write to a text file. The `Storage` class also fails gracefully if the save files are
corrupted, throwing useful error messages and not loading potentially garbled data while still loading known-good
data. [#96](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/96)

* Near the end, I implemented the command prompt to show the user's current selection/location in the application
. This was in response to [this issue](https://github.com/AY2021S1-CS2113T-T12-3/tp/issues/175) from the PE Dry Run
. The prompt is inspired by the UNIX command prompt which displays the selected directory to the left of the user's
 input, so the user always knows where they are and what directory is being affected by their commands. [#201](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/201)

* I helped implement, troubleshoot, and refactor various other features. [#53](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/53), [#197](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/197)

### Contributions to User Guide

* I wrote the introduction and part of the setup guide. 

* I wrote the feature guides for `help`. 

* I wrote the section about editing the save file. 

[#122](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/122), [#217](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/217), [#232](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/232)

### Contributions to Developer Guide

* I created the overall architecture diagram. 

* I created the class diagrams for the CLICommand and Notebook components. 

* I created the sequence diagram for the Storage component. 

* I wrote the following sections: 

    * Introduction (all subsections)
    
    * Design (Architecture, UI and Commands subsections)
    
    * Implementation (Storage subsection)
    
    * Documentation (Style Guidance and Diagrams subsections)
    
[#78](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/78), [#117](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/117), [#126](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/126), [#137](https://github.com/AY2021S1-CS2113T-T12-3/tp/pull/137)

### Contributions to team-based tasks

* I set up the organisation and repository for the team on GitHub. 

* I created the project skeleton, with the file and class structure that we would follow in the development of the
 program. 

* I usually managed the issue tracker, setting up issues/milestones, labels etc. 

* I refactored the code a few times to make sure it looked like one person had written all of it and to improve the
 use of OOP principles. 

### Reviewing/mentoring contributions

* [Here are all the PRs I commented on or created](https://github.com/AY2021S1-CS2113T-T12-3/tp/pulls?q=is%3Apr+commenter%3Aneilbaner). 

* In group discussions, being the most experienced with Java and OOP, I guided the others and helped them implement
 OOP features like inheritance, polymorphism etc. 

### Contributions beyond the project team

* [See here](https://github.com/nus-cs2113-AY2021S1/forum/issues?q=is%3Aissue+commenter%3Aneilbaner+) for my
 contributions to the class forum. Also see 
 [here](https://nus-cs2113-ay2021s1.github.io/dashboards/contents/forum-activities.html). 
 
* I made 12 bug reports during the PE dry run, see [here](https://github.com/NeilBaner/ped/issues). 
