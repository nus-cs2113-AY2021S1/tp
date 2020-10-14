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
  * [Help: `help`](#help-help) 
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
         Add a todo task with description "market research" to the task list.
         
    *   `remove Bedroom 1`: 
         Find task contains the keyword "project" in the task list.  
            
    *   `list location`: 
         Lists all the tasks.
             
6.  Refer to the Features below for details of each command.

## Features 

   **Notes about the command format**
    
   * Words in UPPER_CASE are the parameters to be supplied by the user.
    <br>e.g. in create LOCATION, LOCATION is a parameter which can be used as 
   `create Bedroom 1`.

#### Help: `Help`

   
<br/><br/>
#### Creating a location: `create`


<br/><br/>
#### Removing a location: `remove`


<br/><br/>
#### Adding an appliance: `add`


<br/><br/>
#### Deleting an appliance: `delete`

   
<br/><br/>
#### Switching on an appliance: `on` 


<br/><br/> 
#### Switching off an appliance: `off`

   
<br/><br/> 
#### Listing all the locations OR appliances: `list`


<br/><br/> 
#### Displaying the usage of appliance: `usage`


<br/><br/> 
#### Exiting the application: `exit`
Exits the application.

Format: `exit`

   
<br/><br/>
#### Saving the data
SmartHomeBot will auto-save the task list data in the hard disk after 
any command that changes the data. There is no need to save manually.


## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file 
that contains the data of your previous Duke home folder.

## Command summary
