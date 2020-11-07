package seedu.dietbook.list;

import seedu.dietbook.food.Food;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Child class of Food that supports "empty" fields.
 * Is also handled as a data class with methods to query the existence of empty fields.
 */
public class OptionalFood extends Food {
    private final Map<Nutrient, String> nutrientStrings;
    public static final int EMPTY_VALUE = -1;

    /**
     * Only default constructor method. It is the same as the constructor method for Food.
     */
    public OptionalFood(String name, int calorie, int carbohydrate, int protein, int fat) {
        super(name, calorie, carbohydrate, protein, fat);

        nutrientStrings = new HashMap<Nutrient, String>();

        assert (calorie >= 0 || calorie == -1) : "Invalid value for calorie detected";
        addNutrientStringToMap(nutrientStrings, Nutrient.calorie, calorie);

        assert (carbohydrate >= 0 || carbohydrate == -1) : "Invalid value for carbohydrate detected";
        addNutrientStringToMap(nutrientStrings, Nutrient.carbohydrate, carbohydrate);

        assert (protein >= 0 || protein == -1) : "Invalid value for protein detected";
        addNutrientStringToMap(nutrientStrings, Nutrient.protein, protein);

        assert (fat >= 0 || fat == -1) : "Invalid value for fat detected";
        addNutrientStringToMap(nutrientStrings, Nutrient.fat, fat);

    }

    /**
     * Checks if nutrient value is empty and assigns its corresponding string representation to the map.
     * Either "-" or the integer value.
     */
    private void addNutrientStringToMap(Map<Nutrient, String> map, Nutrient nutrient, int nutrientValue) {
        if (nutrientValue == EMPTY_VALUE) {
            map.put(nutrient, "-");
        } else {
            map.put(nutrient, String.valueOf(nutrientValue));
        }
    }

    @Override
    public String toString() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(super.getName());
        
        Map<String, String> stringMap = new HashMap<>();
        nutrientStrings.forEach((x, y) -> stringMap.put(x.toString(), y));
        String text = "calorie : ${calorie}, protein : ${protein}, carbohydrate : ${carbohydrate}, fats : ${fats}";
        
        try {
            Arrays.asList(StringFormatter.formatStringWithMap(text, stringMap)
                    .split(","))
                    .forEach(x -> strings.add(x));
        } catch (NoReplacementFoundException e) { // should not be allowed to happen
            assert (false) : "Error with String formatting: " + e.getMessage();
            nutrientStrings.forEach((x, y) -> strings.add(String.format("%s : %s", x.toString(), y)));
        }
        
        return String.join(" | ", strings);
    }

    private enum Nutrient {
        calorie,
        carbohydrate,
        protein,
        fat {
            @Override
            public String toString() {
                return "fats";
            }
        };
    }
    
}