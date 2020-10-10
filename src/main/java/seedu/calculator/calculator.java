package seedu.calculator;

import java.util.ArrayList;
import seedu.foodList;

/**
 * Represents a calculator of food items in foodList.
 */
public class calculator {
    private static Food total = new Food (total, 1, 0, 0, 0, 0);

    /**
     * Construct a calculator taking in a foodList. Add up calories,
     * carbs, protein, and fats in each food item.
     *
     * @param foodList foodList containing food items to calculate.
     */
    public calculator(ArrayList<Food> foodList){
        try {
            for(int i=0; i< foodList.size(); i++){
                total.calorie += foodList.getPortionSize(i)*foodList.getCalorie(i);
                total.carbs += foodList.getPortionSize(i)*foodList.getCarbs(i);
                total.protein += foodList.getPortionSize(i)*foodList.getProtein(i);
                total.fats += foodList.getPortionSize(i)*foodList.getFats(i);
            }
        } catch (NullPointerException e) {
            System.out.println("Ops, This foodList is null!");
        }
    }

    /**
     * Returns an int type variable containing the value of total calorie.
     *
     * @return the value of total calorie of food items in foodList.
     */
    public int calculateCalorie(){
        return total.calorie;
    }

    /**
     * Returns an int type variable containing the value of total carbs.
     *
     * @return the value of total carbs of food items in foodList.
     */
    public int calculateCarbs(){
        return total.carbs;
    }

    /**
     * Returns an int type variable containing the value of total protein.
     *
     * @return the value of total protein of food items in foodList.
     */
    public int calculateProtein(){
        return total.protein;
    }

    /**
     * Returns an int type variable containing the value of total fats.
     *
     * @return the value of total fats of food items in foodList.
     */
    public int calculateFats(){
        return total.fats;
    }
}