package seedu.duke.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private TransactionType transactionType;
    private int quantity;
    private double unitPrice;
    LocalDateTime dateTimeOfTransaction;

    public Transaction(TransactionType transType, int quantity, double unitPrice, LocalDateTime dateTime) {
        this.transactionType = transType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.dateTimeOfTransaction = dateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public String toString() {
        return transactionType + " " + quantity + " at " + unitPrice + " on " + dateTimeOfTransaction;
    }
}
