package seedu.financeit.utils.storage;

import java.io.IOException;

public class SaveStateHandlerRecurringTracker extends SaveStateHandler {

    public SaveStateHandlerRecurringTracker() {
        super();
    }

    public SaveStateHandlerRecurringTracker(String filepath, String directory) {
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
    }

    public void load() throws IOException {
        buildFile();
    }
}
