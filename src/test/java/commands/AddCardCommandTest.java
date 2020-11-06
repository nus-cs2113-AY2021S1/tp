package commands;

import access.Access;
import manager.admin.Admin;
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

import static common.Messages.CARD;
import static common.Messages.MESSAGE_ITEM_EXISTED;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddCardCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private AddCardCommand addCardCommand;
    private StorageStub storageStub;
    private AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();

        storageStub = new StorageStub("src/test/data/admin");
        storageStub.createDirectory("/CS2113");
        storageStub.createFile("/CS2113/Chapter1.txt");

        accessStub = new AccessStub();

        CardList cards = accessStub.getChapter().getCards();
        cards.addCard(new Card("one plus one", "two"));
        cards.addCard(new Card("2*2", "4"));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113/Chapter1.txt");
        storageStub.deleteDirectory("/CS2113");
        System.setOut(standardOut);
    }

    @Test
    public void execute_validInput_addSuccessful() throws Exception {
        String question = "1+1";
        String answer = "2";
        addCardCommand = new AddCardCommand(question, answer);
        addCardCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(AddCommand.MESSAGE_SUCCESS, CARD)
                + "[Q] " + question + " | [A] " + answer + "\n"
                + String.format(AddCommand.MESSAGE_COUNT, 3, CARD);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_duplicatedCard_addFail() throws Exception {
        String question = "One plus one";
        String answer = "Two";
        addCardCommand = new AddCardCommand(question, answer);
        addCardCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(MESSAGE_ITEM_EXISTED, CARD, "[Q] " + question + " [A] " + answer, CARD);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    public class AccessStub extends Access {
        public AccessStub() {
            this.level = "admin";
            this.adminLevel = "admin";
            this.moduleLevel = "CS2113";
            this.chapterLevel = "Chapter1";
            this.module = new Module("CS2113");
            this.chapter = new Chapter("Chapter1");
            this.admin = new Admin();
            this.isAdminLevel = false;
            this.isModuleLevel = false;
            this.isChapterLevel = true;
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


