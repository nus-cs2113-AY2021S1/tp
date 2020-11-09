package storage;

import common.KajiLog;
import exception.StorageDataException;
import exception.ExclusionFileException;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.history.History;
import manager.module.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static storage.StorageLoad.throwExceptionIfFileDoesNotExist;

/**
 *  Manages writing of Kaji data to local storage.
 */
public class StorageWrite {
    private static Logger logger = KajiLog.getLogger(Storage.class.getName());

    private static final String MESSAGE_CREATED = "Successfully created new %1$s %2$s\n";
    private static final String MESSAGE_EXISTS = "%1$s %2$s already exists\n";

    public static final String FILE = "file";
    public static final String DIR = "directory";

    //@@author gua-guargia
    protected static void createDir(File f) throws IOException {
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
        if (!(dirExists || dirCreated)) {
            throw new IOException("Error creating new folder. Please check your directory.");
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

        if (!(fileExists || fileCreated)) {
            throw new IOException("Error creating new file. Please check your directory.");
        }
    }

    /**
     * Writes the contents of excludedChapters into the Exclusion File.
     * @param excludedChapters ArrayList of String that comprises all the Chapters that are to be excluded from
     *                         scheduling.
     * @param filePath Filepath to the Admin level.
     * @throws ExclusionFileException if there are errors encountered when writing into the Exclusion File.
     */
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

    /**
     * Creates the dueFile that will contain the deadline for a Chapter.
     * @param duePath path to the dueFile
     * @param dirPath path to the dues folder in the folder of the Module that contains the target Chapter
     * @return boolean on whether the creation of the dueFile was successful.
     * @throws IOException if there are errors with regards to file creation or reading.
     */
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
    /**
     * Creates the history directory.
     *
     * @throws IOException if there is an error when loading from storage file
     */
    public static void createHistoryDir() throws IOException {
        File f = new File("data/history");
        createDir(f);
    }

    /**
     * Writes the Chapter Deadline into the Chapter's dueFile.
     * @param dueBy New Deadline to be updated
     * @param chapterDueFilePath path to the Chapter's dueFile
     * @throws IOException if there are errors with writing into the Chapter's dueFile.
     */
    //@@author Darticune
    protected static void writeDeadlineToChapterDue(String dueBy, String chapterDueFilePath) throws IOException {
        FileWriter fw = new FileWriter(chapterDueFilePath);
        fw.write(dueBy);
        fw.close();
    }

    private static void addEntryToExclusionFile(String moduleName, String chapterName,
                                                ArrayList<String> excludedChapters) {
        String chapterEntry = "Module: " + moduleName + "; Chapter: " + chapterName;
        if (!excludedChapters.contains(chapterEntry)) {
            excludedChapters.add(chapterEntry);
        }
    }

    /**
     * Adds the Chapters in a specified Module into the Exclusion File.
     * @param moduleName name of target Module
     * @param filePath Filepath to the Admin level.
     * @throws FileNotFoundException  if the target Module cannot be found.
     * @throws ExclusionFileException if unable to load or update the Exclusion File.
     */
    //@@author Darticune
    protected static void appendModuleToExclusionFile(String moduleName, String filePath)
            throws FileNotFoundException, ExclusionFileException {
        ArrayList<String> excludedChapters = Storage.loadExclusionFile(filePath);
        String[] chaptersInModule = Storage.loadChaptersFromSpecifiedModule(moduleName, filePath);
        addModuleEntriesToExclusionFile(moduleName, excludedChapters, chaptersInModule);
        updateExclusionFile(excludedChapters, filePath);
    }

    private static void addModuleEntriesToExclusionFile(String moduleName, ArrayList<String> excludedChapters,
                                                        String[] chaptersInModule) {
        for (String chapter : chaptersInModule) {
            if (chapter.equals("dues")) {
                continue;
            }
            chapter = chapter.replace(".txt", "");
            addEntryToExclusionFile(moduleName, chapter, excludedChapters);
        }
    }

    /**
     * Adds a specified Chapter into the Exclusion File.
     * @param moduleName name of Module that the target Chapter belongs to
     * @param chapterName name of the target Chapter
     * @param filePath Filepath to the Admin level.
     * @throws FileNotFoundException  if the target Module cannot be found.
     * @throws ExclusionFileException if unable to load or update the Exclusion File.
     */
    //@@author Darticune
    protected static void appendChapterToExclusionFile(String moduleName, String chapterName, String filePath)
            throws FileNotFoundException,
            ExclusionFileException {
        ArrayList<String> excludedChapters = Storage.loadExclusionFile(filePath);
        File file = new File(filePath + "/" + moduleName + "/" + chapterName + ".txt");
        throwExceptionIfFileDoesNotExist(file);
        addEntryToExclusionFile(moduleName, chapterName, excludedChapters);
        updateExclusionFile(excludedChapters, filePath);
    }

    private static boolean deleteEntryFromExclusionFile(String moduleName, ArrayList<String> excludedChapters,
                                                     String chapterName) {
        String chapterEntry = "Module: " + moduleName + "; Chapter: " + chapterName;
        if (!(excludedChapters.contains(chapterEntry))) {
            return false;
        }
        excludedChapters.remove(chapterEntry);
        return true;
    }

    /**
     * Removes the Chapters in a specified Module from the Exclusion File.
     * @param moduleName name of target Module
     * @param filePath Filepath to the Admin level.
     * @throws FileNotFoundException  if the target Module cannot be found.
     * @throws ExclusionFileException if unable to load or update the Exclusion File.
     */
    //@@author Darticune
    protected static void removeModuleFromExclusionFile(String moduleName, String filePath)
            throws FileNotFoundException, ExclusionFileException {
        ArrayList<String> excludedChapters = Storage.loadExclusionFile(filePath);
        String[] chaptersInModule = Storage.loadChaptersFromSpecifiedModule(moduleName, filePath);
        deleteModuleEntriesFromExclusionFile(moduleName, excludedChapters, chaptersInModule);
        updateExclusionFile(excludedChapters, filePath);
    }

    private static void deleteModuleEntriesFromExclusionFile(String moduleName, ArrayList<String> excludedChapters,
                                                             String[] chaptersInModule) {
        for (String chapter : chaptersInModule) {
            String chapterName = chapter.replace(".txt", "");
            deleteEntryFromExclusionFile(moduleName, excludedChapters, chapterName);
        }
    }

    /**
     * Removes a specified Chapter from the Exclusion File.
     * @param moduleName name of Module that the target Chapter belongs to
     * @param chapterName name of the target Chapter
     * @param filePath Filepath to the Admin level.
     * @throws FileNotFoundException  if the target Module or Chapter cannot be found.
     * @throws ExclusionFileException if unable to load or update the Exclusion File.
     */
    //@@author Darticune
    protected static boolean removeChapterFromExclusionFile(String moduleName, String chapterName, String filePath)
            throws FileNotFoundException, ExclusionFileException {
        ArrayList<String> excludedChapters = Storage.loadExclusionFile(filePath);
        boolean result = deleteEntryFromExclusionFile(moduleName, excludedChapters, chapterName);
        updateExclusionFile(excludedChapters, filePath);
        return result;
    }

    //@@author Zhu-Ze-Yu
    /**
     * Saves all the flashcards of the specified {@code chapter}.
     *
     * @param cards list of cards
     * @param module module name of the chapters
     * @param chapter chapter name of the flashcards
     * @param filePath of the storage file
     * @throws IOException if there is an error when loading from storage file
     */
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

    /**
     * Updates the deadline for a Chapter from the name of the Module it belongs to and the name of the target Chapter.
     * @param dueBy new Deadline to be updated with
     * @param moduleName name of Module that the target Chapter belongs to
     * @param chapterName name of the target Chapter
     * @param filePath Filepath to the Admin level.
     */
    //@@author Darticune
    protected static void saveChapterDeadline(String dueBy, String moduleName, String chapterName, String filePath) {
        try {
            String dirPath = filePath + "/" + moduleName + "/" + "dues";
            String duePath = filePath + "/" + moduleName + "/" + "dues" + "/" + chapterName + "due" + ".txt";
            if (createChapterDue(duePath, dirPath)) {
                writeDeadlineToChapterDue(dueBy, duePath);
            } else {
                System.out.println("Unable to produce ChapterDue");
            }
        } catch (IOException e) {
            System.out.println("Error in saving chapter deadline");
        }
    }

    //@@author neojiaern
    /**
     * Deletes a directory recursively.
     *
     * @param directoryToBeDeleted file to be deleted
     * @return boolean result of the deletion
     */
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
    /**
     * Rename the filename of a chapter.
     *
     * @param newChapterName new chapter name to rename to
     * @param module module of the chapter to be renamed
     * @param chapter existing chapter to be renamed
     * @param filePath of the storage file
     * @throws StorageDataException if there is an error renaming the storage file
     */
    protected static void renameChapter(String newChapterName, Module module, Chapter chapter, String filePath)
            throws StorageDataException {
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
            throw new StorageDataException("Error renaming the chapter. Please check your directory.");
        }
    }

    //@@author Jane-Ng
    /**
     * Rename the folder of a module.
     *
     * @param newModuleName new module name to rename to
     * @param module existing module to be renamed
     * @param filePath of the storage file
     * @throws StorageDataException if there is an error renaming the storage file
     */
    protected static void renameModule(String newModuleName, Module module, String filePath)
            throws StorageDataException {
        File file = new File(filePath + "/" + module.getModuleName());
        boolean success = file.renameTo(new File(filePath + "/" + newModuleName));
        if (!success) {
            throw new StorageDataException("Error renaming the module. Please check your directory.");
        }
    }

    //@@author Zhu-Ze-Yu
    /**
     * Create a txt file to store history.
     *
     * @param date the date of the revision
     * @throws IOException if there is an error creating the storage file
     */
    protected static void createHistory(String date) throws IOException {
        try {
            File f = new File("data/history/" + date + ".txt");
            createFile(f);
        } catch (IOException e) {
            throw new IOException("Error creating the data/history file.");
        }
    }

    //@@author Zhu-Ze-Yu
    /**
     * Save revision history into file.
     *
     * @param date the date of the revision
     * @param histories all chapters revised in the date
     * @throws IOException if there is an error creating the storage file
     */
    protected static void saveHistory(ArrayList<History> histories, String date) throws IOException {
        FileWriter fw = new FileWriter("data/history/" + date + ".txt");
        for (History h : histories) {
            fw.write(h.toString() + "\n");
        }
        fw.close();
    }

    //@@author neojiaern
    /**
     * Removes the chapter due file from due folder after chapter is deleted.
     *
     * @param module name of module
     * @param chapter name of chapter
     * @param filePath filepath of file to be deleted
     * @return boolean result of deletion
     */
    protected static boolean removeChapterFromDue(String module, String chapter, String filePath) {
        File fileToDelete = new File(filePath + "/" + module
                + "/dues/" + chapter + "due.txt");
        return deleteDirectory(fileToDelete);
    }
}
