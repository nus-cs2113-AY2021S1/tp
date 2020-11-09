package commands;

import access.Access;
import manager.admin.Admin;
import manager.chapter.Chapter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackModuleCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private BackModuleCommand backModuleCommand;
    private BackModuleCommandTest.StorageStub storageStub;
    private BackModuleCommandTest.AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new BackModuleCommandTest.StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113T");
        storageStub.createFile("/CS2113T/Chapter 1.txt");

        accessStub = new BackModuleCommandTest.AccessStub();
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113T/Chapter 1.txt");
        storageStub.deleteDirectory("/CS2113T");
        System.setOut(standardOut);
    }

    @Test
    public void execute_validInput_backSuccessful() throws Exception {
        backModuleCommand = new BackModuleCommand();
        backModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = "";
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin/CS2113T/Chapter 1";
            this.adminLevel = "admin";
            this.moduleLevel = "CS2113T";
            this.chapterLevel = "Chapter 1";
            this.module = new Module("CS2113T");
            this.chapter = new Chapter("Chapter 1");
            this.admin = new Admin();
            this.isModuleLevel = false;
            this.isModuleLevel = true;
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
