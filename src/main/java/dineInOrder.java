import java.util.List;

public class dineInOrder extends Order {

    private boolean isDineIn;

    dineInOrder(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer, boolean isDineIn) {
        super(canteen, stall, dishes, customer);
        this.isDineIn = isDineIn;
    }

    public boolean getIsDineIn() {
        return this.isDineIn;
    }

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
