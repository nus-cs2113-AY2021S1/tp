package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StarCommandTest {

    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkStorage storage = new BookmarkStorage("data/bookmark.txt");


    @Test
    void executeCommand_validStarCommand_markLinkAsStar() {
        setUp();
        int categoryNumber = 1;
        String inputString = "star 1";
        StarCommand command = new StarCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories,storage);
        assertTrue(categories.get(0).getLinks().get(0).getStar());
    }

    @Test
    void executeCommand_InvalidStarCommand_doesNotMarkLinkAsStar() {
        setUp();
        int categoryNumber = 1;
        String inputString = "star 1000";
        StarCommand command = new StarCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories,storage);
        assertFalse(categories.get(0).getLinks().get(0).getStar());
    }

    @Test
    void executeCommand_EmptyStarCommand_doesNotMarkLinkAsStar() {
        setUp();
        int categoryNumber = 1;
        String inputString = "star ";
        StarCommand command = new StarCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories,storage);
        assertFalse(categories.get(0).getLinks().get(0).getStar());
    }

    @Test
    void executeCommand_notANumberStarCommand_doesNotMarkLinkAsStar() {
        setUp();
        int categoryNumber = 1;
        String inputString = "star adhuhu";
        StarCommand command = new StarCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories,storage);
        assertFalse(categories.get(0).getLinks().get(0).getStar());
    }

    @Test
    void executeCommand_categoryNotChosenStarCommand_doesNotMarkLinkAsStar() {
        setUp();
        int categoryNumber = 0;
        String inputString = "star 1";
        StarCommand command = new StarCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories,storage);
        assertFalse(categories.get(0).getLinks().get(0).getStar());
    }

    void setUp() {
        categories.add(new BookmarkCategory("NUS"));
        categories.get(0).addLink("https://huhu.com", null);
    }
}