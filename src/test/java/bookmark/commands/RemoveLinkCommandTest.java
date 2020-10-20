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
        setUpBookmark();
        String inputString = "rm 1";
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories);
        assertEquals(0,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    void executeCommand_removeInValidCategoryCommand_doseNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm 10000"; //rm 0
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories);
        assertEquals(1,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    void executeCommand_removeEmptyCategoryCommand_doesNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm ";
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories);
        assertEquals(1,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    void executeCommand_removeNotANumber_doesNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm abcdef";
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories);
        assertEquals(1,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    public void executeCommand_categoryNotChosen_doesNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm 1";
        int categoryNumber = 0;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories);
        assertEquals(0,categories.get(categoryNumber).getLinks().size());
        assertEquals(1,categories.get(categoryNumber + 1).getLinks().size());
    }

    private void setUpBookmark() {
        categories.add(new NusCategory());
        categories.add(new ZoomCategory());
        String addLink = "add https://huhuhu.com";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(addLink,categoryNumber);
        command.executeCommand(ui,categories);
    }

}