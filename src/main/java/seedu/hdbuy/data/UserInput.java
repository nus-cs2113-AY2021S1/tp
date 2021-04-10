package seedu.hdbuy.data;

import java.util.LinkedHashMap;

import seedu.hdbuy.common.QueryKey;

public class UserInput {

    private static final LinkedHashMap<QueryKey, String> inputs = new LinkedHashMap<>();

    public static LinkedHashMap<QueryKey, String> getInputs() {
        return inputs;
    }

    public static void clearInputs() {
        inputs.clear();
    }
}
