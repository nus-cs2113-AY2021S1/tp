# User Guide
  ```
   _       _       ____     __       __
| |\ \  | |     / /\ \    \ \     / /
| | \ \ | |    / /__\ \    \ \   / /
| |  \ \| |   / /----\ \    \ \ / /
|_|   \ \_|  / /      \ \    \_V_/    @NUS 
```

- [1. What is Nav@NUS?](#1-what-is-navnus)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
  * [3.1. Check for direct bus: ```/route```](#31-check-for-direct-bus-route)
    + [3.1.1. Examples of usage](#311-examples-of-usage)
  * [3.2. Check bus route: ```/routemap```](#32-check-bus-route-routemap)
    + [3.2.1. Examples of usage](#321-examples-of-usage)
  * [3.3. Check for available bus at a location: ```/bus```](#33check-for-available-bus-at-a-location-bus)
  * [3.3. Check for buses at a bus stop: ```/bus```](#33-check-for-buses-at-a-bus-stop)
    + [3.3.1. Examples of usage](#331-examples-of-usage) 
  * [3.4. List all bus available in NUS: ```/allbus```](#34-list-all-bus-available-in-nus-allbus)
  * [3.5. List all bus stops in NUS: ```/liststops```](#35-list-all-bus-stops-in-nus-liststops)
  * [3.6. Search for dining options within a faculty: ```/dine```](#36-search-for-dining-options-within-a-faculty-dine)
    + [3.6.1. Examples of usage](#361-examples-of-usage)
  * [3.7. Search for specific dining outlet: ```/dineinfo```](#37-search-for-specific-dining-outlet-dineinfo)
    + [3.7.1. Examples of usage](#371-examples-of-usage)
  * [3.8. Exiting the program: ```/exit```](#36-exiting-the-program-exit)
  * [3.9. Listing available help: ```help```](#37-listing-available-help-help)
  * [3.10. Similarity Checks](#38-similarity-checks)
    + [3.10.1. Examples of usage](#381-examples-of-usage)
- [4. FAQ](#4-faq)
- [5. Command Summary](#5-command-summary)
- [6. Glossary](#6-glossary)

## 1. What is Nav@NUS?

Nav@NUS is a useful command line interface (CLI) application to guide you in navigating around the NUS Kent Ridge campus via the school
shuttle services. This application enables you to retrieve key bus information easily, skipping the hassle of
physically checking the bus stop's notice board. Nav@NUS brings convenience to you and wishes your commute in NUS
to be as effortless as possible. Nav@NUS uses a command line interface to facilitate quick typing and retrieval of
information that you require.
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
This command displays the full route of the bus that you have specified. <br>
Format: <code>/routemap <strong>bus code</strong></code> <br>

#### 3.2.1. Examples of usage
<b><u>Example 1</u></b><br>
This command is exceptionally useful to find indirect bus routes.
Let us suppose that you are at Raffles Hall with only bus AA2 available and would like to go to University Town. 
You would notice that there is no direct bus to University Town. You could use the <code>/routemap</code> to find
indirect routes to your intended destination. <br>
These are the steps to follow:
1. You key in <code>/routemap <strong> AA2 </strong></code> into the command line interface and press enter.<br><br>
<img src="UG_Figures/routemap4.png" alt="inputRouteMapCommand" width=800><br>

2. The result will display the whole bus route of bus AA2.<br><br>
<img src="UG_Figures/routemap1.png" alt="RouteMapCommand" width=800><br>

3. With the information that bus AA2 could bring you to bus stops after Raffles Hall e.g. Kent Vale, you can now check 
if there is a direct bus route from these bus stops.<br><br>
<img src="UG_Figures/routemap3.png" alt="inputRouteMapCommand" width=800><br>

<b><u>Example 2</u></b><br>
This command is also useful in showing you the previous bus stops of your intended bus. You could use this information
to gauge how crowded the bus would be.<br><br>
Let us suppose that you are at Raffles Hall intending to board AA2. <br>
These are the steps to follow:
1. You key in <code>/routemap <strong> AA2 </strong></code> into the command line interface and press enter.<br><br>
<img src="UG_Figures/routemap4.png" alt="inputRouteMapCommand" width=800><br>

2. The result will display the whole bus route of bus AA1. You will observe that the bus passes through University Town
which is relatively more crowded than other bus stops.<br><br>
<img src="UG_Figures/routemap1.png" alt="RouteMapCommand" width=800><br>

3. With this information, you could explore other bus routes to your destination.


### 3.3.Check for buses at a bus stop: ```/bus```
This command displays all buses available at a specific bus stop  <br>

The format of this command is as follows: <br>
<code>/bus<strong> bus stop</strong></code> <br>

#### 3.3.1 Examples of Usage
**<u>Example 1</u>** <br>
Let's say that you are at <strong>University Town</strong> bus stop, and you want to know the buses which are available for you to take.

Instead of searching for the bus stops which all the buses stop at, you can easily access this information by using the  <code>/bus</code> command.

To search for available buses at University Town:

1. Type <code>/bus <strong>University Town</strong></code> into the CLI and press enter to execute the command as shown in the figure below. <br><br>
<img src="UG_Figures/bus4.png" width=550><br>
_Figure x: Input command to find buses at University Town._

2. The result will be a message displaying the buses available at University Town. <br><br>
<img src="UG_Figures/bus3.png"><br>
_Figure x: Output message showing buses at University Town._


**<u>Example 2</u>** <br>
Let's say that you are at the <strong>museum</strong> bus stop, and you want to know the buses which are available for you to take. However, you make a spelling error and type <strong>"musuem"</strong> instead. <br>

These are the steps to fix the mistake:

1. The result will be a message displaying bus stop suggestions for possible error in user input.<br><br>
<img src="UG_Figures/bus2.png"><br>
_Figure x: Output message showing suggestions for typo "musuem"._

2. Type <code>/bus <strong>museum</strong></code> into the CLI as suggested in the above output.<br>

3. The result will be a message displaying the buses available at the museum.<br><br>
<img src="UG_Figures/bus1.png"><br>
_Figure x: Input command to find buses at museum._

### 3.4. List all bus available in NUS ```/allbus```
Lists all bus available in NUS. <br>
Example of usage: <code>/allbus</code> <br>
Expected outcome:

<img src="UG_Figures/allbus1.png" alt="inputCommand" width=450><br>

### 3.5. List all bus stops in NUS: ```/liststops```
Lists all bus stops in NUS. <br>
>Note: Index keyed in must be within the range of 1 - n, where n is number of existing tasks. <br>

Format: <code>/liststops</code> <br>
Example of usage: <code>/lisstops</code> <br>
Expected outcome:

<img src="UG_Figures/listOutput.png" alt="inputCommand" width=450><br>

### 3.6. Search for dining options within a faculty: ```/dine```
This command lists out all dining outlets available within a chosen faculty.<br>
The format of this command is as follows:<br> 
<code>/dine <strong>faculty</strong></code> <br>

#### 3.6.1. Examples of Usage

**<u>Example 1</u>**

Let's say you want to know all the dining options available in <strong>School of Business</strong>.<br>
1. Type <code>/dine <strong>business</strong></code> into the command line interface and press enter to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/dine1.png" alt="output" width=600><br>
_Figure x: The expected output message._


### 3.7. Search for specific dining outlet: ```/dineinfo```
Search for all dining outlets that contains the keyword, and display their location and operating hours.<br>
The format of this command is as follows:<br>
<code>/dineinfo <strong>outlet</strong></code> <br>

#### 3.7.1. Examples of Usage

**<u>Example 1</u>**

Let's say you want to find information of the dining outlet <strong>Arise & Shine</strong>.<br>
1. Type <code>/dineinfo <strong>arise</strong></code> into the command line interface and press enter to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/dineinfo1.png" alt="output" width=600><br>
_Figure x: The expected output message._

### 3.8. Exiting the program: ```/exit```
This command helps you exit the application.<br>
The format of this command is as follows:<br>
<code>/exit</code> <br>

The application exits after displaying the following message:<br>
```
So long buddy!
```

### 3.9. Listing available help: ```help```
This command lists a set of features along with their respective commands available to users.<br>
The format of this command is as follows:<br> 
<code>/help</code> <br>

Expected outcome:

<img src="UG_Figures/help1.png" alt="inputCommand" width=450><br>

### 3.10. Similarity Checks

When you enter a location and make a spelling error or a typo in the name, the app performs a similarity check with 
existing location names and suggests some locations to you. The app executes this command automatically and does not 
require any explicit input from you.

#### 3.10.1. Examples of Usage

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
This section addresses some common questions to aid in possible issues faced.

**Q:** Where can I find the release? <br>
It can be found at [here](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases).

## 5. Command Summary
The following table provides a summary of features and command formats.

>Note: No additional parameter is needed if it is not mentioned. eg help <br>

Command | Format | Example
--- | --- | ---
/route | `/route` **location1 /to location2** | `/route` **PGP /to Raffles Hall**
/routemap | `/routemap` **bus code** | `/routemap` **AA1** 
/bus | `/bus` **location** | `/bus` **PGP**
/allbus | `/allbus` | `/allbus`
/liststops | `/liststops`| `/liststops`
/dine | `/dine` **faculty** | `/dine` **business**
/dineinfo | `/dineinfo` **outlet** | `/dineinfo` **arise**
/exit | `/exit` | `/exit`
/help | `/help` | `/help`

## 6. Glossary

1. case-sensitive: Capital and lower case letters are treated differently.
2. Command Line Interface(CLI): Processes commands to a computer program in the form of lines of text.
