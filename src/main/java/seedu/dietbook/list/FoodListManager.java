package seedu.dietbook.list;

import seedu.dietbook.food.Food;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Consumer;

/**
 * Class with static methods to execute "complex commands" on FoodList.
 * This class handles methods that extend beyond the simple function of an arraylist
 * Class contains static methods with logic beyond adding, removing, and instantiating new lists
 * This class may be used to support functional programming by merging these function into functors
 */
public class FoodListManager {
    
    /**
     * Internal helper method to convert the items in the arraylist into enumed strings.
     * Primarily used to obtain String representations of the entire list.
     */
    protected static String listToString(ArrayList<FoodEntry> list) {
        String listString = "";

        for (int i = 1; i <= list.size(); i++) {
            FoodEntry entry = list.get(i - 1);
            listString += "  " + i + ". "
                    + entry.toString() + "\n";
        }
        return listString;
    }

    protected static FoodEntry deleteEntry(List<FoodEntry> list, int index) throws IndexOutOfBoundsException {
        assert (index > 0) : "Invalid index (negative/zero) was given.";
        int indexToDelete = index - 1;
        try {
            return list.remove(indexToDelete);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    /** 
     * Method to obtain list of foodentries in string rep.
     * @param list The foodList arrayList
     * @return List of foodEntries in their String rep.
     */
    protected static List<String> listToStrings(List<FoodEntry> list) {
        Function<FoodEntry, String> function = x -> x.toString();
        return applyFunctionToList(list, function);
    }

    /**
     * Extracts the list of foods from the foodentries list.
     * @param list list of foodEntries
     * @return arraylist of Food objects.
     */
    protected static ArrayList<Food> listToFoods(List<FoodEntry> list) {
        Function<FoodEntry, Food> function = x -> x.getFood();
        return applyFunctionToList(list, function);
    }

    /**
     * Creates a list of foods that have their nutritional values scaled by portion size.
     * This is based on the FoodEntries in the list provided.
     * @param list list of FoodEntries
     * @return arraylist of Food objects
     */
    protected static ArrayList<Food> listToPortionedFoods(List<FoodEntry> list) {
        Function<FoodEntry, Food> function = x -> {
            Food baseFood = x.getFood();
            /**  Explicitly getting return type of getPortionSize() is avoided.
             * Future updates might see the type change from int to float
             * return of getPortionSize() essentially treated as a "multipliable"
            */
            return new Food(baseFood.getName(), 
                    baseFood.getCalorie() * x.getPortionSize(),
                    baseFood.getCarbohydrate() * x.getPortionSize(),
                    baseFood.getProtein() * x.getPortionSize(),
                    baseFood.getFat() * x.getPortionSize());
        };
        return applyFunctionToList(list, function);
    }

    /**
     * Generic method to map a function across a list.
     * @param list list to operate on
     * @param function function to be mapped across list
     * @return list of mapped items under provided function
     */
    protected static <T, E> ArrayList<E> applyFunctionToList(List<T> list, Function<T, E> function) {
        ArrayList<E> appliedList = new ArrayList<>();
        Consumer<T> addResultToAppliedList = x -> appliedList.add(function.apply(x));
        list.forEach(addResultToAppliedList);
        return appliedList;
    }

}

// Potential future work: create a functional interface for the functions instead:
