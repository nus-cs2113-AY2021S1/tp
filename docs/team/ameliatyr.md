# Amelia - Project Portfolio Page

## Overview
Within a time span of 7 weeks of hard work, we, a group of 5 enthusiatic NUS Computing students, proudly present to you our newest product, in the scope of CS2113T-Software Engineering and Object Oriented Programming module, **FLUFFLE**!

**FLUFFLE** is the writers swiss army knife of writing tools and idea organisation. While most writing applications focus on providing the user with a GUI they can type their story in, Fluffle caters specifically to creative writers, with various functions to help the creative process along and make the writing process spontaneous and smooth as possible. 

## Summary of Contributions

### Code contributed
tP Dashboard contribution can be found [here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=totalCommits&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=AmeliaTYR&tabRepo=AY2021S1-CS2113T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other).

### Enhancements implemented
Summary: Most of the enhancements I implemented for this product was for maintaining the Bunny list, as well as some UI components for aesthetics purposes.

More info on implementation and usage of the commands listed below can be found on the [User Guide](UserGuide.md) and [Developer Guide](DeveloperGuide.md), where I wrote the sections about these functions. 

####Bunny list enhancements

For context, a bunny is an idea that has yet to be fully written into a piece. The purpose of the bunnylist was to provide the creative writer using **Fluffle** store and organise these ideas, and to make it easy for user to find specific ideas. This was created to tackle the writers' problem of losing track of their loose ideas as they have too many ideas and no unified place to keep them.

Bunny commands implemented:
* bunny
* list bunny
* filter bunny
* save bunny
* delete bunny
* random bunny
* reset bunny

####Aesthetic enhancements

I implemented the segment of the code that printed out the logo art at the start of the application, as well as the functionality that enables the user to swap out the line dividers to one of the three options available. These components were an attempt to make the dull CLI look more entincing for the creative writer to use.

Aesthetic command: 
* divider

####Miscellaneous enhancements

I implemented the segments of the help function related to the other commands I created as listed above.

####Code quality
For the components I created I am also responsible for the junit tests, javadocs, assertions and logging implemented in those sections.

Note: I wrote the function description and explanations for the all the bunny related commands as well as the `divider` command listed by the help function, which helps the user recall the various commands while using the Fluffle. The only bunny related command I did not implement was the `reset bunny` command. While I did not implement the `reset bunny` command of the bunny list commands, I did the UG, DG and help segments for it.


### Contributions to documentation
* In the UG, my contributions are all the bunny related commands, the divider command and the help command.
       * this included the formatting and expected out puts under those segments
* I also did the Glossary, and the bunny segments of the command summary table.

### Contributions to the DG
* For the DG, I did the explanations for all the bunny related commands, the divider command explanation and some miscellaneous components (miscellaneous components refer to sections in the intro where we did it together and one person pasted it into the markdown from google docs so I do not take full credit for those). 
* I also did the Bunny Manager Component and the bunny object storage and management components below it.
* I also added most of the table of contents with hyperlinks in the DG. 
* I also added the segment on value proposition and target user audience segments, as well as a handful of the user stories.
* I contributed 4 UML diagrams to the DG in total, which can be found in our diagrams folder under these names:
    * Bunny_manager_component
    * Class_diagram_bunny
    * Sequence_diagram_general_command
    * Sequence_diagram_bunny
    
### Contributions to team-based tasks 
* I contributed some issues in the issue tracker at the end while checking code and functionality. 
* I set up the project boards for the user stories earlier in the project. Most of the initial user stories were by me, which can be seen on the team repo issues list
* I proposed the idea of a creative writing application changed from the quiz app to hopefully better meet the requirements and develop within the restrictions on the project.

## Contributions beyond the project team
* I made sure to review as many of the iP as listed during the peer review earlier in the semester. 
* I also left comments on [this teams pull request](https://github.com/nus-cs2113-AY2021S1/tp/pull/36) and the iP pull requests: [1](https://github.com/nus-cs2113-AY2021S1/ip/pull/5) [2](https://github.com/nus-cs2113-AY2021S1/ip/pull/10) [3](https://github.com/nus-cs2113-AY2021S1/ip/pull/20) [4](https://github.com/nus-cs2113-AY2021S1/ip/pull/171)

### Evidence of helping others e.g. responses you posted in our forum, bugs you reported in other team's products
* I gave comprehensive reviews during the UG DG peer reviews.
* I found 10 bugs in the PE-D and tried to comment as helpfully as possible.

The latest release of Fluffle can be accessed from **[here](https://github.com/AY2021S1-CS2113T-W11-4/tp/releases/tag/v2.1)**.



