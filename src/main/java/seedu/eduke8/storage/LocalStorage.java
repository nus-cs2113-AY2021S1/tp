package seedu.eduke8.storage;

import java.io.File;
import java.io.IOException;

public abstract class LocalStorage implements Storage {
    protected String filePath = new File("").getAbsolutePath();

    protected void createFileIfNotExists() throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
    }
}
