import seedu.duke.Bookmark.Commands.*;
import seedu.duke.Bookmark.InvalidBookmarkCommandException;

public class BookmarkParser extends CommandParser {
    private int chosenCategory;
    public BookmarkParser(){
    }

    public BookmarkCommand evaluateInput(String line) throws InvalidBookmarkCommandException{
        String upperLine = line.toUpperCase();
        if (upperLine.startsWith("BM")){
            getChosenCategory(line);
            return new changeModeCommand(chosenCategory);
        } else if (upperLine.startsWith("ADD")){
            return new AddLinkCommand(line, chosenCategory);
        } else if (upperLine.startsWith("RM")){
            return new RemoveLinkCommand(line, chosenCategory);
        } else if (upperLine.startsWith("LIST")){
            return new ListCommand(chosenCategory);
        } else if (upperLine.startsWith("BACK")) {
            return new BackCommand(chosenCategory);
        } else{
            throw new InvalidBookmarkCommandException();
        }
    }

    private void getChosenCategory(String line){
        chosenCategory = Integer.parseInt(line.substring(3));
    }
}
