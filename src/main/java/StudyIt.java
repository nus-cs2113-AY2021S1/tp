import flashcard.Flashcard;
import flashcard.FlashcardRun;
import bookmark.BookmarkCategory;
import bookmark.BookmarkUi;
import bookmark.NusCategory;
import bookmark.ZoomCategory;
import java.util.ArrayList;
import academic.Person;
import java.util.Scanner;


public class StudyIt {
    private ArrayList<BookmarkCategory> bookmarkCategories = new ArrayList<>();
    private BookmarkUi bookmarkUi;
    private BookmarkParser bookmarkParser;
    private static Mode currentMode = Mode.MENU;


    public static void changeMode(Mode destinationMode) {
        currentMode = destinationMode;
    }

    public static Mode getCurrentMode() {
        return currentMode;
    }

    public static FlashcardRun flashcardRun = new FlashcardRun();

    public StudyIt() {
        bookmarkCategories.add(new NusCategory());
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
            Command.executeCommand(command, commandType,bookmarkCategories,bookmarkUi,bookmarkParser,flashcardRun);
        } while (commandType != CommandType.EXIT_PROGRAM);
    }
}