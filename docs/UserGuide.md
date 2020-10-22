# User Guide

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
- [Usage](#usage)
  * [Book Management](#book-management)
    * [Adding a book](#adding-a-book-add--b)
    * [Listing all existing book](#listing-all-existing-books-list--b)
    * [Listing books by an Author](#listing-books-by-an-author-list--b)
    * [Finding books by keyword](#finding-books-by-keyword-find--b)
    * [Deleting a book](#deleting-a-book-delete--b)
  * [Quote Management](#quote-management)
    * [Add quotes](#add-quotes-add--q)
    * [List all quotes](#list-all-quotes-list--q)
    * [List quotes from a specific reference](#list-quotes-from-a-specific-reference-list--q)
    * [List quotes from a specific author](#list-quotes-from-a-specific-author-list--q)
    * [List quotes from a specific reference and author](#list-quotes-from-a-specific-reference-and-author-list--q)
    * [Delete a quote](#delete-a-quote-delete--q)
    * [Edit an existing quote](#edit-an-existing-quote-edit--q)
    * [Finding quotes](#finding-quotes-find--q)
  * [Progress Tracker](#progress-tracker)
  * [Category Management](#category-management)
    * [Add categories](#add-categories-add--c)
    * [List categories](#list-categories-list--c)
    * [List items in a category](#list-items-in-a-category-list--c)
    * [Delete categories](#delete-categories-delete--c)
    * [Edit an existing category](#edit-an-existing-category-edit--c)
  * [Rating System for Books](#rating-system)
    * [Adding a book rating](#adding-a-book-rating-add--r)
    * [Listing book ratings](#listing-book-ratings-list--r)
    * [Deleting a book rating](#deleting-a-book-rating-delete--r)
    * [Editing an existing book rating](#editing-an-existing-book-rating-edit--r)
    * [Finding an existing book rating](#finding-an-existing-book-rating-find--r)
- [Getting Help in Quotesify](#getting-help-in-quotesify)
- [FAQ](#faq)
- [Command Summary](#command-summary)
  
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

### Quote Management
Came across an inspiring quote while reading, or a useful productivity tip from an article only to forget it after
some time? Quotesify is here to help you store and track those all inspiring quotes while reminding you about them
so you'll never forget them!  

#### Add quotes: `add -q`
Add a quote to your current list of quotes.

Format: `add -q QUOTE {[/from REFERENCE] | [/by AUTHOR]}`
* You must specify a quote.
* Reference and Author fields are optional, both or either or can be used.

Example of usage: `add -q Life is short, smile while you still have teeth`

Expected outcome:
```
The quote ["Life is short, smile while you still have teeth"] has been added!
```

#### List all quotes: `list -q`
List all existing quotes.

Format: `list -q`

Example of usage: `list -q`

Expected outcome:
```
Here are all your quotes:
1. "Life is short, smile while you still have teeth"
2. "I am your father" - Darth Vadar
3. "That's my spot!" - The Big Bang Theory
4. "So everyone's supposed to sleep every single night now?" - Rick, (Rick and Morty)
```

#### List quotes from a specific reference: `list -q`
List all quotes tagged under the specified reference title.

Format: `list -q /from REFERENCE`

Example of usage: `list -q /from Rick and Morty`

Expected outcome:
```
Here is a list of quotes from Rick and Morty:
"So everyone's supposed to sleep every single night now?" - Rick, (Rick and Morty)

"Wubba Lubba Dub Dub!" - Rick and Morty
```

#### List quotes from a specific author: `list -q`
List all quotes tagged under the specified author.

Format: `list -q /by AUTHOR`

Example of usage: `list -q /by Sheldon Cooper`

Expected outcome:
```
Here is a list of quotes by Sheldon Cooper:
"That's my spot!" - Sheldon Cooper, (The Big Bang Theory)

"I'm not insane, my mother had me tested!" - Sheldon Cooper, (The Big Bang Theory)
```

#### List quotes from a specific reference and author: `list -q`
List all quotes tagged under the specified reference and author.

Format: `list -q /from REFERENCE /by AUTHOR`

Example of usage: `list -q /from Rick and Morty /by Rick`

Expected outcome:
```
Here is a list of quotes from RIck and Morty by Rick:
"So everyone's supposed to sleep every single night now?" - Rick, (Rick and Morty)

"Uncertainty is inherently unsustainable. Eventually, everything either is or isn't" - Rick, (Rick and Morty)
```

#### Delete a quote: `delete -q`
Remove a quote from your current list of quotes.

Format: `delete -q QUOTE_NUMBER`
* You must specify a quote number.
* The specified quote number should exist in Quotesify.

Example of usage: `delete -q 1`

Expected outcome:
```
The quote "Life is short, smile while you still have teeth" has been deleted!
```

#### Edit an existing quote: `edit -q`
Edit an existing quote in your list of quotes.

Format: `edit -q QUOTE_NUMBER /to UPDATED_QUOTE`

Example of usage: `edit -q 1 /to Do you know the muffin man?`

Expected outcome:
```
The quote has been edited from: ["I am your father" - Darth Vadar] to [Do you know the muffin man?]!
```

#### Finding quotes: `find -q`
Finds existing quotes related to the keyword entered.

Format: `find -q KEYWORD`
* You must specify a keyword.

Example of usage: `find -q sleep`

Expected outcome:
```
Here are the results of my search:
"I pretty much spend all day, every day just looking forward to go back to sleep"
"Don't give up on your dreams, keep sleeping!" - Stranger
```

#### Adding reflection to a quote: `add -qr`
Adds reflection or thoughts to an existing quote.

Format: `add -qr QUOTE_NUMBER /reflect REFLECTION`
* You must specify an existing quote number.
* You must include the /reflect tag
* Reflection field should not be empty

Example of usage: `add -qr 1 /reflect I'm stumped, can't seem to find the muffin man anywhere`

Expected outcome:
```
Reflection has been to quote: ["Do you know the muffin man?"]
Reflection: [I'm stumped, can't seem to find the muffin man anywhere]
```

#### Listing reflection of a quote: `list -qr`
Lists reflection of an existing quote.

Format: `list -qr QUOTE_NUMBER`
* You must specify an existing quote number.
* Quote should have a reflection added to it

Example of usage: `list -qr 1`

Expected outcome:
```
Here is the reflection you are looking for!
Quote: ["Do you know the muffin man?"]
Reflection: [I'm stumped, can't seem to find the muffin man anywhere]
```

### Category Management
If you like customising your own list, you can do so by categorising your books and quotes.

#### Add categories: `add -c`
Add one or more categories to a specified book or quote.

Format: `add -c CATEGORIES {[-b BOOK_NUMBER] | [-q QUOTE_NUMBER]}`
* `[-b BOOK_NUMBER]` tag is optional. Use it to specify an existing book.
* `[-b QUOTE_NUMBER]` tag is optional. Use it to specify an existing quote.
* You must specify either a book, quote, or both.
* The specified book or quote should exist in Quotesify.
* Multiple categories should be <u>space separated</u>.

Example of usage: `add -c fantasy -b 1`

Expected outcome:
```
I have tagged category [fantasy] to "Harry Potter"!
```

#### List categories: `list -c`
List all existing categories.

Format: `list -c`

Example of usage: `list -c`

Expected outcome:
```
Here is the list of all categories:
1. action - (2 items)
2. inspirational - (1 items)
3. fantasy - (1 items)
```

#### List items in a category: `list -c`
List all books and quotes tagged under the specified category name.

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

Format: `delete -c CATEGORIES [-b BOOK_NUMBER] [-q QUOTE_NUMBER]`
* `[-b BOOK_NUMBER]` tag is optional. Use it to specify an existing book.
* `[-b QUOTE_NUMBER]` tag is optional. Use it to specify an existing quote.
* The specified book or quote should exist in Quotesify.
* Multiple categories should be <u>space separated</u>.

Example of usage: 

`delete -c fantasy -b 1`

`delete -c fantasy`

Expected outcome:
```
I have removed category [fantasy] from "Harry Potter"!
```

```
I have removed category [fantasy] from all books and quotes!
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

Format: `add -r RATING_SCORE BOOK_TITLE /by AUTHOR`

* The book you would like to rate should exist in Quotesify.
* `RATING_SCORE` should be within the range of ONE (1) to FIVE (5).
* `RATING_SCORE`, `BOOK_TITLE` and `AUTHOR` fields cannot be left empty.

Example of usage: `add -r 5 Harry Potter /by JK Rowling`

Expected outcome:
```
You have just rated [Harry Potter by JK Rowling] 5 star!
```

#### Listing book ratings: `list -r`
Lists ratings of books.

Format: `list -r [RATING_SCORE]`

* `RATING_SCORE` is **optional**
* `RATING_SCORE` should be within the range of ONE (1) to FIVE (5).

Example of usage:
1. `list -r`
2. `list -r 5`

Expected outcome 1:
```
Planning to recommend some books? Here are your rated books!
[Harry Potter] by JK Rowling: 5 star
```

Expected outcome 2:
```
Here are the books you rated as 5 star!
[Harry Potter] by JK Rowling: 5 star
```

#### Deleting a book rating: `delete -r`
Deletes a rating from a book.

Format: `delete -r BOOK_TITLE /by AUTHOR`

* `BOOK_TITLE` and `AUTHOR` fields cannot be left empty.

Example of usage: `delete -r Harry Potter /by JK Rowling`

Expected outcome:
```
Rating for [Harry Potter by JK Rowling] has been deleted!
```

#### Editing an existing book rating: `edit -r`
Edits a rating of a book.

Format: `edit -r RATING_SCORE BOOK_TITLE /by AUTHOR`

* `RATING_SCORE` should be within the range of ONE (1) to FIVE (5).
* `RATING_SCORE`, `BOOK_TITLE` and `AUTHOR` fields cannot be left empty.

Example of usage: `edit -r 4 Harry Potter /by JK Rowling`

Expected outcome:
```
Ratings for [Harry Potter by JK Rowling] has been updated to 4 star!
```

#### Finding an existing book rating: `find -r`
Finds a rating of a book.

Format: `find -r BOOK_TITLE /by AUTHOR`

* `BOOK_TITLE` and `AUTHOR` fields cannot be left empty.

Example of usage: `find -r Harry Potter /by JK Rowling`

Expected outcome:
```
Here is your rating for [Harry Potter by JK Rowling]!
[Harry Potter] by JK Rowling: 4 star
```

### Getting help in Quotesify

Forgotten the commands to get things done? You will not be left feeling lost.
Quotesify got you covered with the Help page.

Format: `help`

Example of usage: `help`

Expected outcome:
```
Feeling stuck? Well here are the things you can do with Quotesify v1.0:

1. Book Management
Add book: add -b BOOK_TITLE /by AUTHOR
Delete book: delete -b BOOK_TITLE /by AUTHOR
List books: list -b [/by AUTHOR]

2. Quote Management
Add quote: add -q QUOTE [/from BOOK_TITLE] [/by AUTHOR]
Delete quote:delete -q QUOTE_NUMBER
List quotes: list -q [/by AUTHOR] [/from BOOK_TITLE]
Add reflection to quote: add -qr QUOTE_NUM /reflect REFLECTION

3a. Bookmark Tracker
Add bookmark: bookmark -b BOOK_TITLE /pg PAGE_NUMBER
Delete bookmark: delete -bm BOOK_TITLE
List bookmarks: list -bm
Update bookmark: bookmark -b BOOK_TITLE /pg PAGE_NUMBER

3b. Task Tracker
Add task: add -t TASK /by DEADLINE
Delete task: delete -t TASK_NUMBER
List tasks: list -t
Mark task as done: done -t TASK_NUMBER

4. Category Management
Add category: add -c CATEGORY {[-b BOOK_TITLE] | [-q QUOTE_NUMBER]}
Delete category: delete -c CATEGORY {[-b BOOK_TITLE] | [-q QUOTE_NUMBER]}
List categories: list -c [CATEGORY]
Edit category: edit -c OLD_CATEGORY NEW_CATEGORY

5. Rating System
Add rating: add -r RATING_SCORE BOOK_TITLE /by AUTHOR
Delete rating: delete -r BOOK_TITLE /by AUTHOR
List ratings: list -r [RATING_SCORE]
Edit rating: edit -r RATING_SCORE BOOK_TITLE /by AUTHOR
Find rating: find -r BOOK_TITLE /by AUTHOR

Other useful commands
Show this help page: help
Quit Quotesify: bye

Remember: words in [] are optional, and words in CAPS are your own input
Hope this helps!

~ Your friends from Quotesify
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

Enter the following command | To do this
--------------------------- | -----------
`add -r RATING_SCORE BOOK_TITLE /by AUTHOR` | Add rating
`delete -r BOOK_TITLE /by AUTHOR` | Delete rating
`list -r [/RATING_SCORE]` | List ratings
`edit -r RATING_SCORE BOOK_TITLE /by AUTHOR` | Edit rating
`find -r BOOK_TITLE /by AUTHOR` | Find rating
`help` | Show help page
`bye` | Terminate the program

* Words in [] are **OPTIONAL**, and words in **CAPS** are your own input
