public class takeAwayOrder extends Order {

    private boolean isTakeAway;
    private double carrierFee;

    takeAwayOrder(Canteen canteen, Stall stall, Dish dish, boolean isTakeAway, double carrierFee) {
        super(canteen, stall, dish);
        this.isTakeAway = isTakeAway;
        this.carrierFee = carrierFee;
    }

    public boolean getIsTakeAway() {
        return this.isTakeAway;
    }

    public double getCarrierFee() {
        return this.carrierFee;
    }
}
