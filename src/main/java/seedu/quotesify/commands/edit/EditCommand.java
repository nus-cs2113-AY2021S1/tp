package seedu.quotesify.commands.edit;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.category.CategoryParser;
import seedu.quotesify.commands.Command;
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

public class EditCommand extends Command {

    public String type;
    public String information;
    private String arguments;

    public EditCommand(String arguments) {
        this.arguments = arguments;
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
            new EditBookCommand(arguments).execute(ui, storage);
            break;
        case TAG_CATEGORY:
            CategoryList categoryList = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
            editCategory(categoryList, ui);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            editQuote(quotes, ui);
            break;
        case TAG_QUOTE_REFLECTION:
            QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            editQuoteReflection(quoteList, ui);
            break;
        default:
            ui.printListOfEditCommands();
            break;
        }
        storage.save();
    }

    private void editQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            if (information.contains(FLAG_EDIT)) {
                int quoteNumToEdit = QuoteParser.parseQuoteNumber(information, quoteList, Command.FLAG_EDIT);
                String editedReflection = QuoteParser.getEditedReflection(information);
                if (!editedReflection.isEmpty()) {
                    quoteList.updateReflection(editedReflection, quoteNumToEdit);
                    ui.printEditQuoteReflection(quoteList.getQuote(quoteNumToEdit), editedReflection);
                } else {
                    throw new QuotesifyException(ERROR_MISSING_REFLECTION);
                }
            } else {
                throw new QuotesifyException(ERROR_MISSING_EDIT_FLAG);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void editQuote(QuoteList quotes, TextUi ui) {
        try {
            if (information.contains(FLAG_EDIT)) {
                int quoteNumToEdit = QuoteParser.parseQuoteNumber(information, quotes, Command.FLAG_EDIT);
                Quote oldQuote = quotes.getQuote(quoteNumToEdit);
                Quote editedQuote = QuoteParser.getEditedQuote(information);
                quotes.updateQuote(editedQuote, quoteNumToEdit);
                ui.printEditQuote(oldQuote, editedQuote);
            } else {
                throw new QuotesifyException(ERROR_MISSING_EDIT_FLAG);
            }

        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
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

            if (categoryList.isExistingCategory(newCategory)) {
                throw new QuotesifyException("Category [" + newCategory + "] already exists!");
            }

            Category category = categoryList.getCategoryByName(oldCategory);
            category.setCategoryName(newCategory);
            editCategoryInBooksAndQuotes(oldCategory, newCategory);
            ui.printEditCategory(oldCategory, newCategory);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void editCategoryInBooksAndQuotes(String oldCategory, String newCategory) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        bookList.filterByCategory(oldCategory).getList().forEach(book -> {
            book.getCategories().remove(oldCategory);
            book.getCategories().add(newCategory);
        });

        quoteList.filterByCategory(oldCategory).getList().forEach(quote -> {
            quote.getCategories().remove(oldCategory);
            quote.getCategories().add(newCategory);
        });
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
