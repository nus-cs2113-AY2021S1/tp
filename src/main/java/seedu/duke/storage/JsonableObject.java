package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public interface JsonableObject extends Jsonable {

    void fromJson(JsonObject jsonObj);

    static int parseInt(JsonObject jsonObject, String key) {
        return ((BigDecimal) jsonObject.get(key)).intValue();
    }

    static boolean parseBoolean(JsonObject jsonObject, String key) {
        return (Boolean) jsonObject.get(key);
    }

    static String parseString(JsonObject jsonObject, String key) {
        return (String) jsonObject.get(key);
    }

    static LocalDate parseDate(JsonObject jsonObject, String key) {
        Object rawDate = jsonObject.get(key);
        if (rawDate != null) {
            return LocalDate.parse((String) rawDate);
        }
        return null;
    }

    static ArrayList<Integer> parseIntegerList(JsonObject jsonObject, String key) {
        ArrayList<Integer> list = new ArrayList<>();
        JsonArray jsonArr = (JsonArray) jsonObject.get(key);

        for (Object o : jsonArr) {
            int num = ((BigDecimal) o).intValue();
            list.add(num);
        }
        return list;
    }

    static ArrayList<String> parseStringList(JsonObject jsonObject, String key) {
        ArrayList<String> list = new ArrayList<>();
        JsonArray jsonArr = (JsonArray) jsonObject.get(key);

        for (Object o : jsonArr) {
            list.add((String) o);
        }
        return list;
    }
}
