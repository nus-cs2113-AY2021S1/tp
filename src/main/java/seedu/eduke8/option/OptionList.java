package seedu.eduke8.option;

import java.util.ArrayList;

public class OptionList implements OptionListInterface {
    private final ArrayList<OptionInterface> options;

    public OptionList() {
        options = new ArrayList<>();
    }

    public OptionList(ArrayList<OptionInterface> optionList) {
        this.options = optionList;
    }

    public void addOption(OptionInterface option) {
        options.add(option);
    }

    public void deleteOption(OptionInterface option) {
        options.remove(option);
    }

    public ArrayList<OptionInterface> getOptionList() {
        return this.options;
    }
}
