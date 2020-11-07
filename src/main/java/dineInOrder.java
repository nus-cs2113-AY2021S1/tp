import java.util.List;

public class dineInOrder extends Order {

    private boolean isDineIn;

    /**
     * Constructor of the dine in order
     * @param canteen canteen name of the order
     * @param stall stall name of the order
     * @param dishes dishes that the customer ordered
     * @param customer customer name who did the order
     * @param isDineIn order type
     */
    dineInOrder(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer, boolean isDineIn) {
        super(canteen, stall, dishes, customer);
        this.isDineIn = isDineIn;
    }

    /**
     * Get the order type
     * @return whether the order is dine in type or not
     */
    public boolean getIsDineIn() {
        return this.isDineIn;
    }

    /**
     * Format the meaningful string for user to see
     * @return the formatted string
     */
    @Override
    public String toString() {
        if (this.isDineIn) {
            return super.toString() + "(Dine In)";
        }
        else {
            return super.toString() + "(Not Dine In)";
        }
    }
}
