package seedu.duke.list;

import seedu.duke.food.Food;
import java.util.ArrayList;

/**
 * Class with static methods to execute "complex commands" on FoodList.
 * This class handles methods that extend beyond the simple function of an arraylist
 * Class contains static methods with logic beyond adding, removing, and instantiating new lists
 * This class may be used to support functional programming by merging these function into functors
 */
public class FoodListManager {
    
    /**
     * Internal helper method to convert the items in the arraylist into enumed strings.
     * Primarily used to obtain String representations of the list.
     */
    protected static String listToString(ArrayList<FoodEntry> list) {
        String listString = "";

        for (int i = 1; i <= list.size(); i++) {
            FoodEntry entry = list.get(i - 1);
            listString += i + ". "
                    + entry.toString() + "\n";
        }
        return listString;
    }

    /**
     * Similar to listToString.
     * Extracts only the Food component from FoodEntries.
     */
    protected static ArrayList<Food> listToFoods(ArrayList<FoodEntry> list) {
        ArrayList<Food> foods = new ArrayList<>();
        list.forEach(x -> {
            foods.add(x.getFood());
        });
        return foods;
    }

    protected static Food deleteEntry(ArrayList<FoodEntry> list, int index) throws IndexOutOfBoundsException {
        int indexToDelete = index - 1;
        try {
            return list.remove(indexToDelete).getFood();
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }
}
