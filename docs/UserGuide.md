# User Guide

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  * [1. Book Management](#1-book-management)
  * [2. Quote Management](#2-quote-management)
  * [3. Progress Tracker](#3-progress-tracker)
  * [4. Category Management](#4-category-management)
  * [5. Rating System for books](#5-rating-system-for-books)
- [Usage](#usage)
  * [Book Management](#book-management)
    * [Adding a book](#adding-a-book-add--b)
    * [Completing a book](#completing-a-book-done--b)
    * [Listing all existing book](#listing-all-existing-books-list--b)
    * [Listing book details](#listing-book-details-list--b)
    * [Listing books by an Author](#listing-books-by-an-author-list--b)
    * [Listing books by completion](#listing-books-by-completion-list--b)
    * [Finding books by keyword](#finding-books-by-keyword-find--b)
    * [Deleting a book](#deleting-a-book-delete--b)
  * [Quote Management](#quote-management)
    * [Add quotes](#adding-a-quote-add--q)
    * [List all quotes](#listing-all-quotes-list--q)
    * [List quotes from a specific reference](#listing-quotes-from-a-specific-reference-list--q)
    * [List quotes from a specific author](#listing-quotes-from-a-specific-author-list--q)
    * [List quotes from a specific reference and author](#listing-quotes-from-a-specific-reference-and-author-list--q)
    * [Edit an existing quote](#editing-an-existing-quote-edit--q)
    * [Delete a quote](#deleting-a-quote-delete--q)
    * [Finding quotes](#finding-quotes-find--q)
    * [Add quote reflection](#adding-reflection-to-a-quote-add--qr)
    * [List quote reflection](#listing-reflection-of-a-quote-list--qr)
    * [Edit an existing quote reflection](#editing-reflection-of-a-quote-edit--qr)
    * [Delete a quote reflection](#deleting-reflection-of-a-quote-delete--qr)
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

### 1. Book Management
### 2. Quote Management
### 3. Progress Tracker
### 4. Category Management
Category Management in Quotesify allows you to create, list, edit and delete categories at your free will.
With the categories you have created, you can simply tag them to any book or quote and list all of them in a categorised fashion.
Whenever you have second thoughts about a given category name, simply change the name or remove it whilst Quotesify helps you
update the change across all books and quotes.

If you are someone who likes being neat, organised and efficient, this feature is for you!

### 5. Rating System for books
The rating system in Quotesify allows you to rate your books from 1 to 5 star. With this system, you will not forget
how you feel about the books you read, and will always be ready to recommend a book to a fellow book reader.
Ratings can be edited whenever you change your mind about the book, or be deleted when you are not interested
in that rating anymore. Ratings can also be listed according to their ratings - with your favourites at the top - as
well as be searched based on the book title and author.

With this rating system, you will never forget how you feel about the books that you have read!

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
~~~
The book [Harry Potter by JK Rowling] has been added!
~~~

#### Completing a book: `done -b`
Finished reading a book? You can now mark your book as done.

Format: `done -b BOOK_NUMBER`

* `BOOK_NUMBER` refers to the index you see when you list ALL books.
* The book number must be an existing number in the list when you list ALL books.

Example of usage:
 `done -b 2`

Expected outcome:
~~~
The book [Harry Potter by JK Rowling] has been marked as done!
~~~

#### Listing all existing books: `list -b`
Want to see what books you have in your reading list? You can list all books currently in your booklist. 

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

#### Listing book details: `list -b`
Want to check a book's details? You can list the details of a book by specifying the book number.

Format: `list -b BOOK_NUMBER`

* `BOOK_NUMBER` refers to the index you see when you list ALL books.
* The book number must be an existing number in the list when you list ALL books.

Example of usage:
`list -b 2`

Expected outcome:

Case 1: When the book has not been rated and not completed.
~~~
Here are the details of your book:
Title: Harry Potter
Author: JK Rowling
Categories:
There are no categories created!
~~~

Case 2: When the book has been rated but not completed.
~~~
Here are the details of your book:
Title: Harry Potter
Author: JK Rowling
Categories:
There are no categories created!
Rating: 5
~~~

Case 3: When the book has not been rated but completed.
~~~
Here are the details of your book:
[Completed]
Title: Harry Potter 
Author: JK Rowling
Categories:
There are no categories created!
~~~

Case 4: When the book has been rated and completed.
~~~
Here are the details of your book:
[Completed]
Title: Harry Potter
Author: JK Rowling
Categories:
There are no categories created!
Rating: 5
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

#### Listing books by completion: `list -b`
Only want to see the books you have finished or not finished? You can do that using the command below.

##### List done books
Format: `list -b done`

Example of usage:
`list -b done`

Expected outcome:
~~~
Here are the books you have completed:
1. [v] Harry Potter by JK Rowling
2. [v] Harry Potter 2 by JK Rowling
~~~

##### List done books
Format: `list -b undone`

Example of usage:
`list -b undone`

Expected outcome:
~~~
Here are the books you have yet to complete:
1. [x] Harry Potter by JK Rowling
2. [x] Harry Potter 2 by JK Rowling
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
* The book number must be an existing number in the list when you list ALL books.

Example of usage:
`delete -b 1`

Expected outcome:
~~~
The book [Harry Potter by JK Rowling] has been deleted!
~~~ 

#### Editing an existing book: `edit -b`
Accidentally typed in the wrong book title? You can edit your book titles. 

Format: `edit -b BOOK_NUMBER /to NEW_TITLE`

* `BOOK_NUMBER` refers to the index you see when you list ALL books.
* The book number must be an existing number in the list when you list ALL books.
* `/to` flag must be typed before the new title

Example of usage:
`edit -b 2 /to Harry Potter 2`

Expected outcome:
~~~
The book has been edited from [Harry Potter] to [Harry Potter 2]!
~~~

### Quote Management
Came across an inspiring quote while reading, or a useful productivity tip from an article only to forget it after
some time? Quotesify is here to help you store and track those all inspiring quotes while reminding you about them
so you'll never forget them!  

#### Adding a quote: `add -q`
Adds a quote to your current list of quotes.

Format: `add -q QUOTE {[/by AUTHOR] | [/from REFERENCE]}`

* The quote field should not be empty.
* Reference and Author fields are optional, both or either one can be used.
* Tags should not be used without their relevant data.

Example of usage: 

1. `add -q life is short, smile while you still have teeth`
2. `add -q luke, i am your father /by darth vader`
3. `add -q get schwifty! /from rick and morty`
4. `add -q so everyone’s supposed to sleep every single night now? /from rick and morty /by rick`

Expected outcome 1:
```
The quote ["life is short, smile while you still have teeth"] has been added!
```

Expected outcome 2:
```
The quote ["luke, i am your father" - by darth vader] has been added!
```

Expected outcome 3:
```
The quote ["get schwifty!" - from rick and morty] has been added!
```

Expected outcome 4:
```
The quote ["so everyone’s supposed to sleep every single night now?" - by rick, (from rick and morty)] has been added!
```

#### Listing all quotes: `list -q`
Lists all existing quotes.

Format: `list -q`

Example of usage: `list -q`

Expected outcome:
```
Here are all your quotes:
1. "life is short, smile while you still have teeth"
2. "luke, i am your father" - by darth vadar
3. "get schwifty!" - from rick and morty
4. "so everyone’s supposed to sleep every single night now?" - by rick, (from rick and morty)
```

#### Listing quotes from a specific reference: `list -q`
Lists all quotes tagged under the specified reference title.

Format: `list -q /from REFERENCE`

Example of usage: `list -q /from Rick and Morty`

Expected outcome:
```
Here is a list of quotes from Rick and Morty:
"get schwifty!" - from rick and morty
"so everyone’s supposed to sleep every single night now?" - by rick, (from rick and morty)
```

#### Listing quotes from a specific author: `list -q`
Lists all quotes tagged under the specified author.

Format: `list -q /by AUTHOR`

Example of usage: `list -q /by darth vader`

Expected outcome:
```
Here is a list of quotes by darth vader:
"luke, i am your father" - by darth vadar
```

#### Listing quotes from a specific reference and author: `list -q`
Lists all quotes tagged under the specified reference and author.

Format: `list -q /by AUTHOR /from REFERENCE`

Example of usage: `list -q /by rick /from rick and morty`

Expected outcome:
```
Here is a list of quotes from rick and morty by rick:
"so everyone’s supposed to sleep every single night now?" - by rick, (from rick and morty)
```

#### Editing an existing quote: `edit -q`
Edits an existing quote from your list of quotes.

Format: `edit -q QUOTE_NUMBER /to UPDATED_QUOTE`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.
* Format of quote follows adding of quote above.

Example of usage: `edit -q 2 /to no, i am your mummy /by darth vader`

Expected outcome:
```
The quote has been edited from: ["luke, i am your father" - by darth vadar] to ["no, i am your mummy" - by darth vadar]!
```

#### Deleting a quote: `delete -q`
Removes a quote from your current list of quotes.

Format: `delete -q QUOTE_NUMBER`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.

Example of usage: `delete -q 1`

Expected outcome:
```
The quote "life is short, smile while you still have teeth" has been deleted!
```

#### Finding quotes: `find -q`
Finds existing quotes related to a keyword.

Format: `find -q KEYWORD`

* Keyword field should not be empty.

Example of usage: `find -q sleep`

Expected outcome:
```
Here are the results of my search:
"i pretty much spend all day, every day just looking forward to go back to sleep"
"don't give up on your dreams, keep sleeping!" - by stranger
```

#### Adding reflection to a quote: `add -qr`
Adds your reflection to an existing quote.

Format: `add -qr QUOTE_NUMBER /reflect REFLECTION`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.
* Reflect tag and reflection field should not be empty.

Example of usage: `add -qr 1 /reflect No, that’s not true. It’s impossible!`

Expected outcome:
```
Reflection has been added to quote: "no, i am your father" - by darth vader [R]
Reflection: No, that’s not true. It’s impossible!
```

#### Listing reflection of a quote: `list -qr`
Lists reflection of an existing quote.

Format: `list -qr QUOTE_NUMBER`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.
* Quote should have a reflection added to it

Example of usage: `list -qr 1`

Expected outcome:
```
Here is the reflection you are looking for!
Quote: "no, i am your father" - by darth vader [R]
Reflection: No, that’s not true. It’s impossible!
```

#### Editing reflection of a quote: `edit -qr`
Edits the reflection of an existing quote.

Format: `edit -qr QUOTE_NUMBER /to UPDATED_REFLECTION`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.
* Quote should have a reflection added to it

Example of usage: `edit -qr 1 /to Who is Yoda’s daddy?`

Expected outcome:
```
Reflection has been updated!
Quote: "no, i am your father" - by darth vader [R]
Reflection: Who is Yoda’s daddy?
```

#### Deleting reflection of a quote: `delete -qr`
Deletes reflection of an existing quote.

Format: `delete -qr QUOTE_NUMBER`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.
* Quote should have a reflection added to it

Example of usage: `delete -qr 1`

Expected outcome:
```
Reflection for the quote "no, i am your father" has been deleted!
```

### Progress Tracker
If you want to save your reading progress, you can do so by adding bookmarks to the books you read.
If you want to set a goal on reading, you can do so by adding tasks (with deadlines) to your todo list. 

#### Add bookmarks: `bookmark -b /pg`
Add the only one bookmark to any book that has been already added.

Format: `bookmark -b BOOK_NUMBER /pg PAGE_NUMBER`
* book number refers to the index of the book shown in the book list.
* You must specify both book number and page number.

Example of usage: `bookmark -b 3 /pg 109`

Expected outcome:
```
The bookmark ["Harry Potter" at page: 109] has been added!
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
Feeling stuck? Well here are the things you can do with Quotesify v2.0:

1. Book Management
Add book: add -b BOOK_TITLE /by AUTHOR
Delete book: delete -b BOOK_TITLE /by AUTHOR
List books: list -b [/by AUTHOR]

2. Quote Management
Add quote: add -q QUOTE [/from BOOK_TITLE] [/by AUTHOR]
Delete quote:delete -q QUOTE_NUMBER
List quotes: list -q [/by AUTHOR] [/from BOOK_TITLE]
Edit quote: edit -q QUOTE_NUMBER /to QUOTE [/by AUTHOR] [/from BOOK_TITLE]
Find quotes: find -q KEYWORD
Add quote reflection: add -qr QUOTE_NUMBER /reflect REFLECTION
Delete quote reflection:delete -qr QUOTE_NUMBER
List quotes reflection: list -qr QUOTE_NUMBER
Edit quote reflection: edit -qr QUOTE_NUMBER /to UPDATED_REFLECTION

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
List categories: list -c
List items in a category: list -c CATEGORY
Edit category: edit -c OLD_CATEGORY /to NEW_CATEGORY

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
`add -b BOOK_TITLE /by AUTHOR` | Add book
`add -q QUOTE [/from BOOK_TITLE] [/by AUTHOR]` | Add quote
`add -qr QUOTE_NUMBER /reflect REFLECTION` | Add quote reflection
`add -t TASK /by DEADLINE` | Add task
`add -c CATEGORIES {[-b BOOK_TITLE] [-q QUOTE_NUMBER]}` | Add categories
`add -r RATING_SCORE BOOK_TITLE /by AUTHOR` | Add rating
`list -b [/by AUTHOR]` | List books
`list -b BOOK_NUMBER` | List book detail
`list -b done/undone` | List books by completion
`list -q [/by AUTHOR] [/from BOOK_TITLE]` | List quotes
`list -qr QUOTE_NUMBER` | List quote reflection
`list -bm` | List bookmarks
`list -t` | List tasks
`list -c [CATEGORY]` | List all categories / List items in a category
`list -r [/RATING_SCORE]` | List ratings
`edit -b BOOK_NUMBER /to NEW_TITLE` | Edit book title
`edit -q QUOTE_NUMBER /to QUOTE [/by AUTHOR] [/from BOOK_TITLE]` | Edit quote
`edit -qr QUOTE_NUMBER /to UPDATED_REFLECTION` | Edit quote reflection
`edit -c OLD_CATEGORY /to NEW_CATEGORY` | Edit a category name
`edit -r RATING_SCORE BOOK_TITLE /by AUTHOR` | Edit rating
`delete -b BOOK_NUMBER` | Delete book
`delete -q QUOTE_NUMBER` | Delete quote
`delete -qr QUOTE_NUMBER` | Delete quote reflection
`delete -bm BOOK_TITLE` | Delete bookmark
`delete -t TASK_NUMBER` | Delete task
`delete -c CATEGORIES {[-b BOOK_TITLE] [-q QUOTE_NUMBER]}` | Delete categories
`delete -r BOOK_TITLE /by AUTHOR` | Delete rating
`find -b KEYWORD` | Find books
`find -q KEYWORD` | Find quotes
`find -r BOOK_TITLE /by AUTHOR` | Find rating
`bookmark -b BOOK_TITLE /pg PAGE_NUMBER` | Add bookmark
`bookmark -b BOOK_TITLE /pg PAGE_NUMBER` | Update bookmark
`done -b BOOK_NUMBER` | Mark book as complete
`done -t TASK_NUMBER` | Mark task as done
`help` | Show help page
`bye` | Terminate the program

* Words in [] are **OPTIONAL**, and words in **CAPS** are your own input
