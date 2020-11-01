import java.util.List;

public class takeAwayOrder extends Order {

    private boolean isTakeAway;
    private final static double carrierFee = 0.3;

    takeAwayOrder(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer, boolean isTakeAway) {
        super(canteen, stall, dishes, customer);
        this.isTakeAway = isTakeAway;
    }

    public boolean getIsTakeAway() {
        return this.isTakeAway;
    }

    public double getCarrierFee() {
        return this.carrierFee;
    }

    @Override
    public String toString() {
        if(isTakeAway) {
            return super.toString() + "(Take Away)";
        }
        else {
            return super.toString() + "(Not Take Away)";
        }
    }
}
