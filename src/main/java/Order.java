public class Order {

    private Canteen canteen;
    private Stall stall;
    private Dish dish;

    Order(Canteen canteen, Stall stall, Dish dish) {
        this.canteen = canteen;
        this.stall = stall;
        this.dish = dish;
    }

    public Canteen getCanteen() {
        return this.canteen;
    }

    public Stall getStall() {
        return this.stall;
    }

    public Dish getDish() {
        return this.dish;
    }

}
