package bookmark.commands;

import bookmark.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddLinkCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();

    @Test
    void executeCommand_addValidLinkCommand_addLinkCorrectly() {
        categories.add(new NusCategory());
        categories.add(new ZoomCategory());
        String inputString = "add huhuhuhu";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(categories.get(categoryNumber - 1).getLinks().size(), 1);
    }

}