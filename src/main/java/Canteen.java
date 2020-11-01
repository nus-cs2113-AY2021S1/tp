import java.util.ArrayList;
import java.util.List;

public class Canteen {
    private String canteenName;
    public List<Stall> stallList;
    private List<Integer> openTime;
    private List<Integer> closeTime;

    Canteen(String canteenName, List<Stall> stallList, List<Integer> openTime,List<Integer> closeTime){
        this.canteenName = canteenName;
        this.stallList = stallList;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
    public int getOpenTime(int dayOfWeek){
        return openTime.get(dayOfWeek-1);
    }
    int getCloseTime(int dayOfWeek) {
        return closeTime.get(dayOfWeek-1);
    }

    public List<Stall> getStallList() {
        return stallList;
    }

//    List<Stall> checkOpenStall(int dayOfWeek, int time){

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
