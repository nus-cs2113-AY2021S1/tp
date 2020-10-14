package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import bookmark.NusCategory;
import bookmark.ZoomCategory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveLinkCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();

    @Test
    void executeCommand_removeValidLinkCommand_removeLinkCorrectly() {
        categories.add(new NusCategory());
        categories.add(new ZoomCategory());
        String addLink = "add http://huhuhu";
        String inputString = "rm 1";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(addLink,categoryNumber);
        command.executeCommand(ui,categories);
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories);
        assertEquals(categories.get(categoryNumber - 1).getLinks().size(),0);
    }
}