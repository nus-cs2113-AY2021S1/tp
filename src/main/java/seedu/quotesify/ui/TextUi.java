package seedu.quotesify.ui;

import seedu.quotesify.book.Book;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.book.BookList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;

import java.util.Scanner;

/**
 * Represents the UI component of Quotesify.
 */
public class TextUi {
    private final Scanner in;

    /**
     * Default constructor for TextUi.
     * Initialises STDIN.
     */
    public TextUi() {
        in = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(UiMessage.LOGO);
        System.out.println(UiMessage.WELCOME_MESSAGE);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println(UiMessage.GOODBYE_MESSAGE);
    }

    public String getUserCommand() {
        System.out.println(UiMessage.PROMPT_MESSAGE);
        if (in.hasNextLine()) {
            return in.nextLine().trim();
        }
        return "bye";
    }

    public void printDividerLine() {
        System.out.println(UiMessage.DIVIDER_LINE);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    //@@author chloesyy

    /**
     * Prints successful add book message.
     *
     * @param book Book which was added.
     */
    public void printAddBook(Book book) {
        System.out.printf(UiMessage.ADD_BOOK_MESSAGE + "\n", book.toString());
    }

    /**
     * Prints successful delete book message.
     *
     * @param book Book which was deleted.
     */
    public void printDeleteBook(Book book) {
        System.out.printf(UiMessage.DELETE_BOOK_MESSAGE + "\n", book.toString());
    }

    /**
     * Prints successful edit book message.
     *
     * @param oldTitle Old title of book.
     * @param newTitle New title of book.
     */
    public void printEditBook(String oldTitle, String newTitle) {
        System.out.printf(UiMessage.EDIT_BOOK_MESSAGE + "\n", oldTitle, newTitle);
    }

    /**
     * Prints successful done book message.
     *
     * @param book Book which was marked as done.
     */
    public void printDoneBook(Book book) {
        System.out.printf(UiMessage.DONE_BOOK_MESSAGE + "\n", book.toString());
    }

    /**
     * Prints the list of completed books.
     *
     * @param bookList BookList of books which are completed.
     */
    public void printListDoneBook(BookList bookList) {
        System.out.println(UiMessage.LIST_DONE_BOOK_MESSAGE);
        System.out.print(bookList.toStringWithIndex());
    }

    /**
     * Prints the list of undone books.
     *
     * @param bookList BookList of books which are not completed.
     */
    public void printListUndoneBook(BookList bookList) {
        System.out.println(UiMessage.LIST_UNDONE_BOOK_MESSAGE);
        System.out.print(bookList.toStringWithIndex());
    }

    /**
     * Prints a list of all existing books.
     * @param bookList BookList of books.
     */
    public void printAllBooks(BookList bookList) {
        System.out.println(UiMessage.LIST_BOOKS_MESSAGE);
        System.out.print(bookList.toStringWithIndex());
    }

    /**
     * Prints the book details of a specified book.
     *
     * @param book Book which details are to be printed.
     */
    public void printBookDetails(Book book) {
        System.out.println(UiMessage.LIST_BOOK_DETAIL_MESSAGE);
        System.out.print(book.getBookDetailString());
    }

    /**
     * Prints a list of books by the author name.
     *
     * @param bookList BookList of books with the author name.
     * @param authorName Author name of books.
     */
    public void printBooksByAuthor(BookList bookList, String authorName) {
        System.out.printf(UiMessage.LIST_BOOKS_BY_AUTHOR_MESSAGE + "\n", authorName);
        System.out.print(bookList.toStringWithIndex());
    }

    /**
     * Prints a list of books containing a keyword.
     *
     * @param bookList BookList of books with the keyword.
     * @param keyword Keyword.
     */
    public void printBooksByKeyword(BookList bookList, String keyword) {
        System.out.printf(UiMessage.LIST_BOOKS_BY_KEYWORD_MESSAGE + "\n", keyword);
        System.out.print(bookList.toStringWithIndex());
    }

    //@@author nat-ho
    public void printAddQuote(Quote quote) {
        System.out.printf(UiMessage.ADD_QUOTE_MESSAGE + "\n", quote.toString());
    }

    public void printAllQuotes(QuoteList quotes) {
        if (quotes.getSize() > 0) {
            System.out.println(UiMessage.LIST_ALL_QUOTES);
            System.out.println(quotes);
        } else {
            System.out.println(UiMessage.LIST_NO_QUOTES_SAVED_MESSAGE);
        }
    }

    public void printAllQuotesByAuthor(QuoteList quoteList, String authorName) {
        if (quoteList.getSize() == 0) {
            System.out.println(UiMessage.LIST_NO_QUOTES_SAVED_MESSAGE);
        }
        String listToPrint = quoteList.getQuotesByAuthor(authorName);
        if (listToPrint.isEmpty()) {
            System.out.println(UiMessage.LIST_NO_QUOTES_FOUND_MESSAGE);
        } else {
            System.out.printf((UiMessage.LIST_QUOTES_BY_AUTHOR_MESSAGE) + "\n", authorName);
            System.out.println(listToPrint);
        }
    }

    public void printAllQuotesByReference(QuoteList quoteList, String reference) {
        if (quoteList.getSize() == 0) {
            System.out.println(UiMessage.LIST_NO_QUOTES_SAVED_MESSAGE);
        }
        String listToPrint = quoteList.getQuotesByReference(reference);
        if (listToPrint.isEmpty()) {
            System.out.println(UiMessage.LIST_NO_QUOTES_FOUND_MESSAGE);
        } else {
            System.out.printf((UiMessage.LIST_QUOTES_BY_REFERENCE_MESSAGE) + "\n", reference);
            System.out.println(listToPrint);
        }
    }

    public void printAllQuotesByReferenceAndAuthor(QuoteList quoteList, String reference, String authorName) {
        if (quoteList.getSize() == 0) {
            System.out.println(UiMessage.LIST_NO_QUOTES_SAVED_MESSAGE);
        }
        String listToPrint = quoteList.getQuotesByReferenceAndAuthor(reference, authorName);
        if (listToPrint.isEmpty()) {
            System.out.println(UiMessage.LIST_NO_QUOTES_FOUND_MESSAGE);
        } else {
            System.out.printf(UiMessage.LIST_QUOTES_BY_AUTHOR_AND_REFERENCE_MESSAGE + "\n", reference, authorName);
            System.out.println(listToPrint);
        }
    }

    public void printQuoteAndReflection(Quote quote) {
        if (quote.getReflection() != null) {
            System.out.printf(UiMessage.LIST_QUOTE_REFLECTION + "\n", quote.toString(), quote.getReflection());
        } else {
            System.out.println(UiMessage.LIST_QUOTE_NO_REFLECTION);
        }

    }

    public  void printDeleteQuote(String quote) {
        System.out.printf((UiMessage.DELETE_QUOTE_MESSAGE) + "\n", quote);
    }

    public void printRandomQuote() {
        QuoteList quotelist = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        String randomQuote = quotelist.getRandomQuote();
        System.out.println(UiMessage.PRINT_RANDOM_QUOTE + System.lineSeparator() + randomQuote);
    }

    public void printEditQuote(Quote oldQuote, Quote newQuote) {
        System.out.printf(UiMessage.EDIT_QUOTE_MESSAGE + "\n", oldQuote.toString(), newQuote.toString());
    }

    public void printAddReflection(Quote quote) {
        System.out.printf(UiMessage.ADD_QUOTE_REFLECTION + "\n", quote.toString(), quote.getReflection());
    }

    public  void printDeleteQuoteReflection(String quote) {
        System.out.printf((UiMessage.DELETE_QUOTE_REFLECTION_MESSAGE) + "\n", quote);
    }

    public  void printEditQuoteReflection(Quote quote) {
        System.out.printf((UiMessage.EDIT_QUOTE_REFLECTION) + "\n", quote.toString(), quote.getReflection());
    }

    public void printFindQuoteFail() {
        System.out.println(UiMessage.FIND_QUOTE_FAIL);
    }

    public void printFindQuoteSuccess(String results) {
        System.out.println(UiMessage.FIND_QUOTE_SUCCESS);
        System.out.println(results);
    }
    //@@author

    //@@author dozenmatter

    /**
     * Prints the addition of a category to a book message.
     *
     * @param bookTitle Book title.
     * @param categoryName Category name.
     */
    public void printAddCategoryToBook(String bookTitle, String categoryName) {
        String trailer = "the book [" + bookTitle + "]";
        System.out.printf((UiMessage.ADD_CATEGORY_MESSAGE) + "\n", categoryName, trailer);
    }

    /**
     * Prints the addition of a category to a quote message.
     *
     * @param quote Quote string.
     * @param categoryName Category name.
     */
    public void printAddCategoryToQuote(String quote, String categoryName) {
        String trailer = "the quote [\"" + quote + "\"]";
        System.out.printf((UiMessage.ADD_CATEGORY_MESSAGE) + "\n", categoryName, trailer);
    }

    /**
     * Prints the deletion of a category from a book message.
     *
     * @param bookTitle Book title.
     * @param categoryName Category name.
     */
    public void printRemoveCategoryFromBook(String bookTitle, String categoryName) {
        String trailer = "the book [" + bookTitle + "]";
        System.out.printf((UiMessage.DELETE_CATEGORY_MESSAGE) + "\n", categoryName, trailer);
    }

    /**
     * Prints the deletion of a category from a quote message.
     *
     * @param quote Quote string.
     * @param categoryName Category name.
     */
    public void printRemoveCategoryFromQuote(String quote, String categoryName) {
        String trailer = "the quote [\"" + quote + "\"]";
        System.out.printf((UiMessage.DELETE_CATEGORY_MESSAGE) + "\n", categoryName, trailer);
    }

    /**
     * Prints the deletion of a category from all books and quotes message.
     *
     * @param categoryName Category name.
     */
    public void printRemoveCategory(String categoryName) {
        System.out.printf(UiMessage.DELETE_CATEGORY_MESSAGE + "\n", categoryName, "all books and quotes");
    }

    /**
     * Prints the size of a category.
     *
     * @param category Category object.
     */
    public void printCategorySize(Category category) {
        System.out.printf((UiMessage.CATEGORY_SIZE_MESSAGE) + "\n", category.getSize(), category.getCategoryName());
    }

    /**
     * Prints all categories.
     *
     * @param categoryList List of categories.
     */
    public void printAllCategories(CategoryList categoryList) {
        if (categoryList.getList().size() == 0) {
            System.out.println(UiMessage.EMPTY_CATEGORY_LIST_MESSAGE);
            return;
        }
        System.out.println(UiMessage.LIST_CATEGORIES_MESSAGE);
        System.out.println(categoryList.toString());
    }

    /**
     * Prints all categories matching the keyword.
     *
     * @param categoryList List of categories.
     * @param keyword User specified keyword.
     */
    public void printMatchingCategories(CategoryList categoryList, String keyword) {
        System.out.printf(UiMessage.FIND_CATEGORIES_MESSAGE + "\n", keyword);
        System.out.println(categoryList.toString());
    }

    /**
     * Prints all items tagged under the category.
     *
     * @param category Category object.
     */
    public void printAllInCategory(Category category) {
        String categoryName = category.getCategoryName();
        if (category.getSize() == 0) {
            System.out.printf(UiMessage.NO_ITEMS_IN_CATEGORY_MESSAGE + "\n", categoryName);
            return;
        }

        System.out.printf(UiMessage.LIST_ALL_IN_CATEGORIES_MESSAGE + "\n", categoryName);
        printAllBooksInCategory(category);
        System.out.println();
        printAllQuotesInCategory(category);
    }

    /**
     * Prints all books tagged under the category.
     *
     * @param category Category object.
     */
    public void printAllBooksInCategory(Category category) {
        BookList bookList = category.getBookList();
        BookList allBooks = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        if (bookList.getList().size() > 0) {
            System.out.println("BOOKS:");
            for (Book book : bookList.getList()) {
                int index = allBooks.getIndex(book) + 1;
                System.out.println(index + ". " + book.toString());
            }
        }
    }

    /**
     * Prints the successful change from the old to a new category name.
     *
     * @param oldCategory Old category name.
     * @param newCategory New category name.
     */
    public void printEditCategory(String oldCategory, String newCategory) {
        System.out.printf(UiMessage.EDIT_CATEGORY_MESSAGE + "\n", oldCategory, newCategory);
    }

    /**
     * Prints all quotes tagged under the category.
     *
     * @param category Category object.
     */
    public void printAllQuotesInCategory(Category category) {
        QuoteList quoteList = category.getQuoteList();
        QuoteList allQuotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        if (quoteList.getList().size() > 0) {
            System.out.println("QUOTES:");
            for (Quote quote : quoteList.getList()) {
                int index = allQuotes.getIndex(quote) + 1;
                System.out.println(index + ". " + quote.toString());
            }
        }
    }

    //@@author
    //@@author yuen-sihao
    public void printAddRating(int ratingScore, String titleOfBookToRate, String authorOfBookToRate) {
        System.out.printf((UiMessage.ADD_RATING_MESSAGE) + "\n", titleOfBookToRate, authorOfBookToRate, ratingScore);
    }

    public void printAllRatings(RatingList ratingList) {
        if (ratingList.getList().size() == 0) {
            System.out.println(UiMessage.LIST_NO_RATINGS_FOUND_MESSAGE);
            return;
        }
        System.out.println(UiMessage.LIST_ALL_RATINGS_MESSAGE);
        System.out.print(ratingList.toString());
    }

    public void printSpecifiedRating(RatingList ratings, int ratingToPrint) {
        System.out.printf((UiMessage.LIST_SPECIFIED_RATING_MESSAGE) + "\n", ratingToPrint);
        for (Rating rating : ratings.getList()) {
            if (rating.getRating() == ratingToPrint) {
                System.out.println(rating.toString());
            }
        }
    }

    public void printDeleteRating(String bookTitle, String author) {
        System.out.printf((UiMessage.DELETE_RATING_MESSAGE) + "\n", bookTitle, author);
    }

    public void printEditRating(int ratingScore, String title, String author) {
        System.out.printf((UiMessage.EDIT_RATING_MESSAGE) + "\n", title, author, ratingScore);
    }

    public void printFoundRating(RatingList ratings, String keyword) {
        System.out.println(UiMessage.FIND_RATING_MESSAGE);
        for (Rating rating : ratings.getList()) {
            if (rating.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(rating.toString());
            }
        }
    }
    //@@author

    //@@author lunzard
    public void printAddToDo(ToDo toDo) {
        if (toDo != null) {
            System.out.printf(UiMessage.ADD_TODO_MESSAGE + "\n", toDo.toString());
        }
    }

    public void printToDo(ToDo toDo) {
        System.out.println(toDo.toString());
    }

    public void printAllToDos(ToDoList toDoList) {
        if (toDoList.getSize() > 0) {
            System.out.println(UiMessage.LIST_TODOS_MESSAGE);
            System.out.println(toDoList.toString());
        } else {
            System.out.println(UiMessage.EMPTY_TODO_LIST_MESSAGE);
        }
    }

    public void printDeleteToDo(ToDo  toDo) {
        System.out.printf(UiMessage.DELETE_TODO_MESSAGE + "\n", toDo.toString());
    }

    public void printDoneToDo(ToDo  toDo) {
        System.out.printf(UiMessage.DONE_TODO_MESSAGE + "\n", toDo.toString());
    }

    public void printAddBookmark(Bookmark bookmark) {
        System.out.printf(UiMessage.ADD_BOOKMARK_MESSAGE + "\n", bookmark.toString());
    }

    public void printUpdateBookmark(Bookmark bookmark) {
        System.out.printf(UiMessage.UPDATE_BOOKMARK_MESSAGE + "\n", bookmark.toString());
    }

    public void printDeleteBookmark(Bookmark bookmark) {
        System.out.printf(UiMessage.DELETE_BOOKMARK_MESSAGE + "\n", bookmark.toString());
    }

    public void printAllBookmarks(BookmarkList bookmarkList) {
        if (bookmarkList.getSize() > 0) {
            System.out.println(UiMessage.LIST_BOOKMARKS_MESSAGE);
            System.out.println(bookmarkList.toString());
        } else {
            System.out.println(UiMessage.EMPTY_BOOKMARK_LIST_MESSAGE);
        }
    }

    //@@author
    public void printInvalidQuotesifyCommand() {
        System.out.println(UiMessage.INVALID_QUOTESIFY_COMMAND);
    }

    public void printHelpPage() {
        System.out.println("Feeling stuck? Well here are the things you can do with Quotesify v2.1:");
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("                                1. Book Management");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.ADD_BOOK_COMMAND);
        System.out.println(UiMessage.DONE_BOOK_COMMAND);
        System.out.println(UiMessage.DELETE_BOOK_COMMAND);
        System.out.println(UiMessage.EDIT_BOOK_COMMAND);
        System.out.println(UiMessage.LIST_BOOK_COMMAND);
        System.out.println(UiMessage.LIST_BOOK_DETAILS);
        System.out.println(UiMessage.LIST_BOOK_COMPLETE);
        System.out.println(UiMessage.FIND_BOOK_COMMAND);
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("                                2. Quote Management");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.ADD_QUOTE_COMMAND);
        System.out.println(UiMessage.DELETE_QUOTE_COMMAND);
        System.out.println(UiMessage.LIST_QUOTE_COMMAND);
        System.out.println(UiMessage.EDIT_QUOTE_COMMAND);
        System.out.println(UiMessage.FIND_QUOTE_COMMAND);
        System.out.println(UiMessage.ADD_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.DELETE_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.EDIT_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.LIST_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("                                3a. Bookmark Tracker");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.ADD_BOOKMARK_COMMAND);
        System.out.println(UiMessage.DELETE_BOOKMARK_COMMAND);
        System.out.println(UiMessage.LIST_BOOKMARK_COMMAND);
        System.out.println(UiMessage.EDIT_BOOKMARK_COMMAND);
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("                                3b. Task Tracker");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.ADD_TODO_COMMAND);
        System.out.println(UiMessage.DELETE_TODO_COMMAND);
        System.out.println(UiMessage.LIST_TODO_COMMAND);
        System.out.println(UiMessage.DONE_COMMAND);
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("                                4. Category Management");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.ADD_CATEGORY_COMMAND);
        System.out.println(UiMessage.DELETE_CATEGORY_COMMAND);
        System.out.println(UiMessage.LIST_CATEGORY_COMMAND);
        System.out.println(UiMessage.EDIT_CATEGORY_COMMAND);
        System.out.println(UiMessage.FIND_CATEGORY_COMMAND);
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("                                5. Rating System for books");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.ADD_RATING_COMMAND);
        System.out.println(UiMessage.DELETE_RATING_COMMAND);
        System.out.println(UiMessage.LIST_RATING_COMMAND);
        System.out.println(UiMessage.EDIT_RATING_COMMAND);
        System.out.println(UiMessage.FIND_RATING_COMMAND);
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("                                Other useful commands");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println("Show this help page:                                             " + "help");
        System.out.println("Quit Quotesify:                                                  " + "bye");
        System.out.println(UiMessage.DIVIDER_LINE);

        System.out.println("Remember: words in [] are optional, "
                + "and words in CAPS are your own input" + System.lineSeparator()
                + "Hope this helps!");

        System.out.println(System.lineSeparator() + "~ Your friends from Quotesify");
    }

    //@@author dozenmatter

    /**
     * Prints a list of add commands.
     */
    public void printListOfAddCommands() {
        System.out.println("Here is a list of Add commands you can do:");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.ADD_BOOK_COMMAND);
        System.out.println(UiMessage.ADD_BOOKMARK_COMMAND);
        System.out.println(UiMessage.ADD_QUOTE_COMMAND);
        System.out.println(UiMessage.ADD_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.ADD_CATEGORY_COMMAND);
        System.out.println(UiMessage.ADD_RATING_COMMAND);
        System.out.println(UiMessage.ADD_TODO_COMMAND);
    }

    /**
     * Prints a list of delete commands.
     */
    public void printListOfDeleteCommands() {
        System.out.println("Here is a list of Delete commands you can do:");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.DELETE_BOOK_COMMAND);
        System.out.println(UiMessage.DELETE_BOOKMARK_COMMAND);
        System.out.println(UiMessage.DELETE_QUOTE_COMMAND);
        System.out.println(UiMessage.DELETE_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.DELETE_CATEGORY_COMMAND);
        System.out.println(UiMessage.DELETE_RATING_COMMAND);
        System.out.println(UiMessage.DELETE_TODO_COMMAND);
    }

    /**
     * Prints a list of list commands.
     */
    public void printListOfListCommands() {
        System.out.println("Here is a list of List commands you can do:");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.LIST_BOOK_COMMAND);
        System.out.println(UiMessage.LIST_BOOK_DETAILS);
        System.out.println(UiMessage.LIST_BOOK_COMPLETE);
        System.out.println(UiMessage.LIST_BOOKMARK_COMMAND);
        System.out.println(UiMessage.LIST_QUOTE_COMMAND);
        System.out.println(UiMessage.LIST_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.LIST_CATEGORY_COMMAND);
        System.out.println(UiMessage.LIST_RATING_COMMAND);
        System.out.println(UiMessage.LIST_TODO_COMMAND);
    }

    /**
     * Prints a list of edit commands.
     */
    public void printListOfEditCommands() {
        System.out.println("Here is a list of Edit commands you can do:");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.EDIT_BOOK_COMMAND);
        System.out.println(UiMessage.EDIT_BOOKMARK_COMMAND);
        System.out.println(UiMessage.EDIT_QUOTE_COMMAND);
        System.out.println(UiMessage.EDIT_QUOTE_REFLECTION_COMMAND);
        System.out.println(UiMessage.EDIT_CATEGORY_COMMAND);
        System.out.println(UiMessage.EDIT_RATING_COMMAND);
    }

    /**
     * Prints a list of find commands.
     */
    public void printListOfFindCommands() {
        System.out.println("Here is a list of Find commands you can do:");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.FIND_BOOK_COMMAND);
        System.out.println(UiMessage.FIND_QUOTE_COMMAND);
        System.out.println(UiMessage.FIND_RATING_COMMAND);
        System.out.println(UiMessage.FIND_CATEGORY_COMMAND);
    }

    /**
     * Prints a list of done commands.
     */
    public void printDoneCommandUsage() {
        System.out.println("Here is a list of Done commands you can do:");
        System.out.println(UiMessage.DIVIDER_LINE);
        System.out.println(UiMessage.DONE_BOOK_COMMAND);
        System.out.println(UiMessage.DONE_COMMAND);
    }
}
