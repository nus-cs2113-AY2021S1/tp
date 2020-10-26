package storage;

import cheatsheet.CheatSheetList;
import ui.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFileTest {

    String userDir = System.getProperty("user.dir");
    String data = "data";
    String empty = "";
    String sample = "sample";

    Path sampleTest = Paths.get(userDir, data, "sample.xml");
    Path dataDir = Paths.get(userDir, data);

    Printer printer = new Printer();
    CheatSheetList cheatSheetList = new CheatSheetList();
    DataFileWriter testWriter = new DataFileWriter(printer, cheatSheetList);
    DataFileReader testReader = new DataFileReader(printer, cheatSheetList);
    DataFileDestroyer testDestroyer = new DataFileDestroyer(printer);

    void createDataDir() {
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            printer.print(e.getMessage());

        }
    }

    void createSampleFile(Path fileName, String fileContent) {
        try {
            Files.createFile(fileName);
            Files.writeString(fileName, fileContent);
        } catch (IOException e) {
            printer.print(e.getMessage());
        }
    }

    void eraseFile(Path fileName) {
        fileName.toFile().delete();
    }

}
