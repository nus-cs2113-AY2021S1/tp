package storage;

import common.KajiLog;
import exception.ExclusionFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

//@@ author Darticune
public class StorageWrite {
    private static Logger logger = KajiLog.getLogger(Storage.class.getName());

    public static final String MESSAGE_CREATED = "Successfully created new %1$s %2$s\n";
    public static final String MESSAGE_EXISTS = "%1$s %2$s already exists\n";

    public static final String FILE = "file";
    public static final String DIR = "directory";

    protected static void createDir(File f) {
        boolean dirExists = f.exists();
        boolean dirCreated = false;
        if (!dirExists) {
            dirCreated = f.mkdir();
        } else {
            logger.info(String.format(MESSAGE_EXISTS, DIR, f));
        }
        if (dirCreated) {
            logger.info(String.format(MESSAGE_CREATED, DIR, f));
        }
    }

    protected static void createFile(File f) throws IOException {
        boolean fileExists = f.exists();
        boolean fileCreated = false;
        if (!fileExists) {
            fileCreated = f.createNewFile();
        } else {
            logger.info(String.format(MESSAGE_EXISTS, FILE, f));
        }
        if (fileCreated) {
            logger.info(String.format(MESSAGE_CREATED, FILE, f));
        }
    }

    protected static void updateExclusionFile(ArrayList<String> excludedChapters, String filePath)
            throws ExclusionFileException {
        try {
            FileWriter exclusionFileWriter = new FileWriter(filePath + "/exclusions.txt");
            for (String excluded : excludedChapters) {
                exclusionFileWriter.write(excluded + "\n");
            }
            exclusionFileWriter.close();
        } catch (IOException e) {
            throw new ExclusionFileException("Sorry, there was an error in accessing the Exclusion File");
        }
    }

    protected static boolean createChapterDue(String duePath, String dirPath) throws IOException {
        File due = new File(duePath);
        File dir = new File(dirPath);
        boolean isDirValid = dir.exists() && dir.isDirectory();
        if (!isDirValid) {
            return dir.mkdir() && due.createNewFile();
        } else if (!due.exists()) {
            return due.createNewFile();
        } else {
            return true;
        }
    }

    public static void createHistoryDir() {
        File f = new File("data/history");

        boolean historyDirExists = f.exists();
        if (!historyDirExists) {
            f.mkdir();
        }
    }

    protected static void writeDeadlineToChapterDue(String dueBy, String chapterPath) throws IOException {
        FileWriter fw = new FileWriter(chapterPath);
        fw.write(dueBy);
        fw.close();
    }
}
