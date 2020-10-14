package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import java.util.ArrayList;

public class BackCommand extends BookmarkCommand {
    private String backCommand;

    public BackCommand(String backCommand) {
        this.backCommand = backCommand;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        if (backCommand.equals("Goodbye")) {
            ui.printGoodbyeMessage();
        } else {
            ui.showBookmarkCategoryList(categories);
        }
    }
}