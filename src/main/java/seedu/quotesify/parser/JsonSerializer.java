package seedu.quotesify.parser;

import org.json.simple.JSONObject;

public interface JsonSerializer {
    public JSONObject toJson();
}
