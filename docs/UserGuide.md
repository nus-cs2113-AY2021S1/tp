# User Guide
  ```
*Insert NAV@NUS LOGO here 
```
Nav@NUS is a useful CLI application to guide users in navigating around the NUS Kent Ridge campus via the school
shuttle serviceS.

* [Quick Start](#quick-start)
* [Features](#Features)
    + [1. Check for direct bus: `/route`](#1-check-for-direct-bus-route)
    + [2. Check bus route: `/routemap`](#2-check-bus-route-routemap)
    + [3. Check for available bus: `/bus`](#3check-for-available-bus-at-a-location-bus)
    + [4. List all bus available in NUS: `/allbus`](#4-list-all-bus-available-in-nus-allbus)
    + [5. List all bus stops available in NUS: `/liststops`](#5-list-all-bus-stops-in-nus-liststops)
    + [6. Exit the program: `/exit`](#6-exiting-the-program-exit)
    + [7. List available help: `/help`](#7-listing-available-help-help)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure that you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for dude.jar bus application
4. In command prompt, `cd` to the directory of the _home folder_.
5. Run the .jar file in the command prompt as follows: `java -jar duke.jar`
6. Type `help` to view commands available.
7. Refer to [Features](#features) below for details of each command

## Features 
>Notes about command format:  
>
>1. Words in **bold** are parameters to be provided by the user.

### 1. Check for direct bus: ```/route```
Checks for direct bus between two locations. <br>
Format: <code>/route <strong>location_1 /to location_2 </strong> </code> <br>
Example of usage: <code>/route <strong> PGP /to Raffles Hall </strong></code><br>
Expected outcome: 
```
The buses you can take with their intermediate stops are: 
1. AA2
	Kent Ridge MRT Station-> Opp University Health Centre-> University Town
```

### 2. Check bus route: ```/routemap```
Checks for user-specified bus route. <br>
Format: <code>/routemap <strong>task description</strong></code> <br>
        Upon receiving prompt, specify 
        <code><strong>bus route name/strong></code> <br>
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

### 3.Check for available bus at a location: ```/bus```
Checks for available bus at a user-specified location <br>
Format: <code>/bus<strong>bus code</strong></code> <br>
Example of usage: <code>/bus<strong> PGP</strong></code> <br>
Expected outcome: 
```
PGP:  AA1   AA2
```

### 4. List all bus available in NUS ```/allbus```
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
### 5. List all bus stops in NUS: ```/liststops```
Lists all bus stops in NUS. <br>
>Note: Index keyed in must be within the range of 1 - n, where n is number of existing tasks. <br>

Format: <code>/liststops</code> <br>
Example of usage: <code>/lisstops</code> <br>
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

### 6. Exiting the program: ```/exit```
Exits the program <br>
Example of usage: <code>/exit</code> <br>
Expected outcome:
```
So long buddy!
```

### 7. Listing available help: ```help```
Lists a set of commands available to users <br>
Example of usage: <code>/help</code> <br>
Expected outcome:
```
Here are the range of commands:
1./route : Displays possible direct bus from point to point
2./routemap: Displays the route map with its intermediate bus stops
3./bus: Displays buses available at each bus stop
4./allbus: Lists all buses available in NUS Zone a
5./liststops: Lists all bus stops in NUS Zone a
6./exit: Exit program
7./help: List all available commands

```

## FAQ
**Q:** Where can I find the release? <br>
It can be found at [here](https://github.com/AY2021S1-CS2113T-F14-3/tp/releases).

## Command Summary
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

