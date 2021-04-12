# Developer Guide

DISCLAIMER: PlantUML currently does not support hiding of lifelines after objects are destroyed.

* For instance, CloseCommand is destroyed after signalling imminent exit:

<img src="diagrams/CloseCommand.png" width="400" height="200"/>

* However, due to limitations of PlantUML, the lifeline will still be present after destroying CloseCommand.
<div style="page-break-after: always;"></div>

## Table of Contents

* [Design & Implementation](#design--implementation)
    * [Architecture](#architecture)
    * [Api Component](#api-component)
    * [Storage Component](#storage-component)
    * [Ui Component](#ui-component)
    * [Data Component](#data-component)
    * [Parser Component](#parser-component)
    * [Command Component](#command-component)
    * [Common Component](#common-component)  
* [Product scope](#product-scope)
    * [Target user profile](#target-user-profile)
    * [Value proposition](#value-proposition)
    * [User Stories](#user-stories)  
* [Manual Testing Instructions](#manual-testing-instructions)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Launch and Shutdown](#launch-and-shutdown)
    * [Filter conditions](#filter-conditions)
    * [Find units matching filter conditions](#find-units-matching-filter-conditions)
    * [Shortlisting units from search results](#shortlisting-units-from-search-results)
    * [Sorting results by price in ascending or descending order](#sorting-results-by-price-in-ascending-or-descending-order)
* [Error Handling](#error-handling)
    * [Commands without Sufficient Parameters](#commands-without-sufficient-parameters)
    * [Commands with Invalid Parameters](#commands-with-invalid-parameters)
    * [Commands with Invalid Filters](#commands-with-invalid-filters)
* [Glossary](#glossary)
<div style="page-break-after: always;"></div>

## Design & Implementation

### Architecture

<img src="diagrams/architecture.jpg" width="400" height="450"/>

The Architecture Diagram given above explains the high-level design of the App.

The App consists of:

* [**`Api`**](#api-component): Retrieves data on resale flats from server.
* [**`Storage`**](#storage-component): Reads shortlisted units from, and writes shortlisted units to, the text file.
* [**`Ui`**](#ui-component): Communicates with user via messages. Contains a sole TextUi class.
* [**`Data`**](#data-component): Contains shortlist, user input history and temporarily tracks search history. Contains SearchedUnits, ShortList, and UserInput. All of which do not interact with each other.
* [**`Parser`**](#parser-component): Translate user input to valid commands to be executed.
* [**`Command`**](#command-component): The command executor.
* [**`Common`**](#common-component): Models of objects used internally. Includes keys, exceptions, logger and the Unit class.

<div style="page-break-after: always;"></div>

### Api component

The `Api`

* receives a raw query from Find Command, which will contain filter conditions.
* creates a connection to remote server containing data on resale flats.
* formats raw query into valid query to be sent as a GET request.
* updates all flats matching filter conditions in SearchedUnits class of Data component.

The *Class Diagram* below shows the different classes within Api. The entry point will be ApiRepository, accessed by Command.

<img src="diagrams/ApiClass.png" width="500" height="500"/>

<div style="page-break-after: always;"></div>

**How classes within Api component interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where a Find command is executed.

<img src="diagrams/ApiSequence.png" width="700" height="700"/>

<div style="page-break-after: always;"></div>

### Storage component

The `Storage`

* handles read and write of units into a local text file.
* manages data in ShortList.

The *Class Diagram* below shows the different classes within Storage. The entry point will be StorageManager, accessed by Data.

<img src="diagrams/StorageClass.png" width="400" height="300"/>

<div style="page-break-after: always;"></div>

**How classes within Storage component interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where a Save command is executed.

<img src="diagrams/StorageSequence.png" />

<div style="page-break-after: always;"></div>

### Ui component

The `Ui`

* Retrieves user input from CLI.
* Display results to user in CLI.

For Help messages, the UI will call the TermDefinition class to retrieve the definition of the term, which is shown in the diagram below.

<img src = "diagrams/TextUiDefinition.png" />

If the user enters the command with the wrong format, the UI will call the TermDefinition class to retrieve the example of the term, which is shown in the diagram below.

<img src = "diagrams/TextUiExample.png" />

<div style="page-break-after: always;"></div>

### Data component

The `Data`

* Static classes that can be referred by other components to retrieve/modify/store data.

### Parser component

The `Parser`

* Receives the full user input from receiveCommand().
* Calls for creation of a Command type object and returns to HdBuy.

**How other classes interact with Parser component**

The *Sequence Diagram* below shows how the components interact with each other when user enters a command from CLI.

<img src="diagrams/ParserSequence.png" width="600" height="450"/>

The CommandEvaluator class extracts the information from the input and thereafter passes a keyCommand to the Parser class for it to map and retrieve the appropriate Command to the HdBuy.

<div style="page-break-after: always;"></div>

### Command component

The `Command`

* Can be executed to carry out tasks.

The *Class Diagrams (broken into 2 diagrams for easy viewing)* below shows the different Commands that can be mapped and retrieved by Parser.

<img src="diagrams/CommandClass.png" width="600" height="280"/>
<img src="diagrams/CommandClass2.png" width="600" height="280"/>

<div style="page-break-after: always;"></div>

**How classes interact with a Command object during execution**

The *Sequence Diagram* below shows how the components interact with each other when the respective commands are executed:

`FilterCommand`

<img src="diagrams/FilterCommand.png" width="600" height="400"/>

<div style="page-break-after: always;"></div>

`FindCommand`

<img src="diagrams/FindCommand.png" width="600" height="400"/>

`RemoveCommand`

<img src="diagrams/RemoveCommand.png" width="600" height="400"/>

<div style="page-break-after: always;"></div>

`SaveCommand`

<img src="diagrams/SaveCommand.png" width="600" height="400"/>

`ShortlistCommand`

<img src="diagrams/ShortlistCommand.png" width="600" height="400"/>

<div style="page-break-after: always;"></div>
 
`SortCommand`

<img src="diagrams/SortCommand.png" width="600" height="400"/>

`ClearCommand`

<img src="diagrams/ClearCommand.png" width="600" height="300"/>

<div style="page-break-after: always;"></div>

`CloseCommand`

<img src="diagrams/CloseCommand.png" width="600" height="300"/>

`HelpCommand`

<img src="diagrams/HelpCommand.png" width="600" height="300"/>

<div style="page-break-after: always;"></div>

`ListCommand`

<img src="diagrams/ListCommand.png" width="600" height="300"/>

`DefaultCommand` is a placeholder command, it will not be executed.

### Common component

The `Common`

* Utility classes such as keys and exceptions.

<div style="page-break-after: always;"></div>

## Product scope
### Target user profile

Low-to-middle income single users looking to buy resale flats.

### Value proposition

Easily find and bookmark resale flats available matching user's preference.

### User Stories

|Version| As a ... | I want to ... | So that I can ...| Priority|
|--------|----------|---------------|------------------|------------------|
|v1.0|user|find units by location|search for resale flats near workplace| Must have|
|v1.0|user|find units by flat type|search for resale flats large enough for family| Must have|
|v1.0|user|find units by remaining lease|search for resale flats to be sold again in future| Must have|
|v2.0|new user|see usage instructions|understand all the available commands| Nice to have|
|v2.0|returning user|bookmark potential flats|keep track of potential flats| Nice to have|
|v2.0|user|sort flats by price, in either ascending or descending order|view flats matching budget| Nice to have|

<div style="page-break-after: always;"></div>

## Manual Testing Instructions

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Requires internet connection.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Disable caps lock. All commands are to be in `small caps` to be recognised.

### Launch and Shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder.<br>

   2. Execute the jar file (`java --jar <filename>.jar`) <br>
      Expected: Greeting message "Welcome to HDBuy! How may I help you today?".

2. Shutdown and relaunch

   1. Test case: `exit` <br>
      Expected: Terminating message "HDBuy Bye Bye!"

   2. Re-launch the app by executing the jar file <br>
      Expected: Greeting message. If there is a shortlist, text file `shortlist.txt` will be seen in folder.
       
<div style="page-break-after: always;"></div>

### Filter conditions

3. Setting filter condition(s) before searching for units (type of conditions: location, type, lease_remaining)

   1. Test case: `filter location jurong` <br>
      Expected: Filter condition is temporarily saved. Message: "Filter conditions:{location=jurong}"

   2. Test case: `filter type 4 room` <br>
      Expected: Another condition will be saved. Message: "Filter conditions:{type=4 room, location=jurong}"

   3. Incorrect inputs to try: `filter`, `filter 0`, `filter location` (filter condition missing type and parameter) <br>
      Expected: Help message to guide user to fill in with correct inputs. Invalid input is not saved.

4. List all filter condition(s)

   1. Test case: `list` <br>
      Expected: Shows all filter condition(s). Similar to test 3. If no conditions set previously, shows message: "Currently there are no filter conditions set."

   2. Incorrect inputs to try: `list x` (where x can be input string) <br>
      Expected: A message that mentions that command is invalid and `list` command does not need any parameters.
      
5. Clear all filter condition(s)

   1. Test case: `clear` <br>
      Expected: Clear all conditions. Shows message: "Cleared filter conditions."

   2. Incorrect inputs to try: `clear x` (where x can be input string) <br>
      Expected: A message that mentions that command is invalid and `clear` command does not need any parameters.
      
<div style="page-break-after: always;"></div>

### Find units matching filter conditions

6. Searching for units with and without filter conditions set.

   1. Test case: `find` with at least one filter condition set using `filter` <br>
      Expected: Up to 100 units matching filter condition(s) will be shown.

   2. Test case: `find` without any filter conditions set <br>
      Expected: Message stating `find` has no parameters currently.", followed by assistance message.

   3. Incorrect inputs to try: `find x` (where x can be input string) <br>
      Expected: This is an invalid command. Please try the following: `find` command does not need any parameters.

### Sorting results by price

   1. Test case: `sort asc` to display unit(s) in ascending order of price <br>
      Expected: Units from search results are sorted from lowest to highest price.

   2. Test case: `sort desc` to display unit(s) in descending order of price <br>
      Expected: Units from search results are sorted from highest to lowest price.

   3. Incorrect inputs to try: `sort` <br>
      Expected: Message stating that this is an invalid command, followed by a positive example.
      
   4. Incorrect inputs to try: `sort asc` or `sort desc` without executing `find` successfully <br>
      Expected: Message to inform user to perform unit search first.
      
<div style="page-break-after: always;"></div>

### Shortlisting units from search results

7. Saving a unit from search result to shortlist, requires at least 1 `find` to be executed prior.

   1. Test case: After `find`, input `save 1` to save first unit from result to shortlist <br>
      Expected: Message stating the unit being saved to shortlist. Also displays the units' properties.
   
   2. Incorrect inputs to try: `save` <br>
      Expected: Message stating usage of invalid command, followed by a positive example.
      
   3. Incorrect inputs to try: `save x` (where x is not any of the indexes shown in search results) <br>
      Expected: Message stating x is out of range. Unit is not saved.

8. Removing a unit from shortlist, requires user to have at least 1 unit in shortlist

   1. Test case: `remove 1` to remove the first unit in shortlist <br>
      Expected: Message stating the unit being removed from shortlist. Also displays the units' properties.
   
   2. Incorrect inputs to try: `remove` <br>
      Expected: Message stating usage of invalid command, followed by a positive example.
      
   3. Incorrect inputs to try: `remove x` (where x is not any of the indexes shown in shortlist) <br>
      Expected: Message stating x is out of range. Unit is not removed.

9. Retrieving shortlist

   1. Test case: `shortlist` to display unit(s) in shortlist <br>
      Expected: Display units in shortlist. If shortlist is empty, message "There are no flats to be shown." is shown.
   
   2. Incorrect inputs to try: `shortlist x` (where x is any string input) <br>
      Expected: Message stating usage of invalid command. `shortlist` command does not need any parameters.

<div style="page-break-after: always;"></div>

## Error Handling

The app will return error data that the user can use to identify and resolve incorrect formats in the command line. The
app also handles errors occur during the invocation of API calls. In general, the app provides the following types of 
error handling.

* For errors resulting from incorrectly formatted command lines, the app returns an error message with suggestions on
improving the command line.
     
* For errors caused by faulty API calls, the app will return an error message and will ask the user to restart the
app to attempt reconnection.

* All commands must be in `small caps` to be recognised.

### Commands without Sufficient Parameters

When user initiates a `find` command without already setting a parameter to search, the app will not accept the input and
will return an exception `EmptyParameterException` and asks the user to input a valid filter parameter first.

### Commands with Invalid Parameters

When user enters a command to be parsed through `Parser()` with the wrong number of parameters, the app will not 
continue the process and will return an exception `InvalidParameterException` and showcase user the correct parameters 
to use.

### Commands with Invalid Filters

When user initiates a `filter` command with an invalid filter, the app will return an exception `InvalidFilterException`
and will list out all the possible filters.


## Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
