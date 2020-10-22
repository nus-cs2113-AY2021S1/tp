package seedu.eduke8.common;

import seedu.eduke8.exception.Eduke8Exception;

import java.util.ArrayList;

public interface DisplayableList {
    ArrayList<Displayable> getInnerList();

    Displayable find(String description) throws Eduke8Exception;

    int getCount();
}
