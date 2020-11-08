# Lim Yuan Bing - Project Portfolio Page

## Project: Study it

Study It is an interactive all-in-one study companion to help you organize your study matters!

It is packed with 4 major features: bookmark, timetable, academic and flashcard to help store and arrange your
study matters in a thoughtful manner so that you can focus on Studying It!

The following are my contributions to the project!

### Features added: 

* **Designed the main architecture of the program and maintain it**
    * What it does: Provides proper partitioning and command parsing as well as program flow to ensure every team member's
    code can be fit into our program. I have also helped integrated each member's mode into the program.
    * Justification: For a program with 4 major features, I needed to design the architecture such that user can switch
    between the modes seamlessly and use the functionalities provided in each mode without clashing with the rest.
    * Highlights: With the main architecture, the infrastructure within each mode will not interfere with one another, 
    and the design is flexible enough to implement new features for future expansions.
    * Credits: The architectural code was coded from scratch.
    
* **Implemented general functions for the program**
    * Functions: `help`, `location`, `cd`, `exit`
    * What it does: These functions can be used anywhere throughout the program to help user navigate our app containing
    multiple modes. `help` instructs the user what commands are available in each mode. `location` informs the user
    their current mode. `cd` is used to switch between modes. `exit` is used to either exit the program or their current mode
    * Justification: We needed general functions to help user navigate our expansive app that has 4 major modes.
    * Highlights: `help` will print out help messages specific to the mode the user is currently at. `cd` can be used
    anywhere in the app and is modified to take in index number (for quicker navigation) or mode name (for meaningful interaction).
    * Credits: The general functions are implemented during the design of the system architecture.
   
* **Streamlined the user interface of the program**
    * What it does: Decided how the general user interface of the program should look like and helped streamlined it across
    multiple modes. 
    * Justification: A clean user interface will improve the user's experience while using our application.
    * Highlights: Provided some thoughtful functionality when designing user interface, such as printing the current time
    at the menu.
    * Credits: Mode specific interfaces were coded by each respective teammates and streamlined by me.
    
* **Added `highlight` function as one of the general functions**
    * What it does: It prints out the starred items from bookmark mode and academic mode
    * Justification: Allows user to quickly access the important informations he/she deemed important
    * Highlights: It can be called from anywhere in the app, allowing quicker access
    * Credits: Si Hui implemented the `star` function in bookmark mode but I implemented it within the academic mode which was
    designed by Ziyi. I made use of these 2 functionalities to run the `highlight` function.

### Code contributed: 

[RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=farice&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=farice9&tabRepo=AY2021S1-CS2113T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

### Project management

* Helped review many PRs and solved several merge conflicts.
* Made notices for my teammates when issues were found: [checkstyle errors](https://github.com/AY2021S1-CS2113T-T12-1/tp/issues/39)
* Helped my teammates out when they had problems with uploading images to documentations and problems with git.
* Handled the finalizing and release of our project for each milestone: [Releases](https://github.com/AY2021S1-CS2113T-T12-1/tp/releases)

### Documentation:
* User Guide: 
    * Added the [About](https://ay2021s1-cs2113t-t12-1.github.io/tp/UserGuide.html#about), [Getting Started](https://ay2021s1-cs2113t-t12-1.github.io/tp/UserGuide.html#getting-started), 
    [General Commands](https://ay2021s1-cs2113t-t12-1.github.io/tp/UserGuide.html#general-commands) sections
    * Added the [Command Summary](https://ay2021s1-cs2113t-t12-1.github.io/tp/UserGuide.html#command-summary) of the program
    
* Developer Guide:
    * Added the [Setting up & getting started](https://ay2021s1-cs2113t-t12-1.github.io/tp/DeveloperGuide.html#setting-up--getting-started),
    [Major components](https://ay2021s1-cs2113t-t12-1.github.io/tp/DeveloperGuide.html#major-components),
    [Architecture](https://ay2021s1-cs2113t-t12-1.github.io/tp/DeveloperGuide.html#architecture),
    [Main component](https://ay2021s1-cs2113t-t12-1.github.io/tp/DeveloperGuide.html#main-component) sections.
    * Drawn the regular diagrams of major components and system architecture, UML sequence diagram of how the architecture 
    components interact with each other and the UML diagram of the main components in StudyIT class.

### Community:
* Non-trivial PR reviews: 
    * [#102](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/102)
    * [#92](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/92)
    * [#194](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/194)
    
* Reported bugs and suggestions to teammates: 
    * [#190](https://github.com/AY2021S1-CS2113T-T12-1/tp/issues/190)
    * [#133](https://github.com/AY2021S1-CS2113T-T12-1/tp/issues/133)
    * [#60](https://github.com/AY2021S1-CS2113T-T12-1/tp/issues/60)