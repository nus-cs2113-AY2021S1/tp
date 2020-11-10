package seedu.notus.data.tag;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;

//@@author Chongjx
/**
 * Represents a tag with respective color bind to it.
 */
public class Tag {

    public static final String COLOR_RED_STRING = "RED";
    public static final String COLOR_GREEN_STRING = "GREEN";
    public static final String COLOR_BLUE_STRING = "BLUE";
    public static final String COLOR_YELLOW_STRING = "YELLOW";
    public static final String COLOR_MAGENTA_STRING = "PURPLE";
    public static final String COLOR_CYAN_STRING = "CYAN";
    public static final String COLOR_WHITE_STRING = "WHITE";

    private String tagName;
    private Attribute tagAttribute;

    /**
     * Overloaded constructor to take in the color of the tag.
     *
     * @param tagName Name of the tag to be set.
     * @param tagColor Color of the tag to be set.
     */
    public Tag(String tagName, String tagColor) {
        setTagName(tagName);
        setTagColor(tagColor);
    }

    /**
     * Overloaded constructor to take in the ansi value of the color of the tag.
     *
     * @param tagName Name of the tag to be set.
     * @param attribute Attribute of the color to be set.
     */
    public Tag(String tagName, int attribute) {
        setTagName(tagName);
        setTagAttribute(Attribute.BACK_COLOR(attribute));
    }

    /** Getter function for the name of the tag. */
    public String getTagName() {
        return tagName;
    }

    /** Setter function for the name of the tag. */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /** Setter function for the color of the tag. */
    public void setTagColor(String tagColor) {
        switch (tagColor.toUpperCase()) {
        case COLOR_RED_STRING:
            this.tagAttribute = Attribute.BRIGHT_RED_TEXT();
            break;
        case COLOR_GREEN_STRING:
            this.tagAttribute = Attribute.BRIGHT_GREEN_TEXT();
            break;
        case COLOR_BLUE_STRING:
            this.tagAttribute = Attribute.BRIGHT_BLUE_TEXT();
            break;
        case COLOR_YELLOW_STRING:
            this.tagAttribute = Attribute.BRIGHT_YELLOW_TEXT();
            break;
        case COLOR_MAGENTA_STRING:
            this.tagAttribute = Attribute.BRIGHT_MAGENTA_TEXT();
            break;
        case COLOR_CYAN_STRING:
            this.tagAttribute = Attribute.BRIGHT_CYAN_TEXT();
            break;
        case COLOR_WHITE_STRING:
        default:
            this.tagAttribute = Attribute.BRIGHT_WHITE_TEXT();
            break;
        }
    }

    public Attribute getTagAttribute() {
        return tagAttribute;
    }

    public void setTagAttribute(Attribute tagColor) {
        this.tagAttribute = tagColor;
    }

    /**
     * Overrides the parent class toString function to return the tag with its tag color.
     *
     * @return tagName with its respective tag attribute.
     */
    @Override
    public String toString() {
        return colorize("[" + tagName + "]", tagAttribute);
    }

    public String toSaveString() {
        String colorString = "";

        if (tagAttribute.toString().equals(Attribute.BRIGHT_RED_TEXT().toString())) {
            colorString = COLOR_RED_STRING;
        } else if (tagAttribute.toString().equals(Attribute.BRIGHT_GREEN_TEXT().toString())) {
            colorString = COLOR_GREEN_STRING;
        } else if (tagAttribute.toString().equals(Attribute.BRIGHT_BLUE_TEXT().toString())) {
            colorString = COLOR_BLUE_STRING;
        } else if (tagAttribute.toString().equals(Attribute.BRIGHT_YELLOW_TEXT().toString())) {
            colorString = COLOR_YELLOW_STRING;
        } else if (tagAttribute.toString().equals(Attribute.BRIGHT_CYAN_TEXT().toString())) {
            colorString = COLOR_CYAN_STRING;
        } else if (tagAttribute.toString().equals(Attribute.BRIGHT_MAGENTA_TEXT().toString())) {
            colorString = COLOR_MAGENTA_STRING;
        } else if (tagAttribute.toString().equals(Attribute.BRIGHT_WHITE_TEXT().toString())) {
            colorString = COLOR_WHITE_STRING;
        }
        return tagName + " " + colorString;
    }
}
