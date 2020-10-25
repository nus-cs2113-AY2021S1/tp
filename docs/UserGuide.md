# User Guide

## Introduction

Fluffle is a **desktop app for creative writers**, optimized for **use via a Command Line Interface** (CLI). It aids users in creating and managing their writings and ideas. As a fast-typing writer, this CLI app is highly suitable for you.

## Quick Start

1. Check that your computer has Java **11** installed.
1. Get the latest **Fluffle** from [here](https://github.com/AY2021S1-CS2113T-W11-4/tp/releases).
1.Copy the file to the folder you want to use as the home folder.
1.Type in a command prompt ./duke.jar and press Enter.
1.Type the `help` command and press Enter to get started.
1.Some example commands you can try:
    1.`list words` : List the vocabulary stored in the word list.
    1.`filter bunny g/fantasy` : Filters bunny ideas that are of the fantasy genre.
    1.`exit` : Exits the app.
1.Refer to the Features below for details of each command.

## Features 

### Viewing help: `help`
Shows a list of commands you can use and what the commands do. Print the instructions at each stage of the program.  
Output:  
Type “help COMMAND_NAME” to figure out how to use the particular command.  
- help
- settings
- bunny
- list bunny
- filter bunny
- save bunny
- list
- start
- filter 
- stats
- reset
- name
- list name
- filter name
- add name
- delete name
- clear
- exit


Format: `help [COMMAND_NAME]`

Example of usage: 

`help filter name`

### Settings for Fluffle: `settings`
Allows the user to change settings for Fluffle, as well as user profile.  
For the divider, it must be no longer than 40 characters long.  
Format: `settings [NAME]` or `settings [DIVIDER OPTION]`
- The `NAME` is a string indicating the username you want to change to. 
- The `DIVIDER_OPTION` is a parameter indicating your preferable type of line divider.  
The list of dividers is:
   1. ‿︵‿︵‿︵‿︵︵‿︵‿︵‿︵‿︵‿︵‿︵︵‿︵‿︵
   2. •❅──────✧❅✦❅✧──────❅•
   3. \----------------------------------------------------------------
   4. =^..^=   =^..^=   =^..^=    =^..^=    =^..^=    =^..^=    =^..^=
   5. +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

Examples:
- `settings John Doe` changes displaying name to John Doe.
- `settings 2` changes divider option to 2.

### Adding a noun: `noun`
Adds a noun, together with its meaning, into the word bank in the program.  
Format: `noun [WORD] \d[DESCRIPTION]`

### Adding a noun: `noun`
Adds a verb, together with its meaning, into the word bank in the program.  
Format: `verb [WORD] \d[DESCRIPTION]`

### Adding an adjective: `adj`
Adds an adjective, together with its meaning, into the word bank in the program.  
Format: `adj [WORD] \d[DESCRIPTION]`

### Listing words: `list words`
List all words stored in the program.  
Format: list words

### Add bunny idea: `bunny`
Add a bunny idea to the list of bunny ideas in the current run of the program.
This list is not automatically saved.  
Format: `bunny i\IDEA g\[GENRE]`  

Example usages:
- `bunny i\test idea 1`
- `bunny i\test idea 2 g\ fantasy`

Example output:  
`bunny i\test idea 2 g\ fantasy  `  
 `--------------------------------------------------`  
 `New bunny added!`  
   `idea: test idea 2`  
   `genre:  fantasy`  
 `---------------------------------------------------`

### Listing bunny ideas: `list bunny`
List all available quizzes numbered by BUNNY_INDEX numbers.  
Format: `list bunny`  
Example usage: `list bunny`  
Example output:  
`list bunny`  
`-----------------------------------------------------`  
`Here are the bunnies stored in the program:`  
`1.`  
  `idea: test idea 1`  
  `genre: fantasy`  
  
`2.`  
  `idea: test idea 2`  
 `genre: romance`  

`3.`  
  `idea: test idea 3`  
  `genre: none`  
`------------------------------------------------------`

### Filter bunny ideas: `filter bunny`
Filter specific bunny ideas from the list by searching the idea or the genre for specific key terms.  
Format: `filter bunny i\IDEA g\[GENRE]`  
Example usages:
- filter bunny i\test idea 1  
- filter bunny i\test idea 2 g\ fantasy

Example output:
`filter bunny g\ fantasy`  
`------------------------------------------------------`  
`1.`  
  `idea: bunny idea 1`  
  `genre: fantasy`  

`2.`  
  `idea: test idea 2`  
  `genre: fantasy`  

`number bunny filtered: 2`  
`-------------------------------------------------------`

### Save bunny ideas: `save bunny`
Save the current list of bunnies in the program into the designated text file. 
The existing text file is automatically read from at the start of the program and is overwritten when the save function is called.  
Format: `save bunny`  

Example usages:
- `save bunny`

Example output:  
`save bunny`  
`--------------------------------------------------------------`  
`Bunny list saved!`  
`--------------------------------------------------------------`

### Generating names from name database: `name`
Generate a name randomly from the stored database of names.  
Format: `name`

Example usages:
`name`

Example output:  
```
name
--------------------------------------------------------------
name 2
--------------------------------------------------------------
```

### Generating names from name database: `list name`
Displays all the names currently stored in the names database.  
Format: `list name`

Example usages:
`list name`

Example output:  
```
list name
--------------------------------------------------------------
1. name 1
2. name 2
3. name 3
--------------------------------------------------------------
```

### Generating names from name database: `filter name`
Gets the list of names after filtering from the stored list of names.  
Format: `filter name <NAME>`

Example usages:
`filter name 2`

Example output:  
```
filter name 2`  
--------------------------------------------------------------
name 2
--------------------------------------------------------------
```

Example usages:
`filter name name`

Example output:  
```
filter name name
--------------------------------------------------------------
1. name 1
2. name 2
3. name 3
--------------------------------------------------------------
```

### Generating names from name database: `add name`
Adds a name to the list of names currently stored in the names database.
Format: `add name <NAME>`

Example usages:
`add name name 3`

Example output:  
```
add name name 3
--------------------------------------------------------------
name 3 has been added to the Names list!
--------------------------------------------------------------
```

### Generating names from name database: `delete name`
Removes a name from the list of names currently stored in the names database.
Format: `delete name <INDEX>`

Example usages:
`delete name 3`

Example output:  
```
delete name 3
--------------------------------------------------------------
name 3 has been deleted from the Names list!
--------------------------------------------------------------
```

### Filtering words in word bank: `filter`
Filter out the words you need in the list based on your own filtering mode.  
Format: `filter [-continue] by\TYPE_OF_FILTER -ARGUMENTS[1..*]`: 
- -continue (OPTIONAL): this tag is used to tell the program to continue filtering on the last filtered list. 
Without this tag, the previous filtered list would be cleared and the program will filter on the entire word list. 
- TYPE_OF_FILTER: 
    1. type: the next arguments should be “-verb”, “-noun”, “-adjective”.
    2. start: the next arguments should be in the form of -STRING.
    3. include: the next arguments should be in the form of -STRING.

Example usages and example outputs (step by step):
- `filter by\type -noun -adjective`  

`Words filtered by indicated type are:`    
`house: a building for people to live in, usually for one family`  
`grass: a common wild plant with narrow green leaves and stems that are eaten by cows, horses, sheep, etc.`  
`computer: an electronic machine that can store, organize and find information, do processes with numbers and other data, 
and control other machines`  
`class: a group of students who are taught together`  
`beautiful: having beauty; giving pleasure to the senses or to the mind`  
`nice: pleasant or attractive`  
`meaningful : important and serious`  

- `filter -continue by\start -h -gr -co -ni`  

`Words filtered by indicated type are:`   
`house: a building for people to live in, usually for one family`  
`grass: a common wild plant with narrow green leaves and stems that are eaten by cows, horses, sheep, etc.`  
`computer: an electronic machine that can store, organize and find information, do processes with numbers and other data,
and control other machines`  
`nice: pleasant or attractive`  

- `filter -continue by\include -a -pu`  

`Words filtered by indicated type are: `  
`grass: a common wild plant with narrow green leaves and stems that are eaten by cows, horses, sheep, etc.`  
`computer: an electronic machine that can store, organize and find information, `  
`do processes with numbers and other data, and control other machines`

### Begin your writing session: `start`
Requires you to type the following commands for your writings’ configurations:
- `type`
- `topic`  

After choosing the “type” and the “topic” configuration, we can start writing our poems and essays.

### Choosing the “type” of your writing: `type`
Currently, our application offers you with 2 options of “type”, which are “poem” or “essay”.

### Choosing the “topic” of your writing: `topic`
Listing the available topic in the list and pop out the relevant keywords for your writing.
`start`  
`--------------------------------------------------------------`  
`Please indicate your type by typing in "type" command`  
`type`  
`Please let us know your type of writings, either poem or essay`  
`poem`  
`Please let us know your preferred topic, the available are travelling, life story, ghost story, sci-fi, historical`  
`ghost story`  
`Great! The words we have found for you are killing, death and terrify!`  
`Please let us know the title of your writing`  
`Der Erlkönig`  
`Now you can type your content, terminate by typing "end"`  
`Wer reitet so spät durch Nacht und Wind?`  
`Es ist der Vater mit seinem Kind;`  
`Er hat den Knaben wohl in dem Arm,`  
`Er faßt ihn sicher, er hält ihn warm.`  

`Dem Vater grauset's; er reitet geschwind,`  
`Er hält in Armen das ächzende Kind,`  
`Erreicht den Hof mit Mühe und Not;`  
`In seinen Armen das Kind war tot.`  
`end`  
`This Poem, Der Erlkönig has been added`  
`Done! We have added your writing to our storage! You can type "stats" for future reference!`

### Tracking your past writings: `stats`
Inform the user detailed specifications like ids, authors, contents, and other attributes of the writings stored in database. 
 
`stats`  
`This is a Poem`  
`Written by Goethe`  

`Id: 5`  
`DER ERLKÖNIG`  

`Wer reitet so spät durch Nacht und Wind?`  
`Es ist der Vater mit seinem Kind;`  
`Er hat den Knaben wohl in dem Arm,`  
`Er faßt ihn sicher, er hält ihn warm.`  

`Dem Vater grauset's; er reitet geschwind,`  
`Er hält in Armen das ächzende Kind,`  
`Erreicht den Hof mit Mühe und Not;`  
`In seinen Armen das Kind war tot.`  

`This writing was created on 2020-10-18`  
`--------------------------------------------------------------`  

### Clear certain object in database: `clear`
Deletes all items from a list in Fluffle. A warning prompt will be generated before deletion.  

Format: `clear type\TYPE_OF_ITEM item\SPECIFICATION_MARK_OF_THE _OBJECT`  
Example usage:  
`clear type\word item\grass`  
Example Output:  
`Are you sure you want to delete this item grass? Yes/no`

### Closing the program: `exit`
Exits the program. All data is auto-saved.  
Format: `exit`

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data .txt files of your previous Fluffle home folder.

**Q**: Do I need to leave a space before each input tag?

**A**: Yes, the space is necessary. (e.g., add t\science st\chemistry)

**Q**: Do the parameters need to be keyed in in order?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data .txt files of your previous Fluffle home folder.


## Command Summary

| Command      | Format, Examples                                                                                      |
|--------------|-------------------------------------------------------------------------------------------------------|
| settings     | `settings n\[NAME]`                                                                                   |
|              | e.g., settings n\Jane Doe d\2                                                                         |
|              | The list of dividers is                                                                               |
|              | 1. ‿︵‿︵‿︵‿︵︵‿︵‿︵‿︵‿︵‿︵‿︵︵‿︵‿︵                                                             |
|              | 2. •❅──────✧❅✦❅✧──────❅•                                                                           |
|              | 3. ______________________________________                                                             |
|              | 4. =^..^=                                                                                             |
|              | 5. +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+                                                      |
| noun         | `noun [WORD] d\[DESCRIPTION]`                                                                         |
|              | Example: noun bus d\vehicle                                                                           |
| verb         | `verb [WORD] d\[DESCRIPTION]`                                                                         |
|              | Example: verb eat d\to consume                                                                        |
| adjective    | `adj [WORD] d\[DESCRIPTION]`                                                                          |
|              | Example: adj hungry d\having the desire to eat food                                                   |
| list words   | `list words`                                                                                          |
|              | Lists all the words stored in the program.                                                            |
| bunny        | `bunny i\IDEA g\[GENRE]`                                                                              |
|              | Adds a bunny idea to the list of bunnies in the current run.                                          |
| list bunny   | `list bunny`                                                                                          |
|              | Lists all the bunny ideas                                                                             |
| filter bunny | `filter bunny i\IDEA g\[GENRE]`                                                                       |
|              | Filters bunny by terms in idea or genre (must have at least 1 filter parameter)                       |
| save bunny   | `save bunny`                                                                                          |
|              | Saves all the bunny ideas into a text file                                                            |
| filter       | `filter [-continue] by\[TYPE_OF_FILTER] -ARGUMENTS[1..*]  `                                           |
|              | `-continue` (optional): If you want to continue with your last filtered list, provide this argument in your command. |
|              | TYPE_OF_FILTER:<br>type: arguments can be -verb -noun -adjective<br>start: arguments can be -STRING<br>include: arguments can be -STRING|
| name         | `name`                                                                                                |
|              | Generates a random name                                                                               |
| list name    | `list name`                                                                                           |
|              | List all the stored names                                                                             |
| filter name  | `filter name <NAME>`                                                                                  |
|              | Gets the list of names after filtering                                                                |
| add name     | `add name <NAME>`                                                                                     |
|              | Adds a name to the list of stored names                                                               |
| delete name  | `delete name <INDEX>`                                                                                 |
|              | Removes a name from the list of stored names given the index                                          |
| stats        | `stats`                                                                                               |
|              | Show the content of past writings as well as their basic specifications, e.g: number of lines/sentences/type of the writings/date created/ …..|
|              | This is a poem  <br>Written by Goethe                                                                 |
|              | Id: 5 <br> DER ERLKÖNIG                                                                               |
|              | Wer reitet so spät, durch Nacht und Wind?<br>Es ist der Vater mit seinem Kind<br>Er hält den Knaben wohl in den Armen.<br>Er faßt ihn sicher, er hält ihn warm.|
|              |                                                                                                       |
|              | This writing was created on 2020-10-18<br>This poem has 4 lines, 31 words.<br>----------------------------------------------------------------|
| list         | `list`                                                                                                |
| clear        | `clear`                                                                                               |
|              | `clear type\[TYPE_OF_ITEM] item\[SPECIFICATION_MARK_OF_THE _OBJECT]`                                  |
|              | TYPE_OF_ITEM:<br>- word: specify that you are trying to clear a word from word list<br>- Writing: specify that you are trying to clear a writing from writing list|
|              | SPECIFICATION_MARK<br>- If the object is a word, then clear with the respective name<br>- If the object is a writing, then clear with respective id|                                  
| reset        | `reset <NAME_OF_CATEGORY>`                                                                            |
|              | NAME_OF_CATEGORY:<br>- Reset the respective input category database (e.g, bunny, writings, words,...) |
| exit         | `exit`                                                                                                |
|              | Exits the program.                                                                                    |    
