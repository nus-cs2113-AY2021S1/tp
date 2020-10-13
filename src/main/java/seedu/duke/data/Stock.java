package seedu.duke.data;


import java.util.Date;

public class Stock {
    private String symbol;
    private double open;
    private double high;
    private double low;
    private double price;
    private double volume;
    private Date latestTradingDay;

    public Stock() {
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    public Date getLatestTradingDay() {
        return latestTradingDay;
    }

    // Setters

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setLatestTradingDay(Date latestTradingDay) {
        this.latestTradingDay = latestTradingDay;
    }
}
