package seedu.quotesify.author;

import org.json.simple.JSONObject;
import seedu.quotesify.parser.JsonSerializer;

public class Author implements JsonSerializer {
    String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.getName());
        return json;
    }
}
