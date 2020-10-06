package seedu.duke.quote;

import seedu.duke.lists.QuotesifyList;

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
        return "";
    }

    public void printAllQuotes() {
        System.out.println("Here are your quotes:");
        for (Quote quote : quotes) {
            String quoteToPrint = '\"' + quote.getQuote() + '\"';
            String additionalInformation = "";
            if (quote.getAuthor() != null && quote.getReference() != null) {
                additionalInformation = " -" + quote.getAuthor().getName() + ", " + quote.getReference();
            }else if (quote.getAuthor() != null) {
                additionalInformation = " -" + quote.getReference();
            } else if (quote.getReference() != null) {
                additionalInformation = " -" + quote.getReference();
            }
            quoteToPrint += additionalInformation;
            System.out.println(quoteToPrint);
        }
    }
}
