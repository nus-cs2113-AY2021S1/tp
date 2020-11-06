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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RescheduleCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private RescheduleCommand rescheduleCommand;
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
        chapters.addChapter(new Chapter("Chapter1", LocalDate.now().plusDays(1)));
        chapters.addChapter(new Chapter("Chapter2", LocalDate.now().plusDays(2)));

        storageStub.writeToFile("/CS2113/dues/Chapter1due.txt", LocalDate.now().plusDays(1).toString());
        storageStub.writeToFile("/CS2113/dues/Chapter2due.txt", LocalDate.now().plusDays(2).toString());
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113");
        System.setOut(standardOut);
    }

    @Test
    public void execute_invalidIndex_throwsInvalidInputException() {
        int index = -1;
        LocalDate date = LocalDate.now().plusDays(1);
        rescheduleCommand = new RescheduleCommand(index, date);
        assertThrows(InvalidInputException.class, () -> rescheduleCommand.execute(ui, accessStub, storageStub));

        index = 2;
        rescheduleCommand = new RescheduleCommand(index, date);
        assertThrows(InvalidInputException.class, () -> rescheduleCommand.execute(ui, accessStub, storageStub));
    }

    @Test
    public void execute_sameDate_rescheduleFail() throws Exception {
        int index = 0;
        LocalDate date = LocalDate.now().plusDays(1);
        rescheduleCommand = new RescheduleCommand(index, date);
        rescheduleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(RescheduleCommand.MESSAGE_SAME_DATE, "Chapter1", date);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_validInput_rescheduleFail() throws Exception {
        int index = 1;
        LocalDate date = LocalDate.now().plusDays(1);
        rescheduleCommand = new RescheduleCommand(index, date);
        rescheduleCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(RescheduleCommand.MESSAGE_BEFORE_RESCHEDULE, "Chapter2",
                LocalDate.now().plusDays(2))
                + String.format(RescheduleCommand.MESSAGE_AFTER_RESCHEDULE, date);
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
            boolean dirExists = f.exists();
            if (!dirExists) {
                f.mkdir();
            }
        }

        public void createFile(String path) throws IOException {
            File f = new File(filePath + path);
            boolean fileExists = f.exists();
            if (!fileExists) {
                f.createNewFile();
            }
        }

        public void deleteDirectory(String path) {
            File directory = new File(filePath + path);
            File[] allContents = directory.listFiles();
            if (allContents != null) {
                for (File file : allContents) {
                    deleteDirectory(file);
                }
            }
            directory.delete();
        }

        public void writeToFile(String path, String dueBy) throws IOException {
            FileWriter fw = new FileWriter(filePath + path);
            fw.write(dueBy);
            fw.close();
        }
    }

}


