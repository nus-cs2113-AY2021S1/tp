package seedu.duke.model;

import seedu.duke.data.exception.DukeException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio implements Serializable {
    HashMap<String, Stock> stocks;

    public Portfolio() {
        stocks = new HashMap<>();
    }

    public void buyStock(String symbol, int quantity, double buyPrice) {
        Transaction transaction = new Transaction(TransactionType.BUY, quantity, buyPrice, LocalDateTime.now());
        if (stocks.get(symbol) == null) {
            Stock stock = new Stock(symbol);
            stock.addTransaction(transaction);
            stocks.put(symbol, stock);
        } else {
            Stock stock = stocks.get(symbol);
            stock.addTransaction(transaction);
        }
    }

    public void sellStock(String symbol, int quantity, double sellPrice) throws DukeException {
        if (stocks.get(symbol) == null) {
            throw new DukeException("You do not own this stock!");
        } else if (stocks.get(symbol).getTotalQuantity() < quantity) {
            throw new DukeException("You only own " + stocks.get(symbol).getTotalQuantity() + " of this stock!");
        }

        Transaction transaction = new Transaction(TransactionType.SELL, quantity, sellPrice, LocalDateTime.now());
        Stock stock = stocks.get(symbol);
        stock.addTransaction(transaction);
    }

    public ArrayList<Stock> getAllStocks() {
        ArrayList<Stock> stocksArrayList = new ArrayList<>();
        for (Stock stock: stocks.values()) {
            stocksArrayList.add(stock);
        }

        return stocksArrayList;
    }

}
