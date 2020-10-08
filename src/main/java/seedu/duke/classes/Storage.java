package seedu.duke.classes;

import seedu.duke.utility.SaveState;

public class Storage implements SaveState {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void saveState() {

    }

    @Override
    public void loadState() {

    }
}
