package storage;

import cheatsheet.CheatSheetList;
import settings.Settings;
import ui.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFileTest {

    String userDir = System.getProperty("user.dir");
    String data = "data";
    String temp = "temp";
    String test = "Test";
    String empty = "";
    String sample = "sample";
    String sampleJarFile = Paths.get("src", "test", "java", "storage", "Test.jar")
            .toString();

    Path settingsPath = Paths.get(userDir, data, "settings.txt");
    Path sampleTestDir = Paths.get(userDir, data, test);
    Path dataDir = Paths.get(userDir, data);
    Path tempDir = Paths.get(userDir, temp);
    Path tempDataDir = Paths.get(userDir, temp, data);


    Printer printer = new Printer();
    Settings settings = new Settings(printer);
    CheatSheetList testCheatSheetList = new CheatSheetList();
    DataFileReader testReader = new DataFileReader(settings, printer, testCheatSheetList);
    DataFileWriter testWriter = new DataFileWriter(settings, printer, testCheatSheetList);
    DataFileDestroyer testDestroyer = new DataFileDestroyer(printer, testCheatSheetList);

    void createDirectory(Path directoryPath) {
        try {
            Files.createDirectories(directoryPath);
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

    void shiftExistingDataFiles() {
        createDirectory(tempDir);
        try {
            Files.move(dataDir, tempDataDir);
        } catch (IOException e) {
            printer.print(e.getMessage());
        }
    }

    void restoreDataDir(boolean isDataDirOriginallyExistent) {
        eraseFile(settingsPath);
        eraseFile(dataDir);

        if (isDataDirOriginallyExistent) {
            try {
                Files.move(tempDataDir, dataDir);
                eraseFile(tempDir);
            } catch (IOException e) {
                printer.print(e.getMessage());
            }
        }
    }

    boolean checkDataDirectoryExistence() {
        boolean isDataDirectoryExistent = Files.exists(dataDir);
        if (isDataDirectoryExistent) {
            shiftExistingDataFiles();
        }
        createDirectory(dataDir);
        return isDataDirectoryExistent;
    }
}
