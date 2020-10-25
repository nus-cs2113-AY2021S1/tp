package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsonable;

public interface JsonableArray extends Jsonable {
    /**
     * Parse the given JsonArray and map the values to the current class.
     * @param jsonArr JsonArray that contains all values.
     */
    void fromJson(JsonArray jsonArr);
}
