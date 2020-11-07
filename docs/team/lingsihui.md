# Ling Si Hui, Shiho - Project Portfolio Page

## Overview
Our product Study It provides an interactive desktop app to help manage your study related matters.
It can keep track of your various study matters, present them in an organized and thoughtful manner to 
help you organize your study life. It is written in Java and includes 4 features: bookmark, timetable, academic, and flashcard. 

Given below are my contributions to the project.
* **New Feature** : Added the ability to  add link t-> title/ remove link / add category / delete category / star bookmark
    * What it does: allows the user to add bookmark link to bookmark list. Links added can be removed by using the remove command. Added links that are important can be starred.
    * Justification: This feature serves as the basic command for the bookmark feature so that the user can customise the list of links, categories and their starred links.
    * Highlights: This feature was relatively simple yet tedious. The concept behind the implementation for add links was simple, however, the additional conditions such as including 
    the title for the links, deciding on the factor that makes the link valid, and checking whether the link already exist make the add link command challenging. Add category command follows 
    the same structure as the add link commands but with fewer conditions hence it was less challenging. Delete category command and remove 
    category command have the similar structure with fewer conditions, hence it was less challenging as well. Star command was an additional enhancement that was made afterwards, and the 
    challenging component for star command is that this enhancement affected the rest of the bookmark features, hence it required me to change existing feature. 
    * Credits: The program was coded from scratch but mainly adapting the design architecture from my [ip](https://github.com/lingsihui/ip).
    
* **New Feature** : Added the ability to change mode/ go back to bookmark main /list bookmark 
    * What it does: allows the user to change mode and go back to main within bookmark feature. The list command was enhanced to show which mode the users are in and how many links they have.
    * Justification: This features make the navigability within the bookmark feature easier. 
    * Highlights: This feature was challenging in the sense that due to the different modes, the user needs to know the mode of the bookmark mode to better navigate around bookmark feature. This was done through 
    the list command where it shows the current mode the user is currently in and enhancing list command to show the overview of the links, list of categories, list of links in the current mode the user is in and 
    the list of starred links the user has.
    * Credits: The program was coded from scratch.

* **New Feature** : Added the ability to store bookmark links in file
    * What it does: allows the user to keep the list of bookmark even after exiting the program. 
    * Justification: This feature is required so that user do not need to input the list of links they want to bookmark every time they start the program.
    * Highlights: The most challenging part of the feature was to decide on how to separate out the category, title and the links as links can contain many symbols. 
    * Credits: The program was coded from scratch.
   
* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=lingsihui&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Project Management**:
    * Logging [#76](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/76)
    * Maintaining the issue tracker
    * Update developer guide documenting the target user profile, user stories, and the documentation section [#118](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/118/files)
* **Documentation**: 
    * User Guide:
        * Added documentation for bookmark features [#137](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/137)
    * Developer Guide: 
        * Added documentation for bookmark feature: included UML diagram of the bookmark feature class diagram, bookmark feature sequence diagram and add command sequence diagram [#102](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/102)
* **Community:**
    * PR Reviewed (with non-trivial comments): [#103](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/103), [#92](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/92)
    * Reported bugs and suggestions for other teams in the class [#2](https://github.com/lingsihui/ped/issues/2), [#3](https://github.com/lingsihui/ped/issues/3), [#4](https://github.com/lingsihui/ped/issues/4), [#5](https://github.com/lingsihui/ped/issues/5), [#6](https://github.com/lingsihui/ped/issues/6), [#7](https://github.com/lingsihui/ped/issues/7)
  
