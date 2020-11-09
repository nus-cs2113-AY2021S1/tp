package commands;

import access.Access;
import exception.InvalidInputException;
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
import java.io.PrintStream;

import static common.Messages.MESSAGE_ITEM_EXISTED;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EditModuleCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private EditModuleCommand editModuleCommand;
    private StorageStub storageStub;
    private AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113");
        storageStub.createDirectory("/CS2101");

        accessStub = new AccessStub();

        ModuleList modules = accessStub.getAdmin().getModules();
        modules.addModule(new Module("CS2113"));
        modules.addModule(new Module("CS2101"));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113");
        storageStub.deleteDirectory("/CS2101");
        System.setOut(standardOut);
    }

    @Test
    public void execute_invalidIndex_throwsInvalidInputException() {
        int index = -1;
        String moduleName = "cs2113";
        editModuleCommand = new EditModuleCommand(index, moduleName);
        assertThrows(InvalidInputException.class, () -> editModuleCommand.execute(ui, accessStub, storageStub));

        index = 2;
        editModuleCommand = new EditModuleCommand(index, moduleName);
        assertThrows(InvalidInputException.class, () -> editModuleCommand.execute(ui, accessStub, storageStub));
    }

    @Test
    public void execute_sameModuleName_editFail() throws Exception {
        int index = 0;
        String moduleName = "CS2113";
        editModuleCommand = new EditModuleCommand(index, moduleName);
        editModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditModuleCommand.MESSAGE_SAME_NAME, "CS2113", moduleName);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_moduleExists_editFail() throws Exception {
        int index = 1;
        String moduleName = "cs2113";
        editModuleCommand = new EditModuleCommand(index, moduleName);
        editModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(MESSAGE_ITEM_EXISTED, MODULE, moduleName, MODULE);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_validInput_editSuccess() throws Exception {
        int index = 0;
        String moduleName = "cs2113";
        editModuleCommand = new EditModuleCommand(index, moduleName);
        editModuleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditCommand.MESSAGE_BEFORE_EDIT, MODULE)
                + "CS2113\n"
                + String.format(EditCommand.MESSAGE_AFTER_EDIT, MODULE)
                + moduleName;
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


