package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserStorage extends LocalStorage {
    private DisplayableList infoList;

    protected UserStorage(String filePath) {
        super(filePath);
    }

    @Override
    public File save() throws IOException {
        File file = super.save();

        return file;
    }

    @Override
    public ArrayList<Displayable> load() throws IOException, ParseException {
        JSONArray objectsAsJsonArray = getJsonArrayFromFile();

        return null;
    }
}
