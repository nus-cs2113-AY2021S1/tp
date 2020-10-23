# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Feature: Add rating
The proposed *add rating* feature will rely on an existing book object, and a rating object will then be created
in the process.
* The book object will store an attribute named rating, which will be set by this feature.
* The rating object will be made up of the book object and a rating score, which is stored in a list of ratings
named Rating List.

Given below is the class diagram of classes related to ratings:

[TO INSERT CLASS DIAGRAM HERE]

* A `Book` has a `0..1` multiplicity to `Rating` as a book can have only 1 rating or have not been rated at all.
* A `Rating` has a `1` multiplicity to `Book` as 1 rating represent only 1 book.
* A `Rating` has a `1` multiplicity to `RatingList` as the rating will only be stored in 1 rating list.
* A `RatingList` has a `*` multiplicity to `Rating` as the rating list can stored multiple rating objects.
* There is composition between `Book` and `Rating` as a rating can only be created when the book exists.
The rating will be deleted when the book is deleted.

#### Design consideration:
* The rating object stores a book object, which consists of the book title and author,
instead of only the book title. This will uniquely identify books and allow books with the same title
but different authors to be rated.

### Feature: List rating
The proposed *list rating* feature will print all the rating objects found in the rating list.
* Details stored in the rating objects like the book title, author and its rating score will be printed.
* If a rating score is specified by the user, only rating objects with that rating score will be printed.
* The messages are printed using `Ui` which relies on `UiMessage`.

#### Design consideration:
* The rating list is sorted according to the rating score, with the highest rating at the top. This is for
better user experience where users would usually like to see their favourites and do not need to scroll
all the way to the bottom.

### Feature: Find rating
The proposed *find rating* feature will loop through the rating list to find if the rating exists.
* If the rating is found, the details of the book and its rating score will be printed.
* If the rating is not found, a message to indicate unsucessful search will be printed.
* The messages are printed using `Ui` which relies on `UiMessage`.

[INSERT SEQUENCE DIAGRAM HERE]

### Feature Add bookmark
The proposed add bookmark feature will rely on an existing `Book` object, and then a `Bookmark` object will 
be created in the process.
* The `Bookmark` object will be made up of the `Book` object and a page number, which is stored in a list of 
bookmarks named `BookmarkList`.


## Product scope
### Target user profile
* reads a lot
* has difficulty remembering content after reading them
* can type fast
* prefers typing over other means of input
* prefers using desktop applications
* comfortable with using Command Line Interface (CLI) applications

### Value proposition

Improves the reading experience of users with quick and easy features

## User Stories

|Version| As a ... | I want to ... | So that I ...|
|--------|----------|---------------|------------------|
|v1.0|user|give rating for the books I read|can recommend book titles to others when asked|
|v1.0|user|categorise my books or quotes|can view items from a specific category whenever I need|
|v1.0|user|save quotes I find meaningful|can view my favourite quotes whenever I want|
|v2.0|user after some time|find a book rating by its book title|do not have to go through the whole list|

## Non-Functional Requirements

1. Should work on major Operating Systems (OS) such as Windows and Mac with at least `Java 11` installed.
2. A user should have no problems using the various commands without referring to the help page after some time.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Launch and shutdown

#### Initial launch
   1. Ensure `Java 11` and above is installed.
   2. Download the latest **Quotesify JAR file** from [here](https://github.com/AY2021S1-CS2113T-T09-3/tp/releases).
   3. Save the jar file in a desired file directory.
   4. Open your command line or terminal and navigate into the file directory where Quotesify is saved.
   5. Run `java -jar Quotesify.jar` to launch Quotesify.
   
#### Shutdown
   1. To terminate Quotesify, enter the `bye` command.
   2. Data will be automatically saved into a data file.
   
#### Subsequent launch
   1. Open your command line or terminal and navigate into the file directory where Quotesify is saved.
   2. Run `java -jar Quotesify.jar` to launch Quotesify.
   3. Data will be automatically loaded from the data file upon launch.
   
### Adding a book
1. Test case: `add -b Harry Potter /by JK Rowling`
    
   Expected: Book is added to Quotesify. A message will be prompted to indicate that
   the book has been successfully added.
   
2. Other incorrect commands to try:
    * `add -b`: Book title left empty
    * `add -b /by JK Rowling`: Book title left empty with author name specified
    * `add -b title`: Author name left empty
    * `add -b title /by`: Author name left empty with author tag specified
    
    Expected: Book will not be added. An error message will by printed.
    
### List all existing books
1. Test case: `list -b`

   Expected: All existing books in booklist will be listed.
   
### List book details
1. Test case: `list -b 2`

   Expected: Book details of book with the book index 2 from list of all books will be printed.
   
2. Other incorrect commands to try:
   * `list -b Harry Potter /by JK Rowling`: Wrong format. 
   * `list -b 10000`: Index out of range of booklist.
   
   Expected: Book details will not be printed. An error message will be printed instead.
   
### List books by author
1. Test case: `list -b /by JK Rowling`

   Expected: Books with the author JK Rowling will be listed. 
   
2. Other incorrect commands to try:
   * `list -b JK Rowling`: Flag `/by` not specified
   
   Expected: Books will not be listed. An error message will be printed.
   
### Find books by keyword
1. Test case: `find -b Harry`

   Expected: Books with the title or author name containing Harry will be listed. 
   
2. Other incorrect commands to try:
   * `find -b`: Keyword not specified
   
   Expected: Books will not be listed. An error message will be printed.

### Delete books
1. Test case: `delete -b 3`

   Expected: Book with the book index of 3 in booklist will be deleted from list.
   Successful message will be printed.
   
2. Other incorrect commands to try:
   * `delete -b Harry Potter`: Wrong format
   * `delete -b 10000`: Index out of range of booklist.
   
   Expected: Book will not be deleted. An error message will be printed.

### Edit book
1. Test case: `edit -b 3 /to Harry Potterrrrr`

   Expected: Book title of book with the book index of 3 will be changed to Harry Potterrrrr.
   
2. Other incorrect commands to try: 
   * `edit -b Harry Potter /to Harry Potterrrrr`: Wrong format
   * `edit -b 3 Harry Potterrrrr`: Flag `/to` not specified. 
   * `edit -b 3 /to`: New title not specified. 
   
   Expected: Book title will not be edited. An error message will be printed. 
### Adding a quote

1. * Test case 1: `add -q Life's short, smile while you still have teeth`
   * Test case 2: `add -q I am your father /by Darth Vader`
   * Test case 3: `add -q That’s my spot! /from The Big Bang Theory`
   * Test case 4: `add -q Wubba Lubba Dub Dub? /from Rick and Morty /by Rick`
   
   Expected: Quote is added to Quotesify. A message will be prompted to indicate that 
   the quote has been successfully added.
   
2. Other incorrect commands to try:
   * `add -q` : quote field left empty
   * `add -q You can't see me /by` : author tag with missing author name
   * `add -q My name is Inigo Montoya /from` : reference tag with missing reference title
   * `add -q I am your father /by /from` : missing reference title and author name
   
   Expected: Quote will not be added. A message with error details will be shown.
   
### Listing all quotes

1. Test case: `list -q`

   Expected: The entire list of quotes with reference and author name (if present) will be displayed.
   
### Listing quotes from a specific reference

1. Test case: `list -q /from The Big Bang Theory`

   Expected: The list of quotes with the specified reference title will be displayed.
   
2. Other incorrect commands to try:
   * `list -q /from` : reference tag with missing reference title
   
   Expected: No quotes are listed. A message with error details will be shown.
   
### Listing quotes by a specific author

1. Test case: `list -q /by Rick`

   Expected: The list of quotes with the specified author name will be displayed.
   
2. Other incorrect commands to try:
   * `list -q /by` : author tag with missing author name
   
   Expected: No quotes are listed. A message with error details will be shown.
   
### Listing quotes from a specific reference and by a specific author

1. Test case: `list -q /from Rick and Morty /by Rick`

   Expected: The list of quotes with the specified reference title and author name will be displayed.
   
2. Other incorrect commands to try:
   * `list -q /from Rick and Morty /by` : reference and author tag with missing author name
   * `list -q /from /by Rick` : reference and author tag with missing reference title
   * `list -q /from /by` : missing reference title and author name
   
   Expected: No quotes are listed. A message with error details will be shown.
   
### Deleting a quote

1. Test case: `delete -q 3`

   Expected: Quote will be deleted from Quotesify. A message will be prompted to indicate that 
   the quote has been successfully deleted.
   
2. Other incorrect commands to try:
   * `delete -q`: missing quote number field
   * `delete -q X`: non integer inout
   * `delete -q 9999999`: non existent quote number
   
   Expected: No quote is deleted. A message with error details will be shown.
   
### Editing a quote

1. * Test case 1: `edit -q 1 /to I pretty much spend all day, every day just looking forward to go back to sleep`
   * Test case 2: `edit -q 2 /to Don't give up on your dreams, keep sleeping! /by Stranger`
   * Test case 3: `edit -q 2 /to That’s my spot! /from The Big Bang Theory`
   * Test case 4: `edit -q 2 /to Wubba Lubba Dub Dub? /from Rick and Morty /by Rick`
   
   Expected: Quote will be updated, a prompt displaying old and updated quote will be shown.
   
2. Other incorrect commands to try:
   * `edit -q` : missing quote number and updated quote
   * `edit -q 1 /to`: missing updated quote
   * `edit -q 1 You can't see me` : missing "/to" flag
   * `edit -q 9999999 /to You can't see me` : none existent quote number
   
   Expected: Quote will not be updated. A message with error details will be shown.
   
### Finding a quote

1. Test case: `find -q sleep`

   Expected: Quotes related to the keyword will be shown.
   
2. Other incorrect commands to try:
   * `find -q`: missing keyword
   * `find -q `: empty space as keyword
   
   Expected: No quotes will be found. A message with error details will be shown.

### Adding categories
1. Add one or more category to a book
   - Prerequisites: A book should exist in Quotesify.
   - Assume that the book "Harry Potter" is added into Quotesify assigned to index 1.
   - Test case: `add -c fantasy -b 1`
     
     Expected: A message will be prompted to indicate that category has been tagged to the book successfully.
   - Test case: `add -c fantasy romance -b 1`
     
     Expected: A message will be prompted to indicate that categories have been tagged to the book successfully.
     
2. Add one or more category to a quote
   - Prerequisites: A quote should exist in Quotesify.
   - Assume that the quote "Life is great!" is added into Quotesify assigned to index 1.
   - Test case: `add -c inspirational -q 1`
     
     Expected: A message will be prompted to indicate that category has been tagged to the quote successfully.
   - Test case: `add -c inspirational happy -q 1`
     
     Expected: A message will be prompted to indicate that categories have been tagged to the quote successfully.
     
3. Add one or more category to a book and quote
   - Prerequisites: A book and quote should exist in Quotesify.
   - Assume that an existing book and quote are both assigned to index 1.
   - Test case: `add -c inspirational -b 1 -q 1`
     
     Expected: A message will be prompted to indicate that category has been tagged to both book and quote successfully.
   - Test case: `add -c inspirational action -q 1`
     
     Expected: A message will be prompted to indicate that categories have been tagged to both book and quote successfully.

4. Incorrect commands to try
   - `add -c` missing category name, book or quote
   - `add -c action` missing a book or quote
   - `add -c action -b 0 -q 0` invalid book and quote index
   - `add -c -b 1 -q 1` missing category name
   
   Expected: An error message will be prompted. No changes will be made.
   
### Listing all categories
1. List all existing categories
   - Test case: `list -c`
     
     Expected: A list of categories with the total number of items tagged under each category will be displayed.

### Listing a specific category
1. List all books and quotes tagged by a specific category
   - Test case: `list -c action`
     
     Expected: A list of books and quotes tagged under that category will be displayed.

2. Incorrect commands to try
   - `list -c 123` invalid category name
   
   Expected: An error message will be displayed indicating that no such category exists.
   
### Deleting existing categories
1. Remove one or more category from a book
   - Prerequisites: Specified book index, quote index and category should exist in Quotesify.
   - Assume the book "Harry Potter" is tagged with [action, fantasy] category and assigned with index 1.
   - Test case: `delete -c action -b 1`
     
     Expected: A message will be prompted to indicate that category has been removed from book successfully.
   - Test case: `delete -c action fantasy -b 1`
   
     Expected: A message will be prompted to indicate that categories have been removed from book successfully.
     
2. Remove one or more category from a quote
    - Prerequisites: Specified book index, quote index and category should exist in Quotesify.
    - Assume the quote "Life is great!" is tagged with [inspirational, happy] category and assigned with index 1.
    - Test case: `delete -c inspirational -q 1`
     
      Expected: A message will be prompted to indicate that category has been removed from quote successfully.
    - Test case: `delete -c inspirational happy -q 1`
     
      Expected: A message will be prompted to indicate that categories have been removed from quote successfully.

3. Remove one or more category from a book and quote
   - Prerequisites: Specified book index, quote index and category should exist in Quotesify.
   - Assume that a book and quote are both tagged with [action, happy] categories.
   - Test case: `delete -c action -b 1 -q 1`
     
     Expected: A message will be prompted to indicate that category has been removed from both book and quote successfully.
   - Test case: `delete -c action happy -b 1 -q 1`
     
     Expected: A message will be prompted to indicate that categories have been removed from both book and quote successfully.

4. Remove one or more category from list
   - Test case: `delete -c action`
   
   Expected: A message will be prompted to indicate that category has been removed from all book and quotes.
   - Test case: `delete -c action fantasy`
   
   Expected: A message will be prompted to indicate that categories has been removed from all book and quotes.
   
5. Incorrect commands to try
   - `delete -c` missing category name, book or quote
   - `delete -c action -b 0 -q 0` invalid book and quote index
   - `delete -c -b 1 -q 1` missing category name
   
   Expected: An error message will be prompted. No changes will be made.
         
### Editing an existing category
1. Edit an existing category name
   - Test case: `edit -c love /to romance`
     
     Expected: A message will be prompted indicating that category has been changed successfully. All books and quotes tagged under the old category will be changed as well.

2. Incorrect commands to try
   - `edit -c` missing existing and new category name
   - `edit -c love` missing new category name
   
   Expected: An error message indicating invalid parameters and a command usage will be prompted. No changes will be made.
   
### Adding a book rating

1. Prerequisite: Book to be rated should exist in Quotesify.

2. Test case: `add -r 5 Harry Potter /by JK Rowling`

   Expected: Rating is added to the book. A message will be prompted to indicate rating has been added successfully.

3. Other incorrect commands to try:
   * `add -r`: rating score, book title and/or author fields left empty
   * `add -r 1000 Harry Potter`: rating score is out of the range
   * `add -r 3 x`: where x is a book that does not exist
   
   Expected: No rating is added. A message with error details will be prompted.
    
### Listing all existing book ratings

1. Test case: `list -r`

   Expected: The entire list of books and their ratings will be shown. Rating of books are sorted
   in descending order, with the highest rating at the top.

### Listing books of a specific book rating

1. Test case: `list -r 5`

   Expected: The list of books with the specified rating will be shown.
   
2. Other incorrect commands to try:
   * `list -r 1000`: rating score is out of the range
   * `list -r AAA`: invalid rating score
   
   Expected: No rating is listed. A message with error details will be prompted.
   
### Deleting a book rating

1. Test case: `delete -r Harry Potter /by JK Rowling`

   Expected: Rating is deleted from book. A message will be prompted to indicate rating has 
   been deleted successfully.
   
2. Other incorrect commands to try:
   * `delete -r`: book title and/or author fields left empty
   * `delete -r x`: where x is a book that has not been rated
   
   Expected: No rating is deleted. A message with error details will be prompted.
   
### Editing a book rating

1. Test case: `edit -r 4 Harry Potter /by JK Rowling`

   Expected: Rating is edited to the new rating. A message will be prompted to indicate rating has
   been edited successfully.
   
2. Other incorrect commands to try:
   * `edit -r`: rating score, book title and/or author fields left empty
   * `edit -r 1000 Harry Potter /by JK Rowling`: rating score is out of the range
   * `edit -r 3 x`: where x is a book that has not been rated
   
   Expected: No rating is edited. A message with error details will be prompted.
   
### Finding a book rating

1. Test case: `find -r Harry Potter /by JK Rowling`

   Expected: The rating for book titled "Harry Potter" by JK Rowling will be shown.
   
2. Other incorrect commands to try:
   * `find -r`: book title and/or author fields left empty
   * `find -r x`: where x is a book that has not been rated
   
   Expected: No rating is found and listed. A message with error details will be prompted.