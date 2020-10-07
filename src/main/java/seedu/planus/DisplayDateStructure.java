package seedu.planus;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DisplayDateStructure {
    private static final TextStyle TEXT_STYLE = TextStyle.SHORT;
    private static final Locale LOCALE = Locale.ENGLISH;

    private LocalDate currentDate = LocalDate.now();
    private DayStructure day;
    protected int index = 0;

}
