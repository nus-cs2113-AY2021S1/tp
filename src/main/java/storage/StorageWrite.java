package storage;

import exception.ExclusionFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//@@ author Darticune
public class StorageWrite {

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
