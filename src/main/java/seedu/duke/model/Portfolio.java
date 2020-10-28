package seedu.duke.model;

import seedu.duke.exception.DoNotOwnStockException;
import seedu.duke.exception.InsufficientFundException;
import seedu.duke.exception.InsufficientQtyException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio implements Serializable {
    HashMap<String, Stock> stocks;
    Wallet wallet;

    public Portfolio() {
        stocks = new HashMap<>();
        wallet = new Wallet(100000);
        assert getWalletInitialAmount() <= 100000;
    }

    public void buyStock(String symbol, int quantity, double buyPrice) throws InsufficientFundException {
        assert buyPrice > 0 : "buyPrice should be more than 0";

        if (wallet.getCurrentAmount() < buyPrice * quantity) {
            throw new InsufficientFundException();
        }

        wallet.buyStock(quantity, buyPrice);
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

    public void sellStock(String symbol, int quantity, double sellPrice)
            throws InsufficientQtyException, DoNotOwnStockException {
        if (stocks.get(symbol) == null) {
            throw new DoNotOwnStockException();
        } else if (stocks.get(symbol).getTotalQuantity() < quantity) {
            throw new InsufficientQtyException(stocks.get(symbol).getTotalQuantity());
        }

        assert sellPrice > 0 : "sellPrice should be more than 0";

        wallet.sellStock(quantity, sellPrice);
        Transaction transaction = new Transaction(TransactionType.SELL, quantity, sellPrice, LocalDateTime.now());
        Stock stock = stocks.get(symbol);
        stock.addTransaction(transaction);

        if (stock.getTotalQuantity() == 0) {
            stocks.remove(symbol);
        }
    }

    public double getWalletCurrentAmount() {
        return wallet.getCurrentAmount();
    }

    public double getWalletInitialAmount() {
        return wallet.getInitialAmount();
    }

    public ArrayList<Stock> getAllStocks() {
        ArrayList<Stock> stocksArrayList = new ArrayList<>();
        for (Stock stock : stocks.values()) {
            stocksArrayList.add(stock);
        }

        return stocksArrayList;
    }

}
