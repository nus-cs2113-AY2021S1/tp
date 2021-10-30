package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCategoryCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkStorage storage = new BookmarkStorage("data/bookmark.txt");


    @Test
    public void executeCommand_addValidCategory_addCategoryCorrectly() {
        String inputString = "cat Entertainment";
        int categoryNumber = 0;
        AddCategoryCommand command = new AddCategoryCommand(inputString, categoryNumber);
        command.executeCommand(ui, categories, storage);
        assertEquals(1, categories.size());
    }

    @Test
    public void executeCommand_addCategoryInAnotherMode_addCategoryCorrectly() {
        String inputString = "cat Entertainment";
        int categoryNumber = 2;
        AddCategoryCommand command = new AddCategoryCommand(inputString, categoryNumber);
        command.executeCommand(ui, categories, storage);
        assertEquals(1, categories.size());
    }

    @Test
    public void executeCommand_addEmptyCategoryCommand_doesNotAddCategory() {
        String inputString = "cat ";
        int categoryNumber = 0;
        AddCategoryCommand command = new AddCategoryCommand(inputString, categoryNumber);
        command.executeCommand(ui, categories, storage);
        assertEquals(0, categories.size());
    }
}
