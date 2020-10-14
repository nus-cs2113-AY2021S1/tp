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

    public BookmarkCommand evaluateInput(String line)
            throws InvalidBookmarkCommandException {
        if (line == null){
            throw new InvalidBookmarkCommandException();
        }
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
            String backCommand = updateChosenCategory();
            return new BackCommand(backCommand);
        } else {
            throw new InvalidBookmarkCommandException();
        }
    }

    private String updateChosenCategory() {
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
