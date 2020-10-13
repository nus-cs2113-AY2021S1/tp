package seedu.duke.model;

import java.time.LocalDateTime;

public class Stock {
    LocalDateTime purchasedDateTime;
    double purchasedPrice;

    public Stock(double purchasedPrice, LocalDateTime purchasedDateTime) {
        this.purchasedPrice = purchasedPrice;
        this.purchasedDateTime = purchasedDateTime;
    }
}
