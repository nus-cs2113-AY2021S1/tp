package seedu.duke.classes;

import seedu.duke.utility.SaveState;

public class PersistData implements SaveState {

    private String filePath;

    public PersistData(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void saveState() {

    }

    @Override
    public void loadState() {

    }
}
