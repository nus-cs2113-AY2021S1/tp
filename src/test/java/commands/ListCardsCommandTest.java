package commands;

import access.Access;
import manager.admin.Admin;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static common.Messages.CARD;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ListCardsCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private ListCardsCommand listCardsCommand;
    private StorageStub storageStub;
    private AccessStub accessStub;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui = new Ui();
        storageStub = new StorageStub("src/test/data/admin");
        accessStub = new AccessStub();
    }

    @Test
    public void execute_noCards_noCardsMessage() {
        listCardsCommand = new ListCardsCommand();
        listCardsCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(ListCommand.MESSAGE_DOES_NOT_EXIST, CARD);
        assertEquals(expectedResult.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_withCards_listCardsMessage() {
        CardList cards = accessStub.getChapter().getCards();
        cards.addCard(new Card("one plus one", "two"));
        cards.addCard(new Card("2*2", "4"));

        listCardsCommand = new ListCardsCommand();
        listCardsCommand.execute(ui, accessStub, storageStub);
        String expectedResult = String.format(ListCommand.MESSAGE_EXIST, CARD)
                + "\n1.[Q] one plus one | [A] two"
                + "\n2.[Q] 2*2 | [A] 4";
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
    }

}


