package seedu.eduke8.storage;

import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Storage {
    File save() throws IOException;

    ArrayList<Displayable> load() throws IOException, ParseException;

}
