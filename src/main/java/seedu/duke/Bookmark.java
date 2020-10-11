package seedu.duke;

import java.util.ArrayList;

public class Bookmark {
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkUi ui;

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
        String line = ui.readInput();
        String upperLine = line.toUpperCase();
        if (upperLine.startsWith("CD")){
            int chosenCategory = evaluateCategoryChosen(line.substring(3));
            ui.showBookmarkLinkList(categories.get(chosenCategory-1).getLinks());
        }
    }

    public int evaluateCategoryChosen(String line){
        int chosenCategory = Integer.parseInt(line);
        if (chosenCategory > categories.size()){
            return 0;
        } else {
            return chosenCategory;
        }
    }
}

