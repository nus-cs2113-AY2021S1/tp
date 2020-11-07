import java.util.List;

public class deliveryOrder extends Order {

    private final static double carrierFee = 0.3;
    private final static double deliveryFee = 2.0;
    private boolean isDeliveryOrder;

    /**
     * Constructor of the delivery order
     * @param canteen canteen name of the order
     * @param stall stall name of the order
     * @param dishes dishes that the customer ordered
     * @param customer customer name who did the order
     * @param isDeliveryOrder order type
     */
    deliveryOrder(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer, boolean isDeliveryOrder) {
        super(canteen, stall, dishes, customer);
        this.isDeliveryOrder = isDeliveryOrder;
    }

    /**
     * Get the order type
     * @return whether the order is delivery type or not
     */
    public boolean getIsDeliveryOrder() {
        return this.isDeliveryOrder;
    }

    /**
     * Get the carrier fee of the delivery order
     * @return the carrier fee
     */
    public double getCarrierFee() {
        return this.carrierFee;
    }

    /**
     * Get the delivery fee of the delivery order
     * @return the delivery fee
     */
    public double getDeliveryFee() {
        return this.deliveryFee;
    }

    /**
     * Format the meaningful string for user to see
     * @return the formatted string
     */
    @Override
    public String toString() {
        if (isDeliveryOrder) {
            return super.toString();
        }
        else {
            return super.toString() + "(Not Delivery Order)";
        }
    }
}