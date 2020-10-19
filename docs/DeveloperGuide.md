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
   
   Expected: No quote is deleted. A message with error details will 
   
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