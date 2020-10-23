package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsonable;

public interface JsonableArray extends Jsonable {

    void fromJson(JsonArray jsonArr);
}
