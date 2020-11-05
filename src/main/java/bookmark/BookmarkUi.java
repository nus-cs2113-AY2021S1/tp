package bookmark;

import java.util.ArrayList;

public class BookmarkUi {

    public BookmarkUi() {

    }

    public static void printWelcomeBookmarkMessage() {
        System.out.println("Welcome to bookmark mode!");
        System.out.println("You can use this mode to bookmark your links for easier access!");
        System.out.println("\nChoose your category by typing \"bm <category index>!\"");
        System.out.println("Otherwise, insert \"help\" to find the list of commands available.");
    }

    public void showBookmarkCategoryList(ArrayList<BookmarkCategory> bookmarkCategories) {
        System.out.println("Here are the categories available:");
        int i = 1;
        for (BookmarkCategory category: bookmarkCategories) {
            System.out.println(i + "." + category.getName());
            i++;
        }
    }

    public void showBookmarkLinkList(ArrayList<BookmarkList> links) {
        System.out.println("The following are your current bookmarks in this category");
        System.out.println("Bookmarks:");
        if (links.size() == 0) {
            System.out.println("\t<empty>");
        } else {
            int i = 1;
            for (BookmarkList link: links) {
                System.out.println("\t" + i + "." + link);
                i++;
            }
        }
    }

    public void printGoodbyeMessage() {
        showCurrentMode("Bookmark Main");
        System.out.println("Use \"exit\" to exit the mode or enter another category\n"
                + "using \"bm <category index>\".");
    }

    public void showBookmarkList(ArrayList<BookmarkCategory> categories) {
        System.out.println("Here is the list");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". Category: " + categories.get(i).getName());
            showBookmarkLinkList(categories.get(i).getLinks());
        }
    }

    public void printChooseCategoryMessage() {
        System.out.println("You have not chosen a category.");
        System.out.println("Change category by using \"bm <CategoryNumber>\". ");
        System.out.println("View the categories available by using \"list cat\". ");
    }

    public void showEmptyError(String item) {
        System.out.println("Empty " + item + " :(");
        System.out.println("Please input a " + item);
        System.out.println("You can input \"help\" to view the input format.");
    }

    public void showInvalidError(String item) {
        System.out.println("Sorry you have entered an invalid " + item
                + " or your input is in the wrong format!");
        System.out.println("Please enter a valid " + item + " or input \"help\" to find out the correct format!");
    }

    public void showInvalidNumberError() {
        System.out.println("Sorry the format requires a number.");
        System.out.println("Please enter a valid number!");
        System.out.println("You can input \"help\" to view the input format.");
    }

    public void showModeChangeMessage(ArrayList<BookmarkCategory> categories, int categoryNumberInList) {
        System.out.println("You are now in " + categories.get(categoryNumberInList).getName() + " category");
        showBookmarkLinkList(categories.get(categoryNumberInList).getLinks());
        System.out.println("Add new bookmarks by using \"add <link>\"");
    }

    public void showAlreadyInModeMessage(ArrayList<BookmarkCategory> categories, int categoryName) {
        System.out.println("You are already in chosen Category: " + categories.get(categoryName - 1).getName());
        showBookmarkLinkList(categories.get(categoryName - 1).getLinks());
    }

    public void showStarBookmarks(ArrayList<BookmarkCategory> categories) {
        int i = 0;
        System.out.println("Starred bookmarks: ");
        for (BookmarkCategory category : categories) {
            for (BookmarkList link : category.getLinks()) {
                if (link.getStar()) {
                    i++;
                    System.out.println(i + "." + link);
                }
            }
        }
        if (i == 0) {
            System.out.println("\t<empty>");
        }

    }

    public void showExistingBookmarkError(String item) {
        System.out.println("You have the exact same bookmark " + item + " in your list!");
        System.out.println("Add a new unique bookmark " + item + "!");
    }

    public void showCorrectCommand(String item) {
        System.out.println("Did you mean \"" + item + "\"?");
        System.out.println("If you did please input \"" + item + "\"");
        System.out.println("If not input \"help\" to view the correct command format.");
    }

    public void showCurrentMode(String name) {
        System.out.println("You are currently in : " + name);
    }
}
