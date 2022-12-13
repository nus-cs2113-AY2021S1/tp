# Yang Jiaqi - Project Portfolio Page

## Project: NUSchedule

NuSchedule is a desktop task management application used for keeping track of user's events. The user interacts with it 
using a Command Line Interface (CLI). It is written in Java and has around 5 kLoC.

Given below are my contributions to the project:  
* __Main Frame:__ Added the Location classes to an event 
    * What it does: a location element will allow the user to save the location of their events. The location element 
    will also include additional information about the location. 
    * Justification: This feature will users to note down the location which the event is occurring at. 
    If the place is located within NUS, the additional information would include the nearest buildings and bus stops, 
    which allows our target audience to be able to easily find the exact place. 

* __Main Frame:__ Added Location List classes
    * What it does: record all the pre-saved location data of places within NUS. It also includes a separate bus stops 
    list, which saves bus stop data. Users are able to view the entire list by keying in the command `locations` or 
    `busstops`.
    
* __Storage:__ Added location data of locations within NUS

* __New Feature:__ Added a `locate` command to find a location using location name or event index
    * Justification: This feature will allow user to receive location information. It allows users to find more 
    information using a location name, or an event index based on the event list. 

* __Code Contributed:__ [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jiaaaqi)

* __Project Management:__ 
    * Managed releases `v1.0` - `v2.0` on GitHub

* __Documentation:__
    * User guide: 
        * Added documentation for `locate` command ([#84](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/84), [#176](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/176))
    * Developer guide: 
        * Added design details for Location, under Model ([#69](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/69/files), [#75](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/75))
        * Added implementation details for `locate` feature ([#69](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/69/files), [#75](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/75))
        * Added target user profile and value proposition ([#67](https://github.com/AY2021S1-CS2113T-F14-4/tp/pull/67/files))
        
* __Community:__
    * PRs reviewed (with non-trivial comments): [Fitr](https://github.com/nus-cs2113-AY2021S1/tp/pull/16)
    * Reported bugs in other teams: example [1](https://github.com/AY2021S1-CS2113T-W12-4/tp/issues/323), [2](https://github.com/AY2021S1-CS2113T-W12-4/tp/issues/319), [3](https://github.com/AY2021S1-CS2113T-W12-4/tp/issues/322)
