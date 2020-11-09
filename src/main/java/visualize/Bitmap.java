//@@author TomLBZ

package visualize;

import constants.Constants;

import java.util.ArrayList;

/**
 * The type Bitmap.
 */
public class Bitmap {

    private final ColoredString[] map;
    /**
     * The Width.
     */
    public final int width;
    /**
     * The Height.
     */
    public final int height;

    /**
     * Instantiates a new Bitmap.
     *
     * @param width  the width
     * @param height the height
     */
    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        map = new ColoredString[width * height];
        for (int i = 0; i < map.length; i++) {
            map[i] = new ColoredString(Sprite.IGNORE);
        }
    }

    /**
     * Sets pixel color.
     *
     * @param x the x
     * @param y the y
     * @param c the c
     * @throws IndexOutOfBoundsException the index out of bounds exception
     */
    public void setPixelColor(int x, int y, Color c) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException();
        }
        int index = x + y * width;
        map[index].setBackColor(c);
    }

    /**
     * Flush.
     *
     * @param c the c
     */
    public void flush(Color c) {
        for (ColoredString coloredString : map) {
            coloredString.setString(Sprite.SPACE);
            coloredString.setBackColor(c);
            coloredString.setForeColor(c);
        }
    }

    /**
     * Sets pixel text.
     *
     * @param x         the x
     * @param y         the y
     * @param character the character
     * @throws IndexOutOfBoundsException the index out of bounds exception
     */
    public void setPixelText(int x, int y, String character) throws  IndexOutOfBoundsException {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException();
        }
        int index = x + y * width;
        map[index].setString(character);
    }

    /**
     * Sets pixel text color.
     *
     * @param x the x
     * @param y the y
     * @param c the c
     * @throws IndexOutOfBoundsException the index out of bounds exception
     */
    public void setPixelTextColor(int x, int y, Color c) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException();
        }
        int index = x + y * width;
        map[index].setForeColor(c);
    }

    private boolean isInRange(int x, int y, int width, int height) {
        boolean isXInRange = x >= 0 || x < width;
        boolean isYInRange = y >= 0 || y < height;
        return isXInRange && isYInRange;
    }

    private boolean isAllInRange(ArrayList<int[]> points, int width, int height) {
        boolean output = true;
        if (points == null || points.isEmpty()) {
            return false;
        }
        for (int[] point: points) {
            if (point.length != 2) {
                continue;
            }
            output = output && isInRange(point[0], point[1], width, height);
        }
        return output;
    }

    private void checkValidInput(int x1, int y1, int x2, int y2, String string)
            throws IndexOutOfBoundsException, NullPointerException {
        ArrayList<int[]> points = new ArrayList<>();
        points.add(new int[]{x1, y1});
        points.add(new int[]{x2, y2});
        if (!isAllInRange(points, width, height)) {
            throw new IndexOutOfBoundsException();
        }
        if (string == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Draw line.
     *
     * @param x1         the x 1
     * @param y1         the y 1
     * @param x2         the x 2
     * @param y2         the y 2
     * @param string     the string
     * @param backColor  the back color
     * @param foreColor  the fore color
     * @param isAutoFill the is auto fill
     * @throws IndexOutOfBoundsException the index out of bounds exception
     * @throws NullPointerException      the null pointer exception
     */
    public void drawLine(
            int x1, int y1, int x2, int y2, String string,
            Color backColor, Color foreColor, boolean isAutoFill)
            throws IndexOutOfBoundsException, NullPointerException {
        checkValidInput(x1, y1, x2, y2, string);
        int index = 0;
        int dx;
        int dy;
        dx = x2 - x1;
        dy = y2 - y1;
        int x;
        int y;
        int p;  //parameter p of Bresenham's Line Algorithm
        x = x1; // set x to initial value
        y = y1; // set y to initial value
        p = 2 * dy - dx;
        while (x <= x2) {
            setAttributes(string, backColor, foreColor, index, x, y);
            if (p >= 0) {
                y++;
                p = p + 2 * dy - 2 * dx;
            } else {
                p = p + 2 * dy;
            }
            x++;
            index++;
            if (index == string.length() && isAutoFill) {
                index -= string.length();
            }
        }
    }

    private void setAttributes(String string, Color backColor, Color foreColor, int index, int x, int y) {
        String target = index < string.length() ? string.substring(index, index + 1) : String.valueOf(Constants.SPACE);
        if (!target.equals(Sprite.IGNORE)) {
            if (foreColor != null) {
                setPixelTextColor(x, y, foreColor);
            }
            if (backColor != null) {
                setPixelColor(x, y, backColor);
            }
            setPixelText(x, y, target);
        }
    }

    /**
     * Draw sprite.
     *
     * @param x1        the x 1
     * @param y1        the y 1
     * @param scaleX    the scale x
     * @param scaleY    the scale y
     * @param sprite    the sprite
     * @param backColor the back color
     * @param foreColor the fore color
     * @throws IndexOutOfBoundsException the index out of bounds exception
     * @throws NullPointerException      the null pointer exception
     */
    public void drawSprite(
            int x1, int y1, int scaleX, int scaleY, Sprite sprite,
            Color backColor, Color foreColor)
            throws IndexOutOfBoundsException, NullPointerException {
        int x2 = x1 + sprite.width * scaleX - 1;
        int y2 = y1 + sprite.height * scaleY - 1;
        checkValidInput(x1, y1, x2, y2, sprite.toString());
        if (scaleX == 0) {
            return;
        }
        int spriteX = 0;
        int spriteY = 0;
        for (int y = y1; y <= y2; y++) {
            if (y - y1 == (spriteY + 1) * scaleY) {
                spriteY++;
            }
            for (int x = x1; x <= x2; x++) {
                if (x - x1 == (spriteX + 1) * scaleX) {
                    spriteX++;
                }
                int spriteIndex = spriteX + sprite.width * spriteY;
                String strSprite = sprite.toString();
                setAttributes(strSprite, backColor, foreColor, spriteIndex, x, y);
            }
            spriteX = 0;
        }
    }

    /**
     * Get string.
     *
     * @return the string
     */
    public String get() {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            strBuilder.append(map[i].get());
            if ((i + 1) % width == 0) {
                strBuilder.append("\r\n");
            }
        }
        return strBuilder.toString();
    }
}
