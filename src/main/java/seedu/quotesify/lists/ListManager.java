package seedu.quotesify.lists;

import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.todo.ToDoList;

import java.util.HashMap;

/**
 * Manages all of quotesify's lists.
 */
public class ListManager {
    public static final int BOOK_LIST = 0;
    public static final int BOOKMARK_LIST = 1;
    public static final int CATEGORY_LIST = 2;
    public static final int QUOTE_LIST = 3;
    public static final int RATING_LIST = 4;
    public static final int TODO_LIST = 5;

    private static HashMap<Integer, QuotesifyList<?>> listManager = new HashMap<>();

    /**
     * Initialises a new list for all of quotesify's lists.
     */
    public static void initialiseAllLists() {
        listManager.put(BOOK_LIST, new BookList());
        listManager.put(BOOKMARK_LIST, new BookmarkList());
        listManager.put(CATEGORY_LIST, new CategoryList());
        listManager.put(QUOTE_LIST, new QuoteList());
        listManager.put(RATING_LIST, new RatingList());
        listManager.put(TODO_LIST, new ToDoList());
    }

    /**
     * Adds a quotesify list to the list manager.
     *
     * @param type List type.
     * @param list Quotesify list.
     */
    public static void addToList(int type, QuotesifyList<?> list) {
        listManager.put(type, list);
    }

    /**
     * Returns a quotesify list based on the type given.
     *
     * @param type List type.
     * @return Quotesify list.
     */
    public static QuotesifyList<?> getList(int type) {
        return listManager.get(type);
    }
}
