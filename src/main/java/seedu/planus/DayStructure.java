package seedu.planus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DayStructure extends DisplayDateStructure {
    private static final int HASH_NUM_DIGITS = 10000;
    private LocalDate day;
    private ArrayList<Task> tasks = new ArrayList<>();

    public DayStructure(LocalDate date, ArrayList<Task> tasks) {
        day = date;
        for (Task t : tasks) {
            if (t.getDate().equals(date)) {
                this.tasks.add(t);
            }
        }
    }

    public String toString() {
        StringBuilder resultString = new StringBuilder(day.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
                + System.lineSeparator());

        // Header
        resultString
                .append("\t")
                .append("Code")
                .append(" ")
                .append("Description")
                .append(System.lineSeparator());

        for (Task t : tasks) {
            resultString
                    .append("\t")
                    .append(++index)
                    .append(".\t  ")
                    .append(t.getDescription())
                    .append(System.lineSeparator());
        }
        return resultString.toString();
    }

}
