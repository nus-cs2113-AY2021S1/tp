package seedu.duke;
import java.util.ArrayList;
import java.util.Scanner;

public class BookmarkUi {
    public static final String LINE = "========================================================";
    private Scanner in;

    public BookmarkUi(){
        this.in = new Scanner(System.in);
    }
    public void printWelcomeBookmarkMessage(){
        printLine();
        System.out.println("Welcome to B00KMARK!");
        System.out.println("Choose your categories!");
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public String readInput(){
        return this.in.nextLine();
    }

    public void showBookmarkCategoryList(ArrayList<BookmarkCategory> categories){
        int i = 1;
        for (BookmarkCategory category: categories){
            System.out.println(i +"." + category.getName());
            i++;
        }
    }

    public void showBookmarkLinkList(ArrayList<BookmarkList> links){
        int i = 1;
        for (BookmarkList link: links){
            System.out.println(i +"." + link.getLink());
            i++;
        }
    }

}
