# Developer Guide

## Table of Contents
- [1.0 Introduction](#10-introduction)
- [2.0 Setting up](#20-setting-up)
- [3.0 Design](#30-design)
  * [3.1 Architecture](#31-architecture)
  * [3.2 UI component](#32-ui-component)
  * [3.3 Logic component](#33-logic-component)
  * [3.4 Model component](#34-model-component)
  * [3.5 Storage component](#35-storage-component)
- [4.0 Implementation](#40-implementation)
  * [4.1 Book Management](#41-feature-book-management)
  * [4.2 Quote Management](#42-feature-quote-management)
  * [4.3 Progress Tracker](#43-feature-progress-tracker)
  * [4.4 Category Management](#44-feature-category-management)
  * [4.5 Rating System for books](#45-feature-rating-system-for-books)
- [Appendix A: Product Scope](#appendix-a-product-scope)
- [Appendix B: User Stories](#appendix-b-user-stories)
- [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements)
- [Appendix D: Glossary](#appendix-d-glossary)
- [Appendix E: Instructions for manual testing](#appendix-e-instructions-for-manual-testing)
  * [Launch and Shutdown](#launch-and-shutdown)
    + [Initial Launch](#initial-launch)
    + [Subsequent Launch](#subsequent-launch)
    + [Shutdown](#shutdown)
  * [Testing for Book Management](#testing-for-book-management)
    + [Adding a book](#adding-a-book)
    + [List all existing books](#list-all-existing-books)
    + [List book details](#list-book-details)
    + [List books by author](#list-books-by-author)
    + [Find books by keyword](#find-books-by-keyword)
    + [Delete books](#delete-books)
    + [Edit book](#edit-book)
  * [Testing for Quote Management](#testing-for-quote-management)
    + [Adding a quote](#adding-a-quote)
    + [Listing all quotes](#listing-all-quotes)
    + [Listing quotes by a specific author](#listing-quotes-by-a-specific-author)
    + [Listing quotes from a specific reference](#listing-quotes-from-a-specific-reference)
    + [Listing quotes from a specific author and reference](#listing-quotes-from-a-specific-author-and-reference)
    + [Editing a quote](#editing-a-quote)
    + [Deleting a quote](#deleting-a-quote)
    + [Finding a quote](#finding-a-quote)
    + [Adding reflection to quote](#adding-reflection-to-a-quote)
    + [Listing reflection of a quote](#listing-reflection-of-a-quote)
    + [Editing reflection of a quote](#editing-reflection-of-a-quote)
    + [Deleting reflection of a quote](#deleting-reflection-of-a-quote)
  * [Testing for Progress Tracker](#testing-for-progress-tracker)
    + [Adding a bookmark to book](#adding-a-bookmark-to-book)
    + [List all existing bookmarks](#list-all-existing-bookmarks)
    + [Deleting an existing bookmark](#deleting-an-existing-bookmark)
    + [Editing an existing bookmark](#edit-an-existing-bookmark)
    + [Adding a task to todo list](#adding-a-task-to-todo-list)
    + [Listing all existing tasks](#listing-all-existing-tasks)
    + [Marking an existing task as done](#marking-an-existing-task-as-done)
    + [Deleting an existing task](#deleting-an-existing-task)
  * [Testing for Category Management](#testing-for-category-management)
    + [Adding categories](#adding-categories)
    + [Listing all categories](#listing-all-categories)
    + [Listing a specific category](#listing-a-specific-category)
    + [Deleting existing categories](#deleting-existing-categories)
    + [Editing an existing category](#editing-an-existing-category)
    + [Finding an existing category](#finding-an-existing-category)
  * [Testing for Rating System for books](#testing-for-rating-system-for-books)
    + [Adding a book rating](#adding-a-book-rating)
    + [Listing all existing book ratings](#listing-all-existing-book-ratings)
    + [Listing books of a specific book rating](#listing-books-of-a-specific-book-rating)
    + [Deleting a book rating](#deleting-a-book-rating)
    + [Editing a book rating](#editing-a-book-rating)
    + [Finding book ratings](#finding-book-ratings)

---

## 1.0 Introduction

**Welcome to Quotesify!**

Quotesify is a free desktop application to help you in your reading activities. With Quotesify, you can add
books and its related quotes that you wish to remember. You can categorize your books and quotes by an author,
customize categories, and even rate your books. Quotesify also comes with a progress tracker which improves
your reading experience.

This guide will provide information on the design and implementation of Quotesify. It will help you to get started
on your journey of being a contributor to Quotesify. This guide will also explain the steps to test out the program,
so that you will have a better understanding of the current status of Quotesify.

---

## 2.0 Setting up

1. Fork the Quotesify repo from [here](https://github.com/AY2021S1-CS2113T-T09-3/tp),
and clone the fork to your computer.
2. Open up your IDE (IntelliJ is highly recommended). If you are not at the welcome screen,
click `File` > `Close Project` to close any existing project.
3. Set up the correct JDK version for Gradle:
    1. Click `Configure` > `Project Defaults` > `Project Structure`.
    2. Click `New…` and find the directory where you saved your JDK.
4. Click `Import Project`.
5. Locate the *build.gradle* file and select it.
6. Click `OK`.
7. Click `Open as Project`.
8. Click `OK` to accept all default settings.
9. To verify the set up, locate the `Quotesify.java` file, right-click it and select `Run Quotesify.main()`.
If the setup is correct, you should see something like this as shown below:

```
________                __                .__  _____       
\_____  \  __ __  _____/  |_  ____   _____|__|/ ____\__.__.
 /  / \  \|  |  \/  _ \   __\/ __ \ /  ___/  \   __<   |  |
/   \_/.  \  |  (  <_> )  | \  ___/ \___ \|  ||  |  \___  |
\_____\ \_/____/ \____/|__|  \___  >____  >__||__|  / ____|
       \__>                      \/     \/          \/    
Welcome to Quotesify v2.1!
Before you continue, here's something:
Better days are coming, they are called Saturday and Sunday.
----------------------------------------------------------------

What would you like to do with Quotesify?
```

*Note: If you have added a quote before, the quote printed will be randomized.*

---

## 3.0 Design

*Note: All UML diagrams in this guide are stored in the docs/images directory.*

### 3.1 Architecture
![Architecture Diagram](images/Architecture_Diagram.png)

The architecture diagram displayed above describes the high-level design of Quotesify. Below details a brief
description of each component shown.

---

#### Main class: `Quotesify.java`
* On program launch, the Main is responsible for initialising the required components in the correct sequence as well
as to connect these components to start Quotesify.
* On shutdown, the Main gracefully terminates the application and its running components.

The other components that make up Quotesify include:
* **UI**: Text-based User Interface (UI) of the application.
* **Logic**: Handles the execution of user input commands.
* **Model**: Stores Quotesify’s data objects in application memory.
* **Storage**: Stores and accesses program data in the hard disk.

The Sequence Diagram below shows an example of how the components work together upon receiving the command
`add -b Harry Potter /by JK Rowling`.

![Sequence Diagram for Components](images/SeqDiagram_Components.png)

* The user command `add -b Harry Potter /by JK Rowling` will be converted into a String object named `userCommandText`, which is then
passed into the Logic component for parsing and execution of the command.

---

### 3.2 UI Component

The class diagram below shows the associations between classes that make up the UI component.

![Class Diagram for UI Component](images/ClassDiagram_UI.png)

The UI component is made up of mainly 2 classes:
* `TextUi`: Responsible for the majority of the display of Quotesify’s messages. 
* `UiMessage`: Holds all messages required for TextUI to print to the program console.

In essence, the UI is responsible for the majority of the display of all successful command executions, error messages
, as well as user interactions by prompting for the next command.

---

### 3.3 Logic Component

The class diagram below shows the associations between classes that make up the Logic component.

Note: **Model** and **Storage** in this diagram represent components, not classes.

![Class Diagram for Logic Component](images/ClassDiagram_Logic.png)

The Logic component is made up of 2 sub-components, namely the `Parser` and `Command`. Below describes the sequence
flow from the time a user input is read till command execution ends.

1. UI fetches the User input, which is passed into the Parser for parsing.
2. Parser returns a Command object, which is then executed.
3. The command execution outcome may affect Quotesify’s model objects. (e.g. adding a book)
4. Command instructs the UI component to print out relevant output messages depending on command type.
Also, Command may invoke saving of data via Storage at a given point in time.
5. Finally, Command will then inspect the exit status after command execution to verify if the program should exit.
6. Control is handed back over to the UI, either for processing of program exit, or the next user input command.

* `X` represents the execution type consisting of `Add`, `Delete`, `Edit`, `Find`, `List`, `Done`.
* `Y` represents the model type consisting of `Book`, `Quote`, `Category`, `Bookmark`, `ToDo`, `Rating`.

Therefore,
* `XCommand` class represents the command type such as `AddCommand`, `FindCommand`, `DeleteCommand`, etc.
* `XYCommand` class represents the command to execute from a specific model type. Such as `AddBookCommand`
, `FindBookCommand`, `DeleteQuoteCommand`, etc.

---

### 3.4 Model Component

The class diagram below shows the associations between classes that make up the Model component.

![Class Diagram for Model Component](images/ClassDiagram_Model.png)

The model component consists of several classes that make up the main features of Quotesify.
Each object holds in-application data unique to each feature and is stored in a list of their own.

---

### 3.5 Storage Component

The storage component consists of a single `Storage` class. It is responsible for saving user data as instructed
by the command component as well as to detect and load data on program launch.

On program launch:
1. `Storage` is initialised and checks for the existence of save data.
2. If save data exists, `Storage` will read the save data in JSON format and parses them back into their
model objects (e.g. Book).
3. If save data does not exist, `Storage` will create an empty save file in the specified directory.

On Command execution:
1. `Storage` parses all model objects in JSON format and writes into the save file.

---

## 4.0 Implementation

*Note: All UML diagrams in this guide are stored in the docs/images directory.*

### 4.1 Feature: Book Management

Given below is the class diagram for classes related to Book Management in Quotesify:

![Class Diagram for Book Management](images/ClassDiagram_Book.png)

* The `XBookCommand` class represents `AddBookCommand`, `ListBookCommand`, `EditBookCommand`, `DeleteBookCommand`,
`FindBookCommand` and `DoneBookCommand`.
#### 4.1.1 Add Books

The sequence diagram below demonstrates the command execution process when adding a book to the booklist.

![Sequence Diagram for Add Books](images/SeqDiagram_AddBook.png)

* To reduce complexity and increase readability, the sequence diagram excludes error handling.
* The Parser and AddCommand classes parses the user input, which is also not shown in the diagram.
* We did not include the `checkMissingInformation()` method in the sequence diagram as it merely checks for possible
missing information given in the user input and throws exceptions.
* Upon ensuring there are no mistakes in the user input, `createNewBook()` will be called, which essentially creates a 
new book after ensuring there are no identical books already in the BookList.
* `sort()` method is called after adding the book to the BookList in order to sort the books in 
alphabetical order.

##### Design Considerations

* Title and author must be specified as <title,author> is used as the primary key.
    * Pros: Allows users to specify different books with the same title but different author.
    * Cons: Need to check for both title and author to prevent duplicates.
* BookList is always sorted in alphabetical order 
    * Alternative 1 (current choice): Sort after adding the book
        * Pros: Only need to sort after every addition, listing is as per normal
        * Cons: Adding books may take longer if size of BookList is too large
    * Alternative 2: Sort before listing the books
        * Pros: Adding books will not take as long
        * Cons: Since there are multiple listing methods, may not be the best method to keep sorting before listing.

#### 4.1.2 Find Book by Keyword

The sequence diagram below demonstrates the command execution process when finding books by a keyword.

![Sequence Diagram for Find Book by Keyword](images/SeqDiagram_FindBook.png)

* Opt-frames ensure that the user input is correct and that the keyword results in a non-empty list of books.
* Parsing of user input is done in the Parser and FindCommand classes, which is not shown in the diagram.
* `findByKeyword()` method filters books regardless of case. 

##### Design Considerations

* Allows user to find books if either title or author contains the keyword.
    * Pros: Users can find books not just based on book title alone. The search range is increased to author name as well.
    * Cons: Need to check for both title and author for the list of results.
* Case insensitive
    * Pros: Users do not have to bother about the exact letter case when typing the keyword.
    * Cons: Need to ensure all titles or authors are converted to the same case before searching.
* Keyword or phrase
    * Alternative 1: Let users find by multiple space-separated keywords
        * Pros: Users can find books if they can only remember part of the book title.
        * Cons: Search list may not be narrowed as much. 
    * Alternative 2 (current choice): Let users find by exact phrase
        * Pros: Users can narrow down the search using an exact phrase instead of just one word.
        * Cons: The exact phrase must be typed out for the correct result to show.

---

<!-- @@author nat-ho -->
### 4.2 Feature: Quote Management

Given below is the class diagram for classes related to the Quote Management System in Quotesify:

![Class Diagram for Quote Management System](images/ClassDiagram_Quote.png)

A `Quote` object holds the following attributes:
* A `String` that holds the quote given by the user.
* An `Author` object that contains the author name of the quote.
* A `String` that holds the reference title from where the quote was from.
* A `String` that holds the user given reflection of the quote.

* The Quote management system contains the following commands: `AddQuoteCommand`, `ListQuoteCommand`, `EditQuoteCommand`, 
`DeleteQuoteCommand`, `FindQuoteCommand`, `AddQuoteReflectionCommand`, `ListQuoteReflectionCommand`, 
`EditQuoteReflectionCommand`, `DeleteQuoteReflectionCommand`.

#### 4.2.1 Add quote

The add quote feature allows users to add quotes of multiple formats to Quotesify.
Quotes added can be of the following format:
* Quote with author name and reference title
* Quote with author name
* Quote with reference title
* Quote with no author name and reference title

The sequence diagram below reflects the command execution when a user adds a quote to Quotesify.

![Sequence Diagram for Add Quotes](images/SeqDiagram_AddQuote.png)

* Not shown in the sequence diagram is the parsing of user input by Quotesify's main parser class that creates an
`AddCommand` object which subsequently creates the `AddQuoteCommand` as seen in the diagram, and then calling it's 
`execute()` method.
* The `parseParametersIntoQuote()` method from `QuoteParser` will be called to identify the format desired by the user 
and returns the appropriate `Quote` object to be added into the `QuoteList` with the user specified information. 
* Appropriate validation checks will be conducted to identify if the quote field is empty. Similarly, if author and
reference flags are used, their respective fields will also be checked. If any missing field is found, the quote will
not be added, and an error message will be displayed.
* Additional checks for duplicate flags and other invalid input parameters will also result in an exception being 
thrown and respective error messages displayed to the user when detected.
* Upon successful addition of a quote, the user will now be able to list, edit, delete, find and add a reflection to it.

#### Design Considerations

* Aspect: Allowing users to add quotes of different formats, or only accepting one format for quotes.
    * Alternative 1 (current choice): Allowing users to add quotes of different formats with a single command.
        * Pros: Users will not be restricted to one format or be required to use additional commands to add author and reference.
        * Cons: Increased implementation complexity due to parsing different formats and additional error handling.
    * Alternative 2: Users can only add quotes of one format.
        * Pros: Easier implementation and less steep of a learning curve for new users.
        * Cons: Reduced efficiency and usability as not all quotes have author and/or reference titles.
    
#### 4.2.2 Edit Quote Reflection

The edit quote reflection feature updates current reflection of a quote into a new reflection, keeping the remaining 
information such as quote, author name and reference the same.

The sequence diagram below reflects the command execution when a user edits the reflection of a quote.

![Sequence Diagram for Edit Quotes Reflection](images/SeqDiagram_EditQuoteReflection.png)

* Not shown in the sequence diagram is the parsing of user input by Quotesify's main parser class, which creates an
`EditCommand` object which subsequently creates the `EditQuoteReflectionCommand` as seen in the diagram, and then 
calling it's `execute()` method.
* Appropriate validation checks will be conducted to determine that the edit tag `/to` is present, and that the quote
contains a reflection prior. If the following conditions are not met, the reflection will not be updated, and the 
respective error messages will be displayed.
* Upon successful update of a reflection, the user will now be able to list the updated reflection for viewing.

#### Design Considerations

* Aspect: Users have to use a `/to` flag before specifying updated reflection, or allowing users to specify the updated 
reflection without any flags.
    * Alternative 1 (current choice): Use of `/to` flag
        * Pros: Clear demarcation before start of the updated reflection results in easier parsing.
        * Cons: Users are required to type more.
    * Alternative 2: No flags required before specifying updated reflection. 
        * Pros: Increased efficiency and users do not need to remember the different flags.
        * Cons: Increased difficulty in parsing and more prone to unhandled exceptions and errors.
        
* Aspect: Include a `updateReflection` method in `QuoteList` or edit the quote object directly from the `EditReflectionCommand`.
    * Alternative 1 (current choice): Use of a `updateReflection` method in the `QuoteList` to update the reflection.
        * Pros: Better encapsulation and data hiding as attributes can be set to private
        * Cons: Additional methods and passing of data required
    * Alternative 2: Edit the quote object from the `EditReflectionCommand`.
        * Pros: Reduced number of methods and implementation complexity.
        * Cons: Increased coupling and difficulty in tracking down potential bugs if attributes are set to public.
<!-- @@author -->

---

<!-- @@author lunzard -->
### 4.3 Feature: Progress Tracker

Progress Tracker consists of two parts: Bookmark Management and Task Management.
Given below is the class diagram for classes related to Bookmark Management in Quotesify:

![Class Diagram for Bookmark Management](images/ClassDiagram_Bookmark.png)

Given below is the class diagram for classes related to Task Management in Quotesify:

![Class Diagram for Task Management](images/ClassDiagram_ToDo.png)

#### 4.3.1 Add/Update bookmark

The proposed add or update bookmark feature will rely on an existing `Book` object, and then a `Bookmark` object will 
be created in the process.
* The `Bookmark` object will be made up of the `Book` object and a page number, which is stored in a list of 
bookmarks named `BookmarkList`.

The sequence diagram below demonstrates the command execution process when adding or updating a bookmark to an existing book.

![Sequence Diagram for Add Bookmark](images/SeqDiagram_AddBookmark.png)

* To reduce complexity and increase readability, the sequence diagram excludes error handling.
* Not shown in the sequence diagram is the parsing of user input by Quotesify's main parser class which creates an
`BookmarkCommand` object as seen in the diagram, and then calling it's `execute()` method.

#### Design Considerations

* BookmarkList is always sorted in order of creation time
    * Pros: Allow users to easily find the earliest and the latest book that they read.
    * Cons: More difficult for users to find the bookmark that they recently updated.

#### 4.3.2 Add task

The add task feature allows users to add tasks with a deadline to Quotesify. Tasks added can be of the following format:

* Tasks without any deadline
* Tasks with an unformatted deadline
* Tasks with a formatted deadline 
 
* The `ToDo` object (we mentioned as 'task' before) will be made up of a name and a deadline, which is stored in a list of 
tasks named `ToDoList`.

The sequence diagram below demonstrates the command execution process when adding a task.

![Sequence Diagram for Add ToDo](images/SeqDiagram_AddToDo.png)

* To reduce complexity and increase readability, the sequence diagram excludes error handling.
* Not shown in the sequence diagram is the parsing of user input by Quotesify's main parser class, which creates an
`AddCommand` object which subsequently creates the `AddToDoCommand` as seen in the diagram, and then calling it's 
`execute()` method.

#### Design Considerations

* ToDoList is sorted in two ways:
    * Tasks with formatted deadline will be sorted in ascending order of time. (The one with an earlier deadline 
    is displayed ahead of the one with a later deadline)
    * Tasks with unformatted deadline and without specified deadline will be arranged in order of creation time.
    * Tasks with formatted deadline will be listed ahead of all other tasks.
    
        * Pros: Users can view the most urgent task easily
        * Cons: It is harder for users to find a task with an unformatted deadline 
        even though the text in the deadline represents a high urgency.
<!-- @@author -->

---
<!-- @@author dozenmatter -->
### 4.4 Feature: Category Management

Given below is the class diagram for classes related to Category Management in Quotesify:

![Class Diagram for Category Management](images/ClassDiagram_Category.png)

A `Category` object holds the following attributes:
* A `String` object that holds the category name in **lower-case** format.
* A `BookList` object that stores a list of `Book` objects tagged with the specified category name.
* A `QuoteList` object that stores a list of `Quote` objects tagged with the specified category name.

#### 4.4.1 Add Categories

The proposed add categories feature allows users to add multiple categories to an existing book, quote, or both. 

The sequence diagram below demonstrates the command execution process when adding a category to an existing book.

![Sequence Diagram for Add Categories](images/SeqDiagram_AddCategories.png)

* For each category that the user has specified, the process will be executed in a loop until all categories have been processed.
* Additional checks include verifying the existence of the specified book and if the specified category already exists in the book. If either one of these checks fail, an error message will be prompted.
* On success, 
  * The category name will be added into the *categories* attribute of the `Book` object. 
  * A new `Category` object will be created and stored into the category list if it does not exist. 
  * The book will be added into the category's *bookList* attribute for record keeping.

##### Design Consideration

* Aspect: Allowing users to specify only a single, or multiple categories at once.
  * Alternative 1 (current choice): Allowing users to specify multiple categories at once.
    * Pros: Increases efficiency for users as they could add multiple categories to a book/quote in a single command.
    * Cons: Increased complexity in implementation.
  * Alternative 2: Allowing users to specify only a single category at any time.
    * Pros: Easier to implement.
    * Cons: Inefficient for users who wish to add multiple categories to a book/quote.

* Aspect: Whether to give users an option to specify both books and quotes to be tagged with a category.
  * Alternative 1 (current choice): Giving users an option to specify a book, quote, or both to be tagged with a category.
    * Pros: Increases efficiency for users as they could add the same category to a book and quote in a single command.
    * Cons: Difficult to implement.
  * Alternative 2: Users can only specify a book or quote, but not both to be tagged with a category:
    * Pros: Easier to implement.
    * Cons: Inefficient for users who wish to add a category to a book and quote at the same time. 

With the addition of new categories, users can perform several commands that makes use of them. Such as editing of category name, finding a category, deleting a category,
listing all categories, or adding the same category to other books and quotes.
<!-- @@author -->

---

<!-- @@author yuen-sihao -->
### 4.5 Feature: Rating system for books

Given below is the class diagram for classes related to the Rating System in Quotesify:

![Class Diagram for Rating system](images/ClassDiagram_Rating.png)

* A `Book` must exists before a `Rating` can be created.
* A `Book` object will have an attribute rating.

#### 4.5.1 Add rating

The *add rating* feature will rely on an existing book object, and a rating object will then be created
in the process.
* The book object will store an attribute named *rating*, which will be set by this feature.
* The rating object will be made up of the book object and a rating score, which is stored in a list of ratings.

Given below is the sequence diagram for adding rating to a book:

![Sequence Diagram for Add Ratings](images/SeqDiagram_AddRating.png)

* The sequence diagram shows the process of *Add Rating* from the `execute()` method in `AddRatingCommand` class,
which extends the `AddCommand` class. The switch statement in the `execute()` method to decide the item that the user
is adding is not shown in the diagram.
* The list of ratings will be retrieved from the `ListManager` class which stores all the different lists in Quotesify.
* In the `addRating()` method, if the user input such as book number or rating score is missing
, a message will be printed to inform the user and the method is returned.
* There will also be checks implemented by the `RatingParser` to check if the rating score is within range,
if the book to be rated exists in Quotesify and if the book has been rated before.
This is done by checking the list of books in Quotesify. If all these conditions are met, the book will be rated.
* When rating a book, the attribute *rating* of the book is set to the rating score. A rating object containing the
book details and rating score will also be created and stored in the rating list.
This list of ratings will be used when listing or finding ratings.

##### Design Consideration

* Aspect: Saving the ratings in a Rating List as compared to just only using the Book List
    * Alternative 1 (current choice): Saving the ratings in a Rating List.
        * Pros: Helps in listing and finding ratings as not all books are rated.
        * Cons: Increases memory usage.
    * Alternative 2: Saving the ratings only in the Book List.
        * Pros: Less memory usage.
        * Cons: May be slower as not all books are rated and the list is not sorted according to rating score.

* Aspect: Using both book title and author to identify a rating instead of just book title
    * Alternative 1 (current choice): Using both book title and author to identify a rating.
        * Pros: Allows books with the same title but different author to be rated.
    * Alternative 2: Using only book title to identify a rating.
        * Cons: Books with same title but different author will not be rated.

#### 4.5.2 Find ratings

The *find ratings* feature will search for books with title that contains the specified keyword
and print details about the rating.

Given below is the sequence diagram for finding ratings:

![Sequence Diagram for Find Ratings](images/SeqDiagram_FindRating.png)

* The sequence diagram shows the process of *Find Rating* from the `execute()` method in `FindRatingCommand` class
which extends the `FindCommand` class. The switch statement in the `execute()` method to decide the item that the
user is finding is not shown in the diagram.
* The list of ratings will be retrieved from the `ListManager` class which stores all the different lists in Quotesify.
* In the `findRating()` method, if the keyword to search for is missing, a message will be printed
to inform the user and the method is returned.
* The list of ratings will be looped to see if ratings exists for books with title containing the specified keyword.
* The details of the ratings found will be printed to the user.
<!-- @@author -->

---

## Appendix A: Product Scope

### Target user profile

The intended user of Quotesify is someone who meets the following criteria:
* reads a lot
* has difficulty remembering content after reading them
* can type fast
* prefers typing over other means of input
* prefers using desktop applications
* comfortable with using Command Line Interface (CLI) applications

### Value proposition

* Helps user track books that they have read with their relevant review/rating
* Allows user to capture and log quotes with their reflection
* Timely quote reminders to resurface previously saved quotes
* Personalised categories created to only suit what the user needs
* Find books, quotes and categories with a single command
* Works offline and stores data locally
* Portable

---

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I ...|
|--------|----------|---------------|------------------|
|v1.0|reader|enter good quotes and phrases from a book I read|can quickly refer back to it at any time|
|v1.0|user|categorize my listings|can view quotes from a specific category that I want|
|v1.0|long time user|have a page to see a list of books and categories of my notes|can navigate into the relevant book/category without having to remember the titles|
|v1.0|user|give rating for the books I read|can recommend book titles to others when asked|
|v1.0|beginner reader|add deadlines to books I am reading|can keep track of my readling deadlines|
|v1.0|avid reader|be able to keep track of books|can filter out books I have read and those that are on my list of books to read|
|v1.0|forgetful user|add bookmarks to my book|can find the page where I last stopped|
|v1.0|user|categorise my books or quotes|can view items from a specific category whenever I need|
|v1.0|user|save quotes I find meaningful|can view my favourite quotes whenever I want|
|v2.0|forgetful reader|be reminded of quotes I saved|can remember them better in the long run|
|v2.0|student and reader|pen my thoughts to a highlighted quote or text|can expand on certain ideas or express how I feel|
|v2.0|long time user|be able to search for keywords|can find specific quotes I want from the list|
|v2.0|user after some time|find a book rating by its book title|do not have to go through the whole list|

---

## Appendix C: Non-Functional Requirements

1. Should work on major Operating Systems (OS) with at least `Java 11` installed.
2. A user should have no problems using the various commands without referring to the help page after some time.
3. Users should prefer typing to GUI.
4. Data should be stored locally inside the device's hard disk.
5. Data should be in a human editable text file.
6. Program should work without requiring an installer and/or remote server.

---

## Appendix D: Glossary

* *GUI* - Graphical User Interface
* *JAR file* - Java ARchive file
* *Major OS* - Windows, Linux, OS-X

---

## Appendix E: Instructions for Manual Testing

### Launch and shutdown

#### Initial launch
   1. Ensure `Java 11` and above is installed.
   2. Download the latest **Quotesify JAR file** from [here](https://github.com/AY2021S1-CS2113T-T09-3/tp/releases).
   3. Save the jar file in a desired file directory.
   4. Open your command line or terminal and navigate into the file directory where Quotesify is saved.
   5. Run `java -jar [CS2113T-T09-3][Quotesify].jar` to launch Quotesify.
   
#### Subsequent launch
   1. Open your command line or terminal and navigate into the file directory where Quotesify is saved.
   2. Run `java -jar [CS2113T-T09-3][Quotesify].jar` to launch Quotesify.
   3. Data will be automatically loaded from the data file upon launch.
   
#### Shutdown
   1. To terminate Quotesify, enter the `bye` command. You should see the following:
   2. Data will be automatically saved into a data file.
   
```
---------------------------------------------------------------
Before you continue, here's something:
Better days are coming, they are called Saturday and Sunday.
Alright, have a nice day!
---------------------------------------------------------------
```

*Note: If you have added a quote before, the quote printed will be randomized.*

---

### Testing for Book Management

#### Adding a book

1. Test case: `add -b Harry Potter /by JK Rowling`
    
   Expected: Book is added to Quotesify's booklist. A message will be prompted to indicate that
   the book has been successfully added.
   
2. Other incorrect commands to try:
    * `add -b`: Book title left empty
    * `add -b /by JK Rowling`: Book title left empty with author name specified
    * `add -b title`: Author name left empty
    * `add -b title /by`: Author name left empty with author tag specified
    
    Expected: Book will not be added. An error message will be printed.
    
#### List all existing books

1. Test case: `list -b`

   Expected: All existing books in booklist will be listed.
   
#### List book details

1. Test case: `list -b 2`

   Expected: Book details of book with the book index 2 from list of all books will be printed.
   
2. Other incorrect commands to try:
   * `list -b Harry Potter /by JK Rowling`: Wrong format. 
   * `list -b 10000`: Index out of range of booklist.
   
   Expected: Book details will not be printed. An error message will be printed.
   
#### List books by author

1. Test case: `list -b /by JK Rowling`

   Expected: Books with the author JK Rowling will be listed. 
   
2. Other incorrect commands to try:
   * `list -b JK Rowling`: Flag `/by` not specified
   
   Expected: Books will not be listed. An error message will be printed.
   
#### Find books by keyword

1. Test case: `find -b Harry`

   Expected: Books with the title or author name containing Harry will be listed. 
   
2. Other incorrect commands to try:
   * `find -b`: Keyword not specified
   
   Expected: Books will not be listed. An error message will be printed.

#### Delete books

1. Test case: `delete -b 3`

   Expected: Book with the book index of 3 in booklist will be deleted from list.
   A successful message will be printed.
   
2. Other incorrect commands to try:
   * `delete -b Harry Potter`: Wrong format
   * `delete -b 10000`: Index out of range of booklist.
   
   Expected: Book will not be deleted. An error message will be printed.

#### Edit book

1. Test case: `edit -b 3 /to Harry Potterrrrr`

   Expected: Book title of book with the book index of 3 will be changed to Harry Potterrrrr.
   
2. Other incorrect commands to try: 
   * `edit -b Harry Potter /to Harry Potterrrrr`: Wrong format
   * `edit -b 3 Harry Potterrrrr`: Flag `/to` not specified. 
   * `edit -b 3 /to`: New title not specified. 
   
   Expected: Book title will not be edited. An error message will be printed. 
   
---

<!-- @@author nat-ho -->
### Testing for Quote Management
   
#### Adding a quote

1. Add a quote without author and reference title to Quotesify

    * Test case: `add -q Life is short, smile while you still have teeth`
    
    * Expected: Quote is added to Quotesify. A message will be prompted to indicate that the quote has been successfully added.

2. Add a quote with an author to Quotesify

    * Test case: `add -q Luke, I am your father /by Darth Vader`
    
    * Expected: Quote is added to Quotesify. A message will be prompted to indicate that the quote has been successfully added.

3. Add a quote with a reference title to Quotesify

    * Test case:`add -q Get schwifty! /from Rick and Morty`
    
    * Expected: Quote is added to Quotesify. A message will be prompted to indicate that the quote has been successfully added.
    
4. Add a quote with an author and reference title to Quotesify 

    * Test case: `add -q So everyone’s supposed to sleep every single night now? /by Rick /from Rick and Morty`
    
    * Expected: Quote is added to Quotesify. A message will be prompted to indicate that the quote has been successfully added. 
   
5. Other incorrect commands to try:
   * `add -q` : quote field left empty
   * `add -q ` : empty space entered for quote field  
   * `add -q you can't see me /by` : author tag with missing author name
   * `add -q my name is inigo montoya /from` : reference tag with missing reference title
   * `add -q i am your father /by /from` : missing reference title and author name
   
   Expected: Quote will not be added. A message with error details will be shown.
   
#### Listing all quotes

1. Test case: `list -q`

   Expected: The entire list of quotes with reference and author name (if present) in Quotesify will be displayed.
   
#### Listing quotes from a specific reference

1. Test case: `list -q /from rick and morty`

   Expected: The list of quotes with the specified reference title will be displayed.
   
2. Other incorrect commands to try:
   * `list -q /from` : reference tag with missing reference title
   
   Expected: No quotes will be listed. A message with error details will be shown.
   
#### Listing quotes by a specific author

1. Test case: `list -q /by darth vader`

   Expected: The list of quotes with the specified author name will be displayed.
   
2. Other incorrect commands to try:
   * `list -q /by` : author tag with missing author name
   
   Expected: No quotes will be listed. A message with error details will be shown.
   
#### Listing quotes from a specific author and reference

1. Test case: `list -q /by rick /from rick and morty`

   Expected: The list of quotes with the specified author name and reference title will be displayed.
   
2. Other incorrect commands to try:
   * `list -q /by /from rick and morty` : author and reference tag with missing author name
   * `list -q /by rick /from` : author and reference tag with missing reference title
   * `list -q /by /from` : missing author name and reference title
   
   Expected: No quotes will be listed. A message with error details will be shown.
   
#### Editing a quote

1. Test case: `edit -q 2 /to No, I am your mummy /by Darth Vader`
   
   Expected: Quote will be updated, a prompt displaying old and updated quote will be displayed.
   
2. Other incorrect commands to try:
   * `edit -q` : missing quote number and updated quote
   * `edit -q 1 /to`: missing updated quote
   * `edit -q 1 You can't see me` : missing "/to" flag
   * `edit -q 9999999 /to You can't see me` : non-existent quote number
   
   Expected: Quote will not be updated. A message with error details will be shown.
   
#### Deleting a quote

1. Test case: `delete -q 1`

   Expected: Quote will be deleted from Quotesify. A message will be prompted to indicate that 
   the quote has been successfully deleted.
   
2. Other incorrect commands to try:
   * `delete -q`: missing quote number field
   * `delete -q X`: non integer input
   * `delete -q 9999999`: non existent quote number
   
   Expected: Quote will not be deleted. A message with error details will be shown.
   
#### Finding a quote

1. Test case: `find -q sleep`

   Expected: Quotes related to the keyword will be shown.
   
2. Other incorrect commands to try:
   * `find -q`: missing keyword
   * `find -q `: empty space as keyword
   
   Expected: No quotes will be found and listed. A message with error details will be shown.
   
#### Adding reflection to a quote

1. Test case: `add -qr 1 /reflect No, that's not true. It's impossible!`
    
   Expected: Reflection is added to quote. A message will be prompted to indicate that the reflection has been successfully added.
   * Quotes with reflection will have a "[R]" tag attached to differentiate between those that do not.
   
5. Incorrect commands to try:
   * `add -qr` : missing quote number, reflection tag and reflection
   * `add -qr 1 /reflect` : reflection field missing
   * `add -qr 9999 /reflect Reflection is here` : non-existent quote
   
   Expected: Reflection will not be added. A message with error details will be shown.
   
#### Listing reflection of a quote

1. Test case: `list -qr 1`

   Expected: The reflection attached to the specified quote will be displayed.
   
2. Other incorrect commands to try:
   * `list -qr` : missing quote number
   * `list -qr 9999` : non-existent quote
   
   Expected: Reflection will not be listed. A message with error details will be shown.
   
#### Editing reflection of a quote

1. Test case: `edit -qr 1 /to Who is Yoda’s daddy?`
   
   Expected: Reflection will be updated, a prompt displaying updated reflection will be shown.
   
2. Other incorrect commands to try:
   * `edit -qr 1 /to`: missing updated reflection
   * `edit -qr 1 nothing to reflect` : missing `/to` flag
   * `edit -qr 9999999 /to updated reflection here!` : non-existent quote number
   
   Expected: Reflection will not be updated. A message with error details will be shown.
   
#### Deleting reflection of a quote

1. Test case: `delete -qr 1`

   Expected: Reflection will be deleted from the quote. A message will be prompted to indicate that 
   the reflection has been successfully deleted.
   
2. Other incorrect commands to try:
   * `delete -qr`: missing quote number field
   * `delete -qr X`: non integer input
   * `delete -qr 9999999`: non existent quote number
   
   Expected: Quote reflection will not be deleted. A message with error details will be shown.
<!-- @@author -->

---
<!-- @@author lunzard --> 
### Testing for Progress Tracker
   
#### Adding a bookmark to book

1. Test case: `bookmark -b 1 /pg 123`

   Expected: a page number will be marked at the book. A message will be prompted to indicate that 
   the bookmark has been tagged to the book successfully.

2. Other incorrect commands to try:
   * `bookmark -b 1 /pg`: missing page number field
   * `bookmark -b 0 /pg 123`: incorrect book number input
   
   Expected: Bookmark will not be added to any book. A message will error details will be shown.

#### List all existing bookmarks

1. Test case: `list -bm`

   Expected: A list of bookmarks will be displayed. Each row contains an index assigned to the bookmark in the list,
    its book’s information, and a page number marked by the bookmark. 

#### Deleting an existing bookmark

1. Test case: `delete -bm 1`

   Expected: Bookmark will be deleted from the book. A message will be prompted to indicate that 
   the bookmark has been removed from the book successfully.

2. Other incorrect commands to try:
   * `delete -bm 999` : bookmark number with given index does not exist
   * `delete -bm abc` : invalid bookmark number provided
   
   Expected: Bookmark will not be deleted from the book. A message with error details will be shown.

#### Edit an existing bookmark

1. Test case: `bookmark -b 1 /pg 123`

   Expected: The page number will be updated in the bookmark. A message will be prompted to indicate that 
   the bookmark has been updated successfully.

2. Other incorrect commands to try:
   * `bookmark -b 1 /pg`: missing page number field
   * `bookmark -b 0 /pg 123`: incorrect book number input
   
   Expected: Bookmark will not be updated to any book. A message with error details will be shown.

#### Adding a task to todo list

1. Adding a task without deadline
   - Test case: `add -t return Harry Potter`
   
     Expected: A message will be prompted to indicate that the task has been added to the todo list successfully,
    and the deadline is ‘not specified’.
    
2. Adding a task with unformatted deadline
   - Test case: `add -t return Harry Potter /by tmr`
   
     Expected: A message will be prompted to indicate that the task has been added to the todo list successfully, 
   and the deadline is the same as stated in the command line.
   
3. Adding a task with formatted deadline
   - Test case: `add -t return Harry Potter /by 2020-10-24`
   
     Expected: A message will be prompted to indicate that the task has been added to the todo list successfully, 
     and the deadline will be formatted as ‘Oct 24 2020, Saturday’.

#### Listing all existing tasks

1. Test case: `list -t`

   Expected: A list of tasks will be displayed. The tasks with formatted deadlines will be displayed in the front, 
   and sorted in ascending order of timing, while other tasks will be displayed at the back without any order.

#### Marking an existing task as done

1. Test case: `done -t 1`
   
   Expected: A message will be prompted to indicate that the task has been marked as done in the todo list successfully.

#### Deleting an existing task

1. Test case: `delete -t 1`

   Expected: A message will be prompted to indicate that the task has been removed from the todo list successfully.
<!-- @@author -->

---
<!-- @@author dozenmatter -->   
### Testing for Category Management
   
#### Adding categories

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

4. Other incorrect commands to try
   - `add -c` missing category name, book or quote
   - `add -c action` missing a book or quote
   - `add -c action -b 0 -q 0` invalid book and quote index
   - `add -c -b 1 -q 1` missing category name
   
   Expected: An error message will be prompted. No changes will be made.
   
#### Listing all categories

1. List all existing categories
   - Test case: `list -c`
     
     Expected: A list of categories with the total number of items tagged under each category will be displayed.

#### Listing a specific category

1. List all books and quotes tagged by a specific category
   - Test case: `list -c action`
     
     Expected: A list of books and quotes tagged under that category will be displayed.

2. Incorrect commands to try
   - `list -c 123` invalid category name
   
   Expected: An error message will be displayed indicating that no such category exists.
   
#### Deleting existing categories

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
   
5. Other incorrect commands to try
   - `delete -c` missing category name, book or quote
   - `delete -c action -b 0 -q 0` invalid book and quote index
   - `delete -c -b 1 -q 1` missing category name
   
   Expected: An error message will be prompted. No changes will be made.
         
#### Editing an existing category

1. Edit an existing category name
   - Test case: `edit -c love /to romance`
     
     Expected: A message will be prompted indicating that category has been changed successfully. All books and quotes tagged under the old category will be changed as well.

2. Other incorrect commands to try
   - `edit -c` missing existing and new category name
   - `edit -c love` missing new category name
   
   Expected: An error message indicating invalid parameters and a command usage will be prompted. No changes will be made.
   
#### Finding an existing category

1. Find existing categories related to a keyword.
   - Test case: `find -c man`
   
     Expected: Quotesify will list all categories containing the keyword "man".
     
2. Other incorrect commands to try
   - `find -c` missing keyword
   - `find -c 123` invalid category name
   
   Expected: An error message will be prompted. No categories will be listed.
<!-- @@author -->

---
<!-- @@author yuen-sihao -->
### Testing for Rating System for books

#### Adding a book rating

1. Prerequisite: Book to be rated should exist in Quotesify.
Use `list -b` to list all existing books and get book number.

2. Test case: `add -r 5 1`, assuming book number 1 exists.

   Expected: Rating is added to the book. A message will be prompted to indicate rating has been added successfully.

3. Other incorrect commands to try:
   * `add -r`: rating score and/or book number fields left empty
   * `add -r 6 1`: assuming book number 1 exists, but rating score is out of the range
   * `add -r 1 x`: where x is a book number that does not exist
   
   Expected: No rating is added. A message with error details will be prompted.
    
#### Listing all existing book ratings

1. Test case: `list -r`

   Expected: The entire list of books and their ratings will be shown. Rating of books are sorted
   in descending order, with the highest rating at the top.

#### Listing books of a specific book rating

1. Test case: `list -r 5`

   Expected: The list of books with the specified rating will be shown.
   
2. Other incorrect commands to try:
   * `list -r 6`: rating score is out of the range
   * `list -r AAA`: invalid rating score
   
   Expected: No rating is listed. A message with error details will be prompted.
   
#### Deleting a book rating

1. Prerequisite: Book to be rated should exist in Quotesify.
Use `list -b` to list all existing books and get book number.

2. Test case: `delete -r 1`, assuming book number 1 exists.

   Expected: Rating is deleted from book. A message will be prompted to indicate rating has 
   been deleted successfully.
   
3. Other incorrect commands to try:
   * `delete -r`: book number field left empty
   * `delete -r x`: where x is a book number that does not exist or has not been rated
   
   Expected: No rating is deleted. A message with error details will be prompted.
   
#### Editing a book rating

1. Prerequisite: Book to be rated should exist in Quotesify.
Use `list -b` to list all existing books and get book number.

2. Test case: `edit -r 4 1`, assuming book number 1 exists.

   Expected: Rating is edited to the new rating. A message will be prompted to indicate rating has
   been edited successfully.
   
3. Other incorrect commands to try:
   * `edit -r`: rating score and/or book number fields left empty
   * `edit -r -1 1`: assuming book number 1 exists, but rating score is out of the range
   * `edit -r 3 x`: where x is a book number that does not exist or has not been rated
   
   Expected: No rating is edited. A message with error details will be prompted.
   
#### Finding book ratings

1. Test case: `find -r POT`

   Expected: Ratings of books with title that contains the keyword (case-insensitive) will be listed.
   
2. Other incorrect commands to try:
   * `find -r`: keyword field left empty
   
   Expected: No rating is found and listed. A message with error details will be prompted.
<!-- @@author -->

---
