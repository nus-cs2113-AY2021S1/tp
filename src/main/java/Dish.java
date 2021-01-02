import java.util.List;

/**
 * a class represents dish
 */
public class Dish {

    private String dishName;
    private double price;
    private String stallName;
    private String canteenName;
    private String type;
    private String comment;
    private double openTime;
    private double closeTime;
    private List<Integer> openDayOfWeek;
    private int stallID;
    private String stallLocation;
    private int servingTime;

//    /**
//     * constructor
//     * @param dishName
//     * @param price
//     * @param comment
//     */
//    Dish(String dishName, double price, String comment) {
//        this.dishName = dishName;
//        this.price = price;
//        this.comment = comment;
//    }


    public Dish(String dishName, double price, String stallName, String canteenName, String type, String comment, double openTime, double closeTime, List<Integer> openDayOfWeek, int stallID, String stallLocation, int servingTime) {
        this.dishName = dishName;
        this.price = price;
        this.stallName = stallName;
        this.canteenName = canteenName;
        this.type = type;
        this.comment = comment;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openDayOfWeek = openDayOfWeek;
        this.stallID = stallID;
        this.stallLocation = stallLocation;
        this.servingTime = servingTime;
    }

    public int getStallID() {
        return stallID;
    }

    public String getStallLocation() {
        return stallLocation;
    }

    public int getServingTime() {
        return servingTime;
    }

    public String getStallName() {
        return stallName;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public String getType() {
        return type;
    }

    public double getOpenTime() {
        return openTime;
    }

    public double getCloseTime() {
        return closeTime;
    }

    public List<Integer> getOpenDayOfWeek() {
        return openDayOfWeek;
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
