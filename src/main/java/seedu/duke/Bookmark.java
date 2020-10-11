package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Bookmark {
private ArrayList<BookmarkCategory> categories;

public Bookmark(){
    categories.add(new NUSCategory());
    categories.add(new ZoomCategory());
}


public void runBookmark(){
    System.out.println("Welcome to B00KMARK!"); //UI
    System.out.println("Choose your categories!"); //UI
    showBookmarkCategoryList(categories); //UI
    Scanner in = new Scanner(System.in);
    int categoryNumber = Integer.parseInt(in.nextLine());
    boolean isExit = false;
    while (!isExit) {
        if ( categoryNumber > categories.size() ) {
            System.out.println("Invalid");
        } else {
            showBookmarkList(categories.get(categoryNumber));
        }
    }

}

public void showBookmarkCategoryList(ArrayList<BookmarkCategory> categories){
    if ( categories.size() == 0 ){
        System.out.println("Empty List");
    }
    for(BookmarkCategory category: categories){
        System.out.println(category.getName());
    }
}

public void showBookmarkList(BookmarkCategory selectedCategory){
    //if(selectedCategory.list)

}

}
