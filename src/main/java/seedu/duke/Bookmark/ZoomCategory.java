package seedu.duke.Bookmark;

import seedu.duke.Bookmark.BookmarkCategory;
import seedu.duke.Bookmark.BookmarkList;

import java.util.ArrayList;

public class ZoomCategory extends BookmarkCategory {
    private String name = "Zoom";
    private ArrayList<BookmarkList> links = new ArrayList<>();

    public String getName(){
        return name;
    }

    public ArrayList<BookmarkList> getLinks(){
        return links;
    }
    public void addLink(String link){
        links.add(new BookmarkList(link));
    }
    public void removeLink(int number){
        links.remove(number-1);
    }

}
