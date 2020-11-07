import java.util.List;

public class takeAwayOrder extends Order {

    private boolean isTakeAway;
    private final static double carrierFee = 0.3;

    /**
     * Constructor of the delivery order
     * @param canteen canteen name of the order
     * @param stall stall name of the order
     * @param dishes dishes that the customer ordered
     * @param customer customer name who did the order
     * @param isTakeAway order type
     */
    takeAwayOrder(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer, boolean isTakeAway) {
        super(canteen, stall, dishes, customer);
        this.isTakeAway = isTakeAway;
    }

    /**
     * Get the order type
     * @return whether the order is take away type or not
     */
    public boolean getIsTakeAway() {
        return this.isTakeAway;
    }

    /**
     * Get the carrier fee of the delivery order
     * @return the carrier fee
     */
    public double getCarrierFee() {
        return this.carrierFee;
    }

    /**
     * Format the meaningful string for user to see
     * @return the formatted string
     */
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
