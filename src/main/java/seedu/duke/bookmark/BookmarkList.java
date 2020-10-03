package seedu.duke.bookmark;

import seedu.duke.book.Book;

import java.util.ArrayList;

public class BookmarkList {
    private ArrayList<Bookmark> bookmarks;

    public BookmarkList() {
        bookmarks = new ArrayList<Bookmark>();
    }

    public void addBookmark(Bookmark bookmark) {
        System.out.println("Adding bookmark to the list...");
        bookmarks.add(bookmark);
        System.out.println("Finished added");
    }

    public Bookmark findBookmark(Book book) {
        for (int i = 0; i < bookmarks.size(); i++) {
            if(bookmarks.get(i).getBook().equals(book)) {
                return bookmarks.get(i);
            }
        }
        return null;
    }

    public void removeBookmark(Bookmark bookmark) {
        bookmarks.remove(bookmark);
    }

    public void listBookmarks() {
        for (int i = 0; i < bookmarks.size(); i++) {
            System.out.println(bookmarks.get(i).toString());
        }
    }
}
