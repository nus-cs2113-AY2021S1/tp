package seedu.duke.spellchecker;

import java.util.ArrayList;

public class SpellChecker {
    private static Dictionary dict = Dictionary.getInstance();

    public static ArrayList<String> checkSpelling(String words) {
        ArrayList<String> wrongList = new ArrayList<>();
        String[] wordList = words.trim().split(" ");
        ArrayList<String> dictList = dict.getWordList();
        assert dictList.size() > 0;
        for (String word : wordList) {
            String wordToCheck = word.trim().toLowerCase();
            if ((!dictList.contains(wordToCheck)) && (isAlpha(wordToCheck))) {
                String wordNoS = removeS(wordToCheck);
                String wordNoES = removeES(wordToCheck);
                if ((!dictList.contains(wordNoS)) && (!dictList.contains(wordNoES))) {
                    wrongList.add(word);
                }
            }
        }
        if (wrongList.size() > 0) {
            System.out.println("Wrong words found:");
            System.out.println(wrongList);
        }
        return wrongList;
    }

    public static boolean isAlpha(String word) {
        return word.matches("[a-zA-Z]+");
    }

    public static String removeS(String str) {
        if (str != null && str.length() > 1 && str.charAt(str.length() - 1) == 's') {
            return str.substring(0, str.length() - 1);
        } else {
            return str;
        }
    }

    private static String removeES(String str) {
        if (str != null && str.length() > 2 && str.charAt(str.length() - 1) == 's'
                && str.charAt(str.length() - 2) == 'e') {
            return str.substring(0, str.length() - 2);
        } else {
            return str;
        }
    }
}
