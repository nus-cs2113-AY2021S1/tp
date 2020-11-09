package storage;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;

import exception.DirectoryIsEmptyException;
import exception.InvalidParamException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import settings.Settings;
import ui.Printer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to read data from the data directory.
 * The data are used to update the system settings
 * and insert the cheatsheets present in the folder to the application.
 */
public class DataFileReader extends DataFile {

    public static final int MAX_PATH_LENGTH = 250;

    private final Logger readLogger = Logger.getLogger("FileReader");
    private final Settings settings;

    protected String jarDirectory = JAR_DIR;
    public static final String ALPHA_NUMERIC_REGEX = "^[a-zA-Z0-9]+$";

    private static final ArrayList<String> convertedCheatSheets = new ArrayList<>();

    public DataFileReader(Settings settings, Printer printer, CheatSheetList cheatSheetList) {
        this.settings = settings;
        this.printer = printer;
        this.cheatSheetList = cheatSheetList;
    }

    /**
     * Loads the file contents from the data folder. If the file is a settings file,
     * this method will parse and apply the saved settings.
     * Otherwise, it converts the file contents into cheatsheets that can be added into
     * the list of cheatsheets. Handles exceptions arising from issues when
     * loading files or relevant directories.
     */
    @Override
    public void executeFunction() {
        try {
            convertedCheatSheets.clear();
            loadCheatSheetsAndSettings();
        } catch (FileNotFoundException e) {
            readLogger.log(Level.WARNING, "Missing Data Directory");
            printer.print("The following file is not found: "
                    + System.lineSeparator()
                    + e.getMessage());
            createNewDirectory();

        } catch (DirectoryIsEmptyException d) {
            readLogger.log(Level.INFO, "Empty Directory Present");
            printer.print("The following directory is empty!"
                    + System.lineSeparator()
                    + d.getMessage());

        }
    }

    /**
     * Attempts to transfer preloaded cheatsheets from CheatLogs.jar.
     * Handles exceptions arising from issues when
     * transferring these preloaded cheatsheets into the /data directory.
     */
    public void extractPreloadedCheatSheets() {
        try {
            extractXmlFilesFromJar();
        } catch (IOException e) {
            readLogger.log(Level.WARNING, "IO File Error");
            printer.print("The following file could not be written: "
                    + System.lineSeparator()
                    + e.getMessage());
        }
    }

    /**
     * Extracts the preloaded cheatsheet .xml files from CheatLogs.jar
     * and transfers them to the /data dir before executing the reader
     * operation.
     *
     * @throws IOException Thrown if there are issues with reading and
     *                     writing the transferred .xml file as well as
     *                     the files inside CheatLogs.jar.
     */
    private void extractXmlFilesFromJar() throws IOException {
        JarFile jarFile = new JarFile(jarDirectory);

        Enumeration<JarEntry> enumEntries = jarFile.entries();
        while (enumEntries.hasMoreElements()) {
            JarEntry currentFile = enumEntries.nextElement();
            String currentFilePath = currentFile.getName();
            if (!currentFilePath.contains(PRELOADED)
                    || !currentFilePath.contains(XML_EXTENSION)) {
                continue;
            }

            String currentFileDir = filterDir(currentFilePath);

            createNewFile(jarFile, currentFile, currentFilePath, currentFileDir);
        }
        jarFile.close();
    }

    /**
     * Extracts a string containing the path to the directory in
     * CheatLogs.jar containing the .xml file.
     *
     * @param currentFilePath   A string that denotes the path of the file.
     * @return                  A string that denotes the path of the file
     *                          directory.
     */
    private String filterDir(String currentFilePath) {
        String[] splitPathNames = currentFilePath.split(SLASH);
        String xmlPathName = EMPTY;

        for (String splitPathName : splitPathNames) {
            if (splitPathName.contains(XML_EXTENSION)) {
                xmlPathName = splitPathName;
            }
        }
        return currentFilePath.replace(xmlPathName, EMPTY);
    }

    /**
     * Creates a new file within the specified path in the
     * /data directory.
     *
     * @param jarFile            Jarfile which is loading CheatLogs.jar.
     * @param currentFile        JarEntry of the file being process.
     * @param currentFilePath    String denoting the path to which the
     *                           file should be written to
     * @param currentFileDirPath String denoting the path to the directory
     *                           the relevant file should be stored in.
     */
    private void createNewFile(JarFile jarFile,
                               JarEntry currentFile,
                               String currentFilePath,
                               String currentFileDirPath) throws IOException {
        //@@author theopin-reused
        //Reused from https://stackoverflow.com/questions/1529611 with minor modifications

        Path preloadedSubjectDirectory = Paths.get(USER_DIR, DATA, currentFileDirPath);
        Path preloadedFileDirectory = Paths.get(USER_DIR, DATA, currentFilePath);
        verifyDirectoryExistence(null, preloadedSubjectDirectory, true);

        File newFileLocation = new File(preloadedFileDirectory.toString());
        InputStream inputStream = jarFile.getInputStream(currentFile);
        FileOutputStream outputStream = new FileOutputStream(newFileLocation);

        while (inputStream.available() > 0) {
            outputStream.write(inputStream.read());
        }

        outputStream.close();
        inputStream.close();
    }

    /**
     * Loads the data obtained from the /data folder.
     * Parses and applies the settings to the system if the data is a settings save file,
     * Converts the data into cheatsheets and adds them to the application otherwise.
     *
     * @throws FileNotFoundException     Thrown if the /data folder is not found
     * @throws DirectoryIsEmptyException Thrown if the /data folder is empty
     */

    protected void loadCheatSheetsAndSettings() throws FileNotFoundException,
            DirectoryIsEmptyException {
        if (!Files.exists(DATA_DIR)) {
            throw new FileNotFoundException();
        }

        File dataDirectory = new File(DATA_DIR.toString());
        String[] directoryItems = dataDirectory.list();

        assert directoryItems != null;
        if (directoryItems.length == 0) {
            throw new DirectoryIsEmptyException(DATA);
        }

        extractFromDirectory(DATA_DIR);
    }

    /**
     * Extracts all cheatsheet and settings files from the given directory.
     */
    private void extractFromDirectory(Path directoryPath) {
        File[] dataDirectoryFiles = directoryPath.toFile().listFiles();
        if (dataDirectoryFiles == null) {
            return;
        }

        for (File dataDirectoryFile : dataDirectoryFiles) {
            Path filePath = dataDirectoryFile.toPath();

            if (Files.isDirectory(filePath)) {
                extractFromDirectory(filePath);
                continue;
            }
            String preloadedFileName = dataDirectoryFile
                    .getParentFile()
                    .getParentFile()
                    .getName();

            boolean isPreloadedFile = preloadedFileName.equals(PRELOADED);

            if (isPreloadedFile) {
                Path cheatSheetPath = dataDirectoryFile.toPath();
                if (preloadedCheatSheets.contains(cheatSheetPath)) {
                    continue;
                }
                preloadedCheatSheets.add(cheatSheetPath);
            }

            try {
                if (dataDirectoryFile.getName().equals("settings.txt")) {
                    loadUserSettings(dataDirectoryFile);
                } else {
                    if (!convertedCheatSheets.contains(dataDirectoryFile.getName())) {
                        extractCheatSheet(dataDirectoryFile);
                        convertedCheatSheets.add(dataDirectoryFile.getName());
                    }
                }
            } catch (IOException e) {
                readLogger.log(Level.WARNING, "IO File Error");
                printer.print("There were issues reading this file: "
                        + System.lineSeparator()
                        + e.getMessage());

            } catch (ParserConfigurationException f) {
                readLogger.log(Level.WARNING, "XML Parser Error");
                printer.print("There were issues with building the XML parser: "
                        + System.lineSeparator()
                        + f.getMessage());

            } catch (SAXException g) {
                readLogger.log(Level.WARNING, "SAX Read File Error");
                printer.print("There were issues with the usage of the XML parser: "
                        + System.lineSeparator()
                        + g.getMessage());

            } catch (InvalidParamException h) {
                readLogger.log(Level.WARNING, "XML File Param Error");
                printer.print("There were issues with the particular segment of the XML file!"
                        + System.lineSeparator()
                        + h.getMessage());
            }
        }
    }

    /**
     * Extracts the contents of the cheatsheet from the specified file.
     *
     * @param cheatSheetDocument File of the cheatSheet
     * @throws IOException                       Thrown if an I/O error prevents the file from
     *                                           being fully parsed.
     * @throws ParserConfigurationException      Thrown if a serious configuration error is caught.
     * @throws SAXException                      Thrown if a basic error or warning information
     *                                           from either the XML parser or the application is caught.
     * @throws InvalidParamException   Thrown if the xml file content section
     *                                           is not alphanumeric.
     */
    private void extractCheatSheet(File cheatSheetDocument) throws IOException,
            ParserConfigurationException,
            SAXException,
            InvalidParamException {

        Node favouriteElement = null;
        Node subjectElement = null;
        Node contentElement = null;

        DocumentBuilder documentBuilder = getDocumentBuilder();
        Document cheatSheetXml = documentBuilder.parse(cheatSheetDocument);
        cheatSheetXml.getDocumentElement().normalize();

        NodeList sectionNodes = cheatSheetXml.getDocumentElement().getChildNodes();

        int numSections = sectionNodes.getLength();
        for (int i = 0; i < numSections; i++) {
            switch (sectionNodes.item(i).getNodeName()) {
            case FAVOURITE_ELEMENT:
                favouriteElement = sectionNodes.item(i);
                break;
            case SUBJECT_ELEMENT:
                subjectElement = sectionNodes.item(i);
                break;
            case CONTENTS_ELEMENT:
                contentElement = sectionNodes.item(i);
                break;
            default:
                break;
            }
        }
        bundleCheatSheetComponents(cheatSheetDocument,
                favouriteElement,
                subjectElement,
                contentElement);
    }

    /**
     * Extracts and bundles components together to create a new cheatSheet.
     *
     * @param cheatSheetDocument                 File of the cheatSheet
     * @param favouriteElement                   Node containing the favourite status
     *                                           of the cheatsheet.
     * @param subjectElement                     Node containing the subject of the
     *                                           cheatsheet.
     * @param contentElement                     Node containing the details of the
     *                                           cheatsheet.
     * @throws InvalidParamException             Thrown if the xml file content section
     *                                           is not alphanumeric.
     */
    private void bundleCheatSheetComponents(File cheatSheetDocument,
                                            Node favouriteElement,
                                            Node subjectElement,
                                            Node contentElement)
            throws InvalidParamException {
        String cheatSheetName = cheatSheetDocument
                .getName()
                .replace(XML_EXTENSION, EMPTY);

        boolean isMarkedFavourite = extractFavouriteStatus(favouriteElement);
        String cheatSheetSubject = extractCheatSheetSection(subjectElement);
        String cheatSheetContent = extractCheatSheetSection(contentElement);

        if (!cheatSheetSubject.matches(ALPHA_NUMERIC_REGEX)) {
            throw new InvalidParamException("subject");
        } else if (!cheatSheetName.matches(ALPHA_NUMERIC_REGEX)) {
            throw new InvalidParamException("name");
        }


        createNewCheatSheet(isMarkedFavourite,
                cheatSheetName.substring(0, Math.min(cheatSheetName.length(),
                        MAX_PATH_LENGTH)),
                cheatSheetSubject.substring(0, Math.min(cheatSheetSubject.length(),
                        MAX_PATH_LENGTH)),
                cheatSheetContent);
    }

    /**
     * Extracts the favourite status of the referenced cheatSheet.
     *
     * @param favouriteElement      Node containing the favourite status of the cheatSheet.
     * @return isMarkedFavourite    Boolean indicating if the cheatSheet should be marked as
     *                              a favourite.
     */
    private boolean extractFavouriteStatus(Node favouriteElement) {
        boolean isMarkedFavourite;
        try {
            isMarkedFavourite = favouriteElement
                    .getFirstChild()
                    .getTextContent()
                    .toUpperCase()
                    .equals(YES);
        } catch (NullPointerException e) {
            isMarkedFavourite = false;
        }
        return isMarkedFavourite;
    }

    /**
     * Extracts the section of the referenced cheatSheet.
     *
     * @param relevantElement Node containing the contents of the cheatSheet.
     * @return cheatsheetContent String containing the contents of the cheatSheet.
     */
    private String extractCheatSheetSection(Node relevantElement) {
        String cheatSheetSection;
        try {
            cheatSheetSection = relevantElement
                    .getFirstChild()
                    .getTextContent();
        } catch (NullPointerException e) {
            cheatSheetSection = EMPTY;
        }
        return cheatSheetSection;
    }

    /**
     * Creates a new cheatsheet based on the parameters provided
     * and adds it to CheatSheetList.
     *
     * @param isMarkedFavourite Indicator of the favourite status of the cheatSheet.
     * @param cheatSheetName    Name of the cheatSheet.
     * @param cheatSheetContent Contents of the cheatSheet.
     */
    private void createNewCheatSheet(boolean isMarkedFavourite,
                                     String cheatSheetName,
                                     String cheatSheetSubject,
                                     String cheatSheetContent) {
        CheatSheet newCheatSheet = new CheatSheet(cheatSheetName,
                cheatSheetSubject,
                cheatSheetContent);

        newCheatSheet.setFavourite(isMarkedFavourite);
        cheatSheetList.add(newCheatSheet);
    }

    /**
     * Reads the save file line by line and passes each line to applySettings method.
     */
    private void loadUserSettings(File settingsFile) throws IOException {
        Scanner scanner = new Scanner(settingsFile);
        while (scanner.hasNext()) {
            applySettings(scanner.nextLine());
        }
    }

    /**
     * Applies the settings retrieved from the settings.txt save file.
     */
    private void applySettings(String serializedSettings) {
        String[] components = serializedSettings.split(" ");
        try {
            assert components.length == 2;
            String settingType = components[0];
            String settingValue = components[1];
            if (settingType.equals("COLOR")) {
                settings.setColor(Integer.parseInt(settingValue), true);
            } else if (settingType.equals("HELPMESSAGE")) {
                settings.setDisplayingHelpMessages(Boolean.parseBoolean(settingValue), true);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            readLogger.log(Level.WARNING, "The settings.txt file may be corrupted. "
                    + "Running program using default settings\n");
        }
    }
}
