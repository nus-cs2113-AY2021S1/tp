public class Dish {

    private String dishName;
    private double price;

    Dish(String dishName, double price) {
        this.dishName = dishName;
        this.price = price;
    }

    public String getDishName() {
        return this.dishName;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return this.dishName + "price: " + this.price;
    }
}
