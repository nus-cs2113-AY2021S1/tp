# User Guide

## Introduction

Quotesify is a free desktop application to help you in your reading activities. With Quotesify, you can add 
books and the related quotes that you wish to remember. You can categorize your books and quotes by author,
customized categories, and even rate your books. Quotesify also comes with a progress tracker just to improve
your reading experience.

Quotesify is available for download on all major operating systems such as Windows and Mac.

## Quick Start

Before you begin, here's what you need to do:
1. Ensure you have at least `Java 11` installed on your system.
2. Download the latest **Quotesify JAR file** from [here](https://github.com/AY2021S1-CS2113T-T09-3/tp/releases).
3. Open your command line or terminal and navigate into the file directory where you saved the application.
4. Run `java -jar Quotesify.jar` to launch Quotesify.
5. Type a command in the command line and press `ENTER` to execute it.
6. Refer to Features below for details of each command.

## Features 

{Give detailed description of each feature}

### Rating System

Are you having your own opinions about the books you read? You might want to record down your favorites,
so that you can recommend the best books to your friends and fellow book readers.

#### Adding a book rating: `add -r`
Adds a rating to a book.

Format: `add -r RATING_SCORE BOOK_TITLE`

* The book you would like to rate should exist in Quotesify.
* `RATING_SCORE` should be within the range of ONE (1) to FIVE (5).
* Both `RATING_SCORE` and `BOOK_TITLE` fields cannot be left empty.

Example of usage:

`add -r 5 Harry Potter`

#### Listing book ratings: `list -r`
Lists ratings of books.

Format: `list -r [RATING_SCORE]`

* `RATING_SCORE` is **optional**
* `RATING_SCORE` should be within the range of ONE (1) to FIVE (5).

Example of usage:

* `list -r`
* `list -r 5`

#### Deleting a book rating: `delete -r`
Deletes a rating from a book.

Format: `delete -r [BOOK_TITLE]`

* `BOOK_TITLE` field cannot be left empty.

Example of usage:

`delete -r Harry Potter`

#### Editing an existing book rating: `edit -r`
Edits a rating of a book.

Format: `edit -r RATING_SCORE BOOK_TITLE`

* `RATING_SCORE` should be within the range of ONE (1) to FIVE (5).
* Both `RATING_SCORE` and `BOOK_TITLE` fields cannot be left empty.

Example of usage:

`edit -r 5 Harry Potter`

#### Finding an existing book rating: `find -r`
Finds a rating of a book.

Format: `find -r BOOK_TITLE`

* `BOOK_TITLE` field cannot be left empty.

Example of usage:

`find -r Harry Potter`

### Getting help in Quotesify

Forgotten the commands to get things done? You will not be left feeling lost.
Quotesify got you covered with the Help page.

Format: `help`

Example: `help`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

Enter the following command | To do this
--------------------------- | -----------
`add -r RATING_SCORE BOOK_TITLE` | Add rating
`delete -r BOOK_TITLE` | Delete rating
`list -r [/RATING_SCORE]` | List ratings
`edit -r RATING_SCORE BOOK_TITLE` | Edit rating
`find -r BOOK_TITLE` | Find rating
`help` | Show help page
`bye` | Terminate the program

* Words in [] are **OPTIONAL**, and words in **CAPS** are your own input
