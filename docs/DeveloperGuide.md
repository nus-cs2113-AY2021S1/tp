# Developer Guide

## Introduction

### Purpose
This document specified architecture and software design decisions for the creative writing assistant, Fluffle. 

### Scope
This describes the software architecture and software design decisions for the implementation of Fluffle. The intended audience of this document is the developers, designers, and software testers of Fluffle.

### Desgin Goals
Creating an app which improves users’ ability of writing and creativity. Developers should work closely with CS2113T’s module instructors, who represent technical advisors and CS2101’s module instructors, who represent non-technical advisors. By adhering to these tutors’ specific requirements, which imitate industrial professional standards, beginner software engineers will be able to levitate their expertises and mindset in the process of developing and presenting a new product.

### Maintainability
With the aim of increasing maintainability of Fluffle, separated packages and classes were implemented in strict compliance with Object-oriented Programming. Since it is important to be able to easily change functionality of one class without disturbing other dependent classes, each class only serves one purpose to reduce dependency on other classes. With low coupling and high cohesion, subsequent developers can make minimal effort in maintaining Fluffle.

## Definitions

|Terminology|Explanation|
|--------|----------|
|Plot bunny|A story idea that refuses to go away until it is written down.|
|Fluffle|A fluffle is a group of bunnies.| 
|CLI|Command-line Interface. </br></br>A command-line interface (CLI) processes command to a computer program in the form of lines of text. The program in the computer will read the command and start proceeding.|
|Gradle|A build tool used for automated testing. It checks for code style violations and runs unit tests to ensure the code is functional between iterations.| 
|IntelliJ|An integrated development environment that used to write and test java code.|

## Setting up the project in your computer
**Prerequisites:**
* JDK 11
* IntelliJ IDE

<div markdown="span" class="alert alert-warning">

⚠ **Caution:**

Follow the steps in the following guide precisely. Things will not work out if you deviate in some steps.
</div>

First, **fork** this repo, and **clone** the fork into your computer.

If you plan to use Intellij IDEA (highly recommended):
1. **Configure the JDK**: Follow the guide [_[se-edu/guides] IDEA: Configuring the JDK_](https://se-education.org/guides/tutorials/intellijJdk.html) to to ensure Intellij is configured to use **JDK 11**.
1. **Import the project as a Gradle project**: Follow the guide [_[se-edu/guides] IDEA: Importing a Gradle project_](https://se-education.org/guides/tutorials/intellijImportGradleProject.html) to import the project into IDEA.<br>
  :exclamation: Note: Importing a Gradle project is slightly different from importing a normal Java project.
1. **Verify the setup**:
   1. Run the `seedu.trippie.Trippie` and try a few commands.
   2. [Run the tests](Testing.md) to ensure they all pass. 
//Note: should ask team W11-02 the permission for the above part.

## System Overview

### Techonoligies views
The Integrated Development Environment, IntelliJ, is used to develop our program. The program is written in Java, and uses Gradle for building and testing purposes. Our source code is mostly original, with some functions imported from the java.util package. The remaining packages and classes which form the structure of our program are independently developed.

## Architecture
Fluffle Documentation: This document contains all packages and classes that are used in developing Fluffle. 

### Project overview
Fluffle is built using IntelliJ and all concepts for the user interfaces, as well as the backend data management of the application, was created by our team. Due to the restrictions of the project, the main file format used for storage is .txt. We opted to save the data in the text files in a user readable format as opposed to the comma separated format as it is easier for users to directly refer to and edit their saved files.

## Writings class family
### Constitution (member classes)
WritingList: Represent the objects which are particular lists of Writings to be used in the application.
Writings: Represent the objects of the writings, created from user’s input and stored in a database as text. This Writings class is also the parent of 2 subclasses which are Poem and Essay.
Poem: Represents the Writings objects which have type is Poem.
Essay: Represents the Writings objects which have type is Essay.
User: Represents the Users registered to the System



The above class diagram describes the overall architecture of Writings class functionalities and associations within the scope of related classes. By checking “start”, “type” command with checkStartCommand() then checkTypeCommand() methods on that sequence respectively, the user should be able to access the process of creating and saving new writings into the database. During this process, the user has the ability of choosing their preferred type of writings(which are either poem or essay at this stage)


### Filter words
The above class diagram describes the overall architecture of the filter words functionality. FilterExecutor class has the static void method executeFilterCommand that will be called first when the user enters a filter command. In the executeFilterCommand method, it will make use of the enumeration FilterType to get the filter type (by WORD_TYPE, STARTING STRING or INCLUDING_STRING). After that, the method will use the FilterCommandSlicer static methods isNewFilter to determine whether the user wants to continue on the last filtered list or start a new filter on an entire word bank. Subsequently, depending on the filter mode, getTargetedWordTypes or getTargetedStringTags will be called and the returned array of strings will be passed to WordsFilter’s static methods filterByType, filterByStartingString and filterByIncludedString.
The following sequence diagram shows how the components interact with each other for the scenario where the user issues the command `filter -continue by\start limit\10 -cs -cg.`
   
   
## Bunny class family
The above class diagram describes the overall architecture of the bunny list functionalities. The BunnyList class has the public ArrayList of bunnies bunniesList that is accessed by the DeleteBunny class method deleteBunny which removes a selected bunny from the bunniesList ArrayList. Similarly, bunniesList is also accessed by the BunnyFilter class which contains the filterBunny function which can filter through the list and obtain bunnies with specified keywords in the idea or the genre using the command `filter bunny i\IDEA g\GENRE`, where the user may choose to omit either the `IDEA`or the `GENRE` when running the command

The BunnySaver class accesses the bunniesList and overwrites the current bunny.txt file in the data directory, saving all Bunny objects into the file using the saveAllBunny  method. Bunny objects saved in that file can then be read by the BunnyLoader class and saved into the bunniesList ArrayList using the loadBunnyFile method.


## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
