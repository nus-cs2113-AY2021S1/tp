package seedu.financeit.utils.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class SaveStateHandler {

    protected String fullpath;
    protected String dir_path;

    public SaveStateHandler(){

    }

    public SaveStateHandler(String filepath, String directory){
        fullpath = filepath;
        dir_path = directory;
    }
    public void save() throws IOException {

    }

    public void load() throws IOException {

    }

    public void setPath(String filepath, String directory) {
        fullpath = filepath;
        dir_path = directory;
    }

    protected void buildFile() throws IOException {
        if (!Files.exists(Paths.get(dir_path))) {
            Files.createDirectory(Paths.get(dir_path));
        }
        if (!Files.exists(Paths.get(fullpath))) {
            Files.createFile(Paths.get(fullpath));
        }
    }
}
