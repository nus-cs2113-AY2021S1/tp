package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import bookmark.NusCategory;
import bookmark.ZoomCategory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeModeCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();

    @Test
    void executeCommand_validCategory_returnsUpdatedCategoryNumber() {
        categories.add(new ZoomCategory());
        categories.add(new NusCategory());
        int categoryNumber = 0;
        String inputString = "bm 2";
        ChangeModeCommand command = new ChangeModeCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(2,command.getCategoryNumber());
    }

    @Test
    void executeCommand_InvalidCategory_doesNotUpdateCategoryNumber() {
        categories.add(new ZoomCategory());
        categories.add(new NusCategory());
        int categoryNumber = 0;
        String inputString = "bm 200";
        ChangeModeCommand command = new ChangeModeCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(0,command.getCategoryNumber());
    }

    @Test
    void executeCommand_EmptyCategory_doesNotUpdateCategoryNumber() {
        categories.add(new ZoomCategory());
        categories.add(new NusCategory());
        int categoryNumber = 0;
        String inputString = "bm ";
        ChangeModeCommand command = new ChangeModeCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(0,command.getCategoryNumber());
    }

    @Test
    void executeCommand_SameCategory_doesNotUpdateCategoryNumber() {
        categories.add(new ZoomCategory());
        categories.add(new NusCategory());
        int categoryNumber = 2;
        String inputString = "bm 2";
        ChangeModeCommand command = new ChangeModeCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(2,command.getCategoryNumber());
    }
}