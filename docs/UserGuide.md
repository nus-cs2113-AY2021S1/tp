# User Guide
  ```
*Insert NAV@NUS LOGO here 
```

- [1. What is Nav@NUS?](#1-what-is-navnus)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
  * [3.1. Check for direct bus: ```/route```](#31-check-for-direct-bus-route)
    + [3.1.1. Examples of Usage](#311-examples-of-usage)
  * [3.2. Check bus route: ```/routemap```](#32-check-bus-route-routemap)
  * [3.3.Check for available bus at a location: ```/bus```](#33check-for-available-bus-at-a-location-bus)
  * [3.4. List all bus available in NUS: ```/allbus```](#34-list-all-bus-available-in-nus-allbus)
  * [3.5. List all bus stops in NUS: ```/liststops```](#35-list-all-bus-stops-in-nus-liststops)
  * [3.6. Search for dining options within a faculty: ```/dine```](#36-search-for-dining-options-within-a-faculty-dine)
  * [3.7. Search for specific dining outlet: ```/dineinfo```](#37-search-for-specific-dining-outlet-dineinfo)
  * [3.8. Exiting the program: ```/exit```](#36-exiting-the-program-exit)
  * [3.9. Listing available help: ```help```](#37-listing-available-help-help)
  * [3.10. Similarity Checks](#38-similarity-checks)
    + [3.10.1. Examples of Usage](#381-examples-of-usage)
- [4. FAQ](#4-faq)
- [5. Command Summary](#5-command-summary)
- [6. Glossary](#6-glossary)

## 1. What is Nav@NUS?

Nav@NUS is a useful CLI application to guide users in navigating around the NUS Kent Ridge campus via the school
shuttle services.
[add more about the app]

## 2. Quick Start
The following steps will guide you through the process of running Nav@NUS.

1. Ensure that you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for dude.jar bus application
4. In command prompt, `cd` to the directory of the _home folder_.
5. Run the .jar file in the command prompt as follows: `java -jar duke.jar`
6. Type `help` to view commands available.
7. Refer to [Features](#features) below for details of each command

## 3. Features 
There are 7 features available in Nav@NUS. The following are instructions for using the features.

>Notes about command format:  
>
>1. Words in **bold** are parameters to be provided by the user.
>2. Parameters to be entered by the user are not case-sensitive.

### 3.1. Check for direct bus: ```/route```
This command displays all bus routes from one location to another that do not require changing buses.

The format of this command is as follows: <br>
<code>/route <strong>location_1 /to location_2 </strong> </code>

#### 3.1.1. Examples of Usage

**<u>Example 1</u>**

Let's say you are currently at PGP and want to find out the buses you can board from PGP bus station to get to NUS IT.
To find all such bus routes:
1. Type <code>/route <strong> PGP /to NUS IT</strong></code> into the command line interface and press enter 
to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/routeInput1.png" alt="inputCommand" width=450><br>
_Figure 1: The input command to be given to find out bus routes from PGP to NUS IT._

2. The result will be a message displaying the list of buses you can take with their routes as shown in the figure below.<br><br>
<img src="UG_Figures/routeOutput1.png" alt="output" width=650><br>
_Figure 2: The output message seen._

**<u>Example 2</u>**

Let's say you are currently at University Health Centre and you want to go to PGPR. But you accidentally type "Univerity 
Health Center" instead. These are the steps to follow: 

1. You type <code>/route <strong> Univerity Health Center /to PGPR</strong></code> into the command line interface and 
press enter to execute the command as done in example 1.

2. The result will be a message displaying suggestions for possible spelling errors you may have made.<br><br>
<img src="UG_Figures/routeOutput2.png" alt="output" width=550><br>
_Figure 3: The output message showing possible spelling errors._

3. Type <code>/route <strong> University Health Centre /to PGPR</strong></code> into the command line interface
following the suggestion given.

4. The result will be a message displaying the list of buses you can take with their routes as shown in the figure 
below.<br><br>
<img src="UG_Figures/routeOutput3.png" alt="FinalOutput" width=650><br>
_Figure 4: The output message showing bus routes._

### 3.2. Check bus route: ```/routemap```
Checks for user-specified bus route. <br>
Format: <code>/routemap <strong>task description</strong></code> <br>
        Upon receiving prompt, specify 
        <code><strong>bus route name</strong></code> <br>
Example of usage: <code>/routemap <strong> AA1 </strong></code> <br>
Expected outcome: 
```
/routemap
What bus routes would you like to see?
Currently, we have two bus routes available for your viewing 
1.AA1 
2.AA2 
Type the name to view:
aa1
Here is the AA1 route that you have requested:
AA1
PGP-> Kent Ridge MRT Station-> Opp University Health Centre-> Yusof Ishak House-> Central Library-> Kent Ridge-> Museum-> University Town-> University Health Centre-> Opp Kent Ridge MRT station-> PGPR
```

### 3.3. Check for available bus at a location: ```/bus```
Checks for available bus at a user-specified location <br>
Format: <code>/bus <strong>bus code</strong></code> <br>
Example of usage: <code>/bus<strong> PGP</strong></code> <br>
Expected outcome: 
```
PGP:  AA1   AA2
```

### 3.4. List all bus available in NUS ```/allbus```
Lists all bus available in NUS. <br>
Example of usage: <code>/allbus</code> <br>
Expected outcome:
```
The buses available in NUS are: 
AA1
PGP-> Kent Ridge MRT Station-> Opp University Health Centre-> Yusof Ishak House-> Central Library-> Kent Ridge-> Museum-> University Town-> University Health Centre-> Opp Kent Ridge MRT station-> PGPR
AA2
PGP-> Kent Ridge MRT Station-> Opp University Health Centre-> University Town-> Raffles Hall-> Kent Vale-> EA-> NUS IT-> University Health Centre-> Opp Kent Ridge MRT station-> PGPR
```

### 3.5. List all bus stops in NUS: ```/liststops```
Lists all bus stops in NUS. <br>
>Note: Index keyed in must be within the range of 1 - n, where n is number of existing tasks. <br>

Format: <code>/liststops</code> <br>
Example of usage: <code>/liststops</code> <br>
Expected outcome:
```
PGP : Prince George's Park
Kent Ridge MRT Station : Kent Ridge MRT entrance
Opp University Health Centre : Near University Health Centre
Yusof Ishak House : Location with the Student Service center
Central Library : also known as CLB
Kent Ridge : Kent Ridge
Museum : Opposite Faculty of Engineering
University Town : Place filled with dining and recreational options
University Health Centre : UHC, where students can get MC
Opp Kent Ridge MRT station : Opposite Kent Ridge MRT
Raffles Hall : Hall located near Faculty of Engineering
Kent Vale : Accommodation for visiting faculty guests
EA : Located in Faculty of Engineering
NUS IT : Opposite Central library
PGPR : Prince George's Park Residence
```

### 3.6. Search for dining options within a faculty: ```/dine```
Lists out all dining outlets available within a chosen faculty.<br>
Format: <code>/dine <strong>faculty</strong></code> <br>
Example of usage: <code>/dine <strong>business</strong></code> <br>
Expected outcome:<br>
<img src="UG_Figures/dine1.png" alt="output" width=650><br>
_Figure 2: The output message seen._


### 3.7. Search for specific dining outlet: ```/dineinfo```
Search for all dining outlets that contains the keyword, and display their location and operating hours.<br>
Format: <code>/dineinfo <strong>keyword</strong></code> <br>
Example of usage: <code>/dineinfo <strong>arise</strong></code> <br>
Expected outcome:<br>
<img src="UG_Figures/dineinfo1.png" alt="output" width=650><br>
_Figure 2: The output message seen._

### 3.8. Exiting the program: ```/exit```
This command helps you exit the application.<br>
The format of this command is as follows:<br>
<code>/exit</code> <br>

The application exits after displaying the following message:<br>
```
So long buddy!
```

### 3.9. Listing available help: ```/help```
This command lists a set of features along with their respective commands available to users.<br>
The format of this command is as follows:<br> 
<code>/help</code> <br>

Expected outcome:
```
Here are the range of commands:
1./route : Displays possible direct bus from point to point
2./routemap: Displays the route map with its intermediate bus stops
3./bus: Displays buses available at each bus stop
4./allbus: Lists all buses available in NUS Zone a
5./liststops: Lists all bus stops in NUS Zone a
6./dine: Search for dining options within a faculty
7./dineinfo: Search for a specific dining outlet
8./exit: Exit program
9./help: List all available commands
```

### 3.8. Similarity Checks

When you enter a location and make a spelling error or a typo in the name, the app performs a similarity check with 
existing location names and suggests some locations to you. The app executes this command automatically and does not 
require any explicit input from you.

#### 3.8.1. Examples of Usage

**<u>Example 1</u>**

Let's say you want to find all buses that stop at Opp HSSML, but you type `/bus Opp HSML` instead.
You will receive a message with suggested location names you can use as shown in the figure below.<br><br>
<img src="UG_Figures/similarOutput1.png" alt="similar locs message" width = 550><br>
_Figure 5: A message showing suggested locations._

You may then type in the command again with the correct location to see a list of buses that stop at Opp HSSML 
as shown in the figure below.<br><br>
<img src="UG_Figures/similarOutput2.png" alt="Correct input message" width=400><br>
_Figure 6: Result when the input is correct._

>Note: This check is only applicable to bus stop names, so the app performs it only when you enter a 
><code>/route</code> command or a <code>/bus</code> command. 

## 4. FAQ
Some common questions to aid in possible issues faces.

**Q:** Where can I find the release? <br>
It can be found at [here](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases).

## 5. Command Summary
The following table provides a summary of features and command formats.

>Note: No additional parameter is needed if it is not mentioned. eg help <br>

Command | Format | Example
--- | --- | ---
/route | `/route` **location1 /to location2** | `/route` **PGP /to Raffles Hall**
/routemap | `/routemap` **bus code** | `deadline` **/routemap AA1** 
/bus | `/bus` **location** | `event` **/bus PGP**
/allbus | `/allbus` | `/allbus`
/liststops | `liststops`| `/liststops`
/exit | `/exit` | `/exit`
/help | `/help` | `/help`

## 6. Glossary

1. case-sensitive: Capital and lower case letters are treated differently.
2. Command Line Interface(CLI): Processes commands to a computer program in the form of lines of text.
