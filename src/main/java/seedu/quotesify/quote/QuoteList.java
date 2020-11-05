package seedu.quotesify.quote;

import org.json.simple.JSONArray;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents a list of quotes.
 */
public class QuoteList extends QuotesifyList<Quote> {
    private ArrayList<Quote> quotes = super.getList();
    public static final String DEFAULT_QUOTE = "Better days are coming, they are called Saturday and Sunday.";

    /**
     * Default constructor fot quote list.
     */
    public QuoteList() {
        super(new ArrayList<>());
    }

    /**
     * Constructor for quote list with existing list of quotes.
     *
     * @param quotes Array list of quotes.
     */
    public QuoteList(ArrayList<Quote> quotes) {
        super(quotes);
    }

    /**
     * Returns the quote object at the given index.
     *
     * @param index Index of the quote.
     * @return Quote object.
     */
    public Quote getQuote(int index) {
        return quotes.get(index);
    }

    /**
     * Returns the size of the quote list.
     *
     * @return Size of quote list.
     */
    public int getSize() {
        return quotes.size();
    }

    /**
     * Returns the index of a given quote.
     *
     * @param quote Quote object.
     * @return Index of the quote in the quote list.
     */
    public int getIndex(Quote quote) {
        return quotes.indexOf(quote);
    }

    /**
     * Updates a specified quote with a new quote.
     *
     * @param updatedQuote New quote object that will replace an existing quote.
     * @param quoteNumber Index of the quote in the list to be replaced.
     */
    public void updateQuote(Quote updatedQuote, int quoteNumber) {
        if (quotes.get(quoteNumber).getReflection() != null)  {
            updatedQuote.setReflection(quotes.get(quoteNumber).getReflection());
        }
        quotes.set(quoteNumber, updatedQuote);
    }

    /**
     * Adds a reflection to an existing quote.
     *
     * @param reflection Reflection to be added to the quote.
     * @param quoteNumber Index of the quote in the list where reflection will be added.
     * @throws QuotesifyException If quote already has a reflection.
     */
    public void addReflection(String reflection, int quoteNumber) throws QuotesifyException {
        Quote quote = quotes.get(quoteNumber);
        if (quote.getReflection() != null) {
            throw new QuotesifyException("Quote already has a reflection. Please use the edit command instead.");
        }
        quote.setReflection(reflection);
        quotes.set(quoteNumber, quote);
    }

    /**
     * Deletes the reflection of a quote.
     *
     * @param index Index of the quote where its reflection will be deleted.
     */
    public void deleteReflection(int index) {
        quotes.get(index).setReflectionNull();
    }

    /**
     * Updates the reflection of a quote with a new reflection.
     *
     * @param editedReflection New reflection that will replace an existing reflection.
     * @param quoteNumber Index of the quote in the list where its reflection will be replaced.
     */
    public void updateReflection(String editedReflection, int quoteNumber) {
        quotes.get(quoteNumber).setReflection(editedReflection);
    }

    /**
     * Compares the argument quote with all quotes in the list for duplicates.
     *
     * @param newQuote Quote to be compared with.
     * @return True if same quote is found in the quote list, false otherwise.
     */
    public boolean checkDuplicateQuote(Quote newQuote) {
        for (Quote quote : getList()) {
            String quoteToCheck = newQuote.getQuote().toLowerCase();
            if (quote.getQuote().toLowerCase().equals(quoteToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find all matching quotes from a keyword.
     *
     * @param quoteList List of quotes.
     * @param keyword User specified keyword.
     * @return A list of quotes matching the specified keyword.
     */
    public String findQuoteByKeyword(QuoteList quoteList, String keyword) {
        String listToReturn = "";
        int matchCounter = 0;
        for (Quote quote : quoteList.getList()) {
            if (quote.getQuote().toLowerCase().contains(keyword)) {
                listToReturn += (++matchCounter + ". " + quote.toString() + System.lineSeparator());
            } else if (quote.hasReference() && quote.getReference().toLowerCase().contains(keyword)) {
                listToReturn += (++matchCounter + ". " + quote.toString() + System.lineSeparator());
            } else if (quote.hasAuthor() && quote.getAuthorName().toLowerCase().contains(keyword)) {
                listToReturn += (++matchCounter + ". " + quote.toString() + System.lineSeparator());
            }
        }
        return listToReturn;
    }

    /**
     * Gets a random quote
     *
     * @return Random quote.
     */
    public String getRandomQuote() {
        try {
            Random rand = new Random();
            int randomQuoteNumber = rand.nextInt(getSize());
            Quote quoteToPrint = getQuote(randomQuoteNumber);
            return quoteToPrint.toString();
        } catch (IllegalArgumentException e) {
            return DEFAULT_QUOTE;
        }
    }

    /**
     * Returns a list of quotes by a specified author.
     *
     * @param authorName User specified author name.
     * @return List of quotes with matching author name.
     */
    public String getQuotesByAuthor(String authorName) {
        String listToReturn = "";
        int quoteCounter = 0;
        for (Quote quote : getList()) {
            if (quote.hasAuthor() && quote.getAuthorName().toLowerCase().equals(authorName)) {
                listToReturn += (++quoteCounter + ". " + quote.toString() + System.lineSeparator());
            }
        }
        return listToReturn;
    }

    /**
     * Returns a list of quotes from a specified reference.
     *
     * @param reference User specified reference title.
     * @return List of quotes with matching reference title.
     */
    public String getQuotesByReference(String reference) {
        String listToReturn = "";
        int quoteCounter = 0;
        for (Quote quote : getList()) {
            if (quote.hasReference() && quote.getReference().toLowerCase().equals(reference)) {
                listToReturn += (++quoteCounter + ". " + quote.toString() + System.lineSeparator());
            }
        }
        return listToReturn;
    }

    /**
     * Returns a list of quotes by a specified author and from a specified reference.
     *
     * @param reference User specified reference title.
     * @param authorName User specified author name.
     * @return List of quotes with matching reference title and matching author name.
     */
    public String getQuotesByReferenceAndAuthor(String reference, String authorName) {
        String listToReturn = "";
        int quoteCounter = 0;
        for (Quote quote : getList()) {
            if (quote.hasReference() && quote.getReference().toLowerCase().equals(reference)) {
                if (quote.hasAuthor() && quote.getAuthorName().toLowerCase().equals(authorName)) {
                    listToReturn += (++quoteCounter + ". " + quote.toString() + System.lineSeparator());
                }
            }
        }
        return listToReturn;
    }

    public QuoteList filterByCategory(String categoryName) {
        ArrayList<Quote> filteredQuotes = (ArrayList<Quote>) quotes.stream()
                .filter(quote -> {
                    ArrayList<String> categories = quote.getCategories();
                    return categories.contains(categoryName);
                }).collect(Collectors.toList());
        return new QuoteList(filteredQuotes);
    }

    /**
     * Adds a quote into the quote list.
     *
     * @param newQuote Quote to be added.
     */
    @Override
    public void add(Quote newQuote) {
        quotes.add(newQuote);
    }

    /**
     * Deletes a quote in the quote list.
     *
     * @param index Index of quote in the list to be deleted.
     */
    @Override
    public void delete(int index) {
        quotes.remove(index);
    }

    /**
     * Returns a list containing details of all the quotes contained in it.
     *
     * @return List of quote details.
     */
    @Override
    public String toString() {
        String quotesToReturn = "";
        for (int i = 0; i < getSize(); i++) {
            quotesToReturn += (i + 1 + ". " + quotes.get(i).toString()) + System.lineSeparator();
        }
        return quotesToReturn;
    }

    /**
     * Returns JSONArray of the quotes for storage.
     *
     * @return JSONArray.
     */
    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Quote quote : quotes) {
            list.add(quote.toJson());
        }
        return list;
    }
}
