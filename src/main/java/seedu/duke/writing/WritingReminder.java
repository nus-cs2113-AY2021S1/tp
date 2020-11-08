package seedu.duke.writing;

import seedu.duke.constants.FluffleMessages;
import seedu.duke.exceptions.writingexceptions.InvalidReminderDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.duke.constants.InputFormat.INPUT_DATE_FORMAT;

public class WritingReminder {
    private static ArrayList<Writings> writings = WritingList.writingList;

    public static void filterWritingsOnADate(String command) {
        String[] words = command.split(" ", 2);
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
        try {
            LocalDate today = LocalDate.now();
            LocalDate date = LocalDate.parse(words[1], DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
            if (date.isBefore(today)) {
                throw new InvalidReminderDateException();
            }
            ArrayList<Writings> writingsOnThisDate = (ArrayList<Writings>) writings.stream()
                    .filter((s) -> s.getReminderDate().compareTo(date) == 0)
                    .collect(Collectors.toList());
            printWritingsOnADate(date, writingsOnThisDate);
        } catch (DateTimeParseException e) {
            System.out.println(FluffleMessages.PARSE_DATETIME_EXCEPTION);
        } catch (InvalidReminderDateException e) {
            System.out.println(FluffleMessages.INVALID_REMINDER_DATE_EXCEPTION);
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
