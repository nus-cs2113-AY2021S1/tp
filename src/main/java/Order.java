import java.util.List;

public class Order {

    private Canteen canteen;
    private Stall stall;
    private List<Dish> dishes;
    private Customer customer;

    Order(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer) {
        this.canteen = canteen;
        this.stall = stall;
        this.dishes = dishes;
        this.customer = customer;
    }

    public Canteen getCanteen() {
        return this.canteen;
    }

    public Stall getStall() {
        return this.stall;
    }

    public List<Dish> getDish() {
        return this.dishes;
    }

    public String toString() {
        String dishString = "1. " + dishes.get(0).getDishName() + "\n";
        for(int i=1; i<dishes.size();i++){
            dishString = dishString  + "i+1. " + dishes.get(i).getDishName() + "\n";
        }
        return this.canteen + " order in" + this.stall + ": \n" + dishString ;
    }
}
