package seedu.duke.model;

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

    @Override
    public String toString() {
        return "Symbol: " + getSymbol() + ", total quantity: " + getTotalQuantity();
    }
}
