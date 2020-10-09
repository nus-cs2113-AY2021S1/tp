package seedu.eduke8.storage;

import seedu.eduke8.common.Displayable;

import java.util.ArrayList;

public interface Storage {
    void save(ArrayList<Displayable> displayables);

    ArrayList<Displayable> load();

}
