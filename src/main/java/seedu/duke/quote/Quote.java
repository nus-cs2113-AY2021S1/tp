package seedu.duke.quote;

import seedu.duke.author.Author;
import seedu.duke.category.Category;

public class Quote {
    private Author author;
    private String quote;
    private Category category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        String quoteWithoutInformation = '\"' + quote + '\"';
        String quoteInformation = "";
        if (author != null && reference != null) {
            quoteInformation = " - " + author.getName() + ", (" + reference + ')';
        } else if (author != null) {
            quoteInformation = " - " + author.getName();
        } else if (reference != null) {
            quoteInformation = " - " + reference;
        }
        return quoteWithoutInformation + quoteInformation + System.lineSeparator();
    }
}