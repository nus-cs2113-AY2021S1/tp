# Jun Xian - Project Portfolio Page

## Overview: PaperTrade
PaperTrade is a command line paper trading application that allows users to
simulate the buying and selling of stocks. A paper trade is a simulated trade 
that allows an investor to practice buying and selling stocks without risking
real money.

### Summary of Contributions
* **New Feature**: Added **View** function
    * What it does: Allow users to view portfolio of stocks that they currently own.
    It includes quantity owned, transaction history, current market price and current
    profit/loss. 
    
    * Justification: This is a feature that allows users to keep track of their portfolio
    of stocks and to track how well their stocks owned are doing in terms
    of profit/loss, relative to latest market price.
    
    * Highlights: To implement this, there is `PortfolioManager` that is able to access `Portfolio` which encapsulates 
    `Stock` and `Transaction`. `Stock` object also has a method to retrieve latest market price through the use of
    `StockPriceFetcher`.
   
Code contributed: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=JunxianAng&tabRepo=AY2021S1-CS2113-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code)

### Documentation
* **Developer Guide**:
    * Added Object State Diagrams to give the overall view of `View` portfolio stocks function.
    Also explained the step by step changes made to the respective classes as the `View` method is called
    in each class.
    [#45](https://github.com/AY2021S1-CS2113-T16-3/tp/pull/45)
    