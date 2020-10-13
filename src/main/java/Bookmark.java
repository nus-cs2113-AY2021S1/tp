import seedu.duke.Bookmark.*;
import seedu.duke.Bookmark.Commands.BookmarkCommand;

import java.util.ArrayList;

public class Bookmark {
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkUi ui;
    private BookmarkMode currentMode;
    private int currentCategory;

    public Bookmark(){
        categories.add(new NUSCategory());
        categories.add(new ZoomCategory());
        ui = new BookmarkUi();
    }

    public void runBookmark() {
        ui.printLine();
        ui.printWelcomeBookmarkMessage();
        ui.showBookmarkCategoryList(categories); //UI
        ui.printLine();

        if (ModeNames.getCurrentModeName().equals("bookmark")){
            currentMode = BookmarkMode.BOOKMARK_MAIN;
        }

        Boolean isExit = true;
        while (isExit) {
            try {
                String line = ui.readInput();
                BookmarkCommand c = new BookmarkParser().evaluateInput(currentMode,line);
                c.executeCommand(ui,categories);
                currentMode = c.getCurrentMode();
                isExit = c.isExit();
            } catch (InvalidBookmarkCommandException e) {
                System.out.println("Invalid Input");
            }
        }
    }



}

