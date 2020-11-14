# Ho Chong Han Nathaniel - Project Portfolio Page for Quotesify

## Overview
I am part of a team of 5 software engineering students, and we were tasked to design and implement a
Command Line Interface (CLI) desktop application for our team project. The application that we developed
is called Quotesify. I was tasked with the Quote Management and Quote Reflection feature in Quotesify.

### About the project
Quotesify is an application to help book readers improve their reading experience. With Quotesify, users can add books 
and quotes they wished to remember into the application. Users can also categorize their books and quotes, as well as 
rate their books. Quotesify is also built with a progress tracker to manage your reading activities.

## Summary of Contributions
This section shows a brief summary of my contributions to the team project, including coding, documentation
and other helpful contributions throughout the development of Quotesify.

### Code contributed:
Please click [here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=nat-ho&tabRepo=AY2021S1-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other) to access the code that I contributed in this project.

### Enhancements implemented:

#### 1. Quote Management feature in Quotesify.

**What it does:**
* The user may use the `add -q` command to add a quote into Quotesify.
* The user may use the `edit -q` or `delete -q` command to edit or delete an existing quote.
* The user may use the `list -q` command to list all existing quotes, quotes from a specified author, quotes from a 
specified reference, or both from a specified author and reference.
* The user may also use the `find -q` command to find quotes containing a specified keyword.

**Justification:**
The Quote Management system is designed to help users log important and meaningful quotes from books and information 
found on the web, as they are usually read and forgotten after a short period of time. This feature creates a personal repository 
of those quotes, enabling users to revisit and find them effectively.

**Highlights:**
To enable a wider range of use cases, Quotesify accepts multiple formats for quotes. Author and reference are optional 
flags that can be added to provide more information which will enable users to find and list quotes more efficiently. 
On launch and exit, users will also be reminded of saved quotes, resurfacing and reminding users so as to better remember.
    
#### 2. Quote Reflection feature in Quotesify.
    
**What it does:**
* The user may use the `add -qr` command to add a reflection to an existing quote.
* The user may use the `edit -qr` or `delete -qr` command to edit or delete an existing reflection.
* The user may use the `list -qr` command to list the reflection of a specified quote.

**Justification:**
According to "The forgetting curve", memory for any information decays over time if we do not actively engage with it.
By making it more interactive and allowing users to actively engage with the content through their own reflection
and summary, users are far more likely to internalize the content and remember it in the long run. 

### Contributions to the UG and DG:
* I have contributed to all parts relating to Quote Management and Reflection in the UG.
* Additionally, I also contributed to the FAQ section of the UG.
* I have contributed to all parts relating to Quote Management and reflection in the DG, including the UML diagrams.
* I also contributed to other sections including Product Scope and User Stories.

### Contributions to team-based tasks and contributions beyond the project:
* General formatting of UG and DG, provided suggestions during discussions and edited demo video. I also identified bugs 
and gave suggestions for other team's DG during the DG review and PE tests.