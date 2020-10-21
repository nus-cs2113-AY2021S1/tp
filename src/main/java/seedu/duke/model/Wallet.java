package seedu.duke.model;

public class Wallet {
    private double amount;

    public Wallet() {
        amount = 10000.00;
    }

    public double getAmount() {
        return this.amount;
    }

    public void sellStock(int quantity, double price) {
        this.amount = this.amount + price * quantity;
    }

    public void buyStock(int quantity, double price) {
        this.amount = this.amount - (price * quantity);
    }
}
