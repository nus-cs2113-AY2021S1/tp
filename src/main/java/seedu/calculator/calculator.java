package seedu.calculator;

import java.util.ArrayList;
//import seedu.foodList;

/**
 * Represents a calculator of food items in foodList.
 */
public class calculator {
    private int totalCalorie = 0;
    private int totalCarbohydrate = 0;
    private int totalProtein = 0;
    private int totalFat = 0;

    /**
     * Construct a calculator taking in a foodList. Add up calories,
     * carbs, protein, and fats in each food item.
     *
     * @param foodList foodList containing food items to calculate.
     */
    public calculator(ArrayList<Food> foodList){
        try {
            for(int i=0; i< foodList.size(); i++){
                totalCalorie += foodList.get(i).getCalorie();
                totalCarbohydrate += foodList.get(i).getCarbohydrate();
                totalProtein += foodList.get(i).getProtein();
                totalFat += foodList.get(i).getFats();
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
        return totalCalorie;
    }

    /**
     * Returns an int type variable containing the value of total carbs.
     *
     * @return the value of total carbs of food items in foodList.
     */
    public int calculateCarb(){
        return totalCarbohydrate;
    }

    /**
     * Returns an int type variable containing the value of total protein.
     *
     * @return the value of total protein of food items in foodList.
     */
    public int calculateProtein(){
        return totalProtein;
    }

    /**
     * Returns an int type variable containing the value of total fats.
     *
     * @return the value of total fats of food items in foodList.
     */
    public int calculateFat(){
        return totalFat;
    }
}
