# Theodore Pinto's Project Portfolio Page 

## Overview
CheatLogs is a desktop application used for managing a user's cheatsheets. 
The user interacts with it using a command line interface (CLI), 
and it has a graphical user interface (GUI) created with JavaSwing. 
It is written in Java and has about 5000 Lines of Code (LoC).

## Summary of Contributions

### Code Contributed

* [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=w11-3&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=theopin&tabRepo=AY2021S1-CS2113T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

### New Features

* Read cheatsheets from `.xml` files
  * Allows the user to load cheatsheets that were previously created.
  * This feature enhances the product as it prevents the user from having
    to type his cheatsheets everytime CheatLogs is loaded.
  * This implementation was difficult as it involved creating strong links
    to the main features of CheatLogs.
  * It was also a tough feature to implement as it involved creating various
    levels of checks to defend areas that users could exploit to crash
    the program.
    
* Write cheatsheets to `.xml` files
  * Allows the user to store cheatsheets that were created in a session of
    CheatLogs.
  * This feature enhances the product as it allows the user to store his
    cheatsheets in a file, which can be read by the application in a 
    subsequent run.
  * This implementation involved creating a proper structure for the `.xml` 
    file so that it can be properly parsed when the application loads 
    subsequently.
  * This feature was difficult as it involved constantly identifying whether
    the cheatsheet was preloaded or not. Such identification is essential to 
    ensuring that it is sorted into the correct directory. 
      
* Delete `.xml` files from storage
  * Allows the user to remove any cheat sheet.
  * This feature enhances the product as it allows the user to better 
    organize his present cheat sheets by removing cheatsheets he does not
    want to reference anymore. Moreover, it provides an option for the user
    to clean all his stored cheatsheets if he feels that they are 
    unnecessary.
  * This implementation was difficult as it involved deleting both the file
    and its parent directory if it was empty. A systematic system had to be 
    implemented that looks out for any empty directories to reduce clutter
    in the user directory.  
      
* Preloaded cheatsheets
  * Allows `.xml` files containing example cheatsheets to be packaged within
    the `.jar` file.
  * This feature enhances the product as it prevents the user from having
    to type his cheatsheets everytime CheatLogs is loaded.
  * This implementation was difficult as it involved parsing the required files
    from within the packaged `.jar` file. To locate these files, the method 
    used to locate files within the user directory was not effective, and I had
    to look up relevant Java libraries for features that could solve this 
    problem.
  * [Credits](https://stackoverflow.com/questions/1529611/how-to-write-a-java-program-which-can-extract-a-jar-file-and-store-its-data-in-s
)

### Project Management

* Maintained the issue tracker
* Documented the backend features used in the development of CheatLogs
  in the DG.

### Enhancements

* Edited the configurations of the documentation guides to ensure
  proper formatting of tables and emoji icons on github-pages.
  * [#180](https://github.com/AY2021S1-CS2113T-W11-3/tp/pull/180/commits/3ab720ee6bca43e3bf91bea46d83d7c30daaca71), 
  * [#181](https://github.com/AY2021S1-CS2113T-W11-3/tp/pull/181/commits/2fb6dc102f9a6c6f676d2315918e728fab10848e)


### Documentation

* User Guide
    * Added a section explaining the implementation of the storage system.
    * Added steps for advanced users to write XML files that can be parsed by
      CheatLogs.
    * [PR #208](https://github.com/AY2021S1-CS2113T-W11-3/tp/pull/208/commits/bea000ff8c12f87d049c70731ee888b59d5b7f4d)

    
* Developer Guide
    * Added the design of the storage system.
    * Added a class UML diagram to illustrate the storage
      system
    * Added the implementation of the storage system.
    * Added sequence diagrams to illustrate the functionality
      of each class within the storage system.
    * Added the appendix detailing the configuration and
      automated testing fo CheatLogs.
    * [PR #268](https://github.com/AY2021S1-CS2113T-W11-3/tp/pull/268/commits/84512697501b0c01f2002a54360a657481c24310)

### Community

* PRs reviewed (with non-trivial review comments):
    * [Comment 1](https://github.com/nus-cs2113-AY2021S1/tp/pull/2#pullrequestreview-518301475)
    * [Comment 2](https://github.com/nus-cs2113-AY2021S1/tp/pull/2#discussion_r513160385)
    * [Comment 3](https://github.com/nus-cs2113-AY2021S1/tp/pull/2#discussion_r513160725)

* Reported bugs and suggestions for other teams taking CS2113T:
    * Issue [#5](https://github.com/theopin/ped/issues/5)
    * Issue [#7](https://github.com/theopin/ped/issues/7)
    * Issue [#9](https://github.com/theopin/ped/issues/9)

* Shared a link to the Java DOM Parser library to parse .xml files
  * Issue [#101](https://github.com/nus-cs2113-AY2021S1/forum/issues/101)