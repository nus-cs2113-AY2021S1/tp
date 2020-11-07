# Alvin - Project Portfolio Page

## Overview: PaperTrade
PaperTrade is a command line paper trading application that allows users to
simulate the buying and selling of stocks. A paper trade is a simulated trade 
that allows an investor to practice buying and selling stocks without risking
real money.

### Summary of Contributions
* **New Feature**: Added a class to call the **Alphavantage API**
    * What it does: To fetch the latest prices of a particular stock given its ticker symbol.
    
    * Justification: This feature is important throughout the application as we need to obtain
    the latest price of a stock whenever we are buying/selling a stock. Also, when we try to 
    view our portfolio, we need the latest price to calculate the unrealised profits etc.
    
    * Highlights: I used an external library ([alphavantage4j](https://github.com/patriques82/alphavantage4j))
    which is actually a Java wrapper to help simply the process of fetching the prices. I wrote a 
    `StockPriceFetcher` class that uses this wrapper to obtain the latest prices. 
    
* **New Feature**: Added **Search** function
    * What it does: Return the stock information for the particular stock, given its ticker symbol.
    It includes the opening price, high price, low price, closing price, etc.
    
    * Justification: This feature is to allow users to keep track of the latest price of a particular
    stock before deciding to buy or sell.
    
    * Highlights: As this was one of the first feature to be implemented, I tried to lay down the 
    project architecture and packages, so that my other group members can easily understand contribute
    their code and minimize any merge conflicts. The project is largely split into the model, controller, 
    api and ui, where each package would contain respective classes that each have their own responsiblities.
    
* **New Feature**: Added **Buy** function
    * What it does: Buy a particular stock given its ticker symbol.
    
    * Justification: This is one of the main feature of our program, to allow users to simulate the buying
    of a stock.
    
    * Highlights: To implement this, I created the model classes, e.g. `Stock`, `Portfolio` and `PortfolioManager`
    to abstract the features of a stock and portfolio. The `Portfolio` would hold a list of `Stock` while the 
    `PortfolioManager` is in charge of managing the `Portfolio`.  
    
* **New Feature**: Added **data persistency** 
    * What it does: Data persistency for all the stocks that you have purchased, so that you don't lose them
    when you quit the program.
    
    * Justification: This is an important feature so that users can track their portfolio over time. 
    
    * Highlights: Used `Serializable` to store the `Portfolio` object into a binary file. Loads the `Portfolio`
    object from the binary file the next time the program starts again so that users don't lose their information from the
    previous session.
   
Code contributed: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=trolommonm&tabRepo=AY2021S1-CS2113-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

### Documentation
* **User Guide**:
    * Update the User Guide to include exmaples of the input and output from the program. [#54](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/54/commits/648043fd7c90f16c964d1d773efdd4b56e6d1939)

* **Developer Guide**:
    * Added the Package Diagram as well as the High Level Class Diagram to give the overall view of the project structure.
    Also explained how to set up the project in IntelliJ, gave an overview of the responsibilities of each of the classes 
    and packages and a sequence diagram of the lifecycle of the program. [#35](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/35)
    [#94](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/94)
    