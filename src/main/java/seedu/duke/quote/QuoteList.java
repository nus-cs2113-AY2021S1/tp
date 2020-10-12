package seedu.duke.quote;

import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.category.Category;
import seedu.duke.lists.QuotesifyList;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class QuoteList extends QuotesifyList<Quote> {
    private ArrayList<Quote> quotes = super.getList();

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
            quotesToReturn += (i + 1 + ". " + quotes.get(i).toString());
        }
        return quotesToReturn;
    }

    public QuoteList filterByCategory(String categoryName) {
        try {
            ArrayList<Quote> filteredQuotes = (ArrayList<Quote>) quotes.stream()
                    .filter(quote -> {
                        Category category = quote.getCategory();
                        return category.getCategoryName().equals(categoryName);
                    }).collect(Collectors.toList());
            return new QuoteList(filteredQuotes);
        } catch (NullPointerException e) {
            // Do nothing
        }
        return new QuoteList();
    }
}
