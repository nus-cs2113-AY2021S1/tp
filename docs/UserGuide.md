---
layout: page
---

![Logo](UG_Figures/Nav@NUS_UG_Cover.png)

<div style="page-break-after: always;"></div>

## Opening words
Welcome to Nav@NUS application's user guide! <br><br>
The purpose of this user guide is to provide you with all the necessary information to use this application to navigate
around NUS campus via the school's shuttle service.<br>

<div style="page-break-after: always;"></div>

## Table of Contents

- [1. Overview](#1-overview)
  * [1.1 What is Nav@NUS?](#11-what-is-navnus)
  * [1.2 About the User Guide](#12-about-the-user-guide)
  * [1.3 Introduction to Command Line Interface (CLI)](#13-introduction-to-command-line-interface-cli)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
  * [3.1. Bus Features](#31-bus-features)
    + [3.1.1. Check for direct bus: ```/route``` (Wamika)](#311-check-for-direct-bus-route-wamika)
    + [3.1.2. Check bus route: ```/routemap``` (Johnson)](#312-check-bus-route-routemap-johnson)
    + [3.1.3. Check for buses at a bus stop: ```/bus``` (Heng Chin)](#313-check-for-buses-at-a-bus-stop-bus-heng-chin)
    + [3.1.4. List all buses available in NUS ```/allbus``` (Shuyi)](#314-list-all-buses-available-in-nus-allbus-shuyi)
    + [3.1.5. List all bus stops in NUS: ```/liststops``` (Yuxin)](#315-list-all-bus-stops-in-nus-liststops-yuxin)
    + [3.1.6. List all faculties in NUS: ```/faculty``` (Shuyi)](#316-list-all-faculties-in-nus-faculty-shuyi)
  * [3.2. Dine Features](#32-dine-features)
    + [3.2.1. Search for dining options within a faculty: ```/dine``` (Shuyi)](#321-search-for-dining-options-within-a-faculty-dine-shuyi)
    + [3.2.2. Search for specific dining outlet: ```/dineinfo``` (Shuyi)](#322-search-for-specific-dining-outlet-dineinfo-shuyi)
  * [3.3. Favourite Features](#33-favourite-features)
    + [3.3.1. Add a favourite command: `/addfav` (Yuxin)](#331-add-a-favourite-command-addfav-yuxin)
    + [3.3.2. List all favourite commands: `/listfav` (Shuyi)](#332-list-all-favourite-commands-listfav-shuyi)
    + [3.3.3. Delete a favourite command: `/deletefav` (Johnson)](#333-delete-a-favourite-command-deletefav-johnson)
    + [3.3.4. Execute a favourite command: `/execfav` (Heng Chin)](#334-execute-a-favourite-command-execfav-heng-chin)
    + [3.3.5. Change the description for a favourite command: `/descfav` (Wamika)](#335-change-the-description-for-a-favourite-command-descfav-wamika)
    + [3.3.6. Clear the list of favourite commands: `/clearfav` (Shuyi)](#336-clear-the-list-of-favourite-commands-clearfav-shuyi)
    + [3.3.7. Store favourite commands - Heng Chin](#337-store-favourite-commands-heng-chin)
  * [3.4. Common Features](#34-common-features)
    + [3.4.1. Check for similar locations (Wamika)](#341-check-for-similar-locations-wamika)
    + [3.4.2. Display most searched bus stop on start-up (Johnson)](#342-display-most-searched-bus-stop-on-start-up-johnson)
    + [3.4.3. Reset frequent search data: ```/reset``` (Johnson)](#343-reset-frequent-search-data-reset-johnson)
    + [3.4.4. List available help: ```/help``` (Yuxin)](#344-list-available-help-help-yuxin)
    + [3.4.5. Exit the program: ```/exit```](#345-exit-the-program-exit)
- [4. FAQ](#4-faq)
- [5. Command Summary](#5-command-summary)
- [6. Glossary](#6-glossary)

<div style="page-break-after: always;"></div>

## 1. Overview
### 1.1 What is Nav@NUS?
Are you new to NUS? <br>
Are you searching for ways to get around NUS, all squeezed up in front of a tiny information board?<br>
We have just the right solution for you!<br><br>
Introducing **Nav@NUS**, your new navigation assistant!
Nav@NUS is a useful command line interface **(CLI)** application to guide you in navigating around the NUS campus
via the school's shuttle services. This application enables you to retrieve key bus information easily, skipping the 
hassle of physically checking the bus stop's notice board. Nav@NUS is a tool tailor-made for anyone unfamiliar with 
NUS campus, students, professors and visitors included. Nav@NUS brings convenience to you and wishes that your 
commute in NUS is as effortless as possible. Nav@NUS uses a CLI to facilitate quick typing and retrieval of 
information that you require.

Nav@NUS consists of 3 main features:

* **Route**: Searches for bus routes from your location to your intended destination.
* **Dine**: Seeks dining options for you to explore the culinary world of NUS.
* **Favourites**: Saves your commands to text files for you to have a personalised user experience catered to your needs.

Skip the tight squeeze near information boards and use Nav@NUS today!

### 1.2 About the User Guide
This user guide introduces you to the features available in Nav@NUS. Step-by-step guides are provided along with 
instances when the features are used.<br>
This user guide covers the following:
 * How to use the Command Line Interface
 * How to set up Nav@NUS
 * Common instances when each feature is used
 * Step-by-step instructions for using each feature
 * Common errors or problems faced when using features
 * Frequently asked questions

<div style="page-break-after: always;"></div>

### 1.3 Introduction to Command Line Interface (CLI)
Nav@NUS sets up and runs on the CLI. As the CLI is not commonly used, it can seem daunting to users. 
To give you a better experience, this section will introduce you to the CLI.

Orientate yourself to the CLI. As seen in each figure below, the red arrow points to
where you have to type in commands.<br>

For computers running the Windows OS, the red arrow points to where you have to type in commands. <br>
<img src="UG_Figures/windowsCLI.png" alt="Windows CLI" width=650><br>

For computers running the macOS, the red arrow points to where you have to type in commands. <br>
<img src="UG_Figures/appleCLI.png" alt="Apple CLI" width=650><br>

<!-- @@author Johnson-Yee -->
## 2. Quick Start
The following steps will guide you through the process of running **Nav@NUS**.

1. Ensure that you have Java `11` or above installed in your computer. If you do not have it installed,
follow the guide [here](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA).
2. Download the latest `Nav@NUS.jar` from [here](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases).
3. Open command prompt on your computer.
4. Copy the jar file to the folder you want to use as the _home folder_ for Nav@NUS.jar application. In the example
shown in the figure, the home folder is found in the address path of "C:\Users\Johnson Yee\Desktop\CS2113T Empty folder".<br><br>
<img src="UG_Figures/windowsPath.png" alt="Windows Path" width=650><br>
5. Navigate back to the command prompt, type `cd` and the directory of the _home folder_. Press <kbd>Enter</kbd> to continue.<br><br>
<img src="UG_Figures/cd_command.png" alt="cd_Command" width=650><br>
6. Run the .jar file in the command prompt as follows by typing `java -jar Nav@NUS.jar` and press <kbd>Enter</kbd>.
7. Your screen should show the start screen of Nav@NUS as seen in the figure below.<br><br>
<img src="UG_Figures/Nav@NUSstartScreen.png" alt="Start Screen" width=650><br>
8. Try typing `/help` and press <kbd>Enter</kbd>!
<!-- @@author -->

<div style="page-break-after: always;"></div>

## 3. Features 
There are 17 commands available in Nav@NUS. The following are instructions for using the features.
>Notes about command format:  
>
>1. Words in **bold** are parameters to be provided by the user. (e.g. **location_1**)
>2. Parameters and commands to be entered by the user are not case-sensitive.
>3. `/to` is case sensitive.
>4. Location names must be in full for commands that require bus stop location(s).
>5. Words in [ ] are optional parameters (e.g. [**description**]).

:exclamation: WARNING: You are recommended not to edit the text files manually.

### 3.1. Bus Features
This section provides instructions for all features categorised under the main feature of navigation by bus.

<!-- @@author wamikamalik -->
#### 3.1.1. Check for direct bus: ```/route``` (Wamika)
This command displays all bus routes from one location to another that do not require changing buses.

Format: <br>
<code>/route <strong>location_1</strong> /to <strong>location_2 </strong> </code>

> Note: Short forms of bus stop names cannot be used. For example, **UTown** cannot be used in place of **University Town**.
> For more information, see Example 2 of Common errors and problems

**Examples of Usage**

Let's say you are currently at **PGP** and want to find out the buses you can board from **PGP** bus stop to get to **NUS IT**.

To find all such bus routes:

1. Type <code>/route <strong>PGP</strong> /to <strong>NUS IT</strong></code> into the CLI and press <kbd>Enter</kbd> 
to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/routeInput1.png" alt="inputCommand" width=450><br>

2. The result will be a message displaying the list of buses you can take with their routes as shown in the figure below.<br><br>
<img src="UG_Figures/routeOutput1.png" alt="output" width=650><br>

**Common errors and problems** 

**<u>Example 1</u>**<br>
Let's say you are currently at **University Health Centre** and you want to go to **PGPR**. But you accidentally type **"Univerity 
Health Center"** instead. 

Here's what you can do: 

1. When you type <code>/route <strong>Univerity Health Center</strong> /to <strong>PGPR</strong></code> into the CLI and 
press <kbd>Enter</kbd> to execute the command, the result will be a message displaying suggestions 
for possible spelling errors you may have made.<br><br>
<img src="UG_Figures/routeOutput2.png" alt="output" width=550><br>

2. Type <code>/route <strong>University Health Centre</strong> /to <strong>PGPR</strong></code> into the CLI
following the suggestion given and press <kbd>Enter</kbd>.

3. The result will be a message displaying the list of buses you can take with their routes as shown in the figure 
below.<br><br>
<img src="UG_Figures/routeOutput3.png" alt="FinalOutput" width=650><br>
<!-- @@author -->

**<u>Example 2</u>**<br>
Let's say you want to go from **KR MRT** to **YIH**, but you do not know the full names of these locations.

Here's what you can do:

1. Type `/liststops` into the CLI as shown in the figure below and press <kbd>Enter</kbd>. You can find more details about
`/lisstops` in [section 3.1.5.](#315-list-all-bus-stops-in-nus-liststops-yuxin) of the User Guide.<br><br>
<img src="UG_Figures/routeshort1.png" alt="list stops" width = 700><br>

2. The result will be a list of bus stops in NUS. Manually search for the full forms of **KR MRT** and **YIH** as shown in 
the figure below.<br><br>
<img src="UG_Figures/routeshort2.png" alt=" stops to choose" width = 700><br>

3. Type <code>/route <strong>Kent Ridge MRT Station</strong> /to <strong>Yusof Ishak House</strong></code> into the CLI and press
<kbd>Enter</kbd>. You will see the buses you can take as shown in the figure below.<br><br>
<img src="UG_Figures/routeshort3.png" alt="the result" width=600><br>

<div style="page-break-after: always;"></div>

<!-- @@author Johnson-Yee -->
#### 3.1.2. Check bus route: ```/routemap``` (Johnson)
This command displays the full route of the bus that you have specified.

Format: <br><code>/routemap <strong>bus code</strong></code> <br>

**Examples of Usage**

**<u>Example 1</u>**<br>
This command is exceptionally useful to find indirect bus routes.
Let us suppose that you are at **Raffles Hall** with only bus AA2 available and would like to go to **University Town**. 
You would notice that there is no direct bus to **University Town**. You could use the <code>/routemap</code> to find
indirect routes to your intended destination. 

To find indirect bus routes:

1. You type <code>/routemap <strong> AA2 </strong></code> into the CLI and press <kbd>Enter</kbd>.<br><br>
<img src="UG_Figures/routemap4.png" alt="inputRouteMapCommand" width=650><br>

2. The result will display the whole bus route of bus AA2.<br><br>
<img src="UG_Figures/routemap1.png" alt="RouteMapCommand" width=650><br>

3. With the information that bus AA2 could bring you to bus stops after **Raffles Hall** (e.g. **Kent Vale**), you can now check 
if there is a direct bus route from these bus stops.<br><br>
<img src="UG_Figures/routemap3.png" alt="inputRouteMapCommand" width=650><br>

<div style="page-break-after: always;"></div>

**<u>Example 2</u>**<br>
This command is also useful for showing you the previous bus stops of your intended bus. You could use this information
to gauge how crowded the bus would be.<br><br>
Let us suppose that you are at **Raffles Hall** intending to board AA2. 

These are the steps to follow:

1. You type in <code>/routemap <strong> AA2 </strong></code> into the CLI and press <kbd>Enter</kbd>.<br><br>
<img src="UG_Figures/routemap4.png" alt="inputRouteMapCommand" width=650><br>

2. The result will display the whole bus route of bus AA2. You will observe that the bus passes through **University Town**
which is relatively more crowded than other bus stops.<br><br>
<img src="UG_Figures/routemap1.png" alt="RouteMapCommand" width=650><br>

3. With this information, you could explore other bus routes to get to your destination.
<!-- @@author -->

<div style="page-break-after: always;"></div>

#### 3.1.3. Check for buses at a bus stop: ```/bus``` (Heng Chin)
This command displays all buses available at a specific bus stop.

Format: <br>
<code>/bus<strong> bus stop</strong></code> <br>

**Examples of Usage**

Let's say that you are at <strong>University Town</strong> bus stop, and you want to know the buses which are available for you to take. Instead of searching for the bus stops which all the buses stop at, you can easily access this information by using the <code>/bus</code> command. 

To search for available buses at **University Town**:

1. Type <code>/bus <strong>University Town</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below. <br><br>
<img src="UG_Figures/bus4.png" width=600><br>

2. The result will be a message displaying the buses available at University Town. <br><br>
<img src="UG_Figures/bus3.png" width=650><br>

<div style="page-break-after: always;"></div>

**Common errors**

Let's say that you are at the <strong>museum</strong> bus stop, and you want to know the buses which are available for you to take. However, you make a spelling error and type <strong>"musuem"</strong> instead. <br>

These are the steps to fix the mistake:

1. The result will be a message displaying bus stop suggestions for possible error in user input.<br><br>
<img src="UG_Figures/bus2.png"><br>

2. Type <code>/bus <strong>museum</strong></code> into the CLI as suggested in the above output.<br>

3. The result will be a message displaying the buses available at the museum.<br><br>
<img src="UG_Figures/bus1.png"><br>

<!-- @@author mrwsy1 -->
#### 3.1.4. List all buses available in NUS ```/allbus``` (Shuyi)
This command lists all buses available in NUS with their respective routes.

Format:<br> 
<code>/allbus</code> <br>

**Examples of Usage**

Let's say you want to see a list of all bus routes so that you can plan your trip around NUS accordingly. 

To see the complete list of buses:

Type <code>/allbus</code> into the CLI and press <kbd>Enter</kbd>.<br><br>
<img src="UG_Figures/allbus1.png" alt="inputCommand" width=700><br>
<!-- @@author -->

<!-- @@author Lezn0 -->
#### 3.1.5. List all bus stops in NUS: ```/liststops``` (Yuxin)
This command lists all bus stops in NUS.

Format:<br> 
<code>/liststops</code> <br>

**Examples of Usage**

Let's say you want to know more about the bus stops in NUS. 

To see the description of each location:

Type <code>/liststops</code> into the CLI and press <kbd>Enter</kbd>.<br><br>
<img src="UG_Figures/listOutput.png" alt="inputCommand" width=800><br>
<!-- @@author -->

<div style="page-break-after: always;"></div>

<!-- @@author mrwsy1 -->
#### 3.1.6. List all faculties in NUS: ```/faculty``` (Shuyi)
This command lists out all faculties in NUS.

Format:<br> 
<code>/faculty</code> <br>

**Examples of Usage**

Let's say you want to know the names of all faculties in NUS.

Here's what you csn do:

Type <code>/faculty</code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/faculty1.png" alt="output" width=600><br>

### 3.2. Dine Features
This section provides instructions for all features categorised under the main feature of locating dining options.

#### 3.2.1. Search for dining options within a faculty: ```/dine``` (Shuyi)
This command lists out all dining outlets available within a chosen faculty.

Format:<br> 
<code>/dine <strong>faculty</strong></code> <br>

<div style="page-break-after: always;"></div>

**Examples of Usage**

**<u>Example 1</u>**<br>
Let's say you want to know all the dining options available in <strong>School of Business</strong>.

Here's what you can do:

Type <code>/dine <strong>business</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/dine1.png" alt="output" width=600><br>

**<u>Example 2</u>**<br>
Let's say you want to know the available dining options in the <strong>Science</strong> faculty, but you are feeling a little lazy to type out the full name of the faculty.

You can simply use <strong>Sci</strong> instead of <strong>Science</strong>:

Type <code>/dine <strong>sci</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/dine2.png" alt="output" width=600><br>

>Notes about the `/dine` feature:
>
> * It is possible for the feature to return results from multiple faculties if the keyword used for the search is not specific to the desired faculty.<br>
> *  For example, `/dine school` will yield results from both School of Business and School of Computing.

#### 3.2.2. Search for specific dining outlet: ```/dineinfo``` (Shuyi)
This command finds all dining outlets that contain the keyword, and displays their location and operating hours.

Format:<br>
<code>/dineinfo <strong>outlet</strong></code>

**Examples of Usage**

**<u>Example 1</u>**<br>
Let's say you want to find information about the dining outlet <strong>Arise & Shine</strong>.

Here's what you can do:

Type <code>/dineinfo <strong>arise</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/dineinfo1.png" alt="output" width=600><br>

**<u>Example 2</u>**<br>
Let's say you cannot remember the full name of the outlet that you are searching for. You can simply type in a keyword instead.

To find the information of a dining outlet with the name containing <strong>Jewel</strong>:

Type <code>/dineinfo <strong>jewel</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/dineinfo3.png" alt="output" width=600><br>
<!-- @@author -->

### 3.3. Favourite Features
This section provides instructions for all features categorised under the main feature of personalisation of application
to your needs.

<div style="page-break-after: always;"></div>

<!-- @@author Lezn0 -->
### 3.3.1. Add a favourite command: `/addfav` (Yuxin)
This command adds a valid command with an optional description to your list of favourites.
>Note: A valid command is a command that does not return an error message and is not associated 
>with any commands related to favourites.

Format:<br>
<code>/addfav <strong> [description] </strong> </code>

**Examples of Usage**

**<u>Example 1</u>**<br>
Let's say you want to add the command to list dining options in business.

These are the steps to follow:

1. Type <code>/dine <strong>business</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below<br><br>
<img src="UG_Figures/dine1.png" alt="output" width=600><br>

2. Type <code>/addfav <strong>dining options in business</strong></code> and press <kbd>Enter</kbd> to execute the command 
to store the command in your list of favourites with the description
"dining options in business"  as shown in the figure below.<br><br>
<img src="UG_Figures/addfav1.png" alt="output" width=600><br>

<div style="page-break-after: always;"></div>

**<u>Example 2</u>**<br>
Let's say you want to add the command that guided you from PGP to NUS IT to your list of favourites.

These are the steps to follow:

1. Type <code>/route <strong>pgp /to nus it</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below. <br><br>
<img src="UG_Figures/routeOutput1.png" alt="output" width=600><br>

2. Type <code>/addfav</code> into the CLI and press <kbd>Enter</kbd> to execute the command 
to store the command in your list of favourites with no description  as shown in the figure below .<br><br>
<img src="UG_Figures/addfav2.png" alt="output" width=600><br>
<!-- @@author -->

<div style="page-break-after: always;"></div>

<!-- @@author mrwsy1 -->
#### 3.3.2. List all favourite commands: `/listfav` (Shuyi)
This command displays all the commands in your list of favourite commands, along with their index and description.

Format:<br>
<code>/listfav</code>

**Examples of Usage**

Let's say you want to take a look at all the commands that were previously added to your list of favourite commands.

Here's what you can do:

Type <code>/listfav</code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/listfav1.png" alt="output" width=600><br>
<!-- @@author -->

<!-- @@author Johnson-Yee -->
#### 3.3.3. Delete a favourite command: `/deletefav` (Johnson)
This command deletes the command that you have specified from the list of favourite commands.
>Note: Index keyed in must be within the range of 1 - n, where n is the number of favourite commands. <br>

Format: <br>
<code>/deletefav<strong> index in list</strong></code> <br>

**Examples of Usage**

Let's say that you have stored the command <code>/routemap <strong>AA1</strong></code> in your list of favourite commands.
After reviewing your list of favourite commands, you do not want to have this command in it.

To delete this command from your favourites list:

Type <code>/deletefav <strong>index</strong></code> into the CLI and 
press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/deleteFavExample.png" alt="output of deletefav" width=600><br>
<!-- @@author -->

#### 3.3.4. Execute a favourite command: `/execfav` (Heng Chin)
This command executes the specific command in your list of favourite commands.

Format: <br>
<code>/execfav<strong> index in list</strong></code> <br>

**Examples of Usage**

Let's say that you have stored the command <code>/route <strong>Opp University Health Centre</strong> /to <strong>Opp Kent Ridge MRT station</strong></code> in your list of favourite commands. Instead of typing the long command using `/route`, you can now conveniently use the `/execfav` command.

Given you have the list of favourite commands:<br><br>
<img src="UG_Figures/execfav1.png"><br>


To execute the command with the 2nd index in your list of favourite commands:

Type <code>/execfav <strong>2</strong></code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below. <br><br>
<img src="UG_Figures/execfav2.png" width=600><br>

**Common errors and problems**

Let's say your data has been corrupted and thus your list of favourite commands contains an invalid command.<br>
If you attempt to execute the command, Nav@NUS will automatically delete the corrupted data from your list.

Given you have the list of favourite commands:<br><br>
<img src="UG_Figures/execfav3.png"><br>

If you attempt to execute the invalid command <code>/bus random place</code> in your favourites list. Nav@NUS will automatically delete the corrupted data from your list as seen below:<br><br>
<img src="UG_Figures/execfav4.png"><br>

<!-- @@author wamikamalik -->
#### 3.3.5. Change the description for a favourite command: `/descfav` (Wamika)
This command helps you change the description of a command in your list of favourites.

Format:<br>
<code>/descfav <strong>index</strong> /to <strong>new description</strong></code>

**Examples of Usage**

Let's say you have the following list of commands:<br><br>
<img src="UG_Figures/beforedescfav.PNG" alt="original list of commands" width=550>

You want to change the description for `/dineinfo Pines` from "No description" to **"Get dinner @7:30PM every Tuesday"**.
You can do so by following these steps:

1. Type <code>/descfav <strong>5</strong> /to <strong>Get dinner @7:30PM every Tuesday</strong></code> into the CLI as
shown in the figure below and press <kbd>Enter</kbd>. <br><br>
<img src="UG_Figures/descfavinput.PNG" alt="descfav input" width=600>

2. Type <code>/listfav</code> to see the changed description.<br><br>
<img src="UG_Figures/afterdescfav.png" alt="list after changing" width=600>
<!-- @@author -->

<!-- @@author mrwsy1 -->
#### 3.3.6. Clear the list of favourite commands: `/clearfav` (Shuyi)
This command clears all the commands stored in your list of favourite commands.

Format:<br>
<code>/clearfav</code>

**Examples of Usage**

Let's say you no longer need any of the commands in your list of favourite commands. Instead of using 
<code>/deletefav</code> to remove the commands one by one, you can use the <code>/clearfav</code> feature to clear 
your favourites list at one go.

Here's what you can do:

Type <code>/clearfav</code> into the CLI and press <kbd>Enter</kbd> to execute the command as shown in the figure below.<br><br>
<img src="UG_Figures/clearfav1.png" alt="output" width=600><br>
<!-- @@author -->

<!-- @@author EthanWong2212 -->
#### 3.3.7. Store favourite commands (Heng Chin)
:exclamation:  WARNING: **DO NOT** modify the `FavList.txt` file <br>

In order to make Nav@NUS a personalized application for you, all data from your list of favourite commands will be stored on your computer. Thus, everytime Nav@NUS launches, you will be able to maintain and update your list of favourite commands.

The data is stored in the `FavList.txt` file in the `data` folder located in the same location as the Nav@NUS jar file.

<!-- @@author -->

### 3.4. Common Features
This section provides instructions for all the common features.

<div style="page-break-after: always;"></div>

<!-- @@author wamikamalik -->
#### 3.4.1. Check for similar locations (Wamika)
When you enter a location and make a spelling error or a typo in the name, the app performs a similarity check with 
existing location names and suggests some locations to you. _The app executes this command automatically and does not 
require any explicit input from you._

**Examples of Usage**

Let's say you want to find all buses that stop at **Opp HSSML**, but you type <code>/bus <strong>Opp HSML</strong></code> instead.

You will receive a message with suggested location names you can use as shown in the figure below.<br><br>
<img src="UG_Figures/similarOutput1.png" alt="similar locs message" width = 550><br>

You may then type in the command again with the correct location to see a list of buses that stop at **Opp HSSML** 
as shown in the figure below.<br><br>
<img src="UG_Figures/similarOutput2.png" alt="Correct input message" width=400><br>

>Note: This check is only applicable to bus stop names, so the app performs it only when you enter a 
><code>/route</code> command or a <code>/bus</code> command. 
<!-- @@author -->

<!-- @@author Johnson-Yee -->
#### 3.4.2. Display most searched bus stop on start-up (Johnson)
This feature displays your most commonly searched bus stop to remind you of what to type in when using 
the navigation functions. 

>The application executes this command on start-up and does not require any explicit command to use this feature.

<div style="page-break-after: always;"></div>

**Examples of usage**

On start-up, you will receive a prompt of your most searched bus stop. This personalises your application and gives you
the memory jolt of what to key in. <br><br>
<img src="UG_Figures/displayfreq.png" alt="Search Freq Prompt" width=400><br>
<!-- @@author -->

<!-- @@author Johnson-Yee -->
#### 3.4.3. Reset frequent search data: ```/reset``` (Johnson)

This command resets the data set used to display the most frequently searched bus stop on application start-up.

Format:<br> <code>/reset</code>

**Examples of usage**

Let us suppose that you are transitioning to a new academic semester, and the locations that you will key in to the
application changes. To create a new data set that will cater to your needs in this new semester, you will key in the
command <code>/reset</code> to reset the data set and start the application on a clean slate.<br><br>
<img src="UG_Figures/freq1.png" alt="Correct input message" width=600><br>
<!-- @@author -->

<!-- @@author Lezn0-->
#### 3.4.4. List available help: ```/help``` (Yuxin)
This command lists a set of features along with their respective commands available to users.

Format:<br> <code>/help</code>

The expected outcome is as follows:<br><br>
<img src="UG_Figures/help1.png" alt="inputCommand" width=600><br>
<!-- @@author -->

#### 3.4.5. Exit the program: ```/exit```
This command helps you exit the application.

Format:<br>
<code>/exit</code>

The application exits after displaying the following message.<br><br>
<img src="UG_Figures/exit.png" alt="Correct input message" width=600><br>
<div style="page-break-after: always;"></div>

## 4. FAQ
This section addresses some common questions to aid in possible issues faced.

**Q:** Where can I find the release? <br>
It can be found [here](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases).

**Q:** How do I transfer my data to another computer? <br>
Simply copy your `data` folder from the current directory and paste it in the directory containing the `Nav@NUS.jar` 
file in the other computer.

**Q:** What do I do if I have accidentally made changes to the `FavList.txt` file? <br>
Simply run Nav@NUS and the program will automatically delete most of the corrupted data.

## 5. Command Summary
The following table provides a summary of features and command formats.

>Note:<br>
> 1. No additional parameter is needed if it is not mentioned (e.g. help). <br>
> 2. The table below is arranged in alphabetical order. <br>

<div style="page-break-after: always;"></div>

Command | Format | Example
--- | --- | ---
/addfav | `/addfav` **[description]** | 1. `/addfav` <br> 2.`/addfav` **dining options in business**
/allbus | `/allbus` | `/allbus`
/bus | `/bus` **location** | `/bus` **PGP**
/clearfav | `/clearfav` | `/clearfav`
/deletefav | `/deletefav` **index**| `/deletefav` **1**
/descfav | `/descfav` **index** `/to` **new description** | `/descfav` **5** `/to` **Get dinner @7:30PM every Tuesday**
/dine | `/dine` **faculty** | `/dine` **business**
/dineinfo | `/dineinfo` **outlet** | `/dineinfo` **arise & shine**
/execfav | `/execfav` **index** | `/execfav` **5**
/exit | `/exit` | `/exit`
/faculty | `/faculty` | `/faculty`
/help | `/help` | `/help`
/liststops | `/liststops`| `/liststops` 
/listfav | `/listfav` | `/listfav`
/reset | `/reset` | `/reset`
/route | `/route` **location1** `/to` **location2** | `/route` **PGP** `/to` **Raffles Hall**
/routemap | `/routemap` **bus code** | `/routemap` **AA1** 

<div style="page-break-after: always;"></div>

<!-- @@author wamikamalik -->
## 6. Glossary
This section defines key technical terms we have used throughout the user guide.
1. Case-sensitive: Capital and lowercase letters are treated differently.
2. Command Line Interface(CLI): Processes commands to a computer program in the form of lines of text.
3. Corrupted file: A file containing invalid data or data it should not have.
4. Dining options/outlets: Places you can eat at.
5. Direct bus: Commuting between two locations does not require changing buses.
6. Execute: Run the command to display the output.
7. Similarity check: Check for possible spelling errors.
<!-- @@author -->
