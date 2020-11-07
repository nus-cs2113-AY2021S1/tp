package commands;

import access.Access;
import manager.admin.Admin;
import manager.module.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import storage.Storage;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static commands.RemoveCommand.MESSAGE_COUNT;
import static commands.RemoveCommand.MESSAGE_SUCCESS;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveModuleCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Ui ui;
    private Storage storage;
    private Access access;
    private File moduleDir;
    private File chapterFile;

    @BeforeEach
    void init() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));
        ui = new Ui();
        String filePath = "src/test/data/admin";
        storage = new Storage(filePath);
        ArrayList<Module> modules = new ArrayList<>();
        Module module = new Module("CS2113T");
        modules.add(module);
        Admin admin = new Admin(modules);
        access = new Access();
        access.setAdmin(admin);
        moduleDir = new File(filePath + "/CS2113T");
        chapterFile = new File(filePath + "/CS2113T/chapter1.txt");
        moduleDir.mkdir();
        chapterFile.createNewFile();
    }

    @AfterEach
    void end() {
        System.setOut(standardOut);
        if (chapterFile.exists()) {
            chapterFile.delete();
        }
        if (moduleDir.exists()) {
            moduleDir.delete();
        }
    }

    @Test
    public void execute_invalidIndex_expectException() throws IOException {
        RemoveModuleCommand command = new RemoveModuleCommand(1);
        command.execute(ui, access, storage);
        String expectedResult = String.format(MESSAGE_INVALID_INDEX_RANGE, MODULE).trim();
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_validIndex_expectSuccess() throws IOException {
        RemoveModuleCommand command = new RemoveModuleCommand(0);
        command.execute(ui, access, storage);
        String expectedResult = String.format(MESSAGE_SUCCESS, MODULE) + "CS2113T" + "\n"
                + String.format(MESSAGE_COUNT, 0, MODULE);
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }
}
