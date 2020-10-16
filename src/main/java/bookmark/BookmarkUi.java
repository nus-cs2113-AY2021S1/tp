package bookmark;

import bookmark.BookmarkCategory;
import bookmark.BookmarkList;

import java.util.ArrayList;
import java.util.Scanner;

public class BookmarkUi {
    private Scanner in;

    public BookmarkUi() {
        this.in = new Scanner(System.in);
    }

    public static void printWelcomeBookmarkMessage() {
        System.out.println("Welcome to B00KMARK!");
        System.out.println("Choose your category by typing \"bm <category index>!\"");
    }

    public static void showBookmarkCategoryList(ArrayList<BookmarkCategory> bookmarkCategories) {
        System.out.println("Here are the categories available:");
        int i = 1;
        for (BookmarkCategory category: bookmarkCategories) {
            System.out.println(i + "." + category.getName());
            i++;
        }
    }

    public void showBookmarkLinkList(ArrayList<BookmarkList> links) {
        System.out.println("Bookmarks:");
        if (links.size() == 0) {
            System.out.println("<empty>");
        } else {
            int i = 1;
            for (BookmarkList link: links) {
                System.out.println(i + "." + link.getLink());
                i++;
            }
        }
    }

    public void printGoodbyeMessage() {
        System.out.println("Use \"exit\" to exit the mode or enter another category\n"
                + "using \"bm <category index>\"");
    }

    public void showBookmarkList(ArrayList<BookmarkCategory> categories) {
        System.out.println("Here is the list");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("Category: " + categories.get(i).getName());
            showBookmarkLinkList(categories.get(i).getLinks());
        }
    }

    public void showInvalidBookmarkCommand() {
        System.out.println("Invalid Bookmark commands");
    }

    public void printChooseCategoryMessage() {
        System.out.println("Please choose a category.");
    }

    public void showEmptyLinkError() {
        System.out.println("Empty link :(");
    }

    public void showInvalidLinkError() {
        System.out.println("Not a valid link, please enter a valid link.");
    }

}
