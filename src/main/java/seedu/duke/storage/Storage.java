package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

public abstract class Storage {
    File file;

    public Storage(String dir) {
        createFile(dir);
    }

    protected void createFile(String dir) {
        try {
            file = new File(dir);
            file.getParentFile().mkdirs();
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("Error:");
            e.printStackTrace();
        }
    }

    protected File getFile() {
        return file;
    }

    public abstract void readFile();

    public abstract void updateFile();
}
