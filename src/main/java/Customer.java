import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * a class represent a customer
 */
public class Customer {

    public String name;
    public int ID;
    public int arriveTime;

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    /**
     * a method to get which day of week the customer comes
     * @return int
     */
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int dayOfWeek;

    /**
     * constructor
     * @param name
     * @param ID
     * @param arriveTime
     * @param dayOfWeek
     */
    public Customer(String name, int ID,  int arriveTime, int dayOfWeek) {
        this.name = name;
        this.ID = ID;
        this.arriveTime = arriveTime;
        this.dayOfWeek = dayOfWeek;
    }

//    public void printStalls(){
//        openStalls = this.canteen.checkOpenStall(this.dayOfWeek,this.arriveTime);
//        for(int i=0;i<openStalls.size();i++){
//            System.out.println(openStalls.get(i));
//        }
//    }
//    public void printDishes(String canteenName){
//        openStalls = this.canteen.checkOpenStall(this.dayOfWeek,this.arriveTime);
//        for(int i=0;i<openStalls.size();i++){
//            if(canteenName.compareTo(openStalls.get(i).stall_name)==0){
//                openStalls.get(i).printDishes();
//            }
//        }
//    }
//    public List<Stall> CheckOpenStalls(){
//        openStalls = this.canteen.checkOpenStall(this.dayOfWeek,this.arriveTime);
//        return openStalls;
//    }

    /**
     * a method to check open stalls based on
     * a given choosen canteen
     * return a list of stalls
     * @param canteen
     * @return List<Stall>
     */
    public List<Stall> checkOpenStalls(Canteen canteen){
        List<Stall> openStallList = new ArrayList<>();
        for(Stall stall:canteen.stallList){
            if(stall.isOpen(this.dayOfWeek, this.arriveTime)){
                openStallList.add(stall);
            }
        }
        return openStallList;
    }

    /**
     * a method to check open stalls based on
     *    a given list of canteens
     *    return a list of open canteens
     * @param ListCanteen
     * @return
     */
    public List<Canteen> checkOpenCanteens(List<Canteen> ListCanteen){
        List<Canteen> openCanteens=new ArrayList<>();
        for(Canteen canteen:ListCanteen){
            if(canteen.isOpen(this.dayOfWeek, this.arriveTime)){
                openCanteens.add(canteen);
            }
        }
        return openCanteens;
    }

    /**
     * a method to check dishes
     * @param ListDish
     * @return
     */
    public List<Dish> checkDish(List<Dish> ListDish){
        List<Dish> dishList = new ArrayList<>();
        for(Dish dish: ListDish){
            dishList.add(dish);
        }
        return dishList;
    }

    /**
     * a method to check waiting time
     * @param stall
     * @return
     */
    public int checkWaitingTime(Stall stall){
        return stall.queue * stall.servingTimePerPersom;
    }

    /**
     * a method to make the order according to the type of order
     * @param canteen
     * @param stall
     * @param dish
     * @param typeOfOrder
     * @return
     */
    public Order order(Canteen canteen,Stall stall,List<Dish> dish, String typeOfOrder) {

        if(typeOfOrder.compareTo("Delivery")==0){
            return new deliveryOrder(canteen,stall,dish,this,true);
        }
        if(typeOfOrder.compareTo("Dine in")==0){
            return new dineInOrder(canteen,stall,dish,this,true);
        }
        if(typeOfOrder.compareTo("Take away")==0){
            return new takeAwayOrder(canteen,stall,dish,this,true);
        }
        return  null;
    }

    @Override
    public String toString(){
        return this.name + " arrives at " +this.arriveTime ;
    }
}


