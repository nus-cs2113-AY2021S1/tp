package seedu.planus;

import java.time.LocalDate;
import java.util.ArrayList;

public class DayStructure extends DisplayDateStructure {
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

}
