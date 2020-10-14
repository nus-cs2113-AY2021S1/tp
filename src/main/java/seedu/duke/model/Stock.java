package seedu.duke.model;

import seedu.duke.api.StockPriceFetcher;
import seedu.duke.data.exception.DukeException;
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
        switch (transaction.getTransactionType()) {
        case BUY:
            totalQuantity += transaction.getQuantity();
            break;
        case SELL:
            totalQuantity -= transaction.getQuantity();
            break;
        default:
            break;
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

    public double getLatestPrice(){
        StockPriceFetcher stockPriceFetcher = new StockPriceFetcher();
        Ui ui = new Ui();

        try{
            double price = stockPriceFetcher.fetchLatestPrice(getSymbol());
            return price;
        }catch (DukeException e) {
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
