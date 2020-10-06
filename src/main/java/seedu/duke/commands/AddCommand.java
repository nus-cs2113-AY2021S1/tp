package seedu.duke.commands;

import seedu.duke.author.Author;
import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.lists.ListManager;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;
import java.util.Stack;

public class AddCommand extends Command {
    public static final int RATING_ONE = 1;
    public static final int RATING_FIVE = 5;
    private static final String TAG_BOOK = "-b";
    private static final String TAG_QUOTE = "-q";
    private static final String TAG_RATING = "-r";
    private static final String TAG_CATEGORY = "-c";

    private static final String ERROR_INVALID_QUOTE_NUM = "Invalid quote number specified!";

    private String type;
    private String information;

    public AddCommand(String arguments) {
        String[] details = arguments.split(" ", 2);
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_BOOK:
            BookList books = (BookList) listManager.getList(ListManager.BOOK_LIST);
            Book newBook = addBook(books);
            ui.printAddBook(newBook);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
            addQuote(quotes);
            ui.printAllQuotes(quotes);
            break;
        case TAG_CATEGORY:
            CategoryList categories = (CategoryList) listManager.getList(ListManager.CATEGORY_LIST);
            addCategoryToBookAndQuote(categories, ui, listManager);
            break;
        case TAG_RATING:
            RatingList ratings = (RatingList) listManager.getList(ListManager.RATING_LIST);
            BookList existingBooks = (BookList) listManager.getList(ListManager.BOOK_LIST);
            addRating(ratings, existingBooks);
            break;
        default:
        }
    }

    private Book addBook(BookList books) {
        String[] titleAndAuthor = information.split("/by", 2);
        Author author = new Author(titleAndAuthor[1].trim());
        Book newBook = new Book(author, titleAndAuthor[0].trim());
        books.add(newBook);

        return newBook;
    }

    private void addQuote(QuoteList quotes) {
        if (information.contains("/from") && information.contains("/by")) {
            String[] quoteAndInformation = information.split("/from", 2);
            String[] referenceAndAuthor = quoteAndInformation[1].split("/by", 2);
            Author author = new Author(referenceAndAuthor[1].trim());
            quotes.add(new Quote(quoteAndInformation[0].trim(), referenceAndAuthor[0].trim(), author));
        } else if (information.contains("/from")) {
            String[] quoteAndReference = information.split("/from", 2);
            quotes.add(new Quote(quoteAndReference[0].trim(), quoteAndReference[1].trim()));
        } else if (information.contains("/by")) {
            String[] quoteAndAuthor = information.split("/by", 2);
            quotes.add(new Quote(quoteAndAuthor[0].trim(), quoteAndAuthor[1].trim()));
        } else {
            quotes.add(new Quote(information.trim()));
        }

    }

    private void addCategoryToBookAndQuote(CategoryList categories, TextUi ui, ListManager listManager) {
        String[] tokens = information.split(" ");
        Stack<String> stack = convertStringArrayToStack(tokens);
        executeParameters(categories, stack, ui, listManager);
    }

    private Stack<String> convertStringArrayToStack(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            stack.push(token);
        }
        return stack;
    }

    private void executeParameters(CategoryList categories, Stack<String> stack, TextUi ui, ListManager listManager) {
        String categoryName;
        String bookTitle = null;
        int quoteNum = -1;

        try {
            String object = "";
            while (!stack.empty()) {
                String item = stack.pop();
                if (item.equals(TAG_BOOK)) {
                    bookTitle = object.trim();
                    object = "";
                    continue;
                } else if (item.equals(TAG_QUOTE)) {
                    quoteNum = Integer.parseInt(object.trim());
                    object = "";
                    continue;
                }
                object = item + " " + object;
            }
            categoryName = object.trim();

            if (!categories.doesCategoryExist(categoryName)) {
                categories.add(new Category(categoryName));
            }

            Category category = categories.getCategoryByName(categoryName);
            if (addCategoryToBook(category, bookTitle, listManager)) {
                ui.printAddCategoryToBook(bookTitle, category.getCategoryName());
            }

            if (addCategoryToQuote(category, quoteNum, listManager)) {
                QuoteList quoteList = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
                ArrayList<Quote> quotes = quoteList.getList();
                ui.printAddCategoryToQuote(quotes.get(quoteNum).getQuote(), category.getCategoryName());
            }

        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        }
    }

    private boolean addCategoryToBook(Category category, String bookTitle, ListManager listManager) {
        if (bookTitle == null || category == null) {
            return false;
        }

        BookList bookList = (BookList) listManager.getList(ListManager.BOOK_LIST);
        ArrayList<Book> books = bookList.getList();
        for (Book book : books) {
            if (book.getTitle().equals(bookTitle)) {
                book.setCategory(category);
                return true;
            }
        }
        return false;
    }

    private boolean addCategoryToQuote(Category category, int quoteNum, ListManager listManager) {
        if (quoteNum == -1 || category == null) {
            return false;
        }

        QuoteList quoteList = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
        ArrayList<Quote> quotes = quoteList.getList();
        try {
            quotes.get(quoteNum).setCategory(category);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
            return false;
        }
        return true;
    }

    private void addRating(RatingList ratings, BookList existingBooks) {
        String[] ratingDetails = information.split(" ", 2);
        int ratingScore;
        try {
            ratingScore = Integer.parseInt(ratingDetails[0]);
        } catch (NumberFormatException e) {
            System.out.println("Sorry I don't understand you");
            return;
        }
        String titleOfBookToRate = ratingDetails[1].trim();
        if (!(ratingScore >= RATING_ONE && ratingScore <= RATING_FIVE)) {
            System.out.println("That score is out of our range my friend");
            return;
        }

        boolean isRated = false;
        for (int i = 0; i < ratings.getList().size(); i++) {
            if (ratings.getList().get(i).getTitleOfRatedBook().equals(titleOfBookToRate)) {
                isRated = true;
                break;
            }
        }

        if (isRated) {
            System.out.println("This book has already been rated");
            return;
        }

        boolean doesExist = false;
        for (int i = 0; i < existingBooks.getList().size(); i++) {
            if (existingBooks.getList().get(i).getTitle().equals(titleOfBookToRate)) {
                doesExist = true;
                break;
            }
        }

        if (doesExist) {
            ratings.add(new Rating(ratingScore, titleOfBookToRate));
            System.out.println("You have just rated " + titleOfBookToRate
                    + " " + ratingScore + " star!");
            System.out.println(ratings.toString());
        } else {
            System.out.println("I can't find this book to rate!");
        }
    }

    public boolean isExit() {
        return false;
    }
}
