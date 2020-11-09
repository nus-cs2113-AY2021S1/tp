package commands;

import access.Access;
import exception.InvalidInputException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EditCardCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private EditCardCommand editCardCommand;
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
        cards.addCard(new Card("2+2", "4"));
    }

    @AfterEach
    public void cleanUp() {
        storageStub.deleteDirectory("/CS2113/Chapter1.txt");
        storageStub.deleteDirectory("/CS2113");
        System.setOut(standardOut);
    }

    @Test
    public void execute_invalidIndex_throwsInvalidInputException() {
        int index = -1;
        String question = "1+1";
        String answer = "";
        editCardCommand = new EditCardCommand(index, question, answer);
        assertThrows(InvalidInputException.class, () -> editCardCommand.execute(ui, accessStub, storageStub));

        index = 3;
        editCardCommand = new EditCardCommand(index, question, answer);
        assertThrows(InvalidInputException.class, () -> editCardCommand.execute(ui, accessStub, storageStub));
    }

    @Test
    public void execute_sameQuestionAnswer_editFail() throws Exception {
        int index = 0;
        String question = "one plus one";
        String answer = "two";
        editCardCommand = new EditCardCommand(index, question, answer);
        editCardCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditCardCommand.MESSAGE_SAME_CONTENT,
                "[Q] one plus one | [A] two", question, answer);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_sameQuestion_editFail() throws Exception {
        int index = 0;
        String question = "one plus one";
        String answer = "";
        editCardCommand = new EditCardCommand(index, question, answer);
        editCardCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditCardCommand.MESSAGE_SAME_QUESTION,
                "[Q] one plus one | [A] two", question);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_sameAnswer_editFail() throws Exception {
        int index = 0;
        String question = "";
        String answer = "two";
        editCardCommand = new EditCardCommand(index, question, answer);
        editCardCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditCardCommand.MESSAGE_SAME_ANSWER,
                "[Q] one plus one | [A] two", answer);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_cardExists_editFail() throws Exception {
        int index = 1;
        String question = "2+2";
        String answer = "";
        editCardCommand = new EditCardCommand(index, question, answer);
        editCardCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(MESSAGE_ITEM_EXISTED, CARD, "[Q] " + question + " [A] 4", CARD);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_validInput_editSuccess() throws Exception {
        int index = 0;
        String question = "";
        String answer = "2";
        editCardCommand = new EditCardCommand(index, question, answer);
        editCardCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(EditCommand.MESSAGE_BEFORE_EDIT, CARD)
                + "[Q] one plus one | [A] two\n"
                + String.format(EditCommand.MESSAGE_AFTER_EDIT, CARD)
                + "[Q] one plus one | [A] 2";
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


