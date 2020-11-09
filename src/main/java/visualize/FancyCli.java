//@@author TomLBZ

package visualize;

import command.action.FocusAction;
import constants.Constants;
import data.Data;
import data.Item;
import messages.MessageOptions;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Fancy cli.
 */
public class FancyCli extends Cli {

    private final Bitmap bmpList;
    private final Bitmap bmpSel;
    private final Color listBackground = Color.DarkBlue;
    private final Color selBackground = Color.DarkMagenta;
    private final Color foreground = Color.getFromCode(255); // greyscale white
    private final Color listBarColor = Color.DarkGreen;
    private final Color selBarColor = Color.LightCyan;
    private final Color selBarText = Color.getFromCode(232); // greyscale black
    private final int maxX = Constants.BITMAP_W - 1;
    private final int maxListY = Constants.BITMAP_LIST_H - 1;
    private final int maxSelY = Constants.BITMAP_SEL_H - 1;
    private int currentColor;
    private static final Logger LOGGER = Logger.getLogger(FocusAction.class.getName());

    private String[] listString;
    private String[] selString;
    private int listIndex;
    private int selIndex;

    private boolean listFlipped = false;
    private boolean selFlipped = false;

    /**
     * Instantiates a new Fancy cli.
     *
     * @param stream the stream
     * @param input  the input
     */
    public FancyCli(PrintStream stream, InputStream input) {
        super(stream, input);
        currentColor = 29;
        this.bmpList = new Bitmap(Constants.BITMAP_W, Constants.BITMAP_LIST_H);
        this.bmpSel = new Bitmap(Constants.BITMAP_W, Constants.BITMAP_SEL_H);
        listString = new String[0];
        selString = new String[0];
        listIndex = 0;
        selIndex = 0;
    }

    /**
     * Initialize.
     */
    public void initialize() {
        initializeList();
        initializeSel();
    }

    /**
     * Initialize list.
     */
    public void initializeList() {
        bmpList.flush(listBackground);
        bmpList.drawLine(0, 0, maxX, 0, Constants.INIT_LIST, listBarColor, foreground, false);
    }

    /**
     * Initialize list.
     *
     * @param text the text
     */
    public void initializeList(String text) {
        bmpList.flush(listBackground);
        String drawnText = text.replace(Constants.WIN_NEWLINE, Constants.ZERO_LENGTH_STRING);
        bmpList.drawLine(0, 0, maxX, 0, drawnText, listBarColor, foreground, false);
    }

    /**
     * Initialize sel.
     */
    public void initializeSel() {
        bmpSel.flush(selBackground);
        bmpSel.drawLine(0, 0, maxX, 0, Constants.INIT_SEL, selBarColor, selBarText, false);
    }

    /**
     * Initialize sel.
     *
     * @param text the text
     */
    public void initializeSel(String text) {
        bmpSel.flush(selBackground);
        String drawnText = text.replace(Constants.WIN_NEWLINE, Constants.ZERO_LENGTH_STRING);
        bmpSel.drawLine(0, 0, maxX, 0, drawnText, selBarColor, selBarText, false);
    }

    @Override
    public void showWelcome() {
        initialize();
        bmpList.drawSprite(0, Constants.BANNER, 1, 1, Sprite.W, foreground, foreground);
        bmpList.drawSprite(6, Constants.BANNER, 1, 1, Sprite.e, foreground, foreground);
        bmpList.drawSprite(12, Constants.BANNER, 1, 1, Sprite.l, foreground, foreground);
        bmpList.drawSprite(18, Constants.BANNER, 1, 1, Sprite.c, foreground, foreground);
        bmpList.drawSprite(24, Constants.BANNER, 1, 1, Sprite.o, foreground, foreground);
        bmpList.drawSprite(30, Constants.BANNER, 1, 1, Sprite.m, foreground, foreground);
        bmpList.drawSprite(36, Constants.BANNER, 1, 1, Sprite.e, foreground, foreground);
        bmpList.drawSprite(45, Constants.BANNER, 1, 1, Sprite.t, foreground, foreground);
        bmpList.drawSprite(51, Constants.BANNER, 1, 1, Sprite.o, foreground, foreground);
        bmpList.drawSprite(60, Constants.BANNER, 1, 1, Sprite.D, foreground, foreground);
        bmpList.drawSprite(66, Constants.BANNER, 1, 1, Sprite.o, foreground, foreground);
        bmpList.drawSprite(72, Constants.BANNER, 1, 1, Sprite.m, foreground, foreground);
        bmpList.drawSprite(78, Constants.BANNER, 1, 1, Sprite.S, foreground, foreground);
        bmpList.drawSprite(84, Constants.BANNER, 1, 1, Sprite.u, foreground, foreground);
        bmpList.drawSprite(90, Constants.BANNER, 1, 1, Sprite.n, foreground, foreground);
        draw();
    }

    /**
     * Show block.
     *
     * @param x             the x
     * @param y             the y
     * @param width         the width
     * @param height        the height
     * @param rawInput      the raw input
     * @param isDisplayMode the is display mode
     */
    public void showBlock(int x, int y, int width, int height, String rawInput, boolean isDisplayMode) {
        int startIndex = 0;
        int deltaY = 0;
        int myWidth = isDisplayMode ? width : maxX - x;
        int myHeight = isDisplayMode ? height : 1;
        int maxY = isDisplayMode ? maxListY : maxSelY;
        int maxLen = Math.min(myWidth * myHeight, rawInput.length());
        String input = rawInput.substring(0, maxLen);
        boolean isBroken = false;
        LOGGER.entering(getClass().getName(), "UI fancy logger");
        if (isDisplayMode) {
            if ((y - 1) / Constants.CELL_H % 2 == 0) { // even lines
                if (currentColor == 61 || currentColor == 55) {
                    currentColor = 23;
                } else if (currentColor == 23) {
                    currentColor = 29;
                } else {
                    currentColor = 23;
                }
            } else {
                if (currentColor == 23 || currentColor == 29) {
                    currentColor = 55;
                } else if (currentColor == 61) {
                    currentColor = 55;
                } else {
                    currentColor = 61;
                }
            }
        }
        while (startIndex + myWidth - 1 < input.length()) {
            if (y + deltaY > maxY) {
                isBroken = true;
                break;
            }
            String string = input.substring(startIndex, startIndex + myWidth);
            startIndex += myWidth;
            fillCellLine(x, y + deltaY, myWidth, isDisplayMode, string);
            deltaY++;
            if (deltaY >= myHeight) {
                isBroken = true;
                break;
            }
        }
        if (y + deltaY > maxY) {
            isBroken = true;
        }
        if (!isBroken) {
            String string = input.substring(startIndex);
            fillCellLine(x, y + deltaY, myWidth, isDisplayMode, string);
        }
    }

    private void fillCellLine(int x, int y, int width, boolean isDisplayMode, String string) {
        LOGGER.log(Level.INFO, "Successfully filled cell");
        Color backColor = isDisplayMode ? Color.getFromCode(currentColor) : selBackground;
        if (isDisplayMode) {
            bmpList.drawLine(x, y, x + width - 1, y, string, backColor, foreground, false);
        } else {
            bmpSel.drawLine(x, y, x + width - 1, y, string, backColor, foreground, false);
        }
    }

    /**
     * Show text.
     *
     * @param input         the input
     * @param isDisplayMode the is display mode
     * @param indexState    the index state
     */
    public void showText(String input, boolean isDisplayMode, MessageOptions indexState) {
        String wrappedText = wrapStringArray(input.split(Constants.WIN_NEWLINE));
        currentColor = 29;
        String[] lines = wrappedText.split(Constants.WIN_NEWLINE);
        if (lines.length == 0) {
            return;
        }
        if (isDisplayMode) {
            initializeList(lines[0]);
            bmpSel.drawLine(0, 0, maxX, 0,
                     "This command did not update the text region. Keeping the last content:",
                    selBarColor, selBarText, false);
        } else {
            initializeSel(lines[0]);
        }
        switch (indexState) {
        case INDEXED_NUM:
            for (int i = 1; i < lines.length; i++) {
                lines[i] = i + Constants.DOT + Constants.SPACE + lines[i];
            }
            break;
        case INDEXED_ABC:
            for (int i = 1; i < lines.length; i++) {
                lines[i] = (i + Constants.LETTER_OFFSET) + Constants.DOT + Constants.SPACE + lines[i];
            }
            break;
        default:
            break;
        }
        int y = Constants.BANNER;
        int x = 0;
        int maxY = isDisplayMode ? maxListY : maxSelY;
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            showBlock(x, y, Constants.CELL_W, Constants.CELL_H, line, isDisplayMode);
            if (isDisplayMode) {
                x += Constants.CELL_W;
                if (x + Constants.CELL_W - 1 > maxX) {
                    x = 0;
                    y += Constants.CELL_H;
                }
            } else {
                y++;
            }
            if (y > maxY) {
                break;
            }
        }
    }

    @Override
    public void showText(String input) {
        showText(input, false, MessageOptions.NOT_INDEXED);
    }

    /**
     * Update.
     *
     * @param input         the input
     * @param data         the data
     */
    public void update(String input, Data data) {
        selFlipped = false;
        listFlipped = false;
        String wrappedInput = wrapStringArray(input.split(Constants.WIN_NEWLINE));
        if (freshlySwitched) {
            String replay = data.lastInput;
            MessageOptions replayOption = data.lastIndexOption;
            if (replay == null || replay.equals(Constants.ZERO_LENGTH_STRING)) {
                showWelcome();
            } else {
                replay = "Keeping last output: " + Constants.WIN_NEWLINE + replay;
                boolean displayMode = replayOption != MessageOptions.NOT_INDEXED;
                separatePages(replay, displayMode);
                fixIndex();
                if (displayMode) {
                    initializeSel();
                } else {
                    refreshList(data);
                }
                showText(getShownText(displayMode), displayMode, replayOption);
                draw();
            }
            freshlySwitched = false;
            return;
        }
        if (wrappedInput.equals(Constants.ZERO_LENGTH_STRING)) {
            showText(Constants.ZERO_LENGTH_STRING);
        } else if (wrappedInput.contains(Constants.BMP_LIST_SWITCH)
                || wrappedInput.contains(Constants.BMP_SEL_SWITCH)) {
            flipPage(wrappedInput);
            if (!data.lastInput.equals(Constants.ZERO_LENGTH_STRING)) {
                if (listString.length > 0) {
                    String shownText = getShownText(true);
                    if (!listFlipped) {
                        shownText = shownText.replaceFirst(".*" + Constants.WIN_NEWLINE, Constants.ONLY_ONE_PAGE);
                    }
                    showText(shownText, true, MessageOptions.INDEXED_NUM);
                } else {
                    showText(Constants.ONLY_ONE_PAGE, true, MessageOptions.INDEXED_NUM);
                }
                if (selString.length > 0) {
                    String shownText = getShownText(false);
                    if (!selFlipped) {
                        shownText = shownText.replaceFirst(".*" + Constants.WIN_NEWLINE, Constants.ONLY_ONE_PAGE);
                    }
                    showText(shownText, false, MessageOptions.NOT_INDEXED);
                } else {
                    showText(Constants.ONLY_ONE_PAGE, false, MessageOptions.NOT_INDEXED);
                }
            }
        } else {
            boolean displayMode = data.indexOption != MessageOptions.NOT_INDEXED;
            if (!displayMode) {
                refreshList(data);
            }
            separatePages(wrappedInput, displayMode);
            fixIndex();
            showText(getShownText(displayMode), displayMode, data.indexOption);
            data.lastInput = wrappedInput;
            data.lastIndexOption = data.indexOption;
        }
        data.indexOption = MessageOptions.NOT_INDEXED;
        draw();
    }

    private void refreshList(Data data) {
        if (listString != null && listString.length > 0) {
            StringBuilder refreshBuilder = new StringBuilder(listString[0].split(Constants.WIN_NEWLINE)[0]);
            refreshBuilder.append(Constants.WIN_NEWLINE);
            for (Item item : data.target) {
                refreshBuilder.append(item.toString()).append(Constants.WIN_NEWLINE);
            }
            separatePages(refreshBuilder.toString(), true);
            fixIndex();
            showText(getShownText(true), true, MessageOptions.INDEXED_NUM);
        } else {
            initializeList();
        }
    }

    private void flipPage(String input) {
        boolean isList = input.contains(Constants.BMP_LIST_SWITCH);
        boolean isSel = input.contains(Constants.BMP_SEL_SWITCH);
        String number = input;
        if (isList) {
            number = number.replace(Constants.BMP_LIST_SWITCH, Constants.ZERO_LENGTH_STRING);
        }
        if (isSel) {
            number = number.replace(Constants.BMP_SEL_SWITCH, Constants.ZERO_LENGTH_STRING);
        }
        int num = Integer.parseInt(number.trim());
        int oldListIndex = listIndex;
        listIndex = flipNumber(listIndex, isList, num, listString.length);
        listFlipped = !isList || (oldListIndex != listIndex);
        int oldSelIndex = selIndex;
        selIndex = flipNumber(selIndex, isSel, num, selString.length);
        selFlipped = !isSel || (oldSelIndex != selIndex);
    }

    private int flipNumber(int currentNumber, boolean condition, int increment, int max) {
        int result = currentNumber;
        if (condition && max > 1) {
            result += increment;
            if (result > max - 1) {
                result -= max;
            } else if (result < 0) {
                result += max;
            }
        }
        return result;
    }

    private void fixIndex() {
        if (listString == null || listString.length == 0) {
            listIndex = 0;
        } else if (listIndex >= listString.length) {
            listIndex = listString.length - 1;
        }
        if (selString == null || selString.length == 0) {
            selIndex = 0;
        } else if (selIndex >= selString.length) {
            selIndex = selString.length - 1;
        }
    }

    /**
     * Gets shown text.
     *
     * @param isDisplayMode the is display mode
     * @return the shown text
     */
    String getShownText(boolean isDisplayMode) {
        if (isDisplayMode) {
            return listString[listIndex];
        } else {
            return selString[selIndex];
        }
    }

    private void draw() {
        stream.print(bmpList.get());
        stream.print(bmpSel.get());
    }

    private void separatePages(String input, boolean isDisplayMode) {
        if (input == null || input.equals(Constants.ZERO_LENGTH_STRING)) {
            return;
        }
        String[] lines = input.split(Constants.WIN_NEWLINE);
        int numLines = lines.length - 1;
        if (isDisplayMode) {
            int cellNum = (bmpList.width / Constants.CELL_W) * ((bmpList.height - 1) / Constants.CELL_H);
            if (numLines <= cellNum) {
                listString = new String[]{input};
                return;
            }
            int pages = (int) Math.ceil((double) numLines / (double) cellNum);
            listString = groupStrings(lines, pages, cellNum, true);
        } else {
            numLines++;
            int lineNum = bmpSel.height - 1;
            if (numLines <= lineNum) {
                selString = new String[]{input};
                return;
            }
            int pages = (int) Math.ceil((double) numLines / (double) lineNum);
            selString = groupStrings(lines, pages, lineNum, false);
        }
    }

    private boolean isIntString(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String[] groupStrings(String[] strings, int groups, int groupLength, boolean isDisplayMode) {
        StringBuilder[] builders = new StringBuilder[groups];
        String heading = isDisplayMode ? strings[0] : Constants.TEXT_HEADER;
        String paged = Constants.PAGED;
        if (isDisplayMode) {
            paged = paged.replace("next", "next i").replace("prev", "prev i");
        } else {
            paged = paged.replace("next", "next s").replace("prev", "prev s");
        }
        for (int i = 0; i < groups; i++) {
            String numString;
            String suffix;
            String[] heads = heading.replace(Constants.PARAM_SIGNATURE, Constants.LINE_UNIT)
                    .split(Constants.LINE_UNIT);
            numString = heads[heads.length - 1].replace(Constants.PARAM_RIGHT, Constants.LINE_UNIT)
                    .split(Constants.LINE_UNIT)[0];
            if (isIntString(numString)) {
                suffix = " (" + numString + ") " + paged;
            } else {
                suffix = heading.replace(heads[0], Constants.ZERO_LENGTH_STRING);
            }
            String bracket = Constants.SPACE + Constants.PARAM_LEFT + (i + 1) + Constants.PARAM_RIGHT + Constants.SPACE;
            builders[i] = new StringBuilder(heading.replace(suffix, Constants.ZERO_LENGTH_STRING))
                    .append(bracket).append(paged).append(Constants.WIN_NEWLINE);
        }
        for (int i = isDisplayMode ? 1 : 0; i < strings.length; i++) {
            int counter = isDisplayMode ? (i - 1) : i;
            int currentGroup = counter / groupLength;
            builders[currentGroup].append(strings[i]).append(Constants.WIN_NEWLINE);
        }
        String[] groupedStrings = new String[groups];
        for (int i = 0; i < groups; i++) {
            groupedStrings[i] = builders[i].toString();
        }
        return groupedStrings;
    }
}
