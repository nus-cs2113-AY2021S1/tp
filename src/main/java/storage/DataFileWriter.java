package storage;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import settings.Settings;
import ui.Printer;

/**
 * Allows the user to save the current settings into a file
 * and write data based on the cheatSheets currently present
 * in the list of cheatSheets into individual files.
 */
public class DataFileWriter extends DataFile {
    private ArrayList<CheatSheet> cheatSheets;


    private final Logger writeLogger = Logger.getLogger("FileWriter");

    private Settings settings;

    public DataFileWriter(Settings settings, Printer printer, CheatSheetList cheatSheetList) {
        this.settings = settings;
        this.printer = printer;
        this.cheatSheetList = cheatSheetList;
    }

    /**
     * Converts the cheatSheets present in the list of cheatSheet into
     * a string. This string is then saved in an xml file, 1 file
     * for each cheatSheet. Subsequently, it retrieves the current settings
     * and parses them into a string. This strings is then saved
     * in a txt file called settings.txt.
     */
    @Override
    public void executeFunction() {
        cheatSheets = cheatSheetList.getList();
        storeCheatSheet();
        saveSettings();
    }

    /**
     * Converts each cheatSheet present in the list of cheatSheets into
     * a file, provided it its modifiable.
     */
    private void storeCheatSheet() {
        int cheatSheetsSize = cheatSheets.size();
        if (cheatSheetsSize > 0) {
            for (CheatSheet cheatSheet : cheatSheets) {
                convertCheatSheetToFile(cheatSheet);
            }
        }
    }

    /**
     * Converts the cheatSheet into a file format.
     *
     * @param cheatSheet The cheatSheet that is currently being converted into a file.
     */
    public void convertCheatSheetToFile(CheatSheet cheatSheet) {
        String subjectName = cheatSheet.getSubject();
        String fileName = cheatSheet.getName() + XML_EXTENSION;

        Path subjectDirectory = Paths.get(USER_DIR, DATA, subjectName);
        Path possiblePreloadedFile = Paths.get(USER_DIR, DATA,
                PRELOADED, subjectName, fileName);

        Path preloadedSubjectDirectory = Paths.get(USER_DIR, DATA, PRELOADED, subjectName);
        Path textFile = Paths.get(USER_DIR, DATA, subjectName, fileName);

        try {
            initiateWriteProcess(cheatSheet,
                    subjectDirectory,
                    possiblePreloadedFile,
                    preloadedSubjectDirectory,
                    textFile);

        } catch (IOException e) {
            writeLogger.log(Level.WARNING, "IO File Error");
            printer.print("The following file could not be written: "
                    + System.lineSeparator()
                    + e.getMessage());

        } catch (ParserConfigurationException e) {
            writeLogger.log(Level.WARNING, "XML Parser Error");
            printer.print("There were issues with building the XML parser: "
                    + System.lineSeparator()
                    + e.getMessage());

        } catch (TransformerException e) {
            writeLogger.log(Level.WARNING, "XML File Transformer Error");
            printer.print("This file could not be converted into an XML file: "
                    + System.lineSeparator()
                    + e.getMessage());
        }
    }

    /**
     * Attempts to write the indicated cheatsheet into an .xml file.
     *
     * @param cheatSheet                    The cheatSheet that is currently being converted
     *                                      into a file.
     * @param possiblePreloadedFile         Path of the file assuming that it was preloaded.
     * @param preloadedSubjectDirectory     Path of the directory containing the file
     *                                      assuming that it was preloaded.
     * @param textFile                      The default path of the file.
     *
     * @throws IOException                  Thrown if there are issues with reading and
     *                                      writing the transferred .xml file
     * @throws ParserConfigurationException Thrown if a serious configuration error is
     *                                      detected.
     * @throws TransformerException         Thrown if there an exceptional condition occurs
     *                                      during the transformation process.
     *
     */
    private void initiateWriteProcess(CheatSheet cheatSheet,
                                      Path subjectDirectory,
                                      Path possiblePreloadedFile,
                                      Path preloadedSubjectDirectory,
                                      Path textFile) throws IOException,
            ParserConfigurationException,
            TransformerException {
        boolean isPreloadedFile = preloadedCheatSheets.contains(possiblePreloadedFile);
        if (isPreloadedFile) {
            textFile = possiblePreloadedFile;
            subjectDirectory = null;
        }

        verifyDirectoryExistence(subjectDirectory,
                preloadedSubjectDirectory,
                isPreloadedFile);

        if (!Files.exists(textFile)) {
            Files.createFile(textFile);
        }

        Document cheatSheetFile = buildFileContents(cheatSheet);

        writeToFile(textFile, cheatSheetFile);
        updateDirectory(DATA_DIR.toFile());
    }

    /**
     * Sets the contents for the respective cheatSheet file.
     *
     * @param cheatSheet                     The cheatSheet that is currently being converted into a file.
     * @return xmlFileStructure              A document containing relevant data of the cheatsheet
     *                                       in a .xml file format.
     * @throws ParserConfigurationException Thrown if a serious configuration error is detected.
     */
    private Document buildFileContents(CheatSheet cheatSheet) throws ParserConfigurationException {
        DocumentBuilder documentBuilder = getDocumentBuilder();
        Document xmlFileStructure = documentBuilder.newDocument();

        Element mainRoot = xmlFileStructure.createElement(MAIN_ELEMENT);
        xmlFileStructure.appendChild(mainRoot);

        insertFavouriteStatus(cheatSheet, xmlFileStructure, mainRoot);
        insertFileSubject(cheatSheet, xmlFileStructure, mainRoot);
        insertFileContents(cheatSheet, xmlFileStructure, mainRoot);

        return xmlFileStructure;
    }

    /**
     * Sets the favourite status for the respective cheatSheet file.
     *
     * @param cheatSheet                    The cheatSheet that is currently being converted into a file.
     * @param xmlFileStructure              A document containing relevant data of the cheatsheet
     *                                      in a .xml file format.
     * @param mainRoot                      The root that the created element needs to be joined to.
     */
    private void insertFavouriteStatus(CheatSheet cheatSheet,
                                       Document xmlFileStructure,
                                       Element mainRoot) {
        String favouriteStatus = cheatSheet.getIsFavourite()
                ? YES
                : NO;
        Element favouriteElement = xmlFileStructure.createElement(FAVOURITE_ELEMENT);
        appendToFileStructure(xmlFileStructure,
                mainRoot,
                favouriteStatus,
                favouriteElement);
    }

    /**
     * Sets the subject for the respective cheatSheet file.
     *
     * @param cheatSheet                    The cheatSheet that is currently being converted into a file.
     * @param xmlFileStructure              A document containing relevant data of the cheatsheet
     *                                      in a .xml file format.
     * @param mainRoot                      The root that the created element needs to be joined to.
     */
    private void insertFileSubject(CheatSheet cheatSheet,
                                   Document xmlFileStructure,
                                   Element mainRoot) {
        String fileContent = cheatSheet.getSubject();
        Element fileContentElement = xmlFileStructure.createElement(SUBJECT_ELEMENT);
        appendToFileStructure(xmlFileStructure,
                mainRoot,
                fileContent,
                fileContentElement);
    }

    /**
     * Sets the contents for the respective cheatSheet file.
     *
     * @param cheatSheet                    The cheatSheet that is currently being converted into a file.
     * @param xmlFileStructure              A document containing relevant data of the cheatsheet
     *                                      in a .xml file format.
     * @param mainRoot                      The root that the created element needs to be joined to.
     */
    private void insertFileContents(CheatSheet cheatSheet,
                                    Document xmlFileStructure,
                                    Element mainRoot) {
        String fileContent = cheatSheet.getDetails();
        Element fileContentElement = xmlFileStructure.createElement(CONTENTS_ELEMENT);
        appendToFileStructure(xmlFileStructure,
                mainRoot,
                fileContent,
                fileContentElement);
    }

    /**
     * Sets the contents for the respective cheatSheet file.
     *
     * @param xmlFileStructure   A document containing relevant data of the cheatsheet
     *                           in a .xml file format.
     * @param mainRoot           The root that the created element needs to be joined to.
     * @param fileContent        Section of the file that needs to be converted to a text node.
     * @param fileContentElement Element that needs to be appended to the main root.
     */
    private void appendToFileStructure(Document xmlFileStructure,
                                       Element mainRoot,
                                       String fileContent,
                                       Element fileContentElement) {
        Text fileContentTextNode = xmlFileStructure.createTextNode(fileContent);
        fileContentElement.appendChild(fileContentTextNode);
        mainRoot.appendChild(fileContentElement);
    }

    /**
     * Writes formatted attributes of the cheatsheet into an xml file.
     *
     * @param fileDirectory             Name of the file.
     * @param xmlFileContents           Contents of the file in xml format.
     * @throws TransformerException     Thrown if there an exceptional condition occurs
     *                                  during the transformation process.
     */
    private void writeToFile(Path fileDirectory, Document xmlFileContents)
            throws TransformerException {
        Transformer transformer = TransformerFactory
                .newInstance()
                .newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, YES);
        DOMSource fileSource = new DOMSource(xmlFileContents);
        StreamResult fileResult = new StreamResult(fileDirectory.toFile());

        transformer.transform(fileSource, fileResult);
    }

    /**
     * Gets the current settings and parses them into strings.
     */
    private void saveSettings() {
        boolean isDisplayingHelpMessages = settings.getDisplayingHelpMessages();
        int colorOption = settings.getColorOption();
        StringBuilder saveData = new StringBuilder();
        saveData.append("COLOR ").append(colorOption).append("\n");
        saveData.append("HELPMESSAGE ").append(isDisplayingHelpMessages).append("\n");
        try {
            writeToTxtFile(saveData.toString());
        } catch (IOException e) {
            printer.print(e.getMessage());
        }
    }

    /**
     * Writes the save data to data/settings.txt
     */
    private void writeToTxtFile(String saveData) throws IOException {
        FileWriter fileWriter = new FileWriter(DATA_DIR + SETTINGS_FILENAME);
        fileWriter.write(saveData);
        fileWriter.close();
    }
}
