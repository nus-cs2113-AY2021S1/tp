# Chuah Jin Wei's Project Portfolio Page

## Project: HdBuy

HdBuy allow users to easily find and bookmark resale flats available matching their preferences.
The user interacts with it using CLI. It is written in Java with 2.8 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)


* **Enhancements implemented**:
    * **New Feature**: Added the general parsing of inputs.
        * What it does: allows the app to understand the user's input.
            - [Parser](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/parser/Parser.java)

    * **New Feature**: Added the execution of commands.
        * What it does: an abstract class and subclasses for general commands. It forms the initial basis of the application.
            - [Command](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/Command.java)
            - [CloseCommand](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/CloseCommand.java)
            - [DefaultCommand](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/DefaultCommand.java)
            - [FilterCommand](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/FilterCommand.java)
            - [FindCommand](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/FindCommand.java)
            - [HelpCommand](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/HelpCommand.java)

    * **New Feature**: Added the ability to search
        * What it does: allowed the user to add different filters for the `find` command.
            - [FindCommand](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/FilterCommand.java)

    * **New Feature**: Added a class to handle interactions with the user.
        * What it does: handles interactions between the user and the app. (i.e. reading input and printing output)
            - [TextUi](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/ui/TextUi.java)

    * **Enhancement**: Added the tabular view of the `find` command results.
        * What it does: allows the user to more easily digest the information about the resale flats.
            - [showUnits in TextUi](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/ui/TextUi.java)

    * **Enhancement**: Added the `help` command output.
        * What it does: allows the user to easily view the available commands and what they do.
            - [showHelp in TextUi](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/ui/TextUi.java)

    * **Enhancement**: Refactored `TextUi`.
        * What it does: less repeated Strings in the code and important values are now defined at the top for easy editing. (e.g. change in contact email or output format.)
            - [showHelp in TextUi](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/ui/TextUi.java)


* **Contributions to documentation**:
    * User Guide:
        - [Wrote Error Handling](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/docs/UserGuide.md)
    * Developer Guide:
        - [Wrote description of the Parser and Command component](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/docs/DeveloperGuide.md)


* **Contributions to team-based tasks**:
    * Tidy up the Google Docs. Added important links for the group members to keep track of things easily. The structure also makes the content easier to digest.
        - [CS2113 group 1](https://docs.google.com/document/d/1x-nq_wratQyNYqNDiN9RNC1ox3aGxQgZSRB6DzkErlk/edit?usp=sharing)
    * Recorded the demo video. A comprehensive demo which demonstrates the abilities of the application.

* **Review / mentoring contributions**:
    * Provide help and feedback when team members encounter problems setting up things.
        - Limited to problems which have been encountered by me too.


* **Contributions beyond the project team**:
    * Helped others with common problem I faced and provided a temporary solution to it:
        * [Forum post on errors when running tests in exercises](https://github.com/nus-cs2113-AY2021S2/forum/issues/14#issuecomment-765892459)
    