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

## Usage

### Book Management

Do you have books you are currently reading and want to keep track of them? 
Or do you have a list of books you wish to read in the future but could never remember them? 
You can add books to your booklist so that you can refer to it at any time. 

#### Adding a book: `add -b`
You can add a book to your current booklist. 

Format: `add -b BOOK_TITLE /by AUTHOR`

* You must specify both the title and the author of the book. 

Example of usage:

`add -b Harry Potter /by JK Rowling`


Expected outcome:

`The book [Harry Potter by JK Rowling] has been added!`

#### Listing all existing books: `list -b`
You can list all books currently in your booklist. 

Format: `list -b`

Example of usage:
`list -b`

Expected outcome:
~~~
Here is a list of all books:
1. Harry Potter by JK Rowling
2. The Lion, the Witch and the Wardrobe by CS Lewis
3. Becoming by Michelle Obama
4. The Chronicles of Narnia by CS Lewis
~~~

#### Listing books by an Author: `list -b`
You can list all books currently in your booklist with the same author. 

Format: `list -b /by AUTHOR`

* You must specify an existing author name
* You must put `/by` before the author name

Example of usage:
`list -b /by CS Lewis`

Expected outcome:
~~~
Here is a list of books by CS Lewis:
1. The Lion, the Witch and the Wardrobe by CS Lewis
2. The Chronicles of Narnia by CS Lewis
~~~

#### Finding books by keyword: `find -b`
Can't recall the book title you want in your long list of books? You can find the book using a keyword.

Format: `find -b KEYWORD`

Example of usage: 
`find -b the`

Expected outcome:
~~~
Here is a list of books with the keyword "the":
1. The Lion, the Witch and the Wardrobe by CS Lewis
2. The Chronicles of Narnia by CS Lewis
~~~

#### Deleting a book: `delete -b`
Don't need a book anymore? You can delete the book permanently. 

Format: `delete -b BOOK_NUMBER`

* `BOOK_NUMBER` refers to the index you see when you list ALL books.
* Book number specified must be less than the total number of books.

Example of usage:
`delete -b 1`

Expected outcome:
~~~
The book [Harry Potter by JK Rowling] has been deleted!
~~~ 

### Category Management

#### Add categories: `add -c`
Add one or more categories to a specified book or quote.

Format: `add -c CATEGORIES {[-b BOOK_NUMBER] | [-q QUOTE_NUMBER]}`
* You must specify either a book, quote, or both.
* The specified book or quote should exist in Quotesify.
* Multiple categories should be <u>space separated</u>.

Example of usage: `add -c fantasy -b 1`

Expected outcome:
```
I have tagged [fantasy] category to "Harry Potter"!
```

#### List categories: `list -c`
List all existing categories.

Format: `list -c`

Example of usage: `list -c`

Expected outcome:
```
Here is the list of all categories:
1. lol - (1 items)
2. action - (2 items)
3. inspirational - (1 items)
4. fantasy - (1 items)
```

#### List a specific category: `list -c`
List all existing categories.

Format: `list -c CATEGORY`

Example of usage: `list -c fantasy`

Expected outcome:
```
Here are the list of items tagged as [fantasy]:
BOOKS:
1. Harry Potter by JK Rowling
```

#### Delete categories: `delete -c`
Remove one or more categories from a specified book or quote.

Format: `delete -c CATEGORIES {[-b BOOK_NUMBER] | [-q QUOTE_NUMBER]}`
* You must specify either a book, quote, or both.
* The specified book or quote should exist in Quotesify.
* Multiple categories should be <u>space separated</u>.

Example of usage: `delete -c fantasy -b 1`

Expected outcome:
```
I have removed [fantasy] category from "Harry Potter"!
```

#### Edit an existing category: `edit -c`
Edit an existing category name.

Format: `edit -c OLD_CATEGORY /to NEW_CATEGORY`

Example of usage: `edit -c fantasy /to romance`

Expected outcome:
```
The category has been changed from [fantasy] to [romance]!
```

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
