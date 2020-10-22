package seedu.eduke8.common;

import java.util.ArrayList;

public interface DisplayableList {
    ArrayList<Displayable> getInnerList();

    Displayable find(String description);

    int getCount();
}
