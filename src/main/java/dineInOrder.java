public class dineInOrder extends Order {

    private boolean isDineIn;

    dineInOrder(Canteen canteen, Stall stall, Dish dish, boolean isDineIn) {
        super(canteen, stall, dish);
        this.isDineIn = isDineIn;
    }

    public boolean getIsDineIn() {
        return this.isDineIn;
    }

}
