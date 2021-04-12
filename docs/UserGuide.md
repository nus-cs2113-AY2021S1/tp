# User Guide

## Introduction

HdBuy allows you to easily find and bookmark resale flats available matching your preference.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `HdBuy` from [here](https://github.com/AY2021S2-CS2113-F10-1/HdBuy/releases/tag/v2.1).
3. Ensure that the .jar file downloaded is named `hdbuy.jar`. Else, rename it as such.
4. Execute the jar file by going to its directory and running `java -jar hdbuy.jar` in command line.
5. Execute your first command, 'help', to view all other available commands.

![Your first launch](images/landing.png)
<div style="page-break-after: always;"></div>

## How to Use

1. Decide on the filter parameters on resale units matching your preferences. There are 3 possible attributes to filter by:
    1. `location` : The area your preferred unit will be situated in. The app takes in location with spaces too. For example: 'ang mo kio' is valid.
    2. `type`: The type of unit, such as it being an executive flat or 4-room flat. Valid inputs here are: 'executive', '1 room' ... '5 room'.
    3. `lease_remaining`: Number of years left on the unit's lease. Of course, the maximum lease is 99.

2. Set the filters
    1. You can any combination of the 3 attributes listed above as filters
    2. Note that if you set the same attribute twice (filter by location at 'jurong' then at 'bishan'), the latter value will overwrite the former.
    3. An example is shown below:

    <img src="images/filter_example.png" width="400" height="250"/>

3. Find your units
    1. Execute `find`, you will then see up to 100 units matching your preferences.
    2. Every time you have successfully searched for units (searches with units to show), the filters set previously will automatically be cleared.
    
4. Shortlisting of unit
    1. From the latest search results, you can choose to shortlist units. For example: `save 10`, to shortlist the 10th unit from results.
    2. You can also choose view your shortlist using `shortlist`.
    3. Note that even after closing the app, your previous shortlist will be kept.
    
5. Closing the app
    1. Execute `exit` at any time to quit the app.
<div style="page-break-after: always;"></div>

## Features 

**Notes about the command format:**

* Words in wrapped in `<>` are the parameters to be supplied by the user.<br>
  e.g. in `remove <index>`, `index` is a parameter which can be used as `remove 1`.
  e.g. in `filter <attribute> <value>`, `attribute` is the attribute of a unit to filter. `value` is the value of the attribute.
* All commands is to be in `small caps` to be recognised.

### Add a Filter : `filter`

Add a filter condition. 

Format: `filter <attribute> <value>`


### Search for units : `find`

Search for units with the current filter conditions.

Format: `find`


### View Shortlist : `shortlist`

Shows all units in the shortlist.

Format: `shortlist`


### Append to shortlist: `save`

Adds a unit to the shortlist.

Format: `save <index>`

**Note:**
You have to complete a search before saving a unit to shortlist.
Only indexes shown in search results are valid.

Example:
* `save 1` to save the first unit in search result.

<div style="page-break-after: always;"></div>

### Remove from shortlist: `remove`

Removes a unit from the shortlist.

Format: `remove <index>`

**Note:**
You have to have at least 1 unit in shortlist.
Only indexes shown in shortlist are valid.

Example:
* `remove 1` to remove the first unit in shortlist.


### List filter conditions: `list`

Shows all currently set filter condition to filter units matching preferences.

Format: `list`


### Clear filter conditions: `clear`

Removes all currently set filter conditions.


### Display help guide: `help`

Shows the available commands and directs the user to the appropriate links.

Format: `help`


### Sort: `sort`

Sorts listings either in ascending or descending order with respect to price.

Format: `sort <order>`

Example:
* `sort asc` to sort listings in ascending order with respect to price.
* `sort desc` to sort listings in descending order with respect to price.

<div style="page-break-after: always;"></div>

## Error Handling

Listed in the table below are the possible errors, how they can occur and how to remedy it.

|How can this error occur|Description|How to remedy|
|----|------|---------|
|Prerequisites: `filter` not set <br>`find`|\`find` has no parameters currently.<br>Please specify a filter to use before executing this command.<br>Filters available: [location, type, lease_remaining]<br>An example will be "filter location clementi"|`find` requires a valid filter to function.<br>Please specify a filter to use before executing this command.
|`filter quality good`|This is an invalid command. Please try the following:<br>Filter types: [location, type, lease_remaining]<br>Example: "filter location clementi"<br>Example: "filter type 4 room", can be any of X room (X = 1 - 5) or executive<br>Example: "filter lease_remaining 90", can be any whole number from 0 - 99|`filter` command only allows for predetermined attributes.<br>Please use these filters: [location, type, lease_remaining].
|`sort`|This is an invalid command. Please try the following:<br>Example: "sort asc" or "sort desc"|`sort` requires a direction. Either asc (ascending) or desc(descending).
|Prerequisites: `find` not run <br> `sort asc` or `sort desc`|Please perform unit search first.|Perform searching of units first so that sorting can take place thereafter.
|Prerequisites: `find` not run or it does not have flats to show <br> `save 1`|Please perform unit search first.|Set an appropriate filter and run the `find` command before attempting to `save`.
Prerequisites: `find` is run and shows some flats <br> `save 101`|101 is out of range. Check that index is within the range of units displayed.|Enter an integer within the range of indexes shown from your search result.
|`remove`|This is an invalid command. Please try the following:<br>Example: "remove X", X is the index of the unit in the shortlist.|Input the index of the unit to remove.
|Prerequisites: `shortlist` is empty <br> `remove 1`|1 is out of range. Check that index is within the range of units displayed.|Have at least one unit in the shortlist by using the `save` command.
|Prerequisites: Bad internet connection or server down.|Server seems to be down, or was it your Internet connection?|Troubleshoot internet connection. If it is the server issue, please report it to our email.|

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data (shortlisted units) to another computer? 

**A**: Install the app in the other computer and copy the current `shortlist.txt` file into the same directory as the app.

## Command Summary

Action | Format
--------|----------------------------------------
**View Shortlist** | `shortlist`
**Save Shortlist** | `save <index>` eg: `save 1`
**Remove Shortlist** | `remove <index>` eg: `remove 1`
**Add a filter condition** | `filter <attribute> <value>` eg: `filter location woodlands`
**Show filter conditions** | `list`
**Clear filter conditions** | `clear`
**Search for units** | `find`
**Displays help guide** | `help`
**Sort search results by price** | `sort <order>` eg: `sort asc`, `sort desc`