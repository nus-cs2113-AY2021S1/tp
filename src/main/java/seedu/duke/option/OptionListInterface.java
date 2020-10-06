package seedu.duke;

import java.util.ArrayList;

public interface OptionListInterface {
    ArrayList<OptionInterface> getOptionList();

    void addOption(OptionInterface option);

    void deleteOption(OptionInterface option);

    int getOptionCount();

}
