package seedu.eduke8.storage;

import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;

import java.io.IOException;
import java.util.ArrayList;

public interface Storage {
    void save(ArrayList<Displayable> displayables) throws IOException;

    ArrayList<Displayable> load() throws IOException, ParseException;

}
