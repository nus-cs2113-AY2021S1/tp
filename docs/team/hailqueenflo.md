# Lim Si Qiao Florence - Project Portfolio Page

## Overview of Project: Study It
Study It is an interactive desktop app that helps manage your study related matters, optimized for use via a Command
Line Interface (CLI). It can keep track of your various study matters, presenting them in an organized and thoughtful
manner to help you efficiently study it with Study It.

It is written in Java, and comprises four main modes: Bookmark, Timetable, Academic and Flashcard.

The following are my contributions to the project.

### Features added:

* **Added the feature to add/delete flashcards**
    * What it does: Allows the user to add and delete flashcards, based on the question and answer provided by the user.
    * Justification: This feature improves the product significantly as the user can create a knowledge base of content
    to be revised, effectively compiling all the information needed for their next test.
    * Highlights: This feature allows users to add questions and answers according to the self-explanatory prompts by
    the app, making it an intuitive experience.
     
* **Implemented the function to list flashcards**
    * What it does: Lists all the flashcards that the user has added
    * Justification: This function is necessary for the user to refer to all the flashcards currently in the deck.
    * Highlights: This function clearly displays the card indexes along with the questions and answers, which is
    required for deletion of flashcards.

* **Implemented the function to test flashcards**
    * What it does: Returns a random question from the flashcard deck, and prompts user to enter an answer. The user can
    quit the test mode with the `back` command, and reveal the answer with the `show answer` command.
    * Justification: The best way the user can revise their study materials is by grading their own work, effectively
    helping them retain information and improve their grades.
    * Highlights: This feature has thoughtful functionalities, such as a way for users to find out the answer
    immediately when they are able to answer a question, helping them save time while revising. The score feature also
    acts as an encouragement for revision. For the convenience of the user, the test mode ignores the case of the user's
    answers.

* **Implemented the function to find flashcards**
    * What it does: Returns a list of flashcards that matches the search term input by the user.
    * Justification: This feature enhances the product because it conveniently returns only the flashcards that the user
    is interested in.
    * Highlights: This feature is user-friendly as it is not case sensitive, effectively returning all the flashcards
    that the user wishes to find.

### Code Contributed:

[RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=hailqueenflo&tabRepo=AY2021S1-CS2113T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

### Documentation:

* User Guide:
  * Added the documentation for all the commands in flashcard mode, listed under section 4 of the User Guide.
  * Did cosmetic tweaks to existing documentation of features

* Developer Guide:
  * Added the implementation details for the flashcard component, including the UMLs used in that section.
  
 ### Community:
 
 * Reported bugs and suggestions to teammates: 
    * [#127](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/127)
    * [#137](https://github.com/AY2021S1-CS2113T-T12-1/tp/pull/137)
    
  * Reported bugs and suggestions for other teams in the class:
    * [#2](https://github.com/hailqueenflo/ped/issues/2)
    * [#4](https://github.com/hailqueenflo/ped/issues/4)
    