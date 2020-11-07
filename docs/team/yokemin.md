# Yoke Min - Project Portfolio Page

## Overview: PaperTrade
PaperTrade is a command line paper trading application that allows users to
simulate the buying and selling of stocks. A paper trade is a simulated trade 
that allows an investor to practice buying and selling stocks without risking
real money.

### Summary of Contributions
* **New Feature**: Added **Sell** function
    * What it does: To allow users to sell a particular stock that they currently own
    
    * Justification: This is one of the main feature of our program, to allow users to simulate the selling
    of a stock.
    
    * Highlights: To implement this, I created the sellStocks methods in various classes (`Stock`, `Transaction`, 
    `Portfolio`, `PortfolioManager`, `Controller` and the methods to parse user inputs in the `Parser` class.
    
* **New Feature**: Added **mark, unmark and bookmarks** function
    * What it does: `mark` allows the user to add a stock to their bookmarks; 
    `unmark` allows the user to remove a stock from their bookmarks;
    `bookmarks` allows the user to view the details of the stocks in their bookmarks. 
    
    * Justification: This feature is to allow users to easily view the details of stocks that they
    are interested in or want to frequently keep track of. 
    
    * Highlights: To implement these three functions, I added the `Bookmarks` and `BookmarksManager` classes.
    I also implemented command classes which helps to execute the command. The `Bookmarks` would hold an arraylist
    of `Strings` which contains the ticker symbol of the bookmarked stocks. 
    The `BookmarkManager` is in charge of managing the `Bookmarks`.  
    
* **New Feature**: Added **Api** class (Not used in the end product)
    * What it does: Allows us to retrieve the relevant information of the stocks
    
    * Justification: We needed to get real time information about the stock prices and stock details. 
    
    * Highlights: To implement this, I used  the [API documentation](https://www.alphavantage.co/documentation/) 
    to write the code to retrieve the JSON files that contained the information of the stocks. However, as we only needed
    retrieve one type of stock data, Stock time series Intraday, we used an external library to retrieve stock
    information instead.
   
Code contributed: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=yokemin&tabRepo=AY2021S1-CS2113-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

### Documentation
* **User Guide**:
    * Edit the User Guide for bookmarks, mark and unmark function. [#97](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/97)
    * Update the User Guide to correct the input formats. [#52](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/52)
    

* **Developer Guide**:
    * Added Object State Diagrams to give the overall view of buy/sell stock function.
    Also explained the step by step changes made to the respective classes as the buyStock method is called
    in each class. Added the Sequence Diagram for the flow of the sell stock function.
    [#44](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/44)
    [#50](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/50)
    