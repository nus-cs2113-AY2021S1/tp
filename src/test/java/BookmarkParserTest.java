import bookmark.BookmarkCategory;
import bookmark.InvalidBookmarkCommandException;
import bookmark.commands.BookmarkCommand;
import bookmark.commands.ChangeModeCommand;
import bookmark.BookmarkUi;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkParserTest {

    @Test
    void evaluateInput_BM_returnsChangeModeCommand() throws InvalidBookmarkCommandException {
        BookmarkParser parser = new BookmarkParser();
        BookmarkUi ui = new BookmarkUi();
        ArrayList<BookmarkCategory> categories = new ArrayList<>();
        String inputLine = "Bm 2";
        BookmarkCommand command = parser.evaluateInput(inputLine,ui,categories);
        assertEquals(command,new ChangeModeCommand(2));
    }
}