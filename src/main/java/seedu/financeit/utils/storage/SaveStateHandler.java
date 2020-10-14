package seedu.financeit.utils.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class SaveStateHandler {

    protected String fullPath;
    protected String dirPath;

    public SaveStateHandler(){

    }

    public SaveStateHandler(String filepath, String directory) {
        fullPath = filepath;
        dirPath = directory;
    }

    public void save() throws IOException {

    }

    public void load() throws IOException {

    }

    public void setPath(String filepath, String directory) {
        fullPath = filepath;
        dirPath = directory;
    }

    protected void buildFile() throws IOException {
        if (!Files.exists(Paths.get(dirPath))) {
            Files.createDirectory(Paths.get(dirPath));
        }
        if (!Files.exists(Paths.get(fullPath))) {
            Files.createFile(Paths.get(fullPath));
        }
    }

    String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
}
