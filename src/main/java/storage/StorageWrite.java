package storage;

import common.KajiLog;
import exception.DuplicateDataException;
import exception.ExclusionFileException;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.history.History;
import manager.module.Module;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static storage.StorageLoad.checkExists;

public class StorageWrite {
    private static Logger logger = KajiLog.getLogger(Storage.class.getName());

    public static final String MESSAGE_CREATED = "Successfully created new %1$s %2$s\n";
    public static final String MESSAGE_EXISTS = "%1$s %2$s already exists\n";

    public static final String FILE = "file";
    public static final String DIR = "directory";

    //@@author gua-guargia
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

    //@@author gua-guargia
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

    //@@author Darticune
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

    //@@author Darticune
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

    //@@author Zhu-Ze-Yu
    public static void createHistoryDir() {
        File f = new File("data/history");
        createDir(f);
    }

    //@@author Darticune
    protected static void writeDeadlineToChapterDue(String dueBy, String chapterPath) throws IOException {
        FileWriter fw = new FileWriter(chapterPath);
        fw.write(dueBy);
        fw.close();
    }

    //@@author Darticune
    protected static void appendModuleToExclusionFile(String moduleName, String filePath)
            throws FileNotFoundException, ExclusionFileException {
        ArrayList<String> excludedChapters = StorageLoad.loadExclusionFile(filePath);
        String[] chaptersInModule = StorageLoad.loadChaptersFromSpecifiedModule(moduleName, filePath);
        for (String chapter : chaptersInModule) {
            if (chapter.equals("dues")) {
                continue;
            }
            chapter = chapter.replace(".txt", "");
            String chapterEntry = "Module: " + moduleName + "; Chapter: " + chapter;
            if (!excludedChapters.contains(chapterEntry)) {
                excludedChapters.add(chapterEntry);
            }
        }
        updateExclusionFile(excludedChapters, filePath);
    }

    //@@author Darticune
    protected static void appendChapterToExclusionFile(String moduleName, String chapterName, String filePath)
            throws FileNotFoundException,
            ExclusionFileException {
        ArrayList<String> excludedChapters = StorageLoad.loadExclusionFile(filePath);
        File file = new File(filePath + "/" + moduleName + "/" + chapterName + ".txt");
        checkExists(file);
        String chapterEntry = "Module: " + moduleName + "; Chapter: " + chapterName;
        if (!excludedChapters.contains(chapterEntry)) {
            excludedChapters.add(chapterEntry);
        }
        updateExclusionFile(excludedChapters, filePath);
    }

    //@@author Darticune
    protected static void removeModuleFromExclusionFile(String moduleName, String filePath)
            throws FileNotFoundException, ExclusionFileException {
        ArrayList<String> excludedChapters = StorageLoad.loadExclusionFile(filePath);
        String[] chaptersInModule = StorageLoad.loadChaptersFromSpecifiedModule(moduleName, filePath);
        for (String chapter : chaptersInModule) {
            chapter = chapter.replace(".txt", "");
            String chapterEntry = "Module: " + moduleName + "; Chapter: " + chapter;
            excludedChapters.remove(chapterEntry);
        }
        updateExclusionFile(excludedChapters, filePath);
    }

    //@@author Darticune
    protected static void removeChapterFromExclusionFile(String moduleName, String chapterName, String filePath)
            throws FileNotFoundException, ExclusionFileException {
        ArrayList<String> excludedChapters = StorageLoad.loadExclusionFile(filePath);
        String chapterEntry = "Module: " + moduleName + "; Chapter: " + chapterName;
        excludedChapters.remove(chapterEntry);
        updateExclusionFile(excludedChapters, filePath);
    }

    //@@author Zhu-Ze-Yu
    protected static void saveCards(CardList cards, String module, String chapter, String filePath)
            throws IOException {
        FileWriter fw = new FileWriter(filePath + "/" + module + "/" + chapter + ".txt");
        for (int i = 0; i < cards.getCardCount(); i++) {
            int ratingInt = cards.getCard(i).getRating();
            String ratingString = Integer.toString(ratingInt);
            fw.write(cards.getCard(i).toString()
                    + " | [P] " + cards.getCard(i).getPreviousInterval() + " | [R] "
                    + ratingString + "\n");
        }
        fw.close();
    }

    //@@author Darticune
    protected static void saveChapterDeadline(String dueBy, String moduleName, String chapterName, String filePath) {
        try {
            String dirPath = filePath + "/" + moduleName + "/" + "dues";
            String duePath = filePath + "/" + moduleName + "/" + "dues" + "/" + chapterName + "due" + ".txt";
            if (StorageWrite.createChapterDue(duePath, dirPath)) {
                StorageWrite.writeDeadlineToChapterDue(dueBy, duePath);
            } else {
                System.out.println("Unable to produce ChapterDue");
            }
        } catch (IOException e) {
            System.out.println("Error in saving chapter deadline");
        }
    }

    //@@author neojiaern
    protected static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    //@@author Jane-Ng
    protected static void renameChapter(String newChapterName, Module module, Chapter chapter, String filePath)
            throws DuplicateDataException {
        File chapterFile = new File(filePath
                + "/" + module.getModuleName()
                + "/" + chapter.getChapterName() + ".txt");
        File dueFile = new File(filePath
                + "/" + module.getModuleName()
                + "/dues"
                + "/" + chapter.getChapterName() + "due.txt");
        boolean chapterSuccess = chapterFile.renameTo(new File(filePath
                + "/" + module.getModuleName()
                + "/" + newChapterName + ".txt"));
        boolean dueSuccess = dueFile.renameTo(new File(filePath
                + "/" + module.getModuleName()
                + "/dues"
                + "/" + newChapterName + "due.txt"));
        if (!chapterSuccess || !dueSuccess) {
            throw new DuplicateDataException("A chapter with this name already exists.");
        }
    }

    //@@author Jane-Ng
    protected static void renameModule(String newModuleName, Module module, String filePath)
            throws DuplicateDataException {
        File file = new File(filePath + "/" + module.getModuleName());
        boolean success = file.renameTo(new File(filePath + "/" + newModuleName));
        if (!success) {
            throw new DuplicateDataException("A module with this name already exists.");
        }
    }

    //@@author Zhu-Ze-Yu
    protected static void createHistory(Ui ui, String date) {
        try {
            File f = new File("data/history/" + date + ".txt");
            createFile(f);
        } catch (IOException e) {
            ui.showError("Error creating the data/history file.");
        }
    }

    //@@author Zhu-Ze-Yu
    protected static void saveHistory(ArrayList<History> histories, String date) throws IOException {
        FileWriter fw = new FileWriter("data/history/" + date + ".txt");
        for (History h : histories) {
            fw.write(h.toString() + "\n");
        }
        fw.close();
    }

    //@@author neojiaern
    protected static boolean removeChapterFromDue(String module, String chapter, String filePath) {
        File fileToDelete = new File(filePath + "/" + module
                + "/dues/" + chapter + "due.txt");
        return deleteDirectory(fileToDelete);
    }
}
