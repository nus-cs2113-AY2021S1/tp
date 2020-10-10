package seedu.duke.quote;

import seedu.duke.lists.QuotesifyList;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

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
        for (Quote quote : quotes) {
            quotesToReturn += quote.toString();
        }
        return quotesToReturn;
    }
}
