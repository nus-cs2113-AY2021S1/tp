package seedu.duke.data.notebook;

/**
 * Represents a tag with respective color bind to it.
 */
public class Tag {

    private static final String COLOR_RED_STRING = "RED";
    private static final String COLOR_GREEN_STRING = "GREEN";
    private static final String COLOR_BLUE_STRING = "BLUE";
    private static final String COLOR_YELLOW_STRING = "YELLOW";
    private static final String COLOR_PURPLE_STRING = "PURPLE";
    private static final String COLOR_CYAN_STRING = "CYAN";
    private static final String COLOR_BLACK_STRING = "BLACK";
    private static final String COLOR_WHITE_STRING = "WHITE";

    /**
     * Types of color.
     */
    private enum TagColor {
        COLOR_WHITE("\u001B[30m"),
        COLOR_RED("\u001B[31m"),
        COLOR_GREEN("\u001B[32m"),
        COLOR_YELLOW("\u001B[33m"),
        COLOR_BLUE("\u001B[34m"),
        COLOR_PURPLE("\u001B[35m"),
        COLOR_CYAN("\u001B[36m"),
        COLOR_BLACK("\u001B[37m"),
        COLOR_CLEAR("\u001B[0m");

        private final String color;

        TagColor(String color) {
            this.color = color;
        }
    }

    private String tagName;
    private TagColor tagColor;

    public Tag(String tagName) {
        this.tagName = tagName;
        this.tagColor = TagColor.COLOR_BLACK;
    }

    /**
     * Overloaded constructor to take in the color of the tag.
     *
     * @param tagName name of the tag.
     * @param tagColor color of the tag.
     */
    public Tag(String tagName, String tagColor) {
        this.tagName = tagName;

        switch (tagColor.toUpperCase()) {
        case COLOR_RED_STRING:
            this.tagColor = TagColor.COLOR_RED;
            break;
        case "GREEN":
            this.tagColor = TagColor.COLOR_GREEN;
            break;
        case "BLUE":
            this.tagColor = TagColor.COLOR_BLUE;
            break;
        case "YELLOW":
            this.tagColor = TagColor.COLOR_YELLOW;
            break;
        case "PURPLE":
            this.tagColor = TagColor.COLOR_PURPLE;
            break;
        case "CYAN":
            this.tagColor = TagColor.COLOR_CYAN;
            break;
        case "WHITE":
            this.tagColor = TagColor.COLOR_WHITE;
            break;
        case "BLACK":
        default:
            this.tagColor = TagColor.COLOR_BLACK;
            break;
        }
    }

    /** Getter function for the name of the tag. */
    public String getTagName() {
        return tagName;
    }

    /** Setter function for the name of the tag. */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /** Getter function for the color of the tag. */
    public TagColor getTagColor() {
        return tagColor;
    }

    /** Setter function for the color of the tag. */
    public void setTagColor(TagColor tagColor) {
        this.tagColor = tagColor;
    }

    /**
     * Overrides the parent class toString function to return the tag with its tag color.
     *
     * @return tagName with its respective tag color.
     */
    @Override
    public String toString() {
        return tagColor.color + "[" + tagName + "]" + TagColor.COLOR_CLEAR;
    }
}
