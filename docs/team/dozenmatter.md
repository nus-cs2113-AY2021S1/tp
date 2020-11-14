# Tay Chin Heng - Project Portfolio Page for Quotesify

## Overview
I am part of a team of 5 software engineering students, and we were tasked to design and implement a
Command Line Interface (CLI) desktop application for our team project. The application that we developed
is called Quotesify. I was tasked with the Category Management feature in Quotesify.

### About the project
Quotesify is a free desktop application to help book readers improve their reading experience. With Quotesify,
users can add books and quotes they wished to remember into the application. Users can also categorize their books and
quotes, as well as rate their books. Quotesify is also built with a progress tracker to manage your reading activities.

## Summary of Contributions
This section shows a brief summary of my contributions to the team project, including coding, documentation
and other helpful contributions throughout the development of Quotesify.

### Code contributed:
Please click [here](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=dozenmatter&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to access the code that I contributed in this project.

### Enhancements implemented:

#### 1. Category Management feature

**What it does:**
* The user may use the `add -c` or `delete -c` command to add / remove multiple categories to / from an existing book and quote.
* The user may use the `edit -c` command to edit the existing category name.
* The user may use the `list -c` command to list all existing categories or to list all items tagged under a specified category.
* The user may also use the `find -c` command to find categories that contains the specified keyword.
    
**Justification:**
The Category Management feature is designed to allow users to categorise their existing books and quotes in Quotesify.
This feature is useful especially when the user has a large list of books and quotes stored, as it helps them organise these items into groups, making each item easier for reference.
    
**Highlights:**
The total number of items tagged under a category is displayed whenever a user runs the command to list all categories.
The aim is to provide the user a basic statistic on the number of items under each category, just in case if they may be interested.

#### 2. Storage feature

**What it does:**
* Serialises all model objects of Quotesify into a JSON String and saves them into a save file.
* On program launch, data is loaded from the save file and deserialised into Quotesify's model objects.
    
**Justification:**
The Storage is a must-have component for Quotesify as it would have defeated the purpose if a user's favourite books or quotes only lasts on runtime.
    
### Contributions to the UG and DG:
* I have contributed to all parts relating to Category Management in the UG.
* I have contributed to the Quick Start and FAQ sections of the UG.
* I have contributed to all parts relating to Category Management in the DG, including all UML diagrams.
* I have contributed largely to the Design section of the DG.

### Contributions to team-based tasks:
* I have managed the timely release of various Quotesify versions onto the team's Github releases page.
* Contributed to the brain-storming of user stories.

### Contributions beyond the project team:
* I have contributed in bug catching for another team's application and have also provided suggestions to improve their DG.
