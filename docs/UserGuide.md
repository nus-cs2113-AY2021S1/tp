# User Guide

## Introduction

PaperTrade is a command line trading simulator that lets you try your hand at trading stocks with no risk!

__Note: Do not panic if you see the `API limit reached` error!__

What you are using is a free version. Hence, there is a limit of 5 API requests per minute.
You can pay for the premium version starting at only $29.99/month!

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `PaperTrade` from [here](https://github.com/AY2021S1-CS2113-T16-3/tp/releases/tag/v2.1).

## Features 

### Buying a stock: `buy /aapl quantity`
Buys the specified stock at market price

Format: `buy /STOCK_TICKER quantity`

* The `STOCK_TICKER` is the stock's short form
* `quantity` refers to the quantity of stocks user wants to buy; is an integer

Example of usage and output: 

To buy 2 shares of TSLA:
```
What would you like to do today?
buy /tsla 2
____________________________________________________________
You have successfully purchased 2 of tsla at 421.725.
____________________________________________________________
____________________________________________________________
You currently have $96049.14 in your wallet.
____________________________________________________________
```

To buy 1 share of FB:
```
What would you like to do today?
buy /fb 1
____________________________________________________________
You have successfully purchased 1 of fb at 282.485.
____________________________________________________________
____________________________________________________________
You currently have $95766.65 in your wallet.
____________________________________________________________
```

### Selling a stock: `sell /aapl quantity`
Sells the specified stock at market price

Format: `sell /STOCK_TICKER quantity`

* The `STOCK_TICKER` is the stock's short form
* `quantity` refers to the quantity of stocks user wants to sell; is an integer

Example of usage and output: 

To sell 1 share of GOOG:
```
What would you like to do today?
sell /goog 1
____________________________________________________________
You have successfully sold 1 of goog at 1602.13.
____________________________________________________________
____________________________________________________________
You currently have $95766.65 in your wallet.
____________________________________________________________
```

### Searching for info about a stock: `search /aapl`
Shows information about a stock like price and volume

Format: `search /STOCK_TICKER`

* The `STOCK_TICKER` is the stock's short form

Example of usage: 

`search /nflx`

`search /shop`

### View your portfolio: `view`
Shows the stocks you have, its quantity and current price. Also shows transaction history.

Format: `view`

Example of usage: 

```
What would you like to do today?
view
____________________________________________________________
1. Symbol: aapl, total quantity: 1, Current Price: 116.14500000000001
	BUY 1 at 116.14500000000001 on 2020-10-28T19:50:31.555462
Total holding asset = $116.15
Total cost = $116.15
Total Profit/Loss = $0.0

2. Symbol: msft, total quantity: 1, Current Price: 209.695
	BUY 1 at 209.695 on 2020-10-28T19:50:36.916125
Total holding asset = $209.7
Total cost = $209.7
Total Profit/Loss = $0.0
____________________________________________________________

```

### Check your wallet: `wallet`
Shows you how much cash you have left in your wallet to buy stocks, how much you have allocated to stocks and your net profit.

Format: `wallet`

Example of usage: 

```
What would you like to do today?
wallet
____________________________________________________________
You currently have $99674.16 in your wallet.
Current market value of all your equities owned: $325.84
Loss of : -$0.00
____________________________________________________________
```

### Bookmark stocks to keep on watchlist: `mark /STOCK_TICKER`, `unmark /STOCK_TICKER`, `bookmarks`
Allows you to bookmark stocks for easy access of stocks that you are actively watching
* The `STOCK_TICKER` is the stock's short form
* There is a limit of up to __5__ stocks that can be added to bookmarks. 

#### Marking Stocks: 

Format: `mark /STOCK_TICKER`

Example of usage:
```
What would you like to do today?
mark /msft
____________________________________________________________
You have added MSFT to bookmarks.
____________________________________________________________
```

#### Unmarking Stocks: 

Format: `unmark /STOCK_TICKER`

Example of usage: 
```
What would you like to do today?
unmark /aapl
____________________________________________________________
You have removed AAPL from bookmarks.
____________________________________________________________
```

#### Viewing info of all bookmarked stocks: 

Format: `bookmarks`

Example of usage: 
```
What would you like to do today?
bookmarks
____________________________________________________________
Here is the latest information on AAPL:
date:   2020-11-06T20:00
open:   118.53
high:   118.54
low:    118.52
close:  118.52
volume: 5859
____________________________________________________________
____________________________________________________________
Here is the latest information on MSFT:
date:   2020-11-06T20:00
open:   223.4
high:   223.4
low:    223.4
close:  223.4
volume: 190
____________________________________________________________
```

### Exit: `bye`
Exits the program. Duke will save your info for the next time you come back!

Format: `bye`

Example of usage: 
```
What would you like to do today?
bye
____________________________________________________________
Goodbye! Hope to see you again.
____________________________________________________________
```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Just copy over any .ser files into the data folder.


## Command Summary

* Buy a stock `buy /aapl quantity`
* Sell a stock `sell /aapl quantity`
* Search for a stock's info `search /aapl`
* View portfolio `view`
* Check your wallet `wallet`
* Bookmark a stock `mark /aapl`
* Remove a stock from bookmarks `unmark /aapl`
* View bookmarked stocks `bookmarks`
* Exit program `bye`
