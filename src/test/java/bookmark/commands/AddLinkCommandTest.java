package bookmark.commands;

import bookmark.BookmarkUi;
import bookmark.BookmarkCategory;
import bookmark.NusCategory;
import bookmark.ZoomCategory;


import exceptions.InvalidBookmarkLinkException;
import exceptions.InvalidEmptyLinkException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddLinkCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();

    @Test
    public void executeCommand_addValidLinkCommand_addLinkCorrectly() {
        categories.add(new NusCategory());
        categories.add(new ZoomCategory());
        String inputString = "add https://facebook.com";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(categories.get(categoryNumber - 1).getLinks().size(), 1);
    }

    //public void executeCommand_InValidLinkCommand_expectExceptions() {
    //    categories.add(new NusCategory());
    //    categories.add(new ZoomCategory());
    //    String inputString = "add huhuhuh";
    //    int categoryNumber = 2;
    //    AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
    //    assertThrows(InvalidBookmarkLinkException.class, () -> {
    //        command.executeCommand(ui,categories);
    //    });
    //}

    //public void executeCommand_EmptyLinkCommand_expectExceptions() {
    //    categories.add(new NusCategory());
    //    categories.add(new ZoomCategory());
    //    String inputString = "add ";
    //    int categoryNumber = 2;
    //    AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
    //    assertThrows(InvalidBookmarkLinkException.class, () -> {
    //        command.executeCommand(ui,categories);
    //    });
    //}

}