package seedu.duke;

import java.util.ArrayList;

public class ZoomCategory extends BookmarkCategory{
    private String name = "Zoom";
    private ArrayList<BookmarkList> links = new ArrayList<>();

    public String getName(){
        return name;
    }

    public ArrayList<BookmarkList> getLinks(){
        links.add(new BookmarkList("http"));
        return links;
    }
}
