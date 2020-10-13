package seedu.duke.controller;

import org.patriques.output.timeseries.data.StockData;
import seedu.duke.api.StockPriceFetcher;

import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.SearchCommand;
import seedu.duke.command.BuyCommand;
import seedu.duke.command.SellCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.data.exception.DukeException;
import seedu.duke.model.PortfolioManager;
import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;

public class Controller {
    private Ui ui;
    private StockPriceFetcher stockPriceFetcher;
    private PortfolioManager portfolioManager;

    public Controller() {
        ui = new Ui();
        stockPriceFetcher = new StockPriceFetcher();
        portfolioManager = new PortfolioManager();
    }

    private void buyStock(String symbol, int quantity) {
        try {
            double price = stockPriceFetcher.fetchLatestPrice(symbol);
            portfolioManager.buyStock(symbol, quantity, price);

            ui.printWithDivider("You have successfully purchased "
                    + quantity + " of " + symbol + " at " + price + ".");
        } catch (DukeException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

    private void sellStock(String symbol, int quantity) {
        try {
            double price = stockPriceFetcher.fetchLatestPrice(symbol);
            portfolioManager.sellStock(symbol, quantity, price);

            ui.printWithDivider("You have successfully sold "
                    + quantity + " of " + symbol + " at " + price + ".");
        } catch (DukeException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

    public void runApp() {
        ui.greetUser();

        while (true) {
            String userInput = ui.getUserInput();
            Command command = Parser.parseCommand(userInput);

            if (command instanceof SearchCommand) {
                SearchCommand searchCommand = (SearchCommand) command;
                searchSymbol(searchCommand.getSearchKey());
            } else if (command instanceof InvalidCommand) {
                InvalidCommand invalidCommand = (InvalidCommand) command;
                ui.printWithDivider(invalidCommand.getErrorMessage());
            } else if (command instanceof ByeCommand) {
                ui.printWithDivider("Goodbye!");
                break;
            } else if (command instanceof BuyCommand) {
                BuyCommand buyCommand = (BuyCommand) command;
                buyStock(buyCommand.getSymbol(), buyCommand.getQuantity());
            } else if (command instanceof SellCommand) {
                SellCommand sellCommand = (SellCommand) command;
                sellStock(sellCommand.getSymbol(), sellCommand.getQuantity());
            } else if (command instanceof ViewCommand) {
                viewPortfolio();
            }
        }
    }

    public void viewPortfolio() {
        ui.view(portfolioManager.getAllStocks());
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
        } catch (DukeException e) {
            ui.printWithDivider(e.getMessage());
        }
    }

}
