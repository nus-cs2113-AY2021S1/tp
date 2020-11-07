import java.util.ArrayList;
import java.util.List;

/**
 *
 * a class represents a canteen
 */
public class Canteen {
    private String canteenName;
    public List<Stall> stallList;
    private List<Integer> openTime;
    private List<Integer> closeTime;

    /**
     * constructor
     * @param canteenName
     * @param stallList
     * @param openTime
     * @param closeTime
     */
    Canteen(String canteenName, List<Stall> stallList, List<Integer> openTime,List<Integer> closeTime){
        this.canteenName = canteenName;
        this.stallList = stallList;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    /**
     * a method to get the open time of the canteen
     * @param dayOfWeek
     * @return int
     */
    public int getOpenTime(int dayOfWeek){
        return openTime.get(dayOfWeek-1);
    }
    /**
     * a method to get the close time of the canteen
     * @param dayOfWeek
     * @return int
     */
    int getCloseTime(int dayOfWeek) {
        return closeTime.get(dayOfWeek-1);
    }

    /**
     *
     * a method to get all the stalls
     * in a list
     * @return List<Stall>
     */
    public List<Stall> getStallList() {
        return stallList;
    }

//    List<Stall> checkOpenStall(int dayOfWeek, int time){
    /**
     * a method to check if this canteen is open based on
     * given dayOfWeek and time
     *
     * @param dayOfWeek
     * @param time
     * @return boolean
     */
    boolean isOpen (int dayOfWeek, int time) {
                if(time>=this.openTime.get(dayOfWeek)&&time<=this.closeTime.get(dayOfWeek)){
                    return true;
                }
                else{return false;}
    }



    public String getCanteenName() {
        return canteenName;
    }

    @Override
    public String toString() {
        return  canteenName;
    }
}
