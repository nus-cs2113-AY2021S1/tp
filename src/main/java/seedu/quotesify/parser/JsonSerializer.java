package seedu.quotesify.parser;

import org.json.simple.JSONObject;

/**
 * Serializes objects into JSON string.
 */
public interface JsonSerializer {
    JSONObject toJson();
}
