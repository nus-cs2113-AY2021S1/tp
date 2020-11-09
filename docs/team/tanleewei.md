# Tan Lee Wei - Project Portfolio Page

## Overview
SmartHomeBot is a **desktop application that consolidates all home appliance’s control into a 
centralized system via a Command Line Interface (CLI)**. Even though the main target of the 
SmartHomeBot is disabled home users with mobility issues, it can be used by anyone that wants to have
a bot to help in controlling household appliances but prefer typing than speaking to the bot. The main goal
of this application is to help to make the life of the user easier and more convenient by allowing the user to add 
their household appliances into the appliance, allowing the user to switch on and off their appliances throught the 
application and also monitor their electrical power usage.

### Summary of Contributions

## Code Contributed

Below is the link to view all the codes that I contributed to SmartHomeBot Project. Click on 
RepoSense to direct you to the page:

* [RepoSense](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=TanLeeWei&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Main Function implemented
I am mainly responsible for the storage package and some of the data package for SmartHomeBot.

1. LocationList: list of stored locations.
2. StorageFile
    1. ReadStorageFile: Read the data is the .txt storage file back into the program.
    2. WriteStorageFile: Write the data in the program into the .txt storage file.

##### Enhancement implemented
I implemented the WriteStorageFile in such a way that each time the user entered a command, the program 
will automatically save the updates and overwrites all the data in the storage file with the new contents, so that 
this will prevent any lost of data even if the program crashes halfway.

#### Contributions to UserGuide
I have provided explanations and examples for some of the SmartHomeBot functions. I have also added some images as 
examples on how to use the application to give a better illustration on how to use the application to the users. After
my team members and I have complete our parts of the UserGuide, we will come together to review the UserGuide 
to find out any mistakes we have made and parts we are lacking in the UserGuide.

* [UserGuide](https://ay2021s1-cs2113-t14-1.github.io/tp/UserGuide.html)

#### Contributions to DeveloperGuide
I have done explanations and designs for the sequence and class diagrams of storage components. I have also done 
explanations and designs for the sequence diagrams of AddCommand and DeleteCommand. Refer to the link below to view 
the DeveloperGuide.

* [DeveloperGuide](https://ay2021s1-cs2113-t14-1.github.io/tp/DeveloperGuide.html)

#### Contributions beyond the project team:
I have also provided in other group's project development by reviewing the products during tutorials and PE dry runs.

* [Bug Issue 1](https://github.com/TanLeeWei/ped/issues/4)
* [Bug Issue 2](https://github.com/TanLeeWei/ped/issues/1)

