package commands;

import access.Access;
import manager.admin.Admin;
import manager.admin.ModuleList;
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

import static common.Messages.MESSAGE_ITEM_EXISTED;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackAdminCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private BackAdminCommand backAdminCommand;
    private BackAdminCommandTest.StorageStub storageStub;
    private BackAdminCommandTest.AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new BackAdminCommandTest.StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113T");

        accessStub = new BackAdminCommandTest.AccessStub();
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113T");
        System.setOut(standardOut);
    }

    @Test
    public void execute_validInput_backSuccessful() throws Exception {
        backAdminCommand = new BackAdminCommand();
        backAdminCommand.execute(ui, accessStub, storageStub);
        String expectedResult = "";
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin/CS2113T";
            this.adminLevel = "admin";
            this.moduleLevel = "CS2113T";
            this.chapterLevel = "";
            this.module = new Module("CS2113T");
            this.chapter = null;
            this.admin = new Admin();
            this.isAdminLevel = false;
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

        public void deleteDirectory(String path) {
            File directory = new File(filePath + path);
            directory.delete();
        }
    }
}
