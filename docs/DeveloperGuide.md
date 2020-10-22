# **Developer Guide**




## **Introduction**
 ![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/zoomwhitebg.png) <br/><br/>
### **Welcome to Zoomaster!**
Zoomaster is a Java application for the Command Line. It provides you with a simple and intuitive way to store your Zoom links for your online classes, alongside other useful links for the lesson at hand.<br/>

Zoomaster can also intelligently determine the current lesson you are having, allowing you to launch the correct links quickly and elegantly from the command line.


### Table of contents
* [Getting Started](#getting-started)
* [Design](#design)
  * [Architecture](#architecture)
  * [UI component](#architecture)
  * [Logic component](#architecture)
  * [Model component](#architecture)
  * [Storage component](#architecture)
  * [Common classes](#architecture)
* [Implementation](#implementation)
  * [Proposed] Undo/redo feature
  * [Proposed] Implementation
    * Design consideration:
      * Aspect: How undo & redo executes
  * [Proposed] Data archiving
* [Documentation, logging, testing, configuration, dev-ops](#architecture)
* [Appendix: Requirements](#architecture)
  * [Product Scope](#architecture)
  * [User Stories](#architecture)
  * [Use cases](#architecture)
  * [Non-Functional Requirements](#architecture)
  * [Glossary](#glossary)
* [Appendix: Instructions for manual testing](#faq)
  * [Launch and shutdown](#command-summary)


## **Getting Started**
Refer to the setting up guide over [here](https://github.com/AY2021S1-CS2113T-W11-1/tp/releases).






## **Design**

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

{if you have not organized the code into clearly divided components (no penalty if you didn't), you can use a single class diagram (if it is not too complicated) or use several class diagrams each describing a different area of the system.}

### Architecture

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2113T-W11-1/tp/blob/master/src/main/java/seedu/duke/Ui.java)

The UI is responsible for all visual output to the User from the app. It utilises the `Jansi` library to enable the usage of ANSI escape codes to format the console, allowing the app to output in colour. <br/>

The UI also receives input from the User using a `Scanner` object. It returns the input as a String to the main function. <br/>

In addition, the UI contains the different exception and error messages which can be displayed. When a particular exception is thrown (eg. **UNKNOWN_INPUT**), the corresponding function is called in UI to print out the error message (**printUnknownInputMessage()**). 


The `UI` component,

* Receives user commands and returns to the Main function.
* Prints visual output in the console for the User


### Logic component

### Model component

### Storage component

### Common classes
slots class
bookmark class
command class

## **Implementation**
{describe how you implement the features}

### Bookmark and Timetable modes feature (TYS)

#### Implementaion

This feature extends Command class with a way to toggle between different modes of Zoomaster. The integer variable used to control the modes is stored in the Parser class called "programMode". Additionally, it implements the following operation:
* getModeFromCommand() - Decodes the command sent by the users to figure out which mode the user wants to move to.

Given below is a sequential diagram of how changing between modes occur.

![](https://github.com/TYS0n1/tp/blob/team-Branch2/docs/images/sequential%20diagram.png?raw=true) <br/><br/>

Step 1. When the App gets a command from the user to change modes, a new ChangeModeCommand object is created.

Step 2. The ChangeModeCommand passes the command through getModeFromCommand() function to decode the mode the user wishes to change to.

Step 3. The App now executes the command and changes to the respective mode. If an invalid mode was given by the user or if the input field was empty, the execute function throws an exception and tells the user valid modes for the App.

The following activity diagram summarizes what happens when a user executes a new command:

{choose your own adventure diagram}

#### Design consideration:

##### Aspect: How to store programMode variable for security

* **Alternative 1 (Current choice):** No security
    * Pros: Easy to implement
    * Cons: May introduce errors to the App if variable is changed outside of ChangeModeCommand class objects.
* **Alternative 2:** Private variable and implement mode changing inside Parser class
    * Pros: Ensure the App does not change modes outside commands to change modes.
    * Cons: Reduces OOP standard of code by decoupling ChangeModeCommand from Command class and increases code complexity.


## **Product scope**
### Target user profile

Our target users are NUS students with fast typing skills.

### Value proposition

The App was developed during the coronavirus pandemic whereby many NUS classes have been transitioned towards online lessons. NUS lessons are mainly conducted on Zoom video conferencing software. However, as Zoom does not store recurring nor past meetings, it is hard for students to easily access their online lessons. Hence, our App helps to organise students’ Zoom links for easy access to their lesson.

## **User Stories**

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|studious NUS student| bookmark important websites from different modules|launch them easily when needed|
|v1.0|NUS student|label my bookmarks|I can know quickly the topics of each link|
|v1.0|busy NUS student|create a timetable within the app which syncs up with the system time|I will not miss my lessons|
|v1.0|NUS student|take a look at my modules for the day, or the entire week|I can plan out my day/week|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
|v2.0|first time user of the App|be able to see the list commands available|easily naviagate through the App|
|v2.0|advanced user|be able to launch multiple links at the same time if the links are grouped together|save time by not doing multiple launching commands|

## **Non-Functional Requirements**

{Give non-functional requirements}

## **Glossary**

* *glossary item* - Definition

## **Instructions for manual testing**

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
