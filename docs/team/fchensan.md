# Francisco Chensan – Project Portfolio Page

## About the project
I am a part of a team of 5 software engineers and together we created a command line interface application called Zoomaster that helps students store Zoom meeting links.
The app stores the links in a user-created lesson timetable, such that the user is able to launch appropriate Zoom links based on the current
system time without manually selecting a lesson slot.

## Summary of contributions
This section shows the summary of my contribution in code, enhancements, documentation, and more.

### Code contributed
Please click [here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=fchensan&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=fchensan&tabRepo=AY2021S1-CS2113T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
to view the code that I have contributed.

#### Features implemented
* Edit bookmarks in bookmark mode and slots in timetable mode.
* User settings.
    * The `UserSettings` and `SettingsVariable` classes are implemented in such a way that
    it is very easy to add new settings and options for the user to choose (by using generics, etc).

### Enhancements to existing features
* Removed hardcoding of command keywords in the `help` output list of available commands.
* Added ordering of modules based on time in `show {MODULE}` output.

### Contributions to User Guide
* Added documentation for these commands:
    * `showsettings` and `set` settings command
    * `edit` in bookmark and timetable mode
* Added an overview of the features to better guide the readers.
* Suggested the use of a table to better organise notes on command labels.

### Contributions to Developer Guide
* Added explanations and sequence diagrams of the implementation of:
    * `showsettings`
    * `set` (settings command)
    * `edit` (timetable command)
    
### Contributions to team-based tasks
* Implemented the use of the `Gson` library. 
This makes it easier for the whole team to implement new features 
(no need to implement encoders and decoders for storage).
* Resolved issues assigned to me so that they do not clutter the issues page.

### Contributions beyond the project teams
* Gave another team feedback and helped discover bugs [here](https://github.com/fchensan/ped/issues).
