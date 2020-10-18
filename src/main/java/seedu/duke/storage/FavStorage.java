package seedu.duke.storage;

import java.io.File;

public class FavStorage extends Storage {
    private File file;
    private int favCount = 0;

    public FavStorage(String dir) {
        super(dir);
        file = getFile();
    }

    @Override
    public void readFile() {
    }

    @Override
    public void updateFile() {

    }
}
