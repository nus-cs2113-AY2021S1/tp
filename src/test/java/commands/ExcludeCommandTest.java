package commands;

import access.Access;
import exception.InvalidInputException;
import manager.admin.Admin;
import manager.card.Card;
import manager.chapter.Chapter;
import manager.module.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import ui.Ui;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExcludeCommandTest {
    private String filePath = "src/test/data/admin";

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    private Ui ui;
    private Storage storage;
    private Access access;
    private File moduleDir;
    private File chapterFile;
    private File chapterFile2;
    private Card card;

    @BeforeEach
    public void init() throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        card = new Card("1+1", "2", 4, 1);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        Chapter chapter = new Chapter("chapter1");
        chapter.setCards(cards);
        ArrayList<Chapter> chapters = new ArrayList<>();
        chapters.add(chapter);
        Chapter chapter2 = new Chapter("chapter2");
        chapters.add(chapter2);
        Module module = new Module("CS2113T");
        module.setChapters(chapters);
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(module);
        Admin admin = new Admin(modules);
        access = new Access();
        access.setAdmin(admin);
        access.setModuleLevel("CS2113T");
        access.setModule(module);
        access.setIsModuleLevel();
        moduleDir = new File(filePath + "/CS2113T");
        chapterFile = new File(filePath + "/CS2113T/chapter1.txt");
        chapterFile2 = new File(filePath + "/CS2113T/chapter2.txt");
        moduleDir.mkdir();
        chapterFile.createNewFile();
        chapterFile2.createNewFile();
        FileWriter fw = new FileWriter(filePath + "/CS2113T/chapter1.txt");
        fw.write("[Q] 1+1 | [A] 2 | [P] 4 | [R] 1");
        fw.close();
    }

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @AfterEach
    public void end() {
        File dueChapterFile = new File(filePath + "/CS2113T/dues/chapter1due.txt");
        if (dueChapterFile.exists()) {
            dueChapterFile.delete();
        }
        File due = new File(filePath + "/CS2113T/dues");
        if (due.exists()) {
            due.delete();
        }
        if (chapterFile.exists()) {
            chapterFile.delete();
        }
        if (chapterFile2.exists()) {
            chapterFile2.delete();
        }
        if (moduleDir.exists()) {
            moduleDir.delete();
        }
    }

    private String getExpected(String expectedResult) {
        String os = System.getProperty("os.name").toLowerCase();
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.print(expectedResult);
        printWriter.close();
        String expected = expectedStringWriter.toString();
        if (!(os.contains("win"))) {
            expected = expected.replaceAll("\\r\\n", "\n");
        }
        return expected;
    }

    @Test
    void execute_invalidCommandMode_expectException() {
        ExcludeCommand command = new ExcludeCommand("test");
        assertThrows(InvalidInputException.class,() -> command.execute(ui, access, storage));
    }

    @Test
    void execute_validCommandModeModuleInputInvalidModuleName_expectException() {
        ExcludeCommand command = new ExcludeCommand("module");
        String testInput = "test\n";
        provideInput(testInput);
        assertThrows(InvalidInputException.class,() -> command.execute(ui, access, storage));
    }

    @Test
    void execute_validCommandModeChapterInputInvalidChapterName_expectException() {
        ExcludeCommand command = new ExcludeCommand("module");
        String testInput = "test\ntest\n";
        provideInput(testInput);
        assertThrows(InvalidInputException.class,() -> command.execute(ui, access, storage));
    }


}