package seedu.duke.model;


import seedu.duke.api.StockPriceFetcher;
import seedu.duke.data.exception.DukeException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortfolioManager {
    private Portfolio portfolio;
    private static Logger logger = Logger.getLogger("tp");

    public PortfolioManager() {
        // load portfolio object from file
        // if does not exists, create the file
        portfolio = new Portfolio();
    }

    public void buyStock(String symbol, int quantity, double buyPrice) {
        logger.log(Level.INFO, "buying stock ...");
        portfolio.buyStock(symbol, quantity, buyPrice);
    }

    public void sellStock(String symbol, int quantity, double sellPrice) throws DukeException {
        logger.log(Level.INFO, "selling stock ...");
        portfolio.sellStock(symbol, quantity, sellPrice);
    }

    public ArrayList<Stock> getAllStocks() {
        return portfolio.getAllStocks();
    }

}
