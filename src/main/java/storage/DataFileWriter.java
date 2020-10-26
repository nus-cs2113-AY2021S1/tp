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

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import ui.Printer;

/**
 * Allows the user to write data based on the cheatSheets currently present
 * in the list of cheatSheets into individual files.
 */
public class DataFileWriter extends DataFile {
    private ArrayList<CheatSheet> cheatSheets;
    private CheatSheetList cheatSheetList;

    public DataFileWriter(Printer printer, CheatSheetList cheatSheetList) {
        this.printer = printer;
        this.cheatSheetList = cheatSheetList;
    }

    /**
     * Converts the cheatSheets present in the list of cheatSheet into
     * a string. This string is then saved in an xml file, 1 file
     * for each cheatSheet.
     */
    @Override
    public void executeFunction() {
        cheatSheets = cheatSheetList.getList();
        storeCheatSheet();
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
        String fileName = cheatSheet.getName() + XML_EXTENSION;
        Path textFile = Paths.get(USER_DIR, DATA, fileName);

        try {
            if (!Files.exists(textFile)) {
                Files.createFile(textFile);
            }
            Document cheatSheetFile = buildFileContents(cheatSheet);

            writeToFile(textFile, cheatSheetFile);
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            printer.print(e.getMessage());
        }
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
    private void insertFavouriteStatus(CheatSheet cheatSheet, Document xmlFileStructure, Element mainRoot) {
        String favouriteStatus = cheatSheet.getIsFavourite()
                ? YES
                : NO;
        Element favouriteElement = xmlFileStructure.createElement(FAVOURITE_ELEMENT);
        appendToFileStructure(xmlFileStructure, mainRoot, favouriteStatus, favouriteElement);
    }

    /**
     * Sets the subject for the respective cheatSheet file.
     *
     * @param cheatSheet                    The cheatSheet that is currently being converted into a file.
     * @param xmlFileStructure              A document containing relevant data of the cheatsheet
     *                                      in a .xml file format.
     * @param mainRoot                      The root that the created element needs to be joined to.
     */
    private void insertFileSubject(CheatSheet cheatSheet, Document xmlFileStructure, Element mainRoot) {
        String fileContent = cheatSheet.getSubject();
        Element fileContentElement = xmlFileStructure.createElement(SUBJECT_ELEMENT);
        appendToFileStructure(xmlFileStructure, mainRoot, fileContent, fileContentElement);
    }

    /**
     * Sets the contents for the respective cheatSheet file.
     *
     * @param cheatSheet                    The cheatSheet that is currently being converted into a file.
     * @param xmlFileStructure              A document containing relevant data of the cheatsheet
     *                                      in a .xml file format.
     * @param mainRoot                      The root that the created element needs to be joined to.
     */
    private void insertFileContents(CheatSheet cheatSheet, Document xmlFileStructure, Element mainRoot) {
        String fileContent = convertSpecialChars(cheatSheet.getDetails());
        Element fileContentElement = xmlFileStructure.createElement(CONTENTS_ELEMENT);
        appendToFileStructure(xmlFileStructure, mainRoot, fileContent, fileContentElement);
    }

    /**
     * Replaces certain characters to conform to the xml file format.
     *
     * @param details The string that needs to be refined.
     * @return        A string with all the relevant characters replaced.
     */
    private String convertSpecialChars(String details) {
        return details.replaceAll(AMPERSAND, AMPERSAND_XML)
                .replaceAll(LESS_THAN, LESS_THAN_XML)
                .replaceAll(MORE_THAN, MORE_THAN_XML)
                .replaceAll(SINGLE_QUOTE, SINGLE_QUOTE_XML)
                .replaceAll(DOUBLE_QUOTE, DOUBLE_QUOTE_XML);
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
     * @throws TransformerException     Thrown if there an exceptional condition occurrs
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
}
