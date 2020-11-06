package seedu.quotesify.commands.list;

import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Represents the list quote command.
 */
public class ListQuoteCommand extends ListCommand {

    /**
     * Constructor for the list quote command.
     *
     * @param arguments User input argument.
     */
    public ListQuoteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the list quote command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        listQuotes(quoteList, ui);
    }

    /**
     * Determines which list method to call depending on user input.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void listQuotes(QuoteList quoteList, TextUi ui) {
        try {
            if ((information.isEmpty())) {
                listAllQuotes(quoteList, ui);
            } else if (information.contains(FLAG_AUTHOR) && information.contains(FLAG_REFERENCE)) {
                listQuoteWithAuthorAndReference(quoteList, ui);
            } else if (information.contains(FLAG_AUTHOR)) {
                listQuoteWithAuthor(quoteList, ui);
            } else if (information.contains(FLAG_REFERENCE)) {
                listQuoteWithReference(quoteList, ui);
            } else {
                throw new QuotesifyException(ERROR_LIST_UNKNOWN_COMMAND);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * List all quotes from the quote list.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void listAllQuotes(QuoteList quoteList, TextUi ui) {
        ui.printAllQuotes(quoteList);
    }

    /**
     * List all quotes with the specified author name and reference title from the quote list.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     * @throws QuotesifyException If the augments provided by the user is incorrect.
     */
    private void listQuoteWithAuthorAndReference(QuoteList quoteList, TextUi ui) throws QuotesifyException {
        HashMap<String, String> referenceAndAuthor = QuoteParser.getReferenceAndAuthor(information.substring(1));
        String reference = referenceAndAuthor.get(Command.REFERENCE_KEYWORD);
        String authorName = referenceAndAuthor.get(Command.AUTHORNAME_KEYWORD);
        ui.printAllQuotesByReferenceAndAuthor(quoteList, reference, authorName);
    }

    /**
     * Lists all quotes with the specified author name from the quote list.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     * @throws QuotesifyException If the augments provided by the user is incorrect.
     */
    private void listQuoteWithAuthor(QuoteList quoteList, TextUi ui) throws QuotesifyException {
        String authorName = QuoteParser.parseListCommand(information, FLAG_AUTHOR);
        ui.printAllQuotesByAuthor(quoteList, authorName);
    }

    /**
     * Lists all quotes with the specified reference title from the quote list.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     * @throws QuotesifyException If the augments provided by the user is incorrect.
     */
    private void listQuoteWithReference(QuoteList quoteList, TextUi ui) throws QuotesifyException {
        String reference = QuoteParser.parseListCommand(information, FLAG_REFERENCE);
        ui.printAllQuotesByReference(quoteList, reference);
    }
}
