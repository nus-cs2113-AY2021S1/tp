package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Repeat {
    public static final String DAILY = "DAILY";
    public static final String WEEKLY = "WEEKLY";
    public static final String MONTHLY = "MONTHLY";

    private ArrayList<DateStatusPair> repeatList = new ArrayList<>();
    private String repeatType;

    public Repeat(LocalDate startDate, LocalTime startTime, String repeatType, int count) {
        for (int i = 0; i < count; i++) {
            LocalDate repeatDate;
            if (repeatType.equals(MONTHLY)) {
                repeatDate = startDate.plusMonths(1);
            } else if (repeatType.equals(WEEKLY)) {
                repeatDate = startDate.plusWeeks(1);
            } else {
                repeatDate = startDate.plusDays(1);
            }
            DateStatusPair pair = new DateStatusPair(repeatDate, startTime);
        }
        this.repeatType = repeatType;
    }

    public ArrayList<DateStatusPair> getRepeatList() {
        return repeatList;
    }

    public String getRepeatType() {
        return repeatType;
    }

    public int getRepeatCount() {
        return repeatList.size();
    }
}
