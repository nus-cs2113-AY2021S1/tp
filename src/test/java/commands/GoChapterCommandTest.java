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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static common.Messages.MESSAGE_ITEM_EXISTED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoChapterCommandTest {
    public static final String MESSAGE_SUCCESS_EMPTY_CHAPTER = "This is a new chapter, "
            + "you can try to add flashcards inside!";
    public static final String MESSAGE_INVALID_INDEX = "The chapter index needs to be "
            + "within the range of the total number of chapters.";

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private GoChapterCommand goChapterCommand;
    private GoChapterCommandTest.StorageStub storageStub;
    private GoChapterCommandTest.AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new GoChapterCommandTest.StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113T");
        storageStub.createFile("/CS2113T/Chapter 1.txt");

        accessStub = new GoChapterCommandTest.AccessStub();

        ChapterList chapters = accessStub.getModule().getChapters();
        chapters.addChapter(new Chapter("Chapter 1"));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113T/Chapter 1.txt");
        System.setOut(standardOut);
    }

    @Test
    public void execute_validInput_goSuccessful() throws Exception {
        int chapterIndex = 0;
        goChapterCommand = new GoChapterCommand(chapterIndex);
        goChapterCommand.execute(ui, accessStub, storageStub);
        String expectedResult = MESSAGE_SUCCESS_EMPTY_CHAPTER;
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_outOfBoundIndex_goFail() throws Exception {
        int chapterIndex = 5;
        goChapterCommand = new GoChapterCommand(chapterIndex);
        goChapterCommand.execute(ui, accessStub, storageStub);
        String expectedResult = MESSAGE_INVALID_INDEX;
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

        public void deleteDirectory(String path) {
            File directory = new File(filePath + path);
            directory.delete();
        }

        public void createFile(String path) throws IOException {
            File f = new File(filePath + path);
            f.createNewFile();
        }
    }
}
