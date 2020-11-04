
# Chan Jian Hao - Project Portfolio Page

## Overview

**AniChan** is a desktop application created for anime translators to help efficiently manage their workload and time. It has a wide variety of tools that can help improve their workflow efficacy and translation accuracy. **AniChan** is built for fast-typists, as such the user will interact with it through a CLI. It is written in Java with almost 14K LOC.

### Summary of Contributions

Given below are my contributions to the project.

### Summary of Contributions
*   **New Feature:** Added the ability to create `User`.
    *   What it does: On first execution, prompts user for his name and gender to create `User` profile.
    *   Justification: This feature allows program to know user better so that program may greet user appropriately as part of good user experience we hope to provide.
    *   Highlights: Notably, `User` is a subclass of `Human` abstract class. Other classes such as `Character` and `VoiceActor` inherits `Human` class as part of our OOP practice when coding **AniChan** which stores lots of anime data which comes with lots of voice actors and characters. **User** class contains many interesting methods meant for controlling `Workspace` feature of AniChan.

*   **New Feature:** Added the ability to create, list, switch, and delete `Workspace`.
    *   What it does: Allows the user to create, list, select, and delete `Workspace`.
    *   Justification: This feature enables the user to have segregation in his translation work. As `Watchlist` and `Bookmark` are contained in individual `Workspaces`.
    *   Highlights: `Workspace` works closely with core components of AniChan such as `User`, `Watchlist`, `Bookmark` and notably, `Storage`. To ensure secure and safe creation of Workspace when program exits, layers of string validation and sanitization are implemented to ensure no unexpected behaviors when creating `Workspace` in `data` folder.

*   **New Feature:** Added customised exception class, `AniException`.
    *   What it does: Allows application to throw custom exceptions specific to **AniChan**.
    *   Justification: There needs to be a way to handle exception which are caused by **AniChan**.
    *   Highlights: Made a special exception package and class which extends Java default `Exception` class.

*   **New Feature:** Added logging class `AniLogger`.
    *   What it does: Logs information during **AniChan** runtime to both console and file system `data/AniChan.log` file.
    *   Justification: There needs to be a way to handle logging for all the classes in **AniChan** without duplicating Java `logger` everywhere.
    *   Highlights: Implemented method for all classes to easily get logger for their own logging usage. Standardized application-wide **AniChan** logging settings and level for both console and file system. **AniLogger** is fully capable of handling add and removals of console and file handlers and manage logs creation and exceptions, if any.

*   **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chanjianhao)
    
*   **Documentation:**
    *   User Guide: 
        *   a
    *   Developer Guide:
        *   a
    
*   **Team-based Tasks**
    *   a
    
*   **Beyond Project Team Tasks**
    *   a



Code contributed: 

