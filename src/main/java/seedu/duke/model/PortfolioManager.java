package seedu.duke.model;


import seedu.duke.data.exception.DukeException;

import java.util.ArrayList;

public class PortfolioManager {
    private Portfolio portfolio;

    public PortfolioManager() {
        // load portfolio object from file
        // if does not exists, create the file
        portfolio = new Portfolio();
    }

    public void buyStock(String symbol, int quantity, double buyPrice) {
        portfolio.buyStock(symbol, quantity, buyPrice);
    }

    public void sellStock(String symbol, int quantity, double sellPrice) throws DukeException {
        portfolio.sellStock(symbol, quantity, sellPrice);
    }

    public ArrayList<Stock> getAllStocks() {
        return portfolio.getAllStocks();
    }

}
