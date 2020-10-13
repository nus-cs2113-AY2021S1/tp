package seedu.duke.Bookmark;

import seedu.duke.Bookmark.Commands.*;

public class BookmarkParser {
    public BookmarkParser(){

    }

    public BookmarkCommand evaluateInput(BookmarkMode currentMode, String line) throws InvalidBookmarkCommandException{
        String upperLine = line.toUpperCase();
        if (currentMode == BookmarkMode.BOOKMARK_MAIN && upperLine.startsWith("BM")){
            return new changeModeCommand(line);
        } else if ((currentMode == BookmarkMode.ZOOM || currentMode == BookmarkMode.NUS)
                && upperLine.startsWith("ADD")){
            return new AddLinkCommand(currentMode,line);
        } else if ((currentMode == BookmarkMode.ZOOM || currentMode == BookmarkMode.NUS)
                && upperLine.startsWith("RM")){
            return new RemoveLinkCommand(currentMode,line);
        } else if ((currentMode == BookmarkMode.ZOOM || currentMode == BookmarkMode.NUS)
                && upperLine.startsWith("LIST")){
            return new ListCommand(currentMode);
        } else if (upperLine.startsWith("BACK")) {
            return new BackCommand(currentMode);
        } else{
            throw new InvalidBookmarkCommandException();
        }
    }
}
