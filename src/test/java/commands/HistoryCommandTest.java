package commands;

import access.Access;
import exception.InvalidInputException;
import manager.admin.Admin;
import manager.chapter.Chapter;
import manager.history.History;
import manager.module.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HistoryCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private HistoryCommand historyCommand;
    private StorageStub storageStub;
    private AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();
        storageStub = new StorageStub("src/test/data");
        storageStub.createDirectory("/history");
        storageStub.createFile("/history/2020-11-01.txt");

        accessStub = new AccessStub();
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/history/2020-11-01.txt");
        storageStub.deleteDirectory("/history");
        System.setOut(standardOut);
    }

    @Test
    public void execute_invalidDate_throwsInvalidInputException() {
        String date = "11-15-2020";
        historyCommand = new HistoryCommand(date);
        assertThrows(InvalidInputException.class, () -> historyCommand.execute(ui, accessStub, storageStub));

        date = "15-11-2020";
        historyCommand = new HistoryCommand(date);
        assertThrows(InvalidInputException.class, () -> historyCommand.execute(ui, accessStub, storageStub));
    }

    @Test
    public void execute_noHistory_noHistoryMessage() {
        String date = "2020-11-01";
        historyCommand = new HistoryCommand(date);
        historyCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(HistoryCommand.MESSAGE_DOES_NOT_EXIST);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_withHistory_listHistoryMessage() {
        String date = "2020-11-01";
        ArrayList<History> histories = new ArrayList<>();
        histories.add(new History("CS2113", "chapter 1"));
        histories.add(new History("CS2101", "chapter 2"));

        historyCommand = new HistoryCommand(date);
        historyCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(HistoryCommand.MESSAGE_EXIST)
                + "\n1.CS2113/chapter 1"
                + "\n2.CS2101/chapter 2";
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin";
            this.adminLevel = "admin";
            this.moduleLevel = "";
            this.chapterLevel = "";
            this.module = new Module("");
            this.chapter = new Chapter("");
            this.admin = new Admin();
            this.isAdminLevel = true;
            this.isModuleLevel = false;
            this.isChapterLevel = false;
        }
    }

    public class StorageStub extends Storage {
        public StorageStub(String filePath) {
            super(filePath);
        }

        public void createDirectory(String path) {
            File f = new File(filePath + path);
            f.mkdir();
        }

        public void createFile(String path) throws IOException {
            File f = new File(filePath + path);
            f.createNewFile();
        }

        public void deleteDirectory(String path) {
            File directory = new File(filePath + path);
            directory.delete();
        }
    }
}
