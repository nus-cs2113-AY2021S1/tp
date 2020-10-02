package seedu.duke;

import java.util.ArrayList;

public interface OptionListInterface {
    public ArrayList<OptionInterface> getOptionList();
    public void addOption(OptionInterface option);
    public void deleteOption(OptionInterface option);
    public int getOptionCount();
}
