# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

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
|v2.0|user after some time|find a book rating by its book title|do not have to go through the whole list|

## Non-Functional Requirements

1. Should work on major Operating Systems (OS) such as Windows and Mac with at least `Java 11` installed.
2.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Launch and shutdown

### Adding a book rating

1. Prerequisite: Book to be rated should exist in Quotesify.

2. Test case: `add -r 5 Harry Potter`

   Expected: Rating is added to the book. A message will be prompted to indicate rating has been added successfully.

3. Other incorrect commands to try:
   * `add -r`: rating score and/or book title fields left empty
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

1. Test case: `delete -r Harry Potter`

   Expected: Rating is deleted from book. A message will be prompted to indicate rating has 
   been deleted successfully.
   
2. Other incorrect commands to try:
   * `delete -r`: book title field left empty
   * `delete -r x`: where x is a book that has not been rated
   
   Expected: No rating is deleted. A message with error details will be prompted.
   
### Editing a book rating

1. Test case: `edit -r 4 Harry Potter`

   Expected: Rating is edited to the new rating. A message will be prompted to indicate rating has
   been edited successfully.
   
2. Other incorrect commands to try:
   * `edit -r 1000 Harry Potter`: rating score is out of the range
   * `edit -r 3 x`: where x is a book that has not been rated
   
   Expected: No rating is edited. A message with error details will be prompted.
   
### Finding a book rating

1. Test case: `find -r Harry Potter`

   Expected: The rating for book titled "Harry Potter" will be shown.
   
2. Other incorrect commands to try:
   * `find -r`: book title field left empty
   * `find -r x`: where x is a book that has not been rated
   
   Expected: No rating is found and listed. A message with error details will be prompted.