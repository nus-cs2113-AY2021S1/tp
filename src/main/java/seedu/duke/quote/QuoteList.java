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
            String quoteToAppend = '\"' + quote.getQuote() + '\"';
            String additionalInformation = "";
            if (quote.getAuthor() != null && quote.getReference() != null) {
                additionalInformation = " -" + quote.getAuthor().getName() + ", " + quote.getReference();
            } else if (quote.getAuthor() != null) {
                additionalInformation = " -" + quote.getReference();
            } else if (quote.getReference() != null) {
                additionalInformation = " -" + quote.getReference();
            }
            quotesToReturn += (quoteToAppend + additionalInformation + System.lineSeparator());
        }
        return quotesToReturn;
    }
}
