package seedu.hdbuy.data;

import seedu.hdbuy.common.QueryKey;

import java.util.LinkedHashMap;

public class UserInput {

    private static final LinkedHashMap<QueryKey, String> inputs = new LinkedHashMap<>();

    /**
     * This method will return the user input to continue the app.
     *
     * @return A hashmap of the user inputs.
     */
    public static LinkedHashMap<QueryKey, String> getInputs() {
        return inputs;
    }

    /**
     * This method will clear the user input so that a new one can be entered.
     */
    public static void clearInputs() {
        inputs.clear();
    }
}
