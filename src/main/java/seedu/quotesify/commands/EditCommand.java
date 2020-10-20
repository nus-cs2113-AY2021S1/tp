package seedu.quotesify.commands;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.category.CategoryParser;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

public class EditCommand extends Command {

    private String type;
    private String information;

    public EditCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_RATING:
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            editRating(ratings, ui);
            break;
        case TAG_BOOK:
            BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            editBook(books, ui);
            break;
        case TAG_CATEGORY:
            CategoryList categoryList = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
            editCategory(categoryList, ui);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            editQuote(quotes, ui);
            break;
        default:
            ui.printListOfEditCommands();
            break;
        }
        storage.save();
    }

    private void editQuote(QuoteList quotes, TextUi ui) {
        try {
            if (information.contains(FLAG_EDIT)) {
                int quoteNumToEdit = QuoteParser.getQuoteNumberToEdit(information, quotes);
                Quote oldQuote = quotes.getQuote(quoteNumToEdit);
                Quote editedQuote = QuoteParser.getEditedQuote(information);
                quotes.editQuote(editedQuote, quoteNumToEdit);
                ui.printEditQuote(oldQuote, editedQuote);
            } else {
                throw new QuotesifyException(ERROR_MISSING_EDIT_FLAG);
            }

        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void editBook(BookList books, TextUi ui) {
        try {
            String[] bookDetails = information.split(FLAG_EDIT, 2);
            if (bookDetails.length == 1) {
                bookDetails = new String[]{bookDetails[0], ""};
            }
            int bookIndex = Integer.parseInt(bookDetails[0].trim()) - 1;
            String newTitle = bookDetails[1].trim();
            if (newTitle.isEmpty()) {
                throw new QuotesifyException(ERROR_BOOK_TITLE_MISSING);
            }

            Book book = books.getBook(bookIndex);
            String oldTitle = book.getTitle();
            String author = book.getAuthor().getName();

            books.ensureNoSimilarBooks(newTitle, book.getAuthor().getName());
            book.setTitle(newTitle);
            ui.printEditBook(oldTitle, newTitle);

            // check ratings in rating list before editing the title.
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            int currentRatingOfBook = 0;
            for (Rating rating : ratings.getList()) {
                if (rating.getTitleOfRatedBook().equals(oldTitle)
                        && rating.getAuthorOfRatedBook().equals(author)) {
                    currentRatingOfBook = rating.getRating();
                    ratings.delete(ratings.getList().indexOf(rating));
                    break;
                }
            }

            if (currentRatingOfBook != 0) {
                ratings.add(new Rating(book, currentRatingOfBook));
            }

        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
        }
    }

    private void editRating(RatingList ratings, TextUi ui) {
        if (information.isEmpty()) {
            System.out.println(ERROR_RATING_MISSING_INPUTS);
            return;
        }

        String[] ratingDetails;
        String title;
        String author;
        try {
            ratingDetails = information.split(" ", 2);
            String[] titleAndAuthor = ratingDetails[1].split(Command.FLAG_AUTHOR, 2);
            title = titleAndAuthor[0].trim();
            author = titleAndAuthor[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }
        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingDetails[0]);
        Rating existingRating = checkIfRatingExists(ratings, title, author);
        boolean isValid = ((ratingScore != 0) && (existingRating != null));

        if (isValid) {
            existingRating.setRating(ratingScore);
            existingRating.getRatedBook().setRating(ratingScore);
            ui.printEditRatingToBook(ratingScore, title, author);
        }
    }

    private Rating checkIfRatingExists(RatingList ratings, String title, String author) {
        Rating existingRating = null;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(title)
                    && rating.getAuthorOfRatedBook().equals(author)) {
                existingRating = rating;
                break;
            }
        }

        if (existingRating == null) {
            System.out.println(ERROR_RATING_NOT_FOUND);
            return null;
        }
        return existingRating;
    }

    private void editCategory(CategoryList categoryList, TextUi ui) {
        try {
            String[] oldAndNewCategories = CategoryParser.getEditParameters(information);
            String oldCategory = oldAndNewCategories[0];
            String newCategory = oldAndNewCategories[1];

            Category category = categoryList.getCategoryByName(oldCategory);
            if (categoryList.isExistingCategory(newCategory)) {
                throw new QuotesifyException("Category [" + newCategory + "] already exists!");
            }

            category.setCategoryName(newCategory);
            ui.printEditCategory(oldCategory, newCategory);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
