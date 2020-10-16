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
        this.filePath = appendRelativePath(this.filePath, filePath);
    }

    private String appendRelativePath(String originalPath, String relativePath) {
        String fullPath = originalPath;
        String[] relativePathSplit = relativePath.split("/");
        for (String path: relativePathSplit) {
            fullPath += File.separator + path;
        }

        return fullPath;
    }

    protected File createFileIfNotExists() throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        assert file.exists();

        return file;
    }
}
