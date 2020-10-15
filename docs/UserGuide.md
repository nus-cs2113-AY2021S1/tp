# User Guide
## Setting Up
Prerequisites : [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* Refer to [Setting Up](https://github.com/AY2021S1-CS2113T-F11-4/tp/blob/master/README.md#setting-up-in-intellij) guide for instructions to set up the
 project in IntelliJ.
 
* Download the [JAR file](https://github.com/AY2021S1-CS2113T-F11-4/tp) 
 and run the command `java -jar tp.jar`


## Features 
### Project `project`
#### Viewing project information
View project information which includes title, description, time-period and team members of the project. 
It displays the project backlog, and the details of the ongoing sprint.
* Format: `project /view`
* Usage command: `project/view`
* Constraints:
    * A project must be created by the user before using this command.
* Expected outcome:
    ```
    ================= PROJECT =================  
    [Title: MeTube ]  
       [Description: video streaming software ]  
       [No members added]  
       [Project will start along with the first sprint]  
       [Project backlog is empty]  
       [There are no Sprints]  
    ===============================================
    ```
    
#### Create project
Create a new project.
* Format: `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>`
* Usage command: `project /create -title MeTube -desc video streaming software -dur 90 -sd 10`
    * Creates a project titled MeTube which is a video streaming software. 
    * The project will last for 90 days and the sprints will run on a 10 day interval.

* Constraints:
    * Duration and sprint interval must be integers.
    * All the fields must hava an input, else a project is not added.
* Expected outcome:
    ```Project successfully created.  
    ================= PROJECT =================  
    [Title: MeTube ]  
       [Description: video streaming software ]  
       [No members added]  
       [Project will start along with the first sprint]  
       [Project backlog is empty]  
       [There are no Sprints]  
    ===============================================
    ```

      