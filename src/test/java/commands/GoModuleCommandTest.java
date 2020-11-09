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

public class GoModuleCommandTest {
    public static final String MESSAGE_INVALID_INDEX = "The module index needs to "
            + "be within the range of the total number of modules.";
    public static final String MESSAGE_SUCCESS_EMPTY_MODULE = "This is a new module, "
            + "you can try to add chapters inside!";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private GoModuleCommand goModuleCommand;
    private GoModuleCommandTest.StorageStub storageStub;
    private GoModuleCommandTest.AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new GoModuleCommandTest.StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113T");

        accessStub = new GoModuleCommandTest.AccessStub();

        ModuleList modules = accessStub.getAdmin().getModules();
        modules.addModule(new Module("CS2113T"));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113T");
        System.setOut(standardOut);
    }

    @Test
    public void execute_validInput_goSuccessful() throws Exception {
        int moduleIndex = 0;
        goModuleCommand = new GoModuleCommand(moduleIndex);
        goModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = MESSAGE_SUCCESS_EMPTY_MODULE;
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_outOfBoundIndex_goFail() throws Exception {
        int moduleIndex = 5;
        goModuleCommand = new GoModuleCommand(moduleIndex);
        goModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = MESSAGE_INVALID_INDEX;
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin";
            this.adminLevel = "admin";
            this.moduleLevel = "";
            this.chapterLevel = "";
            this.module = null;
            this.chapter = null;
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

        public void deleteDirectory(String path) {
            File directory = new File(filePath + path);
            directory.delete();
        }
    }
}
