package seedu.duke.commands;

import seedu.duke.bookmark.BookmarkList;
import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.lists.ListManager;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.rating.RatingParser;
import seedu.duke.todo.ToDoList;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListCommand extends Command {
    private String type;
    private String information;

    public ListCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_CATEGORY:
            CategoryList categoryList = (CategoryList) listManager.getList(ListManager.CATEGORY_LIST);
            listCategories(categoryList, ui);
            break;
        case TAG_RATING:
            RatingList ratingList = (RatingList) listManager.getList(ListManager.RATING_LIST);
            listRatings(ratingList, ui);
            break;
        case TAG_TODO:
            ToDoList toDoList = (ToDoList) listManager.getList(ListManager.TODO_LIST);
            listToDos(toDoList,ui);
            break;
        case TAG_BOOKMARK:
            BookmarkList bookmarkList = (BookmarkList) listManager.getList(ListManager.BOOKMARK_LIST);
            listBookmarks(bookmarkList, ui);
        default:
        }
    }

    private void listRatings(RatingList ratingList, TextUi ui) {
        ArrayList<Rating> ratings = ratingList.getList();
        ratings.sort(Comparator.comparing(Rating::getRating));
        Collections.reverse(ratings);
        if (information.equals("")) {
            listAllRatings(ratingList, ui);
        } else {
            listSpecifiedRating(ratingList, ui);
        }
    }

    private void listAllRatings(RatingList ratingList, TextUi ui) {
        ui.printAllRatings(ratingList);
    }

    private void listSpecifiedRating(RatingList ratings, TextUi ui) {
        int ratingToList = RatingParser.checkFormatOfRatingValue(information);
        if (RatingParser.checkRangeOfRatingValue(ratingToList)) {
            ui.printSpecifiedRating(ratings, ratingToList);
        }
    }

    private void listCategories(CategoryList categoryList, TextUi ui) {
        if ((information.isEmpty())) {
            listAllCategories(categoryList, ui);
        } else {
            listCategory(categoryList, ui);
        }
    }

    private void listAllCategories(CategoryList categoryList, TextUi ui) {
        ui.printAllCategories(categoryList);
    }

    private void listCategory(CategoryList categoryList, TextUi ui) {
        try {
            ui.printCategory(categoryList.getCategoryByName(information));
        } catch (NullPointerException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void listToDos(ToDoList toDoList, TextUi ui){
        ui.printAllToDos(toDoList);
    }

    private void listBookmarks(BookmarkList bookmarkList, TextUi ui) {
        ui.printAllBookmarks(bookmarkList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
