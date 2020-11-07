
import java.util.List;

/**
 * a class represents a stall in a canteen
 *
 */
public class Stall {
    public String stall_name;
    private int stallID;
    private String stallLocation;
    private int openTime;
    private int closeTime;
    private List<Integer> openDayOfWeek;
    private List<Dish>  dishes;
    public int queue;
    public int servingTimePerPersom;
    /**
     * a method to get the open time of this stall
     * @return int
     */
    public int getOpenTime() {
        return openTime;
    }

    /**
     * a method to get the close time of this stall
     * @return int
     */
    public int getCloseTime() {
        return closeTime;
    }

    /**
     * constructor for Stall class
     * @param stall_name
     * @param stallID
     * @param stallLocation
     * @param openTime
     * @param closeTime
     * @param openDayOfWeek
     * @param dishes
     * @param servingTimePerPersom
     */
    public Stall(String stall_name, int stallID, String stallLocation, int openTime, int closeTime, List<Integer> openDayOfWeek, List<Dish> dishes, int servingTimePerPersom) {
        this.stall_name = stall_name;
        this.stallID = stallID;
        this.stallLocation = stallLocation;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openDayOfWeek = openDayOfWeek;
        this.dishes = dishes;
        this.servingTimePerPersom = servingTimePerPersom;
        this.queue = 0;
    }

    /**
     * a method to return all the dishes in this stall
     * in a list
     * @return List<Dish>
     */
    public List<Dish> getDish(){
        return this.dishes;
    }
    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getQueue() {
        return queue;
    }

    /**
     * a method to check if this stall is open based on
     * given dayOfWeek and time
     *
     * @param dayOfWeek
     * @param time
     * @return boolean
     */
    boolean isOpen (int dayOfWeek, int time) {
        for(Integer day:this.openDayOfWeek){
            if(day == dayOfWeek){
                if(time>=this.openTime&&time<=this.closeTime){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * a method to print all the dishes in this stall
     */
    public void printDishes(){
        for(int i=0;i<dishes.size();i++){
            System.out.println(dishes.get(i));
        }
    }

    @Override
    public String toString() {
        return this.stall_name;
    }
}
