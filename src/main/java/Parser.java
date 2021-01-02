import exception.ArriveTimeException;
import exception.DayOfWeekException;

import java.util.ArrayList;
import java.util.List;

/**
 * class Parser
 * to parse user's input
 */
public class Parser {//name week time

    /**
     * a method to parseCustomer
     * when they input their infomation
     * @param inputMessage
     * @return
     * @throws DayOfWeekException
     * @throws ArriveTimeException
     */
    public static Customer parseCustomer(String inputMessage) throws DayOfWeekException, ArriveTimeException {

        String[] inputWords = inputMessage.split("/"); //split the input message
        String customerName = inputWords[0];
        int dayOfWeek = Integer.parseInt(inputWords[1])-1;
        if(dayOfWeek>=7||dayOfWeek<0){
            throw new DayOfWeekException();
        }
        int arriveTime = Integer.parseInt(inputWords[2]);
        if(arriveTime<0||arriveTime>=2400){
            throw new ArriveTimeException();
        }
        return new Customer(customerName,1,arriveTime,dayOfWeek);
    }
    public static List<Integer> parseList(String inputMessage){
        String s = inputMessage.substring(1,inputMessage.indexOf("]"));
        String sList[] = s.split(", ");
        ArrayList<Integer> nList = new ArrayList<>();
        for(String ss:sList){
            nList.add(Integer.parseInt(ss));
        }
        return nList;
    }
    public static List<Dish> parseDish(String inputMessage, List<Canteen> canteens){
        String sList[] = inputMessage.split(",");
        ArrayList<Dish> dishList = new ArrayList<>();
        for(String s : sList){
            for(Canteen canteen: canteens){
                for(Stall stall:canteen.getStallList()){
                    for(Dish dish:stall.getDish()){
                        if(dish.getDishName().equals(s)){
                            dishList.add(dish);
                        }
                    }
                }
            }
        }
        return dishList;
    }
}
