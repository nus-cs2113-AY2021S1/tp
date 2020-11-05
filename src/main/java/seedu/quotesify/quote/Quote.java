package seedu.quotesify.quote;

import org.json.simple.JSONObject;
import seedu.quotesify.author.Author;
import seedu.quotesify.parser.JsonSerializer;

import java.util.ArrayList;

/**
 * Represents a quote
 */
public class Quote implements JsonSerializer {
    private Author author;
    private String quote;
    private ArrayList<String> categories = new ArrayList<>();
    private String reference;
    private String reflection;

    /**
     * Constructor for quote.
     *
     * @param quote Quote to be initialised.
     */
    public Quote(String quote) {
        this.quote = quote;
    }

    /**
     * Constructor for quote.
     *
     * @param quote Quote to be initialised.
     * @param author Author of the quote.
     */
    public Quote(String quote, Author author) {
        this.quote = quote;
        this.author = author;
    }

    /**
     * Constructor for quote.
     *
     * @param quote Quote to be initialised.
     * @param reference Reference title of where the quote is from.
     */
    public Quote(String quote,String reference) {
        this.quote = quote;
        this.reference = reference;
    }

    /**
     * Constructor for quote.
     *
     * @param quote Quote to be initialised.
     * @param reference Reference title of where the quote is from.
     * @param author Author of the quote.
     */
    public Quote(String quote, String reference, Author author) {
        this.quote = quote;
        this.author = author;
        this.reference = reference;
    }

    /**
     * Constructor for quote.
     *
     * @param author Author of the quote.
     * @param quote Quote to be initialised.
     * @param categories List of categories tagged.
     * @param reference Reference title of where the quote is from.
     */
    public Quote(Author author, String quote, ArrayList<String> categories, String reference) {
        this.author = author;
        this.quote = quote;
        this.categories = categories;
        this.reference = reference;
    }

    /**
     * Constructor for quote.
     *
     * @param author Author of the quote.
     * @param quote Quote to be initialised.
     * @param categories List of categories tagged.
     * @param reference Reference title of where the quote is from.
     * @param reflection Reflection to be added to the quote.
     */
    public Quote(Author author, String quote, ArrayList<String> categories, String reference, String reflection) {
        this.author = author;
        this.quote = quote;
        this.categories = categories;
        this.reference = reference;
        this.reflection = reflection;
    }

    /**
     * Returns the quote string.
     *
     * @return quote The quote.
     */
    public String getQuote() {
        return quote;
    }

    /**
     * Returns the author object of the quote.
     *
     * @return Author object.
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Returns the author name of the quote.
     *
     * @return Author name.
     */
    public String getAuthorName() {
        return author.getName();
    }

    /**
     * Checks if the quote has an author.
     *
     * @return True if author exists, false otherwise.
     */
    public boolean hasAuthor() {
        if (author != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the reference title of the quote.
     *
     * @return Reference title.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Checks if the quote has a reference title.
     *
     * @return True if reference exists, false otherwise.
     */
    public boolean hasReference() {
        if (reference != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the reflection of the quote.
     *
     * @return Quote reflection.
     */
    public String getReflection() {
        return reflection;
    }

    /**
     * Sets the reflection of a quote.
     *
     * @param reflection Reflection to be added to quote.
     */
    public void setReflection(String reflection) {
        this.reflection = reflection;
    }

    /**
     * Sets the reflection of a quote to null.
     */
    public void setReflectionNull() {
        reflection = null;
    }

    /**
     * Returns the list of categories.
     *
     * @return Category list.
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * Sets the category of a quote.
     *
     * @param categories List of categories.
     */
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    /**
     * Returns details of a quote.
     *
     * @return Quote details.
     */
    @Override
    public String toString() {
        String quoteWithoutInformation = '\"' + quote + '\"';
        String quoteInformation = "";

        if (author != null && reference != null) {
            quoteInformation = " - by " + author.getName() + ", (from " + reference + ')';
        } else if (author != null) {
            quoteInformation = " - by " + author.getName();
        } else if (reference != null) {
            quoteInformation = " - from " + reference;
        }

        if (reflection != null) {
            quoteInformation += " [R]";
        }
        return quoteWithoutInformation + quoteInformation;
    }

    /**
     * Returns a JSON object of the category.
     *
     * @return JSON object.
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        if (hasAuthor()) {
            json.put("author", this.getAuthor().toJson());
        } else {
            json.put("author", null);
        }
        json.put("quote", this.getQuote());
        json.put("categories", this.getCategories());
        json.put("reference", this.getReference());
        json.put("reflection", this.getReflection());
        return json;
    }
}