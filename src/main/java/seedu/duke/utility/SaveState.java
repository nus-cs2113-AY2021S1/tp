package seedu.duke.utility;


import seedu.duke.classes.Show;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public interface SaveState {
    void saveState() throws IOException;

    ShowList loadState() throws FileNotFoundException;

}
