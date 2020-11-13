package seedu.duke.utility;


import java.io.FileNotFoundException;
import java.io.IOException;

public interface SaveState {
    void saveState() throws IOException;

    ShowList loadState() throws FileNotFoundException;

}
