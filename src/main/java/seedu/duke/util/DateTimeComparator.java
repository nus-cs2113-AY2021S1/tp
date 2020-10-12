package seedu.duke.util;

import java.time.LocalDateTime;
import java.util.Comparator;

public class DateTimeComparator implements Comparator<LocalDateTime> {
    @Override
    public int compare(LocalDateTime o1, LocalDateTime o2) {
        return o1.compareTo(o2);
    }
}
