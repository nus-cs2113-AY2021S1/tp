public class Dish {

    private String dishName;
    private double price;
    private String comment;

    Dish(String dishName, double price, String comment) {
        this.dishName = dishName;
        this.price = price;
        this.comment = comment;
    }

    public String getDishName() {
        return this.dishName;
    }

    public double getPrice() {
        return this.price;
    }

    public String getComment(){
        return this.comment;
    }

    @Override
    public String toString() {
        return this.dishName + "price: " + this.price;
    }
}
