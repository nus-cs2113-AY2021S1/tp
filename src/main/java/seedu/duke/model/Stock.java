package seedu.duke.model;

import seedu.duke.api.StockPriceFetcher;
import seedu.duke.exception.PaperTradeException;
import seedu.duke.ui.Ui;

import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable {
    private String symbol;
    private int totalQuantity;
    private ArrayList<Transaction> transactions;

    public Stock(String symbol) {
        this.symbol = symbol;
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        assert transactions != null : "ArrayList of transactions not initialised!";

        switch (transaction.getTransactionType()) {
        case BUY:
            totalQuantity += transaction.getQuantity();
            break;
        case SELL:
            totalQuantity -= transaction.getQuantity();
            break;
        default:
            assert false : "How did you end up here?!";
        }

        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getLatestPrice() {
        StockPriceFetcher stockPriceFetcher = new StockPriceFetcher();
        Ui ui = new Ui();
        try {
            double price = stockPriceFetcher.fetchLatestPrice(getSymbol());
            return price;
        } catch (PaperTradeException e) {
            ui.print(e.getMessage());
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Symbol: " + getSymbol() + ", total quantity: " + getTotalQuantity() + ", Current Price: "
                + getLatestPrice();
    }
}
