# User Guide

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  * [Book Management](#1-book-management)
  * [Quote Management](#2-quote-management)
  * [Progress Tracker](#3-progress-tracker)
  * [Category Management](#4-category-management)
  * [Rating System for books](#5-rating-system-for-books)
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
    * [Find an existing category](#find-an-existing-category-find--c)
  * [Rating System for Books](#rating-system)
    * [Adding a book rating](#adding-a-book-rating-add--r)
    * [Listing book ratings](#listing-book-ratings-list--r)
    * [Deleting a book rating](#deleting-a-book-rating-delete--r)
    * [Editing an existing book rating](#editing-an-existing-book-rating-edit--r)
    * [Finding an existing book rating](#finding-an-existing-book-rating-find--r)
- [Getting Help in Quotesify](#getting-help-in-quotesify)
- [FAQs](#faqs)
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
Progress Tracker in Quotesify allows you to add, update, list and delete bookmarks for the books you add. 
It also allows you to add, mark as done, list and delete tasks in the todo list. 

With the bookmarks you have created, you can record your reading progress and trace to the page you stopped at quickly. 
WIth the tasks in your todo list, you can set reminders for various activities 
and easily find the most urgent task in the list.

If you hate flipping pages to find the page to continue reading, 
or want to develop a reading habit by setting many goals of readling, this feature is prepared for you!

### 4. Category Management
Category Management in Quotesify allows you to create, list, edit and delete categories at your free will.
With the categories you have created, you can simply tag them to any book or quote and list all of them in a categorised fashion.
Whenever you have second thoughts about a given category name, simply change the name or remove it whilst Quotesify helps you
update the change across all books and quotes.

If you are someone who likes being neat, organised and efficient, this feature is for you!

### 5. Rating System for books
The rating system in Quotesify allows you to rate your books from 1 to 5 star. With this system, you will not forget
how you feel about the books you read, and will always be ready to recommend a book to your fellow book readers.
Ratings can be edited whenever you change your mind about the book, or be deleted when you are not interested
in that rating anymore. Ratings can also be listed according to their rating score - with your favourites at the top -
as well as be searched based on the book title and author.

With Quotesify's rating system, remembering the books you love will never be an issue for you!

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

1. `add -q Life is short, smile while you still have teeth`
2. `add -q Luke, I am your father /by Darth Vader`
3. `add -q Get schwifty! /from Rick and Morty`
4. `add -q So everyone’s supposed to sleep every single night now? /from Rick and Morty /by Rick`

Expected outcome 1:
```
The quote ["Life is short, smile while you still have teeth"] has been added!
```

Expected outcome 2:
```
The quote ["Luke, I am your father" - by Darth Vader] has been added!
```

Expected outcome 3:
```
The quote ["Get schwifty!" - from Rick and Morty] has been added!
```

Expected outcome 4:
```
The quote ["So everyone’s supposed to sleep every single night now?" - by Rick, (from Rick and Morty)] has been added!
```

#### Listing all quotes: `list -q`
Lists all existing quotes.

Format: `list -q`

Example of usage: `list -q`

Expected outcome:
```
Here are all your quotes:
1. "Life is short, smile while you still have teeth"
2. "Luke, I am your father" - by Darth Vader
3. "Get schwifty!" - from Rick and Morty
4. "So everyone’s supposed to sleep every single night now?" - by Rick, (from Rick and Morty)
```

#### Listing quotes from a specific reference: `list -q`
Lists all quotes tagged under the specified reference title.

Format: `list -q /from REFERENCE`

Example of usage: `list -q /from rick and morty`

Expected outcome:
```
Here is a list of quotes from rick and morty:
"Get schwifty!" - from Rick and Morty
"So everyone’s supposed to sleep every single night now?" - by Rick, (from Rick and Morty)
```

#### Listing quotes from a specific author: `list -q`
Lists all quotes tagged under the specified author.

Format: `list -q /by AUTHOR`

Example of usage: `list -q /by darth vader`

Expected outcome:
```
Here is a list of quotes by darth vader:
"Luke, I am your father" - by Darth Vader
```

#### Listing quotes from a specific reference and author: `list -q`
Lists all quotes tagged under the specified reference and author.

Format: `list -q /by AUTHOR /from REFERENCE`

Example of usage: `list -q /by rick /from rick and morty`

Expected outcome:
```
Here is a list of quotes from rick and morty by rick:
"So everyone’s supposed to sleep every single night now?" - by Rick, (from Rick and Morty)
```

#### Editing an existing quote: `edit -q`
Edits an existing quote from your list of quotes.

Format: `edit -q QUOTE_NUMBER /to UPDATED_QUOTE`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.
* Format of quote follows adding of quote above.

Example of usage: `edit -q 2 /to No, I am your mummy /by Darth Vader`

Expected outcome:
```
The quote has been edited from: ["Luke, I am your father" - by Darth Vader] to ["No, I am your mummy" - by Darth Vader]!
```

#### Deleting a quote: `delete -q`
Removes a quote from your current list of quotes.

Format: `delete -q QUOTE_NUMBER`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.

Example of usage: `delete -q 1`

Expected outcome:
```
The quote "Life is short, smile while you still have teeth" has been deleted!
```

#### Finding quotes: `find -q`
Finds existing quotes related to a keyword.

Format: `find -q KEYWORD`

* Keyword field should not be empty.

Example of usage: `find -q sleep`

Expected outcome:
```
Here are the results of my search:
"I pretty much spend all day, every day just looking forward to go back to sleep"
"Don't give up on your dreams, keep sleeping!" - by Stranger
```

#### Adding reflection to a quote: `add -qr`
Adds your reflection to an existing quote.

Format: `add -qr QUOTE_NUMBER /reflect REFLECTION`

* Quote number field should not be empty.
* The specified quote should exist in Quotesify.
* Reflect tag and reflection field should not be empty.

Example of usage: `add -qr 1 /reflect No, That’s not true. It’s impossible!`

Expected outcome:
```
Reflection has been added to quote: "No, I am your mummy" - by Darth Vader [R]
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
Quote: "No, I am your mummy" - by Darth Vader [R]
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
Quote: "No, I am your mummy" - by Darth Vader [R]
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
Reflection for the quote ["No, I am your mummy" - by Darth Vader] has been deleted!
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

#### List bookmarks: `list -bm`
List all bookmarks that you have already added.

Format: `list -bm`
* You mist specify the bookmark number.

Example of usage: `list -bm`

Expected outcome:
```
Here is the lsit of all bookmark(s) recorded:
1. "hp" at page: 123
2. "Harry Potter" at page: 56
```


#### Delete bookmarks: `delete -bm`
Delete the bookmark that you have already added.

Format: `delete -bm BOOKMARK_NUMBER`
* You mist specify the bookmark number.

Example of usage: `delete -bm 1`

Expected outcome:
```
The bookmark ["hp" at page: 123] has been removed!
```


#### Edit bookmarks: `bookmark -b`
Update the bookmark that you have already added. 
The command is the same as adding a new bookmark.

Format: `bookmark -b BOOK_NUMBER /pg PAGE_NUMBER`
* You must add the bookmark of the same book before so as to update it.
* * You must specify both book number and page number.

Example of usage: `bookmark -b 3 /pg 185`

Expected outcome:
```
The bookmark ["Harry Potter" at page: 185] has been updated!
```


#### Add tasks: `add -t /by`

Format: 
1. `add -t TASK_NAME /by DEADLINE`
2. `add -t TASK_NAME /by YYYY-MM-DD`
3. `add -t TASK_NAME`
* You must specify the task name.
* If you want to use YYYY-MM-DD format, remember the date must exist in real life. Fake date such as `3001-02-30`
 will be rejected by the system and treated as an unformatted deadline (plain text).

Example of usage:
1. add a task with an unformatted deadline: `add -t return Harry Potter /by tmr 2pm`

Expected outcome:
```
The task [[x] return Harry Potter (by: tmr 2pm)] has been added!
```

2. add a task with a formatted deadline: `add -t return Harry Potter /by 2020-10-24`

Expected outcome:
```
The task [[x] return Harry Potter (by: Oct 24 2020, SATURDAY)] has been added!
```

3. add a task without any deadline: `add -t return Harry Potter`

Expected outcome:
```
The task [[x] return Harry Potter (by: not specified)] has been added!
```


#### List tasks: `list -t`
If successful, you will see a full list of todo tasks added by you.
If some tasks have formatted deadlines (YYYY-MM-DD), they will be arranged in ascending order of date 
and displayed ahead of those with unformatted deadline. 

Format: `list -t`

Example of usage: `list -t`

Expected outcome:
```
Here is the list of all task(s) recorded:
1.[x] task4 (by: Sep 13 2020, Sunday)
2.[x] task3 (by: Oct 24 2020, SATURDAY)
3.[x] return Harry Potter (by: Oct 25 2020, SUNDAY)
4.[x] task 1 (by: 1908008)
5.[x] task 2 (by: 1999-12-34)
```


#### Mark tasks as Done: `done -t`

Format: `done -t TASK_NUMBER`
* The task Number must be an integer and smaller than the total number of tasks you added.

Example of usage: `done -t 1`

Expected outcome:
```
The task [[v] return Harry Potter (by: tmr 2pm)] has been marked as done!
```

#### Delete tasks: `delete -t`

Format: `delete -t TASK_NUMBER`
* The task Number must be an integer and smaller than the total number of tasks you added.

Example of usage: `delete -t 1`

Expected outcome:
```
The task [[v] return Harry Potter (by: tmr 2pm)] has been deleted!
```


### Category Management
If you like customising your own list, you can do so by categorising your books and quotes.

Note: All categories will be stored in **lower-case** format.

#### Add categories: `add -c`
Add one or more categories to a specified book or quote.

Format: `add -c CATEGORIES {[-b BOOK_NUMBER] | [-q QUOTE_NUMBER]}`
* `[-b BOOK_NUMBER]` tag is optional. Use it to specify an existing book.
* `[-b QUOTE_NUMBER]` tag is optional. Use it to specify an existing quote.
* You must specify either a book, quote, or both.
* The specified book or quote should exist in Quotesify.
* Multiple categories should be **space separated**.
* All categories specified will be converted and stored in **lower-case** format.

Example of usage: 

1. `add -c fantasy -b 1`
2. `add -c inspirational -q 1`

Expected outcome 1:
```
I have tagged category [fantasy] to the book [Harry Potter]!
```

Expected outcome 2:
```
I have tagged category [inspirational] to the quote ["Life is Great"]!
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

1. `delete -c fantasy`
2. `delete -c fantasy -b 1`
3. `delete -c inspirational -q 1`


Expected outcome 1:
```
I have removed category [fantasy] from all books and quotes!
```

Expected outcome 2:
```
I have removed category [fantasy] from the book [Harry Potter]!
```

Expected outcome 3:
```
I have removed category [inspirational] from the quote ["Life is Great"]!
```

#### Edit an existing category: `edit -c`
Edit an existing category name.

Format: `edit -c OLD_CATEGORY /to NEW_CATEGORY`

Example of usage: `edit -c fantasy /to romance`

Expected outcome:
```
The category has been changed from [fantasy] to [romance]!
```

#### Find an existing category: `find -c`
Find existing categories related to a keyword.

Format: `find -c KEYWORD`

Example of usage: `find -c man`

Expected outcome:
```
Here is a list of categories with the keyword "man":
1. romance - (1 items)
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
Mark book as complete: done -b BOOK_NUMBER
Delete book: delete -b BOOK_NUMBER
Edit book: edit -b BOOK_NUMBER /to NEW_TITLE
List books: list -b [/by AUTHOR]
List book details: list -b BOOK_NUMBER
List books by completion: list -b done/undone

2. Quote Management
Add quote: add -q QUOTE [/from BOOK_TITLE] [/by AUTHOR]
Delete quote:delete -q QUOTE_NUMBER
List quotes: list -q [/by AUTHOR] [/from BOOK_TITLE]
Edit quote: edit -q QUOTE_NUMBER /to NEW_QUOTE [/by AUTHOR] [/from BOOK_TITLE]
Find quote: find -q QUOTE_NUMBER
Add quote reflection: add -qr QUOTE_NUMBER /reflect REFLECTION
Delete quote reflection:delete -qr QUOTE_NUMBER
Edit quote reflection: edit -qr QUOTE_NUMBER /to UPDATED_REFLECTION
List quote reflection: list -qr QUOTE_NUMBER

3a. Bookmark Tracker
Add bookmark: bookmark -b BOOK_TITLE /pg PAGE_NUMBER
Delete bookmark: delete -bm BOOK_NUMBER
List bookmarks: list -bm
Update bookmark: bookmark -b BOOK_NUMBER /pg PAGE_NUMBER

3b. Task Tracker
Add task: add -t TASK /by DEADLINE
Delete task: delete -t TASK_NUMBER
List tasks: list -t
Mark task as done: done -t TASK_NUMBER

4. Category Management
Add category: add -c CATEGORY {[-b BOOK_TITLE] | [-q QUOTE_NUMBER]}
Delete category: delete -c CATEGORY {[-b BOOK_TITLE] | [-q QUOTE_NUMBER]}
List categories: list -c [CATEGORY]
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

## FAQs

**Q: How do I transfer my data to another computer?** 

**A**: Launch Quotesify on your other computer and exit the program. Copy the save file located in data/quotesify.json 
on your current computer and replace it with the save file on your other computer. Launch Quotesify on your other 
computer again and the saved data should load.


**Q: What is a JAR file? Why do I need it?** 

**A**: A JAR (Java ARchive) file is a container file that holds other smaller files,  similar to a Zip file. JAR files 
are practical as it groups everything a Java program requires to run allowing others to run the program with a single 
file.


**Q: What if I run into bugs while using Quotesify?**

**A**: Please head over [here](https://github.com/AY2021S1-CS2113T-T09-3/tp/issues) and create a new issue tracker with a 
description of the bug. Our dedicated team of developers are always on the clock squashing any bugs found! 
Alternatively, you can also reach us at *contact@quotesify.com*!


**Q: I have a suggestion for Quotesify, what should I do?** 

**A**: The team at Quotesify is constantly seeking to improve and better the experience for our users! If you have a 
suggestion for Quotesify, please do not hesitate to reach us at *contact@quotesify.com*!

## Command Summary

Enter the following command | To do this
--------------------------- | -----------
`add -b BOOK_TITLE /by AUTHOR` | Add book
`add -q QUOTE [/from BOOK_TITLE] [/by AUTHOR]` | Add quote
`add -qr QUOTE_NUMBER /reflect REFLECTION` | Add quote reflection
`add -t TASK /by DEADLINE` | Add task
`add -c CATEGORIES {[-b BOOK_TITLE] [-q QUOTE_NUMBER]}` | Add categories
`add -r RATING_SCORE BOOK_TITLE /by AUTHOR` | Add rating
`bookmark -b BOOK_TITLE /pg PAGE_NUMBER` | Add bookmark
`list -b [/by AUTHOR]` | List books
`list -b BOOK_NUMBER` | List book details
`list -b done/undone` | List books by completion
`list -q [/by AUTHOR] [/from BOOK_TITLE]` | List quotes
`list -qr QUOTE_NUMBER` | List quote reflection
`list -bm` | List bookmarks
`list -t` | List tasks
`list -c [CATEGORY]` | List all categories / List items in a category
`list -r [RATING_SCORE]` | List ratings
`edit -b BOOK_NUMBER /to NEW_TITLE` | Edit book title
`edit -q QUOTE_NUMBER /to NEW_QUOTE [/by AUTHOR] [/from BOOK_TITLE]` | Edit quote
`edit -qr QUOTE_NUMBER /to UPDATED_REFLECTION` | Edit quote reflection
`edit -c OLD_CATEGORY /to NEW_CATEGORY` | Edit a category name
`edit -r RATING_SCORE BOOK_TITLE /by AUTHOR` | Edit rating
`bookmark -b BOOK_NUMBER /pg PAGE_NUMBER` | Update bookmark
`delete -b BOOK_NUMBER` | Delete book
`delete -q QUOTE_NUMBER` | Delete quote
`delete -qr QUOTE_NUMBER` | Delete quote reflection
`delete -bm BOOK_NUMBER` | Delete bookmark
`delete -t TASK_NUMBER` | Delete task
`delete -c CATEGORIES {[-b BOOK_TITLE] [-q QUOTE_NUMBER]}` | Delete categories
`delete -r BOOK_TITLE /by AUTHOR` | Delete rating
`find -b KEYWORD` | Find books
`find -q KEYWORD` | Find quotes
`find -r BOOK_TITLE /by AUTHOR` | Find rating
`find -c KEYWORD` | Find categories
`done -b BOOK_NUMBER` | Mark book as complete
`done -t TASK_NUMBER` | Mark task as done
`help` | Show help page
`bye` | Terminate the program

* Words in [] are **OPTIONAL**
* Words in **CAPS** are your own input
* Words are seperated by a single whitespace
