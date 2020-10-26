package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public interface JsonableObject extends Jsonable {

    /**
     * Parse the given JsonObject and map the values to the current class.
     * @param jsonObj JsonObject that contains all the key-value pairs.
     */
    void fromJson(JsonObject jsonObj);

    /**
     * Retrieves the value with the given key and parse it as an integer.
     * @param jsonObject Json object that contains the key.
     * @param key Key of the key-value pair.
     * @return Integer value of the key-value pair.
     */
    static int parseInt(JsonObject jsonObject, String key) {
        return ((BigDecimal) jsonObject.get(key)).intValue();
    }

    /**
     * Retrieves the value with the given key and parse it as an boolean.
     * @param jsonObject Json object that contains the key.
     * @param key Key of the key-value pair.
     * @return Boolean value of the key-value pair.
     */
    static boolean parseBoolean(JsonObject jsonObject, String key) {
        return (Boolean) jsonObject.get(key);
    }

    /**
     * Retrieves the value with the given key and parse it as an string.
     * @param jsonObject Json object that contains the key.
     * @param key Key of the key-value pair.
     * @return String value of the key-value pair.
     */
    static String parseString(JsonObject jsonObject, String key) {
        return (String) jsonObject.get(key);
    }

    /**
     * Retrieves the value with the given key and parse it as an LocalDate.
     * @param jsonObject Json object that contains the key.
     * @param key Key of the key-value pair.
     * @return LocalDate value of the key-value pair.
     */
    static LocalDate parseDate(JsonObject jsonObject, String key) {
        Object rawDate = jsonObject.get(key);
        if (rawDate != null) {
            return LocalDate.parse((String) rawDate);
        }
        return null;
    }
    
    /**
     * Retrieves the value with the given key and parse it as an list of integers.
     * @param jsonObject Json object that contains the key.
     * @param key Key of the key-value pair.
     * @return ArrayList Integer values of the key-value pair.
     */
    static ArrayList<Integer> parseIntegerList(JsonObject jsonObject, String key) {
        ArrayList<Integer> list = new ArrayList<>();
        JsonArray jsonArr = (JsonArray) jsonObject.get(key);

        for (Object o : jsonArr) {
            int num = ((BigDecimal) o).intValue();
            list.add(num);
        }
        return list;
    }

    /**
     * Retrieves the value with the given key and parse it as an list of strings.
     * @param jsonObject Json object that contains the key.
     * @param key Key of the key-value pair.
     * @return ArrayList of String values of the key-value pair.
     */
    static ArrayList<String> parseStringList(JsonObject jsonObject, String key) {
        ArrayList<String> list = new ArrayList<>();
        JsonArray jsonArr = (JsonArray) jsonObject.get(key);

        for (Object o : jsonArr) {
            list.add((String) o);
        }
        return list;
    }
}
