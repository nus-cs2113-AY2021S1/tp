package commands;

import access.Access;
import manager.admin.Admin;
import manager.admin.ModuleList;
import manager.card.Card;
import manager.chapter.CardList;
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

import static common.Messages.MODULE;
import static common.Messages.MESSAGE_ITEM_EXISTED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddModuleCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private AddModuleCommand addModuleCommand;
    private AddModuleCommandTest.StorageStub storageStub;
    private AddModuleCommandTest.AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new AddModuleCommandTest.StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113T");

        accessStub = new AddModuleCommandTest.AccessStub();

        ModuleList modules = accessStub.getAdmin().getModules();
        modules.addModule(new Module("CS2113T"));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113T");
        storageStub.deleteDirectory("/CS2101");
        System.setOut(standardOut);
    }

    @Test
    public void execute_validInput_addSuccessful() throws Exception {
        String moduleName = "CS2101";
        addModuleCommand = new AddModuleCommand(moduleName);
        addModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(addModuleCommand.MESSAGE_SUCCESS, MODULE)
                + moduleName + "\n"
                + String.format(addModuleCommand.MESSAGE_COUNT, 2, MODULE);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_duplicatedModule_addFail() throws Exception {
        String moduleName = "CS2113T";
        addModuleCommand = new AddModuleCommand(moduleName);
        addModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(MESSAGE_ITEM_EXISTED, MODULE, moduleName, MODULE);
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
