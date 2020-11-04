package seedu.quotesify.quote;

import org.json.simple.JSONArray;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class QuoteList extends QuotesifyList<Quote> {
    private ArrayList<Quote> quotes = super.getList();

    public static final String DEFAULT_QUOTE = "Better days are coming, they are called Saturday and Sunday.";

    public QuoteList() {
        super(new ArrayList<>());
    }

    public QuoteList(ArrayList<Quote> quotes) {
        super(quotes);
    }

    public Quote getQuote(int index) {
        return quotes.get(index);
    }

    public int getSize() {
        return quotes.size();
    }

    public int getIndex(Quote quote) {
        return quotes.indexOf(quote);
    }

    public void updateQuote(Quote quote, int quoteNumber) {
        if (quotes.get(quoteNumber).getReflection() != null)  {
            quote.setReflection(quotes.get(quoteNumber).getReflection());
        }
        quotes.set(quoteNumber, quote);
    }

    public Quote addReflection(String reflection, int quoteNumber) {
        Quote quote = quotes.get(quoteNumber);
        quote.setReflection(reflection);
        quotes.set(quoteNumber, quote);
        return quote;
    }

    public void deleteReflection(int index) {
        quotes.get(index).setReflectionNull();
    }

    public void updateReflection(String editedReflection, int quoteNumber) {
        quotes.get(quoteNumber).setReflection(editedReflection);
    }

    public boolean checkDuplicateQuote(Quote newQuote) {
        for (Quote quote : getList()) {
            String quoteToCheck = newQuote.getQuote().toLowerCase();
            if (quote.getQuote().toLowerCase().equals(quoteToCheck)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Quote newQuote) {
        quotes.add(newQuote);
    }

    @Override
    public void delete(int index) {
        quotes.remove(index);
    }

    @Override
    public String toString() {
        String quotesToReturn = "";
        for (int i = 0; i < getSize(); i++) {
            quotesToReturn += (i + 1 + ". " + quotes.get(i).toString()) + System.lineSeparator();
        }
        return quotesToReturn;
    }

    public QuoteList filterByCategory(String categoryName) {
        ArrayList<Quote> filteredQuotes = (ArrayList<Quote>) quotes.stream()
                .filter(quote -> {
                    ArrayList<String> categories = quote.getCategories();
                    return categories.contains(categoryName);
                }).collect(Collectors.toList());
        return new QuoteList(filteredQuotes);
    }

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

    public String getAllQuotesByAuthor(QuoteList quoteList, String authorName) {
        String listToReturn = "";
        int quoteCounter = 0;
        for (Quote quote : quoteList.getList()) {
            if (quote.hasAuthor() && quote.getAuthorName().toLowerCase().equals(authorName)) {
                listToReturn += (++quoteCounter + ". " + quote.toString() + System.lineSeparator());
            }
        }
        return listToReturn;
    }

    public String getAllQuotesByReference(QuoteList quoteList, String reference) {
        String listToReturn = "";
        int quoteCounter = 0;
        for (Quote quote : quoteList.getList()) {
            if (quote.hasReference() && quote.getReference().toLowerCase().equals(reference)) {
                listToReturn += (++quoteCounter + ". " + quote.toString() + System.lineSeparator());
            }
        }
        return listToReturn;
    }

    public String getAllQuotesByReferenceAndAuthor(QuoteList quoteList, String reference, String authorName) {
        String listToReturn = "";
        int quoteCounter = 0;
        for (Quote quote : quoteList.getList()) {
            if (quote.hasReference() && quote.getReference().toLowerCase().equals(reference)) {
                if (quote.hasAuthor() && quote.getAuthorName().toLowerCase().equals(authorName)) {
                    listToReturn += (++quoteCounter + ". " + quote.toString() + System.lineSeparator());
                }
            }
        }
        return listToReturn;
    }

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

    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Quote quote : quotes) {
            list.add(quote.toJson());
        }
        return list;
    }
}
