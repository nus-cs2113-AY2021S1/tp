package commands;

import access.Access;
import manager.admin.Admin;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static common.Messages.MESSAGE_ITEM_EXISTED;
import static common.Messages.CHAPTER;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddChapterCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    private AddChapterCommand addChapterCommand;
    private AddChapterCommandTest.StorageStub storageStub;
    private AddChapterCommandTest.AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new AddChapterCommandTest.StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113T");
        storageStub.createFile("/CS2113T/Chapter 1.txt");

        accessStub = new AddChapterCommandTest.AccessStub();

        ChapterList chapters = accessStub.getModule().getChapters();
        chapters.addChapter(new Chapter("Chapter 1"));
    }

    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113T/Chapter 2.txt");
        storageStub.deleteDirectory("/CS2113T/Chapter 1.txt");
        storageStub.deleteDirectory("/CS2113T");
        System.setOut(standardOut);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(standardIn);
        System.setOut(standardOut);
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
    public void execute_validInput_addSuccessful() throws Exception {
        String chapterName = "Chapter 2";
        addChapterCommand = new AddChapterCommand(chapterName);
        String testInput = "N";
        provideInput(testInput);
        addChapterCommand.execute(ui, accessStub, storageStub);
        String expectedResult = "Would you like to rate this new Chapter? (Y/N)\r\n"
                + "Enter command here: "
                + String.format(addChapterCommand.MESSAGE_SUCCESS, CHAPTER)
                + chapterName + "\n"
                + String.format(addChapterCommand.MESSAGE_COUNT, 2, CHAPTER);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_duplicatedChapter_addFail() throws Exception {
        String chapterName = "Chapter 1";
        addChapterCommand = new AddChapterCommand(chapterName);
        addChapterCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(MESSAGE_ITEM_EXISTED, CHAPTER, chapterName, CHAPTER);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin";
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
