package seedu.duke.ui;

import seedu.duke.book.Book;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.book.BookList;
import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.lists.ListManager;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.todo.ToDo;
import seedu.duke.todo.ToDoList;

import java.util.Random;
import java.util.Scanner;

public class TextUi {
    private static final String LOGO = "________                __                .__  _____       \n"
            + "\\_____  \\  __ __  _____/  |_  ____   _____|__|/ ____\\__.__.\n"
            + " /  / \\  \\|  |  \\/  _ \\   __\\/ __ \\ /  ___/  \\   __<   |  |\n"
            + "/   \\_/.  \\  |  (  <_> )  | \\  ___/ \\___ \\|  ||  |  \\___  |\n"
            + "\\_____\\ \\_/____/ \\____/|__|  \\___  >____  >__||__|  / ____|\n"
            + "       \\__>                      \\/     \\/          \\/    ";

    private static final String WELCOME_MESSAGE = "Welcome to Quotesify!";
    private static final String GOODBYE_MESSAGE = "Alright, have a nice day!";
    private static final String PROMPT_MESSAGE = "\nWhat would you like to do with Quotesify?";
    private static final String INVALID_QUOTESIFY_COMMAND = "I don't understand you." + System.lineSeparator()
            + "Maybe type \"help\" for usage instructions?";
    private static final String ADD_BOOK_MESSAGE = "The book [%s] has been added!";
    private static final String DELETE_BOOK_MESSAGE = "The book [%s] has been deleted!";
    private static final String LIST_BOOKS_MESSAGE = "Here is a list of all books:";
    private static final String LIST_BOOKS_BY_AUTHOR_MESSAGE = "Here is a list of books by %s:";
    private static final String ADD_CATEGORY_MESSAGE = "I have tagged [%s] category to \"%s\"!";
    private static final String DELETE_CATEGORY_MESSAGE = "I have removed [%s] category from \"%s\"!";
    private static final String CATEGORY_SIZE_MESSAGE = "You have a total of %d item(s) tagged as [%s].";
    private static final String LIST_CATEGORIES_MESSAGE = "Here is the list of all categories:";
    private static final String LIST_ALL_IN_CATEGORIES_MESSAGE = "Here are the list of items tagged as [%s]:";
    private static final String ADD_RATING_MESSAGE = "You have just rated %s %d star!";
    public static final String DELETE_RATING_MESSAGE = "Rating for %s has been deleted!";
    private static final String LIST_ALL_RATINGS_MESSAGE = "Planning to recommend some books?"
            + " Here are your rated books!";
    private static final String LIST_NO_RATINGS_FOUND_MESSAGE = "None of the books are rated yet!";
    private static final String LIST_SPECIFIED_RATING_MESSAGE = "Here are the books you rated as %d star!";
    private static final String LIST_SPECIFIED_RATING_NOT_FOUND_MESSAGE = "I can't find any books rated %d star!";
    private static final String ADD_TODO_MESSAGE = "The task [%s] has been added!";
    private static final String TODO_SIZE_MESSAGE = "You have a total of %d task(s) recorded.";
    private static final String LIST_TODOS_MESSAGE = "Here is the list of all task(s) recorded:";
    private static final String DELETE_TODO_MESSAGE = "The Task [%s] has been deleted!";
    private static final String DONE_TODO_MESSAGE = "The Task [%s] has been marked as done!";
    private static final String ADD_BOOKMARK_MESSAGE = "The bookmark [%s] has been added!";
    private static final String UPDATE_BOOKMARK_MESSAGE = "The bookmark [%s] has been updated";
    private static final String BOOKMARK_SIZE_MESSAGE = "You have a total of %d bookmark(s) recorded.";
    private static final String LIST_BOOKMARKS_MESSAGE = "Here is the list of all bookmark(s) recorded:";
    private static final String DELETE_BOOKMARKS_MESSAGE = "The bookmark [%s] has been removed!";
    private static final String LIST_ALL_QUOTES = "Here are all your quotes:";
    private static final String DELETE_QUOTE_MESSAGE = "The quote \"%s\" has been deleted!";
    private static final String LIST_NO_QUOTES_SAVED_MESSAGE = "You have no saved quotes!";
    private static final String LIST_NO_QUOTES_FOUND_MESSAGE = "I could not find any that matched your specifications.";
    private static final String LIST_QUOTES_BY_AUTHOR_MESSAGE = "Here is a list of quotes by %s:";
    private static final String LIST_QUOTES_BY_REFERENCE_MESSAGE = "Here is a list of quotes from %s:";
    private static final String LIST_QUOTES_BY_AUTHOR_AND_REFERENCE_MESSAGE = "Here is a list of quotes from %s by %s:";
    private static final String PRINT_RANDOM_QUOTE = "Before you continue, here's something:";

    private final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
    }

    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public String getUserCommand() {
        System.out.println(PROMPT_MESSAGE);
        if (in.hasNextLine()) {
            return in.nextLine().trim();
        }
        return "bye";
    }

    public void printAddBook(Book book) {
        System.out.printf(ADD_BOOK_MESSAGE + "\n", book.toString());
    }

    public void printDeleteBook(Book book) {
        System.out.printf(DELETE_BOOK_MESSAGE + "\n", book.toString());
    }

    public void printAllBooks(BookList bookList) {
        System.out.println(LIST_BOOKS_MESSAGE);
        System.out.println(bookList.toString());
    }

    public void printBooksByAuthor(BookList bookList, String authorName) {
        System.out.printf(LIST_BOOKS_BY_AUTHOR_MESSAGE + "\n", authorName);
        System.out.println(bookList.toString());
    }

    public void printAllQuotes(QuoteList quotes) {
        System.out.println(LIST_ALL_QUOTES);
        System.out.println(quotes);
    }

    public void printAddCategoryToBook(String bookTitle, String categoryName) {
        System.out.printf((ADD_CATEGORY_MESSAGE) + "\n", categoryName, bookTitle);
    }

    public void printAddCategoryToQuote(String quote, String categoryName) {
        System.out.printf((ADD_CATEGORY_MESSAGE) + "\n", categoryName, quote);
    }

    public void printRemoveCategoryFromBook(String bookTitle, String categoryName) {
        System.out.printf((DELETE_CATEGORY_MESSAGE) + "\n", categoryName, bookTitle);
    }

    public void printRemoveCategoryFromQuote(String quote, String categoryName) {
        System.out.printf((DELETE_CATEGORY_MESSAGE) + "\n", categoryName, quote);
    }

    public void printCategorySize(Category category) {
        System.out.printf((CATEGORY_SIZE_MESSAGE) + "\n", category.getSize(), category.getCategoryName());
    }

    public void printAllCategories(CategoryList categoryList) {
        System.out.println(LIST_CATEGORIES_MESSAGE);
        System.out.println(categoryList.toString());
    }

    public void printAllInCategory(Category category) {
        String categoryName = category.getCategoryName();
        System.out.printf(LIST_ALL_IN_CATEGORIES_MESSAGE + "\n", categoryName);

        BookList bookList = category.getBookList();
        System.out.println("BOOKS:");
        for (int i = 0; i < bookList.getList().size(); i++) {
            Book book = bookList.getList().get(i);
            System.out.println((i + 1) + ". " + book.toString());
        }

        System.out.println(System.lineSeparator());

        QuoteList quoteList = category.getQuoteList();
        System.out.println("QUOTES:");
        for (int i = 0; i < quoteList.getList().size(); i++) {
            Quote quote = quoteList.getList().get(i);
            System.out.println((i + 1) + ". " + quote.toString());
        }
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printAddRatingToBook(int ratingScore, String titleOfBookToRate) {
        System.out.printf((ADD_RATING_MESSAGE) + "\n", titleOfBookToRate, ratingScore);
    }

    public void printAllRatings(RatingList ratingList) {
        if (ratingList.getList().size() == 0) {
            System.out.println(LIST_NO_RATINGS_FOUND_MESSAGE);
            return;
        }
        System.out.println(LIST_ALL_RATINGS_MESSAGE);
        System.out.println(ratingList.toString());
    }

    public void printSpecifiedRating(RatingList ratings, int ratingToList) {
        boolean doesExist = false;
        for (Rating rating : ratings.getList()) {
            if (rating.getRating() == ratingToList) {
                doesExist = true;
                break;
            }
        }
        if (doesExist) {
            System.out.printf((LIST_SPECIFIED_RATING_MESSAGE) + "\n", ratingToList);
            for (Rating rating : ratings.getList()) {
                if (rating.getRating() == ratingToList) {
                    System.out.println(rating.getTitleOfRatedBook());
                }
            }
        } else {
            System.out.printf((LIST_SPECIFIED_RATING_NOT_FOUND_MESSAGE) + "\n", ratingToList);
            return;
        }
    }

    public void printAllQuotesByAuthor(QuoteList quoteList, String authorName) {
        boolean hasResult = false;
        if (quoteList.getSize() >= 1) {
            System.out.printf((LIST_QUOTES_BY_AUTHOR_MESSAGE) + "\n", authorName);
            for (Quote quote : quoteList.getList()) {
                if (quote.getAuthorName().equals(authorName)) {
                    System.out.print(quote.toString());
                    hasResult = true;
                }
            }
            if (!hasResult) {
                System.out.println(LIST_NO_QUOTES_FOUND_MESSAGE);
            }
        } else {
            System.out.println(LIST_NO_QUOTES_SAVED_MESSAGE);
        }
    }

    public void printAllQuotesByReference(QuoteList quoteList, String reference) {
        boolean hasResult = false;
        if (quoteList.getSize() >= 1) {
            System.out.printf((LIST_QUOTES_BY_REFERENCE_MESSAGE) + "\n", reference);
            for (Quote quote : quoteList.getList()) {
                if (quote.getReference().equals(reference)) {
                    System.out.print(quote.toString());
                    hasResult = true;
                }
            }
            if (!hasResult) {
                System.out.println(LIST_NO_QUOTES_FOUND_MESSAGE);
            }
        } else {
            System.out.println(LIST_NO_QUOTES_SAVED_MESSAGE);
        }
    }

    public void printAllQuotesByReferenceAndAuthor(QuoteList quoteList, String reference, String authorName) {
        boolean hasResult = false;
        if (quoteList.getSize() >= 1) {
            System.out.printf((LIST_QUOTES_BY_AUTHOR_AND_REFERENCE_MESSAGE) + "\n", reference, authorName);
            for (Quote quote : quoteList.getList()) {
                if (quote.getReference().equals(reference) && quote.getAuthorName().equals(authorName)) {
                    System.out.print(quote.toString());
                    hasResult = true;
                }
            }
            if (!hasResult) {
                System.out.println(LIST_NO_QUOTES_FOUND_MESSAGE);
            }
        } else {
            System.out.println(LIST_NO_QUOTES_SAVED_MESSAGE);
        }
    }

    public  void printDeleteQuote(String quote) {
        System.out.printf((DELETE_QUOTE_MESSAGE) + "\n", quote);
    }

    public void printDeleteRating(String bookTitle) {
        System.out.printf((DELETE_RATING_MESSAGE) + "\n", bookTitle);
    }

    public void printAddToDo(ToDo toDo) {
        System.out.printf(ADD_TODO_MESSAGE + "\n", toDo.toString());
    }

    public void printToDo(ToDo toDo) {
        System.out.println(toDo.toString());
    }

    public void printAllToDos(ToDoList toDoList) {
        System.out.println(LIST_TODOS_MESSAGE);
        System.out.println(toDoList.toString());
    }

    public void printDeleteToDo(ToDo  toDo) {
        System.out.printf(DELETE_TODO_MESSAGE + "\n", toDo.toString());
    }

    public void printDoneToDo(ToDo  toDo) {
        System.out.printf(DONE_TODO_MESSAGE + "\n", toDo.toString());
    }

    public void printAddBookmark(Bookmark bookmark) {
        System.out.printf(ADD_BOOKMARK_MESSAGE + "\n", bookmark.toString());
    }

    public void printUpdateBookmark(Bookmark bookmark) {
        System.out.printf(UPDATE_BOOKMARK_MESSAGE + "\n", bookmark.toString());
    }

    public void printDeleteBookmark(Bookmark bookmark) {
        System.out.printf(DELETE_BOOKMARKS_MESSAGE + "\n", bookmark.toString());
    }

    public void printAllBookmarks(BookmarkList bookmarkList) {
        System.out.println(LIST_BOOKMARKS_MESSAGE);
        System.out.println(bookmarkList.toString());
    }

    public void printRandomQuote() {
        QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        Random rand = new Random();
        try {
            int randomQuoteNumber = rand.nextInt(quotes.getSize() - 1);
            Quote quoteToPrint = quotes.getQuote(randomQuoteNumber);
            System.out.println(PRINT_RANDOM_QUOTE + System.lineSeparator() + quoteToPrint.toString());
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    public void printInvalidQuotesifyCommand() {
        System.out.println(INVALID_QUOTESIFY_COMMAND);
    }

    public void printHelpPage() {
        System.out.println("Feeling stuck? Well here are the things you can do with Quotesify:"
                + System.lineSeparator());
        System.out.println("1. Book Management");
        System.out.println("Add book: " + "add -b BOOK_TITLE /by AUTHOR");
        System.out.println("Delete book: " + "delete -b BOOK_TITLE /by AUTHOR");
        System.out.println("List books: " + "list -b [/by AUTHOR]");
        System.out.println(System.lineSeparator() + "2. Quote Management");
        System.out.println("Add quote: " + "add -q QUOTE [/from BOOK_TITLE] [/by AUTHOR]");
        System.out.println("Delete quote:" + "delete -q QUOTE_NUMBER");
        System.out.println("List quotes: " + "list -q [/by AUTHOR] [/from BOOK_TITLE]");
        System.out.println(System.lineSeparator() + "3a. Bookmark Tracker");
        System.out.println("Add bookmark: " + "bookmark -b BOOK_TITLE /pg PAGE_NUMBER");
        System.out.println("Update bookmark: " + "bookmark -b BOOK_TITLE /pg PAGE_NUMBER");
        System.out.println("Delete bookmark: " + "delete -bm BOOK_TITLE");
        System.out.println("List bookmarks: " + "list -bm");
        System.out.println(System.lineSeparator() + "3b. Task Tracker");
        System.out.println("Add task: " + "add -t TASK /by DEADLINE");
        System.out.println("Mark task as done: " + "done -t TASK_NUMBER");
        System.out.println("Delete task: " + "delete -t TASK_NUMBER");
        System.out.println("List tasks: " + "list -t");
        System.out.println(System.lineSeparator() + "4. Category Management");
        System.out.println("Add category: " + "add -c CATEGORY {[-b BOOK_TITLE] | [-q QUOTE_NUMBER]}");
        System.out.println("Delete category: " + "delete -c CATEGORY {[-b BOOK_TITLE] | [-q QUOTE_NUMBER]}");
        System.out.println("List categories: " + "list -c [CATEGORY]");
        System.out.println(System.lineSeparator() + "5. Rating System");
        System.out.println("Add rating: " + "add -r RATING_SCORE BOOK_TITLE");
        System.out.println("Delete rating: " + "delete -r BOOK_TITLE");
        System.out.println("List ratings: " + "list -r [/RATING_SCORE]");

        System.out.println(System.lineSeparator() + "Remember: words in [] are optional, "
                + "and words in CAPS are your own input" + System.lineSeparator()
                + "Hope this helps!");
    }
}
