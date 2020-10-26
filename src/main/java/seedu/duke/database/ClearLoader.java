package seedu.duke.database;

import seedu.duke.wordlist.WordList;
import seedu.duke.writing.WritingList;

public class ClearLoader {

    public static void clearItems(String userInput, WritingList writings) throws AssertionError {
        String type;
        String item;
        String[] words = userInput.split(" ");
        if (!(words.length == 3)) {
            throw new AssertionError();
        } else {
            type = getType(words[1]);
            item = getItem(words[2]);
            processing(type, item, writings);
        }
    }

    public static String getType(String rawType) {
        assert (rawType.contains("type\\")) : "Your format is incorrect";
        return rawType.substring("type\\".length()).trim();
    }

    public static String getItem(String rawItem) {
        assert (rawItem.contains("item\\")) : "Your format is incorrect";
        return rawItem.substring("item\\".length()).trim();
    }

    public static void processing(String type, String item, WritingList writings) {
        if (type == "writing") {
            processingItem(item, writings);
        }
    }

    /**
     * To process the removal of the item from writing list.
     *
     * @param item the item Identity to be removed
     * @param writings the writing list to be affected
     * @throws NumberFormatException indicate that the item is not an integer number
     */
    public static void processingItem(String item, WritingList writings) throws NumberFormatException {
        int element;
        if (item.contains("-id")) {
            element = Integer.parseInt(item.substring("-id".length()));
            writings.removeID(element);
        } else {
            element = Integer.parseInt(item);
            writings.remove(element);
        }
        throw new NumberFormatException();
    }

}
