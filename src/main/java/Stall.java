
import java.util.List;

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

    public int getOpenTime() {
        return openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

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
    public List<Dish> getDish(){
        return this.dishes;
    }
    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getQueue() {
        return queue;
    }

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
