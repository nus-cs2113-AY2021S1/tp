import java.util.List;

/**
 *
 * a class represents an order
 */
public class Order {

    private Canteen canteen;
    private Stall stall;
    private List<Dish> dishes;
    private Customer customer;

    /**
     * Constructor of the Order class
     * @param canteen canteen name of the order
     * @param stall stall name of the order
     * @param dishes dishes that the customer ordered
     * @param customer customer name who did the order
     */
    Order(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer) {
        this.canteen = canteen;
        this.stall = stall;
        this.dishes = dishes;
        this.customer = customer;
    }

    /**
     * Get the canteen of the order
     * @return the canteen
     */
    public Canteen getCanteen() {
        return this.canteen;
    }

    /**
     * Get the stall of the order
     * @return the stall
     */
    public Stall getStall() {
        return this.stall;
    }

    /**
     * Get the list of dishes that the customer order
     * @return the list of dishes
     */
    public List<Dish> getDish() {
        return this.dishes;
    }

    /**
     * Format the meaningful string for user to see
     * @return the formatted string
     */
    public String toString() {
        String dishString = "1. " + dishes.get(0).getDishName() + "\n";
        for(int i=1; i<dishes.size();i++){
            dishString = dishString  + "i+1. " + dishes.get(i).getDishName() + "\n";
        }
        return this.canteen + " order in" + this.stall + ": \n" + dishString ;
    }
}
