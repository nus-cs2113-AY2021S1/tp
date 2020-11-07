package seedu.duke.controller;

import org.patriques.output.timeseries.data.StockData;
import seedu.duke.api.StockPriceFetcher;
import seedu.duke.command.AddBookmarkCommand;
import seedu.duke.command.BuyCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.RemoveBookmarkCommand;
import seedu.duke.command.SearchCommand;
import seedu.duke.command.SellCommand;
import seedu.duke.command.ViewBookmarkedStocksCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.WalletCommand;
import seedu.duke.exception.DoNotOwnStockException;
import seedu.duke.exception.InsufficientFundException;
import seedu.duke.exception.InsufficientQtyException;
import seedu.duke.exception.NegativeQtyException;
import seedu.duke.exception.PaperTradeException;
import seedu.duke.model.BookmarksManager;
import seedu.duke.model.PortfolioManager;
import seedu.duke.model.Stock;
import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;

public class Controller {
    private Ui ui;
    private StockPriceFetcher stockPriceFetcher;
    private PortfolioManager portfolioManager;
    private BookmarksManager bookmarksManager;

    public Controller() {
        ui = new Ui();
        stockPriceFetcher = new StockPriceFetcher();
        portfolioManager = new PortfolioManager();
        bookmarksManager = new BookmarksManager();
    }

    private void buyStock(String symbol, int quantity) {
        try {
            double price = stockPriceFetcher.fetchLatestPrice(symbol);
            portfolioManager.buyStock(symbol, quantity, price);

            ui.printWithDivider("You have successfully purchased "
                    + quantity + " of " + symbol + " at " + price + ".");
            ui.printWithDivider("You currently have $"
                    + String.format("%.02f", portfolioManager.getWalletCurrentAmount()) + " in your wallet.");
        } catch (PaperTradeException | InsufficientFundException | NegativeQtyException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

    private void sellStock(String symbol, int quantity) {
        try {
            double price = stockPriceFetcher.fetchLatestPrice(symbol);
            portfolioManager.sellStock(symbol, quantity, price);

            ui.printWithDivider("You have successfully sold "
                    + quantity + " of " + symbol + " at " + price + ".");
            ui.printWithDivider("You currently have $"
                    + String.format("%.02f", portfolioManager.getWalletCurrentAmount()) + " in your wallet.");
        } catch (DoNotOwnStockException | InsufficientQtyException | PaperTradeException | NegativeQtyException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

    public void runApp() {
        ui.greetUser();

        while (true) {
            String userInput = ui.getUserInput();
            Command command = Parser.parseCommand(userInput);
            if (!executeCommand(command)) {
                break;
            }
        }
    }

    public void addBookmark(String symbol) {
        try {
            stockPriceFetcher.fetchLatestPrice(symbol);
        } catch (PaperTradeException e) {
            ui.printWithDivider("Error validating stock, might be an invalid stock!");
            return;
        }
        try {
            bookmarksManager.addToBookmarks(symbol);
            ui.printWithDivider("You have added " + symbol + " to bookmarks.");
        } catch (PaperTradeException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

    public void removeBookmark(String symbol) {
        try {
            bookmarksManager.removeBookmark(symbol);
            ui.printWithDivider("You have removed " + symbol + " from bookmarks.");
        } catch (PaperTradeException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

    private boolean executeCommand(Command command) {
        if (command instanceof SearchCommand) {
            SearchCommand searchCommand = (SearchCommand) command;
            searchSymbol(searchCommand.getSearchKey());
        } else if (command instanceof InvalidCommand) {
            InvalidCommand invalidCommand = (InvalidCommand) command;
            ui.printWithDivider(invalidCommand.getErrorMessage());
        } else if (command instanceof ByeCommand) {
            ui.printWithDivider("Goodbye! Hope to see you again.");
            return false;
        } else if (command instanceof BuyCommand) {
            BuyCommand buyCommand = (BuyCommand) command;
            buyStock(buyCommand.getSymbol(), buyCommand.getQuantity());
        } else if (command instanceof SellCommand) {
            SellCommand sellCommand = (SellCommand) command;
            sellStock(sellCommand.getSymbol(), sellCommand.getQuantity());
        } else if (command instanceof ViewCommand) {
            viewPortfolio();
        } else if (command instanceof WalletCommand) {
            viewWallet();
        } else if (command instanceof AddBookmarkCommand) {
            AddBookmarkCommand addBookmarkCommand = (AddBookmarkCommand) command;
            addBookmark(addBookmarkCommand.getSymbol());
        } else if (command instanceof RemoveBookmarkCommand) {
            RemoveBookmarkCommand removeBookmarkCommand = (RemoveBookmarkCommand) command;
            removeBookmark(removeBookmarkCommand.getSymbol());
        } else if (command instanceof ViewBookmarkedStocksCommand) {
            viewBookmarkedStocks();
        }

        return true;
    }

    private void viewBookmarkedStocks() {
        if (bookmarksManager.getBookmarks().getBookmarkedStocks().size() == 0) {
            ui.printWithDivider("Currently no stocks bookmarked! Try bookmarking stock using mark /TICKER");
        }
        for (String symbol : bookmarksManager.getBookmarks().getBookmarkedStocks()) {
            searchSymbol(symbol);
        }
    }

    public void viewPortfolio() {
        ui.view(portfolioManager.getAllStocks());
    }

    public void viewWallet() {
        double amount = 0.00;
        for (Stock stock : portfolioManager.getAllStocks()) {
            try {
                amount += (stockPriceFetcher.fetchLatestPrice(stock.getSymbol())) * stock.getTotalQuantity();
            } catch (PaperTradeException e) {
                ui.printWithDivider(e.getMessage());
            }
        }
        ui.viewWallet(portfolioManager.getWalletCurrentAmount(), portfolioManager.getWalletInitialAmount(), amount);
    }

    public void searchSymbol(String symbol) {
        try {
            StockData stockData = stockPriceFetcher.fetchLatestStockData(symbol);

            ui.printWithDivider(
                    "Here is the latest information on " + symbol + ":",
                    "date:   " + stockData.getDateTime(),
                    "open:   " + stockData.getOpen(),
                    "high:   " + stockData.getHigh(),
                    "low:    " + stockData.getLow(),
                    "close:  " + stockData.getClose(),
                    "volume: " + stockData.getVolume()
            );
        } catch (PaperTradeException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

}
