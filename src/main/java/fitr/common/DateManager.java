package fitr.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateManager {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
