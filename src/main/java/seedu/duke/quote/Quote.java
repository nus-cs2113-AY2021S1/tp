package seedu.duke.quote;

import seedu.duke.author.Author;
import seedu.duke.category.Category;

public class Quote {
    private Author author;
    private String quote;
    private String reference;

    public Quote(String quote) {
        this.quote = quote;
    }
    public Quote(String quote, Author author) {
        this.quote = quote;
        this.author = author;
    }

    public Quote(String quote,String reference) {
        this.quote = quote;
        this.reference = reference;
    }

    public Quote(String quote, String reference, Author author) {
        this.quote = quote;
        this.author = author;
        this.reference = reference;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
