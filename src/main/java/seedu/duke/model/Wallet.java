package seedu.duke.model;

import java.io.Serializable;

public class Wallet implements Serializable {
    private double currentAmount;
    private double initialAmount;

    public Wallet(double initialAmount) {
        this.initialAmount = initialAmount;
        currentAmount = initialAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void sellStock(int quantity, double price) {
        currentAmount = currentAmount + price * quantity;
    }

    public void buyStock(int quantity, double price) {
        currentAmount = currentAmount - (price * quantity);
    }
}
