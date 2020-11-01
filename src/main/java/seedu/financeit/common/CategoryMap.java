package seedu.financeit.common;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Bi-directional map to store transaction category and its corresponding user input shortcut.
 */
public class CategoryMap {
    /**
     *  Used to parse user category input, ensures that the range of corresponding Categories to given input is
     *  confined to that in the CategoryMap.
     */
    public static String CAT_TRANSPORT = "TRANSPORT";
    public static String CAT_FOOD = "FOOD";
    public static String CAT_TRAVEL = "TRAVEL";
    public static String CAT_SHOPPING = "SHOPPING";
    public static String CAT_BILLS = "BILLS";
    public static String CAT_SALARY = "SALARY";
    public static String CAT_ALLOWANCE = "ALLOWANCE";
    public static String CAT_OTHERS = "OTHERS";

    public static String INPUT_CAT_TRANSPORT = "tpt";
    public static String INPUT_CAT_FOOD = "fd";
    public static String INPUT_CAT_TRAVEL = "tvl";
    public static String INPUT_CAT_SHOPPING = "shp";
    public static String INPUT_CAT_BILLS = "bll";
    public static String INPUT_CAT_SALARY = "slr";
    public static String INPUT_CAT_ALLOWANCE = "alw";
    public static String INPUT_CAT_OTHERS = "oth";

    public static ArrayList<String> expenseCategories = new ArrayList() {
        {
            add(CAT_TRANSPORT);
            add(CAT_FOOD);
            add(CAT_TRAVEL);
            add(CAT_SHOPPING);
            add(CAT_BILLS);
            add(CAT_OTHERS);
        }
    };

    public static ArrayList<String> incomeCategories = new ArrayList() {
        {
            add(CAT_SALARY);
            add(CAT_ALLOWANCE);
            add(CAT_OTHERS);
        }
    };


    public static HashMap<String, String> inputToCategoryMap = new HashMap() {
        {
            put(INPUT_CAT_TRANSPORT, CAT_TRANSPORT);
            put(INPUT_CAT_FOOD, CAT_FOOD);
            put(INPUT_CAT_TRAVEL, CAT_TRAVEL);
            put(INPUT_CAT_SHOPPING, CAT_SHOPPING);
            put(INPUT_CAT_BILLS, CAT_BILLS);
            put(INPUT_CAT_SALARY, CAT_SALARY);
            put(INPUT_CAT_ALLOWANCE, CAT_ALLOWANCE);
            put(INPUT_CAT_OTHERS, CAT_OTHERS);
        }
    };

    /**
     * Used by the SaveManager to convert category value in the Entry class to the
     * corresponding user input.
     * This allows the class to reconstruct the commands required to generate the
     * saved Entry instances.
     */
    public static HashMap<String, String> categoryToInputMap = new HashMap() {
        {
            put(CAT_TRANSPORT, INPUT_CAT_TRANSPORT);
            put(CAT_FOOD, INPUT_CAT_FOOD);
            put(CAT_TRAVEL, INPUT_CAT_TRAVEL);
            put(CAT_SHOPPING, INPUT_CAT_SHOPPING);
            put(CAT_BILLS, INPUT_CAT_BILLS);
            put(CAT_SALARY, INPUT_CAT_SALARY);
            put(CAT_ALLOWANCE, INPUT_CAT_ALLOWANCE);
            put(CAT_OTHERS, INPUT_CAT_OTHERS);
        }
    };

    public static String getInputFromCategory(String input) {
        return categoryToInputMap.get(input);
    }

    public static String getCategoryFromInput(String input) {
        return inputToCategoryMap.get(input);
    }

    public void addNewCategory(String shortcut, String category) {
        inputToCategoryMap.put(shortcut, category);
        categoryToInputMap.put(category, shortcut);
    }
}
