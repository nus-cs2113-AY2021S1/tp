import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import bookmark.commands.AddLinkCommand;
import bookmark.commands.BackCommand;
import bookmark.commands.BookmarkCommand;
import bookmark.commands.ChangeModeCommand;
import bookmark.commands.ListCommand;
import bookmark.commands.RemoveLinkCommand;
import bookmark.InvalidBookmarkCommandException;

import java.util.ArrayList;

public class BookmarkParser extends CommandParser {
    private int chosenCategory;

    public BookmarkParser() {
    }

    public BookmarkCommand evaluateInput(String line, BookmarkUi ui, ArrayList<BookmarkCategory> categories)
            throws InvalidBookmarkCommandException {
        String upperLine = line.toUpperCase();
        if (upperLine.startsWith("BM")) {
            getChosenCategory(line);
            return new ChangeModeCommand(chosenCategory);
        } else if (upperLine.startsWith("ADD")) {
            return new AddLinkCommand(line, chosenCategory);
        } else if (upperLine.startsWith("RM")) {
            return new RemoveLinkCommand(line, chosenCategory);
        } else if (upperLine.startsWith("LIST")) {
            return new ListCommand(chosenCategory);
        } else if (upperLine.startsWith("BACK")) {
            String backCommand = updateChosenCategory(ui, categories);
            return new BackCommand(backCommand);
        } else {
            throw new InvalidBookmarkCommandException();
        }
    }

    private String updateChosenCategory(BookmarkUi ui, ArrayList<BookmarkCategory> categories) {
        if (chosenCategory == 0) {
            return "Goodbye";
        } else {
            chosenCategory = 0;
            return "Category";
        }
    }

    private void getChosenCategory(String line) {
        chosenCategory = Integer.parseInt(line.substring(3));
    }

}
