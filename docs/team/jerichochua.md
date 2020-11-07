# Jericho Chua - Project Portfolio Page

## Overview

Fitr is a command-line application, helping you keep track of your food intake and exercises.

## Summary of Contributions

### Code contributed
[Link to code contributed](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jerichochua)

### Enhancements implemented
- Allow the user to clear the individual list, or clear all the lists at the same time
    - This feature allows users to quickly empty the lists, if they want to start with a fresh list.
- Allow the user to edit a previous exercise or food entry in the lists
    - This feature allows users to correct mistakes that they make in a previous entry.
    - This was initially hard to implement as the `edit` command was also used to edit the user's profile. Hence, I created the `EditCommandParser` to handle the arguments in the `edit` command first. Once the arguments are parsed, it is then passed into a `EditEntryCommand` class and the corresponding entry is edited.
    - The command to edit a specific entry is also designed as a one-shot command, so users who can type fast in a command-line application can quickly edit an entry.
- Added a `ResourceManager` class to handle loading of resources in the JAR file
    - As Fitr requires some resources to be packaged together with the JAR file, such as the tips and exercise lists, the usual way of reading files using `File` and `Scanner` cannot be used.
    - Instead, `InputStream` and `InputStreamReader` is used to read the resources packaged in the JAR file.
- Added some JUnit tests

### Contributions to documentation
Contributed the following sections in the user guide:
- Editing a food entry
- Editing an exercise entry
- Clearing commands (Clearing all food entries, clearing all exercise entries, clearing all entries)
- Saving your data
- FAQ

### Contributions to DG
Contributed the following sections in the developer guide:
- Architecture diagram
- StorageManager component
- ListManager component
- Edit command
- Clear command

### Contributions to team-based tasks
Contributed to the following team-based tasks:
- Set up the GitHub team organisation and repository
- Maintaining the issue tracker
- Release management

### Review/mentoring contributions
Reviewed the following PRs:
- [#86](https://github.com/AY2021S1-CS2113T-W13-2/tp/pull/86)
- [#89](https://github.com/AY2021S1-CS2113T-W13-2/tp/pull/89)
- [#98](https://github.com/AY2021S1-CS2113T-W13-2/tp/pull/98)

### Contributions beyond the project team
- Responded to a [forum post](https://github.com/nus-cs2113-AY2021S1/forum/issues/52#issuecomment-693130427)
- [Reviewed](https://github.com/nus-cs2113-AY2021S1/tp/pull/31#pullrequestreview-518329234) another team's DG
- Found [4 bugs](https://github.com/jerichochua/ped/issues) in a team's tp during PE dry run
