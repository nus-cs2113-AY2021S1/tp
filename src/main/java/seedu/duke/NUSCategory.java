package seedu.duke;

import java.awt.print.Book;
import java.util.ArrayList;

public class NUSCategory extends BookmarkCategory{
    private String name = "NUS";
    private ArrayList<BookmarkList> links = new ArrayList<>();

    public String getName(){
        return name;
    }

    public ArrayList<BookmarkList> getLinks(){
        links.add(new BookmarkList("http"));
        links.add(new BookmarkList( "HUhuhu"));
        return links;
    }
}
