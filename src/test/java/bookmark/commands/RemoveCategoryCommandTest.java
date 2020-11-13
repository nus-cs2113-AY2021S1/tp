package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveCategoryCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkStorage storage = new BookmarkStorage("data/bookmark.txt");


    @Test
    void executeCommand_deleteValidCategory_deleteCategoryCorrectly() {
        setUpBookmark();
        String inputString = "delete 1";
        int categoryNumber = 0;
        RemoveCategoryCommand removeCommand = new RemoveCategoryCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(0,categories.size());
    }

    @Test
    void executeCommand_deleteInValidCategoryCommand_doseNotDeleteCategory() {
        setUpBookmark();
        String inputString = "delete 10000"; //rm 0
        int categoryNumber = 0;
        RemoveCategoryCommand removeCommand = new RemoveCategoryCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(1,categories.size());
    }

    @Test
    void executeCommand_deleteEmptyCategoryCommand_doesNotDeleteCategory() {
        setUpBookmark();
        String inputString = "delete ";
        int categoryNumber = 0;
        RemoveCategoryCommand removeCommand = new RemoveCategoryCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(1,categories.size());
    }

    @Test
    void executeCommand_deleteNotANumber_doesNotDeleteCategory() {
        setUpBookmark();
        String inputString = "delete abcdef";
        int categoryNumber = 0;
        RemoveCategoryCommand removeCommand = new RemoveCategoryCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(1,categories.size());
    }

    @Test
    public void executeCommand_deleteInWrongMode_doesNotDeleteCategory() {
        setUpBookmark();
        String inputString = "delete 1";
        int categoryNumber = 2;
        RemoveCategoryCommand removeCommand = new RemoveCategoryCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(1,categories.size());
    }

    private void setUpBookmark() {
        categories.add(new BookmarkCategory("NUS"));
    }

}