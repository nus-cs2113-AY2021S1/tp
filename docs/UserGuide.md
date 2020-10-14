# SmartHomeBot User Guide

SmartHomeBot is an **desktop application for consolidates all of the home appliance’s control into a 
centralized system via a Command Line Interface (CLI)**. Users can switch on an off appliances using this application
and also review and monitor electricity usage; having a clearer picture of their electrical usage patterns.
SmartHomeBot has an auto-saved feature that will automatically save all the appliances' data
and export it to a text file. Upon start of the application, it will import the data 
from the text file and loads the appliances' data back.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features) 
  * [Viewing help: `help`](#viewing-help-help) 
  * [Creating a location: `create`](#creating-a-location-create)
  * [Removing a location: `remove`](#removing-a-location-remove)
  * [Adding an appliance : `add`](#adding-an-appliance-add)
  * [Deleting an appliance: `delete`](#deleting-an-appliance-delete)
  * [Switching on an appliance: `on`](#switching-on-an-appliance-on)
  * [Switching off an appliance: `off`](#switching-off-an-appliance-off)
  * [Listing all the locations/appliances: `list`](#listing-all-the-locations-or-appliances-list)
  * [Displaying the usage of appliance: `usage`](#displaying-the-usage-of-appliance-usage)
  * [Exiting the application: `exit`](#exiting-the-application-exit)
  * [Saving the data](#saving-the-data)
* [Command summary](#command-summary)

## Quick start

1.  Ensure you have Java `11` or above installed in your Computer.

2.  Download the latest `SmartHomeBot.jar` from here.

3.  Copy the file to the folder you want to use as the home folder for your SmartHomeBot.

4.  Open your command prompt as administrator. Then input java -jar **c:pathtojarfile.jar** in 
Command Prompt and press ENTER. Replace the **c:pathtojarfile.jar** with the actual path and 
file title of the Jar. The display similar to the below should appear in a few seconds.

5.  Type the command in the command box and press Enter to execute it. 
e.g. typing `exit` and pressing Enter, it will exit the program.
Some example commands you can try:
    *   `create Bedroom 1`: 
         Creates a 'location' named "Bedroom 1" in SmartHomeBot. 
         
    *   `remove Bedroom 1`: 
         Remove a 'location' named "Bedroom 1" in SmartHomeBot.
            
    *   `list location`: 
         Lists all the location.
             
6.  Refer to the Features below for details of each command.

## Features 

   **Notes about the command format**
    
   * Words in [UPPER_CASE] are the parameters to be supplied by the user.
    <br>e.g. `create LOCATION_NAME`, LOCATION_NAME is a parameter which can be used as 
   `create Bedroom 1`.
   * Words in [UPPER_CASE] are case-sensitive.

### Viewing help: `help`
Shows all available commands to the user

Format: `help`

**Example output:** 
```
-------------------------------------------------------
help: Shows program usage instructions.
Example: help
-------------------------------------------------------
create: Creates a new location in SmartHomeBot
Parameters: LOCATION
Example: create Bedroom 1
-------------------------------------------------------
remove: Remove the location and all the appliances in that location.
Parameters: LOCATION
Example: remove Bedroom 1
-------------------------------------------------------
add: Adds a new appliance to the particular location to the SmartHomeBot. 
Parameters: add NAME l/LOCATION n/NAME w/WATTS t/TYPE_OF_APPLIANCE 
Example: add Fan 1 l/Bedroom 1 w/50 t/Fan
-------------------------------------------------------
delete: Delete the existing appliance by its indicated name that has been added to SmartHomeBot
Parameters: NAME
Example: delete Fan 1
-------------------------------------------------------
on: Turns on the specified appliance by its indicated name 
Parameters: NAME 
Example: on Aircon 1 
-------------------------------------------------------
off: Turns off specified appliance by its indicated name 
Parameters: NAME
Example: off Fan 1
-------------------------------------------------------
list: Display all the appliances that have been added to SmartHomeBot 
Example: list
-------------------------------------------------------
exit: Exits the program.
Example: exit

===================================================
```
   
<br/><br/>
### Creating a location: `create`
Adds a new location with a name. 

Format: `create [LOCATION_NAME]` 
* `LOCATION_NAME` must be a unique name 

Example: `create Bedroom 1`

Output: 
```
Creating Location "Bedroom 1".....CREATED!
```

<br/><br/>
### Removing a location: `remove`
Removes an added location with its name in the list. 

Format: `remove [LOCATION_NAME]`

Example: `remove Bedroom 1`

Output: 
```
Removing LOCATION "Bedroom 1"......REMOVED!
```

> Note: If there are appliances added to the 'LOCATION_NAME' when removing, it will be deleted as well. 

<br/><br/>
### Adding an appliance: `add`
Adds an appliance to the location created previously. 

Format: `add [APPLIANCE_NAME] l/[LOCATION_NAME] w/[WATTAGE] t/[TYPE_OF_APPLIANCE]`
* `[APPLIANCE_NAME]` must be a unique name.
* `[LOCATION_NAME]` must be an existing location created by `create` command.
* `[WATTAGE]` must be an `int` value.
* `[TYPE_OF_APPLIANCE]` must be one of the type in the following list. 

List of `TYPE_OF_APPLIANCE` 
1. `fan`
2. `light`
2. `airconditioner`
3. `waterheater`

Example: `add AIRCON1 l/Bedroom 1 w/3500 t/airconditioner`

Output: 
```
Adding airconditioner: AIRCON1 (3500W) in Bedroom 1.....ADDED!
```

<br/><br/>
### Deleting an appliance: `delete`
Deletes an appliance base on its name in the list. 

Format: `delete [APPLIANCE_NAME]`

Example: `delete AIRCON1`

Output: 
```
Deleting AirConditioner: AIRCON1 (3500) in Bedroom 1......DELETED!
```
   
<br/><br/>
### Switching on an appliance: `on` 
Switches ON an appliance base on its name in the list. 

Format: `on [APPLIANCE_NAME]`

Example: `on AIRCON1`

Output: 
```
Switching on AIRCON1 in Bedroom 1 ......ON!
```

<br/><br/> 
### Switching off an appliance: `off`
Switches OFF an appliance base on its name in the list. 

Format: `off [APPLIANCE_NAME]`

Example: `off AIRCON1`

Output: 
```
Switching off AIRCON1 in Bedroom 1 ......OFF!
```
   
<br/><br/> 
### Listing all the locations OR appliances: `list`
List out all the appliances or all the location currently stored.

Format: `list appliance` or `list location`
* `list appliance` will list all the appliances entered by the user in SmartHomeBot.
* `list location` will list all the locations entered by the user in SmartHomeBot.

Example: `list location`

Output: 
```
Here are the location you have entered.
1: Bedroom 1
```

Example: `list appliance`

Output: 
```
Here are the appliances in your list.
1. AIRCON1 | Location: Bedroom 1 | Status: Off | Watt: 3500W | Type: AirConditioner
```

<br/><br/> 
### Displaying the usage of appliance: `usage`
Display the current power usage of all appliances and Total power consumption. 
Format: `usage`

Output: 
```
Here are the power usage consumption.

1. AIRCON1 | Location: Bedroom 1 | Status: Off | Usage: 371.00 kWh

Total power consumption: 371.00 kWh
```

<br/><br/> 
### Exiting the application: `exit`
Exits the application.

Format: `exit`

   
<br/><br/>
### Saving the data
SmartHomeBot will auto-save the task list data in the hard disk after 
any command that changes the data. There is no need to save manually.


## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file 
that contains the data of your previous Duke home folder.

## Command summary
| Function | Format | Example |
| -------- | ------ | ------- |
| Help | `help` |   |
| Create location | `create [LOCATION_NAME]` | `create Bedroom 1` |
| Remove location | `remove [LOCATION_NAME]` | `remove Bedroom 1` |
| Add appliance | `add [APPLIANCE_NAME] l/[LOCATION_NAME] w/[WATTAGE] t/[TYPE_OF_APPLIANCE]` | `add AIRCON1 l/Bedroom 1 w/3500 t/airconditioner` |
| Delete appliance | `delete [APPLIANCE_NAME]`| `delete AIRCON1` |
| Switch On | `on [APPLIANCE_NAME]` | `on AIRCON1` |
| Switch Off | `off [APPLIANCE_NAME]` | `off AIRCON1` |
| List | `list appliance` or `list location` |   |
| Usage | `usage` |   |
| Exit | `exit` |   |


