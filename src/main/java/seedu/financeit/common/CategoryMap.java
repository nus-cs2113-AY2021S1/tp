package seedu.financeit.common;

import java.util.HashMap;

/**
 * Bi-directional map to store transaction category and its corresponding user input shortcut.
 */
public class CategoryMap {
    /**
     *  Used to parse user category input, ensures that the range of corresponding Categories to given input is
     *  confined to that in the CategoryMap.
     */
    public static HashMap<String, String> inputToCategoryMap = new HashMap() {
        {
            put("tpt", "TRANSPORT");
            put("fd", "FOOD");
            put("tvl", "TRAVEL");
            put("shp", "SHOPPING");
            put("bll", "BILLS");
            put("slr", "SALARY");
            put("alw", "ALLOWANCE");
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
            put("TRANSPORT", "tpt");
            put("FOOD", "fd");
            put("tvl", "TRAVEL");
            put("SHOPPING", "shp");
            put("BILLS", "bll");
            put("SALARY", "slr");
            put("ALLOWANCE", "alw");
        }
    };

    public static String getInputFromCategory(String input) {
        return inputToCategoryMap.get(input);
    }

    public static String getCategoryFromInput(String input) {
        return categoryToInputMap.get(input);
    }

    public void addNewCategory(String shortcut, String category) {
        inputToCategoryMap.put(shortcut, category);
        categoryToInputMap.put(category, shortcut);
    }
}
