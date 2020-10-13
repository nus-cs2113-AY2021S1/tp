import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkUi;
import seedu.duke.Bookmark.NUSCategory;
import seedu.duke.Bookmark.ZoomCategory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudyIt {
    private ArrayList<BookmarkCategory> bookmarkCategories = new ArrayList<>();
    private BookmarkUi bookmarkUi;
    private static Mode currentMode = Mode.MENU;
    private BookmarkParser bookmarkParser;

    public static void changeMode(Mode destinationMode) {
        currentMode = destinationMode;
    }

    public static Mode getCurrentMode() {
        return currentMode;
    }

    public StudyIt(){
        bookmarkCategories.add(new NUSCategory());
        bookmarkCategories.add(new ZoomCategory());
        bookmarkUi = new BookmarkUi();
        bookmarkParser = new BookmarkParser();
    }

    public static void main(String[] args) {
        MainMenu.printWelcome();
        new StudyIt().run();
    }

    public void run() {
        CommandType commandType;
        // Repeatedly receive & process user command until "exit" is given
        do {
            // Collect user's command & identify the type
            String command = Ui.inputCommand();
            commandType = CommandParser.getCommandType(command);

            Command.executeCommand(command, commandType,bookmarkCategories,bookmarkUi,bookmarkParser);
        } while (commandType != CommandType.EXIT_PROGRAM);
    }
}