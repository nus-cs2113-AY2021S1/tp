# Wei Shuang's Project Portfolio Page

## Project: Trippie 

Trippie is a command-line desktop application used for planning trips, tracking expenses and converting exchange rates.
It is specifically catered for the needs of student travellers. It allows multiple trip plans and is designed for quick
and efficient inputs.

### Summary of Contributions
Given below are a list of my contribution to the project.

- **New feature**: Added the ability for users to save their trips.
    - What it does: It allows users to store and edit their trips in a text file.
    - Justification: This feature enables users to save their trips after every edit made to their trips, and to 
    continue editing even after exiting Trippie.
    - Highlights: The output of trips into the text file is done in a reader-friendly manner, allowing users to view
    the details of the trips in one go.
- **New feature**: Added the ability to print a help list for users.
    - What it does: It prints a list of commands available for usage in Trippie.
    - Justification: Due to the large number of functions available in Trippie, this help functions allows users to
    refer and a convenient way to view all the commands available.
    - Highlights: The help command is printed in a table format for easier reading.
    
- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=weishuangtan&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project management:**
    - Managed and organised issues into `Trippie` project on GitHub.
    - Managed and assigned issues for `v2.1` on GitHub.
    - Managed release `v1.0` on GitHub.
    
- **Enhancements to existing features:**
    - Implemented TrippieException class for error handling mechanisms. [#36](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/36)
    - Enhanced the error handling mechanism for `v1.0` features related to expenses with TrippieInvalidArgumentException
    and TrippieIllegalCommandException [#36](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/36)
    - Enhanced the error handling for new trip and load trip commands. [#82](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/82)
    - Wrote additional tests for existing features to increase coverage at initial phase [#66](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/66)

- **Contributions to documentation:**
    - User Guide:
        - Added documentations for the features `search`, `delete /p` and `budget` [here](https://ay2021s1-cs2113t-w11-2.github.io/tp/UserGuide#58-searching-for-place-search-wei-shuang)
        - Added the Command Summary section [here](https://ay2021s1-cs2113t-w11-2.github.io/tp/UserGuide#70-command-summary-wei-shuang)
    - JavaDoc:
        - Added documentations for JavaDoc for the following classes and its methods: [#148](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/148)
            - Commands: `AddExpenseCommand`, `DeleteExpenseCommand`, `BudgetCommand`, `HelpCommand`.
            - Data: `Expense`
            
- **Contributions to the DG:**
    - Added full-scale descriptions for `Parser`, `Command` and `Exceptions` under Design, and `Import and Export 
    Files`.
    - Added class diagrams created using *Miro*.

- **Review contributions:**
    - PR reviewed (with non-trivial review comments): [#111](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/111) [#147](https://github.com/AY2021S1-CS2113T-W11-2/tp/pull/147)