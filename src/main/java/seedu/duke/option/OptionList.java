package seedu.duke.option;

import java.util.ArrayList;

public class OptionList implements OptionListInterface {
    private final ArrayList<OptionInterface> optionList;
    private int optionCount;

    public OptionList() {
        optionList = new ArrayList<>();
        this.optionCount = 0;
    }

    public OptionList(ArrayList<OptionInterface> optionList) {
        this.optionList = optionList;
        this.optionCount = 0;
    }

    public void addOption(OptionInterface option) {
        optionList.add(option);
        this.optionCount++;
    }

    public void deleteOption(OptionInterface option) {
        optionList.remove(option);
        this.optionCount--;
    }

    public ArrayList<OptionInterface> getOptionList() {
        return this.optionList;
    }

    public int getOptionCount() {
        return this.optionCount;
    }

}
