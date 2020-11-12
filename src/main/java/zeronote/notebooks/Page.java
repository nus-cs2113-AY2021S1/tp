package zeronote.notebooks;

import zeronote.exceptions.InvalidTagException;

// @@author neilbaner

/**
 * Class to represent a page.
 */
public class Page {
    private String title;
    private String content;
    private String tag = "";

    public Page(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // @@author longngng

    /**
     * Gets the title of the page.
     *
     * @return the title of the page.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the page.
     *
     * @param title is the title of the page.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the page.
     *
     * @return the content of the page.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the page.
     *
     * @param content is the content the user has input on the page.
     */
    public void setContent(String content) {
        this.content = content;
    }

    //@@author Lusi711

    /**
     * Sets the tag of this page.
     *
     * @param tag the tag of the page.
     * @throws InvalidTagException when the user inputs an empty tag.
     */
    public void setTag(String tag) throws InvalidTagException {
        if (!tag.equals("")) {
            this.tag = tag;
        } else {
            throw new InvalidTagException("tag /t" + tag);
        }
    }

    /**
     * Gets the tag of this page.
     *
     * @return the tag of the page.
     */
    public String getTag() {
        return tag;
    }

    //@@author chuckiex3

    /**
     * Prints content stored in the page.
     */
    public void printPage() {
        System.out.println(content);
    }

    // @@author neilbaner
    public String serialize() {
        StringBuilder serialized = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        serialized.append(title);
        serialized.append(lineSeparator);
        serialized.append(content.replaceAll(System.lineSeparator(), "~~~"));
        serialized.append(lineSeparator);
        return serialized.toString();
    }
}
