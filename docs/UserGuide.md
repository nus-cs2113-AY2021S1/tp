# **WatchNext** User Guide

* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

## 1. Introduction

<img src = "images/quickstart.PNG" width = "600">

#### **WatchNext** is a show tracker made for teenagers and young adults.
<br><br> 
### 1.a. What is **WatchNext**? [Jazhten]

**WatchNext** is a show tracker designed for users who watch their favourite shows on multiple free streaming platforms and other open source streaming websites.
**WatchNext** records your progress for the different shows you are currently watching, and even for upcoming shows that you plan to watch.
<br>It additionally serves as a tracker to limit your daily watch time to help you better manage your time.
<br>For students, **WatchNext** serves the same purpose of tracking your learning progress for lectures and webcasts.

### 1.b. Why use **WatchNext**? [Jazhten]
It can be difficult and taxing to track your favourite shows through browser bookmarks.
This is especially pertinent for younger teenagers and adults who make up a large part of video entertainment media consumers.

For students like ourselves, there is also a need to manually track lecture videos and webcast progress as lecturers may use different learning management systems.

<br>This motivated our team to create an application that would not only help track all your shows and lectures, 
but also foster a community where everyone will be able to share their watchlist with like-minded individuals and friends. <br>

**WatchNext** is optimized for users who prefer to work with the Command Line Interface (CLI).<br> 

<div style="page-break-after: always;"></div>

### 1.c. What will this guide do? 

This guide aims to equip you with the knowledge on how to use our application by providing you with detailed examples for all of **WatchNext's** features. 
The features can be found in [Section 4: “Features”](#4-features-br).

Interested in using **WatchNext**? Jump to [Section 2: “Quick Start”](#2-quick-start) to get started now!
<br><br>

### 1.d. How to use this guide? \[Shikai]
Links like [this](#1c-what-will-this-guide-do) help you navigate to different parts of the document.

Text that are in a `monospace font` represent either the user input or the program output.

>  **[NOTE]**
>  The notes section contains additional information that may be helpful to you if you encounter any issues.


## 2. Quick Start \[Shikai]

Prerequisite: Ensure that you have Java 11 installed in your Computer.

1. Download the latest version from [here](https://github.com/AY2021S1-CS2113T-W12-3/tp/releases).

2. Copy the file to the folder you want to use as the home folder for your program.

3. Open a command window in that folder.

4. Run the command `java -jar WatchNext.jar`.

5. You will be greeted with this:

<img src = "images/firstopen.PNG" width = "600">

&nbsp;  

If you are unfamiliar with the CLI (Command Line Interface), do give this helpful [guide](https://www.cs.princeton.edu/courses/archive/spring14/cos126/precepts/CommandPromptTutorial.pdf) by Princeton a quick read!

## 3. Command Format

* Words in `UPPER_CASE` and within the `<>` field are the inputs that need to be supplied by the user e.g. rating `<SCORE>`.

* The commands to be entered by the user are case-insensitive (For example, using both `Help` and `help` will work).

* The maximum length for a Show `SHOWNAME` is 100 characters.

* The `<SHOWNAME>` to be entered is case-sensitive.<br><br>



## 4. Features <br>

#### 4.a. `help` and `example` - Provides a condensed help list (Benardo Tang)

 The `help` command provides a list which contains information about all the possible accepted commands.
 
 Format:
  `help`
  
 Example of usage: Let's say you want a quick description of each available command in **WatchNext**. Simply typing `help`
 will display a condensed list of each command and description to you.
  
  <div style="page-break-after: always;"></div>
  
Expected outcome:
 
<img src = "images/help.PNG" width = "550">
 
&nbsp;

 >  **[NOTE]**
 >
 >  * The `help` command does not provide the correct format. The `example` command below provides the correct format for each command.

To supplement the `help` command, The `example` command provides the correct format for every command available in **WatchNext**.
 
 Format:
  `example`

Expected outcome:
 
<img src = "images/example.PNG" width = "750">

&nbsp;

 >  **[NOTE]**
 >  * The command format listed by the `example` command adheres to the same rules as the [command format in this user guide](#3-command-format).
 >  * The `example` command is meant to be a quick refresher on the correct format, and is in no way meant to replace the user guide.Please refer to the user guide if you want a better understanding of each command.

<div style="page-break-after: always;"></div>

#### 4.b. `add` - Adds a show [Jazhten]
 The `add` command adds a show into your existing watchlist.
 
  Format:
   `add <SHOWNAME> <NUMBER OF SEASONS> <NUMBER OF EPISODES PER SEASON> <DURATION OF EPISODE>`
   
   Examples of usage: 
     `add RunningMan 2 10,12 90` <br>
     `add Friends 3 10,20,30 25` <br><br>
     
  Explanation of input: <br>
  For the example input, `add Friends 3 10,20,30 25`, a show named `Friends` is being added.<br> The show has `3` seasons, where season 1 has `10` episodes, season 2 has `20` and season 3 has `30` episodes.
>**[NOTE]**
>Note that the `10`,`20` and `30` are separated by commas without spaces in between.
>

  The duration of each episode is `25` minutes.<br>
 <img src = "images/addCommandUG/addInput.PNG" width = "600">
  &nbsp;<br>
  
 After keying in the input as shown in the image above, you should see th expected output shown in the next image.<br>
 
 <img src = "images/addCommandUG/addResponse.PNG" width = "600">
  &nbsp;<br>
  
 If you see the outcome as pictured above, it means that the show has been successfully added to **WatchNext**.
 
 If you already have an entry with the same name in your list, the program will display the following prompt:
 
 `This action will overwrite your existing data. Continue? (y/n)`
 
 If you want to replace your current entry, enter `y`. If you would like to keep your current entry, enter `n`.
 
 After this, the program will carry on based on your reply. 
 
 If you replied `y` you should see this:
 
 ```
________________________________________________________________________________
Friends was added to your watchlist.
```
<br>
 
 If you replied `n`:
 ```
________________________________________________________________________________
The process is terminated. Your existing data is kept
 ```

 >  **[NOTE]**
 >
 >  * The `<DURATION OF EPISODE>` input, which is `25` in the Friends example, is in minutes.
 >
 >  * If a show has 3 seasons, you will need to input the number of episodes for all three seasons in the `<NUMBER OF EPISODES PER SEASONS>` field. The program will not add the show into the watchlist otherwise.
 >  
 >  * You will need to input `<SHOWNAME>` as one word. If the name of your show contains more than one word you will need to input the name with no spaces (an example would be `<RunningMan>` or you may use `_` to represent a space and use `Running_Man`.)
   
 <div style="page-break-after: always;"></div>

 
#### 4.c. `edit` - Edits your show details \[Shikai]
 The `edit` command allows you to edit the name of the show, number of seasons, number of episodes, or the duration 
 of an episode depending on the input of the user when prompted.
  
 
* Suppose you would like to edit details for your favorite show, Friends.
  Format:
     `edit <SHOWNAME>`
     
* Input in the following format to use the edit feature
  Example of usage:
      `edit Friends`
    
     <img src = "images/edit_step_1.PNG" width = "600">
     


 * You will be prompted thereafter to enter the fields you want to change such as:
 name, season, episode, duration (of an episode).
 
Format:
    `name <SHOWNAME>`, `season <NUMBER OF SEASONS>`, `episode <NUMBER OF EPISODES PER SEASON,SEPERATED BY COMMAS>`, `duration <DURATION OF EPISODE>`
> **[NOTE]**
>   * You do not need to edit all the fields, you can just pick and choose which fields you would like to update

* Suppose you would like to update the name of the show

Example of usage:
     `name F.r.i.e.n.d.s` 
     
* A new season of F.r.i.e.n.d.s just started airing, and you would like to update your show details from 1 season to 2.

Example of usage:
     `season 2`
     
* Let's say that the first season has 10 episodes, and the new season of F.r.i.e.n.d.s has 11 episodes.

Example of usage:
     `episode 10,11`
     
* If the duration of each episode of F.r.i.e.n.d.s is extended to 30 minutes, use the following command:

Example of usage:
     `duration 30`
     
* When you have completed all changes, input `done`.
<div style="page-break-after: always;"></div>

Example of usage:
    `done`
    
* After your changes are successfully updated, the program will acknowledge the changes and print out the update details.

Expected outcome:
   
  <img src = "images/edit.PNG" width = "600">
   
  &nbsp;
  
* If there is an error in the process, an error message would be printed and you will return to the main landing page.
The changes that you made before the error occured will still be kept. 
  
> **[NOTE]**
>  * If you use edit to add new season(s) but did not edit the episodes numbers to reflect the change, 
>the new season(s) will be initialised to 1 episode.
>
>  * The `<DURATION OF EPISODE>` can either be inputted in minutes, such as `duration 85` or in hours and minutes
> such as `duration 1h25m` .
>
>  * The program will wait for your command to edit the show details until you input `done`.

 
 
 
### 4.d. `list` - Displays all your shows in the list

The `list` command displays all existing shows in your watchlist in an easy-to-read format, including the episode and
season that you are currently at.

The rating of your show will also be shown if you have added it using the [addreview](#4e-addreview---adding-a-review) command.

   Format:
    `list`
    
   Example of usage:
   `list`
   
   Expected outcome:
    
   <img src = "images/list.PNG" width = "650">
    
   &nbsp;

  > **[NOTE]**
  >
  > * Additional shows have been added to illustrate what your list command would look like after adding a few shows into your watch list.


 
### 4.e. `addreview` - Adding a review [Bryan Beh]
 
The `addreview` command allows you to add a review for a show, including adding a rating.

 Format:
  `addreview <SHOWNAME> <RATING> / <REVIEW>`
  
  > **[NOTE]**
  >
  > * The `<RATING>` is out of 10. The rating will not be added if it is not a value between 0 and 10.


 Example of usage:
 `addreview Friends 9 / very funny `
 
   Expected outcome:
 
  <img src = "images/addreview.PNG" width = "600">

   &nbsp;
   
   Checking with the `list` command: 
   
   <img src = "images/addreviewlist.png" width = "700">
   
   <div style="page-break-after: always;"></div>
   
### 4.f. `changereview` - Changing a review [Bryan Beh]

The `changereview` command allows you to change a review for a show that is already in the show list.

 Format:
  `changereview <SHOWNAME> / <REVIEW>`
  
   > **[NOTE]**
   >
   > * You can change the rating of your show with the [changerating](#4j-changerating---changes-rating-of-your-show) command.

  
 Example of usage:
 `changereview friends / very unfunny `
 
   Expected outcome:
   
  <img src = "images/changereview.PNG" width = "600">
  
  Checking with the `list` command:
  
  <img src = "images/changereviewlist.png" width = "700">
  

### 4.g. `deletereview` - Deletes a review [Bryan Beh]

The `deletereview` command allows you to delete a review for a show that is already in the show list.

 Format:
  `deletereview <SHOWNAME>`
  
 Example of usage:
 `deletereview Friends `
 
   Expected outcome: 
   
  <img src = "images/deletereview.PNG" width = "600">

<div style="page-break-after: always;"></div>

### 4.h. `delete` - Deletes your show [Chen Jiqing]

 The `delete` command removes a specified show from the watchlist.
 
 Format:
  `delete <SHOWNAME>`
  
 Example of usage:
 
 If you want to delete the show named Friends, you can input the command: `delete Friends`.
 
 Input:
 
 <img src = "images/delete/input.png" width = "600">  
 
<br><br>

 The show Friends will be deleted from the list ,and a success message will be printed.

 Expected outcome :
  
 <img src = "images/delete/delete.PNG" width = "600">

 &nbsp;
 
### 4.i. `deleterating` - Deletes rating of your show [Bryan Beh]

The `deleterating` command deletes the rating for an existing show in the watchlist.

 Format:
  `deleterating <SHOWNAME>`
  
 Example of usage:
 `deleterating Friends`
 
 Expected outcome:
  

 <img src = "images/deleterating.PNG" width = "600">
<br>
 <div style="page-break-after: always;"></div>

### 4.j. `changerating` - Changes rating of your show [Bryan Beh]

The `changerating` command changes the rating for an existing show which already has a rating in the watchlist.

 Format:
  `changerating <SHOWNAME> <NEWRATING>`
  
  > **[NOTE]**
  >
  > * The `<NEWRATING>` is out of 10. The rating will not be added if it is not a value between 0 and 10.
  
 Example of usage:
 `changerating Friends 9`
 
 Expected outcome:
  
 <img src = "images/changerating.PNG" width = "650">
  
 &nbsp;
 
 Checking with the `list` command :
 
 <img src = "images/changeratinglist.png" width = "700">
 

### 4.k. `season` - Changes the season of the show you are currently watching [Chen Jiqing]

The `season` command updates the current season of an existing show in your watchlist.

> **[NOTE]**
>
> * This command changes the current season that you are watching in your watch history. To change the number of episodes a show has, use the [edit](#4c-edit---edits-your-show-details) command. 

If you would only like to change the current season:
 Format:
  `season <SHOWNAME> <SEASON>`
  
 Example of usage:
 
 If your are going to watch the third season of a show named Friends, you can input `season Friends 3`.
 
  <div style="page-break-after: always;"></div>
  
 Input:
 
   <img src = "images/season/input.png" width = "600">
 
   &nbsp;
 
 The current season you are watching of Friends will be updated to season 3.
 
 Expected outcome :
  
 <img src = "images/season/output.png" width = "600">
  
<br><br>

If you would like to change both the current season and current episode:

 Format:
  `season <SHOWNAME> <SEASON> <EPISODE>`
  
### 4.l. `episode` - Changes the episode of the show you are currently watching [Chen Jiqing]

The `episode` command updates the current episode of an existing show in your watchlist.

> **[NOTE]**
> 
> * This command changes the current episode that you are watching in your watch history. To change the number of episodes a show has, use the [edit](#4c-edit---edits-your-show-details) command. 


 Format:
  `episode <SHOWNAME> <EPISODE>`
  
 Example of usage:
 
 In the current season, If your are going to watch the 10th episode of the show named Friends, you can input a command: `episode Friends 10`.
 
 Input: 
 
  <img src = "images/episode/input.png" width = "600">

  <br><br>
 
 The current episode you are watch of the show Friends will be updated to 10.
 
 > **[NOTE]**
 
 > * In the example, the current season that you are watching is season 1
 
 > * If you are going to watch other episodes of other seasons, Please change the current season first using [season](#4k-season---changes-the-season-of-the-show-you-are-currently-watching) command.
 
 
 Expected outcome for the example:
  
 <img src = "images/episode/output.png" width = "600">
  
 &nbsp;
 
### 4.m. Managing your watch time limit 

 **WatchNext** allows you to set and track your daily watch time with its two commands , `watch` and `updatetimelimit`. 
 
### 4.m.a. `watch` - Watch a show in your list  (Benardo Tang)

The `watch` command updates the watching progress for your show, and automatically updates your watch time limit.

 Format:
  `watch <SHOWNAME> `
  
 Example of usage:
 `watch Friends`
 
  > **[NOTE]**
  > * The show name must have already been added into the list.
  > * Refer to our section on [adding a show](#4b-add---adds-a-show) if you need help with adding a show into your watch list. 
 
 Explanation of input: Assuming `Friends` has already been added into the watch list, we use the example input `watch Friends`. 
 
 Before input:
 
 <img src = "images/watch_before.PNG" width = "650">
 &nbsp;
 
 To illustrate the change in your watch time limit, we have added the time limit to 120 minutes.
 The duration of `Friends` is 60 minutes. the change in time limit can be seen in the expected outcome below. 
 
 
 Expected outcome:
  
 <img src = "images/watch_after.PNG" width = "650">
  
 &nbsp;
 
 Note from the expected outcome that the show WatchHistory has been updated from S1E1 to S1E2. That is because you have told the program that you have watched S1E1 of Friends, and 
 are now watching S1E2 of Friends!<br>
 
 In addition, the previously set time limit of 120 minutes has been reduced to 60 minutes.
 

 
### 4.m.b. `updatetimelimit` - update your watch time limit (Benardo Tang)

The `updatetimelimit` command updates your daily watch time limit.

 Format:
  `updatetimelimit <DURATION LIMIT> `
 
  Example of usage:
  Let's say you want to set your daily watch time limit to 120 minutes, or 2 hours. If you have not set your watch time limit yet, your application will look like this on startup: <br><br>
  <img src = "images/updatetimelimitbefore.PNG" width = "600"> <br> &nbsp;
  
  Note the prompt above the red line, which signals to the user that the time limit has not been set yet.
  
  Now, we input `updatetimelimit 120` into the application. which updates your daily limit to 120 minutes.
 
 Expected outcome:
  
 <img src = "images/updatetimelimit.PNG" width = "700"> 

 &nbsp;
 
 > **[NOTE]**
 > * Your set duration will be reset to zero after the end of each day.
 >
 > * `<DURATION LIMIT>` should be entered in minutes. Your set duration will be stored until the next day.
 >
 > * If you have watched a show before updating your time limit, it will automatically be taken into account after you enter the `updatetimelimit` command.

 As seen from the expected outcome, you now have a limit of 2 hours to spend on watching shows.To use the allocated limit, use the [watch command](#4ma-watch---watch-a-show-in-your-list) above.

If you have **used up** your allocated time, a prompt will be displayed to you as seen below:<br> 
 <img src = "images/useduptimelimit.PNG" width = "700"> <br>  &nbsp;

If you have **exceeded** your allocated time, a prompt will be displayed to you as seen below:<br> 
 <img src = "images/exceededlimit.PNG" width = "700"> <br>  &nbsp;
 
The underlined portion shows the prompt to the user, and the circled portion details the amount of time that the user has exceeded.

 > **[NOTE]**
 > * To allocate more time for yourself, simply use the `updatetimelimit` command again with a longer duration limit.

### 4.n. `search` - search a show in the watchlist [Chen Jiqing]

The `search` command helps you search for a specific show in the watchlist and prints out the show information.  

 Format:
 `search <SHOWNAME>`  
 
 Example of usage:
 
 If you want to search a show named Friends in the list, you can use the command: `search Friends`.
 
 > **[NOTE]**
 >
 > * The `<SHOWNAME>` is case-insensitive.

 Input:
   
 <img src = "images/search/search_input.png" width = "600"> 
 
 &nbsp;
 
 If the show is exist in the list, the details of the show will be printed out.  
 
   
 Expected output:
 
 <img src = "images/searchshows.png" width = "600">  
 
 &nbsp;
  
### 4.o. `bye` - Exits the program

The `bye` command exits the program.

Format:
 `bye`
 
Example of usage:
`bye`

Expected outcome:
 
<img src = "images/bye.PNG" width = "600">
 
&nbsp;

 <div style="page-break-after: always;"></div>
 
## 5. FAQ [Chen Jiqing]
<br>

**Q**: Is my watchlist saved after I exit the program?  

**A**: Of course! The watchlist is saved into a local storage file after every input entered by the user.The same file will be loaded up when you start **WatchNext** again.  <br><br> 

**Q**: Where can I find the watchlist file if I want to share it with my friends?  

**A**: The file will be stored in the same path as the jar file, inside the "data" folder. Look for `data/userData.txt`.  <br><br> 

**Q**: Can I edit my watchlist directly in the local file?  

**A**: Yes, it is possible but not recommended. The save file stores the data in a specific format. If the format is not consistent, **WatchList** will be unable to load up file. Your stored shows may disappear.  <br><br> 

**Q**: What should I do if there was an error while I was typing in some input?

**A**: It depends on the nature of the error. WatchNext provides insightful error messages which will inform and guide you on any actions necessary. <br><br> 

**Q**: What format should I use if I want to track my lectures?  

**A**: We suggest you try `add <MODULENAME> <TOPICS> <NUMBER OF LECTURES PER TOPIC,SEPERATED BY COMMAS> <DURATION OF LECTURE>`.However, feel free to be creative and think of any format that suits you!<br><br> 

<br>
 <div style="page-break-after: always;"></div>

## 6. Command Summary

This section showcases the list of available features and usage examples for your reference.
             

|Action|Format|Example|
|--------|----------|---------------|
|Display help| `help`| `help`|
|Display command format| `example`| `example`|
|Add show|`add <SHOWNAME> <SEASON> <NUMBER OF EPISODES>,<EPISODE YOU ARE WATCHING> <DURATION OF EPISODE>` |`add Friends 2 10,10 90`|
|Edit show |`edit <SHOWNAME>`| `edit Friends`|
|Add review |`addreview <SHOWNAME> <RATING> / <REVIEW>`| `addreview Friends 9 / very funny`|
|Change review |`changereview <SHOWNAME> / <REVIEW>`| `changereview Friends / very unfunny`|
|Delete review |`deletereview <SHOWNAME>`| `deletereview Friends`|
|Show watchlist |`list`|  `list`|
|Delete show |`delete <SHOWNAME>`| `delete Friends`|
|Change rating |`changerating <SHOWNAME> <NEWSCORE>`| `changerating Friends 9`|
|Delete rating |`deleterating <SHOWNAME>`|  `deleterating Friends`|
|Update episode |`episode <SHOWNAME> <EPISODE>`|  `episode Friends 10`|
|Update season |`season <SHOWNAME> <SEASON>`|   `season Friends 3`|
|Watch a show  |`watch <SHOWNAME>`|  `watch Friends`|
|Update watch time limit |`updatetimelimit <DURATION LIMIT>`|   `updatetimelimit 120`|
|Search a show |`search <SHOWNAME>`|  `search Friends`|
|Exit program |`bye`|   `bye`|


