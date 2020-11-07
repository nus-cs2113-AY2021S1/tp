/**
 * a class represents dish
 */
public class Dish {

    private String dishName;
    private double price;
    private String comment;

    /**
     * constructor
     * @param dishName
     * @param price
     * @param comment
     */
    Dish(String dishName, double price, String comment) {
        this.dishName = dishName;
        this.price = price;
        this.comment = comment;
    }

    /**
     * return the name of this dish
     * @return String
     */
    public String getDishName() {
        return this.dishName;
    }
    /**
     * return the price of this dish
     * @return double
     */
    public double getPrice() {
        return this.price;
    }
    /**
     * return the comment of this dish
     * @return String
     */
    public String getComment(){
        return this.comment;
    }

    @Override
    public String toString() {
        return this.dishName + " price: " + this.price;
    }
}
