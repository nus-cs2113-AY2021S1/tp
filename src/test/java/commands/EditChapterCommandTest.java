package commands;

import access.Access;
import exception.InvalidInputException;
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

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_ITEM_EXISTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EditChapterCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private EditChapterCommand editChapterCommand;
    private StorageStub storageStub;
    private AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113");
        storageStub.createDirectory("/CS2113/dues");
        storageStub.createFile("/CS2113/Chapter1.txt");
        storageStub.createFile("/CS2113/Chapter2.txt");
        storageStub.createFile("/CS2113/dues/Chapter1due.txt");
        storageStub.createFile("/CS2113/dues/Chapter2due.txt");

        accessStub = new AccessStub();

        ChapterList chapters = accessStub.getModule().getChapters();
        chapters.addChapter(new Chapter("Chapter1"));
        chapters.addChapter(new Chapter("Chapter2"));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113/dues/Chapter1due.txt");
        storageStub.deleteDirectory("/CS2113/dues/Chapter2due.txt");
        storageStub.deleteDirectory("/CS2113/Chapter1.txt");
        storageStub.deleteDirectory("/CS2113/Chapter2.txt");
        storageStub.deleteDirectory("/CS2113/dues");
        storageStub.deleteDirectory("/CS2113");
        System.setOut(standardOut);
    }

    @Test
    public void execute_invalidIndex_throwsInvalidInputException() {
        int index = -1;
        String chapterName = "chapter1";
        editChapterCommand = new EditChapterCommand(index, chapterName);
        assertThrows(InvalidInputException.class, () -> editChapterCommand.execute(ui, accessStub, storageStub));

        index = 2;
        editChapterCommand = new EditChapterCommand(index, chapterName);
        assertThrows(InvalidInputException.class, () -> editChapterCommand.execute(ui, accessStub, storageStub));
    }

    @Test
    public void execute_sameChapterName_editFail() throws Exception {
        int index = 0;
        String chapterName = "Chapter1";
        editChapterCommand = new EditChapterCommand(index, chapterName);
        editChapterCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditChapterCommand.MESSAGE_SAME_NAME, "Chapter1", chapterName);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_chapterExists_editFail() throws Exception {
        int index = 1;
        String chapterName = "chapter1";
        editChapterCommand = new EditChapterCommand(index, chapterName);
        editChapterCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(MESSAGE_ITEM_EXISTED, CHAPTER, chapterName, CHAPTER);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_validInput_editSuccess() throws Exception {
        int index = 0;
        String chapterName = "chapter1";
        editChapterCommand = new EditChapterCommand(index, chapterName);
        editChapterCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditCommand.MESSAGE_BEFORE_EDIT, CHAPTER)
                + "Chapter1\n"
                + String.format(EditCommand.MESSAGE_AFTER_EDIT, CHAPTER)
                + chapterName;
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin";
            this.adminLevel = "admin";
            this.moduleLevel = "CS2113";
            this.chapterLevel = "";
            this.module = new Module("CS2113");
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


