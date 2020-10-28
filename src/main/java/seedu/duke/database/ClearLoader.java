package seedu.duke.database;

import seedu.duke.exceptions.ClearLoaderException;
import seedu.duke.exceptions.WrongClearCommandFormat;
import seedu.duke.writing.WritingList;

public class ClearLoader {

    public static void clearItems(String userInput, WritingList writings) throws WrongClearCommandFormat {
        String type;
        String item;
        String[] words = userInput.split(" ");
        if (!(words.length == 3)) {
            throw new WrongClearCommandFormat();
        } else {
            type = getType(words[1]);
            item = getItem(words[2]);
            try {
                processing(type, item, writings);
            } catch (ClearLoaderException e) {
                System.out.println("The format is not correct!");
            }
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

    public static void processing(String type, String item, WritingList writings) throws ClearLoaderException {
        try {
            System.out.println(type);
            if (type.equals("writing")) {
                System.out.println(type);
                processingItem(item, writings);
            } else {
                throw new ClearLoaderException();
            }
        } catch (NumberFormatException e) {
            System.out.println("The format of item number is inappropriate");
        }
    }

    /**
     * To process the removal of the item from writing list.
     *
     * @param item the item Identity to be removed
     * @param writings the writing list to be affected
     * @throws NumberFormatException indicate that the item is not an integer number
     */
    public static void processingItem(String item, WritingList writings) {
        int element;
        if (item.contains("-id")) {
            String nearFilted = item.substring("-id".length());
            try {
                element = Integer.parseInt(nearFilted);
                System.out.println("We have removed this item");
                writings.removeID(element);
            } catch (NumberFormatException e) {
                System.out.println("Your item format is not an integer!");
            }
        } else {
            try {
                element = Integer.parseInt(item);
                System.out.println("We have removed this item");
                writings.removeWriting(element);
            } catch (NumberFormatException e) {
                System.out.println("Your item format is not an integer!");
            }
        }
    }

}
