package seedu.duke.storage;

import seedu.duke.exceptions.CustomException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Storage {
    File file;
    String dir;

    public Storage(String dir) {
        this.dir = dir;
        createFile(dir);
    }

    protected void createFile(String dir) {
        try {
            file = new File(dir);
            file.getParentFile().mkdirs();
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println(file.getName() + " already exists");
            }
        } catch (IOException e) {
            System.out.println("Error:");
            e.printStackTrace();
        }
    }

    protected File getFile() {
        return file;
    }

    public abstract void readFile() throws FileNotFoundException, CustomException;

    public abstract void updateFile() throws IOException, CustomException;
}
