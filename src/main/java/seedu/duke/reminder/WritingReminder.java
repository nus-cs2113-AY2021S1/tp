package seedu.duke.reminder;

import seedu.duke.writing.WritingList;
import seedu.duke.writing.Writings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class WritingReminder {
    private static ArrayList<Writings> writings = WritingList.writinglist;

    public static void printReminderOnADay(String command) {
        String[] words = command.split(" ", 2);
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
        try {
            LocalDate date = LocalDate.parse(words[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ArrayList<Writings> writingsOnThisDate = (ArrayList<Writings>) writings.stream()
                    .filter((s) -> s.getReminderDate().compareTo(date) == 0)
                    .collect(Collectors.toList());
            if (writingsOnThisDate.size() == 0) {
                System.out.println("You don't have any writings due that day!!!");
            } else {
                System.out.printf("On %s, you should continue on the following writing(s):\n",
                        date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            int filterCount = 1;
            for (Writings w : writingsOnThisDate) {
                System.out.println(filterCount + ".");
                w.printWritingsReminder();
                filterCount++;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Cannot parse date and time. Please enter date in a correct format.");
        }
    }
}
