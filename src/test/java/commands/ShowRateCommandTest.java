package commands;

import access.Access;
import manager.admin.Admin;
import manager.card.Card;
import manager.chapter.CardList;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowRateCommandTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private ShowRateCommand showRateCommand;
    private ShowRateCommandTest.StorageStub storageStub;
    private ShowRateCommandTest.AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new ShowRateCommandTest.StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113T");
        storageStub.createFile("/CS2113T/Chapter 1.txt");

        accessStub = new ShowRateCommandTest.AccessStub();

        CardList cards = accessStub.getChapter().getCards();
        cards.addCard(new Card("1 + 1", "2", 2, 2));
        cards.addCard(new Card("2 * 2", "4", 1, 3));
        cards.addCard(new Card("2 * 1.5", "5", 5, 1));
        cards.addCard(new Card("2 * 3", "6", 2, 0));
        cards.addCard(new Card("2 * 4", "8", 5, 1));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113T/Chapter 1.txt");
        storageStub.deleteDirectory("/CS2113T");
        System.setOut(standardOut);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }

    private String getOutput() {
        String os = System.getProperty("os.name").toLowerCase();
        String expected = outputStreamCaptor.toString();
        if (!(os.contains("win"))) {
            expected = expected.replaceAll("\\r\\n", "\n");
        }
        return expected;
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
    public void execute_validInput_showSuccessful() throws Exception {
        showRateCommand = new ShowRateCommand();
        showRateCommand.execute(ui, accessStub, storageStub);

        String expectedResult = "\nCard count: " + "5" + "\r\n"
                + String.format(ShowRateCommand.MESSAGE_SHOW_PERCENTAGE_PROMPT, ShowRateCommand.EASY, 0.40) + "\r\n"
                + String.format(ShowRateCommand.MESSAGE_SHOW_PERCENTAGE_PROMPT, ShowRateCommand.MEDIUM, 0.20) + "\r\n"
                + String.format(ShowRateCommand.MESSAGE_SHOW_PERCENTAGE_PROMPT, ShowRateCommand.HARD, 0.20) + "\r\n"
                + String.format(ShowRateCommand.MESSAGE_SHOW_PERCENTAGE_PROMPT, ShowRateCommand.CANNOT_ANSWER, 0.20);
        String expected = getExpected(expectedResult);
        assertEquals(expected.trim(), getOutput().trim());
    }

    @Test
    public void execute_noCardInChapter_goFail() throws Exception {
        Chapter chapter = new Chapter("Chapter 1");
        showRateCommand = new ShowRateCommand();
        accessStub.setChapter(chapter);
        showRateCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(ShowRateCommand.MESSAGE_NO_CARDS_IN_CHAPTER, chapter);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin/CS2113T/Chapter 1";
            this.adminLevel = "admin";
            this.moduleLevel = "CS2113T";
            this.chapterLevel = "Chapter 1";
            this.module = new Module("CS2113T");
            this.chapter = new Chapter("Chapter 1");
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
