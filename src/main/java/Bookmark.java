import java.util.ArrayList;

public class Bookmark {
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkUi ui;
    private BookmarkMode currentMode;
    private Boolean isExit = true;

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

        while (isExit) {
            String line = ui.readInput();
            String upperLine = line.toUpperCase();
            if (currentMode == BookmarkMode.BOOKMARK_MAIN && upperLine.startsWith("BK")){
                int chosenCategory = evaluateCategoryChosen(line.substring(3));
                currentMode = BookmarkMode.ZOOM;
                ui.showBookmarkLinkList(categories.get(chosenCategory-1).getLinks());
            } else if (currentMode == BookmarkMode.ZOOM && upperLine.startsWith("ADD")){
                categories.get(1).addLink(line.substring(4));
                ui.showBookmarkLinkList(categories.get(1).getLinks());
            } else if (currentMode == BookmarkMode.ZOOM && upperLine.startsWith("RM")){
                int chosenLink = evaluateCategoryChosen(line.substring(3));
                categories.get(1).removeLink(chosenLink);
                ui.showBookmarkLinkList(categories.get(1).getLinks());
            } else if (currentMode == BookmarkMode.ZOOM && upperLine.startsWith("LIST")){
                ui.showBookmarkLinkList(categories.get(1).getLinks());
            } else if (currentMode == BookmarkMode.ZOOM && upperLine.startsWith("BACK")){
                currentMode = BookmarkMode.BOOKMARK_MAIN;
                ui.showBookmarkCategoryList(categories);
            } else if (currentMode == BookmarkMode.BOOKMARK_MAIN && upperLine.startsWith("BACK")){
                isExit = false;
            }
        }
    }

    public int evaluateCategoryChosen(String line){
        return Integer.parseInt(line);
    }

}

