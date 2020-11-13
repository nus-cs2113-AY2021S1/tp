package seedu.duke.writing;

import seedu.duke.constants.FluffleMessages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class contains a method to get the writings on a specific date and remind the writers about them.
 */
import static seedu.duke.constants.InputFormat.INPUT_DATE_FORMAT;

public class WritingReminder {
    private static ArrayList<Writings> writings = WritingList.writingList;

    /**
     * Gets the writings which are scheduled on a specific date specified by the command.
     *
     * @param command contains the remind keyword and date.
     */
    public static void filterWritingsOnADate(String command) {
        String[] words = command.split(" ", 2);
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
        try {
            LocalDate date = LocalDate.parse(words[1], DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
            ArrayList<Writings> writingsOnThisDate = (ArrayList<Writings>) writings.stream()
                    .filter((s) -> s.getReminderDate().compareTo(date) == 0)
                    .collect(Collectors.toList());
            printWritingsOnADate(date, writingsOnThisDate);
        } catch (DateTimeParseException e) {
            System.out.println(FluffleMessages.PARSE_DATETIME_EXCEPTION);
        }
    }

    private static void printWritingsOnADate(LocalDate date, ArrayList<Writings> writingsOnThisDate) {
        if (writingsOnThisDate.size() == 0) {
            System.out.println(FluffleMessages.NO_WRITING_DUE);
        } else {
            System.out.printf(FluffleMessages.CONTINUE_WRITINGS,
                    date.format(DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));
        }
        int filterCount = 1;
        for (Writings w : writingsOnThisDate) {
            System.out.println(filterCount + ".");
            w.printWritingsReminder();
            filterCount++;
        }
    }
}
