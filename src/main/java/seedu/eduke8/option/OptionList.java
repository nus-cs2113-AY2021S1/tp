package seedu.eduke8.option;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.util.ArrayList;

public class OptionList implements DisplayableList {
    private final ArrayList<Displayable> options;

    public OptionList() {
        options = new ArrayList<>();
    }

    public OptionList(ArrayList<Displayable> options) {
        this.options = options;
    }

    @Override
    public ArrayList<Displayable> getInnerList() {
        return this.options;
    }

    @Override
    public void add(Displayable option) {
        options.add(option);
    }

    @Override
    public void delete(int index) {
        options.remove(index);
    }

    @Override
    public Displayable find(String description) {
        for (Displayable option : options) {
            if (description.equals(option.getDescription())) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return options.size();
    }
}
