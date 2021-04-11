## Project: HdBuy (Tan Pang How's Project Portfolio Page)

HdBuy allow users to easily find and bookmark resale flats available matching their preferences.

* **Code contributed**:
    - [RepoSense link](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=CabbageTime&tabRepo=AY2021S2-CS2113-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
  
* **New Feature**: Added the ability for the app to process input into specific commands.
  * What it does: allows the app to identify different commands and react accordingly.
    - [Parser](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/parser/Parser.java)
    - [CommandEvaluator](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/parser/CommandEvaluator.java)
    - [CommandType](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/parser/CommandType.java)
  
* **New Feature**: Added the ability for the app to handle errors when invalid input is being entered by the user.
  * What it does: allows the app to not crash and continue running, while giving users instructions on writing the command with the correct format.
    - [EmptyParameterException](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/common/exception/EmptyParameterException.java)
    - [InvalidInputException](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/common/exception/InvalidInputException.java)
    - [InvalidParameterException](https://github.com/AY2021S2-CS2113-F10-1/tp/blob/master/src/main/java/seedu/hdbuy/common/exception/InvalidParameterException.java)

* **New Feature**: Added the ability for the user to filter the units.
  * What it does: allows the user to filter the units based on location, room number or lease remaining.
    - [FilterCommand](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/command/FilterCommand.java)

<div style="page-break-after: always;"></div>

* **Enhancement**: Refactor filter types into enumeration.
  * Highlights: Avoid the need to redefine the filter types everytime the filter command is called.
    - [QueryKey](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/common/QueryKey.java)

* **Enhancement**: Adjusted exception messages so that it is easier for the user to understand the mistakes in their input.
  * Highlights: Clearer instructions on the correct input; refactor term definition into a separate class to reduce the repeated code.
    - [TextUi](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/ui/TextUi.java)
    - [TermDefinition](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/src/main/java/seedu/hdbuy/ui/TermDefinition.java)
  
<div style="page-break-after: always;"></div>

* **Project management**:
  * Manual test the code to find bugs and errors.

<div style="page-break-after: always;"></div>

* **Documentation**:
  * Developer Guide:
    * [Wrote description on how does TextUi class access TermDefinition class](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/docs/DeveloperGuide.md)
    * [Drew TermDefinition Definition sequence diagram](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/docs/diagrams/TextUiDefinition.puml)
    * [Drew TermDefinition Example sequence diagram](https://github.com/AY2021S2-CS2113-F10-1/tp/blame/master/docs/diagrams/TextUiExample.puml)  

<div style="page-break-after: always;"></div>

* **Documentation**:
  * Javadocs:
    * Wrote javadocs for every public methods, excluding those of exception handling and logger's.