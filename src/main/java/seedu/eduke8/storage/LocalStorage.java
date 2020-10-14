package seedu.eduke8.storage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public abstract class LocalStorage implements Storage {
    protected String filePath = new File("").getAbsolutePath();
    protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public LocalStorage(String filePath) {
        assert filePath != null;

        // Use relative path for Unix systems
        String[] filePathSplit = filePath.split("/");
        for (String path: filePathSplit) {
            this.filePath += File.separator + path;
        }
    }

    protected void createFileIfNotExists() throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        assert f.exists();
    }
}
