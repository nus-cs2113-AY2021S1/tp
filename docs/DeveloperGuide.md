# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Logic Component

##### Design

![Parser Diagram](./images/ParserDiagram.png)

1. After constructing a new MenuParser() in the Eduke8.java class, the parseCommand() method is used to parse the 
   user command.
2. This results in a Command object, which is executed by Command class itself, using the execute() method.
3. The Ui in the Command object is used to display the requested information, or to display the required task to be 
   completed as per the user input.
   
Below is the sequence diagram for how the Parser component of Eduke8 works with commands to show output to the user.

![Parser Sample Sequence](./images/ParserSampleSequence.png)

#### Command parsing feature

##### Implementation

The command parsing feature is our program’s way of reading the user’s input into the command line. It makes use of a 
single method parseCommand that identifies what command the user is calling for and then calls the command. There are 
two parsers in our program that implements a single Parser interface. One parser is for choosing menu options and is 
named MenuParser. The other parser is used during quizzes, in order to answer questions or request for hints, and is 
called QuizParser. Given below is an example usage scenario of how the command parsing feature works at each step, when 
the user types in input to get help in order to see what commands are available to the user.

Step 1. The user launches the program for the first time. The parser will be initialised and awaiting the user’s input 
        to proceed.
        
Step 2. The user types in help into the command line interface and presses enter. This user input “help” is stored as 
        a string and is put into the parseCommand() method as a parameter, together with the topic list. This topic 
        list is not relevant to the help command for now.
        
Step 3. The user input string is subjected to the trim() and split() functions of a string in the Java libraries in 
        order to remove redundant spaces around the input, and to discern the number of words in the input. The split() 
        function uses a blank space string, “ “, as the delimiter to split the string into its individual components.
        
Step 4. Each subsequent string separated by a space is stored in a string array named commandArr. The 0th index of the 
        commandArr array is the first word, the 1st index is the second word, and so on. In this case there is only one 
        word stored in the array, at the 0th index, which is “help”.
        
Step 5. The string at the 0th index is then used in a switch statement, where each case represents the different menu 
        options available. As such, the contents of the case with reference “help” is run, which is a return statement 
        containing a new HelpCommand(). This leads to the execution of the help command.

## Product scope
### Target user profile

CS2113/T Students

### Value proposition

Help CS2113/T students learn and understand software engineering and OOP principles through a gamified platform and enhance their learning experience. Consolidate key concepts for easy revision.

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
