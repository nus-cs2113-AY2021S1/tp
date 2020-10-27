package seedu.duke.model;


import seedu.duke.exception.DoNotOwnStockException;
import seedu.duke.exception.InsufficientFundException;
import seedu.duke.exception.InsufficientQtyException;

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

    public void buyStock(String symbol, int quantity, double buyPrice) throws InsufficientFundException {
        logger.log(Level.INFO, "buying stock ...");
        portfolio.buyStock(symbol, quantity, buyPrice);
    }

    public void sellStock(String symbol, int quantity, double sellPrice)
            throws InsufficientQtyException, DoNotOwnStockException {
        logger.log(Level.INFO, "selling stock ...");
        portfolio.sellStock(symbol, quantity, sellPrice);
    }

    public double getWalletCurrentAmount() {
        return portfolio.getWalletCurrentAmount();
    }

    public double getWalletInitialAmount() {
        return portfolio.getWalletInitialAmount();
    }

    public ArrayList<Stock> getAllStocks() {
        return portfolio.getAllStocks();
    }

}
