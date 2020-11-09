package storage;

import cheatsheet.CheatSheetList;
import ui.Printer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * A general class that stores certain constants regarding the
 * file location of the /data directory, as well as methods that can be
 * utilised by its sub-classes.
 */
public abstract class DataFile {

    protected static final String YES = "YES";
    protected static final String NO = "NO";
    protected static final String EMPTY = "";
    protected static final String SLASH = "/";

    protected static final String USER_DIR = System.getProperty("user.dir");
    protected static final String JAR_DIR = "CheatLogs.jar";

    protected static final String DATA = "data";
    protected static final String PRELOADED = "preloaded";
    protected static final String XML_EXTENSION = ".xml";

    protected static final String MAIN_ELEMENT = "main";
    protected static final String FAVOURITE_ELEMENT = "favourite";
    protected static final String SUBJECT_ELEMENT = "subject";
    protected static final String CONTENTS_ELEMENT = "contents";

    protected static final Path PRELOADED_DIR = Paths.get(USER_DIR, DATA, PRELOADED);
    protected static final Path DATA_DIR = Paths.get(USER_DIR, DATA);

    protected static final String SETTINGS_FILENAME = "/settings.txt";

    protected Printer printer;
    protected CheatSheetList cheatSheetList;
    public static ArrayList<Path> preloadedCheatSheets = new ArrayList<>();

    /**
     * A method that is to be run based on its subclass.
     */
    public abstract void executeFunction();

    /**
     * Creates a new directory and file at the specified location
     * if it currently does not exist.
     */
    protected void createNewDirectory() {
        if (!Files.exists(DATA_DIR)) {
            try {
                Files.createDirectories(DATA_DIR);
            } catch (IOException e) {
                printer.print(e.getMessage());
            }
        }
    }

    /**
     * Creates a new document builder to be used for the particular instance of
     * reading or writing a file.
     *
     * @return                                A new document builder.
     * @throws ParserConfigurationException   Throws when a serious
     *                                        configuration error has been caught.
     */
    protected DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder();
    }

    /**
     * Checks if the /data and /subjectName directories exist and creates them if they
     * are currently non-existent.
     *
     * @param subjectDirectory          The directory to store a non-preloaded cheatSheet.
     * @param preloadedSubjectDirectory The directory to store a preloaded cheatSheet
     * @param isPreloadedFile           Boolean indicating if the file is preloaded or not.
     * @throws IOException              Thrown if errors occur when attempting to create the
     *                                  respective directories.
     */
    protected void verifyDirectoryExistence(Path subjectDirectory,
                                          Path preloadedSubjectDirectory,
                                          boolean isPreloadedFile) throws IOException {
        if (!Files.exists(DATA_DIR)) {
            Files.createDirectory(DATA_DIR);
        }

        if (isPreloadedFile) {
            if (!Files.exists(PRELOADED_DIR)) {
                Files.createDirectory(PRELOADED_DIR);
            }
            if (!Files.exists(preloadedSubjectDirectory)) {
                Files.createDirectory(preloadedSubjectDirectory);
            }
        }

        if (subjectDirectory != null && !Files.exists(subjectDirectory)) {
            Files.createDirectory(subjectDirectory);
        }
    }

    /**
     * Deletes the input directory if it is found to be empty as well as
     * XML files that are present in /data directory.
     *
     * @param queriedDirFile Directory file that the method is looking at.
     */
    protected void updateDirectory(File queriedDirFile) {
        if (!queriedDirFile.isDirectory()) {
            if (queriedDirFile.getParentFile().equals(DATA_DIR.toFile())) {
                queriedDirFile.delete();
            }
            return;
        }
        File[] queriedDirSubFiles = queriedDirFile.listFiles();
        assert queriedDirSubFiles != null;

        if (queriedDirSubFiles.length == 0 && queriedDirFile != DATA_DIR.toFile()) {
            queriedDirFile.delete();
        } else {
            for (File queriedDirSubFile : queriedDirSubFiles) {
                if (queriedDirFile.isDirectory()) {
                    updateDirectory(queriedDirSubFile);
                }
            }
        }
    }
}
