package seedu.eduke8.common;

import java.util.ArrayList;

public interface DisplayableList {
    ArrayList<Displayable> getInnerList();

    void add(Displayable displayable);

    void delete(int index);

    Displayable find(String description);

    int getCount();
}
