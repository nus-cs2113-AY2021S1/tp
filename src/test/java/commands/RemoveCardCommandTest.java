package commands;

import access.Access;
import manager.admin.Admin;
import manager.card.Card;
import manager.chapter.Chapter;
import manager.module.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import storage.Storage;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static commands.RemoveCommand.MESSAGE_COUNT;
import static commands.RemoveCommand.MESSAGE_SUCCESS;
import static common.Messages.CARD;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveCardCommandTest {
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
        ArrayList<Chapter> chapters = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        Chapter chapter = new Chapter("chapter1");
        Card card = new Card("1+1", "2", 4, 1);
        cards.add(card);
        chapter.setCards(cards);
        chapters.add(chapter);
        Module module = new Module("CS2113T");
        module.setChapters(chapters);
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(module);
        Admin admin = new Admin(modules);
        access = new Access();
        access.setAdmin(admin);
        access.setModule(module);
        access.setChapter(chapter);
        access.setIsChapterLevel();
        moduleDir = new File(filePath + "/CS2113T");
        chapterFile = new File(filePath + "/CS2113T/chapter1.txt");
        moduleDir.mkdir();
        chapterFile.createNewFile();
        FileWriter fw = new FileWriter(filePath + "/CS2113T/chapter1.txt");
        fw.write("[Q] 1+1 | [A] 2 | [P] 4 | [R] 1");
        fw.close();
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
        RemoveCardCommand command = new RemoveCardCommand(1);
        command.execute(ui, access, storage);
        String expectedResult = String.format(MESSAGE_INVALID_INDEX_RANGE, CARD).trim();
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_validIndex_expectSuccess() throws IOException {
        RemoveCardCommand command = new RemoveCardCommand(0);
        command.execute(ui, access, storage);
        String expectedResult = String.format(MESSAGE_SUCCESS, CARD) + "[Q] 1+1 | [A] 2" + "\n"
                + String.format(MESSAGE_COUNT, 0, CARD);
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }
}
