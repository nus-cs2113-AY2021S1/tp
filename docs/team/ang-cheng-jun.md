# Ang Cheng Jun - Project Portfolio Page

## Overview
SmartHomeBot is a **desktop application that consolidates all home appliance’s control into a 
centralized system via a Command Line Interface (CLI)**. Users can switch on or off appliances by using this application
and review and monitor electricity usage; having a clearer picture of their electrical usage patterns. They will need to 
create a location and add appliances into the particular location to enable the on/off feature. There is 
a AutoSave feature which will save all your location, appliances and appliances' electrical usage into a text file. All the 
appliances will be switch off when the user exit the application. Upon the start of the application, the data of the text file
will be imported to the application. 

### Summary of Contributions
#### Code Contributed
Below is the link to view all the codes that I contributed to SmartHomeBot Project. Click on RepoSense to direct you to the page

* [RepoSense](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ang-cheng-jun&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Enhancements implemented
1. I created a ListCommand filter which allows the users to list all the appliances in that particular location. 
   * Implementation of ListCommand filter
      * I used stream method to store all the Appliance Class which stores the filtered location into a new ArrayList and display the list to user. 

2. In addition, to add ease to the typing of command, I design a logic function in OnCommand which allows users to key you `on [APPLIANCE_NAME]` or 
`on [APPLIANCE_NAME]` which allows user to switch on an appliance or a group of appliance in the location.This logic function is
also implement in the OffCommand.  
   * Implementation of logic function which ease the typing of command for OnCommand and OffCommand 
     * For the logic function, I assume that argument that the user typed is an appliance initially. In the function, it will check if any of the Appliance Class in
     the ApplianceList has the location similar to the argument that user typed.
     * If there is similar location in the Appliance Class, then we know that the argument is a location. All Appliance Class with the similar location will store in a new ArrayList 
     and turn on/off all the appliance in the ArrayList. Then, it will display the corresponding message to user.
     * If there is no Appliance Class' location is similar to the argument, then we check if the location exists in the LocationList or not. If 
     it exists, then we know that the argument is a location. This means that there is no appliance in that location, and it will display the corresponding message to user.
     * If both check failed, then we can assume that the argument is an appliance. Then the function will look for the Appliance Class' name which is similar to the argument
     and turn on/off the appliance and display the corresponding message to the user.
     * If argument cannot be found in both LocationList and ApplianceList, we can conclude that the argument is invalid and display the corresponding message to the user.
     
#### Contributions to the UserGuide
Initially, I did the overall layout of the UserGuide which allowing all the team member including me to know where to input their content of the function that they have implemented.
In addition, I have added images to the UserGuide for better illustration to the users.
When all the team members have completed their subsection, I reviewed their content to check for any wrong information in the content. 
Refer to the link below to view the UserGuide.

* [UserGuide](https://ay2021s1-cs2113-t14-1.github.io/tp/UserGuide.html)

#### Contributions to the DeveloperGuide
Initially, the whole group including me brainstormed to decide on the user stories which is used in the DeveloperGuide. You can refer to the link below on Trello to see the contribution,
in the user stories.

* [Trello](https://trello.com/b/s32JQHmK/cs2113-storyboard)

 
I also write and design the Sequence Diagram for ListCommand, HelpCommand, InvalidCommand and ExitCommand. All the information and design 
follow the textbook standard provided to us. And also, I did the Test Cases for list command which is in the Appendix E. You can refer
the link below on DeveloperGuide to view the contribution.

* [DeveloperGuide](https://ay2021s1-cs2113-t14-1.github.io/tp/DeveloperGuide.html)

#### Review/mentoring contributions:
Provided feedbacks to team members on their code through pull request and also private messaging. Refer the links below on some of my feedback 
to the team members through pull request.

* [Pull Request 1](https://github.com/AY2021S1-CS2113-T14-1/tp/pull/201)
* [Pull Request 2](https://github.com/AY2021S1-CS2113-T14-1/tp/pull/208)

In addition, I always get involved in discussion on a better implementation of method in the function. Most of our discussion is on Zoom. 

#### Contributions beyond the project team:
Provided post feedbacks to other project teams on the bug i encountered during the PE Dry Run. Refer the links below on some of my post feedback 
to other project team through the issue tracker.

* [Bug Issue 1](https://github.com/Ang-Cheng-Jun/ped/issues/9)
* [Bug Issue 2](https://github.com/AY2021S1-CS2113-T13-2/tp/issues/220)