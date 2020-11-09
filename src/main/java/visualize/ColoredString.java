//@@author TomLBZ

package visualize;

/**
 * The type Colored string.
 */
public class ColoredString {

    private String string;
    private Color foreColor;
    private Color backColor;

    /**
     * Instantiates a new Colored string.
     *
     * @param string the string
     */
    public ColoredString(String string) {
        setString(string);
        if (!string.equals(Sprite.IGNORE)) {
            setForeColor(Color.White);
            setBackColor(Color.Black);
        }
    }

    /**
     * Sets string.
     *
     * @param string the string
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Sets fore color.
     *
     * @param color the color
     */
    public void setForeColor(Color color) {
        foreColor = color;
    }

    /**
     * Sets back color.
     *
     * @param color the color
     */
    public void setBackColor(Color color) {
        backColor = color;
    }

    /**
     * Bold.
     */
    public void bold() {
        string = "\u001b[1m" + string;
    }

    /**
     * Underline.
     */
    public void underline() {
        string = "\u001b[4m" + string;
    }

    /**
     * Reversed color.
     */
    public void reversedColor() {
        string = "\u001b[7m" + string;
    }

    /**
     * Sub string colored string.
     *
     * @param start the start
     * @param end   the end
     * @return the colored string
     */
    public ColoredString subString(int start, int end) {
        ColoredString output = new ColoredString(string.substring(start, end));
        output.setForeColor(foreColor);
        output.setBackColor(backColor);
        return output;
    }

    /**
     * Get string.
     *
     * @return the string
     */
    public String get() {
        String strForeColor = "";
        String strBackColor = "";
        if (foreColor != null) {
            strForeColor = "\u001b[38;5;" + foreColor.code + "m";
        }
        if (backColor != null) {
            strBackColor = "\u001b[48;5;" + backColor.code + "m";
        }
        return strForeColor + strBackColor + string.replace(Sprite.IGNORE, Sprite.SPACE) + "\u001b[0m";
    }

    @Override
    public String toString() {
        return get();
    }
}
