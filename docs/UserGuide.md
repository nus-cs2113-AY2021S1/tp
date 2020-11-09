# <img src="images/smarthomebot-logo.png" width="50"> SmartHomeBot User Guide 

SmartHomeBot is a **desktop application that consolidates all home appliance’s control into a 
centralized system via a Command Line Interface (CLI)**. It is designed to assist disabled individuals with 
difficulties to perform tasks like switching On/Off Appliances at home.  Users can also 
review and monitor electricity usage; having a clearer picture of their electrical usage patterns.
SmartHomeBot has an auto-save feature that will automatically save all the appliances' data
and export it to a text file. Upon the start of the application, it will import the data 
from the text file and loads the appliances' data back.


##### Disclaimer: SmartHomeBot V2.1 does not interface with actual Appliances. Instead, Appliances are simulated in this program. 

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
  * [Displaying the recorded usage of appliance: `usage`](#displaying-the-recorded-usage-of-appliance-usage)
  * [Resetting all power usage in application: `p_reset`](#resetting-all-power-usage-in-application-p_reset)
  * [Exiting the application: `exit`](#exiting-the-application-exit)
  * [Saving the data](#saving-the-data)
* [Command summary](#command-summary)

<div style="page-break-after: always;"></div>
## Quick start

1.  Ensure you have Java `11` or above installed in your Computer.

2.  Download the latest `SmartHomeBot.jar` from [here](https://github.com/AY2021S1-CS2113-T14-1/tp/releases/latest). 

3.  Copy the file to the folder you want to use as the home folder for your SmartHomeBot.

4.  Open your Command Prompt/Terminal. Navigate to the folder which you place the SmartHomeBot.jar. Then input `java -jar SmartHomeBot.jar` and 
press ENTER. The display similar to the below should appear in a few seconds.
    
    ![](images/user_guide/startMessage.png)
     
5.  Type the command in the command box and press Enter to execute it. 
e.g. typing `exit` and pressing Enter, it will exit the program.
Some example commands you can try:
    *   `create Bedroom1`: 
         Creates a 'location' named "Bedroom1" in SmartHomeBot. 
         
    *   `remove Bedroom1`: 
         Remove a 'location' named "Bedroom1" in SmartHomeBot.
            
    *   `list location`: 
         Lists all the location.
             
6.  Refer to the Features below for details of each command.

## Features 

   **Notes about the command format**
    
   * Words in **[UPPER_CASE]** are the parameters to be supplied by the user.
    <br>e.g. `create [LOCATION_NAME]`, LOCATION_NAME is a parameter which can be used as 
   `create Bedroom1`.
   * Words in **[UPPER_CASE]** are case-sensitive.
   * Words in **[LOCATION_NAME]** and **[APPLIANCE_NAME]** has to be unique and not duplicate of each other. 
   * Words in **[LOCATION_NAME]** and **[APPLIANCE_NAME]** cannot contain `space` or `/` or `|` when `create` or `add`
   * Caution: Please do not type `Ctrl-C or Ctrl-Z` as it may cause the application to terminate due the in-build function 
   on command prompt  
   
### Viewing help: `help`
Shows all available commands to the user

Format: `help`

Example: `help`

Output: 

   ![](images/user_guide/helpCommand.png)   
<br/><br/>
### Creating a location: `create`
Adds a new location with a name. 

Format: `create [LOCATION_NAME]` 
* `LOCATION_NAME` must be a unique name. 
* `Space` cannot be included in `LOCATION_NAME`. However `_` can be used to replace `space` if you want to create a 
`LOCATION_NAME` with more than one word.

Example: `create Bedroom1`

Output: 

   ![](images/user_guide/createCommand.png) 

<br/><br/>
### Removing a location: `remove`
Removes an added location with its name in the list. 

Format: `remove [LOCATION_NAME]`

Example: `remove Bedroom1`

Output: 

   ![](images/user_guide/removeCommand.png) 

> Note: If there are appliances in the Location of 'LOCATION_NAME' when removing, it will be deleted as well. 

<br/><br/>
### Adding an appliance: `add`
Adds an appliance to the location created previously. 

Format: `add [APPLIANCE_NAME] l/[LOCATION_NAME] w/[WATTAGE] t/[TYPE_OF_APPLIANCE]`
* `[APPLIANCE_NAME]` must be a unique name.
* `[LOCATION_NAME]` must be an existing location created by `create` command.
* `[WATTAGE]` must be an `int` value between 1-9999.
* `[TYPE_OF_APPLIANCE]` must be one of the type in the following list. 

List of `TYPE_OF_APPLIANCE` 
1. `fan`
2. `light`
2. `aircon`
3. `smartplug`

Example: `add AIRCON1 l/Bedroom1 w/3500 t/aircon`

Output: 

  ![](images/user_guide/addCommand.png) 

<br/><br/>
### Deleting an appliance: `delete`
Deletes an appliance base on its name in the list. 

Format: `delete [APPLIANCE_NAME]`

Example: `delete AIRCON1`

Output: 

  ![](images/user_guide/deleteCommand.png) 
   
<br/><br/>
### Switching on an appliance: `on` 
Switches ON an appliance base on its name in the list. 

Format: `on [APPLIANCE_NAME]` or `on [APPLIANCE_NAME] p/[PARAMETER]` or `on [LOCATION_NAME]`

1. `on [APPLIANCE_NAME]`: Switch ON the appliance by its name.
2. `on [APPLIANCE_NAME] p/[TEMPERATURE]`: Switch ON the appliance by its name with parameter for air-conditioner.
3. `on [APPLIANCE_NAME] p/[SPEED]`: Switch ON the appliance by its name with parameter for fan.
4. `on [LOCATION]`: Switch ON every appliance in the location. 

* Range for `TEMPERATURE`: 16 - 30
* Range for `SPEED`: 1-3
* If no `PARAMETER` is provided, the appliance will turn on with their default parameter, which are: 25 for `TEMPERATURE` and 1 for `SPEED`

Example: `on AIRCON1`

Output: 
   
   ![](images/user_guide/onCommand1.png) 
   
Example: `on AIRCON1 p/27`

Output: 
   
   ![](images/user_guide/onCommand2.png) 

When Appliance is already ON, we can still change the Appliance's parameter:

Example: `on AIRCON1 p/16`

Output:

   ![](images/user_guide/onCommand6.png) 
   
Example: `on FAN1 p/3`

Output: 
   
   ![](images/user_guide/onCommand3.png) 

Example: `on Bedroom1`

Output: 
   
   ![](images/user_guide/onCommand4.png) 

<br/><br/> 
### Switching off an appliance: `off`
Switches OFF an appliance base on its name in the list. 

Format: `off [APPLIANCE_NAME]` or `off [LOCATION]`

Example: `off AIRCON1`

Output: 
   
   ![](images/user_guide/offCommand1.png) 


Example: `off Bedroom1`

Output: 
   
   ![](images/user_guide/offCommand2.png) 

<br/><br/> 
### Listing all the locations OR appliances: `list`
List out all the appliances or all the location currently stored.

Format: `list appliance` or `list location` or `list appliance l/[LOCATION_NAME]`
* `list appliance` will list all the appliances entered by the user.
* `list location` will list all the locations entered by the user.
* `list appliance l/[LOCATION_NAME]` will list all the appliances in the location entered by the user.

Example: `list location`

Output: 
   
   ![](images/user_guide/listCommand1.png) 


Example: `list appliance`

Output: 
   
   ![](images/user_guide/listCommand2.png) 

Example: `list appliance l/Bedroom1`

Output: 
   
   ![](images/user_guide/listCommand3.png) 

<br/><br/> 
### Displaying the recorded usage of appliance: `usage`
Display the current power usage of all appliances and total power consumption monitored within SmartHomeBot. 

Format: `usage`

Output: 
   
   ![](images/user_guide/usageCommand.png) 

<br/><br/> 
### Resetting all power usage in application: `p_reset`
Reset the previous recorded power usage of all appliances and total power consumption to zero. Note that if appliance remains **ON**, its usage will be reset to zero, monitoring process continues, as the appliance remains running.


Format: `p_reset`

Output: 
   
   ![](images/user_guide/p_resetCommand.png) 

<br/><br/> 
### Exiting the application: `exit`
Exits the application and switch off all appliances within SmartHomeBot. The monitoring of all appliances will be stop.

Format: `exit`

Output: 

   ![](images/user_guide/ExitMessage.png) 
   
<br/><br/>

## Saving the data
SmartHomeBot will auto-save the task list data in the hard disk after 
any command that changes the data. There is no need to save manually.

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file 
that contains the data of your previous SmartHomeBot folder.

**Q**: Does the program interface with the appliance? 

**A**: Current SmartHomeBot v2.1 does not interface with actual Appliances. Instead the Appliances are simulated 
as a proof of concept for future implementation.

## Command summary

Function | Format | Example 
-------- |--------|-------- 
Help|`help`
Create location|`create [LOCATION_NAME]`|`create Bedroom1`
Remove location|`remove [LOCATION_NAME]`|`remove Bedroom1`
Add appliance|`add [APPLIANCE_NAME] l/[LOCATION_NAME] w/[WATTAGE] t/[TYPE_OF_APPLIANCE]`|`add AIRCON1 l/Bedroom1 w/3500 t/aircon`
Delete appliance|`delete [APPLIANCE_NAME]`|`delete AIRCON1`
Switch On|`on [APPLIANCE_NAME]` or `on [APPLIANCE_NAME] p/[PARAMETER]` or `on [LOCATION_NAME]`|`on AIRCON1`  or `on AIRCON1 p/27` or `on Bedroom1`
Switch Off|`off [APPLIANCE_NAME]` or `off [LOCATION]`|`off AIRCON1` or `off Bedroom1`
List|`list appliance` or `list location` or `list appliance l/[LOCATION_NAME]`|`list appliance l/Bedroom1`
Usage|`usage`
Reset|`p_reset`
Exit|`exit`
