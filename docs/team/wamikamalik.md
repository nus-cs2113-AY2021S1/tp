# Wamika Malik - Project Portfolio Page

## Overview
#### Project: Nav@NUS
Nav@NUS is a desktop application that aids users in getting around NUS. The user interacts with it using a CLI and thus,
 this application is appropriate for users who can type fast.

## Summary of Contributions
Given below are my contributions to the project.

#### New Feature: Find a bus route between 2 locations 
* What it does: it allows the user to find all direct bus routes between 2 locations.
* Justification: This is one of the main features of the product as it aids navigation by allowing the user to find all 
buses that go from one bus stop to another. It further eases the user's travel by displaying intermediate stops for each
 possible bus they can take.
 * Highlights: This feature forms the basis of the product and was challenging to implement as it required
 careful analysis of the type of locations that can be input by the user.
 
#### New Feature: Change description for your favourite commands
 * What it does: it allows the user to change the description for an existing command in the list of favourite commands.
 * Justification: This feature improves the ease of usability of the product as it allows the user to change description
 in one step rather than deleting the command from the list and adding it again with the new description.

#### Code Contributed
* [This is the RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=wamikamalik) to 
my code.

#### Enhancements implemented
* Added food places data classes. (Pull request [#57](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/57))
* Added a similarity check to existing features. This check recommends possible locations based on potential spelling 
errors. (Pull request [#73](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/73))
    + Credits: The edit distance algorithm used was taken from 
    [http://rosettacode.org/wiki/Levenshtein_distance#Java](http://rosettacode.org/wiki/Levenshtein_distance#Java).
* Refactored classes into packages. (Pull request [#96](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/96))
* Wrote tests for existing route and description change features and a few tests for Parser. (Pull requests 
[#11](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/11), 
[#33](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/33), 
[#104](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/104))    

#### Contributions to documentation
* Added documentation for features `/route`and `/descfav`. (Pull request 
[#75](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/75), 
[#136](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/136))
* Added documentation for `similarity check`. (Pull request [#80](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/80))
* Did cosmetic tweaks to images used. (Pull request [#90](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/90))

#### Contributions to the DG
* Added implementation details, class diagrams and sequence diagrams for `/route` and `/descfav` features. (Pull 
requests [#111](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/111), 
[#112](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/112), 
[#119](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/119)
) 
* Added details for architecture, architecture diagram and class diagrams for all components. (Pull requests 
[#68](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/68), 
[#195](https://github.com/AY2021S1-CS2113T-F14-3/tp/pull/195)
)

#### Contributions to team-based tasks 
* Managed releases v1.0 and v2.0
* Set up the GitHub team organisation and repo
* Added details about the different components in DG.
* Maintained the issue tracker for specific features and some common tasks.